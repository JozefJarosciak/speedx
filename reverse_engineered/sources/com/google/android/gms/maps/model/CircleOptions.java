package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class CircleOptions implements SafeParcelable {
    public static final zzb CREATOR = new zzb();
    private LatLng aih;
    private double aii;
    private float aij;
    private int aik;
    private int ail;
    private float aim;
    private boolean ain;
    private boolean aio;
    private final int mVersionCode;

    public CircleOptions() {
        this.aih = null;
        this.aii = 0.0d;
        this.aij = 10.0f;
        this.aik = ViewCompat.MEASURED_STATE_MASK;
        this.ail = 0;
        this.aim = 0.0f;
        this.ain = true;
        this.aio = false;
        this.mVersionCode = 1;
    }

    CircleOptions(int i, LatLng latLng, double d, float f, int i2, int i3, float f2, boolean z, boolean z2) {
        this.aih = null;
        this.aii = 0.0d;
        this.aij = 10.0f;
        this.aik = ViewCompat.MEASURED_STATE_MASK;
        this.ail = 0;
        this.aim = 0.0f;
        this.ain = true;
        this.aio = false;
        this.mVersionCode = i;
        this.aih = latLng;
        this.aii = d;
        this.aij = f;
        this.aik = i2;
        this.ail = i3;
        this.aim = f2;
        this.ain = z;
        this.aio = z2;
    }

    public CircleOptions center(LatLng latLng) {
        this.aih = latLng;
        return this;
    }

    public CircleOptions clickable(boolean z) {
        this.aio = z;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public CircleOptions fillColor(int i) {
        this.ail = i;
        return this;
    }

    public LatLng getCenter() {
        return this.aih;
    }

    public int getFillColor() {
        return this.ail;
    }

    public double getRadius() {
        return this.aii;
    }

    public int getStrokeColor() {
        return this.aik;
    }

    public float getStrokeWidth() {
        return this.aij;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public float getZIndex() {
        return this.aim;
    }

    public boolean isClickable() {
        return this.aio;
    }

    public boolean isVisible() {
        return this.ain;
    }

    public CircleOptions radius(double d) {
        this.aii = d;
        return this;
    }

    public CircleOptions strokeColor(int i) {
        this.aik = i;
        return this;
    }

    public CircleOptions strokeWidth(float f) {
        this.aij = f;
        return this;
    }

    public CircleOptions visible(boolean z) {
        this.ain = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb.zza(this, parcel, i);
    }

    public CircleOptions zIndex(float f) {
        this.aim = f;
        return this;
    }
}
