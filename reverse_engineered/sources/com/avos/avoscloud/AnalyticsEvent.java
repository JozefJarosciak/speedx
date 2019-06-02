package com.avos.avoscloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.HashMap;
import java.util.Map;

class AnalyticsEvent implements Parcelable {
    public static final Creator<AnalyticsEvent> CREATOR = new C09661();
    public static final String accTag = "acc";
    public static final String attributesTag = "attributes";
    public static final String eventTag = "name";
    public static final String labelTag = "tag";
    public static final String primaryKeyTag = "primaryKey";
    private int accumulation;
    private Map<String, Object> attributes;
    private AVDuration duration;
    private String eventName;
    private String labelName;
    private String primaryKey;

    /* renamed from: com.avos.avoscloud.AnalyticsEvent$1 */
    static class C09661 implements Creator<AnalyticsEvent> {
        C09661() {
        }

        public AnalyticsEvent createFromParcel(Parcel parcel) {
            return new AnalyticsEvent(parcel);
        }

        public AnalyticsEvent[] newArray(int i) {
            return new AnalyticsEvent[i];
        }
    }

    public AnalyticsEvent(String str) {
        this.duration = new AVDuration();
        this.eventName = str;
        this.attributes = new HashMap();
        this.accumulation = 1;
    }

    public AnalyticsEvent() {
        this("");
    }

    public void start() {
        this.duration.start();
    }

    public void stop() {
        this.duration.stop();
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setDurationValue(long j) {
        this.duration.setDuration(j);
    }

    public void setAccumulation(int i) {
        if (i > 0) {
            this.accumulation = i;
        }
    }

    public void setLabel(String str) {
        this.labelName = str;
    }

    public void setPrimaryKey(String str) {
        this.primaryKey = str;
    }

    public void addAttributes(Map<String, String> map) {
        if (map != null) {
            this.attributes.putAll(map);
        }
    }

    public boolean isMatch(String str, String str2, String str3) {
        if (this.eventName.equals(str) && AnalyticsUtils.isStringEqual(this.labelName, str2) && AnalyticsUtils.isStringEqual(this.primaryKey, str3) && !this.duration.isStopped()) {
            return true;
        }
        return false;
    }

    long myDuration() {
        return this.duration.getActualDuration();
    }

    public Map<String, Object> jsonMap() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("name", this.eventName);
        if (AVUtils.isBlankString(this.labelName)) {
            hashMap.put(labelTag, this.eventName);
        } else {
            hashMap.put(labelTag, this.labelName);
        }
        if (!AVUtils.isBlankString(this.primaryKey)) {
            hashMap.put(primaryKeyTag, this.primaryKey);
        }
        if (this.accumulation > 1) {
            hashMap.put(accTag, Integer.valueOf(this.accumulation));
        }
        if (this.attributes.size() > 0) {
            try {
                hashMap.put(attributesTag, this.attributes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        hashMap.put("du", Long.valueOf(myDuration()));
        hashMap.put("ts", Long.valueOf(this.duration.getCreateTimeStamp()));
        return hashMap;
    }

    protected AVDuration getDuration() {
        return this.duration;
    }

    protected void setDuration(AVDuration aVDuration) {
        this.duration = aVDuration;
    }

    protected Map<String, Object> getAttributes() {
        return this.attributes;
    }

    protected void setAttributes(Map<String, Object> map) {
        this.attributes = map;
    }

    protected String getLabelName() {
        return this.labelName;
    }

    protected void setLabelName(String str) {
        this.labelName = str;
    }

    protected String getPrimaryKey() {
        return this.primaryKey;
    }

    protected int getAccumulation() {
        return this.accumulation;
    }

    protected void setEventName(String str) {
        this.eventName = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.duration, 1);
        parcel.writeMap(this.attributes);
        parcel.writeString(this.eventName);
        parcel.writeString(this.labelName);
        parcel.writeString(this.primaryKey);
        parcel.writeInt(this.accumulation);
    }

    public AnalyticsEvent(Parcel parcel) {
        this.duration = new AVDuration();
        this.duration = (AVDuration) parcel.readParcelable(AnalyticsEvent.class.getClassLoader());
        this.attributes = parcel.readHashMap(Map.class.getClassLoader());
        this.eventName = parcel.readString();
        this.labelName = parcel.readString();
        this.primaryKey = parcel.readString();
        this.accumulation = parcel.readInt();
    }
}
