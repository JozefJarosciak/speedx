package com.beastbikes.android.utils;

import java.io.UnsupportedEncodingException;

/* compiled from: StringUtil */
/* renamed from: com.beastbikes.android.utils.x */
public class C2581x {
    /* renamed from: a */
    public static int m12906a(String str) {
        int i = 0;
        if (C2581x.m12907b(str)) {
            return i;
        }
        try {
            int length = str.getBytes("UTF-8").length;
            int length2 = new String(str.getBytes(), "UTF-8").length();
            length = (length - length2) / 2;
            return (((length2 - length) + 1) / 2) + length;
        } catch (UnsupportedEncodingException e) {
            return i;
        }
    }

    /* renamed from: b */
    public static boolean m12907b(String str) {
        return str == null || str.equals("");
    }
}
