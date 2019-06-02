package com.digits.sdk.android;

import java.text.Collator;
import java.util.Locale;

/* compiled from: CountryInfo */
/* renamed from: com.digits.sdk.android.w */
class C2935w implements Comparable<C2935w> {
    /* renamed from: a */
    public final String f13350a;
    /* renamed from: b */
    public final int f13351b;
    /* renamed from: c */
    private final Collator f13352c = Collator.getInstance(Locale.getDefault());

    public /* synthetic */ int compareTo(Object obj) {
        return m14259a((C2935w) obj);
    }

    public C2935w(String str, int i) {
        this.f13352c.setStrength(0);
        this.f13350a = str;
        this.f13351b = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C2935w c2935w = (C2935w) obj;
        if (this.f13351b == c2935w.f13351b) {
            if (this.f13350a != null) {
                if (this.f13350a.equals(c2935w.f13350a)) {
                    return true;
                }
            } else if (c2935w.f13350a == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((this.f13350a != null ? this.f13350a.hashCode() : 0) * 31) + this.f13351b;
    }

    public String toString() {
        return this.f13350a + " +" + this.f13351b;
    }

    /* renamed from: a */
    public int m14259a(C2935w c2935w) {
        return this.f13352c.compare(this.f13350a, c2935w.f13350a);
    }
}
