package com.beastbikes.android.utils;

import java.math.BigDecimal;

/* compiled from: SpeedXFormatUtil */
/* renamed from: com.beastbikes.android.utils.v */
public class C2579v {
    /* renamed from: a */
    public static String m12904a(double d) {
        String str = "";
        if (d >= 10.0d) {
            return str + ((int) d);
        }
        return new BigDecimal(d).setScale(1, 4) + "";
    }
}
