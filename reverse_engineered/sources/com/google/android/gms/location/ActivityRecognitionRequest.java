package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.WorkSource;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class ActivityRecognitionRequest extends AbstractSafeParcelable {
    public static final Creator<ActivityRecognitionRequest> CREATOR = new zza();
    private long act;
    private boolean acu;
    @Nullable
    private WorkSource acv;
    @Nullable
    private int[] acw;
    @Nullable
    private boolean acx;
    @Nullable
    private String cf;
    @Nullable
    private String mTag;
    private final int mVersionCode;

    ActivityRecognitionRequest(int i, long j, boolean z, @Nullable WorkSource workSource, @Nullable String str, @Nullable int[] iArr, boolean z2, @Nullable String str2) {
        this.mVersionCode = i;
        this.act = j;
        this.acu = z;
        this.acv = workSource;
        this.mTag = str;
        this.acw = iArr;
        this.acx = z2;
        this.cf = str2;
    }

    @Nullable
    public String getAccountName() {
        return this.cf;
    }

    public long getIntervalMillis() {
        return this.act;
    }

    @Nullable
    public String getTag() {
        return this.mTag;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public boolean zzbnb() {
        return this.acu;
    }

    @Nullable
    public WorkSource zzbnc() {
        return this.acv;
    }

    @Nullable
    public int[] zzbnd() {
        return this.acw;
    }

    public boolean zzbne() {
        return this.acx;
    }
}
