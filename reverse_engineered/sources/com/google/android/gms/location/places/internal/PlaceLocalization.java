package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import java.util.List;

@Deprecated
public final class PlaceLocalization extends AbstractSafeParcelable {
    public static final zzo CREATOR = new zzo();
    public final String address;
    public final String afU;
    public final String afV;
    public final List<String> afW;
    public final String name;
    public final int versionCode;

    public PlaceLocalization(int i, String str, String str2, String str3, String str4, List<String> list) {
        this.versionCode = i;
        this.name = str;
        this.address = str2;
        this.afU = str3;
        this.afV = str4;
        this.afW = list;
    }

    public static PlaceLocalization zza(String str, String str2, String str3, String str4, List<String> list) {
        return new PlaceLocalization(0, str, str2, str3, str4, list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceLocalization)) {
            return false;
        }
        PlaceLocalization placeLocalization = (PlaceLocalization) obj;
        return zzaa.equal(this.name, placeLocalization.name) && zzaa.equal(this.address, placeLocalization.address) && zzaa.equal(this.afU, placeLocalization.afU) && zzaa.equal(this.afV, placeLocalization.afV) && zzaa.equal(this.afW, placeLocalization.afW);
    }

    public int hashCode() {
        return zzaa.hashCode(new Object[]{this.name, this.address, this.afU, this.afV});
    }

    public String toString() {
        return zzaa.zzz(this).zzg("name", this.name).zzg(GeocodingCriteria.TYPE_ADDRESS, this.address).zzg("internationalPhoneNumber", this.afU).zzg("regularOpenHours", this.afV).zzg("attributions", this.afW).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzo zzo = CREATOR;
        zzo.zza(this, parcel, i);
    }
}
