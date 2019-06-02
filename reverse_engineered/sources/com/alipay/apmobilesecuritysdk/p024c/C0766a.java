package com.alipay.apmobilesecuritysdk.p024c;

import android.content.Context;
import android.os.Build;
import com.alipay.p029b.p030a.p031a.p039e.C0807a;
import com.alipay.p029b.p030a.p031a.p039e.C0810d;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* renamed from: com.alipay.apmobilesecuritysdk.c.a */
public final class C0766a {
    /* renamed from: a */
    public static synchronized void m2924a(Context context, String str, String str2, String str3) {
        synchronized (C0766a.class) {
            C0807a c0807a = new C0807a(Build.MODEL, context.getApplicationContext().getApplicationInfo().packageName, "security-sdk-token", "3.2.0-20160621", str, str2, str3);
            C0810d.m3136a(context.getFilesDir().getAbsolutePath() + "/log/ap", new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log", c0807a.toString());
        }
    }

    /* renamed from: a */
    public static synchronized void m2925a(String str) {
        synchronized (C0766a.class) {
            C0810d.m3135a(str);
        }
    }

    /* renamed from: a */
    public static synchronized void m2926a(Throwable th) {
        synchronized (C0766a.class) {
            C0810d.m3137a(th);
        }
    }
}
