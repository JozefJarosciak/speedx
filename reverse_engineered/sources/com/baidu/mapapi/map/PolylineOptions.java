package com.baidu.mapapi.map;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.baidu.mapapi.model.LatLng;
import java.util.ArrayList;
import java.util.List;

public final class PolylineOptions extends OverlayOptions {
    /* renamed from: a */
    int f3094a;
    /* renamed from: b */
    boolean f3095b = true;
    /* renamed from: c */
    boolean f3096c = false;
    /* renamed from: d */
    Bundle f3097d;
    /* renamed from: e */
    private int f3098e = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: f */
    private List<LatLng> f3099f;
    /* renamed from: g */
    private List<Integer> f3100g;
    /* renamed from: h */
    private List<Integer> f3101h;
    /* renamed from: i */
    private int f3102i = 5;
    /* renamed from: j */
    private BitmapDescriptor f3103j;
    /* renamed from: k */
    private List<BitmapDescriptor> f3104k;
    /* renamed from: l */
    private boolean f3105l = true;
    /* renamed from: m */
    private boolean f3106m = false;

    /* renamed from: a */
    Overlay mo2614a() {
        int i = 0;
        Overlay polyline = new Polyline();
        polyline.r = this.f3095b;
        polyline.f3089f = this.f3096c;
        polyline.q = this.f3094a;
        polyline.s = this.f3097d;
        if (this.f3099f == null || this.f3099f.size() < 2) {
            throw new IllegalStateException("when you add polyline, you must at least supply 2 points");
        }
        polyline.f3085b = this.f3099f;
        polyline.f3084a = this.f3098e;
        polyline.f3088e = this.f3102i;
        polyline.f3092i = this.f3103j;
        polyline.f3093j = this.f3104k;
        polyline.f3090g = this.f3105l;
        polyline.f3091h = this.f3106m;
        if (this.f3100g != null && this.f3100g.size() < this.f3099f.size() - 1) {
            this.f3100g.addAll(this.f3100g.size(), new ArrayList((this.f3099f.size() - 1) - this.f3100g.size()));
        }
        if (this.f3100g != null && this.f3100g.size() > 0) {
            int[] iArr = new int[this.f3100g.size()];
            int i2 = 0;
            for (Integer intValue : this.f3100g) {
                iArr[i2] = intValue.intValue();
                i2++;
            }
            polyline.f3086c = iArr;
        }
        if (this.f3101h != null && this.f3101h.size() < this.f3099f.size() - 1) {
            this.f3101h.addAll(this.f3101h.size(), new ArrayList((this.f3099f.size() - 1) - this.f3101h.size()));
        }
        if (this.f3101h != null && this.f3101h.size() > 0) {
            int[] iArr2 = new int[this.f3101h.size()];
            for (Integer intValue2 : this.f3101h) {
                iArr2[i] = intValue2.intValue();
                i++;
            }
            polyline.f3087d = iArr2;
        }
        return polyline;
    }

    public PolylineOptions color(int i) {
        this.f3098e = i;
        return this;
    }

    public PolylineOptions colorsValues(List<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("colors list can not be null");
        } else if (list.contains(null)) {
            throw new IllegalArgumentException("colors list can not contains null");
        } else {
            this.f3101h = list;
            return this;
        }
    }

    public PolylineOptions customTexture(BitmapDescriptor bitmapDescriptor) {
        this.f3103j = bitmapDescriptor;
        return this;
    }

    public PolylineOptions customTextureList(List<BitmapDescriptor> list) {
        if (list == null) {
            throw new IllegalArgumentException("customTexture list can not be null");
        }
        if (list.size() == 0) {
            Log.e("baidumapsdk", "custom texture list is empty,the texture will not work");
        }
        for (BitmapDescriptor bitmapDescriptor : list) {
            if (bitmapDescriptor == null) {
                Log.e("baidumapsdk", "the custom texture item is null,it will be discard");
            }
        }
        this.f3104k = list;
        return this;
    }

    public PolylineOptions dottedLine(boolean z) {
        this.f3096c = z;
        return this;
    }

    public PolylineOptions extraInfo(Bundle bundle) {
        this.f3097d = bundle;
        return this;
    }

    public PolylineOptions focus(boolean z) {
        this.f3105l = z;
        return this;
    }

    public int getColor() {
        return this.f3098e;
    }

    public BitmapDescriptor getCustomTexture() {
        return this.f3103j;
    }

    public List<BitmapDescriptor> getCustomTextureList() {
        return this.f3104k;
    }

    public Bundle getExtraInfo() {
        return this.f3097d;
    }

    public List<LatLng> getPoints() {
        return this.f3099f;
    }

    public List<Integer> getTextureIndexs() {
        return this.f3100g;
    }

    public int getWidth() {
        return this.f3102i;
    }

    public int getZIndex() {
        return this.f3094a;
    }

    public boolean isDottedLine() {
        return this.f3096c;
    }

    public boolean isFocus() {
        return this.f3105l;
    }

    public boolean isVisible() {
        return this.f3095b;
    }

    public PolylineOptions keepScale(boolean z) {
        this.f3106m = z;
        return this;
    }

    public PolylineOptions points(List<LatLng> list) {
        if (list == null) {
            throw new IllegalArgumentException("points list can not be null");
        } else if (list.size() < 2) {
            throw new IllegalArgumentException("points count can not less than 2");
        } else if (list.contains(null)) {
            throw new IllegalArgumentException("points list can not contains null");
        } else {
            this.f3099f = list;
            return this;
        }
    }

    public PolylineOptions textureIndex(List<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("indexs list can not be null");
        } else if (list.contains(null)) {
            throw new IllegalArgumentException("index list can not contains null");
        } else {
            this.f3100g = list;
            return this;
        }
    }

    public PolylineOptions visible(boolean z) {
        this.f3095b = z;
        return this;
    }

    public PolylineOptions width(int i) {
        if (i > 0) {
            this.f3102i = i;
        }
        return this;
    }

    public PolylineOptions zIndex(int i) {
        this.f3094a = i;
        return this;
    }
}
