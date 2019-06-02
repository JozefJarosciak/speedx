package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zzd.zza;

public final class GroundOverlayOptions implements SafeParcelable {
    public static final zzc CREATOR = new zzc();
    public static final float NO_DIMENSION = -1.0f;
    private LatLngBounds agp;
    private float aif;
    private float aim;
    private boolean ain;
    private boolean aio;
    private BitmapDescriptor aiq;
    private LatLng air;
    private float ais;
    private float ait;
    private float aiu;
    private float aiv;
    private float aiw;
    private final int mVersionCode;

    public GroundOverlayOptions() {
        this.ain = true;
        this.aiu = 0.0f;
        this.aiv = 0.5f;
        this.aiw = 0.5f;
        this.aio = false;
        this.mVersionCode = 1;
    }

    GroundOverlayOptions(int i, IBinder iBinder, LatLng latLng, float f, float f2, LatLngBounds latLngBounds, float f3, float f4, boolean z, float f5, float f6, float f7, boolean z2) {
        this.ain = true;
        this.aiu = 0.0f;
        this.aiv = 0.5f;
        this.aiw = 0.5f;
        this.aio = false;
        this.mVersionCode = i;
        this.aiq = new BitmapDescriptor(zza.zzfc(iBinder));
        this.air = latLng;
        this.ais = f;
        this.ait = f2;
        this.agp = latLngBounds;
        this.aif = f3;
        this.aim = f4;
        this.ain = z;
        this.aiu = f5;
        this.aiv = f6;
        this.aiw = f7;
        this.aio = z2;
    }

    private GroundOverlayOptions zza(LatLng latLng, float f, float f2) {
        this.air = latLng;
        this.ais = f;
        this.ait = f2;
        return this;
    }

    public GroundOverlayOptions anchor(float f, float f2) {
        this.aiv = f;
        this.aiw = f2;
        return this;
    }

    public GroundOverlayOptions bearing(float f) {
        this.aif = ((f % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    public GroundOverlayOptions clickable(boolean z) {
        this.aio = z;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public float getAnchorU() {
        return this.aiv;
    }

    public float getAnchorV() {
        return this.aiw;
    }

    public float getBearing() {
        return this.aif;
    }

    public LatLngBounds getBounds() {
        return this.agp;
    }

    public float getHeight() {
        return this.ait;
    }

    public BitmapDescriptor getImage() {
        return this.aiq;
    }

    public LatLng getLocation() {
        return this.air;
    }

    public float getTransparency() {
        return this.aiu;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public float getWidth() {
        return this.ais;
    }

    public float getZIndex() {
        return this.aim;
    }

    public GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        this.aiq = bitmapDescriptor;
        return this;
    }

    public boolean isClickable() {
        return this.aio;
    }

    public boolean isVisible() {
        return this.ain;
    }

    public GroundOverlayOptions position(LatLng latLng, float f) {
        boolean z = true;
        zzab.zza(this.agp == null, (Object) "Position has already been set using positionFromBounds");
        zzab.zzb(latLng != null, (Object) "Location must be specified");
        if (f < 0.0f) {
            z = false;
        }
        zzab.zzb(z, (Object) "Width must be non-negative");
        return zza(latLng, f, -1.0f);
    }

    public GroundOverlayOptions position(LatLng latLng, float f, float f2) {
        boolean z = true;
        zzab.zza(this.agp == null, (Object) "Position has already been set using positionFromBounds");
        zzab.zzb(latLng != null, (Object) "Location must be specified");
        zzab.zzb(f >= 0.0f, (Object) "Width must be non-negative");
        if (f2 < 0.0f) {
            z = false;
        }
        zzab.zzb(z, (Object) "Height must be non-negative");
        return zza(latLng, f, f2);
    }

    public GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        boolean z = this.air == null;
        String valueOf = String.valueOf(this.air);
        zzab.zza(z, new StringBuilder(String.valueOf(valueOf).length() + 46).append("Position has already been set using position: ").append(valueOf).toString());
        this.agp = latLngBounds;
        return this;
    }

    public GroundOverlayOptions transparency(float f) {
        boolean z = f >= 0.0f && f <= 1.0f;
        zzab.zzb(z, (Object) "Transparency must be in the range [0..1]");
        this.aiu = f;
        return this;
    }

    public GroundOverlayOptions visible(boolean z) {
        this.ain = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzc.zza(this, parcel, i);
    }

    public GroundOverlayOptions zIndex(float f) {
        this.aim = f;
        return this;
    }

    IBinder zzbqg() {
        return this.aiq.zzbpg().asBinder();
    }
}
