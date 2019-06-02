package com.baidu.location.p043b;

import java.util.Locale;

/* renamed from: com.baidu.location.b.a */
public class C1072a {
    /* renamed from: a */
    public int f2557a;
    /* renamed from: b */
    public int f2558b;
    /* renamed from: c */
    public int f2559c;
    /* renamed from: d */
    public int f2560d;
    /* renamed from: e */
    public int f2561e;
    /* renamed from: f */
    public int f2562f;
    /* renamed from: g */
    public long f2563g;
    /* renamed from: h */
    public int f2564h;
    /* renamed from: i */
    public char f2565i;
    /* renamed from: j */
    private boolean f2566j;

    public C1072a() {
        this.f2557a = -1;
        this.f2558b = -1;
        this.f2559c = -1;
        this.f2560d = -1;
        this.f2561e = Integer.MAX_VALUE;
        this.f2562f = Integer.MAX_VALUE;
        this.f2563g = 0;
        this.f2564h = -1;
        this.f2565i = '0';
        this.f2566j = false;
        this.f2563g = System.currentTimeMillis();
    }

    public C1072a(int i, int i2, int i3, int i4, int i5, char c) {
        this.f2557a = -1;
        this.f2558b = -1;
        this.f2559c = -1;
        this.f2560d = -1;
        this.f2561e = Integer.MAX_VALUE;
        this.f2562f = Integer.MAX_VALUE;
        this.f2563g = 0;
        this.f2564h = -1;
        this.f2565i = '0';
        this.f2566j = false;
        this.f2557a = i;
        this.f2558b = i2;
        this.f2559c = i3;
        this.f2560d = i4;
        this.f2564h = i5;
        this.f2565i = c;
        this.f2563g = System.currentTimeMillis();
    }

    public C1072a(C1072a c1072a) {
        this(c1072a.f2557a, c1072a.f2558b, c1072a.f2559c, c1072a.f2560d, c1072a.f2564h, c1072a.f2565i);
        this.f2563g = c1072a.f2563g;
    }

    /* renamed from: a */
    public boolean m3853a() {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis - this.f2563g > 0 && currentTimeMillis - this.f2563g < 3000;
    }

    /* renamed from: a */
    public boolean m3854a(C1072a c1072a) {
        return this.f2557a == c1072a.f2557a && this.f2558b == c1072a.f2558b && this.f2560d == c1072a.f2560d && this.f2559c == c1072a.f2559c;
    }

    /* renamed from: b */
    public boolean m3855b() {
        return this.f2557a > -1 && this.f2558b > 0;
    }

    /* renamed from: c */
    public boolean m3856c() {
        return this.f2557a == -1 && this.f2558b == -1 && this.f2560d == -1 && this.f2559c == -1;
    }

    /* renamed from: d */
    public boolean m3857d() {
        return this.f2557a > -1 && this.f2558b > -1 && this.f2560d == -1 && this.f2559c == -1;
    }

    /* renamed from: e */
    public boolean m3858e() {
        return this.f2557a > -1 && this.f2558b > -1 && this.f2560d > -1 && this.f2559c > -1;
    }

    /* renamed from: f */
    public void m3859f() {
        this.f2566j = true;
    }

    /* renamed from: g */
    public String m3860g() {
        StringBuffer stringBuffer = new StringBuffer(128);
        stringBuffer.append("&nw=");
        stringBuffer.append(this.f2565i);
        stringBuffer.append(String.format(Locale.CHINA, "&cl=%d|%d|%d|%d&cl_s=%d", new Object[]{Integer.valueOf(this.f2559c), Integer.valueOf(this.f2560d), Integer.valueOf(this.f2557a), Integer.valueOf(this.f2558b), Integer.valueOf(this.f2564h)}));
        if (this.f2566j) {
            stringBuffer.append("&newcl=1");
        }
        return stringBuffer.toString();
    }
}
