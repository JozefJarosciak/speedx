package com.github.mikephil.charting.p180b;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.p089e.p090b.C3229a;

/* compiled from: BarBuffer */
/* renamed from: com.github.mikephil.charting.b.b */
public class C3187b extends C3186a<C3229a> {
    /* renamed from: g */
    protected int f13860g = 0;
    /* renamed from: h */
    protected int f13861h = 1;
    /* renamed from: i */
    protected boolean f13862i = false;
    /* renamed from: j */
    protected boolean f13863j = false;
    /* renamed from: k */
    protected float f13864k = 1.0f;

    public C3187b(int i, int i2, boolean z) {
        super(i);
        this.f13861h = i2;
        this.f13862i = z;
    }

    /* renamed from: a */
    public void m15297a(float f) {
        this.f13864k = f;
    }

    /* renamed from: a */
    public void m15299a(int i) {
        this.f13860g = i;
    }

    /* renamed from: a */
    public void m15301a(boolean z) {
        this.f13863j = z;
    }

    /* renamed from: a */
    protected void m15298a(float f, float f2, float f3, float f4) {
        float[] fArr = this.b;
        int i = this.a;
        this.a = i + 1;
        fArr[i] = f;
        fArr = this.b;
        i = this.a;
        this.a = i + 1;
        fArr[i] = f2;
        fArr = this.b;
        i = this.a;
        this.a = i + 1;
        fArr[i] = f3;
        fArr = this.b;
        i = this.a;
        this.a = i + 1;
        fArr[i] = f4;
    }

    /* renamed from: a */
    public void mo3884a(C3229a c3229a) {
        float F = ((float) c3229a.mo3938F()) * this.c;
        float f = this.f13864k / 2.0f;
        for (int i = 0; ((float) i) < F; i++) {
            BarEntry barEntry = (BarEntry) c3229a.mo3948g(i);
            if (barEntry != null) {
                float i2 = barEntry.m15450i();
                float b = barEntry.mo3912b();
                float[] a = barEntry.m15451a();
                float f2;
                float f3;
                float f4;
                if (!this.f13862i || a == null) {
                    float f5;
                    f2 = i2 - f;
                    f3 = i2 + f;
                    if (this.f13863j) {
                        f4 = b >= 0.0f ? b : 0.0f;
                        f5 = b <= 0.0f ? b : 0.0f;
                        b = f4;
                    } else {
                        f5 = b >= 0.0f ? b : 0.0f;
                        if (b > 0.0f) {
                            b = 0.0f;
                        }
                    }
                    if (f5 > 0.0f) {
                        f5 *= this.d;
                    } else {
                        b *= this.d;
                    }
                    m15298a(f2, f5, f3, b);
                } else {
                    f4 = 0.0f;
                    b = -barEntry.m15456f();
                    for (float f22 : a) {
                        if (f22 == 0.0f && (f4 == 0.0f || b == 0.0f)) {
                            f3 = f22;
                        } else if (f22 >= 0.0f) {
                            f22 += f4;
                            f3 = f4;
                            f4 = f22;
                        } else {
                            float abs = Math.abs(f22) + b;
                            f3 = b;
                            b = Math.abs(f22) + b;
                            f22 = abs;
                        }
                        float f6 = i2 - f;
                        float f7 = i2 + f;
                        float f8;
                        if (this.f13863j) {
                            if (f3 >= f22) {
                                f8 = f3;
                            } else {
                                f8 = f22;
                            }
                            if (f3 > f22) {
                                f3 = f22;
                            }
                            f22 = f8;
                        } else {
                            if (f3 >= f22) {
                                f8 = f3;
                            } else {
                                f8 = f22;
                            }
                            if (f3 > f22) {
                                f3 = f22;
                            }
                            f22 = f3;
                            f3 = f8;
                        }
                        m15298a(f6, f3 * this.d, f7, f22 * this.d);
                    }
                }
            }
        }
        m15294a();
    }
}
