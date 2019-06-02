package com.beastbikes.android.modules.cycling.activity.ui.record.p122b;

import com.github.mikephil.charting.components.C1476a;
import com.github.mikephil.charting.p121c.C1968d;
import java.text.DecimalFormat;

/* compiled from: MyValueFormatter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.b.d */
public class C1972d implements C1968d {
    /* renamed from: a */
    private DecimalFormat f8868a = new DecimalFormat("0");

    /* renamed from: a */
    public String mo3334a(float f, C1476a c1476a) {
        if (f == 0.0f) {
            return "";
        }
        return this.f8868a.format((double) f);
    }
}
