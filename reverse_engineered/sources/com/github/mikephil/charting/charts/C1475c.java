package com.github.mikephil.charting.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.github.mikephil.charting.components.C1981d;
import com.github.mikephil.charting.components.C3207c;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.C3224j;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.C2012c;
import com.github.mikephil.charting.listener.C3285b;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p128a.C2007e;
import com.github.mikephil.charting.p121c.C3192f;
import com.github.mikephil.charting.p121c.C3193c;
import com.github.mikephil.charting.p127f.C3248g;
import com.github.mikephil.charting.p127f.C3259i;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p181d.C3209f;
import com.github.mikephil.charting.p181d.C3210b;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressLint({"NewApi"})
/* compiled from: Chart */
/* renamed from: com.github.mikephil.charting.charts.c */
public abstract class C1475c<T extends C3224j<? extends C3220e<? extends Entry>>> extends ViewGroup implements C2007e {
    /* renamed from: B */
    protected boolean f6936B = false;
    /* renamed from: C */
    protected T f6937C = null;
    /* renamed from: D */
    protected boolean f6938D = true;
    /* renamed from: E */
    protected C3193c f6939E = new C3193c(0);
    /* renamed from: F */
    protected Paint f6940F;
    /* renamed from: G */
    protected Paint f6941G;
    /* renamed from: H */
    protected XAxis f6942H;
    /* renamed from: I */
    protected boolean f6943I = true;
    /* renamed from: J */
    protected C3207c f6944J;
    /* renamed from: K */
    protected Legend f6945K;
    /* renamed from: L */
    protected C2012c f6946L;
    /* renamed from: M */
    protected ChartTouchListener f6947M;
    /* renamed from: N */
    protected C3259i f6948N;
    /* renamed from: O */
    protected C3248g f6949O;
    /* renamed from: P */
    protected C3209f f6950P;
    /* renamed from: Q */
    protected C3275j f6951Q = new C3275j();
    /* renamed from: R */
    protected C3185a f6952R;
    /* renamed from: S */
    protected C3213d[] f6953S;
    /* renamed from: T */
    protected float f6954T = 0.0f;
    /* renamed from: U */
    protected boolean f6955U = true;
    /* renamed from: V */
    protected C1981d f6956V;
    /* renamed from: W */
    protected ArrayList<Runnable> f6957W = new ArrayList();
    /* renamed from: a */
    private boolean f6958a = true;
    /* renamed from: b */
    private float f6959b = 0.9f;
    /* renamed from: c */
    private String f6960c = "No chart data available.";
    /* renamed from: d */
    private C3285b f6961d;
    /* renamed from: e */
    private float f6962e = 0.0f;
    /* renamed from: f */
    private float f6963f = 0.0f;
    /* renamed from: g */
    private float f6964g = 0.0f;
    /* renamed from: h */
    private float f6965h = 0.0f;
    /* renamed from: i */
    private boolean f6966i = false;
    /* renamed from: j */
    private boolean f6967j = false;

    /* renamed from: b */
    protected abstract void m8163b();

    /* renamed from: h */
    public abstract void m8168h();

    /* renamed from: j */
    protected abstract void m8169j();

    public C1475c(Context context) {
        super(context);
        m8156a();
    }

    public C1475c(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m8156a();
    }

    public C1475c(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m8156a();
    }

    /* renamed from: a */
    protected void m8156a() {
        setWillNotDraw(false);
        if (VERSION.SDK_INT < 11) {
            this.f6952R = new C3185a();
        } else {
            this.f6952R = new C3185a(new c$1(this));
        }
        C3283i.a(getContext());
        this.f6954T = C3283i.a(500.0f);
        this.f6944J = new C3207c();
        this.f6945K = new Legend();
        this.f6948N = new C3259i(this.f6951Q, this.f6945K);
        this.f6942H = new XAxis();
        this.f6940F = new Paint(1);
        this.f6941G = new Paint(1);
        this.f6941G.setColor(Color.rgb(247, 189, 51));
        this.f6941G.setTextAlign(Align.CENTER);
        this.f6941G.setTextSize(C3283i.a(12.0f));
        if (this.f6936B) {
            Log.i("", "Chart.init()");
        }
    }

