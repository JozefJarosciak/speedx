package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.annotations.SerializedName;

/* compiled from: EventNamespace */
/* renamed from: com.twitter.sdk.android.core.internal.scribe.c */
public class C1502c {
    @SerializedName("client")
    /* renamed from: a */
    public final String f7057a;
    @SerializedName("page")
    /* renamed from: b */
    public final String f7058b;
    @SerializedName("section")
    /* renamed from: c */
    public final String f7059c;
    @SerializedName("component")
    /* renamed from: d */
    public final String f7060d;
    @SerializedName("element")
    /* renamed from: e */
    public final String f7061e;
    @SerializedName("action")
    /* renamed from: f */
    public final String f7062f;

    public C1502c(String str, String str2, String str3, String str4, String str5, String str6) {
        this.f7057a = str;
        this.f7058b = str2;
        this.f7059c = str3;
        this.f7060d = str4;
        this.f7061e = str5;
        this.f7062f = str6;
    }

    public String toString() {
        return "client=" + this.f7057a + ", page=" + this.f7058b + ", section=" + this.f7059c + ", component=" + this.f7060d + ", element=" + this.f7061e + ", action=" + this.f7062f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C1502c c1502c = (C1502c) obj;
        if (this.f7062f == null ? c1502c.f7062f != null : !this.f7062f.equals(c1502c.f7062f)) {
            return false;
        }
        if (this.f7057a == null ? c1502c.f7057a != null : !this.f7057a.equals(c1502c.f7057a)) {
            return false;
        }
        if (this.f7060d == null ? c1502c.f7060d != null : !this.f7060d.equals(c1502c.f7060d)) {
            return false;
        }
        if (this.f7061e == null ? c1502c.f7061e != null : !this.f7061e.equals(c1502c.f7061e)) {
            return false;
        }
        if (this.f7058b == null ? c1502c.f7058b != null : !this.f7058b.equals(c1502c.f7058b)) {
            return false;
        }
        if (this.f7059c != null) {
            if (this.f7059c.equals(c1502c.f7059c)) {
                return true;
            }
        } else if (c1502c.f7059c == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (this.f7057a != null ? this.f7057a.hashCode() : 0) * 31;
        if (this.f7058b != null) {
            hashCode = this.f7058b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.f7059c != null) {
            hashCode = this.f7059c.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.f7060d != null) {
            hashCode = this.f7060d.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.f7061e != null) {
            hashCode = this.f7061e.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.f7062f != null) {
            i = this.f7062f.hashCode();
        }
        return hashCode + i;
    }
}
