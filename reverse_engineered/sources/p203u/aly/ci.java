package p203u.aly;

import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TCompactProtocol */
/* renamed from: u.aly.ci */
public class ci extends as {
    /* renamed from: d */
    private static final aw f18723d = new aw("");
    /* renamed from: f */
    private static final ap f18724f = new ap("", (byte) 0, (short) 0);
    /* renamed from: g */
    private static final byte[] f18725g = new byte[16];
    /* renamed from: a */
    byte[] f18726a;
    /* renamed from: b */
    byte[] f18727b;
    /* renamed from: c */
    byte[] f18728c;
    /* renamed from: h */
    private aj f18729h;
    /* renamed from: i */
    private short f18730i;
    /* renamed from: j */
    private ap f18731j;
    /* renamed from: k */
    private Boolean f18732k;
    /* renamed from: l */
    private final long f18733l;
    /* renamed from: m */
    private byte[] f18734m;

    /* compiled from: TCompactProtocol */
    /* renamed from: u.aly.ci$a */
    public static class C5934a implements cq {
        /* renamed from: a */
        private final long f19009a;

        public C5934a() {
            this.f19009a = -1;
        }

        public C5934a(int i) {
            this.f19009a = (long) i;
        }

        /* renamed from: a */
        public as mo7222a(bm bmVar) {
            return new ci(bmVar, this.f19009a);
        }
    }

    static {
        f18725g[0] = (byte) 0;
        f18725g[2] = (byte) 1;
        f18725g[3] = (byte) 3;
        f18725g[6] = (byte) 4;
        f18725g[8] = (byte) 5;
        f18725g[10] = (byte) 6;
        f18725g[4] = (byte) 7;
        f18725g[11] = (byte) 8;
        f18725g[15] = (byte) 9;
        f18725g[14] = (byte) 10;
        f18725g[13] = Ascii.VT;
        f18725g[12] = Ascii.FF;
    }

    public ci(bm bmVar, long j) {
        super(bmVar);
        this.f18729h = new aj(15);
        this.f18730i = (short) 0;
        this.f18731j = null;
        this.f18732k = null;
        this.f18726a = new byte[5];
        this.f18727b = new byte[10];
        this.f18734m = new byte[1];
        this.f18728c = new byte[1];
        this.f18733l = j;
    }

    public ci(bm bmVar) {
        this(bmVar, -1);
    }

    /* renamed from: x */
    public void mo7204x() {
        this.f18729h.m21176b();
        this.f18730i = (short) 0;
    }

    /* renamed from: a */
    public void mo7181a(aw awVar) throws bv {
        this.f18729h.m21175a(this.f18730i);
        this.f18730i = (short) 0;
    }

    /* renamed from: a */
    public void mo7173a() throws bv {
        this.f18730i = this.f18729h.m21174a();
    }

    /* renamed from: a */
    public void mo7178a(ap apVar) throws bv {
        if (apVar.f18595b == (byte) 2) {
            this.f18731j = apVar;
        } else {
            m21244a(apVar, (byte) -1);
        }
    }

    /* renamed from: a */
    private void m21244a(ap apVar, byte b) throws bv {
        if (b == (byte) -1) {
            b = m21255e(apVar.f18595b);
        }
        if (apVar.f18596c <= this.f18730i || apVar.f18596c - this.f18730i > 15) {
            m21246b(b);
            m21271a(apVar.f18596c);
        } else {
            m21254d(((apVar.f18596c - this.f18730i) << 4) | b);
        }
        this.f18730i = apVar.f18596c;
    }

    /* renamed from: c */
    public void mo7183c() throws bv {
        m21246b((byte) 0);
    }

    /* renamed from: a */
    public void mo7180a(ar arVar) throws bv {
        if (arVar.f18601c == 0) {
            m21254d(0);
            return;
        }
        mo7205b(arVar.f18601c);
        m21254d((m21255e(arVar.f18599a) << 4) | m21255e(arVar.f18600b));
    }

    /* renamed from: a */
    public void mo7179a(aq aqVar) throws bv {
        m21262a(aqVar.f18597a, aqVar.f18598b);
    }

    /* renamed from: a */
    public void m21261a(byte b) throws bv {
        m21246b(b);
    }

    /* renamed from: a */
    public void m21271a(short s) throws bv {
        mo7205b(m21249c((int) s));
    }

    /* renamed from: a */
    public void mo7174a(int i) throws bv {
        mo7205b(m21249c(i));
    }

    /* renamed from: a */
    public void mo7175a(long j) throws bv {
        m21248b(m21250c(j));
    }

