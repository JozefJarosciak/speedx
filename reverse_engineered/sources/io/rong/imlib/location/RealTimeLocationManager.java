package io.rong.imlib.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import io.rong.common.RLog;
import io.rong.imlib.AnnotationNotFoundException;
import io.rong.imlib.NativeClient;
import io.rong.imlib.NativeClient.OnReceiveMessageListenerEx;
import io.rong.imlib.NativeClient.RealTimeLocationListener;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationErrorCode;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationStatus;
import io.rong.imlib.location.message.RealTimeLocationEndMessage;
import io.rong.imlib.location.message.RealTimeLocationJoinMessage;
import io.rong.imlib.location.message.RealTimeLocationQuitMessage;
import io.rong.imlib.location.message.RealTimeLocationStartMessage;
import io.rong.imlib.location.message.RealTimeLocationStatusMessage;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$MessageDirection;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.navigation.LocationConfig;
import io.rong.imlib.navigation.NavigationClient;
import java.util.HashMap;
import java.util.List;

public class RealTimeLocationManager {
    private static final String TAG = RealTimeLocationManager.class.getSimpleName();
    private static RealTimeLocationManager sIns;
    private Context mContext;
    private HashMap<String, RealTimeLocation> mInsMap;
    private int mLastLeft;
    private HashMap<String, RealTimeLocationListener> mObservers;
    private HashMap<String, OfflineRequest> mOfflineRequest;
    private NetworkStatusReceiver mReceiver = new NetworkStatusReceiver();

    /* renamed from: io.rong.imlib.location.RealTimeLocationManager$1 */
    class C53691 implements OnReceiveMessageListenerEx {
        C53691() {
        }

        public boolean onReceived(Message message, int i) {
            Log.i(RealTimeLocationManager.TAG, "onReceived : " + message.getObjectName() + ", left = " + i + ", sender=" + message.getSenderUserId());
            if (message.getContent() == null) {
                return false;
            }
            if (i > 0) {
                RealTimeLocationManager.this.mLastLeft = i;
            }
            if (RealTimeLocationManager.this.mLastLeft <= 0) {
                return RealTimeLocationManager.this.handleRequest(message);
            }
            boolean access$300 = RealTimeLocationManager.this.cacheOfflineRequest(message);
            if (i != 0) {
                return access$300;
            }
            RealTimeLocationManager.this.mLastLeft = 0;
            return RealTimeLocationManager.this.handleRequest(message);
        }
    }

