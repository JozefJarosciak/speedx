package p203u.aly;

import com.google.common.base.Ascii;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: UMEnvelope */
/* renamed from: u.aly.bg */
public class bg implements Serializable, Cloneable, bp<bg, C5913e> {
    /* renamed from: A */
    private static final int f18869A = 3;
    /* renamed from: k */
    public static final Map<C5913e, cb> f18870k;
    /* renamed from: l */
    private static final aw f18871l = new aw("UMEnvelope");
    /* renamed from: m */
    private static final ap f18872m = new ap(MapboxEvent.ATTRIBUTE_VERSION, Ascii.VT, (short) 1);
    /* renamed from: n */
    private static final ap f18873n = new ap(GeocodingCriteria.TYPE_ADDRESS, Ascii.VT, (short) 2);
    /* renamed from: o */
    private static final ap f18874o = new ap("signature", Ascii.VT, (short) 3);
    /* renamed from: p */
    private static final ap f18875p = new ap("serial_num", (byte) 8, (short) 4);
    /* renamed from: q */
    private static final ap f18876q = new ap("ts_secs", (byte) 8, (short) 5);
    /* renamed from: r */
    private static final ap f18877r = new ap("length", (byte) 8, (short) 6);
    /* renamed from: s */
    private static final ap f18878s = new ap("entity", Ascii.VT, (short) 7);
    /* renamed from: t */
    private static final ap f18879t = new ap("guid", Ascii.VT, (short) 8);
    /* renamed from: u */
    private static final ap f18880u = new ap("checksum", Ascii.VT, (short) 9);
    /* renamed from: v */
    private static final ap f18881v = new ap("codex", (byte) 8, (short) 10);
    /* renamed from: w */
    private static final Map<Class<? extends ay>, az> f18882w = new HashMap();
    /* renamed from: x */
    private static final int f18883x = 0;
    /* renamed from: y */
    private static final int f18884y = 1;
    /* renamed from: z */
    private static final int f18885z = 2;
    /* renamed from: B */
    private byte f18886B;
    /* renamed from: C */
    private C5913e[] f18887C;
    /* renamed from: a */
    public String f18888a;
    /* renamed from: b */
    public String f18889b;
    /* renamed from: c */
    public String f18890c;
    /* renamed from: d */
    public int f18891d;
    /* renamed from: e */
    public int f18892e;
    /* renamed from: f */
    public int f18893f;
    /* renamed from: g */
    public ByteBuffer f18894g;
    /* renamed from: h */
    public String f18895h;
    /* renamed from: i */
    public String f18896i;
    /* renamed from: j */
    public int f18897j;

    /* compiled from: UMEnvelope */
    /* renamed from: u.aly.bg$a */
    private static class C5909a extends bh<bg> {
        private C5909a() {
        }

        /* renamed from: a */
        public /* synthetic */ void mo7208a(as asVar, bp bpVar) throws bv {
            m21610b(asVar, (bg) bpVar);
        }

        /* renamed from: b */
        public /* synthetic */ void mo7209b(as asVar, bp bpVar) throws bv {
            m21608a(asVar, (bg) bpVar);
        }

