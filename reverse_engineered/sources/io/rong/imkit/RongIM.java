package io.rong.imkit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.view.View;
import com.beastbikes.framework.ui.android.WebActivity;
import io.rong.common.RLog;
import io.rong.eventbus.EventBus;
import io.rong.imageloader.core.ImageLoader;
import io.rong.imkit.manager.AudioRecordManager;
import io.rong.imkit.mention.RongMentionManager;
import io.rong.imkit.model.ConversationTypeFilter;
import io.rong.imkit.model.Event.ConversationRemoveEvent;
import io.rong.imkit.model.Event.ConversationTopEvent;
import io.rong.imkit.model.Event.ConversationUnreadEvent;
import io.rong.imkit.model.Event.MessageDeleteEvent;
import io.rong.imkit.model.Event.MessageSentStatusEvent;
import io.rong.imkit.model.Event.MessagesClearEvent;
import io.rong.imkit.model.Event.OnMessageSendErrorEvent;
import io.rong.imkit.model.Event.OnReceiveMessageProgressEvent;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imkit.userInfoCache.RongUserInfoManager;
import io.rong.imkit.utils.SystemUtils;
import io.rong.imkit.widget.provider.DiscussionNotificationMessageItemProvider;
import io.rong.imkit.widget.provider.HandshakeMessageItemProvider;
import io.rong.imkit.widget.provider.IContainerItemProvider.ConversationProvider;
import io.rong.imkit.widget.provider.IContainerItemProvider.MessageProvider;
import io.rong.imkit.widget.provider.ImageMessageItemProvider;
import io.rong.imkit.widget.provider.InfoNotificationMsgItemProvider;
import io.rong.imkit.widget.provider.InputProvider.ExtendProvider;
import io.rong.imkit.widget.provider.InputProvider.MainInputProvider;
import io.rong.imkit.widget.provider.LocationMessageItemProvider;
import io.rong.imkit.widget.provider.PublicServiceMultiRichContentMessageProvider;
import io.rong.imkit.widget.provider.PublicServiceRichContentMessageProvider;
import io.rong.imkit.widget.provider.RecallMessageItemProvider;
import io.rong.imkit.widget.provider.RichContentMessageItemProvider;
import io.rong.imkit.widget.provider.TextMessageItemProvider;
import io.rong.imkit.widget.provider.UnknownMessageItemProvider;
import io.rong.imkit.widget.provider.VoiceMessageItemProvider;
import io.rong.imlib.AnnotationNotFoundException;
import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient$BlacklistStatus;
import io.rong.imlib.RongIMClient$ConnectionStatusListener$ConnectionStatus;
import io.rong.imlib.RongIMClient$CreateDiscussionCallback;
import io.rong.imlib.RongIMClient$DiscussionInviteStatus;
import io.rong.imlib.RongIMClient$DownloadMediaCallback;
import io.rong.imlib.RongIMClient$ErrorCode;
import io.rong.imlib.RongIMClient$GetBlacklistCallback;
import io.rong.imlib.RongIMClient$GetNotificationQuietHoursCallback;
import io.rong.imlib.RongIMClient$MediaType;
import io.rong.imlib.RongIMClient$OperationCallback;
import io.rong.imlib.RongIMClient$ResultCallback$Result;
import io.rong.imlib.RongIMClient$SearchType;
import io.rong.imlib.RongIMClient$SendImageMessageCallback;
import io.rong.imlib.RongIMClient$SendImageMessageWithUploadListenerCallback;
import io.rong.imlib.RongIMClient$SendMessageCallback;
import io.rong.imlib.RongIMClient.ConnectCallback;
import io.rong.imlib.RongIMClient.ConnectionStatusListener;
import io.rong.imlib.RongIMClient.OnReceiveMessageListener;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationNotificationStatus;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Conversation.PublicServiceType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$ReceivedStatus;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.PublicServiceProfileList;
import io.rong.imlib.model.UserData;
import io.rong.imlib.model.UserInfo;
import io.rong.message.InformationNotificationMessage;
import io.rong.push.RongPushClient;
import java.util.List;
import java.util.Map;
import java.util.Timer;

public class RongIM {
    private static final String TAG = RongIM.class.getSimpleName();
    private static Timer mCallTimer;
    private static ConnectionStatusListener mConnectionStatusListener = new RongIM$3();
    private static Context mContext;
    static ConnectionStatusListener sConnectionStatusListener;
    static OnReceiveMessageListener sMessageListener;
    private static boolean uiReady = false;
    private String mAppKey;
    private RongIMClientWrapper mClientWrapper;

    public interface ConversationBehaviorListener {
        boolean onMessageClick(Context context, View view, Message message);

        boolean onMessageLinkClick(Context context, String str);

        boolean onMessageLongClick(Context context, View view, Message message);

        boolean onUserPortraitClick(Context context, ConversationType conversationType, UserInfo userInfo);

        boolean onUserPortraitLongClick(Context context, ConversationType conversationType, UserInfo userInfo);
    }

    public interface GroupInfoProvider {
        Group getGroupInfo(String str);
    }

    public interface OnReceiveUnreadCountChangedListener {
        void onMessageIncreased(int i);
    }

    public interface UserInfoProvider {
        UserInfo getUserInfo(String str);
    }

    private RongIM() {
        this.mClientWrapper = new RongIMClientWrapper();
    }

    private static void saveToken(String str) {
        Editor edit = mContext.getSharedPreferences("rc_token", 0).edit();
        edit.putString("token_value", str);
        edit.commit();
    }

