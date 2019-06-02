package com.avos.avoscloud;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.LogUtil.avlog;
import com.avos.avoscloud.LogUtil.log;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

class AnalyticsImpl implements AnalyticsRequestDispatcher {
    private static final String TAG = AnalyticsImpl.class.getSimpleName();
    static boolean analysisReportEnableFlag = true;
    protected static boolean enableDebugLog = AVOSCloud.showInternalDebugLog();
    private static final String firstBootTag = "firstBoot";
    static AnalyticsImpl instance;
    private static long sessionThreshold = 30000;
    private static final Map<String, AnalyticsSession> sessions = new ConcurrentHashMap();
    private static final List<String> whiteList = new LinkedList();
    private String appChannel;
    private boolean autoLocation;
    private String currentSessionId;
    private Map<String, String> customInfo;
    private AVUncaughtExceptionHandler handler;
    private AVOnlineConfigureListener listener;
    private AnalyticsOnlineConfig onlineConfig;
    RealTimeRequestController realTimeController;
    AnalyticsRequestController requestController;
    private volatile Timer updateOnlineConfigTimer;

    /* renamed from: com.avos.avoscloud.AnalyticsImpl$1 */
    class C09671 extends GenericObjectCallback {
        C09671() {
        }

        public void onSuccess(String str, AVException aVException) {
            if (AnalyticsImpl.enableDebugLog) {
                Log.i(AnalyticsImpl.TAG, "Save success: " + str);
            }
        }

        public void onFailure(Throwable th, String str) {
            if (AnalyticsImpl.enableDebugLog) {
                Log.i(AnalyticsImpl.TAG, "Save failed: " + str);
            }
        }
    }

    /* renamed from: com.avos.avoscloud.AnalyticsImpl$2 */
    class C09682 extends TimerTask {
        C09682() {
        }

        public void run() {
            try {
                AnalyticsImpl.this.onlineConfig.update(null, false);
            } catch (Throwable e) {
                Log.e(AnalyticsImpl.TAG, "update online config failed", e);
            }
        }
    }

    private AnalyticsImpl() {
        this.appChannel = "AVOS Cloud";
        this.handler = null;
        this.onlineConfig = null;
        this.listener = null;
        this.updateOnlineConfigTimer = null;
        this.onlineConfig = new AnalyticsOnlineConfig(this);
        this.requestController = new BatchRequestController(this.currentSessionId, this, AnalyticsUtils.getRequestInterval());
        this.realTimeController = new RealTimeRequestController(this);
    }

    public static AnalyticsImpl getInstance() {
        if (instance == null) {
            instance = new AnalyticsImpl();
        }
        return instance;
    }

    public void setAutoLocation(boolean z) {
        this.autoLocation = z;
    }

    public boolean isAutoLocation() {
        return this.autoLocation;
    }

    public boolean isEnableStats() {
        return this.onlineConfig.isEnableStats();
    }

    public void setAppChannel(String str) {
        this.appChannel = str;
    }

    public String getAppChannel() {
        return this.appChannel;
    }

    public void setEnableDebugLog(boolean z) {
        enableDebugLog = z;
    }

    public boolean isEnableDebugLog() {
        return enableDebugLog;
    }

    public void enableCrashReport(Context context, boolean z) {
        if (z && this.handler == null) {
            this.handler = new AVUncaughtExceptionHandler(context);
        }
        if (this.handler != null) {
            this.handler.enableCrashHanlder(z);
        }
    }

    public ReportPolicy getReportPolicy(Context context) {
        ReportPolicy reportPolicy = this.onlineConfig.getReportPolicy();
        if (reportPolicy == ReportPolicy.REALTIME && whiteList.contains(AVOSCloud.applicationId)) {
            return ReportPolicy.REALTIME;
        }
        if (reportPolicy == ReportPolicy.REALTIME && !AnalyticsUtils.inDebug(context)) {
            return ReportPolicy.BATCH;
        }
        if (reportPolicy != ReportPolicy.SENDWIFIONLY || AnalyticsUtils.inDebug(context)) {
            return reportPolicy;
        }
        return ReportPolicy.BATCH;
    }

    protected void setReportPolicy(ReportPolicy reportPolicy) {
        if (this.onlineConfig.setReportPolicy(reportPolicy)) {
            if (this.requestController != null) {
                this.requestController.quit();
            }
            this.requestController = AnalyticsRequestControllerFactory.getAnalyticsRequestController(this.currentSessionId, getReportPolicy(AVOSCloud.applicationContext), this);
            AnalyticsSession currentSession = getCurrentSession(false);
            if (currentSession != null && (this.requestController instanceof BatchRequestController)) {
                ((BatchRequestController) this.requestController).resetMessageCount(currentSession.getMessageCount());
            }
        }
    }

