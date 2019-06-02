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

/* compiled from: IdJournal */
/* renamed from: u.aly.ba */
public class ba implements Serializable, Cloneable, bp<ba, C5877e> {
    /* renamed from: e */
    public static final Map<C5877e, cb> f18749e;
    /* renamed from: f */
    private static final aw f18750f = new aw("IdJournal");
    /* renamed from: g */
    private static final ap f18751g = new ap("domain", Ascii.VT, (short) 1);
    /* renamed from: h */
    private static final ap f18752h = new ap("old_id", Ascii.VT, (short) 2);
    /* renamed from: i */
    private static final ap f18753i = new ap("new_id", Ascii.VT, (short) 3);
    /* renamed from: j */
    private static final ap f18754j = new ap("ts", (byte) 10, (short) 4);
    /* renamed from: k */
    private static final Map<Class<? extends ay>, az> f18755k = new HashMap();
    /* renamed from: l */
    private static final int f18756l = 0;
    /* renamed from: a */
    public String f18757a;
    /* renamed from: b */
    public String f18758b;
    /* renamed from: c */
    public String f18759c;
    /* renamed from: d */
    public long f18760d;
    /* renamed from: m */
    private byte f18761m;
    /* renamed from: n */
    private C5877e[] f18762n;

    /* compiled from: IdJournal */
    /* renamed from: u.aly.ba$a */
    private static class C5873a extends bh<ba> {
        private C5873a() {
        }

        /* renamed from: a */
        public /* synthetic */ void mo7208a(as asVar, bp bpVar) throws bv {
            m21316b(asVar, (ba) bpVar);
        }

        /* renamed from: b */
        public /* synthetic */ void mo7209b(as asVar, bp bpVar) throws bv {
            m21314a(asVar, (ba) bpVar);
        }

        /* renamed from: a */
        public void m21314a(as asVar, ba baVar) throws bv {
            asVar.mo7186f();
            while (true) {
                ap h = asVar.mo7188h();
                if (h.f18595b == (byte) 0) {
                    asVar.mo7187g();
                    if (baVar.m21368n()) {
                        baVar.m21369o();
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
                        baVar.f18757a = asVar.mo7202v();
                        baVar.m21348a(true);
                        break;
                    case (short) 2:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        baVar.f18758b = asVar.mo7202v();
                        baVar.m21353b(true);
                        break;
                    case (short) 3:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        baVar.f18759c = asVar.mo7202v();
                        baVar.m21356c(true);
                        break;
                    case (short) 4:
                        if (h.f18595b != (byte) 10) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        baVar.f18760d = asVar.mo7200t();
                        baVar.m21358d(true);
                        break;
                    default:
                        at.m21234a(asVar, h.f18595b);
                        break;
                }
                asVar.mo7189i();
            }
        }

        /* renamed from: b */
        public void m21316b(as asVar, ba baVar) throws bv {
            baVar.m21369o();
            asVar.mo7181a(ba.f18750f);
            if (baVar.f18757a != null) {
                asVar.mo7178a(ba.f18751g);
                asVar.mo7176a(baVar.f18757a);
                asVar.mo7182b();
            }
            if (baVar.f18758b != null && baVar.m21362h()) {
                asVar.mo7178a(ba.f18752h);
                asVar.mo7176a(baVar.f18758b);
                asVar.mo7182b();
            }
            if (baVar.f18759c != null) {
                asVar.mo7178a(ba.f18753i);
                asVar.mo7176a(baVar.f18759c);
                asVar.mo7182b();
            }
            asVar.mo7178a(ba.f18754j);
            asVar.mo7175a(baVar.f18760d);
            asVar.mo7182b();
            asVar.mo7183c();
            asVar.mo7173a();
        }
    }

    /* compiled from: IdJournal */
    /* renamed from: u.aly.ba$b */
    private static class C5874b implements az {
        private C5874b() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21318a();
        }

        /* renamed from: a */
        public C5873a m21318a() {
            return new C5873a();
        }
    }

    /* compiled from: IdJournal */
    /* renamed from: u.aly.ba$c */
    private static class C5875c extends bi<ba> {
        private C5875c() {
        }