    public static void init(Context context) {
        String curProcessName = SystemUtils.getCurProcessName(context);
        if (context.getPackageName().equals(curProcessName)) {
            RLog.i(TAG, "init : " + curProcessName);
            mContext = context;
            RongContext.init(context);
            if (TextUtils.isEmpty(RongIM$SingletonHolder.sRongIM.mAppKey)) {
                try {
                    ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                    if (applicationInfo != null) {
                        RongIM$SingletonHolder.sRongIM.mAppKey = applicationInfo.metaData.getString("RONG_CLOUD_APP_KEY");
                    }
                    if (TextUtils.isEmpty(RongIM$SingletonHolder.sRongIM.mAppKey)) {
                        throw new IllegalArgumentException("can't find RONG_CLOUD_APP_KEY in AndroidManifest.xml.");
                    }
                } catch (NameNotFoundException e) {
                    e.printStackTrace();
                    throw new ExceptionInInitializerError("can't find packageName!");
                }
            }
            RongIMClient.init(context, RongIM$SingletonHolder.sRongIM.mAppKey);
            registerMessageTemplate(new TextMessageItemProvider());
            registerMessageTemplate(new ImageMessageItemProvider());
            registerMessageTemplate(new LocationMessageItemProvider());
            registerMessageTemplate(new VoiceMessageItemProvider(context));
            registerMessageTemplate(new DiscussionNotificationMessageItemProvider());
            registerMessageTemplate(new InfoNotificationMsgItemProvider());
            registerMessageTemplate(new RichContentMessageItemProvider());
            registerMessageTemplate(new PublicServiceMultiRichContentMessageProvider());
            registerMessageTemplate(new PublicServiceRichContentMessageProvider());
            registerMessageTemplate(new HandshakeMessageItemProvider());
            registerMessageTemplate(new RecallMessageItemProvider());
            registerMessageTemplate(new UnknownMessageItemProvider());
            Intent intent = new Intent("io.rong.intent.action.SDK_INIT");
            intent.setPackage(context.getPackageName());
            context.sendBroadcast(intent);
            return;
        }
        RLog.w(TAG, "Init. Current process : " + curProcessName);
    }

    public static void init(Context context, String str) {
        String curProcessName = SystemUtils.getCurProcessName(context);
        if (context.getPackageName().equals(curProcessName)) {
            RLog.i(TAG, "init with appKey : " + curProcessName);
            RongIM$SingletonHolder.sRongIM.mAppKey = str;
            RongContext.init(context);
            init(context);
            return;
        }
        RLog.w(TAG, "Init with appKey. Current process : " + curProcessName);
    }

    public static void registerMessageType(Class<? extends MessageContent> cls) {
        if (RongContext.getInstance() != null) {
            try {
                RongIMClient.registerMessageType(cls);
            } catch (AnnotationNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void registerMessageTemplate(MessageProvider messageProvider) {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().registerMessageTemplate(messageProvider);
        }
    }

    public void setCurrentUserInfo(UserInfo userInfo) {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().setCurrentUserInfo(userInfo);
        }
    }

    public static RongIM connect(String str, ConnectCallback connectCallback) {
        if (RongContext.getInstance() == null) {
            RLog.e(TAG, "connect should be called in main process.");
            return RongIM$SingletonHolder.sRongIM;
        }
        saveToken(str);
        initData();
        RongIMClient.connect(str, new RongIM$1(connectCallback));
        if (!uiReady && mCallTimer == null) {
            mCallTimer = new Timer();
            mCallTimer.schedule(new RongIM$2(), 0, 1000);
        }
        return RongIM$SingletonHolder.sRongIM;
    }

    private static void initData() {
        RongIMClient.setOnReceiveMessageListener(new RongIM$4());
        if (RongIMClient.getInstance().getReadReceipt()) {
            RongIMClient.setReadReceiptListener(new RongIM$5());
        }
        RongIMClient.setRecallMessageListener(new RongIM$6());
        RongIMClient.setConnectionStatusListener(mConnectionStatusListener);
    }

    public static void setOnReceiveMessageListener(OnReceiveMessageListener onReceiveMessageListener) {
        RLog.i(TAG, "RongIM setOnReceiveMessageListener");
        sMessageListener = onReceiveMessageListener;
    }

    public static void setConnectionStatusListener(ConnectionStatusListener connectionStatusListener) {
        RongIMClient.setConnectionStatusListener(connectionStatusListener);
        sConnectionStatusListener = connectionStatusListener;
    }

    @Deprecated
    public RongIMClientWrapper getRongIMClient() {
        return this.mClientWrapper;
    }

    @Deprecated
    public void disconnect(boolean z) {
        RongIMClient.getInstance().disconnect(z);
    }

    public void logout() {
        RongIMClient.getInstance().logout();
        if (!(RongContext.getInstance() == null || RongContext.getInstance().getMessageCounterLogic() == null)) {
            RongContext.getInstance().getMessageCounterLogic().clearCache();
        }
        RongUserInfoManager.getInstance().uninit();
    }

    public void setGroupMembersProvider(RongIM$IGroupMembersProvider rongIM$IGroupMembersProvider) {
        RongMentionManager.getInstance().setGroupMembersProvider(rongIM$IGroupMembersProvider);
    }

    public static void setLocationProvider(RongIM$LocationProvider rongIM$LocationProvider) {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().setLocationProvider(rongIM$LocationProvider);
        }
    }

    public void disconnect() {
        RongIMClient.getInstance().disconnect();
    }

    public static RongIM getInstance() {
        return RongIM$SingletonHolder.sRongIM;
    }

