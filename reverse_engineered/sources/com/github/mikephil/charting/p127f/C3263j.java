package com.github.mikephil.charting.p127f;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.drawable.Drawable;
import com.github.mikephil.charting.data.C3238l;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet$Mode;
import com.github.mikephil.charting.p089e.p090b.C1477f;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p128a.C3196g;
import com.github.mikephil.charting.p127f.C3249c.C3251a;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3277d;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3281g;
import com.github.mikephil.charting.p183g.C3283i;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;

/* compiled from: LineChartRenderer */
/* renamed from: com.github.mikephil.charting.f.j */
public class C3263j extends C3262k {
    /* renamed from: a */
    protected C3196g f14114a;
    /* renamed from: b */
    protected Paint f14115b;
    /* renamed from: c */
    protected WeakReference<Bitmap> f14116c;
    /* renamed from: d */
    protected Canvas f14117d;
    /* renamed from: e */
    protected Config f14118e = Config.ARGB_8888;
    /* renamed from: l */
    protected Path f14119l = new Path();
    /* renamed from: m */
    protected Path f14120m = new Path();
    /* renamed from: n */
    protected Path f14121n = new Path();
    /* renamed from: p */
    private float[] f14122p = new float[4];
    /* renamed from: q */
    private HashMap<C3220e, C3261a> f14123q = new HashMap();
    /* renamed from: r */
    private float[] f14124r = new float[2];

    /* compiled from: LineChartRenderer */
    /* renamed from: com.github.mikephil.charting.f.j$a */
    private class C3261a {
        /* renamed from: a */
        final /* synthetic */ C3263j f14111a;
        /* renamed from: b */
        private Path f14112b;
        /* renamed from: c */
        private Bitmap[] f14113c;

        private C3261a(C3263j c3263j) {
            this.f14111a = c3263j;
            this.f14112b = new Path();
        }

        /* renamed from: a */
        protected boolean m15761a(C1477f c1477f) {
            int C = c1477f.C();
            if (this.f14113c == null) {
                this.f14113c = new Bitmap[C];
                return true;
            } else if (this.f14113c.length == C) {
                return false;
            } else {
                this.f14113c = new Bitmap[C];
                return true;
            }
        }

        /* renamed from: a */
        protected void m15760a(C1477f c1477f, boolean z, boolean z2) {
            int C = c1477f.C();
            float c = c1477f.c();
            float d = c1477f.d();
            for (int i = 0; i < C; i++) {
                Bitmap createBitmap = Bitmap.createBitmap((int) (((double) c) * 2.1d), (int) (((double) c) * 2.1d), Config.ARGB_4444);
                Canvas canvas = new Canvas(createBitmap);
                this.f14113c[i] = createBitmap;
                this.f14111a.h.setColor(c1477f.f(i));
                if (z2) {
                    this.f14112b.reset();
                    this.f14112b.addCircle(c, c, c, Direction.CW);
                    this.f14112b.addCircle(c, c, d, Direction.CCW);
                    canvas.drawPath(this.f14112b, this.f14111a.h);
                } else {
                    canvas.drawCircle(c, c, c, this.f14111a.h);
                    if (z) {
                        canvas.drawCircle(c, c, d, this.f14111a.f14115b);
                    }
                }
            }
        }

        /* renamed from: a */
        protected Bitmap m15759a(int i) {
            return this.f14113c[i % this.f14113c.length];
        }
    }

    public C3263j(C3196g c3196g, C3185a c3185a, C3275j c3275j) {
        super(c3185a, c3275j);
        this.f14114a = c3196g;
        this.f14115b = new Paint(1);
        this.f14115b.setStyle(Style.FILL);
        this.f14115b.setColor(-1);
    }

    /* renamed from: a */
    public void mo3994a() {
    }

