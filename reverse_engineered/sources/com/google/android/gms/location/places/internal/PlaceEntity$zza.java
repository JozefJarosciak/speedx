package com.google.android.gms.location.places.internal;

import android.net.Uri;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.List;

public class PlaceEntity$zza {
    private String MA;
    private LatLng aeg;
    private String aei;
    private Uri aej;
    private float afF;
    private LatLngBounds afG;
    private boolean afI;
    private float afJ;
    private int afK;
    private List<String> afO;
    private List<Integer> afR;
    private String mName;
    private int mVersionCode = 0;
    private String zzbgk;

    public PlaceEntity$zza zza(LatLng latLng) {
        this.aeg = latLng;
        return this;
    }

    public PlaceEntity$zza zza(LatLngBounds latLngBounds) {
        this.afG = latLngBounds;
        return this;
    }

    public PlaceEntity$zza zzaa(List<Integer> list) {
        this.afR = list;
        return this;
    }

    public PlaceEntity$zza zzab(List<String> list) {
        this.afO = list;
        return this;
    }

    public PlaceEntity zzbow() {
        return new PlaceEntity(0, this.zzbgk, this.afR, Collections.emptyList(), null, this.mName, this.MA, this.aei, null, this.afO, this.aeg, this.afF, this.afG, null, this.aej, this.afI, this.afJ, this.afK, 0, PlaceLocalization.zza(this.mName, this.MA, this.aei, null, this.afO));
    }

    public PlaceEntity$zza zzby(boolean z) {
        this.afI = z;
        return this;
    }

    public PlaceEntity$zza zzg(float f) {
        this.afF = f;
        return this;
    }

    public PlaceEntity$zza zzh(float f) {
        this.afJ = f;
        return this;
    }

    public PlaceEntity$zza zzkr(String str) {
        this.zzbgk = str;
        return this;
    }

    public PlaceEntity$zza zzks(String str) {
        this.mName = str;
        return this;
    }

    public PlaceEntity$zza zzkt(String str) {
        this.MA = str;
        return this;
    }

    public PlaceEntity$zza zzku(String str) {
        this.aei = str;
        return this;
    }

    public PlaceEntity$zza zzr(Uri uri) {
        this.aej = uri;
        return this;
    }

    public PlaceEntity$zza zzua(int i) {
        this.afK = i;
        return this;
    }
}
