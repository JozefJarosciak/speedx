package p203u.aly;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/* compiled from: UMCCAggregatedObject */
/* renamed from: u.aly.i */
public class C5947i implements Serializable {
    /* renamed from: a */
    private static final long f19063a = 1;
    /* renamed from: b */
    private List<String> f19064b = new ArrayList();
    /* renamed from: c */
    private List<String> f19065c = new ArrayList();
    /* renamed from: d */
    private long f19066d = 0;
    /* renamed from: e */
    private long f19067e = 0;
    /* renamed from: f */
    private long f19068f = 0;
    /* renamed from: g */
    private String f19069g = null;

    public C5947i(List<String> list, long j, long j2, long j3, List<String> list2, String str) {
        this.f19064b = list;
        this.f19065c = list2;
        this.f19066d = j;
        this.f19067e = j2;
        this.f19068f = j3;
        this.f19069g = str;
    }

    /* renamed from: a */
    public void m21959a(String str) {
        try {
            if (this.f19065c.size() < br.m21760a().m21761b()) {
                this.f19065c.add(str);
            } else {
                this.f19065c.remove(this.f19065c.get(0));
                this.f19065c.add(str);
            }
            if (this.f19065c.size() > br.m21760a().m21761b()) {
                for (int i = 0; i < this.f19065c.size() - br.m21760a().m21761b(); i++) {
                    this.f19065c.remove(this.f19065c.get(0));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m21961a(bo boVar, C5950l c5950l) {
        m21959a(c5950l.m21987b());
        this.f19068f++;
        this.f19067e += c5950l.m21988c();
        this.f19066d += c5950l.m21989d();
        boVar.mo6179a(this, false);
    }

    /* renamed from: a */
    public void m21962a(C5950l c5950l) {
        this.f19068f = 1;
        this.f19064b = c5950l.m21986a();
        m21959a(c5950l.m21987b());
        this.f19067e = c5950l.m21988c();
        this.f19066d = System.currentTimeMillis();
        this.f19069g = bt.m21776a(System.currentTimeMillis());
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[key: ").append(this.f19064b).append("] [label: ").append(this.f19065c).append("][ totalTimeStamp").append(this.f19069g).append("][ value").append(this.f19067e).append("][ count").append(this.f19068f).append("][ timeWindowNum").append(this.f19069g).append("]");
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public String m21957a() {
        return bj.m21699a(this.f19064b);
    }

    /* renamed from: b */
    public List<String> m21963b() {
        return this.f19064b;
    }

    /* renamed from: c */
    public String m21967c() {
        return bj.m21699a(this.f19065c);
    }

    /* renamed from: d */
    public List<String> m21969d() {
        return this.f19065c;
    }

    /* renamed from: e */
    public long m21970e() {
        return this.f19066d;
    }

    /* renamed from: f */
    public long m21971f() {
        return this.f19067e;
    }

    /* renamed from: g */
    public long m21972g() {
        return this.f19068f;
    }

    /* renamed from: h */
    public String m21973h() {
        return this.f19069g;
    }

    /* renamed from: a */
    public void m21960a(List<String> list) {
        this.f19064b = list;
    }

    /* renamed from: b */
    public void m21966b(List<String> list) {
        this.f19065c = list;
    }

    /* renamed from: a */
    public void m21958a(long j) {
        this.f19066d = j;
    }

    /* renamed from: b */
    public void m21964b(long j) {
        this.f19067e = j;
    }

    /* renamed from: c */
    public void m21968c(long j) {
        this.f19068f = j;
    }

    /* renamed from: b */
    public void m21965b(String str) {
        this.f19069g = str;
    }
}
