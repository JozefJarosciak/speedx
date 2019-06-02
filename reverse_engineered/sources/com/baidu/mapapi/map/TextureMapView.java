package com.baidu.mapapi.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.NinePatch;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alibaba.fastjson.asm.Opcodes;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.MapViewLayoutParams.ELayoutMode;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.platform.comapi.commonutils.C1221a;
import com.baidu.platform.comapi.map.C1114l;
import com.baidu.platform.comapi.map.C1237F;
import com.baidu.platform.comapi.map.C1244N;
import com.baidu.platform.comapi.map.C1253i;
import com.google.common.primitives.Ints;
import java.io.File;

public final class TextureMapView extends ViewGroup {
    /* renamed from: a */
    private static final String f3154a = TextureMapView.class.getSimpleName();
    /* renamed from: i */
    private static String f3155i;
    /* renamed from: n */
    private static final SparseArray<Integer> f3156n = new SparseArray();
    /* renamed from: b */
    private C1237F f3157b;
    /* renamed from: c */
    private BaiduMap f3158c;
    /* renamed from: d */
    private ImageView f3159d;
    /* renamed from: e */
    private Bitmap f3160e;
    /* renamed from: f */
    private C1244N f3161f;
    /* renamed from: g */
    private Point f3162g;
    /* renamed from: h */
    private Point f3163h;
    /* renamed from: j */
    private RelativeLayout f3164j;
    /* renamed from: k */
    private TextView f3165k;
    /* renamed from: l */
    private TextView f3166l;
    /* renamed from: m */
    private ImageView f3167m;
    /* renamed from: o */
    private float f3168o;
    /* renamed from: p */
    private C1114l f3169p;
    /* renamed from: q */
    private int f3170q = LogoPosition.logoPostionleftBottom.ordinal();
    /* renamed from: r */
    private boolean f3171r = true;
    /* renamed from: s */
    private boolean f3172s = true;
    /* renamed from: t */
    private int f3173t;
    /* renamed from: u */
    private int f3174u;
    /* renamed from: v */
    private int f3175v;
    /* renamed from: w */
    private int f3176w;
    /* renamed from: x */
    private int f3177x;
    /* renamed from: y */
    private int f3178y;

    static {
        f3156n.append(3, Integer.valueOf(2000000));
        f3156n.append(4, Integer.valueOf(1000000));
        f3156n.append(5, Integer.valueOf(500000));
        f3156n.append(6, Integer.valueOf(200000));
        f3156n.append(7, Integer.valueOf(100000));
        f3156n.append(8, Integer.valueOf(50000));
        f3156n.append(9, Integer.valueOf(25000));
        f3156n.append(10, Integer.valueOf(20000));
        f3156n.append(11, Integer.valueOf(10000));
        f3156n.append(12, Integer.valueOf(5000));
        f3156n.append(13, Integer.valueOf(m_AppUI.MSG_APP_DATA_OK));
        f3156n.append(14, Integer.valueOf(1000));
        f3156n.append(15, Integer.valueOf(500));
        f3156n.append(16, Integer.valueOf(200));
        f3156n.append(17, Integer.valueOf(100));
        f3156n.append(18, Integer.valueOf(50));
        f3156n.append(19, Integer.valueOf(20));
        f3156n.append(20, Integer.valueOf(10));
        f3156n.append(21, Integer.valueOf(5));
        f3156n.append(22, Integer.valueOf(2));
    }

    public TextureMapView(Context context) {
        super(context);
        m4183a(context, null);
    }

