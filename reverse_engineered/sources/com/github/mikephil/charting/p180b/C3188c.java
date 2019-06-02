package com.github.mikephil.charting.p180b;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.p089e.p090b.C3229a;

/* compiled from: HorizontalBarBuffer */
/* renamed from: com.github.mikephil.charting.b.c */
public class C3188c extends C3187b {
    public C3188c(int i, int i2, boolean z) {
        super(i, i2, z);
    }

    /* renamed from: a */
    public void mo3884a(C3229a c3229a) {
        float F = ((float) c3229a.mo3938F()) * this.c;
        float f = this.k / 2.0f;
        for (int i = 0; ((float) i) < F; i++) {
            BarEntry barEntry = (BarEntry) c3229a.mo3948g(i);
            if (barEntry != null) {
                float i2 = barEntry.m15450i();
                float b = barEntry.mo3912b();
                float[] a = barEntry.m15451a();
                float f2;
                float f3;
                float f4;
                if (!this.i || a == null) {
                    float f5;
                    f2 = i2 - f;
                    f3 = i2 + f;
                    if (this.j) {
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
                    m15298a(b, f3, f5, f2);
                } else {
                    f4 = 0.0f;
                    b = -barEntry.m15456f();
                    for (float f32 : a) {
                        if (f32 >= 0.0f) {
                            f2 = f4 + f32;
                            f32 = f4;
                            f4 = f2;
                        } else {
                            f2 = Math.abs(f32) + b;
                            float abs = Math.abs(f32) + b;
                            f32 = b;
                            b = abs;
                        }
                        float f6 = i2 - f;
                        float f7 = i2 + f;
                        float f8;
                        if (this.j) {
                            if (f32 >= f2) {
                                f8 = f32;
                            } else {
                                f8 = f2;
                            }
                            if (f32 > f2) {
                                f32 = f2;
                            }
                            f2 = f8;
                        } else {
                            if (f32 >= f2) {
                                f8 = f32;
                            } else {
                                f8 = f2;
                            }
                            if (f32 > f2) {
                                f32 = f2;
                            }
                            f2 = f32;
                            f32 = f8;
                        }
                        m15298a(f2 * this.d, f7, f32 * this.d, f6);
                    }
                }
            }
        }
        m15294a();
    }
}
