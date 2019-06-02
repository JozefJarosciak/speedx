package com.avos.avoscloud;

import android.annotation.SuppressLint;
import com.alibaba.fastjson.JSON;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.packet.C0861d;
import com.avos.avoscloud.AVOSCloud.StorageType;
import com.avos.avoscloud.AVQuery.CachePolicy;
import com.avos.avoscloud.LogUtil.avlog;
import com.avos.avoscloud.LogUtil.log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;
import com.loopj.android.http.SyncHttpClient;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.CoreConnectionPNames;

public class PaasClient {
    private static final CookieStore COOKIE_STORE = new ForgotCookieStore();
    public static final String DEFAULT_FAIL_STRING = "request failed!!!";
    static String apiKeyField = null;
    static String applicationIdField = null;
    private static final String defaultContentType = "application/json";
    private static final String defaultEncoding = "UTF-8";
    private static Comparator<File> fileModifiedDateComparator = new Comparator<File>() {
        public int compare(File file, File file2) {
            return (int) (file.lastModified() - file2.lastModified());
        }
    };
    static Map<String, AVObjectReferenceCount> internalObjectsForEventuallySave = Collections.synchronizedMap(new HashMap());
    private static boolean isCN = true;
    private static boolean isUrulu = true;
    private static Map<String, String> lastModify = Collections.synchronizedMap(new WeakHashMap());
    private static boolean lastModifyEnabled = false;
    public static final String sdkVersion = "v3.4.1";
    static HashMap<String, PaasClient> serviceClientMap = new HashMap();
    static Map<String, String> serviceHostMap = Collections.synchronizedMap(new HashMap());
    public static String sessionTokenField = null;
    private static final String userAgent = "AVOS Cloud android-v3.4.1 SDK";
    private final String apiVersion = "1.1";
    private AsyncHttpClient asyncHttpClient;
    private String baseUrl;
    private AVUser currentUser = null;
    private AVACL defaultACL;
    private boolean isProduction = true;
    private SyncHttpClient syncHttpClient;

    /* renamed from: com.avos.avoscloud.PaasClient$1 */
    class C09791 extends SyncHttpClient {
        C09791() {
        }

        public String onRequestFailed(Throwable th, String str) {
            return str != null ? str : th.getMessage();
        }
    }

    private static final class ForgotCookieStore implements CookieStore {
        private ForgotCookieStore() {
        }

        public List<Cookie> getCookies() {
            return Collections.emptyList();
        }

        public boolean clearExpired(Date date) {
            return true;
        }

        public void clear() {
        }

        public void addCookie(Cookie cookie) {
        }
    }

    static {
        serviceHostMap.put(AVOSServices.STORAGE_SERVICE.toString(), "https://api.leancloud.cn");
    }

    void setProduction(boolean z) {
        this.isProduction = z;
    }

    public static boolean isAVOSCloud() {
        return isUrulu;
    }

    protected static PaasClient sharedInstance(AVOSServices aVOSServices) {
        String str = AVUtils.isBlankString((String) serviceHostMap.get(aVOSServices.toString())) ? (String) serviceHostMap.get(AVOSServices.STORAGE_SERVICE.toString()) : (String) serviceHostMap.get(aVOSServices.toString());
        PaasClient paasClient = (PaasClient) serviceClientMap.get(str);
        if (paasClient != null) {
            return paasClient;
        }
        paasClient = new PaasClient();
        paasClient.setBaseUrl(str);
        serviceClientMap.put(str, paasClient);
        return paasClient;
    }

    public static PaasClient storageInstance() {
        return sharedInstance(AVOSServices.STORAGE_SERVICE);
    }

    public static PaasClient cloudInstance() {
        return sharedInstance(AVOSServices.FUNCTION_SERVICE);
    }

    public static PaasClient statistisInstance() {
        return sharedInstance(AVOSServices.STATISTICS_SERVICE);
    }

    AVACL getDefaultACL() {
        return this.defaultACL;
    }

    void setDefaultACL(AVACL avacl) {
        this.defaultACL = avacl;
    }

    AVUser getCurrentUser() {
        return this.currentUser;
    }

