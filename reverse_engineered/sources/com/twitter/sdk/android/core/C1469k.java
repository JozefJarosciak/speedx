package com.twitter.sdk.android.core;

import com.google.gson.annotations.SerializedName;

/* compiled from: Session */
/* renamed from: com.twitter.sdk.android.core.k */
public class C1469k<T extends C1500b> {
    @SerializedName("auth_token")
    /* renamed from: a */
    private final T f6874a;
    @SerializedName("id")
    /* renamed from: b */
    private final long f6875b;

    public C1469k(T t, long j) {
        this.f6874a = t;
        this.f6875b = j;
    }

    /* renamed from: d */
    public T m8100d() {
        return this.f6874a;
    }

    /* renamed from: e */
    public long m8101e() {
        return this.f6875b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C1469k c1469k = (C1469k) obj;
        if (this.f6875b != c1469k.f6875b) {
            return false;
        }
        if (this.f6874a != null) {
            if (this.f6874a.equals(c1469k.f6874a)) {
                return true;
            }
        } else if (c1469k.f6874a == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.f6874a != null ? this.f6874a.hashCode() : 0) * 31) + ((int) (this.f6875b ^ (this.f6875b >>> 32)));
    }
}
