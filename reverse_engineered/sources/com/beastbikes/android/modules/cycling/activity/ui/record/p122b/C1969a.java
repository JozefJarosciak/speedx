package com.beastbikes.android.modules.cycling.activity.ui.record.p122b;

import android.util.SparseArray;
import com.github.mikephil.charting.components.C1476a;
import com.github.mikephil.charting.p121c.C1968d;

/* compiled from: HeartRateXAxisValueFormatter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.b.a */
public class C1969a implements C1968d {
    /* renamed from: a */
    private SparseArray<String> f8863a;

    public C1969a(SparseArray<String> sparseArray) {
        this.f8863a = sparseArray;
    }

    /* renamed from: a */
    public String mo3334a(float f, C1476a c1476a) {
        if (this.f8863a == null) {
            return "";
        }
        int i = (int) f;
        if (i < 0 || i > this.f8863a.size() - 1) {
            return "";
        }
        return (String) this.f8863a.get((int) f);
    }
}
