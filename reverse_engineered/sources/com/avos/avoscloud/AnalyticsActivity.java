package com.avos.avoscloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.HashMap;
import java.util.Map;

class AnalyticsActivity implements Parcelable {
    public static final Creator<AnalyticsActivity> CREATOR = new C09651();
    private String activityName;
    private AVDuration duration;
    boolean isFragment;
    volatile boolean savedToServer;

    /* renamed from: com.avos.avoscloud.AnalyticsActivity$1 */
    static class C09651 implements Creator<AnalyticsActivity> {
        C09651() {
        }

        public AnalyticsActivity createFromParcel(Parcel parcel) {
            return new AnalyticsActivity(parcel);
        }

        public AnalyticsActivity[] newArray(int i) {
            return new AnalyticsActivity[i];
        }
    }

    public AnalyticsActivity(String str) {
        this.duration = new AVDuration();
        this.isFragment = false;
        this.activityName = str;
        this.savedToServer = false;
    }

    public AnalyticsActivity() {
        this("");
    }

    public void start() {
        this.duration.start();
    }

    public void stop() {
        this.duration.stop();
    }

    public void setDurationValue(long j) {
        this.duration.setDuration(j);
    }

    public double getStart() {
        return (double) this.duration.getCreateTimeStamp();
    }

    public String getActivityName() {
        return this.activityName;
    }

    public boolean isStopped() {
        return this.duration.isStopped();
    }

    long myDuration() {
        return this.duration.getActualDuration();
    }

    public Map<String, Object> jsonMap() {
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("name", this.activityName);
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

    protected void setActivityName(String str) {
        this.activityName = str;
    }

    protected boolean isSavedToServer() {
        return this.savedToServer;
    }

    protected void setSavedToServer(boolean z) {
        this.savedToServer = z;
    }

    protected boolean isFragment() {
        return this.isFragment;
    }

    protected void setFragment(boolean z) {
        this.isFragment = z;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeParcelable(this.duration, 1);
        parcel.writeString(this.activityName);
        if (this.savedToServer) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeInt(i2);
        if (!this.isFragment) {
            i3 = 0;
        }
        parcel.writeInt(i3);
    }

    public AnalyticsActivity(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.duration = new AVDuration();
        this.isFragment = false;
        this.duration = (AVDuration) parcel.readParcelable(AVDuration.class.getClassLoader());
        this.activityName = parcel.readString();
        if (parcel.readInt() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.savedToServer = z;
        if (parcel.readInt() != 1) {
            z2 = false;
        }
        this.isFragment = z2;
    }
}
