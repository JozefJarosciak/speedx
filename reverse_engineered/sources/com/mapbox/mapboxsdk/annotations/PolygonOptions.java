package com.mapbox.mapboxsdk.annotations;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mapbox.mapboxsdk.geometry.LatLng;
import java.util.ArrayList;
import java.util.List;

public final class PolygonOptions implements Parcelable {
    public static final Creator<PolygonOptions> CREATOR = new C41871();
    private Polygon polygon;

    /* renamed from: com.mapbox.mapboxsdk.annotations.PolygonOptions$1 */
    static class C41871 implements Creator<PolygonOptions> {
        C41871() {
        }

        public PolygonOptions createFromParcel(Parcel parcel) {
            return new PolygonOptions(parcel);
        }

        public PolygonOptions[] newArray(int i) {
            return new PolygonOptions[i];
        }
    }

    private PolygonOptions(Parcel parcel) {
        this.polygon = new Polygon();
        Iterable arrayList = new ArrayList();
        parcel.readList(arrayList, LatLng.class.getClassLoader());
        addAll(arrayList);
        alpha(parcel.readFloat());
        fillColor(parcel.readInt());
        strokeColor(parcel.readInt());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(getPoints());
        parcel.writeFloat(getAlpha());
        parcel.writeInt(getFillColor());
        parcel.writeInt(getStrokeColor());
    }

    public PolygonOptions() {
        this.polygon = new Polygon();
    }

    public PolygonOptions add(LatLng latLng) {
        this.polygon.addPoint(latLng);
        return this;
    }

    public PolygonOptions add(LatLng... latLngArr) {
        for (LatLng add : latLngArr) {
            add(add);
        }
        return this;
    }

    public PolygonOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            add(add);
        }
        return this;
    }

    public PolygonOptions alpha(float f) {
        this.polygon.setAlpha(f);
        return this;
    }

    public float getAlpha() {
        return this.polygon.getAlpha();
    }

    public PolygonOptions fillColor(int i) {
        this.polygon.setFillColor(i);
        return this;
    }

    public int getFillColor() {
        return this.polygon.getFillColor();
    }

    public Polygon getPolygon() {
        return this.polygon;
    }

    public PolygonOptions strokeColor(int i) {
        this.polygon.setStrokeColor(i);
        return this;
    }

    public int getStrokeColor() {
        return this.polygon.getStrokeColor();
    }

    public List<LatLng> getPoints() {
        return this.polygon.getPoints();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        if (r4 != r5) goto L_0x0006;
    L_0x0004:
        r1 = r0;
    L_0x0005:
        return r1;
    L_0x0006:
        if (r5 == 0) goto L_0x0005;
    L_0x0008:
        r2 = r4.getClass();
        r3 = r5.getClass();
        if (r2 != r3) goto L_0x0005;
    L_0x0012:
        r5 = (com.mapbox.mapboxsdk.annotations.PolygonOptions) r5;
        r2 = r5.getAlpha();
        r3 = r4.getAlpha();
        r2 = java.lang.Float.compare(r2, r3);
        if (r2 != 0) goto L_0x0005;
    L_0x0022:
        r2 = r4.getFillColor();
        r3 = r5.getFillColor();
        if (r2 != r3) goto L_0x0005;
    L_0x002c:
        r2 = r4.getStrokeColor();
        r3 = r5.getStrokeColor();
        if (r2 != r3) goto L_0x0005;
    L_0x0036:
        r2 = r4.getPoints();
        if (r2 == 0) goto L_0x004d;
    L_0x003c:
        r2 = r4.getPoints();
        r3 = r5.getPoints();
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x004b;
    L_0x004a:
        r0 = r1;
    L_0x004b:
        r1 = r0;
        goto L_0x0005;
    L_0x004d:
        r2 = r5.getPoints();
        if (r2 != 0) goto L_0x004a;
    L_0x0053:
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mapbox.mapboxsdk.annotations.PolygonOptions.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int floatToIntBits;
        int i = 0;
        if (getAlpha() != 0.0f) {
            floatToIntBits = Float.floatToIntBits(getAlpha());
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits = (((((floatToIntBits + 31) * 31) + getFillColor()) * 31) + getStrokeColor()) * 31;
        if (getPoints() != null) {
            i = getPoints().hashCode();
        }
        return floatToIntBits + i;
    }
}
