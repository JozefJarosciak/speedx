package io.fabric.sdk.android.services.common;

/* compiled from: AdvertisingInfo */
/* renamed from: io.fabric.sdk.android.services.common.b */
class C4865b {
    /* renamed from: a */
    public final String f17148a;
    /* renamed from: b */
    public final boolean f17149b;

    C4865b(String str, boolean z) {
        this.f17148a = str;
        this.f17149b = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C4865b c4865b = (C4865b) obj;
        if (this.f17149b != c4865b.f17149b) {
            return false;
        }
        if (this.f17148a != null) {
            if (this.f17148a.equals(c4865b.f17148a)) {
                return true;
            }
        } else if (c4865b.f17148a == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        if (this.f17148a != null) {
            hashCode = this.f17148a.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode *= 31;
        if (this.f17149b) {
            i = 1;
        }
        return hashCode + i;
    }
}
