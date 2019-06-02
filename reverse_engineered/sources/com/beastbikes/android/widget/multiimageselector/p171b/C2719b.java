package com.beastbikes.android.widget.multiimageselector.p171b;

/* compiled from: Image */
/* renamed from: com.beastbikes.android.widget.multiimageselector.b.b */
public class C2719b {
    /* renamed from: a */
    public String f12695a;
    /* renamed from: b */
    public String f12696b;
    /* renamed from: c */
    public long f12697c;

    public C2719b(String str, String str2, long j) {
        this.f12695a = str;
        this.f12696b = str2;
        this.f12697c = j;
    }

    public boolean equals(Object obj) {
        if (obj == null || this.f12695a == null) {
            return false;
        }
        try {
            return this.f12695a.equalsIgnoreCase(((C2719b) obj).f12695a);
        } catch (ClassCastException e) {
            e.printStackTrace();
            return super.equals(obj);
        }
    }

    public int hashCode() {
        return super.hashCode();
    }
}
