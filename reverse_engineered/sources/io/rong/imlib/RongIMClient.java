package io.rong.imlib;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import ch.qos.logback.core.joran.action.Action;
import io.rong.common.RLog;
import io.rong.common.SystemUtils;
import io.rong.imlib.IRongCallback.ISendMessageCallback;
import io.rong.imlib.RongCommonDefine.GetMessageDirection;
import io.rong.imlib.TypingMessage.TypingMessageManager;
import io.rong.imlib.TypingMessage.TypingStatus;
import io.rong.imlib.TypingMessage.TypingStatusMessage;
import io.rong.imlib.common.DeviceUtils;
import io.rong.imlib.common.RongLibConst;
import io.rong.imlib.ipc.RongService;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationErrorCode;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationStatus;
import io.rong.imlib.model.CSCustomServiceInfo;
import io.rong.imlib.model.CSCustomServiceInfo.Builder;
import io.rong.imlib.model.ChatRoomInfo;
import io.rong.imlib.model.ChatRoomInfo.ChatRoomMemberOrder;
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
import io.rong.imlib.statistics.Statistics;
import io.rong.message.CSChangeModeMessage;
import io.rong.message.CSChangeModeResponseMessage;
import io.rong.message.CSEvaluateMessage;
import io.rong.message.CSEvaluateMessage$Builder;
import io.rong.message.CSHandShakeMessage;
import io.rong.message.CSHandShakeResponseMessage;
import io.rong.message.CSSuspendMessage;
import io.rong.message.CSTerminateMessage;
import io.rong.message.CSUpdateMessage;
import io.rong.message.CommandMessage;
import io.rong.message.CommandNotificationMessage;
import io.rong.message.ContactNotificationMessage;
import io.rong.message.DiscussionNotificationMessage;
import io.rong.message.HandshakeMessage;
import io.rong.message.ImageMessage;
import io.rong.message.InformationNotificationMessage;
import io.rong.message.LocationMessage;
import io.rong.message.ProfileNotificationMessage;
import io.rong.message.PublicServiceCommandMessage;
import io.rong.message.PublicServiceMultiRichContentMessage;
import io.rong.message.PublicServiceRichContentMessage;
import io.rong.message.ReadReceiptMessage;
import io.rong.message.RecallCommandMessage;
import io.rong.message.RecallNotificationMessage;
import io.rong.message.RichContentMessage;
import io.rong.message.SuspendMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;
import io.rong.push.RongPushClient;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Pattern;

public class RongIMClient {
    private static final int RECONNECT_INTERVAL = 1000;
    private static final String TAG = "RongIMClient";
    private static final String URL_STATISTIC = "https://stats.cn.ronghub.com/active.json";
    private static String mFileServer;
    private static Handler mHandler;
    private static String mNaviServer;
    private static List<Integer> reconnectList = new ArrayList();
    private static ConnectionStatusListener sConnectionListener;
    private static RongIMClient$ReadReceiptListener sReadReceiptListener;
    private static RongIMClient$RecallMessageListener sRecallMessageListener;
    private static OnReceiveMessageListener sReceiveMessageListener;
    private static Map<Integer, RongIMClient$ConnectionStatusListener$ConnectionStatus> sStateMap = new HashMap();
    private HashMap<String, RongIMClient$CustomServiceProfile> customServiceCache;
    private ICustomServiceListener customServiceListener;
    private RongIMClient$AidlConnection mAidlConnection;
    private String mAppKey;
    private Set<String> mChatroomCache;
    private ConnectChangeReceiver mConnectChangeReceiver;
    private RongIMClient$ConnectRunnable mConnectRunnable;
    private RongIMClient$ConnectionStatusListener$ConnectionStatus mConnectionStatus;
    private Context mContext;
    private String mCurrentUserId;
    private String mDeviceId;
    private RongIMClient$DisconnectRunnable mDisconnectRunnable;
    private boolean mHasConnect;
    private IHandler mLibHandler;
    private int mReconnectCount;
    private int[] mReconnectInterval;
    private RongIMClient$ReconnectRunnable mReconnectRunnable;
    private final List<String> mRegCache;
    private RongIMClient$StatusListener mStatusListener;
    private String mToken;
    private Handler mWorkHandler;

    public static abstract class ResultCallback<T> {
        public abstract void onError(RongIMClient$ErrorCode rongIMClient$ErrorCode);

        public abstract void onSuccess(T t);

        public void onFail(int i) {
            RongIMClient.mHandler.post(new RongIMClient$ResultCallback$1(this, i));
        }

        public void onFail(RongIMClient$ErrorCode rongIMClient$ErrorCode) {
            RongIMClient.mHandler.post(new RongIMClient$ResultCallback$2(this, rongIMClient$ErrorCode));
        }

        public void onCallback(T t) {
            RongIMClient.mHandler.post(new RongIMClient$ResultCallback$3(this, t));
        }
    }

    public static abstract class ConnectCallback extends ResultCallback<String> {
        public abstract void onTokenIncorrect();
    }

    public interface ConnectionStatusListener {
        void onChanged(RongIMClient$ConnectionStatusListener$ConnectionStatus rongIMClient$ConnectionStatusListener$ConnectionStatus);
    }

    public interface OnReceiveMessageListener {
        boolean onReceived(Message message, int i);
    }

