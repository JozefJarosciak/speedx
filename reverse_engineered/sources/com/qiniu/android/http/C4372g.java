package com.qiniu.android.http;

import java.util.Locale;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/* compiled from: ResponseInfo */
/* renamed from: com.qiniu.android.http.g */
public final class C4372g {
    /* renamed from: a */
    public final int f15168a;
    /* renamed from: b */
    public final String f15169b;
    /* renamed from: c */
    public final String f15170c;
    /* renamed from: d */
    public final String f15171d;
    /* renamed from: e */
    public final String f15172e;
    /* renamed from: f */
    public final double f15173f;
    /* renamed from: g */
    public final String f15174g;
    /* renamed from: h */
    public final String f15175h;
    /* renamed from: i */
    public final int f15176i;
    /* renamed from: j */
    public final String f15177j;
    /* renamed from: k */
    public final String f15178k = C4374i.m17208a().f15183a;
    /* renamed from: l */
    public final long f15179l = (System.currentTimeMillis() / 1000);
    /* renamed from: m */
    public final long f15180m;
    /* renamed from: n */
    private final JSONObject f15181n;

    public C4372g(JSONObject jSONObject, int i, String str, String str2, String str3, String str4, String str5, String str6, int i2, double d, long j, String str7) {
        this.f15181n = jSONObject;
        this.f15168a = i;
        this.f15169b = str;
        this.f15170c = str2;
        this.f15171d = str3;
        this.f15174g = str4;
        this.f15177j = str5;
        this.f15173f = d;
        this.f15172e = str7;
        this.f15175h = str6;
        this.f15176i = i2;
        this.f15180m = j;
    }

    /* renamed from: a */
    public static C4372g m17194a() {
        return new C4372g(null, -6, "", "", "", "", "", "", -1, 0.0d, 0, "file or data size is zero");
    }

    /* renamed from: b */
    public static C4372g m17197b() {
        return new C4372g(null, -2, "", "", "", "", "", "", -1, 0.0d, 0, "cancelled by user");
    }

    /* renamed from: a */
    public static C4372g m17196a(String str) {
        return new C4372g(null, -4, "", "", "", "", "", "", -1, 0.0d, 0, str);
    }

    /* renamed from: b */
    public static C4372g m17198b(String str) {
        return new C4372g(null, -5, "", "", "", "", "", "", -1, 0.0d, 0, str);
    }

    /* renamed from: a */
    public static C4372g m17195a(Exception exception) {
        return new C4372g(null, -3, "", "", "", "", "", "", -1, 0.0d, 0, exception.getMessage());
    }

    /* renamed from: c */
    public boolean m17199c() {
        return this.f15168a == -2;
    }

    /* renamed from: d */
    public boolean m17200d() {
        return this.f15168a == 200 && this.f15172e == null && (m17206j() || this.f15181n != null);
    }

    /* renamed from: e */
    public boolean m17201e() {
        return this.f15168a == -1 || this.f15168a == -1003 || this.f15168a == -1004 || this.f15168a == -1001 || this.f15168a == -1005;
    }

    /* renamed from: f */
    public boolean m17202f() {
        return (this.f15168a >= 500 && this.f15168a < 600 && this.f15168a != 579) || this.f15168a == 996;
    }

    /* renamed from: g */
    public boolean m17203g() {
        return m17201e() || m17202f();
    }

    /* renamed from: h */
    public boolean m17204h() {
        return !m17199c() && (m17203g() || this.f15168a == HttpStatus.SC_NOT_ACCEPTABLE || (this.f15168a == 200 && this.f15172e != null));
    }

    /* renamed from: i */
    public boolean m17205i() {
        return this.f15168a < 500 && this.f15168a >= 200 && !m17206j() && this.f15181n == null;
    }

    public String toString() {
        return String.format(Locale.ENGLISH, "{ver:%s,ResponseInfo:%s,status:%d, reqId:%s, xlog:%s, xvia:%s, host:%s, path:%s, ip:%s, port:%d, duration:%f s, time:%d, sent:%d,error:%s}", new Object[]{"7.2.0", this.f15178k, Integer.valueOf(this.f15168a), this.f15169b, this.f15170c, this.f15171d, this.f15174g, this.f15177j, this.f15175h, Integer.valueOf(this.f15176i), Double.valueOf(this.f15173f), Long.valueOf(this.f15179l), Long.valueOf(this.f15180m), this.f15172e});
    }

    /* renamed from: j */
    public boolean m17206j() {
        return this.f15169b != null;
    }
}
