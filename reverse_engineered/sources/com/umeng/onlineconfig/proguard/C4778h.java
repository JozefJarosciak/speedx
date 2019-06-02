package com.umeng.onlineconfig.proguard;

import com.umeng.onlineconfig.OnlineConfigLog;
import java.security.MessageDigest;

/* compiled from: OnlineConfigHelper */
/* renamed from: com.umeng.onlineconfig.proguard.h */
public class C4778h {
    /* renamed from: a */
    public static final String f16728a = System.getProperty("line.separator");
    /* renamed from: b */
    private static final String f16729b = C4778h.class.getName();

    /* renamed from: a */
    public static boolean m18754a(String str) {
        return str == null || str.length() == 0;
    }

    /* renamed from: b */
    public static String m18755b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            byte[] digest = instance.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString(b & 255));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            OnlineConfigLog.m18733i(f16729b, "getMD5 error", e);
            return "";
        }
    }
}
