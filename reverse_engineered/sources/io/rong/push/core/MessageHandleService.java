package io.rong.push.core;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.framework.ui.android.WebActivity;
import com.xiaomi.mipush.sdk.MiPushMessage;
import io.rong.push.PushConst;
import io.rong.push.RongPushClient;
import io.rong.push.RongPushClient.ConversationType;
import io.rong.push.common.RLog;
import io.rong.push.notification.PushMessageReceiver;
import io.rong.push.notification.PushNotificationMessage;
import io.rong.push.notification.RongNotificationInterface;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageHandleService extends IntentService {
    private static final String TAG = "MsgHandleService";
    private static ConcurrentLinkedQueue<Job> jobQueue = new ConcurrentLinkedQueue();

    public static class Job {
        private Intent intent;
        private PushMessageReceiver receiver;

        public Job(Intent intent, PushMessageReceiver pushMessageReceiver) {
            this.receiver = pushMessageReceiver;
            this.intent = intent;
        }

        public PushMessageReceiver getReceiver() {
            return this.receiver;
        }

        public Intent getIntent() {
            return this.intent;
        }
    }

    public static void addJob(Job job) {
        if (job != null) {
            jobQueue.add(job);
        }
    }

    public MessageHandleService() {
        super("MessageHandleThread");
    }

    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            RLog.i(TAG, "onHandleIntent " + intent);
            Job job = (Job) jobQueue.poll();
            if (job == null) {
                RLog.e(TAG, "Can not find receiver job. Current process id is " + Process.myPid());
                return;
            }
            Intent intent2 = job.getIntent();
            RLog.d(TAG, "Handle Job deliveredIntent " + intent2);
            if (intent2 == null || intent2.getAction() == null) {
                RLog.e(TAG, "Can not find intent in job. Current process id is " + Process.myPid());
                return;
            }
            PushMessageReceiver receiver = job.getReceiver();
            Bundle extras = intent2.getExtras();
            PushNotificationMessage decodeNotificationMessage;
            if (intent2.getAction().equals(PushConst.ACTION_RONG_PUSH_MESSAGE_ARRIVED)) {
                decodeNotificationMessage = decodeNotificationMessage(extras);
                if (decodeNotificationMessage != null && !receiver.onNotificationMessageArrived(this, decodeNotificationMessage)) {
                    RLog.d(TAG, "sendNotification");
                    RongNotificationInterface.sendNotification(this, decodeNotificationMessage);
                }
            } else if (intent2.getAction().equals(PushConst.ACTION_MI_PUSH_MESSAGE_ARRIVED)) {
                try {
                    decodeNotificationMessage = transformToPushMessage(new JSONObject(((MiPushMessage) intent2.getSerializableExtra(AVStatus.MESSAGE_TAG)).getContent()));
                    if (decodeNotificationMessage != null) {
                        receiver.onNotificationMessageArrived(this, decodeNotificationMessage);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else if (intent2.getAction().equals(PushConst.ACTION_PUSH_MESSAGE_CLICKED)) {
                decodeNotificationMessage = (PushNotificationMessage) intent2.getParcelableExtra(AVStatus.MESSAGE_TAG);
                if (decodeNotificationMessage != null) {
                    if (!TextUtils.isEmpty(decodeNotificationMessage.getPushId())) {
                        RongPushClient.recordNotificationEvent(decodeNotificationMessage.getPushId());
                    }
                    if (!receiver.onNotificationMessageClicked(this, decodeNotificationMessage)) {
                        ConversationType conversationType = decodeNotificationMessage.getConversationType();
                        String objectName = decodeNotificationMessage.getObjectName();
                        String pushFlag = decodeNotificationMessage.getPushFlag();
                        if (conversationType != null && conversationType.equals(ConversationType.PUSH_SERVICE)) {
                            startPushServiceActivity(decodeNotificationMessage.getTargetId(), decodeNotificationMessage.getPushContent(), decodeNotificationMessage.getPushData(), decodeNotificationMessage.getPushId(), decodeNotificationMessage.getExtra(), pushFlag);
                        } else if (objectName != null && (objectName.equals("RC:VCInvite") || objectName.equals("RC:VCModifyMem"))) {
                            startConversationListActivity(pushFlag);
                        } else if (Boolean.valueOf(intent2.getBooleanExtra("isMulti", false)).booleanValue()) {
                            startConversationListActivity(pushFlag);
                        } else {
                            startConversationActivity(decodeNotificationMessage.getConversationType(), decodeNotificationMessage.getTargetId(), decodeNotificationMessage.getTargetUserName(), pushFlag);
                        }
                    }
                }
            } else if (intent2.getAction().equals(PushConst.ACTION_MI_PUSH_MESSAGE_CLICKED)) {
                try {
                    decodeNotificationMessage = transformToPushMessage(new JSONObject(((MiPushMessage) intent2.getSerializableExtra(AVStatus.MESSAGE_TAG)).getContent()));
                    if (decodeNotificationMessage != null) {
                        if (!TextUtils.isEmpty(decodeNotificationMessage.getPushId())) {
                            RongPushClient.recordNotificationEvent(decodeNotificationMessage.getPushId());
                        }
                        if (!receiver.onNotificationMessageClicked(this, decodeNotificationMessage)) {
                            ConversationType conversationType2 = decodeNotificationMessage.getConversationType();
                            if (conversationType2 == null || !conversationType2.equals(ConversationType.PUSH_SERVICE)) {
                                startConversationListActivity(decodeNotificationMessage.getPushFlag());
                                return;
                            }
                            startPushServiceActivity(decodeNotificationMessage.getTargetId(), decodeNotificationMessage.getPushContent(), decodeNotificationMessage.getPushData(), decodeNotificationMessage.getPushId(), decodeNotificationMessage.getExtra(), decodeNotificationMessage.getPushFlag());
                        }
                    }
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private void startConversationListActivity(String str) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        Builder buildUpon = Uri.parse("rong://" + getPackageName()).buildUpon();
        buildUpon.appendPath("conversationlist");
        buildUpon.appendQueryParameter("isFromPush", str);
        intent.setData(buildUpon.build());
        startActivity(intent);
    }

    private void startConversationActivity(ConversationType conversationType, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        Builder buildUpon = Uri.parse("rong://" + getPackageName()).buildUpon();
        buildUpon.appendPath("conversation").appendPath(conversationType.getName()).appendQueryParameter("targetId", str).appendQueryParameter(WebActivity.EXTRA_TITLE, str2).appendQueryParameter("isFromPush", str3);
        intent.setData(buildUpon.build());
        startActivity(intent);
    }

    private void startPushServiceActivity(String str, String str2, String str3, String str4, String str5, String str6) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        Builder buildUpon = Uri.parse("rong://" + getPackageName()).buildUpon();
        buildUpon.appendPath("push_message").appendQueryParameter("targetId", str).appendQueryParameter("pushContent", str2).appendQueryParameter("pushData", str3).appendQueryParameter("pushId", str4).appendQueryParameter("extra", str5).appendQueryParameter("isFromPush", str6);
        intent.setData(buildUpon.build());
        startActivity(intent);
    }

    private PushNotificationMessage decodeNotificationMessage(Bundle bundle) {
        Uri uri = null;
        if (bundle.getInt("conversationType") == 0) {
            RLog.e(TAG, "onReceive, conversationType is 0");
            return null;
        }
        Object string = getSharedPreferences("Statistics", 0).getString("userId", "");
        ConversationType value = ConversationType.setValue(bundle.getInt("conversationType"));
        if (value == null || value.equals(ConversationType.PUSH_SERVICE) || value.equals(ConversationType.SYSTEM) || (!TextUtils.isEmpty(string) && string.equals(bundle.getString("toId")))) {
            PushNotificationMessage pushNotificationMessage = new PushNotificationMessage();
            pushNotificationMessage.setReceivedTime(bundle.getLong("receivedTime"));
            pushNotificationMessage.setConversationType(value);
            pushNotificationMessage.setObjectName(bundle.getString("objectName"));
            pushNotificationMessage.setSenderId(bundle.getString("senderId"));
            pushNotificationMessage.setSenderName(bundle.getString("senderName"));
            if (!TextUtils.isEmpty(bundle.getString("senderUri"))) {
                uri = Uri.parse(bundle.getString("senderUri"));
            }
            pushNotificationMessage.setSenderPortrait(uri);
            pushNotificationMessage.setTargetId(bundle.getString("targetId"));
            pushNotificationMessage.setTargetUserName(bundle.getString("targetUserName"));
            pushNotificationMessage.setPushId(bundle.getString("pushId"));
            pushNotificationMessage.setPushContent(bundle.getString("pushContent"));
            pushNotificationMessage.setPushTitle(bundle.getString("pushTitle"));
            pushNotificationMessage.setPushData(bundle.getString("pushData"));
            pushNotificationMessage.setExtra(bundle.getString("extra"));
            pushNotificationMessage.setPushFlag("true");
            return pushNotificationMessage;
        }
        RLog.e(TAG, "The userId isn't matched. Return directly!!");
        return null;
    }

    private PushNotificationMessage transformToPushMessage(JSONObject jSONObject) {
        ConversationType value;
        String str;
        JSONException e;
        if (jSONObject == null) {
            return null;
        }
        int parseInt;
        Uri parse;
        JSONObject jSONObject2;
        Object string;
        PushNotificationMessage pushNotificationMessage = new PushNotificationMessage();
        Object optString = jSONObject.optString("channelType");
        if (!TextUtils.isEmpty(optString)) {
            try {
                parseInt = Integer.parseInt(optString);
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
            }
            value = ConversationType.setValue(parseInt);
            pushNotificationMessage.setConversationType(value);
            if (!value.equals(ConversationType.DISCUSSION) || value.equals(ConversationType.GROUP) || value.equals(ConversationType.CHATROOM)) {
                pushNotificationMessage.setTargetId(jSONObject.optString("channelId"));
                pushNotificationMessage.setTargetUserName(jSONObject.optString("channelName"));
            } else {
                pushNotificationMessage.setTargetId(jSONObject.optString("fromUserId"));
                pushNotificationMessage.setTargetUserName(jSONObject.optString("fromUserName"));
            }
            pushNotificationMessage.setReceivedTime(jSONObject.optLong("timeStamp"));
            pushNotificationMessage.setObjectName(jSONObject.optString("objectName"));
            pushNotificationMessage.setSenderId(jSONObject.optString("fromUserId"));
            pushNotificationMessage.setSenderName(jSONObject.optString("fromUserName"));
            if (TextUtils.isEmpty(jSONObject.optString("fromUserPo"))) {
                parse = Uri.parse(jSONObject.optString("fromUserPo"));
            } else {
                parse = null;
            }
            pushNotificationMessage.setSenderPortrait(parse);
            pushNotificationMessage.setPushTitle(jSONObject.optString(WebActivity.EXTRA_TITLE));
            pushNotificationMessage.setPushContent(jSONObject.optString("content"));
            pushNotificationMessage.setPushData(jSONObject.optString("appData"));
            pushNotificationMessage.setPushFlag("true");
            str = "";
            jSONObject2 = jSONObject.getJSONObject("rc");
            optString = jSONObject2.optString("tId");
            pushNotificationMessage.setPushId(jSONObject2.optString("id"));
            if (jSONObject2.has("ext") && jSONObject2.getJSONObject("ext") != null) {
                pushNotificationMessage.setExtra(jSONObject2.getJSONObject("ext").toString());
            }
            string = getSharedPreferences("Statistics", 0).getString("userId", "");
            if (value.equals(ConversationType.PUSH_SERVICE) || value.equals(ConversationType.SYSTEM) || (!TextUtils.isEmpty(string) && string.equals(r0))) {
                return pushNotificationMessage;
            }
            RLog.e(TAG, "The userId isn't matched. Return directly!!");
            return null;
        }
        parseInt = 0;
        value = ConversationType.setValue(parseInt);
        pushNotificationMessage.setConversationType(value);
        if (value.equals(ConversationType.DISCUSSION)) {
        }
        pushNotificationMessage.setTargetId(jSONObject.optString("channelId"));
        pushNotificationMessage.setTargetUserName(jSONObject.optString("channelName"));
        pushNotificationMessage.setReceivedTime(jSONObject.optLong("timeStamp"));
        pushNotificationMessage.setObjectName(jSONObject.optString("objectName"));
        pushNotificationMessage.setSenderId(jSONObject.optString("fromUserId"));
        pushNotificationMessage.setSenderName(jSONObject.optString("fromUserName"));
        if (TextUtils.isEmpty(jSONObject.optString("fromUserPo"))) {
            parse = Uri.parse(jSONObject.optString("fromUserPo"));
        } else {
            parse = null;
        }
        pushNotificationMessage.setSenderPortrait(parse);
        pushNotificationMessage.setPushTitle(jSONObject.optString(WebActivity.EXTRA_TITLE));
        pushNotificationMessage.setPushContent(jSONObject.optString("content"));
        pushNotificationMessage.setPushData(jSONObject.optString("appData"));
        pushNotificationMessage.setPushFlag("true");
        str = "";
        try {
            jSONObject2 = jSONObject.getJSONObject("rc");
            optString = jSONObject2.optString("tId");
            try {
                pushNotificationMessage.setPushId(jSONObject2.optString("id"));
                pushNotificationMessage.setExtra(jSONObject2.getJSONObject("ext").toString());
            } catch (JSONException e3) {
                e = e3;
                e.printStackTrace();
                string = getSharedPreferences("Statistics", 0).getString("userId", "");
                if (!value.equals(ConversationType.PUSH_SERVICE)) {
                }
                return pushNotificationMessage;
            }
        } catch (JSONException e4) {
            JSONException jSONException = e4;
            optString = str;
            e = jSONException;
            e.printStackTrace();
            string = getSharedPreferences("Statistics", 0).getString("userId", "");
            if (value.equals(ConversationType.PUSH_SERVICE)) {
            }
            return pushNotificationMessage;
        }
        string = getSharedPreferences("Statistics", 0).getString("userId", "");
        if (value.equals(ConversationType.PUSH_SERVICE)) {
        }
        return pushNotificationMessage;
    }
}