    public void setData(T t) {
        this.f6937C = t;
        this.f6966i = false;
        if (t != null) {
            m8166c(t.e(), t.f());
            for (C3220e c3220e : this.f6937C.i()) {
                if (c3220e.p() || c3220e.o() == this.f6939E) {
                    c3220e.a(this.f6939E);
                }
            }
            m8168h();
            if (this.f6936B) {
                Log.i("MPAndroidChart", "Data is set.");
            }
        }
    }

    /* renamed from: u */
    public void m8170u() {
        this.f6937C = null;
        this.f6966i = false;
        this.f6953S = null;
        invalidate();
    }

    /* renamed from: c */
    protected void m8166c(float f, float f2) {
        float max;
        if (this.f6937C == null || this.f6937C.k() < 2) {
            max = Math.max(Math.abs(f), Math.abs(f2));
        } else {
            max = Math.abs(f2 - f);
        }
        this.f6939E.a(C3283i.b(max));
    }

    protected void onDraw(Canvas canvas) {
        boolean z = true;
        if (this.f6937C == null) {
            if (TextUtils.isEmpty(this.f6960c)) {
                z = false;
            }
            if (z) {
                C3279e center = getCenter();
                canvas.drawText(this.f6960c, center.f14200a, center.f14201b, this.f6941G);
            }
        } else if (!this.f6966i) {
            m8169j();
            this.f6966i = true;
        }
    }

    /* renamed from: b */
    protected void m8164b(Canvas canvas) {
        if (this.f6944J != null && this.f6944J.A()) {
            float width;
            float height;
            C3279e b = this.f6944J.b();
            this.f6940F.setTypeface(this.f6944J.x());
            this.f6940F.setTextSize(this.f6944J.y());
            this.f6940F.setColor(this.f6944J.z());
            this.f6940F.setTextAlign(this.f6944J.c());
            if (b == null) {
                width = (((float) getWidth()) - this.f6951Q.b()) - this.f6944J.v();
                height = (((float) getHeight()) - this.f6951Q.d()) - this.f6944J.w();
            } else {
                width = b.f14200a;
                height = b.f14201b;
            }
            canvas.drawText(this.f6944J.a(), width, height, this.f6940F);
        }
    }

    public float getMaxHighlightDistance() {
        return this.f6954T;
    }

    public void setMaxHighlightDistance(float f) {
        this.f6954T = C3283i.a(f);
    }

    public C3213d[] getHighlighted() {
        return this.f6953S;
    }

    /* renamed from: v */
    public boolean m8171v() {
        return this.f6938D;
    }

    public void setHighlightPerTapEnabled(boolean z) {
        this.f6938D = z;
    }

    /* renamed from: w */
    public boolean m8172w() {
        return (this.f6953S == null || this.f6953S.length <= 0 || this.f6953S[0] == null) ? false : true;
    }

    protected void setLastHighlighted(C3213d[] c3213dArr) {
        if (c3213dArr == null || c3213dArr.length <= 0 || c3213dArr[0] == null) {
            this.f6947M.a(null);
        } else {
            this.f6947M.a(c3213dArr[0]);
        }
    }

    /* renamed from: a */
    public void m8162a(C3213d[] c3213dArr) {
        this.f6953S = c3213dArr;
        setLastHighlighted(c3213dArr);
        invalidate();
    }

    /* renamed from: a */
    public void m8158a(float f, int i) {
        m8159a(f, i, true);
    }

    /* renamed from: a */
    public void m8159a(float f, int i, boolean z) {
        m8157a(f, Float.NaN, i, z);
    }

    /* renamed from: a */
    public void m8157a(float f, float f2, int i, boolean z) {
        if (i < 0 || i >= this.f6937C.d()) {
            m8161a(null, z);
        } else {
            m8161a(new C3213d(f, f2, i), z);
        }
    }

    /* renamed from: a */
    public void m8160a(C3213d c3213d) {
        m8161a(c3213d, false);
    }

