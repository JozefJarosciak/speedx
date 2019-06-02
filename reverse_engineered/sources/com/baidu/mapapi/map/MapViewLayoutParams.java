package com.baidu.mapapi.map;

import android.graphics.Point;
import android.view.ViewGroup.LayoutParams;
import com.baidu.mapapi.model.LatLng;

public final class MapViewLayoutParams extends LayoutParams {
    public static final int ALIGN_BOTTOM = 16;
    public static final int ALIGN_CENTER_HORIZONTAL = 4;
    public static final int ALIGN_CENTER_VERTICAL = 32;
    public static final int ALIGN_LEFT = 1;
    public static final int ALIGN_RIGHT = 2;
    public static final int ALIGN_TOP = 8;
    /* renamed from: a */
    LatLng f3029a;
    /* renamed from: b */
    Point f3030b;
    /* renamed from: c */
    ELayoutMode f3031c;
    /* renamed from: d */
    float f3032d;
    /* renamed from: e */
    float f3033e;
    /* renamed from: f */
    int f3034f;

    public static final class Builder {
        /* renamed from: a */
        private int f3021a;
        /* renamed from: b */
        private int f3022b;
        /* renamed from: c */
        private LatLng f3023c;
        /* renamed from: d */
        private Point f3024d;
        /* renamed from: e */
        private ELayoutMode f3025e = ELayoutMode.absoluteMode;
        /* renamed from: f */
        private int f3026f = 4;
        /* renamed from: g */
        private int f3027g = 16;
        /* renamed from: h */
        private int f3028h;

        public Builder align(int i, int i2) {
            if (i == 1 || i == 2 || i == 4) {
                this.f3026f = i;
            }
            if (i2 == 8 || i2 == 16 || i2 == 32) {
                this.f3027g = i2;
            }
            return this;
        }

        public MapViewLayoutParams build() {
            Object obj = 1;
            if (this.f3025e != ELayoutMode.mapMode ? !(this.f3025e == ELayoutMode.absoluteMode && this.f3024d == null) : this.f3023c != null) {
                obj = null;
            }
            if (obj == null) {
                return new MapViewLayoutParams(this.f3021a, this.f3022b, this.f3023c, this.f3024d, this.f3025e, this.f3026f, this.f3027g, this.f3028h);
            }
            throw new IllegalStateException("if it is map mode, you must supply position info; else if it is absolute mode, you must supply the point info");
        }

        public Builder height(int i) {
            this.f3022b = i;
            return this;
        }

        public Builder layoutMode(ELayoutMode eLayoutMode) {
            this.f3025e = eLayoutMode;
            return this;
        }

        public Builder point(Point point) {
            this.f3024d = point;
            return this;
        }

        public Builder position(LatLng latLng) {
            this.f3023c = latLng;
            return this;
        }

        public Builder width(int i) {
            this.f3021a = i;
            return this;
        }

        public Builder yOffset(int i) {
            this.f3028h = i;
            return this;
        }
    }

    public enum ELayoutMode {
        mapMode,
        absoluteMode
    }

    MapViewLayoutParams(int i, int i2, LatLng latLng, Point point, ELayoutMode eLayoutMode, int i3, int i4, int i5) {
        super(i, i2);
        this.f3029a = latLng;
        this.f3030b = point;
        this.f3031c = eLayoutMode;
        switch (i3) {
            case 1:
                this.f3032d = 0.0f;
                break;
            case 2:
                this.f3032d = 1.0f;
                break;
            case 4:
                this.f3032d = 0.5f;
                break;
            default:
                this.f3032d = 0.5f;
                break;
        }
        switch (i4) {
            case 8:
                this.f3033e = 0.0f;
                break;
            case 16:
                this.f3033e = 1.0f;
                break;
            case 32:
                this.f3033e = 0.5f;
                break;
            default:
                this.f3033e = 1.0f;
                break;
        }
        this.f3034f = i5;
    }
}
