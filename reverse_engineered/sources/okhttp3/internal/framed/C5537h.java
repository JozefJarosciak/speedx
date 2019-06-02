package okhttp3.internal.framed;

import com.avos.avoscloud.AVException;
import com.google.common.base.Ascii;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* compiled from: Huffman */
/* renamed from: okhttp3.internal.framed.h */
class C5537h {
    /* renamed from: a */
    private static final int[] f17850a = new int[]{8184, 8388568, 268435426, 268435427, 268435428, 268435429, 268435430, 268435431, 268435432, 16777194, 1073741820, 268435433, 268435434, 1073741821, 268435435, 268435436, 268435437, 268435438, 268435439, 268435440, 268435441, 268435442, 1073741822, 268435443, 268435444, 268435445, 268435446, 268435447, 268435448, 268435449, 268435450, 268435451, 20, 1016, 1017, 4090, 8185, 21, 248, 2042, 1018, 1019, 249, 2043, 250, 22, 23, 24, 0, 1, 2, 25, 26, 27, 28, 29, 30, 31, 92, AVException.INVALID_LINKED_SESSION, 32764, 32, 4091, 1020, 8186, 33, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, AVException.UNSUPPORTED_SERVICE, AVException.PUSH_MISCONFIGURED, AVException.FILE_DOWNLOAD_INCONSISTENT_FAILURE, 8187, 524272, 8188, 16380, 34, 32765, 3, 35, 4, 36, 5, 37, 38, 39, 6, AVException.OBJECT_TOO_LARGE, 117, 40, 41, 42, 7, 43, 118, 44, 8, 9, 45, AVException.OPERATION_FORBIDDEN, 120, AVException.INVALID_NESTED_KEY, AVException.INVALID_FILE_NAME, AVException.INVALID_ACL, 32766, 2044, 16381, 8189, 268435452, 1048550, 4194258, 1048551, 1048552, 4194259, 4194260, 4194261, 8388569, 4194262, 8388570, 8388571, 8388572, 8388573, 8388574, 16777195, 8388575, 16777196, 16777197, 4194263, 8388576, 16777198, 8388577, 8388578, 8388579, 8388580, 2097116, 4194264, 8388581, 4194265, 8388582, 8388583, 16777199, 4194266, 2097117, 1048553, 4194267, 4194268, 8388584, 8388585, 2097118, 8388586, 4194269, 4194270, 16777200, 2097119, 4194271, 8388587, 8388588, 2097120, 2097121, 4194272, 2097122, 8388589, 4194273, 8388590, 8388591, 1048554, 4194274, 4194275, 4194276, 8388592, 4194277, 4194278, 8388593, 67108832, 67108833, 1048555, 524273, 4194279, 8388594, 4194280, 33554412, 67108834, 67108835, 67108836, 134217694, 134217695, 67108837, 16777201, 33554413, 524274, 2097123, 67108838, 134217696, 134217697, 67108839, 134217698, 16777202, 2097124, 2097125, 67108840, 67108841, 268435453, 134217699, 134217700, 134217701, 1048556, 16777203, 1048557, 2097126, 4194281, 2097127, 2097128, 8388595, 4194282, 4194283, 33554414, 33554415, 16777204, 16777205, 67108842, 8388596, 67108843, 134217702, 67108844, 67108845, 134217703, 134217704, 134217705, 134217706, 134217707, 268435454, 134217708, 134217709, 134217710, 134217711, 134217712, 67108846};
    /* renamed from: b */
    private static final byte[] f17851b = new byte[]{(byte) 13, Ascii.ETB, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.CAN, Ascii.RS, Ascii.FS, Ascii.FS, Ascii.RS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.RS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, Ascii.FS, (byte) 6, (byte) 10, (byte) 10, Ascii.FF, (byte) 13, (byte) 6, (byte) 8, Ascii.VT, (byte) 10, (byte) 10, (byte) 8, Ascii.VT, (byte) 8, (byte) 6, (byte) 6, (byte) 6, (byte) 5, (byte) 5, (byte) 5, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 7, (byte) 8, Ascii.SI, (byte) 6, Ascii.FF, (byte) 10, (byte) 13, (byte) 6, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 8, (byte) 7, (byte) 8, (byte) 13, (byte) 19, (byte) 13, Ascii.SO, (byte) 6, Ascii.SI, (byte) 5, (byte) 6, (byte) 5, (byte) 6, (byte) 5, (byte) 6, (byte) 6, (byte) 6, (byte) 5, (byte) 7, (byte) 7, (byte) 6, (byte) 6, (byte) 6, (byte) 5, (byte) 6, (byte) 7, (byte) 6, (byte) 5, (byte) 5, (byte) 6, (byte) 7, (byte) 7, (byte) 7, (byte) 7, (byte) 7, Ascii.SI, Ascii.VT, Ascii.SO, (byte) 13, Ascii.FS, Ascii.DC4, Ascii.SYN, Ascii.DC4, Ascii.DC4, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.ETB, Ascii.SYN, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.CAN, Ascii.ETB, Ascii.CAN, Ascii.CAN, Ascii.SYN, Ascii.ETB, Ascii.CAN, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.ETB, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.SYN, Ascii.ETB, Ascii.ETB, Ascii.CAN, Ascii.SYN, Ascii.NAK, Ascii.DC4, Ascii.SYN, Ascii.SYN, Ascii.ETB, Ascii.ETB, Ascii.NAK, Ascii.ETB, Ascii.SYN, Ascii.SYN, Ascii.CAN, Ascii.NAK, Ascii.SYN, Ascii.ETB, Ascii.ETB, Ascii.NAK, Ascii.NAK, Ascii.SYN, Ascii.NAK, Ascii.ETB, Ascii.SYN, Ascii.ETB, Ascii.ETB, Ascii.DC4, Ascii.SYN, Ascii.SYN, Ascii.SYN, Ascii.ETB, Ascii.SYN, Ascii.SYN, Ascii.ETB, (byte) 26, (byte) 26, Ascii.DC4, (byte) 19, Ascii.SYN, Ascii.ETB, Ascii.SYN, Ascii.EM, (byte) 26, (byte) 26, (byte) 26, Ascii.ESC, Ascii.ESC, (byte) 26, Ascii.CAN, Ascii.EM, (byte) 19, Ascii.NAK, (byte) 26, Ascii.ESC, Ascii.ESC, (byte) 26, Ascii.ESC, Ascii.CAN, Ascii.NAK, Ascii.NAK, (byte) 26, (byte) 26, Ascii.FS, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.DC4, Ascii.CAN, Ascii.DC4, Ascii.NAK, Ascii.SYN, Ascii.NAK, Ascii.NAK, Ascii.ETB, Ascii.SYN, Ascii.SYN, Ascii.EM, Ascii.EM, Ascii.CAN, Ascii.CAN, (byte) 26, Ascii.ETB, (byte) 26, Ascii.ESC, (byte) 26, (byte) 26, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.FS, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.ESC, Ascii.ESC, (byte) 26};
    /* renamed from: c */
    private static final C5537h f17852c = new C5537h();
    /* renamed from: d */
    private final C5536a f17853d = new C5536a();

