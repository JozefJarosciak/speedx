package com.avos.avoscloud;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.avos.avoscloud.LogUtil.avlog;

class AVDuration implements Parcelable {
    public static final Creator<AVDuration> CREATOR = new C09111();
    private long createTimeStamp;
    private long duration;
    private long pausedTimeStamp;
    private long resumeTimeStamp;
    private boolean stopped;

    /* renamed from: com.avos.avoscloud.AVDuration$1 */
    static class C09111 implements Creator<AVDuration> {
        C09111() {
        }

        public AVDuration createFromParcel(Parcel parcel) {
            return new AVDuration(parcel);
        }

        public AVDuration[] newArray(int i) {
            return new AVDuration[i];
        }
    }

    public long getCreateTimeStamp() {
        return this.createTimeStamp;
    }

    public long getActualDuration() {
        long lastTimeInterval = this.duration + getLastTimeInterval();
        if (lastTimeInterval >= 0) {
            return lastTimeInterval;
        }
        if (!AVOSCloud.showInternalDebugLog()) {
            return 0;
        }
        avlog.m3506d("Negative duration " + lastTimeInterval);
        return 0;
    }

    public long getDuration() {
        return this.duration;
    }

    public synchronized void start() {
        this.stopped = false;
        this.createTimeStamp = currentTS();
        this.resumeTimeStamp = this.createTimeStamp;
        this.pausedTimeStamp = -1;
    }

    public synchronized void stop() {
        sync();
        this.stopped = true;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    public synchronized void resume() {
        if (!this.stopped) {
            sync();
            this.resumeTimeStamp = currentTS();
        }
    }

    public synchronized void pause() {
        this.pausedTimeStamp = currentTS();
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public void addDuration(long j) {
        this.duration += j;
    }

    public void sync() {
        if (!this.stopped) {
            this.duration += getLastTimeInterval();
            this.pausedTimeStamp = -1;
        }
    }

    private long getLastTimeInterval() {
        if (this.pausedTimeStamp > this.resumeTimeStamp) {
            return this.pausedTimeStamp - this.resumeTimeStamp;
        }
        if (this.stopped) {
            return 0;
        }
        return currentTS() - this.resumeTimeStamp;
    }

    public static long currentTS() {
        return System.currentTimeMillis();
    }

    protected long getResumeTimeStamp() {
        return this.resumeTimeStamp;
    }

    protected void setResumeTimeStamp(long j) {
        this.resumeTimeStamp = j;
    }

    protected void setCreateTimeStamp(long j) {
        this.createTimeStamp = j;
    }

    protected void setStopped(boolean z) {
        this.stopped = z;
    }

    public long getPausedTimeStamp() {
        return this.pausedTimeStamp;
    }

    public void setPausedTimeStamp(long j) {
        this.pausedTimeStamp = j;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.createTimeStamp);
        parcel.writeLong(this.resumeTimeStamp);
        parcel.writeLong(this.pausedTimeStamp);
        parcel.writeLong(this.duration);
        parcel.writeInt(this.stopped ? 1 : 0);
    }

    public AVDuration(Parcel parcel) {
        boolean z = true;
        this.createTimeStamp = parcel.readLong();
        this.resumeTimeStamp = parcel.readLong();
        this.pausedTimeStamp = parcel.readLong();
        this.duration = parcel.readLong();
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.stopped = z;
    }
}
