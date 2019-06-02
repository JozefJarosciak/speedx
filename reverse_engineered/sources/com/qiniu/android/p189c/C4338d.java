package com.qiniu.android.p189c;

import ch.qos.logback.core.CoreConstants;
import com.google.common.base.Ascii;

/* compiled from: StringUtils */
/* renamed from: com.qiniu.android.c.d */
public final class C4338d {
    /* renamed from: a */
    public static String m17126a(String[] strArr, String str) {
        int i = 0;
        if (strArr == null) {
            return null;
        }
        int i2;
        int i3;
        int length = strArr.length;
        if (str == null || str.equals("")) {
            i2 = 0;
        } else {
            i2 = str.length();
        }
        if (length == 0) {
            i3 = 0;
        } else {
            i3 = ((strArr[0] == null ? 16 : strArr[0].length()) + i2) * length;
        }
        StringBuilder stringBuilder = new StringBuilder(i3);
        while (i < length) {
            if (i > 0) {
                stringBuilder.append(str);
            }
            if (strArr[i] != null) {
                stringBuilder.append(strArr[i]);
            }
            i++;
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static String m17125a(String[] strArr) {
        int i = 0;
        int length = strArr.length;
        StringBuilder stringBuilder = new StringBuilder((strArr[0].length() + 3) * length);
        while (i < length) {
            if (i > 0) {
                stringBuilder.append(CoreConstants.COMMA_CHAR);
            }
            stringBuilder.append(CoreConstants.DOUBLE_QUOTE_CHAR);
            stringBuilder.append(strArr[i]);
            stringBuilder.append(CoreConstants.DOUBLE_QUOTE_CHAR);
            i++;
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static boolean m17127a(String str) {
        return str == null || "".equals(str);
    }

    /* renamed from: b */
    public static String m17128b(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt > '\u001f' && charAt < Ascii.MAX) {
                stringBuilder.append(charAt);
            }
        }
        return stringBuilder.toString();
    }
}
