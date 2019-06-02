package com.beastbikes.android.modules.cycling.activity.ui.record.p122b;

import com.github.mikephil.charting.components.C1476a;
import com.github.mikephil.charting.p121c.C1968d;
import java.text.DecimalFormat;

/* compiled from: MAxisValueFormatter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.b.b */
public class C1970b implements C1968d {
    /* renamed from: a */
    private String f8864a;
    /* renamed from: b */
    private float f8865b;
    /* renamed from: c */
    private DecimalFormat f8866c = new DecimalFormat("0.0");

    public C1970b(String str, float f) {
        this.f8864a = str;
        this.f8865b = f;
    }

    /* renamed from: a */
    public String mo3334a(float f, C1476a c1476a) {
        if (f == 0.0f) {
            return this.f8864a;
        }
        if (this.f8865b == 0.0f) {
            return this.f8866c.format((double) f);
        }
        return this.f8866c.format((double) ((f / c1476a.u()) * this.f8865b));
    }
}
