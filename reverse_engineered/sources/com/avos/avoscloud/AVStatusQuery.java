package com.avos.avoscloud;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.cons.C0844a;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AVStatusQuery extends AVQuery<AVStatus> {
    private static final String END = "end";
    private boolean count;
    private String inboxType;
    private long maxId;
    private AVUser owner;
    private boolean selfStatusQuery = false;
    private long sinceId;

    public AVStatusQuery() {
        super(AVStatus.STATUS_END_POINT, null);
        getInclude().add(MapboxEvent.ATTRIBUTE_SOURCE);
    }

    public void setSinceId(long j) {
        this.sinceId = j;
    }

    public long getSinceId() {
        return this.sinceId;
    }

    public void setInboxType(String str) {
        this.inboxType = str;
    }

    protected String getInboxType() {
        return this.inboxType;
    }

    public long getMaxId() {
        return this.maxId;
    }

    public void setMaxId(long j) {
        this.maxId = j;
    }

    public boolean isCount() {
        return this.count;
    }

    public void setCount(boolean z) {
        this.count = z;
    }

    public AVUser getOwner() {
        return this.owner;
    }

    public void setOwner(AVUser aVUser) {
        this.owner = aVUser;
    }

    protected void setSelfQuery(boolean z) {
        this.selfStatusQuery = z;
    }

    public Map<String, String> assembleParameters() {
        if (this.selfStatusQuery && this.inboxType != null) {
            whereEqualTo("inboxType", this.inboxType);
        }
        super.assembleParameters();
        Map<String, String> parameters = getParameters();
        if (this.owner != null) {
            parameters.put("owner", JSON.toJSONString(AVUtils.mapFromUserObjectId(this.owner.getObjectId())));
        }
        if (this.sinceId > 0) {
            parameters.put("sinceId", String.valueOf(this.sinceId));
        }
        if (!(AVUtils.isBlankString(this.inboxType) || this.selfStatusQuery)) {
            parameters.put("inboxType", this.inboxType);
        }
        if (this.maxId > 0) {
            parameters.put("maxId", String.valueOf(this.maxId));
        }
        if (this.count) {
            parameters.put("count", C0844a.f2048d);
        }
        this.conditions.setParameters(parameters);
        return parameters;
    }

    protected List<AVStatus> processResults(String str) throws Exception {
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

    private void processStatusFromObject(Object obj, AVStatus aVStatus) {
        JSONObject jSONObject = (JSONObject) obj;
        aVStatus.setObjectId(AVUtils.getJSONString(jSONObject, AVUtils.objectIdTag, aVStatus.getObjectId()));
        aVStatus.setMessageId(AVUtils.getJSONInteger(jSONObject, "messageId", aVStatus.getMessageId()));
        String jSONString = AVUtils.getJSONString(jSONObject, "inboxType", aVStatus.getInboxType());
        this.inboxType = jSONString;
        aVStatus.setInboxType(jSONString);
        aVStatus.setCreatedAt(AVUtils.getJSONString(jSONObject, "createdAt", aVStatus.getCreatedAt() != null ? aVStatus.getCreatedAt().toLocaleString() : null));
        aVStatus.setImageUrl(AVUtils.getJSONString(jSONObject, AVStatus.IMAGE_TAG, aVStatus.getImageUrl()));
        aVStatus.setMessage(AVUtils.getJSONString(jSONObject, AVStatus.MESSAGE_TAG, aVStatus.getMessage()));
        jSONString = AVUtils.getJSONString(jSONObject, MapboxEvent.ATTRIBUTE_SOURCE, "");
        if (!AVUtils.isBlankString(jSONString)) {
            aVStatus.setSource(AVUtils.parseObjectFromMap(JSON.parseObject(jSONString)));
        }
        for (Entry entry : jSONObject.entrySet()) {
            if (!(AVStatus.ignoreList.contains(entry.getKey().toString()) || entry.getValue() == null)) {
                aVStatus.put(entry.getKey().toString(), AVUtils.getParsedObject(entry.getValue()));
            }
        }
    }

    protected void processAdditionalInfo(String str, FindCallback<AVStatus> findCallback) {
        if (InboxStatusFindCallback.class.isAssignableFrom(findCallback.getClass())) {
            InboxStatusFindCallback inboxStatusFindCallback = (InboxStatusFindCallback) findCallback;
            JSONObject parseObject = JSON.parseObject(str);
            boolean z = false;
            if (parseObject.containsKey(END)) {
                z = parseObject.getBoolean(END).booleanValue();
            }
            inboxStatusFindCallback.setEnd(z);
        }
    }
}
