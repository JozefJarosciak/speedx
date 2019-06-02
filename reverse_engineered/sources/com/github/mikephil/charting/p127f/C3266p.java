package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.github.mikephil.charting.data.C3243q;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3246k;
import com.github.mikephil.charting.p089e.p128a.C3197h;
import com.github.mikephil.charting.p127f.p182a.C3247a;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3277d;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3281g;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

/* compiled from: ScatterChartRenderer */
/* renamed from: com.github.mikephil.charting.f.p */
public class C3266p extends C3253l {
    /* renamed from: a */
    protected C3197h f14147a;
    /* renamed from: b */
    float[] f14148b = new float[2];

    public C3266p(C3197h c3197h, C3185a c3185a, C3275j c3275j) {
        super(c3185a, c3275j);
        this.f14147a = c3197h;
    }

    /* renamed from: a */
    public void mo3994a() {
    }

    /* renamed from: a */
    public void mo3995a(Canvas canvas) {
        for (C3246k c3246k : this.f14147a.getScatterData().m15595i()) {
            if (c3246k.mo3936z()) {
                m15805a(canvas, c3246k);
            }
        }
    }

    /* renamed from: a */
    protected void m15805a(Canvas canvas, C3246k c3246k) {
        C3275j c3275j = this.o;
        C3281g a = this.f14147a.mo3343a(c3246k.mo3913A());
        float a2 = this.g.m15292a();
        C3247a b = c3246k.m15706b();
        if (b == null) {
            Log.i("MISSING", "There's no IShapeRenderer specified for ScatterDataSet");
            return;
        }
        int min = (int) Math.min(Math.ceil((double) (((float) c3246k.mo3938F()) * this.g.m15293b())), (double) ((float) c3246k.mo3938F()));
        int i = 0;
        while (i < min) {
            Entry g = c3246k.mo3948g(i);
            this.f14148b[0] = g.m15450i();
            this.f14148b[1] = g.mo3912b() * a2;
            a.m15918a(this.f14148b);
            if (c3275j.m15869h(this.f14148b[0])) {
                if (c3275j.m15867g(this.f14148b[0]) && c3275j.m15865f(this.f14148b[1])) {
                    this.h.setColor(c3246k.mo3917b(i / 2));
                    b.m15707a(canvas, c3246k, this.o, this.f14148b[0], this.f14148b[1], this.h);
                }
                i++;
            } else {
                return;
            }
        }
    }

    /* renamed from: b */
    public void mo3997b(Canvas canvas) {
        if (mo4002a(this.f14147a)) {
            List i = this.f14147a.getScatterData().m15595i();
            for (int i2 = 0; i2 < this.f14147a.getScatterData().m15590d(); i2++) {
                C3246k c3246k = (C3246k) i.get(i2);
                if (m15717a(c3246k)) {
                    m15714b((C3220e) c3246k);
                    this.f.m15726a(this.f14147a, c3246k);
                    float[] a = this.f14147a.mo3343a(c3246k.mo3913A()).m15922a(c3246k, this.g.m15293b(), this.g.m15292a(), this.f.f14080a, this.f.f14081b);
                    float a2 = C3283i.m15928a(c3246k.m15705a());
                    C3279e a3 = C3279e.m15898a(c3246k.mo3935y());
                    a3.f14200a = C3283i.m15928a(a3.f14200a);
                    a3.f14201b = C3283i.m15928a(a3.f14201b);
                    int i3 = 0;
                    while (i3 < a.length && this.o.m15869h(a[i3])) {
                        if (this.o.m15867g(a[i3]) && this.o.m15865f(a[i3 + 1])) {
                            Entry g = c3246k.mo3948g((i3 / 2) + this.f.f14080a);
                            if (c3246k.mo3933w()) {
                                m15710a(canvas, c3246k.mo3925o(), g.mo3912b(), g, i2, a[i3], a[i3 + 1] - a2, c3246k.mo3920e((i3 / 2) + this.f.f14080a));
                            }
                            if (g.m15448g() != null && c3246k.mo3934x()) {
                                Drawable g2 = g.m15448g();
                                C3283i.m15935a(canvas, g2, (int) (a[i3] + a3.f14200a), (int) (a[i3 + 1] + a3.f14201b), g2.getIntrinsicWidth(), g2.getIntrinsicHeight());
                            }
                        }
                        i3 += 2;
                    }
                    C3279e.m15900b(a3);
                }
            }
        }
    }

    /* renamed from: c */
    public void mo3998c(Canvas canvas) {
    }

    /* renamed from: a */
    public void mo3996a(Canvas canvas, C3213d[] c3213dArr) {
        C3243q scatterData = this.f14147a.getScatterData();
        for (C3213d c3213d : c3213dArr) {
            C3246k c3246k = (C3246k) scatterData.mo3993a(c3213d.m15431f());
            if (c3246k != null && c3246k.mo3924n()) {
                Entry b = c3246k.mo3945b(c3213d.m15423a(), c3213d.m15427b());
                if (m15716a(b, c3246k)) {
                    C3277d b2 = this.f14147a.mo3343a(c3246k.mo3913A()).m15923b(b.m15450i(), b.mo3912b() * this.g.m15292a());
                    c3213d.m15424a((float) b2.f14196a, (float) b2.f14197b);
                    m15734a(canvas, (float) b2.f14196a, (float) b2.f14197b, c3246k);
                }
            }
        }
    }
}