        /* renamed from: a */
        public void m21320a(as asVar, ba baVar) throws bv {
            ax axVar = (ax) asVar;
            axVar.mo7176a(baVar.f18757a);
            axVar.mo7176a(baVar.f18759c);
            axVar.mo7175a(baVar.f18760d);
            BitSet bitSet = new BitSet();
            if (baVar.m21362h()) {
                bitSet.set(0);
            }
            axVar.m21297a(bitSet, 1);
            if (baVar.m21362h()) {
                axVar.mo7176a(baVar.f18758b);
            }
        }

        /* renamed from: b */
        public void m21322b(as asVar, ba baVar) throws bv {
            ax axVar = (ax) asVar;
            baVar.f18757a = axVar.mo7202v();
            baVar.m21348a(true);
            baVar.f18759c = axVar.mo7202v();
            baVar.m21356c(true);
            baVar.f18760d = axVar.mo7200t();
            baVar.m21358d(true);
            if (axVar.mo7205b(1).get(0)) {
                baVar.f18758b = axVar.mo7202v();
                baVar.m21353b(true);
            }
        }
    }

    /* compiled from: IdJournal */
    /* renamed from: u.aly.ba$d */
    private static class C5876d implements az {
        private C5876d() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21324a();
        }

        /* renamed from: a */
        public C5875c m21324a() {
            return new C5875c();
        }
    }

    /* compiled from: IdJournal */
    /* renamed from: u.aly.ba$e */
    public enum C5877e implements am {
        DOMAIN((short) 1, "domain"),
        OLD_ID((short) 2, "old_id"),
        NEW_ID((short) 3, "new_id"),
        TS((short) 4, "ts");
        
        /* renamed from: e */
        private static final Map<String, C5877e> f18745e = null;
        /* renamed from: f */
        private final short f18747f;
        /* renamed from: g */
        private final String f18748g;

        static {
            f18745e = new HashMap();
            Iterator it = EnumSet.allOf(C5877e.class).iterator();
            while (it.hasNext()) {
                C5877e c5877e = (C5877e) it.next();
                f18745e.put(c5877e.m21330b(), c5877e);
            }
        }

        /* renamed from: a */
        public static C5877e m21326a(int i) {
            switch (i) {
                case 1:
                    return DOMAIN;
                case 2:
                    return OLD_ID;
                case 3:
                    return NEW_ID;
                case 4:
                    return TS;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C5877e m21328b(int i) {
            C5877e a = C5877e.m21326a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C5877e m21327a(String str) {
            return (C5877e) f18745e.get(str);
        }

        private C5877e(short s, String str) {
            this.f18747f = s;
            this.f18748g = str;
        }

        /* renamed from: a */
        public short m21329a() {
            return this.f18747f;
        }

        /* renamed from: b */
        public String m21330b() {
            return this.f18748g;
        }
    }

    /* renamed from: b */
    public /* synthetic */ am mo7212b(int i) {
        return m21343a(i);
    }

    /* renamed from: p */
    public /* synthetic */ bp mo7215p() {
        return m21344a();
    }

    static {
        f18755k.put(bh.class, new C5874b());
        f18755k.put(bi.class, new C5876d());
        Map enumMap = new EnumMap(C5877e.class);
        enumMap.put(C5877e.DOMAIN, new cb("domain", (byte) 1, new cc(Ascii.VT)));
        enumMap.put(C5877e.OLD_ID, new cb("old_id", (byte) 2, new cc(Ascii.VT)));
        enumMap.put(C5877e.NEW_ID, new cb("new_id", (byte) 1, new cc(Ascii.VT)));
        enumMap.put(C5877e.TS, new cb("ts", (byte) 1, new cc((byte) 10)));
        f18749e = Collections.unmodifiableMap(enumMap);
        cb.m21817a(ba.class, f18749e);
    }

    public ba() {
        this.f18761m = (byte) 0;
        this.f18762n = new C5877e[]{C5877e.OLD_ID};
    }

    public ba(String str, String str2, long j) {
        this();
        this.f18757a = str;
        this.f18759c = str2;
        this.f18760d = j;
        m21358d(true);
    }

    public ba(ba baVar) {
        this.f18761m = (byte) 0;
        this.f18762n = new C5877e[]{C5877e.OLD_ID};
        this.f18761m = baVar.f18761m;
        if (baVar.m21359e()) {
            this.f18757a = baVar.f18757a;
        }
        if (baVar.m21362h()) {
            this.f18758b = baVar.f18758b;
        }
        if (baVar.m21365k()) {
            this.f18759c = baVar.f18759c;
        }
        this.f18760d = baVar.f18760d;
    }

    /* renamed from: a */
    public ba m21344a() {
        return new ba(this);
    }

    /* renamed from: b */
    public void mo7213b() {
        this.f18757a = null;
        this.f18758b = null;
        this.f18759c = null;
        m21358d(false);
        this.f18760d = 0;
    }

    /* renamed from: c */
    public String m21354c() {
        return this.f18757a;
    }

    /* renamed from: a */
    public ba m21346a(String str) {
        this.f18757a = str;
        return this;
    }

    /* renamed from: d */
    public void m21357d() {
        this.f18757a = null;
    }

    /* renamed from: e */
    public boolean m21359e() {
        return this.f18757a != null;
    }

    /* renamed from: a */
    public void m21348a(boolean z) {
        if (!z) {
            this.f18757a = null;
        }
    }

    /* renamed from: f */
    public String m21360f() {
        return this.f18758b;
    }

    /* renamed from: b */
    public ba m21350b(String str) {
        this.f18758b = str;
        return this;
    }

    /* renamed from: g */
    public void m21361g() {
        this.f18758b = null;
    }

    /* renamed from: h */
    public boolean m21362h() {
        return this.f18758b != null;
    }

    /* renamed from: b */
    public void m21353b(boolean z) {
        if (!z) {
            this.f18758b = null;
        }
    }

    /* renamed from: i */
    public String m21363i() {
        return this.f18759c;
    }

    /* renamed from: c */
    public ba m21355c(String str) {
        this.f18759c = str;
        return this;
    }

    /* renamed from: j */
    public void m21364j() {
        this.f18759c = null;
    }

    /* renamed from: k */
    public boolean m21365k() {
        return this.f18759c != null;
    }

    /* renamed from: c */
    public void m21356c(boolean z) {
        if (!z) {
            this.f18759c = null;
        }
    }

    /* renamed from: l */
    public long m21366l() {
        return this.f18760d;
    }

    /* renamed from: a */
    public ba m21345a(long j) {
        this.f18760d = j;
        m21358d(true);
        return this;
    }

    /* renamed from: m */
    public void m21367m() {
        this.f18761m = ai.m21171b(this.f18761m, 0);
    }

    /* renamed from: n */
    public boolean m21368n() {
        return ai.m21169a(this.f18761m, 0);
    }

    /* renamed from: d */
    public void m21358d(boolean z) {
        this.f18761m = ai.m21167a(this.f18761m, 0, z);
    }

    /* renamed from: a */
    public C5877e m21343a(int i) {
        return C5877e.m21326a(i);
    }

    /* renamed from: a */
    public void mo7211a(as asVar) throws bv {
        ((az) f18755k.get(asVar.mo7206y())).mo7210b().mo7209b(asVar, this);
    }

    /* renamed from: b */
    public void mo7214b(as asVar) throws bv {
        ((az) f18755k.get(asVar.mo7206y())).mo7210b().mo7208a(asVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdJournal(");
        stringBuilder.append("domain:");
        if (this.f18757a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f18757a);
        }
        if (m21362h()) {
            stringBuilder.append(", ");
            stringBuilder.append("old_id:");
            if (this.f18758b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f18758b);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("new_id:");
        if (this.f18759c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f18759c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.f18760d);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /* renamed from: o */
    public void m21369o() throws bv {
        if (this.f18757a == null) {
            throw new cp("Required field 'domain' was not present! Struct: " + toString());
        } else if (this.f18759c == null) {
            throw new cp("Required field 'new_id' was not present! Struct: " + toString());
        }
    }

    /* renamed from: a */
    private void m21337a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo7214b(new ci(new bk((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m21336a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f18761m = (byte) 0;
            mo7211a(new ci(new bk((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
