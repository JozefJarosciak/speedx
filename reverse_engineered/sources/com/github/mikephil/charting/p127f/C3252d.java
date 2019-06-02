package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import com.alipay.sdk.cons.C0844a;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.C3231g;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3244c;
import com.github.mikephil.charting.p089e.p128a.C3195c;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3281g;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

/* compiled from: BubbleChartRenderer */
/* renamed from: com.github.mikephil.charting.f.d */
public class C3252d extends C3249c {
    /* renamed from: a */
    protected C3195c f14084a;
    /* renamed from: b */
    private float[] f14085b = new float[4];
    /* renamed from: c */
    private float[] f14086c = new float[2];
    /* renamed from: d */
    private float[] f14087d = new float[3];

    public C3252d(C3195c c3195c, C3185a c3185a, C3275j c3275j) {
        super(c3185a, c3275j);
        this.f14084a = c3195c;
        this.h.setStyle(Style.FILL);
        this.i.setStyle(Style.STROKE);
        this.i.setStrokeWidth(C3283i.m15928a(1.5f));
    }

    /* renamed from: a */
    public void mo3994a() {
    }

    /* renamed from: a */
    public void mo3995a(Canvas canvas) {
        for (C3244c c3244c : this.f14084a.getBubbleData().m15595i()) {
            if (c3244c.mo3936z()) {
                m15730a(canvas, c3244c);
            }
        }
    }

    /* renamed from: a */
    protected float m15727a(float f, float f2, float f3, boolean z) {
        if (z) {
            f = f2 == 0.0f ? 1.0f : (float) Math.sqrt((double) (f / f2));
        }
        return f3 * f;
    }