    /* renamed from: a */
    public void mo7176a(String str) throws bv {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            m21245a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new bv("UTF-8 not supported!");
        }
    }

    /* renamed from: a */
    public void mo7177a(ByteBuffer byteBuffer) throws bv {
        m21245a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    /* renamed from: a */
    private void m21245a(byte[] bArr, int i, int i2) throws bv {
        mo7205b(i2);
        this.e.mo7217b(bArr, i, i2);
    }

    /* renamed from: d */
    public void mo7184d() throws bv {
    }

    /* renamed from: e */
    public void mo7185e() throws bv {
    }

    /* renamed from: b */
    public void mo7182b() throws bv {
    }

    /* renamed from: a */
    protected void m21262a(byte b, int i) throws bv {
        if (i <= 14) {
            m21254d((i << 4) | m21255e(b));
            return;
        }
        m21254d(m21255e(b) | 240);
        mo7205b(i);
    }

    /* renamed from: b */
    private void mo7205b(int i) throws bv {
        int i2 = 0;
        while ((i & -128) != 0) {
            int i3 = i2 + 1;
            this.f18726a[i2] = (byte) ((i & 127) | 128);
            i >>>= 7;
            i2 = i3;
        }
        int i4 = i2 + 1;
        this.f18726a[i2] = (byte) i;
        this.e.mo7217b(this.f18726a, 0, i4);
    }

    /* renamed from: b */
    private void m21248b(long j) throws bv {
        int i = 0;
        while ((-128 & j) != 0) {
            int i2 = i + 1;
            this.f18727b[i] = (byte) ((int) ((127 & j) | 128));
            j >>>= 7;
            i = i2;
        }
        int i3 = i + 1;
        this.f18727b[i] = (byte) ((int) j);
        this.e.mo7217b(this.f18727b, 0, i3);
    }

    /* renamed from: c */
    private long m21250c(long j) {
        return (j << 1) ^ (j >> 63);
    }

    /* renamed from: c */
    private int m21249c(int i) {
        return (i << 1) ^ (i >> 31);
    }

    /* renamed from: b */
    private void m21246b(byte b) throws bv {
        this.f18734m[0] = b;
        this.e.m21703b(this.f18734m);
    }

    /* renamed from: d */
    private void m21254d(int i) throws bv {
        m21246b((byte) i);
    }

    /* renamed from: f */
    public aw mo7186f() throws bv {
        this.f18729h.m21175a(this.f18730i);
        this.f18730i = (short) 0;
        return f18723d;
    }

    /* renamed from: g */
    public void mo7187g() throws bv {
        this.f18730i = this.f18729h.m21174a();
    }

    /* renamed from: h */
    public ap mo7188h() throws bv {
        byte q = mo7197q();
        if (q == (byte) 0) {
            return f18724f;
        }
        short s = (short) ((q & 240) >> 4);
        if (s == (short) 0) {
            s = mo7198r();
        } else {
            s = (short) (s + this.f18730i);
        }
        ap apVar = new ap("", m21252d((byte) (q & 15)), s);
        if (m21251c(q)) {
            this.f18732k = ((byte) (q & 15)) == (byte) 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.f18730i = apVar.f18596c;
        return apVar;
    }

    /* renamed from: j */
    public ar mo7190j() throws bv {
        int z = m21259z();
        int q = z == 0 ? 0 : mo7197q();
        return new ar(m21252d((byte) (q >> 4)), m21252d((byte) (q & 15)), z);
    }

    /* renamed from: l */
    public aq mo7192l() throws bv {
        byte q = mo7197q();
        int i = (q >> 4) & 15;
        if (i == 15) {
            i = m21259z();
        }
        return new aq(m21252d(q), i);
    }

    /* renamed from: n */
    public au mo7194n() throws bv {
        return new au(mo7192l());
    }

    /* renamed from: p */
    public boolean mo7196p() throws bv {
        if (this.f18732k != null) {
            boolean booleanValue = this.f18732k.booleanValue();
            this.f18732k = null;
            return booleanValue;
        } else if (mo7197q() != (byte) 1) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: q */
    public byte mo7197q() throws bv {
        if (this.e.mo7221d() > 0) {
            byte b = this.e.mo7219b()[this.e.mo7220c()];
            this.e.mo7218a(1);
            return b;
        }
        this.e.m21708d(this.f18728c, 0, 1);
        return this.f18728c[0];
    }

    /* renamed from: r */
    public short mo7198r() throws bv {
        return (short) m21258g(m21259z());
    }

    /* renamed from: s */
    public int mo7199s() throws bv {
        return m21258g(m21259z());
    }

    /* renamed from: t */
    public long mo7200t() throws bv {
        return m21253d(m21242A());
    }

    /* renamed from: u */
    public double mo7201u() throws bv {
        byte[] bArr = new byte[8];
        this.e.m21708d(bArr, 0, 8);
        return Double.longBitsToDouble(m21243a(bArr));
    }

    /* renamed from: v */
    public String mo7202v() throws bv {
        int z = m21259z();
        m21257f(z);
        if (z == 0) {
            return "";
        }
        try {
            if (this.e.mo7221d() < z) {
                return new String(m21256e(z), "UTF-8");
            }
            String str = new String(this.e.mo7219b(), this.e.mo7220c(), z, "UTF-8");
            this.e.mo7218a(z);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new bv("UTF-8 not supported!");
        }
    }

    /* renamed from: w */
    public ByteBuffer mo7203w() throws bv {
        int z = m21259z();
        m21257f(z);
        if (z == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[z];
        this.e.m21708d(bArr, 0, z);
        return ByteBuffer.wrap(bArr);
    }

    /* renamed from: e */
    private byte[] m21256e(int i) throws bv {
        if (i == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        this.e.m21708d(bArr, 0, i);
        return bArr;
    }

    /* renamed from: f */
    private void m21257f(int i) throws cp {
        if (i < 0) {
            throw new cp("Negative length: " + i);
        } else if (this.f18733l != -1 && ((long) i) > this.f18733l) {
            throw new cp("Length exceeded max allowed: " + i);
        }
    }

    /* renamed from: i */
    public void mo7189i() throws bv {
    }

    /* renamed from: k */
    public void mo7191k() throws bv {
    }

    /* renamed from: m */
    public void mo7193m() throws bv {
    }

    /* renamed from: o */
    public void mo7195o() throws bv {
    }

    /* renamed from: z */
    private int m21259z() throws bv {
        int i = 0;
        int i2;
        if (this.e.mo7221d() >= 5) {
            byte[] b = this.e.mo7219b();
            int c = this.e.mo7220c();
            i2 = 0;
            int i3 = 0;
            while (true) {
                byte b2 = b[c + i];
                i3 |= (b2 & 127) << i2;
                if ((b2 & 128) != 128) {
                    this.e.mo7218a(i + 1);
                    return i3;
                }
                i2 += 7;
                i++;
            }
        } else {
            i2 = 0;
            while (true) {
                byte q = mo7197q();
                i2 |= (q & 127) << i;
                if ((q & 128) != 128) {
                    return i2;
                }
                i += 7;
            }
        }
    }

    /* renamed from: A */
    private long m21242A() throws bv {
        long j = null;
        long j2 = 0;
        if (this.e.mo7221d() >= 10) {
            int i;
            byte[] b = this.e.mo7219b();
            int c = this.e.mo7220c();
            long j3 = 0;
            while (true) {
                byte b2 = b[c + i];
                j2 |= ((long) (b2 & 127)) << j3;
                if ((b2 & 128) != 128) {
                    break;
                }
                j3 += 7;
                i++;
            }
            this.e.mo7218a(i + 1);
        } else {
            while (true) {
                byte q = mo7197q();
                j2 |= ((long) (q & 127)) << j;
                if ((q & 128) != 128) {
                    break;
                }
                j += 7;
            }
        }
        return j2;
    }

    /* renamed from: g */
    private int m21258g(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    /* renamed from: d */
    private long m21253d(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    /* renamed from: a */
    private long m21243a(byte[] bArr) {
        return ((((((((((long) bArr[7]) & 255) << 56) | ((((long) bArr[6]) & 255) << 48)) | ((((long) bArr[5]) & 255) << 40)) | ((((long) bArr[4]) & 255) << 32)) | ((((long) bArr[3]) & 255) << 24)) | ((((long) bArr[2]) & 255) << 16)) | ((((long) bArr[1]) & 255) << 8)) | (((long) bArr[0]) & 255);
    }

    /* renamed from: c */
    private boolean m21251c(byte b) {
        int i = b & 15;
        if (i == 1 || i == 2) {
            return true;
        }
        return false;
    }

    /* renamed from: d */
    private byte m21252d(byte b) throws cp {
        switch ((byte) (b & 15)) {
            case (byte) 0:
                return (byte) 0;
            case (byte) 1:
            case (byte) 2:
                return (byte) 2;
            case (byte) 3:
                return (byte) 3;
            case (byte) 4:
                return (byte) 6;
            case (byte) 5:
                return (byte) 8;
            case (byte) 6:
                return (byte) 10;
            case (byte) 7:
                return (byte) 4;
            case (byte) 8:
                return Ascii.VT;
            case (byte) 9:
                return Ascii.SI;
            case (byte) 10:
                return Ascii.SO;
            case (byte) 11:
                return (byte) 13;
            case (byte) 12:
                return Ascii.FF;
            default:
                throw new cp("don't know what type: " + ((byte) (b & 15)));
        }
    }

    /* renamed from: e */
    private byte m21255e(byte b) {
        return f18725g[b];
    }
}
