package com.tencent.bugly.proguard;

/* compiled from: BUGLY */
public final class ai extends C4447j implements Cloneable {
    /* renamed from: d */
    private static byte[] f15543d;
    /* renamed from: a */
    private byte f15544a = (byte) 0;
    /* renamed from: b */
    private String f15545b = "";
    /* renamed from: c */
    private byte[] f15546c = null;

    public ai(byte b, String str, byte[] bArr) {
        this.f15544a = b;
        this.f15545b = str;
        this.f15546c = bArr;
    }

    /* renamed from: a */
    public final void mo6071a(C4456i c4456i) {
        c4456i.m17631a(this.f15544a, 0);
        c4456i.m17636a(this.f15545b, 1);
        if (this.f15546c != null) {
            c4456i.m17641a(this.f15546c, 2);
        }
    }

    /* renamed from: a */
    public final void mo6070a(C4455h c4455h) {
        byte[] bArr;
        this.f15544a = c4455h.m17615a(this.f15544a, 0, true);
        this.f15545b = c4455h.m17625b(1, true);
        if (f15543d == null) {
            bArr = new byte[1];
            f15543d = bArr;
            bArr[0] = (byte) 0;
        }
        bArr = f15543d;
        this.f15546c = c4455h.m17626c(2, false);
    }

    /* renamed from: a */
    public final void mo6072a(StringBuilder stringBuilder, int i) {
    }
}
