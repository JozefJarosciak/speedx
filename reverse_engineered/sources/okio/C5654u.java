package okio;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;
import java.nio.charset.Charset;

/* compiled from: Util */
/* renamed from: okio.u */
final class C5654u {
    /* renamed from: a */
    public static final Charset f18252a = Charset.forName("UTF-8");

    /* renamed from: a */
    public static void m20769a(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", new Object[]{Long.valueOf(j), Long.valueOf(j2), Long.valueOf(j3)}));
        }
    }

    /* renamed from: a */
    public static short m20768a(short s) {
        int i = 65535 & s;
        return (short) (((i & 255) << 8) | ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >>> 8));
    }

    /* renamed from: a */
    public static int m20767a(int i) {
        return ((((ViewCompat.MEASURED_STATE_MASK & i) >>> 24) | ((16711680 & i) >>> 8)) | ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) << 8)) | ((i & 255) << 24);
    }

    /* renamed from: a */
    public static void m20770a(Throwable th) {
        C5654u.m20772b(th);
    }

    /* renamed from: b */
    private static <T extends Throwable> void m20772b(Throwable th) throws Throwable {
        throw th;
    }

    /* renamed from: a */
    public static boolean m20771a(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i4 + i] != bArr2[i4 + i2]) {
                return false;
            }
        }
        return true;
    }
}