        /* renamed from: a */
        public void m21608a(as asVar, bg bgVar) throws bv {
            asVar.mo7186f();
            while (true) {
                ap h = asVar.mo7188h();
                if (h.f18595b == (byte) 0) {
                    asVar.mo7187g();
                    if (!bgVar.m21685n()) {
                        throw new cp("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!bgVar.m21689r()) {
                        throw new cp("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (bgVar.m21692u()) {
                        bgVar.m21646I();
                        return;
                    } else {
                        throw new cp("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (h.f18596c) {
                    case (short) 1:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bgVar.f18888a = asVar.mo7202v();
                        bgVar.m21653a(true);
                        break;
                    case (short) 2:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bgVar.f18889b = asVar.mo7202v();
                        bgVar.m21658b(true);
                        break;
                    case (short) 3:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bgVar.f18890c = asVar.mo7202v();
                        bgVar.m21662c(true);
                        break;
                    case (short) 4:
                        if (h.f18595b != (byte) 8) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bgVar.f18891d = asVar.mo7199s();
                        bgVar.m21666d(true);
                        break;
                    case (short) 5:
                        if (h.f18595b != (byte) 8) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bgVar.f18892e = asVar.mo7199s();
                        bgVar.m21669e(true);
                        break;
                    case (short) 6:
                        if (h.f18595b != (byte) 8) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bgVar.f18893f = asVar.mo7199s();
                        bgVar.m21673f(true);
                        break;
                    case (short) 7:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bgVar.f18894g = asVar.mo7203w();
                        bgVar.m21675g(true);
                        break;
                    case (short) 8:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bgVar.f18895h = asVar.mo7202v();
                        bgVar.m21676h(true);
                        break;
                    case (short) 9:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bgVar.f18896i = asVar.mo7202v();
                        bgVar.m21679i(true);
                        break;
                    case (short) 10:
                        if (h.f18595b != (byte) 8) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bgVar.f18897j = asVar.mo7199s();
                        bgVar.m21681j(true);
                        break;
                    default:
                        at.m21234a(asVar, h.f18595b);
                        break;
                }
                asVar.mo7189i();
            }
        }

        /* renamed from: b */
        public void m21610b(as asVar, bg bgVar) throws bv {
            bgVar.m21646I();
            asVar.mo7181a(bg.f18871l);
            if (bgVar.f18888a != null) {
                asVar.mo7178a(bg.f18872m);
                asVar.mo7176a(bgVar.f18888a);
                asVar.mo7182b();
            }
            if (bgVar.f18889b != null) {
                asVar.mo7178a(bg.f18873n);
                asVar.mo7176a(bgVar.f18889b);
                asVar.mo7182b();
            }
            if (bgVar.f18890c != null) {
                asVar.mo7178a(bg.f18874o);
                asVar.mo7176a(bgVar.f18890c);
                asVar.mo7182b();
            }
            asVar.mo7178a(bg.f18875p);
            asVar.mo7174a(bgVar.f18891d);
            asVar.mo7182b();
            asVar.mo7178a(bg.f18876q);
            asVar.mo7174a(bgVar.f18892e);
            asVar.mo7182b();
            asVar.mo7178a(bg.f18877r);
            asVar.mo7174a(bgVar.f18893f);
            asVar.mo7182b();
            if (bgVar.f18894g != null) {
                asVar.mo7178a(bg.f18878s);
                asVar.mo7177a(bgVar.f18894g);
                asVar.mo7182b();
            }
            if (bgVar.f18895h != null) {
                asVar.mo7178a(bg.f18879t);
                asVar.mo7176a(bgVar.f18895h);
                asVar.mo7182b();
            }
            if (bgVar.f18896i != null) {
                asVar.mo7178a(bg.f18880u);
                asVar.mo7176a(bgVar.f18896i);
                asVar.mo7182b();
            }
            if (bgVar.m21645H()) {
                asVar.mo7178a(bg.f18881v);
                asVar.mo7174a(bgVar.f18897j);
                asVar.mo7182b();
            }
            asVar.mo7183c();
            asVar.mo7173a();
        }
    }

    /* compiled from: UMEnvelope */
    /* renamed from: u.aly.bg$b */
    private static class C5910b implements az {
        private C5910b() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21612a();
        }

        /* renamed from: a */
        public C5909a m21612a() {
            return new C5909a();
        }
    }

    /* compiled from: UMEnvelope */
    /* renamed from: u.aly.bg$c */
    private static class C5911c extends bi<bg> {
        private C5911c() {
        }

        /* renamed from: a */
        public void m21614a(as asVar, bg bgVar) throws bv {
            ax axVar = (ax) asVar;
            axVar.mo7176a(bgVar.f18888a);
            axVar.mo7176a(bgVar.f18889b);
            axVar.mo7176a(bgVar.f18890c);
            axVar.mo7174a(bgVar.f18891d);
            axVar.mo7174a(bgVar.f18892e);
            axVar.mo7174a(bgVar.f18893f);
            axVar.mo7177a(bgVar.f18894g);
            axVar.mo7176a(bgVar.f18895h);
            axVar.mo7176a(bgVar.f18896i);
            BitSet bitSet = new BitSet();
            if (bgVar.m21645H()) {
                bitSet.set(0);
            }
            axVar.m21297a(bitSet, 1);
            if (bgVar.m21645H()) {
                axVar.mo7174a(bgVar.f18897j);
            }
        }

        /* renamed from: b */
        public void m21616b(as asVar, bg bgVar) throws bv {
            ax axVar = (ax) asVar;
            bgVar.f18888a = axVar.mo7202v();
            bgVar.m21653a(true);
            bgVar.f18889b = axVar.mo7202v();
            bgVar.m21658b(true);
            bgVar.f18890c = axVar.mo7202v();
            bgVar.m21662c(true);
            bgVar.f18891d = axVar.mo7199s();
            bgVar.m21666d(true);
            bgVar.f18892e = axVar.mo7199s();
            bgVar.m21669e(true);
            bgVar.f18893f = axVar.mo7199s();
            bgVar.m21673f(true);
            bgVar.f18894g = axVar.mo7203w();
            bgVar.m21675g(true);
            bgVar.f18895h = axVar.mo7202v();
            bgVar.m21676h(true);
            bgVar.f18896i = axVar.mo7202v();
            bgVar.m21679i(true);
            if (axVar.mo7205b(1).get(0)) {
                bgVar.f18897j = axVar.mo7199s();
                bgVar.m21681j(true);
            }
        }
    }

    /* compiled from: UMEnvelope */
    /* renamed from: u.aly.bg$d */
    private static class C5912d implements az {
        private C5912d() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21618a();
        }

        /* renamed from: a */
        public C5911c m21618a() {
            return new C5911c();
        }
    }

    /* compiled from: UMEnvelope */
    /* renamed from: u.aly.bg$e */
    public enum C5913e implements am {
        VERSION((short) 1, MapboxEvent.ATTRIBUTE_VERSION),
        ADDRESS((short) 2, GeocodingCriteria.TYPE_ADDRESS),
        SIGNATURE((short) 3, "signature"),
        SERIAL_NUM((short) 4, "serial_num"),
        TS_SECS((short) 5, "ts_secs"),
        LENGTH((short) 6, "length"),
        ENTITY((short) 7, "entity"),
        GUID((short) 8, "guid"),
        CHECKSUM((short) 9, "checksum"),
        CODEX((short) 10, "codex");
        
        /* renamed from: k */
        private static final Map<String, C5913e> f18865k = null;
        /* renamed from: l */
        private final short f18867l;
        /* renamed from: m */
        private final String f18868m;

        static {
            f18865k = new HashMap();
            Iterator it = EnumSet.allOf(C5913e.class).iterator();
            while (it.hasNext()) {
                C5913e c5913e = (C5913e) it.next();
                f18865k.put(c5913e.m21624b(), c5913e);
            }
        }

        /* renamed from: a */
        public static C5913e m21620a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C5913e m21622b(int i) {
            C5913e a = C5913e.m21620a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C5913e m21621a(String str) {
            return (C5913e) f18865k.get(str);
        }

        private C5913e(short s, String str) {
            this.f18867l = s;
            this.f18868m = str;
        }

        /* renamed from: a */
        public short m21623a() {
            return this.f18867l;
        }

        /* renamed from: b */
        public String m21624b() {
            return this.f18868m;
        }
    }

    /* renamed from: b */
    public /* synthetic */ am mo7212b(int i) {
        return m21672f(i);
    }

    /* renamed from: p */
    public /* synthetic */ bp mo7215p() {
        return m21647a();
    }

    static {
        f18882w.put(bh.class, new C5910b());
        f18882w.put(bi.class, new C5912d());
        Map enumMap = new EnumMap(C5913e.class);
        enumMap.put(C5913e.VERSION, new cb(MapboxEvent.ATTRIBUTE_VERSION, (byte) 1, new cc(Ascii.VT)));
        enumMap.put(C5913e.ADDRESS, new cb(GeocodingCriteria.TYPE_ADDRESS, (byte) 1, new cc(Ascii.VT)));
        enumMap.put(C5913e.SIGNATURE, new cb("signature", (byte) 1, new cc(Ascii.VT)));
        enumMap.put(C5913e.SERIAL_NUM, new cb("serial_num", (byte) 1, new cc((byte) 8)));
        enumMap.put(C5913e.TS_SECS, new cb("ts_secs", (byte) 1, new cc((byte) 8)));
        enumMap.put(C5913e.LENGTH, new cb("length", (byte) 1, new cc((byte) 8)));
        enumMap.put(C5913e.ENTITY, new cb("entity", (byte) 1, new cc((byte) Ascii.VT, true)));
        enumMap.put(C5913e.GUID, new cb("guid", (byte) 1, new cc(Ascii.VT)));
        enumMap.put(C5913e.CHECKSUM, new cb("checksum", (byte) 1, new cc(Ascii.VT)));
        enumMap.put(C5913e.CODEX, new cb("codex", (byte) 2, new cc((byte) 8)));
        f18870k = Collections.unmodifiableMap(enumMap);
        cb.m21817a(bg.class, f18870k);
    }

    public bg() {
        this.f18886B = (byte) 0;
        this.f18887C = new C5913e[]{C5913e.CODEX};
    }

    public bg(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.f18888a = str;
        this.f18889b = str2;
        this.f18890c = str3;
        this.f18891d = i;
        m21666d(true);
        this.f18892e = i2;
        m21669e(true);
        this.f18893f = i3;
        m21673f(true);
        this.f18894g = byteBuffer;
        this.f18895h = str4;
        this.f18896i = str5;
    }

    public bg(bg bgVar) {
        this.f18886B = (byte) 0;
        this.f18887C = new C5913e[]{C5913e.CODEX};
        this.f18886B = bgVar.f18886B;
        if (bgVar.m21670e()) {
            this.f18888a = bgVar.f18888a;
        }
        if (bgVar.m21677h()) {
            this.f18889b = bgVar.f18889b;
        }
        if (bgVar.m21682k()) {
            this.f18890c = bgVar.f18890c;
        }
        this.f18891d = bgVar.f18891d;
        this.f18892e = bgVar.f18892e;
        this.f18893f = bgVar.f18893f;
        if (bgVar.m21696y()) {
            this.f18894g = ak.m21190d(bgVar.f18894g);
        }
        if (bgVar.m21639B()) {
            this.f18895h = bgVar.f18895h;
        }
        if (bgVar.m21642E()) {
            this.f18896i = bgVar.f18896i;
        }
        this.f18897j = bgVar.f18897j;
    }

    /* renamed from: a */
    public bg m21647a() {
        return new bg(this);
    }

    /* renamed from: b */
    public void mo7213b() {
        this.f18888a = null;
        this.f18889b = null;
        this.f18890c = null;
        m21666d(false);
        this.f18891d = 0;
        m21669e(false);
        this.f18892e = 0;
        m21673f(false);
        this.f18893f = 0;
        this.f18894g = null;
        this.f18895h = null;
        this.f18896i = null;
        m21681j(false);
        this.f18897j = 0;
    }

    /* renamed from: c */
    public String m21659c() {
        return this.f18888a;
    }

    /* renamed from: a */
    public bg m21649a(String str) {
        this.f18888a = str;
        return this;
    }

    /* renamed from: d */
    public void m21665d() {
        this.f18888a = null;
    }

    /* renamed from: e */
    public boolean m21670e() {
        return this.f18888a != null;
    }

    /* renamed from: a */
    public void m21653a(boolean z) {
        if (!z) {
            this.f18888a = null;
        }
    }

    /* renamed from: f */
    public String m21671f() {
        return this.f18889b;
    }

    /* renamed from: b */
    public bg m21655b(String str) {
        this.f18889b = str;
        return this;
    }

    /* renamed from: g */
    public void m21674g() {
        this.f18889b = null;
    }

    /* renamed from: h */
    public boolean m21677h() {
        return this.f18889b != null;
    }

    /* renamed from: b */
    public void m21658b(boolean z) {
        if (!z) {
            this.f18889b = null;
        }
    }

    /* renamed from: i */
    public String m21678i() {
        return this.f18890c;
    }

    /* renamed from: c */
    public bg m21661c(String str) {
        this.f18890c = str;
        return this;
    }

    /* renamed from: j */
    public void m21680j() {
        this.f18890c = null;
    }

    /* renamed from: k */
    public boolean m21682k() {
        return this.f18890c != null;
    }

    /* renamed from: c */
    public void m21662c(boolean z) {
        if (!z) {
            this.f18890c = null;
        }
    }

    /* renamed from: l */
    public int m21683l() {
        return this.f18891d;
    }

    /* renamed from: a */
    public bg m21648a(int i) {
        this.f18891d = i;
        m21666d(true);
        return this;
    }

    /* renamed from: m */
    public void m21684m() {
        this.f18886B = ai.m21171b(this.f18886B, 0);
    }

    /* renamed from: n */
    public boolean m21685n() {
        return ai.m21169a(this.f18886B, 0);
    }

    /* renamed from: d */
    public void m21666d(boolean z) {
        this.f18886B = ai.m21167a(this.f18886B, 0, z);
    }

    /* renamed from: o */
    public int m21686o() {
        return this.f18892e;
    }

    /* renamed from: c */
    public bg m21660c(int i) {
        this.f18892e = i;
        m21669e(true);
        return this;
    }

    /* renamed from: q */
    public void m21688q() {
        this.f18886B = ai.m21171b(this.f18886B, 1);
    }

    /* renamed from: r */
    public boolean m21689r() {
        return ai.m21169a(this.f18886B, 1);
    }

    /* renamed from: e */
    public void m21669e(boolean z) {
        this.f18886B = ai.m21167a(this.f18886B, 1, z);
    }

    /* renamed from: s */
    public int m21690s() {
        return this.f18893f;
    }

    /* renamed from: d */
    public bg m21663d(int i) {
        this.f18893f = i;
        m21673f(true);
        return this;
    }

    /* renamed from: t */
    public void m21691t() {
        this.f18886B = ai.m21171b(this.f18886B, 2);
    }

    /* renamed from: u */
    public boolean m21692u() {
        return ai.m21169a(this.f18886B, 2);
    }

    /* renamed from: f */
    public void m21673f(boolean z) {
        this.f18886B = ai.m21167a(this.f18886B, 2, z);
    }

    /* renamed from: v */
    public byte[] m21693v() {
        m21650a(ak.m21189c(this.f18894g));
        return this.f18894g == null ? null : this.f18894g.array();
    }

    /* renamed from: w */
    public ByteBuffer m21694w() {
        return this.f18894g;
    }

    /* renamed from: a */
    public bg m21651a(byte[] bArr) {
        m21650a(bArr == null ? (ByteBuffer) null : ByteBuffer.wrap(bArr));
        return this;
    }

    /* renamed from: a */
    public bg m21650a(ByteBuffer byteBuffer) {
        this.f18894g = byteBuffer;
        return this;
    }

    /* renamed from: x */
    public void m21695x() {
        this.f18894g = null;
    }

    /* renamed from: y */
    public boolean m21696y() {
        return this.f18894g != null;
    }

    /* renamed from: g */
    public void m21675g(boolean z) {
        if (!z) {
            this.f18894g = null;
        }
    }

    /* renamed from: z */
    public String m21697z() {
        return this.f18895h;
    }

    /* renamed from: d */
    public bg m21664d(String str) {
        this.f18895h = str;
        return this;
    }

    /* renamed from: A */
    public void m21638A() {
        this.f18895h = null;
    }

    /* renamed from: B */
    public boolean m21639B() {
        return this.f18895h != null;
    }

    /* renamed from: h */
    public void m21676h(boolean z) {
        if (!z) {
            this.f18895h = null;
        }
    }

    /* renamed from: C */
    public String m21640C() {
        return this.f18896i;
    }

    /* renamed from: e */
    public bg m21668e(String str) {
        this.f18896i = str;
        return this;
    }

    /* renamed from: D */
    public void m21641D() {
        this.f18896i = null;
    }

    /* renamed from: E */
    public boolean m21642E() {
        return this.f18896i != null;
    }

    /* renamed from: i */
    public void m21679i(boolean z) {
        if (!z) {
            this.f18896i = null;
        }
    }

    /* renamed from: F */
    public int m21643F() {
        return this.f18897j;
    }

    /* renamed from: e */
    public bg m21667e(int i) {
        this.f18897j = i;
        m21681j(true);
        return this;
    }

    /* renamed from: G */
    public void m21644G() {
        this.f18886B = ai.m21171b(this.f18886B, 3);
    }

    /* renamed from: H */
    public boolean m21645H() {
        return ai.m21169a(this.f18886B, 3);
    }

    /* renamed from: j */
    public void m21681j(boolean z) {
        this.f18886B = ai.m21167a(this.f18886B, 3, z);
    }

    /* renamed from: f */
    public C5913e m21672f(int i) {
        return C5913e.m21620a(i);
    }

    /* renamed from: a */
    public void mo7211a(as asVar) throws bv {
        ((az) f18882w.get(asVar.mo7206y())).mo7210b().mo7209b(asVar, this);
    }

    /* renamed from: b */
    public void mo7214b(as asVar) throws bv {
        ((az) f18882w.get(asVar.mo7206y())).mo7210b().mo7208a(asVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("UMEnvelope(");
        stringBuilder.append("version:");
        if (this.f18888a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f18888a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("address:");
        if (this.f18889b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f18889b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("signature:");
        if (this.f18890c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f18890c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("serial_num:");
        stringBuilder.append(this.f18891d);
        stringBuilder.append(", ");
        stringBuilder.append("ts_secs:");
        stringBuilder.append(this.f18892e);
        stringBuilder.append(", ");
        stringBuilder.append("length:");
        stringBuilder.append(this.f18893f);
        stringBuilder.append(", ");
        stringBuilder.append("entity:");
        if (this.f18894g == null) {
            stringBuilder.append("null");
        } else {
            ak.m21186a(this.f18894g, stringBuilder);
        }
        stringBuilder.append(", ");
        stringBuilder.append("guid:");
        if (this.f18895h == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f18895h);
        }
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.f18896i == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f18896i);
        }
        if (m21645H()) {
            stringBuilder.append(", ");
            stringBuilder.append("codex:");
            stringBuilder.append(this.f18897j);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /* renamed from: I */
    public void m21646I() throws bv {
        if (this.f18888a == null) {
            throw new cp("Required field 'version' was not present! Struct: " + toString());
        } else if (this.f18889b == null) {
            throw new cp("Required field 'address' was not present! Struct: " + toString());
        } else if (this.f18890c == null) {
            throw new cp("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.f18894g == null) {
            throw new cp("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.f18895h == null) {
            throw new cp("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.f18896i == null) {
            throw new cp("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    /* renamed from: a */
    private void m21637a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo7214b(new ci(new bk((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m21636a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f18886B = (byte) 0;
            mo7211a(new ci(new bk((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
