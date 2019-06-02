package com.avos.avoscloud;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.alipay.sdk.packet.C0861d;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class AnalyticsSession implements Parcelable {
    public static final Creator<AnalyticsSession> CREATOR = new C09711();
    private static final String TAG = AnalyticsSession.class.getSimpleName();
    private static final String sessionIdTag = "sessionId";
    private List<AnalyticsActivity> activities;
    private AVDuration duration;
    private List<AnalyticsEvent> events;
    private String sessionId;

    /* renamed from: com.avos.avoscloud.AnalyticsSession$1 */
    static class C09711 implements Creator<AnalyticsSession> {
        C09711() {
        }

        public AnalyticsSession createFromParcel(Parcel parcel) {
            return new AnalyticsSession(parcel);
        }

        public AnalyticsSession[] newArray(int i) {
            return new AnalyticsSession[i];
        }
    }

    public AnalyticsSession() {
        this.sessionId = "";
        this.duration = new AVDuration();
        this.activities = new ArrayList();
        this.events = new ArrayList();
    }

    public synchronized void beginSession() {
        this.sessionId = AnalyticsUtils.uniqueId();
        this.duration.start();
    }

    public synchronized void endSession() {
        if (!AVUtils.isBlankString(this.sessionId)) {
            for (AnalyticsActivity analyticsActivity : getActivities()) {
                if (!analyticsActivity.isStopped()) {
                    analyticsActivity.stop();
                }
            }
            for (AnalyticsEvent analyticsEvent : getEvents()) {
                if (!analyticsEvent.getDuration().isStopped()) {
                    analyticsEvent.stop();
                }
            }
            this.duration.stop();
        }
    }

    public boolean isSessionFinished() {
        return this.duration.isStopped();
    }

    public synchronized void pauseSession() {
        this.duration.pause();
    }

    public long getSessionStart() {
        return this.duration.getCreateTimeStamp();
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionDuration(long j) {
        this.duration.setDuration(j);
    }

    private AnalyticsActivity activityByName(String str, boolean z) {
        for (AnalyticsActivity analyticsActivity : this.activities) {
            AnalyticsActivity analyticsActivity2;
            if (analyticsActivity2.getActivityName().equalsIgnoreCase(str) && !analyticsActivity2.isStopped()) {
                return analyticsActivity2;
            }
        }
        if (!z) {
            return null;
        }
        analyticsActivity2 = new AnalyticsActivity(str);
        this.activities.add(analyticsActivity2);
        return analyticsActivity2;
    }

    private AnalyticsEvent eventByName(String str, String str2, String str3, boolean z) {
        AnalyticsEvent analyticsEvent;
        for (AnalyticsEvent analyticsEvent2 : this.events) {
            if (analyticsEvent2.isMatch(str, str2, str3)) {
                return analyticsEvent2;
            }
        }
        if (!z) {
            return null;
        }
        analyticsEvent2 = new AnalyticsEvent(str);
        analyticsEvent2.setLabel(str2);
        analyticsEvent2.setPrimaryKey(str3);
        this.events.add(analyticsEvent2);
        return analyticsEvent2;
    }

    public synchronized void addActivity(String str, long j) {
        activityByName(str, true).setDurationValue(j);
    }

    public synchronized void beginActivity(String str) {
        AnalyticsActivity activityByName = activityByName(str, true);
        activityByName.start();
        activityByName.setSavedToServer(false);
        this.duration.resume();
    }

    public synchronized void beginFragment(String str) {
        AnalyticsActivity activityByName = activityByName(str, true);
        activityByName.setFragment(true);
        activityByName.start();
        this.duration.resume();
    }

    public synchronized AnalyticsEvent beginEvent(Context context, String str, String str2, String str3) {
        AnalyticsEvent eventByName;
        eventByName = eventByName(str, str2, str3, true);
        if (!AVUtils.isBlankString(str2)) {
            eventByName.setLabel(str2);
        }
        eventByName.start();
        this.duration.resume();
        return eventByName;
    }

    public synchronized void endEvent(Context context, String str, String str2, String str3) {
        AnalyticsEvent eventByName = eventByName(str, str2, str3, false);
        if (eventByName != null) {
            eventByName.stop();
        }
    }

    public synchronized void endActivity(String str) {
        AnalyticsActivity activityByName = activityByName(str, false);
        if (activityByName == null) {
            Log.i("", "Please call begin activity before using endActivity");
        } else {
            activityByName.setSavedToServer(false);
            activityByName.stop();
        }
    }

    public synchronized void endFragment(String str) {
        AnalyticsActivity activityByName = activityByName(str, false);
        if (activityByName == null) {
            Log.i("", "Please call begin Fragment before using endFragment");
        } else {
            activityByName.stop();
        }
    }

    public Map<String, Object> launchMap() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("sessionId", this.sessionId);
        hashMap.put("date", Long.valueOf(this.duration.getCreateTimeStamp()));
        return hashMap;
    }

    public synchronized Map<String, Object> activitiesMap(boolean z) {
        Map<String, Object> hashMap;
        List linkedList = new LinkedList();
        long j = 0;
        for (AnalyticsActivity analyticsActivity : this.activities) {
            long j2;
            synchronized (analyticsActivity) {
                if (analyticsActivity.isStopped() && !analyticsActivity.isSavedToServer()) {
                    linkedList.add(analyticsActivity.jsonMap());
                    if (z) {
                        analyticsActivity.setSavedToServer(true);
                    }
                }
            }
            if (analyticsActivity.isFragment) {
                j2 = j;
            } else {
                j2 = analyticsActivity.myDuration() + j;
            }
            j = j2;
        }
        hashMap = new HashMap();
        hashMap.put("activities", linkedList);
        hashMap.put("sessionId", this.sessionId);
        hashMap.put("duration", Long.valueOf(getDuration().getActualDuration()));
        return hashMap;
    }

    public synchronized List<Object> eventArray(boolean z) {
        List<Object> linkedList;
        linkedList = new LinkedList();
        Collection linkedList2 = new LinkedList();
        for (AnalyticsEvent analyticsEvent : this.events) {
            if (analyticsEvent.getDuration().isStopped()) {
                linkedList.add(analyticsEvent.jsonMap());
                linkedList2.add(analyticsEvent);
            }
        }
        if (z) {
            this.events.removeAll(linkedList2);
        }
        return linkedList;
    }

    protected boolean hasNewData() {
        for (AnalyticsActivity analyticsActivity : this.activities) {
            if (analyticsActivity.isStopped() && !analyticsActivity.isSavedToServer()) {
                return true;
            }
        }
        for (AnalyticsEvent duration : this.events) {
            if (duration.getDuration().isStopped()) {
                return true;
            }
        }
        return false;
    }

    protected int getMessageCount() {
        int i = 0;
        for (AnalyticsActivity analyticsActivity : this.activities) {
            int i2;
            if (analyticsActivity.isStopped() && !analyticsActivity.isSavedToServer()) {
                i2 = i + 2;
            } else if (analyticsActivity.isSavedToServer() || analyticsActivity.isStopped()) {
                i2 = i;
            } else {
                i2 = i + 1;
            }
            i = i2;
        }
        for (AnalyticsEvent duration : this.events) {
            if (duration.getDuration().isStopped()) {
                i += 2;
            } else {
                i++;
            }
        }
        return i;
    }

    public Map<String, Object> jsonMap(Context context, Map<String, String> map, boolean z) {
        if (!hasNewData()) {
            return null;
        }
        Map<String, Object> hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        hashMap2.put("launch", launchMap());
        hashMap2.put("terminate", activitiesMap(z));
        hashMap2.put("event", eventArray(z));
        hashMap.put("events", hashMap2);
        hashMap.put(C0861d.f2142n, AnalyticsUtils.deviceInfo(context));
        if (map == null) {
            return hashMap;
        }
        hashMap.put("customInfo", map);
        return hashMap;
    }

    protected Map<String, Object> firstBootMap(Context context, Map<String, String> map) {
        Map<String, Object> hashMap = new HashMap();
        Map hashMap2 = new HashMap();
        hashMap2.put("launch", launchMap());
        hashMap2.put("terminate", activitiesMap(false));
        hashMap.put("events", hashMap2);
        hashMap.put(C0861d.f2142n, AnalyticsUtils.deviceInfo(context));
        if (map != null) {
            hashMap.put("customInfo", map);
        }
        return hashMap;
    }

    protected List<AnalyticsActivity> getActivities() {
        return this.activities;
    }

    protected void setActivities(List<AnalyticsActivity> list) {
        this.activities = list;
    }

    protected List<AnalyticsEvent> getEvents() {
        return this.events;
    }

    protected void setEvents(List<AnalyticsEvent> list) {
        this.events = list;
    }

    protected AVDuration getDuration() {
        return this.duration;
    }

    protected void setDuration(AVDuration aVDuration) {
        this.duration = aVDuration;
    }

    protected void setSessionId(String str) {
        this.sessionId = str;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelableArray((Parcelable[]) this.activities.toArray(new AnalyticsActivity[0]), 1);
        parcel.writeParcelableArray((Parcelable[]) this.events.toArray(new AnalyticsEvent[0]), 1);
        parcel.writeParcelable(this.duration, 1);
        parcel.writeString(this.sessionId);
    }

    public AnalyticsSession(Parcel parcel) {
        int i = 0;
        this();
        Parcelable[] readParcelableArray = parcel.readParcelableArray(AnalyticsActivity.class.getClassLoader());
        Parcelable[] readParcelableArray2 = parcel.readParcelableArray(AnalyticsEvent.class.getClassLoader());
        for (Parcelable parcelable : readParcelableArray) {
            this.activities.add((AnalyticsActivity) parcelable);
        }
        int length = readParcelableArray2.length;
        while (i < length) {
            this.events.add((AnalyticsEvent) readParcelableArray2[i]);
            i++;
        }
        this.duration = (AVDuration) parcel.readParcelable(AVDuration.class.getClassLoader());
        this.sessionId = parcel.readString();
    }
}
