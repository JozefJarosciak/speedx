package com.baidu.mapapi.map;

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
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
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
import com.baidu.platform.comapi.map.C1244N;
import com.baidu.platform.comapi.map.C1253i;
import com.baidu.platform.comapi.map.C1255j;
import com.google.common.primitives.Ints;
import java.io.File;

public final class MapView extends ViewGroup {
    /* renamed from: a */
    private static final String f2996a = MapView.class.getSimpleName();
    /* renamed from: b */
    private static String f2997b;
    /* renamed from: n */
    private static final SparseArray<Integer> f2998n = new SparseArray();
    /* renamed from: c */
    private C1255j f2999c;
    /* renamed from: d */
    private BaiduMap f3000d;
    /* renamed from: e */
    private ImageView f3001e;
    /* renamed from: f */
    private Bitmap f3002f;
    /* renamed from: g */
    private C1244N f3003g;
    /* renamed from: h */
    private Point f3004h;
    /* renamed from: i */
    private Point f3005i;
    /* renamed from: j */
    private RelativeLayout f3006j;
    /* renamed from: k */
    private TextView f3007k;
    /* renamed from: l */
    private TextView f3008l;
    /* renamed from: m */
    private ImageView f3009m;
    /* renamed from: o */
    private int f3010o = LogoPosition.logoPostionleftBottom.ordinal();
    /* renamed from: p */
    private boolean f3011p = true;
    /* renamed from: q */
    private boolean f3012q = true;
    /* renamed from: r */
    private float f3013r;
    /* renamed from: s */
    private C1114l f3014s;
    /* renamed from: t */
    private int f3015t;
    /* renamed from: u */
    private int f3016u;
    /* renamed from: v */
    private int f3017v;
    /* renamed from: w */
    private int f3018w;
    /* renamed from: x */
    private int f3019x;
    /* renamed from: y */
    private int f3020y;

    static {
        f2998n.append(3, Integer.valueOf(2000000));
        f2998n.append(4, Integer.valueOf(1000000));
        f2998n.append(5, Integer.valueOf(500000));
        f2998n.append(6, Integer.valueOf(200000));
        f2998n.append(7, Integer.valueOf(100000));
        f2998n.append(8, Integer.valueOf(50000));
        f2998n.append(9, Integer.valueOf(25000));
        f2998n.append(10, Integer.valueOf(20000));
        f2998n.append(11, Integer.valueOf(10000));
        f2998n.append(12, Integer.valueOf(5000));
        f2998n.append(13, Integer.valueOf(m_AppUI.MSG_APP_DATA_OK));
        f2998n.append(14, Integer.valueOf(1000));
        f2998n.append(15, Integer.valueOf(500));
        f2998n.append(16, Integer.valueOf(200));
        f2998n.append(17, Integer.valueOf(100));
        f2998n.append(18, Integer.valueOf(50));
        f2998n.append(19, Integer.valueOf(20));
        f2998n.append(20, Integer.valueOf(10));
        f2998n.append(21, Integer.valueOf(5));
        f2998n.append(22, Integer.valueOf(2));
    }

    public MapView(Context context) {
        super(context);
        m4143a(context, null);
    }

