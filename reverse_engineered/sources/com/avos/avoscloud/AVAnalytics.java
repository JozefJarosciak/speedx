package com.avos.avoscloud;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.avos.avoscloud.LogUtil.avlog;
import com.avos.avoscloud.LogUtil.log;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.util.HashMap;
import java.util.Map;

public class AVAnalytics {
    private static final String NEW_CHANNEL_ID = "leancloud";
    private static final String OLD_CHANNEL_ID = "Channel ID";
    public static final String TAG = AVAnalytics.class.getSimpleName();
    private static String appOpen = "_appOpen";
    private static String appOpenWithPush = "_appOpenWithPush";
    private static final String defaultChannel = "AVOS Cloud";
    private static String endPoint = "statistics";
    static AnalyticsImpl impl = AnalyticsImpl.getInstance();

    /* renamed from: com.avos.avoscloud.AVAnalytics$1 */
    static class C09051 extends GenericObjectCallback {
        C09051() {
        }

        public void onSuccess(String str, AVException aVException) {
            log.m3514d(str);
        }

        public void onFailure(Throwable th, String str) {
            log.m3519e(str);
        }
    }

    public static void trackAppOpened(Intent intent) {
        onEvent(AVOSCloud.applicationContext, "!AV!AppOpen", statisticsDictionary(appOpen));
        if (intent != null && intent.getIntExtra(AVConstants.PUSH_INTENT_KEY, -1) == 1) {
            trackPushOpened(intent);
        }
    }

    @Deprecated
    public void setDefaultReportPolicy(Context context, ReportPolicy reportPolicy) {
        impl.setReportPolicy(reportPolicy);
    }

    private static void trackPushOpened(Intent intent) {
        onEvent(AVOSCloud.applicationContext, "!AV!PushOpen", statisticsDictionary(appOpenWithPush));
    }

    public static void setAppChannel(String str) {
        if (AVUtils.isBlankString(str)) {
            throw new IllegalArgumentException("Blank channel string.");
        }
        impl.setAppChannel(str);
    }

    static String getAppChannel() {
        return impl.getAppChannel();
    }

    @Deprecated
    public static void SetCustomInfo(Map<String, String> map) {
        impl.setCustomInfo(map);
    }

    public static void setCustomInfo(Map<String, String> map) {
        impl.setCustomInfo(map);
    }

    public static Map<String, String> getCustomInfo() {
        return impl.getCustomInfo();
    }

    private static Map<String, String> statisticsDictionary(String str) {
        if (AVUtils.isBlankString(str)) {
            throw new IllegalArgumentException("Blank event string.");
        }
        Map<String, String> hashMap = new HashMap();
        hashMap.put("event_id", str);
        hashMap.put(OnlineConfigAgent.KEY_CHANNEL, impl.getAppChannel());
        return hashMap;
    }

    private static void postAnalytics(Map<String, Object> map) {
        try {
            String jsonStringFromMapWithNull = AVUtils.jsonStringFromMapWithNull(map);
            PaasClient.statistisInstance().postObject(endPoint, jsonStringFromMapWithNull, false, true, new C09051(), null, AVUtils.md5(jsonStringFromMapWithNull));
        } catch (Exception e) {
            log.m3522e(TAG, "post analytics data failed.", e);
        }
    }

