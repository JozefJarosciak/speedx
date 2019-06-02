package com.google.android.gms.location.places;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.util.concurrent.TimeUnit;

public final class PlaceRequest extends AbstractSafeParcelable {
    public static final Creator<PlaceRequest> CREATOR = new zzk();
    static final long aeM = TimeUnit.SECONDS.toMillis(20);
    static final long aeN = TimeUnit.MINUTES.toMillis(5);
    static final long aeO = TimeUnit.MINUTES.toMillis(40);
    static final long aeP = TimeUnit.HOURS.toMillis(1);
    static final long aeQ = aeN;
    private final long acI;
    private final long add;
    private final PlaceFilter aeR;
    private final int mPriority;
    final int mVersionCode;

    public PlaceRequest(int i, PlaceFilter placeFilter, long j, int i2, long j2) {
        this.mVersionCode = i;
        this.aeR = placeFilter;
        this.add = j;
        this.mPriority = i2;
        this.acI = j2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceRequest)) {
            return false;
        }
        PlaceRequest placeRequest = (PlaceRequest) obj;
        return zzaa.equal(this.aeR, placeRequest.aeR) && this.add == placeRequest.add && this.mPriority == placeRequest.mPriority && this.acI == placeRequest.acI;
    }

    public long getExpirationTime() {
        return this.acI;
    }

    public long getInterval() {
        return this.add;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public int hashCode() {
        return zzaa.hashCode(this.aeR, Long.valueOf(this.add), Integer.valueOf(this.mPriority), Long.valueOf(this.acI));
    }

    @SuppressLint({"DefaultLocale"})
    public String toString() {
        return zzaa.zzz(this).zzg("filter", this.aeR).zzg("interval", Long.valueOf(this.add)).zzg("priority", Integer.valueOf(this.mPriority)).zzg("expireAt", Long.valueOf(this.acI)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzk.zza(this, parcel, i);
    }

    public PlaceFilter zzbnx() {
        return this.aeR;
    }
}
