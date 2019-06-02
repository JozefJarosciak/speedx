package com.mapbox.mapboxsdk.annotations;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mapbox.mapboxsdk.geometry.LatLng;
import java.util.ArrayList;
import java.util.List;

public final class PolylineOptions implements Parcelable {
    public static final Creator<PolylineOptions> CREATOR = new C41881();
    private Polyline polyline;

    /* renamed from: com.mapbox.mapboxsdk.annotations.PolylineOptions$1 */
    static class C41881 implements Creator<PolylineOptions> {
        C41881() {
        }

        public PolylineOptions createFromParcel(Parcel parcel) {
            return new PolylineOptions(parcel);
        }

        public PolylineOptions[] newArray(int i) {
            return new PolylineOptions[i];
        }
    }

    private PolylineOptions(Parcel parcel) {
        this.polyline = new Polyline();
        Iterable arrayList = new ArrayList();
        parcel.readList(arrayList, LatLng.class.getClassLoader());
        addAll(arrayList);
        alpha(parcel.readFloat());
        color(parcel.readInt());
        width(parcel.readFloat());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(getPoints());
        parcel.writeFloat(getAlpha());
        parcel.writeInt(getColor());
        parcel.writeFloat(getWidth());
    }

    public PolylineOptions() {
        this.polyline = new Polyline();
    }

    public PolylineOptions add(LatLng latLng) {
        this.polyline.addPoint(latLng);
        return this;
    }

    public PolylineOptions add(LatLng... latLngArr) {
        for (LatLng add : latLngArr) {
            add(add);
        }
        return this;
    }

    public PolylineOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng add : iterable) {
            add(add);
        }
        return this;
    }

    public PolylineOptions alpha(float f) {
        this.polyline.setAlpha(f);
        return this;
    }

    public float getAlpha() {
        return this.polyline.getAlpha();
    }

    public PolylineOptions color(int i) {
        this.polyline.setColor(i);
        return this;
    }

    public int getColor() {
        return this.polyline.getColor();
    }

    public Polyline getPolyline() {
        return this.polyline;
    }

    public float getWidth() {
        return this.polyline.getWidth();
    }

    public PolylineOptions width(float f) {
        this.polyline.setWidth(f);
        return this;
    }

    public List<LatLng> getPoints() {
        return this.polyline.getPoints();
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
        r5 = (com.mapbox.mapboxsdk.annotations.PolylineOptions) r5;
        r2 = r5.getAlpha();
        r3 = r4.getAlpha();
        r2 = java.lang.Float.compare(r2, r3);
        if (r2 != 0) goto L_0x0005;
    L_0x0022:
        r2 = r4.getColor();
        r3 = r5.getColor();
        if (r2 != r3) goto L_0x0005;
    L_0x002c:
        r2 = r5.getWidth();
        r3 = r4.getWidth();
        r2 = java.lang.Float.compare(r2, r3);
        if (r2 != 0) goto L_0x0005;
    L_0x003a:
        r2 = r4.getPoints();
        if (r2 == 0) goto L_0x0051;
    L_0x0040:
        r2 = r4.getPoints();
        r3 = r5.getPoints();
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x004f;
    L_0x004e:
        r0 = r1;
    L_0x004f:
        r1 = r0;
        goto L_0x0005;
    L_0x0051:
        r2 = r5.getPoints();
        if (r2 != 0) goto L_0x004e;
    L_0x0057:
        goto L_0x004f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mapbox.mapboxsdk.annotations.PolylineOptions.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        int floatToIntBits;
        int i = 0;
        if (getAlpha() != 0.0f) {
            floatToIntBits = Float.floatToIntBits(getAlpha());
        } else {
            floatToIntBits = 0;
        }
        int color = (((floatToIntBits + 31) * 31) + getColor()) * 31;
        if (getWidth() != 0.0f) {
            floatToIntBits = Float.floatToIntBits(getWidth());
        } else {
            floatToIntBits = 0;
        }
        floatToIntBits = (floatToIntBits + color) * 31;
        if (getPoints() != null) {
            i = getPoints().hashCode();
        }
        return floatToIntBits + i;
    }
}
