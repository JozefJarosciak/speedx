package p203u.aly;

/* compiled from: TTransportException */
/* renamed from: u.aly.dd */
public class dd extends bv {
    /* renamed from: a */
    public static final int f19020a = 0;
    /* renamed from: b */
    public static final int f19021b = 1;
    /* renamed from: c */
    public static final int f19022c = 2;
    /* renamed from: d */
    public static final int f19023d = 3;
    /* renamed from: e */
    public static final int f19024e = 4;
    /* renamed from: g */
    private static final long f19025g = 1;
    /* renamed from: f */
    protected int f19026f = 0;

    public dd(int i) {
        this.f19026f = i;
    }

    public dd(int i, String str) {
        super(str);
        this.f19026f = i;
    }

    public dd(String str) {
        super(str);
    }

    public dd(int i, Throwable th) {
        super(th);
        this.f19026f = i;
    }

    public dd(Throwable th) {
        super(th);
    }

    public dd(String str, Throwable th) {
        super(str, th);
    }

    public dd(int i, String str, Throwable th) {
        super(str, th);
        this.f19026f = i;
    }

    /* renamed from: a */
    public int m21894a() {
        return this.f19026f;
    }
}
