package io.rong.imlib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.packet.C0861d;
import com.avos.avoscloud.AVUser.AVThirdPartyUserAuth;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import io.rong.common.FileUtils;
import io.rong.common.RFLog;
import io.rong.common.RLog;
import io.rong.imlib.NativeObject.AccountInfo;
import io.rong.imlib.NativeObject.AccountInfoListener;
import io.rong.imlib.NativeObject.BizAckListener;
import io.rong.imlib.NativeObject.ChatroomInfoListener;
import io.rong.imlib.NativeObject.ConnectAckCallback;
import io.rong.imlib.NativeObject.CreateDiscussionCallback;
import io.rong.imlib.NativeObject.DiscussionInfo;
import io.rong.imlib.NativeObject.DiscussionInfoListener;
import io.rong.imlib.NativeObject.ExceptionListener;
import io.rong.imlib.NativeObject.HistoryMessageListener;
import io.rong.imlib.NativeObject.PublishAckListener;
import io.rong.imlib.NativeObject.PushSettingListener;
import io.rong.imlib.NativeObject.ReceiveMessageListener;
import io.rong.imlib.NativeObject.SetBlacklistListener;
import io.rong.imlib.NativeObject.TokenListener;
import io.rong.imlib.NativeObject.UserInfo;
import io.rong.imlib.filetransfer.Configuration.Builder;
import io.rong.imlib.filetransfer.FileTransferClient;
import io.rong.imlib.filetransfer.FtConst.MimeType;
import io.rong.imlib.filetransfer.FtConst.ServiceType;
import io.rong.imlib.filetransfer.FtUtilities;
import io.rong.imlib.filetransfer.RequestCallBack;
import io.rong.imlib.filetransfer.RequestOption;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationErrorCode;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationStatus;
import io.rong.imlib.location.RealTimeLocationManager;
import io.rong.imlib.model.ChatRoomInfo;
import io.rong.imlib.model.ChatRoomMemberInfo;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.imlib.model.Message$ReceivedStatus;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.PublicServiceProfileList;
import io.rong.imlib.model.UnknownMessage;
import io.rong.imlib.model.UserData;
import io.rong.imlib.navigation.NavigationCallback;
import io.rong.imlib.navigation.NavigationClient;
import io.rong.imlib.navigation.NavigationObserver;
import io.rong.imlib.navigation.PCAuthConfig;
import io.rong.message.DiscussionNotificationMessage;
import io.rong.message.IHandleMessageListener;
import io.rong.message.LocationMessage;
import io.rong.message.MessageHandler;
import io.rong.message.TextMessage;
import java.lang.reflect.Constructor;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

public final class NativeClient {
    private static final String TAG = "NativeClient";
    private static NativeClient client = null;
    private static HashMap<String, Constructor<? extends MessageContent>> constructorMap = new HashMap();
    private static HashMap<String, Constructor<? extends MessageHandler>> messageHandlerMap = new HashMap();
    protected static NativeObject nativeObj;
    private String appKey;
    private String currentUserId;
    private String deviceId;
    private Context mContext = null;
    private String mFileServer;
    private String mNaviServer;
    private volatile RealTimeLocationManager mRealTimeLocationManager;
    private OnReceiveMessageListener mReceiveMessageListener;
    private OnReceiveMessageListenerEx mReceiveMessageListenerEx;
    Timer timer;
    private String token;

    public interface IResultCallback<T> {
        void onError(int i);

        void onSuccess(T t);
    }

    public interface OperationCallback {
        void onError(int i);

        void onSuccess();
    }

    public interface GetNotificationQuietHoursCallback {
        void onError(int i);

        void onSuccess(String str, int i);
    }

    public interface IResultProgressCallback<T> {
        void onError(int i);

        void onProgress(int i);

        void onSuccess(T t);
    }

    public interface RealTimeLocationListener {
        void onError(RealTimeLocationErrorCode realTimeLocationErrorCode);

        void onParticipantsJoin(String str);

        void onParticipantsQuit(String str);

        void onReceiveLocation(double d, double d2, String str);

        void onStatusChange(RealTimeLocationStatus realTimeLocationStatus);
    }

    public interface ICodeListener {
        void onChanged(int i);
    }

    public interface OnReceiveMessageListener {
        void onReceived(Message message, int i, boolean z);
    }

    public interface ISendMessageCallback<T> {
        void onAttached(T t);

        void onError(T t, int i);

        void onSuccess(T t);
    }

    public enum BlacklistStatus {
        EXIT_BLACK_LIST(0),
        NOT_EXIT_BLACK_LIST(1);
        
        private int value;

        private BlacklistStatus(int i) {
            this.value = 1;
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static BlacklistStatus setValue(int i) {
            for (BlacklistStatus blacklistStatus : values()) {
                if (i == blacklistStatus.getValue()) {
                    return blacklistStatus;
                }
            }
            return NOT_EXIT_BLACK_LIST;
        }
    }

    public interface ICodeCallback {
        void onResult(int i);
    }

    public interface IResultSendMessageCallback<T> {
        void onError(T t, int i);

        void onSuccess(T t);
    }

    public interface OnReceiveMessageListenerEx {
        boolean onReceived(Message message, int i);
    }

    private MessageContent renderMessageContent(String str, byte[] bArr, Message message) {
        Exception e;
        Constructor constructor = (Constructor) constructorMap.get(str);
        if (constructor == null) {
            return new UnknownMessage(bArr);
        }
        MessageContent messageContent;
        try {
            messageContent = (MessageContent) constructor.newInstance(new Object[]{bArr});
            try {
                Constructor constructor2 = (Constructor) messageHandlerMap.get(str);
                if (constructor2 != null) {
                    ((MessageHandler) constructor2.newInstance(new Object[]{this.mContext})).decodeMessage(message, messageContent);
                    return messageContent;
                }
                RLog.m19420e(TAG, "renderMessageContent 该消息未注册，请调用registerMessageType方法注册。");
                return messageContent;
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return messageContent;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            messageContent = null;
            e = exception;
            e.printStackTrace();
            return messageContent;
        }
    }

    private NativeClient() {
    }

    public static NativeClient getInstance() {
        if (client == null) {
            client = new NativeClient();
        }
        return client;
    }

    public void init(Context context, String str, String str2) {
        this.mContext = context.getApplicationContext();
        this.appKey = str;
        this.deviceId = str2;
        nativeObj = new NativeObject();
        RLog.m19419d(TAG, "init result = " + nativeObj.InitClient(str, context.getPackageName(), str2, context.getFilesDir().getPath(), FileUtils.getCachePath(context, "ronglog")));
        this.mRealTimeLocationManager = RealTimeLocationManager.init(context);
        FileTransferClient.init(new Builder().serverType(ServiceType.QI_NIU).build());
    }

    public static void registerMessageType(Class<? extends MessageContent> cls) throws AnnotationNotFoundException {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (cls == null) {
            throw new IllegalArgumentException("MessageContent 为空！");
        } else {
            MessageTag messageTag = (MessageTag) cls.getAnnotation(MessageTag.class);
            if (messageTag != null) {
                String value = messageTag.value();
                int flag = messageTag.flag();
                try {
                    Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{byte[].class});
                    Constructor constructor = messageTag.messageHandler().getConstructor(new Class[]{Context.class});
                    constructorMap.put(value, declaredConstructor);
                    messageHandlerMap.put(value, constructor);
                    nativeObj.RegisterMessageType(value, flag);
                    return;
                } catch (NoSuchMethodException e) {
                    throw new AnnotationNotFoundException();
                }
            }
            throw new AnnotationNotFoundException();
        }
    }

    public void connect(final String str, final IResultCallback<String> iResultCallback) throws Exception {
        RLog.m19422i(TAG, "connect");
        this.token = str;
        setEnvInfo(this.mContext);
        RFLog.uploadIfNeed(this.mContext);
        NavigationClient.getInstance().addObserver(new NavigationObserver() {
            public void onSuccess(String str) {
                RLog.m19422i(NativeClient.TAG, "connect onSuccess - " + str);
                NativeClient.this.internalConnect(str, str.substring(0, str.indexOf(":")), Integer.parseInt(str.substring(str.indexOf(":") + 1)), iResultCallback);
            }

            public void onError(String str, int i) {
                RLog.m19420e(NativeClient.TAG, "connect onError - " + i);
                if (!TextUtils.isEmpty(str)) {
                    NativeClient.this.internalConnect(str, str.substring(0, str.indexOf(":")), Integer.parseInt(str.substring(str.indexOf(":") + 1)), iResultCallback);
                } else if (iResultCallback != null) {
                    iResultCallback.onError(i);
                }
                NavigationClient.getInstance().clearObserver();
            }

            public void onReconnect(String str, final NavigationCallback navigationCallback) {
                RLog.m19420e(NativeClient.TAG, "connect onReconnect - " + str);
                NativeClient.nativeObj.Connect(str, str.substring(0, str.indexOf(":")), Integer.parseInt(str.substring(str.indexOf(":") + 1)), new ConnectAckCallback() {
                    public void operationComplete(int i, String str) {
                        RLog.m19419d(NativeClient.TAG, "reconnect operationComplete : state = " + i);
                        if (navigationCallback == null) {
                            return;
                        }
                        if (i == 0) {
                            navigationCallback.onSuccess();
                        } else {
                            navigationCallback.onError();
                        }
                    }
                });
                NavigationClient.getInstance().clearObserver();
            }
        });
        NavigationClient.getInstance().getCMPServer(this.mContext, this.appKey, str);
    }

