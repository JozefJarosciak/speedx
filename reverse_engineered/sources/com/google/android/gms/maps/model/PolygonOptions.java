package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.support.v4.view.ViewCompat;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PolygonOptions implements SafeParcelable {
    public static final zzh CREATOR = new zzh();
    private final List<LatLng> aiO;
    private final List<List<LatLng>> aiP;
    private boolean aiQ;
    private float aij;
    private int aik;
    private int ail;
    private float aim;
    private boolean ain;
    private boolean aio;
    private final int mVersionCode;

    public PolygonOptions() {
        this.aij = 10.0f;
        this.aik = ViewCompat.MEASURED_STATE_MASK;
        this.ail = 0;
        this.aim = 0.0f;
        this.ain = true;
        this.aiQ = false;
        this.aio = false;
        this.mVersionCode = 1;
        this.aiO = new ArrayList();
        this.aiP = new ArrayList();
    }

    PolygonOptions(int i, List<LatLng> list, List list2, float f, int i2, int i3, float f2, boolean z, boolean z2, boolean z3) {
        this.aij = 10.0f;
        this.aik = ViewCompat.MEASURED_STATE_MASK;
        this.ail = 0;
        this.aim = 0.0f;
        this.ain = true;
        this.aiQ = false;
        this.aio = false;
        this.mVersionCode = i;
        this.aiO = list;
        this.aiP = list2;
        this.aij = f;
        this.aik = i2;
        this.ail = i3;
        this.aim = f2;
        this.ain = z;
        this.aiQ = z2;
        this.aio = z3;
    }

    public PolygonOptions add(LatLng latLng) {
        this.aiO.add(latLng);
        return this;
    }

    public PolygonOptions add(LatLng... latLngArr) {
        this.aiO.addAll(Arrays.asList(latLngArr));
        return this;
    }

    public PolygonOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            this.aiO.add(add);
        }
        return this;
    }

    public PolygonOptions addHole(Iterable<LatLng> iterable) {
        ArrayList arrayList = new ArrayList();
        for (LatLng add : iterable) {
            arrayList.add(add);
        }
        this.aiP.add(arrayList);
        return this;
    }

    public PolygonOptions clickable(boolean z) {
        this.aio = z;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public PolygonOptions fillColor(int i) {
        this.ail = i;
        return this;
    }

    public PolygonOptions geodesic(boolean z) {
        this.aiQ = z;
        return this;
    }

    public int getFillColor() {
        return this.ail;
    }

    public List<List<LatLng>> getHoles() {
        return this.aiP;
    }

    public List<LatLng> getPoints() {
        return this.aiO;
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

    public boolean isGeodesic() {
        return this.aiQ;
    }

    public boolean isVisible() {
        return this.ain;
    }

    public PolygonOptions strokeColor(int i) {
        this.aik = i;
        return this;
    }

    public PolygonOptions strokeWidth(float f) {
        this.aij = f;
        return this;
    }

    public PolygonOptions visible(boolean z) {
        this.ain = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.zza(this, parcel, i);
    }

    public PolygonOptions zIndex(float f) {
        this.aim = f;
        return this;
    }

    List zzbqi() {
        return this.aiP;
    }
}
