package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1252h;
import java.util.List;

public abstract class Overlay {
    protected C1109a listener;
    /* renamed from: p */
    String f2804p = (System.currentTimeMillis() + "_" + hashCode());
    /* renamed from: q */
    int f2805q;
    /* renamed from: r */
    boolean f2806r;
    /* renamed from: s */
    Bundle f2807s;
    public C1252h type;

    /* renamed from: com.baidu.mapapi.map.Overlay$a */
    interface C1109a {
        /* renamed from: a */
        void mo2623a(Overlay overlay);

        /* renamed from: b */
        void mo2624b(Overlay overlay);
    }

    protected Overlay() {
    }

    /* renamed from: a */
    static void m4045a(int i, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        int i2 = i >>> 24;
        int i3 = (i >> 8) & 255;
        int i4 = i & 255;
        bundle2.putFloat("red", ((float) ((i >> 16) & 255)) / 255.0f);
        bundle2.putFloat("green", ((float) i3) / 255.0f);
        bundle2.putFloat("blue", ((float) i4) / 255.0f);
        bundle2.putFloat("alpha", ((float) i2) / 255.0f);
        bundle.putBundle("color", bundle2);
    }

    /* renamed from: a */
    static void m4046a(List<LatLng> list, Bundle bundle) {
        int size = list.size();
        double[] dArr = new double[size];
        double[] dArr2 = new double[size];
        for (int i = 0; i < size; i++) {
            GeoPoint ll2mc = CoordUtil.ll2mc((LatLng) list.get(i));
            dArr[i] = ll2mc.getLongitudeE6();
            dArr2[i] = ll2mc.getLatitudeE6();
        }
        bundle.putDoubleArray("x_array", dArr);
        bundle.putDoubleArray("y_array", dArr2);
    }

    /* renamed from: a */
    Bundle mo2621a() {
        Bundle bundle = new Bundle();
        bundle.putString("id", this.f2804p);
        bundle.putInt("type", this.type.ordinal());
        return bundle;
    }

    /* renamed from: a */
    Bundle mo2613a(Bundle bundle) {
        bundle.putString("id", this.f2804p);
        bundle.putInt("type", this.type.ordinal());
        bundle.putInt("visibility", this.f2806r ? 1 : 0);
        bundle.putInt("z_index", this.f2805q);
        return bundle;
    }

    public Bundle getExtraInfo() {
        return this.f2807s;
    }

    public int getZIndex() {
        return this.f2805q;
    }

    public boolean isVisible() {
        return this.f2806r;
    }

    public void remove() {
        this.listener.mo2623a(this);
    }

    public void setExtraInfo(Bundle bundle) {
        this.f2807s = bundle;
    }

    public void setVisible(boolean z) {
        this.f2806r = z;
        this.listener.mo2624b(this);
    }

    public void setZIndex(int i) {
        this.f2805q = i;
        this.listener.mo2624b(this);
    }
}
