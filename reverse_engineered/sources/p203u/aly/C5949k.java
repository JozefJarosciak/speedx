package p203u.aly;

import java.io.Serializable;

/* compiled from: UMCCSystemBuffer */
/* renamed from: u.aly.k */
public class C5949k implements Serializable {
    /* renamed from: a */
    private static final long f19075a = 1;
    /* renamed from: b */
    private String f19076b;
    /* renamed from: c */
    private long f19077c;
    /* renamed from: d */
    private long f19078d;
    /* renamed from: e */
    private String f19079e;

    private C5949k() {
        this.f19076b = null;
        this.f19077c = 0;
        this.f19078d = 0;
        this.f19079e = null;
    }

    public C5949k(String str, long j, long j2) {
        this(str, j, j2, null);
    }

    public C5949k(String str, long j, long j2, String str2) {
        this.f19076b = null;
        this.f19077c = 0;
        this.f19078d = 0;
        this.f19079e = null;
        this.f19076b = str;
        this.f19077c = j;
        this.f19078d = j2;
        this.f19079e = str2;
    }

    /* renamed from: a */
    public C5949k m21977a() {
        this.f19078d++;
        return this;
    }

    /* renamed from: b */
    public String m21980b() {
        return this.f19079e;
    }

    /* renamed from: a */
    public void m21979a(String str) {
        this.f19079e = str;
    }

    /* renamed from: c */
    public String m21982c() {
        return this.f19076b;
    }

    /* renamed from: b */
    public void m21981b(String str) {
        this.f19076b = str;
    }

    /* renamed from: d */
    public long m21983d() {
        return this.f19077c;
    }

    /* renamed from: e */
    public long m21984e() {
        return this.f19078d;
    }

    /* renamed from: a */
    public C5949k m21978a(C5949k c5949k) {
        this.f19078d = c5949k.m21984e() + this.f19078d;
        this.f19077c = c5949k.m21983d();
        return this;
    }
}
