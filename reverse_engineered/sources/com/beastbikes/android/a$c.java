package com.beastbikes.android;

/* compiled from: Constants */
public final class a$c {
    /* renamed from: a */
    public static final String f7200a = m8384a();
    /* renamed from: b */
    public static final String f7201b = m8385b();
    /* renamed from: c */
    public static final String f7202c = m8386c();
    /* renamed from: d */
    public static final String f7203d = m8388e();
    /* renamed from: e */
    public static final String f7204e = m8387d();

    /* renamed from: a */
    static String m8384a() {
        return BeastBikes.getHost();
    }

    /* renamed from: b */
    static String m8385b() {
        return BeastBikes.getHostDomain();
    }

    /* renamed from: c */
    static String m8386c() {
        return BeastBikes.getApiUrl();
    }

    /* renamed from: d */
    static String m8387d() {
        return "https://account.speedx.com/m/products";
    }

    /* renamed from: e */
    static String m8388e() {
        return "http://static.speedx.com/speedforce/update.json?t=" + System.currentTimeMillis();
    }

    /* renamed from: f */
    static String m8389f() {
        return "http://merchant.speedx.com";
    }
}
