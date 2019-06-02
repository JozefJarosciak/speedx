package p203u.aly;

import com.google.common.base.Ascii;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: Imprint */
/* renamed from: u.aly.bd */
public class bd implements Serializable, Cloneable, bp<bd, C5895e> {
    /* renamed from: d */
    public static final Map<C5895e, cb> f18806d;
    /* renamed from: e */
    private static final aw f18807e = new aw("Imprint");
    /* renamed from: f */
    private static final ap f18808f = new ap("property", (byte) 13, (short) 1);
    /* renamed from: g */
    private static final ap f18809g = new ap(MapboxEvent.ATTRIBUTE_VERSION, (byte) 8, (short) 2);
    /* renamed from: h */
    private static final ap f18810h = new ap("checksum", Ascii.VT, (short) 3);
    /* renamed from: i */
    private static final Map<Class<? extends ay>, az> f18811i = new HashMap();
    /* renamed from: j */
    private static final int f18812j = 0;
    /* renamed from: a */
    public Map<String, be> f18813a;
    /* renamed from: b */
    public int f18814b;
    /* renamed from: c */
    public String f18815c;
    /* renamed from: k */
    private byte f18816k;

    /* compiled from: Imprint */
    /* renamed from: u.aly.bd$a */
    private static class C5891a extends bh<bd> {
        private C5891a() {
        }

        /* renamed from: a */
        public /* synthetic */ void mo7208a(as asVar, bp bpVar) throws bv {
            m21470b(asVar, (bd) bpVar);
        }

        /* renamed from: b */
        public /* synthetic */ void mo7209b(as asVar, bp bpVar) throws bv {
            m21468a(asVar, (bd) bpVar);
        }

        /* renamed from: a */
        public void m21468a(as asVar, bd bdVar) throws bv {
            asVar.mo7186f();
            while (true) {
                ap h = asVar.mo7188h();
                if (h.f18595b == (byte) 0) {
                    asVar.mo7187g();
                    if (bdVar.m21510i()) {
                        bdVar.m21514m();
                        return;
                    }
                    throw new cp("Required field 'version' was not found in serialized data! Struct: " + toString());
                }
                switch (h.f18596c) {
                    case (short) 1:
                        if (h.f18595b != (byte) 13) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        ar j = asVar.mo7190j();
                        bdVar.f18813a = new HashMap(j.f18601c * 2);
                        for (int i = 0; i < j.f18601c; i++) {
                            String v = asVar.mo7202v();
                            be beVar = new be();
                            beVar.mo7211a(asVar);
                            bdVar.f18813a.put(v, beVar);
                        }
                        asVar.mo7191k();
                        bdVar.m21497a(true);
                        break;
                    case (short) 2:
                        if (h.f18595b != (byte) 8) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bdVar.f18814b = asVar.mo7199s();
                        bdVar.m21501b(true);
                        break;
                    case (short) 3:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bdVar.f18815c = asVar.mo7202v();
                        bdVar.m21504c(true);
                        break;
                    default:
                        at.m21234a(asVar, h.f18595b);
                        break;
                }
                asVar.mo7189i();
            }
        }

        /* renamed from: b */
        public void m21470b(as asVar, bd bdVar) throws bv {
            bdVar.m21514m();
            asVar.mo7181a(bd.f18807e);
            if (bdVar.f18813a != null) {
                asVar.mo7178a(bd.f18808f);
                asVar.mo7180a(new ar(Ascii.VT, Ascii.FF, bdVar.f18813a.size()));
                for (Entry entry : bdVar.f18813a.entrySet()) {
                    asVar.mo7176a((String) entry.getKey());
                    ((be) entry.getValue()).mo7214b(asVar);
                }
                asVar.mo7184d();
                asVar.mo7182b();
            }
            asVar.mo7178a(bd.f18809g);
            asVar.mo7174a(bdVar.f18814b);
            asVar.mo7182b();
            if (bdVar.f18815c != null) {
                asVar.mo7178a(bd.f18810h);
                asVar.mo7176a(bdVar.f18815c);
                asVar.mo7182b();
            }
            asVar.mo7183c();
            asVar.mo7173a();
        }
    }

