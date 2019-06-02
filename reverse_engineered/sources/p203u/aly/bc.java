package p203u.aly;

import com.google.common.base.Ascii;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: IdTracking */
/* renamed from: u.aly.bc */
public class bc implements Serializable, Cloneable, bp<bc, C5889e> {
    /* renamed from: d */
    public static final Map<C5889e, cb> f18789d;
    /* renamed from: e */
    private static final aw f18790e = new aw("IdTracking");
    /* renamed from: f */
    private static final ap f18791f = new ap("snapshots", (byte) 13, (short) 1);
    /* renamed from: g */
    private static final ap f18792g = new ap("journals", Ascii.SI, (short) 2);
    /* renamed from: h */
    private static final ap f18793h = new ap("checksum", Ascii.VT, (short) 3);
    /* renamed from: i */
    private static final Map<Class<? extends ay>, az> f18794i = new HashMap();
    /* renamed from: a */
    public Map<String, bb> f18795a;
    /* renamed from: b */
    public List<ba> f18796b;
    /* renamed from: c */
    public String f18797c;
    /* renamed from: j */
    private C5889e[] f18798j;

    /* compiled from: IdTracking */
    /* renamed from: u.aly.bc$a */
    private static class C5885a extends bh<bc> {
        private C5885a() {
        }

        /* renamed from: a */
        public /* synthetic */ void mo7208a(as asVar, bp bpVar) throws bv {
            m21419b(asVar, (bc) bpVar);
        }

        /* renamed from: b */
        public /* synthetic */ void mo7209b(as asVar, bp bpVar) throws bv {
            m21417a(asVar, (bc) bpVar);
        }

        /* renamed from: a */
        public void m21417a(as asVar, bc bcVar) throws bv {
            asVar.mo7186f();
            while (true) {
                ap h = asVar.mo7188h();
                if (h.f18595b == (byte) 0) {
                    asVar.mo7187g();
                    bcVar.m21466o();
                    return;
                }
                int i;
                switch (h.f18596c) {
                    case (short) 1:
                        if (h.f18595b != (byte) 13) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        ar j = asVar.mo7190j();
                        bcVar.f18795a = new HashMap(j.f18601c * 2);
                        for (i = 0; i < j.f18601c; i++) {
                            String v = asVar.mo7202v();
                            bb bbVar = new bb();
                            bbVar.mo7211a(asVar);
                            bcVar.f18795a.put(v, bbVar);
                        }
                        asVar.mo7191k();
                        bcVar.m21448a(true);
                        break;
                    case (short) 2:
                        if (h.f18595b != Ascii.SI) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        aq l = asVar.mo7192l();
                        bcVar.f18796b = new ArrayList(l.f18598b);
                        for (i = 0; i < l.f18598b; i++) {
                            ba baVar = new ba();
                            baVar.mo7211a(asVar);
                            bcVar.f18796b.add(baVar);
                        }
                        asVar.mo7193m();
                        bcVar.m21452b(true);
                        break;
                    case (short) 3:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bcVar.f18797c = asVar.mo7202v();
                        bcVar.m21454c(true);
                        break;
                    default:
                        at.m21234a(asVar, h.f18595b);
                        break;
                }
                asVar.mo7189i();
            }
        }

        /* renamed from: b */
        public void m21419b(as asVar, bc bcVar) throws bv {
            bcVar.m21466o();
            asVar.mo7181a(bc.f18790e);
            if (bcVar.f18795a != null) {
                asVar.mo7178a(bc.f18791f);
                asVar.mo7180a(new ar(Ascii.VT, Ascii.FF, bcVar.f18795a.size()));
                for (Entry entry : bcVar.f18795a.entrySet()) {
                    asVar.mo7176a((String) entry.getKey());
                    ((bb) entry.getValue()).mo7214b(asVar);
                }
                asVar.mo7184d();
                asVar.mo7182b();
            }
            if (bcVar.f18796b != null && bcVar.m21462k()) {
                asVar.mo7178a(bc.f18792g);
                asVar.mo7179a(new aq(Ascii.FF, bcVar.f18796b.size()));
                for (ba b : bcVar.f18796b) {
                    b.mo7214b(asVar);
                }
                asVar.mo7185e();
                asVar.mo7182b();
            }
            if (bcVar.f18797c != null && bcVar.m21465n()) {
                asVar.mo7178a(bc.f18793h);
                asVar.mo7176a(bcVar.f18797c);
                asVar.mo7182b();
            }
            asVar.mo7183c();
            asVar.mo7173a();
        }
    }

    /* compiled from: IdTracking */
    /* renamed from: u.aly.bc$b */
    private static class C5886b implements az {
        private C5886b() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21421a();
        }

