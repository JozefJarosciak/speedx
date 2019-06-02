package com.avos.avoscloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.avos.avoscloud.LogUtil.log;
import com.avos.avoscloud.ops.AVOp;
import com.avos.avoscloud.ops.AddOp;
import com.avos.avoscloud.ops.AddRelationOp;
import com.avos.avoscloud.ops.AddUniqueOp;
import com.avos.avoscloud.ops.CollectionOp;
import com.avos.avoscloud.ops.CompoundOp;
import com.avos.avoscloud.ops.DeleteOp;
import com.avos.avoscloud.ops.IncrementOp;
import com.avos.avoscloud.ops.RemoveOp;
import com.avos.avoscloud.ops.RemoveRelationOp;
import com.avos.avoscloud.ops.SetOp;
import com.loopj.android.http.RequestParams;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONArray;
import org.json.JSONObject;

public class AVObject implements Parcelable {
    static final Creator<AVObject> CREATOR = new Creator<AVObject>() {
        public AVObject createFromParcel(Parcel parcel) {
            return new AVObject(parcel);
        }

        public AVObject[] newArray(int i) {
            return new AVObject[i];
        }
    };
    public static final Set<String> INVALID_KEYS = new HashSet();
    private static final String LOGTAG = AVObject.class.getName();
    private static final Map<String, Class<? extends AVObject>> SUB_CLASSES_MAP = new HashMap();
    private static final Map<Class<? extends AVObject>, String> SUB_CLASSES_REVERSE_MAP = new HashMap();
    static final int UUID_LEN = UUID.randomUUID().toString().length();
    protected transient AVACL acl;
    private String className;
    protected String createdAt;
    private volatile boolean fetchWhenSave;
    Map<String, Object> instanceData;
    @JSONField
    private boolean isDataReady;
    ReadWriteLock lock;
    protected String objectId;
    Map<String, AVOp> operationQueue;
    private volatile transient boolean running;
    Map<String, Object> serverData;
    Map<String, AVOp> tempDataForServerSaving;
    protected String updatedAt;
    private String uuid;

    private abstract class KeyValueCallback {
        public abstract AVOp createOp();

        private KeyValueCallback() {
        }

