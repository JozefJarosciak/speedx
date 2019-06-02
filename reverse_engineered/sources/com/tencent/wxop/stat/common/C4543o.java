package com.tencent.wxop.stat.common;

import java.io.File;

/* renamed from: com.tencent.wxop.stat.common.o */
class C4543o {
    /* renamed from: a */
    private static int f16115a = -1;

    /* renamed from: a */
    public static boolean m18089a() {
        if (f16115a == 1) {
            return true;
        }
        if (f16115a == 0) {
            return false;
        }
        String[] strArr = new String[]{"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < 6) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    f16115a = 1;
                    return true;
                }
                i++;
            } catch (Exception e) {
            }
        }
        f16115a = 0;
        return false;
    }
}
