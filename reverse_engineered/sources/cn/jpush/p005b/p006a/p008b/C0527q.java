package cn.jpush.p005b.p006a.p008b;

import cn.jpush.p005b.p006a.p007a.C0518c;
import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.C4050r;
import com.google.gson.jpush.annotations.C1479a;

/* renamed from: cn.jpush.b.a.b.q */
public abstract class C0527q {
    /* renamed from: f */
    protected static C4042k f1116f = new C4050r().a().b();
    /* renamed from: z */
    private static final String f1117z;
    @C1479a
    /* renamed from: g */
    protected long f1118g = 0;
    @C1479a
    /* renamed from: h */
    protected long f1119h = 0;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "+>\f\rH\u001a<\u0001\b\u0006\u001d?\t\u0001G\u00104DA\u0006";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0027;
    L_0x000b:
        r3 = r0;
        r4 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x0010:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0044;
            case 1: goto L_0x0047;
            case 2: goto L_0x004a;
            case 3: goto L_0x004d;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 38;
    L_0x0019:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0010;
    L_0x0025:
        r1 = r0;
        r0 = r3;
    L_0x0027:
        if (r1 > r2) goto L_0x000b;
    L_0x0029:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f1117z = r0;
        r0 = new com.google.gson.jpush.r;
        r0.<init>();
        r0 = r0.a();
        r0 = r0.b();
        f1116f = r0;
        return;
    L_0x0044:
        r5 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        goto L_0x0019;
    L_0x0047:
        r5 = 80;
        goto L_0x0019;
    L_0x004a:
        r5 = 100;
        goto L_0x0019;
    L_0x004d:
        r5 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.b.q.<clinit>():void");
    }

    /* renamed from: a */
    public static C0527q m1871a(String str, int i) {
        Class cls;
        switch (i) {
            case 1:
                cls = C0544r.class;
                break;
            case 2:
                cls = C0545s.class;
                break;
            case 3:
                cls = C0548v.class;
                break;
            case 4:
                cls = C0542o.class;
                break;
            case 5:
                cls = C0529b.class;
                break;
            case 6:
                cls = C0536i.class;
                break;
            case 7:
                cls = C0550x.class;
                break;
            case 8:
                cls = C0534g.class;
                break;
            case 9:
                cls = C0541n.class;
                break;
            case 10:
                cls = C0530c.class;
                break;
            case 11:
                cls = C0537j.class;
                break;
            case 12:
                cls = C0549w.class;
                break;
            case 18:
                cls = C0528a.class;
                break;
            case 19:
                cls = C0535h.class;
                break;
            case 23:
                cls = C0547u.class;
                break;
            case 31:
                cls = C0533f.class;
                break;
            case 32:
                cls = C0540m.class;
                break;
            case 33:
                cls = C0532e.class;
                break;
            case 34:
                cls = C0539l.class;
                break;
            case 35:
                cls = C0531d.class;
                break;
            case 36:
                cls = C0538k.class;
                break;
            default:
                System.out.println(new StringBuilder(f1117z).append(i).toString());
                return null;
        }
        return (C0527q) f1116f.a(str, cls);
    }

    /* renamed from: a */
    public final C0518c m1872a(String str) {
        return new C0518c(this.f1118g, mo2235a(this.f1119h, str));
    }

    /* renamed from: a */
    abstract C0543p mo2235a(long j, String str);
}
