package com.google.android.gms.location;

import android.content.Intent;
import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class LocationResult extends AbstractSafeParcelable {
    public static final Creator<LocationResult> CREATOR = new zzf();
    static final List<Location> adi = Collections.emptyList();
    private final List<Location> adj;
    private final int mVersionCode;

    LocationResult(int i, List<Location> list) {
        this.mVersionCode = i;
        this.adj = list;
    }

    public static LocationResult create(List<Location> list) {
        List list2;
        if (list == null) {
            list2 = adi;
        }
        return new LocationResult(2, list2);
    }

    public static LocationResult extractResult(Intent intent) {
        return !hasResult(intent) ? null : (LocationResult) intent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
    }

    public static boolean hasResult(Intent intent) {
        return intent == null ? false : intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocationResult)) {
            return false;
        }
        LocationResult locationResult = (LocationResult) obj;
        if (locationResult.adj.size() != this.adj.size()) {
            return false;
        }
        Iterator it = this.adj.iterator();
        for (Location time : locationResult.adj) {
            if (((Location) it.next()).getTime() != time.getTime()) {
                return false;
            }
        }
        return true;
    }

    public Location getLastLocation() {
        int size = this.adj.size();
        return size == 0 ? null : (Location) this.adj.get(size - 1);
    }

    @NonNull
    public List<Location> getLocations() {
        return this.adj;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        int i = 17;
        for (Location time : this.adj) {
            long time2 = time.getTime();
            i = ((int) (time2 ^ (time2 >>> 32))) + (i * 31);
        }
        return i;
    }

    public String toString() {
        String valueOf = String.valueOf(this.adj);
        return new StringBuilder(String.valueOf(valueOf).length() + 27).append("LocationResult[locations: ").append(valueOf).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }
}
