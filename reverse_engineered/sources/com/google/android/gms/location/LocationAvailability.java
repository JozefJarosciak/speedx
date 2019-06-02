package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;

public final class LocationAvailability extends AbstractSafeParcelable {
    public static final LocationAvailabilityCreator CREATOR = new LocationAvailabilityCreator();
    int acZ;
    int ada;
    long adb;
    int adc;
    private final int mVersionCode;

    LocationAvailability(int i, int i2, int i3, int i4, long j) {
        this.mVersionCode = i;
        this.adc = i2;
        this.acZ = i3;
        this.ada = i4;
        this.adb = j;
    }

    public static LocationAvailability extractLocationAvailability(Intent intent) {
        return !hasLocationAvailability(intent) ? null : (LocationAvailability) intent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
    }

    public static boolean hasLocationAvailability(Intent intent) {
        return intent == null ? false : intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_AVAILABILITY");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocationAvailability)) {
            return false;
        }
        LocationAvailability locationAvailability = (LocationAvailability) obj;
        return this.adc == locationAvailability.adc && this.acZ == locationAvailability.acZ && this.ada == locationAvailability.ada && this.adb == locationAvailability.adb;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.adc), Integer.valueOf(this.acZ), Integer.valueOf(this.ada), Long.valueOf(this.adb));
    }

    public boolean isLocationAvailable() {
        return this.adc < 1000;
    }

    public String toString() {
        return "LocationAvailability[isLocationAvailable: " + isLocationAvailable() + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        LocationAvailabilityCreator.zza(this, parcel, i);
    }
}
