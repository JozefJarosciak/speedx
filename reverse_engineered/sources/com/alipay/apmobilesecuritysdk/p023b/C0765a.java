package com.alipay.apmobilesecuritysdk.p023b;

import com.alipay.p029b.p030a.p031a.p032a.C0789a;

/* renamed from: com.alipay.apmobilesecuritysdk.b.a */
public final class C0765a {
    /* renamed from: b */
    private static C0765a f1814b = new C0765a();
    /* renamed from: a */
    private int f1815a = 0;

    /* renamed from: a */
    public static C0765a m2920a() {
        return f1814b;
    }

    /* renamed from: a */
    public final void m2921a(int i) {
        this.f1815a = i;
    }

    /* renamed from: b */
    public final int m2922b() {
        return this.f1815a;
    }

    /* renamed from: c */
    public final String m2923c() {
        if (C0789a.m3023b(null)) {
            return null;
        }
        switch (this.f1815a) {
            case 1:
                return "http://mobilegw.stable.alipay.net/mgw.htm";
            case 2:
                return "https://mobilegw.alipay.com/mgw.htm";
            case 3:
                return "http://mobilegw-1-64.test.alipay.net/mgw.htm";
            case 4:
                return "http://mobilegw.aaa.alipay.net/mgw.htm";
            default:
                return "https://mobilegw.alipay.com/mgw.htm";
        }
    }
}
