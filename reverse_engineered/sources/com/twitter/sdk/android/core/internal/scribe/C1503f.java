package com.twitter.sdk.android.core.internal.scribe;

import android.text.TextUtils;
import com.google.gson.annotations.SerializedName;
import java.util.Collections;
import java.util.List;

/* compiled from: ScribeEvent */
/* renamed from: com.twitter.sdk.android.core.internal.scribe.f */
public class C1503f {
    @SerializedName("event_namespace")
    /* renamed from: a */
    final C1502c f7063a;
    @SerializedName("ts")
    /* renamed from: b */
    final String f7064b;
    @SerializedName("format_version")
    /* renamed from: c */
    final String f7065c = "2";
    @SerializedName("_category_")
    /* renamed from: d */
    final String f7066d;
    @SerializedName("items")
    /* renamed from: e */
    final List<C1506j> f7067e;

    public C1503f(String str, C1502c c1502c, long j, List<C1506j> list) {
        this.f7066d = str;
        this.f7063a = c1502c;
        this.f7064b = String.valueOf(j);
        this.f7067e = Collections.unmodifiableList(list);
    }

    public String toString() {
        return "event_namespace=" + this.f7063a + ", ts=" + this.f7064b + ", format_version=" + this.f7065c + ", _category_=" + this.f7066d + ", items=" + ("[" + TextUtils.join(", ", this.f7067e) + "]");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C1503f c1503f = (C1503f) obj;
        if (this.f7066d == null ? c1503f.f7066d != null : !this.f7066d.equals(c1503f.f7066d)) {
            return false;
        }
        if (this.f7063a == null ? c1503f.f7063a != null : !this.f7063a.equals(c1503f.f7063a)) {
            return false;
        }
        if (this.f7065c == null ? c1503f.f7065c != null : !this.f7065c.equals(c1503f.f7065c)) {
            return false;
        }
        if (this.f7064b == null ? c1503f.f7064b != null : !this.f7064b.equals(c1503f.f7064b)) {
            return false;
        }
        if (this.f7067e != null) {
            if (this.f7067e.equals(c1503f.f7067e)) {
                return true;
            }
        } else if (c1503f.f7067e == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (this.f7063a != null ? this.f7063a.hashCode() : 0) * 31;
        if (this.f7064b != null) {
            hashCode = this.f7064b.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.f7065c != null) {
            hashCode = this.f7065c.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.f7066d != null) {
            hashCode = this.f7066d.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.f7067e != null) {
            i = this.f7067e.hashCode();
        }
        return hashCode + i;
    }
}
