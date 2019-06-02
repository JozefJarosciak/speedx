package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.C1478R;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.CameraPosition;

public final class GoogleMapOptions implements SafeParcelable {
    public static final zza CREATOR = new zza();
    private Boolean agV;
    private Boolean agW;
    private int agX;
    private CameraPosition agY;
    private Boolean agZ;
    private Boolean aha;
    private Boolean ahb;
    private Boolean ahc;
    private Boolean ahd;
    private Boolean ahe;
    private Boolean ahf;
    private Boolean ahg;
    private Boolean ahh;
    private final int mVersionCode;

    public GoogleMapOptions() {
        this.agX = -1;
        this.mVersionCode = 1;
    }

    GoogleMapOptions(int i, byte b, byte b2, int i2, CameraPosition cameraPosition, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10, byte b11) {
        this.agX = -1;
        this.mVersionCode = i;
        this.agV = zza.zza(b);
        this.agW = zza.zza(b2);
        this.agX = i2;
        this.agY = cameraPosition;
        this.agZ = zza.zza(b3);
        this.aha = zza.zza(b4);
        this.ahb = zza.zza(b5);
        this.ahc = zza.zza(b6);
        this.ahd = zza.zza(b7);
        this.ahe = zza.zza(b8);
        this.ahf = zza.zza(b9);
        this.ahg = zza.zza(b10);
        this.ahh = zza.zza(b11);
    }

    public static GoogleMapOptions createFromAttributes(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, C1478R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(C1478R.styleable.MapAttrs_mapType)) {
            googleMapOptions.mapType(obtainAttributes.getInt(C1478R.styleable.MapAttrs_mapType, -1));
        }
        if (obtainAttributes.hasValue(C1478R.styleable.MapAttrs_zOrderOnTop)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(C1478R.styleable.MapAttrs_zOrderOnTop, false));
        }
        if (obtainAttributes.hasValue(C1478R.styleable.MapAttrs_useViewLifecycle)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(C1478R.styleable.MapAttrs_useViewLifecycle, false));
        }
        if (obtainAttributes.hasValue(C1478R.styleable.MapAttrs_uiCompass)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(C1478R.styleable.MapAttrs_uiCompass, true));
        }
        if (obtainAttributes.hasValue(C1478R.styleable.MapAttrs_uiRotateGestures)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(C1478R.styleable.MapAttrs_uiRotateGestures, true));
        }
        if (obtainAttributes.hasValue(C1478R.styleable.MapAttrs_uiScrollGestures)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(C1478R.styleable.MapAttrs_uiScrollGestures, true));
        }
        if (obtainAttributes.hasValue(C1478R.styleable.MapAttrs_uiTiltGestures)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(C1478R.styleable.MapAttrs_uiTiltGestures, true));
        }
        if (obtainAttributes.hasValue(C1478R.styleable.MapAttrs_uiZoomGestures)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(C1478R.styleable.MapAttrs_uiZoomGestures, true));
        }
        if (obtainAttributes.hasValue(C1478R.styleable.MapAttrs_uiZoomControls)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(C1478R.styleable.MapAttrs_uiZoomControls, true));
        }
        if (obtainAttributes.hasValue(C1478R.styleable.MapAttrs_liteMode)) {
            googleMapOptions.liteMode(obtainAttributes.getBoolean(C1478R.styleable.MapAttrs_liteMode, false));
        }
        if (obtainAttributes.hasValue(C1478R.styleable.MapAttrs_uiMapToolbar)) {
            googleMapOptions.mapToolbarEnabled(obtainAttributes.getBoolean(C1478R.styleable.MapAttrs_uiMapToolbar, true));
        }
        if (obtainAttributes.hasValue(C1478R.styleable.MapAttrs_ambientEnabled)) {
            googleMapOptions.ambientEnabled(obtainAttributes.getBoolean(C1478R.styleable.MapAttrs_ambientEnabled, false));
        }
        googleMapOptions.camera(CameraPosition.createFromAttributes(context, attributeSet));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    public GoogleMapOptions ambientEnabled(boolean z) {
        this.ahh = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions camera(CameraPosition cameraPosition) {
        this.agY = cameraPosition;
        return this;
    }

    public GoogleMapOptions compassEnabled(boolean z) {
        this.aha = Boolean.valueOf(z);
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public Boolean getAmbientEnabled() {
        return this.ahh;
    }

    public CameraPosition getCamera() {
        return this.agY;
    }

    public Boolean getCompassEnabled() {
        return this.aha;
    }

    public Boolean getLiteMode() {
        return this.ahf;
    }

    public Boolean getMapToolbarEnabled() {
        return this.ahg;
    }

    public int getMapType() {
        return this.agX;
    }

    public Boolean getRotateGesturesEnabled() {
        return this.ahe;
    }

    public Boolean getScrollGesturesEnabled() {
        return this.ahb;
    }

    public Boolean getTiltGesturesEnabled() {
        return this.ahd;
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.agW;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public Boolean getZOrderOnTop() {
        return this.agV;
    }

    public Boolean getZoomControlsEnabled() {
        return this.agZ;
    }

    public Boolean getZoomGesturesEnabled() {
        return this.ahc;
    }

    public GoogleMapOptions liteMode(boolean z) {
        this.ahf = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions mapToolbarEnabled(boolean z) {
        this.ahg = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions mapType(int i) {
        this.agX = i;
        return this;
    }

    public GoogleMapOptions rotateGesturesEnabled(boolean z) {
        this.ahe = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions scrollGesturesEnabled(boolean z) {
        this.ahb = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions tiltGesturesEnabled(boolean z) {
        this.ahd = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions useViewLifecycleInFragment(boolean z) {
        this.agW = Boolean.valueOf(z);
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public GoogleMapOptions zOrderOnTop(boolean z) {
        this.agV = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions zoomControlsEnabled(boolean z) {
        this.agZ = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions zoomGesturesEnabled(boolean z) {
        this.ahc = Boolean.valueOf(z);
        return this;
    }

    byte zzbpi() {
        return zza.zze(this.agV);
    }

    byte zzbpj() {
        return zza.zze(this.agW);
    }

    byte zzbpk() {
        return zza.zze(this.agZ);
    }

    byte zzbpl() {
        return zza.zze(this.aha);
    }

    byte zzbpm() {
        return zza.zze(this.ahb);
    }

    byte zzbpn() {
        return zza.zze(this.ahc);
    }

    byte zzbpo() {
        return zza.zze(this.ahd);
    }

    byte zzbpp() {
        return zza.zze(this.ahe);
    }

    byte zzbpq() {
        return zza.zze(this.ahf);
    }

    byte zzbpr() {
        return zza.zze(this.ahg);
    }

    byte zzbps() {
        return zza.zze(this.ahh);
    }
}