    public MapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m4143a(context, null);
    }

    public MapView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m4143a(context, null);
    }

    public MapView(Context context, BaiduMapOptions baiduMapOptions) {
        super(context);
        m4143a(context, baiduMapOptions);
    }

    /* renamed from: a */
    private void m4142a(Context context) {
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
            this.f3002f = Bitmap.createBitmap(a, 0, 0, a.getWidth(), a.getHeight(), matrix, true);
        } else if (densityDpi <= 320 || densityDpi > 480) {
            this.f3002f = a;
        } else {
            matrix = new Matrix();
            matrix.postScale(1.5f, 1.5f);
            this.f3002f = Bitmap.createBitmap(a, 0, 0, a.getWidth(), a.getHeight(), matrix, true);
        }
        if (this.f3002f != null) {
            this.f3001e = new ImageView(context);
            this.f3001e.setImageBitmap(this.f3002f);
            addView(this.f3001e);
        }
    }

    /* renamed from: a */
    private void m4143a(Context context, BaiduMapOptions baiduMapOptions) {
        C1253i.m4750a();
        BMapManager.init();
        m4144a(context, baiduMapOptions, f2997b);
        this.f3000d = new BaiduMap(this.f2999c);
        m4142a(context);
        m4148b(context);
        if (!(baiduMapOptions == null || baiduMapOptions.f2875h)) {
            this.f3003g.setVisibility(4);
        }
        m4150c(context);
        if (!(baiduMapOptions == null || baiduMapOptions.f2876i)) {
            this.f3006j.setVisibility(4);
        }
        if (!(baiduMapOptions == null || baiduMapOptions.f2877j == null)) {
            this.f3010o = baiduMapOptions.f2877j.ordinal();
        }
        if (!(baiduMapOptions == null || baiduMapOptions.f2879l == null)) {
            this.f3005i = baiduMapOptions.f2879l;
        }
        if (baiduMapOptions != null && baiduMapOptions.f2878k != null) {
            this.f3004h = baiduMapOptions.f2878k;
        }
    }

    /* renamed from: a */
    private void m4144a(Context context, BaiduMapOptions baiduMapOptions, String str) {
        if (baiduMapOptions == null) {
            this.f2999c = new C1255j(context, null, str);
        } else {
            this.f2999c = new C1255j(context, baiduMapOptions.m4090a(), str);
        }
        addView(this.f2999c);
        this.f3014s = new C1124i(this);
        this.f2999c.m4760a().m4685a(this.f3014s);
    }

    /* renamed from: a */
    private void m4145a(View view) {
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
    private void m4147b() {
        boolean z = false;
        float f = this.f2999c.m4760a().m4660D().f3678a;
        if (this.f3003g.m4642a()) {
            this.f3003g.m4645b(f > this.f2999c.m4760a().f3755b);
            C1244N c1244n = this.f3003g;
            if (f < this.f2999c.m4760a().f3754a) {
                z = true;
            }
            c1244n.m4641a(z);
        }
    }

    /* renamed from: b */
    private void m4148b(Context context) {
        this.f3003g = new C1244N(context, false);
        if (this.f3003g.m4642a()) {
            this.f3003g.m4644b(new C1125j(this));
            this.f3003g.m4640a(new C1126k(this));
            addView(this.f3003g);
        }
    }

    /* renamed from: c */
    private void m4150c(Context context) {
        this.f3006j = new RelativeLayout(context);
        this.f3006j.setLayoutParams(new LayoutParams(-2, -2));
        this.f3007k = new TextView(context);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(14);
        this.f3007k.setTextColor(Color.parseColor("#FFFFFF"));
        this.f3007k.setTextSize(2, 11.0f);
        this.f3007k.setTypeface(this.f3007k.getTypeface(), 1);
        this.f3007k.setLayoutParams(layoutParams);
        this.f3007k.setId(Integer.MAX_VALUE);
        this.f3006j.addView(this.f3007k);
        this.f3008l = new TextView(context);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.addRule(14);
        this.f3008l.setTextColor(Color.parseColor("#000000"));
        this.f3008l.setTextSize(2, 11.0f);
        this.f3008l.setLayoutParams(layoutParams);
        this.f3006j.addView(this.f3008l);
        this.f3009m = new ImageView(context);
        layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.addRule(14);
        layoutParams.addRule(3, this.f3007k.getId());
        this.f3009m.setLayoutParams(layoutParams);
        Bitmap a = C1221a.m4570a("icon_scale.9.png", context);
        byte[] ninePatchChunk = a.getNinePatchChunk();
        NinePatch.isNinePatchChunk(ninePatchChunk);
        this.f3009m.setBackgroundDrawable(new NinePatchDrawable(a, ninePatchChunk, new Rect(), null));
        this.f3006j.addView(this.f3009m);
        addView(this.f3006j);
    }

    public static void setCustomMapStylePath(String str) {
        if (str == null || str.length() == 0) {
            throw new RuntimeException("customMapStylePath String is illegal");
        } else if (new File(str).exists()) {
            f2997b = str;
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
        switch (this.f3010o) {
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
        this.f3000d.f2843a = this;
        return this.f3000d;
    }

    public final int getMapLevel() {
        return ((Integer) f2998n.get((int) this.f2999c.m4760a().m4660D().f3678a)).intValue();
    }

    public int getScaleControlViewHeight() {
        return this.f3019x;
    }

    public int getScaleControlViewWidth() {
        return this.f3020y;
    }

    public boolean handleMultiTouch(float f, float f2, float f3, float f4) {
        return this.f2999c != null && this.f2999c.m4764a(f, f2, f3, f4);
    }

    public void handleTouchDown(float f, float f2) {
        if (this.f2999c != null) {
            this.f2999c.m4761a(f, f2);
        }
    }

    public boolean handleTouchMove(float f, float f2) {
        return this.f2999c != null && this.f2999c.m4768c(f, f2);
    }

    public boolean handleTouchUp(float f, float f2) {
        return this.f2999c == null ? false : this.f2999c.m4766b(f, f2);
    }

    public boolean inRangeOfView(float f, float f2) {
        return this.f2999c != null && this.f2999c.m4770d(f, f2);
    }

    public void onCreate(Context context, Bundle bundle) {
        if (bundle != null) {
            f2997b = bundle.getString("customMapPath");
            if (bundle == null) {
                m4143a(context, new BaiduMapOptions());
                return;
            }
            MapStatus mapStatus = (MapStatus) bundle.getParcelable("mapstatus");
            if (this.f3004h != null) {
                this.f3004h = (Point) bundle.getParcelable("scalePosition");
            }
            if (this.f3005i != null) {
                this.f3005i = (Point) bundle.getParcelable("zoomPosition");
            }
            this.f3011p = bundle.getBoolean("mZoomControlEnabled");
            this.f3012q = bundle.getBoolean("mScaleControlEnabled");
            this.f3010o = bundle.getInt("logoPosition");
            setPadding(bundle.getInt("paddingLeft"), bundle.getInt("paddingTop"), bundle.getInt("paddingRight"), bundle.getInt("paddingBottom"));
            m4143a(context, new BaiduMapOptions().mapStatus(mapStatus));
        }
    }

    public final void onDestroy() {
        this.f2999c.m4765b();
        if (!(this.f3002f == null || this.f3002f.isRecycled())) {
            this.f3002f.recycle();
            this.f3002f = null;
        }
        this.f3003g.m4643b();
        BMapManager.destroy();
        C1253i.m4753b();
    }

    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        float f;
        float f2;
        int childCount = getChildCount();
        m4145a(this.f3001e);
        if (((getWidth() - this.f3015t) - this.f3016u) - this.f3001e.getMeasuredWidth() <= 0 || ((getHeight() - this.f3017v) - this.f3018w) - this.f3001e.getMeasuredHeight() <= 0) {
            this.f3015t = 0;
            this.f3016u = 0;
            this.f3018w = 0;
            this.f3017v = 0;
            f = 1.0f;
            f2 = 1.0f;
        } else {
            f = ((float) ((getWidth() - this.f3015t) - this.f3016u)) / ((float) getWidth());
            f2 = ((float) ((getHeight() - this.f3017v) - this.f3018w)) / ((float) getHeight());
        }
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            if (childAt != null) {
                if (childAt == this.f2999c) {
                    this.f2999c.layout(0, 0, getWidth(), getHeight());
                } else if (childAt == this.f3001e) {
                    r3 = (int) (((float) this.f3015t) + (5.0f * f));
                    r0 = (int) (((float) this.f3016u) + (5.0f * f));
                    r4 = (int) (((float) this.f3017v) + (5.0f * f2));
                    r5 = (int) (((float) this.f3018w) + (5.0f * f2));
                    switch (this.f3010o) {
                        case 1:
                            r5 = r4 + this.f3001e.getMeasuredHeight();
                            r0 = this.f3001e.getMeasuredWidth() + r3;
                            break;
                        case 2:
                            r5 = getHeight() - r5;
                            r4 = r5 - this.f3001e.getMeasuredHeight();
                            r3 = (((getWidth() - this.f3001e.getMeasuredWidth()) + this.f3015t) - this.f3016u) / 2;
                            r0 = (((getWidth() + this.f3001e.getMeasuredWidth()) + this.f3015t) - this.f3016u) / 2;
                            break;
                        case 3:
                            r5 = r4 + this.f3001e.getMeasuredHeight();
                            r3 = (((getWidth() - this.f3001e.getMeasuredWidth()) + this.f3015t) - this.f3016u) / 2;
                            r0 = (((getWidth() + this.f3001e.getMeasuredWidth()) + this.f3015t) - this.f3016u) / 2;
                            break;
                        case 4:
                            r5 = getHeight() - r5;
                            r4 = r5 - this.f3001e.getMeasuredHeight();
                            r0 = getWidth() - r0;
                            r3 = r0 - this.f3001e.getMeasuredWidth();
                            break;
                        case 5:
                            r5 = r4 + this.f3001e.getMeasuredHeight();
                            r0 = getWidth() - r0;
                            r3 = r0 - this.f3001e.getMeasuredWidth();
                            break;
                        default:
                            r5 = getHeight() - r5;
                            r0 = this.f3001e.getMeasuredWidth() + r3;
                            r4 = r5 - this.f3001e.getMeasuredHeight();
                            break;
                    }
                    this.f3001e.layout(r3, r4, r0, r5);
                } else if (childAt == this.f3003g) {
                    if (this.f3003g.m4642a()) {
                        m4145a(this.f3003g);
                        if (this.f3005i == null) {
                            r3 = (int) ((((float) (getHeight() - 15)) * f2) + ((float) this.f3017v));
                            r4 = (int) ((((float) (getWidth() - 15)) * f) + ((float) this.f3015t));
                            r5 = r4 - this.f3003g.getMeasuredWidth();
                            r0 = r3 - this.f3003g.getMeasuredHeight();
                            if (this.f3010o == 4) {
                                r3 -= this.f3001e.getMeasuredHeight();
                                r0 -= this.f3001e.getMeasuredHeight();
                            }
                            this.f3003g.layout(r5, r0, r4, r3);
                        } else {
                            this.f3003g.layout(this.f3005i.x, this.f3005i.y, this.f3005i.x + this.f3003g.getMeasuredWidth(), this.f3005i.y + this.f3003g.getMeasuredHeight());
                        }
                    }
                } else if (childAt == this.f3006j) {
                    m4145a(this.f3006j);
                    if (this.f3004h == null) {
                        r0 = (int) ((((float) this.f3018w) + (5.0f * f2)) + 56.0f);
                        this.f3020y = this.f3006j.getMeasuredWidth();
                        this.f3019x = this.f3006j.getMeasuredHeight();
                        r3 = (int) (((float) this.f3015t) + (5.0f * f));
                        r0 = (getHeight() - r0) - this.f3001e.getMeasuredHeight();
                        this.f3006j.layout(r3, r0, this.f3020y + r3, this.f3019x + r0);
                    } else {
                        this.f3006j.layout(this.f3004h.x, this.f3004h.y, this.f3004h.x + this.f3006j.getMeasuredWidth(), this.f3004h.y + this.f3006j.getMeasuredHeight());
                    }
                } else {
                    LayoutParams layoutParams = childAt.getLayoutParams();
                    if (layoutParams == null) {
                        Log.e("test", "lp == null");
                    }
                    if (layoutParams instanceof MapViewLayoutParams) {
                        Point point;
                        MapViewLayoutParams mapViewLayoutParams = (MapViewLayoutParams) layoutParams;
                        if (mapViewLayoutParams.f3031c == ELayoutMode.absoluteMode) {
                            point = mapViewLayoutParams.f3030b;
                        } else {
                            point = this.f2999c.m4760a().m4673a(CoordUtil.ll2mc(mapViewLayoutParams.f3029a));
                        }
                        m4145a(childAt);
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
    }

    public final void onPause() {
        this.f2999c.onPause();
    }

    public final void onResume() {
        this.f2999c.onResume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (bundle != null && this.f3000d != null) {
            bundle.putParcelable("mapstatus", this.f3000d.getMapStatus());
            if (this.f3004h != null) {
                bundle.putParcelable("scalePosition", this.f3004h);
            }
            if (this.f3005i != null) {
                bundle.putParcelable("zoomPosition", this.f3005i);
            }
            bundle.putBoolean("mZoomControlEnabled", this.f3011p);
            bundle.putBoolean("mScaleControlEnabled", this.f3012q);
            bundle.putInt("logoPosition", this.f3010o);
            bundle.putInt("paddingLeft", this.f3015t);
            bundle.putInt("paddingTop", this.f3017v);
            bundle.putInt("paddingRight", this.f3016u);
            bundle.putInt("paddingBottom", this.f3018w);
            bundle.putString("customMapPath", f2997b);
        }
    }

    public void removeView(View view) {
        if (view != this.f3001e) {
            super.removeView(view);
        }
    }

    public final void setLogoPosition(LogoPosition logoPosition) {
        if (logoPosition == null) {
            this.f3010o = LogoPosition.logoPostionleftBottom.ordinal();
        }
        this.f3010o = logoPosition.ordinal();
        requestLayout();
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.f3015t = i;
        this.f3017v = i2;
        this.f3016u = i3;
        this.f3018w = i4;
    }

    public void setScaleControlPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f3004h = point;
            requestLayout();
        }
    }

    public void setUpViewEventToMapView(MotionEvent motionEvent) {
        this.f2999c.onTouchEvent(motionEvent);
    }

    public final void setZOrderMediaOverlay(boolean z) {
        if (this.f2999c != null) {
            this.f2999c.setZOrderMediaOverlay(z);
        }
    }

    public void setZoomControlsPosition(Point point) {
        if (point != null && point.x >= 0 && point.y >= 0 && point.x <= getWidth() && point.y <= getHeight()) {
            this.f3005i = point;
            requestLayout();
        }
    }

    public void showScaleControl(boolean z) {
        this.f3006j.setVisibility(z ? 0 : 8);
        this.f3012q = z;
    }

    public void showZoomControls(boolean z) {
        if (this.f3003g.m4642a()) {
            this.f3003g.setVisibility(z ? 0 : 8);
            this.f3011p = z;
        }
    }
}