    public TextureMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m4183a(context, null);
    }

    public TextureMapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m4183a(context, null);
    }

    public TextureMapView(Context context, BaiduMapOptions baiduMapOptions) {
        super(context);
        m4183a(context, baiduMapOptions);
    }

    /* renamed from: a */
    private void m4182a(Context context) {
        String str = "logo_h.png";
        int densityDpi = SysOSUtil.getDensityDpi();
        if (densityDpi < Opcodes.GETFIELD) {
            str = "logo_l.png";
        }
        Bitmap a = C1221a.m4570a(str, context);
        Matrix matrix;
        if (densityDpi > 480) {
            matrix = new Matrix();
            matrix.postScale(2.0f, 2.0f);
            this.f3160e = Bitmap.createBitmap(a, 0, 0, a.getWidth(), a.getHeight(), matrix, true);
        } else if (densityDpi <= 320 || densityDpi > 480) {
            this.f3160e = a;
        } else {
            matrix = new Matrix();
            matrix.postScale(1.5f, 1.5f);
            this.f3160e = Bitmap.createBitmap(a, 0, 0, a.getWidth(), a.getHeight(), matrix, true);
        }
        if (this.f3160e != null) {
            this.f3159d = new ImageView(context);
            this.f3159d.setImageBitmap(this.f3160e);
            addView(this.f3159d);
        }
    }

    /* renamed from: a */
    private void m4183a(Context context, BaiduMapOptions baiduMapOptions) {
        setBackgroundColor(-1);
        C1253i.m4750a();
        BMapManager.init();
        m4184a(context, baiduMapOptions, f3155i);
        this.f3158c = new BaiduMap(this.f3157b);
        m4182a(context);
        m4188b(context);
        if (!(baiduMapOptions == null || baiduMapOptions.f2875h)) {
            this.f3161f.setVisibility(4);
        }
        m4190c(context);
        if (!(baiduMapOptions == null || baiduMapOptions.f2876i)) {
            this.f3164j.setVisibility(4);
        }
        if (!(baiduMapOptions == null || baiduMapOptions.f2877j == null)) {
            this.f3170q = baiduMapOptions.f2877j.ordinal();
        }
        if (!(baiduMapOptions == null || baiduMapOptions.f2879l == null)) {
            this.f3163h = baiduMapOptions.f2879l;
        }
        if (baiduMapOptions != null && baiduMapOptions.f2878k != null) {
            this.f3162g = baiduMapOptions.f2878k;
        }
    }

    /* renamed from: a */
    private void m4184a(Context context, BaiduMapOptions baiduMapOptions, String str) {
        f3155i = str;
        if (baiduMapOptions == null) {
            this.f3157b = new C1237F(context, null, str);
        } else {
            this.f3157b = new C1237F(context, baiduMapOptions.m4090a(), str);
        }
        addView(this.f3157b);
        this.f3169p = new C1134q(this);
        this.f3157b.m4625b().m4685a(this.f3169p);
    }

    /* renamed from: a */
    private void m4185a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-2, -2);
        }
        int i = layoutParams.width;
        i = i > 0 ? MeasureSpec.makeMeasureSpec(i, Ints.MAX_POWER_OF_TWO) : MeasureSpec.makeMeasureSpec(0, 0);
        int i2 = layoutParams.height;
        view.measure(i, i2 > 0 ? MeasureSpec.makeMeasureSpec(i2, Ints.MAX_POWER_OF_TWO) : MeasureSpec.makeMeasureSpec(0, 0));
    }

    /* renamed from: b */
    private void m4187b() {
        boolean z = false;
        float f = this.f3157b.m4625b().m4660D().f3678a;
        if (this.f3161f.m4642a()) {
            this.f3161f.m4645b(f > this.f3157b.m4625b().f3755b);
            C1244N c1244n = this.f3161f;
            if (f < this.f3157b.m4625b().f3754a) {
                z = true;
            }
            c1244n.m4641a(z);
        }
    }

    /* renamed from: b */
    private void m4188b(Context context) {
        this.f3161f = new C1244N(context);
        if (this.f3161f.m4642a()) {
            this.f3161f.m4644b(new C1135r(this));
            this.f3161f.m4640a(new C1136s(this));
            addView(this.f3161f);
        }
    }

    /* renamed from: c */
    private void m4190c(Context context) {
        this.f3164j = new RelativeLayout(context);
        this.f3164j.setLayoutParams(new LayoutParams(-2, -2));
        this.f3165k = new TextView(context);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        this.f3165k.setTextColor(Color.parseColor("#FFFFFF"));
        this.f3165k.setTextSize(2, 11.0f);
        this.f3165k.setTypeface(this.f3165k.getTypeface(), 1);
        this.f3165k.setLayoutParams(layoutParams);
        this.f3165k.setId(Integer.MAX_VALUE);
        this.f3164j.addView(this.f3165k);
        this.f3166l = new TextView(context);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.addRule(14);
        this.f3166l.setTextColor(Color.parseColor("#000000"));
        this.f3166l.setTextSize(2, 11.0f);
        this.f3166l.setLayoutParams(layoutParams);
        this.f3164j.addView(this.f3166l);
        this.f3167m = new ImageView(context);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.addRule(14);
        layoutParams.addRule(3, this.f3165k.getId());
        this.f3167m.setLayoutParams(layoutParams);
        Bitmap a = C1221a.m4570a("icon_scale.9.png", context);
        byte[] ninePatchChunk = a.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        this.f3167m.setBackgroundDrawable(new NinePatchDrawable(a, ninePatchChunk, new Rect(), null));
        this.f3164j.addView(this.f3167m);
        addView(this.f3164j);
    }

    public static void setCustomMapStylePath(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("customMapStylePath String is illegal");
        } else if (new File(str).exists()) {
            f3155i = str;
        } else {
            throw new RuntimeException("please check whether the customMapStylePath file exits");
        }
    }

    public static void setMapCustomEnable(boolean z) {
        C1253i.m4752a(z);
    }

    public void addView(View view, LayoutParams layoutParams) {
        if (layoutParams instanceof MapViewLayoutParams) {
            super.addView(view, layoutParams);
        }
    }

    public final LogoPosition getLogoPosition() {
        switch (this.f3170q) {
            case 1:
                return LogoPosition.logoPostionleftTop;
            case 2:
                return LogoPosition.logoPostionCenterBottom;
            case 3:
                return LogoPosition.logoPostionCenterTop;
            case 4:
                return LogoPosition.logoPostionRightBottom;
            case 5:
                return LogoPosition.logoPostionRightTop;
            default:
                return LogoPosition.logoPostionleftBottom;
        }
    }

    public final BaiduMap getMap() {
        this.f3158c.f2844b = this;
        return this.f3158c;
    }

    public final int getMapLevel() {
        return ((Integer) f3156n.get((int) this.f3157b.m4625b().m4660D().f3678a)).intValue();
    }

    public int getScaleControlViewHeight() {
        return this.f3178y;
    }

    public int getScaleControlViewWidth() {
        return this.f3178y;
    }

    public void onCreate(Context context, Bundle bundle) {
        if (bundle != null) {
            f3155i = bundle.getString("customMapPath");
            if (bundle == null) {
                m4183a(context, new BaiduMapOptions());
                return;
            }
            MapStatus mapStatus = (MapStatus) bundle.getParcelable("mapstatus");
            if (this.f3162g != null) {
                this.f3162g = (Point) bundle.getParcelable("scalePosition");
            }
            if (this.f3163h != null) {
                this.f3163h = (Point) bundle.getParcelable("zoomPosition");
            }
            this.f3171r = bundle.getBoolean("mZoomControlEnabled");
            this.f3172s = bundle.getBoolean("mScaleControlEnabled");
            this.f3170q = bundle.getInt("logoPosition");
            setPadding(bundle.getInt("paddingLeft"), bundle.getInt("paddingTop"), bundle.getInt("paddingRight"), bundle.getInt("paddingBottom"));
            m4183a(context, new BaiduMapOptions().mapStatus(mapStatus));
        }
    }

    public final void onDestroy() {
        this.f3157b.m4628e();
        if (!(this.f3160e == null || this.f3160e.isRecycled())) {
            this.f3160e.recycle();
        }
        this.f3161f.m4643b();
        BMapManager.destroy();
        C1253i.m4753b();
    }

    @SuppressLint({"NewApi"})
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        int childCount = getChildCount();
        m4185a(this.f3159d);
        if (((getWidth() - this.f3173t) - this.f3174u) - this.f3159d.getMeasuredWidth() <= 0 || ((getHeight() - this.f3175v) - this.f3176w) - this.f3159d.getMeasuredHeight() <= 0) {
            this.f3173t = 0;
            this.f3174u = 0;
            this.f3176w = 0;
            this.f3175v = 0;
            f = 1.0f;
            f2 = 1.0f;
        } else {
            f = ((float) ((getWidth() - this.f3173t) - this.f3174u)) / ((float) getWidth());
            f2 = ((float) ((getHeight() - this.f3175v) - this.f3176w)) / ((float) getHeight());
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt == this.f3157b) {
                this.f3157b.layout(0, 0, getWidth(), getHeight());
            } else if (childAt == this.f3159d) {
                r3 = (int) (((float) this.f3173t) + (5.0f * f));
                r0 = (int) (((float) this.f3174u) + (5.0f * f));
                r4 = (int) (((float) this.f3175v) + (5.0f * f2));
                r5 = (int) (((float) this.f3176w) + (5.0f * f2));
                switch (this.f3170q) {
                    case 1:
                        r5 = r4 + this.f3159d.getMeasuredHeight();
                        r0 = this.f3159d.getMeasuredWidth() + r3;
                        break;
                    case 2:
                        r5 = getHeight() - r5;
                        r4 = r5 - this.f3159d.getMeasuredHeight();
                        r3 = (((getWidth() - this.f3159d.getMeasuredWidth()) + this.f3173t) - this.f3174u) / 2;
                        r0 = (((getWidth() + this.f3159d.getMeasuredWidth()) + this.f3173t) - this.f3174u) / 2;
                        break;
                    case 3:
                        r5 = r4 + this.f3159d.getMeasuredHeight();
                        r3 = (((getWidth() - this.f3159d.getMeasuredWidth()) + this.f3173t) - this.f3174u) / 2;
                        r0 = (((getWidth() + this.f3159d.getMeasuredWidth()) + this.f3173t) - this.f3174u) / 2;
                        break;
                    case 4:
                        r5 = getHeight() - r5;
                        r4 = r5 - this.f3159d.getMeasuredHeight();
                        r0 = getWidth() - r0;
                        r3 = r0 - this.f3159d.getMeasuredWidth();
                        break;
                    case 5:
                        r5 = r4 + this.f3159d.getMeasuredHeight();
                        r0 = getWidth() - r0;
                        r3 = r0 - this.f3159d.getMeasuredWidth();
                        break;
                    default:
                        r5 = getHeight() - r5;
                        r0 = this.f3159d.getMeasuredWidth() + r3;
                        r4 = r5 - this.f3159d.getMeasuredHeight();
                        break;
                }
                this.f3159d.layout(r3, r4, r0, r5);
            } else if (childAt == this.f3161f) {
                if (this.f3161f.m4642a()) {
                    m4185a(this.f3161f);
                    if (this.f3163h == null) {
                        r3 = (int) ((((float) (getHeight() - 15)) * f2) + ((float) this.f3175v));
                        r4 = (int) ((((float) (getWidth() - 15)) * f) + ((float) this.f3173t));
                        r5 = r4 - this.f3161f.getMeasuredWidth();
                        r0 = r3 - this.f3161f.getMeasuredHeight();
                        if (this.f3170q == 4) {
                            r3 -= this.f3159d.getMeasuredHeight();
                            r0 -= this.f3159d.getMeasuredHeight();
                        }
                        this.f3161f.layout(r5, r0, r4, r3);
                    } else {
                        this.f3161f.layout(this.f3163h.x, this.f3163h.y, this.f3163h.x + this.f3161f.getMeasuredWidth(), this.f3163h.y + this.f3161f.getMeasuredHeight());
                    }
                }
            } else if (childAt == this.f3164j) {
                m4185a(this.f3164j);
                if (this.f3162g == null) {
                    r0 = (int) ((((float) this.f3176w) + (5.0f * f2)) + 56.0f);
                    this.f3178y = this.f3164j.getMeasuredWidth();
                    this.f3177x = this.f3164j.getMeasuredHeight();
                    r3 = (int) (((float) this.f3173t) + (5.0f * f));
                    r0 = (getHeight() - r0) - this.f3159d.getMeasuredHeight();
                    this.f3164j.layout(r3, r0, this.f3178y + r3, this.f3177x + r0);
                } else {
                    this.f3164j.layout(this.f3162g.x, this.f3162g.y, this.f3162g.x + this.f3164j.getMeasuredWidth(), this.f3162g.y + this.f3164j.getMeasuredHeight());
                }
            } else {
                LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof MapViewLayoutParams) {
                    Point point;
                    MapViewLayoutParams mapViewLayoutParams = (MapViewLayoutParams) layoutParams;
                    if (mapViewLayoutParams.f3031c == ELayoutMode.absoluteMode) {
                        point = mapViewLayoutParams.f3030b;
                    } else {
                        point = this.f3157b.m4625b().m4673a(CoordUtil.ll2mc(mapViewLayoutParams.f3029a));
                    }
                    m4185a(childAt);
                    r5 = childAt.getMeasuredWidth();
                    int measuredHeight = childAt.getMeasuredHeight();
                    float f3 = mapViewLayoutParams.f3032d;
                    int i6 = (int) (((float) point.x) - (f3 * ((float) r5)));
                    r0 = mapViewLayoutParams.f3034f + ((int) (((float) point.y) - (mapViewLayoutParams.f3033e * ((float) measuredHeight))));
                    childAt.layout(i6, r0, i6 + r5, r0 + measuredHeight);
                }
            }
        }
    }

    public final void onPause() {
        this.f3157b.m4627d();
    }

    public final void onResume() {
        this.f3157b.m4626c();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null && this.f3158c != null) {
            bundle.putParcelable("mapstatus", this.f3158c.getMapStatus());
            if (this.f3162g != null) {
                bundle.putParcelable("scalePosition", this.f3162g);
            }
            if (this.f3163h != null) {
                bundle.putParcelable("zoomPosition", this.f3163h);
            }
            bundle.putBoolean("mZoomControlEnabled", this.f3171r);
            bundle.putBoolean("mScaleControlEnabled", this.f3172s);
            bundle.putInt("logoPosition", this.f3170q);
            bundle.putInt("paddingLeft", this.f3173t);
            bundle.putInt("paddingTop", this.f3175v);
            bundle.putInt("paddingRight", this.f3174u);
            bundle.putInt("paddingBottom", this.f3176w);
            bundle.putString("customMapPath", f3155i);
        }
    }

    public void removeView(View view) {
        if (view != this.f3159d) {
            super.removeView(view);
        }
    }

    public final void setLogoPosition(LogoPosition logoPosition) {
        if (logoPosition == null) {
            this.f3170q = LogoPosition.logoPostionleftBottom.ordinal();
        }
        this.f3170q = logoPosition.ordinal();
        requestLayout();
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.f3173t = i;
        this.f3175v = i2;
        this.f3174u = i3;
        this.f3176w = i4;
    }

    public void setScaleControlPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f3162g = point;
            requestLayout();
        }
    }

    public void setZoomControlsPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f3163h = point;
            requestLayout();
        }
    }

    public void showScaleControl(boolean z) {
        this.f3164j.setVisibility(z ? 0 : 8);
        this.f3172s = z;
    }

    public void showZoomControls(boolean z) {
        if (this.f3161f.m4642a()) {
            this.f3161f.setVisibility(z ? 0 : 8);
            this.f3171r = z;
        }
    }
}