    /* renamed from: a */
    public void m8161a(C3213d c3213d, boolean z) {
        Entry entry = null;
        if (c3213d == null) {
            this.f6953S = null;
        } else {
            if (this.f6936B) {
                Log.i("MPAndroidChart", "Highlighted: " + c3213d.toString());
            }
            Entry a = this.f6937C.a(c3213d);
            if (a == null) {
                this.f6953S = null;
                c3213d = null;
                entry = a;
            } else {
                this.f6953S = new C3213d[]{c3213d};
                entry = a;
            }
        }
        setLastHighlighted(this.f6953S);
        if (z && this.f6946L != null) {
            if (m8172w()) {
                this.f6946L.a(entry, c3213d);
            } else {
                this.f6946L.a();
            }
        }
        invalidate();
    }

    /* renamed from: a */
    public C3213d m8155a(float f, float f2) {
        if (this.f6937C != null) {
            return getHighlighter().a(f, f2);
        }
        Log.e("MPAndroidChart", "Can't select by touch. No data set.");
        return null;
    }

    public void setOnTouchListener(ChartTouchListener chartTouchListener) {
        this.f6947M = chartTouchListener;
    }

    public ChartTouchListener getOnTouchListener() {
        return this.f6947M;
    }

    /* renamed from: c */
    protected void m8167c(Canvas canvas) {
        if (this.f6956V != null && m8154B() && m8172w()) {
            for (int i = 0; i < this.f6953S.length; i++) {
                C3213d c3213d = this.f6953S[i];
                C3220e a = this.f6937C.a(c3213d.f());
                Entry a2 = this.f6937C.a(this.f6953S[i]);
                int d = a.d(a2);
                if (a2 != null && ((float) d) <= ((float) a.F()) * this.f6952R.b()) {
                    float[] b = m8165b(c3213d);
                    if (this.f6951Q.b(b[0], b[1])) {
                        this.f6956V.a(a2, c3213d);
                        this.f6956V.a(canvas, b[0], b[1]);
                    }
                }
            }
        }
    }

    /* renamed from: b */
    protected float[] m8165b(C3213d c3213d) {
        return new float[]{c3213d.i(), c3213d.j()};
    }

    public C3185a getAnimator() {
        return this.f6952R;
    }

    /* renamed from: x */
    public boolean m8173x() {
        return this.f6958a;
    }

    public void setDragDecelerationEnabled(boolean z) {
        this.f6958a = z;
    }

    public float getDragDecelerationFrictionCoef() {
        return this.f6959b;
    }

    public void setDragDecelerationFrictionCoef(float f) {
        float f2 = 0.0f;
        if (f >= 0.0f) {
            f2 = f;
        }
        if (f2 >= 1.0f) {
            f2 = 0.999f;
        }
        this.f6959b = f2;
    }

    public XAxis getXAxis() {
        return this.f6942H;
    }

    public C3192f getDefaultValueFormatter() {
        return this.f6939E;
    }

    public void setOnChartValueSelectedListener(C2012c c2012c) {
        this.f6946L = c2012c;
    }

    public void setOnChartGestureListener(C3285b c3285b) {
        this.f6961d = c3285b;
    }

    public C3285b getOnChartGestureListener() {
        return this.f6961d;
    }

    public float getYMax() {
        return this.f6937C.f();
    }

    public float getYMin() {
        return this.f6937C.e();
    }

    public float getXChartMax() {
        return this.f6942H.s;
    }

    public float getXChartMin() {
        return this.f6942H.t;
    }

    public float getXRange() {
        return this.f6942H.u;
    }

    public C3279e getCenter() {
        return C3279e.a(((float) getWidth()) / 2.0f, ((float) getHeight()) / 2.0f);
    }

    public C3279e getCenterOffsets() {
        return this.f6951Q.l();
    }

    public void setExtraTopOffset(float f) {
        this.f6962e = C3283i.a(f);
    }

    public float getExtraTopOffset() {
        return this.f6962e;
    }

    public void setExtraRightOffset(float f) {
        this.f6963f = C3283i.a(f);
    }

    public float getExtraRightOffset() {
        return this.f6963f;
    }

    public void setExtraBottomOffset(float f) {
        this.f6964g = C3283i.a(f);
    }

    public float getExtraBottomOffset() {
        return this.f6964g;
    }

