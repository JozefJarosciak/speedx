package com.google.android.gms.location.places.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class zzr extends zzt implements Place {
    private final String aeL = zzao("place_id", "");

    public zzr(DataHolder dataHolder, int i, Context context) {
        super(dataHolder, i);
    }

    private List<String> zzbop() {
        return zzb("place_attributions", Collections.emptyList());
    }

    private PlaceEntity zzboz() {
        PlaceEntity zzbow = new PlaceEntity$zza().zzkt(getAddress().toString()).zzab(zzbop()).zzkr(getId()).zzby(zzboq()).zza(getLatLng()).zzg(zzbon()).zzks(getName().toString()).zzku(getPhoneNumber().toString()).zzua(getPriceLevel()).zzh(getRating()).zzaa(getPlaceTypes()).zza(getViewport()).zzr(getWebsiteUri()).zzbow();
        zzbow.setLocale(getLocale());
        return zzbow;
    }

    public /* synthetic */ Object freeze() {
        return zzbov();
    }

    public CharSequence getAddress() {
        return zzao("place_address", "");
    }

    public CharSequence getAttributions() {
        return zzc.zzo(zzbop());
    }

    public String getId() {
        return this.aeL;
    }

    public LatLng getLatLng() {
        return (LatLng) zza("place_lat_lng", LatLng.CREATOR);
    }

    public Locale getLocale() {
        Object zzao = zzao("place_locale_language", "");
        if (!TextUtils.isEmpty(zzao)) {
            return new Locale(zzao, zzao("place_locale_country", ""));
        }
        zzao = zzao("place_locale", "");
        return !TextUtils.isEmpty(zzao) ? new Locale(zzao) : Locale.getDefault();
    }

    public CharSequence getName() {
        return zzao("place_name", "");
    }

    public CharSequence getPhoneNumber() {
        return zzao("place_phone_number", "");
    }

    public List<Integer> getPlaceTypes() {
        return zza("place_types", Collections.emptyList());
    }

    public int getPriceLevel() {
        return zzx("place_price_level", -1);
    }

    public float getRating() {
        return zzb("place_rating", -1.0f);
    }

    public LatLngBounds getViewport() {
        return (LatLngBounds) zza("place_viewport", LatLngBounds.CREATOR);
    }

    public Uri getWebsiteUri() {
        String zzao = zzao("place_website_uri", null);
        return zzao == null ? null : Uri.parse(zzao);
    }

    public float zzbon() {
        return zzb("place_level_number", 0.0f);
    }

    public boolean zzboq() {
        return zzn("place_is_permanently_closed", false);
    }

    public Place zzbov() {
        return zzboz();
    }
}
