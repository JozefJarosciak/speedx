package com.alipay.p029b.p030a.p031a.p038d;

import android.os.Environment;
import com.alipay.p029b.p030a.p031a.p032a.C0790b;
import java.io.File;

/* renamed from: com.alipay.b.a.a.d.b */
public final class C0805b {
    /* renamed from: a */
    public static String m3127a(String str) {
        try {
            if (C0805b.m3128a()) {
                String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath();
                if (new File(absolutePath, str).exists()) {
                    return C0790b.m3028a(absolutePath, str);
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m3128a() {
        String externalStorageState = Environment.getExternalStorageState();
        return externalStorageState != null && externalStorageState.length() > 0 && ((externalStorageState.equals("mounted") || externalStorageState.equals("mounted_ro")) && Environment.getExternalStorageDirectory() != null);
    }
}
