package com.google.android.gms.location.places.internal;

import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.internal.AutocompletePredictionEntity.SubstringEntity;
import java.util.Collections;
import java.util.List;

public class zzb extends zzt implements AutocompletePrediction {
    public zzb(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    private String zzbog() {
        return zzao("ap_description", "");
    }

    private String zzboh() {
        return zzao("ap_primary_text", "");
    }

    private String zzboi() {
        return zzao("ap_secondary_text", "");
    }

    private List<SubstringEntity> zzboj() {
        return zza("ap_matched_subscriptions", SubstringEntity.CREATOR, Collections.emptyList());
    }

    private List<SubstringEntity> zzbok() {
        return zza("ap_primary_text_matched", SubstringEntity.CREATOR, Collections.emptyList());
    }

    private List<SubstringEntity> zzbol() {
        return zza("ap_secondary_text_matched", SubstringEntity.CREATOR, Collections.emptyList());
    }

    public /* synthetic */ Object freeze() {
        return zzboe();
    }

    public CharSequence getFullText(@Nullable CharacterStyle characterStyle) {
        return zzc.zza(zzbog(), zzboj(), characterStyle);
    }

    public String getPlaceId() {
        return zzao("ap_place_id", null);
    }

    public List<Integer> getPlaceTypes() {
        return zza("ap_place_types", Collections.emptyList());
    }

    public CharSequence getPrimaryText(@Nullable CharacterStyle characterStyle) {
        return zzc.zza(zzboh(), zzbok(), characterStyle);
    }

    public CharSequence getSecondaryText(@Nullable CharacterStyle characterStyle) {
        return zzc.zza(zzboi(), zzbol(), characterStyle);
    }

    public AutocompletePrediction zzboe() {
        return AutocompletePredictionEntity.zza(getPlaceId(), getPlaceTypes(), zzbof(), zzbog(), zzboj(), zzboh(), zzbok(), zzboi(), zzbol());
    }

    public int zzbof() {
        return zzx("ap_personalization_type", 6);
    }
}
