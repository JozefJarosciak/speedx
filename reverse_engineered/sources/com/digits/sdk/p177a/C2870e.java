package com.digits.sdk.p177a;

import android.telephony.PhoneNumberUtils;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.SparseArray;
import ch.qos.logback.core.CoreConstants;
import com.avos.avoscloud.AVException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: VCardUtils */
/* renamed from: com.digits.sdk.a.e */
public class C2870e {
    /* renamed from: a */
    private static final SparseArray<String> f13062a = new SparseArray();
    /* renamed from: b */
    private static final Set<String> f13063b = new HashSet();
    /* renamed from: c */
    private static final Map<String, Integer> f13064c = new HashMap();
    /* renamed from: d */
    private static final SparseArray<String> f13065d = new SparseArray();
    /* renamed from: e */
    private static final Set<String> f13066e = new HashSet(Arrays.asList(new String[]{"MOBILE", "携帯電話", "携帯", "ケイタイ", "ｹｲﾀｲ"}));
    /* renamed from: f */
    private static final Set<Character> f13067f = new HashSet(Arrays.asList(new Character[]{Character.valueOf('['), Character.valueOf(']'), Character.valueOf('='), Character.valueOf(CoreConstants.COLON_CHAR), Character.valueOf(CoreConstants.DOT), Character.valueOf(CoreConstants.COMMA_CHAR), Character.valueOf(' ')}));
    /* renamed from: g */
    private static final int[] f13068g = new int[]{58, 59, 44, 32};
    /* renamed from: h */
    private static final int[] f13069h = new int[]{59, 58};

    /* compiled from: VCardUtils */
    /* renamed from: com.digits.sdk.a.e$a */
    public static class C2868a {
        /* renamed from: a */
        public static String m13809a(String str, int i) {
            Object spannableStringBuilder = new SpannableStringBuilder(str);
            PhoneNumberUtils.formatNumber(spannableStringBuilder, i);
            return spannableStringBuilder.toString();
        }
    }

    /* compiled from: VCardUtils */
    /* renamed from: com.digits.sdk.a.e$b */
    public static class C2869b {
        /* renamed from: a */
        public static boolean m13810a(char c) {
            return (' ' <= c && c <= '~') || c == '\r' || c == '\n';
        }