    public Map<String, String> userHeaderMap() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            return currentUser.headerMap();
        }
        return null;
    }

    void setCurrentUser(AVUser aVUser) {
        this.currentUser = aVUser;
    }

    private PaasClient() {
        if (isUrulu) {
            useUruluServer();
        } else {
            useOfficalParseServer();
        }
    }

    private String signRequest() {
        StringBuilder stringBuilder = new StringBuilder();
        long currentTimestamp = AVUtils.getCurrentTimestamp();
        StringBuilder stringBuilder2 = new StringBuilder();
        stringBuilder2.append(AVUtils.md5(stringBuilder.append(currentTimestamp).append(AVOSCloud.clientKey).toString()).toLowerCase());
        return stringBuilder2.append(",").append(currentTimestamp).toString();
    }

    private void updateHeaders(AsyncHttpClient asyncHttpClient) {
        asyncHttpClient.addHeader("X-Uluru-Application-Production", this.isProduction ? C0844a.f2048d : "0");
        AVUser currentUser = AVUser.getCurrentUser();
        String str = sessionTokenField;
        String sessionToken = (currentUser == null || currentUser.getSessionToken() == null) ? "" : currentUser.getSessionToken();
        asyncHttpClient.addHeader(str, sessionToken);
        asyncHttpClient.addHeader(applicationIdField, AVOSCloud.applicationId);
        asyncHttpClient.addHeader("Accept", "application/json");
        asyncHttpClient.addHeader("Content-Type", "application/json");
        asyncHttpClient.setUserAgent(userAgent);
        asyncHttpClient.addHeader("x-avoscloud-request-sign", signRequest());
    }

    private void configHttpClient(AsyncHttpClient asyncHttpClient) {
        asyncHttpClient.getHttpClient().getParams().setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, true);
        asyncHttpClient.setTimeout(AVOSCloud.getNetworkTimeout());
        asyncHttpClient.setCookieStore(COOKIE_STORE);
        asyncHttpClient.setThreadPool(Executors.newFixedThreadPool(AVOSCloud.getThreadPoolSize()));
    }

    public synchronized AsyncHttpClient clientInstance(boolean z) {
        AsyncHttpClient asyncHttpClient;
        if (z) {
            if (this.syncHttpClient == null) {
                this.syncHttpClient = new C09791();
                configHttpClient(this.syncHttpClient);
            }
            updateHeaders(this.syncHttpClient);
            asyncHttpClient = this.syncHttpClient;
        } else {
            if (this.asyncHttpClient == null) {
                this.asyncHttpClient = new AsyncHttpClient();
                configHttpClient(this.asyncHttpClient);
            }
            updateHeaders(this.asyncHttpClient);
            asyncHttpClient = this.asyncHttpClient;
        }
        return asyncHttpClient;
    }

    public void useUruluServer() {
        if (isCN) {
            useAVCloudCN();
        } else {
            useAVCloudUS();
        }
    }

    public void useOfficalParseServer() {
        isUrulu = false;
        this.baseUrl = "https://api.parse.com";
        applicationIdField = "X-AVOSCloud-Application-Id";
        apiKeyField = "X-AVOSCloud-REST-API-Key";
        sessionTokenField = "X-AVOSCloud-Session-Token";
        AVOSCloud.setStorageType(StorageType.StorageTypeAV);
    }

    public void useAVCloudUS() {
        isUrulu = true;
        isCN = false;
        this.baseUrl = "https://us-api.leancloud.cn";
        serviceHostMap.put(AVOSServices.STORAGE_SERVICE.toString(), this.baseUrl);
        applicationIdField = "X-avoscloud-Application-Id";
        apiKeyField = "X-avoscloud-Application-Key";
        sessionTokenField = "X-avoscloud-Session-Token";
        AVOSCloud.setStorageType(StorageType.StorageTypeS3);
        switchPushRouter("useAVOSCloudUS");
    }

    private void switchPushRouter(String str) {
        try {
            Class cls = Class.forName("com.avos.avospush.push.AVPushRouter");
            cls.getMethod(str, new Class[0]).invoke(cls, new Object[0]);
        } catch (Exception e) {
            avlog.m3509i("avpushRouter server didn't switched");
        }
    }

    public void useAVCloudCN() {
        isUrulu = true;
        this.baseUrl = "https://api.leancloud.cn";
        serviceHostMap.put(AVOSServices.STORAGE_SERVICE.toString(), this.baseUrl);
        applicationIdField = "X-avoscloud-Application-Id";
        apiKeyField = "X-avoscloud-Application-Key";
        sessionTokenField = "X-avoscloud-Session-Token";
        AVOSCloud.setStorageType(StorageType.StorageTypeQiniu);
        switchPushRouter("useAVOSCloudCN");
    }

    public void useLocalStg() {
        isUrulu = true;
        this.baseUrl = "https://cn-stg1.avoscloud.com";
        serviceHostMap.put(AVOSServices.STORAGE_SERVICE.toString(), this.baseUrl);
        applicationIdField = "X-avoscloud-Application-Id";
        apiKeyField = "X-avoscloud-Application-Key";
        sessionTokenField = "X-avoscloud-Session-Token";
        AVOSCloud.setStorageType(StorageType.StorageTypeQiniu);
    }

    public String buildUrl(String str) {
        return String.format("%s/%s/%s", new Object[]{this.baseUrl, this.apiVersion, str});
    }

    private String batchUrl() {
        return String.format("%s/%s/batch", new Object[]{this.baseUrl, this.apiVersion});
    }

    private String batchSaveRelativeUrl() {
        return "batch/save";
    }

    private AsyncHttpResponseHandler createGetHandler(GenericObjectCallback genericObjectCallback, CachePolicy cachePolicy, String str) {
        return new GetHttpResponseHandler(genericObjectCallback, cachePolicy, str);
    }

    private AsyncHttpResponseHandler createPostHandler(GenericObjectCallback genericObjectCallback) {
        return new PostHttpResponseHandler(genericObjectCallback);
    }

    public String getApiVersion() {
        return this.apiVersion;
    }

    public void setBaseUrl(String str) {
        this.baseUrl = str;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    protected static void setServiceHost(AVOSServices aVOSServices, String str) {
        serviceHostMap.put(aVOSServices.toString(), str);
    }

    public String getObject(String str, RequestParams requestParams, boolean z, Map<String, String> map, GenericObjectCallback genericObjectCallback, CachePolicy cachePolicy, long j) {
        String generateQueryPath = generateQueryPath(str, requestParams);
        String updateHeaderForPath = updateHeaderForPath(str, requestParams, map);
        final GenericObjectCallback genericObjectCallback2;
        final String str2;
        final RequestParams requestParams2;
        final boolean z2;
        final Map<String, String> map2;
        final CachePolicy cachePolicy2;
        switch (cachePolicy) {
            case CACHE_ONLY:
                AVCacheManager.sharedInstance().get(generateQueryPath, j, updateHeaderForPath, genericObjectCallback);
                break;
            case NETWORK_ONLY:
                getObject(str, requestParams, z, map, genericObjectCallback, cachePolicy);
                break;
            case CACHE_ELSE_NETWORK:
                genericObjectCallback2 = genericObjectCallback;
                str2 = str;
                requestParams2 = requestParams;
                z2 = z;
                map2 = map;
                cachePolicy2 = cachePolicy;
                AVCacheManager.sharedInstance().get(generateQueryPath, j, updateHeaderForPath, new GenericObjectCallback() {
                    public void onSuccess(String str, AVException aVException) {
                        genericObjectCallback2.onSuccess(str, aVException);
                    }

                    public void onFailure(Throwable th, String str) {
                        PaasClient.this.getObject(str2, requestParams2, z2, map2, genericObjectCallback2, cachePolicy2);
                    }
                });
                break;
            case NETWORK_ELSE_CACHE:
                final GenericObjectCallback genericObjectCallback3 = genericObjectCallback;
                final String str3 = generateQueryPath;
                final long j2 = j;
                final String str4 = updateHeaderForPath;
                getObject(str, requestParams, z, map, new GenericObjectCallback() {
                    public void onSuccess(String str, AVException aVException) {
                        genericObjectCallback3.onSuccess(str, aVException);
                    }

                    public void onFailure(Throwable th, String str) {
                        AVCacheManager.sharedInstance().get(str3, j2, str4, genericObjectCallback3);
                    }
                }, cachePolicy);
                break;
            case CACHE_THEN_NETWORK:
                genericObjectCallback2 = genericObjectCallback;
                str2 = str;
                requestParams2 = requestParams;
                z2 = z;
                map2 = map;
                cachePolicy2 = cachePolicy;
                AVCacheManager.sharedInstance().get(generateQueryPath, j, updateHeaderForPath, new GenericObjectCallback() {
                    public void onSuccess(String str, AVException aVException) {
                        genericObjectCallback2.onSuccess(str, aVException);
                        PaasClient.this.getObject(str2, requestParams2, z2, map2, genericObjectCallback2, cachePolicy2);
                    }

                    public void onFailure(Throwable th, String str) {
                        genericObjectCallback2.onFailure(th, str);
                        PaasClient.this.getObject(str2, requestParams2, z2, map2, genericObjectCallback2, cachePolicy2);
                    }
                });
                break;
            default:
                getObject(str, requestParams, z, map, genericObjectCallback, cachePolicy);
                break;
        }
        return generateQueryPath;
    }

    String generateQueryPath(String str, RequestParams requestParams) {
        return AsyncHttpClient.getUrlWithQueryString(true, buildUrl(str), requestParams);
    }

    public void getObject(String str, RequestParams requestParams, boolean z, Map<String, String> map, GenericObjectCallback genericObjectCallback, CachePolicy cachePolicy) {
        boolean z2 = !cachePolicy.equals(CachePolicy.CACHE_ONLY) && isLastModifyEnabled();
        getObject(str, requestParams, z, (Map) map, genericObjectCallback, cachePolicy, z2);
    }

    public void getObject(String str, RequestParams requestParams, boolean z, Map<String, String> map, GenericObjectCallback genericObjectCallback, CachePolicy cachePolicy, boolean z2) {
        GenericObjectCallback dataFetchCallbackWithFailureRetry;
        if (z2) {
            dataFetchCallbackWithFailureRetry = new DataFetchCallbackWithFailureRetry(str, requestParams, z, null, genericObjectCallback, cachePolicy);
        } else {
            dataFetchCallbackWithFailureRetry = genericObjectCallback;
        }
        getObject(str, requestParams, z, map, dataFetchCallbackWithFailureRetry, cachePolicy, z2, true);
    }

    protected void getObject(String str, RequestParams requestParams, boolean z, Map<String, String> map, GenericObjectCallback genericObjectCallback, CachePolicy cachePolicy, boolean z2, boolean z3) {
        Map hashMap;
        GenericObjectCallback c09835;
        if (map == null) {
            hashMap = new HashMap();
        } else {
            Map<String, String> map2 = map;
        }
        updateHeaderForPath(str, requestParams, hashMap);
        String buildUrl = buildUrl(str);
        if (z3) {
            final String str2 = str;
            final RequestParams requestParams2 = requestParams;
            final boolean z4 = z;
            final Map<String, String> map3 = map;
            final CachePolicy cachePolicy2 = cachePolicy;
            final boolean z5 = z2;
            c09835 = new DNSRetryCallback(getBaseUrl(), genericObjectCallback) {
                public boolean isRetryNeeded(int i, Throwable th) {
                    return AVUtils.checkDNSException(i, th);
                }

                public void executeRequest() {
                    PaasClient.this.getObject(str2, requestParams2, z4, map3, this.callback, cachePolicy2, z5, false);
                }
            };
        } else {
            c09835 = genericObjectCallback;
        }
        ResponseHandlerInterface createGetHandler = createGetHandler(c09835, cachePolicy, AsyncHttpClient.getUrlWithQueryString(true, buildUrl, requestParams));
        if (AVOSCloud.isDebugLogEnabled()) {
            dumpHttpGetRequest(buildUrl, requestParams);
        }
        AsyncHttpClient clientInstance = clientInstance(z);
        addHeader(clientInstance, hashMap);
        clientInstance.get(buildUrl, requestParams, createGetHandler);
    }

    public void getObject(String str, RequestParams requestParams, boolean z, Map<String, String> map, GenericObjectCallback genericObjectCallback) {
        getObject(str, requestParams, z, map, genericObjectCallback, CachePolicy.IGNORE_CACHE);
    }

    public void putObject(String str, String str2, boolean z, Map<String, String> map, GenericObjectCallback genericObjectCallback, String str3, String str4) {
        putObject(str, str2, z, false, map, genericObjectCallback, str3, str4);
    }

    private void addHeader(AsyncHttpClient asyncHttpClient, Map<String, String> map) {
        if (map != null) {
            for (String str : map.keySet()) {
                asyncHttpClient.addHeader(str, (String) map.get(str));
            }
        }
    }

    public void putObject(String str, String str2, boolean z, boolean z2, Map<String, String> map, GenericObjectCallback genericObjectCallback, String str3, String str4) {
        putObject(str, str2, z, z2, map, genericObjectCallback, str3, str4, true);
    }

    private void putObject(String str, String str2, boolean z, boolean z2, Map<String, String> map, GenericObjectCallback genericObjectCallback, String str3, String str4, boolean z3) {
        if (z3) {
            try {
                final String str5 = str;
                final String str6 = str2;
                final boolean z4 = z;
                final boolean z5 = z2;
                final Map<String, String> map2 = map;
                final String str7 = str3;
                final String str8 = str4;
                GenericObjectCallback c09846 = new DNSRetryCallback(getBaseUrl(), genericObjectCallback) {
                    public void executeRequest() {
                        PaasClient.this.putObject(str5, str6, z4, z5, map2, this.callback, str7, str8, false);
                    }
                };
            } catch (Exception e) {
                processException(e, genericObjectCallback);
                return;
            }
        }
        c09846 = genericObjectCallback;
        if (z2) {
            handleArchivedRequest(archiveRequest("put", str, str2, str3, str4), z, c09846);
            return;
        }
        String buildUrl = buildUrl(str);
        HttpEntity byteArrayEntity = new ByteArrayEntity(str2.getBytes("UTF-8"));
        ResponseHandlerInterface createPostHandler = createPostHandler(c09846);
        if (AVOSCloud.isDebugLogEnabled()) {
            dumpHttpPutRequest(map, buildUrl, str2);
        }
        AsyncHttpClient clientInstance = clientInstance(z);
        addHeader(clientInstance, map);
        clientInstance.put(null, buildUrl, byteArrayEntity, "application/json", createPostHandler);
    }

    private void processException(Exception exception, GenericObjectCallback genericObjectCallback) {
        if (genericObjectCallback != null) {
            genericObjectCallback.onFailure(exception, null);
        }
    }

    Map<String, Object> batchItemMap(String str, String str2, Object obj, Map map) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(C0861d.f2145q, str);
        hashMap.put("path", str2);
        hashMap.put("body", obj);
        if (map != null) {
            hashMap.put("params", map);
        }
        return hashMap;
    }

    Map<String, Object> batchItemMap(String str, String str2, Object obj) {
        return batchItemMap(str, str2, obj, null);
    }

    @Deprecated
    List<Object> assembleBatchOpsList(List<Object> list, String str) {
        List<Object> arrayList = new ArrayList();
        for (Object batchItemMap : list) {
            arrayList.add(batchItemMap("PUT", str, batchItemMap));
        }
        return arrayList;
    }

    private Map<String, Object> batchRequest(List<Object> list) {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("requests", list);
        return hashMap;
    }

    public void postBatchObject(List<Object> list, boolean z, Map<String, String> map, GenericObjectCallback genericObjectCallback) {
        try {
            String batchUrl = batchUrl();
            String toJSONString = JSON.toJSONString(batchRequest(list));
            HttpEntity byteArrayEntity = new ByteArrayEntity(toJSONString.getBytes("UTF-8"));
            if (AVOSCloud.isDebugLogEnabled()) {
                dumpHttpPostRequest(map, batchUrl, toJSONString);
            }
            ResponseHandlerInterface createPostHandler = createPostHandler(genericObjectCallback);
            AsyncHttpClient clientInstance = clientInstance(z);
            addHeader(clientInstance, map);
            clientInstance.post(null, batchUrl, byteArrayEntity, "application/json", createPostHandler);
        } catch (Exception e) {
            processException(e, genericObjectCallback);
        }
    }

    public void postBatchSave(List list, boolean z, boolean z2, Map<String, String> map, GenericObjectCallback genericObjectCallback, String str, String str2) {
        postBatchSave(list, z, z2, map, genericObjectCallback, str, str2, true);
    }

    private void postBatchSave(List list, boolean z, boolean z2, Map<String, String> map, GenericObjectCallback genericObjectCallback, String str, String str2, boolean z3) {
        try {
            GenericObjectCallback c09857;
            Map hashMap = new HashMap();
            hashMap.put("requests", list);
            String jsonStringFromMapWithNull = AVUtils.jsonStringFromMapWithNull(hashMap);
            if (z3) {
                final List list2 = list;
                final boolean z4 = z;
                final boolean z5 = z2;
                final Map<String, String> map2 = map;
                final String str3 = str;
                final String str4 = str2;
                c09857 = new DNSRetryCallback(getBaseUrl(), genericObjectCallback) {
                    public void executeRequest() {
                        PaasClient.this.postBatchSave(list2, z4, z5, map2, this.callback, str3, str4, false);
                    }
                };
            } else {
                c09857 = genericObjectCallback;
            }
            if (z2) {
                handleArchivedRequest(archiveRequest("post", batchSaveRelativeUrl(), jsonStringFromMapWithNull, str, str2), z, c09857);
                return;
            }
            String buildUrl = buildUrl(batchSaveRelativeUrl());
            HttpEntity byteArrayEntity = new ByteArrayEntity(jsonStringFromMapWithNull.getBytes("UTF-8"));
            if (AVOSCloud.isDebugLogEnabled()) {
                dumpHttpPostRequest(map, buildUrl, jsonStringFromMapWithNull);
            }
            ResponseHandlerInterface createPostHandler = createPostHandler(c09857);
            AsyncHttpClient clientInstance = clientInstance(z);
            addHeader(clientInstance, map);
            clientInstance.post(null, buildUrl, byteArrayEntity, "application/json", createPostHandler);
        } catch (Exception e) {
            processException(e, genericObjectCallback);
        }
    }

    public void postObject(String str, String str2, boolean z, GenericObjectCallback genericObjectCallback) {
        postObject(str, str2, z, false, genericObjectCallback, null, null);
    }

    public void postObject(String str, String str2, boolean z, boolean z2, GenericObjectCallback genericObjectCallback, String str3, String str4) {
        postObject(str, str2, z, z2, genericObjectCallback, str3, str4, true);
    }

    private void postObject(String str, String str2, boolean z, boolean z2, GenericObjectCallback genericObjectCallback, String str3, String str4, boolean z3) {
        if (z3) {
            try {
                final String str5 = str;
                final String str6 = str2;
                final boolean z4 = z;
                final boolean z5 = z2;
                final String str7 = str3;
                final String str8 = str4;
                GenericObjectCallback c09868 = new DNSRetryCallback(getBaseUrl(), genericObjectCallback) {
                    public void executeRequest() {
                        PaasClient.this.postObject(str5, str6, z4, z5, this.callback, str7, str8, false);
                    }
                };
            } catch (Exception e) {
                processException(e, genericObjectCallback);
                return;
            }
        }
        c09868 = genericObjectCallback;
        if (z2) {
            handleArchivedRequest(archiveRequest("post", str, str2, str3, str4), z, c09868);
            return;
        }
        String buildUrl = buildUrl(str);
        HttpEntity byteArrayEntity = new ByteArrayEntity(str2.getBytes("UTF-8"));
        if (AVOSCloud.isDebugLogEnabled()) {
            dumpHttpPostRequest(null, buildUrl, str2);
        }
        clientInstance(z).post(null, buildUrl, byteArrayEntity, "application/json", createPostHandler(c09868));
    }

    public void deleteObject(String str, boolean z, GenericObjectCallback genericObjectCallback, String str2, String str3) {
        deleteObject(str, z, false, genericObjectCallback, str2, str3);
    }

    public void deleteObject(String str, boolean z, boolean z2, GenericObjectCallback genericObjectCallback, String str2, String str3) {
        deleteObject(str, z, z2, genericObjectCallback, str2, str3, true);
    }

    public void deleteObject(String str, boolean z, boolean z2, GenericObjectCallback genericObjectCallback, String str2, String str3, boolean z3) {
        if (z3) {
            try {
                final String str4 = str;
                final boolean z4 = z;
                final boolean z5 = z2;
                final String str5 = str2;
                final String str6 = str3;
                GenericObjectCallback c09879 = new DNSRetryCallback(getBaseUrl(), genericObjectCallback) {
                    public void executeRequest() {
                        PaasClient.this.deleteObject(str4, z4, z5, this.callback, str5, str6, false);
                    }
                };
            } catch (Exception e) {
                processException(e, genericObjectCallback);
                return;
            }
        }
        c09879 = genericObjectCallback;
        if (z2) {
            handleArchivedRequest(archiveRequest("delete", str, null, str2, str3), z, c09879);
            return;
        }
        String buildUrl = buildUrl(str);
        if (AVOSCloud.isDebugLogEnabled()) {
            dumpHttpDeleteRequest(null, buildUrl, null);
        }
        clientInstance(z).delete(buildUrl, createPostHandler(c09879));
    }

    private File archiveRequest(String str, String str2, String str3, String str4, String str5) {
        File file = new File(AVPersistenceUtils.getCommandCacheDir(), AVUtils.getArchiveRequestFileName(str4, str5, str, str2, str3));
        Map hashMap = new HashMap(3);
        hashMap.put(C0861d.f2145q, str);
        hashMap.put("relativePath", str2);
        hashMap.put("paramString", str3);
        hashMap.put(AVUtils.objectIdTag, str4);
        hashMap.put("_internalId", str5);
        AVPersistenceUtils.saveContentToFile(AVUtils.toJSON(hashMap), file);
        if (AVOSCloud.showInternalDebugLog()) {
            log.m3514d(AVUtils.restfulServerData(hashMap) + "\n" + "did save to " + file.getAbsolutePath());
        }
        return file;
    }

    private void handleArchivedRequest(File file, boolean z) {
        handleArchivedRequest(file, z, null);
    }

    private void handleArchivedRequest(final File file, boolean z, final GenericObjectCallback genericObjectCallback) {
        try {
            Map map = (Map) AVUtils.getFromJSON(AVPersistenceUtils.readContentFromFile(file), Map.class);
            if (map != null && !map.isEmpty()) {
                String str = (String) map.get(C0861d.f2145q);
                String str2 = (String) map.get("relativePath");
                String str3 = (String) map.get("paramString");
                String str4 = (String) map.get(AVUtils.objectIdTag);
                String str5 = (String) map.get("_internalId");
                GenericObjectCallback anonymousClass10 = new GenericObjectCallback() {
                    public void onSuccess(String str, AVException aVException) {
                        if (genericObjectCallback != null) {
                            genericObjectCallback.onSuccess(str, aVException);
                        }
                        try {
                            Map map = (Map) AVUtils.getFromJSON(str, Map.class);
                            for (String str2 : map.keySet()) {
                                if (PaasClient.internalObjectsForEventuallySave.get(str2) != null) {
                                    ((AVObjectReferenceCount) PaasClient.internalObjectsForEventuallySave.get(str2)).getValue().copyFromMap(map);
                                    PaasClient.unregisterEvtuallyObject(((AVObjectReferenceCount) PaasClient.internalObjectsForEventuallySave.get(str2)).getValue());
                                }
                            }
                        } catch (Exception e) {
                            avlog.m3507e("parse exception during archive request" + aVException.getMessage());
                        }
                        file.delete();
                        AVPersistenceUtils.removeLock(file.getAbsolutePath());
                    }

                    public void onFailure(Throwable th, String str) {
                        if (genericObjectCallback != null) {
                            genericObjectCallback.onFailure(th, str);
                        }
                        AVPersistenceUtils.removeLock(file.getAbsolutePath());
                    }
                };
                if (str == null) {
                    anonymousClass10.onFailure(new AVRuntimeException("Null method."), null);
                }
                if ("post".equalsIgnoreCase(str)) {
                    postObject(str2, str3, z, anonymousClass10);
                } else if ("put".equalsIgnoreCase(str)) {
                    putObject(str2, str3, z, null, anonymousClass10, str4, str5);
                } else if ("delete".equalsIgnoreCase(str)) {
                    deleteObject(str2, z, anonymousClass10, str4, str5);
                }
            }
        } catch (Exception e) {
        }
    }

    public void handleAllArchivedRequest() {
        handleAllArchivedRequest(false);
    }

    protected void handleAllArchivedRequest(boolean z) {
        File[] listFiles = AVPersistenceUtils.getCommandCacheDir().listFiles();
        if (listFiles != null && listFiles.length > 0) {
            Arrays.sort(listFiles, fileModifiedDateComparator);
            for (File file : listFiles) {
                if (file.isFile()) {
                    handleArchivedRequest(file, z);
                } else if (AVOSCloud.showInternalDebugLog()) {
                    avlog.m3507e(file.getAbsolutePath() + " is a dir");
                }
            }
        }
    }

    public void dumpHttpGetRequest(String str, RequestParams requestParams) {
        String str2 = "";
        if (requestParams != null) {
            str2 = String.format("curl -X GET -H \"%s: %s\" -H \"%s: %s\" -G --data-urlencode '%s' %s", new Object[]{applicationIdField, AVOSCloud.applicationId, apiKeyField, getDebugClientKey(), requestParams.toString(), str});
        } else {
            str2 = String.format("curl -X GET -H \"%s: %s\" -H \"%s: %s\"  %s", new Object[]{applicationIdField, AVOSCloud.applicationId, apiKeyField, getDebugClientKey(), str});
        }
        avlog.m3506d(str2);
    }

    private String getDebugClientKey() {
        if (AVOSCloud.showInternalDebugLog()) {
            return AVOSCloud.clientKey;
        }
        return "YourAppKey";
    }

    private String headerString(Map<String, String> map) {
        String str;
        String format = String.format(" -H \"%s: %s\" -H \"%s: %s\" ", new Object[]{applicationIdField, AVOSCloud.applicationId, apiKeyField, getDebugClientKey()});
        if (map != null) {
            str = format;
            for (String format2 : map.keySet()) {
                String str2 = (String) map.get(format2);
                str = str + String.format(" -H \"%s: %s\" ", new Object[]{(String) r3.next(), str2});
            }
        } else {
            str = format2;
        }
        return str + " -H \"Content-Type: application/json\" ";
    }

    public void dumpHttpPutRequest(Map<String, String> map, String str, String str2) {
        avlog.m3506d(String.format("curl -X PUT %s  -d ' %s ' %s", new Object[]{headerString(map), str2, str}));
    }

    public void dumpHttpPostRequest(Map<String, String> map, String str, String str2) {
        avlog.m3506d(String.format("curl -X POST %s  -d '%s' %s", new Object[]{headerString(map), str2, str}));
    }

    public void dumpHttpDeleteRequest(Map<String, String> map, String str, String str2) {
        avlog.m3506d(String.format("curl -X DELETE %s  -d '%s' %s", new Object[]{headerString(map), str2, str}));
    }

    public String updateHeaderForPath(String str, RequestParams requestParams, Map<String, String> map) {
        if (!isLastModifyEnabled()) {
            return null;
        }
        String generateQueryPath = generateQueryPath(str, requestParams);
        String lastModify = getLastModify(generateQueryPath);
        boolean hasCache = AVCacheManager.sharedInstance().hasCache(generateQueryPath, lastModify);
        if (lastModify == null || map == null || !hasCache) {
            return lastModify;
        }
        map.put("If-Modified-Since", lastModify);
        return lastModify;
    }

    public static String getLastModify(String str) {
        if (isLastModifyEnabled()) {
            return (String) lastModify.get(str);
        }
        return null;
    }

    public static boolean isLastModifyEnabled() {
        return lastModifyEnabled;
    }

    public static void setLastModifyEnabled(boolean z) {
        lastModifyEnabled = z;
    }

    public static void clearLastModifyCache() {
        for (Entry entry : lastModify.entrySet()) {
            AVCacheManager.sharedInstance().remove((String) entry.getKey(), (String) entry.getValue());
        }
        lastModify.clear();
    }

    public static String lastModifyFromHeaders(Header[] headerArr) {
        for (Header header : headerArr) {
            if (header.getName().equalsIgnoreCase("Last-Modified")) {
                return header.getValue();
            }
        }
        return null;
    }

    @SuppressLint({"DefaultLocale"})
    public static boolean isJSONResponse(String str) {
        if (AVUtils.isBlankString(str)) {
            return false;
        }
        return str.toLowerCase().contains("application/json");
    }

    protected static String extractContentType(Header[] headerArr) {
        if (headerArr != null) {
            for (Header header : headerArr) {
                if (header.getName().equalsIgnoreCase("Content-Type")) {
                    return header.getValue();
                }
            }
        }
        return null;
    }

    public static boolean updateLastModify(String str, String str2) {
        if (!isLastModifyEnabled() || AVUtils.isBlankString(str2)) {
            return false;
        }
        lastModify.put(str, str2);
        return true;
    }

    public static void removeLastModifyForUrl(String str) {
        lastModify.remove(str);
    }

    protected static void registerEventuallyObject(AVObject aVObject) {
        if (aVObject != null) {
            synchronized (aVObject) {
                AVObjectReferenceCount aVObjectReferenceCount = (AVObjectReferenceCount) internalObjectsForEventuallySave.get(aVObject.internalId());
                if (aVObjectReferenceCount != null) {
                    aVObjectReferenceCount.increment();
                } else {
                    internalObjectsForEventuallySave.put(aVObject.internalId(), new AVObjectReferenceCount(aVObject));
                }
            }
        }
    }

    protected static void unregisterEvtuallyObject(AVObject aVObject) {
        if (aVObject != null) {
            synchronized (aVObject) {
                AVObjectReferenceCount aVObjectReferenceCount = internalObjectsForEventuallySave.get(aVObject.internalId()) == null ? (AVObjectReferenceCount) internalObjectsForEventuallySave.get(aVObject.internalId()) : (AVObjectReferenceCount) internalObjectsForEventuallySave.get(aVObject.getUuid());
                if (aVObjectReferenceCount != null && aVObjectReferenceCount.desc() <= 0) {
                    internalObjectsForEventuallySave.remove(aVObject.internalId());
                    internalObjectsForEventuallySave.remove(aVObject.getUuid());
                }
            }
        }
    }
}
