package cn.sharesdk.framework.utils;

import ch.qos.logback.core.CoreConstants;
import com.avos.avoscloud.AVException;

/* compiled from: PercentEscaper */
/* renamed from: cn.sharesdk.framework.utils.b */
public class C0618b extends C0617e {
    /* renamed from: a */
    private static final char[] f1398a = new char[]{'+'};
    /* renamed from: b */
    private static final char[] f1399b = "0123456789ABCDEF".toCharArray();
    /* renamed from: c */
    private final boolean f1400c;
    /* renamed from: d */
    private final boolean[] f1401d;

    public C0618b(String str, boolean z) {
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        } else if (z && str.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        } else if (str.contains("%")) {
            throw new IllegalArgumentException("The '%' character cannot be specified as 'safe'");
        } else {
            this.f1400c = z;
            this.f1401d = C0618b.m2275a(str);
        }
    }

    /* renamed from: a */
    private static boolean[] m2275a(String str) {
        int i;
        int i2 = 0;
        char[] toCharArray = str.toCharArray();
        int i3 = AVException.INVALID_FILE_NAME;
        for (char max : toCharArray) {
            i3 = Math.max(max, i3);
        }
        boolean[] zArr = new boolean[(i3 + 1)];
        for (i = 48; i <= 57; i++) {
            zArr[i] = true;
        }
        for (i = 65; i <= 90; i++) {
            zArr[i] = true;
        }
        for (i = 97; i <= AVException.INVALID_FILE_NAME; i++) {
            zArr[i] = true;
        }
        i = toCharArray.length;
        while (i2 < i) {
            zArr[toCharArray[i2]] = true;
            i2++;
        }
        return zArr;
    }

    /* renamed from: a */
    protected int mo2289a(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            if (charAt >= this.f1401d.length || !this.f1401d[charAt]) {
                break;
            }
            i++;
        }
        return i;
    }

    public String escape(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt >= this.f1401d.length || !this.f1401d[charAt]) {
                return m2273a(str, i);
            }
        }
        return str;
    }

    /* renamed from: a */
    protected char[] mo2290a(int i) {
        if (i < this.f1401d.length && this.f1401d[i]) {
            return null;
        }
        if (i == 32 && this.f1400c) {
            return f1398a;
        }
        if (i <= 127) {
            return new char[]{CoreConstants.PERCENT_CHAR, f1399b[i & 15], f1399b[i >>> 4]};
        } else if (i <= 2047) {
            r0 = new char[6];
            r0[0] = CoreConstants.PERCENT_CHAR;
            r0[3] = CoreConstants.PERCENT_CHAR;
            r0[5] = f1399b[i & 15];
            r1 = i >>> 4;
            r0[4] = f1399b[(r1 & 3) | 8];
            r1 >>>= 2;
            r0[2] = f1399b[r1 & 15];
            r0[1] = f1399b[(r1 >>> 4) | 12];
            return r0;
        } else if (i <= 65535) {
            r0 = new char[9];
            r1 = i >>> 4;
            r0[7] = f1399b[(r1 & 3) | 8];
            r1 >>>= 2;
            r0[5] = f1399b[r1 & 15];
            r1 >>>= 4;
            r0[4] = f1399b[(r1 & 3) | 8];
            r0[2] = f1399b[r1 >>> 2];
            return r0;
        } else if (i <= Surrogate.UCS4_MAX) {
            r0 = new char[12];
            r1 = i >>> 4;
            r0[10] = f1399b[(r1 & 3) | 8];
            r1 >>>= 2;
            r0[8] = f1399b[r1 & 15];
            r1 >>>= 4;
            r0[7] = f1399b[(r1 & 3) | 8];
            r1 >>>= 2;
            r0[5] = f1399b[r1 & 15];
            r1 >>>= 4;
            r0[4] = f1399b[(r1 & 3) | 8];
            r0[2] = f1399b[(r1 >>> 2) & 7];
            return r0;
        } else {
            throw new IllegalArgumentException("Invalid unicode character value " + i);
        }
    }
}
