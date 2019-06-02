package cn.sharesdk.framework.p011b.p013b;

/* compiled from: ApiEvent */
/* renamed from: cn.sharesdk.framework.b.b.a */
public class C0592a extends C0591c {
    /* renamed from: c */
    private static int f1282c;
    /* renamed from: d */
    private static long f1283d;
    /* renamed from: a */
    public int f1284a;
    /* renamed from: b */
    public String f1285b;

    /* renamed from: a */
    protected String mo2277a() {
        return "[API]";
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(super.toString());
        stringBuilder.append('|').append(this.f1284a);
        stringBuilder.append('|').append(this.f1285b);
        return stringBuilder.toString();
    }

    /* renamed from: b */
    protected int mo2279b() {
        return 5000;
    }

    /* renamed from: c */
    protected int mo2280c() {
        return 50;
    }

    /* renamed from: d */
    protected long mo2281d() {
        return (long) f1282c;
    }

    /* renamed from: e */
    protected long mo2282e() {
        return f1283d;
    }

    /* renamed from: f */
    protected void mo2283f() {
        f1282c++;
    }

    /* renamed from: a */
    protected void mo2278a(long j) {
        f1283d = j;
    }
}
