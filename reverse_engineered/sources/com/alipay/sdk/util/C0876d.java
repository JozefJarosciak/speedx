package com.alipay.sdk.util;

/* renamed from: com.alipay.sdk.util.d */
public enum C0876d {
    WIFI(0, "WIFI"),
    NETWORK_TYPE_1(1, "unicom2G"),
    NETWORK_TYPE_2(2, "mobile2G"),
    NETWORK_TYPE_4(4, "telecom2G"),
    NETWORK_TYPE_5(5, "telecom3G"),
    NETWORK_TYPE_6(6, "telecom3G"),
    NETWORK_TYPE_12(12, "telecom3G"),
    NETWORK_TYPE_8(8, "unicom3G"),
    NETWORK_TYPE_3(3, "unicom3G"),
    NETWORK_TYPE_13(13, "LTE"),
    NETWORK_TYPE_11(11, "IDEN"),
    NETWORK_TYPE_9(9, "HSUPA"),
    NETWORK_TYPE_10(10, "HSPA"),
    NETWORK_TYPE_15(15, "HSPAP"),
    NONE(-1, "none");
    
    /* renamed from: p */
    public String f2207p;
    /* renamed from: q */
    private int f2208q;

    private C0876d(int i, String str) {
        this.f2208q = i;
        this.f2207p = str;
    }

    /* renamed from: a */
    private int m3435a() {
        return this.f2208q;
    }

    /* renamed from: b */
    private String m3437b() {
        return this.f2207p;
    }

    /* renamed from: a */
    public static C0876d m3436a(int i) {
        for (C0876d c0876d : C0876d.values()) {
            if (c0876d.f2208q == i) {
                return c0876d;
            }
        }
        return NONE;
    }
}
