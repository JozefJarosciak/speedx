package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Typeface;
import com.github.mikephil.charting.components.C3208e;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.data.C3224j;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p090b.C3223i;
import com.github.mikephil.charting.p089e.p090b.C3229a;
import com.github.mikephil.charting.p089e.p090b.C3235d;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: LegendRenderer */
/* renamed from: com.github.mikephil.charting.f.i */
public class C3259i extends C2003o {
    /* renamed from: a */
    protected Paint f14104a;
    /* renamed from: b */
    protected Paint f14105b;
    /* renamed from: c */
    protected Legend f14106c;
    /* renamed from: d */
    protected List<C3208e> f14107d = new ArrayList(16);
    /* renamed from: e */
    protected FontMetrics f14108e = new FontMetrics();
    /* renamed from: f */
    private Path f14109f = new Path();

    public C3259i(C3275j c3275j, Legend legend) {
        super(c3275j);
        this.f14106c = legend;
        this.f14104a = new Paint(1);
        this.f14104a.setTextSize(C3283i.m15928a(9.0f));
        this.f14104a.setTextAlign(Align.LEFT);
        this.f14105b = new Paint(1);
        this.f14105b.setStyle(Style.FILL);
    }

    /* renamed from: a */
    public Paint m15754a() {
        return this.f14104a;
    }