    private void internalConnect(final String str, String str2, int i, final IResultCallback<String> iResultCallback) {
        nativeObj.Connect(str, str2, i, new ConnectAckCallback() {
            public void operationComplete(int i, String str) {
                RLog.m19419d(NativeClient.TAG, "connect operationComplete : state = " + i);
                if (i == 31006 || i == 33003) {
                    NavigationClient.getInstance().clearCache(NativeClient.this.mContext);
                    RLog.m19420e(NativeClient.TAG, "internalConnect status = " + i);
                }
                if (i == 30001 || i == 30002 || i == 31000 || i == 30014 || i == 30010 || i == 30011) {
                    NavigationClient.getInstance().updateCacheTime(NativeClient.this.mContext);
                    RLog.m19420e(NativeClient.TAG, "internalConnect status = " + i);
                }
                if (i == 0) {
                    NativeClient.this.currentUserId = str;
                    if (iResultCallback != null) {
                        iResultCallback.onSuccess(str);
                    }
                    if (!NavigationClient.getInstance().needUpdateCMP(NativeClient.this.mContext, NativeClient.this.appKey, str)) {
                        NavigationClient.getInstance().clearObserver();
                        return;
                    }
                    return;
                }
                RLog.m19420e(NativeClient.TAG, "internalConnect status = " + i);
                NativeClient.this.currentUserId = NativeClient.nativeObj.GetUserIdByToken(str);
                if (iResultCallback != null) {
                    iResultCallback.onError(i);
                }
                NavigationClient.getInstance().clearObserver();
            }
        });
    }

    public String getCurrentUserId() {
        return this.currentUserId;
    }

    public void disconnect() {
        disconnect(true);
    }

