package com.github.mikephil.charting.charts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.C3225c;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.C3284a;
import com.github.mikephil.charting.listener.C3286d;
import com.github.mikephil.charting.p089e.p090b.C3227b;
import com.github.mikephil.charting.p089e.p128a.C2008b;
import com.github.mikephil.charting.p127f.C2005q;
import com.github.mikephil.charting.p127f.C3269t;
import com.github.mikephil.charting.p181d.C3210b;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3277d;
import com.github.mikephil.charting.p183g.C3281g;
import com.github.mikephil.charting.p183g.C3283i;

@SuppressLint({"RtlHardcoded"})
/* compiled from: BarLineChartBase */
/* renamed from: com.github.mikephil.charting.charts.b */
public abstract class C2009b<T extends C3225c<? extends C3227b<? extends Entry>>> extends C1475c<T> implements C2008b {
    /* renamed from: A */
    protected float[] f9036A;
    /* renamed from: a */
    private boolean f9037a;
    private boolean aa;
    private boolean ab;
    private long ac;
    private long ad;
    private RectF ae;
    private boolean af;
    /* renamed from: b */
    protected int f9038b;
    /* renamed from: c */
    protected boolean f9039c;
    /* renamed from: d */
    protected boolean f9040d;
    /* renamed from: e */
    protected boolean f9041e;
    /* renamed from: f */
    protected boolean f9042f;
    /* renamed from: g */
    protected Paint f9043g;
    /* renamed from: h */
    protected Paint f9044h;
    /* renamed from: i */
    protected boolean f9045i;
    /* renamed from: j */
    protected boolean f9046j;
    /* renamed from: k */
    protected boolean f9047k;
    /* renamed from: l */
    protected float f9048l;
    /* renamed from: m */
    protected boolean f9049m;
    /* renamed from: n */
    protected C3286d f9050n;
    /* renamed from: o */
    protected YAxis f9051o;
    /* renamed from: p */
    protected YAxis f9052p;
    /* renamed from: q */
    protected C3269t f9053q;
    /* renamed from: r */
    protected C3269t f9054r;
    /* renamed from: s */
    protected C3281g f9055s;
    /* renamed from: t */
    protected C3281g f9056t;
    /* renamed from: u */
    protected C2005q f9057u;
    /* renamed from: v */
    protected Matrix f9058v;
    /* renamed from: w */
    protected Matrix f9059w;
    /* renamed from: x */
    protected float[] f9060x;
    /* renamed from: y */
    protected C3277d f9061y;
    /* renamed from: z */
    protected C3277d f9062z;

    public /* bridge */ /* synthetic */ C3225c getData() {
        return (C3225c) super.getData();
    }