    /* renamed from: a */
    public void m15758a(C3224j<?> c3224j) {
        if (!this.f14106c.m15352c()) {
            this.f14107d.clear();
            for (int i = 0; i < c3224j.m15590d(); i++) {
                C3220e a = c3224j.mo3993a(i);
                List j = a.mo3921j();
                int F = a.mo3938F();
                int i2;
                if ((a instanceof C3229a) && ((C3229a) a).mo3963b()) {
                    C3229a c3229a = (C3229a) a;
                    String[] g = c3229a.mo3968g();
                    i2 = 0;
                    while (i2 < j.size() && i2 < c3229a.mo3962a()) {
                        this.f14107d.add(new C3208e(g[i2 % g.length], a.mo3929s(), a.mo3930t(), a.mo3931u(), a.mo3932v(), ((Integer) j.get(i2)).intValue()));
                        i2++;
                    }
                    if (c3229a.mo3923m() != null) {
                        this.f14107d.add(new C3208e(a.mo3923m(), LegendForm.NONE, Float.NaN, Float.NaN, null, 1122867));
                    }
                } else if (a instanceof C3223i) {
                    C3223i c3223i = (C3223i) a;
                    i2 = 0;
                    while (i2 < j.size() && i2 < F) {
                        this.f14107d.add(new C3208e(((PieEntry) c3223i.mo3948g(i2)).a(), a.mo3929s(), a.mo3930t(), a.mo3931u(), a.mo3932v(), ((Integer) j.get(i2)).intValue()));
                        i2++;
                    }
                    if (c3223i.mo3923m() != null) {
                        this.f14107d.add(new C3208e(a.mo3923m(), LegendForm.NONE, Float.NaN, Float.NaN, null, 1122867));
                    }
                } else if (!(a instanceof C3235d) || ((C3235d) a).mo3982f() == 1122867) {
                    r8 = 0;
                    while (r8 < j.size() && r8 < F) {
                        String m;
                        if (r8 >= j.size() - 1 || r8 >= F - 1) {
                            m = c3224j.mo3993a(i).mo3923m();
                        } else {
                            m = null;
                        }
                        this.f14107d.add(new C3208e(m, a.mo3929s(), a.mo3930t(), a.mo3931u(), a.mo3932v(), ((Integer) j.get(r8)).intValue()));
                        r8++;
                    }
                } else {
                    int f = ((C3235d) a).mo3982f();
                    r8 = ((C3235d) a).mo3981e();
                    this.f14107d.add(new C3208e(null, a.mo3929s(), a.mo3930t(), a.mo3931u(), a.mo3932v(), f));
                    this.f14107d.add(new C3208e(a.mo3923m(), a.mo3929s(), a.mo3930t(), a.mo3931u(), a.mo3932v(), r8));
                }
            }
            if (this.f14106c.m15351b() != null) {
                Collections.addAll(this.f14107d, this.f14106c.m15351b());
            }
            this.f14106c.m15348a(this.f14107d);
        }
        Typeface x = this.f14106c.m15343x();
        if (x != null) {
            this.f14104a.setTypeface(x);
        }
        this.f14104a.setTextSize(this.f14106c.m15344y());
        this.f14104a.setColor(this.f14106c.m15345z());
        this.f14106c.m15347a(this.f14104a, this.o);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public void m15755a(android.graphics.Canvas r30) {
        /*
        r29 = this;
        r0 = r29;
        r2 = r0.f14106c;
        r2 = r2.m15336A();
        if (r2 != 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r0 = r29;
        r2 = r0.f14106c;
        r2 = r2.m15343x();
        if (r2 == 0) goto L_0x001c;
    L_0x0015:
        r0 = r29;
        r3 = r0.f14104a;
        r3.setTypeface(r2);
    L_0x001c:
        r0 = r29;
        r2 = r0.f14104a;
        r0 = r29;
        r3 = r0.f14106c;
        r3 = r3.m15344y();
        r2.setTextSize(r3);
        r0 = r29;
        r2 = r0.f14104a;
        r0 = r29;
        r3 = r0.f14106c;
        r3 = r3.m15345z();
        r2.setColor(r3);
        r0 = r29;
        r2 = r0.f14104a;
        r0 = r29;
        r3 = r0.f14108e;
        r19 = com.github.mikephil.charting.p183g.C3283i.m15930a(r2, r3);
        r0 = r29;
        r2 = r0.f14104a;
        r0 = r29;
        r3 = r0.f14108e;
        r2 = com.github.mikephil.charting.p183g.C3283i.m15943b(r2, r3);
        r0 = r29;
        r3 = r0.f14106c;
        r3 = r3.m15363n();
        r3 = com.github.mikephil.charting.p183g.C3283i.m15928a(r3);
        r20 = r2 + r3;
        r0 = r29;
        r2 = r0.f14104a;
        r3 = "ABC";
        r2 = com.github.mikephil.charting.p183g.C3283i.m15946b(r2, r3);
        r2 = (float) r2;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r3;
        r21 = r19 - r2;
        r0 = r29;
        r2 = r0.f14106c;
        r22 = r2.m15349a();
        r0 = r29;
        r2 = r0.f14106c;
        r2 = r2.m15364o();
        r13 = com.github.mikephil.charting.p183g.C3283i.m15928a(r2);
        r0 = r29;
        r2 = r0.f14106c;
        r2 = r2.m15362m();
        r14 = com.github.mikephil.charting.p183g.C3283i.m15928a(r2);
        r0 = r29;
        r2 = r0.f14106c;
        r6 = r2.m15355f();
        r0 = r29;
        r2 = r0.f14106c;
        r23 = r2.m15353d();
        r0 = r29;
        r2 = r0.f14106c;
        r7 = r2.m15354e();
        r0 = r29;
        r2 = r0.f14106c;
        r24 = r2.m15357h();
        r0 = r29;
        r2 = r0.f14106c;
        r2 = r2.m15359j();
        r11 = com.github.mikephil.charting.p183g.C3283i.m15928a(r2);
        r0 = r29;
        r2 = r0.f14106c;
        r2 = r2.m15365p();
        r15 = com.github.mikephil.charting.p183g.C3283i.m15928a(r2);
        r0 = r29;
        r2 = r0.f14106c;
        r5 = r2.m15342w();
        r0 = r29;
        r2 = r0.f14106c;
        r2 = r2.m15341v();
        r3 = 0;
        r4 = com.github.mikephil.charting.p127f.C3259i.C32581.f14100a;
        r8 = r23.ordinal();
        r4 = r4[r8];
        switch(r4) {
            case 1: goto L_0x01f7;
            case 2: goto L_0x0215;
            case 3: goto L_0x023e;
            default: goto L_0x00e4;
        };
    L_0x00e4:
        r8 = r3;
    L_0x00e5:
        r2 = com.github.mikephil.charting.p127f.C3259i.C32581.f14102c;
        r3 = r6.ordinal();
        r2 = r2[r3];
        switch(r2) {
            case 1: goto L_0x00f2;
            case 2: goto L_0x02f9;
            default: goto L_0x00f0;
        };
    L_0x00f0:
        goto L_0x000a;
    L_0x00f2:
        r0 = r29;
        r2 = r0.f14106c;
        r25 = r2.m15369t();
        r0 = r29;
        r2 = r0.f14106c;
        r26 = r2.m15367r();
        r0 = r29;
        r2 = r0.f14106c;
        r27 = r2.m15368s();
        r2 = 0;
        r3 = com.github.mikephil.charting.p127f.C3259i.C32581.f14101b;
        r4 = r7.ordinal();
        r3 = r3[r4];
        switch(r3) {
            case 1: goto L_0x029e;
            case 2: goto L_0x02a1;
            case 3: goto L_0x02b3;
            default: goto L_0x0116;
        };
    L_0x0116:
        r12 = 0;
        r3 = 0;
        r0 = r22;
        r0 = r0.length;
        r28 = r0;
        r18 = r3;
        r4 = r2;
        r5 = r8;
        r3 = r12;
    L_0x0122:
        r0 = r18;
        r1 = r28;
        if (r0 >= r1) goto L_0x000a;
    L_0x0128:
        r6 = r22[r18];
        r2 = r6.f13959b;
        r7 = com.github.mikephil.charting.components.Legend.LegendForm.NONE;
        if (r2 == r7) goto L_0x02c8;
    L_0x0130:
        r2 = 1;
        r9 = r2;
    L_0x0132:
        r2 = r6.f13960c;
        r2 = java.lang.Float.isNaN(r2);
        if (r2 == 0) goto L_0x02cc;
    L_0x013a:
        r10 = r11;
    L_0x013b:
        r2 = r27.size();
        r0 = r18;
        if (r0 >= r2) goto L_0x041a;
    L_0x0143:
        r0 = r27;
        r1 = r18;
        r2 = r0.get(r1);
        r2 = (java.lang.Boolean) r2;
        r2 = r2.booleanValue();
        if (r2 == 0) goto L_0x041a;
    L_0x0153:
        r2 = r19 + r20;
        r2 = r2 + r4;
        r17 = r2;
        r4 = r8;
    L_0x0159:
        r2 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r2 != 0) goto L_0x0416;
    L_0x015d:
        r2 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER;
        r0 = r23;
        if (r0 != r2) goto L_0x0416;
    L_0x0163:
        r2 = r25.size();
        if (r3 >= r2) goto L_0x0416;
    L_0x0169:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r2) goto L_0x02d5;
    L_0x016f:
        r0 = r25;
        r2 = r0.get(r3);
        r2 = (com.github.mikephil.charting.p183g.C3274b) r2;
        r2 = r2.f14176a;
    L_0x0179:
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r5;
        r4 = r4 + r2;
        r2 = r3 + 1;
        r12 = r2;
        r2 = r4;
    L_0x0181:
        r3 = r6.f13958a;
        if (r3 != 0) goto L_0x02e2;
    L_0x0185:
        r3 = 1;
        r16 = r3;
    L_0x0188:
        if (r9 == 0) goto L_0x0413;
    L_0x018a:
        r3 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r3) goto L_0x0410;
    L_0x0190:
        r4 = r2 - r10;
    L_0x0192:
        r5 = r17 + r21;
        r0 = r29;
        r7 = r0.f14106c;
        r2 = r29;
        r3 = r30;
        r2.m15756a(r3, r4, r5, r6, r7);
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r2) goto L_0x040d;
    L_0x01a5:
        r3 = r4 + r10;
    L_0x01a7:
        if (r16 != 0) goto L_0x02ed;
    L_0x01a9:
        if (r9 == 0) goto L_0x01b4;
    L_0x01ab:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r2) goto L_0x02e7;
    L_0x01b1:
        r2 = -r13;
    L_0x01b2:
        r2 = r2 + r3;
        r3 = r2;
    L_0x01b4:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r2) goto L_0x01c7;
    L_0x01ba:
        r0 = r26;
        r1 = r18;
        r2 = r0.get(r1);
        r2 = (com.github.mikephil.charting.p183g.C3274b) r2;
        r2 = r2.f14176a;
        r3 = r3 - r2;
    L_0x01c7:
        r2 = r17 + r19;
        r4 = r6.f13958a;
        r0 = r29;
        r1 = r30;
        r0.m15757a(r1, r3, r2, r4);
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r2) goto L_0x01e5;
    L_0x01d8:
        r0 = r26;
        r1 = r18;
        r2 = r0.get(r1);
        r2 = (com.github.mikephil.charting.p183g.C3274b) r2;
        r2 = r2.f14176a;
        r3 = r3 + r2;
    L_0x01e5:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r2) goto L_0x02ea;
    L_0x01eb:
        r2 = -r14;
    L_0x01ec:
        r2 = r2 + r3;
    L_0x01ed:
        r3 = r18 + 1;
        r18 = r3;
        r4 = r17;
        r5 = r2;
        r3 = r12;
        goto L_0x0122;
    L_0x01f7:
        r3 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL;
        if (r6 != r3) goto L_0x020b;
    L_0x01fb:
        r3 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r3) goto L_0x041f;
    L_0x0201:
        r0 = r29;
        r3 = r0.f14106c;
        r3 = r3.f13908a;
        r2 = r2 + r3;
        r8 = r2;
        goto L_0x00e5;
    L_0x020b:
        r0 = r29;
        r3 = r0.o;
        r3 = r3.m15864f();
        r2 = r2 + r3;
        goto L_0x01fb;
    L_0x0215:
        r3 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL;
        if (r6 != r3) goto L_0x0233;
    L_0x0219:
        r0 = r29;
        r3 = r0.o;
        r3 = r3.m15879n();
        r2 = r3 - r2;
    L_0x0223:
        r3 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r3) goto L_0x041f;
    L_0x0229:
        r0 = r29;
        r3 = r0.f14106c;
        r3 = r3.f13908a;
        r2 = r2 - r3;
        r8 = r2;
        goto L_0x00e5;
    L_0x0233:
        r0 = r29;
        r3 = r0.o;
        r3 = r3.m15866g();
        r2 = r3 - r2;
        goto L_0x0223;
    L_0x023e:
        r3 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL;
        if (r6 != r3) goto L_0x0276;
    L_0x0242:
        r0 = r29;
        r3 = r0.o;
        r3 = r3.m15879n();
        r4 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = r3 / r4;
    L_0x024d:
        r4 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r4) goto L_0x028b;
    L_0x0253:
        r4 = r2;
    L_0x0254:
        r3 = r3 + r4;
        r4 = com.github.mikephil.charting.components.Legend.LegendOrientation.VERTICAL;
        if (r6 != r4) goto L_0x00e4;
    L_0x0259:
        r8 = (double) r3;
        r3 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r3) goto L_0x028d;
    L_0x0260:
        r0 = r29;
        r3 = r0.f14106c;
        r3 = r3.f13908a;
        r3 = -r3;
        r0 = (double) r3;
        r16 = r0;
        r26 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r16 = r16 / r26;
        r2 = (double) r2;
        r2 = r2 + r16;
    L_0x0271:
        r2 = r2 + r8;
        r2 = (float) r2;
        r8 = r2;
        goto L_0x00e5;
    L_0x0276:
        r0 = r29;
        r3 = r0.o;
        r3 = r3.m15864f();
        r0 = r29;
        r4 = r0.o;
        r4 = r4.m15870i();
        r8 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r4 = r4 / r8;
        r3 = r3 + r4;
        goto L_0x024d;
    L_0x028b:
        r4 = -r2;
        goto L_0x0254;
    L_0x028d:
        r0 = r29;
        r3 = r0.f14106c;
        r3 = r3.f13908a;
        r0 = (double) r3;
        r16 = r0;
        r26 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r16 = r16 / r26;
        r2 = (double) r2;
        r2 = r16 - r2;
        goto L_0x0271;
    L_0x029e:
        r2 = r5;
        goto L_0x0116;
    L_0x02a1:
        r0 = r29;
        r2 = r0.o;
        r2 = r2.m15878m();
        r2 = r2 - r5;
        r0 = r29;
        r3 = r0.f14106c;
        r3 = r3.f13909b;
        r2 = r2 - r3;
        goto L_0x0116;
    L_0x02b3:
        r0 = r29;
        r2 = r0.o;
        r2 = r2.m15878m();
        r0 = r29;
        r3 = r0.f14106c;
        r3 = r3.f13909b;
        r2 = r2 - r3;
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r3;
        r2 = r2 + r5;
        goto L_0x0116;
    L_0x02c8:
        r2 = 0;
        r9 = r2;
        goto L_0x0132;
    L_0x02cc:
        r2 = r6.f13960c;
        r2 = com.github.mikephil.charting.p183g.C3283i.m15928a(r2);
        r10 = r2;
        goto L_0x013b;
    L_0x02d5:
        r0 = r25;
        r2 = r0.get(r3);
        r2 = (com.github.mikephil.charting.p183g.C3274b) r2;
        r2 = r2.f14176a;
        r2 = -r2;
        goto L_0x0179;
    L_0x02e2:
        r3 = 0;
        r16 = r3;
        goto L_0x0188;
    L_0x02e7:
        r2 = r13;
        goto L_0x01b2;
    L_0x02ea:
        r2 = r14;
        goto L_0x01ec;
    L_0x02ed:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r2) goto L_0x02f7;
    L_0x02f3:
        r2 = -r15;
    L_0x02f4:
        r2 = r2 + r3;
        goto L_0x01ed;
    L_0x02f7:
        r2 = r15;
        goto L_0x02f4;
    L_0x02f9:
        r6 = 0;
        r4 = 0;
        r2 = 0;
        r3 = com.github.mikephil.charting.p127f.C3259i.C32581.f14101b;
        r7 = r7.ordinal();
        r3 = r3[r7];
        switch(r3) {
            case 1: goto L_0x0381;
            case 2: goto L_0x0394;
            case 3: goto L_0x03b5;
            default: goto L_0x0307;
        };
    L_0x0307:
        r3 = 0;
        r9 = r3;
        r10 = r2;
        r12 = r4;
        r14 = r6;
    L_0x030c:
        r0 = r22;
        r2 = r0.length;
        if (r9 >= r2) goto L_0x000a;
    L_0x0311:
        r6 = r22[r9];
        r2 = r6.f13959b;
        r3 = com.github.mikephil.charting.components.Legend.LegendForm.NONE;
        if (r2 == r3) goto L_0x03d5;
    L_0x0319:
        r2 = 1;
        r16 = r2;
    L_0x031c:
        r2 = r6.f13960c;
        r2 = java.lang.Float.isNaN(r2);
        if (r2 == 0) goto L_0x03da;
    L_0x0324:
        r17 = r11;
    L_0x0326:
        if (r16 == 0) goto L_0x040a;
    L_0x0328:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r2) goto L_0x03e4;
    L_0x032e:
        r4 = r8 + r14;
    L_0x0330:
        r5 = r10 + r21;
        r0 = r29;
        r7 = r0.f14106c;
        r2 = r29;
        r3 = r30;
        r2.m15756a(r3, r4, r5, r6, r7);
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r2) goto L_0x0345;
    L_0x0343:
        r4 = r4 + r17;
    L_0x0345:
        r2 = r6.f13958a;
        if (r2 == 0) goto L_0x0402;
    L_0x0349:
        if (r16 == 0) goto L_0x03ed;
    L_0x034b:
        if (r12 != 0) goto L_0x03ed;
    L_0x034d:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.LEFT_TO_RIGHT;
        r0 = r24;
        if (r0 != r2) goto L_0x03ea;
    L_0x0353:
        r2 = r13;
    L_0x0354:
        r4 = r4 + r2;
    L_0x0355:
        r2 = com.github.mikephil.charting.components.Legend.LegendDirection.RIGHT_TO_LEFT;
        r0 = r24;
        if (r0 != r2) goto L_0x0367;
    L_0x035b:
        r0 = r29;
        r2 = r0.f14104a;
        r3 = r6.f13958a;
        r2 = com.github.mikephil.charting.p183g.C3283i.m15931a(r2, r3);
        r2 = (float) r2;
        r4 = r4 - r2;
    L_0x0367:
        if (r12 != 0) goto L_0x03f2;
    L_0x0369:
        r2 = r10 + r19;
        r3 = r6.f13958a;
        r0 = r29;
        r1 = r30;
        r0.m15757a(r1, r4, r2, r3);
    L_0x0374:
        r2 = r19 + r20;
        r3 = r10 + r2;
        r5 = 0;
        r4 = r12;
    L_0x037a:
        r2 = r9 + 1;
        r9 = r2;
        r10 = r3;
        r12 = r4;
        r14 = r5;
        goto L_0x030c;
    L_0x0381:
        r2 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER;
        r0 = r23;
        if (r0 != r2) goto L_0x038b;
    L_0x0387:
        r2 = 0;
    L_0x0388:
        r2 = r2 + r5;
        goto L_0x0307;
    L_0x038b:
        r0 = r29;
        r2 = r0.o;
        r2 = r2.m15862e();
        goto L_0x0388;
    L_0x0394:
        r2 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.CENTER;
        r0 = r23;
        if (r0 != r2) goto L_0x03ac;
    L_0x039a:
        r0 = r29;
        r2 = r0.o;
        r2 = r2.m15878m();
    L_0x03a2:
        r0 = r29;
        r3 = r0.f14106c;
        r3 = r3.f13909b;
        r3 = r3 + r5;
        r2 = r2 - r3;
        goto L_0x0307;
    L_0x03ac:
        r0 = r29;
        r2 = r0.o;
        r2 = r2.m15868h();
        goto L_0x03a2;
    L_0x03b5:
        r0 = r29;
        r2 = r0.o;
        r2 = r2.m15878m();
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r3;
        r0 = r29;
        r3 = r0.f14106c;
        r3 = r3.f13909b;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = r3 / r5;
        r2 = r2 - r3;
        r0 = r29;
        r3 = r0.f14106c;
        r3 = r3.m15342w();
        r2 = r2 + r3;
        goto L_0x0307;
    L_0x03d5:
        r2 = 0;
        r16 = r2;
        goto L_0x031c;
    L_0x03da:
        r2 = r6.f13960c;
        r2 = com.github.mikephil.charting.p183g.C3283i.m15928a(r2);
        r17 = r2;
        goto L_0x0326;
    L_0x03e4:
        r2 = r17 - r14;
        r4 = r8 - r2;
        goto L_0x0330;
    L_0x03ea:
        r2 = -r13;
        goto L_0x0354;
    L_0x03ed:
        if (r12 == 0) goto L_0x0355;
    L_0x03ef:
        r4 = r8;
        goto L_0x0355;
    L_0x03f2:
        r2 = r19 + r20;
        r10 = r10 + r2;
        r2 = r10 + r19;
        r3 = r6.f13958a;
        r0 = r29;
        r1 = r30;
        r0.m15757a(r1, r4, r2, r3);
        goto L_0x0374;
    L_0x0402:
        r2 = r17 + r15;
        r5 = r14 + r2;
        r4 = 1;
        r3 = r10;
        goto L_0x037a;
    L_0x040a:
        r4 = r8;
        goto L_0x0345;
    L_0x040d:
        r3 = r4;
        goto L_0x01a7;
    L_0x0410:
        r4 = r2;
        goto L_0x0192;
    L_0x0413:
        r3 = r2;
        goto L_0x01a7;
    L_0x0416:
        r12 = r3;
        r2 = r4;
        goto L_0x0181;
    L_0x041a:
        r17 = r4;
        r4 = r5;
        goto L_0x0159;
    L_0x041f:
        r8 = r2;
        goto L_0x00e5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.f.i.a(android.graphics.Canvas):void");
    }

    /* renamed from: a */
    protected void m15756a(Canvas canvas, float f, float f2, C3208e c3208e, Legend legend) {
        if (c3208e.f13963f != 1122868 && c3208e.f13963f != 1122867 && c3208e.f13963f != 0) {
            int save = canvas.save();
            LegendForm legendForm = c3208e.f13959b;
            if (legendForm == LegendForm.DEFAULT) {
                legendForm = legend.m15358i();
            }
            this.f14105b.setColor(c3208e.f13963f);
            float a = C3283i.m15928a(Float.isNaN(c3208e.f13960c) ? legend.m15359j() : c3208e.f13960c);
            float f3 = a / 2.0f;
            switch (legendForm) {
                case DEFAULT:
                case CIRCLE:
                    this.f14105b.setStyle(Style.FILL);
                    canvas.drawCircle(f + f3, f2, f3, this.f14105b);
                    break;
                case SQUARE:
                    this.f14105b.setStyle(Style.FILL);
                    canvas.drawRect(f, f2 - f3, f + a, f3 + f2, this.f14105b);
                    break;
                case LINE:
                    float a2 = C3283i.m15928a(Float.isNaN(c3208e.f13961d) ? legend.m15360k() : c3208e.f13961d);
                    PathEffect l = c3208e.f13962e == null ? legend.m15361l() : c3208e.f13962e;
                    this.f14105b.setStyle(Style.STROKE);
                    this.f14105b.setStrokeWidth(a2);
                    this.f14105b.setPathEffect(l);
                    this.f14109f.reset();
                    this.f14109f.moveTo(f, f2);
                    this.f14109f.lineTo(a + f, f2);
                    canvas.drawPath(this.f14109f, this.f14105b);
                    break;
            }
            canvas.restoreToCount(save);
        }
    }

    /* renamed from: a */
    protected void m15757a(Canvas canvas, float f, float f2, String str) {
        canvas.drawText(str, f, f2, this.f14104a);
    }
}