    public void notifyOnlineConfigListener(JSONObject jSONObject) {
        if (this.listener != null) {
            try {
                this.listener.onDataReceived(jSONObject);
            } catch (Throwable e) {
                Log.e(TAG, "Notify online data received failed.", e);
            }
        }
    }

    private AnalyticsSession getCurrentSession(boolean z) {
        AnalyticsSession sessionByName = sessionByName(this.currentSessionId);
        if (sessionByName != null) {
            return sessionByName;
        }
        if (!z) {
            return null;
        }
        sessionByName = createSession();
        this.currentSessionId = sessionByName.getSessionId();
        return sessionByName;
    }

    public void setSessionContinueMillis(long j) {
        sessionThreshold = j;
    }

    public void setSessionDuration(long j) {
        getCurrentSession(true).setSessionDuration(j);
    }

    static AnalyticsSession sessionByName(String str) {
        if (str == null) {
            return null;
        }
        return (AnalyticsSession) sessions.get(str);
    }

    private AnalyticsSession createSession() {
        AnalyticsSession analyticsSession = new AnalyticsSession();
        analyticsSession.beginSession();
        if (analyticsSession.getSessionId() != null) {
            sessions.put(analyticsSession.getSessionId(), analyticsSession);
        }
        return analyticsSession;
    }

    protected void flushLastSessions(Context context) {
        AnalyticsSession cachedSession = AnalyticsSessionCacheRepository.getInstance().getCachedSession();
        if (enableDebugLog && cachedSession != null) {
            avlog.m3509i("get cached sessions:" + cachedSession.getSessionId());
        }
        if (cachedSession != null) {
            sessions.put(cachedSession.getSessionId(), cachedSession);
        }
        sendInstantRecordingRequest();
    }

    public void beginSession() {
        AnalyticsSession sessionByName = sessionByName(this.currentSessionId);
        if (sessionByName == null) {
            sessionByName = createSession();
        }
        this.currentSessionId = sessionByName.getSessionId();
    }

    public void endSession(Context context) {
        AnalyticsSession sessionByName = sessionByName(this.currentSessionId);
        if (sessionByName != null) {
            sessionByName.endSession();
            postRecording();
            this.currentSessionId = null;
        }
    }

    public void pauseSession() {
        AnalyticsSession sessionByName = sessionByName(this.currentSessionId);
        if (sessionByName != null) {
            sessionByName.pauseSession();
        }
    }

    public void addActivity(String str, long j) {
        getCurrentSession(true).addActivity(str, j);
    }

    public void beginActivity(String str) {
        getCurrentSession(true).beginActivity(str);
        postRecording();
    }

    public void beginFragment(String str) {
        getCurrentSession(true).beginFragment(str);
        postRecording();
    }

    public void beginEvent(Context context, String str) {
        beginEvent(context, str, "", "");
    }

    public AnalyticsEvent beginEvent(Context context, String str, String str2, String str3) {
        AnalyticsEvent beginEvent = getCurrentSession(true).beginEvent(context, str, str2, str3);
        postRecording();
        return beginEvent;
    }

    public void endEvent(Context context, String str, String str2, String str3) {
        getCurrentSession(true).endEvent(context, str, str2, str3);
        postRecording();
    }

    public void setCustomInfo(Map<String, String> map) {
        this.customInfo = map;
    }

    public Map<String, String> getCustomInfo() {
        return this.customInfo;
    }

    private long getSessionTimeoutThreshold() {
        return sessionThreshold;
    }

    public boolean shouldRegardAsNewSession() {
        AnalyticsSession currentSession = getCurrentSession(false);
        if (currentSession == null) {
            return true;
        }
        long currentTimestamp = AnalyticsUtils.getCurrentTimestamp();
        long pausedTimeStamp = currentSession.getDuration().getPausedTimeStamp();
        if (currentTimestamp - pausedTimeStamp <= getSessionTimeoutThreshold() || pausedTimeStamp <= 0) {
            return false;
        }
        return true;
    }

    public void endActivity(String str) {
        getCurrentSession(true).endActivity(str);
        postRecording();
    }

    public void endFragment(String str) {
        getCurrentSession(true).endFragment(str);
        postRecording();
    }

    private void dumpJsonMap(Context context) {
        for (AnalyticsSession jsonMap : sessions.values()) {
            Map jsonMap2 = jsonMap.jsonMap(context, this.customInfo, false);
            if (jsonMap2 != null) {
                try {
                    log.m3514d(JSONHelper.toJsonString(jsonMap2));
                } catch (Exception e) {
                    log.m3522e(TAG, "", e);
                }
            }
        }
    }

