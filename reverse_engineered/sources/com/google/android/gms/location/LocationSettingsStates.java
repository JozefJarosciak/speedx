package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zzc;

public final class LocationSettingsStates extends AbstractSafeParcelable {
    public static final Creator<LocationSettingsStates> CREATOR = new zzi();
    private final boolean ado;
    private final boolean adp;
    private final boolean adq;
    private final boolean adr;
    private final boolean ads;
    private final boolean adt;
    private final int mVersionCode;

    LocationSettingsStates(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.mVersionCode = i;
        this.ado = z;
        this.adp = z2;
        this.adq = z3;
        this.adr = z4;
        this.ads = z5;
        this.adt = z6;
    }

    public static LocationSettingsStates fromIntent(Intent intent) {
        return (LocationSettingsStates) zzc.zza(intent, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", CREATOR);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean isBlePresent() {
        return this.adt;
    }

    public boolean isBleUsable() {
        return this.adq;
    }

    public boolean isGpsPresent() {
        return this.adr;
    }

    public boolean isGpsUsable() {
        return this.ado;
    }

    public boolean isLocationPresent() {
        return this.adr || this.ads;
    }

    public boolean isLocationUsable() {
        return this.ado || this.adp;
    }

    public boolean isNetworkLocationPresent() {
        return this.ads;
    }

    public boolean isNetworkLocationUsable() {
        return this.adp;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }
}