    /* renamed from: a */
    public void mo3995a(Canvas canvas) {
        int n = (int) this.o.m15879n();
        int m = (int) this.o.m15878m();
        if (!(this.f14116c != null && ((Bitmap) this.f14116c.get()).getWidth() == n && ((Bitmap) this.f14116c.get()).getHeight() == m)) {
            if (n > 0 && m > 0) {
                this.f14116c = new WeakReference(Bitmap.createBitmap(n, m, this.f14118e));
                this.f14117d = new Canvas((Bitmap) this.f14116c.get());
            } else {
                return;
            }
        }
        ((Bitmap) this.f14116c.get()).eraseColor(0);
        for (C1477f c1477f : this.f14114a.getLineData().m15595i()) {
            if (c1477f.z()) {
                m15768a(canvas, c1477f);
            }
        }
        canvas.drawBitmap((Bitmap) this.f14116c.get(), 0.0f, 0.0f, this.h);
    }

    /* renamed from: a */
    protected void m15768a(Canvas canvas, C1477f c1477f) {
        if (c1477f.F() >= 1) {
            this.h.setStrokeWidth(c1477f.R());
            this.h.setPathEffect(c1477f.f());
            switch (c1477f.a()) {
                case CUBIC_BEZIER:
                    m15776b(c1477f);
                    break;
                case HORIZONTAL_BEZIER:
                    m15772a(c1477f);
                    break;
                default:
                    m15775b(canvas, c1477f);
                    break;
            }
            this.h.setPathEffect(null);
        }
    }

    /* renamed from: a */
    protected void m15772a(C1477f c1477f) {
        float a = this.g.m15292a();
        C3281g a2 = this.f14114a.mo3343a(c1477f.A());
        this.f.m15726a(this.f14114a, c1477f);
        this.f14119l.reset();
        if (this.f.f14082c >= 1) {
            Entry g = c1477f.g(this.f.f14080a);
            this.f14119l.moveTo(g.m15450i(), g.mo3912b() * a);
            int i = this.f.f14080a + 1;
            Entry entry = g;
            while (i <= this.f.f14082c + this.f.f14080a) {
                Entry g2 = c1477f.g(i);
                float i2 = ((g2.m15450i() - entry.m15450i()) / 2.0f) + entry.m15450i();
                this.f14119l.cubicTo(i2, entry.mo3912b() * a, i2, g2.mo3912b() * a, g2.m15450i(), g2.mo3912b() * a);
                i++;
                entry = g2;
            }
        }
        if (c1477f.S()) {
            this.f14120m.reset();
            this.f14120m.addPath(this.f14119l);
            m15769a(this.f14117d, c1477f, this.f14120m, a2, this.f);
        }
        this.h.setColor(c1477f.k());
        this.h.setStyle(Style.STROKE);
        a2.m15914a(this.f14119l);
        this.f14117d.drawPath(this.f14119l, this.h);
        this.h.setPathEffect(null);
    }

    /* renamed from: b */
    protected void m15776b(C1477f c1477f) {
        Math.max(0.0f, Math.min(1.0f, this.g.m15293b()));
        float a = this.g.m15292a();
        C3281g a2 = this.f14114a.mo3343a(c1477f.A());
        this.f.m15726a(this.f14114a, c1477f);
        float b = c1477f.b();
        this.f14119l.reset();
        if (this.f.f14082c >= 1) {
            int i = this.f.f14080a + 1;
            int i2 = this.f.f14080a + this.f.f14082c;
            Entry g = c1477f.g(Math.max(i - 2, 0));
            Entry g2 = c1477f.g(Math.max(i - 1, 0));
            if (g2 != null) {
                this.f14119l.moveTo(g2.m15450i(), g2.mo3912b() * a);
                int i3 = this.f.f14080a + 1;
                i = -1;
                Entry entry = g2;
                Entry entry2 = g2;
                g2 = g;
                g = entry2;
                while (i3 <= this.f.f14082c + this.f.f14080a) {
                    int i4;
                    Entry g3 = i == i3 ? entry : c1477f.g(i3);
                    if (i3 + 1 < c1477f.F()) {
                        i4 = i3 + 1;
                    } else {
                        i4 = i3;
                    }
                    Entry g4 = c1477f.g(i4);
                    this.f14119l.cubicTo(((g3.m15450i() - g2.m15450i()) * b) + g.m15450i(), (((g3.mo3912b() - g2.mo3912b()) * b) + g.mo3912b()) * a, g3.m15450i() - ((g4.m15450i() - g.m15450i()) * b), (g3.mo3912b() - ((g4.mo3912b() - g.mo3912b()) * b)) * a, g3.m15450i(), g3.mo3912b() * a);
                    i3++;
                    entry = g4;
                    g2 = g;
                    g = g3;
                    i = i4;
                }
            } else {
                return;
            }
        }
        if (c1477f.S()) {
            this.f14120m.reset();
            this.f14120m.addPath(this.f14119l);
            m15769a(this.f14117d, c1477f, this.f14120m, a2, this.f);
        }
        this.h.setColor(c1477f.k());
        this.h.setStyle(Style.STROKE);
        a2.m15914a(this.f14119l);
        this.f14117d.drawPath(this.f14119l, this.h);
        this.h.setPathEffect(null);
    }

