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

/* compiled from: ImprintValue */
/* renamed from: u.aly.be */
public class be implements Serializable, Cloneable, bp<be, C5901e> {
    /* renamed from: d */
    public static final Map<C5901e, cb> f18824d;
    /* renamed from: e */
    private static final aw f18825e = new aw("ImprintValue");
    /* renamed from: f */
    private static final ap f18826f = new ap("value", Ascii.VT, (short) 1);
    /* renamed from: g */
    private static final ap f18827g = new ap("ts", (byte) 10, (short) 2);
    /* renamed from: h */
    private static final ap f18828h = new ap("guid", Ascii.VT, (short) 3);
    /* renamed from: i */
    private static final Map<Class<? extends ay>, az> f18829i = new HashMap();
    /* renamed from: j */
    private static final int f18830j = 0;
    /* renamed from: a */
    public String f18831a;
    /* renamed from: b */
    public long f18832b;
    /* renamed from: c */
    public String f18833c;
    /* renamed from: k */
    private byte f18834k;
    /* renamed from: l */
    private C5901e[] f18835l;

    /* compiled from: ImprintValue */
    /* renamed from: u.aly.be$a */
    private static class C5897a extends bh<be> {
        private C5897a() {
        }

        /* renamed from: a */
        public /* synthetic */ void mo7208a(as asVar, bp bpVar) throws bv {
            m21518b(asVar, (be) bpVar);
        }

        /* renamed from: b */
        public /* synthetic */ void mo7209b(as asVar, bp bpVar) throws bv {
            m21516a(asVar, (be) bpVar);
        }

        /* renamed from: a */
        public void m21516a(as asVar, be beVar) throws bv {
            asVar.mo7186f();
            while (true) {
                ap h = asVar.mo7188h();
                if (h.f18595b == (byte) 0) {
                    asVar.mo7187g();
                    if (beVar.m21556h()) {
                        beVar.m21560l();
                        return;
                    }
                    throw new cp("Required field 'ts' was not found in serialized data! Struct: " + toString());
                }
                switch (h.f18596c) {
                    case (short) 1:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        beVar.f18831a = asVar.mo7202v();
                        beVar.m21544a(true);
                        break;
                    case (short) 2:
                        if (h.f18595b != (byte) 10) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        beVar.f18832b = asVar.mo7200t();
                        beVar.m21549b(true);
                        break;
                    case (short) 3:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        beVar.f18833c = asVar.mo7202v();
                        beVar.m21551c(true);
                        break;
                    default:
                        at.m21234a(asVar, h.f18595b);
                        break;
                }
                asVar.mo7189i();
            }
        }

        /* renamed from: b */
        public void m21518b(as asVar, be beVar) throws bv {
            beVar.m21560l();
            asVar.mo7181a(be.f18825e);
            if (beVar.f18831a != null && beVar.m21553e()) {
                asVar.mo7178a(be.f18826f);
                asVar.mo7176a(beVar.f18831a);
                asVar.mo7182b();
            }
            asVar.mo7178a(be.f18827g);
            asVar.mo7175a(beVar.f18832b);
            asVar.mo7182b();
            if (beVar.f18833c != null) {
                asVar.mo7178a(be.f18828h);
                asVar.mo7176a(beVar.f18833c);
                asVar.mo7182b();
            }
            asVar.mo7183c();
            asVar.mo7173a();
        }
    }

    /* compiled from: ImprintValue */
    /* renamed from: u.aly.be$b */
    private static class C5898b implements az {
        private C5898b() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21520a();
        }

