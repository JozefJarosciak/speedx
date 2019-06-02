package p203u.aly;

/* compiled from: TProtocolException */
/* renamed from: u.aly.cp */
public class cp extends bv {
    /* renamed from: a */
    public static final int f19011a = 0;
    /* renamed from: b */
    public static final int f19012b = 1;
    /* renamed from: c */
    public static final int f19013c = 2;
    /* renamed from: d */
    public static final int f19014d = 3;
    /* renamed from: e */
    public static final int f19015e = 4;
    /* renamed from: f */
    public static final int f19016f = 5;
    /* renamed from: h */
    private static final long f19017h = 1;
    /* renamed from: g */
    protected int f19018g = 0;

    public cp(int i) {
        this.f19018g = i;
    }

    public cp(int i, String str) {
        super(str);
        this.f19018g = i;
    }

    public cp(String str) {
        super(str);
    }

    public cp(int i, Throwable th) {
        super(th);
        this.f19018g = i;
    }

    public cp(Throwable th) {
        super(th);
    }

    public cp(String str, Throwable th) {
        super(str, th);
    }

    public cp(int i, String str, Throwable th) {
        super(str, th);
        this.f19018g = i;
    }

    /* renamed from: a */
    public int m21892a() {
        return this.f19018g;
    }
}