    /* renamed from: a */
    protected void m15769a(Canvas canvas, C1477f c1477f, Path path, C3281g c3281g, C3251a c3251a) {
        float a = c1477f.N().mo3885a(c1477f, this.f14114a);
        path.lineTo(c1477f.g(c3251a.f14080a + c3251a.f14082c).m15450i(), a);
        path.lineTo(c1477f.g(c3251a.f14080a).m15450i(), a);
        path.close();
        c3281g.m15914a(path);
        Drawable P = c1477f.P();
        if (P != null) {
            m15764a(canvas, path, P);
        } else {
            m15763a(canvas, path, c1477f.O(), c1477f.Q());
        }
    }

    /* renamed from: b */
    protected void m15775b(Canvas canvas, C1477f c1477f) {
        Canvas canvas2;
        int F = c1477f.F();
        boolean B = c1477f.B();
        int i = B ? 4 : 2;
        C3281g a = this.f14114a.mo3343a(c1477f.A());
        float a2 = this.g.m15292a();
        this.h.setStyle(Style.STROKE);
        if (c1477f.e()) {
            canvas2 = this.f14117d;
        } else {
            canvas2 = canvas;
        }
        this.f.m15726a(this.f14114a, c1477f);
        if (c1477f.S() && F > 0) {
            m15770a(canvas, c1477f, a, this.f);
        }
        if (c1477f.j().size() > 1) {
            if (this.f14122p.length <= i * 2) {
                this.f14122p = new float[(i * 4)];
            }
            for (F = this.f.f14080a; F <= this.f.f14082c + this.f.f14080a; F++) {
                Entry g = c1477f.g(F);
                if (g != null) {
                    this.f14122p[0] = g.m15450i();
                    this.f14122p[1] = g.mo3912b() * a2;
                    if (F < this.f.f14081b) {
                        g = c1477f.g(F + 1);
                        if (g == null) {
                            break;
                        } else if (B) {
                            this.f14122p[2] = g.m15450i();
                            this.f14122p[3] = this.f14122p[1];
                            this.f14122p[4] = this.f14122p[2];
                            this.f14122p[5] = this.f14122p[3];
                            this.f14122p[6] = g.m15450i();
                            this.f14122p[7] = g.mo3912b() * a2;
                        } else {
                            this.f14122p[2] = g.m15450i();
                            this.f14122p[3] = g.mo3912b() * a2;
                        }
                    } else {
                        this.f14122p[2] = this.f14122p[0];
                        this.f14122p[3] = this.f14122p[1];
                    }
                    a.m15918a(this.f14122p);
                    if (!this.o.m15869h(this.f14122p[0])) {
                        break;
                    } else if (this.o.m15867g(this.f14122p[2]) && (this.o.m15871i(this.f14122p[1]) || this.o.m15873j(this.f14122p[3]))) {
                        this.h.setColor(c1477f.b(F));
                        canvas2.drawLines(this.f14122p, 0, i * 2, this.h);
                    }
                }
            }
        } else {
            if (this.f14122p.length < Math.max(F * i, i) * 2) {
                this.f14122p = new float[(Math.max(F * i, i) * 4)];
            }
            if (c1477f.g(this.f.f14080a) != null) {
                int i2 = 0;
                F = this.f.f14080a;
                while (F <= this.f.f14082c + this.f.f14080a) {
                    Entry g2 = c1477f.g(F == 0 ? 0 : F - 1);
                    Entry g3 = c1477f.g(F);
                    if (!(g2 == null || g3 == null)) {
                        int i3;
                        int i4 = i2 + 1;
                        this.f14122p[i2] = g2.m15450i();
                        i2 = i4 + 1;
                        this.f14122p[i4] = g2.mo3912b() * a2;
                        if (B) {
                            i4 = i2 + 1;
                            this.f14122p[i2] = g3.m15450i();
                            i3 = i4 + 1;
                            this.f14122p[i4] = g2.mo3912b() * a2;
                            i4 = i3 + 1;
                            this.f14122p[i3] = g3.m15450i();
                            i2 = i4 + 1;
                            this.f14122p[i4] = g2.mo3912b() * a2;
                        }
                        i3 = i2 + 1;
                        this.f14122p[i2] = g3.m15450i();
                        i2 = i3 + 1;
                        this.f14122p[i3] = g3.mo3912b() * a2;
                    }
                    F++;
                }
                if (i2 > 0) {
                    a.m15918a(this.f14122p);
                    i = Math.max((this.f.f14082c + 1) * i, i) * 2;
                    this.h.setColor(c1477f.k());
                    canvas2.drawLines(this.f14122p, 0, i, this.h);
                }
            }
        }
        this.h.setPathEffect(null);
    }

