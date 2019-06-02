package io.rong.imlib.statistics;

import android.content.Context;
import android.util.Log;
import io.rong.imlib.statistics.DeviceId.Type;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import org.json.JSONObject;

public class Statistics {
    public static final String COUNTLY_SDK_VERSION_STRING = "15.06";
    public static final String DEFAULT_APP_VERSION = "1.0";
    private static final int EVENT_QUEUE_SIZE_THRESHOLD = 10;
    public static final String TAG = "Statistics";
    private static final long TIMER_DELAY_IN_SECONDS = 3600;
    protected static List<String> publicKeyPinCertificates;
    private int activityCount_;
    private ConnectionQueue connectionQueue_ = new ConnectionQueue();
    private Context context_;
    private DeviceId deviceId_Manager_;
    private boolean disableUpdateSessionRequests_;
    private boolean enableLogging_;
    private EventQueue eventQueue_;
    private CountlyMessagingMode messagingMode_;
    private long prevSessionDurationStartTime_;
    private ScheduledExecutorService timerService_;

    public enum CountlyMessagingMode {
        TEST,
        PRODUCTION
    }

    private static class SingletonHolder {
        static final Statistics instance = new Statistics();

        private SingletonHolder() {
        }
    }

    public static Statistics sharedInstance() {
        return SingletonHolder.instance;
    }

    Statistics() {
    }

    public Statistics init(Context context, String str, String str2) {
        return init(context, str, str2, null, OpenUDIDAdapter.isOpenUDIDAvailable() ? Type.OPEN_UDID : Type.ADVERTISING_ID);
    }

    public Statistics init(Context context, String str, String str2, String str3) {
        return init(context, str, str2, str3, null);
    }

    public synchronized Statistics init(Context context, String str, String str2, String str3, Type type) {
        if (context == null) {
            throw new IllegalArgumentException("valid context is required");
        } else if (!isValidURL(str)) {
            throw new IllegalArgumentException("valid serverURL is required");
        } else if (str2 == null || str2.length() == 0) {
            throw new IllegalArgumentException("valid appKey is required");
        } else if (str3 == null || str3.length() != 0) {
            if (str3 == null && type == null) {
                if (OpenUDIDAdapter.isOpenUDIDAvailable()) {
                    type = Type.OPEN_UDID;
                } else if (AdvertisingIdAdapter.isAdvertisingIdAvailable()) {
                    type = Type.ADVERTISING_ID;
                }
            }
            if (str3 == null && type == Type.OPEN_UDID && !OpenUDIDAdapter.isOpenUDIDAvailable()) {
                throw new IllegalArgumentException("valid deviceID is required because OpenUDID is not available");
            } else if (str3 == null && type == Type.ADVERTISING_ID && !AdvertisingIdAdapter.isAdvertisingIdAvailable()) {
                throw new IllegalArgumentException("valid deviceID is required because Advertising ID is not available (you need to include Google Play services 4.0+ into your project)");
            } else if (this.eventQueue_ == null || (this.connectionQueue_.getServerURL().equals(str) && this.connectionQueue_.getAppKey().equals(str2) && DeviceId.deviceIDEqualsNullSafe(str3, type, this.connectionQueue_.getDeviceId()))) {
                if (MessagingAdapter.isMessagingAvailable()) {
                    MessagingAdapter.storeConfiguration(context, str, str2, str3, type);
                }
                if (this.eventQueue_ == null) {
                    DeviceId deviceId;
                    if (str3 != null) {
                        deviceId = new DeviceId(str3);
                    } else {
                        deviceId = new DeviceId(type);
                    }
                    StatisticsStore statisticsStore = new StatisticsStore(context);
                    deviceId.init(context, statisticsStore, true);
                    this.connectionQueue_.setServerURL(str);
                    this.connectionQueue_.setAppKey(str2);
                    this.connectionQueue_.setCountlyStore(statisticsStore);
                    this.connectionQueue_.setDeviceId(deviceId);
                    this.eventQueue_ = new EventQueue(statisticsStore);
                }
                this.context_ = context;
                this.connectionQueue_.setContext(context);
            } else {
                throw new IllegalStateException("Statistics cannot be reinitialized with different values");
            }
        } else {
            throw new IllegalArgumentException("valid deviceID is required");
        }
        return this;
    }

    public synchronized boolean isInitialized() {
        return this.eventQueue_ != null;
    }