        /* renamed from: a */
        public C5897a m21520a() {
            return new C5897a();
        }
    }

    /* compiled from: ImprintValue */
    /* renamed from: u.aly.be$c */
    private static class C5899c extends bi<be> {
        private C5899c() {
        }

        /* renamed from: a */
        public void m21522a(as asVar, be beVar) throws bv {
            ax axVar = (ax) asVar;
            axVar.mo7175a(beVar.f18832b);
            axVar.mo7176a(beVar.f18833c);
            BitSet bitSet = new BitSet();
            if (beVar.m21553e()) {
                bitSet.set(0);
            }
            axVar.m21297a(bitSet, 1);
            if (beVar.m21553e()) {
                axVar.mo7176a(beVar.f18831a);
            }
        }

        /* renamed from: b */
        public void m21524b(as asVar, be beVar) throws bv {
            ax axVar = (ax) asVar;
            beVar.f18832b = axVar.mo7200t();
            beVar.m21549b(true);
            beVar.f18833c = axVar.mo7202v();
            beVar.m21551c(true);
            if (axVar.mo7205b(1).get(0)) {
                beVar.f18831a = axVar.mo7202v();
                beVar.m21544a(true);
            }
        }
    }

    /* compiled from: ImprintValue */
    /* renamed from: u.aly.be$d */
    private static class C5900d implements az {
        private C5900d() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21526a();
        }

        /* renamed from: a */
        public C5899c m21526a() {
            return new C5899c();
        }
    }

    /* compiled from: ImprintValue */
    /* renamed from: u.aly.be$e */
    public enum C5901e implements am {
        VALUE((short) 1, "value"),
        TS((short) 2, "ts"),
        GUID((short) 3, "guid");
        
        /* renamed from: d */
        private static final Map<String, C5901e> f18820d = null;
        /* renamed from: e */
        private final short f18822e;
        /* renamed from: f */
        private final String f18823f;

        static {
            f18820d = new HashMap();
            Iterator it = EnumSet.allOf(C5901e.class).iterator();
            while (it.hasNext()) {
                C5901e c5901e = (C5901e) it.next();
                f18820d.put(c5901e.m21532b(), c5901e);
            }
        }

        /* renamed from: a */
        public static C5901e m21528a(int i) {
            switch (i) {
                case 1:
                    return VALUE;
                case 2:
                    return TS;
                case 3:
                    return GUID;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C5901e m21530b(int i) {
            C5901e a = C5901e.m21528a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C5901e m21529a(String str) {
            return (C5901e) f18820d.get(str);
        }

        private C5901e(short s, String str) {
            this.f18822e = s;
            this.f18823f = str;
        }

        /* renamed from: a */
        public short m21531a() {
            return this.f18822e;
        }

        /* renamed from: b */
        public String m21532b() {
            return this.f18823f;
        }
    }

    /* renamed from: b */
    public /* synthetic */ am mo7212b(int i) {
        return m21539a(i);
    }

    /* renamed from: p */
    public /* synthetic */ bp mo7215p() {
        return m21540a();
    }

    static {
        f18829i.put(bh.class, new C5898b());
        f18829i.put(bi.class, new C5900d());
        Map enumMap = new EnumMap(C5901e.class);
        enumMap.put(C5901e.VALUE, new cb("value", (byte) 2, new cc(Ascii.VT)));
        enumMap.put(C5901e.TS, new cb("ts", (byte) 1, new cc((byte) 10)));
        enumMap.put(C5901e.GUID, new cb("guid", (byte) 1, new cc(Ascii.VT)));
        f18824d = Collections.unmodifiableMap(enumMap);
        cb.m21817a(be.class, f18824d);
    }

    public be() {
        this.f18834k = (byte) 0;
        this.f18835l = new C5901e[]{C5901e.VALUE};
    }

    public be(long j, String str) {
        this();
        this.f18832b = j;
        m21549b(true);
        this.f18833c = str;
    }

    public be(be beVar) {
        this.f18834k = (byte) 0;
        this.f18835l = new C5901e[]{C5901e.VALUE};
        this.f18834k = beVar.f18834k;
        if (beVar.m21553e()) {
            this.f18831a = beVar.f18831a;
        }
        this.f18832b = beVar.f18832b;
        if (beVar.m21559k()) {
            this.f18833c = beVar.f18833c;
        }
    }

    /* renamed from: a */
    public be m21540a() {
        return new be(this);
    }

    /* renamed from: b */
    public void mo7213b() {
        this.f18831a = null;
        m21549b(false);
        this.f18832b = 0;
        this.f18833c = null;
    }

    /* renamed from: c */
    public String m21550c() {
        return this.f18831a;
    }

    /* renamed from: a */
    public be m21542a(String str) {
        this.f18831a = str;
        return this;
    }

    /* renamed from: d */
    public void m21552d() {
        this.f18831a = null;
    }

    /* renamed from: e */
    public boolean m21553e() {
        return this.f18831a != null;
    }

    /* renamed from: a */
    public void m21544a(boolean z) {
        if (!z) {
            this.f18831a = null;
        }
    }

    /* renamed from: f */
    public long m21554f() {
        return this.f18832b;
    }

    /* renamed from: a */
    public be m21541a(long j) {
        this.f18832b = j;
        m21549b(true);
        return this;
    }

    /* renamed from: g */
    public void m21555g() {
        this.f18834k = ai.m21171b(this.f18834k, 0);
    }

    /* renamed from: h */
    public boolean m21556h() {
        return ai.m21169a(this.f18834k, 0);
    }

    /* renamed from: b */
    public void m21549b(boolean z) {
        this.f18834k = ai.m21167a(this.f18834k, 0, z);
    }

    /* renamed from: i */
    public String m21557i() {
        return this.f18833c;
    }

    /* renamed from: b */
    public be m21546b(String str) {
        this.f18833c = str;
        return this;
    }

    /* renamed from: j */
    public void m21558j() {
        this.f18833c = null;
    }

    /* renamed from: k */
    public boolean m21559k() {
        return this.f18833c != null;
    }

    /* renamed from: c */
    public void m21551c(boolean z) {
        if (!z) {
            this.f18833c = null;
        }
    }

    /* renamed from: a */
    public C5901e m21539a(int i) {
        return C5901e.m21528a(i);
    }

    /* renamed from: a */
    public void mo7211a(as asVar) throws bv {
        ((az) f18829i.get(asVar.mo7206y())).mo7210b().mo7209b(asVar, this);
    }

    /* renamed from: b */
    public void mo7214b(as asVar) throws bv {
        ((az) f18829i.get(asVar.mo7206y())).mo7210b().mo7208a(asVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ImprintValue(");
        Object obj = 1;
        if (m21553e()) {
            stringBuilder.append("value:");
            if (this.f18831a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f18831a);
            }
            obj = null;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("ts:");
        stringBuilder.append(this.f18832b);
        stringBuilder.append(", ");
        stringBuilder.append("guid:");
        if (this.f18833c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f18833c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /* renamed from: l */
    public void m21560l() throws bv {
        if (this.f18833c == null) {
            throw new cp("Required field 'guid' was not present! Struct: " + toString());
        }
    }

    /* renamed from: a */
    private void m21534a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo7214b(new ci(new bk((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m21533a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f18834k = (byte) 0;
            mo7211a(new ci(new bk((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