    public static void start(Context context) {
        String str = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                String valueOf = applicationInfo.metaData.get(OLD_CHANNEL_ID) == null ? null : String.valueOf(applicationInfo.metaData.get(OLD_CHANNEL_ID));
                if (applicationInfo.metaData.get(NEW_CHANNEL_ID) != null) {
                    str = String.valueOf(applicationInfo.metaData.get(NEW_CHANNEL_ID));
                }
                if (!AVUtils.isBlankString(valueOf)) {
                    impl.setAppChannel(valueOf);
                } else if (AVUtils.isBlankString(str)) {
                    impl.setAppChannel(defaultChannel);
                } else {
                    impl.setAppChannel(str);
                }
            }
            impl.enableCrashReport(context, true);
            impl.flushLastSessions(context);
            impl.updateOnlineConfig(context);
            impl.beginSession();
            impl.reportFirstBoot(context);
        } catch (Exception e) {
            log.m3522e(TAG, "Start context failed.", e);
        }
    }

    public static void onFragmentStart(String str) {
        if (AVUtils.isBlankString(str)) {
            throw new IllegalArgumentException("Blank page name string.");
        }
        impl.beginFragment(str);
    }

    public static void onFragmentEnd(String str) {
        if (AVUtils.isBlankString(str)) {
            throw new IllegalArgumentException("Blank page name string.");
        }
        impl.endFragment(str);
    }

    public static void setAutoLocation(boolean z) {
        impl.setAutoLocation(z);
    }

    public static void setSessionContinueMillis(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("Invalid session continute milliseconds.");
        }
        impl.setSessionContinueMillis(j);
    }

    public static void setDebugMode(boolean z) {
        impl.setEnableDebugLog(z);
    }

    public static void enableCrashReport(Context context, boolean z) {
        impl.enableCrashReport(context, z);
    }

    public static void onPause(Context context) {
        impl.endActivity(context.getClass().getSimpleName());
        impl.pauseSession();
    }

    public static void onResume(Context context) {
        onResume(context, "", "");
    }

    private static void onResume(Context context, String str, String str2) {
        String simpleName = context.getClass().getSimpleName();
        if (impl.shouldRegardAsNewSession()) {
            impl.endSession(context);
            impl.beginSession();
            avlog.m3506d("new session start when resume");
        }
        impl.beginActivity(simpleName);
    }

    public static void onError(Context context) {
    }

    public static void onError(Context context, String str) {
    }

    public static void reportError(Context context, String str) {
    }

    public static void reportError(Context context, Throwable th) {
    }

    static void reportError(Context context, Map<String, Object> map, final SaveCallback saveCallback) {
        Map deviceInfo = AnalyticsUtils.deviceInfo(context);
        deviceInfo.putAll(map);
        String toJSONString = JSON.toJSONString(deviceInfo);
        PaasClient.statistisInstance().postObject("stats/crash", toJSONString, false, true, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                if (AVAnalytics.impl.isEnableDebugLog()) {
                    Log.i(AVAnalytics.TAG, "Save success: " + str);
                }
                if (saveCallback != null) {
                    saveCallback.internalDone(null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (AVAnalytics.impl.isEnableDebugLog()) {
                    Log.i(AVAnalytics.TAG, "Save failed: " + str);
                }
                if (saveCallback != null) {
                    saveCallback.internalDone(AVErrorUtils.createException(th, str));
                }
            }
        }, null, AVUtils.md5(toJSONString));
    }

    public static void flush(Context context) {
        impl.sendInstantRecordingRequest();
    }

    protected static void debugDump(Context context) {
        impl.debugDump(context);
    }

    public static void onEvent(Context context, String str) {
        onEvent(context, str, 1);
    }

    public static void onEvent(Context context, String str, int i) {
        onEvent(context, str, "", i);
    }

    public static void onEvent(Context context, String str, String str2) {
        onEvent(context, str, str2, 1);
    }

    public static void onEvent(Context context, String str, String str2, int i) {
        AnalyticsEvent beginEvent = impl.beginEvent(context, str, str2, "");
        beginEvent.setDurationValue(0);
        beginEvent.setAccumulation(i);
        impl.endEvent(context, str, str2, "");
    }

    public static void onEvent(Context context, String str, Map<String, String> map) {
        impl.beginEvent(context, str, "", "").addAttributes(map);
        impl.endEvent(context, str, "", "");
    }

    public static void onEventDuration(Context context, String str, long j) {
        onEventDuration(context, str, "", j);
    }

    public static void onEventDuration(Context context, String str, String str2, long j) {
        onEventDuration(context, str, str2, null, j);
    }

    public static void onEventDuration(Context context, String str, Map<String, String> map, long j) {
        onEventDuration(context, str, "", map, j);
    }

    private static void onEventDuration(Context context, String str, String str2, Map<String, String> map, long j) {
        AnalyticsEvent beginEvent = impl.beginEvent(context, str, str2, "");
        beginEvent.addAttributes(map);
        beginEvent.setDurationValue(j);
        impl.endEvent(context, str, str2, "");
    }

    public static void onEventBegin(Context context, String str) {
        onEventBegin(context, str, "");
    }

    public static void onEventEnd(Context context, String str) {
        impl.endEvent(context, str, "", "");
    }

    public static void onEventBegin(Context context, String str, String str2) {
        impl.beginEvent(context, str, str2, "");
    }

    public static void onEventEnd(Context context, String str, String str2) {
        impl.endEvent(context, str, str2, "");
    }

    public static void onKVEventBegin(Context context, String str, HashMap<String, String> hashMap, String str2) {
        impl.beginEvent(context, str, "", str2).setPrimaryKey(str2);
    }

    public static void onKVEventEnd(Context context, String str, String str2) {
        impl.endEvent(context, str, "", str2);
    }

    public static String getConfigParams(Context context, String str) {
        return getConfigParams(context, str, "");
    }

    public static String getConfigParams(Context context, String str, String str2) {
        return impl.getConfigParams(str, str2);
    }

    public static void updateOnlineConfig(Context context) {
        impl.updateOnlineConfig(context);
    }

    public void setGender(Context context, String str) {
    }

    public void setAge(Context context, int i) {
    }

    public void setUserID(Context context, String str, String str2) {
    }

    public static void onKillProcess(Context context) {
    }

    @Deprecated
    public static void setReportPolicy(Context context, ReportPolicy reportPolicy) {
        if (reportPolicy == null) {
            throw new IllegalArgumentException("Null report policy.");
        }
        impl.setReportPolicy(reportPolicy);
    }

    public static void setOnlineConfigureListener(AVOnlineConfigureListener aVOnlineConfigureListener) {
        if (aVOnlineConfigureListener == null) {
            throw new IllegalArgumentException("Null AVOnlineConfigureListener.");
        }
        impl.setAVOnlineConfigureListener(aVOnlineConfigureListener);
    }

    public static void setAnalyticsEnabled(boolean z) {
        impl.setAnalyticsEnabled(z);
    }
}
