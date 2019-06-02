package com.tencent.bugly.crashreport.crash;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.a */
public final class C4423a implements Comparable<C4423a> {
    /* renamed from: a */
    public long f15400a = -1;
    /* renamed from: b */
    public long f15401b = -1;
    /* renamed from: c */
    public String f15402c = null;
    /* renamed from: d */
    public boolean f15403d = false;
    /* renamed from: e */
    public boolean f15404e = false;
    /* renamed from: f */
    public int f15405f = 0;

    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        C4423a c4423a = (C4423a) obj;
        if (c4423a != null) {
            long j = this.f15401b - c4423a.f15401b;
            if (j <= 0) {
                return j < 0 ? -1 : 0;
            }
        }
        return 1;
    }
}
