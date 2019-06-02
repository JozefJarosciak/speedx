package com.beastbikes.android.modules.cycling.activity.ui.record.p122b;

import com.github.mikephil.charting.components.C1476a;
import com.github.mikephil.charting.p121c.C1968d;
import java.text.DecimalFormat;

/* compiled from: UnitFormatter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.b.g */
public class C1975g implements C1968d {
    /* renamed from: a */
    private DecimalFormat f8871a = new DecimalFormat("#");

    /* renamed from: a */
    public String mo3334a(float f, C1476a c1476a) {
        return this.f8871a.format((double) (f * 10.0f)) + "\n" + this.f8871a.format((double) ((1.0f + f) * 10.0f));
    }
}
