package com.beastbikes.android.modules.cycling.activity.ui.record.p122b;

import com.beastbikes.android.utils.C2555d;
import com.github.mikephil.charting.components.C1476a;
import com.github.mikephil.charting.p121c.C1968d;

/* compiled from: XAxisTimeFormatter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.b.h */
public class C1976h implements C1968d {
    /* renamed from: a */
    private double f8872a;

    public C1976h(double d) {
        this.f8872a = d;
    }

    /* renamed from: a */
    public String mo3334a(float f, C1476a c1476a) {
        if (f == 0.0f) {
            return "0";
        }
        if (this.f8872a == 0.0d) {
            return "00:00:00";
        }
        if (f == c1476a.u()) {
            return "";
        }
        return C2555d.m12802b((long) (((double) (f / c1476a.u())) * this.f8872a));
    }
}
