package p203u.aly;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TBinaryProtocol */
/* renamed from: u.aly.ch */
public class ch extends as {
    /* renamed from: f */
    private static final aw f18996f = new aw();
    /* renamed from: a */
    protected boolean f18997a = false;
    /* renamed from: b */
    protected boolean f18998b = true;
    /* renamed from: c */
    protected int f18999c;
    /* renamed from: d */
    protected boolean f19000d = false;
    /* renamed from: g */
    private byte[] f19001g = new byte[1];
    /* renamed from: h */
    private byte[] f19002h = new byte[2];
    /* renamed from: i */
    private byte[] f19003i = new byte[4];
    /* renamed from: j */
    private byte[] f19004j = new byte[8];
    /* renamed from: k */
    private byte[] f19005k = new byte[1];
    /* renamed from: l */
    private byte[] f19006l = new byte[2];
    /* renamed from: m */
    private byte[] f19007m = new byte[4];
    /* renamed from: n */
    private byte[] f19008n = new byte[8];

    /* compiled from: TBinaryProtocol */
    /* renamed from: u.aly.ch$a */
    public static class C5933a implements cq {
        /* renamed from: a */
        protected boolean f18993a;
        /* renamed from: b */
        protected boolean f18994b;
        /* renamed from: c */
        protected int f18995c;

        public C5933a() {
            this(false, true);
        }

        public C5933a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public C5933a(boolean z, boolean z2, int i) {
            this.f18993a = false;
            this.f18994b = true;
            this.f18993a = z;
            this.f18994b = z2;
            this.f18995c = i;
        }

        /* renamed from: a */
        public as mo7222a(bm bmVar) {
            as chVar = new ch(bmVar, this.f18993a, this.f18994b);
            if (this.f18995c != 0) {
                chVar.m21867c(this.f18995c);
            }
            return chVar;
        }
    }

    public ch(bm bmVar, boolean z, boolean z2) {
        super(bmVar);
        this.f18997a = z;
        this.f18998b = z2;
    }

    /* renamed from: a */
    public void mo7181a(aw awVar) {
    }

    /* renamed from: a */
    public void mo7173a() {
    }

    /* renamed from: a */
    public void mo7178a(ap apVar) throws bv {
        m21854a(apVar.f18595b);
        m21863a(apVar.f18596c);
    }

    /* renamed from: b */
    public void mo7182b() {
    }

    /* renamed from: c */
    public void mo7183c() throws bv {
        m21854a((byte) 0);
    }

    /* renamed from: a */
    public void mo7180a(ar arVar) throws bv {
        m21854a(arVar.f18599a);
        m21854a(arVar.f18600b);
        mo7174a(arVar.f18601c);
    }

    /* renamed from: d */
    public void mo7184d() {
    }

    /* renamed from: a */
    public void mo7179a(aq aqVar) throws bv {
        m21854a(aqVar.f18597a);
        mo7174a(aqVar.f18598b);
    }

    /* renamed from: e */
    public void mo7185e() {
    }

    /* renamed from: a */
    public void m21854a(byte b) throws bv {
        this.f19001g[0] = b;
        this.e.mo7217b(this.f19001g, 0, 1);
    }

    /* renamed from: a */
    public void m21863a(short s) throws bv {
        this.f19002h[0] = (byte) ((s >> 8) & 255);
        this.f19002h[1] = (byte) (s & 255);
        this.e.mo7217b(this.f19002h, 0, 2);
    }

    /* renamed from: a */
    public void mo7174a(int i) throws bv {
        this.f19003i[0] = (byte) ((i >> 24) & 255);
        this.f19003i[1] = (byte) ((i >> 16) & 255);
        this.f19003i[2] = (byte) ((i >> 8) & 255);
        this.f19003i[3] = (byte) (i & 255);
        this.e.mo7217b(this.f19003i, 0, 4);
    }

    /* renamed from: a */
    public void mo7175a(long j) throws bv {
        this.f19004j[0] = (byte) ((int) ((j >> 56) & 255));
        this.f19004j[1] = (byte) ((int) ((j >> 48) & 255));
        this.f19004j[2] = (byte) ((int) ((j >> 40) & 255));
        this.f19004j[3] = (byte) ((int) ((j >> 32) & 255));
        this.f19004j[4] = (byte) ((int) ((j >> 24) & 255));
        this.f19004j[5] = (byte) ((int) ((j >> 16) & 255));
        this.f19004j[6] = (byte) ((int) ((j >> 8) & 255));
        this.f19004j[7] = (byte) ((int) (255 & j));
        this.e.mo7217b(this.f19004j, 0, 8);
    }

