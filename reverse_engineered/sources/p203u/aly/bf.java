package p203u.aly;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: Response */
/* renamed from: u.aly.bf */
public class bf implements Serializable, Cloneable, bp<bf, C5907e> {
    /* renamed from: d */
    public static final Map<C5907e, cb> f18843d;
    /* renamed from: e */
    private static final aw f18844e = new aw("Response");
    /* renamed from: f */
    private static final ap f18845f = new ap("resp_code", (byte) 8, (short) 1);
    /* renamed from: g */
    private static final ap f18846g = new ap("msg", Ascii.VT, (short) 2);
    /* renamed from: h */
    private static final ap f18847h = new ap("imprint", Ascii.FF, (short) 3);
    /* renamed from: i */
    private static final Map<Class<? extends ay>, az> f18848i = new HashMap();
    /* renamed from: j */
    private static final int f18849j = 0;
    /* renamed from: a */
    public int f18850a;
    /* renamed from: b */
    public String f18851b;
    /* renamed from: c */
    public bd f18852c;
    /* renamed from: k */
    private byte f18853k;
    /* renamed from: l */
    private C5907e[] f18854l;

    /* compiled from: Response */
    /* renamed from: u.aly.bf$a */
    private static class C5903a extends bh<bf> {
        private C5903a() {
        }

        /* renamed from: a */
        public /* synthetic */ void mo7208a(as asVar, bp bpVar) throws bv {
            m21564b(asVar, (bf) bpVar);
        }

        /* renamed from: b */
        public /* synthetic */ void mo7209b(as asVar, bp bpVar) throws bv {
            m21562a(asVar, (bf) bpVar);
        }

        /* renamed from: a */
        public void m21562a(as asVar, bf bfVar) throws bv {
            asVar.mo7186f();
            while (true) {
                ap h = asVar.mo7188h();
                if (h.f18595b == (byte) 0) {
                    asVar.mo7187g();
                    if (bfVar.m21599e()) {
                        bfVar.m21606l();
                        return;
                    }
                    throw new cp("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
                }
                switch (h.f18596c) {
                    case (short) 1:
                        if (h.f18595b != (byte) 8) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bfVar.f18850a = asVar.mo7199s();
                        bfVar.m21590a(true);
                        break;
                    case (short) 2:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bfVar.f18851b = asVar.mo7202v();
                        bfVar.m21594b(true);
                        break;
                    case (short) 3:
                        if (h.f18595b != Ascii.FF) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bfVar.f18852c = new bd();
                        bfVar.f18852c.mo7211a(asVar);
                        bfVar.m21597c(true);
                        break;
                    default:
                        at.m21234a(asVar, h.f18595b);
                        break;
                }
                asVar.mo7189i();
            }
        }

        /* renamed from: b */
        public void m21564b(as asVar, bf bfVar) throws bv {
            bfVar.m21606l();
            asVar.mo7181a(bf.f18844e);
            asVar.mo7178a(bf.f18845f);
            asVar.mo7174a(bfVar.f18850a);
            asVar.mo7182b();
            if (bfVar.f18851b != null && bfVar.m21602h()) {
                asVar.mo7178a(bf.f18846g);
                asVar.mo7176a(bfVar.f18851b);
                asVar.mo7182b();
            }
            if (bfVar.f18852c != null && bfVar.m21605k()) {
                asVar.mo7178a(bf.f18847h);
                bfVar.f18852c.mo7214b(asVar);
                asVar.mo7182b();
            }
            asVar.mo7183c();
            asVar.mo7173a();
        }
    }

    /* compiled from: Response */
    /* renamed from: u.aly.bf$b */
    private static class C5904b implements az {
        private C5904b() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21566a();
        }

        /* renamed from: a */
        public C5903a m21566a() {
            return new C5903a();
        }
    }

    /* compiled from: Response */
    /* renamed from: u.aly.bf$c */
    private static class C5905c extends bi<bf> {
        private C5905c() {
        }

        /* renamed from: a */
        public void m21568a(as asVar, bf bfVar) throws bv {
            asVar = (ax) asVar;
            asVar.mo7174a(bfVar.f18850a);
            BitSet bitSet = new BitSet();
            if (bfVar.m21602h()) {
                bitSet.set(0);
            }
            if (bfVar.m21605k()) {
                bitSet.set(1);
            }
            asVar.m21297a(bitSet, 2);
            if (bfVar.m21602h()) {
                asVar.mo7176a(bfVar.f18851b);
            }
            if (bfVar.m21605k()) {
                bfVar.f18852c.mo7214b(asVar);
            }
        }

        /* renamed from: b */
        public void m21570b(as asVar, bf bfVar) throws bv {
            asVar = (ax) asVar;
            bfVar.f18850a = asVar.mo7199s();
            bfVar.m21590a(true);
            BitSet b = asVar.mo7205b(2);
            if (b.get(0)) {
                bfVar.f18851b = asVar.mo7202v();
                bfVar.m21594b(true);
            }
            if (b.get(1)) {
                bfVar.f18852c = new bd();
                bfVar.f18852c.mo7211a(asVar);
                bfVar.m21597c(true);
            }
        }
    }

    /* compiled from: Response */
    /* renamed from: u.aly.bf$d */
    private static class C5906d implements az {
        private C5906d() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21572a();
        }