        /* renamed from: a */
        public static boolean m13811a(CharSequence charSequence) {
            int length = charSequence.length();
            for (int i = 0; i < length; i++) {
                if (!C2869b.m13810a(charSequence.charAt(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    static {
        f13062a.put(9, "CAR");
        f13064c.put("CAR", Integer.valueOf(9));
        f13062a.put(6, "PAGER");
        f13064c.put("PAGER", Integer.valueOf(6));
        f13062a.put(11, "ISDN");
        f13064c.put("ISDN", Integer.valueOf(11));
        f13064c.put("HOME", Integer.valueOf(1));
        f13064c.put("WORK", Integer.valueOf(3));
        f13064c.put("CELL", Integer.valueOf(2));
        f13064c.put("OTHER", Integer.valueOf(7));
        f13064c.put("CALLBACK", Integer.valueOf(8));
        f13064c.put("COMPANY-MAIN", Integer.valueOf(10));
        f13064c.put("RADIO", Integer.valueOf(14));
        f13064c.put("TTY-TDD", Integer.valueOf(16));
        f13064c.put("ASSISTANT", Integer.valueOf(19));
        f13064c.put("VOICE", Integer.valueOf(7));
        f13063b.add("MODEM");
        f13063b.add("MSG");
        f13063b.add("BBS");
        f13063b.add("VIDEO");
        f13065d.put(0, "X-AIM");
        f13065d.put(1, "X-MSN");
        f13065d.put(2, "X-YAHOO");
        f13065d.put(3, "X-SKYPE-USERNAME");
        f13065d.put(5, "X-GOOGLE-TALK");
        f13065d.put(6, "X-ICQ");
        f13065d.put(7, "X-JABBER");
        f13065d.put(4, "X-QQ");
        f13065d.put(8, "X-NETMEETING");
    }

    /* renamed from: a */
    public static String m13814a(Integer num) {
        return (String) f13062a.get(num.intValue());
    }

    /* renamed from: a */
    public static boolean m13816a(String str) {
        return "_AUTO_CELL".equals(str) || f13066e.contains(str);
    }

    /* renamed from: b */
    public static boolean m13821b(String str) {
        return f13063b.contains(str);
    }

    /* renamed from: a */
    public static String[] m13819a(int i, String str, String str2, String str3) {
        String[] strArr = new String[3];
        switch (C2866c.m13800e(i)) {
            case 4:
                strArr[0] = str2;
                strArr[1] = str3;
                strArr[2] = str;
                break;
            case 8:
                if (C2870e.m13818a(str)) {
                    if (C2870e.m13818a(str3)) {
                        strArr[0] = str3;
                        strArr[1] = str2;
                        strArr[2] = str;
                        break;
                    }
                }
                strArr[0] = str;
                strArr[1] = str2;
                strArr[2] = str3;
                break;
            default:
                strArr[0] = str3;
                strArr[1] = str2;
                strArr[2] = str;
                break;
        }
        return strArr;
    }

    /* renamed from: a */
    public static int m13812a(int i) {
        if (C2866c.m13804i(i)) {
            return 2;
        }
        return 1;
    }

    /* renamed from: b */
    public static String m13820b(int i, String str, String str2, String str3) {
        return C2870e.m13813a(i, str, str2, str3, null, null);
    }

    /* renamed from: a */
    public static String m13813a(int i, String str, String str2, String str3, String str4, String str5) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] a = C2870e.m13819a(i, str, str2, str3);
        Object obj = 1;
        if (!TextUtils.isEmpty(str4)) {
            stringBuilder.append(str4);
            obj = null;
        }
        for (Object obj2 : a) {
            if (!TextUtils.isEmpty(obj2)) {
                if (obj != null) {
                    obj = null;
                } else {
                    stringBuilder.append(' ');
                }
                stringBuilder.append(obj2);
            }
        }
        if (!TextUtils.isEmpty(str5)) {
            if (obj == null) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(str5);
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static boolean m13818a(String... strArr) {
        if (strArr == null) {
            return true;
        }
        return C2870e.m13817a(Arrays.asList(strArr));
    }

    /* renamed from: a */
    public static boolean m13817a(Collection<String> collection) {
        if (collection == null) {
            return true;
        }
        for (CharSequence charSequence : collection) {
            if (!TextUtils.isEmpty(charSequence) && !C2869b.m13811a(charSequence)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: b */
    public static boolean m13823b(String... strArr) {
        if (strArr == null) {
            return true;
        }
        return C2870e.m13822b(Arrays.asList(strArr));
    }

    /* renamed from: b */
    public static boolean m13822b(Collection<String> collection) {
        if (collection == null) {
            return true;
        }
        for (String str : collection) {
            if (!TextUtils.isEmpty(str)) {
                int length = str.length();
                for (int i = 0; i < length; i = str.offsetByCodePoints(i, 1)) {
                    int codePointAt = str.codePointAt(i);
                    if (32 > codePointAt || codePointAt > 126) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    /* renamed from: c */
    public static boolean m13826c(String... strArr) {
        if (strArr == null) {
            return true;
        }
        return C2870e.m13825c(Arrays.asList(strArr));
    }

    /* renamed from: c */
    public static boolean m13825c(Collection<String> collection) {
        if (collection == null) {
            return true;
        }
        for (String str : collection) {
            if (!TextUtils.isEmpty(str)) {
                int length = str.length();
                for (int i = 0; i < length; i = str.offsetByCodePoints(i, 1)) {
                    int codePointAt = str.codePointAt(i);
                    if ((97 > codePointAt || codePointAt >= AVException.INVALID_ACL) && ((65 > codePointAt || codePointAt >= 91) && ((48 > codePointAt || codePointAt >= 58) && codePointAt != 45))) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    /* renamed from: d */
    public static boolean m13829d(String... strArr) {
        if (strArr == null) {
            return true;
        }
        return C2870e.m13828d(Arrays.asList(strArr));
    }

    /* renamed from: d */
    public static boolean m13828d(Collection<String> collection) {
        if (collection == null) {
            return true;
        }
        for (String str : collection) {
            if (!TextUtils.isEmpty(str)) {
                int length = str.length();
                for (int i = 0; i < length; i = str.offsetByCodePoints(i, 1)) {
                    if (!Character.isWhitespace(str.codePointAt(i))) {
                        return false;
                    }
                }
                continue;
            }
        }
        return true;
    }

    /* renamed from: c */
    public static boolean m13824c(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        int length = str.length();
        int i = 0;
        while (i < length) {
            int codePointAt = str.codePointAt(i);
            if (32 > codePointAt || codePointAt > 126 || f13067f.contains(Character.valueOf((char) codePointAt))) {
                return false;
            }
            i = str.offsetByCodePoints(i, 1);
        }
        return true;
    }

    /* renamed from: d */
    public static String m13827d(String str) {
        return C2870e.m13815a(str, f13068g);
    }

    /* renamed from: e */
    public static String m13830e(String str) {
        return C2870e.m13815a(str, f13069h);
    }

    /* renamed from: a */
    private static String m13815a(String str, int[] iArr) {
        if (TextUtils.isEmpty(str)) {
            str = "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2 = str.offsetByCodePoints(i2, 1)) {
            int codePointAt = str.codePointAt(i2);
            if (codePointAt >= 32 && codePointAt != 34) {
                stringBuilder.appendCodePoint(codePointAt);
                for (int i3 : iArr) {
                    if (codePointAt == i3) {
                        i = 1;
                        break;
                    }
                }
            }
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.length() != 0) {
            if (!C2870e.m13829d(stringBuilder2)) {
                return i != 0 ? CoreConstants.DOUBLE_QUOTE_CHAR + stringBuilder2 + CoreConstants.DOUBLE_QUOTE_CHAR : stringBuilder2;
            }
        }
        return "";
    }

    /* renamed from: f */
    public static String m13831f(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            String a = C2864a.m13772a(charAt);
            if (a != null) {
                stringBuilder.append(a);
            } else {
                stringBuilder.append(charAt);
            }
            i = str.offsetByCodePoints(i, 1);
        }
        return stringBuilder.toString();
    }
}
