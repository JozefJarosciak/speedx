package p203u.aly;

/* compiled from: TProtocolUtil */
/* renamed from: u.aly.at */
public class at {
    /* renamed from: a */
    private static int f18603a = Integer.MAX_VALUE;

    /* renamed from: a */
    public static void m21234a(as asVar, byte b) throws bv {
        at.m21235a(asVar, b, f18603a);
    }

    /* renamed from: a */
    public static void m21235a(as asVar, byte b, int i) throws bv {
        int i2 = 0;
        if (i <= 0) {
            throw new bv("Maximum skip depth exceeded");
        }
        switch (b) {
            case (byte) 2:
                asVar.mo7196p();
                return;
            case (byte) 3:
                asVar.mo7197q();
                return;
            case (byte) 4:
                asVar.mo7201u();
                return;
            case (byte) 6:
                asVar.mo7198r();
                return;
            case (byte) 8:
                asVar.mo7199s();
                return;
            case (byte) 10:
                asVar.mo7200t();
                return;
            case (byte) 11:
                asVar.mo7203w();
                return;
            case (byte) 12:
                asVar.mo7186f();
                while (true) {
                    ap h = asVar.mo7188h();
                    if (h.f18595b == (byte) 0) {
                        asVar.mo7187g();
                        return;
                    } else {
                        at.m21235a(asVar, h.f18595b, i - 1);
                        asVar.mo7189i();
                    }
                }
            case (byte) 13:
                ar j = asVar.mo7190j();
                while (i2 < j.f18601c) {
                    at.m21235a(asVar, j.f18599a, i - 1);
                    at.m21235a(asVar, j.f18600b, i - 1);
                    i2++;
                }
                asVar.mo7191k();
                return;
            case (byte) 14:
                au n = asVar.mo7194n();
                while (i2 < n.f18605b) {
                    at.m21235a(asVar, n.f18604a, i - 1);
                    i2++;
                }
                asVar.mo7195o();
                return;
            case (byte) 15:
                aq l = asVar.mo7192l();
                while (i2 < l.f18598b) {
                    at.m21235a(asVar, l.f18597a, i - 1);
                    i2++;
                }
                asVar.mo7193m();
                return;
            default:
                return;
        }
    }
}