    public synchronized void report(Context context, boolean z) {
        try {
            saveSessionsToServer(context);
            Iterator it = sessions.entrySet().iterator();
            while (it.hasNext()) {
                if (((AnalyticsSession) ((Entry) it.next()).getValue()).isSessionFinished()) {
                    it.remove();
                }
            }
            AnalyticsSession currentSession = getCurrentSession(false);
            if (this.requestController != null) {
                this.requestController.appraisalSession(currentSession);
            }
            if (z) {
                clearSessions();
            }
        } catch (Throwable e) {
            Log.e(TAG, "Send statstics report failed", e);
        }
        return;
    }

    public void debugDump(Context context) {
        if (enableDebugLog) {
            for (AnalyticsSession jsonMap : sessions.values()) {
                Log.i(TAG, "json data: " + jsonMap.jsonMap(context, this.customInfo, false));
            }
        }
    }

    public void postRecording() {
        if (AVOSCloud.showInternalDebugLog()) {
            Log.d(TAG, "report policy:" + this.onlineConfig.getReportPolicy());
        }
        if (isEnableStats()) {
            if (this.requestController != null) {
                this.requestController.requestToSend(this.currentSessionId);
            }
            getCurrentSession(false);
            archiveCurrentSession();
        }
    }

    protected void archiveCurrentSession() {
        AnalyticsSession sessionByName = sessionByName(this.currentSessionId);
        if (sessionByName != null) {
            AnalyticsSessionCacheRepository.getInstance().cacheSession(sessionByName);
        }
    }

    public void saveSessionsToServer(Context context) {
        try {
            sendArchivedRequests(true);
            for (AnalyticsSession jsonMap : sessions.values()) {
                Map jsonMap2 = jsonMap.jsonMap(context, this.customInfo, true);
                if (jsonMap2 != null) {
                    String toJSONString = JSON.toJSONString(jsonMap2);
                    if (enableDebugLog) {
                        log.m3523i(toJSONString);
                    }
                    sendAnalysisRequest(toJSONString, true, true, new C09671());
                }
            }
        } catch (Throwable e) {
            Log.e(TAG, "saveSessionsToServer failed.", e);
        }
    }

    public void clearSessions() {
        sessions.clear();
        this.currentSessionId = null;
    }

    public void setAVOnlineConfigureListener(AVOnlineConfigureListener aVOnlineConfigureListener) {
        this.listener = aVOnlineConfigureListener;
        if (aVOnlineConfigureListener != null && this.updateOnlineConfigTimer == null) {
            this.updateOnlineConfigTimer = new Timer(true);
            this.updateOnlineConfigTimer.schedule(new C09682(), 5000, 5000);
        }
    }

    protected void updateOnlineConfig(Context context) {
        try {
            if (this.onlineConfig != null) {
                if (enableDebugLog) {
                    Log.d(TAG, "try to update statistics config from online data");
                }
                this.onlineConfig.update(context);
            }
        } catch (Throwable e) {
            Log.e(TAG, "Update online config failed.", e);
        }
    }

    public void reportFirstBoot(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("AVOSCloud-SDK", 0);
        if (sharedPreferences.getBoolean(firstBootTag, true)) {
            sendInstantRecordingRequest();
            Map firstBootMap = getCurrentSession(false).firstBootMap(context, this.customInfo);
            if (firstBootMap != null) {
                if (enableDebugLog) {
                    avlog.m3506d("report data on first boot");
                }
                sendAnalysisRequest(JSON.toJSONString(firstBootMap), false, true, null);
            }
            Editor edit = sharedPreferences.edit();
            edit.putBoolean(firstBootTag, false);
            edit.commit();
        } else if (enableDebugLog) {
            avlog.m3506d("no need to first boot report");
        }
    }

    protected void sendInstantRecordingRequest() {
        this.realTimeController.requestToSend(this.currentSessionId);
    }

    protected String getConfigParams(String str, String str2) {
        String configParams = this.onlineConfig.getConfigParams(str);
        return configParams == null ? str2 : configParams;
    }

    protected static void sendAnalysisRequest(String str, boolean z, boolean z2, GenericObjectCallback genericObjectCallback) {
        if (analysisReportEnableFlag) {
            PaasClient.statistisInstance().postObject("stats/collect", str, z, z2, genericObjectCallback, null, AVUtils.md5(str));
        }
    }

    protected synchronized void setAnalyticsEnabled(boolean z) {
        analysisReportEnableFlag = z;
    }

    protected synchronized void sendArchivedRequests(boolean z) {
        if (analysisReportEnableFlag) {
            PaasClient.statistisInstance().handleAllArchivedRequest(z);
        }
    }

    public void sendRequest() {
        report(AVOSCloud.applicationContext, false);
    }
}
