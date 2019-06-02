package com.avos.avoscloud;

import ch.qos.logback.core.joran.action.Action;
import com.alibaba.fastjson.JSON;
import com.alipay.sdk.cons.C0844a;
import com.google.android.gms.actions.SearchIntents;
import com.loopj.android.http.RequestParams;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AVQuery<T extends AVObject> {
    private static final String CLOUD_QUERY_PATH = "cloudQuery";
    private static final String TAG = "com.parse.AVQuery";
    private CachePolicy cachePolicy;
    private String className;
    private Class<T> clazz;
    QueryConditions conditions;
    private String externalQueryPath;
    private Boolean isRunning;
    private long maxCacheAge;
    private String queryPath;

    public enum CachePolicy {
        CACHE_ELSE_NETWORK,
        CACHE_ONLY,
        CACHE_THEN_NETWORK,
        IGNORE_CACHE,
        NETWORK_ELSE_CACHE,
        NETWORK_ONLY
    }

    public AVQuery() {
        this.cachePolicy = CachePolicy.IGNORE_CACHE;
        this.maxCacheAge = -1;
    }

    Class<T> getClazz() {
        return this.clazz;
    }

    void setClazz(Class<T> cls) {
        this.clazz = cls;
    }

    List<String> getInclude() {
        return this.conditions.getInclude();
    }

    void setInclude(List<String> list) {
        this.conditions.setInclude(list);
    }

    Set<String> getSelectedKeys() {
        return this.conditions.getSelectedKeys();
    }

    void setSelectedKeys(Set<String> set) {
        this.conditions.setSelectedKeys(set);
    }

    Boolean getIsRunning() {
        return this.isRunning;
    }

    void setIsRunning(Boolean bool) {
        this.isRunning = bool;
    }

    Map<String, String> getParameters() {
        return this.conditions.getParameters();
    }

    void setParameters(Map<String, String> map) {
        this.conditions.setParameters(map);
    }

    String getQueryPath() {
        return this.queryPath;
    }

    void setQueryPath(String str) {
        this.queryPath = str;
    }

    String getExternalQueryPath() {
        return this.externalQueryPath;
    }

    void setExternalQueryPath(String str) {
        this.externalQueryPath = str;
    }

    static String getTag() {
        return TAG;
    }

    Map<String, List<QueryOperation>> getWhere() {
        return this.conditions.getWhere();
    }

    public String getClassName() {
        return this.className;
    }

    public AVQuery<T> setClassName(String str) {
        this.className = str;
        return this;
    }

    private void generateQueryPath() {
        if (AVUtils.isBlankString(this.queryPath)) {
            this.conditions.assembleParameters();
            this.queryPath = PaasClient.storageInstance().generateQueryPath(queryPath(), new RequestParams(getParameters()));
        }
    }

    public CachePolicy getCachePolicy() {
        return this.cachePolicy;
    }

    public AVQuery<T> setCachePolicy(CachePolicy cachePolicy) {
        this.cachePolicy = cachePolicy;
        return this;
    }

    public CachePolicy getPolicy() {
        return this.cachePolicy;
    }

    public AVQuery<T> setPolicy(CachePolicy cachePolicy) {
        this.cachePolicy = cachePolicy;
        return this;
    }

    public long getMaxCacheAge() {
        return this.maxCacheAge;
    }

    public AVQuery<T> setMaxCacheAge(long j) {
        this.maxCacheAge = j;
        return this;
    }

    public boolean isTrace() {
        return this.conditions.isTrace();
    }

    public AVQuery<T> setTrace(boolean z) {
        this.conditions.setTrace(z);
        return this;
    }

    public AVQuery(String str) {
        this(str, null);
    }

    AVQuery(String str, Class<T> cls) {
        this.cachePolicy = CachePolicy.IGNORE_CACHE;
        this.maxCacheAge = -1;
        AVUtils.checkClassName(str);
        this.className = str;
        this.clazz = cls;
        this.conditions = new QueryConditions();
    }

    public static <T extends AVObject> AVQuery<T> getQuery(String str) {
        return new AVQuery(str);
    }

    public static <T extends AVObject> AVQuery<T> getQuery(Class<T> cls) {
        return new AVQuery(AVObject.getSubClassName(cls), cls);
    }

    public void cancel() {
    }

    public static void clearAllCachedResults() {
        AVCacheManager.clearAllCache();
    }

    public void clearCachedResult() {
        generateQueryPath();
        if (!AVUtils.isBlankString(this.queryPath)) {
            AVCacheManager.sharedInstance().delete(this.queryPath);
        }
    }

    private String queryPath() {
        if (AVUtils.isBlankString(this.externalQueryPath)) {
            return AVPowerfulUtils.getEndpoint(getClassName());
        }
        return this.externalQueryPath;
    }

    @Deprecated
    public static AVQuery<AVUser> getUserQuery() {
        return AVUser.getQuery();
    }

    public boolean hasCachedResult() {
        generateQueryPath();
        return !AVUtils.isBlankString(this.queryPath) && AVCacheManager.sharedInstance().hasCache(this.queryPath);
    }

    public int getLimit() {
        return this.conditions.getLimit();
    }

    public AVQuery<T> setLimit(int i) {
        this.conditions.setLimit(i);
        return this;
    }

    public AVQuery<T> limit(int i) {
        setLimit(i);
        return this;
    }

    public AVQuery<T> skip(int i) {
        setSkip(i);
        return this;
    }

    public int getSkip() {
        return this.conditions.getSkip();
    }

    public AVQuery<T> setSkip(int i) {
        this.conditions.setSkip(i);
        return this;
    }

    public String getOrder() {
        return this.conditions.getOrder();
    }

    public AVQuery<T> setOrder(String str) {
        this.conditions.setOrder(str);
        return this;
    }

    public AVQuery<T> order(String str) {
        setOrder(str);
        return this;
    }

    public AVQuery<T> addAscendingOrder(String str) {
        this.conditions.addAscendingOrder(str);
        return this;
    }

    public AVQuery<T> addDescendingOrder(String str) {
        this.conditions.addDescendingOrder(str);
        return this;
    }

    public AVQuery<T> include(String str) {
        this.conditions.include(str);
        return this;
    }

    public AVQuery<T> selectKeys(Collection<String> collection) {
        this.conditions.selectKeys(collection);
        return this;
    }

    public AVQuery<T> orderByAscending(String str) {
        this.conditions.orderByAscending(str);
        return this;
    }

    public AVQuery<T> orderByDescending(String str) {
        this.conditions.orderByDescending(str);
        return this;
    }

    public AVQuery<T> whereContainedIn(String str, Collection<? extends Object> collection) {
        this.conditions.whereContainedIn(str, collection);
        return this;
    }

    public AVQuery<T> whereContains(String str, String str2) {
        this.conditions.whereContains(str, str2);
        return this;
    }

    public AVQuery<T> whereSizeEqual(String str, int i) {
        this.conditions.whereSizeEqual(str, i);
        return this;
    }

    public AVQuery<T> whereContainsAll(String str, Collection<?> collection) {
        this.conditions.whereContainsAll(str, collection);
        return this;
    }

    public AVQuery<T> whereDoesNotExist(String str) {
        this.conditions.whereDoesNotExist(str);
        return this;
    }

    public AVQuery<T> whereEndsWith(String str, String str2) {
        this.conditions.whereEndsWith(str, str2);
        return this;
    }

    public AVQuery<T> whereEqualTo(String str, Object obj) {
        this.conditions.whereEqualTo(str, obj);
        return this;
    }

    private AVQuery<T> addWhereItem(QueryOperation queryOperation) {
        this.conditions.addWhereItem(queryOperation);
        return this;
    }

    private AVQuery<T> addOrItems(QueryOperation queryOperation) {
        this.conditions.addOrItems(queryOperation);
        return this;
    }

    protected AVQuery<T> addWhereItem(String str, String str2, Object obj) {
        this.conditions.addWhereItem(str, str2, obj);
        return this;
    }

    public AVQuery<T> whereExists(String str) {
        this.conditions.whereExists(str);
        return this;
    }

    public AVQuery<T> whereGreaterThan(String str, Object obj) {
        this.conditions.whereGreaterThan(str, obj);
        return this;
    }

    public AVQuery<T> whereGreaterThanOrEqualTo(String str, Object obj) {
        this.conditions.whereGreaterThanOrEqualTo(str, obj);
        return this;
    }

    public AVQuery<T> whereLessThan(String str, Object obj) {
        this.conditions.whereLessThan(str, obj);
        return this;
    }

    public AVQuery<T> whereLessThanOrEqualTo(String str, Object obj) {
        this.conditions.whereLessThanOrEqualTo(str, obj);
        return this;
    }

    public AVQuery<T> whereMatches(String str, String str2) {
        this.conditions.whereMatches(str, str2);
        return this;
    }

    public AVQuery<T> whereMatches(String str, String str2, String str3) {
        this.conditions.whereMatches(str, str2, str3);
        return this;
    }

    public AVQuery<T> whereNear(String str, AVGeoPoint aVGeoPoint) {
        this.conditions.whereNear(str, aVGeoPoint);
        return this;
    }

    public AVQuery<T> whereNotContainedIn(String str, Collection<? extends Object> collection) {
        this.conditions.whereNotContainedIn(str, collection);
        return this;
    }

    public AVQuery<T> whereNotEqualTo(String str, Object obj) {
        this.conditions.whereNotEqualTo(str, obj);
        return this;
    }

    public AVQuery<T> whereStartsWith(String str, String str2) {
        this.conditions.whereStartsWith(str, str2);
        return this;
    }

    public AVQuery<T> whereWithinGeoBox(String str, AVGeoPoint aVGeoPoint, AVGeoPoint aVGeoPoint2) {
        this.conditions.whereWithinGeoBox(str, aVGeoPoint, aVGeoPoint2);
        return this;
    }

    public AVQuery<T> whereWithinKilometers(String str, AVGeoPoint aVGeoPoint, double d) {
        this.conditions.whereWithinKilometers(str, aVGeoPoint, d);
        return this;
    }

    public AVQuery<T> whereWithinMiles(String str, AVGeoPoint aVGeoPoint, double d) {
        this.conditions.whereWithinMiles(str, aVGeoPoint, d);
        return this;
    }

    public AVQuery<T> whereWithinRadians(String str, AVGeoPoint aVGeoPoint, double d) {
        this.conditions.whereWithinRadians(str, aVGeoPoint, d);
        return this;
    }

    public static <T extends AVObject> AVQuery<T> or(List<AVQuery<T>> list) {
        String className;
        if (list.size() > 0) {
            className = ((AVQuery) list.get(0)).getClassName();
        } else {
            className = null;
        }
        AVQuery<T> aVQuery = new AVQuery(className);
        if (list.size() > 1) {
            for (AVQuery aVQuery2 : list) {
                if (className.equals(aVQuery2.getClassName())) {
                    aVQuery.addOrItems(new QueryOperation(QueryOperation.OR_OP, QueryOperation.OR_OP, aVQuery2.conditions.compileWhereOperationMap()));
                } else {
                    throw new IllegalArgumentException("All queries must be for the same class");
                }
            }
        }
        aVQuery.setWhere(((AVQuery) list.get(0)).conditions.getWhere());
        return aVQuery;
    }

    public AVQuery<T> whereMatchesKeyInQuery(String str, String str2, AVQuery<?> aVQuery) {
        Map hashMap = new HashMap();
        hashMap.put("className", aVQuery.getClassName());
        hashMap.put("where", aVQuery.conditions.compileWhereOperationMap());
        if (aVQuery.conditions.getSkip() > 0) {
            hashMap.put("skip", Integer.valueOf(aVQuery.conditions.getSkip()));
        }
        if (aVQuery.conditions.getLimit() > 0) {
            hashMap.put("limit", Integer.valueOf(aVQuery.conditions.getLimit()));
        }
        if (!AVUtils.isBlankContent(aVQuery.getOrder())) {
            hashMap.put("order", aVQuery.getOrder());
        }
        Map hashMap2 = new HashMap();
        hashMap2.put(SearchIntents.EXTRA_QUERY, hashMap);
        hashMap2.put(Action.KEY_ATTRIBUTE, str2);
        return addWhereItem(str, "$select", hashMap2);
    }

    public AVQuery<T> whereMatchesQuery(String str, AVQuery<?> aVQuery) {
        Map createMap = AVUtils.createMap("where", aVQuery.conditions.compileWhereOperationMap());
        createMap.put("className", aVQuery.className);
        if (aVQuery.conditions.getSkip() > 0) {
            createMap.put("skip", Integer.valueOf(aVQuery.conditions.getSkip()));
        }
        if (aVQuery.conditions.getLimit() > 0) {
            createMap.put("limit", Integer.valueOf(aVQuery.conditions.getLimit()));
        }
        if (!AVUtils.isBlankContent(aVQuery.getOrder())) {
            createMap.put("order", aVQuery.getOrder());
        }
        addWhereItem(str, "$inQuery", createMap);
        return this;
    }

    public AVQuery<T> whereDoesNotMatchKeyInQuery(String str, String str2, AVQuery<?> aVQuery) {
        Map createMap = AVUtils.createMap("className", aVQuery.className);
        createMap.put("where", aVQuery.conditions.compileWhereOperationMap());
        createMap = AVUtils.createMap(SearchIntents.EXTRA_QUERY, createMap);
        createMap.put(Action.KEY_ATTRIBUTE, str2);
        addWhereItem(str, "$dontSelect", createMap);
        return this;
    }

    public AVQuery<T> whereDoesNotMatchQuery(String str, AVQuery<?> aVQuery) {
        Map createMap = AVUtils.createMap("className", aVQuery.className);
        createMap.put("where", aVQuery.conditions.compileWhereOperationMap());
        addWhereItem(str, "$notInQuery", createMap);
        return this;
    }

    AVQuery<T> setWhere(Map<String, List<QueryOperation>> map) {
        this.conditions.setWhere(map);
        return this;
    }

    public static AVCloudQueryResult doCloudQuery(String str, Object... objArr) throws Exception {
        final AVCloudQueryResult aVCloudQueryResult = new AVCloudQueryResult();
        doCloudQueryInBackground(str, new CloudQueryCallback<AVCloudQueryResult>() {
            public void done(AVCloudQueryResult aVCloudQueryResult, AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(AVErrorUtils.createException((Throwable) aVException, null));
                    return;
                }
                aVCloudQueryResult.setCount(aVCloudQueryResult.getCount());
                aVCloudQueryResult.setResults(aVCloudQueryResult.getResults());
            }
        }, AVObject.class, true, objArr);
        if (!AVExceptionHolder.exists()) {
            return aVCloudQueryResult;
        }
        throw AVExceptionHolder.remove();
    }

    public static AVCloudQueryResult doCloudQuery(String str, Class<? extends AVObject> cls, Object... objArr) throws Exception {
        final AVCloudQueryResult aVCloudQueryResult = new AVCloudQueryResult();
        doCloudQueryInBackground(str, new CloudQueryCallback<AVCloudQueryResult>() {
            public void done(AVCloudQueryResult aVCloudQueryResult, AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(AVErrorUtils.createException((Throwable) aVException, null));
                    return;
                }
                aVCloudQueryResult.setCount(aVCloudQueryResult.getCount());
                aVCloudQueryResult.setResults(aVCloudQueryResult.getResults());
            }
        }, AVObject.class, true, objArr);
        if (!AVExceptionHolder.exists()) {
            return aVCloudQueryResult;
        }
        throw AVExceptionHolder.remove();
    }

    public static void doCloudQueryInBackground(String str, CloudQueryCallback<AVCloudQueryResult> cloudQueryCallback, Object... objArr) {
        doCloudQueryInBackground(str, cloudQueryCallback, AVObject.class, false, objArr);
    }

    public static void doCloudQueryInBackground(String str, CloudQueryCallback<AVCloudQueryResult> cloudQueryCallback, Class<? extends AVObject> cls, Object... objArr) {
        doCloudQueryInBackground(str, cloudQueryCallback, cls, false, objArr);
    }

    public static AVCloudQueryResult doCloudQuery(String str) throws Exception {
        return doCloudQuery(str, AVObject.class);
    }

    public static AVCloudQueryResult doCloudQuery(String str, Class<? extends AVObject> cls) throws Exception {
        final AVCloudQueryResult aVCloudQueryResult = new AVCloudQueryResult();
        doCloudQueryInBackground(str, new CloudQueryCallback<AVCloudQueryResult>() {
            public void done(AVCloudQueryResult aVCloudQueryResult, AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(AVErrorUtils.createException((Throwable) aVException, null));
                    return;
                }
                aVCloudQueryResult.setCount(aVCloudQueryResult.getCount());
                aVCloudQueryResult.setResults(aVCloudQueryResult.getResults());
            }
        }, cls, true, null);
        if (!AVExceptionHolder.exists()) {
            return aVCloudQueryResult;
        }
        throw AVExceptionHolder.remove();
    }

    public static void doCloudQueryInBackground(String str, CloudQueryCallback<AVCloudQueryResult> cloudQueryCallback) {
        doCloudQueryInBackground(str, cloudQueryCallback, AVObject.class, false, null);
    }

    public static void doCloudQueryInBackground(String str, CloudQueryCallback<AVCloudQueryResult> cloudQueryCallback, Class<? extends AVObject> cls) {
        doCloudQueryInBackground(str, cloudQueryCallback, cls, false, null);
    }

    private static void doCloudQueryInBackground(String str, final CloudQueryCallback<AVCloudQueryResult> cloudQueryCallback, final Class<? extends AVObject> cls, boolean z, Object[] objArr) {
        List linkedList = new LinkedList();
        if (objArr != null) {
            for (Object add : objArr) {
                linkedList.add(add);
            }
        }
        RequestParams requestParams = new RequestParams();
        requestParams.put("cql", str);
        if (!AVUtils.isEmptyList(linkedList)) {
            requestParams.put("pvalues", AVUtils.jsonStringFromObjectWithNull(linkedList));
        }
        PaasClient.storageInstance().getObject(CLOUD_QUERY_PATH, requestParams, z, null, new GenericObjectCallback() {
            public void onFailure(Throwable th, String str) {
                if (cloudQueryCallback != null) {
                    cloudQueryCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }

            public void onSuccess(String str, AVException aVException) {
                try {
                    AVCloudQueryResult access$000 = AVQuery.processCloudResults(str, cls);
                    if (cloudQueryCallback != null) {
                        cloudQueryCallback.internalDone(access$000, null);
                    }
                } catch (Throwable e) {
                    if (cloudQueryCallback != null) {
                        cloudQueryCallback.internalDone(null, AVErrorUtils.createException(e, null));
                    }
                }
            }
        });
    }

    private static <T extends AVObject> AVCloudQueryResult processCloudResults(String str, Class<T> cls) throws Exception {
        AVCloudQueryResult aVCloudQueryResult = new AVCloudQueryResult();
        if (AVUtils.isBlankContent(str)) {
            aVCloudQueryResult.setResults(Collections.emptyList());
            return aVCloudQueryResult;
        }
        AVResponse aVResponse = (AVResponse) JSON.parseObject(str, new AVResponse().getClass());
        List linkedList = new LinkedList();
        if (aVResponse.results != null) {
            for (Map map : aVResponse.results) {
                if (!(map == null || map.isEmpty())) {
                    AVObject aVObject;
                    if (cls != null) {
                        aVObject = (AVObject) cls.newInstance();
                        if (AVUtils.isBlankString(aVObject.getClassName())) {
                            aVObject.setClassName(aVResponse.className);
                        }
                    } else {
                        aVObject = new AVObject(aVResponse.className);
                    }
                    AVUtils.copyPropertiesFromMapToAVObject(map, aVObject);
                    linkedList.add(aVObject);
                }
            }
        }
        aVCloudQueryResult.setCount(aVResponse.count);
        aVCloudQueryResult.setResults(linkedList);
        return aVCloudQueryResult;
    }

    protected void processAdditionalInfo(String str, FindCallback<T> findCallback) {
    }

    protected List<T> processResults(String str) throws Exception {
        if (AVUtils.isBlankContent(str)) {
            return Collections.emptyList();
        }
        AVResponse aVResponse = (AVResponse) JSON.parseObject(str, new AVResponse().getClass());
        List<T> linkedList = new LinkedList();
        for (Map map : aVResponse.results) {
            if (!(map == null || map.isEmpty())) {
                AVObject aVObject;
                if (this.clazz != null) {
                    aVObject = (AVObject) this.clazz.newInstance();
                } else {
                    aVObject = AVUtils.newAVObjectByClassName(aVResponse.className, getClassName());
                }
                AVUtils.copyPropertiesFromMapToAVObject(map, aVObject);
                aVObject.rebuildInstanceData();
                linkedList.add(aVObject);
            }
        }
        return linkedList;
    }

    public void findInBackground(final FindCallback<T> findCallback) {
        assembleParameters();
        this.queryPath = PaasClient.storageInstance().getObject(queryPath(), new RequestParams(getParameters()), false, null, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                try {
                    List processResults = AVQuery.this.processResults(str);
                    AVQuery.this.processAdditionalInfo(str, findCallback);
                    if (findCallback != null) {
                        findCallback.internalDone(processResults, null);
                    }
                } catch (Throwable e) {
                    if (findCallback != null) {
                        findCallback.internalDone(null, AVErrorUtils.createException(e, null));
                    }
                }
            }

            public void onFailure(Throwable th, String str) {
                if (findCallback != null) {
                    findCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        }, this.cachePolicy, this.maxCacheAge);
    }

    public T get(String str) throws AVException {
        final Object[] objArr = new Object[]{null};
        getInBackground(str, true, new GetCallback<T>() {
            public void done(T t, AVException aVException) {
                if (aVException == null) {
                    objArr[0] = t;
                } else {
                    AVExceptionHolder.add(aVException);
                }
            }

            protected boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (!AVExceptionHolder.exists()) {
            return (AVObject) objArr[0];
        }
        throw AVExceptionHolder.remove();
    }

    public T getFirst() throws AVException {
        final Object[] objArr = new Object[]{null};
        getFirstInBackground(true, new GetCallback<T>() {
            public void done(AVObject aVObject, AVException aVException) {
                if (aVException == null) {
                    objArr[0] = aVObject;
                } else {
                    AVExceptionHolder.add(aVException);
                }
            }

            protected boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (!AVExceptionHolder.exists()) {
            return (AVObject) objArr[0];
        }
        throw AVExceptionHolder.remove();
    }

    public void getFirstInBackground(GetCallback<T> getCallback) {
        getFirstInBackground(false, getCallback);
    }

    private void getFirstInBackground(boolean z, final GetCallback<T> getCallback) {
        assembleParameters();
        Map parameters = getParameters();
        parameters.put("limit", Integer.toString(1));
        parameters.put("order", "-updatedAt");
        PaasClient.storageInstance().getObject(queryPath(), new RequestParams(getParameters()), z, null, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                try {
                    List processResults = AVQuery.this.processResults(str);
                    if (getCallback != null) {
                        Object obj;
                        if (processResults.size() > 0) {
                            obj = (AVObject) processResults.get(0);
                        } else {
                            obj = null;
                        }
                        getCallback.internalDone(obj, null);
                    }
                } catch (Throwable e) {
                    if (getCallback != null) {
                        getCallback.internalDone(null, AVErrorUtils.createException(e, null));
                    }
                }
            }

            public void onFailure(Throwable th, String str) {
                if (getCallback != null) {
                    getCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        });
    }

    public void getInBackground(String str, final GetCallback<T> getCallback) {
        getInBackground(str, false, new GetCallback<T>() {
            public void done(T t, AVException aVException) {
                if (getCallback != null) {
                    getCallback.internalDone(t, aVException);
                }
            }
        });
    }

    private void getInBackground(String str, boolean z, final GetCallback<T> getCallback) {
        String endpointByAVClassName = AVPowerfulUtils.getEndpointByAVClassName(getClassName(), str);
        assembleParameters();
        PaasClient.storageInstance().getObject(endpointByAVClassName, new RequestParams(getParameters()), z, null, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                Object obj;
                Throwable e;
                if (AVUtils.isBlankContent(str)) {
                    aVException = new AVException(101, "Object is not found.");
                    obj = null;
                } else {
                    try {
                        if (AVQuery.this.clazz != null) {
                            try {
                                obj = (AVObject) AVQuery.this.clazz.newInstance();
                            } catch (Throwable e2) {
                                getCallback.internalDone(AVErrorUtils.createException(e2, "Please create non-params constructor"));
                                obj = null;
                            }
                        } else {
                            obj = AVUtils.newAVObjectByClassName(AVQuery.this.getClassName());
                        }
                        try {
                            AVUtils.copyPropertiesFromJsonStringToAVObject(str, obj);
                            obj.rebuildInstanceData();
                        } catch (Exception e3) {
                            e = e3;
                            if (getCallback != null) {
                                getCallback.internalDone(obj, new AVException(e));
                            }
                            if (getCallback == null) {
                                getCallback.internalDone(obj, aVException);
                            }
                        }
                    } catch (Throwable e22) {
                        Throwable th = e22;
                        obj = null;
                        e = th;
                        if (getCallback != null) {
                            getCallback.internalDone(obj, new AVException(e));
                        }
                        if (getCallback == null) {
                            getCallback.internalDone(obj, aVException);
                        }
                    }
                }
                if (getCallback == null) {
                    getCallback.internalDone(obj, aVException);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (getCallback != null) {
                    getCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        });
    }

    public int count() throws AVException {
        final int[] iArr = new int[]{0};
        countInBackground(true, new CountCallback() {
            public void done(int i, AVException aVException) {
                if (aVException == null) {
                    iArr[0] = i;
                } else {
                    AVExceptionHolder.add(aVException);
                }
            }

            protected boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (!AVExceptionHolder.exists()) {
            return iArr[0];
        }
        throw AVExceptionHolder.remove();
    }

    protected int count(boolean z) {
        final int[] iArr = new int[]{0};
        countInBackground(true, new CountCallback() {
            public void done(int i, AVException aVException) {
                iArr[0] = i;
            }
        });
        return iArr[0];
    }

    public void countInBackground(CountCallback countCallback) {
        countInBackground(false, countCallback);
    }

    private void countInBackground(boolean z, final CountCallback countCallback) {
        this.conditions.assembleParameters();
        Map parameters = this.conditions.getParameters();
        parameters.put("count", C0844a.f2048d);
        parameters.put("limit", "0");
        this.queryPath = PaasClient.storageInstance().getObject(queryPath(), new RequestParams(getParameters()), z, null, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                try {
                    AVResponse aVResponse = (AVResponse) JSON.parseObject(str, AVResponse.class);
                    if (countCallback != null) {
                        countCallback.internalDone(Integer.valueOf(aVResponse.count), null);
                    }
                } catch (Throwable e) {
                    countCallback.internalDone(AVErrorUtils.createException(e, "Exception during response parse"));
                }
            }

            public void onFailure(Throwable th, String str) {
                if (countCallback != null) {
                    countCallback.internalDone(Integer.valueOf(0), AVErrorUtils.createException(th, str));
                }
            }
        }, this.cachePolicy, this.maxCacheAge);
    }

    public List<T> find() throws AVException {
        String queryPath = queryPath();
        assembleParameters();
        final List<T> arrayList = new ArrayList();
        this.queryPath = PaasClient.storageInstance().getObject(queryPath, new RequestParams(getParameters()), true, null, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                try {
                    arrayList.addAll(AVQuery.this.processResults(str));
                } catch (Throwable e) {
                    AVExceptionHolder.add(AVErrorUtils.createException(e, null));
                }
            }

            public void onFailure(Throwable th, String str) {
                AVExceptionHolder.add(AVErrorUtils.createException(th, str));
            }
        }, this.cachePolicy, this.maxCacheAge);
        if (!AVExceptionHolder.exists()) {
            return arrayList;
        }
        throw AVExceptionHolder.remove();
    }

    public void deleteAll() throws AVException {
        AVObject.deleteAll(find());
    }

    public void deleteAllInBackground(final DeleteCallback deleteCallback) {
        findInBackground(new FindCallback<T>() {
            public void done(List<T> list, AVException aVException) {
                if (aVException != null) {
                    deleteCallback.internalDone(null, aVException);
                } else {
                    AVObject.deleteAllInBackground(list, deleteCallback);
                }
            }
        });
    }

    protected Map<String, String> assembleParameters() {
        return this.conditions.assembleParameters();
    }
}
