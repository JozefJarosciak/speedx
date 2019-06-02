package com.alipay.sdk.sys;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C0823a;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.data.C0849c;
import com.ta.utdid2.device.UTDevice;
import java.io.File;

/* renamed from: com.alipay.sdk.sys.b */
public final class C0870b {
    /* renamed from: b */
    private static C0870b f2176b;
    /* renamed from: a */
    public Context f2177a;

    private C0870b() {
    }

    /* renamed from: a */
    public static C0870b m3386a() {
        if (f2176b == null) {
            f2176b = new C0870b();
        }
        return f2176b;
    }

    /* renamed from: d */
    private Context m3389d() {
        return this.f2177a;
    }

    /* renamed from: a */
    public final void m3391a(Context context) {
        this.f2177a = context.getApplicationContext();
    }

    /* renamed from: e */
    private static C0849c m3390e() {
        return C0849c.m3265a();
    }

    /* renamed from: b */
    public static boolean m3388b() {
        String[] strArr = new String[]{"/system/xbin/", "/system/bin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < 5) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    String a = C0870b.m3387a(new String[]{"ls", "-l", strArr[i] + "su"});
                    if (TextUtils.isEmpty(a) || a.indexOf("root") == a.lastIndexOf("root")) {
                        return false;
                    }
                    return true;
                }
                i++;
            } catch (Exception e) {
            }
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    private static java.lang.String m3387a(java.lang.String[] r6) {
        /*
        r2 = "";
        r0 = 0;
        r1 = new java.lang.ProcessBuilder;	 Catch:{ Throwable -> 0x0035, all -> 0x003e }
        r1.<init>(r6);	 Catch:{ Throwable -> 0x0035, all -> 0x003e }
        r3 = 0;
        r1.redirectErrorStream(r3);	 Catch:{ Throwable -> 0x0035, all -> 0x003e }
        r1 = r1.start();	 Catch:{ Throwable -> 0x0035, all -> 0x003e }
        r3 = new java.io.DataOutputStream;	 Catch:{ Throwable -> 0x004c, all -> 0x004a }
        r0 = r1.getOutputStream();	 Catch:{ Throwable -> 0x004c, all -> 0x004a }
        r3.<init>(r0);	 Catch:{ Throwable -> 0x004c, all -> 0x004a }
        r0 = new java.io.DataInputStream;	 Catch:{ Throwable -> 0x004c, all -> 0x004a }
        r4 = r1.getInputStream();	 Catch:{ Throwable -> 0x004c, all -> 0x004a }
        r0.<init>(r4);	 Catch:{ Throwable -> 0x004c, all -> 0x004a }
        r0 = r0.readLine();	 Catch:{ Throwable -> 0x004c, all -> 0x004a }
        r2 = "exit\n";
        r3.writeBytes(r2);	 Catch:{ Throwable -> 0x004f, all -> 0x004a }
        r3.flush();	 Catch:{ Throwable -> 0x004f, all -> 0x004a }
        r1.waitFor();	 Catch:{ Throwable -> 0x004f, all -> 0x004a }
        r1.destroy();	 Catch:{ Exception -> 0x0046 }
    L_0x0034:
        return r0;
    L_0x0035:
        r1 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0038:
        r1.destroy();	 Catch:{ Exception -> 0x003c }
        goto L_0x0034;
    L_0x003c:
        r1 = move-exception;
        goto L_0x0034;
    L_0x003e:
        r1 = move-exception;
        r5 = r1;
        r1 = r0;
        r0 = r5;
    L_0x0042:
        r1.destroy();	 Catch:{ Exception -> 0x0048 }
    L_0x0045:
        throw r0;
    L_0x0046:
        r1 = move-exception;
        goto L_0x0034;
    L_0x0048:
        r1 = move-exception;
        goto L_0x0045;
    L_0x004a:
        r0 = move-exception;
        goto L_0x0042;
    L_0x004c:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0038;
    L_0x004f:
        r2 = move-exception;
        goto L_0x0038;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.sys.b.a(java.lang.String[]):java.lang.String");
    }

    /* renamed from: c */
    public final String m3392c() {
        String str = "";
        try {
            str = UTDevice.getUtdid(this.f2177a);
        } catch (Throwable th) {
            C0823a.m3185a(C0825c.f1955e, C0825c.f1960j, th);
        }
        return str;
    }
}
