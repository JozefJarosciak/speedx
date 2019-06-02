package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import com.github.mikephil.charting.charts.C1475c;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.CombinedChart.DrawOrder;
import com.github.mikephil.charting.data.C3231g;
import com.github.mikephil.charting.data.C3232h;
import com.github.mikephil.charting.data.C3237k;
import com.github.mikephil.charting.data.C3238l;
import com.github.mikephil.charting.data.C3243q;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3275j;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CombinedChartRenderer */
/* renamed from: com.github.mikephil.charting.f.f */
public class C3256f extends C3248g {
    /* renamed from: a */
    protected List<C3248g> f14096a = new ArrayList(5);
    /* renamed from: b */
    protected WeakReference<C1475c> f14097b;
    /* renamed from: c */
    protected List<C3213d> f14098c = new ArrayList();

    public C3256f(CombinedChart combinedChart, C3185a c3185a, C3275j c3275j) {
        super(c3185a, c3275j);
        this.f14097b = new WeakReference(combinedChart);
        m15744b();
    }

    /* renamed from: b */
    public void m15744b() {
        this.f14096a.clear();
        CombinedChart combinedChart = (CombinedChart) this.f14097b.get();
        if (combinedChart != null) {
            for (DrawOrder drawOrder : combinedChart.getDrawOrder()) {
                switch (drawOrder) {
                    case BAR:
                        if (combinedChart.getBarData() == null) {
                            break;
                        }
                        this.f14096a.add(new C3250b(combinedChart, this.g, this.o));
                        break;
                    case BUBBLE:
                        if (combinedChart.getBubbleData() == null) {
                            break;
                        }
                        this.f14096a.add(new C3252d(combinedChart, this.g, this.o));
                        break;
                    case LINE:
                        if (combinedChart.getLineData() == null) {
                            break;
                        }
                        this.f14096a.add(new C3263j(combinedChart, this.g, this.o));
                        break;
                    case CANDLE:
                        if (combinedChart.getCandleData() == null) {
                            break;
                        }
                        this.f14096a.add(new C3254e(combinedChart, this.g, this.o));
                        break;
                    case SCATTER:
                        if (combinedChart.getScatterData() == null) {
                            break;
                        }
                        this.f14096a.add(new C3266p(combinedChart, this.g, this.o));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /* renamed from: a */
    public void mo3994a() {
        for (C3248g a : this.f14096a) {
            a.mo3994a();
        }
    }

    /* renamed from: a */
    public void mo3995a(Canvas canvas) {
        for (C3248g a : this.f14096a) {
            a.mo3995a(canvas);
        }
    }

    /* renamed from: b */
    public void mo3997b(Canvas canvas) {
        for (C3248g b : this.f14096a) {
            b.mo3997b(canvas);
        }
    }

    /* renamed from: c */
    public void mo3998c(Canvas canvas) {
        for (C3248g c : this.f14096a) {
            c.mo3998c(canvas);
        }
    }

    /* renamed from: a */
    public void mo3996a(Canvas canvas, C3213d[] c3213dArr) {
        C1475c c1475c = (C1475c) this.f14097b.get();
        if (c1475c != null) {
            for (C3248g c3248g : this.f14096a) {
                Object barData;
                int i;
                if (c3248g instanceof C3250b) {
                    barData = ((C3250b) c3248g).f14074a.getBarData();
                } else if (c3248g instanceof C3263j) {
                    C3238l lineData = ((C3263j) c3248g).f14114a.getLineData();
                } else if (c3248g instanceof C3254e) {
                    C3232h candleData = ((C3254e) c3248g).f14089a.getCandleData();
                } else if (c3248g instanceof C3266p) {
                    C3243q scatterData = ((C3266p) c3248g).f14147a.getScatterData();
                } else if (c3248g instanceof C3252d) {
                    C3231g bubbleData = ((C3252d) c3248g).f14084a.getBubbleData();
                } else {
                    barData = null;
                }
                if (barData == null) {
                    i = -1;
                } else {
                    i = ((C3237k) c1475c.getData()).m15673q().indexOf(barData);
                }
                this.f14098c.clear();
                for (C3213d c3213d : c3213dArr) {
                    if (c3213d.m15430e() == i || c3213d.m15430e() == -1) {
                        this.f14098c.add(c3213d);
                    }
                }
                c3248g.mo3996a(canvas, (C3213d[]) this.f14098c.toArray(new C3213d[this.f14098c.size()]));
            }
        }
    }
}
