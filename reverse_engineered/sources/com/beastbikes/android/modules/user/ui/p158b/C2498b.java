package com.beastbikes.android.modules.user.ui.p158b;

import com.github.mikephil.charting.components.C1476a;
import com.github.mikephil.charting.p121c.C1968d;
import java.text.DecimalFormat;

/* compiled from: PersonalRecordYFormat */
/* renamed from: com.beastbikes.android.modules.user.ui.b.b */
public class C2498b implements C1968d {
    /* renamed from: a */
    private DecimalFormat f11847a = new DecimalFormat("#.#");

    /* renamed from: a */
    public String mo3334a(float f, C1476a c1476a) {
        if (f == 0.0f) {
            return "";
        }
        return this.f11847a.format((double) f);
    }
}
