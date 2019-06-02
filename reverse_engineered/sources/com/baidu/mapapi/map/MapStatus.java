package com.baidu.mapapi.map;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1235E;

public final class MapStatus implements Parcelable {
    public static final Creator<MapStatus> CREATOR = new C1123h();
    /* renamed from: a */
    C1235E f2981a;
    /* renamed from: b */
    private double f2982b;
    public final LatLngBounds bound;
    /* renamed from: c */
    private double f2983c;
    public final float overlook;
    public final float rotate;
    public final LatLng target;
    public final Point targetScreen;
    public WinRound winRound;
    public final float zoom;

    public static final class Builder {
        /* renamed from: a */
        private float f2973a = -2.14748365E9f;
        /* renamed from: b */
        private LatLng f2974b = null;
        /* renamed from: c */
        private float f2975c = -2.14748365E9f;
        /* renamed from: d */
        private float f2976d = -2.14748365E9f;
        /* renamed from: e */
        private Point f2977e = null;
        /* renamed from: f */
        private LatLngBounds f2978f = null;
        /* renamed from: g */
        private double f2979g = 0.0d;
        /* renamed from: h */
        private double f2980h = 0.0d;

        public Builder(MapStatus mapStatus) {
            this.f2973a = mapStatus.rotate;
            this.f2974b = mapStatus.target;
            this.f2975c = mapStatus.overlook;
            this.f2976d = mapStatus.zoom;
            this.f2977e = mapStatus.targetScreen;
            this.f2979g = mapStatus.m4134a();
            this.f2980h = mapStatus.m4135b();
        }

        public MapStatus build() {
            return new MapStatus(this.f2973a, this.f2974b, this.f2975c, this.f2976d, this.f2977e, this.f2978f);
        }

        public Builder overlook(float f) {
            this.f2975c = f;
            return this;
        }

        public Builder rotate(float f) {
            this.f2973a = f;
            return this;
        }

        public Builder target(LatLng latLng) {
            this.f2974b = latLng;
            return this;
        }

        public Builder targetScreen(Point point) {
            this.f2977e = point;
            return this;
        }

        public Builder zoom(float f) {
            this.f2976d = f;
            return this;
        }
    }

    MapStatus(float f, LatLng latLng, float f2, float f3, Point point, double d, double d2, LatLngBounds latLngBounds) {
        this.rotate = f;
        this.target = latLng;
        this.overlook = f2;
        this.zoom = f3;
        this.targetScreen = point;
        this.f2982b = d;
        this.f2983c = d2;
        this.bound = latLngBounds;
    }

    MapStatus(float f, LatLng latLng, float f2, float f3, Point point, LatLngBounds latLngBounds) {
        this.rotate = f;
        this.target = latLng;
        this.overlook = f2;
        this.zoom = f3;
        this.targetScreen = point;
        if (this.target != null) {
            this.f2982b = CoordUtil.ll2mc(this.target).getLongitudeE6();
            this.f2983c = CoordUtil.ll2mc(this.target).getLatitudeE6();
        }
        this.bound = latLngBounds;
    }

    MapStatus(float f, LatLng latLng, float f2, float f3, Point point, C1235E c1235e, double d, double d2, LatLngBounds latLngBounds, WinRound winRound) {
        this.rotate = f;
        this.target = latLng;
        this.overlook = f2;
        this.zoom = f3;
        this.targetScreen = point;
        this.f2981a = c1235e;
        this.f2982b = d;
        this.f2983c = d2;
        this.bound = latLngBounds;
        this.winRound = winRound;
    }

    protected MapStatus(Parcel parcel) {
        this.rotate = parcel.readFloat();
        this.target = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.overlook = parcel.readFloat();
        this.zoom = parcel.readFloat();
        this.targetScreen = (Point) parcel.readParcelable(Point.class.getClassLoader());
        this.bound = (LatLngBounds) parcel.readParcelable(LatLngBounds.class.getClassLoader());
        this.f2982b = parcel.readDouble();
        this.f2983c = parcel.readDouble();
    }

