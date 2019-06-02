package p203u.aly;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: UMCCVerbatimObject */
/* renamed from: u.aly.l */
public class C5950l implements Serializable {
    /* renamed from: a */
    private static final long f19080a = 1;
    /* renamed from: b */
    private List<String> f19081b = new ArrayList();
    /* renamed from: c */
    private String f19082c;
    /* renamed from: d */
    private long f19083d;
    /* renamed from: e */
    private long f19084e;
    /* renamed from: f */
    private String f19085f;

    public C5950l(List<String> list, long j, String str, long j2) {
        this.f19081b = list;
        this.f19083d = j;
        this.f19082c = str;
        this.f19084e = j2;
        m21985f();
    }

    /* renamed from: f */
    private void m21985f() {
        this.f19085f = bt.m21776a(this.f19084e);
    }

    /* renamed from: a */
    public List<String> m21986a() {
        return this.f19081b;
    }

    /* renamed from: b */
    public String m21987b() {
        return this.f19082c;
    }

    /* renamed from: c */
    public long m21988c() {
        return this.f19083d;
    }

    /* renamed from: d */
    public long m21989d() {
        return this.f19084e;
    }

    /* renamed from: e */
    public String m21990e() {
        return this.f19085f;
    }
}
