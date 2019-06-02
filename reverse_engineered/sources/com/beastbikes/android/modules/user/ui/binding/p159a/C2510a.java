package com.beastbikes.android.modules.user.ui.binding.p159a;

import android.text.TextUtils;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import org.slf4j.Marker;

/* compiled from: Util */
/* renamed from: com.beastbikes.android.modules.user.ui.binding.a.a */
public class C2510a {
    /* renamed from: a */
    public static String m12611a(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("@")) {
            return str;
        }
        String substring = str.substring(0, str.indexOf("@"));
        String substring2 = str.substring(substring.length(), str.length());
        int length = substring.length();
        if (length <= 5) {
            String str2 = "";
            for (int i = 0; i < length; i++) {
                str2 = str2 + Marker.ANY_MARKER;
            }
            substring = str2 + substring2;
            if (length != 5) {
                return substring;
            }
            return str.substring(0, 1) + substring.substring(1, substring.length());
        }
        int i2 = (length / 2) - 1;
        String substring3 = substring.substring(0, i2);
        return (substring3 + "****" + substring.substring(i2 + 4)) + substring2;
    }

    /* renamed from: b */
    public static String m12613b(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String str2 = "";
        if (str.contains(Marker.ANY_NON_NULL_MARKER)) {
            str2 = str.substring(0, 3);
            str = str.replace(str2, "");
        }
        return str2.concat(str.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
    }

    /* renamed from: c */
    public static boolean m12614c(String str) {
        return str.matches("^[a-zA-Z0-9]+([._\\-]*[a-zA-Z0-9])*@([a-zA-Z0-9]+[-a-zA-Z0-9]*[a-zA-Z0-9]+.){1,63}[a-zA_Z0-9]+$");
    }

    /* renamed from: a */
    public static boolean m12612a(String str, int i) {
        try {
            PhoneNumberUtil instance = PhoneNumberUtil.getInstance();
            return instance.isValidNumber(instance.parse(str, instance.getRegionCodeForCountryCode(i)));
        } catch (NumberParseException e) {
            e.printStackTrace();
            return false;
        }
    }
}
