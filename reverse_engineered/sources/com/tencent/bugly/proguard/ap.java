package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public final class ap extends C4447j {
    /* renamed from: i */
    private static Map<String, String> f15631i = new HashMap();
    /* renamed from: a */
    public long f15632a = 0;
    /* renamed from: b */
    public byte f15633b = (byte) 0;
    /* renamed from: c */
    public String f15634c = "";
    /* renamed from: d */
    public String f15635d = "";
    /* renamed from: e */
    public String f15636e = "";
    /* renamed from: f */
    public Map<String, String> f15637f = null;
    /* renamed from: g */
    public String f15638g = "";
    /* renamed from: h */
    public boolean f15639h = true;

    /* renamed from: a */
    public final void mo6071a(C4456i c4456i) {
        c4456i.m17633a(this.f15632a, 0);
        c4456i.m17631a(this.f15633b, 1);
        if (this.f15634c != null) {
            c4456i.m17636a(this.f15634c, 2);
        }
        if (this.f15635d != null) {
            c4456i.m17636a(this.f15635d, 3);
        }
        if (this.f15636e != null) {
            c4456i.m17636a(this.f15636e, 4);
        }
        if (this.f15637f != null) {
            c4456i.m17638a(this.f15637f, 5);
        }
        if (this.f15638g != null) {
            c4456i.m17636a(this.f15638g, 6);
        }
        c4456i.m17640a(this.f15639h, 7);
    }

    static {
        f15631i.put("", "");
    }

    /* renamed from: a */
    public final void mo6070a(C4455h c4455h) {
        this.f15632a = c4455h.m17618a(this.f15632a, 0, true);
        this.f15633b = c4455h.m17615a(this.f15633b, 1, true);
        this.f15634c = c4455h.m17625b(2, false);
        this.f15635d = c4455h.m17625b(3, false);
        this.f15636e = c4455h.m17625b(4, false);
        this.f15637f = (Map) c4455h.m17620a(f15631i, 5, false);
        this.f15638g = c4455h.m17625b(6, false);
        boolean z = this.f15639h;
        this.f15639h = c4455h.m17624a(7, false);
    }
}