    /* renamed from: a */
    protected void m15730a(Canvas canvas, C3244c c3244c) {
        C3281g a = this.f14084a.mo3343a(c3244c.mo3913A());
        float a2 = this.g.m15292a();
        this.f.m15726a(this.f14084a, c3244c);
        this.f14085b[0] = 0.0f;
        this.f14085b[2] = 1.0f;
        a.m15918a(this.f14085b);
        boolean b = c3244c.m15696b();
        float min = Math.min(Math.abs(this.o.m15868h() - this.o.m15862e()), Math.abs(this.f14085b[2] - this.f14085b[0]));
        for (int i = this.f.f14080a; i <= this.f.f14082c + this.f.f14080a; i++) {
            BubbleEntry bubbleEntry = (BubbleEntry) c3244c.mo3948g(i);
            this.f14086c[0] = bubbleEntry.m15450i();
            this.f14086c[1] = bubbleEntry.mo3912b() * a2;
            a.m15918a(this.f14086c);
            float a3 = m15727a(bubbleEntry.m15457a(), c3244c.m15695a(), min, b) / 2.0f;
            if (this.o.m15871i(this.f14086c[1] + a3) && this.o.m15873j(this.f14086c[1] - a3) && this.o.m15867g(this.f14086c[0] + a3)) {
                if (this.o.m15869h(this.f14086c[0] - a3)) {
                    this.h.setColor(c3244c.mo3917b((int) bubbleEntry.m15450i()));
                    canvas.drawCircle(this.f14086c[0], this.f14086c[1], a3, this.h);
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: b */
    public void mo3997b(Canvas canvas) {
        C3231g bubbleData = this.f14084a.getBubbleData();
        if (bubbleData != null) {
            if (mo4002a(this.f14084a)) {
                List i = bubbleData.m15595i();
                float b = (float) C3283i.m15946b(this.k, C0844a.f2048d);
                for (int i2 = 0; i2 < i.size(); i2++) {
                    C3244c c3244c = (C3244c) i.get(i2);
                    if (m15717a(c3244c)) {
                        m15714b((C3220e) c3244c);
                        float max = Math.max(0.0f, Math.min(1.0f, this.g.m15293b()));
                        float a = this.g.m15292a();
                        this.f.m15726a(this.f14084a, c3244c);
                        float[] a2 = this.f14084a.mo3343a(c3244c.mo3913A()).m15919a(c3244c, a, this.f.f14080a, this.f.f14081b);
                        float f = max == 1.0f ? a : max;
                        C3279e a3 = C3279e.m15898a(c3244c.mo3935y());
                        a3.f14200a = C3283i.m15928a(a3.f14200a);
                        a3.f14201b = C3283i.m15928a(a3.f14201b);
                        for (int i3 = 0; i3 < a2.length; i3 += 2) {
                            int e = c3244c.mo3920e((i3 / 2) + this.f.f14080a);
                            int argb = Color.argb(Math.round(255.0f * f), Color.red(e), Color.green(e), Color.blue(e));
                            float f2 = a2[i3];
                            float f3 = a2[i3 + 1];
                            if (!this.o.m15869h(f2)) {
                                break;
                            }
                            if (this.o.m15867g(f2) && this.o.m15865f(f3)) {
                                BubbleEntry bubbleEntry = (BubbleEntry) c3244c.mo3948g((i3 / 2) + this.f.f14080a);
                                if (c3244c.mo3933w()) {
                                    m15710a(canvas, c3244c.mo3925o(), bubbleEntry.m15457a(), bubbleEntry, i2, f2, f3 + (0.5f * b), argb);
                                }
                                if (bubbleEntry.m15448g() != null && c3244c.mo3934x()) {
                                    Drawable g = bubbleEntry.m15448g();
                                    C3283i.m15935a(canvas, g, (int) (a3.f14200a + f2), (int) (a3.f14201b + f3), g.getIntrinsicWidth(), g.getIntrinsicHeight());
                                }
                            }
                        }
                        C3279e.m15900b(a3);
                    }
                }
            }
        }
    }

    /* renamed from: c */
    public void mo3998c(Canvas canvas) {
    }

    /* renamed from: a */
    public void mo3996a(Canvas canvas, C3213d[] c3213dArr) {
        C3231g bubbleData = this.f14084a.getBubbleData();
        float a = this.g.m15292a();
        for (C3213d c3213d : c3213dArr) {
            C3244c c3244c = (C3244c) bubbleData.mo3993a(c3213d.m15431f());
            if (c3244c != null && c3244c.mo3924n()) {
                BubbleEntry bubbleEntry = (BubbleEntry) c3244c.mo3945b(c3213d.m15423a(), c3213d.m15427b());
                if (bubbleEntry.mo3912b() == c3213d.m15427b() && m15716a(bubbleEntry, c3244c)) {
                    C3281g a2 = this.f14084a.mo3343a(c3244c.mo3913A());
                    this.f14085b[0] = 0.0f;
                    this.f14085b[2] = 1.0f;
                    a2.m15918a(this.f14085b);
                    boolean b = c3244c.m15696b();
                    float min = Math.min(Math.abs(this.o.m15868h() - this.o.m15862e()), Math.abs(this.f14085b[2] - this.f14085b[0]));
                    this.f14086c[0] = bubbleEntry.m15450i();
                    this.f14086c[1] = bubbleEntry.mo3912b() * a;
                    a2.m15918a(this.f14086c);
                    c3213d.m15424a(this.f14086c[0], this.f14086c[1]);
                    float a3 = m15727a(bubbleEntry.m15457a(), c3244c.m15695a(), min, b) / 2.0f;
                    if (this.o.m15871i(this.f14086c[1] + a3) && this.o.m15873j(this.f14086c[1] - a3) && this.o.m15867g(this.f14086c[0] + a3)) {
                        if (this.o.m15869h(this.f14086c[0] - a3)) {
                            int b2 = c3244c.mo3917b((int) bubbleEntry.m15450i());
                            Color.RGBToHSV(Color.red(b2), Color.green(b2), Color.blue(b2), this.f14087d);
                            float[] fArr = this.f14087d;
                            fArr[2] = fArr[2] * 0.5f;
                            this.i.setColor(Color.HSVToColor(Color.alpha(b2), this.f14087d));
                            this.i.setStrokeWidth(c3244c.m15697c());
                            canvas.drawCircle(this.f14086c[0], this.f14086c[1], a3, this.i);
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }
}
