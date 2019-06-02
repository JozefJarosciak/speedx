package p203u.aly;

/* compiled from: EncodingUtils */
/* renamed from: u.aly.ai */
public class ai {
    /* renamed from: a */
    public static final boolean m21169a(byte b, int i) {
        return ai.m21170a((int) b, i);
    }

    /* renamed from: a */
    public static final boolean m21170a(int i, int i2) {
        return ((1 << i2) & i) != 0;
    }

    /* renamed from: b */
    public static final byte m21171b(byte b, int i) {
        return (byte) ai.m21172b((int) b, i);
    }

    /* renamed from: b */
    public static final int m21172b(int i, int i2) {
        return ((1 << i2) ^ -1) & i;
    }

    /* renamed from: a */
    public static final byte m21167a(byte b, int i, boolean z) {
        return (byte) ai.m21168a((int) b, i, z);
    }

    /* renamed from: a */
    public static final int m21168a(int i, int i2, boolean z) {
        if (z) {
            return (1 << i2) | i;
        }
        return ai.m21172b(i, i2);
    }
}
