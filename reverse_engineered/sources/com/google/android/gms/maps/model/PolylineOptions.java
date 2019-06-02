package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolylineOptions implements SafeParcelable {
    public static final zzi CREATOR = new zzi();
    private final List<LatLng> aiO;
    private boolean aiQ;
    private float aim;
    private boolean ain;
    private boolean aio;
    private float ais;
    private int mColor;
    private final int mVersionCode;

    public PolylineOptions() {
        this.ais = 10.0f;
        this.mColor = ViewCompat.MEASURED_STATE_MASK;
        this.aim = 0.0f;
        this.ain = true;
        this.aiQ = false;
        this.aio = false;
        this.mVersionCode = 1;
        this.aiO = new ArrayList();
    }

    PolylineOptions(int i, List list, float f, int i2, float f2, boolean z, boolean z2, boolean z3) {
        this.ais = 10.0f;
        this.mColor = ViewCompat.MEASURED_STATE_MASK;
        this.aim = 0.0f;
        this.ain = true;
        this.aiQ = false;
        this.aio = false;
        this.mVersionCode = i;
        this.aiO = list;
        this.ais = f;
        this.mColor = i2;
        this.aim = f2;
        this.ain = z;
        this.aiQ = z2;
        this.aio = z3;
    }

    public PolylineOptions add(LatLng latLng) {
        this.aiO.add(latLng);
        return this;
    }

    public PolylineOptions add(LatLng... latLngArr) {
        this.aiO.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolylineOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.aiO.add(add);
        }
        return this;
    }

    public PolylineOptions clickable(boolean z) {
        this.aio = z;
        return this;
    }

    public PolylineOptions color(int i) {
        this.mColor = i;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public PolylineOptions geodesic(boolean z) {
        this.aiQ = z;
        return this;
    }

    public int getColor() {
        return this.mColor;
    }

    public List<LatLng> getPoints() {
        return this.aiO;
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

    public boolean isClickable() {
        return this.aio;
    }

    public boolean isGeodesic() {
        return this.aiQ;
    }

    public boolean isVisible() {
        return this.ain;
    }

    public PolylineOptions visible(boolean z) {
        this.ain = z;
        return this;
    }

    public PolylineOptions width(float f) {
        this.ais = f;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }

    public PolylineOptions zIndex(float f) {
        this.aim = f;
        return this;
    }
}
