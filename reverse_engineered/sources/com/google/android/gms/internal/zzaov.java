package com.google.android.gms.internal;

import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzaov {
    private final ByteBuffer bia;

    public static class zza extends IOException {
        zza(int i, int i2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + i + " limit " + i2 + ").");
        }
    }

    private zzaov(ByteBuffer byteBuffer) {
        this.bia = byteBuffer;
        this.bia.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzaov(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private static int zza(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        int i3 = i;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt < 'ࠀ') {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if ('?' <= charAt && charAt <= '?') {
                    if (Character.codePointAt(charSequence, i3) < 65536) {
                        throw new IllegalArgumentException("Unpaired surrogate at index " + i3);
                    }
                    i3++;
                }
            }
            i3++;
        }
        return i2;
    }

    private static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int length = charSequence.length();
        int i3 = 0;
        int i4 = i + i2;
        while (i3 < length && i3 + i < i4) {
            char charAt = charSequence.charAt(i3);
            if (charAt >= '') {
                break;
            }
            bArr[i + i3] = (byte) charAt;
            i3++;
        }
        if (i3 == length) {
            return i + length;
        }
        int i5 = i + i3;
        while (i3 < length) {
            int i6;
            char charAt2 = charSequence.charAt(i3);
            if (charAt2 < '' && i5 < i4) {
                i6 = i5 + 1;
                bArr[i5] = (byte) charAt2;
            } else if (charAt2 < 'ࠀ' && i5 <= i4 - 2) {
                r6 = i5 + 1;
                bArr[i5] = (byte) ((charAt2 >>> 6) | 960);
                i6 = r6 + 1;
                bArr[r6] = (byte) ((charAt2 & 63) | 128);
            } else if ((charAt2 < '?' || '?' < charAt2) && i5 <= i4 - 3) {
                i6 = i5 + 1;
                bArr[i5] = (byte) ((charAt2 >>> 12) | 480);
                i5 = i6 + 1;
                bArr[i6] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i6 = i5 + 1;
                bArr[i5] = (byte) ((charAt2 & 63) | 128);
            } else if (i5 <= i4 - 4) {
                if (i3 + 1 != charSequence.length()) {
                    i3++;
                    charAt = charSequence.charAt(i3);
                    if (Character.isSurrogatePair(charAt2, charAt)) {
                        int toCodePoint = Character.toCodePoint(charAt2, charAt);
                        i6 = i5 + 1;
                        bArr[i5] = (byte) ((toCodePoint >>> 18) | 240);
                        i5 = i6 + 1;
                        bArr[i6] = (byte) (((toCodePoint >>> 12) & 63) | 128);
                        r6 = i5 + 1;
                        bArr[i5] = (byte) (((toCodePoint >>> 6) & 63) | 128);
                        i6 = r6 + 1;
                        bArr[r6] = (byte) ((toCodePoint & 63) | 128);
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (i3 - 1));
            } else {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i5);
            }
            i3++;
            i5 = i6;
        }
        return i5;
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
            } catch (Throwable e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            zzb(charSequence, byteBuffer);
        }
    }

    public static int zzaeo(int i) {
        return i >= 0 ? zzaet(i) : 10;
    }

    public static int zzaep(int i) {
        return zzaet(zzaev(i));
    }

    public static int zzaer(int i) {
        return zzaet(zzapf.zzaj(i, 0));
    }

    public static int zzaet(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzaev(int i) {
        return (i << 1) ^ (i >> 31);
    }

    public static int zzag(int i, int i2) {
        return zzaer(i) + zzaeo(i2);
    }

    public static int zzah(int i, int i2) {
        return zzaer(i) + zzaep(i2);
    }

    public static int zzb(int i, double d) {
        return zzaer(i) + zzp(d);
    }

    public static int zzb(int i, zzapc zzapc) {
        return (zzaer(i) * 2) + zzd(zzapc);
    }

    public static int zzb(int i, byte[] bArr) {
        return zzaer(i) + zzbc(bArr);
    }

    private static void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < '') {
                byteBuffer.put((byte) charAt);
            } else if (charAt < 'ࠀ') {
                byteBuffer.put((byte) ((charAt >>> 6) | 960));
                byteBuffer.put((byte) ((charAt & 63) | 128));
            } else if (charAt < '?' || '?' < charAt) {
                byteBuffer.put((byte) ((charAt >>> 12) | 480));
                byteBuffer.put((byte) (((charAt >>> 6) & 63) | 128));
                byteBuffer.put((byte) ((charAt & 63) | 128));
            } else {
                if (i + 1 != charSequence.length()) {
                    i++;
                    char charAt2 = charSequence.charAt(i);
                    if (Character.isSurrogatePair(charAt, charAt2)) {
                        int toCodePoint = Character.toCodePoint(charAt, charAt2);
                        byteBuffer.put((byte) ((toCodePoint >>> 18) | 240));
                        byteBuffer.put((byte) (((toCodePoint >>> 12) & 63) | 128));
                        byteBuffer.put((byte) (((toCodePoint >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((toCodePoint & 63) | 128));
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
            }
            i++;
        }
    }

    public static zzaov zzba(byte[] bArr) {
        return zzc(bArr, 0, bArr.length);
    }

    public static int zzbc(byte[] bArr) {
        return zzaet(bArr.length) + bArr.length;
    }

    public static int zzc(int i, zzapc zzapc) {
        return zzaer(i) + zze(zzapc);
    }

    public static zzaov zzc(byte[] bArr, int i, int i2) {
        return new zzaov(bArr, i, i2);
    }

    public static int zzcv(long j) {
        return zzda(j);
    }

    public static int zzcw(long j) {
        return zzda(j);
    }

    public static int zzcx(long j) {
        return 8;
    }

    public static int zzcy(long j) {
        return zzda(zzdc(j));
    }

    public static int zzd(int i, float f) {
        return zzaer(i) + zzl(f);
    }

    public static int zzd(zzapc zzapc) {
        return zzapc.ao();
    }

    private static int zzd(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < '') {
            i++;
        }
        int i2 = i;
        i = length;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt >= 'ࠀ') {
                i += zza(charSequence, i2);
                break;
            }
            i2++;
            i = ((127 - charAt) >>> 31) + i;
        }
        if (i >= length) {
            return i;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i) + 4294967296L));
    }

    public static int zzda(long j) {
        return (-128 & j) == 0 ? 1 : (-16384 & j) == 0 ? 2 : (-2097152 & j) == 0 ? 3 : (-268435456 & j) == 0 ? 4 : (-34359738368L & j) == 0 ? 5 : (-4398046511104L & j) == 0 ? 6 : (-562949953421312L & j) == 0 ? 7 : (-72057594037927936L & j) == 0 ? 8 : (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    public static long zzdc(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zzdf(boolean z) {
        return 1;
    }

    public static int zze(int i, long j) {
        return zzaer(i) + zzcw(j);
    }

    public static int zze(zzapc zzapc) {
        int ao = zzapc.ao();
        return ao + zzaet(ao);
    }

    public static int zzf(int i, long j) {
        return zzaer(i) + zzcx(j);
    }

    public static int zzg(int i, long j) {
        return zzaer(i) + zzcy(j);
    }

    public static int zzk(int i, boolean z) {
        return zzaer(i) + zzdf(z);
    }

    public static int zzl(float f) {
        return 4;
    }

    public static int zzp(double d) {
        return 8;
    }

    public static int zzs(int i, String str) {
        return zzaer(i) + zztg(str);
    }

    public static int zztg(String str) {
        int zzd = zzd((CharSequence) str);
        return zzd + zzaet(zzd);
    }

    public int aa() {
        return this.bia.remaining();
    }

    public void ab() {
        if (aa() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void zza(int i, double d) throws IOException {
        zzai(i, 1);
        zzo(d);
    }

    public void zza(int i, long j) throws IOException {
        zzai(i, 0);
        zzcr(j);
    }

    public void zza(int i, zzapc zzapc) throws IOException {
        zzai(i, 2);
        zzc(zzapc);
    }

    public void zza(int i, byte[] bArr) throws IOException {
        zzai(i, 2);
        zzbb(bArr);
    }

    public void zzae(int i, int i2) throws IOException {
        zzai(i, 0);
        zzaem(i2);
    }

    public void zzaem(int i) throws IOException {
        if (i >= 0) {
            zzaes(i);
        } else {
            zzcz((long) i);
        }
    }

    public void zzaen(int i) throws IOException {
        zzaes(zzaev(i));
    }

    public void zzaeq(int i) throws IOException {
        zzc((byte) i);
    }

    public void zzaes(int i) throws IOException {
        while ((i & -128) != 0) {
            zzaeq((i & 127) | 128);
            i >>>= 7;
        }
        zzaeq(i);
    }

    public void zzaeu(int i) throws IOException {
        if (this.bia.remaining() < 4) {
            throw new zza(this.bia.position(), this.bia.limit());
        }
        this.bia.putInt(i);
    }

    public void zzaf(int i, int i2) throws IOException {
        zzai(i, 0);
        zzaen(i2);
    }

    public void zzai(int i, int i2) throws IOException {
        zzaes(zzapf.zzaj(i, i2));
    }

    public void zzb(int i, long j) throws IOException {
        zzai(i, 0);
        zzcs(j);
    }

    public void zzb(zzapc zzapc) throws IOException {
        zzapc.zza(this);
    }

    public void zzbb(byte[] bArr) throws IOException {
        zzaes(bArr.length);
        zzbd(bArr);
    }

    public void zzbd(byte[] bArr) throws IOException {
        zzd(bArr, 0, bArr.length);
    }

    public void zzc(byte b) throws IOException {
        if (this.bia.hasRemaining()) {
            this.bia.put(b);
            return;
        }
        throw new zza(this.bia.position(), this.bia.limit());
    }

    public void zzc(int i, float f) throws IOException {
        zzai(i, 5);
        zzk(f);
    }

    public void zzc(int i, long j) throws IOException {
        zzai(i, 1);
        zzct(j);
    }

    public void zzc(zzapc zzapc) throws IOException {
        zzaes(zzapc.an());
        zzapc.zza(this);
    }

    public void zzcr(long j) throws IOException {
        zzcz(j);
    }

    public void zzcs(long j) throws IOException {
        zzcz(j);
    }

    public void zzct(long j) throws IOException {
        zzdb(j);
    }

    public void zzcu(long j) throws IOException {
        zzcz(zzdc(j));
    }

    public void zzcz(long j) throws IOException {
        while ((-128 & j) != 0) {
            zzaeq((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzaeq((int) j);
    }

    public void zzd(int i, long j) throws IOException {
        zzai(i, 0);
        zzcu(j);
    }

    public void zzd(byte[] bArr, int i, int i2) throws IOException {
        if (this.bia.remaining() >= i2) {
            this.bia.put(bArr, i, i2);
            return;
        }
        throw new zza(this.bia.position(), this.bia.limit());
    }

    public void zzdb(long j) throws IOException {
        if (this.bia.remaining() < 8) {
            throw new zza(this.bia.position(), this.bia.limit());
        }
        this.bia.putLong(j);
    }

    public void zzde(boolean z) throws IOException {
        zzaeq(z ? 1 : 0);
    }

    public void zzj(int i, boolean z) throws IOException {
        zzai(i, 0);
        zzde(z);
    }

    public void zzk(float f) throws IOException {
        zzaeu(Float.floatToIntBits(f));
    }

    public void zzo(double d) throws IOException {
        zzdb(Double.doubleToLongBits(d));
    }

    public void zzr(int i, String str) throws IOException {
        zzai(i, 2);
        zztf(str);
    }

    public void zztf(String str) throws IOException {
        try {
            int zzaet = zzaet(str.length());
            if (zzaet == zzaet(str.length() * 3)) {
                int position = this.bia.position();
                if (this.bia.remaining() < zzaet) {
                    throw new zza(zzaet + position, this.bia.limit());
                }
                this.bia.position(position + zzaet);
                zza((CharSequence) str, this.bia);
                int position2 = this.bia.position();
                this.bia.position(position);
                zzaes((position2 - position) - zzaet);
                this.bia.position(position2);
                return;
            }
            zzaes(zzd((CharSequence) str));
            zza((CharSequence) str, this.bia);
        } catch (Throwable e) {
            zza zza = new zza(this.bia.position(), this.bia.limit());
            zza.initCause(e);
            throw zza;
        }
    }
}
