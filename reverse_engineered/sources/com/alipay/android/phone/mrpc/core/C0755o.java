package com.alipay.android.phone.mrpc.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

/* renamed from: com.alipay.android.phone.mrpc.core.o */
public final class C0755o extends C0754t {
    /* renamed from: b */
    private String f1769b;
    /* renamed from: c */
    private byte[] f1770c;
    /* renamed from: d */
    private String f1771d = "application/x-www-form-urlencoded";
    /* renamed from: e */
    private ArrayList<Header> f1772e = new ArrayList();
    /* renamed from: f */
    private Map<String, String> f1773f = new HashMap();
    /* renamed from: g */
    private boolean f1774g;

    public C0755o(String str) {
        this.f1769b = str;
    }

    /* renamed from: a */
    public final String m2877a() {
        return this.f1769b;
    }

    /* renamed from: a */
    public final void m2878a(String str) {
        this.f1771d = str;
    }

    /* renamed from: a */
    public final void m2879a(String str, String str2) {
        if (this.f1773f == null) {
            this.f1773f = new HashMap();
        }
        this.f1773f.put(str, str2);
    }

    /* renamed from: a */
    public final void m2880a(Header header) {
        this.f1772e.add(header);
    }

    /* renamed from: a */
    public final void m2881a(boolean z) {
        this.f1774g = z;
    }

    /* renamed from: a */
    public final void m2882a(byte[] bArr) {
        this.f1770c = bArr;
    }

    /* renamed from: b */
    public final String m2883b(String str) {
        return this.f1773f == null ? null : (String) this.f1773f.get(str);
    }

    /* renamed from: b */
    public final byte[] m2884b() {
        return this.f1770c;
    }

    /* renamed from: c */
    public final String m2885c() {
        return this.f1771d;
    }

    /* renamed from: d */
    public final ArrayList<Header> m2886d() {
        return this.f1772e;
    }

    /* renamed from: e */
    public final boolean m2887e() {
        return this.f1774g;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        C0755o c0755o = (C0755o) obj;
        if (this.f1770c == null) {
            if (c0755o.f1770c != null) {
                return false;
            }
        } else if (!this.f1770c.equals(c0755o.f1770c)) {
            return false;
        }
        return this.f1769b == null ? c0755o.f1769b == null : this.f1769b.equals(c0755o.f1769b);
    }

    public final int hashCode() {
        int i = 1;
        if (this.f1773f != null && this.f1773f.containsKey("id")) {
            i = ((String) this.f1773f.get("id")).hashCode() + 31;
        }
        return (this.f1769b == null ? 0 : this.f1769b.hashCode()) + (i * 31);
    }

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", new Object[]{this.f1769b, this.f1772e});
    }
}
