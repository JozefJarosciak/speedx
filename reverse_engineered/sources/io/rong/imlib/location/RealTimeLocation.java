package io.rong.imlib.location;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import io.rong.imlib.NativeClient;
import io.rong.imlib.NativeClient.IResultCallback;
import io.rong.imlib.NativeClient.ISendMessageCallback;
import io.rong.imlib.NativeClient.RealTimeLocationListener;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationErrorCode;
import io.rong.imlib.location.RealTimeLocationConstant.RealTimeLocationStatus;
import io.rong.imlib.location.message.RealTimeLocationJoinMessage;
import io.rong.imlib.location.message.RealTimeLocationQuitMessage;
import io.rong.imlib.location.message.RealTimeLocationStartMessage;
import io.rong.imlib.location.message.RealTimeLocationStatusMessage;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message$SentStatus;
import io.rong.imlib.navigation.LocationConfig;
import io.rong.imlib.navigation.NavigationClient;
import io.rong.imlib.stateMachine.State;
import io.rong.imlib.stateMachine.StateMachine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RealTimeLocation extends StateMachine {
    public static final int RC_REAL_TIME_LOCATION_EVENT_DISABLE_GPS = 14;
    public static final int RC_REAL_TIME_LOCATION_EVENT_JOIN = 1;
    public static final int RC_REAL_TIME_LOCATION_EVENT_JOIN_FAILURE = 9;
    public static final int RC_REAL_TIME_LOCATION_EVENT_NETWORK_UNAVAILABLE = 13;
    public static final int RC_REAL_TIME_LOCATION_EVENT_PARTICIPANT_JOIN = 4;
    public static final int RC_REAL_TIME_LOCATION_EVENT_PARTICIPANT_NO_RESPONSE = 12;
    public static final int RC_REAL_TIME_LOCATION_EVENT_PARTICIPANT_QUIT = 5;
    public static final int RC_REAL_TIME_LOCATION_EVENT_PARTICIPANT_START = 3;
    public static final int RC_REAL_TIME_LOCATION_EVENT_QUIT = 2;
    public static final int RC_REAL_TIME_LOCATION_EVENT_RECEIVE_LOCATION_MESSAGE = 6;
    public static final int RC_REAL_TIME_LOCATION_EVENT_REFRESH_TIME_EXPIRE = 10;
    public static final int RC_REAL_TIME_LOCATION_EVENT_SEND_LOCATION_MESSAGE = 7;
    public static final int RC_REAL_TIME_LOCATION_EVENT_START = 0;
    public static final int RC_REAL_TIME_LOCATION_EVENT_START_FAILURE = 8;
    public static final int RC_REAL_TIME_LOCATION_EVENT_TERMINAL = 11;
    private static final String TAG = RealTimeLocation.class.getSimpleName();
    private NativeClient mClient;
    private State mConnectedState = new ConnectedState();
    private ConversationType mConversationType;
    private RealTimeLocationStatus mCurrentState;
    private int mFilterDistance = 5;
    private boolean mGpsEnable = true;
    private State mIdleState = new IdleState();
    private State mIncomingState = new IncomingState();
    private double mLatitude = 0.0d;
    private LocationListener mLocationListener;
    private LocationManager mLocationManager;
    private double mLongitude = 0.0d;
    private RealTimeLocationListener mObservers;
    private State mOutgoingState = new OutgoingState();
    private ArrayList<String> mParticipants;
    private int mRefreshInterval = 10000;
    private Runnable mRefreshRunnable;
    private String mSelfId;
    private String mTargetId;
    private State mTerminalState = new TerminalState();
    private HashMap<String, ParticipantWatcher> mWatcher;

    /* renamed from: io.rong.imlib.location.RealTimeLocation$1 */
    class C53631 implements Runnable {
        C53631() {
        }

        public void run() {
            RealTimeLocation.this.getHandler().sendEmptyMessage(10);
            RealTimeLocation.this.getHandler().postDelayed(RealTimeLocation.this.mRefreshRunnable, (long) RealTimeLocation.this.mRefreshInterval);
        }
    }

    /* renamed from: io.rong.imlib.location.RealTimeLocation$2 */
    class C53642 implements LocationListener {
        C53642() {
        }

        public void onLocationChanged(Location location) {
            Log.d(RealTimeLocation.TAG, "onLocationChanged");
            if (location != null) {
                RealTimeLocation.this.mLatitude = location.getLatitude();
                RealTimeLocation.this.mLongitude = location.getLongitude();
            }
        }

        public void onStatusChanged(String str, int i, Bundle bundle) {
            Log.d(RealTimeLocation.TAG, "onStatusChanged");
            switch (i) {
                case 0:
                    Log.i(RealTimeLocation.TAG, "当前GPS状态为服务区外状态");
                    return;
                case 1:
                    Log.i(RealTimeLocation.TAG, "当前GPS状态为暂停服务状态");
                    return;
                case 2:
                    Log.i(RealTimeLocation.TAG, "当前GPS状态为可见状态");
                    return;
                default:
                    return;
            }
        }

        public void onProviderEnabled(String str) {
            RealTimeLocation.this.mGpsEnable = true;
            Location lastKnownLocation = RealTimeLocation.this.mLocationManager.getLastKnownLocation(str);
            if (lastKnownLocation != null) {
                RealTimeLocation.this.mLatitude = lastKnownLocation.getLatitude();
                RealTimeLocation.this.mLongitude = lastKnownLocation.getLongitude();
            }
        }

        public void onProviderDisabled(String str) {
            RealTimeLocation.this.mGpsEnable = false;
            RealTimeLocation.this.getHandler().sendEmptyMessage(14);
        }
    }

    /* renamed from: io.rong.imlib.location.RealTimeLocation$3 */
    class C53653 implements ISendMessageCallback<Message> {
        C53653() {
        }

        public void onAttached(Message message) {
            if (NativeClient.getInstance().getOnReceiveMessageListener() != null) {
                message.setSentStatus(Message$SentStatus.SENT);
                NativeClient.getInstance().getOnReceiveMessageListener().onReceived(message, 0, false);
            }
        }

        public void onSuccess(Message message) {
            RealTimeLocation.this.getHandler().sendEmptyMessage(7);
        }

        public void onError(Message message, int i) {
            RealTimeLocation.this.getHandler().sendEmptyMessage(8);
        }
    }

    /* renamed from: io.rong.imlib.location.RealTimeLocation$4 */
    class C53664 implements ISendMessageCallback<Message> {
        C53664() {
        }

        public void onAttached(Message message) {
        }

        public void onSuccess(Message message) {
            RealTimeLocation.this.getHandler().sendEmptyMessage(7);
        }

        public void onError(Message message, int i) {
            RealTimeLocation.this.getHandler().sendEmptyMessage(9);
        }
    }

    /* renamed from: io.rong.imlib.location.RealTimeLocation$5 */
    class C53675 implements IResultCallback<Integer> {
        C53675() {
        }

        public void onSuccess(Integer num) {
        }

        public void onError(int i) {
        }
    }

    private class ConnectedState extends State {
        private ConnectedState() {
        }

        public void enter() {
            RealTimeLocation.this.mCurrentState = RealTimeLocationStatus.RC_REAL_TIME_LOCATION_STATUS_CONNECTED;
            RealTimeLocation.this.onStatusChanged(RealTimeLocation.this.mCurrentState);
            RealTimeLocation.this.startTimer();
            Log.d(RealTimeLocation.TAG, "connected enter : current = " + RealTimeLocation.this.mCurrentState);
        }

        public boolean processMessage(android.os.Message message) {
            ParticipantWatcher participantWatcher;
            String str;
            switch (message.what) {
                case 2:
                    RealTimeLocation.this.sendQuitMessage();
                    RealTimeLocation.this.mParticipants.remove(RealTimeLocation.this.mSelfId);
                    if (RealTimeLocation.this.mParticipants.size() != 0) {
                        RealTimeLocation.this.transitionTo(RealTimeLocation.this.mIncomingState);
                        break;
                    }
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mTerminalState);
                    break;
                case 4:
                    participantWatcher = new ParticipantWatcher(null);
                    participantWatcher.start();
                    str = (String) message.obj;
                    RealTimeLocation.this.mWatcher.put(str, participantWatcher);
                    RealTimeLocation.this.mParticipants.add(str);
                    RealTimeLocation.this.onParticipantsJoin(str);
                    RealTimeLocation.this.getHandler().sendEmptyMessage(7);
                    break;
                case 5:
                case 12:
                    str = (String) message.obj;
                    RealTimeLocation.this.mParticipants.remove(str);
                    ((ParticipantWatcher) RealTimeLocation.this.mWatcher.get(str)).stop();
                    RealTimeLocation.this.mWatcher.remove(str);
                    RealTimeLocation.this.onParticipantQuit(str);
                    if (RealTimeLocation.this.mParticipants.size() == 1) {
                        RealTimeLocation.this.transitionTo(RealTimeLocation.this.mOutgoingState);
                        break;
                    }
                    break;
                case 6:
                    Message message2 = (Message) message.obj;
                    String senderUserId = message2.getSenderUserId();
                    if (RealTimeLocation.this.mWatcher.get(senderUserId) == null) {
                        participantWatcher = new ParticipantWatcher(senderUserId);
                        participantWatcher.start();
                        RealTimeLocation.this.mWatcher.put(senderUserId, participantWatcher);
                        RealTimeLocation.this.mParticipants.add(senderUserId);
                        RealTimeLocation.this.onParticipantsJoin(senderUserId);
                    } else {
                        ((ParticipantWatcher) RealTimeLocation.this.mWatcher.get(senderUserId)).update();
                    }
                    RealTimeLocationStatusMessage realTimeLocationStatusMessage = (RealTimeLocationStatusMessage) message2.getContent();
                    RealTimeLocation.this.onReceiveLocation(realTimeLocationStatusMessage.getLatitude(), realTimeLocationStatusMessage.getLongitude(), senderUserId);
                    break;
                case 7:
                case 10:
                    RealTimeLocation.this.sendLocationMessage();
                    RealTimeLocation.this.updateSelfLocation();
                    break;
                case 8:
                    RealTimeLocation.this.onError(RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_START_FAILURE);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mTerminalState);
                    break;
                case 9:
                    RealTimeLocation.this.onError(RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_JOIN_FAILURE);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mTerminalState);
                    break;
                case 13:
                    RealTimeLocation.this.onError(RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_NETWORK_UNAVAILABLE);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mTerminalState);
                    break;
                case 14:
                    RealTimeLocation.this.onError(RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_GPS_DISABLED);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mTerminalState);
                    break;
            }
            return true;
        }
    }

    private class IdleState extends State {
        private IdleState() {
        }

        public void enter() {
            RealTimeLocation.this.mCurrentState = RealTimeLocationStatus.RC_REAL_TIME_LOCATION_STATUS_IDLE;
            if (RealTimeLocation.this.mSelfId != null) {
                RealTimeLocation.this.onStatusChanged(RealTimeLocation.this.mCurrentState);
            }
            Log.d(RealTimeLocation.TAG, "idle enter : current = " + RealTimeLocation.this.mCurrentState);
        }

        public boolean processMessage(android.os.Message message) {
            String str;
            ParticipantWatcher participantWatcher;
            switch (message.what) {
                case 0:
                    RealTimeLocation.this.sendStartMessage();
                    RealTimeLocation.this.mSelfId = RealTimeLocation.this.mClient.getCurrentUserId();
                    RealTimeLocation.this.mParticipants.add(RealTimeLocation.this.mSelfId);
                    RealTimeLocation.this.updateSelfLocation();
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mOutgoingState);
                    break;
                case 3:
                    str = (String) message.obj;
                    participantWatcher = new ParticipantWatcher(str);
                    participantWatcher.start();
                    RealTimeLocation.this.mWatcher.put(str, participantWatcher);
                    RealTimeLocation.this.mParticipants.add(str);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mIncomingState);
                    break;
                case 6:
                    str = ((Message) message.obj).getSenderUserId();
                    participantWatcher = new ParticipantWatcher(str);
                    participantWatcher.start();
                    RealTimeLocation.this.mWatcher.put(str, participantWatcher);
                    RealTimeLocation.this.mParticipants.add(str);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mIncomingState);
                    break;
            }
            return true;
        }
    }

    private class IncomingState extends State {
        private IncomingState() {
        }

        public void enter() {
            RealTimeLocation.this.stopTimer();
            RealTimeLocation.this.mCurrentState = RealTimeLocationStatus.RC_REAL_TIME_LOCATION_STATUS_INCOMING;
            RealTimeLocation.this.onStatusChanged(RealTimeLocation.this.mCurrentState);
            Log.d(RealTimeLocation.TAG, "incoming enter : current = " + RealTimeLocation.this.mCurrentState);
        }

        public boolean processMessage(android.os.Message message) {
            String str;
            ParticipantWatcher participantWatcher;
            switch (message.what) {
                case 1:
                    RealTimeLocation.this.sendJoinMessage();
                    RealTimeLocation.this.mSelfId = RealTimeLocation.this.mClient.getCurrentUserId();
                    RealTimeLocation.this.mParticipants.add(RealTimeLocation.this.mSelfId);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mConnectedState);
                    break;
                case 4:
                    str = (String) message.obj;
                    participantWatcher = new ParticipantWatcher(str);
                    participantWatcher.start();
                    RealTimeLocation.this.mWatcher.put(str, participantWatcher);
                    RealTimeLocation.this.mParticipants.add(str);
                    RealTimeLocation.this.onParticipantsJoin(str);
                    break;
                case 5:
                case 12:
                    str = (String) message.obj;
                    if (RealTimeLocation.this.mWatcher.get(str) != null) {
                        ((ParticipantWatcher) RealTimeLocation.this.mWatcher.get(str)).stop();
                        RealTimeLocation.this.mWatcher.remove(str);
                        RealTimeLocation.this.mParticipants.remove(str);
                        RealTimeLocation.this.onParticipantQuit(str);
                    }
                    if (RealTimeLocation.this.mParticipants.size() == 0) {
                        RealTimeLocation.this.transitionTo(RealTimeLocation.this.mTerminalState);
                        break;
                    }
                    break;
                case 6:
                    Message message2 = (Message) message.obj;
                    String senderUserId = message2.getSenderUserId();
                    if (RealTimeLocation.this.mWatcher.get(senderUserId) == null) {
                        participantWatcher = new ParticipantWatcher(senderUserId);
                        participantWatcher.start();
                        RealTimeLocation.this.mWatcher.put(senderUserId, participantWatcher);
                        RealTimeLocation.this.mParticipants.add(senderUserId);
                        RealTimeLocation.this.onParticipantsJoin(senderUserId);
                    } else {
                        ((ParticipantWatcher) RealTimeLocation.this.mWatcher.get(senderUserId)).update();
                    }
                    RealTimeLocationStatusMessage realTimeLocationStatusMessage = (RealTimeLocationStatusMessage) message2.getContent();
                    RealTimeLocation.this.onReceiveLocation(realTimeLocationStatusMessage.getLatitude(), realTimeLocationStatusMessage.getLongitude(), senderUserId);
                    break;
                case 13:
                    RealTimeLocation.this.onError(RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_NETWORK_UNAVAILABLE);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mTerminalState);
                    break;
                case 14:
                    RealTimeLocation.this.onError(RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_GPS_DISABLED);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mTerminalState);
                    break;
            }
            return true;
        }
    }

    private class OutgoingState extends State {
        private OutgoingState() {
        }

        public void enter() {
            RealTimeLocation.this.mCurrentState = RealTimeLocationStatus.RC_REAL_TIME_LOCATION_STATUS_OUTGOING;
            RealTimeLocation.this.onStatusChanged(RealTimeLocation.this.mCurrentState);
            RealTimeLocation.this.startTimer();
            Log.d(RealTimeLocation.TAG, "outgoing enter : current = " + RealTimeLocation.this.mCurrentState);
        }

        public boolean processMessage(android.os.Message message) {
            switch (message.what) {
                case 2:
                    RealTimeLocation.this.sendQuitMessage();
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mTerminalState);
                    break;
                case 4:
                    String str = (String) message.obj;
                    ParticipantWatcher participantWatcher = new ParticipantWatcher(str);
                    participantWatcher.start();
                    RealTimeLocation.this.mWatcher.put(str, participantWatcher);
                    RealTimeLocation.this.mParticipants.add(str);
                    RealTimeLocation.this.onParticipantsJoin(str);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mConnectedState);
                    break;
                case 7:
                case 10:
                    RealTimeLocation.this.sendLocationMessage();
                    RealTimeLocation.this.updateSelfLocation();
                    break;
                case 8:
                    RealTimeLocation.this.onError(RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_START_FAILURE);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mIdleState);
                    break;
                case 13:
                    RealTimeLocation.this.onError(RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_NETWORK_UNAVAILABLE);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mTerminalState);
                    break;
                case 14:
                    RealTimeLocation.this.onError(RealTimeLocationErrorCode.RC_REAL_TIME_LOCATION_GPS_DISABLED);
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mTerminalState);
                    break;
            }
            return true;
        }
    }

    private class ParticipantWatcher {
        String id;
        Runnable runnable;

        public ParticipantWatcher(final String str) {
            this.id = str;
            this.runnable = new Runnable(RealTimeLocation.this) {
                public void run() {
                    android.os.Message obtain = android.os.Message.obtain();
                    obtain.what = 12;
                    obtain.obj = str;
                    RealTimeLocation.this.getHandler().sendMessage(obtain);
                }
            };
        }

        public void start() {
            RealTimeLocation.this.getHandler().postDelayed(this.runnable, (long) (RealTimeLocation.this.mRefreshInterval * 3));
        }

        public void stop() {
            RealTimeLocation.this.getHandler().removeCallbacks(this.runnable);
        }

        public void update() {
            RealTimeLocation.this.getHandler().removeCallbacks(this.runnable);
            RealTimeLocation.this.getHandler().postDelayed(this.runnable, (long) (RealTimeLocation.this.mRefreshInterval * 3));
        }
    }

    private class TerminalState extends State {
        private TerminalState() {
        }

        public void enter() {
            Log.d(RealTimeLocation.TAG, "terminal enter : current = " + RealTimeLocation.this.mCurrentState);
            RealTimeLocation.this.mParticipants.clear();
            RealTimeLocation.this.stopTimer();
            if (RealTimeLocation.this.mWatcher.size() > 0) {
                for (ParticipantWatcher stop : RealTimeLocation.this.mWatcher.values()) {
                    stop.stop();
                }
                RealTimeLocation.this.mWatcher.clear();
            }
            RealTimeLocation.this.getHandler().sendEmptyMessage(11);
        }

        public boolean processMessage(android.os.Message message) {
            switch (message.what) {
                case 11:
                    RealTimeLocation.this.transitionTo(RealTimeLocation.this.mIdleState);
                    break;
            }
            return true;
        }
    }

    public void addListener(RealTimeLocationListener realTimeLocationListener) {
        this.mObservers = realTimeLocationListener;
    }

    public void deleteListener(RealTimeLocationListener realTimeLocationListener) {
        this.mObservers = null;
    }

    public RealTimeLocation(Context context, ConversationType conversationType, String str) {
        boolean z = true;
        super(conversationType.getName() + str);
        Log.d(TAG, "RealTimeLocation");
        this.mConversationType = conversationType;
        this.mTargetId = str;
        this.mClient = NativeClient.getInstance();
        this.mCurrentState = RealTimeLocationStatus.RC_REAL_TIME_LOCATION_STATUS_IDLE;
        this.mParticipants = new ArrayList();
        this.mWatcher = new HashMap();
        LocationConfig locationConfig = NavigationClient.getInstance().getLocationConfig(context);
        if (locationConfig != null) {
            this.mFilterDistance = locationConfig.getDistanceFilter();
            this.mRefreshInterval = locationConfig.getRefreshInterval() * 1000;
        }
        this.mRefreshRunnable = new C53631();
        if (context.checkCallingPermission("android.permission.ACCESS_FINE_LOCATION") != 0) {
            z = false;
        }
        this.mGpsEnable = z;
        addState(this.mIdleState);
        addState(this.mIncomingState, this.mIdleState);
        addState(this.mOutgoingState, this.mIdleState);
        addState(this.mConnectedState, this.mIdleState);
        addState(this.mTerminalState, this.mIdleState);
        setInitialState(this.mIdleState);
        start();
    }

    private void startTimer() {
        getHandler().removeCallbacks(this.mRefreshRunnable);
        getHandler().postDelayed(this.mRefreshRunnable, (long) this.mRefreshInterval);
    }

    private void stopTimer() {
        getHandler().removeCallbacks(this.mRefreshRunnable);
    }

    public void updateLocation(double d, double d2) {
        this.mLatitude = d;
        this.mLongitude = d2;
    }

    public RealTimeLocationStatus getRealTimeLocationCurrentState() {
        return this.mCurrentState;
    }

    public boolean gpsIsAvailable() {
        return this.mGpsEnable;
    }

    public List<String> getParticipants() {
        return this.mParticipants;
    }

    private void gpsInit(Context context) {
        Log.d(TAG, "gpsInit");
        this.mLocationManager = (LocationManager) context.getSystemService(MapboxEvent.TYPE_LOCATION);
        if (this.mLocationManager.isProviderEnabled("gps")) {
            this.mGpsEnable = true;
            this.mLocationListener = new C53642();
            Location lastKnownLocation = this.mLocationManager.getLastKnownLocation(this.mLocationManager.getBestProvider(getCriteria(), true));
            if (lastKnownLocation != null) {
                this.mLatitude = lastKnownLocation.getLatitude();
                this.mLongitude = lastKnownLocation.getLongitude();
            }
            Log.e(TAG, "gpsInit: location = " + (lastKnownLocation != null ? "[ " + this.mLatitude + " " + this.mLongitude + " ]" : "null"));
            return;
        }
        Log.e(TAG, "GSP is disabled");
    }

    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);
        criteria.setBearingRequired(false);
        criteria.setAltitudeRequired(false);
        criteria.setPowerRequirement(1);
        return criteria;
    }

    private void updateSelfLocation() {
        onReceiveLocation(this.mLatitude, this.mLongitude, this.mSelfId);
    }

    private void sendStartMessage() {
        this.mClient.sendMessage(this.mConversationType, this.mTargetId, RealTimeLocationStartMessage.obtain("start real time location."), "收到一条位置共享消息", null, new C53653());
    }

    private void sendJoinMessage() {
        this.mClient.sendMessage(this.mConversationType, this.mTargetId, RealTimeLocationJoinMessage.obtain("join real time location."), null, null, new C53664());
    }

    private void sendQuitMessage() {
        this.mClient.sendMessage(this.mConversationType, this.mTargetId, RealTimeLocationQuitMessage.obtain("quit real time location."), null, null, null);
    }

    private void sendLocationMessage() {
        this.mClient.sendStatusMessage(this.mConversationType, this.mTargetId, RealTimeLocationStatusMessage.obtain(this.mLatitude, this.mLongitude), 1, new C53675());
    }

    private void onStatusChanged(RealTimeLocationStatus realTimeLocationStatus) {
        if (this.mObservers != null) {
            this.mObservers.onStatusChange(realTimeLocationStatus);
        }
    }

    private void onParticipantQuit(String str) {
        if (this.mObservers != null) {
            this.mObservers.onParticipantsQuit(str);
        }
    }

    private void onParticipantsJoin(String str) {
        if (this.mObservers != null) {
            this.mObservers.onParticipantsJoin(str);
        }
    }

    private void onReceiveLocation(double d, double d2, String str) {
        if (this.mObservers != null) {
            this.mObservers.onReceiveLocation(d, d2, str);
        }
    }

    private void onError(RealTimeLocationErrorCode realTimeLocationErrorCode) {
        if (this.mObservers != null) {
            this.mObservers.onError(realTimeLocationErrorCode);
        }
    }
}