    /* renamed from: a */
    public void mo7176a(String str) throws bv {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            mo7174a(bytes.length);
            this.e.mo7217b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new bv("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    /* renamed from: a */
    public void mo7177a(ByteBuffer byteBuffer) throws bv {
        int limit = byteBuffer.limit() - byteBuffer.position();
        mo7174a(limit);
        this.e.mo7217b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    /* renamed from: f */
    public aw mo7186f() {
        return f18996f;
    }

    /* renamed from: g */
    public void mo7187g() {
    }

    /* renamed from: h */
    public ap mo7188h() throws bv {
        byte q = mo7197q();
        return new ap("", q, q == (byte) 0 ? (short) 0 : mo7198r());
    }

    /* renamed from: i */
    public void mo7189i() {
    }

    /* renamed from: j */
    public ar mo7190j() throws bv {
        return new ar(mo7197q(), mo7197q(), mo7199s());
    }

    /* renamed from: k */
    public void mo7191k() {
    }

    /* renamed from: l */
    public aq mo7192l() throws bv {
        return new aq(mo7197q(), mo7199s());
    }

    /* renamed from: m */
    public void mo7193m() {
    }

    /* renamed from: n */
    public au mo7194n() throws bv {
        return new au(mo7197q(), mo7199s());
    }

    /* renamed from: o */
    public void mo7195o() {
    }

    /* renamed from: p */
    public boolean mo7196p() throws bv {
        return mo7197q() == (byte) 1;
    }

    /* renamed from: q */
    public byte mo7197q() throws bv {
        if (this.e.mo7221d() >= 1) {
            byte b = this.e.mo7219b()[this.e.mo7220c()];
            this.e.mo7218a(1);
            return b;
        }
        m21852a(this.f19005k, 0, 1);
        return this.f19005k[0];
    }

    /* renamed from: r */
    public short mo7198r() throws bv {
        int i = 0;
        byte[] bArr = this.f19006l;
        if (this.e.mo7221d() >= 2) {
            bArr = this.e.mo7219b();
            i = this.e.mo7220c();
            this.e.mo7218a(2);
        } else {
            m21852a(this.f19006l, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    /* renamed from: s */
    public int mo7199s() throws bv {
        int i = 0;
        byte[] bArr = this.f19007m;
        if (this.e.mo7221d() >= 4) {
            bArr = this.e.mo7219b();
            i = this.e.mo7220c();
            this.e.mo7218a(4);
        } else {
            m21852a(this.f19007m, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16)) | ((bArr[i + 2] & 255) << 8));
    }

    /* renamed from: t */
    public long mo7200t() throws bv {
        int i = 0;
        byte[] bArr = this.f19008n;
        if (this.e.mo7221d() >= 8) {
            bArr = this.e.mo7219b();
            i = this.e.mo7220c();
            this.e.mo7218a(8);
        } else {
            m21852a(this.f19008n, 0, 8);
        }
        return ((long) (bArr[i + 7] & 255)) | (((((((((long) (bArr[i] & 255)) << 56) | (((long) (bArr[i + 1] & 255)) << 48)) | (((long) (bArr[i + 2] & 255)) << 40)) | (((long) (bArr[i + 3] & 255)) << 32)) | (((long) (bArr[i + 4] & 255)) << 24)) | (((long) (bArr[i + 5] & 255)) << 16)) | (((long) (bArr[i + 6] & 255)) << 8));
    }

    /* renamed from: u */
    public double mo7201u() throws bv {
        return Double.longBitsToDouble(mo7200t());
    }

    /* renamed from: v */
    public String mo7202v() throws bv {
        int s = mo7199s();
        if (this.e.mo7221d() < s) {
            return m21864b(s);
        }
        try {
            String str = new String(this.e.mo7219b(), this.e.mo7220c(), s, "UTF-8");
            this.e.mo7218a(s);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new bv("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    /* renamed from: b */
    public String m21864b(int i) throws bv {
        try {
            m21869d(i);
            byte[] bArr = new byte[i];
            this.e.m21708d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new bv("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    /* renamed from: w */
    public ByteBuffer mo7203w() throws bv {
        int s = mo7199s();
        m21869d(s);
        if (this.e.mo7221d() >= s) {
            ByteBuffer wrap = ByteBuffer.wrap(this.e.mo7219b(), this.e.mo7220c(), s);
            this.e.mo7218a(s);
            return wrap;
        }
        byte[] bArr = new byte[s];
        this.e.m21708d(bArr, 0, s);
        return ByteBuffer.wrap(bArr);
    }

    /* renamed from: a */
    private int m21852a(byte[] bArr, int i, int i2) throws bv {
        m21869d(i2);
        return this.e.m21708d(bArr, i, i2);
    }

    /* renamed from: c */
    public void m21867c(int i) {
        this.f18999c = i;
        this.f19000d = true;
    }

    /* renamed from: d */
    protected void m21869d(int i) throws bv {
        if (i < 0) {
            throw new cp("Negative length: " + i);
        } else if (this.f19000d) {
            this.f18999c -= i;
            if (this.f18999c < 0) {
                throw new cp("Message length exceeded: " + i);
            }
        }
    }
}
