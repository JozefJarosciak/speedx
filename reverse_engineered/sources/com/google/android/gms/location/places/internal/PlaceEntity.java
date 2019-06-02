package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class PlaceEntity implements SafeParcelable, Place {
    public static final zzl CREATOR = new zzl();
    private final String MA;
    private final LatLng aeg;
    private final List<Integer> aeh;
    private final String aei;
    private final Uri aej;
    private final Bundle afD;
    @Deprecated
    private final PlaceLocalization afE;
    private final float afF;
    private final LatLngBounds afG;
    private final String afH;
    private final boolean afI;
    private final float afJ;
    private final int afK;
    private final long afL;
    private final List<Integer> afM;
    private final String afN;
    private final List<String> afO;
    private final Map<Integer, String> afP;
    private final TimeZone afQ;
    private Locale afz;
    private final String mName;
    final int mVersionCode;
    private final String zzbgk;

    PlaceEntity(int i, String str, List<Integer> list, List<Integer> list2, Bundle bundle, String str2, String str3, String str4, String str5, List<String> list3, LatLng latLng, float f, LatLngBounds latLngBounds, String str6, Uri uri, boolean z, float f2, int i2, long j, PlaceLocalization placeLocalization) {
        List emptyList;
        this.mVersionCode = i;
        this.zzbgk = str;
        this.aeh = Collections.unmodifiableList(list);
        this.afM = list2;
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.afD = bundle;
        this.mName = str2;
        this.MA = str3;
        this.aei = str4;
        this.afN = str5;
        if (list3 == null) {
            emptyList = Collections.emptyList();
        }
        this.afO = emptyList;
        this.aeg = latLng;
        this.afF = f;
        this.afG = latLngBounds;
        if (str6 == null) {
            str6 = "UTC";
        }
        this.afH = str6;
        this.aej = uri;
        this.afI = z;
        this.afJ = f2;
        this.afK = i2;
        this.afL = j;
        this.afP = Collections.unmodifiableMap(new HashMap());
        this.afQ = null;
        this.afz = null;
        this.afE = placeLocalization;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PlaceEntity)) {
            return false;
        }
        PlaceEntity placeEntity = (PlaceEntity) obj;
        return this.zzbgk.equals(placeEntity.zzbgk) && zzaa.equal(this.afz, placeEntity.afz) && this.afL == placeEntity.afL;
    }

    public /* synthetic */ Object freeze() {
        return zzbov();
    }

    public String getAddress() {
        return this.MA;
    }

    public CharSequence getAttributions() {
        return zzc.zzo(this.afO);
    }

    public String getId() {
        return this.zzbgk;
    }

    public LatLng getLatLng() {
        return this.aeg;
    }

    public Locale getLocale() {
        return this.afz;
    }

    public String getName() {
        return this.mName;
    }

    public String getPhoneNumber() {
        return this.aei;
    }

    public List<Integer> getPlaceTypes() {
        return this.aeh;
    }

    public int getPriceLevel() {
        return this.afK;
    }

    public float getRating() {
        return this.afJ;
    }

    public LatLngBounds getViewport() {
        return this.afG;
    }

    public Uri getWebsiteUri() {
        return this.aej;
    }

    public int hashCode() {
        return zzaa.hashCode(new Object[]{this.zzbgk, this.afz, Long.valueOf(this.afL)});
    }

    public boolean isDataValid() {
        return true;
    }

    public void setLocale(Locale locale) {
        this.afz = locale;
    }

    @SuppressLint({"DefaultLocale"})
    public String toString() {
        return zzaa.zzz(this).zzg("id", this.zzbgk).zzg("placeTypes", this.aeh).zzg("locale", this.afz).zzg("name", this.mName).zzg(GeocodingCriteria.TYPE_ADDRESS, this.MA).zzg("phoneNumber", this.aei).zzg("latlng", this.aeg).zzg("viewport", this.afG).zzg("websiteUri", this.aej).zzg("isPermanentlyClosed", Boolean.valueOf(this.afI)).zzg("priceLevel", Integer.valueOf(this.afK)).zzg("timestampSecs", Long.valueOf(this.afL)).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzl.zza(this, parcel, i);
    }

    public List<Integer> zzbom() {
        return this.afM;
    }

    public float zzbon() {
        return this.afF;
    }

    public String zzboo() {
        return this.afN;
    }

    public List<String> zzbop() {
        return this.afO;
    }

    public boolean zzboq() {
        return this.afI;
    }

    public long zzbor() {
        return this.afL;
    }

    public Bundle zzbos() {
        return this.afD;
    }

    public String zzbot() {
        return this.afH;
    }

    @Deprecated
    public PlaceLocalization zzbou() {
        return this.afE;
    }

    public Place zzbov() {
        return this;
    }
}