    /* renamed from: a */
    protected void m15770a(Canvas canvas, C1477f c1477f, C3281g c3281g, C3251a c3251a) {
        Path path = this.f14121n;
        int i = c3251a.f14080a;
        int i2 = c3251a.f14080a + c3251a.f14082c;
        int i3 = 0;
        int i4;
        int i5;
        do {
            i4 = i + (i3 * 128);
            i5 = i4 + 128;
            if (i5 > i2) {
                i5 = i2;
            }
            if (i4 <= i5) {
                m15765a(c1477f, i4, i5, path);
                c3281g.m15914a(path);
                Drawable P = c1477f.P();
                if (P != null) {
                    m15764a(canvas, path, P);
                } else {
                    m15763a(canvas, path, c1477f.O(), c1477f.Q());
                }
            }
            i3++;
        } while (i4 <= i5);
    }

    /* renamed from: a */
    private void m15765a(C1477f c1477f, int i, int i2, Path path) {
        Entry entry = null;
        float a = c1477f.N().mo3885a(c1477f, this.f14114a);
        float a2 = this.g.m15292a();
        Object obj = c1477f.a() == LineDataSet$Mode.STEPPED ? 1 : null;
        path.reset();
        Entry g = c1477f.g(i);
        path.moveTo(g.m15450i(), a);
        path.lineTo(g.m15450i(), g.mo3912b() * a2);
        int i3 = i + 1;
        Entry entry2 = null;
        while (i3 <= i2) {
            entry2 = c1477f.g(i3);
            if (!(obj == null || entry == null)) {
                path.lineTo(entry2.m15450i(), entry.mo3912b() * a2);
            }
            path.lineTo(entry2.m15450i(), entry2.mo3912b() * a2);
            i3++;
            entry = entry2;
        }
        if (entry2 != null) {
            path.lineTo(entry2.m15450i(), a);
        }
        path.close();
    }

    /* renamed from: b */
    public void mo3997b(Canvas canvas) {
        if (mo4002a(this.f14114a)) {
            List i = this.f14114a.getLineData().m15595i();
            for (int i2 = 0; i2 < i.size(); i2++) {
                C1477f c1477f = (C1477f) i.get(i2);
                if (m15717a(c1477f)) {
                    int i3;
                    m15714b((C3220e) c1477f);
                    C3281g a = this.f14114a.mo3343a(c1477f.A());
                    int c = (int) (c1477f.c() * 1.75f);
                    if (c1477f.g()) {
                        i3 = c;
                    } else {
                        i3 = c / 2;
                    }
                    this.f.m15726a(this.f14114a, c1477f);
                    float[] a2 = a.m15921a(c1477f, this.g.m15293b(), this.g.m15292a(), this.f.f14080a, this.f.f14081b);
                    C3279e a3 = C3279e.m15898a(c1477f.y());
                    a3.f14200a = C3283i.m15928a(a3.f14200a);
                    a3.f14201b = C3283i.m15928a(a3.f14201b);
                    for (int i4 = 0; i4 < a2.length; i4 += 2) {
                        float f = a2[i4];
                        float f2 = a2[i4 + 1];
                        if (!this.o.m15869h(f)) {
                            break;
                        }
                        if (this.o.m15867g(f) && this.o.m15865f(f2)) {
                            Entry g = c1477f.g((i4 / 2) + this.f.f14080a);
                            if (c1477f.w()) {
                                m15710a(canvas, c1477f.o(), g.mo3912b(), g, i2, f, f2 - ((float) i3), c1477f.e(i4 / 2));
                            }
                            if (g.m15448g() != null && c1477f.x()) {
                                Drawable g2 = g.m15448g();
                                C3283i.m15935a(canvas, g2, (int) (a3.f14200a + f), (int) (a3.f14201b + f2), g2.getIntrinsicWidth(), g2.getIntrinsicHeight());
                            }
                        }
                    }
                    C3279e.m15900b(a3);
                }
            }
        }
    }