        /* renamed from: a */
        public C5885a m21421a() {
            return new C5885a();
        }
    }

    /* compiled from: IdTracking */
    /* renamed from: u.aly.bc$c */
    private static class C5887c extends bi<bc> {
        private C5887c() {
        }

        /* renamed from: a */
        public void m21423a(as asVar, bc bcVar) throws bv {
            asVar = (ax) asVar;
            asVar.mo7174a(bcVar.f18795a.size());
            for (Entry entry : bcVar.f18795a.entrySet()) {
                asVar.mo7176a((String) entry.getKey());
                ((bb) entry.getValue()).mo7214b(asVar);
            }
            BitSet bitSet = new BitSet();
            if (bcVar.m21462k()) {
                bitSet.set(0);
            }
            if (bcVar.m21465n()) {
                bitSet.set(1);
            }
            asVar.m21297a(bitSet, 2);
            if (bcVar.m21462k()) {
                asVar.mo7174a(bcVar.f18796b.size());
                for (ba b : bcVar.f18796b) {
                    b.mo7214b(asVar);
                }
            }
            if (bcVar.m21465n()) {
                asVar.mo7176a(bcVar.f18797c);
            }
        }

        /* renamed from: b */
        public void m21425b(as asVar, bc bcVar) throws bv {
            int i = 0;
            asVar = (ax) asVar;
            ar arVar = new ar(Ascii.VT, Ascii.FF, asVar.mo7199s());
            bcVar.f18795a = new HashMap(arVar.f18601c * 2);
            for (int i2 = 0; i2 < arVar.f18601c; i2++) {
                String v = asVar.mo7202v();
                bb bbVar = new bb();
                bbVar.mo7211a(asVar);
                bcVar.f18795a.put(v, bbVar);
            }
            bcVar.m21448a(true);
            BitSet b = asVar.mo7205b(2);
            if (b.get(0)) {
                aq aqVar = new aq(Ascii.FF, asVar.mo7199s());
                bcVar.f18796b = new ArrayList(aqVar.f18598b);
                while (i < aqVar.f18598b) {
                    ba baVar = new ba();
                    baVar.mo7211a(asVar);
                    bcVar.f18796b.add(baVar);
                    i++;
                }
                bcVar.m21452b(true);
            }
            if (b.get(1)) {
                bcVar.f18797c = asVar.mo7202v();
                bcVar.m21454c(true);
            }
        }
    }

    /* compiled from: IdTracking */
    /* renamed from: u.aly.bc$d */
    private static class C5888d implements az {
        private C5888d() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21427a();
        }

        /* renamed from: a */
        public C5887c m21427a() {
            return new C5887c();
        }
    }

    /* compiled from: IdTracking */
    /* renamed from: u.aly.bc$e */
    public enum C5889e implements am {
        SNAPSHOTS((short) 1, "snapshots"),
        JOURNALS((short) 2, "journals"),
        CHECKSUM((short) 3, "checksum");
        
        /* renamed from: d */
        private static final Map<String, C5889e> f18785d = null;
        /* renamed from: e */
        private final short f18787e;
        /* renamed from: f */
        private final String f18788f;

        static {
            f18785d = new HashMap();
            Iterator it = EnumSet.allOf(C5889e.class).iterator();
            while (it.hasNext()) {
                C5889e c5889e = (C5889e) it.next();
                f18785d.put(c5889e.m21433b(), c5889e);
            }
        }

        /* renamed from: a */
        public static C5889e m21429a(int i) {
            switch (i) {
                case 1:
                    return SNAPSHOTS;
                case 2:
                    return JOURNALS;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C5889e m21431b(int i) {
            C5889e a = C5889e.m21429a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C5889e m21430a(String str) {
            return (C5889e) f18785d.get(str);
        }

        private C5889e(short s, String str) {
            this.f18787e = s;
            this.f18788f = str;
        }

        /* renamed from: a */
        public short m21432a() {
            return this.f18787e;
        }

        /* renamed from: b */
        public String m21433b() {
            return this.f18788f;
        }
    }

    /* renamed from: b */
    public /* synthetic */ am mo7212b(int i) {
        return m21440a(i);
    }

    /* renamed from: p */
    public /* synthetic */ bp mo7215p() {
        return m21441a();
    }

    static {
        f18794i.put(bh.class, new C5886b());
        f18794i.put(bi.class, new C5888d());
        Map enumMap = new EnumMap(C5889e.class);
        enumMap.put(C5889e.SNAPSHOTS, new cb("snapshots", (byte) 1, new ce((byte) 13, new cc(Ascii.VT), new cg(Ascii.FF, bb.class))));
        enumMap.put(C5889e.JOURNALS, new cb("journals", (byte) 2, new cd(Ascii.SI, new cg(Ascii.FF, ba.class))));
        enumMap.put(C5889e.CHECKSUM, new cb("checksum", (byte) 2, new cc(Ascii.VT)));
        f18789d = Collections.unmodifiableMap(enumMap);
        cb.m21817a(bc.class, f18789d);
    }

    public bc() {
        this.f18798j = new C5889e[]{C5889e.JOURNALS, C5889e.CHECKSUM};
    }

    public bc(Map<String, bb> map) {
        this();
        this.f18795a = map;
    }

    public bc(bc bcVar) {
        this.f18798j = new C5889e[]{C5889e.JOURNALS, C5889e.CHECKSUM};
        if (bcVar.m21457f()) {
            Map hashMap = new HashMap();
            for (Entry entry : bcVar.f18795a.entrySet()) {
                hashMap.put((String) entry.getKey(), new bb((bb) entry.getValue()));
            }
            this.f18795a = hashMap;
        }
        if (bcVar.m21462k()) {
            List arrayList = new ArrayList();
            for (ba baVar : bcVar.f18796b) {
                arrayList.add(new ba(baVar));
            }
            this.f18796b = arrayList;
        }
        if (bcVar.m21465n()) {
            this.f18797c = bcVar.f18797c;
        }
    }

    /* renamed from: a */
    public bc m21441a() {
        return new bc(this);
    }

    /* renamed from: b */
    public void mo7213b() {
        this.f18795a = null;
        this.f18796b = null;
        this.f18797c = null;
    }

    /* renamed from: c */
    public int m21453c() {
        return this.f18795a == null ? 0 : this.f18795a.size();
    }

    /* renamed from: a */
    public void m21445a(String str, bb bbVar) {
        if (this.f18795a == null) {
            this.f18795a = new HashMap();
        }
        this.f18795a.put(str, bbVar);
    }

    /* renamed from: d */
    public Map<String, bb> m21455d() {
        return this.f18795a;
    }

    /* renamed from: a */
    public bc m21444a(Map<String, bb> map) {
        this.f18795a = map;
        return this;
    }

    /* renamed from: e */
    public void m21456e() {
        this.f18795a = null;
    }

    /* renamed from: f */
    public boolean m21457f() {
        return this.f18795a != null;
    }

    /* renamed from: a */
    public void m21448a(boolean z) {
        if (!z) {
            this.f18795a = null;
        }
    }

    /* renamed from: g */
    public int m21458g() {
        return this.f18796b == null ? 0 : this.f18796b.size();
    }

    /* renamed from: h */
    public Iterator<ba> m21459h() {
        return this.f18796b == null ? null : this.f18796b.iterator();
    }

    /* renamed from: a */
    public void m21447a(ba baVar) {
        if (this.f18796b == null) {
            this.f18796b = new ArrayList();
        }
        this.f18796b.add(baVar);
    }

    /* renamed from: i */
    public List<ba> m21460i() {
        return this.f18796b;
    }

    /* renamed from: a */
    public bc m21443a(List<ba> list) {
        this.f18796b = list;
        return this;
    }

    /* renamed from: j */
    public void m21461j() {
        this.f18796b = null;
    }

    /* renamed from: k */
    public boolean m21462k() {
        return this.f18796b != null;
    }

    /* renamed from: b */
    public void m21452b(boolean z) {
        if (!z) {
            this.f18796b = null;
        }
    }

    /* renamed from: l */
    public String m21463l() {
        return this.f18797c;
    }

    /* renamed from: a */
    public bc m21442a(String str) {
        this.f18797c = str;
        return this;
    }

    /* renamed from: m */
    public void m21464m() {
        this.f18797c = null;
    }

    /* renamed from: n */
    public boolean m21465n() {
        return this.f18797c != null;
    }

    /* renamed from: c */
    public void m21454c(boolean z) {
        if (!z) {
            this.f18797c = null;
        }
    }

    /* renamed from: a */
    public C5889e m21440a(int i) {
        return C5889e.m21429a(i);
    }

    /* renamed from: a */
    public void mo7211a(as asVar) throws bv {
        ((az) f18794i.get(asVar.mo7206y())).mo7210b().mo7209b(asVar, this);
    }

    /* renamed from: b */
    public void mo7214b(as asVar) throws bv {
        ((az) f18794i.get(asVar.mo7206y())).mo7210b().mo7208a(asVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdTracking(");
        stringBuilder.append("snapshots:");
        if (this.f18795a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f18795a);
        }
        if (m21462k()) {
            stringBuilder.append(", ");
            stringBuilder.append("journals:");
            if (this.f18796b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f18796b);
            }
        }
        if (m21465n()) {
            stringBuilder.append(", ");
            stringBuilder.append("checksum:");
            if (this.f18797c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f18797c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /* renamed from: o */
    public void m21466o() throws bv {
        if (this.f18795a == null) {
            throw new cp("Required field 'snapshots' was not present! Struct: " + toString());
        }
    }

    /* renamed from: a */
    private void m21435a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo7214b(new ci(new bk((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m21434a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            mo7211a(new ci(new bk((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
