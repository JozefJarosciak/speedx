package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;

public final class NearbyAlertRequest extends AbstractSafeParcelable {
    public static final zze CREATOR = new zze();
    private final int acH;
    private final int aev;
    @Deprecated
    private final PlaceFilter aew;
    private final NearbyAlertFilter aex;
    private final boolean aey;
    private final int aez;
    private int mPriority = 110;
    private final int mVersionCode;

    NearbyAlertRequest(int i, int i2, int i3, PlaceFilter placeFilter, NearbyAlertFilter nearbyAlertFilter, boolean z, int i4, int i5) {
        this.mVersionCode = i;
        this.acH = i2;
        this.aev = i3;
        if (nearbyAlertFilter != null) {
            this.aex = nearbyAlertFilter;
        } else if (placeFilter == null) {
            this.aex = null;
        } else if (placeFilter.getPlaceIds() != null && !placeFilter.getPlaceIds().isEmpty()) {
            this.aex = NearbyAlertFilter.zzm(placeFilter.getPlaceIds());
        } else if (placeFilter.zzbob() == null || placeFilter.zzbob().isEmpty()) {
            this.aex = null;
        } else {
            this.aex = NearbyAlertFilter.zzn(placeFilter.zzbob());
        }
        this.aew = null;
        this.aey = z;
        this.aez = i4;
        this.mPriority = i5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NearbyAlertRequest)) {
            return false;
        }
        NearbyAlertRequest nearbyAlertRequest = (NearbyAlertRequest) obj;
        return this.acH == nearbyAlertRequest.acH && this.aev == nearbyAlertRequest.aev && zzaa.equal(this.aex, nearbyAlertRequest.aex) && this.mPriority == nearbyAlertRequest.mPriority;
    }

    public int getPriority() {
        return this.mPriority;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzaa.hashCode(new Object[]{Integer.valueOf(this.acH), Integer.valueOf(this.aev), this.aex, Integer.valueOf(this.mPriority)});
    }

    public String toString() {
        return zzaa.zzz(this).zzg("transitionTypes", Integer.valueOf(this.acH)).zzg("loiteringTimeMillis", Integer.valueOf(this.aev)).zzg("nearbyAlertFilter", this.aex).zzg("priority", Integer.valueOf(this.mPriority)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zze.zza(this, parcel, i);
    }

    public int zzbns() {
        return this.acH;
    }

    public int zzbnw() {
        return this.aev;
    }

    @Deprecated
    public PlaceFilter zzbnx() {
        return null;
    }

    public NearbyAlertFilter zzbny() {
        return this.aex;
    }

    public boolean zzbnz() {
        return this.aey;
    }

    public int zzboa() {
        return this.aez;
    }
}
