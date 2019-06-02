package com.baidu.mapapi.map;

import android.graphics.Point;

/* renamed from: com.baidu.mapapi.map.f */
class C1121f {
    /* renamed from: a */
    public final double f3243a;
    /* renamed from: b */
    public final double f3244b;
    /* renamed from: c */
    public final double f3245c;
    /* renamed from: d */
    public final double f3246d;
    /* renamed from: e */
    public final double f3247e;
    /* renamed from: f */
    public final double f3248f;

    public C1121f(double d, double d2, double d3, double d4) {
        this.f3243a = d;
        this.f3244b = d3;
        this.f3245c = d2;
        this.f3246d = d4;
        this.f3247e = (d + d2) / 2.0d;
        this.f3248f = (d3 + d4) / 2.0d;
    }

    /* renamed from: a */
    public boolean m4283a(double d, double d2) {
        return this.f3243a <= d && d <= this.f3245c && this.f3244b <= d2 && d2 <= this.f3246d;
    }

    /* renamed from: a */
    public boolean m4284a(double d, double d2, double d3, double d4) {
        return d < this.f3245c && this.f3243a < d2 && d3 < this.f3246d && this.f3244b < d4;
    }

    /* renamed from: a */
    public boolean m4285a(Point point) {
        return m4283a((double) point.x, (double) point.y);
    }

    /* renamed from: a */
    public boolean m4286a(C1121f c1121f) {
        return m4284a(c1121f.f3243a, c1121f.f3245c, c1121f.f3244b, c1121f.f3246d);
    }

    /* renamed from: b */
    public boolean m4287b(C1121f c1121f) {
        return c1121f.f3243a >= this.f3243a && c1121f.f3245c <= this.f3245c && c1121f.f3244b >= this.f3244b && c1121f.f3246d <= this.f3246d;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("minX: " + this.f3243a);
        stringBuilder.append(" minY: " + this.f3244b);
        stringBuilder.append(" maxX: " + this.f3245c);
        stringBuilder.append(" maxY: " + this.f3246d);
        stringBuilder.append(" midX: " + this.f3247e);
        stringBuilder.append(" midY: " + this.f3248f);
        return stringBuilder.toString();
    }
}
