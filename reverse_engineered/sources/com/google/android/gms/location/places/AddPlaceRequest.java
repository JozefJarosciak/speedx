package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.maps.model.LatLng;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AddPlaceRequest extends AbstractSafeParcelable {
    public static final Creator<AddPlaceRequest> CREATOR = new zzb();
    private final String MA;
    private final LatLng aeg;
    private final List<Integer> aeh;
    private final String aei;
    private final Uri aej;
    private final String mName;
    final int mVersionCode;

    AddPlaceRequest(int i, String str, LatLng latLng, String str2, List<Integer> list, String str3, Uri uri) {
        boolean z = false;
        this.mVersionCode = i;
        this.mName = zzab.zzhs(str);
        this.aeg = (LatLng) zzab.zzaa(latLng);
        this.MA = zzab.zzhs(str2);
        this.aeh = new ArrayList((Collection) zzab.zzaa(list));
        zzab.zzb(!this.aeh.isEmpty(), (Object) "At least one place type should be provided.");
        if (!(TextUtils.isEmpty(str3) && uri == null)) {
            z = true;
        }
        zzab.zzb(z, (Object) "One of phone number or URI should be provided.");
        this.aei = str3;
        this.aej = uri;
    }

    public AddPlaceRequest(String str, LatLng latLng, String str2, List<Integer> list, Uri uri) {
        this(str, latLng, str2, list, null, (Uri) zzab.zzaa(uri));
    }

    public AddPlaceRequest(String str, LatLng latLng, String str2, List<Integer> list, String str3) {
        this(str, latLng, str2, list, zzab.zzhs(str3), null);
    }

    public AddPlaceRequest(String str, LatLng latLng, String str2, List<Integer> list, String str3, Uri uri) {
        this(0, str, latLng, str2, list, str3, uri);
    }

    public String getAddress() {
        return this.MA;
    }

    public LatLng getLatLng() {
        return this.aeg;
    }

    public String getName() {
        return this.mName;
    }

    @Nullable
    public String getPhoneNumber() {
        return this.aei;
    }

    public List<Integer> getPlaceTypes() {
        return this.aeh;
    }

    @Nullable
    public Uri getWebsiteUri() {
        return this.aej;
    }

    public String toString() {
        return zzaa.zzz(this).zzg("name", this.mName).zzg("latLng", this.aeg).zzg(GeocodingCriteria.TYPE_ADDRESS, this.MA).zzg("placeTypes", this.aeh).zzg("phoneNumer", this.aei).zzg("websiteUri", this.aej).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }
}
