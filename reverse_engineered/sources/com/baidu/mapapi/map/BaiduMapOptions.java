package com.baidu.mapapi.map;

import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.map.C1232C;

public final class BaiduMapOptions implements Parcelable {
    public static final Creator<BaiduMapOptions> CREATOR = new C1120e();
    /* renamed from: a */
    MapStatus f2868a = new MapStatus(0.0f, new LatLng(39.914935d, 116.403119d), 0.0f, 12.0f, null, null);
    /* renamed from: b */
    boolean f2869b = true;
    /* renamed from: c */
    int f2870c = 1;
    /* renamed from: d */
    boolean f2871d = true;
    /* renamed from: e */
    boolean f2872e = true;
    /* renamed from: f */
    boolean f2873f = true;
    /* renamed from: g */
    boolean f2874g = true;
    /* renamed from: h */
    boolean f2875h = true;
    /* renamed from: i */
    boolean f2876i = true;
    /* renamed from: j */
    LogoPosition f2877j;
    /* renamed from: k */
    Point f2878k;
    /* renamed from: l */
    Point f2879l;

    protected BaiduMapOptions(Parcel parcel) {
        boolean z = true;
        this.f2868a = (MapStatus) parcel.readParcelable(MapStatus.class.getClassLoader());
        this.f2869b = parcel.readByte() != (byte) 0;
        this.f2870c = parcel.readInt();
        this.f2871d = parcel.readByte() != (byte) 0;
        this.f2872e = parcel.readByte() != (byte) 0;
        this.f2873f = parcel.readByte() != (byte) 0;
        this.f2874g = parcel.readByte() != (byte) 0;
        this.f2875h = parcel.readByte() != (byte) 0;
        if (parcel.readByte() == (byte) 0) {
            z = false;
        }
        this.f2876i = z;
        this.f2878k = (Point) parcel.readParcelable(Point.class.getClassLoader());
        this.f2879l = (Point) parcel.readParcelable(Point.class.getClassLoader());
    }

    /* renamed from: a */
    C1232C m4090a() {
        return new C1232C().m4610a(this.f2868a.m4137c()).m4611a(this.f2869b).m4609a(this.f2870c).m4612b(this.f2871d).m4613c(this.f2872e).m4614d(this.f2873f).m4615e(this.f2874g);
    }

    public BaiduMapOptions compassEnabled(boolean z) {
        this.f2869b = z;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public BaiduMapOptions logoPosition(LogoPosition logoPosition) {
        this.f2877j = logoPosition;
        return this;
    }

    public BaiduMapOptions mapStatus(MapStatus mapStatus) {
        if (mapStatus != null) {
            this.f2868a = mapStatus;
        }
        return this;
    }

    public BaiduMapOptions mapType(int i) {
        this.f2870c = i;
        return this;
    }

    public BaiduMapOptions overlookingGesturesEnabled(boolean z) {
        this.f2873f = z;
        return this;
    }

    public BaiduMapOptions rotateGesturesEnabled(boolean z) {
        this.f2871d = z;
        return this;
    }

    public BaiduMapOptions scaleControlEnabled(boolean z) {
        this.f2876i = z;
        return this;
    }

    public BaiduMapOptions scaleControlPosition(Point point) {
        this.f2878k = point;
        return this;
    }

    public BaiduMapOptions scrollGesturesEnabled(boolean z) {
        this.f2872e = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeParcelable(this.f2868a, i);
        parcel.writeByte((byte) (this.f2869b ? 1 : 0));
        parcel.writeInt(this.f2870c);
        parcel.writeByte((byte) (this.f2871d ? 1 : 0));
        parcel.writeByte((byte) (this.f2872e ? 1 : 0));
        parcel.writeByte((byte) (this.f2873f ? 1 : 0));
        parcel.writeByte((byte) (this.f2874g ? 1 : 0));
        parcel.writeByte((byte) (this.f2875h ? 1 : 0));
        if (!this.f2876i) {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeParcelable(this.f2878k, i);
        parcel.writeParcelable(this.f2879l, i);
    }

    public BaiduMapOptions zoomControlsEnabled(boolean z) {
        this.f2875h = z;
        return this;
    }

    public BaiduMapOptions zoomControlsPosition(Point point) {
        this.f2879l = point;
        return this;
    }

    public BaiduMapOptions zoomGesturesEnabled(boolean z) {
        this.f2874g = z;
        return this;
    }
}
