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
import org.apache.http.protocol.HTTP;

/* compiled from: IdSnapshot */
/* renamed from: u.aly.bb */
public class bb implements Serializable, Cloneable, bp<bb, C5883e> {
    /* renamed from: d */
    public static final Map<C5883e, cb> f18770d;
    /* renamed from: e */
    private static final aw f18771e = new aw("IdSnapshot");
    /* renamed from: f */
    private static final ap f18772f = new ap(HTTP.IDENTITY_CODING, Ascii.VT, (short) 1);
    /* renamed from: g */
    private static final ap f18773g = new ap("ts", (byte) 10, (short) 2);
    /* renamed from: h */
    private static final ap f18774h = new ap(MapboxEvent.ATTRIBUTE_VERSION, (byte) 8, (short) 3);
    /* renamed from: i */
    private static final Map<Class<? extends ay>, az> f18775i = new HashMap();
    /* renamed from: j */
    private static final int f18776j = 0;
    /* renamed from: k */
    private static final int f18777k = 1;
    /* renamed from: a */
    public String f18778a;
    /* renamed from: b */
    public long f18779b;
    /* renamed from: c */
    public int f18780c;
    /* renamed from: l */
    private byte f18781l;

    /* compiled from: IdSnapshot */
    /* renamed from: u.aly.bb$a */
    private static class C5879a extends bh<bb> {
        private C5879a() {
        }

        /* renamed from: a */
        public /* synthetic */ void mo7208a(as asVar, bp bpVar) throws bv {
            m21373b(asVar, (bb) bpVar);
        }

        /* renamed from: b */
        public /* synthetic */ void mo7209b(as asVar, bp bpVar) throws bv {
            m21371a(asVar, (bb) bpVar);
        }

