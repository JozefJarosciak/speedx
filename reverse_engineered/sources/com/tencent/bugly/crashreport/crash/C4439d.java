package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import com.tencent.bugly.proguard.C4474v;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4479y;
import java.util.Map;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.d */
public final class C4439d {
    /* renamed from: a */
    private static C4439d f15479a = null;
    /* renamed from: b */
    private C4421a f15480b;
    /* renamed from: c */
    private C4417a f15481c;
    /* renamed from: d */
    private C4433b f15482d;
    /* renamed from: e */
    private Context f15483e;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.d$1 */
    class C44371 implements Runnable {
        /* renamed from: a */
        private /* synthetic */ C4439d f15472a;

        C44371(C4439d c4439d) {
            this.f15472a = c4439d;
        }

        public final void run() {
            C4439d.m17465a(this.f15472a);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m17465a(C4439d c4439d) {
        C4475w.m17751c("[ExtraCrashManager] Trying to notify Bugly agents.", new Object[0]);
        try {
            Class cls = Class.forName("com.tencent.bugly.agent.GameAgent");
            Object obj = "com.tencent.bugly";
            c4439d.f15481c.getClass();
            String str = "";
            if (!"".equals(str)) {
                obj = obj + "." + str;
            }
            C4479y.m17789a(cls, "sdkPackageName", obj, null);
            C4475w.m17751c("[ExtraCrashManager] Bugly game agent has been notified.", new Object[0]);
        } catch (Throwable th) {
            C4475w.m17747a("[ExtraCrashManager] no game agent", new Object[0]);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    static /* synthetic */ void m17466a(com.tencent.bugly.crashreport.crash.C4439d r8, java.lang.Thread r9, int r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.util.Map r14) {
        /*
        r2 = 1;
        r6 = 0;
        switch(r10) {
            case 4: goto L_0x00a3;
            case 5: goto L_0x0013;
            case 6: goto L_0x0013;
            case 7: goto L_0x0005;
            case 8: goto L_0x00a7;
            default: goto L_0x0005;
        };
    L_0x0005:
        r0 = "[ExtraCrashManager] Unknown extra crash type: %d";
        r1 = new java.lang.Object[r2];
        r2 = java.lang.Integer.valueOf(r10);
        r1[r6] = r2;
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r1);
    L_0x0012:
        return;
    L_0x0013:
        r0 = "Cocos";
    L_0x0015:
        r1 = "[ExtraCrashManager] %s Crash Happen";
        r2 = new java.lang.Object[r2];
        r2[r6] = r0;
        com.tencent.bugly.proguard.C4475w.m17753e(r1, r2);
        r1 = r8.f15480b;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17395b();	 Catch:{ Throwable -> 0x028d }
        if (r1 != 0) goto L_0x0042;
    L_0x0026:
        r1 = "waiting for remote sync";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x028d }
        com.tencent.bugly.proguard.C4475w.m17753e(r1, r2);	 Catch:{ Throwable -> 0x028d }
        r1 = r6;
    L_0x002f:
        r2 = r8.f15480b;	 Catch:{ Throwable -> 0x028d }
        r2 = r2.m17395b();	 Catch:{ Throwable -> 0x028d }
        if (r2 != 0) goto L_0x0042;
    L_0x0037:
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        com.tencent.bugly.proguard.C4479y.m17804b(r2);	 Catch:{ Throwable -> 0x028d }
        r1 = r1 + 500;
        r2 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        if (r1 < r2) goto L_0x002f;
    L_0x0042:
        r1 = r8.f15480b;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17395b();	 Catch:{ Throwable -> 0x028d }
        if (r1 != 0) goto L_0x0052;
    L_0x004a:
        r1 = "[ExtraCrashManager] There is no remote strategy, but still store it.";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x028d }
        com.tencent.bugly.proguard.C4475w.m17752d(r1, r2);	 Catch:{ Throwable -> 0x028d }
    L_0x0052:
        r1 = r8.f15480b;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17396c();	 Catch:{ Throwable -> 0x028d }
        r2 = r1.f15320g;	 Catch:{ Throwable -> 0x028d }
        if (r2 != 0) goto L_0x00ab;
    L_0x005c:
        r2 = r8.f15480b;	 Catch:{ Throwable -> 0x028d }
        r2 = r2.m17395b();	 Catch:{ Throwable -> 0x028d }
        if (r2 == 0) goto L_0x00ab;
    L_0x0064:
        r1 = "[ExtraCrashManager] Crash report was closed by remote , will not upload to Bugly , print local for helpful!";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x028d }
        com.tencent.bugly.proguard.C4475w.m17753e(r1, r2);	 Catch:{ Throwable -> 0x028d }
        r1 = com.tencent.bugly.proguard.C4479y.m17777a();	 Catch:{ Throwable -> 0x028d }
        r2 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r2 = r2.f15289d;	 Catch:{ Throwable -> 0x028d }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x028d }
        r3.<init>();	 Catch:{ Throwable -> 0x028d }
        r3 = r3.append(r11);	 Catch:{ Throwable -> 0x028d }
        r4 = "\n";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x028d }
        r3 = r3.append(r12);	 Catch:{ Throwable -> 0x028d }
        r4 = "\n";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x028d }
        r3 = r3.append(r13);	 Catch:{ Throwable -> 0x028d }
        r4 = r3.toString();	 Catch:{ Throwable -> 0x028d }
        r5 = 0;
        r3 = r9;
        com.tencent.bugly.crashreport.crash.C4433b.m17429a(r0, r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x028d }
        r0 = "[ExtraCrashManager] Successfully handled.";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);
        goto L_0x0012;
    L_0x00a3:
        r0 = "Unity";
        goto L_0x0015;
    L_0x00a7:
        r0 = "H5";
        goto L_0x0015;
    L_0x00ab:
        switch(r10) {
            case 5: goto L_0x00c6;
            case 6: goto L_0x00c6;
            case 7: goto L_0x00ae;
            case 8: goto L_0x00de;
            default: goto L_0x00ae;
        };
    L_0x00ae:
        r0 = "[ExtraCrashManager] Unknown extra crash type: %d";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x028d }
        r2 = 0;
        r3 = java.lang.Integer.valueOf(r10);	 Catch:{ Throwable -> 0x028d }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x028d }
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r1);	 Catch:{ Throwable -> 0x028d }
        r0 = "[ExtraCrashManager] Successfully handled.";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);
        goto L_0x0012;
    L_0x00c6:
        r1 = r1.f15325l;	 Catch:{ Throwable -> 0x028d }
        if (r1 != 0) goto L_0x00f6;
    L_0x00ca:
        r1 = "[ExtraCrashManager] %s report is disabled.";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x028d }
        r3 = 0;
        r2[r3] = r0;	 Catch:{ Throwable -> 0x028d }
        com.tencent.bugly.proguard.C4475w.m17753e(r1, r2);	 Catch:{ Throwable -> 0x028d }
        r0 = "[ExtraCrashManager] Successfully handled.";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);
        goto L_0x0012;
    L_0x00de:
        r1 = r1.f15326m;	 Catch:{ Throwable -> 0x028d }
        if (r1 != 0) goto L_0x00f6;
    L_0x00e2:
        r1 = "[ExtraCrashManager] %s report is disabled.";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x028d }
        r3 = 0;
        r2[r3] = r0;	 Catch:{ Throwable -> 0x028d }
        com.tencent.bugly.proguard.C4475w.m17753e(r1, r2);	 Catch:{ Throwable -> 0x028d }
        r0 = "[ExtraCrashManager] Successfully handled.";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);
        goto L_0x0012;
    L_0x00f6:
        r1 = 8;
        if (r10 != r1) goto L_0x00fb;
    L_0x00fa:
        r10 = 5;
    L_0x00fb:
        r5 = new com.tencent.bugly.crashreport.crash.CrashDetailBean;	 Catch:{ Throwable -> 0x028d }
        r5.<init>();	 Catch:{ Throwable -> 0x028d }
        r2 = com.tencent.bugly.crashreport.common.info.C4418b.m17371g();	 Catch:{ Throwable -> 0x028d }
        r5.f15353B = r2;	 Catch:{ Throwable -> 0x028d }
        r2 = com.tencent.bugly.crashreport.common.info.C4418b.m17367e();	 Catch:{ Throwable -> 0x028d }
        r5.f15354C = r2;	 Catch:{ Throwable -> 0x028d }
        r2 = com.tencent.bugly.crashreport.common.info.C4418b.m17375i();	 Catch:{ Throwable -> 0x028d }
        r5.f15355D = r2;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r2 = r1.m17347p();	 Catch:{ Throwable -> 0x028d }
        r5.f15356E = r2;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r2 = r1.m17346o();	 Catch:{ Throwable -> 0x028d }
        r5.f15357F = r2;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r2 = r1.m17348q();	 Catch:{ Throwable -> 0x028d }
        r5.f15358G = r2;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15483e;	 Catch:{ Throwable -> 0x028d }
        r2 = com.tencent.bugly.crashreport.crash.C4436c.f15453d;	 Catch:{ Throwable -> 0x028d }
        r3 = 0;
        r1 = com.tencent.bugly.proguard.C4479y.m17779a(r1, r2, r3);	 Catch:{ Throwable -> 0x028d }
        r5.f15396w = r1;	 Catch:{ Throwable -> 0x028d }
        r5.f15375b = r10;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17339h();	 Catch:{ Throwable -> 0x028d }
        r5.f15378e = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.f15295j;	 Catch:{ Throwable -> 0x028d }
        r5.f15379f = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17354w();	 Catch:{ Throwable -> 0x028d }
        r5.f15380g = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17337g();	 Catch:{ Throwable -> 0x028d }
        r5.f15386m = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x028d }
        r1.<init>();	 Catch:{ Throwable -> 0x028d }
        r1 = r1.append(r11);	 Catch:{ Throwable -> 0x028d }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x028d }
        r5.f15387n = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x028d }
        r1.<init>();	 Catch:{ Throwable -> 0x028d }
        r1 = r1.append(r12);	 Catch:{ Throwable -> 0x028d }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x028d }
        r5.f15388o = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = "";
        if (r13 == 0) goto L_0x0240;
    L_0x0177:
        r2 = "\n";
        r2 = r13.split(r2);	 Catch:{ Throwable -> 0x028d }
        r3 = r2.length;	 Catch:{ Throwable -> 0x028d }
        if (r3 <= 0) goto L_0x0183;
    L_0x0180:
        r1 = 0;
        r1 = r2[r1];	 Catch:{ Throwable -> 0x028d }
    L_0x0183:
        r2 = r1;
        r1 = r13;
    L_0x0185:
        r5.f15389p = r2;	 Catch:{ Throwable -> 0x028d }
        r5.f15390q = r1;	 Catch:{ Throwable -> 0x028d }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x028d }
        r5.f15391r = r2;	 Catch:{ Throwable -> 0x028d }
        r1 = r5.f15390q;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.getBytes();	 Catch:{ Throwable -> 0x028d }
        r1 = com.tencent.bugly.proguard.C4479y.m17802b(r1);	 Catch:{ Throwable -> 0x028d }
        r5.f15394u = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = com.tencent.bugly.crashreport.crash.C4436c.f15454e;	 Catch:{ Throwable -> 0x028d }
        r2 = 0;
        r1 = com.tencent.bugly.proguard.C4479y.m17786a(r1, r2);	 Catch:{ Throwable -> 0x028d }
        r5.f15398y = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.f15289d;	 Catch:{ Throwable -> 0x028d }
        r5.f15399z = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x028d }
        r1.<init>();	 Catch:{ Throwable -> 0x028d }
        r2 = r9.getName();	 Catch:{ Throwable -> 0x028d }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x028d }
        r2 = "(";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x028d }
        r2 = r9.getId();	 Catch:{ Throwable -> 0x028d }
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x028d }
        r2 = ")";
        r1 = r1.append(r2);	 Catch:{ Throwable -> 0x028d }
        r1 = r1.toString();	 Catch:{ Throwable -> 0x028d }
        r5.f15352A = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17356y();	 Catch:{ Throwable -> 0x028d }
        r5.f15359H = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17353v();	 Catch:{ Throwable -> 0x028d }
        r5.f15381h = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r2 = r1.f15286a;	 Catch:{ Throwable -> 0x028d }
        r5.f15363L = r2;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17325a();	 Catch:{ Throwable -> 0x028d }
        r5.f15364M = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17311F();	 Catch:{ Throwable -> 0x028d }
        r5.f15366O = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17312G();	 Catch:{ Throwable -> 0x028d }
        r5.f15367P = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17357z();	 Catch:{ Throwable -> 0x028d }
        r5.f15368Q = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r1 = r1.m17310E();	 Catch:{ Throwable -> 0x028d }
        r5.f15369R = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r8.f15482d;	 Catch:{ Throwable -> 0x028d }
        r1.m17442b(r5);	 Catch:{ Throwable -> 0x028d }
        r1 = 0;
        r1 = com.tencent.bugly.proguard.C4478x.m17766a(r1);	 Catch:{ Throwable -> 0x028d }
        r5.f15397x = r1;	 Catch:{ Throwable -> 0x028d }
        r1 = r5.f15365N;	 Catch:{ Throwable -> 0x028d }
        if (r1 != 0) goto L_0x0226;
    L_0x021f:
        r1 = new java.util.LinkedHashMap;	 Catch:{ Throwable -> 0x028d }
        r1.<init>();	 Catch:{ Throwable -> 0x028d }
        r5.f15365N = r1;	 Catch:{ Throwable -> 0x028d }
    L_0x0226:
        if (r14 == 0) goto L_0x022d;
    L_0x0228:
        r1 = r5.f15365N;	 Catch:{ Throwable -> 0x028d }
        r1.putAll(r14);	 Catch:{ Throwable -> 0x028d }
    L_0x022d:
        if (r5 != 0) goto L_0x0247;
    L_0x022f:
        r0 = "[ExtraCrashManager] Failed to package crash data.";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x028d }
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);	 Catch:{ Throwable -> 0x028d }
        r0 = "[ExtraCrashManager] Successfully handled.";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);
        goto L_0x0012;
    L_0x0240:
        r2 = "";
        r7 = r2;
        r2 = r1;
        r1 = r7;
        goto L_0x0185;
    L_0x0247:
        r1 = com.tencent.bugly.proguard.C4479y.m17777a();	 Catch:{ Throwable -> 0x028d }
        r2 = r8.f15481c;	 Catch:{ Throwable -> 0x028d }
        r2 = r2.f15289d;	 Catch:{ Throwable -> 0x028d }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x028d }
        r3.<init>();	 Catch:{ Throwable -> 0x028d }
        r3 = r3.append(r11);	 Catch:{ Throwable -> 0x028d }
        r4 = "\n";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x028d }
        r3 = r3.append(r12);	 Catch:{ Throwable -> 0x028d }
        r4 = "\n";
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x028d }
        r3 = r3.append(r13);	 Catch:{ Throwable -> 0x028d }
        r4 = r3.toString();	 Catch:{ Throwable -> 0x028d }
        r3 = r9;
        com.tencent.bugly.crashreport.crash.C4433b.m17429a(r0, r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x028d }
        r0 = r8.f15482d;	 Catch:{ Throwable -> 0x028d }
        r0 = r0.m17440a(r5);	 Catch:{ Throwable -> 0x028d }
        if (r0 != 0) goto L_0x0284;
    L_0x027c:
        r0 = r8.f15482d;	 Catch:{ Throwable -> 0x028d }
        r2 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r1 = 0;
        r0.m17438a(r5, r2, r1);	 Catch:{ Throwable -> 0x028d }
    L_0x0284:
        r0 = "[ExtraCrashManager] Successfully handled.";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);
        goto L_0x0012;
    L_0x028d:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.C4475w.m17748a(r0);	 Catch:{ all -> 0x02a0 }
        if (r1 != 0) goto L_0x0297;
    L_0x0294:
        r0.printStackTrace();	 Catch:{ all -> 0x02a0 }
    L_0x0297:
        r0 = "[ExtraCrashManager] Successfully handled.";
        r1 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);
        goto L_0x0012;
    L_0x02a0:
        r0 = move-exception;
        r1 = "[ExtraCrashManager] Successfully handled.";
        r2 = new java.lang.Object[r6];
        com.tencent.bugly.proguard.C4475w.m17753e(r1, r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.d.a(com.tencent.bugly.crashreport.crash.d, java.lang.Thread, int, java.lang.String, java.lang.String, java.lang.String, java.util.Map):void");
    }

    private C4439d(Context context) {
        C4436c a = C4436c.m17444a();
        if (a != null) {
            this.f15480b = C4421a.m17388a();
            this.f15481c = C4417a.m17303a(context);
            this.f15482d = a.f15464n;
            this.f15483e = context;
            C4474v.m17740a().m17741a(new C44371(this));
        }
    }

    /* renamed from: a */
    public static C4439d m17464a(Context context) {
        if (f15479a == null) {
            f15479a = new C4439d(context);
        }
        return f15479a;
    }

    /* renamed from: a */
    public static void m17467a(Thread thread, int i, String str, String str2, String str3, Map<String, String> map) {
        final Thread thread2 = thread;
        final int i2 = i;
        final String str4 = str;
        final String str5 = str2;
        final String str6 = str3;
        final Map<String, String> map2 = map;
        C4474v.m17740a().m17741a(new Runnable() {
            public final void run() {
                try {
                    if (C4439d.f15479a == null) {
                        C4475w.m17753e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
                    } else {
                        C4439d.m17466a(C4439d.f15479a, thread2, i2, str4, str5, str6, map2);
                    }
                } catch (Throwable th) {
                    if (!C4475w.m17750b(th)) {
                        th.printStackTrace();
                    }
                    C4475w.m17753e("[ExtraCrashManager] Crash error %s %s %s", str4, str5, str6);
                }
            }
        });
    }
}
