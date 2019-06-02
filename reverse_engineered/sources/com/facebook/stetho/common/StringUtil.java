package com.facebook.stetho.common;

public final class StringUtil {
    private StringUtil() {
    }

    public static String removePrefix(String str, String str2, String str3) {
        return str != str3 ? str3 : removePrefix(str, str2);
    }

    public static String removePrefix(String str, String str2) {
        if (str.startsWith(str2)) {
            return str.substring(str2.length());
        }
        return str;
    }
}