    public synchronized void halt() {
        this.eventQueue_ = null;
        StatisticsStore countlyStore = this.connectionQueue_.getCountlyStore();
        if (countlyStore != null) {
            countlyStore.clear();
        }
        this.connectionQueue_.setContext(null);
        this.connectionQueue_.setServerURL(null);
        this.connectionQueue_.setAppKey(null);
        this.connectionQueue_.setCountlyStore(null);
        this.prevSessionDurationStartTime_ = 0;
        this.activityCount_ = 0;
    }

    public synchronized void onStart() {
        if (this.eventQueue_ == null) {
            throw new IllegalStateException("init must be called before onStart");
        }
        this.activityCount_++;
        if (this.activityCount_ == 1) {
            onStartHelper();
        }
        String referrer = ReferrerReceiver.getReferrer(this.context_);
        if (sharedInstance().isLoggingEnabled()) {
            Log.d("Statistics", "Checking referrer: " + referrer);
        }
        if (referrer != null) {
            this.connectionQueue_.sendReferrerData(referrer);
            ReferrerReceiver.deleteReferrer(this.context_);
        }
        CrashDetails.inForeground();
    }

    void onStartHelper() {
        this.prevSessionDurationStartTime_ = System.nanoTime();
        this.connectionQueue_.beginSession();
    }

    public synchronized void onStop() {
        if (this.eventQueue_ == null) {
            throw new IllegalStateException("init must be called before onStop");
        } else if (this.activityCount_ == 0) {
            throw new IllegalStateException("must call onStart before onStop");
        } else {
            this.activityCount_--;
            if (this.activityCount_ == 0) {
                onStopHelper();
            }
            CrashDetails.inBackground();
        }
    }

    void onStopHelper() {
        this.connectionQueue_.endSession(roundedSecondsSinceLastSessionDurationUpdate());
        this.prevSessionDurationStartTime_ = 0;
        if (this.eventQueue_.size() > 0) {
            this.connectionQueue_.recordEvents(this.eventQueue_.events());
        }
    }

    public void onRegistrationId(String str) {
        this.connectionQueue_.tokenSession(str, this.messagingMode_);
    }

    public void recordEvent(String str) {
        recordEvent(str, null, 1, 0.0d);
    }

    public void recordEvent(String str, int i) {
        recordEvent(str, null, i, 0.0d);
    }

    public void recordEvent(String str, int i, double d) {
        recordEvent(str, null, i, d);
    }

    public void recordEvent(String str, Map<String, String> map, int i) {
        recordEvent(str, map, i, 0.0d);
    }

    public synchronized void recordEvent(String str, Map<String, String> map, int i, double d) {
        if (isInitialized()) {
            if (str != null) {
                if (str.length() != 0) {
                    if (i < 1) {
                        throw new IllegalArgumentException("Statistics event count should be greater than zero");
                    }
                    if (map != null) {
                        for (String str2 : map.keySet()) {
                            if (str2 == null || str2.length() == 0) {
                                throw new IllegalArgumentException("Statistics event segmentation key cannot be null or empty");
                            }
                            if (map.get(str2) != null) {
                                if (((String) map.get(str2)).length() == 0) {
                                }
                            }
                            throw new IllegalArgumentException("Statistics event segmentation value cannot be null or empty");
                        }
                    }
                    this.eventQueue_.recordEvent(str, map, i, d);
                    sendEventsIfNeeded();
                }
            }
            throw new IllegalArgumentException("Valid Statistics event key is required");
        }
        throw new IllegalStateException("Statistics.sharedInstance().init must be called before recordEvent");
    }

