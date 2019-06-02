package com.tencent.bugly.proguard;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import java.util.Map;
import java.util.UUID;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.proguard.u */
public final class C4472u implements Runnable {
    /* renamed from: a */
    private int f15752a;
    /* renamed from: b */
    private int f15753b;
    /* renamed from: c */
    private final Context f15754c;
    /* renamed from: d */
    private final int f15755d;
    /* renamed from: e */
    private final byte[] f15756e;
    /* renamed from: f */
    private final C4417a f15757f;
    /* renamed from: g */
    private final C4421a f15758g;
    /* renamed from: h */
    private final C4467r f15759h;
    /* renamed from: i */
    private final C4471t f15760i;
    /* renamed from: j */
    private final int f15761j;
    /* renamed from: k */
    private final C4406s f15762k;
    /* renamed from: l */
    private final C4406s f15763l;
    /* renamed from: m */
    private String f15764m;
    /* renamed from: n */
    private final String f15765n;
    /* renamed from: o */
    private final Map<String, String> f15766o;
    /* renamed from: p */
    private int f15767p;
    /* renamed from: q */
    private long f15768q;
    /* renamed from: r */
    private long f15769r;
    /* renamed from: s */
    private boolean f15770s;
    /* renamed from: t */
    private boolean f15771t;

    public C4472u(Context context, int i, int i2, byte[] bArr, String str, String str2, C4406s c4406s, boolean z, boolean z2) {
        this(context, i, i2, bArr, str, str2, c4406s, z, 2, 30000, z2, null);
    }

    public C4472u(Context context, int i, int i2, byte[] bArr, String str, String str2, C4406s c4406s, boolean z, int i3, int i4, boolean z2, Map<String, String> map) {
        this.f15752a = 2;
        this.f15753b = 30000;
        this.f15764m = null;
        this.f15767p = 0;
        this.f15768q = 0;
        this.f15769r = 0;
        this.f15770s = true;
        this.f15771t = false;
        this.f15754c = context;
        this.f15757f = C4417a.m17303a(context);
        this.f15756e = bArr;
        this.f15758g = C4421a.m17388a();
        this.f15759h = C4467r.m17697a(context);
        this.f15760i = C4471t.m17703a();
        this.f15761j = i;
        this.f15764m = str;
        this.f15765n = str2;
        this.f15762k = c4406s;
        C4471t c4471t = this.f15760i;
        this.f15763l = null;
        this.f15770s = z;
        this.f15755d = i2;
        if (i3 > 0) {
            this.f15752a = i3;
        }
        if (i4 > 0) {
            this.f15753b = i4;
        }
        this.f15771t = z2;
        this.f15766o = map;
    }

    /* renamed from: a */
    private void m17736a(am amVar, boolean z, int i, String str, int i2) {
        String str2;
        switch (this.f15755d) {
            case 630:
            case 830:
                str2 = "crash";
                break;
            case 640:
            case 840:
                str2 = "userinfo";
                break;
            default:
                str2 = String.valueOf(this.f15755d);
                break;
        }
        if (z) {
            C4475w.m17747a("[Upload] Success: %s", str2);
        } else {
            C4475w.m17753e("[Upload] Failed to upload(%d) %s: %s", Integer.valueOf(i), str2, str);
            if (this.f15770s) {
                this.f15760i.m17727a(i2, null);
            }
        }
        if (this.f15768q + this.f15769r > 0) {
            this.f15760i.m17728a((this.f15760i.m17722a(this.f15771t) + this.f15768q) + this.f15769r, this.f15771t);
        }
        if (this.f15762k != null) {
            C4406s c4406s = this.f15762k;
            int i3 = this.f15755d;
            long j = this.f15768q;
            j = this.f15769r;
            c4406s.mo6056a(z);
        }
        if (this.f15763l != null) {
            c4406s = this.f15763l;
            i3 = this.f15755d;
            j = this.f15768q;
            j = this.f15769r;
            c4406s.mo6056a(z);
        }
    }

