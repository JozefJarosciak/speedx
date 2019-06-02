package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.util.List;

public class AliasedPlace implements SafeParcelable {
    public static final zza CREATOR = new zza();
    private final String aeL;
    private final List<String> agh;
    final int mVersionCode;

    AliasedPlace(int i, @NonNull String str, @NonNull List<String> list) {
        this.mVersionCode = i;
        this.aeL = str;
        this.agh = list;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AliasedPlace)) {
            return false;
        }
        AliasedPlace aliasedPlace = (AliasedPlace) obj;
        return this.aeL.equals(aliasedPlace.aeL) && this.agh.equals(aliasedPlace.agh);
    }

    public String getPlaceId() {
        return this.aeL;
    }

    public int hashCode() {
        return zzaa.hashCode(this.aeL, this.agh);
    }

    public String toString() {
        return zzaa.zzz(this).zzg("placeId", this.aeL).zzg("placeAliases", this.agh).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public List<String> zzbpa() {
        return this.agh;
    }
}
