package com.beastbikes.android.modules.cycling.activity.ui.record.p122b;

import com.github.mikephil.charting.components.C1476a;
import com.github.mikephil.charting.p121c.C1968d;
import java.text.DecimalFormat;

/* compiled from: MyAxisValueFormatter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.b.c */
public class C1971c implements C1968d {
    /* renamed from: a */
    private DecimalFormat f8867a = new DecimalFormat("#");

    /* renamed from: a */
    public String mo3334a(float f, C1476a c1476a) {
        if (f == 0.0f) {
            return "BPM";
        }
        return this.f8867a.format((double) f) + " %";
    }
}