        /* renamed from: a */
        public void m21371a(as asVar, bb bbVar) throws bv {
            asVar.mo7186f();
            while (true) {
                ap h = asVar.mo7188h();
                if (h.f18595b == (byte) 0) {
                    asVar.mo7187g();
                    if (!bbVar.m21411h()) {
                        throw new cp("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    } else if (bbVar.m21414k()) {
                        bbVar.m21415l();
                        return;
                    } else {
                        throw new cp("Required field 'version' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (h.f18596c) {
                    case (short) 1:
                        if (h.f18595b != Ascii.VT) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bbVar.f18778a = asVar.mo7202v();
                        bbVar.m21399a(true);
                        break;
                    case (short) 2:
                        if (h.f18595b != (byte) 10) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bbVar.f18779b = asVar.mo7200t();
                        bbVar.m21403b(true);
                        break;
                    case (short) 3:
                        if (h.f18595b != (byte) 8) {
                            at.m21234a(asVar, h.f18595b);
                            break;
                        }
                        bbVar.f18780c = asVar.mo7199s();
                        bbVar.m21406c(true);
                        break;
                    default:
                        at.m21234a(asVar, h.f18595b);
                        break;
                }
                asVar.mo7189i();
            }
        }

        /* renamed from: b */
        public void m21373b(as asVar, bb bbVar) throws bv {
            bbVar.m21415l();
            asVar.mo7181a(bb.f18771e);
            if (bbVar.f18778a != null) {
                asVar.mo7178a(bb.f18772f);
                asVar.mo7176a(bbVar.f18778a);
                asVar.mo7182b();
            }
            asVar.mo7178a(bb.f18773g);
            asVar.mo7175a(bbVar.f18779b);
            asVar.mo7182b();
            asVar.mo7178a(bb.f18774h);
            asVar.mo7174a(bbVar.f18780c);
            asVar.mo7182b();
            asVar.mo7183c();
            asVar.mo7173a();
        }
    }

    /* compiled from: IdSnapshot */
    /* renamed from: u.aly.bb$b */
    private static class C5880b implements az {
        private C5880b() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21375a();
        }

        /* renamed from: a */
        public C5879a m21375a() {
            return new C5879a();
        }
    }

    /* compiled from: IdSnapshot */
    /* renamed from: u.aly.bb$c */
    private static class C5881c extends bi<bb> {
        private C5881c() {
        }

        /* renamed from: a */
        public void m21377a(as asVar, bb bbVar) throws bv {
            ax axVar = (ax) asVar;
            axVar.mo7176a(bbVar.f18778a);
            axVar.mo7175a(bbVar.f18779b);
            axVar.mo7174a(bbVar.f18780c);
        }

        /* renamed from: b */
        public void m21379b(as asVar, bb bbVar) throws bv {
            ax axVar = (ax) asVar;
            bbVar.f18778a = axVar.mo7202v();
            bbVar.m21399a(true);
            bbVar.f18779b = axVar.mo7200t();
            bbVar.m21403b(true);
            bbVar.f18780c = axVar.mo7199s();
            bbVar.m21406c(true);
        }
    }

    /* compiled from: IdSnapshot */
    /* renamed from: u.aly.bb$d */
    private static class C5882d implements az {
        private C5882d() {
        }

        /* renamed from: b */
        public /* synthetic */ ay mo7210b() {
            return m21381a();
        }

        /* renamed from: a */
        public C5881c m21381a() {
            return new C5881c();
        }
    }

    /* compiled from: IdSnapshot */
    /* renamed from: u.aly.bb$e */
    public enum C5883e implements am {
        IDENTITY((short) 1, HTTP.IDENTITY_CODING),
        TS((short) 2, "ts"),
        VERSION((short) 3, MapboxEvent.ATTRIBUTE_VERSION);
        
        /* renamed from: d */
        private static final Map<String, C5883e> f18766d = null;
        /* renamed from: e */
        private final short f18768e;
        /* renamed from: f */
        private final String f18769f;

        static {
            f18766d = new HashMap();
            Iterator it = EnumSet.allOf(C5883e.class).iterator();
            while (it.hasNext()) {
                C5883e c5883e = (C5883e) it.next();
                f18766d.put(c5883e.m21387b(), c5883e);
            }
        }

        /* renamed from: a */
        public static C5883e m21383a(int i) {
            switch (i) {
                case 1:
                    return IDENTITY;
                case 2:
                    return TS;
                case 3:
                    return VERSION;
                default:
                    return null;
            }
        }

        /* renamed from: b */
        public static C5883e m21385b(int i) {
            C5883e a = C5883e.m21383a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* renamed from: a */
        public static C5883e m21384a(String str) {
            return (C5883e) f18766d.get(str);
        }

        private C5883e(short s, String str) {
            this.f18768e = s;
            this.f18769f = str;
        }

        /* renamed from: a */
        public short m21386a() {
            return this.f18768e;
        }

        /* renamed from: b */
        public String m21387b() {
            return this.f18769f;
        }
    }

    /* renamed from: b */
    public /* synthetic */ am mo7212b(int i) {
        return m21405c(i);
    }

    /* renamed from: p */
    public /* synthetic */ bp mo7215p() {
        return m21394a();
    }

    static {
        f18775i.put(bh.class, new C5880b());
        f18775i.put(bi.class, new C5882d());
        Map enumMap = new EnumMap(C5883e.class);
        enumMap.put(C5883e.IDENTITY, new cb(HTTP.IDENTITY_CODING, (byte) 1, new cc(Ascii.VT)));
        enumMap.put(C5883e.TS, new cb("ts", (byte) 1, new cc((byte) 10)));
        enumMap.put(C5883e.VERSION, new cb(MapboxEvent.ATTRIBUTE_VERSION, (byte) 1, new cc((byte) 8)));
        f18770d = Collections.unmodifiableMap(enumMap);
        cb.m21817a(bb.class, f18770d);
    }

    public bb() {
        this.f18781l = (byte) 0;
    }

    public bb(String str, long j, int i) {
        this();
        this.f18778a = str;
        this.f18779b = j;
        m21403b(true);
        this.f18780c = i;
        m21406c(true);
    }

    public bb(bb bbVar) {
        this.f18781l = (byte) 0;
        this.f18781l = bbVar.f18781l;
        if (bbVar.m21408e()) {
            this.f18778a = bbVar.f18778a;
        }
        this.f18779b = bbVar.f18779b;
        this.f18780c = bbVar.f18780c;
    }

    /* renamed from: a */
    public bb m21394a() {
        return new bb(this);
    }

    /* renamed from: b */
    public void mo7213b() {
        this.f18778a = null;
        m21403b(false);
        this.f18779b = 0;
        m21406c(false);
        this.f18780c = 0;
    }

    /* renamed from: c */
    public String m21404c() {
        return this.f18778a;
    }

    /* renamed from: a */
    public bb m21397a(String str) {
        this.f18778a = str;
        return this;
    }

    /* renamed from: d */
    public void m21407d() {
        this.f18778a = null;
    }

    /* renamed from: e */
    public boolean m21408e() {
        return this.f18778a != null;
    }

    /* renamed from: a */
    public void m21399a(boolean z) {
        if (!z) {
            this.f18778a = null;
        }
    }

    /* renamed from: f */
    public long m21409f() {
        return this.f18779b;
    }

    /* renamed from: a */
    public bb m21396a(long j) {
        this.f18779b = j;
        m21403b(true);
        return this;
    }

    /* renamed from: g */
    public void m21410g() {
        this.f18781l = ai.m21171b(this.f18781l, 0);
    }

    /* renamed from: h */
    public boolean m21411h() {
        return ai.m21169a(this.f18781l, 0);
    }

    /* renamed from: b */
    public void m21403b(boolean z) {
        this.f18781l = ai.m21167a(this.f18781l, 0, z);
    }

    /* renamed from: i */
    public int m21412i() {
        return this.f18780c;
    }

    /* renamed from: a */
    public bb m21395a(int i) {
        this.f18780c = i;
        m21406c(true);
        return this;
    }

    /* renamed from: j */
    public void m21413j() {
        this.f18781l = ai.m21171b(this.f18781l, 1);
    }

    /* renamed from: k */
    public boolean m21414k() {
        return ai.m21169a(this.f18781l, 1);
    }

    /* renamed from: c */
    public void m21406c(boolean z) {
        this.f18781l = ai.m21167a(this.f18781l, 1, z);
    }

    /* renamed from: c */
    public C5883e m21405c(int i) {
        return C5883e.m21383a(i);
    }

    /* renamed from: a */
    public void mo7211a(as asVar) throws bv {
        ((az) f18775i.get(asVar.mo7206y())).mo7210b().mo7209b(asVar, this);
    }

    /* renamed from: b */
    public void mo7214b(as asVar) throws bv {
        ((az) f18775i.get(asVar.mo7206y())).mo7210b().mo7208a(asVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdSnapshot(");
        stringBuilder.append("identity:");
        if (this.f18778a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.f18778a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.f18779b);
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.f18780c);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    /* renamed from: l */
    public void m21415l() throws bv {
        if (this.f18778a == null) {
            throw new cp("Required field 'identity' was not present! Struct: " + toString());
        }
    }

    /* renamed from: a */
    private void m21389a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            mo7214b(new ci(new bk((OutputStream) objectOutputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    private void m21388a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f18781l = (byte) 0;
            mo7211a(new ci(new bk((InputStream) objectInputStream)));
        } catch (bv e) {
            throw new IOException(e.getMessage());
        }
    }
}
