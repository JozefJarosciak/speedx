package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.zzd.zza;

public final class MarkerOptions implements SafeParcelable {
    public static final zzf CREATOR = new zzf();
    private String Fx;
    private LatLng ahK;
    private String aiE;
    private BitmapDescriptor aiF;
    private boolean aiG;
    private boolean aiH;
    private float aiI;
    private float aiJ;
    private float aiK;
    private float aim;
    private boolean ain;
    private float aiv;
    private float aiw;
    private float mAlpha;
    private final int mVersionCode;

    public MarkerOptions() {
        this.aiv = 0.5f;
        this.aiw = 1.0f;
        this.ain = true;
        this.aiH = false;
        this.aiI = 0.0f;
        this.aiJ = 0.5f;
        this.aiK = 0.0f;
        this.mAlpha = 1.0f;
        this.mVersionCode = 1;
    }

    MarkerOptions(int i, LatLng latLng, String str, String str2, IBinder iBinder, float f, float f2, boolean z, boolean z2, boolean z3, float f3, float f4, float f5, float f6, float f7) {
        this.aiv = 0.5f;
        this.aiw = 1.0f;
        this.ain = true;
        this.aiH = false;
        this.aiI = 0.0f;
        this.aiJ = 0.5f;
        this.aiK = 0.0f;
        this.mAlpha = 1.0f;
        this.mVersionCode = i;
        this.ahK = latLng;
        this.Fx = str;
        this.aiE = str2;
        this.aiF = iBinder == null ? null : new BitmapDescriptor(zza.zzfc(iBinder));
        this.aiv = f;
        this.aiw = f2;
        this.aiG = z;
        this.ain = z2;
        this.aiH = z3;
        this.aiI = f3;
        this.aiJ = f4;
        this.aiK = f5;
        this.mAlpha = f6;
        this.aim = f7;
    }

    public MarkerOptions alpha(float f) {
        this.mAlpha = f;
        return this;
    }

    public MarkerOptions anchor(float f, float f2) {
        this.aiv = f;
        this.aiw = f2;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public MarkerOptions draggable(boolean z) {
        this.aiG = z;
        return this;
    }

    public MarkerOptions flat(boolean z) {
        this.aiH = z;
        return this;
    }

    public float getAlpha() {
        return this.mAlpha;
    }

    public float getAnchorU() {
        return this.aiv;
    }

    public float getAnchorV() {
        return this.aiw;
    }

    public BitmapDescriptor getIcon() {
        return this.aiF;
    }

    public float getInfoWindowAnchorU() {
        return this.aiJ;
    }

    public float getInfoWindowAnchorV() {
        return this.aiK;
    }

    public LatLng getPosition() {
        return this.ahK;
    }

    public float getRotation() {
        return this.aiI;
    }

    public String getSnippet() {
        return this.aiE;
    }

    public String getTitle() {
        return this.Fx;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public float getZIndex() {
        return this.aim;
    }

    public MarkerOptions icon(@Nullable BitmapDescriptor bitmapDescriptor) {
        this.aiF = bitmapDescriptor;
        return this;
    }

    public MarkerOptions infoWindowAnchor(float f, float f2) {
        this.aiJ = f;
        this.aiK = f2;
        return this;
    }

    public boolean isDraggable() {
        return this.aiG;
    }

    public boolean isFlat() {
        return this.aiH;
    }

    public boolean isVisible() {
        return this.ain;
    }

    public MarkerOptions position(@NonNull LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("latlng cannot be null - a position is required.");
        }
        this.ahK = latLng;
        return this;
    }

    public MarkerOptions rotation(float f) {
        this.aiI = f;
        return this;
    }

    public MarkerOptions snippet(@Nullable String str) {
        this.aiE = str;
        return this;
    }

    public MarkerOptions title(@Nullable String str) {
        this.Fx = str;
        return this;
    }

    public MarkerOptions visible(boolean z) {
        this.ain = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    public MarkerOptions zIndex(float f) {
        this.aim = f;
        return this;
    }

    IBinder zzbqh() {
        return this.aiF == null ? null : this.aiF.zzbpg().asBinder();
    }
}