    public C2009b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f9038b = 100;
        this.f9039c = false;
        this.f9040d = false;
        this.f9041e = true;
        this.f9042f = true;
        this.f9037a = true;
        this.aa = true;
        this.ab = true;
        this.f9045i = false;
        this.f9046j = false;
        this.f9047k = false;
        this.f9048l = 15.0f;
        this.f9049m = false;
        this.ac = 0;
        this.ad = 0;
        this.ae = new RectF();
        this.f9058v = new Matrix();
        this.f9059w = new Matrix();
        this.af = false;
        this.f9060x = new float[2];
        this.f9061y = C3277d.m15892a(0.0d, 0.0d);
        this.f9062z = C3277d.m15892a(0.0d, 0.0d);
        this.f9036A = new float[2];
    }

    public C2009b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f9038b = 100;
        this.f9039c = false;
        this.f9040d = false;
        this.f9041e = true;
        this.f9042f = true;
        this.f9037a = true;
        this.aa = true;
        this.ab = true;
        this.f9045i = false;
        this.f9046j = false;
        this.f9047k = false;
        this.f9048l = 15.0f;
        this.f9049m = false;
        this.ac = 0;
        this.ad = 0;
        this.ae = new RectF();
        this.f9058v = new Matrix();
        this.f9059w = new Matrix();
        this.af = false;
        this.f9060x = new float[2];
        this.f9061y = C3277d.m15892a(0.0d, 0.0d);
        this.f9062z = C3277d.m15892a(0.0d, 0.0d);
        this.f9036A = new float[2];
    }

    public C2009b(Context context) {
        super(context);
        this.f9038b = 100;
        this.f9039c = false;
        this.f9040d = false;
        this.f9041e = true;
        this.f9042f = true;
        this.f9037a = true;
        this.aa = true;
        this.ab = true;
        this.f9045i = false;
        this.f9046j = false;
        this.f9047k = false;
        this.f9048l = 15.0f;
        this.f9049m = false;
        this.ac = 0;
        this.ad = 0;
        this.ae = new RectF();
        this.f9058v = new Matrix();
        this.f9059w = new Matrix();
        this.af = false;
        this.f9060x = new float[2];
        this.f9061y = C3277d.m15892a(0.0d, 0.0d);
        this.f9062z = C3277d.m15892a(0.0d, 0.0d);
        this.f9036A = new float[2];
    }

    /* renamed from: a */
    protected void mo3350a() {
        super.a();
        this.f9051o = new YAxis(AxisDependency.LEFT);
        this.f9052p = new YAxis(AxisDependency.RIGHT);
        this.f9055s = new C3281g(this.Q);
        this.f9056t = new C3281g(this.Q);
        this.f9053q = new C3269t(this.Q, this.f9051o, this.f9055s);
        this.f9054r = new C3269t(this.Q, this.f9052p, this.f9056t);
        this.f9057u = new C2005q(this.Q, this.H, this.f9055s);
        setHighlighter(new C3210b(this));
        this.M = new C3284a(this, this.Q.m15881p(), 3.0f);
        this.f9043g = new Paint();
        this.f9043g.setStyle(Style.FILL);
        this.f9043g.setColor(Color.rgb(240, 240, 240));
        this.f9044h = new Paint();
        this.f9044h.setStyle(Style.STROKE);
        this.f9044h.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.f9044h.setStrokeWidth(C3283i.m15928a(1.0f));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.C != null) {
            long currentTimeMillis = System.currentTimeMillis();
            m10356a(canvas);
            if (this.f9039c) {
                m10366i();
            }
            if (this.f9051o.A()) {
                this.f9053q.mo3340a(this.f9051o.t, this.f9051o.s, this.f9051o.m15391H());
            }
            if (this.f9052p.A()) {
                this.f9054r.mo3340a(this.f9052p.t, this.f9052p.s, this.f9052p.m15391H());
            }
            if (this.H.A()) {
                this.f9057u.mo3340a(this.H.t, this.H.s, false);
            }
            this.f9057u.mo4006b(canvas);
            this.f9053q.mo4014b(canvas);
            this.f9054r.mo4014b(canvas);
            this.f9057u.m10347c(canvas);
            this.f9053q.m15824c(canvas);
            this.f9054r.m15824c(canvas);
            if (this.H.A() && this.H.o()) {
                this.f9057u.mo4009d(canvas);
            }
            if (this.f9051o.A() && this.f9051o.o()) {
                this.f9053q.mo4017e(canvas);
            }
            if (this.f9052p.A() && this.f9052p.o()) {
                this.f9054r.mo4017e(canvas);
            }
            int save = canvas.save();
            canvas.clipRect(this.Q.m15874k());
            this.O.mo3995a(canvas);
            if (w()) {
                this.O.mo3996a(canvas, this.S);
            }
            canvas.restoreToCount(save);
            this.O.mo3998c(canvas);
            if (this.H.A() && !this.H.o()) {
                this.f9057u.mo4009d(canvas);
            }
            if (this.f9051o.A() && !this.f9051o.o()) {
                this.f9053q.mo4017e(canvas);
            }
            if (this.f9052p.A() && !this.f9052p.o()) {
                this.f9054r.mo4017e(canvas);
            }
            this.f9057u.mo4004a(canvas);
            this.f9053q.mo4011a(canvas);
            this.f9054r.mo4011a(canvas);
            if (m10373p()) {
                save = canvas.save();
                canvas.clipRect(this.Q.m15874k());
                this.O.mo3997b(canvas);
                canvas.restoreToCount(save);
            } else {
                this.O.mo3997b(canvas);
            }
            this.N.m15755a(canvas);
            b(canvas);
            c(canvas);
            if (this.B) {
                currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                this.ac += currentTimeMillis;
                this.ad++;
                Log.i("MPAndroidChart", "Drawtime: " + currentTimeMillis + " ms, average: " + (this.ac / this.ad) + " ms, cycles: " + this.ad);
            }
        }
    }

    /* renamed from: f */
    protected void mo3893f() {
        if (this.B) {
            Log.i("MPAndroidChart", "Preparing Value-Px Matrix, xmin: " + this.H.t + ", xmax: " + this.H.s + ", xdelta: " + this.H.u);
        }
        this.f9056t.m15912a(this.H.t, this.H.u, this.f9052p.u, this.f9052p.t);
        this.f9055s.m15912a(this.H.t, this.H.u, this.f9051o.u, this.f9051o.t);
    }

    /* renamed from: g */
    protected void m10364g() {
        this.f9056t.mo4019a(this.f9052p.m15391H());
        this.f9055s.mo4019a(this.f9051o.m15391H());
    }

    /* renamed from: h */
    public void m10365h() {
        if (this.C != null) {
            if (this.B) {
                Log.i("MPAndroidChart", "Preparing...");
            }
            if (this.O != null) {
                this.O.mo3994a();
            }
            mo3351b();
            this.f9053q.mo3340a(this.f9051o.t, this.f9051o.s, this.f9051o.m15391H());
            this.f9054r.mo3340a(this.f9052p.t, this.f9052p.s, this.f9052p.m15391H());
            this.f9057u.mo3340a(this.H.t, this.H.s, false);
            if (this.K != null) {
                this.N.m15758a(this.C);
            }
            mo3894j();
        } else if (this.B) {
            Log.i("MPAndroidChart", "Preparing... DATA NOT SET.");
        }
    }

    /* renamed from: i */
    protected void m10366i() {
        ((C3225c) this.C).m15578a(getLowestVisibleX(), getHighestVisibleX());
        this.H.a(((C3225c) this.C).m15593g(), ((C3225c) this.C).m15594h());
        if (this.f9051o.A()) {
            this.f9051o.m15399a(((C3225c) this.C).m15574a(AxisDependency.LEFT), ((C3225c) this.C).m15581b(AxisDependency.LEFT));
        }
        if (this.f9052p.A()) {
            this.f9052p.m15399a(((C3225c) this.C).m15574a(AxisDependency.RIGHT), ((C3225c) this.C).m15581b(AxisDependency.RIGHT));
        }
        mo3894j();
    }

    /* renamed from: b */
    protected void mo3351b() {
        this.H.a(((C3225c) this.C).m15593g(), ((C3225c) this.C).m15594h());
        this.f9051o.m15399a(((C3225c) this.C).m15574a(AxisDependency.LEFT), ((C3225c) this.C).m15581b(AxisDependency.LEFT));
        this.f9052p.m15399a(((C3225c) this.C).m15574a(AxisDependency.RIGHT), ((C3225c) this.C).m15581b(AxisDependency.RIGHT));
    }

    /* renamed from: a */
    protected void m10357a(RectF rectF) {
        rectF.left = 0.0f;
        rectF.right = 0.0f;
        rectF.top = 0.0f;
        rectF.bottom = 0.0f;
        if (this.K != null && this.K.m15336A() && !this.K.m15356g()) {
            switch (this.K.m15355f()) {
                case VERTICAL:
                    switch (this.K.m15353d()) {
                        case LEFT:
                            rectF.left += Math.min(this.K.f13908a, this.Q.m15879n() * this.K.m15366q()) + this.K.m15341v();
                            return;
                        case RIGHT:
                            rectF.right += Math.min(this.K.f13908a, this.Q.m15879n() * this.K.m15366q()) + this.K.m15341v();
                            return;
                        case CENTER:
                            switch (this.K.m15354e()) {
                                case TOP:
                                    rectF.top += Math.min(this.K.f13909b, this.Q.m15878m() * this.K.m15366q()) + this.K.m15342w();
                                    return;
                                case BOTTOM:
                                    rectF.bottom += Math.min(this.K.f13909b, this.Q.m15878m() * this.K.m15366q()) + this.K.m15342w();
                                    return;
                                default:
                                    return;
                            }
                        default:
                            return;
                    }
                case HORIZONTAL:
                    switch (this.K.m15354e()) {
                        case TOP:
                            rectF.top += Math.min(this.K.f13909b, this.Q.m15878m() * this.K.m15366q()) + this.K.m15342w();
                            if (getXAxis().A() && getXAxis().h()) {
                                rectF.top += (float) getXAxis().f13939E;
                                return;
                            }
                            return;
                        case BOTTOM:
                            rectF.bottom += Math.min(this.K.f13909b, this.Q.m15878m() * this.K.m15366q()) + this.K.m15342w();
                            if (getXAxis().A() && getXAxis().h()) {
                                rectF.bottom += (float) getXAxis().f13939E;
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    /* renamed from: j */
    public void mo3894j() {
        if (!this.af) {
            float w;
            m10357a(this.ae);
            float f = this.ae.left + 0.0f;
            float f2 = 0.0f + this.ae.top;
            float f3 = this.ae.right + 0.0f;
            float f4 = this.ae.bottom + 0.0f;
            if (this.f9051o.m15397N()) {
                f += this.f9051o.m15398a(this.f9053q.m10333a());
            }
            if (this.f9052p.m15397N()) {
                f3 += this.f9052p.m15398a(this.f9054r.m10333a());
            }
            if (this.H.A() && this.H.h()) {
                w = ((float) this.H.f13939E) + this.H.w();
                if (this.H.m15381B() == XAxisPosition.BOTTOM) {
                    f4 += w;
                } else if (this.H.m15381B() == XAxisPosition.TOP) {
                    f2 += w;
                } else if (this.H.m15381B() == XAxisPosition.BOTH_SIDED) {
                    f4 += w;
                    f2 += w;
                }
            }
            f2 += getExtraTopOffset();
            f3 += getExtraRightOffset();
            f4 += getExtraBottomOffset();
            f += getExtraLeftOffset();
            w = C3283i.m15928a(this.f9048l);
            this.Q.m15851a(Math.max(w, f), Math.max(w, f2), Math.max(w, f3), Math.max(w, f4));
            if (this.B) {
                Log.i("MPAndroidChart", "offsetLeft: " + f + ", offsetTop: " + f2 + ", offsetRight: " + f3 + ", offsetBottom: " + f4);
                Log.i("MPAndroidChart", "Content: " + this.Q.m15874k().toString());
            }
        }
        m10364g();
        mo3893f();
    }

    /* renamed from: a */
    protected void m10356a(Canvas canvas) {
        if (this.f9045i) {
            canvas.drawRect(this.Q.m15874k(), this.f9043g);
        }
        if (this.f9046j) {
            canvas.drawRect(this.Q.m15874k(), this.f9044h);
        }
    }

    /* renamed from: a */
    public C3281g mo3343a(AxisDependency axisDependency) {
        if (axisDependency == AxisDependency.LEFT) {
            return this.f9055s;
        }
        return this.f9056t;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        if (this.M == null || this.C == null || !this.I) {
            return false;
        }
        return this.M.onTouch(this, motionEvent);
    }

    public void computeScroll() {
        if (this.M instanceof C3284a) {
            ((C3284a) this.M).m15969b();
        }
    }

    /* renamed from: a */
    public void m10355a(float f, float f2, float f3, float f4) {
        this.Q.m15852a(f, f2, f3, -f4, this.f9058v);
        this.Q.m15848a(this.f9058v, this, false);
        mo3894j();
        postInvalidate();
    }

    public void setVisibleXRangeMaximum(float f) {
        this.Q.m15849a(this.H.u / f);
    }

    public void setVisibleXRangeMinimum(float f) {
        this.Q.m15856b(this.H.u / f);
    }

    /* renamed from: b */
    public void m10361b(float f, float f2, float f3, float f4) {
        this.af = true;
        final float f5 = f;
        final float f6 = f2;
        final float f7 = f3;
        final float f8 = f4;
        post(new Runnable(this) {
            /* renamed from: e */
            final /* synthetic */ C2009b f13874e;

            public void run() {
                this.f13874e.Q.m15851a(f5, f6, f7, f8);
                this.f13874e.m10364g();
                this.f13874e.mo3893f();
            }
        });
    }

    public void setOnDrawListener(C3286d c3286d) {
        this.f9050n = c3286d;
    }

    public C3286d getDrawListener() {
        return this.f9050n;
    }

    public void setMaxVisibleValueCount(int i) {
        this.f9038b = i;
    }

    public int getMaxVisibleCount() {
        return this.f9038b;
    }

    public void setHighlightPerDragEnabled(boolean z) {
        this.f9042f = z;
    }

    /* renamed from: k */
    public boolean m10368k() {
        return this.f9042f;
    }

    public void setGridBackgroundColor(int i) {
        this.f9043g.setColor(i);
    }

    public void setDragEnabled(boolean z) {
        this.f9037a = z;
    }

    /* renamed from: l */
    public boolean m10369l() {
        return this.f9037a;
    }

    public void setScaleEnabled(boolean z) {
        this.aa = z;
        this.ab = z;
    }

    public void setScaleXEnabled(boolean z) {
        this.aa = z;
    }

    public void setScaleYEnabled(boolean z) {
        this.ab = z;
    }

    /* renamed from: m */
    public boolean m10370m() {
        return this.aa;
    }

    /* renamed from: n */
    public boolean m10371n() {
        return this.ab;
    }

    public void setDoubleTapToZoomEnabled(boolean z) {
        this.f9041e = z;
    }

    /* renamed from: o */
    public boolean m10372o() {
        return this.f9041e;
    }

    public void setDrawGridBackground(boolean z) {
        this.f9045i = z;
    }

    public void setDrawBorders(boolean z) {
        this.f9046j = z;
    }

    public void setClipValuesToContent(boolean z) {
        this.f9047k = z;
    }

    /* renamed from: p */
    public boolean m10373p() {
        return this.f9047k;
    }

    public void setBorderWidth(float f) {
        this.f9044h.setStrokeWidth(C3283i.m15928a(f));
    }

    public void setBorderColor(int i) {
        this.f9044h.setColor(i);
    }

    public float getMinOffset() {
        return this.f9048l;
    }

    public void setMinOffset(float f) {
        this.f9048l = f;
    }

    public void setKeepPositionOnRotation(boolean z) {
        this.f9049m = z;
    }

    /* renamed from: b */
    public C3227b m10359b(float f, float f2) {
        C3213d a = a(f, f2);
        if (a != null) {
            return (C3227b) ((C3225c) this.C).mo3993a(a.m15431f());
        }
        return null;
    }

    public float getLowestVisibleX() {
        mo3343a(AxisDependency.LEFT).m15913a(this.Q.m15864f(), this.Q.m15868h(), this.f9061y);
        return (float) Math.max((double) this.H.t, this.f9061y.f14196a);
    }

    public float getHighestVisibleX() {
        mo3343a(AxisDependency.LEFT).m15913a(this.Q.m15866g(), this.Q.m15868h(), this.f9062z);
        return (float) Math.min((double) this.H.s, this.f9062z.f14196a);
    }

    public float getVisibleXRange() {
        return Math.abs(getHighestVisibleX() - getLowestVisibleX());
    }

    public float getScaleX() {
        if (this.Q == null) {
            return 1.0f;
        }
        return this.Q.m15882q();
    }

    public float getScaleY() {
        if (this.Q == null) {
            return 1.0f;
        }
        return this.Q.m15883r();
    }

    /* renamed from: q */
    public boolean m10374q() {
        return this.Q.m15884s();
    }

    public YAxis getAxisLeft() {
        return this.f9051o;
    }

    public YAxis getAxisRight() {
        return this.f9052p;
    }

    /* renamed from: b */
    public YAxis m10358b(AxisDependency axisDependency) {
        if (axisDependency == AxisDependency.LEFT) {
            return this.f9051o;
        }
        return this.f9052p;
    }

    /* renamed from: c */
    public boolean mo3344c(AxisDependency axisDependency) {
        return m10358b(axisDependency).m15391H();
    }

    public void setPinchZoom(boolean z) {
        this.f9040d = z;
    }

    /* renamed from: r */
    public boolean m10375r() {
        return this.f9040d;
    }

    public void setDragOffsetX(float f) {
        this.Q.m15875k(f);
    }

    public void setDragOffsetY(float f) {
        this.Q.m15877l(f);
    }

    /* renamed from: s */
    public boolean m10376s() {
        return this.Q.m15887v();
    }

    public C2005q getRendererXAxis() {
        return this.f9057u;
    }

    public void setXAxisRenderer(C2005q c2005q) {
        this.f9057u = c2005q;
    }

    public C3269t getRendererLeftYAxis() {
        return this.f9053q;
    }

    public void setRendererLeftYAxis(C3269t c3269t) {
        this.f9053q = c3269t;
    }

    public C3269t getRendererRightYAxis() {
        return this.f9054r;
    }

    public void setRendererRightYAxis(C3269t c3269t) {
        this.f9054r = c3269t;
    }

    public float getYChartMax() {
        return Math.max(this.f9051o.s, this.f9052p.s);
    }

    public float getYChartMin() {
        return Math.min(this.f9051o.t, this.f9052p.t);
    }

    /* renamed from: t */
    public boolean m10377t() {
        if (this.f9051o.m15391H() || this.f9052p.m15391H()) {
            return true;
        }
        return false;
    }

    public void setAutoScaleMinMaxEnabled(boolean z) {
        this.f9039c = z;
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        float[] fArr = this.f9036A;
        this.f9036A[1] = 0.0f;
        fArr[0] = 0.0f;
        if (this.f9049m) {
            this.f9036A[0] = this.Q.m15864f();
            this.f9036A[1] = this.Q.m15862e();
            mo3343a(AxisDependency.LEFT).m15925b(this.f9036A);
        }
        super.onSizeChanged(i, i2, i3, i4);
        if (this.f9049m) {
            mo3343a(AxisDependency.LEFT).m15918a(this.f9036A);
            this.Q.m15854a(this.f9036A, (View) this);
            return;
        }
        this.Q.m15848a(this.Q.m15881p(), this, true);
    }
}