    /* renamed from: a */
    private static boolean m17737a(am amVar, C4417a c4417a, C4421a c4421a) {
        if (amVar == null) {
            C4475w.m17752d("resp == null!", new Object[0]);
            return false;
        } else if (amVar.f15606a != (byte) 0) {
            C4475w.m17753e("resp result error %d", Byte.valueOf(amVar.f15606a));
            return false;
        } else {
            try {
                if (!(C4479y.m17792a(amVar.f15609d) || C4417a.m17304b().m17340i().equals(amVar.f15609d))) {
                    C4464o.m17672a().m17693a(C4421a.f15340a, "key_ip", amVar.f15609d.getBytes("UTF-8"), null, true);
                    c4417a.m17332d(amVar.f15609d);
                }
                if (!(C4479y.m17792a(amVar.f15611f) || C4417a.m17304b().m17341j().equals(amVar.f15611f))) {
                    C4464o.m17672a().m17693a(C4421a.f15340a, "key_imei", amVar.f15611f.getBytes("UTF-8"), null, true);
                    c4417a.m17334e(amVar.f15611f);
                }
            } catch (Throwable th) {
                C4475w.m17748a(th);
            }
            c4417a.f15294i = amVar.f15610e;
            if (amVar.f15607b == 510) {
                if (amVar.f15608c == null) {
                    C4475w.m17753e("[Upload] Strategy data is null. Response cmd: %d", Integer.valueOf(amVar.f15607b));
                    return false;
                }
                ao aoVar = (ao) C4446a.m17527a(amVar.f15608c, ao.class);
                if (aoVar == null) {
                    C4475w.m17753e("[Upload] Failed to decode strategy from server. Response cmd: %d", Integer.valueOf(amVar.f15607b));
                    return false;
                }
                c4421a.m17394a(aoVar);
            }
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r11 = this;
        r0 = 0;
        r11.f15767p = r0;	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
        r11.f15768q = r0;	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
        r11.f15769r = r0;	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.f15756e;	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.f15754c;	 Catch:{ Throwable -> 0x0030 }
        r1 = com.tencent.bugly.crashreport.common.info.C4418b.m17368e(r1);	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x0020;
    L_0x0015:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "network is not available";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
    L_0x001f:
        return;
    L_0x0020:
        if (r0 == 0) goto L_0x0025;
    L_0x0022:
        r1 = r0.length;	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x003b;
    L_0x0025:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "request package is empty!";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x0030:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.C4475w.m17748a(r0);
        if (r1 != 0) goto L_0x001f;
    L_0x0037:
        r0.printStackTrace();
        goto L_0x001f;
    L_0x003b:
        r1 = r11.f15760i;	 Catch:{ Throwable -> 0x0030 }
        r2 = r11.f15771t;	 Catch:{ Throwable -> 0x0030 }
        r2 = r1.m17722a(r2);	 Catch:{ Throwable -> 0x0030 }
        r1 = r0.length;	 Catch:{ Throwable -> 0x0030 }
        r4 = (long) r1;	 Catch:{ Throwable -> 0x0030 }
        r4 = r4 + r2;
        r6 = 2097152; // 0x200000 float:2.938736E-39 double:1.0361308E-317;
        r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r1 < 0) goto L_0x0086;
    L_0x004d:
        r0 = "[Upload] Upload too much data, try next time: %d/%d";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0030 }
        r4 = 0;
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ Throwable -> 0x0030 }
        r1[r4] = r2;	 Catch:{ Throwable -> 0x0030 }
        r2 = 1;
        r4 = 2097152; // 0x200000 float:2.938736E-39 double:1.0361308E-317;
        r3 = java.lang.Long.valueOf(r4);	 Catch:{ Throwable -> 0x0030 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0030 }
        r4 = "over net consume: ";
        r0.<init>(r4);	 Catch:{ Throwable -> 0x0030 }
        r4 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x0030 }
        r4 = "K";
        r0 = r0.append(r4);	 Catch:{ Throwable -> 0x0030 }
        r4 = r0.toString();	 Catch:{ Throwable -> 0x0030 }
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x0086:
        r1 = "[Upload] Run upload task with cmd: %d";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0030 }
        r3 = 0;
        r4 = r11.f15755d;	 Catch:{ Throwable -> 0x0030 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0030 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17751c(r1, r2);	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.f15754c;	 Catch:{ Throwable -> 0x0030 }
        if (r1 == 0) goto L_0x00a7;
    L_0x009b:
        r1 = r11.f15757f;	 Catch:{ Throwable -> 0x0030 }
        if (r1 == 0) goto L_0x00a7;
    L_0x009f:
        r1 = r11.f15758g;	 Catch:{ Throwable -> 0x0030 }
        if (r1 == 0) goto L_0x00a7;
    L_0x00a3:
        r1 = r11.f15759h;	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x00b3;
    L_0x00a7:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "illegal access error";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x00b3:
        r1 = r11.f15758g;	 Catch:{ Throwable -> 0x0030 }
        r1 = r1.m17396c();	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x00c7;
    L_0x00bb:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "illegal local strategy";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x00c7:
        r3 = 0;
        r7 = new java.util.HashMap;	 Catch:{ Throwable -> 0x0030 }
        r7.<init>();	 Catch:{ Throwable -> 0x0030 }
        r2 = "prodId";
        r4 = r11.f15757f;	 Catch:{ Throwable -> 0x0030 }
        r4 = r4.m17335f();	 Catch:{ Throwable -> 0x0030 }
        r7.put(r2, r4);	 Catch:{ Throwable -> 0x0030 }
        r2 = "bundleId";
        r4 = r11.f15757f;	 Catch:{ Throwable -> 0x0030 }
        r4 = r4.f15288c;	 Catch:{ Throwable -> 0x0030 }
        r7.put(r2, r4);	 Catch:{ Throwable -> 0x0030 }
        r2 = "appVer";
        r4 = r11.f15757f;	 Catch:{ Throwable -> 0x0030 }
        r4 = r4.f15295j;	 Catch:{ Throwable -> 0x0030 }
        r7.put(r2, r4);	 Catch:{ Throwable -> 0x0030 }
        r2 = r11.f15766o;	 Catch:{ Throwable -> 0x0030 }
        if (r2 == 0) goto L_0x00f3;
    L_0x00ee:
        r2 = r11.f15766o;	 Catch:{ Throwable -> 0x0030 }
        r7.putAll(r2);	 Catch:{ Throwable -> 0x0030 }
    L_0x00f3:
        r2 = r11.f15770s;	 Catch:{ Throwable -> 0x0030 }
        if (r2 == 0) goto L_0x015e;
    L_0x00f7:
        r2 = "cmd";
        r4 = r11.f15755d;	 Catch:{ Throwable -> 0x0030 }
        r4 = java.lang.Integer.toString(r4);	 Catch:{ Throwable -> 0x0030 }
        r7.put(r2, r4);	 Catch:{ Throwable -> 0x0030 }
        r2 = "platformId";
        r4 = 1;
        r4 = java.lang.Byte.toString(r4);	 Catch:{ Throwable -> 0x0030 }
        r7.put(r2, r4);	 Catch:{ Throwable -> 0x0030 }
        r2 = "sdkVer";
        r4 = r11.f15757f;	 Catch:{ Throwable -> 0x0030 }
        r4.getClass();	 Catch:{ Throwable -> 0x0030 }
        r4 = "2.4.0";
        r7.put(r2, r4);	 Catch:{ Throwable -> 0x0030 }
        r2 = "strategylastUpdateTime";
        r4 = r1.f15329p;	 Catch:{ Throwable -> 0x0030 }
        r1 = java.lang.Long.toString(r4);	 Catch:{ Throwable -> 0x0030 }
        r7.put(r2, r1);	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.f15760i;	 Catch:{ Throwable -> 0x0030 }
        r1 = r1.m17729a(r7);	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x0137;
    L_0x012b:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "failed to add security info to HTTP headers";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x0137:
        r1 = 2;
        r0 = com.tencent.bugly.proguard.C4479y.m17797a(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x014a;
    L_0x013e:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "failed to zip request body";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x014a:
        r1 = r11.f15760i;	 Catch:{ Throwable -> 0x0030 }
        r0 = r1.m17730a(r0);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x015e;
    L_0x0152:
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = "failed to encrypt request body";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x015e:
        r6 = r0;
        r0 = r11.f15760i;	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.f15761j;	 Catch:{ Throwable -> 0x0030 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x0030 }
        r0.m17724a(r1, r4);	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.f15762k;	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x0172;
    L_0x016e:
        r0 = r11.f15762k;	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.f15755d;	 Catch:{ Throwable -> 0x0030 }
    L_0x0172:
        r0 = r11.f15763l;	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x017a;
    L_0x0176:
        r0 = r11.f15763l;	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.f15755d;	 Catch:{ Throwable -> 0x0030 }
    L_0x017a:
        r2 = r11.f15764m;	 Catch:{ Throwable -> 0x0030 }
        r5 = -1;
        r0 = 0;
        r1 = r0;
        r0 = r2;
    L_0x0180:
        r4 = r1 + 1;
        r2 = r11.f15752a;	 Catch:{ Throwable -> 0x0030 }
        if (r1 >= r2) goto L_0x04ac;
    L_0x0186:
        r1 = 1;
        if (r4 <= r1) goto L_0x01b1;
    L_0x0189:
        r1 = "[Upload] Failed to upload last time, wait and try(%d) again.";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0030 }
        r3 = 0;
        r8 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0030 }
        r2[r3] = r8;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17752d(r1, r2);	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.f15753b;	 Catch:{ Throwable -> 0x0030 }
        r2 = (long) r1;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4479y.m17804b(r2);	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.f15752a;	 Catch:{ Throwable -> 0x0030 }
        if (r4 != r1) goto L_0x01b1;
    L_0x01a2:
        r0 = "[Upload] Use the back-up url at the last time: %s";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0030 }
        r2 = 0;
        r3 = r11.f15765n;	 Catch:{ Throwable -> 0x0030 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.f15765n;	 Catch:{ Throwable -> 0x0030 }
    L_0x01b1:
        r1 = "[Upload] Send %d bytes";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0030 }
        r3 = 0;
        r8 = r6.length;	 Catch:{ Throwable -> 0x0030 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x0030 }
        r2[r3] = r8;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17751c(r1, r2);	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.f15770s;	 Catch:{ Throwable -> 0x0030 }
        if (r1 == 0) goto L_0x04b7;
    L_0x01c5:
        r0 = com.tencent.bugly.proguard.C4472u.m17735a(r0);	 Catch:{ Throwable -> 0x0030 }
        r2 = r0;
    L_0x01ca:
        r0 = "[Upload] Upload to %s with cmd %d (pid=%d | tid=%d).";
        r1 = 4;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0030 }
        r3 = 0;
        r1[r3] = r2;	 Catch:{ Throwable -> 0x0030 }
        r3 = 1;
        r8 = r11.f15755d;	 Catch:{ Throwable -> 0x0030 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x0030 }
        r1[r3] = r8;	 Catch:{ Throwable -> 0x0030 }
        r3 = 2;
        r8 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x0030 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x0030 }
        r1[r3] = r8;	 Catch:{ Throwable -> 0x0030 }
        r3 = 3;
        r8 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0030 }
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x0030 }
        r1[r3] = r8;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.f15759h;	 Catch:{ Throwable -> 0x0030 }
        r1 = r0.m17702a(r2, r6, r11, r7);	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x0216;
    L_0x01fc:
        r0 = "Failed to upload for no response!";
        r1 = "[Upload] Failed to upload(%d): %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0030 }
        r8 = 0;
        r9 = 1;
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Throwable -> 0x0030 }
        r3[r8] = r9;	 Catch:{ Throwable -> 0x0030 }
        r8 = 1;
        r3[r8] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17753e(r1, r3);	 Catch:{ Throwable -> 0x0030 }
        r3 = 1;
        r1 = r4;
        r0 = r2;
        goto L_0x0180;
    L_0x0216:
        r0 = r11.f15759h;	 Catch:{ Throwable -> 0x0030 }
        r3 = r0.f15723a;	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.f15770s;	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x03d8;
    L_0x021e:
        if (r3 == 0) goto L_0x0226;
    L_0x0220:
        r0 = r3.size();	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x0298;
    L_0x0226:
        r0 = "[Upload] Headers is empty.";
        r8 = 0;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r8);	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
    L_0x022f:
        if (r0 != 0) goto L_0x0300;
    L_0x0231:
        r0 = "[Upload] Headers from server is not valid, just try again (pid=%d | tid=%d).";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0030 }
        r8 = 0;
        r9 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x0030 }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Throwable -> 0x0030 }
        r1[r8] = r9;	 Catch:{ Throwable -> 0x0030 }
        r8 = 1;
        r9 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0030 }
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Throwable -> 0x0030 }
        r1[r8] = r9;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        r0 = "[Upload] Failed to upload for no status header.";
        r1 = "[Upload] Failed to upload(%d): %s";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0030 }
        r9 = 0;
        r10 = 1;
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Throwable -> 0x0030 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0030 }
        r9 = 1;
        r8[r9] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17753e(r1, r8);	 Catch:{ Throwable -> 0x0030 }
        if (r3 == 0) goto L_0x02f3;
    L_0x0266:
        r0 = r3.entrySet();	 Catch:{ Throwable -> 0x0030 }
        r1 = r0.iterator();	 Catch:{ Throwable -> 0x0030 }
    L_0x026e:
        r0 = r1.hasNext();	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x02f3;
    L_0x0274:
        r0 = r1.next();	 Catch:{ Throwable -> 0x0030 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ Throwable -> 0x0030 }
        r3 = "[key]: %s, [value]: %s";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0030 }
        r9 = 0;
        r10 = r0.getKey();	 Catch:{ Throwable -> 0x0030 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0030 }
        r9 = 1;
        r0 = r0.getValue();	 Catch:{ Throwable -> 0x0030 }
        r8[r9] = r0;	 Catch:{ Throwable -> 0x0030 }
        r0 = java.lang.String.format(r3, r8);	 Catch:{ Throwable -> 0x0030 }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r3);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x026e;
    L_0x0298:
        r0 = "status";
        r0 = r3.containsKey(r0);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x02af;
    L_0x02a0:
        r0 = "[Upload] Headers does not contain %s";
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0030 }
        r9 = 0;
        r10 = "status";
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r8);	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
        goto L_0x022f;
    L_0x02af:
        r0 = "Bugly-Version";
        r0 = r3.containsKey(r0);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x02c7;
    L_0x02b7:
        r0 = "[Upload] Headers does not contain %s";
        r8 = 1;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0030 }
        r9 = 0;
        r10 = "Bugly-Version";
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r8);	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
        goto L_0x022f;
    L_0x02c7:
        r0 = "Bugly-Version";
        r0 = r3.get(r0);	 Catch:{ Throwable -> 0x0030 }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x0030 }
        r8 = "bugly";
        r8 = r0.contains(r8);	 Catch:{ Throwable -> 0x0030 }
        if (r8 != 0) goto L_0x02e5;
    L_0x02d7:
        r8 = "[Upload] Bugly version is not valid: %s";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ Throwable -> 0x0030 }
        r10 = 0;
        r9[r10] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17752d(r8, r9);	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
        goto L_0x022f;
    L_0x02e5:
        r8 = "[Upload] Bugly version from headers is: %s";
        r9 = 1;
        r9 = new java.lang.Object[r9];	 Catch:{ Throwable -> 0x0030 }
        r10 = 0;
        r9[r10] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17751c(r8, r9);	 Catch:{ Throwable -> 0x0030 }
        r0 = 1;
        goto L_0x022f;
    L_0x02f3:
        r0 = "[Upload] Failed to upload for no status header.";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        r3 = 1;
        r1 = r4;
        r0 = r2;
        goto L_0x0180;
    L_0x0300:
        r0 = "status";
        r0 = r3.get(r0);	 Catch:{ Throwable -> 0x0394 }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x0394 }
        r5 = java.lang.Integer.parseInt(r0);	 Catch:{ Throwable -> 0x0394 }
        r0 = "[Upload] Status from server is %d (pid=%d | tid=%d).";
        r8 = 3;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x0394 }
        r9 = 0;
        r10 = java.lang.Integer.valueOf(r5);	 Catch:{ Throwable -> 0x0394 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0394 }
        r9 = 1;
        r10 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x0394 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Throwable -> 0x0394 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0394 }
        r9 = 2;
        r10 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0394 }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ Throwable -> 0x0394 }
        r8[r9] = r10;	 Catch:{ Throwable -> 0x0394 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r8);	 Catch:{ Throwable -> 0x0394 }
        if (r5 == 0) goto L_0x03d8;
    L_0x0333:
        r0 = 2;
        if (r5 != r0) goto L_0x03c0;
    L_0x0336:
        r0 = r11.f15768q;	 Catch:{ Throwable -> 0x0030 }
        r2 = r11.f15769r;	 Catch:{ Throwable -> 0x0030 }
        r0 = r0 + r2;
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 <= 0) goto L_0x0356;
    L_0x0341:
        r0 = r11.f15760i;	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.f15771t;	 Catch:{ Throwable -> 0x0030 }
        r0 = r0.m17722a(r1);	 Catch:{ Throwable -> 0x0030 }
        r2 = r11.f15768q;	 Catch:{ Throwable -> 0x0030 }
        r0 = r0 + r2;
        r2 = r11.f15769r;	 Catch:{ Throwable -> 0x0030 }
        r0 = r0 + r2;
        r2 = r11.f15760i;	 Catch:{ Throwable -> 0x0030 }
        r3 = r11.f15771t;	 Catch:{ Throwable -> 0x0030 }
        r2.m17728a(r0, r3);	 Catch:{ Throwable -> 0x0030 }
    L_0x0356:
        r0 = r11.f15760i;	 Catch:{ Throwable -> 0x0030 }
        r1 = 0;
        r0.m17727a(r5, r1);	 Catch:{ Throwable -> 0x0030 }
        r0 = "[Upload] Session ID is invalid, will try again immediately (pid=%d | tid=%d).";
        r1 = 2;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x0030 }
        r2 = 0;
        r3 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x0030 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x0030 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0030 }
        r2 = 1;
        r3 = android.os.Process.myTid();	 Catch:{ Throwable -> 0x0030 }
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x0030 }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17747a(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.f15760i;	 Catch:{ Throwable -> 0x0030 }
        r1 = r11.f15761j;	 Catch:{ Throwable -> 0x0030 }
        r2 = r11.f15755d;	 Catch:{ Throwable -> 0x0030 }
        r3 = r11.f15756e;	 Catch:{ Throwable -> 0x0030 }
        r4 = r11.f15764m;	 Catch:{ Throwable -> 0x0030 }
        r5 = r11.f15765n;	 Catch:{ Throwable -> 0x0030 }
        r6 = r11.f15762k;	 Catch:{ Throwable -> 0x0030 }
        r7 = r11.f15752a;	 Catch:{ Throwable -> 0x0030 }
        r8 = r11.f15753b;	 Catch:{ Throwable -> 0x0030 }
        r9 = 1;
        r10 = r11.f15766o;	 Catch:{ Throwable -> 0x0030 }
        r0.m17723a(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x0394:
        r0 = move-exception;
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0030 }
        r1 = "[Upload] Failed to upload for format of status header is invalid: ";
        r0.<init>(r1);	 Catch:{ Throwable -> 0x0030 }
        r1 = java.lang.Integer.toString(r5);	 Catch:{ Throwable -> 0x0030 }
        r0 = r0.append(r1);	 Catch:{ Throwable -> 0x0030 }
        r0 = r0.toString();	 Catch:{ Throwable -> 0x0030 }
        r1 = "[Upload] Failed to upload(%d): %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0030 }
        r8 = 0;
        r9 = 1;
        r9 = java.lang.Integer.valueOf(r9);	 Catch:{ Throwable -> 0x0030 }
        r3[r8] = r9;	 Catch:{ Throwable -> 0x0030 }
        r8 = 1;
        r3[r8] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17753e(r1, r3);	 Catch:{ Throwable -> 0x0030 }
        r3 = 1;
        r1 = r4;
        r0 = r2;
        goto L_0x0180;
    L_0x03c0:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r0 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x0030 }
        r4 = "status of server is ";
        r0.<init>(r4);	 Catch:{ Throwable -> 0x0030 }
        r0 = r0.append(r5);	 Catch:{ Throwable -> 0x0030 }
        r4 = r0.toString();	 Catch:{ Throwable -> 0x0030 }
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x03d8:
        r0 = "[Upload] Received %d bytes";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0030 }
        r4 = 0;
        r6 = r1.length;	 Catch:{ Throwable -> 0x0030 }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ Throwable -> 0x0030 }
        r2[r4] = r6;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r2);	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.f15770s;	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x044d;
    L_0x03ec:
        r0 = r1.length;	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x0426;
    L_0x03ef:
        r0 = r3.entrySet();	 Catch:{ Throwable -> 0x0030 }
        r1 = r0.iterator();	 Catch:{ Throwable -> 0x0030 }
    L_0x03f7:
        r0 = r1.hasNext();	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x041a;
    L_0x03fd:
        r0 = r1.next();	 Catch:{ Throwable -> 0x0030 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ Throwable -> 0x0030 }
        r2 = "[Upload] HTTP headers from server: key = %s, value = %s";
        r3 = 2;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x0030 }
        r4 = 0;
        r5 = r0.getKey();	 Catch:{ Throwable -> 0x0030 }
        r3[r4] = r5;	 Catch:{ Throwable -> 0x0030 }
        r4 = 1;
        r0 = r0.getValue();	 Catch:{ Throwable -> 0x0030 }
        r3[r4] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17751c(r2, r3);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x03f7;
    L_0x041a:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r4 = "response data from server is empty";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x0426:
        r0 = r11.f15760i;	 Catch:{ Throwable -> 0x0030 }
        r0 = r0.m17734b(r1);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x043a;
    L_0x042e:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r4 = "failed to decrypt response from server";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x043a:
        r1 = 2;
        r0 = com.tencent.bugly.proguard.C4479y.m17809b(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x044e;
    L_0x0441:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r4 = "failed unzip(Gzip) response from server";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x044d:
        r0 = r1;
    L_0x044e:
        r1 = r11.f15770s;	 Catch:{ Throwable -> 0x0030 }
        r1 = com.tencent.bugly.proguard.C4446a.m17524a(r0, r1);	 Catch:{ Throwable -> 0x0030 }
        if (r1 != 0) goto L_0x0462;
    L_0x0456:
        r1 = 0;
        r2 = 0;
        r3 = 1;
        r4 = "failed to decode response package";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x0462:
        r0 = r11.f15770s;	 Catch:{ Throwable -> 0x0030 }
        if (r0 == 0) goto L_0x046b;
    L_0x0466:
        r0 = r11.f15760i;	 Catch:{ Throwable -> 0x0030 }
        r0.m17727a(r5, r1);	 Catch:{ Throwable -> 0x0030 }
    L_0x046b:
        r2 = "[Upload] Response cmd is: %d, length of sBuffer is: %d";
        r0 = 2;
        r3 = new java.lang.Object[r0];	 Catch:{ Throwable -> 0x0030 }
        r0 = 0;
        r4 = r1.f15607b;	 Catch:{ Throwable -> 0x0030 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0030 }
        r3[r0] = r4;	 Catch:{ Throwable -> 0x0030 }
        r4 = 1;
        r0 = r1.f15608c;	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x049d;
    L_0x047e:
        r0 = 0;
    L_0x047f:
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Throwable -> 0x0030 }
        r3[r4] = r0;	 Catch:{ Throwable -> 0x0030 }
        com.tencent.bugly.proguard.C4475w.m17751c(r2, r3);	 Catch:{ Throwable -> 0x0030 }
        r0 = r11.f15757f;	 Catch:{ Throwable -> 0x0030 }
        r2 = r11.f15758g;	 Catch:{ Throwable -> 0x0030 }
        r0 = com.tencent.bugly.proguard.C4472u.m17737a(r1, r0, r2);	 Catch:{ Throwable -> 0x0030 }
        if (r0 != 0) goto L_0x04a1;
    L_0x0492:
        r2 = 0;
        r3 = 2;
        r4 = "failed to process response package";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x049d:
        r0 = r1.f15608c;	 Catch:{ Throwable -> 0x0030 }
        r0 = r0.length;	 Catch:{ Throwable -> 0x0030 }
        goto L_0x047f;
    L_0x04a1:
        r2 = 1;
        r3 = 2;
        r4 = "successfully uploaded";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x04ac:
        r1 = 0;
        r2 = 0;
        r4 = "failed after many attempts";
        r5 = 0;
        r0 = r11;
        r0.m17736a(r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x0030 }
        goto L_0x001f;
    L_0x04b7:
        r2 = r0;
        goto L_0x01ca;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.u.run():void");
    }

    /* renamed from: a */
    public final void m17738a(long j) {
        this.f15767p++;
        this.f15768q += j;
    }

    /* renamed from: b */
    public final void m17739b(long j) {
        this.f15769r += j;
    }

    /* renamed from: a */
    private static String m17735a(String str) {
        if (!C4479y.m17792a(str)) {
            try {
                str = String.format("%s?aid=%s", new Object[]{str, UUID.randomUUID().toString()});
            } catch (Throwable th) {
                C4475w.m17748a(th);
            }
        }
        return str;
    }
}