    /* compiled from: Imprint */
    /* renamed from: u.aly.bd$b */
    private static class C5892b implements az {
        private C5892b() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21472a();
        }

        /* renamed from: a */
        public C5891a m21472a() {
            return new C5891a();
        }
    }

    /* compiled from: Imprint */
    /* renamed from: u.aly.bd$c */
    private static class C5893c extends bi<bd> {
        private C5893c() {
        }

        /* renamed from: a */
        public void m21474a(as asVar, bd bdVar) throws bv {
            asVar = (ax) asVar;
            asVar.mo7174a(bdVar.f18813a.size());
            for (Entry entry : bdVar.f18813a.entrySet()) {
                asVar.mo7176a((String) entry.getKey());
                ((be) entry.getValue()).mo7214b(asVar);
            }
            asVar.mo7174a(bdVar.f18814b);
            asVar.mo7176a(bdVar.f18815c);
        }

        /* renamed from: b */
        public void m21476b(as asVar, bd bdVar) throws bv {
            asVar = (ax) asVar;
            ar arVar = new ar(Ascii.VT, Ascii.FF, asVar.mo7199s());
            bdVar.f18813a = new HashMap(arVar.f18601c * 2);
            for (int i = 0; i < arVar.f18601c; i++) {
                String v = asVar.mo7202v();
                be beVar = new be();
                beVar.mo7211a(asVar);
                bdVar.f18813a.put(v, beVar);
            }
            bdVar.m21497a(true);
            bdVar.f18814b = asVar.mo7199s();
            bdVar.m21501b(true);
            bdVar.f18815c = asVar.mo7202v();
            bdVar.m21504c(true);
        }
    }

    /* compiled from: Imprint */
    /* renamed from: u.aly.bd$d */
    private static class C5894d implements az {
        private C5894d() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21478a();
        }

        /* renamed from: a */
        public C5893c m21478a() {
            return new C5893c();
        }
    }

    /* compiled from: Imprint */
    /* renamed from: u.aly.bd$e */
    public enum C5895e implements am {
        PROPERTY((short) 1, "property"),
        VERSION((short) 2, MapboxEvent.ATTRIBUTE_VERSION),
        CHECKSUM((short) 3, "checksum");
        
        /* renamed from: d */
        private static final Map<String, C5895e> f18802d = null;
        /* renamed from: e */
        private final short f18804e;
        /* renamed from: f */
        private final String f18805f;

        static {
            f18802d = new HashMap();
            Iterator it = EnumSet.allOf(C5895e.class).iterator();
            while (it.hasNext()) {
                C5895e c5895e = (C5895e) it.next();
                f18802d.put(c5895e.m21484b(), c5895e);
            }
        }

        /* renamed from: a */
        public static C5895e m21480a(int i) {
            switch (i) {
                case 1:
                    return PROPERTY;
                case 2:
                    return VERSION;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C5895e m21482b(int i) {
            C5895e a = C5895e.m21480a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C5895e m21481a(String str) {
            return (C5895e) f18802d.get(str);
        }

        private C5895e(short s, String str) {
            this.f18804e = s;
            this.f18805f = str;
        }

        /* renamed from: a */
        public short m21483a() {
            return this.f18804e;
        }

        /* renamed from: b */
        public String m21484b() {
            return this.f18805f;
        }
    }

    /* renamed from: b */
    public /* synthetic */ am mo7212b(int i) {
        return m21503c(i);
    }

    /* renamed from: p */
    public /* synthetic */ bp mo7215p() {
        return m21491a();
    }

    static {
        f18811i.put(bh.class, new C5892b());
        f18811i.put(bi.class, new C5894d());
        Map enumMap = new EnumMap(C5895e.class);
        enumMap.put(C5895e.PROPERTY, new cb("property", (byte) 1, new ce((byte) 13, new cc(Ascii.VT), new cg(Ascii.FF, be.class))));
        enumMap.put(C5895e.VERSION, new cb(MapboxEvent.ATTRIBUTE_VERSION, (byte) 1, new cc((byte) 8)));
        enumMap.put(C5895e.CHECKSUM, new cb("checksum", (byte) 1, new cc(Ascii.VT)));
        f18806d = Collections.unmodifiableMap(enumMap);
        cb.m21817a(bd.class, f18806d);
    }

    public bd() {
        this.f18816k = (byte) 0;
    }

    public bd(Map<String, be> map, int i, String str) {
        this();
        this.f18813a = map;
        this.f18814b = i;
        m21501b(true);
        this.f18815c = str;
    }

    public bd(bd bdVar) {
        this.f18816k = (byte) 0;
        this.f18816k = bdVar.f18816k;
        if (bdVar.m21507f()) {
            Map hashMap = new HashMap();
            for (Entry entry : bdVar.f18813a.entrySet()) {
                hashMap.put((String) entry.getKey(), new be((be) entry.getValue()));
            }
            this.f18813a = hashMap;
        }
        this.f18814b = bdVar.f18814b;
        if (bdVar.m21513l()) {
            this.f18815c = bdVar.f18815c;
        }
    }

    /* renamed from: a */
    public bd m21491a() {
        return new bd(this);
    }

    /* renamed from: b */
    public void mo7213b() {
        this.f18813a = null;
        m21501b(false);
        this.f18814b = 0;
        this.f18815c = null;
    }

    /* renamed from: c */
    public int m21502c() {
        return this.f18813a == null ? 0 : this.f18813a.size();
    }

    /* renamed from: a */
    public void m21495a(String str, be beVar) {
        if (this.f18813a == null) {
            this.f18813a = new HashMap();
        }
        this.f18813a.put(str, beVar);
    }

    /* renamed from: d */
    public Map<String, be> m21505d() {
        return this.f18813a;
    }

    /* renamed from: a */
    public bd m21494a(Map<String, be> map) {
        this.f18813a = map;
        return this;
    }

    /* renamed from: e */
    public void m21506e() {
        this.f18813a = null;
    }

    /* renamed from: f */
    public boolean m21507f() {
        return this.f18813a != null;
    }

    /* renamed from: a */
    public void m21497a(boolean z) {
        if (!z) {
            this.f18813a = null;
        }
    }

    /* renamed from: g */
    public int m21508g() {
        return this.f18814b;
    }

    /* renamed from: a */
    public bd m21492a(int i) {
        this.f18814b = i;
        m21501b(true);
        return this;
    }

    /* renamed from: h */
    public void m21509h() {
        this.f18816k = ai.m21171b(this.f18816k, 0);
    }

    /* renamed from: i */
    public boolean m21510i() {
        return ai.m21169a(this.f18816k, 0);
    }

    /* renamed from: b */
    public void m21501b(boolean z) {
        this.f18816k = ai.m21167a(this.f18816k, 0, z);
    }

    /* renamed from: j */
    public String m21511j() {
        return this.f18815c;
    }

    /* renamed from: a */
    public bd m21493a(String str) {
        this.f18815c = str;
        return this;
    }

    /* renamed from: k */
    public void m21512k() {
        this.f18815c = null;
    }

    /* renamed from: l */
    public boolean m21513l() {
        return this.f18815c != null;
    }

    /* renamed from: c */
    public void m21504c(boolean z) {
        if (!z) {
            this.f18815c = null;
        }
    }

    /* renamed from: c */
    public C5895e m21503c(int i) {
        return C5895e.m21480a(i);
    }

    /* renamed from: a */
    public void mo7211a(as asVar) throws bv {
        ((az) f18811i.get(asVar.mo7206y())).mo7210b().mo7209b(asVar, this);
    }

    /* renamed from: b */
    public void mo7214b(as asVar) throws bv {
        ((az) f18811i.get(asVar.mo7206y())).mo7210b().mo7208a(asVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Imprint(");
        stringBuilder.append("property:");
        if (this.f18813a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f18813a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.f18814b);
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.f18815c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f18815c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /* renamed from: m */
    public void m21514m() throws bv {
        if (this.f18813a == null) {
            throw new cp("Required field 'property' was not present! Struct: " + toString());
        } else if (this.f18815c == null) {
            throw new cp("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    /* renamed from: a */
    private void m21486a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo7214b(new ci(new bk((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m21485a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f18816k = (byte) 0;
            mo7211a(new ci(new bk((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