    private class NetworkStatusReceiver extends BroadcastReceiver {
        private NetworkStatusReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                if ((activeNetworkInfo == null || !activeNetworkInfo.isConnected()) && RealTimeLocationManager.this.mInsMap != null && RealTimeLocationManager.this.mInsMap.size() > 0) {
                    for (RealTimeLocation handler : RealTimeLocationManager.this.mInsMap.values()) {
                        handler.getHandler().sendEmptyMessage(13);
                    }
                }
            }
        }
    }

    private class OfflineRequest {
        private int joinCount;
        private int quitCount;
        private String sender;
        private int startCount;
        private String targetId;
        private ConversationType type;

        public OfflineRequest(ConversationType conversationType, String str, String str2) {
            this.targetId = str;
            this.sender = str2;
            this.type = conversationType;
        }

        public void startInc() {
            this.startCount++;
        }

        public void joinInc() {
            this.joinCount++;
        }

        public void quitInc() {
            this.quitCount++;
        }

        public String getSender() {
            return this.sender;
        }

        public String getTargetId() {
            return this.targetId;
        }

        public ConversationType getConversationType() {
            return this.type;
        }

        public boolean shouldHandle() {
            boolean z;
            String access$100 = RealTimeLocationManager.TAG;
            StringBuilder append = new StringBuilder().append("shouldHandle : ");
            if (this.startCount + this.joinCount > this.quitCount) {
                z = true;
            } else {
                z = false;
            }
            Log.d(access$100, append.append(z).toString());
            return this.startCount + this.joinCount > this.quitCount;
        }
    }

    private RealTimeLocationManager(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(this.mReceiver, intentFilter);
        this.mContext = context;
        this.mInsMap = new HashMap();
        this.mOfflineRequest = new HashMap();
        this.mObservers = new HashMap();
        this.mLastLeft = 0;
        NativeClient.getInstance().setOnReceiveMessageListenerEx(new C53691());
        try {
            NativeClient.registerMessageType(RealTimeLocationStartMessage.class);
            NativeClient.registerMessageType(RealTimeLocationJoinMessage.class);
            NativeClient.registerMessageType(RealTimeLocationQuitMessage.class);
            NativeClient.registerMessageType(RealTimeLocationStatusMessage.class);
            NativeClient.registerMessageType(RealTimeLocationEndMessage.class);
        } catch (AnnotationNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static RealTimeLocationManager init(Context context) {
        if (sIns == null) {
            sIns = new RealTimeLocationManager(context);
        }
        return sIns;
    }

    private RealTimeLocation getRealTimeLocation(ConversationType conversationType, String str) {
        RealTimeLocation realTimeLocation = null;
        String str2 = conversationType.getName() + str;
        if (this.mInsMap.size() > 0) {
            realTimeLocation = (RealTimeLocation) this.mInsMap.get(str2);
        }
        if (realTimeLocation != null) {
            return realTimeLocation;
        }
        realTimeLocation = new RealTimeLocation(this.mContext, conversationType, str);
        this.mInsMap.put(str2, realTimeLocation);
        return realTimeLocation;
    }

    private boolean cacheOfflineRequest(Message message) {
        MessageContent content = message.getContent();
        String str = message.getConversationType() + message.getTargetId();
        OfflineRequest offlineRequest;
        if (content instanceof RealTimeLocationStartMessage) {
            offlineRequest = (OfflineRequest) this.mOfflineRequest.get(str);
            if (offlineRequest == null) {
                offlineRequest = new OfflineRequest(message.getConversationType(), message.getTargetId(), message.getSenderUserId());
                this.mOfflineRequest.put(str, offlineRequest);
            }
            offlineRequest.startInc();
            return false;
        } else if (content instanceof RealTimeLocationJoinMessage) {
            offlineRequest = (OfflineRequest) this.mOfflineRequest.get(str);
            if (offlineRequest == null) {
                offlineRequest = new OfflineRequest(message.getConversationType(), message.getTargetId(), message.getSenderUserId());
                this.mOfflineRequest.put(str, offlineRequest);
            }
            offlineRequest.joinInc();
            return false;
        } else if (!(content instanceof RealTimeLocationQuitMessage)) {
            return content instanceof RealTimeLocationStatusMessage;
        } else {
            offlineRequest = (OfflineRequest) this.mOfflineRequest.get(str);
            if (offlineRequest == null) {
                offlineRequest = new OfflineRequest(message.getConversationType(), message.getTargetId(), message.getSenderUserId());
                this.mOfflineRequest.put(str, offlineRequest);
            }
            offlineRequest.quitInc();
            return false;
        }
    }

    private boolean handleRequest(Message message) {
        if (this.mOfflineRequest.size() > 0) {
            for (OfflineRequest offlineRequest : this.mOfflineRequest.values()) {
                if (offlineRequest != null && offlineRequest.shouldHandle()) {
                    RealTimeLocation realTimeLocation = getRealTimeLocation(offlineRequest.getConversationType(), offlineRequest.getTargetId());
                    android.os.Message obtain = android.os.Message.obtain();
                    obtain.what = 3;
                    obtain.obj = offlineRequest.getSender();
                    realTimeLocation.sendMessage(obtain);
                }
            }
            this.mOfflineRequest.clear();
            this.mLastLeft = 0;
        } else {
            MessageContent content = message.getContent();
            if (message.getMessageDirection().equals(Message$MessageDirection.SEND)) {
                return false;
            }
            if (content instanceof RealTimeLocationStartMessage) {
                RealTimeLocationListener realTimeLocationListener = (RealTimeLocationListener) this.mObservers.get(message.getConversationType().getName() + message.getTargetId());
                RealTimeLocation realTimeLocation2 = getRealTimeLocation(message.getConversationType(), message.getTargetId());
                realTimeLocation2.addListener(realTimeLocationListener);
                this.mObservers.get(message.getConversationType().getName() + message.getSenderUserId());
                android.os.Message obtain2 = android.os.Message.obtain();
                obtain2.what = 3;
                obtain2.obj = message.getSenderUserId();
                realTimeLocation2.sendMessage(obtain2);
                return false;
            } else if (content instanceof RealTimeLocationJoinMessage) {
                r0 = getRealTimeLocation(message.getConversationType(), message.getTargetId());
                r2 = android.os.Message.obtain();
                r2.what = 4;
                r2.obj = message.getSenderUserId();
                r0.sendMessage(r2);
                return false;
            } else if (content instanceof RealTimeLocationQuitMessage) {
                r0 = getRealTimeLocation(message.getConversationType(), message.getTargetId());
                r2 = android.os.Message.obtain();
                r2.what = 5;
                r2.obj = message.getSenderUserId();
                r0.sendMessage(r2);
                return false;
            } else if (content instanceof RealTimeLocationStatusMessage) {
                r0 = getRealTimeLocation(message.getConversationType(), message.getTargetId());
                android.os.Message obtain3 = android.os.Message.obtain();
                obtain3.what = 6;
                obtain3.obj = message;
                r0.sendMessage(obtain3);
                return true;
            }
        }
        return false;
    }

    public int setupRealTimeLocation(Context context, ConversationType conversationType, String str) {
        String str2 = conversationType.getName() + str;
        LocationConfig locationConfig = NavigationClient.getInstance().getLocationConfig(this.mContext);
        if (locationConfig == null || !locationConfig.isConfigure()) {
            return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_CONVERSATION_NOT_SUPPORT.getValue();
        }
        int i;
        int[] conversationTypes = locationConfig.getConversationTypes();
        for (int i2 : conversationTypes) {
            if (conversationType.getValue() == i2) {
                i = 1;
                break;
            }
        }
        i = 0;
        if (i == 0) {
            return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_CONVERSATION_NOT_SUPPORT.getValue();
        }
        RealTimeLocation realTimeLocation;
        if (this.mInsMap.size() > 0) {
            realTimeLocation = (RealTimeLocation) this.mInsMap.get(str2);
        } else {
            realTimeLocation = null;
        }
        if (realTimeLocation == null || realTimeLocation.getRealTimeLocationCurrentState().equals(RealTimeLocationStatus.RC_REAL_TIME_LOCATION_STATUS_IDLE)) {
            return 0;
        }
        return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_IS_ON_GOING.getValue();
    }

    public int startRealTimeLocation(ConversationType conversationType, String str) {
        RealTimeLocation realTimeLocation;
        String str2 = conversationType.getName() + str;
        RealTimeLocation realTimeLocation2;
        if (this.mInsMap.size() > 0) {
            realTimeLocation2 = (RealTimeLocation) this.mInsMap.get(str2);
            if (realTimeLocation2 == null) {
                realTimeLocation2 = new RealTimeLocation(this.mContext, conversationType, str);
                this.mInsMap.put(str2, realTimeLocation2);
                realTimeLocation = realTimeLocation2;
            } else {
                realTimeLocation = realTimeLocation2;
            }
        } else {
            realTimeLocation2 = new RealTimeLocation(this.mContext, conversationType, str);
            this.mInsMap.put(str2, realTimeLocation2);
            realTimeLocation = realTimeLocation2;
        }
        realTimeLocation.addListener((RealTimeLocationListener) this.mObservers.get(str2));
        realTimeLocation.sendMessage(0);
        if (realTimeLocation.gpsIsAvailable()) {
            return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_SUCCESS.getValue();
        }
        return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_GPS_DISABLED.getValue();
    }

    public int joinRealTimeLocation(ConversationType conversationType, String str) {
        if (this.mInsMap.size() == 0) {
            RLog.m19420e(TAG, "joinRealTimeLocation No instance!");
            return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_NOT_INIT.getValue();
        }
        RealTimeLocation realTimeLocation = (RealTimeLocation) this.mInsMap.get(conversationType.getName() + str);
        if (realTimeLocation == null) {
            RLog.m19420e(TAG, "joinRealTimeLocation No instance!");
            return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_NOT_INIT.getValue();
        }
        LocationConfig locationConfig = NavigationClient.getInstance().getLocationConfig(this.mContext);
        List realTimeLocationParticipants = getRealTimeLocationParticipants(conversationType, str);
        if (locationConfig != null && realTimeLocationParticipants != null && locationConfig.getMaxParticipant() < realTimeLocationParticipants.size()) {
            return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_EXCEED_MAX_PARTICIPANT.getValue();
        }
        realTimeLocation.sendMessage(1);
        if (realTimeLocation.gpsIsAvailable()) {
            return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_SUCCESS.getValue();
        }
        return RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_GPS_DISABLED.getValue();
    }

    public void quitRealTimeLocation(ConversationType conversationType, String str) {
        if (this.mInsMap.size() == 0) {
            RLog.m19420e(TAG, "quitRealTimeLocation No instance!");
            return;
        }
        RealTimeLocation realTimeLocation = (RealTimeLocation) this.mInsMap.get(conversationType.getName() + str);
        if (realTimeLocation == null) {
            RLog.m19420e(TAG, "quitRealTimeLocation No instance!");
        } else {
            realTimeLocation.sendMessage(2);
        }
    }

    public List<String> getRealTimeLocationParticipants(ConversationType conversationType, String str) {
        if (this.mInsMap.size() == 0) {
            RLog.m19420e(TAG, "getRealTimeLocationParticipants No instance!");
            return null;
        }
        RealTimeLocation realTimeLocation = (RealTimeLocation) this.mInsMap.get(conversationType.getName() + str);
        if (realTimeLocation != null) {
            return realTimeLocation.getParticipants();
        }
        RLog.m19420e(TAG, "getRealTimeLocationParticipants No instance!");
        return null;
    }

    public RealTimeLocationStatus getRealTimeLocationCurrentState(ConversationType conversationType, String str) {
        if (this.mInsMap.size() == 0) {
            RLog.m19420e(TAG, "getRealTimeLocationCurrentState No instance!");
            return RealTimeLocationStatus.RC_REAL_TIME_LOCATION_STATUS_IDLE;
        }
        RealTimeLocation realTimeLocation = (RealTimeLocation) this.mInsMap.get(conversationType.getName() + str);
        if (realTimeLocation != null) {
            return realTimeLocation.getRealTimeLocationCurrentState();
        }
        RLog.m19420e(TAG, "getRealTimeLocationCurrentState No instance!");
        return RealTimeLocationStatus.RC_REAL_TIME_LOCATION_STATUS_IDLE;
    }

    public void addListener(ConversationType conversationType, String str, RealTimeLocationListener realTimeLocationListener) {
        String str2 = conversationType.getName() + str;
        if (this.mObservers.size() <= 0 || this.mObservers.get(str2) == null) {
            this.mObservers.put(str2, realTimeLocationListener);
        } else {
            this.mObservers.remove(str2);
            this.mObservers.put(str2, realTimeLocationListener);
        }
        if (this.mInsMap.size() > 0 && this.mInsMap.get(str2) != null) {
            ((RealTimeLocation) this.mInsMap.get(str2)).addListener(realTimeLocationListener);
        }
    }

    public void removeListener(ConversationType conversationType, String str, RealTimeLocationListener realTimeLocationListener) {
        String str2 = conversationType.getName() + str;
        if (this.mObservers.size() > 0 && realTimeLocationListener != null) {
            this.mObservers.remove(str2);
            if (this.mInsMap.get(str2) != null) {
                ((RealTimeLocation) this.mInsMap.get(str2)).deleteListener(realTimeLocationListener);
            }
        }
    }

    public void updateLocation(ConversationType conversationType, String str, double d, double d2) {
        if (this.mInsMap.size() == 0) {
            RLog.m19420e(TAG, "getRealTimeLocationCurrentState No instance!");
            return;
        }
        RealTimeLocation realTimeLocation = (RealTimeLocation) this.mInsMap.get(conversationType.getName() + str);
        if (realTimeLocation == null) {
            RLog.m19420e(TAG, "getRealTimeLocationCurrentState No instance!");
        } else {
            realTimeLocation.updateLocation(d, d2);
        }
    }
}
