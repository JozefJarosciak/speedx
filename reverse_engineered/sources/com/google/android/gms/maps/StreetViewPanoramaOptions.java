package com.google.android.gms.maps;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public final class StreetViewPanoramaOptions implements SafeParcelable {
    public static final zzb CREATOR = new zzb();
    private Boolean agW;
    private StreetViewPanoramaCamera ahI;
    private String ahJ;
    private LatLng ahK;
    private Integer ahL;
    private Boolean ahM;
    private Boolean ahN;
    private Boolean ahO;
    private Boolean ahc;
    private final int mVersionCode;

    public StreetViewPanoramaOptions() {
        this.ahM = Boolean.valueOf(true);
        this.ahc = Boolean.valueOf(true);
        this.ahN = Boolean.valueOf(true);
        this.ahO = Boolean.valueOf(true);
        this.mVersionCode = 1;
    }

    StreetViewPanoramaOptions(int i, StreetViewPanoramaCamera streetViewPanoramaCamera, String str, LatLng latLng, Integer num, byte b, byte b2, byte b3, byte b4, byte b5) {
        this.ahM = Boolean.valueOf(true);
        this.ahc = Boolean.valueOf(true);
        this.ahN = Boolean.valueOf(true);
        this.ahO = Boolean.valueOf(true);
        this.mVersionCode = i;
        this.ahI = streetViewPanoramaCamera;
        this.ahK = latLng;
        this.ahL = num;
        this.ahJ = str;
        this.ahM = zza.zza(b);
        this.ahc = zza.zza(b2);
        this.ahN = zza.zza(b3);
        this.ahO = zza.zza(b4);
        this.agW = zza.zza(b5);
    }

    public int describeContents() {
        return 0;
    }

    public Boolean getPanningGesturesEnabled() {
        return this.ahN;
    }

    public String getPanoramaId() {
        return this.ahJ;
    }

    public LatLng getPosition() {
        return this.ahK;
    }

    public Integer getRadius() {
        return this.ahL;
    }

    public Boolean getStreetNamesEnabled() {
        return this.ahO;
    }

    public StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.ahI;
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.agW;
    }

    public Boolean getUserNavigationEnabled() {
        return this.ahM;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public Boolean getZoomGesturesEnabled() {
        return this.ahc;
    }

    public StreetViewPanoramaOptions panningGesturesEnabled(boolean z) {
        this.ahN = Boolean.valueOf(z);
        return this;
    }

    public StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.ahI = streetViewPanoramaCamera;
        return this;
    }

    public StreetViewPanoramaOptions panoramaId(String str) {
        this.ahJ = str;
        return this;
    }

    public StreetViewPanoramaOptions position(LatLng latLng) {
        this.ahK = latLng;
        return this;
    }

    public StreetViewPanoramaOptions position(LatLng latLng, Integer num) {
        this.ahK = latLng;
        this.ahL = num;
        return this;
    }

    public StreetViewPanoramaOptions streetNamesEnabled(boolean z) {
        this.ahO = Boolean.valueOf(z);
        return this;
    }

    public StreetViewPanoramaOptions useViewLifecycleInFragment(boolean z) {
        this.agW = Boolean.valueOf(z);
        return this;
    }

    public StreetViewPanoramaOptions userNavigationEnabled(boolean z) {
        this.ahM = Boolean.valueOf(z);
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public StreetViewPanoramaOptions zoomGesturesEnabled(boolean z) {
        this.ahc = Boolean.valueOf(z);
        return this;
    }

    byte zzbpj() {
        return zza.zze(this.agW);
    }

    byte zzbpn() {
        return zza.zze(this.ahc);
    }

    byte zzbpx() {
        return zza.zze(this.ahM);
    }

    byte zzbpy() {
        return zza.zze(this.ahN);
    }

    byte zzbpz() {
        return zza.zze(this.ahO);
    }
}
