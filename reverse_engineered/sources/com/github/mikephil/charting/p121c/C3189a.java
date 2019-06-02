package com.github.mikephil.charting.p121c;

import com.github.mikephil.charting.components.C1476a;
import java.text.DecimalFormat;

/* compiled from: DefaultAxisValueFormatter */
/* renamed from: com.github.mikephil.charting.c.a */
public class C3189a implements C1968d {
    /* renamed from: a */
    protected DecimalFormat f13865a;
    /* renamed from: b */
    protected int f13866b = 0;

    public C3189a(int i) {
        int i2 = 0;
        this.f13866b = i;
        StringBuffer stringBuffer = new StringBuffer();
        while (i2 < i) {
            if (i2 == 0) {
                stringBuffer.append(".");
            }
            stringBuffer.append("0");
            i2++;
        }
        this.f13865a = new DecimalFormat("###,###,###,##0" + stringBuffer.toString());
    }

    /* renamed from: a */
    public String mo3334a(float f, C1476a c1476a) {
        return this.f13865a.format((double) f);
    }

    /* renamed from: a */
    public int m15303a() {
        return this.f13866b;
    }
}