    public synchronized void recordEvent(String str, Map<String, String> map) {
        String[] strArr = new String[(map.size() * 2)];
        if (isInitialized()) {
            if (str != null) {
                if (str.length() != 0) {
                    String encode;
                    if (map != null) {
                        int i = 0;
                        for (String encode2 : map.keySet()) {
                            if (encode2 == null || encode2.length() == 0) {
                                throw new IllegalArgumentException("Countly event segmentation key cannot be null or empty");
                            } else if (map.get(encode2) == null || ((String) map.get(encode2)).length() == 0) {
                                throw new IllegalArgumentException("Countly event segmentation value cannot be null or empty");
                            } else {
                                strArr[i] = encode2;
                                int i2 = i + 1;
                                strArr[i2] = (String) map.get(encode2);
                                i = i2 + 1;
                            }
                        }
                    }
                    JSONObject jSONObject = new JSONObject();
                    DeviceInfo.fillJSONIfValuesNotEmpty(jSONObject, strArr);
                    try {
                        encode2 = URLEncoder.encode(jSONObject.toString(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                    }
                    this.connectionQueue_.recordEvents(str, encode2);
                }
            }
            throw new IllegalArgumentException("Valid Countly event key is required");
        }
        throw new IllegalStateException("Countly.sharedInstance().init must be called before recordEvent");
    }

    public synchronized Statistics setUserData(Map<String, String> map) {
        return setUserData(map, null);
    }

    public synchronized Statistics setUserData(Map<String, String> map, Map<String, String> map2) {
        UserData.setData(map);
        if (map2 != null) {
            UserData.setCustomData(map2);
        }
        this.connectionQueue_.sendUserData();
        return this;
    }

    public synchronized Statistics setCustomUserData(Map<String, String> map) {
        if (map != null) {
            UserData.setCustomData(map);
        }
        this.connectionQueue_.sendUserData();
        return this;
    }

    public synchronized Statistics setLocation(double d, double d2) {
        this.connectionQueue_.getCountlyStore().setLocation(d, d2);
        if (this.disableUpdateSessionRequests_) {
            this.connectionQueue_.updateSession(roundedSecondsSinceLastSessionDurationUpdate());
        }
        return this;
    }

    public synchronized Statistics setCustomCrashSegments(Map<String, String> map) {
        if (map != null) {
            CrashDetails.setCustomSegments(map);
        }
        return this;
    }

    public synchronized Statistics addCrashLog(String str) {
        CrashDetails.addLog(str);
        return this;
    }

    public synchronized Statistics logException(Exception exception) {
        Writer stringWriter = new StringWriter();
        exception.printStackTrace(new PrintWriter(stringWriter));
        this.connectionQueue_.sendCrashReport(stringWriter.toString(), true);
        return this;
    }

    public synchronized Statistics enableCrashReporting() {
        final UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                Writer stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                Statistics.this.connectionQueue_.sendCrashReport(stringWriter.toString(), false);
                if (defaultUncaughtExceptionHandler != null) {
                    defaultUncaughtExceptionHandler.uncaughtException(thread, th);
                }
            }
        });
        return this;
    }

    public synchronized Statistics setDisableUpdateSessionRequests(boolean z) {
        this.disableUpdateSessionRequests_ = z;
        return this;
    }

    public synchronized Statistics setLoggingEnabled(boolean z) {
        this.enableLogging_ = z;
        return this;
    }

    public synchronized boolean isLoggingEnabled() {
        return this.enableLogging_;
    }

    void sendEventsIfNeeded() {
        Log.d("Statistics", "sendEventsIfNeeded: queue=" + this.eventQueue_.size());
        if (this.eventQueue_.size() >= 10) {
            this.connectionQueue_.recordEvents(this.eventQueue_.events());
        }
    }

    synchronized void onTimer() {
        boolean z = true;
        synchronized (this) {
            if (this.activityCount_ > 0) {
                String str = "Statistics";
                StringBuilder append = new StringBuilder().append("onTimer: update=");
                if (this.disableUpdateSessionRequests_) {
                    z = false;
                }
                Log.d(str, append.append(z).append(", queue=").append(this.eventQueue_.size()).toString());
                if (!this.disableUpdateSessionRequests_) {
                    this.connectionQueue_.updateSession(roundedSecondsSinceLastSessionDurationUpdate());
                }
                if (this.eventQueue_.size() > 0) {
                    this.connectionQueue_.recordEvents(this.eventQueue_.events());
                }
            }
        }
    }

    int roundedSecondsSinceLastSessionDurationUpdate() {
        long nanoTime = System.nanoTime();
        long j = nanoTime - this.prevSessionDurationStartTime_;
        this.prevSessionDurationStartTime_ = nanoTime;
        return (int) Math.round(((double) j) / 1.0E9d);
    }

    static int currentTimestamp() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    static boolean isValidURL(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        try {
            URL url = new URL(str);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    public static Statistics enablePublicKeyPinning(List<String> list) {
        publicKeyPinCertificates = list;
        return sharedInstance();
    }

    ConnectionQueue getConnectionQueue() {
        return this.connectionQueue_;
    }

    void setConnectionQueue(ConnectionQueue connectionQueue) {
        this.connectionQueue_ = connectionQueue;
    }

    ExecutorService getTimerService() {
        return this.timerService_;
    }

    EventQueue getEventQueue() {
        return this.eventQueue_;
    }

    void setEventQueue(EventQueue eventQueue) {
        this.eventQueue_ = eventQueue;
    }

    long getPrevSessionDurationStartTime() {
        return this.prevSessionDurationStartTime_;
    }

    void setPrevSessionDurationStartTime(long j) {
        this.prevSessionDurationStartTime_ = j;
    }

    int getActivityCount() {
        return this.activityCount_;
    }

    boolean getDisableUpdateSessionRequests() {
        return this.disableUpdateSessionRequests_;
    }
}
