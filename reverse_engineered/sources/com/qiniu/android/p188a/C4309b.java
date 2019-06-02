package com.qiniu.android.p188a;

/* compiled from: Zone */
/* renamed from: com.qiniu.android.a.b */
public final class C4309b {
    /* renamed from: a */
    public static final C4309b f15017a = C4309b.m17045a("upload.qiniu.com", "up.qiniu.com", "183.136.139.10", "115.231.182.136");
    /* renamed from: b */
    public static final C4309b f15018b = C4309b.m17045a("upload-z1.qiniu.com", "up-z1.qiniu.com", "106.38.227.27", "106.38.227.28");
    /* renamed from: c */
    public final C4308a f15019c;
    /* renamed from: d */
    public final C4308a f15020d;

    public C4309b(C4308a c4308a, C4308a c4308a2) {
        this.f15019c = c4308a;
        this.f15020d = c4308a2;
    }

    /* renamed from: a */
    private static C4309b m17045a(String str, String str2, String str3, String str4) {
        String[] strArr = new String[]{str3, str4};
        return new C4309b(new C4308a("http://" + str, strArr), new C4308a("http://" + str2, strArr));
    }
}