    /* compiled from: Huffman */
    /* renamed from: okhttp3.internal.framed.h$a */
    private static final class C5536a {
        /* renamed from: a */
        private final C5536a[] f17847a;
        /* renamed from: b */
        private final int f17848b;
        /* renamed from: c */
        private final int f17849c;

        C5536a() {
            this.f17847a = new C5536a[256];
            this.f17848b = 0;
            this.f17849c = 0;
        }

        C5536a(int i, int i2) {
            this.f17847a = null;
            this.f17848b = i;
            int i3 = i2 & 7;
            if (i3 == 0) {
                i3 = 8;
            }
            this.f17849c = i3;
        }
    }

    /* renamed from: a */
    public static C5537h m20094a() {
        return f17852c;
    }

    private C5537h() {
        m20096b();
    }

    /* renamed from: a */
    byte[] m20097a(byte[] bArr) throws IOException {
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i2 = 0;
        C5536a c5536a = this.f17853d;
        int i3 = 0;
        while (i < bArr.length) {
            i2 = (i2 << 8) | (bArr[i] & 255);
            i3 += 8;
            while (i3 >= 8) {
                c5536a = c5536a.f17847a[(i2 >>> (i3 - 8)) & 255];
                if (c5536a.f17847a == null) {
                    byteArrayOutputStream.write(c5536a.f17848b);
                    i3 -= c5536a.f17849c;
                    c5536a = this.f17853d;
                } else {
                    i3 -= 8;
                }
            }
            i++;
        }
        while (i3 > 0) {
            C5536a c5536a2 = c5536a.f17847a[(i2 << (8 - i3)) & 255];
            if (c5536a2.f17847a != null || c5536a2.f17849c > i3) {
                break;
            }
            byteArrayOutputStream.write(c5536a2.f17848b);
            i3 -= c5536a2.f17849c;
            c5536a = this.f17853d;
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: b */
    private void m20096b() {
        for (int i = 0; i < f17851b.length; i++) {
            m20095a(i, f17850a[i], f17851b[i]);
        }
    }

    /* renamed from: a */
    private void m20095a(int i, int i2, byte b) {
        int i3;
        C5536a c5536a = new C5536a(i, b);
        C5536a c5536a2 = this.f17853d;
        while (b > (byte) 8) {
            b = (byte) (b - 8);
            i3 = (i2 >>> b) & 255;
            if (c5536a2.f17847a == null) {
                throw new IllegalStateException("invalid dictionary: prefix not unique");
            }
            if (c5536a2.f17847a[i3] == null) {
                c5536a2.f17847a[i3] = new C5536a();
            }
            c5536a2 = c5536a2.f17847a[i3];
        }
        i3 = 8 - b;
        int i4 = (i2 << i3) & 255;
        int i5 = 1 << i3;
        for (i3 = i4; i3 < i4 + i5; i3++) {
            c5536a2.f17847a[i3] = c5536a;
        }
    }
}
