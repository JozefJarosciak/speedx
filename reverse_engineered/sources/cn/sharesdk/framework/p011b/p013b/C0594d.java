package cn.sharesdk.framework.p011b.p013b;

/* compiled from: DemoEvent */
/* renamed from: cn.sharesdk.framework.b.b.d */
public class C0594d extends C0591c {
    /* renamed from: d */
    private static int f1292d;
    /* renamed from: n */
    private static long f1293n;
    /* renamed from: a */
    public String f1294a;
    /* renamed from: b */
    public int f1295b;
    /* renamed from: c */
    public String f1296c = "";

    /* renamed from: a */
    protected String mo2277a() {
        return "[EVT]";
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|').append(this.f1294a);
        stringBuilder.append('|').append(this.f1295b);
        stringBuilder.append('|').append(this.f1296c);
        return stringBuilder.toString();
    }

    /* renamed from: b */
    protected int mo2279b() {
        return 5000;
    }

    /* renamed from: c */
    protected int mo2280c() {
        return 30;
    }

    /* renamed from: d */
    protected long mo2281d() {
        return (long) f1292d;
    }

    /* renamed from: e */
    protected long mo2282e() {
        return f1293n;
    }

    /* renamed from: f */
    protected void mo2283f() {
        f1292d++;
    }

    /* renamed from: a */
    protected void mo2278a(long j) {
        f1293n = j;
    }
}
