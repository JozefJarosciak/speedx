package com.avos.avoscloud;

import android.content.Context;
import android.os.Handler;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alipay.sdk.cons.C0844a;
import com.avos.avoscloud.LogUtil.avlog;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class AVOSCloud {
    public static final String AV_CLOUD_API_VERSION_KEY = "AV_CLOUD_API_VERSION";
    public static final String AV_CLOUD_API_VERSION_KEY_ZONE = "AV_CLOUD_API_VERSION_KEY_ZONE";
    public static final Integer AV_CLOUD_CACHE_DEFAULT_EXPIRE_DATE = Integer.valueOf(30);
    public static final String AV_CLOUD_CACHE_EXPIRE_AUTO_CLEAN_KEY = "AV_CLOUD_CACHE_EXPIRE_AUTO_CLEAN_KEY";
    public static final String AV_CLOUD_CACHE_EXPIRE_DATE_KEY = "AV_CLOUD_CACHE_EXPIRE_DATE_KEY";
    public static final String AV_CLOUD_CACHE_EXPIRE_KEY_ZONE = "AV_CLOUD_CACHE_EXPIRE_KEY_ZONE";
    public static final int DEFAULT_NETWORK_TIMEOUT = 15000;
    public static final int DEFAULT_THREAD_POOL_SIZE = 10;
    public static final int LOG_LEVEL_DEBUG = 4;
    public static final int LOG_LEVEL_ERROR = 32;
    public static final int LOG_LEVEL_INFO = 8;
    public static final int LOG_LEVEL_NONE = -1;
    public static final int LOG_LEVEL_VERBOSE = 2;
    public static final int LOG_LEVEL_WARNING = 16;
    public static Context applicationContext;
    public static String applicationId;
    public static String clientKey;
    protected static Handler handler;
    private static boolean internalDebugLog = false;
    private static int logLevel = -1;
    private static int networkTimeoutInMills = DEFAULT_NETWORK_TIMEOUT;
    private static StorageType storageType = StorageType.StorageTypeQiniu;
    private static int threadPoolSize = 10;
    private static boolean userInternalDebugLog = false;

    /* renamed from: com.avos.avoscloud.AVOSCloud$1 */
    static class C09201 extends RequestMobileCodeCallback {
        C09201() {
        }

        public void done(AVException aVException) {
            if (aVException != null) {
                AVExceptionHolder.add(aVException);
            }
        }

        public boolean mustRunOnUIThread() {
            return false;
        }
    }

    /* renamed from: com.avos.avoscloud.AVOSCloud$2 */
    static class C09212 extends RequestMobileCodeCallback {
        C09212() {
        }

        public void done(AVException aVException) {
            if (aVException != null) {
                AVExceptionHolder.add(aVException);
            }
        }

        public boolean mustRunOnUIThread() {
            return false;
        }
    }

    /* renamed from: com.avos.avoscloud.AVOSCloud$4 */
    static class C09234 extends RequestMobileCodeCallback {
        C09234() {
        }

        public void done(AVException aVException) {
            if (aVException != null) {
                AVExceptionHolder.add(aVException);
            }
        }

        public boolean mustRunOnUIThread() {
            return false;
        }
    }

    /* renamed from: com.avos.avoscloud.AVOSCloud$5 */
    static class C09245 extends AVMobilePhoneVerifyCallback {
        C09245() {
        }

        public void done(AVException aVException) {
            if (aVException != null) {
                AVExceptionHolder.add(aVException);
            }
        }

        public boolean mustRunOnUIThread() {
            return false;
        }
    }

    /* renamed from: com.avos.avoscloud.AVOSCloud$7 */
    static class C09267 extends GetCallback<AVObject> {
        C09267() {
        }

        public void done(AVObject aVObject, AVException aVException) {
            AVUser.changeCurrentUser((AVUser) aVObject, true);
        }
    }

    public enum StorageType {
        StorageTypeQiniu,
        StorageTypeAV,
        StorageTypeS3
    }

    static {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        ParserConfig.getGlobalInstance().putDeserializer(AVObject.class, AVObjectDeserializer.instance);
        ParserConfig.getGlobalInstance().putDeserializer(AVUser.class, AVObjectDeserializer.instance);
        SerializeConfig.getGlobalInstance().put(AVObject.class, AVObjectSerializer.instance);
        SerializeConfig.getGlobalInstance().put(AVUser.class, AVObjectSerializer.instance);
        try {
            Type cls = Class.forName("com.avos.avoscloud.AVInstallation");
            ParserConfig.getGlobalInstance().putDeserializer(cls, AVObjectDeserializer.instance);
            SerializeConfig.getGlobalInstance().put(cls, AVObjectSerializer.instance);
        } catch (Exception e) {
        }
    }

    public static void setNetworkTimeout(int i) {
        networkTimeoutInMills = i;
    }

    public static int getNetworkTimeout() {
        return networkTimeoutInMills;
    }

    public static int getThreadPoolSize() {
        return threadPoolSize;
    }

    public static void setThreadPoolSize(int i) {
        threadPoolSize = i;
    }

    private AVOSCloud() {
    }

    public static void initialize(Context context, String str, String str2) {
        if (handler != null || AVUtils.isMainThread()) {
            applicationId = str;
            clientKey = str2;
            applicationContext = context;
            startAnalytics(context);
            if (handler == null) {
                handler = new Handler();
            }
            if (AVPersistenceUtils.sharedInstance().getPersistentSettingBoolean(AV_CLOUD_CACHE_EXPIRE_KEY_ZONE, AV_CLOUD_CACHE_EXPIRE_AUTO_CLEAN_KEY, Boolean.valueOf(true))) {
                AVCacheManager.clearCacheMoreThanDays(AVPersistenceUtils.sharedInstance().getPersistentSettingInteger(AV_CLOUD_CACHE_EXPIRE_KEY_ZONE, AV_CLOUD_CACHE_EXPIRE_DATE_KEY, AV_CLOUD_CACHE_DEFAULT_EXPIRE_DATE).intValue());
                AVCacheManager.clearFileCacheMoreThanDays(AVPersistenceUtils.sharedInstance().getPersistentSettingInteger(AV_CLOUD_CACHE_EXPIRE_KEY_ZONE, AV_CLOUD_CACHE_EXPIRE_DATE_KEY, AV_CLOUD_CACHE_DEFAULT_EXPIRE_DATE).intValue() * 2);
            }
            ArchiveRequestTaskController.schedule();
            onUpgrade(AVPersistenceUtils.sharedInstance().getPersistentSettingString(AV_CLOUD_API_VERSION_KEY_ZONE, AV_CLOUD_API_VERSION_KEY, C0844a.f2048d), PaasClient.storageInstance().getApiVersion());
            AVPersistenceUtils.sharedInstance().savePersistentSettingString(AV_CLOUD_API_VERSION_KEY_ZONE, AV_CLOUD_API_VERSION_KEY, PaasClient.storageInstance().getApiVersion());
            return;
        }
        throw new IllegalStateException("Please call AVOSCloud.initialize in main thread.");
    }

    public static void useAVCloudUS() {
        PaasClient.storageInstance().useAVCloudUS();
    }

    public static void useAVCloudCN() {
        PaasClient.storageInstance().useAVCloudCN();
    }

    public static int getLogLevel() {
        return logLevel;
    }

    public static void setLogLevel(int i) {
        logLevel = i;
    }

    static void showInternalDebugLog(boolean z) {
        internalDebugLog = z;
    }

    public static boolean showInternalDebugLog() {
        return internalDebugLog;
    }

    public static void setDebugLogEnabled(boolean z) {
        userInternalDebugLog = z;
    }

    public static boolean isDebugLogEnabled() {
        return userInternalDebugLog || internalDebugLog;
    }

    public static StorageType getStorageType() {
        return storageType;
    }

    public static void setStorageType(StorageType storageType) {
        storageType = storageType;
    }

    public static boolean isLastModifyEnabled() {
        return PaasClient.isLastModifyEnabled();
    }

    public static void setLastModifyEnabled(boolean z) {
        PaasClient.setLastModifyEnabled(z);
    }

    public static void clearLastModifyCache() {
        PaasClient.clearLastModifyCache();
    }

    public static void enableAutoCacheCleaner() {
        AVPersistenceUtils.sharedInstance().savePersistentSettingBoolean(AV_CLOUD_CACHE_EXPIRE_KEY_ZONE, AV_CLOUD_CACHE_EXPIRE_AUTO_CLEAN_KEY, Boolean.valueOf(true));
    }

    public static void disableAutoCacheCleaner() {
        AVPersistenceUtils.sharedInstance().savePersistentSettingBoolean(AV_CLOUD_CACHE_EXPIRE_KEY_ZONE, AV_CLOUD_CACHE_EXPIRE_AUTO_CLEAN_KEY, Boolean.valueOf(false));
    }

    public static void setCacheFileAutoExpireDate(int i) {
        AVPersistenceUtils.sharedInstance().savePersistentSettingInteger(AV_CLOUD_CACHE_EXPIRE_KEY_ZONE, AV_CLOUD_CACHE_EXPIRE_DATE_KEY, Integer.valueOf(i));
    }

    public static void setBaseUrl(String str) {
        PaasClient.storageInstance().setBaseUrl(str);
    }

    public static void requestSMSCode(String str, String str2, String str3, int i) throws AVException {
        requestSMSCodeInBackground(str, null, getSMSCodeEnv(str2, str3, i), true, new C09201());
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    private static Map<String, Object> getSMSCodeEnv(String str, String str2, int i) {
        Map<String, Object> hashMap = new HashMap();
        if (!AVUtils.isBlankString(str2)) {
            hashMap.put("op", str2);
        }
        if (!AVUtils.isBlankString(str)) {
            hashMap.put("name", str);
        }
        if (i > 0) {
            hashMap.put("ttl", Integer.valueOf(i));
        }
        return hashMap;
    }

    private static Map<String, Object> getVoiceCodeEnv(String str) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("smsType", "voice");
        if (!AVUtils.isBlankString(str)) {
            hashMap.put("IDD", str);
        }
        return hashMap;
    }

    @Deprecated
    public static void requestSMSCodeInBackgroud(String str, String str2, String str3, int i, RequestMobileCodeCallback requestMobileCodeCallback) {
        requestSMSCodeInBackground(str, null, getSMSCodeEnv(str2, str3, i), false, requestMobileCodeCallback);
    }

    public static void requestSMSCodeInBackground(String str, String str2, String str3, int i, RequestMobileCodeCallback requestMobileCodeCallback) {
        requestSMSCodeInBackground(str, null, getSMSCodeEnv(str2, str3, i), false, requestMobileCodeCallback);
    }

    public static void requestSMSCode(String str, String str2, Map<String, Object> map) throws AVException {
        requestSMSCodeInBackground(str, str2, (Map) map, true, new C09212());
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    @Deprecated
    public static void requestSMSCodeInBackgroud(String str, String str2, Map<String, Object> map, RequestMobileCodeCallback requestMobileCodeCallback) {
        requestSMSCodeInBackground(str, str2, (Map) map, false, requestMobileCodeCallback);
    }

    public static void requestSMSCodeInBackground(String str, String str2, Map<String, Object> map, RequestMobileCodeCallback requestMobileCodeCallback) {
        requestSMSCodeInBackground(str, str2, (Map) map, false, requestMobileCodeCallback);
    }

    private static void requestSMSCodeInBackground(String str, String str2, Map<String, Object> map, boolean z, final RequestMobileCodeCallback requestMobileCodeCallback) {
        if (AVUtils.isBlankString(str) || !AVUtils.checkMobilePhoneNumber(str)) {
            requestMobileCodeCallback.internalDone(new AVException(127, "Invalid Phone Number"));
        }
        if (map == null) {
            map = new HashMap();
        }
        map.put("mobilePhoneNumber", str);
        if (!AVUtils.isBlankString(str2)) {
            map.put("template", str2);
        }
        PaasClient.storageInstance().postObject("requestSmsCode", AVUtils.jsonStringFromMapWithNull(map), z, false, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                if (requestMobileCodeCallback != null) {
                    requestMobileCodeCallback.internalDone(null, null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (requestMobileCodeCallback != null) {
                    requestMobileCodeCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        }, null, null);
    }

    public static void requestSMSCode(String str) throws AVException {
        requestSMSCode(str, null, null, 0);
    }

    @Deprecated
    public static void requestSMSCodeInBackgroud(String str, RequestMobileCodeCallback requestMobileCodeCallback) {
        requestSMSCodeInBackgroud(str, null, null, 0, requestMobileCodeCallback);
    }

    public static void requestSMSCodeInBackground(String str, RequestMobileCodeCallback requestMobileCodeCallback) {
        requestSMSCodeInBackgroud(str, null, null, 0, requestMobileCodeCallback);
    }

    public static void requestVoiceCode(String str) throws AVException {
        requestVoiceCode(str, null);
    }

    private static void requestVoiceCode(String str, String str2) throws AVException {
        requestSMSCodeInBackground(str, null, getVoiceCodeEnv(str2), true, new C09234());
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public static void requestVoiceCodeInBackground(String str, RequestMobileCodeCallback requestMobileCodeCallback) {
        requestSMSCodeInBackground(str, null, getVoiceCodeEnv(null), requestMobileCodeCallback);
    }

    private static void requestVoiceCodeInBackground(String str, String str2, RequestMobileCodeCallback requestMobileCodeCallback) {
        requestSMSCodeInBackground(str, null, getVoiceCodeEnv(str2), requestMobileCodeCallback);
    }

    public static void verifySMSCode(String str, String str2) throws AVException {
        verifySMSCodeInBackground(str, str2, true, new C09245());
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public static void verifyCode(String str, String str2) throws AVException {
        verifySMSCode(str, str2);
    }

    public static void verifySMSCodeInBackground(String str, String str2, AVMobilePhoneVerifyCallback aVMobilePhoneVerifyCallback) {
        verifySMSCodeInBackground(str, str2, false, aVMobilePhoneVerifyCallback);
    }

    public static void verifyCodeInBackground(String str, String str2, AVMobilePhoneVerifyCallback aVMobilePhoneVerifyCallback) {
        verifySMSCodeInBackground(str, str2, false, aVMobilePhoneVerifyCallback);
    }

    private static void verifySMSCodeInBackground(String str, String str2, boolean z, final AVMobilePhoneVerifyCallback aVMobilePhoneVerifyCallback) {
        if (AVUtils.isBlankString(str) || !AVUtils.checkMobileVerifyCode(str)) {
            aVMobilePhoneVerifyCallback.internalDone(new AVException(127, "Invalid Verify Code"));
        }
        String format = String.format("verifySmsCode/%s", new Object[]{str});
        Map hashMap = new HashMap();
        hashMap.put("mobilePhoneNumber", str2);
        PaasClient.storageInstance().postObject(format, AVUtils.restfulServerData(hashMap), z, false, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                if (aVMobilePhoneVerifyCallback != null) {
                    aVMobilePhoneVerifyCallback.internalDone(null, null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (aVMobilePhoneVerifyCallback != null) {
                    aVMobilePhoneVerifyCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        }, null, null);
    }

    protected static void onUpgrade(String str, String str2) {
        if (!str.equals(str2) && AVUtils.compareNumberString(str2, str) && "1.1".equals(str2)) {
            if (showInternalDebugLog()) {
                avlog.m3506d("try to do some upgrade work");
            }
            AVUser currentUser = AVUser.getCurrentUser();
            if (!(currentUser == null || AVUtils.isBlankString(currentUser.getObjectId()))) {
                currentUser.fetchInBackground(new C09267());
            }
            try {
                Class cls = Class.forName("com.avos.avoscloud.AVInstallation");
                cls.getDeclaredMethod("updateCurrentInstallation", new Class[0]).invoke(cls, new Object[0]);
            } catch (Exception e) {
                avlog.m3509i("failed to update local Installation");
            }
            AVCacheManager.clearAllCache();
        }
    }

    private static void startAnalytics(Context context) {
        try {
            Class cls = Class.forName("com.avos.avoscloud.AVAnalytics");
            cls.getMethod("start", new Class[]{Context.class}).invoke(cls, new Object[]{context});
        } catch (Exception e) {
            avlog.m3509i("statistics library not started since not included");
        }
    }
}
