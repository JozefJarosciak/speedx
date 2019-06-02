package com.beastbikes.android.modules.cycling.activity.ui.record.p122b;

import com.github.mikephil.charting.components.C1476a;
import com.github.mikephil.charting.p121c.C1968d;
import java.text.DecimalFormat;

/* compiled from: PowerXAxisValueFormatter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.b.f */
public class C1974f implements C1968d {
    /* renamed from: a */
    private DecimalFormat f8870a = new DecimalFormat("#");

    /* renamed from: a */
    public String mo3334a(float f, C1476a c1476a) {
        if (f == 0.0f) {
            return "0W";
        }
        if (f == 10.0f) {
            return "450+";
        }
        return this.f8870a.format((double) (50.0f * f)) + "W";
    }
}