    @Deprecated
    public void startConversationList(Context context) {
        if (context == null) {
            throw new IllegalArgumentException();
        } else if (RongContext.getInstance() == null) {
            throw new ExceptionInInitializerError("RongCloud SDK not init");
        } else {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon().appendPath("conversationlist").build()));
        }
    }

    public void startConversationList(Context context, Map<String, Boolean> map) {
        if (context == null) {
            throw new IllegalArgumentException();
        } else if (RongContext.getInstance() == null) {
            throw new ExceptionInInitializerError("RongCloud SDK not init");
        } else {
            Builder appendPath = Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon().appendPath("conversationlist");
            if (map != null && map.size() > 0) {
                for (String str : map.keySet()) {
                    appendPath.appendQueryParameter(str, ((Boolean) map.get(str)).booleanValue() ? "true" : "false");
                }
            }
            context.startActivity(new Intent("android.intent.action.VIEW", appendPath.build()));
        }
    }

    public void startSubConversationList(Context context, ConversationType conversationType) {
        if (context == null) {
            throw new IllegalArgumentException();
        } else if (RongContext.getInstance() == null) {
            throw new ExceptionInInitializerError("RongCloud SDK not init");
        } else {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon().appendPath("subconversationlist").appendQueryParameter("type", conversationType.getName()).build()));
        }
    }

    public static void setConversationBehaviorListener(ConversationBehaviorListener conversationBehaviorListener) {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().setConversationBehaviorListener(conversationBehaviorListener);
        }
    }

    public static void setConversationListBehaviorListener(RongIM$ConversationListBehaviorListener rongIM$ConversationListBehaviorListener) {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().setConversationListBehaviorListener(rongIM$ConversationListBehaviorListener);
        }
    }

    public static void setPublicServiceBehaviorListener(RongIM$PublicServiceBehaviorListener rongIM$PublicServiceBehaviorListener) {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().setPublicServiceBehaviorListener(rongIM$PublicServiceBehaviorListener);
        }
    }

    public void startPrivateChat(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        } else if (RongContext.getInstance() == null) {
            throw new ExceptionInInitializerError("RongCloud SDK not init");
        } else {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon().appendPath("conversation").appendPath(ConversationType.PRIVATE.getName().toLowerCase()).appendQueryParameter("targetId", str).appendQueryParameter(WebActivity.EXTRA_TITLE, str2).build()));
        }
    }

    public void startConversation(Context context, ConversationType conversationType, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || conversationType == null) {
            throw new IllegalArgumentException();
        }
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("rong://" + context.getApplicationInfo().processName).buildUpon().appendPath("conversation").appendPath(conversationType.getName().toLowerCase()).appendQueryParameter("targetId", str).appendQueryParameter(WebActivity.EXTRA_TITLE, str2).build()));
    }

    public void createDiscussionChat(Context context, List<String> list, String str) {
        if (context == null || list == null || list.size() == 0) {
            throw new IllegalArgumentException();
        }
        RongIMClient.getInstance().createDiscussion(str, list, new RongIM$7(this, context, list, str));
    }

    public void createDiscussionChat(Context context, List<String> list, String str, RongIMClient$CreateDiscussionCallback rongIMClient$CreateDiscussionCallback) {
        if (context == null || list == null || list.size() == 0) {
            throw new IllegalArgumentException();
        }
        RongIMClient.getInstance().createDiscussion(str, list, new RongIM$8(this, context, list, str, rongIMClient$CreateDiscussionCallback));
    }

    public void startDiscussionChat(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        }
        context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon().appendPath("conversation").appendPath(ConversationType.DISCUSSION.getName().toLowerCase()).appendQueryParameter("targetId", str).appendQueryParameter(WebActivity.EXTRA_TITLE, str2).build()));
    }

    public void startGroupChat(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        } else if (RongContext.getInstance() == null) {
            throw new ExceptionInInitializerError("RongCloud SDK not init");
        } else {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon().appendPath("conversation").appendPath(ConversationType.GROUP.getName().toLowerCase()).appendQueryParameter("targetId", str).appendQueryParameter(WebActivity.EXTRA_TITLE, str2).build()));
        }
    }

    public void startChatRoomChat(Context context, String str, boolean z) {
        if (context == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        } else if (RongContext.getInstance() == null) {
            throw new ExceptionInInitializerError("RongCloud SDK not init");
        } else {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon().appendPath("conversation").appendPath(ConversationType.CHATROOM.getName().toLowerCase()).appendQueryParameter("targetId", str).build());
            intent.putExtra("createIfNotExist", z);
            context.startActivity(intent);
        }
    }

    public void startCustomerServiceChat(Context context, String str, String str2, CSCustomServiceInfo cSCustomServiceInfo) {
        if (context == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        } else if (RongContext.getInstance() == null) {
            throw new ExceptionInInitializerError("RongCloud SDK not init");
        } else {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("rong://" + context.getApplicationInfo().packageName).buildUpon().appendPath("conversation").appendPath(ConversationType.CUSTOMER_SERVICE.getName().toLowerCase()).appendQueryParameter("targetId", str).appendQueryParameter(WebActivity.EXTRA_TITLE, str2).build());
            intent.putExtra("customServiceInfo", cSCustomServiceInfo);
            context.startActivity(intent);
        }
    }

    public static void setUserInfoProvider(UserInfoProvider userInfoProvider, boolean z) {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().setGetUserInfoProvider(userInfoProvider, z);
        }
    }

    public static void setGroupUserInfoProvider(RongIM$GroupUserInfoProvider rongIM$GroupUserInfoProvider, boolean z) {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().setGroupUserInfoProvider(rongIM$GroupUserInfoProvider, z);
        }
    }

    public static void setGroupInfoProvider(GroupInfoProvider groupInfoProvider, boolean z) {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().setGetGroupInfoProvider(groupInfoProvider, z);
        }
    }

    public void refreshDiscussionCache(Discussion discussion) {
        if (discussion != null) {
            RongUserInfoManager.getInstance().setDiscussionInfo(discussion);
        }
    }

    public void refreshUserInfoCache(UserInfo userInfo) {
        if (userInfo != null) {
            RongUserInfoManager.getInstance().setUserInfo(userInfo);
        }
    }

    public void refreshGroupUserInfoCache(GroupUserInfo groupUserInfo) {
        if (groupUserInfo != null) {
            RongUserInfoManager.getInstance().setGroupUserInfo(groupUserInfo);
        }
    }

    public void refreshGroupInfoCache(Group group) {
        if (group != null) {
            RongUserInfoManager.getInstance().setGroupInfo(group);
        }
    }

    public void setSendMessageListener(RongIM$OnSendMessageListener rongIM$OnSendMessageListener) {
        if (rongIM$OnSendMessageListener == null) {
            throw new IllegalArgumentException();
        } else if (RongContext.getInstance() == null) {
            throw new ExceptionInInitializerError("RongCloud SDK not init");
        } else {
            RongContext.getInstance().setOnSendMessageListener(rongIM$OnSendMessageListener);
        }
    }

    public void setMessageAttachedUserInfo(boolean z) {
        RongContext.getInstance().setUserInfoAttachedState(z);
    }

    public void setOnReceiveUnreadCountChangedListener(OnReceiveUnreadCountChangedListener onReceiveUnreadCountChangedListener, ConversationType... conversationTypeArr) {
        if (RongContext.getInstance() != null && onReceiveUnreadCountChangedListener != null && conversationTypeArr != null && conversationTypeArr.length > 0) {
            RongContext.getInstance().getMessageCounterLogic().registerMessageCounter(new RongIM$9(this, ConversationTypeFilter.obtain(conversationTypeArr), onReceiveUnreadCountChangedListener));
        }
    }

    public void startPublicServiceProfile(Context context, ConversationType conversationType, String str) {
        if (context == null || conversationType == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException();
        } else if (RongContext.getInstance() == null) {
            throw new ExceptionInInitializerError("RongCloud SDK not init");
        } else {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("rong://" + context.getApplicationInfo().processName).buildUpon().appendPath("publicServiceProfile").appendPath(conversationType.getName().toLowerCase()).appendQueryParameter("targetId", str).build());
            intent.setFlags(268435456);
            context.startActivity(intent);
        }
    }

    public static void setPrimaryInputProvider(MainInputProvider mainInputProvider) {
        if (RongContext.getInstance() == null) {
            return;
        }
        if (mainInputProvider == null) {
            throw new IllegalArgumentException();
        }
        RongContext.getInstance().setPrimaryInputProvider(mainInputProvider);
    }

    public static void setSecondaryInputProvider(MainInputProvider mainInputProvider) {
        if (RongContext.getInstance() == null) {
            return;
        }
        if (mainInputProvider == null) {
            throw new IllegalArgumentException();
        }
        RongContext.getInstance().setSecondaryInputProvider(mainInputProvider);
    }

    public static void resetInputExtensionProvider(ConversationType conversationType, ExtendProvider[] extendProviderArr) {
        if (RongContext.getInstance() == null) {
            return;
        }
        if (extendProviderArr == null) {
            throw new IllegalArgumentException();
        }
        RongContext.getInstance().resetInputExtentionProvider(conversationType, extendProviderArr);
    }

    public static void addInputExtensionProvider(ConversationType conversationType, ExtendProvider[] extendProviderArr) {
        if (RongContext.getInstance() == null) {
            return;
        }
        if (extendProviderArr == null) {
            throw new IllegalArgumentException();
        }
        RongContext.getInstance().addInputExtentionProvider(conversationType, extendProviderArr);
    }

    public void registerConversationTemplate(ConversationProvider conversationProvider) {
        if (RongContext.getInstance() == null) {
            return;
        }
        if (conversationProvider != null) {
            throw new IllegalArgumentException();
        }
        RongContext.getInstance().registerConversationTemplate(conversationProvider);
    }

    public void enableNewComingMessageIcon(boolean z) {
        RongContext.getInstance().showNewMessageIcon(z);
    }

    public void enableUnreadMessageIcon(boolean z) {
        RongContext.getInstance().showUnreadMessageIcon(z);
    }

    public void setMaxVoiceDurationg(int i) {
        AudioRecordManager.getInstance().setMaxVoiceDuration(i);
    }

    public RongIMClient$ConnectionStatusListener$ConnectionStatus getCurrentConnectionStatus() {
        return RongIMClient.getInstance().getCurrentConnectionStatus();
    }

    public void getConversationList(ResultCallback<List<Conversation>> resultCallback) {
        uiReady = true;
        RongIMClient.getInstance().getConversationList((ResultCallback) resultCallback);
    }

    @Deprecated
    public List<Conversation> getConversationList() {
        uiReady = true;
        return RongIMClient.getInstance().getConversationList();
    }

    public void getConversationList(ResultCallback<List<Conversation>> resultCallback, ConversationType... conversationTypeArr) {
        uiReady = true;
        RongIMClient.getInstance().getConversationList(resultCallback, conversationTypeArr);
    }

    @Deprecated
    public List<Conversation> getConversationList(ConversationType... conversationTypeArr) {
        uiReady = true;
        return RongIMClient.getInstance().getConversationList(conversationTypeArr);
    }

    public void getConversation(ConversationType conversationType, String str, ResultCallback<Conversation> resultCallback) {
        uiReady = true;
        RongIMClient.getInstance().getConversation(conversationType, str, resultCallback);
    }

    @Deprecated
    public Conversation getConversation(ConversationType conversationType, String str) {
        uiReady = true;
        return RongIMClient.getInstance().getConversation(conversationType, str);
    }

    public void removeConversation(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        RongIMClient.getInstance().removeConversation(conversationType, str, new RongIM$10(this, resultCallback, conversationType, str));
    }

    @Deprecated
    public boolean removeConversation(ConversationType conversationType, String str) {
        boolean removeConversation = RongIMClient.getInstance().removeConversation(conversationType, str);
        if (removeConversation) {
            RongContext.getInstance().getEventBus().post(new ConversationRemoveEvent(conversationType, str));
        }
        return removeConversation;
    }

    public void setConversationToTop(ConversationType conversationType, String str, boolean z, ResultCallback<Boolean> resultCallback) {
        RongIMClient.getInstance().setConversationToTop(conversationType, str, z, new RongIM$11(this, resultCallback, conversationType, str, z));
    }

    @Deprecated
    public boolean setConversationToTop(ConversationType conversationType, String str, boolean z) {
        boolean conversationToTop = RongIMClient.getInstance().setConversationToTop(conversationType, str, z);
        if (conversationToTop) {
            RongContext.getInstance().getEventBus().post(new ConversationTopEvent(conversationType, str, z));
        }
        return conversationToTop;
    }

    public void getTotalUnreadCount(ResultCallback<Integer> resultCallback) {
        RongIMClient.getInstance().getTotalUnreadCount(new RongIM$12(this, resultCallback));
    }

    @Deprecated
    public int getTotalUnreadCount() {
        return RongIMClient.getInstance().getTotalUnreadCount();
    }

    public void getUnreadCount(ConversationType conversationType, String str, ResultCallback<Integer> resultCallback) {
        RongIMClient.getInstance().getUnreadCount(conversationType, str, resultCallback);
    }

    @Deprecated
    public int getUnreadCount(ConversationType conversationType, String str) {
        return RongIMClient.getInstance().getUnreadCount(conversationType, str);
    }

    public void getUnreadCount(ResultCallback<Integer> resultCallback, ConversationType... conversationTypeArr) {
        RongIMClient.getInstance().getUnreadCount((ResultCallback) resultCallback, conversationTypeArr);
    }

    @Deprecated
    public int getUnreadCount(ConversationType... conversationTypeArr) {
        return RongIMClient.getInstance().getUnreadCount(conversationTypeArr);
    }

    public void getUnreadCount(ConversationType[] conversationTypeArr, ResultCallback<Integer> resultCallback) {
        RongIMClient.getInstance().getUnreadCount(conversationTypeArr, (ResultCallback) resultCallback);
    }

    @Deprecated
    public List<Message> getLatestMessages(ConversationType conversationType, String str, int i) {
        return RongIMClient.getInstance().getLatestMessages(conversationType, str, i);
    }

    public void getLatestMessages(ConversationType conversationType, String str, int i, ResultCallback<List<Message>> resultCallback) {
        RongIMClient.getInstance().getLatestMessages(conversationType, str, i, resultCallback);
    }

    @Deprecated
    public List<Message> getHistoryMessages(ConversationType conversationType, String str, int i, int i2) {
        return RongIMClient.getInstance().getHistoryMessages(conversationType, str, i, i2);
    }

    @Deprecated
    public List<Message> getHistoryMessages(ConversationType conversationType, String str, String str2, int i, int i2) {
        return RongIMClient.getInstance().getHistoryMessages(conversationType, str, str2, i, i2);
    }

    public void getHistoryMessages(ConversationType conversationType, String str, String str2, int i, int i2, ResultCallback<List<Message>> resultCallback) {
        RongIMClient.getInstance().getHistoryMessages(conversationType, str, str2, i, i2, resultCallback);
    }

    public void getHistoryMessages(ConversationType conversationType, String str, int i, int i2, ResultCallback<List<Message>> resultCallback) {
        RongIMClient.getInstance().getHistoryMessages(conversationType, str, i, i2, (ResultCallback) resultCallback);
    }

    public void getRemoteHistoryMessages(ConversationType conversationType, String str, long j, int i, ResultCallback<List<Message>> resultCallback) {
        RongIMClient.getInstance().getRemoteHistoryMessages(conversationType, str, j, i, resultCallback);
    }

    @Deprecated
    public boolean deleteMessages(int[] iArr) {
        Boolean valueOf = Boolean.valueOf(RongIMClient.getInstance().deleteMessages(iArr));
        if (valueOf.booleanValue()) {
            RongContext.getInstance().getEventBus().post(new MessageDeleteEvent(iArr));
        }
        return valueOf.booleanValue();
    }

    public void deleteMessages(int[] iArr, ResultCallback<Boolean> resultCallback) {
        RongIMClient.getInstance().deleteMessages(iArr, new RongIM$13(this, iArr, resultCallback));
    }

    public void deleteMessages(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        RongIMClient.getInstance().deleteMessages(conversationType, str, new RongIM$14(this, conversationType, str, resultCallback));
    }

    @Deprecated
    public boolean clearMessages(ConversationType conversationType, String str) {
        boolean clearMessages = RongIMClient.getInstance().clearMessages(conversationType, str);
        if (clearMessages) {
            RongContext.getInstance().getEventBus().post(new MessagesClearEvent(conversationType, str));
        }
        return clearMessages;
    }

    public void clearMessages(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        RongIMClient.getInstance().clearMessages(conversationType, str, new RongIM$15(this, conversationType, str, resultCallback));
    }

    @Deprecated
    public boolean clearMessagesUnreadStatus(ConversationType conversationType, String str) {
        boolean clearMessagesUnreadStatus = RongIMClient.getInstance().clearMessagesUnreadStatus(conversationType, str);
        if (clearMessagesUnreadStatus) {
            RongContext.getInstance().getEventBus().post(new ConversationUnreadEvent(conversationType, str));
        }
        return clearMessagesUnreadStatus;
    }

    public void clearMessagesUnreadStatus(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        RongIMClient.getInstance().clearMessagesUnreadStatus(conversationType, str, new RongIM$16(this, resultCallback, conversationType, str));
    }

    @Deprecated
    public boolean setMessageExtra(int i, String str) {
        return RongIMClient.getInstance().setMessageExtra(i, str);
    }

    public void setMessageExtra(int i, String str, ResultCallback<Boolean> resultCallback) {
        RongIMClient.getInstance().setMessageExtra(i, str, resultCallback);
    }

    @Deprecated
    public boolean setMessageReceivedStatus(int i, Message$ReceivedStatus message$ReceivedStatus) {
        return RongIMClient.getInstance().setMessageReceivedStatus(i, message$ReceivedStatus);
    }

    public void setMessageReceivedStatus(int i, Message$ReceivedStatus message$ReceivedStatus, ResultCallback<Boolean> resultCallback) {
        RongIMClient.getInstance().setMessageReceivedStatus(i, message$ReceivedStatus, resultCallback);
    }

    @Deprecated
    public boolean setMessageSentStatus(int i, Message$SentStatus message$SentStatus) {
        boolean messageSentStatus = RongIMClient.getInstance().setMessageSentStatus(i, message$SentStatus);
        if (messageSentStatus) {
            RongContext.getInstance().getEventBus().post(new MessageSentStatusEvent(i, message$SentStatus));
        }
        return messageSentStatus;
    }

    public void setMessageSentStatus(int i, Message$SentStatus message$SentStatus, ResultCallback<Boolean> resultCallback) {
        RongIMClient.getInstance().setMessageSentStatus(i, message$SentStatus, new RongIM$17(this, resultCallback, i, message$SentStatus));
    }

    @Deprecated
    public String getTextMessageDraft(ConversationType conversationType, String str) {
        return RongIMClient.getInstance().getTextMessageDraft(conversationType, str);
    }

    @Deprecated
    public boolean saveTextMessageDraft(ConversationType conversationType, String str, String str2) {
        return RongIMClient.getInstance().saveTextMessageDraft(conversationType, str, str2);
    }

    @Deprecated
    public boolean clearTextMessageDraft(ConversationType conversationType, String str) {
        return RongIMClient.getInstance().clearTextMessageDraft(conversationType, str);
    }

    public void getTextMessageDraft(ConversationType conversationType, String str, ResultCallback<String> resultCallback) {
        RongIMClient.getInstance().getTextMessageDraft(conversationType, str, resultCallback);
    }

    public void saveTextMessageDraft(ConversationType conversationType, String str, String str2, ResultCallback<Boolean> resultCallback) {
        RongIMClient.getInstance().saveTextMessageDraft(conversationType, str, str2, resultCallback);
    }

    public void clearTextMessageDraft(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        RongIMClient.getInstance().clearTextMessageDraft(conversationType, str, resultCallback);
    }

    public void getDiscussion(String str, ResultCallback<Discussion> resultCallback) {
        RongIMClient.getInstance().getDiscussion(str, resultCallback);
    }

    public void setDiscussionName(String str, String str2, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().setDiscussionName(str, str2, new RongIM$18(this, rongIMClient$OperationCallback, str, str2));
    }

    public void createDiscussion(String str, List<String> list, RongIMClient$CreateDiscussionCallback rongIMClient$CreateDiscussionCallback) {
        RongIMClient.getInstance().createDiscussion(str, list, new RongIM$19(this, str, list, rongIMClient$CreateDiscussionCallback));
    }

    public void addMemberToDiscussion(String str, List<String> list, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().addMemberToDiscussion(str, list, new RongIM$20(this, str, list, rongIMClient$OperationCallback));
    }

    public void removeMemberFromDiscussion(String str, String str2, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().removeMemberFromDiscussion(str, str2, new RongIM$21(this, str, str2, rongIMClient$OperationCallback));
    }

    public void quitDiscussion(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().quitDiscussion(str, new RongIM$22(this, str, rongIMClient$OperationCallback));
    }

    public void insertMessage(ConversationType conversationType, String str, String str2, MessageContent messageContent, ResultCallback<Message> resultCallback) {
        MessageTag messageTag = (MessageTag) messageContent.getClass().getAnnotation(MessageTag.class);
        if (messageTag == null || (messageTag.flag() & 1) != 1) {
            RLog.e(TAG, "insertMessage Message is missing MessageTag.ISPERSISTED");
            return;
        }
        RongIMClient.getInstance().insertMessage(conversationType, str, str2, messageContent, new RongIM$23(this, resultCallback));
    }

    @Deprecated
    public Message insertMessage(ConversationType conversationType, String str, String str2, MessageContent messageContent) {
        Message obtain;
        MessageTag messageTag = (MessageTag) messageContent.getClass().getAnnotation(MessageTag.class);
        if (messageTag == null || (messageTag.flag() & 1) != 1) {
            obtain = Message.obtain(str, conversationType, messageContent);
            RLog.e(TAG, "insertMessage Message is missing MessageTag.ISPERSISTED");
        } else {
            obtain = RongIMClient.getInstance().insertMessage(conversationType, str, str2, messageContent);
        }
        RongContext.getInstance().getEventBus().post(obtain);
        return obtain;
    }

    @Deprecated
    public Message sendMessage(ConversationType conversationType, String str, MessageContent messageContent, String str2, String str3, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback) {
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        Message obtain = Message.obtain(str, conversationType, messageContent);
        Message filterSendMessage = filterSendMessage(obtain);
        if (filterSendMessage == null) {
            return null;
        }
        if (filterSendMessage == obtain) {
            filterSendMessage = obtain;
        }
        MessageContent messageAttachedUserInfo = setMessageAttachedUserInfo(filterSendMessage.getContent());
        obtain = RongIMClient.getInstance().sendMessage(conversationType, str, messageAttachedUserInfo, str2, str3, new RongIM$24(this, rongIMClient$ResultCallback$Result, rongIMClient$SendMessageCallback));
        MessageTag messageTag = (MessageTag) messageAttachedUserInfo.getClass().getAnnotation(MessageTag.class);
        if (messageTag != null && (messageTag.flag() & 1) == 1) {
            RongContext.getInstance().getEventBus().post(obtain);
        }
        rongIMClient$ResultCallback$Result.f17368t = obtain;
        return obtain;
    }

    public void sendMessage(ConversationType conversationType, String str, MessageContent messageContent, String str2, String str3, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback, ResultCallback<Message> resultCallback) {
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        Message obtain = Message.obtain(str, conversationType, messageContent);
        Message filterSendMessage = filterSendMessage(obtain);
        if (filterSendMessage != null) {
            if (filterSendMessage == obtain) {
                filterSendMessage = obtain;
            }
            MessageContent messageAttachedUserInfo = setMessageAttachedUserInfo(filterSendMessage.getContent());
            RongIMClient.getInstance().sendMessage(conversationType, str, messageAttachedUserInfo, str2, str3, new RongIM$25(this, rongIMClient$ResultCallback$Result, rongIMClient$SendMessageCallback), new RongIM$26(this, rongIMClient$ResultCallback$Result, resultCallback));
        }
    }

    public void sendMessage(Message message, String str, String str2, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback, ResultCallback<Message> resultCallback) {
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        Message filterSendMessage = filterSendMessage(message);
        if (filterSendMessage != null) {
            if (filterSendMessage == message) {
                filterSendMessage = message;
            }
            filterSendMessage.setContent(setMessageAttachedUserInfo(filterSendMessage.getContent()));
            RongIMClient.getInstance().sendMessage(filterSendMessage, str, str2, new RongIM$27(this, rongIMClient$ResultCallback$Result, rongIMClient$SendMessageCallback), new RongIM$28(this, rongIMClient$ResultCallback$Result, resultCallback));
        }
    }

    @Deprecated
    public Message sendMessage(Message message, String str, String str2, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback) {
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        Message filterSendMessage = filterSendMessage(message);
        if (filterSendMessage == null) {
            return null;
        }
        if (filterSendMessage != message) {
            message = filterSendMessage;
        }
        message.setContent(setMessageAttachedUserInfo(message.getContent()));
        Message sendMessage = RongIMClient.getInstance().sendMessage(message, str, str2, new RongIM$29(this, rongIMClient$ResultCallback$Result, rongIMClient$SendMessageCallback));
        MessageTag messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
        if (messageTag != null && (messageTag.flag() & 1) == 1) {
            EventBus.getDefault().post(sendMessage);
        }
        rongIMClient$ResultCallback$Result.f17368t = sendMessage;
        return sendMessage;
    }

    public void sendMessage(Message message, String str, String str2, ISendMessageCallback iSendMessageCallback) {
        Message filterSendMessage = filterSendMessage(message);
        if (filterSendMessage == null) {
            RLog.w(TAG, "sendMessage: 因在 onSend 中消息被过滤为 null，取消发送。");
            return;
        }
        if (filterSendMessage != message) {
            message = filterSendMessage;
        }
        message.setContent(setMessageAttachedUserInfo(message.getContent()));
        RongIMClient.getInstance().sendMessage(message, str, str2, new RongIM$30(this, iSendMessageCallback));
    }

    public void sendLocationMessage(Message message, String str, String str2, ISendMessageCallback iSendMessageCallback) {
        Message filterSendMessage = filterSendMessage(message);
        if (filterSendMessage == null) {
            RLog.w(TAG, "sendLocationMessage: 因在 onSend 中消息被过滤为 null，取消发送。");
            return;
        }
        if (filterSendMessage != message) {
            message = filterSendMessage;
        }
        message.setContent(setMessageAttachedUserInfo(message.getContent()));
        RongIMClient.getInstance().sendLocationMessage(message, str, str2, new RongIM$31(this, iSendMessageCallback));
    }

    public void sendImageMessage(ConversationType conversationType, String str, MessageContent messageContent, String str2, String str3, RongIMClient$SendImageMessageCallback rongIMClient$SendImageMessageCallback) {
        Message obtain = Message.obtain(str, conversationType, messageContent);
        Message filterSendMessage = filterSendMessage(obtain);
        if (filterSendMessage != null) {
            if (filterSendMessage == obtain) {
                filterSendMessage = obtain;
            }
            MessageContent messageAttachedUserInfo = setMessageAttachedUserInfo(filterSendMessage.getContent());
            RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
            rongIMClient$ResultCallback$Result.f17368t = new OnReceiveMessageProgressEvent();
            RongIMClient.getInstance().sendImageMessage(conversationType, str, messageAttachedUserInfo, str2, str3, new RongIM$32(this, rongIMClient$SendImageMessageCallback, rongIMClient$ResultCallback$Result));
        }
    }

    public void sendImageMessage(Message message, String str, String str2, RongIMClient$SendImageMessageCallback rongIMClient$SendImageMessageCallback) {
        Message filterSendMessage = filterSendMessage(message);
        if (filterSendMessage != null) {
            if (filterSendMessage != message) {
                message = filterSendMessage;
            }
            setMessageAttachedUserInfo(message.getContent());
            RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
            rongIMClient$ResultCallback$Result.f17368t = new OnReceiveMessageProgressEvent();
            RongIMClient.getInstance().sendImageMessage(message, str, str2, new RongIM$33(this, rongIMClient$SendImageMessageCallback, rongIMClient$ResultCallback$Result));
        }
    }

    public void sendImageMessage(Message message, String str, String str2, RongIMClient$SendImageMessageWithUploadListenerCallback rongIMClient$SendImageMessageWithUploadListenerCallback) {
        Message filterSendMessage = filterSendMessage(message);
        if (filterSendMessage != null) {
            if (filterSendMessage != message) {
                message = filterSendMessage;
            }
            RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
            rongIMClient$ResultCallback$Result.f17368t = new OnReceiveMessageProgressEvent();
            RongIMClient.getInstance().sendImageMessage(message, str, str2, new RongIM$34(this, rongIMClient$SendImageMessageWithUploadListenerCallback, rongIMClient$ResultCallback$Result));
        }
    }

    public void downloadMedia(ConversationType conversationType, String str, RongIMClient$MediaType rongIMClient$MediaType, String str2, RongIMClient$DownloadMediaCallback rongIMClient$DownloadMediaCallback) {
        RongIMClient.getInstance().downloadMedia(conversationType, str, rongIMClient$MediaType, str2, rongIMClient$DownloadMediaCallback);
    }

    public void downloadMedia(String str, RongIMClient$DownloadMediaCallback rongIMClient$DownloadMediaCallback) {
        ImageLoader.getInstance().loadImage(str, null, null, new RongIM$35(this, rongIMClient$DownloadMediaCallback), new RongIM$36(this, rongIMClient$DownloadMediaCallback));
    }

    public void getConversationNotificationStatus(ConversationType conversationType, String str, ResultCallback<ConversationNotificationStatus> resultCallback) {
        RongIMClient.getInstance().getConversationNotificationStatus(conversationType, str, new RongIM$37(this, str, conversationType, resultCallback));
    }

    public void setConversationNotificationStatus(ConversationType conversationType, String str, ConversationNotificationStatus conversationNotificationStatus, ResultCallback<ConversationNotificationStatus> resultCallback) {
        RongIMClient.getInstance().setConversationNotificationStatus(conversationType, str, conversationNotificationStatus, new RongIM$38(this, resultCallback, str, conversationType, conversationNotificationStatus));
    }

    public void setDiscussionInviteStatus(String str, RongIMClient$DiscussionInviteStatus rongIMClient$DiscussionInviteStatus, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().setDiscussionInviteStatus(str, rongIMClient$DiscussionInviteStatus, new RongIM$39(this, str, rongIMClient$DiscussionInviteStatus, rongIMClient$OperationCallback));
    }

    @Deprecated
    public void syncGroup(List<Group> list, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().syncGroup(list, new RongIM$40(this, list, rongIMClient$OperationCallback));
    }

    @Deprecated
    public void joinGroup(String str, String str2, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().joinGroup(str, str2, new RongIM$41(this, str, str2, rongIMClient$OperationCallback));
    }

    @Deprecated
    public void quitGroup(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().quitGroup(str, new RongIM$42(this, str, rongIMClient$OperationCallback));
    }

    public String getCurrentUserId() {
        return RongIMClient.getInstance().getCurrentUserId();
    }

    public long getDeltaTime() {
        return RongIMClient.getInstance().getDeltaTime();
    }

    public void joinChatRoom(String str, int i, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().joinChatRoom(str, i, new RongIM$43(this, str, i, rongIMClient$OperationCallback));
    }

    public void joinExistChatRoom(String str, int i, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().joinExistChatRoom(str, i, new RongIM$44(this, str, i, rongIMClient$OperationCallback));
    }

    public void quitChatRoom(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().quitChatRoom(str, new RongIM$45(this, str, rongIMClient$OperationCallback));
    }

    public void clearConversations(ResultCallback resultCallback, ConversationType... conversationTypeArr) {
        RongIMClient.getInstance().clearConversations(new RongIM$46(this, conversationTypeArr, resultCallback), conversationTypeArr);
    }

    @Deprecated
    public boolean clearConversations(ConversationType... conversationTypeArr) {
        return RongIMClient.getInstance().clearConversations(conversationTypeArr);
    }

    public void addToBlacklist(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().addToBlacklist(str, new RongIM$47(this, str, rongIMClient$OperationCallback));
    }

    public void removeFromBlacklist(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().removeFromBlacklist(str, new RongIM$48(this, str, rongIMClient$OperationCallback));
    }

    public void getBlacklistStatus(String str, ResultCallback<RongIMClient$BlacklistStatus> resultCallback) {
        RongIMClient.getInstance().getBlacklistStatus(str, resultCallback);
    }

    public void getBlacklist(RongIMClient$GetBlacklistCallback rongIMClient$GetBlacklistCallback) {
        RongIMClient.getInstance().getBlacklist(rongIMClient$GetBlacklistCallback);
    }

    public void setNotificationQuietHours(String str, int i, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().setNotificationQuietHours(str, i, new RongIM$49(this, str, i, rongIMClient$OperationCallback));
    }

    public void removeNotificationQuietHours(RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().removeNotificationQuietHours(new RongIM$50(this, rongIMClient$OperationCallback));
    }

    public void getNotificationQuietHours(RongIMClient$GetNotificationQuietHoursCallback rongIMClient$GetNotificationQuietHoursCallback) {
        RongIMClient.getInstance().getNotificationQuietHours(new RongIM$51(this, rongIMClient$GetNotificationQuietHoursCallback));
    }

    public void getPublicServiceProfile(PublicServiceType publicServiceType, String str, ResultCallback<PublicServiceProfile> resultCallback) {
        RongIMClient.getInstance().getPublicServiceProfile(publicServiceType, str, resultCallback);
    }

    public void searchPublicService(RongIMClient$SearchType rongIMClient$SearchType, String str, ResultCallback<PublicServiceProfileList> resultCallback) {
        RongIMClient.getInstance().searchPublicService(rongIMClient$SearchType, str, resultCallback);
    }

    public void searchPublicServiceByType(PublicServiceType publicServiceType, RongIMClient$SearchType rongIMClient$SearchType, String str, ResultCallback<PublicServiceProfileList> resultCallback) {
        RongIMClient.getInstance().searchPublicServiceByType(publicServiceType, rongIMClient$SearchType, str, resultCallback);
    }

    public void subscribePublicService(PublicServiceType publicServiceType, String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().subscribePublicService(publicServiceType, str, rongIMClient$OperationCallback);
    }

    public void unsubscribePublicService(PublicServiceType publicServiceType, String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().unsubscribePublicService(publicServiceType, str, rongIMClient$OperationCallback);
    }

    public void getPublicServiceList(ResultCallback<PublicServiceProfileList> resultCallback) {
        RongIMClient.getInstance().getPublicServiceList(resultCallback);
    }

    public void syncUserData(UserData userData, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        RongIMClient.getInstance().syncUserData(userData, new RongIM$52(this, rongIMClient$OperationCallback));
    }

    public void setRequestPermissionListener(RongIM$RequestPermissionsListener rongIM$RequestPermissionsListener) {
        RongContext.getInstance().setRequestPermissionListener(rongIM$RequestPermissionsListener);
    }

    public void recordNotificationEvent(String str) {
        RongPushClient.recordNotificationEvent(str);
    }

    private MessageContent setMessageAttachedUserInfo(MessageContent messageContent) {
        if (RongContext.getInstance().getUserInfoAttachedState() && messageContent.getUserInfo() == null) {
            String currentUserId = getInstance().getCurrentUserId();
            UserInfo currentUserInfo = RongContext.getInstance().getCurrentUserInfo();
            if (currentUserInfo == null) {
                currentUserInfo = RongUserInfoManager.getInstance().getUserInfo(currentUserId);
            }
            if (currentUserInfo != null) {
                messageContent.setUserInfo(currentUserInfo);
            }
        }
        return messageContent;
    }

    private Message filterSendMessage(ConversationType conversationType, String str, MessageContent messageContent) {
        Message message = new Message();
        message.setConversationType(conversationType);
        message.setTargetId(str);
        message.setContent(messageContent);
        if (RongContext.getInstance().getOnSendMessageListener() != null) {
            return RongContext.getInstance().getOnSendMessageListener().onSend(message);
        }
        return message;
    }

    private Message filterSendMessage(Message message) {
        if (RongContext.getInstance().getOnSendMessageListener() != null) {
            return RongContext.getInstance().getOnSendMessageListener().onSend(message);
        }
        return message;
    }

    private void filterSentMessage(Message message, RongIMClient$ErrorCode rongIMClient$ErrorCode) {
        boolean z = false;
        if (RongContext.getInstance().getOnSendMessageListener() != null) {
            RongIM$SentMessageErrorCode value;
            if (rongIMClient$ErrorCode != null) {
                value = RongIM$SentMessageErrorCode.setValue(rongIMClient$ErrorCode.getValue());
            } else {
                value = null;
            }
            z = RongContext.getInstance().getOnSendMessageListener().onSent(message, value);
        }
        MessageTag messageTag;
        if (rongIMClient$ErrorCode != null && !r0) {
            if (rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.NOT_IN_DISCUSSION) || rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.NOT_IN_GROUP) || rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.NOT_IN_CHATROOM) || rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.REJECTED_BY_BLACKLIST) || rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.FORBIDDEN_IN_GROUP) || rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.FORBIDDEN_IN_CHATROOM) || rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.KICKED_FROM_CHATROOM)) {
                MessageContent obtain;
                if (rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.NOT_IN_DISCUSSION)) {
                    obtain = InformationNotificationMessage.obtain(mContext.getString(C4974R.string.rc_info_not_in_discussion));
                } else if (rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.NOT_IN_GROUP)) {
                    obtain = InformationNotificationMessage.obtain(mContext.getString(C4974R.string.rc_info_not_in_group));
                } else if (rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.NOT_IN_CHATROOM)) {
                    obtain = InformationNotificationMessage.obtain(mContext.getString(C4974R.string.rc_info_not_in_chatroom));
                } else if (rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.REJECTED_BY_BLACKLIST)) {
                    obtain = InformationNotificationMessage.obtain(mContext.getString(C4974R.string.rc_rejected_by_blacklist_prompt));
                } else if (rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.FORBIDDEN_IN_GROUP)) {
                    obtain = InformationNotificationMessage.obtain(mContext.getString(C4974R.string.rc_info_forbidden_to_talk));
                } else if (rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.FORBIDDEN_IN_CHATROOM)) {
                    obtain = InformationNotificationMessage.obtain(mContext.getString(C4974R.string.rc_forbidden_in_chatroom));
                } else if (rongIMClient$ErrorCode.equals(RongIMClient$ErrorCode.KICKED_FROM_CHATROOM)) {
                    obtain = InformationNotificationMessage.obtain(mContext.getString(C4974R.string.rc_kicked_from_chatroom));
                } else {
                    obtain = null;
                }
                insertMessage(message.getConversationType(), message.getTargetId(), "rong", obtain, new RongIM$53(this));
            }
            messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
            if (messageTag != null && (messageTag.flag() & 1) == 1) {
                RongContext.getInstance().getEventBus().post(new OnMessageSendErrorEvent(message, rongIMClient$ErrorCode));
            }
        } else if (message != null) {
            messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
            if (messageTag != null && (messageTag.flag() & 1) == 1) {
                RongContext.getInstance().getEventBus().post(message);
            }
        }
    }

    public static void setServerInfo(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "setServerInfo naviServer should not be null.");
            throw new IllegalArgumentException("naviServer should not be null.");
        } else {
            RongIMClient.setServerInfo(str, str2);
        }
    }

    public void setPublicServiceMenuClickListener(IPublicServiceMenuClickListener iPublicServiceMenuClickListener) {
        if (RongContext.getInstance() != null) {
            RongContext.getInstance().setPublicServiceMenuClickListener(iPublicServiceMenuClickListener);
        }
    }

    public void recallMessage(Message message) {
        RongIMClient.getInstance().recallMessage(message, new RongIM$54(this, message));
    }
}
