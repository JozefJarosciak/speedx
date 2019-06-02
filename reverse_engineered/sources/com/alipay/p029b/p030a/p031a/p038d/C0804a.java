package com.alipay.p029b.p030a.p031a.p038d;

import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import java.io.File;

/* renamed from: com.alipay.b.a.a.d.a */
public final class C0804a {
    /* renamed from: a */
    public static String m3126a(String str) {
        String str2 = "";
        try {
            str2 = System.getProperty(str);
        } catch (Throwable th) {
        }
        return C0789a.m3020a(str2) ? C0805b.m3127a(".SystemConfig" + File.separator + str) : str2;
    }
}
