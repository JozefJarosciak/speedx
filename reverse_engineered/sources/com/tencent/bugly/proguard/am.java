package com.tencent.bugly.proguard;

import java.util.HashMap;
import java.util.Map;

/* compiled from: BUGLY */
public final class am extends C4447j {
    /* renamed from: i */
    private static byte[] f15604i;
    /* renamed from: j */
    private static Map<String, String> f15605j = new HashMap();
    /* renamed from: a */
    public byte f15606a = (byte) 0;
    /* renamed from: b */
    public int f15607b = 0;
    /* renamed from: c */
    public byte[] f15608c = null;
    /* renamed from: d */
    public String f15609d = "";
    /* renamed from: e */
    public long f15610e = 0;
    /* renamed from: f */
    public String f15611f = "";
    /* renamed from: g */
    public Map<String, String> f15612g = null;
    /* renamed from: h */
    private String f15613h = "";

    /* renamed from: a */
    public final void mo6071a(C4456i c4456i) {
        c4456i.m17631a(this.f15606a, 0);
        c4456i.m17632a(this.f15607b, 1);
        if (this.f15608c != null) {
            c4456i.m17641a(this.f15608c, 2);
        }
        if (this.f15609d != null) {
            c4456i.m17636a(this.f15609d, 3);
        }
        c4456i.m17633a(this.f15610e, 4);
        if (this.f15613h != null) {
            c4456i.m17636a(this.f15613h, 5);
        }
        if (this.f15611f != null) {
            c4456i.m17636a(this.f15611f, 6);
        }
        if (this.f15612g != null) {
            c4456i.m17638a(this.f15612g, 7);
        }
    }

    static {
        byte[] bArr = new byte[1];
        f15604i = bArr;
        bArr[0] = (byte) 0;
        f15605j.put("", "");
    }

    /* renamed from: a */
    public final void mo6070a(C4455h c4455h) {
        this.f15606a = c4455h.m17615a(this.f15606a, 0, true);
        this.f15607b = c4455h.m17616a(this.f15607b, 1, true);
        byte[] bArr = f15604i;
        this.f15608c = c4455h.m17626c(2, false);
        this.f15609d = c4455h.m17625b(3, false);
        this.f15610e = c4455h.m17618a(this.f15610e, 4, false);
        this.f15613h = c4455h.m17625b(5, false);
        this.f15611f = c4455h.m17625b(6, false);
        this.f15612g = (Map) c4455h.m17620a(f15605j, 7, false);
    }
}