    /* renamed from: a */
    static MapStatus m4133a(C1235E c1235e) {
        if (c1235e == null) {
            return null;
        }
        float f = (float) c1235e.f3679b;
        double d = c1235e.f3682e;
        double d2 = c1235e.f3681d;
        LatLng mc2ll = CoordUtil.mc2ll(new GeoPoint(d, d2));
        float f2 = (float) c1235e.f3680c;
        float f3 = c1235e.f3678a;
        Point point = new Point(c1235e.f3683f, c1235e.f3684g);
        LatLng mc2ll2 = CoordUtil.mc2ll(new GeoPoint((double) c1235e.f3688k.f3672e.f3294y, (double) c1235e.f3688k.f3672e.f3293x));
        LatLng mc2ll3 = CoordUtil.mc2ll(new GeoPoint((double) c1235e.f3688k.f3673f.f3294y, (double) c1235e.f3688k.f3673f.f3293x));
        LatLng mc2ll4 = CoordUtil.mc2ll(new GeoPoint((double) c1235e.f3688k.f3675h.f3294y, (double) c1235e.f3688k.f3675h.f3293x));
        LatLng mc2ll5 = CoordUtil.mc2ll(new GeoPoint((double) c1235e.f3688k.f3674g.f3294y, (double) c1235e.f3688k.f3674g.f3293x));
        com.baidu.mapapi.model.LatLngBounds.Builder builder = new com.baidu.mapapi.model.LatLngBounds.Builder();
        builder.include(mc2ll2);
        builder.include(mc2ll3);
        builder.include(mc2ll4);
        builder.include(mc2ll5);
        return new MapStatus(f, mc2ll, f2, f3, point, c1235e, d2, d, builder.build(), c1235e.f3687j);
    }

    /* renamed from: a */
    double m4134a() {
        return this.f2982b;
    }

    /* renamed from: b */
    double m4135b() {
        return this.f2983c;
    }

    /* renamed from: b */
    C1235E m4136b(C1235E c1235e) {
        if (this.rotate != -2.14748365E9f) {
            c1235e.f3679b = (int) this.rotate;
        }
        if (this.zoom != -2.14748365E9f) {
            c1235e.f3678a = this.zoom;
        }
        if (this.overlook != -2.14748365E9f) {
            c1235e.f3680c = (int) this.overlook;
        }
        if (this.target != null) {
            CoordUtil.ll2mc(this.target);
            c1235e.f3681d = this.f2982b;
            c1235e.f3682e = this.f2983c;
        }
        if (this.targetScreen != null) {
            c1235e.f3683f = this.targetScreen.x;
            c1235e.f3684g = this.targetScreen.y;
        }
        return c1235e;
    }

    /* renamed from: c */
    C1235E m4137c() {
        return m4136b(new C1235E());
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.target != null) {
            stringBuilder.append("target lat: " + this.target.latitude + "\n");
            stringBuilder.append("target lng: " + this.target.longitude + "\n");
        }
        if (this.targetScreen != null) {
            stringBuilder.append("target screen x: " + this.targetScreen.x + "\n");
            stringBuilder.append("target screen y: " + this.targetScreen.y + "\n");
        }
        stringBuilder.append("zoom: " + this.zoom + "\n");
        stringBuilder.append("rotate: " + this.rotate + "\n");
        stringBuilder.append("overlook: " + this.overlook + "\n");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.rotate);
        parcel.writeParcelable(this.target, i);
        parcel.writeFloat(this.overlook);
        parcel.writeFloat(this.zoom);
        parcel.writeParcelable(this.targetScreen, i);
        parcel.writeParcelable(this.bound, i);
        parcel.writeDouble(this.f2982b);
        parcel.writeDouble(this.f2983c);
    }
}