    /* renamed from: c */
    public void mo3998c(Canvas canvas) {
        m15778d(canvas);
    }

    /* renamed from: d */
    protected void m15778d(Canvas canvas) {
        this.h.setStyle(Style.FILL);
        float a = this.g.m15292a();
        this.f14124r[0] = 0.0f;
        this.f14124r[1] = 0.0f;
        List i = this.f14114a.getLineData().m15595i();
        for (int i2 = 0; i2 < i.size(); i2++) {
            C1477f c1477f = (C1477f) i.get(i2);
            if (c1477f.z() && c1477f.g() && c1477f.F() != 0) {
                C3261a c3261a;
                this.f14115b.setColor(c1477f.L());
                C3281g a2 = this.f14114a.mo3343a(c1477f.A());
                this.f.m15726a(this.f14114a, c1477f);
                float c = c1477f.c();
                float d = c1477f.d();
                boolean z = c1477f.M() && d < c && d > 0.0f;
                boolean z2 = z && c1477f.L() == 1122867;
                if (this.f14123q.containsKey(c1477f)) {
                    c3261a = (C3261a) this.f14123q.get(c1477f);
                } else {
                    c3261a = new C3261a();
                    this.f14123q.put(c1477f, c3261a);
                }
                if (c3261a.m15761a(c1477f)) {
                    c3261a.m15760a(c1477f, z, z2);
                }
                int i3 = this.f.f14080a + this.f.f14082c;
                for (int i4 = this.f.f14080a; i4 <= i3; i4++) {
                    Entry g = c1477f.g(i4);
                    if (g == null) {
                        break;
                    }
                    this.f14124r[0] = g.m15450i();
                    this.f14124r[1] = g.mo3912b() * a;
                    a2.m15918a(this.f14124r);
                    if (!this.o.m15869h(this.f14124r[0])) {
                        break;
                    }
                    if (this.o.m15867g(this.f14124r[0]) && this.o.m15865f(this.f14124r[1])) {
                        Bitmap a3 = c3261a.m15759a(i4);
                        if (a3 != null) {
                            canvas.drawBitmap(a3, this.f14124r[0] - c, this.f14124r[1] - c, null);
                        }
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public void mo3996a(Canvas canvas, C3213d[] c3213dArr) {
        C3238l lineData = this.f14114a.getLineData();
        for (C3213d c3213d : c3213dArr) {
            C1477f c1477f = (C1477f) lineData.mo3993a(c3213d.m15431f());
            if (c1477f != null && c1477f.n()) {
                Entry b = c1477f.b(c3213d.m15423a(), c3213d.m15427b());
                if (m15716a(b, c1477f)) {
                    C3277d b2 = this.f14114a.mo3343a(c1477f.A()).m15923b(b.m15450i(), b.mo3912b() * this.g.m15292a());
                    c3213d.m15424a((float) b2.f14196a, (float) b2.f14197b);
                    m15734a(canvas, (float) b2.f14196a, (float) b2.f14197b, c1477f);
                }
            }
        }
    }

    /* renamed from: b */
    public void mo4003b() {
        if (this.f14117d != null) {
            this.f14117d.setBitmap(null);
            this.f14117d = null;
        }
        if (this.f14116c != null) {
            ((Bitmap) this.f14116c.get()).recycle();
            this.f14116c.clear();
            this.f14116c = null;
        }
    }
}
