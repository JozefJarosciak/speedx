package com.github.mikephil.charting.p121c;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p183g.C3275j;
import java.text.DecimalFormat;

/* compiled from: DefaultValueFormatter */
/* renamed from: com.github.mikephil.charting.c.c */
public class C3193c implements C3192f {
    /* renamed from: a */
    protected DecimalFormat f13867a;
    /* renamed from: b */
    protected int f13868b;

    public C3193c(int i) {
        m15309a(i);
    }

    /* renamed from: a */
    public void m15309a(int i) {
        this.f13868b = i;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            if (i2 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append("0");
        }
        this.f13867a = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }

    /* renamed from: a */
    public String mo3886a(float f, Entry entry, int i, C3275j c3275j) {
        return this.f13867a.format((double) f);
    }
}
