package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;

@Deprecated
public class PlaceAlias extends AbstractSafeParcelable {
    public static final zzd CREATOR = new zzd();
    public static final PlaceAlias agj = new PlaceAlias(0, "Home");
    public static final PlaceAlias agk = new PlaceAlias(0, "Work");
    private final String agl;
    final int mVersionCode;

    PlaceAlias(int i, String str) {
        this.mVersionCode = i;
        this.agl = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceAlias)) {
            return false;
        }
        return zzaa.equal(this.agl, ((PlaceAlias) obj).agl);
    }

    public int hashCode() {
        return zzaa.hashCode(new Object[]{this.agl});
    }

    public String toString() {
        return zzaa.zzz(this).zzg("alias", this.agl).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }

    public String zzbpc() {
        return this.agl;
    }
}
