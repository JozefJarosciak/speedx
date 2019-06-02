package com.alipay.android.phone.mrpc.core;

/* renamed from: com.alipay.android.phone.mrpc.core.p */
public final class C0757p extends C0756u {
    /* renamed from: c */
    private int f1777c;
    /* renamed from: d */
    private String f1778d;
    /* renamed from: e */
    private long f1779e;
    /* renamed from: f */
    private long f1780f;
    /* renamed from: g */
    private String f1781g;
    /* renamed from: h */
    private HttpUrlHeader f1782h;

    public C0757p(HttpUrlHeader httpUrlHeader, int i, String str, byte[] bArr) {
        this.f1782h = httpUrlHeader;
        this.f1777c = i;
        this.f1778d = str;
        this.a = bArr;
    }

    /* renamed from: a */
    public final HttpUrlHeader m2890a() {
        return this.f1782h;
    }

    /* renamed from: a */
    public final void m2891a(long j) {
        this.f1779e = j;
    }

    /* renamed from: a */
    public final void m2892a(String str) {
        this.f1781g = str;
    }

    /* renamed from: b */
    public final void m2893b(long j) {
        this.f1780f = j;
    }
}
