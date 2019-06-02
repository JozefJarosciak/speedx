package com.google.android.gms.internal;

import java.io.IOException;

public final class zzaou {
    private int bhR;
    private int bhS;
    private int bhT;
    private int bhU;
    private int bhV;
    private int bhW = Integer.MAX_VALUE;
    private int bhX;
    private int bhY = 64;
    private int bhZ = 67108864;
    private final byte[] buffer;

    private zzaou(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.bhR = i;
        this.bhS = i + i2;
        this.bhU = i;
    }

    /* renamed from: W */
    private void m16038W() {
        this.bhS += this.bhT;
        int i = this.bhS;
        if (i > this.bhW) {
            this.bhT = i - this.bhW;
            this.bhS -= this.bhT;
            return;
        }
        this.bhT = 0;
    }

    public static int zzaeh(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static zzaou zzaz(byte[] bArr) {
        return zzb(bArr, 0, bArr.length);
    }

    public static zzaou zzb(byte[] bArr, int i, int i2) {
        return new zzaou(bArr, i, i2);
    }

    public static long zzcq(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    /* renamed from: J */
    public int m16039J() throws IOException {
        if (m16053Y()) {
            this.bhV = 0;
            return 0;
        }
        this.bhV = m16048S();
        if (this.bhV != 0) {
            return this.bhV;
        }
        throw zzapb.aj();
    }

    /* renamed from: K */
    public void m16040K() throws IOException {
        int J;
        do {
            J = m16039J();
            if (J == 0) {
                return;
            }
        } while (zzaeg(J));
    }

    /* renamed from: L */
    public long m16041L() throws IOException {
        return m16049T();
    }

    /* renamed from: M */
    public long m16042M() throws IOException {
        return m16049T();
    }

    /* renamed from: N */
    public int m16043N() throws IOException {
        return m16048S();
    }

    /* renamed from: O */
    public long m16044O() throws IOException {
        return m16051V();
    }

    /* renamed from: P */
    public boolean m16045P() throws IOException {
        return m16048S() != 0;
    }

    /* renamed from: Q */
    public int m16046Q() throws IOException {
        return zzaeh(m16048S());
    }

    /* renamed from: R */
    public long m16047R() throws IOException {
        return zzcq(m16049T());
    }

    /* renamed from: S */
    public int m16048S() throws IOException {
        byte Z = m16054Z();
        if (Z >= (byte) 0) {
            return Z;
        }
        int i = Z & 127;
        byte Z2 = m16054Z();
        if (Z2 >= (byte) 0) {
            return i | (Z2 << 7);
        }
        i |= (Z2 & 127) << 7;
        Z2 = m16054Z();
        if (Z2 >= (byte) 0) {
            return i | (Z2 << 14);
        }
        i |= (Z2 & 127) << 14;
        Z2 = m16054Z();
        if (Z2 >= (byte) 0) {
            return i | (Z2 << 21);
        }
        i |= (Z2 & 127) << 21;
        Z2 = m16054Z();
        i |= Z2 << 28;
        if (Z2 >= (byte) 0) {
            return i;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            if (m16054Z() >= (byte) 0) {
                return i;
            }
        }
        throw zzapb.ai();
    }

    /* renamed from: T */
    public long m16049T() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte Z = m16054Z();
            j |= ((long) (Z & 127)) << i;
            if ((Z & 128) == 0) {
                return j;
            }
        }
        throw zzapb.ai();
    }

    /* renamed from: U */
    public int m16050U() throws IOException {
        return (((m16054Z() & 255) | ((m16054Z() & 255) << 8)) | ((m16054Z() & 255) << 16)) | ((m16054Z() & 255) << 24);
    }

    /* renamed from: V */
    public long m16051V() throws IOException {
        byte Z = m16054Z();
        byte Z2 = m16054Z();
        return ((((((((((long) Z2) & 255) << 8) | (((long) Z) & 255)) | ((((long) m16054Z()) & 255) << 16)) | ((((long) m16054Z()) & 255) << 24)) | ((((long) m16054Z()) & 255) << 32)) | ((((long) m16054Z()) & 255) << 40)) | ((((long) m16054Z()) & 255) << 48)) | ((((long) m16054Z()) & 255) << 56);
    }

