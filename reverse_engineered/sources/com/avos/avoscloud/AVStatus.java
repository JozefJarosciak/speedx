package com.avos.avoscloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.packet.C0861d;
import com.avos.avoscloud.LogUtil.log;
import com.google.android.gms.actions.SearchIntents;
import com.loopj.android.http.RequestParams;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AVStatus extends AVObject {
    private static final String AV_CLASS_NAME = "_FeedStatus";
    private static int DEFAULT_COUNT = 100;
    public static final String IMAGE_TAG = "image";
    @Deprecated
    public static final String INBOX_PRIVATE = "private";
    @Deprecated
    public static final String INBOX_TIMELINE = "default";
    public static final String MESSAGE_TAG = "message";
    public static final String STATUS_END_POINT = "statuses";
    private static final String UNREAD_TAG = "unread";
    static List<String> ignoreList = Arrays.asList(new String[]{AVUtils.objectIdTag, "updatedAt", "createdAt", "inboxType", "messageId"});
    private String createdAt;
    private final Map<String, Object> dataMap = new ConcurrentHashMap();
    private String inboxType;
    private long messageId = 0;
    private AVQuery query = null;
    private AVObject source = null;

    /* renamed from: com.avos.avoscloud.AVStatus$2 */
    static class C09472 extends DeleteCallback {
        C09472() {
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

    /* renamed from: com.avos.avoscloud.AVStatus$8 */
    class C09538 extends DeleteCallback {
        C09538() {
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

    public enum INBOX_TYPE {
        TIMELINE("default"),
        PRIVATE(AVStatus.INBOX_PRIVATE);
        
        private String type;

        private INBOX_TYPE(String str) {
            this.type = str;
        }

        public String toString() {
            return this.type;
        }
    }

    public static AVStatus createStatus(String str, String str2) {
        AVStatus aVStatus = new AVStatus();
        aVStatus.setImageUrl(str);
        aVStatus.setMessage(str2);
        return aVStatus;
    }

    public static AVStatus createStatusWithData(Map<String, Object> map) {
        AVStatus aVStatus = new AVStatus();
        aVStatus.setData(map);
        return aVStatus;
    }

    private static boolean checkCurrentUser(AVCallback aVCallback) {
        if (AVUser.getCurrentUser() != null) {
            return true;
        }
        if (aVCallback != null) {
            aVCallback.internalDone(null, AVErrorUtils.sessionMissingException());
        }
        return false;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public Date getCreatedAt() {
        return AVUtils.dateFromString(this.createdAt);
    }

    protected void setCreatedAt(String str) {
        this.createdAt = str;
    }

    public void setImageUrl(String str) {
        if (str != null) {
            this.dataMap.put(IMAGE_TAG, str);
        }
    }

    public String getImageUrl() {
        Object obj = this.dataMap.get(IMAGE_TAG);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public AVUser getSource() {
        return (AVUser) this.source;
    }

    public void setSource(AVObject aVObject) {
        this.source = aVObject;
    }

    public void setInboxType(String str) {
        if (str != null) {
            this.inboxType = str;
        }
    }

    public void setQuery(AVQuery aVQuery) {
        this.query = aVQuery;
    }

    public void setMessage(String str) {
        if (str != null) {
            this.dataMap.put(MESSAGE_TAG, str);
        }
    }

    public String getMessage() {
        Object obj = this.dataMap.get(MESSAGE_TAG);
        if (obj instanceof String) {
            return (String) obj;
        }
        return null;
    }

    public void setData(Map<String, Object> map) {
        this.dataMap.putAll(map);
    }

    public Map<String, Object> getData() {
        return this.dataMap;
    }

    public void put(String str, Object obj) {
        this.dataMap.put(str, obj);
    }

    public void remove(String str) {
        this.dataMap.remove(str);
    }

    public long getMessageId() {
        return this.messageId;
    }

    protected void setMessageId(long j) {
        this.messageId = j;
    }

    public String getInboxType() {
        return this.inboxType;
    }

    public void deleteStatusInBackground(DeleteCallback deleteCallback) {
        deleteStatusWithIDInBackgroud(this.objectId, deleteCallback);
    }

    public static void deleteStatusWithIDInBackgroud(String str, DeleteCallback deleteCallback) {
        deleteStatusWithId(false, str, deleteCallback);
    }

    private static void deleteStatusWithId(boolean z, String str, final DeleteCallback deleteCallback) {
        if (checkCurrentUser(null)) {
            if (!AVUtils.isBlankString(str)) {
                PaasClient.storageInstance().deleteObject(String.format("statuses/%s", new Object[]{str}), z, new GenericObjectCallback() {
                    public void onSuccess(String str, AVException aVException) {
                        if (deleteCallback != null) {
                            deleteCallback.internalDone(null);
                        }
                    }

                    public void onFailure(Throwable th, String str) {
                        if (deleteCallback != null) {
                            deleteCallback.internalDone(AVErrorUtils.createException(th, str));
                        }
                    }
                }, str, null);
            } else if (deleteCallback != null) {
                deleteCallback.internalDone(AVErrorUtils.invalidObjectIdException());
            }
        } else if (deleteCallback != null) {
            deleteCallback.internalDone(AVErrorUtils.sessionMissingException());
        }
    }

    public static void deleteInboxStatus(long j, String str, AVUser aVUser) throws Exception {
        deleteInboxStatus(true, j, str, aVUser, new C09472());
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    public static void deleteInboxStatusInBackground(long j, String str, AVUser aVUser, DeleteCallback deleteCallback) {
        deleteInboxStatus(false, j, str, aVUser, deleteCallback);
    }

    private static void deleteInboxStatus(boolean z, long j, String str, AVUser aVUser, final DeleteCallback deleteCallback) {
        if (aVUser != null) {
            String toJSONString = JSON.toJSONString(AVUtils.mapFromUserObjectId(aVUser.getObjectId()));
            Map hashMap = new HashMap();
            hashMap.put("messageId", String.valueOf(j));
            hashMap.put("inboxType", str);
            hashMap.put("owner", toJSONString);
            PaasClient.storageInstance().deleteObject(AVUtils.getEncodeUrl("subscribe/statuses/inbox?", hashMap), z, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    if (deleteCallback != null) {
                        deleteCallback.internalDone(null);
                    }
                }

                public void onFailure(Throwable th, String str) {
                    if (deleteCallback != null) {
                        deleteCallback.internalDone(AVErrorUtils.createException(th, str));
                    }
                }
            }, null, null);
        } else if (deleteCallback != null) {
            deleteCallback.internalDone(new AVException((int) AVException.USER_DOESNOT_EXIST, "Owner can't be null"));
        }
    }

    @Deprecated
    public static void getStatuses(long j, long j2, StatusListCallback statusListCallback) {
        if (checkCurrentUser(statusListCallback)) {
            getStatusImpl(STATUS_END_POINT, statusQueryMap(AVUser.getCurrentUser().getObjectId(), j, j2, 0, null, null, true, false), statusListCallback);
        }
    }

    @Deprecated
    static Map<String, String> sourceQueryMap(String str, long j, long j2) {
        Map hashMap = new HashMap();
        Map<String, String> hashMap2 = new HashMap();
        try {
            hashMap.put(MapboxEvent.ATTRIBUTE_SOURCE, AVUtils.mapFromUserObjectId(str));
            hashMap2.put("where", JSON.toJSONString(hashMap));
            hashMap2.put("include", MapboxEvent.ATTRIBUTE_SOURCE);
            if (j > 0) {
                hashMap2.put("skip", Long.toString(j));
            }
            if (j2 > 0) {
                hashMap2.put("count", Long.toString(j2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hashMap2;
    }

    @Deprecated
    public static void getStatusesFromCurrentUserWithType(String str, long j, long j2, StatusListCallback statusListCallback) {
        if (checkCurrentUser(statusListCallback)) {
            getStatusImpl(STATUS_END_POINT, sourceQueryMap(AVUser.getCurrentUser().getObjectId(), j, j2), statusListCallback);
        }
    }

    @Deprecated
    public static void getStatusesFromUser(String str, long j, long j2, StatusListCallback statusListCallback) {
        if (!AVUtils.isBlankString(str)) {
            getStatusImpl(STATUS_END_POINT, sourceQueryMap(str, j, j2), statusListCallback);
        } else if (statusListCallback != null) {
            statusListCallback.internalDone(null, AVErrorUtils.invalidObjectIdException());
        }
    }

    @Deprecated
    static Map<String, String> statusQueryMap(String str, long j, long j2, long j3, String str2, Map<String, Object> map, boolean z, boolean z2) {
        Map mapFromUserObjectId = AVUtils.mapFromUserObjectId(str);
        Map<String, String> hashMap = new HashMap();
        try {
            hashMap.put("owner", JSON.toJSONString(mapFromUserObjectId));
            if (j > 0) {
                hashMap.put("skip", Long.toString(j));
            }
            if (j2 > 0) {
                hashMap.put("limit", Long.toString(j2));
            }
            if (j3 > 0) {
                hashMap.put("maxId", Long.toString(j3));
            }
            if (!AVUtils.isBlankString(str2)) {
                hashMap.put("inboxType", str2);
            }
            if (map != null) {
                hashMap.put("where", JSON.toJSONString(map));
            }
            if (z) {
                hashMap.put("include", MapboxEvent.ATTRIBUTE_SOURCE);
            }
            if (z2) {
                hashMap.put("count", Long.toString(1));
            }
        } catch (Exception e) {
            log.m3519e(e.toString());
        }
        return hashMap;
    }

    static Map<String, String> getStatusQueryMap(String str, long j, long j2, long j3, String str2, Map<String, Object> map, boolean z, boolean z2) {
        Map mapFromUserObjectId = AVUtils.mapFromUserObjectId(str);
        Map<String, String> hashMap = new HashMap();
        try {
            hashMap.put("owner", JSON.toJSONString(mapFromUserObjectId));
            if (j > 0) {
                hashMap.put("sinceId", Long.toString(j));
            }
            if (j2 > 0) {
                hashMap.put("limit", Long.toString(j2));
            }
            if (j3 > 0) {
                hashMap.put("maxId", Long.toString(j3));
            }
            if (!AVUtils.isBlankString(str2)) {
                hashMap.put("inboxType", str2);
            }
            if (map != null) {
                hashMap.put("where", JSON.toJSONString(map));
            }
            if (z) {
                hashMap.put("include", MapboxEvent.ATTRIBUTE_SOURCE);
            }
            if (z2) {
                hashMap.put("count", Long.toString(1));
            }
        } catch (Exception e) {
            log.m3519e(e.toString());
        }
        return hashMap;
    }

    static List<AVStatus> processStatusResultList(String str) {
        if (AVUtils.isBlankContent(str)) {
            return Collections.emptyList();
        }
        JSONArray jSONArray = JSON.parseObject(str).getJSONArray("results");
        List<AVStatus> linkedList = new LinkedList();
        Iterator it = jSONArray.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            AVStatus aVStatus = new AVStatus();
            processStatusFromObject(next, aVStatus);
            linkedList.add(aVStatus);
        }
        return linkedList;
    }

    static void processStatus(String str, AVStatus aVStatus) {
        processStatusFromObject(JSON.parseObject(str), aVStatus);
    }

    static void processStatusFromObject(Object obj, AVStatus aVStatus) {
        JSONObject jSONObject = (JSONObject) obj;
        aVStatus.objectId = AVUtils.getJSONString(jSONObject, AVUtils.objectIdTag, aVStatus.objectId);
        aVStatus.messageId = AVUtils.getJSONInteger(jSONObject, "messageId", aVStatus.messageId);
        aVStatus.inboxType = AVUtils.getJSONString(jSONObject, "inboxType", aVStatus.inboxType);
        aVStatus.createdAt = AVUtils.getJSONString(jSONObject, "createdAt", aVStatus.createdAt);
        aVStatus.setImageUrl(AVUtils.getJSONString(jSONObject, IMAGE_TAG, aVStatus.getImageUrl()));
        aVStatus.setMessage(AVUtils.getJSONString(jSONObject, MESSAGE_TAG, aVStatus.getMessage()));
        String jSONString = AVUtils.getJSONString(jSONObject, MapboxEvent.ATTRIBUTE_SOURCE, "");
        if (!AVUtils.isBlankString(jSONString)) {
            aVStatus.source = AVUtils.parseObjectFromMap(JSON.parseObject(jSONString));
        }
        for (Entry entry : jSONObject.entrySet()) {
            if (!(ignoreList.contains(entry.getKey().toString()) || entry.getValue() == null)) {
                aVStatus.dataMap.put(entry.getKey().toString(), AVUtils.getParsedObject(entry.getValue()));
            }
        }
    }

    static void getStatusImpl(String str, Map<String, String> map, final StatusListCallback statusListCallback) {
        RequestParams requestParams;
        if (map != null) {
            requestParams = new RequestParams(map);
        } else {
            requestParams = null;
        }
        PaasClient.storageInstance().getObject(str, requestParams, false, null, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                List processStatusResultList = AVStatus.processStatusResultList(str);
                if (statusListCallback != null) {
                    statusListCallback.internalDone(processStatusResultList, null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (statusListCallback != null) {
                    statusListCallback.internalDone(null, AVErrorUtils.createException(th, str));
                }
            }
        });
    }

    static int processStatusCount(String str) {
        return JSON.parseObject(str).getInteger(UNREAD_TAG).intValue();
    }

    static void getStatusCountImpl(String str, Map<String, String> map, final CountCallback countCallback) {
        PaasClient.storageInstance().getObject(str, new RequestParams(map), false, null, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                int processStatusCount = AVStatus.processStatusCount(str);
                if (countCallback != null) {
                    countCallback.internalDone(Integer.valueOf(processStatusCount), null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (countCallback != null) {
                    countCallback.internalDone(Integer.valueOf(0), AVErrorUtils.createException(th, str));
                }
            }
        });
    }

    @Deprecated
    public static void getInboxStatusesInBackground(long j, long j2, StatusListCallback statusListCallback) {
        getInboxStatusesWithInboxType(j, j2, INBOX_TYPE.TIMELINE.toString(), statusListCallback);
    }

    @Deprecated
    public static void getInboxStatusesWithInboxType(long j, long j2, String str, StatusListCallback statusListCallback) {
        if (checkCurrentUser(statusListCallback)) {
            getStatusImpl("subscribe/statuses", statusQueryMap(AVUser.getCurrentUser().getObjectId(), j, j2, 0, str, null, true, false), statusListCallback);
        }
    }

    public static void getUnreadStatusesCountInBackground(String str, CountCallback countCallback) {
        if (checkCurrentUser(null)) {
            getStatusCountImpl("subscribe/statuses/count", getStatusQueryMap(AVUser.getCurrentUser().getObjectId(), 0, 0, 0, str, null, true, true), countCallback);
        } else if (countCallback != null) {
            countCallback.internalDone(Integer.valueOf(0), AVErrorUtils.sessionMissingException());
        }
    }

    @Deprecated
    public static void getInboxUnreadStatusesCountInBackgroud(CountCallback countCallback) {
        getInboxUnreadStatusesCountWithInboxTypeInBackgroud(0, 0, INBOX_TYPE.TIMELINE.toString(), countCallback);
    }

    @Deprecated
    public static void getInboxUnreadStatusesCountWithInboxTypeInBackgroud(long j, long j2, String str, CountCallback countCallback) {
        if (checkCurrentUser(null)) {
            getStatusCountImpl("subscribe/statuses/count", statusQueryMap(AVUser.getCurrentUser().getObjectId(), j, j2, 0, str, null, true, true), countCallback);
        } else if (countCallback != null) {
            countCallback.internalDone(Integer.valueOf(0), AVErrorUtils.sessionMissingException());
        }
    }

    @Deprecated
    public static void getInboxPrivteStatuses(long j, long j2, StatusListCallback statusListCallback) {
        getInboxStatusesWithInboxType(j, j2, INBOX_TYPE.PRIVATE.toString(), statusListCallback);
    }

    static boolean checkStatusId(String str, StatusCallback statusCallback) {
        if (!AVUtils.isBlankString(str)) {
            return true;
        }
        if (statusCallback != null) {
            statusCallback.internalDone(null, AVErrorUtils.invalidObjectIdException());
        }
        return false;
    }

    public static void getStatusWithIdInBackgroud(String str, final StatusCallback statusCallback) {
        if (checkStatusId(str, statusCallback) && checkCurrentUser(statusCallback)) {
            String objectId = AVUser.getCurrentUser().getObjectId();
            String format = String.format("statuses/%s", new Object[]{str});
            String str2 = format;
            PaasClient.storageInstance().getObject(str2, new RequestParams(statusQueryMap(objectId, 0, 0, 0, null, null, true, false)), false, null, new GenericObjectCallback() {
                public void onSuccess(String str, AVException aVException) {
                    AVStatus aVStatus = new AVStatus();
                    AVStatus.processStatus(str, aVStatus);
                    if (statusCallback != null) {
                        statusCallback.internalDone(aVStatus, null);
                    }
                }

                public void onFailure(Throwable th, String str) {
                    if (statusCallback != null) {
                        statusCallback.internalDone(null, AVErrorUtils.createException(th, str));
                    }
                }
            });
        }
    }

    static void postStatusImpl(final AVStatus aVStatus, Map<String, Object> map, final SaveCallback saveCallback) {
        PaasClient.storageInstance().postObject(STATUS_END_POINT, AVUtils.restfulServerData(map), false, false, new GenericObjectCallback() {
            public void onSuccess(String str, AVException aVException) {
                AVStatus.processStatus(str, aVStatus);
                if (saveCallback != null) {
                    saveCallback.internalDone(null);
                }
            }

            public void onFailure(Throwable th, String str) {
                if (saveCallback != null) {
                    saveCallback.internalDone(AVErrorUtils.createException(th, str));
                }
            }
        }, aVStatus.getObjectId(), null);
    }

    static Map<String, Object> statusBody(AVStatus aVStatus, String str, Map<String, Object> map) {
        Map hashMap = new HashMap();
        hashMap.putAll(aVStatus.dataMap);
        Map<String, Object> hashMap2 = new HashMap();
        if (aVStatus.source != null) {
            hashMap.put(MapboxEvent.ATTRIBUTE_SOURCE, AVUtils.getParsedObject(aVStatus.source));
        } else {
            hashMap.put(MapboxEvent.ATTRIBUTE_SOURCE, AVUtils.getParsedObject(AVUser.getCurrentUser()));
        }
        hashMap2.put(C0861d.f2139k, hashMap);
        hashMap2.put("inboxType", str);
        hashMap2.put(SearchIntents.EXTRA_QUERY, map);
        return hashMap2;
    }

    Map<String, Object> myQueryParameters(AVQuery aVQuery) {
        Map<String, Object> hashMap = new HashMap();
        if (aVQuery.getWhere().keySet().size() > 0) {
            hashMap.put("where", AVUtils.getParsedMap(aVQuery.getWhere()));
        }
        if (aVQuery.getLimit() > 0) {
            hashMap.put("limit", Integer.toString(aVQuery.getLimit()));
        }
        if (aVQuery.getSkip() > 0) {
            hashMap.put("skip", Integer.toString(aVQuery.getSkip()));
        }
        if (aVQuery.getOrder() != null && aVQuery.getOrder().length() > 0) {
            hashMap.put("order", aVQuery.getOrder());
        }
        if (aVQuery.getInclude() != null && aVQuery.getInclude().size() > 0) {
            hashMap.put("include", AVUtils.joinCollection(aVQuery.getInclude(), ","));
        }
        if (aVQuery.getSelectedKeys() != null && aVQuery.getSelectedKeys().size() > 0) {
            hashMap.put("keys", AVUtils.joinCollection(aVQuery.getSelectedKeys(), ","));
        }
        return hashMap;
    }

    @Deprecated
    public void sendInBackgroundWithBlock(SaveCallback saveCallback) {
        sendInBackground(saveCallback);
    }

    public void sendInBackground(SaveCallback saveCallback) {
        if (!checkCurrentUser(saveCallback)) {
            return;
        }
        if (this.query == null) {
            sendStatusToFollowersInBackgroud(this, saveCallback);
            return;
        }
        Map hashMap = new HashMap();
        hashMap.putAll(myQueryParameters(this.query));
        hashMap.put("className", this.query.getClassName());
        postStatusImpl(this, statusBody(this, AVUtils.isBlankString(this.inboxType) ? INBOX_TYPE.TIMELINE.toString() : this.inboxType, hashMap), saveCallback);
    }

    public static void sendStatusToFollowersInBackgroud(AVStatus aVStatus, SaveCallback saveCallback) {
        if (checkCurrentUser(saveCallback)) {
            Map hashMap = new HashMap();
            hashMap.put(AVUtils.classNameTag, "_Follower");
            hashMap.put("keys", AVUser.FOLLOWER_TAG);
            hashMap.put("where", currentUserBody());
            postStatusImpl(aVStatus, statusBody(aVStatus, AVUtils.isBlankString(aVStatus.inboxType) ? INBOX_TYPE.TIMELINE.toString() : aVStatus.inboxType, hashMap), saveCallback);
        }
    }

    public static void sendPrivateStatusInBackgroud(AVStatus aVStatus, String str, SaveCallback saveCallback) {
        if (checkCurrentUser(saveCallback)) {
            Map hashMap = new HashMap();
            hashMap.put(AVUtils.classNameTag, "_User");
            Map hashMap2 = new HashMap();
            hashMap2.put(AVUtils.objectIdTag, str);
            hashMap.put("where", hashMap2);
            postStatusImpl(aVStatus, statusBody(aVStatus, INBOX_TYPE.PRIVATE.toString(), hashMap), saveCallback);
        }
    }

    private static Map<String, Object> currentUserBody() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("user", AVUtils.mapFromPointerObject(AVUser.getCurrentUser()));
        return hashMap;
    }

    public static AVStatusQuery statusQuery(AVUser aVUser) throws AVException {
        AVStatusQuery aVStatusQuery = new AVStatusQuery();
        aVStatusQuery.setSelfQuery(true);
        aVStatusQuery.whereEqualTo(MapboxEvent.ATTRIBUTE_SOURCE, aVUser);
        aVStatusQuery.setExternalQueryPath(STATUS_END_POINT);
        return aVStatusQuery;
    }

    public static AVStatusQuery inboxQuery(AVUser aVUser, String str) {
        AVStatusQuery aVStatusQuery = new AVStatusQuery();
        aVStatusQuery.setInboxType(str);
        aVStatusQuery.setOwner(aVUser);
        aVStatusQuery.setExternalQueryPath("subscribe/statuses");
        return aVStatusQuery;
    }

    @Deprecated
    public void add(String str, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void addAll(String str, Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void addAllUnique(String str, Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void addUnique(String str, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public boolean containsKey(String str) {
        throw new UnsupportedOperationException();
    }

    public void delete() throws AVException {
        deleteStatusWithId(true, getObjectId(), new C09538());
        if (AVExceptionHolder.exists()) {
            throw AVExceptionHolder.remove();
        }
    }

    @Deprecated
    public void deleteEventually(DeleteCallback deleteCallback) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void deleteEventually() {
        throw new UnsupportedOperationException();
    }

    public void deleteInBackground() {
        deleteStatusInBackground(null);
    }

    public AVObject toObject() {
        return AVObject.createWithoutData("_Status", this.objectId);
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
        AVStatus aVStatus = (AVStatus) obj;
        if (getClassName() == null) {
            if (aVStatus.getClassName() != null) {
                return false;
            }
        } else if (!getClassName().equals(aVStatus.getClassName())) {
            return false;
        }
        if (this.objectId == null) {
            if (aVStatus.objectId != null) {
                return false;
            }
        } else if (!this.objectId.equals(aVStatus.objectId)) {
            return false;
        }
        return true;
    }

    @Deprecated
    public AVObject fetch() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public AVObject fetch(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public AVObject fetchIfNeeded() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public AVObject fetchIfNeeded(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void fetchIfNeededInBackground(GetCallback<AVObject> getCallback) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void fetchIfNeededInBackground(String str, GetCallback<AVObject> getCallback) {
        throw new UnsupportedOperationException();
    }

    public String toString() {
        return "AVStatus [, objectId=" + this.objectId + ", createdAt=" + this.createdAt + ", data=" + this.dataMap + "]";
    }

    @Deprecated
    public boolean isFetchWhenSave() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void setFetchWhenSave(boolean z) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public String getUuid() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void deleteInBackground(DeleteCallback deleteCallback) {
        super.deleteInBackground(deleteCallback);
    }

    @Deprecated
    public void fetchInBackground(GetCallback<AVObject> getCallback) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void fetchInBackground(String str, GetCallback<AVObject> getCallback) {
        throw new UnsupportedOperationException();
    }

    public Object get(String str) {
        return this.dataMap.get(str);
    }

    @Deprecated
    public AVACL getACL() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public boolean getBoolean(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public byte[] getBytes(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public String getClassName() {
        return AVStatus.class.getSimpleName();
    }

    @Deprecated
    public Date getDate(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public double getDouble(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public int getInt(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public org.json.JSONArray getJSONArray(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public org.json.JSONObject getJSONObject(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public List getList(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public long getLong(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public <V> Map<String, V> getMap(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public Number getNumber(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public <T extends AVFile> T getAVFile(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public AVGeoPoint getAVGeoPoint(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public <T extends AVObject> T getAVObject(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public <T extends AVUser> T getAVUser(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public <T extends AVObject> AVRelation<T> getRelation(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public String getString(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public Date getUpdatedAt() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public boolean has(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public boolean hasSameId(AVObject aVObject) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void increment(String str) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void increment(String str, Number number) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public Set<String> keySet() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void refresh() throws AVException {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void refresh(String str) throws AVException {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void refreshInBackground(RefreshCallback<AVObject> refreshCallback) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void refreshInBackground(String str, RefreshCallback<AVObject> refreshCallback) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void removeAll(String str, Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void save() throws AVException {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void saveEventually() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void saveEventually(SaveCallback saveCallback) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void saveInBackground() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void saveInBackground(SaveCallback saveCallback) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public void setACL(AVACL avacl) {
        throw new UnsupportedOperationException();
    }
}
