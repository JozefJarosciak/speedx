package com.journeyapps.barcodescanner;

/* compiled from: Size */
/* renamed from: com.journeyapps.barcodescanner.l */
public class C4168l implements Comparable<C4168l> {
    /* renamed from: a */
    public final int f14828a;
    /* renamed from: b */
    public final int f14829b;

    public /* synthetic */ int compareTo(Object obj) {
        return m16696c((C4168l) obj);
    }

    public C4168l(int i, int i2) {
        this.f14828a = i;
        this.f14829b = i2;
    }

    /* renamed from: a */
    public C4168l m16693a() {
        return new C4168l(this.f14829b, this.f14828a);
    }

    /* renamed from: a */
    public C4168l m16694a(C4168l c4168l) {
        if (this.f14828a * c4168l.f14829b >= c4168l.f14828a * this.f14829b) {
            return new C4168l(c4168l.f14828a, (this.f14829b * c4168l.f14828a) / this.f14828a);
        }
        return new C4168l((this.f14828a * c4168l.f14829b) / this.f14829b, c4168l.f14829b);
    }

    /* renamed from: b */
    public C4168l m16695b(C4168l c4168l) {
        if (this.f14828a * c4168l.f14829b <= c4168l.f14828a * this.f14829b) {
            return new C4168l(c4168l.f14828a, (this.f14829b * c4168l.f14828a) / this.f14828a);
        }
        return new C4168l((this.f14828a * c4168l.f14829b) / this.f14829b, c4168l.f14829b);
    }

    /* renamed from: c */
    public int m16696c(C4168l c4168l) {
        int i = this.f14829b * this.f14828a;
        int i2 = c4168l.f14829b * c4168l.f14828a;
        if (i2 < i) {
            return 1;
        }
        if (i2 > i) {
            return -1;
        }
        return 0;
    }

    public String toString() {
        return this.f14828a + "x" + this.f14829b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C4168l c4168l = (C4168l) obj;
        if (this.f14828a != c4168l.f14828a) {
            return false;
        }
        if (this.f14829b != c4168l.f14829b) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.f14828a * 31) + this.f14829b;
    }
}