    /* renamed from: X */
    public int m16052X() {
        if (this.bhW == Integer.MAX_VALUE) {
            return -1;
        }
        return this.bhW - this.bhU;
    }

    /* renamed from: Y */
    public boolean m16053Y() {
        return this.bhU == this.bhS;
    }

    /* renamed from: Z */
    public byte m16054Z() throws IOException {
        if (this.bhU == this.bhS) {
            throw zzapb.ag();
        }
        byte[] bArr = this.buffer;
        int i = this.bhU;
        this.bhU = i + 1;
        return bArr[i];
    }

    public int getPosition() {
        return this.bhU - this.bhR;
    }

    public byte[] readBytes() throws IOException {
        int S = m16048S();
        if (S < 0) {
            throw zzapb.ah();
        } else if (S == 0) {
            return zzapf.bit;
        } else {
            if (S > this.bhS - this.bhU) {
                throw zzapb.ag();
            }
            Object obj = new byte[S];
            System.arraycopy(this.buffer, this.bhU, obj, 0, S);
            this.bhU = S + this.bhU;
            return obj;
        }
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(m16051V());
    }

    public float readFloat() throws IOException {
        return Float.intBitsToFloat(m16050U());
    }

    public String readString() throws IOException {
        int S = m16048S();
        if (S < 0) {
            throw zzapb.ah();
        } else if (S > this.bhS - this.bhU) {
            throw zzapb.ag();
        } else {
            String str = new String(this.buffer, this.bhU, S, zzapa.UTF_8);
            this.bhU = S + this.bhU;
            return str;
        }
    }

    public void zza(zzapc zzapc) throws IOException {
        int S = m16048S();
        if (this.bhX >= this.bhY) {
            throw zzapb.am();
        }
        S = zzaei(S);
        this.bhX++;
        zzapc.zzb(this);
        zzaef(0);
        this.bhX--;
        zzaej(S);
    }

    public void zza(zzapc zzapc, int i) throws IOException {
        if (this.bhX >= this.bhY) {
            throw zzapb.am();
        }
        this.bhX++;
        zzapc.zzb(this);
        zzaef(zzapf.zzaj(i, 4));
        this.bhX--;
    }

    public byte[] zzad(int i, int i2) {
        if (i2 == 0) {
            return zzapf.bit;
        }
        Object obj = new byte[i2];
        System.arraycopy(this.buffer, this.bhR + i, obj, 0, i2);
        return obj;
    }

    public void zzaef(int i) throws zzapb {
        if (this.bhV != i) {
            throw zzapb.ak();
        }
    }

    public boolean zzaeg(int i) throws IOException {
        switch (zzapf.zzaez(i)) {
            case 0:
                m16043N();
                return true;
            case 1:
                m16051V();
                return true;
            case 2:
                zzael(m16048S());
                return true;
            case 3:
                m16040K();
                zzaef(zzapf.zzaj(zzapf.zzafa(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                m16050U();
                return true;
            default:
                throw zzapb.al();
        }
    }

    public int zzaei(int i) throws zzapb {
        if (i < 0) {
            throw zzapb.ah();
        }
        int i2 = this.bhU + i;
        int i3 = this.bhW;
        if (i2 > i3) {
            throw zzapb.ag();
        }
        this.bhW = i2;
        m16038W();
        return i3;
    }

    public void zzaej(int i) {
        this.bhW = i;
        m16038W();
    }

    public void zzaek(int i) {
        if (i > this.bhU - this.bhR) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.bhU - this.bhR));
        } else if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        } else {
            this.bhU = this.bhR + i;
        }
    }

    public void zzael(int i) throws IOException {
        if (i < 0) {
            throw zzapb.ah();
        } else if (this.bhU + i > this.bhW) {
            zzael(this.bhW - this.bhU);
            throw zzapb.ag();
        } else if (i <= this.bhS - this.bhU) {
            this.bhU += i;
        } else {
            throw zzapb.ag();
        }
    }
}
