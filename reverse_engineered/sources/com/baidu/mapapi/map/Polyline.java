package com.baidu.mapapi.map;

import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1252h;
import com.mapbox.mapboxsdk.style.layers.Property;
import io.rong.imlib.statistics.UserData;
import java.util.List;

public final class Polyline extends Overlay {
    /* renamed from: a */
    int f3084a;
    /* renamed from: b */
    List<LatLng> f3085b;
    /* renamed from: c */
    int[] f3086c;
    /* renamed from: d */
    int[] f3087d;
    /* renamed from: e */
    int f3088e;
    /* renamed from: f */
    boolean f3089f;
    /* renamed from: g */
    boolean f3090g;
    /* renamed from: h */
    boolean f3091h;
    /* renamed from: i */
    BitmapDescriptor f3092i;
    /* renamed from: j */
    List<BitmapDescriptor> f3093j;

    Polyline() {
        this.f3090g = false;
        this.f3091h = true;
        this.type = C1252h.polyline;
    }

    /* renamed from: a */
    private Bundle m4163a(boolean z) {
        return z ? BitmapDescriptorFactory.fromAsset("lineDashTexture.png").m4093b() : this.f3092i.m4093b();
    }

    /* renamed from: a */
    static void m4164a(int[] iArr, Bundle bundle) {
        if (iArr != null && iArr.length > 0) {
            bundle.putIntArray("traffic_array", iArr);
        }
    }

    /* renamed from: b */
    private Bundle m4165b(boolean z) {
        if (z) {
            Bundle bundle = new Bundle();
            bundle.putInt("total", 1);
            bundle.putBundle("texture_0", BitmapDescriptorFactory.fromAsset("lineDashTexture.png").m4093b());
            return bundle;
        }
        Bundle bundle2 = new Bundle();
        int i = 0;
        for (int i2 = 0; i2 < this.f3093j.size(); i2++) {
            if (this.f3093j.get(i2) != null) {
                bundle2.putBundle("texture_" + String.valueOf(i), ((BitmapDescriptor) this.f3093j.get(i2)).m4093b());
                i++;
            }
        }
        bundle2.putInt("total", i);
        return bundle2;
    }

    /* renamed from: b */
    static void m4166b(int[] iArr, Bundle bundle) {
        if (iArr != null && iArr.length > 0) {
            bundle.putIntArray("color_array", iArr);
            bundle.putInt("total", 1);
        }
    }

    /* renamed from: a */
    Bundle mo2613a(Bundle bundle) {
        int i = 1;
        super.mo2613a(bundle);
        GeoPoint ll2mc = CoordUtil.ll2mc((LatLng) this.f3085b.get(0));
        bundle.putDouble("location_x", ll2mc.getLongitudeE6());
        bundle.putDouble("location_y", ll2mc.getLatitudeE6());
        bundle.putInt(Property.ICON_TEXT_FIT_WIDTH, this.f3088e);
        Overlay.m4046a(this.f3085b, bundle);
        Overlay.m4045a(this.f3084a, bundle);
        m4164a(this.f3086c, bundle);
        m4166b(this.f3087d, bundle);
        if (this.f3086c != null && this.f3086c.length > 0 && this.f3086c.length > this.f3085b.size() - 1) {
            Log.e("baidumapsdk", "the size of textureIndexs is larger than the size of points");
        }
        if (this.f3089f) {
            bundle.putInt("dotline", 1);
        } else {
            bundle.putInt("dotline", 0);
        }
        bundle.putInt("focus", this.f3090g ? 1 : 0);
        try {
            if (this.f3092i != null) {
                bundle.putInt(UserData.CUSTOM_KEY, 1);
                bundle.putBundle("image_info", m4163a(false));
            } else {
                if (this.f3089f) {
                    bundle.putBundle("image_info", m4163a(true));
                }
                bundle.putInt(UserData.CUSTOM_KEY, 0);
            }
            if (this.f3093j != null) {
                bundle.putInt("customlist", 1);
                bundle.putBundle("image_info_list", m4165b(false));
            } else {
                if (this.f3089f && ((this.f3086c != null && this.f3086c.length > 0) || (this.f3087d != null && this.f3087d.length > 0))) {
                    bundle.putBundle("image_info_list", m4165b(true));
                }
                bundle.putInt("customlist", 0);
            }
            String str = "keep";
            if (!this.f3091h) {
                i = 0;
            }
            bundle.putInt(str, i);
        } catch (Exception e) {
            Log.e("baidumapsdk", "load texture resource failed!");
            bundle.putInt("dotline", 0);
        }
        return bundle;
    }

    public int getColor() {
        return this.f3084a;
    }

    public List<LatLng> getPoints() {
        return this.f3085b;
    }

    public int getWidth() {
        return this.f3088e;
    }

    public boolean isDottedLine() {
        return this.f3089f;
    }

    public boolean isFocus() {
        return this.f3090g;
    }

    public void setColor(int i) {
        this.f3084a = i;
        this.listener.mo2624b(this);
    }

    public void setDottedLine(boolean z) {
        this.f3089f = z;
        this.listener.mo2624b(this);
    }

    public void setFocus(boolean z) {
        this.f3090g = z;
        this.listener.mo2624b(this);
    }

    public void setPoints(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("points list can not be null");
        } else if (list.size() < 2) {
            throw new IllegalArgumentException("points count can not less than 2 or more than 10000");
        } else if (list.contains(null)) {
            throw new IllegalArgumentException("points list can not contains null");
        } else {
            this.f3085b = list;
            this.listener.mo2624b(this);
        }
    }

    public void setWidth(int i) {
        if (i > 0) {
            this.f3088e = i;
            this.listener.mo2624b(this);
        }
    }
}
