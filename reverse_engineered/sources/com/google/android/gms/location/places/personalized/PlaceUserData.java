package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.util.List;

@Deprecated
public class PlaceUserData extends AbstractSafeParcelable {
    public static final zzf CREATOR = new zzf();
    private final String aeL;
    private final List<PlaceAlias> agh;
    private final String cf;
    final int mVersionCode;

    PlaceUserData(int i, String str, String str2, List<PlaceAlias> list) {
        this.mVersionCode = i;
        this.cf = str;
        this.aeL = str2;
        this.agh = list;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceUserData)) {
            return false;
        }
        PlaceUserData placeUserData = (PlaceUserData) obj;
        return this.cf.equals(placeUserData.cf) && this.aeL.equals(placeUserData.aeL) && this.agh.equals(placeUserData.agh);
    }

    public String getPlaceId() {
        return this.aeL;
    }

    public int hashCode() {
        return zzaa.hashCode(new Object[]{this.cf, this.aeL, this.agh});
    }

    public String toString() {
        return zzaa.zzz(this).zzg("accountName", this.cf).zzg("placeId", this.aeL).zzg("placeAliases", this.agh).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    public List<PlaceAlias> zzbpa() {
        return this.agh;
    }

    public String zzbpd() {
        return this.cf;
    }
}
