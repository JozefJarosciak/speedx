package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public final class LocationSettingsResult extends AbstractSafeParcelable implements Result {
    public static final Creator<LocationSettingsResult> CREATOR = new zzh();
    private final LocationSettingsStates adn;
    private final Status cc;
    private final int mVersionCode;

    LocationSettingsResult(int i, Status status, LocationSettingsStates locationSettingsStates) {
        this.mVersionCode = i;
        this.cc = status;
        this.adn = locationSettingsStates;
    }

    public LocationSettingsResult(Status status) {
        this(1, status, null);
    }

    public LocationSettingsStates getLocationSettingsStates() {
        return this.adn;
    }

    public Status getStatus() {
        return this.cc;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.zza(this, parcel, i);
    }
}