    static {
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.CONNECTED.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.BIZ_ERROR_RECONNECT_SUCCESS.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_DISCONN_KICK.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_CONN_USER_OR_PASSWD_ERROR.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.TOKEN_INCORRECT);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_CONN_SERVER_UNAVAILABLE.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.SERVER_INVALID);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_CONN_PROTO_VERSION_ERROR.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_CONN_ID_REJECT.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_CONN_NOT_AUTHRORIZED.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_CONN_REDIRECTED.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_CONN_PACKAGE_NAME_INVALID.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_CONN_APP_BLOCKED_OR_DELETED.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_CONN_USER_BLOCKED.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_DISCONN_EXCEPTION.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_QUERY_ACK_NO_DATA.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_MSG_DATA_INCOMPLETE.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.BIZ_ERROR_CLIENT_NOT_INIT.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.BIZ_ERROR_DATABASE_ERROR.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.BIZ_ERROR_INVALID_PARAMETER.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.BIZ_ERROR_NO_CHANNEL.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.BIZ_ERROR_CONNECTING.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_NET_CHANNEL_INVALID.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_NET_UNAVAILABLE.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_MSG_RESP_TIMEOUT.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_HTTP_SEND_FAIL.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_HTTP_REQ_TIMEOUT.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_HTTP_RECV_FAIL.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_NAVI_RESOURCE_ERROR.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_NODE_NOT_FOUND.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_DOMAIN_NOT_RESOLVE.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_SOCKET_NOT_CREATED.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_SOCKET_DISCONNECTED.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_PONG_RECV_FAIL.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_CONN_ACK_TIMEOUT.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        sStateMap.put(Integer.valueOf(RongIMClient$ErrorCode.RC_CONN_OVERFREQUENCY.getValue()), RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
        reconnectList.add(Integer.valueOf(RongIMClient$ErrorCode.RC_NET_CHANNEL_INVALID.getValue()));
        reconnectList.add(Integer.valueOf(RongIMClient$ErrorCode.RC_NET_UNAVAILABLE.getValue()));
        reconnectList.add(Integer.valueOf(RongIMClient$ErrorCode.RC_MSG_RESP_TIMEOUT.getValue()));
        reconnectList.add(Integer.valueOf(RongIMClient$ErrorCode.RC_SOCKET_NOT_CREATED.getValue()));
        reconnectList.add(Integer.valueOf(RongIMClient$ErrorCode.RC_SOCKET_DISCONNECTED.getValue()));
        reconnectList.add(Integer.valueOf(RongIMClient$ErrorCode.RC_CONN_SERVER_UNAVAILABLE.getValue()));
        reconnectList.add(Integer.valueOf(RongIMClient$ErrorCode.RC_MSG_SEND_FAIL.getValue()));
    }

    private RongIMClient() {
        this.mConnectionStatus = RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED;
        this.mReconnectCount = 0;
        this.mReconnectInterval = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512};
        this.customServiceCache = new HashMap();
        RLog.i(TAG, TAG);
        mHandler = new Handler(Looper.getMainLooper());
        this.mRegCache = new ArrayList();
        this.mChatroomCache = new HashSet();
        HandlerThread handlerThread = new HandlerThread("IPC_WORK");
        handlerThread.start();
        this.mStatusListener = new RongIMClient$StatusListener(this);
        this.mWorkHandler = new Handler(handlerThread.getLooper());
        this.mConnectChangeReceiver = new ConnectChangeReceiver();
        this.mAidlConnection = new RongIMClient$AidlConnection(this);
    }

    public static RongIMClient getInstance() {
        return RongIMClient$SingletonHolder.sInstance;
    }

    private void initBindService() {
        Intent intent = new Intent(this.mContext, RongService.class);
        intent.putExtra(RongLibConst.KEY_APPKEY, this.mAppKey);
        intent.putExtra("deviceId", this.mDeviceId);
        try {
            this.mContext.bindService(intent, this.mAidlConnection, 1);
        } catch (SecurityException e) {
            RLog.e(TAG, "initBindService SecurityException");
            e.printStackTrace();
        }
    }

    public RongIMClient$ConnectionStatusListener$ConnectionStatus getCurrentConnectionStatus() {
        return this.mConnectionStatus;
    }

    private void initStatistics(Context context, String str) {
        if (!Statistics.sharedInstance().isInitialized()) {
            List arrayList = new ArrayList();
            arrayList.add("rongcloud");
            Statistics.enablePublicKeyPinning(arrayList);
            Statistics.sharedInstance().init(context, URL_STATISTIC, str, this.mDeviceId);
            Statistics.sharedInstance().setLoggingEnabled(false);
            Statistics.sharedInstance().onStart();
        }
    }

    public static void init(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context异常");
        }
        String currentProcessName = SystemUtils.getCurrentProcessName(context);
        String packageName = context.getPackageName();
        RLog.d(TAG, "init : " + currentProcessName + ", " + packageName);
        if (currentProcessName == null || packageName == null || !packageName.equals(currentProcessName)) {
            RLog.e(TAG, "SDK should init in main process.");
            return;
        }
        RongIMClient$SingletonHolder.sInstance.mContext = context.getApplicationContext();
        if (TextUtils.isEmpty(RongIMClient$SingletonHolder.sInstance.mAppKey)) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null) {
                    RongIMClient$SingletonHolder.sInstance.mAppKey = applicationInfo.metaData.getString("RONG_CLOUD_APP_KEY");
                }
                if (TextUtils.isEmpty(RongIMClient$SingletonHolder.sInstance.mAppKey)) {
                    throw new IllegalArgumentException("can't find RONG_CLOUD_APP_KEY in AndroidManifest.xml.");
                }
            } catch (NameNotFoundException e) {
                e.printStackTrace();
                throw new ExceptionInInitializerError("can't find packageName!");
            }
        }
        RongIMClient$SingletonHolder.sInstance.mDeviceId = DeviceUtils.getDeviceId(context, RongIMClient$SingletonHolder.sInstance.mAppKey);
        context.getSharedPreferences("Statistics", 0).edit().putString(RongLibConst.KEY_APPKEY, RongIMClient$SingletonHolder.sInstance.mAppKey).apply();
        try {
            registerMessageType(TextMessage.class);
            registerMessageType(VoiceMessage.class);
            registerMessageType(ImageMessage.class);
            registerMessageType(LocationMessage.class);
            registerMessageType(CommandNotificationMessage.class);
            registerMessageType(ContactNotificationMessage.class);
            registerMessageType(RichContentMessage.class);
            registerMessageType(PublicServiceMultiRichContentMessage.class);
            registerMessageType(PublicServiceRichContentMessage.class);
            registerMessageType(PublicServiceCommandMessage.class);
            registerMessageType(ProfileNotificationMessage.class);
            registerMessageType(HandshakeMessage.class);
            registerMessageType(InformationNotificationMessage.class);
            registerMessageType(DiscussionNotificationMessage.class);
            registerMessageType(SuspendMessage.class);
            registerMessageType(ReadReceiptMessage.class);
            registerMessageType(CommandMessage.class);
            registerMessageType(TypingStatusMessage.class);
            registerMessageType(CSHandShakeMessage.class);
            registerMessageType(CSHandShakeResponseMessage.class);
            registerMessageType(CSChangeModeMessage.class);
            registerMessageType(CSChangeModeResponseMessage.class);
            registerMessageType(CSSuspendMessage.class);
            registerMessageType(CSTerminateMessage.class);
            registerMessageType(CSEvaluateMessage.class);
            registerMessageType(CSUpdateMessage.class);
            registerMessageType(RecallCommandMessage.class);
            registerMessageType(RecallNotificationMessage.class);
        } catch (AnnotationNotFoundException e2) {
            e2.printStackTrace();
        }
        RongIMClient$SingletonHolder.sInstance.initBindService();
        RongIMClient$SingletonHolder.sInstance.initStatistics(context, RongIMClient$SingletonHolder.sInstance.mAppKey);
        if (mNaviServer != null) {
            RongPushClient.init(context, RongIMClient$SingletonHolder.sInstance.mAppKey, mNaviServer);
        } else {
            RongPushClient.init(context, RongIMClient$SingletonHolder.sInstance.mAppKey);
        }
        TypingMessageManager.getInstance().init(context);
    }

    public static void init(Context context, String str) {
        RongIMClient$SingletonHolder.sInstance.mAppKey = str;
        init(context);
    }

    public static RongIMClient connect(String str, ConnectCallback connectCallback) {
        if (TextUtils.isEmpty(str)) {
            if (connectCallback != null) {
                connectCallback.onTokenIncorrect();
            }
            RLog.e(TAG, "connect token is incorrect!");
            return RongIMClient$SingletonHolder.sInstance;
        } else if (RongIMClient$SingletonHolder.sInstance.mConnectionStatus == RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTING) {
            RLog.e(TAG, "connect Client is connecting!");
            if (connectCallback != null) {
                connectCallback.onError(RongIMClient$ErrorCode.RC_CONN_OVERFREQUENCY);
            }
            return RongIMClient$SingletonHolder.sInstance;
        } else {
            if (RongIMClient$SingletonHolder.sInstance.mReconnectRunnable != null) {
                mHandler.removeCallbacks(RongIMClient$SingletonHolder.sInstance.mReconnectRunnable);
                RongIMClient$SingletonHolder.sInstance.mReconnectRunnable = null;
            }
            RongIMClient$SingletonHolder.sInstance.mToken = str;
            if (RongIMClient$SingletonHolder.sInstance.mLibHandler == null) {
                RLog.d(TAG, "connect mLibHandler is null, connect waiting for bind service");
                RongIMClient$SingletonHolder.sInstance.mConnectRunnable = new RongIMClient$ConnectRunnable(str, connectCallback);
            } else {
                RongIMClient$SingletonHolder.sInstance.mConnectRunnable = null;
                RongIMClient$SingletonHolder.sInstance.mStatusListener.onStatusChange(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTING);
                try {
                    RLog.d(TAG, "connect service binded, connect");
                    RongIMClient$SingletonHolder.sInstance.mLibHandler.connect(str, new RongIMClient$1(connectCallback));
                } catch (RemoteException e) {
                    RLog.d(TAG, "connect RemoteException");
                    e.printStackTrace();
                }
            }
            return RongIMClient$SingletonHolder.sInstance;
        }
    }

    public void reconnect(ConnectCallback connectCallback) {
        RLog.d(TAG, "reconnect mConnectionStatus :" + this.mConnectionStatus);
        if (this.mToken != null) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
            if (RongIMClient$SingletonHolder.sInstance.mReconnectRunnable != null) {
                mHandler.removeCallbacks(RongIMClient$SingletonHolder.sInstance.mReconnectRunnable);
                RongIMClient$SingletonHolder.sInstance.mReconnectRunnable = null;
            }
            if (this.mConnectionStatus == RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED) {
                if (activeNetworkInfo == null) {
                    this.mStatusListener.onStatusChange(RongIMClient$ConnectionStatusListener$ConnectionStatus.NETWORK_UNAVAILABLE);
                }
                if (connectCallback != null) {
                    connectCallback.onCallback(RongIMClient$SingletonHolder.sInstance.mCurrentUserId);
                }
            } else if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                connect(this.mToken, connectCallback);
            }
        } else if (connectCallback != null) {
            connectCallback.onFail(RongIMClient$ErrorCode.RC_CONN_USER_OR_PASSWD_ERROR);
        }
    }

    public void disconnect() {
        disconnect(true);
    }

    @Deprecated
    public void disconnect(boolean z) {
        RLog.d(TAG, "disconnect isReceivePush = " + z + ", mConnectionStatus = " + this.mConnectionStatus);
        if (this.mLibHandler == null) {
            RLog.e(TAG, "disconnect IPC service unbind!");
            return;
        }
        this.mChatroomCache.clear();
        if (this.mReconnectRunnable != null) {
            mHandler.removeCallbacks(this.mReconnectRunnable);
            this.mReconnectRunnable = null;
        }
        this.mReconnectCount = 0;
        if (this.mConnectionStatus == RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED || this.mConnectionStatus == RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTING) {
            this.mDisconnectRunnable = new RongIMClient$DisconnectRunnable(this, z);
            if (this.mConnectionStatus == RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED) {
                this.mWorkHandler.post(this.mDisconnectRunnable);
            }
        } else if (this.mConnectionStatus != RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED) {
            this.mStatusListener.onStatusChange(RongIMClient$ConnectionStatusListener$ConnectionStatus.DISCONNECTED);
            this.mToken = null;
        }
    }

    public void logout() {
        disconnect(false);
    }

    public static void setConnectionStatusListener(ConnectionStatusListener connectionStatusListener) {
        sConnectionListener = connectionStatusListener;
    }

    public static void setOnReceiveMessageListener(OnReceiveMessageListener onReceiveMessageListener) {
        sReceiveMessageListener = onReceiveMessageListener;
    }

    public static void registerMessageType(Class<? extends MessageContent> cls) throws AnnotationNotFoundException {
        if (cls == null) {
            throw new IllegalArgumentException("MessageContent 为空！");
        }
        synchronized (RongIMClient$SingletonHolder.sInstance.mRegCache) {
            if (!RongIMClient$SingletonHolder.sInstance.mRegCache.contains(cls.getName())) {
                RongIMClient$SingletonHolder.sInstance.mRegCache.add(cls.getName());
            }
        }
        RLog.d(TAG, "registerMessageType " + cls.toString());
        if (RongIMClient$SingletonHolder.sInstance.mLibHandler != null) {
            try {
                RongIMClient$SingletonHolder.sInstance.mLibHandler.registerMessageType(cls.getName());
            } catch (RemoteException e) {
                RLog.e(TAG, "registerMessageType RemoteException");
                e.printStackTrace();
            }
        }
    }

    public void getConversationList(ResultCallback<List<Conversation>> resultCallback) {
        this.mWorkHandler.post(new RongIMClient$2(this, resultCallback));
    }

    @Deprecated
    public List<Conversation> getConversationList() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        getConversationList(new RongIMClient$3(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (List) rongIMClient$ResultCallback$Result.f17368t;
    }

    public void getConversationList(ResultCallback<List<Conversation>> resultCallback, ConversationType... conversationTypeArr) {
        this.mWorkHandler.post(new RongIMClient$4(this, resultCallback, conversationTypeArr));
    }

    @Deprecated
    public List<Conversation> getConversationList(ConversationType... conversationTypeArr) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        getConversationList(new RongIMClient$5(this, rongIMClient$ResultCallback$Result, countDownLatch), conversationTypeArr);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (List) rongIMClient$ResultCallback$Result.f17368t;
    }

    public void getConversation(ConversationType conversationType, String str, ResultCallback<Conversation> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "getConversation. the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$6(this, resultCallback, conversationType, str));
    }

    public void updateConversationInfo(ConversationType conversationType, String str, String str2, String str3, ResultCallback resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "getConversation. the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$7(this, resultCallback, conversationType, str, str2, str3));
    }

    @Deprecated
    public Conversation getConversation(ConversationType conversationType, String str) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        getConversation(conversationType, str, new RongIMClient$8(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (Conversation) rongIMClient$ResultCallback$Result.f17368t;
    }

    public void removeConversation(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "getConversation. the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$9(this, resultCallback, conversationType, str));
    }

    @Deprecated
    public boolean removeConversation(ConversationType conversationType, String str) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Boolean.valueOf(false);
        removeConversation(conversationType, str, new RongIMClient$10(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Boolean) rongIMClient$ResultCallback$Result.f17368t).booleanValue();
    }

    public void setConversationToTop(ConversationType conversationType, String str, boolean z, ResultCallback<Boolean> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "getConversation. the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$11(this, resultCallback, conversationType, str, z));
    }

    @Deprecated
    public boolean setConversationToTop(ConversationType conversationType, String str, boolean z) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Boolean.valueOf(false);
        setConversationToTop(conversationType, str, z, new RongIMClient$12(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Boolean) rongIMClient$ResultCallback$Result.f17368t).booleanValue();
    }

    public void getTotalUnreadCount(ResultCallback<Integer> resultCallback) {
        this.mWorkHandler.post(new RongIMClient$13(this, resultCallback));
    }

    @Deprecated
    public int getTotalUnreadCount() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Integer.valueOf(0);
        getTotalUnreadCount(new RongIMClient$14(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Integer) rongIMClient$ResultCallback$Result.f17368t).intValue();
    }

    public void getUnreadCount(ConversationType conversationType, String str, ResultCallback<Integer> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "getConversation. the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$15(this, resultCallback, conversationType, str));
    }

    @Deprecated
    public int getUnreadCount(ConversationType conversationType, String str) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Integer.valueOf(0);
        getUnreadCount(conversationType, str, new RongIMClient$16(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Integer) rongIMClient$ResultCallback$Result.f17368t).intValue();
    }

    public void getUnreadCount(ResultCallback<Integer> resultCallback, ConversationType... conversationTypeArr) {
        if (conversationTypeArr == null || conversationTypeArr.length == 0) {
            Log.i(TAG, "conversationTypes is null. Return directly!!!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$17(this, resultCallback, conversationTypeArr));
    }

    public void getUnreadCount(ConversationType[] conversationTypeArr, ResultCallback<Integer> resultCallback) {
        getUnreadCount((ResultCallback) resultCallback, conversationTypeArr);
    }

    @Deprecated
    public int getUnreadCount(ConversationType... conversationTypeArr) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Integer.valueOf(0);
        getUnreadCount(new RongIMClient$18(this, rongIMClient$ResultCallback$Result, countDownLatch), conversationTypeArr);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Integer) rongIMClient$ResultCallback$Result.f17368t).intValue();
    }

    @Deprecated
    public List<Message> getLatestMessages(ConversationType conversationType, String str, int i) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        getLatestMessages(conversationType, str, i, new RongIMClient$19(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (List) rongIMClient$ResultCallback$Result.f17368t;
    }

    public void getLatestMessages(ConversationType conversationType, String str, int i, ResultCallback<List<Message>> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$20(this, resultCallback, conversationType, str, i));
    }

    @Deprecated
    public List<Message> getHistoryMessages(ConversationType conversationType, String str, int i, int i2) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        getHistoryMessages(conversationType, str, i, i2, new RongIMClient$21(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (List) rongIMClient$ResultCallback$Result.f17368t;
    }

    @Deprecated
    public List<Message> getHistoryMessages(ConversationType conversationType, String str, String str2, int i, int i2) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        getHistoryMessages(conversationType, str, str2, i, i2, new RongIMClient$22(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (List) rongIMClient$ResultCallback$Result.f17368t;
    }

    public void getHistoryMessages(ConversationType conversationType, String str, String str2, int i, int i2, ResultCallback<List<Message>> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$23(this, resultCallback, conversationType, str, str2, i, i2));
    }

    public void getHistoryMessages(ConversationType conversationType, String str, String str2, int i, int i2, GetMessageDirection getMessageDirection, ResultCallback<List<Message>> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
        } else if (TextUtils.isEmpty(str2) || i2 <= 0 || getMessageDirection == null) {
            RLog.e(TAG, "the parameter of objectName, count or direction is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
        } else {
            this.mWorkHandler.post(new RongIMClient$24(this, resultCallback, conversationType, str, str2, i, i2, getMessageDirection));
        }
    }

    public void getRemoteHistoryMessages(ConversationType conversationType, String str, long j, int i, ResultCallback<List<Message>> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$25(this, resultCallback, conversationType, str, j, i));
    }

    public void getHistoryMessages(ConversationType conversationType, String str, int i, int i2, ResultCallback<List<Message>> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$26(this, resultCallback, conversationType, str, i, i2));
    }

    @Deprecated
    public boolean deleteMessages(int[] iArr) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Boolean.valueOf(false);
        deleteMessages(iArr, new RongIMClient$27(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Boolean) rongIMClient$ResultCallback$Result.f17368t).booleanValue();
    }

    public void deleteMessages(int[] iArr, ResultCallback<Boolean> resultCallback) {
        if (iArr == null || iArr.length == 0) {
            RLog.e(TAG, "the messageIds is null!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$28(this, resultCallback, iArr));
    }

    public void deleteMessages(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$29(this, resultCallback, conversationType, str));
    }

    @Deprecated
    public boolean clearMessages(ConversationType conversationType, String str) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Boolean.valueOf(false);
        clearMessages(conversationType, str, new RongIMClient$30(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Boolean) rongIMClient$ResultCallback$Result.f17368t).booleanValue();
    }

    public void clearMessages(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$31(this, resultCallback, conversationType, str));
    }

    @Deprecated
    public boolean clearMessagesUnreadStatus(ConversationType conversationType, String str) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Boolean.valueOf(false);
        clearMessagesUnreadStatus(conversationType, str, new RongIMClient$32(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Boolean) rongIMClient$ResultCallback$Result.f17368t).booleanValue();
    }

    public void clearMessagesUnreadStatus(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$33(this, resultCallback, conversationType, str));
    }

    @Deprecated
    public boolean setMessageExtra(int i, String str) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Boolean.valueOf(false);
        setMessageExtra(i, str, new RongIMClient$34(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Boolean) rongIMClient$ResultCallback$Result.f17368t).booleanValue();
    }

    public void setMessageExtra(int i, String str, ResultCallback<Boolean> resultCallback) {
        if (i == 0) {
            RLog.e(TAG, "messageId is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$35(this, resultCallback, i, str));
    }

    @Deprecated
    public boolean setMessageReceivedStatus(int i, Message$ReceivedStatus message$ReceivedStatus) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Boolean.valueOf(false);
        setMessageReceivedStatus(i, message$ReceivedStatus, new RongIMClient$36(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Boolean) rongIMClient$ResultCallback$Result.f17368t).booleanValue();
    }

    public void setMessageReceivedStatus(int i, Message$ReceivedStatus message$ReceivedStatus, ResultCallback<Boolean> resultCallback) {
        if (i == 0) {
            RLog.e(TAG, "Error.The messageId can't be 0!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$37(this, resultCallback, i, message$ReceivedStatus));
    }

    @Deprecated
    public boolean setMessageSentStatus(int i, Message$SentStatus message$SentStatus) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Boolean.valueOf(false);
        setMessageSentStatus(i, message$SentStatus, new RongIMClient$38(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Boolean) rongIMClient$ResultCallback$Result.f17368t).booleanValue();
    }

    public void setMessageSentStatus(int i, Message$SentStatus message$SentStatus, ResultCallback<Boolean> resultCallback) {
        if (i == 0) {
            RLog.e(TAG, "Error.The messageId can't be 0!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$39(this, resultCallback, i, message$SentStatus));
    }

    @Deprecated
    public String getTextMessageDraft(ConversationType conversationType, String str) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        getTextMessageDraft(conversationType, str, new RongIMClient$40(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (String) rongIMClient$ResultCallback$Result.f17368t;
    }

    public void getTextMessageDraft(ConversationType conversationType, String str, ResultCallback<String> resultCallback) {
        Conversation conversation = new Conversation();
        conversation.setConversationType(conversationType);
        conversation.setTargetId(str);
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "the value of targetId or ConversationType is error!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$41(this, resultCallback, conversation));
    }

    @Deprecated
    public boolean saveTextMessageDraft(ConversationType conversationType, String str, String str2) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Boolean.valueOf(false);
        saveTextMessageDraft(conversationType, str, str2, new RongIMClient$42(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Boolean) rongIMClient$ResultCallback$Result.f17368t).booleanValue();
    }

    public void saveTextMessageDraft(ConversationType conversationType, String str, String str2, ResultCallback<Boolean> resultCallback) {
        Conversation conversation = new Conversation();
        conversation.setConversationType(conversationType);
        conversation.setTargetId(str);
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "the value of targetId or ConversationType is error!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$43(this, resultCallback, conversation, str2));
    }

    @Deprecated
    public boolean clearTextMessageDraft(ConversationType conversationType, String str) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Boolean.valueOf(false);
        clearTextMessageDraft(conversationType, str, new RongIMClient$44(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Boolean) rongIMClient$ResultCallback$Result.f17368t).booleanValue();
    }

    public void clearTextMessageDraft(ConversationType conversationType, String str, ResultCallback<Boolean> resultCallback) {
        Conversation conversation = new Conversation();
        conversation.setConversationType(conversationType);
        conversation.setTargetId(str);
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "the value of targetId or ConversationType is error!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$45(this, resultCallback, conversation));
    }

    public void getDiscussion(String str, ResultCallback<Discussion> resultCallback) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "the discussionId can't be empty!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$46(this, resultCallback, str));
    }

    public void setDiscussionName(String str, String str2, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            RLog.e(TAG, "discussionId or name is null");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$47(this, rongIMClient$OperationCallback, str2, str));
    }

    public void createDiscussion(String str, List<String> list, RongIMClient$CreateDiscussionCallback rongIMClient$CreateDiscussionCallback) {
        if (TextUtils.isEmpty(str) || list == null || list.size() == 0) {
            RLog.e(TAG, "name or userIdList is null");
            if (rongIMClient$CreateDiscussionCallback != null) {
                rongIMClient$CreateDiscussionCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$48(this, rongIMClient$CreateDiscussionCallback, str, list));
    }

    public void addMemberToDiscussion(String str, List<String> list, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str) || list == null || list.size() == 0) {
            RLog.e(TAG, "discussionId or userIdList is null");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$49(this, rongIMClient$OperationCallback, str, list));
    }

    public void removeMemberFromDiscussion(String str, String str2, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            RLog.e(TAG, "discussionId or userId is null");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$50(this, rongIMClient$OperationCallback, str, str2));
    }

    public void quitDiscussion(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "discussionId is null");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$51(this, rongIMClient$OperationCallback, str));
    }

    public void getMessage(int i, ResultCallback<Message> resultCallback) {
        if (i <= 0) {
            RLog.e(TAG, "Illegal argument of messageId.");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$52(this, resultCallback, i));
    }

    public void insertMessage(ConversationType conversationType, String str, String str2, MessageContent messageContent, ResultCallback<Message> resultCallback) {
        if (conversationType == null || TextUtils.isEmpty(str)) {
            RLog.e(TAG, "insertMessage::ConversationType or targetId is null");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$53(this, resultCallback, str, conversationType, messageContent, str2));
    }

    @Deprecated
    public Message insertMessage(ConversationType conversationType, String str, String str2, MessageContent messageContent) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        insertMessage(conversationType, str, str2, messageContent, new RongIMClient$54(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (Message) rongIMClient$ResultCallback$Result.f17368t;
    }

    public void sendLocationMessage(Message message, String str, String str2, ISendMessageCallback iSendMessageCallback) {
        if (message == null || message.getConversationType() == null || TextUtils.isEmpty(message.getTargetId()) || message.getContent() == null || !(message.getContent() instanceof LocationMessage)) {
            RLog.e(TAG, "sendLocationMessage : conversation type or targetId or content can't be null!");
            if (iSendMessageCallback != null) {
                iSendMessageCallback.onError(message, RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        if (TypingMessageManager.getInstance().isShowMessageTyping()) {
            TypingMessageManager.getInstance().setTypingEnd(message.getConversationType(), message.getTargetId());
        }
        this.mWorkHandler.post(new RongIMClient$55(this, iSendMessageCallback, message, str, str2));
    }

    @Deprecated
    public Message sendMessage(ConversationType conversationType, String str, MessageContent messageContent, String str2, String str3, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        sendMessage(conversationType, str, messageContent, str2, str3, rongIMClient$SendMessageCallback, new RongIMClient$56(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (Message) rongIMClient$ResultCallback$Result.f17368t;
    }

    public void sendMessage(ConversationType conversationType, String str, MessageContent messageContent, String str2, String str3, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback, ResultCallback<Message> resultCallback) {
        sendMessage(Message.obtain(str, conversationType, messageContent), str2, str3, rongIMClient$SendMessageCallback, resultCallback);
    }

    public void sendMessage(Message message, String str, String str2, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback, ResultCallback<Message> resultCallback) {
        if (message == null || message.getConversationType() == null || TextUtils.isEmpty(message.getTargetId()) || message.getContent() == null) {
            RLog.e(TAG, "sendMessage : conversation type or targetId or content can't be null!");
            if (rongIMClient$SendMessageCallback != null) {
                rongIMClient$SendMessageCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            }
        } else if (((MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class)) == null) {
            RLog.e(TAG, "sendMessage 自定义消息没有加注解信息。");
            if (rongIMClient$SendMessageCallback != null) {
                rongIMClient$SendMessageCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            }
        } else {
            if (TypingMessageManager.getInstance().isShowMessageTyping()) {
                MessageContent content = message.getContent();
                if (!((content instanceof TypingStatusMessage) || (content instanceof ReadReceiptMessage))) {
                    TypingMessageManager.getInstance().setTypingEnd(message.getConversationType(), message.getTargetId());
                }
            }
            this.mWorkHandler.post(new RongIMClient$57(this, rongIMClient$SendMessageCallback, message, str, str2, resultCallback));
        }
    }

    public void sendMessage(ConversationType conversationType, String str, MessageContent messageContent, String str2, String str3, ISendMessageCallback iSendMessageCallback) {
        sendMessage(Message.obtain(str, conversationType, messageContent), str2, str3, iSendMessageCallback);
    }

    private void runOnUiThread(Runnable runnable) {
        mHandler.post(runnable);
    }

    public void sendMessage(Message message, String str, String str2, ISendMessageCallback iSendMessageCallback) {
        if (message == null || message.getConversationType() == null || TextUtils.isEmpty(message.getTargetId()) || message.getContent() == null) {
            RLog.e(TAG, "sendMessage : conversation type or targetId or content can't be null!");
            if (iSendMessageCallback != null) {
                iSendMessageCallback.onError(message, RongIMClient$ErrorCode.PARAMETER_ERROR);
            }
        } else if (((MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class)) == null) {
            RLog.e(TAG, "sendMessage 自定义消息没有加注解信息。");
            if (iSendMessageCallback != null) {
                iSendMessageCallback.onError(message, RongIMClient$ErrorCode.PARAMETER_ERROR);
            }
        } else {
            if (TypingMessageManager.getInstance().isShowMessageTyping()) {
                MessageContent content = message.getContent();
                if (!((content instanceof TypingStatusMessage) || (content instanceof ReadReceiptMessage))) {
                    TypingMessageManager.getInstance().setTypingEnd(message.getConversationType(), message.getTargetId());
                }
            }
            this.mWorkHandler.post(new RongIMClient$58(this, iSendMessageCallback, message, str, str2));
        }
    }

    @Deprecated
    public void sendStatusMessage(Message message, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback, ResultCallback<Message> resultCallback) {
        MessageTag messageTag = (MessageTag) message.getContent().getClass().getAnnotation(MessageTag.class);
        if (messageTag == null) {
            throw new RuntimeException("自定义消息没有加注解信息。");
        } else if (messageTag.flag() != 16) {
            RLog.e(TAG, "sendStatusMessage MessageTag should be STATUS.");
        } else {
            this.mWorkHandler.post(new RongIMClient$59(this, rongIMClient$SendMessageCallback, message, resultCallback));
        }
    }

    @Deprecated
    public Message sendMessage(Message message, String str, String str2, RongIMClient$SendMessageCallback rongIMClient$SendMessageCallback) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        sendMessage(message, str, str2, rongIMClient$SendMessageCallback, new RongIMClient$60(this, rongIMClient$ResultCallback$Result, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return (Message) rongIMClient$ResultCallback$Result.f17368t;
    }

    public void sendImageMessage(ConversationType conversationType, String str, MessageContent messageContent, String str2, String str3, RongIMClient$SendImageMessageCallback rongIMClient$SendImageMessageCallback) {
        if (conversationType == null || TextUtils.isEmpty(str) || messageContent == null) {
            Log.i(TAG, "Illegal parameter!");
            if (rongIMClient$SendImageMessageCallback != null) {
                rongIMClient$SendImageMessageCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        sendImageMessage(Message.obtain(str, conversationType, messageContent), str2, str3, rongIMClient$SendImageMessageCallback);
    }

    public void sendImageMessage(Message message, String str, String str2, RongIMClient$SendImageMessageCallback rongIMClient$SendImageMessageCallback) {
        if (message == null) {
            RLog.e(TAG, "sendImageMessage message is null!");
            if (rongIMClient$SendImageMessageCallback != null) {
                rongIMClient$SendImageMessageCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = message;
        RongIMClient$UploadMediaCallback rongIMClient$61 = new RongIMClient$61(this, rongIMClient$SendImageMessageCallback, str, str2);
        ResultCallback rongIMClient$62 = new RongIMClient$62(this, rongIMClient$ResultCallback$Result, rongIMClient$SendImageMessageCallback, rongIMClient$61);
        if (message.getMessageId() <= 0) {
            insertMessage(message.getConversationType(), message.getTargetId(), null, message.getContent(), rongIMClient$62);
            return;
        }
        message.setSentStatus(Message$SentStatus.SENDING);
        setMessageSentStatus(message.getMessageId(), Message$SentStatus.SENDING, null);
        uploadMedia(message, RongIMClient$MediaType.IMAGE, rongIMClient$61);
    }

    public void sendImageMessage(Message message, String str, String str2, RongIMClient$SendImageMessageWithUploadListenerCallback rongIMClient$SendImageMessageWithUploadListenerCallback) {
        if (message == null) {
            RLog.e(TAG, "message is null!");
            if (rongIMClient$SendImageMessageWithUploadListenerCallback != null) {
                rongIMClient$SendImageMessageWithUploadListenerCallback.onError(null, RongIMClient$ErrorCode.PARAMETER_ERROR);
            }
        } else if (this.mLibHandler == null) {
            RLog.e(TAG, "sendImageMessage IPC 进程尚未运行！");
            if (rongIMClient$SendImageMessageWithUploadListenerCallback != null) {
                rongIMClient$SendImageMessageWithUploadListenerCallback.onError(message, RongIMClient$ErrorCode.IPC_DISCONNECT);
            }
        } else {
            String str3 = null;
            insertMessage(message.getConversationType(), message.getTargetId(), str3, message.getContent(), new RongIMClient$63(this, rongIMClient$SendImageMessageWithUploadListenerCallback, str, str2, message));
        }
    }

    private void uploadMedia(Message message, RongIMClient$MediaType rongIMClient$MediaType, RongIMClient$UploadMediaCallback rongIMClient$UploadMediaCallback) {
        Uri uri = null;
        if (message.getContent() instanceof ImageMessage) {
            uri = ((ImageMessage) message.getContent()).getLocalUri();
        }
        if (uri == null || uri.getScheme() == null || !uri.getScheme().equals(Action.FILE_ATTRIBUTE)) {
            RLog.e(TAG, "uploadMedia Uri :[" + uri + ", 必须为file://格式");
            if (rongIMClient$UploadMediaCallback != null) {
                rongIMClient$UploadMediaCallback.onError(message, RongIMClient$ErrorCode.PARAMETER_ERROR);
            }
        } else if (new File(uri.getPath()).exists()) {
            Conversation conversation = new Conversation();
            conversation.setConversationType(message.getConversationType());
            conversation.setTargetId(message.getTargetId());
            this.mWorkHandler.post(new RongIMClient$64(this, rongIMClient$UploadMediaCallback, conversation, uri, rongIMClient$MediaType, message));
        } else {
            RLog.e(TAG, "uploadMedia Uri 文件不存在。" + uri);
            if (rongIMClient$UploadMediaCallback != null) {
                rongIMClient$UploadMediaCallback.onError(message, RongIMClient$ErrorCode.PARAMETER_ERROR);
            }
        }
    }

    public void downloadMedia(ConversationType conversationType, String str, RongIMClient$MediaType rongIMClient$MediaType, String str2, RongIMClient$DownloadMediaCallback rongIMClient$DownloadMediaCallback) {
        if (conversationType == null || TextUtils.isEmpty(str) || rongIMClient$MediaType == null || TextUtils.isEmpty(str2)) {
            RLog.e(TAG, "downloadMedia 参数异常。");
            if (rongIMClient$DownloadMediaCallback != null) {
                rongIMClient$DownloadMediaCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        Conversation conversation = new Conversation();
        conversation.setTargetId(str);
        conversation.setConversationType(conversationType);
        this.mWorkHandler.post(new RongIMClient$65(this, rongIMClient$DownloadMediaCallback, conversation, rongIMClient$MediaType, str2));
    }

    public void getConversationNotificationStatus(ConversationType conversationType, String str, ResultCallback<ConversationNotificationStatus> resultCallback) {
        if (conversationType == null || TextUtils.isEmpty(str)) {
            RLog.e(TAG, "Parameter is error!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$66(this, resultCallback, conversationType, str));
    }

    public void setConversationNotificationStatus(ConversationType conversationType, String str, ConversationNotificationStatus conversationNotificationStatus, ResultCallback<ConversationNotificationStatus> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null || conversationNotificationStatus == null) {
            RLog.e(TAG, "Parameter is error!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$67(this, resultCallback, conversationType, str, conversationNotificationStatus));
    }

    public void setDiscussionInviteStatus(String str, RongIMClient$DiscussionInviteStatus rongIMClient$DiscussionInviteStatus, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str) || rongIMClient$DiscussionInviteStatus == null) {
            RLog.e(TAG, "Parameter is error!");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$68(this, rongIMClient$OperationCallback, str, rongIMClient$DiscussionInviteStatus));
    }

    @Deprecated
    public void syncGroup(List<Group> list, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (list == null || list.size() == 0) {
            RLog.e(TAG, "groups is null!");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$69(this, rongIMClient$OperationCallback, list));
    }

    @Deprecated
    public void joinGroup(String str, String str2, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            RLog.e(TAG, "groupId or groupName is null");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$70(this, rongIMClient$OperationCallback, str, str2));
    }

    @Deprecated
    public void quitGroup(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "groupId  is null");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$71(this, rongIMClient$OperationCallback, str));
    }

    public String getCurrentUserId() {
        try {
            if (TextUtils.isEmpty(RongIMClient$SingletonHolder.sInstance.mCurrentUserId) && this.mLibHandler != null) {
                RongIMClient$SingletonHolder.sInstance.mCurrentUserId = this.mLibHandler.getCurrentUserId();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return RongIMClient$SingletonHolder.sInstance.mCurrentUserId;
    }

    public long getDeltaTime() {
        if (this.mLibHandler == null) {
            RLog.e(TAG, "getDeltaTime IPC 进程错误。");
            return 0;
        }
        long[] jArr = new long[]{0};
        CountDownLatch countDownLatch = new CountDownLatch(1);
        this.mWorkHandler.post(new RongIMClient$72(this, jArr, countDownLatch));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jArr[0];
    }

    public void getChatRoomInfo(String str, int i, ChatRoomMemberOrder chatRoomMemberOrder, ResultCallback<ChatRoomInfo> resultCallback) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "id is null");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$73(this, resultCallback, str, i, chatRoomMemberOrder));
    }

    public void joinChatRoom(String str, int i, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "id is null");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mChatroomCache.add(str);
        this.mWorkHandler.post(new RongIMClient$74(this, rongIMClient$OperationCallback, str, i));
    }

    private void reJoinChatRoom(String str, int i, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "id is null");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$75(this, rongIMClient$OperationCallback, str, i));
    }

    public void joinExistChatRoom(String str, int i, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "id is null");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mChatroomCache.add(str);
        this.mWorkHandler.post(new RongIMClient$76(this, rongIMClient$OperationCallback, str, i));
    }

    public void quitChatRoom(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "id is null!");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mChatroomCache.remove(str);
        this.mWorkHandler.post(new RongIMClient$77(this, rongIMClient$OperationCallback, str));
    }

    public void clearConversations(ResultCallback resultCallback, ConversationType... conversationTypeArr) {
        if (conversationTypeArr == null || conversationTypeArr.length == 0) {
            RLog.e(TAG, "conversationTypes is null!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$78(this, resultCallback, conversationTypeArr));
    }

    @Deprecated
    public boolean clearConversations(ConversationType... conversationTypeArr) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        RongIMClient$ResultCallback$Result rongIMClient$ResultCallback$Result = new RongIMClient$ResultCallback$Result();
        rongIMClient$ResultCallback$Result.f17368t = Boolean.valueOf(false);
        clearConversations(new RongIMClient$79(this, rongIMClient$ResultCallback$Result, countDownLatch), conversationTypeArr);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ((Boolean) rongIMClient$ResultCallback$Result.f17368t).booleanValue();
    }

    public void addToBlacklist(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "userId  is null!");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$80(this, rongIMClient$OperationCallback, str));
    }

    public void removeFromBlacklist(String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "userId  is null!");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$81(this, rongIMClient$OperationCallback, str));
    }

    public void getBlacklistStatus(String str, ResultCallback<RongIMClient$BlacklistStatus> resultCallback) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "userId  is null!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$82(this, resultCallback, str));
    }

    public void getBlacklist(RongIMClient$GetBlacklistCallback rongIMClient$GetBlacklistCallback) {
        this.mWorkHandler.post(new RongIMClient$83(this, rongIMClient$GetBlacklistCallback));
    }

    public void searchPublicService(RongIMClient$SearchType rongIMClient$SearchType, String str, ResultCallback<PublicServiceProfileList> resultCallback) {
        if (rongIMClient$SearchType == null) {
            RLog.e(TAG, "searchType  is null!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$84(this, resultCallback, str, rongIMClient$SearchType));
    }

    public void searchPublicServiceByType(PublicServiceType publicServiceType, RongIMClient$SearchType rongIMClient$SearchType, String str, ResultCallback<PublicServiceProfileList> resultCallback) {
        if (publicServiceType == null || rongIMClient$SearchType == null) {
            RLog.e(TAG, "searchType  is null!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        int[] iArr = new int[]{0};
        if (publicServiceType == PublicServiceType.APP_PUBLIC_SERVICE) {
            iArr[0] = 2;
        } else if (publicServiceType == PublicServiceType.PUBLIC_SERVICE) {
            iArr[0] = 1;
        }
        this.mWorkHandler.post(new RongIMClient$85(this, resultCallback, str, iArr, rongIMClient$SearchType));
    }

    public void subscribePublicService(PublicServiceType publicServiceType, String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (publicServiceType == null || TextUtils.isEmpty(str)) {
            RLog.e(TAG, "Parameter  is error!");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$86(this, rongIMClient$OperationCallback, str, publicServiceType));
    }

    public void unsubscribePublicService(PublicServiceType publicServiceType, String str, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (publicServiceType == null || TextUtils.isEmpty(str)) {
            RLog.e(TAG, "Parameter  is error!");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$87(this, rongIMClient$OperationCallback, str, publicServiceType));
    }

    public void getPublicServiceProfile(PublicServiceType publicServiceType, String str, ResultCallback<PublicServiceProfile> resultCallback) {
        if (publicServiceType == null || TextUtils.isEmpty(str)) {
            RLog.e(TAG, "Parameter  is error!");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
                return;
            }
            return;
        }
        this.mWorkHandler.post(new RongIMClient$88(this, resultCallback, str, publicServiceType));
    }

    public void getPublicServiceList(ResultCallback<PublicServiceProfileList> resultCallback) {
        this.mWorkHandler.post(new RongIMClient$89(this, resultCallback));
    }

    public void setNotificationQuietHours(String str, int i, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (TextUtils.isEmpty(str) || i <= 0 || i >= 1440) {
            RLog.e(TAG, "startTime, spanMinutes 或 spanMinutes 参数异常。");
            if (rongIMClient$OperationCallback != null) {
                rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            }
        } else if (Pattern.compile("^(([0-1][0-9])|2[0-3]):[0-5][0-9]:([0-5][0-9])$").matcher(str).find()) {
            this.mWorkHandler.post(new RongIMClient$90(this, rongIMClient$OperationCallback, str, i));
        } else {
            RLog.e(TAG, "startTime 参数异常。");
            rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
        }
    }

    public void removeNotificationQuietHours(RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        this.mWorkHandler.post(new RongIMClient$91(this, rongIMClient$OperationCallback));
    }

    public void updateMessageReceiptStatus(ConversationType conversationType, String str, long j, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        this.mWorkHandler.post(new RongIMClient$92(this, rongIMClient$OperationCallback, str, conversationType, j));
    }

    public long getSendTimeByMessageId(int i) {
        long j = 0;
        try {
            if (this.mLibHandler == null) {
                RLog.e(TAG, "getSendTimeByMessageId mLibHandler is null!");
            } else {
                j = this.mLibHandler.getSendTimeByMessageId(i);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return j;
    }

    public void getNotificationQuietHours(RongIMClient$GetNotificationQuietHoursCallback rongIMClient$GetNotificationQuietHoursCallback) {
        this.mWorkHandler.post(new RongIMClient$93(this, rongIMClient$GetNotificationQuietHoursCallback));
    }

    @Deprecated
    public void syncUserData(UserData userData, RongIMClient$OperationCallback rongIMClient$OperationCallback) {
        if (userData == null) {
            rongIMClient$OperationCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
        } else {
            this.mWorkHandler.post(new RongIMClient$94(this, rongIMClient$OperationCallback, userData));
        }
    }

    private void initMessageReceiver() {
        RLog.i(TAG, "initMessageReceiver");
        try {
            this.mLibHandler.setOnReceiveMessageListener(new RongIMClient$95(this));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getMessageByUid(String str, ResultCallback resultCallback) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "getMessageByUid mLibHandler is null!");
        } else {
            this.mWorkHandler.post(new RongIMClient$96(this, resultCallback, str));
        }
    }

    @Deprecated
    public void recordNotificationEvent(String str) {
        RongPushClient.recordNotificationEvent(str);
    }

    @Deprecated
    public void clearNotifications() {
        RongPushClient.clearAllNotifications(this.mContext);
    }

    public RealTimeLocationErrorCode getRealTimeLocation(ConversationType conversationType, String str) {
        if (this.mLibHandler == null) {
            RLog.e(TAG, "getRealTimeLocation IPC 进程尚未运行。");
            return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_NOT_INIT;
        } else if (conversationType == null || str == null) {
            RLog.e(TAG, "getRealTimeLocation Type or id is null!");
            return null;
        } else {
            int i = -1;
            try {
                i = this.mLibHandler.setupRealTimeLocation(conversationType.getValue(), str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return RealTimeLocationErrorCode.valueOf(i);
        }
    }

    public RealTimeLocationErrorCode startRealTimeLocation(ConversationType conversationType, String str) {
        if (this.mLibHandler == null) {
            RLog.e(TAG, "startRealTimeLocation IPC 进程尚未运行。");
            return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_NOT_INIT;
        } else if (conversationType == null || str == null) {
            RLog.e(TAG, "startRealTimeLocation Type or id is null!");
            return null;
        } else {
            int i = -1;
            try {
                i = this.mLibHandler.startRealTimeLocation(conversationType.getValue(), str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return RealTimeLocationErrorCode.valueOf(i);
        }
    }

    public RealTimeLocationErrorCode joinRealTimeLocation(ConversationType conversationType, String str) {
        if (this.mLibHandler == null) {
            RLog.e(TAG, "joinRealTimeLocation IPC 进程尚未运行。");
            return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_NOT_INIT;
        } else if (conversationType == null || str == null) {
            RLog.e(TAG, "joinRealTimeLocation Type or id is null!");
            return null;
        } else {
            int i = -1;
            try {
                i = this.mLibHandler.joinRealTimeLocation(conversationType.getValue(), str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return RealTimeLocationErrorCode.valueOf(i);
        }
    }

    public void quitRealTimeLocation(ConversationType conversationType, String str) {
        if (conversationType == null || str == null) {
            RLog.e(TAG, "quitRealTimeLocation Type or id is null!");
        } else if (this.mLibHandler == null) {
            RLog.e(TAG, "quitRealTimeLocation IPC 进程尚未运行。");
        } else {
            this.mWorkHandler.post(new RongIMClient$97(this, conversationType, str));
        }
    }

    public List<String> getRealTimeLocationParticipants(ConversationType conversationType, String str) {
        List<String> list = null;
        if (this.mLibHandler == null) {
            RLog.e(TAG, "getRealTimeLocationParticipants IPC 进程尚未运行。");
        } else if (conversationType == null || str == null) {
            RLog.e(TAG, "getRealTimeLocationParticipants Type or id is null!");
        } else {
            try {
                list = this.mLibHandler.getRealTimeLocationParticipants(conversationType.getValue(), str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public RealTimeLocationStatus getRealTimeLocationCurrentState(ConversationType conversationType, String str) {
        if (this.mLibHandler == null) {
            RLog.e(TAG, "getRealTimeLocationCurrentState IPC 进程尚未运行。");
            return RealTimeLocationStatus.RC_REAL_TIME_LOCATION_STATUS_IDLE;
        } else if (conversationType == null || str == null) {
            RLog.e(TAG, "getRealTimeLocationCurrentState Type or id is null!");
            return RealTimeLocationStatus.RC_REAL_TIME_LOCATION_STATUS_IDLE;
        } else {
            int i = 0;
            try {
                i = this.mLibHandler.getRealTimeLocationCurrentState(conversationType.getValue(), str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            return RealTimeLocationStatus.valueOf(i);
        }
    }

    public void addRealTimeLocationListener(ConversationType conversationType, String str, RongIMClient$RealTimeLocationListener rongIMClient$RealTimeLocationListener) {
        if (conversationType == null || str == null) {
            RLog.e(TAG, "addRealTimeLocationListener Type or id is null!");
        } else {
            this.mWorkHandler.post(new RongIMClient$98(this, conversationType, str, rongIMClient$RealTimeLocationListener));
        }
    }

    public void updateRealTimeLocationStatus(ConversationType conversationType, String str, double d, double d2) {
        RLog.d(TAG, "updateRealTimeLocationStatus latitude=" + d);
        if (conversationType == null || str == null) {
            RLog.e(TAG, "updateRealTimeLocationStatus Type or id is null!");
        } else if (this.mLibHandler != null) {
            try {
                this.mLibHandler.updateRealTimeLocationStatus(conversationType.getValue(), str, d, d2);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public Collection<TypingStatus> getTypingUserListFromConversation(ConversationType conversationType, String str) {
        return TypingMessageManager.getInstance().getTypingUserListFromConversation(conversationType, str);
    }

    public void sendTypingStatus(ConversationType conversationType, String str, String str2) {
        TypingMessageManager.getInstance().sendTypingMessage(conversationType, str, str2);
    }

    public static void setTypingStatusListener(RongIMClient$TypingStatusListener rongIMClient$TypingStatusListener) {
        TypingMessageManager.getInstance().setTypingMessageStatusListener(rongIMClient$TypingStatusListener);
    }

    public boolean getReadReceipt() {
        Resources resources = this.mContext.getResources();
        boolean z = false;
        try {
            z = resources.getBoolean(resources.getIdentifier("rc_read_receipt", "bool", this.mContext.getPackageName()));
        } catch (NotFoundException e) {
            RLog.e(TAG, "getReadReceipt rc_read_receipt not configure in rc_configuration.xml");
            e.printStackTrace();
        }
        return z;
    }

    public void sendReadReceiptMessage(ConversationType conversationType, String str, long j) {
        sendMessage(conversationType, str, new ReadReceiptMessage(j), null, null, null, null);
    }

    public static void setReadReceiptListener(RongIMClient$ReadReceiptListener rongIMClient$ReadReceiptListener) {
        sReadReceiptListener = rongIMClient$ReadReceiptListener;
    }

    public void startCustomService(String str, ICustomServiceListener iCustomServiceListener, CSCustomServiceInfo cSCustomServiceInfo) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "startCustomService kefuId should not be null!");
            return;
        }
        if (!(this.customServiceListener == null || this.customServiceCache == null || this.customServiceCache.get(str) == null)) {
            this.customServiceCache.remove(str);
            this.customServiceListener = null;
        }
        if (cSCustomServiceInfo == null) {
            cSCustomServiceInfo = new Builder().build();
        }
        MessageContent cSHandShakeMessage = new CSHandShakeMessage();
        cSHandShakeMessage.setCustomInfo(cSCustomServiceInfo);
        this.customServiceListener = iCustomServiceListener;
        sendMessage(ConversationType.CUSTOMER_SERVICE, str, cSHandShakeMessage, null, null, new RongIMClient$99(this), null);
    }

    public void selectCustomServiceGroup(String str, String str2) {
        sendChangeModelMessage(str, str2);
    }

    private void sendChangeModelMessage(String str, String str2) {
        RongIMClient$CustomServiceProfile rongIMClient$CustomServiceProfile = (RongIMClient$CustomServiceProfile) this.customServiceCache.get(str);
        String str3 = str;
        sendMessage(ConversationType.CUSTOMER_SERVICE, str3, CSChangeModeMessage.obtain(rongIMClient$CustomServiceProfile.sid, rongIMClient$CustomServiceProfile.uid, rongIMClient$CustomServiceProfile.pid, str2), null, null, new RongIMClient$100(this, str), null);
    }

    public void switchToHumanMode(String str) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "switchToHumanMode kefuId should not be null!");
        } else if (this.customServiceCache.containsKey(str)) {
            RongIMClient$CustomServiceProfile rongIMClient$CustomServiceProfile = (RongIMClient$CustomServiceProfile) this.customServiceCache.get(str);
            if (rongIMClient$CustomServiceProfile.groupList == null || rongIMClient$CustomServiceProfile.groupList.size() <= 0) {
                sendChangeModelMessage(str, null);
            } else {
                mHandler.post(new RongIMClient$101(this, rongIMClient$CustomServiceProfile));
            }
        } else {
            RLog.e(TAG, "switchToHumanMode " + str + " is not started yet!");
        }
    }

    public void evaluateCustomService(String str, boolean z, String str2) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "evaluateCustomService kefuId should not be null!");
        } else if (this.customServiceCache.containsKey(str)) {
            RongIMClient$CustomServiceProfile rongIMClient$CustomServiceProfile = (RongIMClient$CustomServiceProfile) this.customServiceCache.get(str);
            CSEvaluateMessage$Builder cSEvaluateMessage$Builder = new CSEvaluateMessage$Builder();
            if (TextUtils.isEmpty(str2)) {
                str2 = rongIMClient$CustomServiceProfile.sid;
            }
            sendMessage(ConversationType.CUSTOMER_SERVICE, str, cSEvaluateMessage$Builder.sid(str2).pid(rongIMClient$CustomServiceProfile.pid).uid(rongIMClient$CustomServiceProfile.uid).type(0).isRobotResolved(z).build(), null, null, null, null);
        } else {
            RLog.e(TAG, "evaluateCustomService " + str + " is not started yet!");
        }
    }

    public void evaluateCustomService(String str, int i, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "evaluateCustomService kefuId should not be null!");
        } else if (this.customServiceCache.containsKey(str)) {
            RongIMClient$CustomServiceProfile rongIMClient$CustomServiceProfile = (RongIMClient$CustomServiceProfile) this.customServiceCache.get(str);
            CSEvaluateMessage$Builder cSEvaluateMessage$Builder = new CSEvaluateMessage$Builder();
            if (TextUtils.isEmpty(str3)) {
                str3 = rongIMClient$CustomServiceProfile.sid;
            }
            sendMessage(ConversationType.CUSTOMER_SERVICE, str, cSEvaluateMessage$Builder.sid(str3).pid(rongIMClient$CustomServiceProfile.pid).uid(rongIMClient$CustomServiceProfile.uid).source(i).suggest(str2).type(1).build(), null, null, null, null);
        } else {
            RLog.e(TAG, "evaluateCustomService " + str + " is not started yet!");
        }
    }

    public void stopCustomService(String str) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "stopCustomService kefuId should not be null!");
        } else if (this.customServiceCache.containsKey(str)) {
            RongIMClient$CustomServiceProfile rongIMClient$CustomServiceProfile = (RongIMClient$CustomServiceProfile) this.customServiceCache.get(str);
            sendMessage(ConversationType.CUSTOMER_SERVICE, str, CSSuspendMessage.obtain(rongIMClient$CustomServiceProfile.sid, rongIMClient$CustomServiceProfile.uid, rongIMClient$CustomServiceProfile.pid), null, null, null, null);
            this.customServiceCache.remove(str);
            this.customServiceListener = null;
        } else {
            RLog.e(TAG, "stopCustomService " + str + " is not started yet!");
        }
    }

    public static void setServerInfo(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            RLog.e(TAG, "setServerInfo naviServer should not be null.");
            throw new IllegalArgumentException("naviServer should not be null.");
        }
        mNaviServer = str;
        mFileServer = str2;
    }

    public void recallMessage(Message message, ResultCallback<RecallNotificationMessage> resultCallback) {
        if (this.mLibHandler == null) {
            RLog.e(TAG, "recallMessage IPC 进程尚未运行。");
            if (resultCallback != null) {
                resultCallback.onError(RongIMClient$ErrorCode.IPC_DISCONNECT);
                return;
            }
            return;
        }
        sendMessage(message.getConversationType(), message.getTargetId(), new RecallCommandMessage(message.getUId()), null, null, new RongIMClient$102(this, message, resultCallback));
    }

    public void getUnreadMentionedMessages(ConversationType conversationType, String str, ResultCallback<List<Message>> resultCallback) {
        if (TextUtils.isEmpty(str) || conversationType == null) {
            RLog.e(TAG, "the parameter of targetId or ConversationType is error!");
            resultCallback.onError(RongIMClient$ErrorCode.PARAMETER_ERROR);
            return;
        }
        this.mWorkHandler.post(new RongIMClient$103(this, resultCallback, conversationType, str));
    }

    public static void setRecallMessageListener(RongIMClient$RecallMessageListener rongIMClient$RecallMessageListener) {
        sRecallMessageListener = rongIMClient$RecallMessageListener;
    }
}
