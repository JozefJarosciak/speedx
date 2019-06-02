package com.google.android.gms.location;

import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;

public final class LocationRequest extends AbstractSafeParcelable {
    public static final LocationRequestCreator CREATOR = new LocationRequestCreator();
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;
    boolean PO;
    long acI;
    long add;
    long ade;
    int adf;
    float adg;
    long adh;
    int mPriority;
    private final int mVersionCode;

    public LocationRequest() {
        this.mVersionCode = 1;
        this.mPriority = 102;
        this.add = 3600000;
        this.ade = 600000;
        this.PO = false;
        this.acI = Long.MAX_VALUE;
        this.adf = Integer.MAX_VALUE;
        this.adg = 0.0f;
        this.adh = 0;
    }

    LocationRequest(int i, int i2, long j, long j2, boolean z, long j3, int i3, float f, long j4) {
        this.mVersionCode = i;
        this.mPriority = i2;
        this.add = j;
        this.ade = j2;
        this.PO = z;
        this.acI = j3;
        this.adf = i3;
        this.adg = f;
        this.adh = j4;
    }

    public static LocationRequest create() {
        return new LocationRequest();
    }

    private static void zzas(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("invalid interval: " + j);
        }
    }

    private static void zze(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("invalid displacement: " + f);
        }
    }

    private static void zzsy(int i) {
        switch (i) {
            case 100:
            case 102:
            case 104:
            case 105:
                return;
            default:
                throw new IllegalArgumentException("invalid quality: " + i);
        }
    }

    public static String zzsz(int i) {
        switch (i) {
            case 100:
                return "PRIORITY_HIGH_ACCURACY";
            case 102:
                return "PRIORITY_BALANCED_POWER_ACCURACY";
            case 104:
                return "PRIORITY_LOW_POWER";
            case 105:
                return "PRIORITY_NO_POWER";
            default:
                return "???";
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LocationRequest)) {
            return false;
        }
        LocationRequest locationRequest = (LocationRequest) obj;
        return this.mPriority == locationRequest.mPriority && this.add == locationRequest.add && this.ade == locationRequest.ade && this.PO == locationRequest.PO && this.acI == locationRequest.acI && this.adf == locationRequest.adf && this.adg == locationRequest.adg;
    }

    public long getExpirationTime() {
        return this.acI;
    }

    public long getFastestInterval() {
        return this.ade;
    }

    public long getInterval() {
        return this.add;
    }

    public long getMaxWaitTime() {
        long j = this.adh;
        return j < this.add ? this.add : j;
    }

    public int getNumUpdates() {
        return this.adf;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public float getSmallestDisplacement() {
        return this.adg;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.mPriority), Long.valueOf(this.add), Long.valueOf(this.ade), Boolean.valueOf(this.PO), Long.valueOf(this.acI), Integer.valueOf(this.adf), Float.valueOf(this.adg));
    }

    public LocationRequest setExpirationDuration(long j) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (j > Long.MAX_VALUE - elapsedRealtime) {
            this.acI = Long.MAX_VALUE;
        } else {
            this.acI = elapsedRealtime + j;
        }
        if (this.acI < 0) {
            this.acI = 0;
        }
        return this;
    }

    public LocationRequest setExpirationTime(long j) {
        this.acI = j;
        if (this.acI < 0) {
            this.acI = 0;
        }
        return this;
    }

    public LocationRequest setFastestInterval(long j) {
        zzas(j);
        this.PO = true;
        this.ade = j;
        return this;
    }

    public LocationRequest setInterval(long j) {
        zzas(j);
        this.add = j;
        if (!this.PO) {
            this.ade = (long) (((double) this.add) / 6.0d);
        }
        return this;
    }

    public LocationRequest setMaxWaitTime(long j) {
        zzas(j);
        this.adh = j;
        return this;
    }

    public LocationRequest setNumUpdates(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("invalid numUpdates: " + i);
        }
        this.adf = i;
        return this;
    }

    public LocationRequest setPriority(int i) {
        zzsy(i);
        this.mPriority = i;
        return this;
    }

    public LocationRequest setSmallestDisplacement(float f) {
        zze(f);
        this.adg = f;
        return this;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Request[").append(zzsz(this.mPriority));
        if (this.mPriority != 105) {
            stringBuilder.append(" requested=");
            stringBuilder.append(this.add).append("ms");
        }
        stringBuilder.append(" fastest=");
        stringBuilder.append(this.ade).append("ms");
        if (this.adh > this.add) {
            stringBuilder.append(" maxWait=");
            stringBuilder.append(this.adh).append("ms");
        }
        if (this.acI != Long.MAX_VALUE) {
            long elapsedRealtime = this.acI - SystemClock.elapsedRealtime();
            stringBuilder.append(" expireIn=");
            stringBuilder.append(elapsedRealtime).append("ms");
        }
        if (this.adf != Integer.MAX_VALUE) {
            stringBuilder.append(" num=").append(this.adf);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        LocationRequestCreator.zza(this, parcel, i);
    }
}
