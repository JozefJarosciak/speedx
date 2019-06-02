package com.alipay.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

/* renamed from: com.alipay.sdk.util.a */
public final class C0873a {
    /* renamed from: b */
    private static final String f2184b = "00:00:00:00:00:00";
    /* renamed from: e */
    private static C0873a f2185e = null;
    /* renamed from: a */
    public String f2186a;
    /* renamed from: c */
    private String f2187c;
    /* renamed from: d */
    private String f2188d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private C0873a(android.content.Context r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0052 in list [B:8:0x004e]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r3 = this;
        r3.<init>();
        r0 = "phone";	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r4.getSystemService(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = (android.telephony.TelephonyManager) r0;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r1 = r0.getDeviceId();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r3.m3417b(r1);	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r0.getSubscriberId();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        if (r0 == 0) goto L_0x0032;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
    L_0x0018:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r1.<init>();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r1.append(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r1 = "000000000000000";	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r1 = 0;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r2 = 15;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r0.substring(r1, r2);	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
    L_0x0032:
        r3.f2187c = r0;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = "wifi";	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r4.getSystemService(r0);	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = (android.net.wifi.WifiManager) r0;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r0.getConnectionInfo();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r0.getMacAddress();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r3.f2186a = r0;	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r3.f2186a;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0052;
    L_0x004e:
        r0 = "00:00:00:00:00:00";
        r3.f2186a = r0;
    L_0x0052:
        return;
    L_0x0053:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x0053, all -> 0x0064 }
        r0 = r3.f2186a;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 == 0) goto L_0x0052;
    L_0x005f:
        r0 = "00:00:00:00:00:00";
        r3.f2186a = r0;
        goto L_0x0052;
    L_0x0064:
        r0 = move-exception;
        r1 = r3.f2186a;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 == 0) goto L_0x0071;
    L_0x006d:
        r1 = "00:00:00:00:00:00";
        r3.f2186a = r1;
    L_0x0071:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.util.a.<init>(android.content.Context):void");
    }

    /* renamed from: a */
    public static C0873a m3414a(Context context) {
        if (f2185e == null) {
            f2185e = new C0873a(context);
        }
        return f2185e;
    }

    /* renamed from: a */
    public final String m3422a() {
        if (TextUtils.isEmpty(this.f2187c)) {
            this.f2187c = "000000000000000";
        }
        return this.f2187c;
    }

    /* renamed from: b */
    public final String m3423b() {
        if (TextUtils.isEmpty(this.f2188d)) {
            this.f2188d = "000000000000000";
        }
        return this.f2188d;
    }

    /* renamed from: a */
    private void m3415a(String str) {
        if (str != null) {
            str = (str + "000000000000000").substring(0, 15);
        }
        this.f2187c = str;
    }

    /* renamed from: b */
    private void m3417b(String str) {
        if (str != null) {
            byte[] bytes = str.getBytes();
            int i = 0;
            while (i < bytes.length) {
                if (bytes[i] < (byte) 48 || bytes[i] > (byte) 57) {
                    bytes[i] = (byte) 48;
                }
                i++;
            }
            str = (new String(bytes) + "000000000000000").substring(0, 15);
        }
        this.f2188d = str;
    }

    /* renamed from: c */
    private String m3418c() {
        String str = m3423b() + "|";
        Object a = m3422a();
        if (TextUtils.isEmpty(a)) {
            return str + "000000000000000";
        }
        return str + a;
    }

    /* renamed from: d */
    private String m3420d() {
        return this.f2186a;
    }

    /* renamed from: b */
    public static C0876d m3416b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.getType() == 0) {
                return C0876d.m3436a(activeNetworkInfo.getSubtype());
            }
            if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
                return C0876d.NONE;
            }
            return C0876d.WIFI;
        } catch (Exception e) {
            return C0876d.NONE;
        }
    }

    /* renamed from: c */
    public static String m3419c(Context context) {
        C0873a a = C0873a.m3414a(context);
        String str = a.m3423b() + "|";
        Object a2 = a.m3422a();
        return (TextUtils.isEmpty(a2) ? str + "000000000000000" : str + a2).substring(0, 8);
    }

    /* renamed from: d */
    public static String m3421d(Context context) {
        if (context == null) {
            return "";
        }
        try {
            return context.getResources().getConfiguration().locale.toString();
        } catch (Throwable th) {
            return "";
        }
    }
}
