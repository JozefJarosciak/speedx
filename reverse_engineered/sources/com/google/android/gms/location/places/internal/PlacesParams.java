package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.util.Locale;

public class PlacesParams extends AbstractSafeParcelable {
    public static final zzs CREATOR = new zzs();
    public static final PlacesParams agc = new PlacesParams("com.google.android.gms", Locale.getDefault(), null);
    public final String DP;
    public final String afc;
    public final String agd;
    public final String age;
    public final int agf;
    public final int agg;
    public final int versionCode;

    public PlacesParams(int i, String str, String str2, String str3, String str4, int i2, int i3) {
        this.versionCode = i;
        this.agd = str;
        this.age = str2;
        this.DP = str3;
        this.afc = str4;
        this.agf = i2;
        this.agg = i3;
    }

    public PlacesParams(String str, Locale locale, String str2) {
        this(3, str, locale.toString(), str2, null, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, 0);
    }

    public PlacesParams(String str, Locale locale, String str2, String str3, int i) {
        this(3, str, locale.toString(), str2, str3, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof PlacesParams)) {
            return false;
        }
        PlacesParams placesParams = (PlacesParams) obj;
        return this.agf == placesParams.agf && this.agg == placesParams.agg && this.age.equals(placesParams.age) && this.agd.equals(placesParams.agd) && zzaa.equal(this.DP, placesParams.DP) && zzaa.equal(this.afc, placesParams.afc);
    }

    public int hashCode() {
        return zzaa.hashCode(this.agd, this.age, this.DP, this.afc, Integer.valueOf(this.agf), Integer.valueOf(this.agg));
    }

    @SuppressLint({"DefaultLocale"})
    public String toString() {
        return zzaa.zzz(this).zzg("clientPackageName", this.agd).zzg("locale", this.age).zzg("accountName", this.DP).zzg("gCoreClientName", this.afc).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzs.zza(this, parcel, i);
    }
}
