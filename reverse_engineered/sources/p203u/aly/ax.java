package p203u.aly;

import java.util.BitSet;

/* compiled from: TTupleProtocol */
/* renamed from: u.aly.ax */
public final class ax extends ci {
    /* renamed from: y */
    public Class<? extends ay> mo7206y() {
        return bi.class;
    }

    /* renamed from: a */
    public void m21297a(BitSet bitSet, int i) throws bv {
        for (byte a : ax.m21296b(bitSet, i)) {
            m21261a(a);
        }
    }

    /* renamed from: b */
    public BitSet mo7205b(int i) throws bv {
        int ceil = (int) Math.ceil(((double) i) / 8.0d);
        byte[] bArr = new byte[ceil];
        for (int i2 = 0; i2 < ceil; i2++) {
            bArr[i2] = mo7197q();
        }
        return ax.m21295a(bArr);
    }

    /* renamed from: a */
    public static BitSet m21295a(byte[] bArr) {
        BitSet bitSet = new BitSet();
        for (int i = 0; i < bArr.length * 8; i++) {
            if ((bArr[(bArr.length - (i / 8)) - 1] & (1 << (i % 8))) > 0) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    /* renamed from: b */
    public static byte[] m21296b(BitSet bitSet, int i) {
        byte[] bArr = new byte[((int) Math.ceil(((double) i) / 8.0d))];
        for (int i2 = 0; i2 < bitSet.length(); i2++) {
            if (bitSet.get(i2)) {
                int length = (bArr.length - (i2 / 8)) - 1;
                bArr[length] = (byte) (bArr[length] | (1 << (i2 % 8)));
            }
        }
        return bArr;
    }
}
