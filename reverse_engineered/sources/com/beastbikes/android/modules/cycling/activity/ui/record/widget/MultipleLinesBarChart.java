package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import com.github.mikephil.charting.charts.C2011a;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.p127f.C2005q;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3279e;
import com.github.mikephil.charting.p183g.C3281g;
import com.github.mikephil.charting.p183g.C3283i;

public class MultipleLinesBarChart extends C2011a {

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.MultipleLinesBarChart$a */
    private class C2006a extends C2005q {
        /* renamed from: a */
        final /* synthetic */ MultipleLinesBarChart f9035a;

        public C2006a(MultipleLinesBarChart multipleLinesBarChart, C3275j c3275j, XAxis xAxis, C3281g c3281g) {
            this.f9035a = multipleLinesBarChart;
            super(c3275j, xAxis, c3281g);
        }

        /* renamed from: a */
        protected void mo3341a(Canvas canvas, float f, C3279e c3279e) {
            float C = this.h.m15382C();
            boolean c = this.h.c();
            float[] fArr = new float[(this.h.d * 2)];
            for (int i = 0; i < fArr.length; i += 2) {
                if (c) {
                    fArr[i] = this.h.c[i / 2];
                } else {
                    fArr[i] = this.h.b[i / 2];
                }
            }
            this.c.m15918a(fArr);
            for (int i2 = 0; i2 < fArr.length; i2 += 2) {
                float f2 = fArr[i2];
                if (this.o.m15863e(f2)) {
                    float f3;
                    String[] split;
                    int length;
                    float b;
                    float f4;
                    int i3;
                    float f5;
                    String a = this.h.q().mo3334a(this.h.b[i2 / 2], this.h);
                    if (this.h.m15383D()) {
                        if (i2 == this.h.d - 1 && this.h.d > 1) {
                            float a2 = (float) C3283i.m15931a(this.e, a);
                            if (a2 > this.o.m15855b() * 2.0f && f2 + a2 > this.o.m15879n()) {
                                f2 -= a2 / 2.0f;
                            }
                            f3 = f2;
                            split = a.split("\n");
                            length = split.length;
                            b = (float) C3283i.m15946b(this.e, a);
                            f4 = b * 0.5f;
                            if (length > 1) {
                                m10343a(canvas, a, f3, f, c3279e, C);
                            } else {
                                i3 = 0;
                                f2 = f;
                                while (i3 < length) {
                                    f5 = f2 + (((float) i3) * b);
                                    if (i3 >= 1) {
                                    }
                                    m10343a(canvas, split[i3], f3, f5, c3279e, C);
                                    i3++;
                                    f2 = f5;
                                }
                            }
                        } else if (i2 == 0) {
                            f3 = f2 + (((float) C3283i.m15931a(this.e, a)) / 2.0f);
                            split = a.split("\n");
                            length = split.length;
                            b = (float) C3283i.m15946b(this.e, a);
                            f4 = b * 0.5f;
                            if (length > 1) {
                                i3 = 0;
                                f2 = f;
                                while (i3 < length) {
                                    f5 = f2 + (((float) i3) * b);
                                    if (i3 >= 1 || i3 > length - 1) {
                                        m10343a(canvas, split[i3], f3, f5, c3279e, C);
                                    } else {
                                        m10343a(canvas, split[i3], f3, f5 + f4, c3279e, C);
                                    }
                                    i3++;
                                    f2 = f5;
                                }
                            } else {
                                m10343a(canvas, a, f3, f, c3279e, C);
                            }
                        }
                    }
                    f3 = f2;
                    split = a.split("\n");
                    length = split.length;
                    b = (float) C3283i.m15946b(this.e, a);
                    f4 = b * 0.5f;
                    if (length > 1) {
                        i3 = 0;
                        f2 = f;
                        while (i3 < length) {
                            f5 = f2 + (((float) i3) * b);
                            if (i3 >= 1) {
                            }
                            m10343a(canvas, split[i3], f3, f5, c3279e, C);
                            i3++;
                            f2 = f5;
                        }
                    } else {
                        m10343a(canvas, a, f3, f, c3279e, C);
                    }
                }
            }
        }
    }

    public MultipleLinesBarChart(Context context) {
        super(context);
    }

    public MultipleLinesBarChart(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MultipleLinesBarChart(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3350a() {
        super.mo3350a();
        setXAxisRenderer(new C2006a(this, this.Q, this.H, this.s));
    }
}
