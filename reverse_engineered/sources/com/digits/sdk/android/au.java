package com.digits.sdk.android;

import com.google.gson.annotations.SerializedName;

/* compiled from: Email */
public class au {
    @SerializedName("address")
    /* renamed from: a */
    final String f6884a;
    @SerializedName("is_verified")
    /* renamed from: b */
    final boolean f6885b;

    au(String str, boolean z) {
        this.f6884a = str;
        this.f6885b = z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        au auVar = (au) obj;
        if (this.f6885b == auVar.f6885b && this.f6884a.equals(auVar.f6884a)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.f6885b ? 1 : 0) + (this.f6884a.hashCode() * 31);
    }
}
