package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

public class LocationRequestInternal extends AbstractSafeParcelable {
    public static final zzm CREATOR = new zzm();
    static final List<ClientIdentity> adT = Collections.emptyList();
    LocationRequest PM;
    boolean acu;
    List<ClientIdentity> adU;
    boolean adV;
    boolean adW;
    @Nullable
    String mTag;
    private final int mVersionCode;

    LocationRequestInternal(int i, LocationRequest locationRequest, boolean z, List<ClientIdentity> list, @Nullable String str, boolean z2, boolean z3) {
        this.mVersionCode = i;
        this.PM = locationRequest;
        this.acu = z;
        this.adU = list;
        this.mTag = str;
        this.adV = z2;
        this.adW = z3;
    }

    public static LocationRequestInternal zza(@Nullable String str, LocationRequest locationRequest) {
        return new LocationRequestInternal(1, locationRequest, true, adT, str, false, false);
    }

    @Deprecated
    public static LocationRequestInternal zzb(LocationRequest locationRequest) {
        return zza(null, locationRequest);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LocationRequestInternal)) {
            return false;
        }
        LocationRequestInternal locationRequestInternal = (LocationRequestInternal) obj;
        return zzaa.equal(this.PM, locationRequestInternal.PM) && this.acu == locationRequestInternal.acu && this.adV == locationRequestInternal.adV && zzaa.equal(this.adU, locationRequestInternal.adU) && this.adW == locationRequestInternal.adW;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return this.PM.hashCode();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.PM.toString());
        if (this.mTag != null) {
            stringBuilder.append(" tag=").append(this.mTag);
        }
        stringBuilder.append(" trigger=").append(this.acu);
        stringBuilder.append(" hideAppOps=").append(this.adV);
        stringBuilder.append(" clients=").append(this.adU);
        stringBuilder.append(" forceCoarseLocation=").append(this.adW);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzm.zza(this, parcel, i);
    }
}
