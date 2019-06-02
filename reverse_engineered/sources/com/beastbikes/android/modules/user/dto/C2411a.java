package com.beastbikes.android.modules.user.dto;

import com.alipay.sdk.cons.C0844a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample;
import com.beastbikes.android.modules.cycling.p111a.C1886b;
import org.json.JSONObject;

/* compiled from: SampleDTO */
/* renamed from: com.beastbikes.android.modules.user.dto.a */
public class C2411a implements C1886b {
    /* renamed from: a */
    private double f11398a;
    /* renamed from: b */
    private double f11399b;
    /* renamed from: c */
    private double f11400c;
    /* renamed from: d */
    private double f11401d;
    /* renamed from: e */
    private double f11402e;
    /* renamed from: f */
    private long f11403f;
    /* renamed from: g */
    private double f11404g;
    /* renamed from: h */
    private double f11405h;
    /* renamed from: i */
    private double f11406i;
    /* renamed from: j */
    private double f11407j;
    /* renamed from: k */
    private double f11408k;
    /* renamed from: l */
    private int f11409l;
    /* renamed from: m */
    private boolean f11410m;
    /* renamed from: n */
    private double f11411n;

    public C2411a(LocalActivitySample localActivitySample) {
        boolean z = true;
        this.f11400c = Double.parseDouble(localActivitySample.getLatitude1());
        this.f11401d = Double.parseDouble(localActivitySample.getLongitude1());
        this.f11402e = Double.parseDouble(localActivitySample.getAltitude());
        this.f11403f = localActivitySample.getCurrTime();
        this.f11404g = localActivitySample.getDistance();
        this.f11405h = localActivitySample.getVelocity();
        this.f11406i = localActivitySample.getCalorie();
        this.f11407j = localActivitySample.getCardiacRate();
        this.f11408k = localActivitySample.getCadence();
        this.f11409l = localActivitySample.getCyclingStatus();
        if (localActivitySample.getIsRepair() != 1) {
            z = false;
        }
        this.f11410m = z;
        this.f11411n = localActivitySample.getPower();
    }

    public C2411a(JSONObject jSONObject) {
        boolean z = true;
        if (jSONObject.has(C1919a.f8574a)) {
            this.f11398a = jSONObject.optDouble(C1919a.f8574a);
            this.f11399b = jSONObject.optDouble(C1919a.f8575b);
            this.f11400c = jSONObject.optDouble(C1919a.f8576c);
            this.f11401d = jSONObject.optDouble(C1919a.f8577d);
            this.f11402e = jSONObject.optDouble(C1919a.f8578e);
            this.f11403f = (long) (jSONObject.optDouble(C1919a.f8579f) * 1000.0d);
            this.f11404g = jSONObject.optDouble(C1919a.f8580g);
            this.f11405h = jSONObject.optDouble(C1919a.f8581h);
            this.f11406i = jSONObject.optDouble(C1919a.f8582i);
            this.f11407j = jSONObject.optDouble(C1919a.f8583j);
            this.f11408k = jSONObject.optDouble(C1919a.f8584k);
            this.f11409l = jSONObject.optInt(C1919a.f8585l);
            if (jSONObject.optInt(C1919a.f8586m) != 1) {
                z = false;
            }
            this.f11410m = z;
            this.f11411n = jSONObject.optDouble(C1919a.f8587n);
            return;
        }
        this.f11398a = jSONObject.optDouble(C0844a.f2048d);
        this.f11399b = jSONObject.optDouble("2");
        this.f11400c = jSONObject.optDouble("3");
        this.f11401d = jSONObject.optDouble("4");
        this.f11402e = jSONObject.optDouble("5");
        this.f11403f = jSONObject.optLong("6");
        this.f11404g = jSONObject.optDouble("7");
        this.f11405h = jSONObject.optDouble("8");
        this.f11406i = jSONObject.optDouble("9");
        this.f11407j = jSONObject.optDouble("10");
        this.f11408k = jSONObject.optDouble("11");
        if (jSONObject.optInt("12") != 1) {
            z = false;
        }
        this.f11410m = z;
        this.f11411n = jSONObject.optDouble("13");
    }

    /* renamed from: c */
    public double m12225c() {
        return this.f11398a;
    }

    /* renamed from: d */
    public double m12227d() {
        return this.f11399b;
    }

    /* renamed from: e */
    public double m12229e() {
        return this.f11400c;
    }

    /* renamed from: f */
    public double m12230f() {
        return this.f11401d;
    }

    /* renamed from: g */
    public double m12231g() {
        return this.f11402e;
    }

    /* renamed from: h */
    public long m12232h() {
        return this.f11403f;
    }

    /* renamed from: i */
    public double m12233i() {
        return this.f11404g;
    }

    /* renamed from: j */
    public double m12234j() {
        return this.f11405h;
    }

    /* renamed from: a */
    public void m12222a(double d) {
        this.f11405h = d;
    }

    /* renamed from: k */
    public double m12235k() {
        return this.f11406i;
    }

    /* renamed from: l */
    public double m12236l() {
        return this.f11407j;
    }

    /* renamed from: b */
    public void m12224b(double d) {
        this.f11407j = d;
    }

    /* renamed from: m */
    public double m12237m() {
        return this.f11408k;
    }

    /* renamed from: c */
    public void m12226c(double d) {
        this.f11408k = d;
    }

    /* renamed from: n */
    public int m12238n() {
        return this.f11409l;
    }

    /* renamed from: o */
    public boolean m12239o() {
        return this.f11410m;
    }

    /* renamed from: p */
    public double m12240p() {
        return this.f11411n;
    }

    /* renamed from: d */
    public void m12228d(double d) {
        this.f11411n = d;
    }

    /* renamed from: a */
    public double mo3263a() {
        return this.f11400c;
    }

    /* renamed from: b */
    public double mo3264b() {
        return this.f11401d;
    }
}