    public void disconnect(boolean z) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        }
        RLog.m19422i(TAG, "disconnect isReceivePush : " + z);
        nativeObj.Disconnect(z ? 2 : 4);
    }

    public List<Conversation> getConversationList() {
        int[] iArr = new int[]{ConversationType.PRIVATE.getValue(), ConversationType.DISCUSSION.getValue(), ConversationType.GROUP.getValue(), ConversationType.SYSTEM.getValue()};
        if (nativeObj != null) {
            return getConversationList(iArr);
        }
        throw new RuntimeException("NativeClient 尚未初始化!");
    }

    public List<Conversation> getGroupConversationList() {
        int[] iArr = new int[]{ConversationType.GROUP.getValue()};
        if (nativeObj != null) {
            return getConversationList(iArr);
        }
        throw new RuntimeException("NativeClient 尚未初始化!");
    }

    public List<Conversation> getConversationList(int[] iArr) {
        NativeObject.Conversation[] GetConversationListEx;
        List<Conversation> list = null;
        try {
            GetConversationListEx = nativeObj.GetConversationListEx(iArr);
        } catch (Exception e) {
            e.printStackTrace();
            GetConversationListEx = null;
        }
        if (GetConversationListEx != null) {
            list = new ArrayList();
            for (NativeObject.Conversation renderConversationFromNative : GetConversationListEx) {
                list.add(renderConversationFromNative(renderConversationFromNative));
            }
        }
        return list;
    }

    private Conversation renderConversationFromNative(NativeObject.Conversation conversation) {
        Conversation conversation2 = new Conversation();
        conversation2.setTargetId(conversation.getTargetId());
        conversation2.setLatestMessageId(conversation.getMessageId());
        conversation2.setConversationTitle(conversation.getConversationTitle());
        conversation2.setUnreadMessageCount(conversation.getUnreadMessageCount());
        conversation2.setConversationType(ConversationType.setValue(conversation.getConversationType()));
        conversation2.setTop(conversation.isTop());
        conversation2.setObjectName(conversation.getObjectName());
        if (conversation.getMessageId() > 0) {
            Message message = new Message();
            message.setMessageId(conversation.getMessageId());
            message.setSenderUserId(conversation.getSenderUserId());
            conversation2.setLatestMessage(renderMessageContent(conversation.getObjectName(), conversation.getContent(), message));
        }
        conversation2.setReceivedStatus(new Message$ReceivedStatus(conversation.getReadStatus()));
        conversation2.setReceivedTime(conversation.getReceivedTime());
        conversation2.setSentTime(conversation.getSentTime());
        conversation2.setSenderUserId(conversation.getSenderUserId());
        conversation2.setSentStatus(Message$SentStatus.setValue(conversation.getSentStatus()));
        conversation2.setSenderUserName(conversation.getSenderName());
        conversation2.setDraft(conversation.getDraft());
        conversation2.setPortraitUrl(conversation.getPortraitUrl());
        conversation2.setNotificationStatus(conversation.isBlockPush() ? ConversationNotificationStatus.DO_NOT_DISTURB : ConversationNotificationStatus.NOTIFY);
        conversation2.setMentionedCount(conversation.getMentionCount());
        return conversation2;
    }

    private Conversation renderConversationFromJson(JSONObject jSONObject) {
        boolean z = true;
        Conversation conversation = new Conversation();
        conversation.setTargetId(jSONObject.optString("target_id"));
        conversation.setLatestMessageId(jSONObject.optInt("last_message_id"));
        conversation.setConversationTitle(jSONObject.optString("conversation_title"));
        conversation.setUnreadMessageCount(jSONObject.optInt("unread_count"));
        conversation.setConversationType(ConversationType.setValue(jSONObject.optInt("conversation_category")));
        if (jSONObject.optInt("is_top") != 1) {
            z = false;
        }
        conversation.setTop(z);
        conversation.setObjectName(jSONObject.optString("object_name"));
        if (conversation.getLatestMessageId() > 0) {
            Message message = new Message();
            message.setMessageId(conversation.getLatestMessageId());
            message.setSenderUserId(conversation.getSenderUserId());
            conversation.setLatestMessage(renderMessageContent(conversation.getObjectName(), jSONObject.optString("content").getBytes(), message));
        }
        conversation.setReceivedStatus(new Message$ReceivedStatus(jSONObject.optInt("read_status")));
        conversation.setReceivedTime(jSONObject.optLong("receive_time"));
        conversation.setSentTime(jSONObject.optLong("send_time"));
        conversation.setSentStatus(Message$SentStatus.setValue(jSONObject.optInt("send_status")));
        conversation.setSenderUserId(jSONObject.optString("sender_user_id"));
        conversation.setSenderUserName(jSONObject.optString("sender_user_name"));
        conversation.setDraft(jSONObject.optString("draft_message"));
        conversation.setNotificationStatus(jSONObject.optInt("block_push") == 100 ? ConversationNotificationStatus.DO_NOT_DISTURB : ConversationNotificationStatus.NOTIFY);
        return conversation;
    }

    public Conversation getConversation(ConversationType conversationType, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("ConversationType 和 TargetId 参数异常");
        } else {
            NativeObject.Conversation GetConversationEx = nativeObj.GetConversationEx(str, conversationType.getValue());
            if (GetConversationEx == null) {
                return null;
            }
            Conversation renderConversationFromNative = renderConversationFromNative(GetConversationEx);
            renderConversationFromNative.setConversationType(conversationType);
            return renderConversationFromNative;
        }
    }

    public boolean removeConversation(ConversationType conversationType, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str.trim())) {
            throw new IllegalArgumentException("ConversationType 和 TargetId 参数异常");
        } else {
            return nativeObj.RemoveConversation(conversationType.getValue(), str.trim());
        }
    }

    public boolean setConversationToTop(ConversationType conversationType, String str, boolean z) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType != null && !TextUtils.isEmpty(str)) {
            return nativeObj.SetIsTop(conversationType.getValue(), str, z);
        } else {
            throw new IllegalArgumentException("ConversationType 或 TargetId 参数异常");
        }
    }

    public int getTotalUnreadCount() {
        if (nativeObj != null) {
            return nativeObj.GetTotalUnreadCount();
        }
        throw new RuntimeException("NativeClient 尚未初始化!");
    }

    public int getUnreadCount(ConversationType conversationType, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (!TextUtils.isEmpty(str) && conversationType != null) {
            return nativeObj.GetUnreadCount(str, conversationType.getValue());
        } else {
            throw new IllegalArgumentException("ConversationType 或 TargetId 参数异常");
        }
    }

    public int getUnreadCount(ConversationType... conversationTypeArr) {
        int i = 0;
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationTypeArr == null || conversationTypeArr.length == 0) {
            throw new IllegalArgumentException("ConversationTypes 参数异常。");
        } else {
            int[] iArr = new int[conversationTypeArr.length];
            int length = conversationTypeArr.length;
            int i2 = 0;
            while (i < length) {
                iArr[i2] = conversationTypeArr[i].getValue();
                i2++;
                i++;
            }
            return nativeObj.GetCateUnreadCount(iArr);
        }
    }

    public List<Message> getLatestMessages(ConversationType conversationType, String str, int i) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType != null && !TextUtils.isEmpty(str)) {
            return getHistoryMessages(conversationType, str.trim(), -1, i);
        } else {
            throw new IllegalArgumentException("ConversationTypes 或 targetId 参数异常。");
        }
    }

    public List<Message> getHistoryMessages(ConversationType conversationType, String str, int i, int i2) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("ConversationTypes 或 targetId 参数异常。");
        } else {
            NativeObject.Message[] GetHistoryMessagesEx = nativeObj.GetHistoryMessagesEx(str.trim(), conversationType.getValue(), "", i, i2, true);
            List arrayList = new ArrayList();
            if (GetHistoryMessagesEx != null) {
                for (NativeObject.Message message : GetHistoryMessagesEx) {
                    Message message2 = new Message(message);
                    message2.setContent(renderMessageContent(message.getObjectName(), message.getContent(), message2));
                    arrayList.add(message2);
                }
            }
            return arrayList;
        }
    }

    public List<Message> getHistoryMessages(ConversationType conversationType, String str, String str2, int i, int i2) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("ConversationTypes, objectName 或 targetId 参数异常。");
        } else {
            NativeObject.Message[] GetHistoryMessagesEx = nativeObj.GetHistoryMessagesEx(str.trim(), conversationType.getValue(), str2, i, i2, true);
            List<Message> arrayList = new ArrayList();
            if (GetHistoryMessagesEx == null) {
                return null;
            }
            for (NativeObject.Message message : GetHistoryMessagesEx) {
                Message message2 = new Message(message);
                message2.setContent(renderMessageContent(message.getObjectName(), message.getContent(), message2));
                arrayList.add(message2);
            }
            return arrayList;
        }
    }

    public List<Message> getHistoryMessages(ConversationType conversationType, String str, String str2, int i, int i2, boolean z) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("ConversationTypes, objectName 或 targetId 参数异常。");
        } else {
            NativeObject.Message[] GetHistoryMessagesEx = nativeObj.GetHistoryMessagesEx(str.trim(), conversationType.getValue(), str2, i, i2, z);
            List<Message> arrayList = new ArrayList();
            if (GetHistoryMessagesEx == null) {
                return null;
            }
            for (NativeObject.Message message : GetHistoryMessagesEx) {
                Message message2 = new Message(message);
                message2.setContent(renderMessageContent(message.getObjectName(), message.getContent(), message2));
                arrayList.add(message2);
            }
            return arrayList;
        }
    }

    public void getRemoteHistoryMessages(ConversationType conversationType, String str, long j, int i, final IResultCallback<List<Message>> iResultCallback) {
        RLog.m19422i(TAG, "getRemoteHistoryMessages call");
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || TextUtils.isEmpty(str) || iResultCallback == null) {
            throw new IllegalArgumentException("ConversationTypes，callback 或 targetId 参数异常。");
        } else {
            nativeObj.LoadHistoryMessage(str.trim(), conversationType.getValue(), j, i, new HistoryMessageListener() {
                public void onReceived(NativeObject.Message[] messageArr) {
                    List arrayList = new ArrayList();
                    if (messageArr != null && messageArr.length > 0) {
                        for (NativeObject.Message message : messageArr) {
                            Message message2 = new Message(message);
                            message2.setContent(NativeClient.this.renderMessageContent(message.getObjectName(), message.getContent(), message2));
                            arrayList.add(message2);
                        }
                    }
                    iResultCallback.onSuccess(arrayList);
                }

                public void onError(int i) {
                    iResultCallback.onError(i);
                }
            });
        }
    }

    public boolean deleteMessages(int[] iArr) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (iArr != null && iArr.length != 0) {
            return nativeObj.DeleteMessages(iArr);
        } else {
            throw new IllegalArgumentException("MessageIds 参数异常。");
        }
    }

    public boolean deleteMessage(ConversationType conversationType, String str) {
        return nativeObj.ClearMessages(conversationType.getValue(), str, true);
    }

    public boolean clearMessages(ConversationType conversationType, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType != null && !TextUtils.isEmpty(str)) {
            return nativeObj.ClearMessages(conversationType.getValue(), str, false);
        } else {
            throw new IllegalArgumentException("conversationType 或 targetId 参数异常。");
        }
    }

    public boolean clearMessagesUnreadStatus(ConversationType conversationType, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType != null && !TextUtils.isEmpty(str)) {
            return nativeObj.ClearUnread(conversationType.getValue(), str);
        } else {
            throw new IllegalArgumentException("conversationType 或 targetId 参数异常。");
        }
    }

    public boolean setMessageExtra(int i, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (i != 0) {
            return nativeObj.SetMessageExtra(i, str);
        } else {
            throw new IllegalArgumentException("messageId 参数异常。");
        }
    }

    public boolean setMessageReceivedStatus(int i, Message$ReceivedStatus message$ReceivedStatus) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (message$ReceivedStatus != null && i != 0) {
            return nativeObj.SetReadStatus(i, message$ReceivedStatus.getFlag());
        } else {
            throw new IllegalArgumentException("receivedStatus 或 messageId 参数异常。");
        }
    }

    public boolean setMessageSentStatus(int i, Message$SentStatus message$SentStatus) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (message$SentStatus != null && i != 0) {
            return nativeObj.SetSendStatus(i, message$SentStatus.getValue());
        } else {
            throw new IllegalArgumentException("sentStatus 或 messageId 参数异常。");
        }
    }

    public String getTextMessageDraft(ConversationType conversationType, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType != null && !TextUtils.isEmpty(str)) {
            return nativeObj.GetTextMessageDraft(conversationType.getValue(), str);
        } else {
            throw new IllegalArgumentException("conversationType 或 targetId 参数异常。");
        }
    }

    public boolean saveTextMessageDraft(ConversationType conversationType, String str, String str2) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType != null && !TextUtils.isEmpty(str)) {
            return nativeObj.SetTextMessageDraft(conversationType.getValue(), str, str2);
        } else {
            throw new IllegalArgumentException("conversationType 或 targetId 参数异常。");
        }
    }

    public boolean clearTextMessageDraft(ConversationType conversationType, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("conversationType 或 targetId 参数异常。");
        } else if (TextUtils.isEmpty(getTextMessageDraft(conversationType, str))) {
            return true;
        } else {
            return saveTextMessageDraft(conversationType, str, "");
        }
    }

    public void getDiscussion(String str, final IResultCallback<Discussion> iResultCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(" discussionId 参数异常。");
        } else {
            DiscussionInfo GetDiscussionInfoSync = nativeObj.GetDiscussionInfoSync(str);
            if (GetDiscussionInfoSync != null) {
                Discussion discussion = new Discussion(GetDiscussionInfoSync);
                if (discussion.getMemberIdList() == null || discussion.getMemberIdList().size() == 0) {
                    nativeObj.GetDiscussionInfo(str, new DiscussionInfoListener() {
                        public void onReceived(DiscussionInfo discussionInfo) {
                            if (iResultCallback != null) {
                                iResultCallback.onSuccess(new Discussion(discussionInfo));
                            }
                        }

                        public void OnError(int i) {
                            if (iResultCallback != null) {
                                iResultCallback.onError(i);
                            }
                        }
                    });
                    return;
                } else if (iResultCallback != null) {
                    iResultCallback.onSuccess(discussion);
                    return;
                } else {
                    return;
                }
            }
            nativeObj.GetDiscussionInfo(str, new DiscussionInfoListener() {
                public void onReceived(DiscussionInfo discussionInfo) {
                    if (iResultCallback != null) {
                        iResultCallback.onSuccess(new Discussion(discussionInfo));
                    }
                }

                public void OnError(int i) {
                    if (iResultCallback != null) {
                        iResultCallback.onError(i);
                    }
                }
            });
        }
    }

    public void setDiscussionName(String str, String str2, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str.trim()) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str2.trim())) {
            throw new IllegalArgumentException(" discussionId 或 name 参数异常。");
        } else {
            nativeObj.RenameDiscussion(str, str2, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (operationCallback != null) {
                        if (i == 0) {
                            operationCallback.onSuccess();
                        } else {
                            operationCallback.onError(i);
                        }
                    }
                }
            });
        }
    }

    public void createDiscussion(String str, List<String> list, final IResultCallback<String> iResultCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str) || list == null || list.size() == 0) {
            throw new IllegalArgumentException("name 或 userIdList 参数异常。");
        } else {
            if (!TextUtils.isEmpty(this.currentUserId) && list.contains(this.currentUserId)) {
                list.remove(this.currentUserId);
            }
            String[] strArr = new String[list.size()];
            list.toArray(strArr);
            nativeObj.CreateInviteDiscussion(str, strArr, new CreateDiscussionCallback() {
                public void OnError(int i) {
                    if (iResultCallback != null) {
                        iResultCallback.onError(i);
                    }
                }

                public void OnSuccess(String str) {
                    if (iResultCallback != null) {
                        iResultCallback.onSuccess(str);
                    }
                }
            });
        }
    }

    public void searchPublicService(String str, int i, int i2, final IResultCallback iResultCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (str == null) {
            throw new IllegalArgumentException("keyWords 参数异常。");
        } else {
            nativeObj.SearchAccount(str, i, i2, new AccountInfoListener() {
                public void onReceived(AccountInfo[] accountInfoArr) {
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < accountInfoArr.length; i++) {
                        PublicServiceProfile publicServiceProfile = new PublicServiceProfile();
                        publicServiceProfile.setTargetId(new String(accountInfoArr[i].getAccountId()));
                        publicServiceProfile.setName(new String(accountInfoArr[i].getAccountName()));
                        publicServiceProfile.setPublicServiceType(ConversationType.setValue(accountInfoArr[i].getAccountType()));
                        publicServiceProfile.setPortraitUri(Uri.parse(new String(accountInfoArr[i].getAccountUri())));
                        String str = new String(accountInfoArr[i].getExtra());
                        RLog.m19422i(NativeClient.TAG, "getPublicAccountInfoList extra:" + str);
                        publicServiceProfile.setExtra(str);
                        arrayList.add(publicServiceProfile);
                    }
                    iResultCallback.onSuccess(new PublicServiceProfileList(arrayList));
                }

                public void OnError(int i) {
                    iResultCallback.onError(i);
                }
            });
        }
    }

    public void subscribePublicService(String str, int i, boolean z, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        }
        nativeObj.SubscribeAccount(str, i, z, new PublishAckListener() {
            public void operationComplete(int i, String str, long j) {
                if (operationCallback != null) {
                    if (i == 0) {
                        operationCallback.onSuccess();
                    } else {
                        operationCallback.onError(i);
                    }
                }
            }
        });
    }

    public void getPublicServiceProfile(String str, int i, IResultCallback<PublicServiceProfile> iResultCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("RongIMClient 尚未初始化!");
        } else if (str == null) {
            throw new IllegalArgumentException("targetId 参数异常。");
        } else if (iResultCallback != null) {
            PublicServiceProfile publicServiceProfile = new PublicServiceProfile();
            UserInfo GetUserInfoExSync = nativeObj.GetUserInfoExSync(str, i);
            if (GetUserInfoExSync != null) {
                publicServiceProfile.setTargetId(str);
                publicServiceProfile.setName(GetUserInfoExSync.getUserName());
                if (GetUserInfoExSync.getUrl() != null) {
                    publicServiceProfile.setPortraitUri(Uri.parse(GetUserInfoExSync.getUrl()));
                }
                publicServiceProfile.setPublicServiceType(ConversationType.setValue(GetUserInfoExSync.getCategoryId()));
                publicServiceProfile.setExtra(GetUserInfoExSync.getAccountExtra());
                iResultCallback.onSuccess(publicServiceProfile);
                return;
            }
            iResultCallback.onError(-1);
        }
    }

    public void getPublicServiceList(IResultCallback<PublicServiceProfileList> iResultCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("RongIMClient 尚未初始化!");
        } else if (iResultCallback != null) {
            AccountInfo[] LoadAccountInfo = nativeObj.LoadAccountInfo();
            if (LoadAccountInfo == null || LoadAccountInfo.length <= 0) {
                iResultCallback.onError(-1);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < LoadAccountInfo.length; i++) {
                PublicServiceProfile publicServiceProfile = new PublicServiceProfile();
                publicServiceProfile.setTargetId(new String(LoadAccountInfo[i].getAccountId()));
                publicServiceProfile.setName(new String(LoadAccountInfo[i].getAccountName()));
                publicServiceProfile.setPublicServiceType(ConversationType.setValue(LoadAccountInfo[i].getAccountType()));
                publicServiceProfile.setPortraitUri(Uri.parse(new String(LoadAccountInfo[i].getAccountUri())));
                publicServiceProfile.setExtra(new String(LoadAccountInfo[i].getExtra()));
                arrayList.add(publicServiceProfile);
            }
            iResultCallback.onSuccess(new PublicServiceProfileList(arrayList));
        }
    }

    public void addMemberToDiscussion(String str, List<String> list, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str) || list == null || list.size() == 0) {
            throw new IllegalArgumentException("discussionId 或 userIdList 参数异常。");
        } else {
            String[] strArr = new String[list.size()];
            list.toArray(strArr);
            nativeObj.InviteMemberToDiscussion(str, strArr, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (operationCallback != null) {
                        if (i == 0) {
                            operationCallback.onSuccess();
                        } else {
                            operationCallback.onError(i);
                        }
                    }
                }
            });
        }
    }

    public void removeMemberFromDiscussion(String str, String str2, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("discussionId 或 userId 参数异常。");
        } else {
            nativeObj.RemoveMemberFromDiscussion(str, str2, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (operationCallback != null) {
                        if (i == 0) {
                            operationCallback.onSuccess();
                        } else {
                            operationCallback.onError(i);
                        }
                    }
                }
            });
        }
    }

    public void quitDiscussion(String str, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("discussionId 参数异常。");
        } else {
            nativeObj.QuitDiscussion(str, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (operationCallback != null) {
                        if (i == 0) {
                            operationCallback.onSuccess();
                        } else {
                            operationCallback.onError(i);
                        }
                    }
                }
            });
        }
    }

    public Message getMessage(int i) {
        NativeObject.Message GetMessageById = nativeObj.GetMessageById(i);
        Message message = new Message(GetMessageById);
        message.setContent(renderMessageContent(GetMessageById.getObjectName(), GetMessageById.getContent(), message));
        return message;
    }

    public void sendMessage(ConversationType conversationType, String str, MessageContent messageContent, String str2, String str3, ISendMessageCallback<Message> iSendMessageCallback) {
        sendMessage(Message.obtain(str, conversationType, messageContent), str2, str3, iSendMessageCallback);
    }

    public void sendMessage(final Message message, String str, String str2, final ISendMessageCallback<Message> iSendMessageCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (message.getConversationType() == null || TextUtils.isEmpty(message.getTargetId()) || message.getContent() == null) {
            throw new IllegalArgumentException("message, ConversationType 或 TargetId 参数异常。");
        } else {
            MessageTag messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
            if (TextUtils.isEmpty(message.getSenderUserId())) {
                message.setSenderUserId(this.currentUserId);
            }
            message.setMessageDirection(Message$MessageDirection.SEND);
            message.setSentStatus(Message$SentStatus.SENDING);
            message.setSentTime(System.currentTimeMillis());
            message.setObjectName(messageTag.value());
            byte[] bArr = new byte[1];
            if ((messageTag.flag() & 1) == 1 && message.getMessageId() <= 0) {
                message.setMessageId(nativeObj.SaveMessage(message.getTargetId(), message.getConversationType().getValue(), messageTag.value(), message.getSenderUserId(), bArr, false, 0));
            }
            int i = messageTag.flag() == 16 ? 1 : 3;
            Constructor constructor = (Constructor) messageHandlerMap.get(messageTag.value());
            if (constructor == null) {
                RLog.m19420e(TAG, "sendMessage MessageHandler is null");
                if (iSendMessageCallback != null) {
                    iSendMessageCallback.onError(message, -3);
                    return;
                }
                return;
            }
            try {
                byte[] bArr2;
                byte[] bArr3;
                ((MessageHandler) constructor.newInstance(new Object[]{this.mContext})).encodeMessage(message);
                bArr = message.getContent().encode();
                if (iSendMessageCallback != null) {
                    iSendMessageCallback.onAttached(message);
                }
                boolean isMentionedMessage = isMentionedMessage(message);
                nativeObj.SetMessageContent(message.getMessageId(), bArr, message.getObjectName());
                NativeObject nativeObject = nativeObj;
                String targetId = message.getTargetId();
                int value = message.getConversationType().getValue();
                String value2 = messageTag.value();
                if (TextUtils.isEmpty(str)) {
                    bArr2 = null;
                } else {
                    bArr2 = str.getBytes();
                }
                if (TextUtils.isEmpty(str2)) {
                    bArr3 = null;
                } else {
                    bArr3 = str2.getBytes();
                }
                nativeObject.SendMessage(targetId, value, i, value2, bArr, bArr2, bArr3, message.getMessageId(), new PublishAckListener() {
                    public void operationComplete(int i, String str, long j) {
                        RLog.m19419d(NativeClient.TAG, "sendMessage code = " + i + ", id = " + message.getMessageId() + ", uid = " + str);
                        if (i == 0) {
                            message.setSentStatus(Message$SentStatus.SENT);
                            message.setSentTime(j);
                            message.setUId(str);
                            if (iSendMessageCallback != null) {
                                iSendMessageCallback.onSuccess(message);
                                return;
                            }
                            return;
                        }
                        message.setSentStatus(Message$SentStatus.FAILED);
                        if (iSendMessageCallback != null) {
                            iSendMessageCallback.onError(message, i);
                        }
                    }
                }, isMentionedMessage);
            } catch (Exception e) {
                RLog.m19420e(TAG, "sendMessage exception : " + e.getMessage());
                e.printStackTrace();
                if (iSendMessageCallback != null) {
                    iSendMessageCallback.onError(message, -3);
                }
            }
        }
    }

    public void sendLocationMessage(Message message, String str, String str2, ISendMessageCallback<Message> iSendMessageCallback) {
        MessageTag messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
        if (TextUtils.isEmpty(message.getSenderUserId())) {
            message.setSenderUserId(this.currentUserId);
        }
        message.setMessageDirection(Message$MessageDirection.SEND);
        message.setSentTime(System.currentTimeMillis());
        message.setObjectName(messageTag.value());
        message.setMessageId(nativeObj.SaveMessage(message.getTargetId(), message.getConversationType().getValue(), messageTag.value(), message.getSenderUserId(), message.getContent().encode(), false, 0));
        if (iSendMessageCallback != null) {
            message.setSentStatus(Message$SentStatus.SENDING);
            iSendMessageCallback.onAttached(message);
        }
        if (message.getMessageId() == 0) {
            RLog.m19420e(TAG, "Location Message saved error");
            if (iSendMessageCallback != null) {
                message.setSentStatus(Message$SentStatus.FAILED);
                nativeObj.SetSendStatus(message.getMessageId(), Message$SentStatus.FAILED.getValue());
                iSendMessageCallback.onError(message, -3);
                return;
            }
            return;
        }
        Constructor constructor = (Constructor) messageHandlerMap.get(messageTag.value());
        if (constructor == null) {
            RLog.m19420e(TAG, "MessageHandler is null");
            if (iSendMessageCallback != null) {
                message.setSentStatus(Message$SentStatus.FAILED);
                nativeObj.SetSendStatus(message.getMessageId(), Message$SentStatus.FAILED.getValue());
                iSendMessageCallback.onError(message, -3);
                return;
            }
            return;
        }
        try {
            MessageHandler messageHandler = (MessageHandler) constructor.newInstance(new Object[]{this.mContext});
            final MessageTag messageTag2 = messageTag;
            final String str3 = str;
            final String str4 = str2;
            final ISendMessageCallback<Message> iSendMessageCallback2 = iSendMessageCallback;
            messageHandler.setHandleMessageListener(new IHandleMessageListener() {
                public void onHandleResult(final Message message, int i) {
                    byte[] bArr = null;
                    RLog.m19419d(NativeClient.TAG, "onHandleResult " + ((LocationMessage) message.getContent()).getImgUri());
                    if (i == 0) {
                        byte[] bArr2;
                        boolean access$500 = NativeClient.this.isMentionedMessage(message);
                        byte[] encode = message.getContent().encode();
                        NativeClient.nativeObj.SetMessageContent(message.getMessageId(), encode, message.getObjectName());
                        NativeObject nativeObject = NativeClient.nativeObj;
                        String targetId = message.getTargetId();
                        int value = message.getConversationType().getValue();
                        String value2 = messageTag2.value();
                        if (TextUtils.isEmpty(str3)) {
                            bArr2 = null;
                        } else {
                            bArr2 = str3.getBytes();
                        }
                        if (!TextUtils.isEmpty(str4)) {
                            bArr = str4.getBytes();
                        }
                        nativeObject.SendMessage(targetId, value, 3, value2, encode, bArr2, bArr, message.getMessageId(), new PublishAckListener() {
                            public void operationComplete(int i, String str, long j) {
                                RLog.m19419d(NativeClient.TAG, "sendLocationMessage code = " + i + ", id = " + message.getMessageId() + ", uid = " + str);
                                if (i == 0) {
                                    message.setSentStatus(Message$SentStatus.SENT);
                                    message.setSentTime(j);
                                    message.setUId(str);
                                    if (iSendMessageCallback2 != null) {
                                        iSendMessageCallback2.onSuccess(message);
                                        return;
                                    }
                                    return;
                                }
                                message.setSentStatus(Message$SentStatus.FAILED);
                                NativeClient.nativeObj.SetSendStatus(message.getMessageId(), Message$SentStatus.FAILED.getValue());
                                if (iSendMessageCallback2 != null) {
                                    iSendMessageCallback2.onError(message, i);
                                }
                            }
                        }, access$500);
                        return;
                    }
                    message.setSentStatus(Message$SentStatus.FAILED);
                    NativeClient.nativeObj.SetSendStatus(message.getMessageId(), Message$SentStatus.FAILED.getValue());
                    if (iSendMessageCallback2 != null) {
                        iSendMessageCallback2.onError(message, 30014);
                    }
                }
            });
            messageHandler.encodeMessage(message);
        } catch (Exception e) {
            RLog.m19420e(TAG, "sendLocationMessage exception : " + e.getMessage());
            e.printStackTrace();
            if (iSendMessageCallback != null) {
                message.setSentStatus(Message$SentStatus.FAILED);
                iSendMessageCallback.onError(message, -3);
            }
        }
    }

    public Message sendStatusMessage(ConversationType conversationType, String str, MessageContent messageContent, int i, IResultCallback<Integer> iResultCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (str == null) {
            throw new IllegalArgumentException("targetId 参数异常。");
        } else {
            final Message obtain = Message.obtain(str, conversationType, messageContent);
            MessageTag messageTag = (MessageTag) obtain.getContent().getClass().getAnnotation(MessageTag.class);
            if (TextUtils.isEmpty(obtain.getSenderUserId())) {
                obtain.setSenderUserId(this.currentUserId);
            }
            obtain.setMessageDirection(Message$MessageDirection.SEND);
            obtain.setSentStatus(Message$SentStatus.SENDING);
            obtain.setSentTime(System.currentTimeMillis());
            obtain.setObjectName(messageTag.value());
            byte[] bArr = new byte[1];
            if ((messageTag.flag() & 1) == 1 && obtain.getMessageId() <= 0) {
                obtain.setMessageId(nativeObj.SaveMessage(obtain.getTargetId(), obtain.getConversationType().getValue(), messageTag.value(), obtain.getSenderUserId(), bArr, false, 0));
            }
            Constructor constructor = (Constructor) messageHandlerMap.get(messageTag.value());
            if (constructor != null) {
                try {
                    MessageHandler messageHandler = (MessageHandler) constructor.newInstance(new Object[]{this.mContext});
                    boolean isMentionedMessage = isMentionedMessage(obtain);
                    messageHandler.encodeMessage(obtain);
                    bArr = obtain.getContent().encode();
                    nativeObj.SetMessageContent(obtain.getMessageId(), bArr, obtain.getObjectName());
                    final IResultCallback<Integer> iResultCallback2 = iResultCallback;
                    nativeObj.SendMessage(obtain.getTargetId(), obtain.getConversationType().getValue(), i, messageTag.value(), bArr, null, null, obtain.getMessageId(), new PublishAckListener() {
                        public void operationComplete(int i, String str, long j) {
                            if (iResultCallback2 != null) {
                                if (i == 0) {
                                    iResultCallback2.onSuccess(Integer.valueOf(obtain.getMessageId()));
                                } else {
                                    iResultCallback2.onError(i);
                                }
                            }
                        }
                    }, isMentionedMessage);
                    RLog.m19419d(TAG, "sendStatusMessage SENDED, id = " + obtain.getMessageId());
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                } catch (Throwable e2) {
                    throw new RuntimeException(e2);
                } catch (Throwable e22) {
                    throw new RuntimeException(e22);
                }
            }
            RLog.m19420e(TAG, "sendStatusMessage 该消息未注册，请调用registerMessageType方法注册。");
            return obtain;
        }
    }

    public Message insertMessage(ConversationType conversationType, String str, String str2, MessageContent messageContent) {
        boolean z = true;
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || TextUtils.isEmpty(str) || messageContent == null) {
            throw new IllegalArgumentException("conversationType 或 targetId 参数异常。");
        } else {
            MessageTag messageTag = (MessageTag) messageContent.getClass().getAnnotation(MessageTag.class);
            if (messageTag == null) {
                throw new RuntimeException("自定义消息没有加注解信息。");
            } else if (messageTag.flag() == 16) {
                RLog.m19420e(TAG, "insertMessage MessageTag can not be STATUS.");
                return null;
            } else {
                String str3;
                Message message = new Message();
                message.setConversationType(conversationType);
                message.setTargetId(str);
                if (str2 == null) {
                    str3 = this.currentUserId;
                } else {
                    str3 = str2;
                }
                if (this.currentUserId == null) {
                    message.setMessageDirection(Message$MessageDirection.SEND);
                    message.setSentStatus(Message$SentStatus.SENT);
                } else {
                    message.setMessageDirection(this.currentUserId.equals(str3) ? Message$MessageDirection.SEND : Message$MessageDirection.RECEIVE);
                    message.setSentStatus(this.currentUserId.equals(str3) ? Message$SentStatus.SENT : Message$SentStatus.RECEIVED);
                }
                message.setSenderUserId(str3);
                message.setReceivedTime(System.currentTimeMillis());
                message.setSentTime(System.currentTimeMillis());
                message.setObjectName(messageTag.value());
                message.setContent(messageContent);
                byte[] bArr = new byte[1];
                if (message.getMessageId() <= 0) {
                    if (message.getMessageDirection() == null || !message.getMessageDirection().equals(Message$MessageDirection.RECEIVE)) {
                        z = false;
                    }
                    message.setMessageId(nativeObj.SaveMessage(message.getTargetId(), message.getConversationType().getValue(), messageTag.value(), str3, bArr, z, 0));
                }
                Constructor constructor = (Constructor) messageHandlerMap.get(messageTag.value());
                if (constructor != null) {
                    try {
                        ((MessageHandler) constructor.newInstance(new Object[]{this.mContext})).encodeMessage(message);
                        nativeObj.SetMessageContent(message.getMessageId(), message.getContent().encode(), message.getObjectName());
                        nativeObj.SetSendStatus(message.getMessageId(), Message$SentStatus.SENT.getValue());
                        RLog.m19419d(TAG, "insertMessage Inserted, id = " + message.getMessageId());
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    } catch (Throwable e2) {
                        throw new RuntimeException(e2);
                    } catch (Throwable e22) {
                        throw new RuntimeException(e22);
                    }
                }
                RLog.m19420e(TAG, "insertMessage 该消息未注册，请调用registerMessageType方法注册。");
                return message;
            }
        }
    }

    public void uploadMedia(ConversationType conversationType, String str, final String str2, final int i, final IResultProgressCallback<String> iResultProgressCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || str == null) {
            throw new IllegalArgumentException("conversationType 或 targetId 参数异常。");
        } else {
            nativeObj.GetUploadToken(1, new TokenListener() {
                public void OnError(int i, String str) {
                    if (i == 0) {
                        final String generateKey = FtUtilities.generateKey(FtUtilities.getMimeType(i));
                        String mediaServer = NavigationClient.getInstance().getMediaServer(NativeClient.this.mContext);
                        if (!TextUtils.isEmpty(NativeClient.this.mFileServer)) {
                            mediaServer = NativeClient.this.mFileServer;
                        }
                        if (mediaServer != null) {
                            int indexOf = mediaServer.indexOf(":");
                            if (indexOf > 0) {
                                String substring = mediaServer.substring(0, indexOf);
                                mediaServer = "http://" + substring + ":" + mediaServer.substring(indexOf + 1);
                            } else {
                                mediaServer = "http://" + mediaServer;
                            }
                            FileTransferClient.getInstance().upload(str2, str, new RequestOption(generateKey, MimeType.setValue(i), mediaServer, new RequestCallBack() {

                                /* renamed from: io.rong.imlib.NativeClient$16$1$1 */
                                class C52671 implements TokenListener {
                                    C52671() {
                                    }

                                    public void OnError(int i, String str) {
                                        if (i != 0) {
                                            RLog.m19419d(NativeClient.TAG, "GetDownloadUrl onError code =" + i);
                                            if (iResultProgressCallback != null) {
                                                iResultProgressCallback.onError(i);
                                            }
                                        } else if (iResultProgressCallback != null) {
                                            iResultProgressCallback.onSuccess(str);
                                        }
                                    }
                                }

                                public void onError(int i) {
                                    RLog.m19419d(NativeClient.TAG, "uploadMedia onError code =" + i);
                                    iResultProgressCallback.onError(i);
                                }

                                public void onComplete(String str) {
                                    NativeClient.nativeObj.GetDownloadUrl(1, generateKey, new C52671());
                                }

                                public void onProgress(int i) {
                                    if (iResultProgressCallback != null) {
                                        iResultProgressCallback.onProgress(i);
                                    }
                                }

                                public void onCanceled() {
                                }
                            }));
                            return;
                        }
                        RLog.m19419d(NativeClient.TAG, "uploadMedia getMediaServer returns null");
                        iResultProgressCallback.onError(30008);
                    } else if (iResultProgressCallback != null) {
                        iResultProgressCallback.onError(i);
                    }
                }
            });
        }
    }

    public void downloadMedia(ConversationType conversationType, String str, int i, String str2, final IResultProgressCallback<String> iResultProgressCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("conversationType，imageUrl 或 targetId 参数异常。");
        } else {
            FileTransferClient.getInstance().download(str2, new RequestOption(FtUtilities.getFileName(FileUtils.getCachePath(this.mContext, "download"), ShortMD5(str2), conversationType.getValue(), i, str, client.currentUserId), MimeType.setValue(i), new RequestCallBack() {
                public void onError(int i) {
                    RLog.m19419d(NativeClient.TAG, "downloadMedia onError code =" + i);
                    iResultProgressCallback.onError(i);
                }

                public void onComplete(String str) {
                    RLog.m19419d(NativeClient.TAG, "downloadMedia onComplete url =" + str);
                    iResultProgressCallback.onSuccess(str);
                }

                public void onProgress(int i) {
                    if (iResultProgressCallback != null) {
                        iResultProgressCallback.onProgress(i);
                    }
                }

                public void onCanceled() {
                }
            }));
        }
    }

    private void clearCachedResources(OperationCallback operationCallback) {
    }

    public void getConversationNotificationStatus(ConversationType conversationType, String str, final IResultCallback<Integer> iResultCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || TextUtils.isEmpty(str) || iResultCallback == null) {
            throw new IllegalArgumentException("conversationType 或 targetId 参数异常。");
        } else {
            nativeObj.GetBlockPush(str, conversationType.getValue(), new BizAckListener() {
                public void operationComplete(int i, int i2) {
                    if (iResultCallback != null) {
                        if (i == 0) {
                            iResultCallback.onSuccess(Integer.valueOf(i2 == 100 ? ConversationNotificationStatus.DO_NOT_DISTURB.getValue() : ConversationNotificationStatus.NOTIFY.getValue()));
                        } else {
                            iResultCallback.onError(i);
                        }
                    }
                }
            });
        }
    }

    public void setConversationNotificationStatus(ConversationType conversationType, String str, ConversationNotificationStatus conversationNotificationStatus, final IResultCallback<Integer> iResultCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || conversationNotificationStatus == null || TextUtils.isEmpty(str) || iResultCallback == null) {
            throw new IllegalArgumentException("conversationType, notificationStatus 或 targetId 参数异常。");
        } else {
            boolean z;
            NativeObject nativeObject = nativeObj;
            int value = conversationType.getValue();
            if (conversationNotificationStatus == ConversationNotificationStatus.DO_NOT_DISTURB) {
                z = true;
            } else {
                z = false;
            }
            nativeObject.SetBlockPush(str, value, z, new BizAckListener() {
                public void operationComplete(int i, int i2) {
                    if (iResultCallback != null) {
                        if (i == 0) {
                            iResultCallback.onSuccess(Integer.valueOf(i2 == 100 ? ConversationNotificationStatus.DO_NOT_DISTURB.getValue() : ConversationNotificationStatus.NOTIFY.getValue()));
                            return;
                        }
                        RLog.m19419d(NativeClient.TAG, "setConversationNotificationStatus operationComplete: opStatus = " + i);
                        iResultCallback.onError(i2);
                    }
                }
            });
        }
    }

    public void setDiscussionInviteStatus(String str, int i, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("targetId 参数异常。");
        } else {
            nativeObj.SetInviteStatus(str, i, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (operationCallback != null) {
                        if (i == 0) {
                            operationCallback.onSuccess();
                        } else {
                            operationCallback.onError(i);
                        }
                    }
                }
            });
        }
    }

    public void syncGroup(List<Group> list, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (list == null || list.size() == 0) {
            throw new IllegalArgumentException(" groups 参数异常。");
        } else {
            String[] strArr = new String[list.size()];
            String[] strArr2 = new String[list.size()];
            int i = 0;
            for (Group group : list) {
                strArr[i] = group.getId();
                int i2 = i + 1;
                strArr2[i] = group.getName();
                i = i2;
            }
            nativeObj.SyncGroups(strArr, strArr2, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (operationCallback != null) {
                        if (i == 0) {
                            operationCallback.onSuccess();
                        } else {
                            operationCallback.onError(i);
                        }
                    }
                }
            });
        }
    }

    public void joinGroup(String str, String str2, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (str == null || str2 == null) {
            throw new IllegalArgumentException("groupId 或 groupName参数异常。");
        } else {
            nativeObj.JoinGroup(str, str2, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (operationCallback != null) {
                        if (i == 0) {
                            operationCallback.onSuccess();
                        } else {
                            operationCallback.onError(i);
                        }
                    }
                }
            });
        }
    }

    public void quitGroup(String str, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (str == null) {
            throw new IllegalArgumentException("groupId 参数异常。");
        } else {
            nativeObj.QuitGroup(str, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (operationCallback != null) {
                        if (i == 0) {
                            operationCallback.onSuccess();
                        } else {
                            operationCallback.onError(i);
                        }
                    }
                }
            });
        }
    }

    public void setOnReceiveMessageListener(final OnReceiveMessageListener onReceiveMessageListener) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        }
        this.mReceiveMessageListener = onReceiveMessageListener;
        nativeObj.SetMessageListener(new ReceiveMessageListener() {
            public void onReceived(NativeObject.Message message, int i, final boolean z) {
                RLog.m19422i(NativeClient.TAG, "onReceived: objectName = " + message.getObjectName());
                final Message message2 = new Message(message);
                message2.setContent(NativeClient.this.renderMessageContent(message.getObjectName(), message.getContent(), message2));
                if (message2.getContent() instanceof DiscussionNotificationMessage) {
                    NativeClient.nativeObj.GetDiscussionInfo(message2.getTargetId(), new DiscussionInfoListener() {
                        public void onReceived(DiscussionInfo discussionInfo) {
                            if (onReceiveMessageListener != null) {
                                onReceiveMessageListener.onReceived(message2, 0, z);
                            }
                        }

                        public void OnError(int i) {
                        }
                    });
                    return;
                }
                boolean z2 = false;
                if (NativeClient.this.mReceiveMessageListenerEx != null) {
                    z2 = NativeClient.this.mReceiveMessageListenerEx.onReceived(message2, i);
                }
                if (onReceiveMessageListener != null && !r0) {
                    onReceiveMessageListener.onReceived(message2, i, z);
                }
            }
        });
    }

    public void setOnReceiveMessageListenerEx(OnReceiveMessageListenerEx onReceiveMessageListenerEx) {
        this.mReceiveMessageListenerEx = onReceiveMessageListenerEx;
    }

    public OnReceiveMessageListener getOnReceiveMessageListener() {
        return this.mReceiveMessageListener;
    }

    public static void setConnectionStatusListener(final ICodeListener iCodeListener) {
        nativeObj.SetExceptionListener(new ExceptionListener() {
            public void onError(int i, String str) {
                if (iCodeListener != null) {
                    iCodeListener.onChanged(i);
                }
                if (i != 0 && NativeClient.client != null) {
                    NativeClient.client.stopCRHeartBeat();
                }
            }
        });
    }

    public long getDeltaTime() {
        return nativeObj.GetDeltaTime();
    }

    public void queryChatRoomInfo(final String str, int i, int i2, final IResultCallback<ChatRoomInfo> iResultCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("聊天室 Id 参数异常。");
        } else {
            nativeObj.QueryChatroomInfo(str, i, i2, new ChatroomInfoListener() {
                public void OnSuccess(int i, UserInfo[] userInfoArr) {
                    List arrayList = new ArrayList();
                    if (userInfoArr != null) {
                        for (int i2 = 0; i2 < userInfoArr.length; i2++) {
                            ChatRoomMemberInfo chatRoomMemberInfo = new ChatRoomMemberInfo();
                            chatRoomMemberInfo.setUserId(userInfoArr[i2].getUserId());
                            chatRoomMemberInfo.setJoinTime(userInfoArr[i2].getJoinTime());
                            arrayList.add(chatRoomMemberInfo);
                        }
                    }
                    ChatRoomInfo chatRoomInfo = new ChatRoomInfo();
                    chatRoomInfo.setChatRoomId(str);
                    chatRoomInfo.setTotalMemberCount(i);
                    chatRoomInfo.setMemberInfo(arrayList);
                    iResultCallback.onSuccess(chatRoomInfo);
                }

                public void OnError(int i) {
                    iResultCallback.onError(i);
                }
            });
        }
    }

    public void joinChatRoom(String str, int i, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("聊天室 Id 参数异常。");
        } else {
            nativeObj.JoinChatRoom(str, ConversationType.CHATROOM.getValue(), i, false, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (i == 0) {
                        NativeClient.this.startCRHeartBeatIfNeed();
                        if (operationCallback != null) {
                            operationCallback.onSuccess();
                        }
                    } else if (operationCallback != null) {
                        operationCallback.onError(i);
                    }
                }
            });
        }
    }

    public void reJoinChatRoom(String str, int i, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("聊天室 Id 参数异常。");
        } else {
            nativeObj.JoinChatRoom(str, ConversationType.CHATROOM.getValue(), i, true, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (i == 0) {
                        NativeClient.this.startCRHeartBeatIfNeed();
                        if (operationCallback != null) {
                            operationCallback.onSuccess();
                        }
                    } else if (operationCallback != null) {
                        operationCallback.onError(i);
                    }
                }
            });
        }
    }

    private void startCRHeartBeatIfNeed() {
        if (this.timer == null) {
            RLog.m19419d(TAG, "startCRHeartBeat");
            this.timer = new Timer();
            this.timer.schedule(new TimerTask() {
                public void run() {
                    NativeClient.nativeObj.ping();
                    RLog.m19419d(NativeClient.TAG, "-heart beat-");
                }
            }, 1000, 15000);
        }
    }

    private void stopCRHeartBeat() {
        if (this.timer != null) {
            this.timer.cancel();
            this.timer = null;
            RLog.m19419d(TAG, "stopCRHeartBeat");
        }
    }

    public void joinExistChatRoom(String str, int i, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("聊天室 Id 参数异常。");
        } else {
            nativeObj.JoinExistingChatroom(str, ConversationType.CHATROOM.getValue(), i, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (i == 0) {
                        if (operationCallback != null) {
                            operationCallback.onSuccess();
                        }
                    } else if (operationCallback != null) {
                        operationCallback.onError(i);
                    }
                }
            });
            startCRHeartBeatIfNeed();
        }
    }

    public void quitChatRoom(String str, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("聊天室 Id 参数异常。");
        } else {
            nativeObj.QuitChatRoom(str, ConversationType.CHATROOM.getValue(), new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (i == 0) {
                        if (operationCallback != null) {
                            operationCallback.onSuccess();
                        }
                    } else if (operationCallback != null) {
                        operationCallback.onError(i);
                    }
                }
            });
            clearMessages(ConversationType.CHATROOM, str);
            stopCRHeartBeat();
        }
    }

    public boolean clearConversations(ConversationType... conversationTypeArr) {
        int i = 0;
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        }
        if (conversationTypeArr == null || conversationTypeArr.length == 0) {
            IllegalAccessException illegalAccessException = new IllegalAccessException("ConversationTypes 参数异常。");
        }
        int[] iArr = new int[conversationTypeArr.length];
        int length = conversationTypeArr.length;
        int i2 = 0;
        while (i < length) {
            iArr[i2] = conversationTypeArr[i].getValue();
            i2++;
            i++;
        }
        return nativeObj.ClearConversations(iArr);
    }

    public void addToBlacklist(String str, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str) || operationCallback == null) {
            throw new IllegalArgumentException("参数异常。");
        } else {
            nativeObj.AddToBlacklist(str, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (i == 0) {
                        operationCallback.onSuccess();
                    } else {
                        operationCallback.onError(i);
                    }
                }
            });
        }
    }

    public void removeFromBlacklist(String str, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str) || operationCallback == null) {
            throw new IllegalArgumentException("用户 Id 参数异常。");
        } else {
            nativeObj.RemoveFromBlacklist(str, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (i == 0) {
                        operationCallback.onSuccess();
                    } else {
                        operationCallback.onError(i);
                    }
                }
            });
        }
    }

    public void getBlacklistStatus(String str, final IResultCallback<BlacklistStatus> iResultCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str) || iResultCallback == null) {
            throw new IllegalArgumentException("用户 Id 参数异常。");
        } else {
            nativeObj.GetBlacklistStatus(str, new BizAckListener() {
                public void operationComplete(int i, int i2) {
                    if (i != 0) {
                        iResultCallback.onError(i);
                    } else if (i2 == 0) {
                        iResultCallback.onSuccess(BlacklistStatus.EXIT_BLACK_LIST);
                    } else if (i2 == 101) {
                        iResultCallback.onSuccess(BlacklistStatus.NOT_EXIT_BLACK_LIST);
                    }
                }
            });
        }
    }

    public void getBlacklist(final IResultCallback<String> iResultCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (iResultCallback == null) {
            throw new IllegalArgumentException("参数异常。");
        } else {
            nativeObj.GetBlacklist(new SetBlacklistListener() {
                public void OnSuccess(String str) {
                    if (TextUtils.isEmpty(str)) {
                        if (iResultCallback != null) {
                            iResultCallback.onSuccess(null);
                        }
                    } else if (iResultCallback != null) {
                        iResultCallback.onSuccess(str);
                    }
                }

                public void OnError(int i) {
                    if (iResultCallback != null) {
                        iResultCallback.onError(i);
                    }
                }
            });
        }
    }

    public void setNotificationQuietHours(String str, int i, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (TextUtils.isEmpty(str) || i <= 0 || i >= 1440 || operationCallback == null) {
            throw new IllegalArgumentException("startTime, spanMinutes 或 spanMinutes 参数异常。");
        } else if (Pattern.compile("^(([0-1][0-9])|2[0-3]):[0-5][0-9]:([0-5][0-9])$").matcher(str).find()) {
            nativeObj.AddPushSetting(str, i, new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (i == 0) {
                        operationCallback.onSuccess();
                    } else {
                        operationCallback.onError(i);
                    }
                }
            });
        } else {
            throw new IllegalArgumentException("startTime 参数异常。");
        }
    }

    public void removeNotificationQuietHours(final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (operationCallback == null) {
            throw new IllegalArgumentException("参数异常。");
        } else {
            nativeObj.RemovePushSetting(new PublishAckListener() {
                public void operationComplete(int i, String str, long j) {
                    if (i == 0) {
                        operationCallback.onSuccess();
                    } else {
                        operationCallback.onError(i);
                    }
                }
            });
        }
    }

    public void getNotificationQuietHours(final GetNotificationQuietHoursCallback getNotificationQuietHoursCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (getNotificationQuietHoursCallback == null) {
            throw new IllegalArgumentException("参数异常。");
        } else {
            nativeObj.QueryPushSetting(new PushSettingListener() {
                public void OnError(int i) {
                    getNotificationQuietHoursCallback.onError(i);
                }

                public void OnSuccess(String str, int i) {
                    getNotificationQuietHoursCallback.onSuccess(str, i);
                }
            });
        }
    }

    public void setUserData(UserData userData, final OperationCallback operationCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (userData == null) {
            throw new IllegalArgumentException("userData 参数异常。");
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                JSONObject jSONObject2;
                if (userData.getPersonalInfo() != null) {
                    jSONObject2 = new JSONObject();
                    jSONObject2.putOpt("realName", userData.getPersonalInfo().getRealName());
                    jSONObject2.putOpt("sex", userData.getPersonalInfo().getSex());
                    jSONObject2.putOpt("age", userData.getPersonalInfo().getAge());
                    jSONObject2.putOpt("birthday", userData.getPersonalInfo().getBirthday());
                    jSONObject2.putOpt("job", userData.getPersonalInfo().getJob());
                    jSONObject2.putOpt("portraitUri", userData.getPersonalInfo().getPortraitUri());
                    jSONObject2.putOpt("comment", userData.getPersonalInfo().getComment());
                    jSONObject.put("personalInfo", jSONObject2);
                }
                if (userData.getAccountInfo() != null) {
                    jSONObject2 = new JSONObject();
                    jSONObject2.putOpt("appUserId", userData.getAccountInfo().getAppUserId());
                    jSONObject2.putOpt("userName", userData.getAccountInfo().getUserName());
                    jSONObject2.putOpt("nickName", userData.getAccountInfo().getNickName());
                    jSONObject.putOpt("accountInfo", jSONObject2);
                }
                if (userData.getContactInfo() != null) {
                    jSONObject2 = new JSONObject();
                    jSONObject2.putOpt("tel", userData.getContactInfo().getTel());
                    jSONObject2.putOpt("email", userData.getContactInfo().getEmail());
                    jSONObject2.putOpt(GeocodingCriteria.TYPE_ADDRESS, userData.getContactInfo().getAddress());
                    jSONObject2.putOpt(AVThirdPartyUserAuth.SNS_TENCENT_WEIBO, userData.getContactInfo().getQQ());
                    jSONObject2.putOpt(AVThirdPartyUserAuth.SNS_SINA_WEIBO, userData.getContactInfo().getWeibo());
                    jSONObject2.putOpt(AVThirdPartyUserAuth.SNS_TENCENT_WEIXIN, userData.getContactInfo().getWeixin());
                    jSONObject.putOpt("contactInfo", jSONObject2);
                }
                if (userData.getClientInfo() != null) {
                    jSONObject2 = new JSONObject();
                    jSONObject2.putOpt("network", userData.getClientInfo().getNetwork());
                    jSONObject2.putOpt(MapboxEvent.ATTRIBUTE_CARRIER, userData.getClientInfo().getCarrier());
                    jSONObject2.putOpt("systemVersion", userData.getClientInfo().getSystemVersion());
                    jSONObject2.putOpt("os", userData.getClientInfo().getOs());
                    jSONObject2.putOpt(C0861d.f2142n, userData.getClientInfo().getDevice());
                    jSONObject2.putOpt("mobilePhoneManufacturers", userData.getClientInfo().getMobilePhoneManufacturers());
                    jSONObject.putOpt("clientInfo", jSONObject2);
                }
                jSONObject.putOpt("appVersion", userData.getAppVersion());
                jSONObject.putOpt("extra", userData.getExtra());
                String jSONObject3 = jSONObject.toString();
                RLog.m19419d(TAG, "UserData " + jSONObject3);
                nativeObj.SetUserData(jSONObject3, new PublishAckListener() {
                    public void operationComplete(int i, String str, long j) {
                        if (operationCallback == null) {
                            return;
                        }
                        if (i == 0) {
                            operationCallback.onSuccess();
                        } else {
                            operationCallback.onError(i);
                        }
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setEnvInfo(Context context) {
        String str;
        String str2 = "";
        String str3 = "";
        try {
            String networkOperator;
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (!(connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null)) {
                str2 = connectivityManager.getActiveNetworkInfo().getTypeName();
            }
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(io.rong.imlib.statistics.UserData.PHONE_KEY);
            if (telephonyManager != null) {
                networkOperator = telephonyManager.getNetworkOperator();
            } else {
                networkOperator = str3;
            }
            str = networkOperator;
        } catch (SecurityException e) {
            e.printStackTrace();
            str = str3;
        }
        str3 = Build.MANUFACTURER;
        String str4 = Build.MODEL;
        if (str3 == null) {
            str3 = "";
        }
        if (str4 == null) {
            str4 = "";
        }
        nativeObj.SetDeviceInfo(str3, str4, String.valueOf(VERSION.SDK_INT), str2, str);
    }

    public Message getMessageByUid(String str) {
        NativeObject.Message GetMessageByUId = nativeObj.GetMessageByUId(str);
        if (GetMessageByUId == null) {
            return null;
        }
        Message message = new Message(GetMessageByUId);
        message.setContent(renderMessageContent(GetMessageByUId.getObjectName(), GetMessageByUId.getContent(), message));
        return message;
    }

    public int setupRealTimeLocation(Context context, int i, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (this.mRealTimeLocationManager != null) {
            return this.mRealTimeLocationManager.setupRealTimeLocation(context, ConversationType.setValue(i), str);
        } else {
            RLog.m19420e(TAG, "setupRealTimeLocation RealTimeLocationManager Not setup!");
            return -1;
        }
    }

    public int startRealTimeLocation(int i, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (this.mRealTimeLocationManager != null) {
            return this.mRealTimeLocationManager.startRealTimeLocation(ConversationType.setValue(i), str);
        } else {
            RLog.m19420e(TAG, "startRealTimeLocation RealTimeLocationManager Not setup!");
            return -1;
        }
    }

    public int joinRealTimeLocation(int i, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (this.mRealTimeLocationManager != null) {
            return this.mRealTimeLocationManager.joinRealTimeLocation(ConversationType.setValue(i), str);
        } else {
            RLog.m19420e(TAG, "joinRealTimeLocation RealTimeLocationManager Not setup!");
            return -1;
        }
    }

    public void quitRealTimeLocation(int i, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (this.mRealTimeLocationManager == null) {
            RLog.m19420e(TAG, "quitRealTimeLocation RealTimeLocationManager Not setup!");
        } else {
            this.mRealTimeLocationManager.quitRealTimeLocation(ConversationType.setValue(i), str);
        }
    }

    public List<String> getRealTimeLocationParticipants(int i, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (this.mRealTimeLocationManager != null) {
            return this.mRealTimeLocationManager.getRealTimeLocationParticipants(ConversationType.setValue(i), str);
        } else {
            RLog.m19420e(TAG, "getRealTimeLocationParticipants RealTimeLocationManager Not setup!");
            return null;
        }
    }

    public RealTimeLocationStatus getRealTimeLocationCurrentState(int i, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (this.mRealTimeLocationManager != null) {
            return this.mRealTimeLocationManager.getRealTimeLocationCurrentState(ConversationType.setValue(i), str);
        } else {
            RLog.m19420e(TAG, "getRealTimeLocationCurrentState RealTimeLocationManager Not setup!");
            return RealTimeLocationStatus.RC_REAL_TIME_LOCATION_STATUS_IDLE;
        }
    }

    public void addListener(int i, String str, RealTimeLocationListener realTimeLocationListener) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (this.mRealTimeLocationManager == null) {
            RLog.m19420e(TAG, "addListener RealTimeLocationManager Not setup!");
        } else {
            this.mRealTimeLocationManager.addListener(ConversationType.setValue(i), str, realTimeLocationListener);
        }
    }

    public void removeListener(int i, String str, RealTimeLocationListener realTimeLocationListener) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (this.mRealTimeLocationManager == null) {
            RLog.m19420e(TAG, "removeListener RealTimeLocationManager Not setup!");
        } else {
            this.mRealTimeLocationManager.removeListener(ConversationType.setValue(i), str, realTimeLocationListener);
        }
    }

    public void updateRealTimeLocationStatus(int i, String str, double d, double d2) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (this.mRealTimeLocationManager == null) {
            RLog.m19420e(TAG, "removeListener RealTimeLocationManager Not setup!");
        } else {
            this.mRealTimeLocationManager.updateLocation(ConversationType.setValue(i), str, d, d2);
        }
    }

    public boolean updateMessageReceiptStatus(String str, int i, long j) {
        if (nativeObj != null) {
            return nativeObj.UpdateMessageReceiptStatus(str, i, j);
        }
        throw new RuntimeException("NativeClient 尚未初始化!");
    }

    public long getSendTimeByMessageId(int i) {
        if (nativeObj != null) {
            return nativeObj.GetSendTimeByMessageId(i);
        }
        throw new RuntimeException("NativeClient 尚未初始化!");
    }

    public boolean updateConversationInfo(ConversationType conversationType, String str, String str2, String str3) {
        if (nativeObj != null) {
            return nativeObj.UpdateConversationInfo(str, conversationType.getValue(), str2, str3);
        }
        throw new RuntimeException("NativeClient 尚未初始化!");
    }

    public void getVoIPKey(int i, String str, String str2, final IResultCallback<String> iResultCallback) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        }
        nativeObj.GetVoIPKey(i, str, str2, new TokenListener() {
            public void OnError(int i, String str) {
                if (i == 0) {
                    iResultCallback.onSuccess(str);
                } else {
                    iResultCallback.onError(i);
                }
            }
        });
    }

    public String getVoIPCallInfo() {
        if (nativeObj != null) {
            return NavigationClient.getInstance().getVoIPCallInfo(this.mContext);
        }
        throw new RuntimeException("NativeClient 尚未初始化!");
    }

    private String ShortMD5(String... strArr) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            for (String append : strArr) {
                stringBuilder.append(append);
            }
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(stringBuilder.toString().getBytes());
            return new String(Base64.encode(instance.digest(), 2)).replace(SimpleComparison.EQUAL_TO_OPERATION, "").replace(Marker.ANY_NON_NULL_MARKER, HelpFormatter.DEFAULT_OPT_PREFIX).replace("/", "_").replace("\n", "");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void setServerInfo(String str, String str2) {
        this.mNaviServer = str;
        this.mFileServer = str2;
        NavigationClient.getInstance().setNaviDomain(str);
    }

    public void getPCAuthConfig(final IResultCallback<String> iResultCallback) {
        nativeObj.GetAuthConfig(new TokenListener() {
            public void OnError(int i, String str) {
                RLog.m19422i(NativeClient.TAG, "getPCAuthConfig: " + i + " --- " + str);
                if (i != 0 || str == null) {
                    iResultCallback.onError(i);
                    return;
                }
                String[] split = str.split(";;;");
                String str2 = split[0];
                String str3 = split[1];
                Object cMPServer = NavigationClient.getInstance().getCMPServer();
                if (!TextUtils.isEmpty(cMPServer)) {
                    String str4 = cMPServer.split(":")[0];
                    iResultCallback.onSuccess(str2 + ":" + str3 + ":" + str4);
                    PCAuthConfig.getInstance().postConfig(str2, str3, NativeClient.this.appKey, NativeClient.this.mNaviServer, str4);
                }
            }
        });
    }

    public boolean setMessageContent(int i, byte[] bArr, String str) {
        if (nativeObj != null) {
            return nativeObj.SetMessageContent(i, bArr, str);
        }
        throw new RuntimeException("NativeClient 尚未初始化!");
    }

    public String getToken() {
        return this.token;
    }

    public String getAppKey() {
        return this.appKey;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    private boolean isMentionedMessage(Message message) {
        if (!(message.getContent() instanceof TextMessage) || ((TextMessage) message.getContent()).getMentionedInfo() == null) {
            return false;
        }
        return true;
    }

    public List<Message> getUnreadMentionedMessages(ConversationType conversationType, String str) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        } else if (conversationType == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("ConversationTypes 或 targetId 参数异常。");
        } else {
            NativeObject.Message[] GetMentionMessages = nativeObj.GetMentionMessages(str.trim(), conversationType.getValue());
            List arrayList = new ArrayList();
            if (GetMentionMessages != null) {
                for (NativeObject.Message message : GetMentionMessages) {
                    Message message2 = new Message(message);
                    message2.setContent(renderMessageContent(message.getObjectName(), message.getContent(), message2));
                    arrayList.add(message2);
                }
            }
            return arrayList;
        }
    }

    public void setLogStatus(boolean z) {
        if (nativeObj == null) {
            throw new RuntimeException("NativeClient 尚未初始化!");
        }
        nativeObj.SetLogStatus(z);
    }
}