        public void execute(String str) {
            execute(str, true);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void execute(java.lang.String r4, boolean r5) {
            /*
            r3 = this;
            r0 = com.avos.avoscloud.AVObject.this;	 Catch:{ Exception -> 0x0053 }
            r0 = r0.lock;	 Catch:{ Exception -> 0x0053 }
            r0 = r0.writeLock();	 Catch:{ Exception -> 0x0053 }
            r0.lock();	 Catch:{ Exception -> 0x0053 }
            r0 = com.avos.avoscloud.AVObject.this;	 Catch:{ Exception -> 0x0053 }
            r0 = r0.operationQueue;	 Catch:{ Exception -> 0x0053 }
            r0 = r0.get(r4);	 Catch:{ Exception -> 0x0053 }
            r0 = (com.avos.avoscloud.ops.AVOp) r0;	 Catch:{ Exception -> 0x0053 }
            r1 = r3.createOp();	 Catch:{ Exception -> 0x0053 }
            if (r0 != 0) goto L_0x0046;
        L_0x001b:
            r0 = r1;
        L_0x001c:
            r2 = com.avos.avoscloud.AVObject.this;	 Catch:{ Exception -> 0x0053 }
            r2 = r2.instanceData;	 Catch:{ Exception -> 0x0053 }
            r2 = r2.get(r4);	 Catch:{ Exception -> 0x0053 }
            r1 = r1.apply(r2);	 Catch:{ Exception -> 0x0053 }
            if (r5 == 0) goto L_0x004b;
        L_0x002a:
            r2 = com.avos.avoscloud.AVObject.this;	 Catch:{ Exception -> 0x0053 }
            r2 = r2.operationQueue;	 Catch:{ Exception -> 0x0053 }
            r2.put(r4, r0);	 Catch:{ Exception -> 0x0053 }
        L_0x0031:
            if (r1 != 0) goto L_0x0069;
        L_0x0033:
            r0 = com.avos.avoscloud.AVObject.this;	 Catch:{ Exception -> 0x0053 }
            r0 = r0.instanceData;	 Catch:{ Exception -> 0x0053 }
            r0.remove(r4);	 Catch:{ Exception -> 0x0053 }
        L_0x003a:
            r0 = com.avos.avoscloud.AVObject.this;
            r0 = r0.lock;
            r0 = r0.writeLock();
            r0.unlock();
        L_0x0045:
            return;
        L_0x0046:
            r0 = r0.merge(r1);	 Catch:{ Exception -> 0x0053 }
            goto L_0x001c;
        L_0x004b:
            r0 = com.avos.avoscloud.AVObject.this;	 Catch:{ Exception -> 0x0053 }
            r0 = r0.serverData;	 Catch:{ Exception -> 0x0053 }
            r0.put(r4, r1);	 Catch:{ Exception -> 0x0053 }
            goto L_0x0031;
        L_0x0053:
            r0 = move-exception;
            r1 = com.avos.avoscloud.AVObject.LOGTAG;	 Catch:{ all -> 0x0071 }
            r2 = "";
            com.avos.avoscloud.LogUtil.log.m3522e(r1, r2, r0);	 Catch:{ all -> 0x0071 }
            r0 = com.avos.avoscloud.AVObject.this;
            r0 = r0.lock;
            r0 = r0.writeLock();
            r0.unlock();
            goto L_0x0045;
        L_0x0069:
            r0 = com.avos.avoscloud.AVObject.this;	 Catch:{ Exception -> 0x0053 }
            r0 = r0.instanceData;	 Catch:{ Exception -> 0x0053 }
            r0.put(r4, r1);	 Catch:{ Exception -> 0x0053 }
            goto L_0x003a;
        L_0x0071:
            r0 = move-exception;
            r1 = com.avos.avoscloud.AVObject.this;
            r1 = r1.lock;
            r1 = r1.writeLock();
            r1.unlock();
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.avos.avoscloud.AVObject.KeyValueCallback.execute(java.lang.String, boolean):void");
        }
    }

    /* renamed from: com.avos.avoscloud.AVObject$1 */
    class C09271 extends DeleteCallback {
        C09271() {
        }

        public void done(AVException aVException) {
            if (aVException != null) {
                AVExceptionHolder.add(aVException);
            }
        }

        protected boolean mustRunOnUIThread() {
            return false;
        }
    }

    /* renamed from: com.avos.avoscloud.AVObject$2 */
    static class C09282 extends DeleteCallback {
        C09282() {
        }

        public void done(AVException aVException) {
            if (aVException != null) {
                AVExceptionHolder.add(aVException);
            }
        }

        protected boolean mustRunOnUIThread() {
            return false;
        }
    }

    /* renamed from: com.avos.avoscloud.AVObject$5 */
    class C09315 extends GetCallback<AVObject> {
        C09315() {
        }

        public void done(AVObject aVObject, AVException aVException) {
            if (aVException != null) {
                AVExceptionHolder.add(aVException);
            }
        }

        protected boolean mustRunOnUIThread() {
            return false;
        }
    }

    /* renamed from: com.avos.avoscloud.AVObject$9 */
    class C09359 extends GetCallback<AVObject> {
        C09359() {
        }

        public void done(AVObject aVObject, AVException aVException) {
            if (aVException != null) {
                AVExceptionHolder.add(aVException);
            }
        }

        protected boolean mustRunOnUIThread() {
            return false;
        }
    }

    private final class FetchObjectCallback extends GenericObjectCallback {
        private final AVCallback<AVObject> internalCallback;

        private FetchObjectCallback(AVCallback<AVObject> aVCallback) {
            this.internalCallback = aVCallback;
        }

        public void onSuccess(String str, AVException aVException) {
            Object obj = AVObject.this;
            if (AVUtils.isBlankContent(str)) {
                obj = null;
                aVException = new AVException(101, "The object is not Found");
            } else {
                AVUtils.copyPropertiesFromJsonStringToAVObject(str, obj);
                AVObject.this.isDataReady = true;
                AVObject.this.onDataSynchronized();
            }
            if (this.internalCallback != null) {
                this.internalCallback.internalDone(obj, aVException);
            }
        }

        public void onFailure(Throwable th, String str) {
            if (this.internalCallback != null) {
                this.internalCallback.internalDone(null, AVErrorUtils.createException(th, str));
            }
        }
    }

    private boolean processOperationData() {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r7 = this;
        r4 = 1;
        r5 = 0;
        r1 = r7.lock;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r1 = r1.writeLock();	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r1.lock();	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r1 = r7.instanceData;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r1 = r1.keySet();	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r6 = r1.iterator();	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
    L_0x0015:
        r1 = r6.hasNext();	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        if (r1 == 0) goto L_0x0059;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
    L_0x001b:
        r1 = r6.next();	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r1 = (java.lang.String) r1;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r2 = r7.instanceData;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r3 = r2.get(r1);	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        if (r3 == 0) goto L_0x0015;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
    L_0x0029:
        r2 = r3 instanceof com.avos.avoscloud.AVObject;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        if (r2 == 0) goto L_0x0015;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
    L_0x002d:
        r0 = r3;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r0 = (com.avos.avoscloud.AVObject) r0;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r2 = r0;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r2 = r2.processOperationData();	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        if (r2 == 0) goto L_0x0015;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
    L_0x0037:
        r7.put(r1, r3);	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        goto L_0x0015;
    L_0x003b:
        r1 = move-exception;
        r2 = LOGTAG;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r3 = "";	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        com.avos.avoscloud.LogUtil.log.m3522e(r2, r3, r1);	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r1 = r7.lock;
        r1 = r1.writeLock();
        r1.unlock();
        r1 = r5;
    L_0x004d:
        if (r1 != 0) goto L_0x0057;
    L_0x004f:
        r1 = r7.objectId;
        r1 = com.avos.avoscloud.AVUtils.isBlankString(r1);
        if (r1 == 0) goto L_0x0058;
    L_0x0057:
        r5 = r4;
    L_0x0058:
        return r5;
    L_0x0059:
        r1 = r7.operationQueue;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r1 = r1.isEmpty();	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        if (r1 != 0) goto L_0x006d;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
    L_0x0061:
        r1 = r7.tempDataForServerSaving;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r2 = r7.operationQueue;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r1.putAll(r2);	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r1 = r7.operationQueue;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r1.clear();	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
    L_0x006d:
        r1 = r7.tempDataForServerSaving;	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        r1 = r1.isEmpty();	 Catch:{ Exception -> 0x003b, all -> 0x0082 }
        if (r1 != 0) goto L_0x0080;
    L_0x0075:
        r1 = r4;
    L_0x0076:
        r2 = r7.lock;
        r2 = r2.writeLock();
        r2.unlock();
        goto L_0x004d;
    L_0x0080:
        r1 = r5;
        goto L_0x0076;
    L_0x0082:
        r1 = move-exception;
        r2 = r7.lock;
        r2 = r2.writeLock();
        r2.unlock();
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.avos.avoscloud.AVObject.processOperationData():boolean");
    }

    static {
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        INVALID_KEYS.add("code");
        INVALID_KEYS.add("uuid");
        INVALID_KEYS.add("className");
        INVALID_KEYS.add("keyValues");
        INVALID_KEYS.add("fetchWhenSave");
        INVALID_KEYS.add("running");
        INVALID_KEYS.add("acl");
        INVALID_KEYS.add("ACL");
        INVALID_KEYS.add("isDataReady");
        INVALID_KEYS.add("pendingKeys");
        INVALID_KEYS.add("createdAt");
        INVALID_KEYS.add("updatedAt");
        INVALID_KEYS.add(AVUtils.objectIdTag);
    }

    public AVObject() {
        this.fetchWhenSave = false;
        this.lock = new ReentrantReadWriteLock();
        this.serverData = new HashMap();
        this.operationQueue = new HashMap();
        this.instanceData = new HashMap();
        this.tempDataForServerSaving = new HashMap();
        init();
    }

    public String toString() {
        return JSON.toJSONString(this);
    }

    public JSONObject toJSONObject() {
        Map hashMap = new HashMap();
        for (String str : this.instanceData.keySet()) {
            hashMap.put(str, parseObject(this.instanceData.get(str)));
        }
        hashMap.put(AVUtils.objectIdTag, this.objectId);
        hashMap.put("createdAt", this.createdAt);
        hashMap.put("updatedAt", this.updatedAt);
        hashMap.put("className", this.className);
        return new JSONObject(hashMap);
    }

    private static Object parseObject(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Map) {
            return getParsedMap((Map) obj);
        }
        if (obj instanceof Collection) {
            return getParsedList((Collection) obj);
        }
        if (obj instanceof AVObject) {
            return ((AVObject) obj).toJSONObject();
        }
        if (obj instanceof AVGeoPoint) {
            return AVUtils.mapFromGeoPoint((AVGeoPoint) obj);
        }
        if (obj instanceof Date) {
            return AVUtils.mapFromDate((Date) obj);
        }
        if (obj instanceof byte[]) {
            return AVUtils.mapFromByteArray((byte[]) obj);
        }
        if (obj instanceof AVFile) {
            return ((AVFile) obj).toJSONObject();
        }
        if (obj instanceof JSONObject) {
            return JSON.parse(obj.toString());
        }
        if (obj instanceof JSONArray) {
            return JSON.parse(obj.toString());
        }
        return obj;
    }

    private static List getParsedList(Collection collection) {
        List arrayList = new ArrayList(collection.size());
        for (Object parseObject : collection) {
            arrayList.add(parseObject(parseObject));
        }
        return arrayList;
    }

    private static Map<String, Object> getParsedMap(Map<String, Object> map) {
        Map<String, Object> hashMap = new HashMap(map.size());
        for (Entry entry : map.entrySet()) {
            hashMap.put((String) entry.getKey(), parseObject(entry.getValue()));
        }
        return hashMap;
    }

    Map<String, Object> getServerData() {
        return this.serverData;
    }

    void setServerData(HashMap<String, Object> hashMap) {
        this.serverData = hashMap;
    }

    Map<String, AVOp> getOperationQueue() {
        return this.operationQueue;
    }

    void setOperationQueue(HashMap<String, AVOp> hashMap) {
        this.operationQueue = hashMap;
    }

    boolean isDataReady() {
        return this.isDataReady;
    }

    void setDataReady(boolean z) {
        this.isDataReady = z;
    }

    void setUpdatedAt(String str) {
        this.updatedAt = str;
    }

    void setCreatedAt(String str) {
        this.createdAt = str;
    }

    void setUuid(String str) {
        this.uuid = str;
    }

    public boolean isFetchWhenSave() {
        return this.fetchWhenSave;
    }

    public void setFetchWhenSave(boolean z) {
        this.fetchWhenSave = z;
    }

    public String getUuid() {
        if (AVUtils.isBlankString(this.uuid)) {
            this.uuid = UUID.randomUUID().toString().toLowerCase();
        }
        return this.uuid;
    }

    static Class<? extends AVObject> getSubClass(String str) {
        return (Class) SUB_CLASSES_MAP.get(str);
    }

    static String getSubClassName(Class<? extends AVObject> cls) {
        if (AVUser.class.isAssignableFrom(cls)) {
            return AVUser.userClassName();
        }
        if (AVRole.class.isAssignableFrom(cls)) {
            return AVRole.className;
        }
        return (String) SUB_CLASSES_REVERSE_MAP.get(cls);
    }

    public static <T extends AVObject> void registerSubclass(Class<T> cls) {
        AVClassName aVClassName = (AVClassName) cls.getAnnotation(AVClassName.class);
        if (aVClassName == null) {
            throw new IllegalArgumentException("The class is not annotated by @AVClassName");
        }
        String value = aVClassName.value();
        AVUtils.checkClassName(value);
        SUB_CLASSES_MAP.put(value, cls);
        SUB_CLASSES_REVERSE_MAP.put(cls, value);
    }

    public AVObject(String str) {
        this();
        AVUtils.checkClassName(str);
        this.className = str;
    }

    private void init() {
        this.objectId = "";
        this.isDataReady = false;
        if (PaasClient.storageInstance().getDefaultACL() != null) {
            this.acl = new AVACL(PaasClient.storageInstance().getDefaultACL());
        }
        this.running = false;
    }

    public void add(String str, Object obj) {
        addObjectToArray(str, obj, false);
    }

    public void addAll(String str, Collection<?> collection) {
        for (Object addObjectToArray : collection) {
            addObjectToArray(str, addObjectToArray, false);
        }
    }

    public static <T extends AVObject> AVQuery<T> getQuery(Class<T> cls) {
        return new AVQuery(getSubClassName(cls), cls);
    }

    public void addAllUnique(String str, Collection<?> collection) {
        for (Object addObjectToArray : collection) {
            addObjectToArray(str, addObjectToArray, true);
        }
    }

    public void addUnique(String str, Object obj) {
        addObjectToArray(str, obj, true);
    }

    public boolean containsKey(String str) {
        return get(str) != null;
    }

    public static AVObject create(String str) {
        return new AVObject(str);
    }

    public static AVObject parseAVObject(String str) throws Exception {
        return (AVObject) JSON.parse(str);
    }

    public static AVObject createWithoutData(String str, String str2) {
        AVObject aVObject = new AVObject(str);
        aVObject.setObjectId(str2);
        return aVObject;
    }

    void setClassName(String str) {
        this.className = str;
    }

    public static <T extends AVObject> T createWithoutData(Class<T> cls, String str) throws AVException {
        try {
            AVObject aVObject = (AVObject) cls.newInstance();
            aVObject.setClassName(getSubClassName(cls));
            aVObject.setObjectId(str);
            return aVObject;
        } catch (Throwable e) {
            throw new AVException("Create subclass instance failed.", e);
        }
    }

    public void delete() throws AVException {
        delete(true, false, new C09271());
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public static void deleteAll(Collection<? extends AVObject> collection) throws AVException {
        deleteAll(true, false, collection, new C09282());
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public static void deleteAllInBackground(Collection<? extends AVObject> collection, DeleteCallback deleteCallback) {
        deleteAll(false, false, collection, deleteCallback);
    }

    private static void deleteAll(boolean z, boolean z2, Collection<? extends AVObject> collection, final DeleteCallback deleteCallback) {
        if (collection == null || collection.isEmpty()) {
            deleteCallback.internalDone(null, null);
        } else if (z2) {
            for (AVObject aVObject : collection) {
                if (aVObject != null) {
                    aVObject.deleteEventually(deleteCallback);
                }
            }
        } else {
            boolean z3 = true;
            StringBuilder stringBuilder = new StringBuilder();
            String str = null;
            for (Object obj : collection) {
                if (AVUtils.isBlankString(obj.getClassName()) || AVUtils.isBlankString(obj.objectId)) {
                    throw new IllegalArgumentException("Invalid AVObject, the class name or objectId is blank.");
                }
                boolean z4;
                if (str == null) {
                    str = obj.getClassName();
                } else if (!str.equals(obj.getClassName())) {
                    throw new IllegalArgumentException("The objects class name must be the same.");
                }
                if (z3) {
                    stringBuilder.append(AVPowerfulUtils.getEndpoint(obj));
                    z4 = false;
                } else {
                    stringBuilder.append(",").append(obj.getObjectId());
                    z4 = z3;
                }
                z3 = z4;
            }
            PaasClient.storageInstance().deleteObject(stringBuilder.toString(), z, false, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    if (deleteCallback != null) {
                        deleteCallback.internalDone(null, null);
                    }
                }

                public void onFailure(Throwable th, String str) {
                    if (deleteCallback != null) {
                        deleteCallback.internalDone(null, AVErrorUtils.createException(th, str));
                    }
                }
            }, null, null);
        }
    }

    public void deleteEventually() {
        deleteEventually(null);
    }

    public void deleteEventually(DeleteCallback deleteCallback) {
        delete(false, true, deleteCallback);
    }

    public void deleteInBackground() {
        deleteInBackground(null);
    }

    public void deleteInBackground(DeleteCallback deleteCallback) {
        delete(false, false, deleteCallback);
    }

    private void delete(boolean z, boolean z2, final DeleteCallback deleteCallback) {
        PaasClient.storageInstance().deleteObject(AVPowerfulUtils.getEndpoint((Object) this), z, z2, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                if (deleteCallback != null) {
                    deleteCallback.internalDone(null, null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (deleteCallback != null) {
                    deleteCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        }, getObjectId(), internalId());
    }

    public AVObject fetch() throws AVException {
        return fetch(null);
    }

    public AVObject fetch(String str) throws AVException {
        fetchInBackground(true, str, new C09315());
        if (!AVExceptionHolder.exists()) {
            return this;
        }
        throw AVExceptionHolder.remove();
    }

    public static List<AVObject> fetchAll(List<AVObject> list) throws AVException {
        List<AVObject> linkedList = new LinkedList();
        for (AVObject fetch : list) {
            linkedList.add(fetch.fetch());
        }
        return linkedList;
    }

    public static List<AVObject> fetchAllIfNeeded(List<AVObject> list) throws AVException {
        List<AVObject> linkedList = new LinkedList();
        for (AVObject fetchIfNeeded : list) {
            linkedList.add(fetchIfNeeded.fetchIfNeeded());
        }
        return linkedList;
    }

    public static void fetchAllIfNeededInBackground(List<AVObject> list, final FindCallback<AVObject> findCallback) {
        final List arrayList = new ArrayList();
        fetchAllInBackground(true, list, new GenericObjectCallback() {
            public void onGroupRequestFinished(int i, int i2, AVObject aVObject) {
                if (aVObject != null) {
                    arrayList.add(aVObject);
                }
                if (i <= 0 && findCallback != null) {
                    findCallback.internalDone(arrayList, null);
                }
            }
        });
    }

    public static void fetchAllInBackground(List<AVObject> list, final FindCallback<AVObject> findCallback) {
        final List arrayList = new ArrayList();
        fetchAllInBackground(false, list, new GenericObjectCallback() {
            public void onGroupRequestFinished(int i, int i2, AVObject aVObject) {
                if (aVObject != null) {
                    arrayList.add(aVObject);
                }
                if (i <= 0 && findCallback != null) {
                    findCallback.internalDone(arrayList, null);
                }
            }
        });
    }

    private static void fetchAllInBackground(boolean z, List<AVObject> list, final GenericObjectCallback genericObjectCallback) {
        final int size = list.size();
        final AtomicInteger atomicInteger = new AtomicInteger(list.size());
        for (AVObject aVObject : list) {
            if (!z || !aVObject.isDataAvailable()) {
                aVObject.fetchInBackground(false, null, new GetCallback<AVObject>() {
                    public void done(AVObject aVObject, AVException aVException) {
                        if (genericObjectCallback != null) {
                            genericObjectCallback.onGroupRequestFinished(atomicInteger.decrementAndGet(), size, aVObject);
                        }
                    }
                });
            } else if (genericObjectCallback != null) {
                genericObjectCallback.onGroupRequestFinished(atomicInteger.decrementAndGet(), size, aVObject);
            }
        }
        if (list.size() <= 0 && genericObjectCallback != null) {
            genericObjectCallback.onGroupRequestFinished(0, 0, null);
        }
    }

    public AVObject fetchIfNeeded() throws AVException {
        return fetchIfNeeded(null);
    }

    public AVObject fetchIfNeeded(String str) throws AVException {
        if (!isDataAvailable()) {
            fetchInBackground(true, str, new C09359());
        }
        if (!AVExceptionHolder.exists()) {
            return this;
        }
        throw AVExceptionHolder.remove();
    }

    public void fetchIfNeededInBackground(GetCallback<AVObject> getCallback) {
        fetchIfNeededInBackground(null, getCallback);
    }

    public void fetchIfNeededInBackground(String str, GetCallback<AVObject> getCallback) {
        if (!isDataAvailable()) {
            fetchInBackground(str, getCallback);
        } else if (getCallback != null) {
            getCallback.internalDone(this, null);
        }
    }

    public void fetchInBackground(GetCallback<AVObject> getCallback) {
        fetchInBackground(null, getCallback);
    }

    public void fetchInBackground(String str, GetCallback<AVObject> getCallback) {
        fetchInBackground(false, str, getCallback);
    }

    private void fetchInBackground(boolean z, String str, GetCallback<AVObject> getCallback) {
        if (!AVUtils.isBlankString(getObjectId())) {
            Map hashMap = new HashMap();
            if (!AVUtils.isBlankString(str)) {
                hashMap.put("include", str);
            }
            PaasClient.storageInstance().getObject(AVPowerfulUtils.getEndpoint((Object) this), new RequestParams(hashMap), z, headerMap(), new FetchObjectCallback(getCallback));
        } else if (getCallback != null) {
            getCallback.internalDone(null, AVErrorUtils.createException(104, "Missing objectId"));
        }
    }

    public Object get(String str) {
        Object obj = null;
        try {
            this.lock.readLock().lock();
            obj = this.instanceData.get(str);
        } catch (Exception e) {
            log.m3522e(LOGTAG, "", e);
        } finally {
            this.lock.readLock().unlock();
        }
        return obj;
    }

    public AVACL getACL() {
        return this.acl;
    }

    public boolean getBoolean(String str) {
        Boolean bool = (Boolean) get(str);
        return bool == null ? false : bool.booleanValue();
    }

    public byte[] getBytes(String str) {
        return (byte[]) get(str);
    }

    public String getClassName() {
        if (AVUtils.isBlankString(this.className)) {
            this.className = getSubClassName(getClass());
        }
        return this.className;
    }

    public Date getCreatedAt() {
        return AVUtils.dateFromString(this.createdAt);
    }

    public Date getDate(String str) {
        return (Date) get(str);
    }

    public double getDouble(String str) {
        Number number = (Number) get(str);
        if (number != null) {
            return number.doubleValue();
        }
        return 0.0d;
    }

    public int getInt(String str) {
        Number number = (Number) get(str);
        if (number != null) {
            return number.intValue();
        }
        return 0;
    }

    public JSONArray getJSONArray(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        if (obj instanceof Collection) {
            return new JSONArray((Collection) obj);
        }
        if (!(obj instanceof Object[])) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object put : (Object[]) obj) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    public JSONObject getJSONObject(String str) {
        Object obj = get(str);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        try {
            return new JSONObject(JSON.toJSONString(obj));
        } catch (Throwable e) {
            throw new IllegalStateException("Invalid json string", e);
        }
    }

    public List getList(String str) {
        return (List) get(str);
    }

    public <T extends AVObject> List<T> getList(String str, Class<T> cls) {
        List<AVObject> list = getList(str);
        if (list == null) {
            return null;
        }
        List<T> linkedList = new LinkedList();
        try {
            for (AVObject cast : list) {
                linkedList.add(cast(cast, cls));
            }
            return linkedList;
        } catch (Exception e) {
            log.m3520e("ClassCast Exception", e);
            return linkedList;
        }
    }

    public long getLong(String str) {
        Number number = (Number) get(str);
        if (number != null) {
            return number.longValue();
        }
        return 0;
    }

    public <V> Map<String, V> getMap(String str) {
        return (Map) get(str);
    }

    public Number getNumber(String str) {
        return (Number) get(str);
    }

    public String getObjectId() {
        return this.objectId;
    }

    public <T extends AVFile> T getAVFile(String str) {
        return (AVFile) get(str);
    }

    public AVGeoPoint getAVGeoPoint(String str) {
        return (AVGeoPoint) get(str);
    }

    public <T extends AVObject> T getAVObject(String str) {
        return (AVObject) get(str);
    }

    public <T extends AVObject> T getAVObject(String str, Class<T> cls) throws Exception {
        T aVObject = getAVObject(str);
        if (aVObject == null) {
            return null;
        }
        return !cls.isInstance(aVObject) ? cast(this, cls) : aVObject;
    }

    public <T extends AVUser> T getAVUser(String str) {
        return (AVUser) get(str);
    }

    public <T extends AVUser> T getAVUser(String str, Class<T> cls) {
        AVUser aVUser = (AVUser) get(str);
        return aVUser == null ? null : AVUser.cast(aVUser, cls);
    }

    public <T extends AVObject> AVRelation<T> getRelation(String str) {
        if (!checkKey(str)) {
            return null;
        }
        Object obj = get(str);
        if (obj == null || !(obj instanceof AVRelation)) {
            AVRelation<T> aVRelation = new AVRelation(this, str);
            this.instanceData.put(str, aVRelation);
            return aVRelation;
        }
        ((AVRelation) obj).setParent(this);
        return (AVRelation) obj;
    }

    public String getString(String str) {
        Object obj = get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public Date getUpdatedAt() {
        return AVUtils.dateFromString(this.updatedAt);
    }

    public boolean has(String str) {
        return get(str) != null;
    }

    public boolean hasSameId(AVObject aVObject) {
        return aVObject.objectId.equals(this.objectId);
    }

    public void increment(String str) {
        increment(str, Integer.valueOf(1));
    }

    public void increment(final String str, final Number number) {
        if (checkKey(str)) {
            new KeyValueCallback() {
                public AVOp createOp() {
                    return new IncrementOp(str, number);
                }
            }.execute(str);
        }
    }

    public boolean isDataAvailable() {
        return !AVUtils.isBlankString(this.objectId) && this.isDataReady;
    }

    public Set<String> keySet() {
        return this.instanceData.keySet();
    }

    private boolean checkKey(String str) {
        if (AVUtils.isBlankString(str)) {
            throw new IllegalArgumentException("Blank key");
        } else if (str.startsWith("_")) {
            throw new IllegalArgumentException("key should not start with '_'");
        } else {
            if (INVALID_KEYS.contains(str)) {
                log.m3525w("Internal key name:`" + str + "`,please use setter/getter for it.");
            }
            return !INVALID_KEYS.contains(str);
        }
    }

    public void put(String str, Object obj) {
        put(str, obj, true);
    }

    protected void put(final String str, final Object obj, boolean z) {
        if (checkKey(str)) {
            new KeyValueCallback() {
                public AVOp createOp() {
                    return new SetOp(str, obj);
                }
            }.execute(str, z);
        }
    }

    public void refresh() throws AVException {
        refresh(null);
    }

    public void refresh(String str) throws AVException {
        refreshInBackground(true, str, new RefreshCallback<AVObject>() {
            public void done(AVObject aVObject, AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                }
            }

            protected boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public void refreshInBackground(RefreshCallback<AVObject> refreshCallback) {
        refreshInBackground(false, null, refreshCallback);
    }

    public void refreshInBackground(String str, RefreshCallback<AVObject> refreshCallback) {
        refreshInBackground(false, str, refreshCallback);
    }

    private void refreshInBackground(boolean z, String str, RefreshCallback<AVObject> refreshCallback) {
        Map hashMap = new HashMap();
        if (!AVUtils.isBlankString(str)) {
            hashMap.put("include", str);
        }
        PaasClient.storageInstance().getObject(AVPowerfulUtils.getEndpoint((Object) this), new RequestParams(hashMap), z, headerMap(), new FetchObjectCallback(refreshCallback));
    }

    public void remove(String str) {
        removeObjectForKey(str);
    }

    public void removeAll(final String str, final Collection<?> collection) {
        if (checkKey(str)) {
            new KeyValueCallback() {
                public AVOp createOp() {
                    return new RemoveOp(str, collection);
                }
            }.execute(str);
        }
    }

    public void save() throws AVException {
        saveObject(true, false, new SaveCallback() {
            public void done(AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                }
            }

            protected boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public static void saveAll(List<? extends AVObject> list) throws AVException {
        _saveAll(true, list, new SaveCallback() {
            public void done(AVException aVException) {
                if (aVException != null) {
                    AVExceptionHolder.add(aVException);
                }
            }

            protected boolean mustRunOnUIThread() {
                return false;
            }
        });
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public static void saveAllInBackground(List<? extends AVObject> list) {
        _saveAll(false, list, null);
    }

    public static void saveAllInBackground(List<? extends AVObject> list, SaveCallback saveCallback) {
        _saveAll(false, list, saveCallback);
    }

    private static void _saveAll(final boolean z, final List<? extends AVObject> list, final SaveCallback saveCallback) {
        final List linkedList = new LinkedList();
        List linkedList2 = new LinkedList();
        for (AVObject aVObject : list) {
            if (aVObject.checkCircleReference()) {
                if (aVObject.processOperationData()) {
                    Collection filesToSave = aVObject.getFilesToSave();
                    if (!AVUtils.isEmptyList(filesToSave)) {
                        linkedList2.addAll(filesToSave);
                    }
                }
            } else if (saveCallback != null) {
                saveCallback.internalDone(AVErrorUtils.circleException());
                return;
            } else {
                return;
            }
        }
        final GenericObjectCallback anonymousClass16 = new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                for (AVObject aVObject : list) {
                    aVObject.copyFromJson(str);
                    aVObject.transferDataToServerData();
                    aVObject.running = false;
                    aVObject.onSaveSuccess();
                }
                if (saveCallback != null) {
                    saveCallback.done(null);
                }
            }

            public void onFailure(Throwable th, String str) {
                for (AVObject aVObject : list) {
                    aVObject.running = false;
                    aVObject.rollbackDataToOperationQueue();
                    aVObject.onSaveFailure();
                }
                log.m3514d(str);
                if (saveCallback != null) {
                    saveCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        };
        if (linkedList2 != null) {
            try {
                if (linkedList2.size() > 0) {
                    saveFileBeforeSave(linkedList2, z, new SaveCallback() {
                        public void done(AVException aVException) {
                            for (AVObject aVObject : list) {
                                aVObject.running = true;
                                aVObject.buildParameterForNonSavedObject(linkedList);
                            }
                            PaasClient.storageInstance().postBatchSave(linkedList, z, false, null, anonymousClass16, null, null);
                        }
                    });
                    return;
                }
            } catch (AVException e) {
                if (saveCallback != null) {
                    saveCallback.internalDone(e);
                    return;
                }
                return;
            }
        }
        for (AVObject aVObject2 : list) {
            aVObject2.running = true;
            aVObject2.buildParameterForNonSavedObject(linkedList);
        }
        PaasClient.storageInstance().postBatchSave(linkedList, z, false, null, anonymousClass16, null, null);
    }

    public void saveEventually() {
        saveEventually(null);
    }

    public void saveEventually(SaveCallback saveCallback) {
        PaasClient.registerEventuallyObject(this);
        saveInBackground(saveCallback, true);
    }

    protected void onSaveSuccess() {
    }

    protected void onDataSynchronized() {
    }

    protected void onSaveFailure() {
    }

    protected Map<String, String> headerMap() {
        return PaasClient.storageInstance().userHeaderMap();
    }

    private void saveObject(final boolean z, final boolean z2, final SaveCallback saveCallback) {
        if (this.running) {
            if (saveCallback != null) {
                saveCallback.internalDone(new AVException(-1, "already has one request sending"));
            }
        } else if (processOperationData()) {
            this.running = true;
            try {
                List filesToSave = getFilesToSave();
                if (filesToSave == null || filesToSave.size() <= 0) {
                    _saveObject(z, z2, saveCallback);
                } else {
                    saveFileBeforeSave(filesToSave, z, new SaveCallback() {
                        public void done(AVException aVException) {
                            AVObject.this._saveObject(z, z2, saveCallback);
                        }
                    });
                }
            } catch (AVException e) {
                if (saveCallback != null) {
                    saveCallback.internalDone(e);
                }
            }
        } else if (saveCallback != null) {
            saveCallback.internalDone(null);
        }
    }

    private List<AVFile> getFilesToSave() {
        List<AVFile> linkedList = new LinkedList();
        for (Entry value : this.tempDataForServerSaving.entrySet()) {
            Object values = ((AVOp) value.getValue()).getValues();
            if (values != null && (values instanceof AVObject)) {
                Collection filesToSave = ((AVObject) values).getFilesToSave();
                if (filesToSave != null && filesToSave.size() > 0) {
                    linkedList.addAll(filesToSave);
                }
            } else if (values != null && AVFile.class.isInstance(values)) {
                AVFile aVFile = (AVFile) values;
                if (aVFile.getObjectId() == null) {
                    linkedList.add(aVFile);
                }
            }
        }
        return linkedList;
    }

    private void _saveObject(boolean z, boolean z2, SaveCallback saveCallback) {
        List linkedList = new LinkedList();
        buildParameterForNonSavedObject(linkedList);
        saveObjectToAVOSCloud(linkedList, z, z2, saveCallback);
    }

    private void saveObjectToAVOSCloud(List<Map<String, Object>> list, boolean z, boolean z2, final SaveCallback saveCallback) {
        for (Map map : list) {
            if (((String) ((Map) map.get("body")).get("__internalId")).length() == UUID_LEN) {
                map.put("new", Boolean.valueOf(true));
            }
        }
        PaasClient.storageInstance().postBatchSave(list, z, z2, headerMap(), new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                AVObject.this.running = false;
                AVObject.this.transferDataToServerData();
                AVObject.this.copyFromJson(str);
                AVObject.this.onSaveSuccess();
                if (saveCallback != null) {
                    saveCallback.internalDone(aVException);
                }
            }

            public void onFailure(Throwable th, String str) {
                AVObject.this.running = false;
                AVObject.this.rollbackDataToOperationQueue();
                if (saveCallback != null) {
                    if (AVObject.this.shouldThrowException(th, str)) {
                        saveCallback.internalDone(AVErrorUtils.createException(th, str));
                    } else {
                        saveCallback.internalDone(null);
                    }
                }
                AVObject.this.onSaveFailure();
            }
        }, getObjectId(), internalId());
    }

    private void transferDataToServerData() {
        try {
            this.lock.writeLock().lock();
            for (String str : this.tempDataForServerSaving.keySet()) {
                this.serverData.put(str, ((AVOp) this.tempDataForServerSaving.get(str)).apply(this.serverData.get(str)));
            }
            this.tempDataForServerSaving.clear();
        } catch (Exception e) {
            log.m3522e(LOGTAG, "", e);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    private void rollbackDataToOperationQueue() {
        try {
            this.lock.writeLock().lock();
            for (String str : this.operationQueue.keySet()) {
                Object obj = (AVOp) this.operationQueue.get(str);
                AVOp aVOp = (AVOp) this.tempDataForServerSaving.get(str);
                if (aVOp != null) {
                    obj = aVOp.merge(obj);
                }
                this.tempDataForServerSaving.put(str, obj);
            }
            this.operationQueue.clear();
            this.operationQueue.putAll(this.tempDataForServerSaving);
            this.tempDataForServerSaving.clear();
        } catch (Exception e) {
            log.m3522e(LOGTAG, "", e);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    private AVException copyFromJsonArray(String str) {
        try {
            Iterator it = ((ArrayList) AVUtils.getFromJSON(str, ArrayList.class)).iterator();
            while (it.hasNext()) {
                Map map = (Map) it.next();
                Map map2 = (Map) map.get("success");
                if (map2 != null) {
                    AVUtils.copyPropertiesFromMapToAVObject(map2, this);
                } else {
                    map = (Map) map.get("error");
                    return AVErrorUtils.createException(((Number) map.get("code")).intValue(), (String) map.get("error"));
                }
            }
        } catch (Exception e) {
            log.m3520e("parse jsonArray exception", e);
        }
        return null;
    }

    protected void copyFromJson(String str) {
        try {
            copyFromMap((Map) AVUtils.getFromJSON(str, Map.class));
        } catch (Exception e) {
            log.m3520e("AVObject parse error", e);
        }
    }

    protected void copyFromMap(Map map) {
        try {
            this.lock.writeLock().lock();
            Object obj = map.get(this.uuid);
            if (obj != null && (obj instanceof Map)) {
                AVUtils.copyPropertiesFromMapToAVObject((Map) obj, this);
            }
            obj = map.get(getObjectId());
            if (obj != null && (obj instanceof Map)) {
                AVUtils.copyPropertiesFromMapToAVObject((Map) obj, this);
            }
            for (Object obj2 : this.instanceData.values()) {
                if (obj2 instanceof AVObject) {
                    ((AVObject) obj2).copyFromMap(map);
                }
            }
        } catch (Exception e) {
            log.m3522e(LOGTAG, "", e);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    protected boolean alwaysUsePost() {
        return false;
    }

    protected String internalId() {
        return AVUtils.isBlankString(getObjectId()) ? getUuid() : getObjectId();
    }

    protected boolean alwaysSaveAllKeyValues() {
        return false;
    }

    protected void buildBatchParameterForNonSavedObject(List<AVObject> list, List list2) {
        if (alwaysUsePost()) {
            if (!this.instanceData.isEmpty()) {
                Map hashMap = new HashMap();
                List arrayList = new ArrayList();
                for (Entry entry : this.instanceData.entrySet()) {
                    parseObjectValue(list, hashMap, arrayList, entry.getValue(), (String) entry.getKey());
                }
                mergeRequestQueue(wrapperRequestBody(hashMap, arrayList, false), list2);
            }
        } else if (!this.tempDataForServerSaving.isEmpty() || AVUtils.isBlankString(this.objectId)) {
            Map hashMap2 = new HashMap();
            List arrayList2 = new ArrayList();
            for (Entry entry2 : this.tempDataForServerSaving.entrySet()) {
                parseOperation(hashMap2, (String) entry2.getKey(), (AVOp) entry2.getValue(), arrayList2, list, list2);
            }
            mergeRequestQueue(wrapperRequestBody(hashMap2, arrayList2, false), list2);
        }
    }

    private void parseObjectValue(List<AVObject> list, Map<String, Object> map, List<Map<String, String>> list2, Object obj, String str) {
        if (obj instanceof AVObject) {
            AVObject aVObject = (AVObject) obj;
            list2.add(AVUtils.mapFromChildObject(aVObject, str));
            if (aVObject.processOperationData()) {
                list.add(aVObject);
            }
        } else if (obj instanceof AVGeoPoint) {
            map.put(str, AVUtils.mapFromGeoPoint((AVGeoPoint) obj));
        } else if (obj instanceof Date) {
            map.put(str, AVUtils.mapFromDate((Date) obj));
        } else if (obj instanceof byte[]) {
            map.put(str, AVUtils.mapFromByteArray((byte[]) obj));
        } else if (obj instanceof AVFile) {
            map.put(str, AVUtils.mapFromFile((AVFile) obj));
        } else {
            map.put(str, AVUtils.getParsedObject(obj));
        }
    }

    private Map<String, Object> parseOperation(Map<String, Object> map, String str, AVOp aVOp, List list, List list2, List list3) {
        Object values = aVOp.getValues();
        if (!(aVOp instanceof CollectionOp) && !(aVOp instanceof IncrementOp)) {
            parseObjectValue(list2, map, list, values, str);
        } else if ((aVOp instanceof IncrementOp) || (aVOp instanceof AddOp) || (aVOp instanceof RemoveOp) || (aVOp instanceof AddRelationOp) || (aVOp instanceof RemoveRelationOp) || (aVOp instanceof AddUniqueOp)) {
            map.putAll(aVOp.encodeOp());
        } else if (aVOp instanceof CompoundOp) {
            List values2 = ((CompoundOp) aVOp).getValues();
            if (!AVUtils.isEmptyList(values2)) {
                parseOperation(map, str, (AVOp) values2.get(0), list, list2, list3);
            }
            for (int i = 1; i < values2.size(); i++) {
                AVOp aVOp2 = (AVOp) values2.get(i);
                Map hashMap = new HashMap();
                List arrayList = new ArrayList();
                parseOperation(hashMap, str, aVOp2, arrayList, list2, list3);
                mergeRequestQueue(wrapperRequestBody(hashMap, arrayList, true), list3);
            }
        }
        return map;
    }

    private void mergeRequestQueue(Map<String, Object> map, List list) {
        if (!map.isEmpty()) {
            list.add(0, map);
        }
    }

    private Map<String, Object> wrapperRequestBody(Map<String, Object> map, List list, boolean z) {
        map.put("__children", list);
        if (this.acl != null) {
            map.putAll(AVUtils.getParsedMap(this.acl.getACLMap()));
        }
        map.put("__internalId", internalId());
        String str = "PUT";
        boolean z2 = (AVUtils.isBlankString(getObjectId()) || alwaysUsePost()) && !z;
        if (z2) {
            str = "POST";
        }
        return PaasClient.storageInstance().batchItemMap(str, AVPowerfulUtils.getBatchEndpoint(PaasClient.storageInstance().getApiVersion(), this, z2), map, getBatchParams());
    }

    private Map getBatchParams() {
        if (!this.fetchWhenSave) {
            return null;
        }
        Map hashMap = new HashMap();
        hashMap.put("new", Boolean.valueOf(this.fetchWhenSave));
        return hashMap;
    }

    private void buildParameterForNonSavedObject(List list) {
        List<AVObject> linkedList = new LinkedList();
        buildBatchParameterForNonSavedObject(linkedList, list);
        for (AVObject buildParameterForNonSavedObject : linkedList) {
            buildParameterForNonSavedObject.buildParameterForNonSavedObject(list);
        }
    }

    private boolean checkCircleReference() {
        return checkCircleReference(new HashMap());
    }

    private boolean checkCircleReference(Map<AVObject, Boolean> map) {
        if (map.get(this) == null) {
            map.put(this, Boolean.valueOf(false));
            boolean z = true;
            for (Object next : this.instanceData.values()) {
                boolean z2;
                if (!(next instanceof AVObject)) {
                    z2 = z;
                } else if (z && ((AVObject) next).checkCircleReference(map)) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                z = z2;
            }
            map.put(this, Boolean.valueOf(true));
            return z;
        } else if (((Boolean) map.get(this)).booleanValue()) {
            return true;
        } else {
            log.m3519e("Found a circular dependency while saving");
            return false;
        }
    }

    public void saveInBackground() {
        saveInBackground(null);
    }

    public void saveInBackground(SaveCallback saveCallback) {
        saveInBackground(saveCallback, false);
    }

    private void saveInBackground(SaveCallback saveCallback, boolean z) {
        saveObject(false, z, saveCallback);
    }

    public void setACL(AVACL avacl) {
        this.acl = avacl;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    private List findArray(Map<String, Object> map, String str, boolean z) {
        List list;
        Exception exception;
        Exception exception2;
        try {
            list = (List) map.get(str);
            if (list != null || !z) {
                return list;
            }
            try {
                ArrayList arrayList = new ArrayList();
                try {
                    map.put(str, arrayList);
                    return arrayList;
                } catch (Exception e) {
                    exception = e;
                    Object obj = arrayList;
                    exception2 = exception;
                    log.m3522e(LOGTAG, "find array failed.", exception2);
                    return list;
                }
            } catch (Exception e2) {
                exception2 = e2;
                log.m3522e(LOGTAG, "find array failed.", exception2);
                return list;
            }
        } catch (Exception e3) {
            exception = e3;
            list = null;
            exception2 = exception;
            log.m3522e(LOGTAG, "find array failed.", exception2);
            return list;
        }
    }

    protected String internalClassName() {
        return getClassName();
    }

    protected boolean shouldThrowException(Throwable th, String str) {
        return true;
    }

    void addRelationFromServer(final String str, String str2, boolean z) {
        if (checkKey(str)) {
            new KeyValueCallback() {
                public AVOp createOp() {
                    return new AddRelationOp(str, new AVObject[0]);
                }
            }.execute(str, z);
        }
    }

    void addRelation(final AVObject aVObject, final String str, boolean z) {
        if (checkKey(str)) {
            new KeyValueCallback() {
                public AVOp createOp() {
                    return new AddRelationOp(str, aVObject);
                }
            }.execute(str, z);
        }
    }

    void removeRelation(final AVObject aVObject, final String str, boolean z) {
        if (checkKey(str)) {
            new KeyValueCallback() {
                public AVOp createOp() {
                    return new RemoveRelationOp(str, aVObject);
                }
            }.execute(str, z);
        }
    }

    private void addObjectToArray(final String str, final Object obj, final boolean z) {
        if (checkKey(str)) {
            new KeyValueCallback() {
                public AVOp createOp() {
                    if (z) {
                        return new AddUniqueOp(str, obj);
                    }
                    return new AddOp(str, obj);
                }
            }.execute(str);
        }
    }

    private void removeObjectForKey(final String str) {
        if (checkKey(str)) {
            new KeyValueCallback() {
                public AVOp createOp() {
                    return new DeleteOp(str);
                }
            }.execute(str);
        }
    }

    public static void saveFileBeforeSave(List<AVFile> list, boolean z, final SaveCallback saveCallback) throws AVException {
        if (z) {
            for (AVFile aVFile : list) {
                if (aVFile != null) {
                    aVFile.save();
                }
            }
            saveCallback.done(null);
            return;
        }
        final AtomicInteger atomicInteger = new AtomicInteger(AVUtils.collectionNonNullCount(list));
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        for (AVFile aVFile2 : list) {
            if (aVFile2 != null) {
                aVFile2.saveInBackground(new SaveCallback() {
                    public void done(AVException aVException) {
                        if (aVException != null && atomicBoolean.compareAndSet(false, true)) {
                            saveCallback.done(aVException);
                        } else if (aVException == null && atomicInteger.decrementAndGet() == 0) {
                            saveCallback.done(null);
                        }
                    }
                });
            }
        }
    }

    public int hashCode() {
        int i = 0;
        if (AVUtils.isBlankString(this.objectId)) {
            return super.hashCode();
        }
        int hashCode = ((getClassName() == null ? 0 : getClassName().hashCode()) + 31) * 31;
        if (this.objectId != null) {
            i = this.objectId.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (AVUtils.isBlankString(this.objectId)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AVObject aVObject = (AVObject) obj;
        if (getClassName() == null) {
            if (aVObject.getClassName() != null) {
                return false;
            }
        } else if (!getClassName().equals(aVObject.getClassName())) {
            return false;
        }
        if (this.objectId == null) {
            if (aVObject.objectId != null) {
                return false;
            }
        } else if (!this.objectId.equals(aVObject.objectId)) {
            return false;
        }
        return true;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.className);
        parcel.writeString(this.createdAt);
        parcel.writeString(this.updatedAt);
        parcel.writeString(this.objectId);
        parcel.writeString(JSON.toJSONString(this.serverData, new ObjectValueFilter(), SerializerFeature.NotWriteRootClassName, SerializerFeature.WriteClassName));
        parcel.writeString(JSON.toJSONString(this.operationQueue, SerializerFeature.WriteClassName, SerializerFeature.NotWriteRootClassName));
    }

    public AVObject(Parcel parcel) {
        this();
        this.className = parcel.readString();
        this.createdAt = parcel.readString();
        this.updatedAt = parcel.readString();
        this.objectId = parcel.readString();
        Map map = (Map) JSON.parse(parcel.readString());
        if (!(map == null || map.isEmpty())) {
            this.serverData.putAll(map);
        }
        map = (Map) JSON.parse(parcel.readString());
        if (!(map == null || map.isEmpty())) {
            this.operationQueue.putAll(map);
        }
        rebuildInstanceData();
    }

    protected void rebuildInstanceData() {
        try {
            this.lock.writeLock().lock();
            this.instanceData.putAll(this.serverData);
            for (Entry entry : this.operationQueue.entrySet()) {
                String str = (String) entry.getKey();
                Object apply = ((AVOp) entry.getValue()).apply(this.instanceData.get(str));
                if (apply == null) {
                    this.instanceData.remove(str);
                } else {
                    this.instanceData.put(str, apply);
                }
            }
        } catch (Exception e) {
            log.m3522e(LOGTAG, "", e);
        } finally {
            this.lock.writeLock().unlock();
        }
    }

    protected static <T extends AVObject> T cast(AVObject aVObject, Class<T> cls) throws Exception {
        if (cls.getClass().isAssignableFrom(aVObject.getClass())) {
            return aVObject;
        }
        AVObject aVObject2 = (AVObject) cls.newInstance();
        aVObject2.operationQueue.putAll(aVObject.operationQueue);
        aVObject2.serverData.putAll(aVObject.serverData);
        aVObject2.createdAt = aVObject.createdAt;
        aVObject2.updatedAt = aVObject.updatedAt;
        aVObject2.objectId = aVObject.objectId;
        aVObject2.rebuildInstanceData();
        return aVObject2;
    }
}
