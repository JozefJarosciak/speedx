package com.beastbikes.android.modules.user.ui.p158b;

import com.github.mikephil.charting.components.C1476a;
import com.github.mikephil.charting.p121c.C1968d;
import java.text.DecimalFormat;
import java.util.ArrayList;

/* compiled from: PersonalRecordXFormat */
/* renamed from: com.beastbikes.android.modules.user.ui.b.a */
public class C2497a implements C1968d {
    /* renamed from: a */
    private ArrayList<String> f11845a;
    /* renamed from: b */
    private DecimalFormat f11846b = new DecimalFormat("#");

    public C2497a(ArrayList<String> arrayList) {
        this.f11845a = arrayList;
    }

    /* renamed from: a */
    public String mo3334a(float f, C1476a c1476a) {
        if (this.f11845a == null || this.f11845a.isEmpty()) {
            return this.f11846b.format((double) f);
        }
        return (String) this.f11845a.get((int) f);
    }

    /* renamed from: a */
    public void m12574a(ArrayList<String> arrayList) {
        this.f11845a.clear();
        this.f11845a.addAll(arrayList);
    }
}