        /* renamed from: a */
        public C5905c m21572a() {
            return new C5905c();
        }
    }

    /* compiled from: Response */
    /* renamed from: u.aly.bf$e */
    public enum C5907e implements am {
        RESP_CODE((short) 1, "resp_code"),
        MSG((short) 2, "msg"),
        IMPRINT((short) 3, "imprint");
        
        /* renamed from: d */
        private static final Map<String, C5907e> f18839d = null;
        /* renamed from: e */
        private final short f18841e;
        /* renamed from: f */
        private final String f18842f;

        static {
            f18839d = new HashMap();
            Iterator it = EnumSet.allOf(C5907e.class).iterator();
            while (it.hasNext()) {
                C5907e c5907e = (C5907e) it.next();
                f18839d.put(c5907e.m21578b(), c5907e);
            }
        }

        /* renamed from: a */
        public static C5907e m21574a(int i) {
            switch (i) {
                case 1:
                    return RESP_CODE;
                case 2:
                    return MSG;
                case 3:
                    return IMPRINT;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C5907e m21576b(int i) {
            C5907e a = C5907e.m21574a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C5907e m21575a(String str) {
            return (C5907e) f18839d.get(str);
        }

        private C5907e(short s, String str) {
            this.f18841e = s;
            this.f18842f = str;
        }

        /* renamed from: a */
        public short m21577a() {
            return this.f18841e;
        }

        /* renamed from: b */
        public String m21578b() {
            return this.f18842f;
        }
    }

    /* renamed from: b */
    public /* synthetic */ am mo7212b(int i) {
        return m21596c(i);
    }

    /* renamed from: p */
    public /* synthetic */ bp mo7215p() {
        return m21585a();
    }

    static {
        f18848i.put(bh.class, new C5904b());
        f18848i.put(bi.class, new C5906d());
        Map enumMap = new EnumMap(C5907e.class);
        enumMap.put(C5907e.RESP_CODE, new cb("resp_code", (byte) 1, new cc((byte) 8)));
        enumMap.put(C5907e.MSG, new cb("msg", (byte) 2, new cc(Ascii.VT)));
        enumMap.put(C5907e.IMPRINT, new cb("imprint", (byte) 2, new cg(Ascii.FF, bd.class)));
        f18843d = Collections.unmodifiableMap(enumMap);
        cb.m21817a(bf.class, f18843d);
    }

    public bf() {
        this.f18853k = (byte) 0;
        this.f18854l = new C5907e[]{C5907e.MSG, C5907e.IMPRINT};
    }

    public bf(int i) {
        this();
        this.f18850a = i;
        m21590a(true);
    }

    public bf(bf bfVar) {
        this.f18853k = (byte) 0;
        this.f18854l = new C5907e[]{C5907e.MSG, C5907e.IMPRINT};
        this.f18853k = bfVar.f18853k;
        this.f18850a = bfVar.f18850a;
        if (bfVar.m21602h()) {
            this.f18851b = bfVar.f18851b;
        }
        if (bfVar.m21605k()) {
            this.f18852c = new bd(bfVar.f18852c);
        }
    }

    /* renamed from: a */
    public bf m21585a() {
        return new bf(this);
    }

    /* renamed from: b */
    public void mo7213b() {
        m21590a(false);
        this.f18850a = 0;
        this.f18851b = null;
        this.f18852c = null;
    }

    /* renamed from: c */
    public int m21595c() {
        return this.f18850a;
    }

    /* renamed from: a */
    public bf m21586a(int i) {
        this.f18850a = i;
        m21590a(true);
        return this;
    }

    /* renamed from: d */
    public void m21598d() {
        this.f18853k = ai.m21171b(this.f18853k, 0);
    }

    /* renamed from: e */
    public boolean m21599e() {
        return ai.m21169a(this.f18853k, 0);
    }

    /* renamed from: a */
    public void m21590a(boolean z) {
        this.f18853k = ai.m21167a(this.f18853k, 0, z);
    }

    /* renamed from: f */
    public String m21600f() {
        return this.f18851b;
    }

    /* renamed from: a */
    public bf m21587a(String str) {
        this.f18851b = str;
        return this;
    }

    /* renamed from: g */
    public void m21601g() {
        this.f18851b = null;
    }

    /* renamed from: h */
    public boolean m21602h() {
        return this.f18851b != null;
    }

    /* renamed from: b */
    public void m21594b(boolean z) {
        if (!z) {
            this.f18851b = null;
        }
    }

    /* renamed from: i */
    public bd m21603i() {
        return this.f18852c;
    }

    /* renamed from: a */
    public bf m21588a(bd bdVar) {
        this.f18852c = bdVar;
        return this;
    }

    /* renamed from: j */
    public void m21604j() {
        this.f18852c = null;
    }

    /* renamed from: k */
    public boolean m21605k() {
        return this.f18852c != null;
    }

    /* renamed from: c */
    public void m21597c(boolean z) {
        if (!z) {
            this.f18852c = null;
        }
    }

    /* renamed from: c */
    public C5907e m21596c(int i) {
        return C5907e.m21574a(i);
    }

    /* renamed from: a */
    public void mo7211a(as asVar) throws bv {
        ((az) f18848i.get(asVar.mo7206y())).mo7210b().mo7209b(asVar, this);
    }

    /* renamed from: b */
    public void mo7214b(as asVar) throws bv {
        ((az) f18848i.get(asVar.mo7206y())).mo7210b().mo7208a(asVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Response(");
        stringBuilder.append("resp_code:");
        stringBuilder.append(this.f18850a);
        if (m21602h()) {
            stringBuilder.append(", ");
            stringBuilder.append("msg:");
            if (this.f18851b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f18851b);
            }
        }
        if (m21605k()) {
            stringBuilder.append(", ");
            stringBuilder.append("imprint:");
            if (this.f18852c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f18852c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /* renamed from: l */
    public void m21606l() throws bv {
        if (this.f18852c != null) {
            this.f18852c.m21514m();
        }
    }

    /* renamed from: a */
    private void m21580a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo7214b(new ci(new bk((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m21579a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f18853k = (byte) 0;
            mo7211a(new ci(new bk((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
