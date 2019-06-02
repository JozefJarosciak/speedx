package com.baidu.platform.comapi;

import android.app.Application;
import android.content.Context;
import com.baidu.mapapi.JNIInitializer;
import com.baidu.mapapi.common.EnvironmentUtilities;
import java.io.File;

/* renamed from: com.baidu.platform.comapi.c */
public class C1220c {
    /* renamed from: a */
    private static boolean f3610a;

    /* renamed from: a */
    public static void m4569a(String str, Context context) {
        if (!f3610a) {
            if (context == null) {
                throw new IllegalArgumentException("context can not be null");
            } else if (context instanceof Application) {
                NativeLoader.getInstance();
                NativeLoader.setContext(context);
                C1218a.m4559a().m4562a(context);
                C1218a.m4559a().m4566c();
                JNIInitializer.setContext((Application) context);
                if (!(str == null || str.equals(""))) {
                    try {
                        File file = new File(str + "/test.0");
                        if (file.exists()) {
                            file.delete();
                        }
                        file.createNewFile();
                        if (file.exists()) {
                            file.delete();
                        }
                        EnvironmentUtilities.setSDCardPath(str);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new IllegalArgumentException("provided sdcard path can not used.");
                    }
                }
                f3610a = true;
            } else {
                throw new RuntimeException("context must be an Application Context");
            }
        }
    }
}
