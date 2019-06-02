package com.github.mikephil.charting.charts;

import android.content.Context;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.github.mikephil.charting.data.C3224j;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.listener.C3288e;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3283i;

/* compiled from: PieRadarChartBase */
/* renamed from: com.github.mikephil.charting.charts.e */
public abstract class C3203e<T extends C3224j<? extends C3220e<? extends Entry>>> extends C1475c<T> {
    /* renamed from: a */
    private float f13882a = 270.0f;
    /* renamed from: b */
    private float f13883b = 270.0f;
    /* renamed from: c */
    protected boolean f13884c = true;
    /* renamed from: d */
    protected float f13885d = 0.0f;

    /* renamed from: a */
    public abstract int mo3897a(float f);

    public abstract float getRadius();

    protected abstract float getRequiredBaseOffset();

    protected abstract float getRequiredLegendOffset();

    public C3203e(Context context) {
        super(context);
    }

    public C3203e(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C3203e(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3898a() {
        super.a();
        this.M = new C3288e(this);
    }

    /* renamed from: b */
    protected void mo3899b() {
    }

    public int getMaxVisibleCount() {
        return this.C.m15597k();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.I || this.M == null) {
            return super.onTouchEvent(motionEvent);
        }
        return this.M.onTouch(this, motionEvent);
    }

    public void computeScroll() {
        if (this.M instanceof C3288e) {
            ((C3288e) this.M).m15983b();
        }
    }

    /* renamed from: h */
    public void mo3905h() {
        if (this.C != null) {
            mo3899b();
            if (this.K != null) {
                this.N.m15758a(this.C);
            }
            m15331j();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: j */
    public void m15331j() {
        /*
        r10 = this;
        r6 = 1097859072; // 0x41700000 float:15.0 double:5.424144515E-315;
        r2 = 0;
        r0 = r10.K;
        if (r0 == 0) goto L_0x0228;
    L_0x0007:
        r0 = r10.K;
        r0 = r0.m15336A();
        if (r0 == 0) goto L_0x0228;
    L_0x000f:
        r0 = r10.K;
        r0 = r0.m15356g();
        if (r0 != 0) goto L_0x0228;
    L_0x0017:
        r0 = r10.K;
        r0 = r0.f13908a;
        r1 = r10.Q;
        r1 = r1.m15879n();
        r3 = r10.K;
        r3 = r3.m15366q();
        r1 = r1 * r3;
        r0 = java.lang.Math.min(r0, r1);
        r1 = com.github.mikephil.charting.charts.C3203e.C32021.f13881c;
        r3 = r10.K;
        r3 = r3.m15355f();
        r3 = r3.ordinal();
        r1 = r1[r3];
        switch(r1) {
            case 1: goto L_0x00e1;
            case 2: goto L_0x01d6;
            default: goto L_0x003d;
        };
    L_0x003d:
        r0 = r2;
        r1 = r2;
        r3 = r2;
    L_0x0040:
        r4 = r10.getRequiredBaseOffset();
        r3 = r3 + r4;
        r4 = r10.getRequiredBaseOffset();
        r1 = r1 + r4;
        r4 = r10.getRequiredBaseOffset();
        r2 = r2 + r4;
        r4 = r10.getRequiredBaseOffset();
        r0 = r0 + r4;
    L_0x0054:
        r4 = r10.f13885d;
        r4 = com.github.mikephil.charting.p183g.C3283i.m15928a(r4);
        r5 = r10 instanceof com.github.mikephil.charting.charts.C3204f;
        if (r5 == 0) goto L_0x0075;
    L_0x005e:
        r5 = r10.getXAxis();
        r6 = r5.A();
        if (r6 == 0) goto L_0x0075;
    L_0x0068:
        r6 = r5.h();
        if (r6 == 0) goto L_0x0075;
    L_0x006e:
        r5 = r5.f13938D;
        r5 = (float) r5;
        r4 = java.lang.Math.max(r4, r5);
    L_0x0075:
        r5 = r10.getExtraTopOffset();
        r2 = r2 + r5;
        r5 = r10.getExtraRightOffset();
        r1 = r1 + r5;
        r5 = r10.getExtraBottomOffset();
        r0 = r0 + r5;
        r5 = r10.getExtraLeftOffset();
        r3 = r3 + r5;
        r3 = java.lang.Math.max(r4, r3);
        r2 = java.lang.Math.max(r4, r2);
        r1 = java.lang.Math.max(r4, r1);
        r5 = r10.getRequiredBaseOffset();
        r0 = java.lang.Math.max(r5, r0);
        r0 = java.lang.Math.max(r4, r0);
        r4 = r10.Q;
        r4.m15851a(r3, r2, r1, r0);
        r4 = r10.B;
        if (r4 == 0) goto L_0x00e0;
    L_0x00aa:
        r4 = "MPAndroidChart";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "offsetLeft: ";
        r5 = r5.append(r6);
        r3 = r5.append(r3);
        r5 = ", offsetTop: ";
        r3 = r3.append(r5);
        r2 = r3.append(r2);
        r3 = ", offsetRight: ";
        r2 = r2.append(r3);
        r1 = r2.append(r1);
        r2 = ", offsetBottom: ";
        r1 = r1.append(r2);
        r0 = r1.append(r0);
        r0 = r0.toString();
        android.util.Log.i(r4, r0);
    L_0x00e0:
        return;
    L_0x00e1:
        r1 = r10.K;
        r1 = r1.m15353d();
        r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.LEFT;
        if (r1 == r3) goto L_0x00f5;
    L_0x00eb:
        r1 = r10.K;
        r1 = r1.m15353d();
        r3 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.RIGHT;
        if (r1 != r3) goto L_0x0225;
    L_0x00f5:
        r1 = r10.K;
        r1 = r1.m15354e();
        r3 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.CENTER;
        if (r1 != r3) goto L_0x011e;
    L_0x00ff:
        r1 = 1095761920; // 0x41500000 float:13.0 double:5.413783207E-315;
        r1 = com.github.mikephil.charting.p183g.C3283i.m15928a(r1);
        r0 = r0 + r1;
    L_0x0106:
        r1 = com.github.mikephil.charting.charts.C3203e.C32021.f13880b;
        r3 = r10.K;
        r3 = r3.m15353d();
        r3 = r3.ordinal();
        r1 = r1[r3];
        switch(r1) {
            case 1: goto L_0x0119;
            case 2: goto L_0x0189;
            case 3: goto L_0x018e;
            default: goto L_0x0117;
        };
    L_0x0117:
        goto L_0x003d;
    L_0x0119:
        r1 = r2;
        r3 = r0;
        r0 = r2;
        goto L_0x0040;
    L_0x011e:
        r1 = 1090519040; // 0x41000000 float:8.0 double:5.38787994E-315;
        r1 = com.github.mikephil.charting.p183g.C3283i.m15928a(r1);
        r1 = r1 + r0;
        r0 = r10.K;
        r0 = r0.f13909b;
        r3 = r10.K;
        r3 = r3.f13910c;
        r3 = r3 + r0;
        r4 = r10.getCenter();
        r0 = r10.K;
        r0 = r0.m15353d();
        r5 = com.github.mikephil.charting.components.Legend.LegendHorizontalAlignment.RIGHT;
        if (r0 != r5) goto L_0x017f;
    L_0x013c:
        r0 = r10.getWidth();
        r0 = (float) r0;
        r0 = r0 - r1;
        r0 = r0 + r6;
    L_0x0143:
        r3 = r3 + r6;
        r5 = r10.m15328d(r0, r3);
        r6 = r10.getRadius();
        r0 = r10.m15326b(r0, r3);
        r6 = r10.m15323a(r4, r6, r0);
        r0 = r6.f14200a;
        r7 = r6.f14201b;
        r0 = r10.m15328d(r0, r7);
        r7 = 1084227584; // 0x40a00000 float:5.0 double:5.356796015E-315;
        r7 = com.github.mikephil.charting.p183g.C3283i.m15928a(r7);
        r8 = r4.f14201b;
        r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1));
        if (r3 < 0) goto L_0x0182;
    L_0x0168:
        r3 = r10.getHeight();
        r3 = (float) r3;
        r3 = r3 - r1;
        r8 = r10.getWidth();
        r8 = (float) r8;
        r3 = (r3 > r8 ? 1 : (r3 == r8 ? 0 : -1));
        if (r3 <= 0) goto L_0x0182;
    L_0x0177:
        r0 = r1;
    L_0x0178:
        com.github.mikephil.charting.p183g.C3279e.m15900b(r4);
        com.github.mikephil.charting.p183g.C3279e.m15900b(r6);
        goto L_0x0106;
    L_0x017f:
        r0 = r1 - r6;
        goto L_0x0143;
    L_0x0182:
        r1 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1));
        if (r1 >= 0) goto L_0x0222;
    L_0x0186:
        r0 = r0 - r5;
        r0 = r0 + r7;
        goto L_0x0178;
    L_0x0189:
        r1 = r0;
        r3 = r2;
        r0 = r2;
        goto L_0x0040;
    L_0x018e:
        r0 = com.github.mikephil.charting.charts.C3203e.C32021.f13879a;
        r1 = r10.K;
        r1 = r1.m15354e();
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x01a1;
            case 2: goto L_0x01bd;
            default: goto L_0x019f;
        };
    L_0x019f:
        goto L_0x003d;
    L_0x01a1:
        r0 = r10.K;
        r0 = r0.f13909b;
        r1 = r10.Q;
        r1 = r1.m15878m();
        r3 = r10.K;
        r3 = r3.m15366q();
        r1 = r1 * r3;
        r0 = java.lang.Math.min(r0, r1);
        r1 = r2;
        r3 = r2;
        r9 = r0;
        r0 = r2;
        r2 = r9;
        goto L_0x0040;
    L_0x01bd:
        r0 = r10.K;
        r0 = r0.f13909b;
        r1 = r10.Q;
        r1 = r1.m15878m();
        r3 = r10.K;
        r3 = r3.m15366q();
        r1 = r1 * r3;
        r0 = java.lang.Math.min(r0, r1);
        r1 = r2;
        r3 = r2;
        goto L_0x0040;
    L_0x01d6:
        r0 = r10.K;
        r0 = r0.m15354e();
        r1 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.TOP;
        if (r0 == r1) goto L_0x01ea;
    L_0x01e0:
        r0 = r10.K;
        r0 = r0.m15354e();
        r1 = com.github.mikephil.charting.components.Legend.LegendVerticalAlignment.BOTTOM;
        if (r0 != r1) goto L_0x003d;
    L_0x01ea:
        r0 = r10.getRequiredLegendOffset();
        r1 = r10.K;
        r1 = r1.f13909b;
        r0 = r0 + r1;
        r1 = r10.Q;
        r1 = r1.m15878m();
        r3 = r10.K;
        r3 = r3.m15366q();
        r1 = r1 * r3;
        r0 = java.lang.Math.min(r0, r1);
        r1 = com.github.mikephil.charting.charts.C3203e.C32021.f13879a;
        r3 = r10.K;
        r3 = r3.m15354e();
        r3 = r3.ordinal();
        r1 = r1[r3];
        switch(r1) {
            case 1: goto L_0x0217;
            case 2: goto L_0x021e;
            default: goto L_0x0215;
        };
    L_0x0215:
        goto L_0x003d;
    L_0x0217:
        r1 = r2;
        r3 = r2;
        r9 = r0;
        r0 = r2;
        r2 = r9;
        goto L_0x0040;
    L_0x021e:
        r1 = r2;
        r3 = r2;
        goto L_0x0040;
    L_0x0222:
        r0 = r2;
        goto L_0x0178;
    L_0x0225:
        r0 = r2;
        goto L_0x0106;
    L_0x0228:
        r0 = r2;
        r1 = r2;
        r3 = r2;
        goto L_0x0054;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.mikephil.charting.charts.e.j():void");
    }

    /* renamed from: b */
    public float m15326b(float f, float f2) {
        C3279e centerOffsets = getCenterOffsets();
        double d = (double) (f - centerOffsets.f14200a);
        double d2 = (double) (f2 - centerOffsets.f14201b);
        float toDegrees = (float) Math.toDegrees(Math.acos(d2 / Math.sqrt((d * d) + (d2 * d2))));
        if (f > centerOffsets.f14200a) {
            toDegrees = 360.0f - toDegrees;
        }
        toDegrees += 90.0f;
        if (toDegrees > 360.0f) {
            toDegrees -= 360.0f;
        }
        C3279e.m15900b(centerOffsets);
        return toDegrees;
    }

    /* renamed from: a */
    public C3279e m15323a(C3279e c3279e, float f, float f2) {
        C3279e a = C3279e.m15897a(0.0f, 0.0f);
        m15325a(c3279e, f, f2, a);
        return a;
    }

    /* renamed from: a */
    public void m15325a(C3279e c3279e, float f, float f2, C3279e c3279e2) {
        c3279e2.f14200a = (float) (((double) c3279e.f14200a) + (((double) f) * Math.cos(Math.toRadians((double) f2))));
        c3279e2.f14201b = (float) (((double) c3279e.f14201b) + (((double) f) * Math.sin(Math.toRadians((double) f2))));
    }

    /* renamed from: d */
    public float m15328d(float f, float f2) {
        float f3;
        float f4;
        C3279e centerOffsets = getCenterOffsets();
        if (f > centerOffsets.f14200a) {
            f3 = f - centerOffsets.f14200a;
        } else {
            f3 = centerOffsets.f14200a - f;
        }
        if (f2 > centerOffsets.f14201b) {
            f4 = f2 - centerOffsets.f14201b;
        } else {
            f4 = centerOffsets.f14201b - f2;
        }
        f3 = (float) Math.sqrt(Math.pow((double) f4, 2.0d) + Math.pow((double) f3, 2.0d));
        C3279e.m15900b(centerOffsets);
        return f3;
    }

    public void setRotationAngle(float f) {
        this.f13883b = f;
        this.f13882a = C3283i.m15948c(this.f13883b);
    }

    public float getRawRotationAngle() {
        return this.f13883b;
    }

    public float getRotationAngle() {
        return this.f13882a;
    }

    public void setRotationEnabled(boolean z) {
        this.f13884c = z;
    }

    /* renamed from: i */
    public boolean m15330i() {
        return this.f13884c;
    }

    public float getMinOffset() {
        return this.f13885d;
    }

    public void setMinOffset(float f) {
        this.f13885d = f;
    }

    public float getDiameter() {
        RectF k = this.Q.m15874k();
        k.left += getExtraLeftOffset();
        k.top += getExtraTopOffset();
        k.right -= getExtraRightOffset();
        k.bottom -= getExtraBottomOffset();
        return Math.min(k.width(), k.height());
    }

    public float getYChartMax() {
        return 0.0f;
    }

    public float getYChartMin() {
        return 0.0f;
    }
}