    public void setExtraLeftOffset(float f) {
        this.f6965h = C3283i.a(f);
    }

    public float getExtraLeftOffset() {
        return this.f6965h;
    }

    public void setLogEnabled(boolean z) {
        this.f6936B = z;
    }

    /* renamed from: y */
    public boolean m8174y() {
        return this.f6936B;
    }

    public void setNoDataText(String str) {
        this.f6960c = str;
    }

    public void setNoDataTextColor(int i) {
        this.f6941G.setColor(i);
    }

    public void setNoDataTextTypeface(Typeface typeface) {
        this.f6941G.setTypeface(typeface);
    }

    public void setTouchEnabled(boolean z) {
        this.f6943I = z;
    }

    public void setMarker(C1981d c1981d) {
        this.f6956V = c1981d;
    }

    public C1981d getMarker() {
        return this.f6956V;
    }

    @Deprecated
    public void setMarkerView(C1981d c1981d) {
        setMarker(c1981d);
    }

    @Deprecated
    public C1981d getMarkerView() {
        return getMarker();
    }

    public void setDescription(C3207c c3207c) {
        this.f6944J = c3207c;
    }

    public C3207c getDescription() {
        return this.f6944J;
    }

    public Legend getLegend() {
        return this.f6945K;
    }

    public C3259i getLegendRenderer() {
        return this.f6948N;
    }

    public RectF getContentRect() {
        return this.f6951Q.k();
    }

    /* renamed from: z */
    public void m8175z() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    /* renamed from: A */
    public void m8153A() {
        ViewParent parent = getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
    }

    @Deprecated
    public void setDrawMarkerViews(boolean z) {
        setDrawMarkers(z);
    }

    /* renamed from: B */
    public boolean m8154B() {
        return this.f6955U;
    }

    public void setDrawMarkers(boolean z) {
        this.f6955U = z;
    }

    public T getData() {
        return this.f6937C;
    }

    public C3275j getViewPortHandler() {
        return this.f6951Q;
    }

    public C3248g getRenderer() {
        return this.f6949O;
    }

    public void setRenderer(C3248g c3248g) {
        if (c3248g != null) {
            this.f6949O = c3248g;
        }
    }

    public C3209f getHighlighter() {
        return this.f6950P;
    }

    public void setHighlighter(C3210b c3210b) {
        this.f6950P = c3210b;
    }

    public C3279e getCenterOfView() {
        return getCenter();
    }

    public Bitmap getChartBitmap() {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        Drawable background = getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        draw(canvas);
        return createBitmap;
    }

    public ArrayList<Runnable> getJobs() {
        return this.f6957W;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        for (int i5 = 0; i5 < getChildCount(); i5++) {
            getChildAt(i5).layout(i, i2, i3, i4);
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int a = (int) C3283i.a(50.0f);
        setMeasuredDimension(Math.max(getSuggestedMinimumWidth(), C1475c.resolveSize(a, i)), Math.max(getSuggestedMinimumHeight(), C1475c.resolveSize(a, i2)));
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        if (this.f6936B) {
            Log.i("MPAndroidChart", "OnSizeChanged()");
        }
        if (i > 0 && i2 > 0 && i < 10000 && i2 < 10000) {
            this.f6951Q.a((float) i, (float) i2);
            if (this.f6936B) {
                Log.i("MPAndroidChart", "Setting chart dimens, width: " + i + ", height: " + i2);
            }
            Iterator it = this.f6957W.iterator();
            while (it.hasNext()) {
                post((Runnable) it.next());
            }
            this.f6957W.clear();
        }
        m8168h();
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setHardwareAccelerationEnabled(boolean z) {
        if (VERSION.SDK_INT < 11) {
            Log.e("MPAndroidChart", "Cannot enable/disable hardware acceleration for devices below API level 11.");
        } else if (z) {
            setLayerType(2, null);
        } else {
            setLayerType(1, null);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f6967j) {
            m8152a((View) this);
        }
    }

    /* renamed from: a */
    private void m8152a(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                m8152a(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    public void setUnbindEnabled(boolean z) {
        this.f6967j = z;
    }
}
