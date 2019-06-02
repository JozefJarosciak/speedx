package com.tencent.bugly.crashreport.crash.anr;

import android.content.Context;
import android.os.FileObserver;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.info.C4418b;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.C4433b;
import com.tencent.bugly.crashreport.crash.C4436c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.C4427a;
import com.tencent.bugly.proguard.C4474v;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4478x;
import com.tencent.bugly.proguard.C4479y;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.anr.b */
public final class C4431b {
    /* renamed from: a */
    private AtomicInteger f15423a = new AtomicInteger(0);
    /* renamed from: b */
    private long f15424b = -1;
    /* renamed from: c */
    private final Context f15425c;
    /* renamed from: d */
    private final C4417a f15426d;
    /* renamed from: e */
    private final C4474v f15427e;
    /* renamed from: f */
    private final C4421a f15428f;
    /* renamed from: g */
    private final String f15429g;
    /* renamed from: h */
    private final C4433b f15430h;
    /* renamed from: i */
    private FileObserver f15431i;
    /* renamed from: j */
    private boolean f15432j = true;

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.anr.b$1 */
    class C44291 extends FileObserver {
        /* renamed from: a */
        private /* synthetic */ C4431b f15421a;

        C44291(C4431b c4431b, String str, int i) {
            this.f15421a = c4431b;
            super(str, 8);
        }

        public final void onEvent(int i, String str) {
            if (str != null) {
                String str2 = "/data/anr/" + str;
                if (str2.contains("trace")) {
                    this.f15421a.m17419a(str2);
                    return;
                }
                C4475w.m17752d("not anr file %s", str2);
            }
        }
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.anr.b$2 */
    class C44302 implements Runnable {
        /* renamed from: a */
        private /* synthetic */ C4431b f15422a;

        C44302(C4431b c4431b) {
            this.f15422a = c4431b;
        }

        public final void run() {
            this.f15422a.m17422b();
        }
    }

    public C4431b(Context context, C4421a c4421a, C4417a c4417a, C4474v c4474v, C4433b c4433b) {
        this.f15425c = C4479y.m17772a(context);
        this.f15429g = context.getDir("bugly", 0).getAbsolutePath();
        this.f15426d = c4417a;
        this.f15427e = c4474v;
        this.f15428f = c4421a;
        this.f15430h = c4433b;
    }

    /* renamed from: a */
    private CrashDetailBean m17410a(C4428a c4428a) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.f15353B = C4418b.m17371g();
            crashDetailBean.f15354C = C4418b.m17367e();
            crashDetailBean.f15355D = C4418b.m17375i();
            crashDetailBean.f15356E = this.f15426d.m17347p();
            crashDetailBean.f15357F = this.f15426d.m17346o();
            crashDetailBean.f15358G = this.f15426d.m17348q();
            crashDetailBean.f15396w = C4479y.m17779a(this.f15425c, C4436c.f15453d, null);
            crashDetailBean.f15397x = C4478x.m17766a(true);
            crashDetailBean.f15375b = 3;
            crashDetailBean.f15378e = this.f15426d.m17339h();
            crashDetailBean.f15379f = this.f15426d.f15295j;
            crashDetailBean.f15380g = this.f15426d.m17354w();
            crashDetailBean.f15386m = this.f15426d.m17337g();
            crashDetailBean.f15387n = "ANR_EXCEPTION";
            crashDetailBean.f15388o = c4428a.f15419f;
            crashDetailBean.f15390q = c4428a.f15420g;
            crashDetailBean.f15365N = new HashMap();
            crashDetailBean.f15365N.put("BUGLY_CR_01", c4428a.f15418e);
            int i = -1;
            if (crashDetailBean.f15390q != null) {
                i = crashDetailBean.f15390q.indexOf("\n");
            }
            crashDetailBean.f15389p = i > 0 ? crashDetailBean.f15390q.substring(0, i) : "GET_FAIL";
            crashDetailBean.f15391r = c4428a.f15416c;
            if (crashDetailBean.f15390q != null) {
                crashDetailBean.f15394u = C4479y.m17802b(crashDetailBean.f15390q.getBytes());
            }
            crashDetailBean.f15398y = c4428a.f15415b;
            crashDetailBean.f15399z = this.f15426d.f15289d;
            crashDetailBean.f15352A = "main(1)";
            crashDetailBean.f15359H = this.f15426d.m17356y();
            crashDetailBean.f15381h = this.f15426d.m17353v();
            crashDetailBean.f15382i = this.f15426d.m17314I();
            crashDetailBean.f15395v = c4428a.f15417d;
            crashDetailBean.f15362K = this.f15426d.f15299n;
            crashDetailBean.f15363L = this.f15426d.f15286a;
            crashDetailBean.f15364M = this.f15426d.m17325a();
            crashDetailBean.f15366O = this.f15426d.m17311F();
            crashDetailBean.f15367P = this.f15426d.m17312G();
            crashDetailBean.f15368Q = this.f15426d.m17357z();
            crashDetailBean.f15369R = this.f15426d.m17310E();
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    /* renamed from: a */
    private static boolean m17411a(String str, String str2, String str3) {
        Throwable e;
        C4427a readTargetDumpInfo = TraceFileHelper.readTargetDumpInfo(str3, str, true);
        if (readTargetDumpInfo == null || readTargetDumpInfo.f15413d == null || readTargetDumpInfo.f15413d.size() <= 0) {
            C4475w.m17753e("not found trace dump for %s", str3);
            return false;
        }
        File file = new File(str2);
        try {
            if (!file.exists()) {
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                file.createNewFile();
            }
            if (file.exists() && file.canWrite()) {
                BufferedWriter bufferedWriter = null;
                BufferedWriter bufferedWriter2;
                try {
                    bufferedWriter2 = new BufferedWriter(new FileWriter(file, false));
                    try {
                        String[] strArr = (String[]) readTargetDumpInfo.f15413d.get("main");
                        if (strArr != null && strArr.length >= 3) {
                            String str4 = strArr[0];
                            bufferedWriter2.write("\"main\" tid=" + strArr[2] + " :\n" + str4 + "\n" + strArr[1] + "\n\n");
                            bufferedWriter2.flush();
                        }
                        for (Entry entry : readTargetDumpInfo.f15413d.entrySet()) {
                            if (!(((String) entry.getKey()).equals("main") || entry.getValue() == null || ((String[]) entry.getValue()).length < 3)) {
                                String str5 = ((String[]) entry.getValue())[0];
                                bufferedWriter2.write("\"" + ((String) entry.getKey()) + "\" tid=" + ((String[]) entry.getValue())[2] + " :\n" + str5 + "\n" + ((String[]) entry.getValue())[1] + "\n\n");
                                bufferedWriter2.flush();
                            }
                        }
                        try {
                            bufferedWriter2.close();
                        } catch (Throwable e2) {
                            if (!C4475w.m17748a(e2)) {
                                e2.printStackTrace();
                            }
                        }
                        return true;
                    } catch (IOException e3) {
                        e2 = e3;
                        bufferedWriter = bufferedWriter2;
                        try {
                            if (!C4475w.m17748a(e2)) {
                                e2.printStackTrace();
                            }
                            C4475w.m17753e("dump trace fail %s", e2.getClass().getName() + ":" + e2.getMessage());
                            if (bufferedWriter != null) {
                                try {
                                    bufferedWriter.close();
                                } catch (Throwable e22) {
                                    if (!C4475w.m17748a(e22)) {
                                        e22.printStackTrace();
                                    }
                                }
                            }
                            return false;
                        } catch (Throwable th) {
                            e22 = th;
                            bufferedWriter2 = bufferedWriter;
                            if (bufferedWriter2 != null) {
                                try {
                                    bufferedWriter2.close();
                                } catch (Throwable e4) {
                                    if (!C4475w.m17748a(e4)) {
                                        e4.printStackTrace();
                                    }
                                }
                            }
                            throw e22;
                        }
                    } catch (Throwable th2) {
                        e22 = th2;
                        if (bufferedWriter2 != null) {
                            bufferedWriter2.close();
                        }
                        throw e22;
                    }
                } catch (IOException e5) {
                    e22 = e5;
                    if (C4475w.m17748a(e22)) {
                        e22.printStackTrace();
                    }
                    C4475w.m17753e("dump trace fail %s", e22.getClass().getName() + ":" + e22.getMessage());
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    return false;
                } catch (Throwable th3) {
                    e22 = th3;
                    bufferedWriter2 = null;
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    throw e22;
                }
            }
            C4475w.m17753e("backup file create fail %s", str2);
            return false;
        } catch (Throwable e222) {
            if (!C4475w.m17748a(e222)) {
                e222.printStackTrace();
            }
            C4475w.m17753e("backup file create error! %s  %s", e222.getClass().getName() + ":" + e222.getMessage(), str2);
            return false;
        }
    }

    /* renamed from: a */
    public final boolean m17421a() {
        return this.f15423a.get() != 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final void m17419a(java.lang.String r13) {
        /*
        r12 = this;
        monitor-enter(r12);
        r0 = r12.f15423a;	 Catch:{ all -> 0x0066 }
        r0 = r0.get();	 Catch:{ all -> 0x0066 }
        if (r0 == 0) goto L_0x0013;
    L_0x0009:
        r0 = "trace started return ";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0066 }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);	 Catch:{ all -> 0x0066 }
        monitor-exit(r12);	 Catch:{ all -> 0x0066 }
    L_0x0012:
        return;
    L_0x0013:
        r0 = r12.f15423a;	 Catch:{ all -> 0x0066 }
        r1 = 1;
        r0.set(r1);	 Catch:{ all -> 0x0066 }
        monitor-exit(r12);	 Catch:{ all -> 0x0066 }
        r0 = "read trace first dump for create time!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = -1;
        r2 = 0;
        r2 = com.tencent.bugly.crashreport.crash.anr.TraceFileHelper.readFirstDumpInfo(r13, r2);	 Catch:{ Throwable -> 0x01cd }
        if (r2 == 0) goto L_0x002d;
    L_0x002b:
        r0 = r2.f15412c;	 Catch:{ Throwable -> 0x01cd }
    L_0x002d:
        r2 = -1;
        r2 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r2 != 0) goto L_0x02f0;
    L_0x0033:
        r0 = "trace dump fail could not get time!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = java.lang.System.currentTimeMillis();	 Catch:{ Throwable -> 0x01cd }
        r4 = r0;
    L_0x0040:
        r0 = r12.f15424b;	 Catch:{ Throwable -> 0x01cd }
        r0 = r4 - r0;
        r0 = java.lang.Math.abs(r0);	 Catch:{ Throwable -> 0x01cd }
        r2 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x0069;
    L_0x004e:
        r0 = "should not process ANR too Fre in %d";
        r1 = 1;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        r2 = 0;
        r3 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x01cd }
        r1[r2] = r3;	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f15423a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x0066:
        r0 = move-exception;
        monitor-exit(r12);
        throw r0;
    L_0x0069:
        r12.f15424b = r4;	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f15423a;	 Catch:{ Throwable -> 0x01cd }
        r1 = 1;
        r0.set(r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = com.tencent.bugly.crashreport.crash.C4436c.f15454e;	 Catch:{ Throwable -> 0x008f }
        r1 = 0;
        r6 = com.tencent.bugly.proguard.C4479y.m17786a(r0, r1);	 Catch:{ Throwable -> 0x008f }
        if (r6 == 0) goto L_0x0080;
    L_0x007a:
        r0 = r6.size();	 Catch:{ Throwable -> 0x01cd }
        if (r0 > 0) goto L_0x00a3;
    L_0x0080:
        r0 = "can't get all thread skip this anr";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f15423a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x008f:
        r0 = move-exception;
        com.tencent.bugly.proguard.C4475w.m17748a(r0);	 Catch:{ Throwable -> 0x01cd }
        r0 = "get all thread stack fail!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f15423a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x00a3:
        r7 = r12.f15425c;	 Catch:{ Throwable -> 0x01cd }
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x0103;
    L_0x00ad:
        r0 = 0;
        r2 = r0;
    L_0x00b0:
        r0 = "to find!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = "activity";
        r0 = r7.getSystemService(r0);	 Catch:{ Throwable -> 0x01cd }
        r0 = (android.app.ActivityManager) r0;	 Catch:{ Throwable -> 0x01cd }
        r8 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r8 = r2 / r8;
        r1 = 0;
        r2 = r1;
    L_0x00c6:
        r1 = "waiting!";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17751c(r1, r3);	 Catch:{ Throwable -> 0x01cd }
        r1 = r0.getProcessesInErrorState();	 Catch:{ Throwable -> 0x01cd }
        if (r1 == 0) goto L_0x0107;
    L_0x00d4:
        r3 = r1.iterator();	 Catch:{ Throwable -> 0x01cd }
    L_0x00d8:
        r1 = r3.hasNext();	 Catch:{ Throwable -> 0x01cd }
        if (r1 == 0) goto L_0x0107;
    L_0x00de:
        r1 = r3.next();	 Catch:{ Throwable -> 0x01cd }
        r1 = (android.app.ActivityManager.ProcessErrorStateInfo) r1;	 Catch:{ Throwable -> 0x01cd }
        r7 = r1.condition;	 Catch:{ Throwable -> 0x01cd }
        r10 = 2;
        if (r7 != r10) goto L_0x00d8;
    L_0x00e9:
        r0 = "found!";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r2);	 Catch:{ Throwable -> 0x01cd }
    L_0x00f1:
        if (r1 != 0) goto L_0x011d;
    L_0x00f3:
        r0 = "proc state is unvisiable!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f15423a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x0103:
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r2 = r0;
        goto L_0x00b0;
    L_0x0107:
        r10 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        com.tencent.bugly.proguard.C4479y.m17804b(r10);	 Catch:{ Throwable -> 0x01cd }
        r1 = r2 + 1;
        r2 = (long) r2;	 Catch:{ Throwable -> 0x01cd }
        r2 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r2 < 0) goto L_0x02ed;
    L_0x0113:
        r0 = "end!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r1 = 0;
        goto L_0x00f1;
    L_0x011d:
        r0 = r1.pid;	 Catch:{ Throwable -> 0x01cd }
        r2 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x01cd }
        if (r0 == r2) goto L_0x013a;
    L_0x0125:
        r0 = "not mind proc!";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01cd }
        r3 = 0;
        r1 = r1.processName;	 Catch:{ Throwable -> 0x01cd }
        r2[r3] = r1;	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17751c(r0, r2);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f15423a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x013a:
        r0 = "found visiable anr , start to process!";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17747a(r0, r2);	 Catch:{ Throwable -> 0x01cd }
        r2 = r12.f15425c;	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f15428f;	 Catch:{ Throwable -> 0x01cd }
        r0.m17396c();	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f15428f;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.m17395b();	 Catch:{ Throwable -> 0x01cd }
        if (r0 != 0) goto L_0x016d;
    L_0x0151:
        r0 = "waiting for remote sync";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r3);	 Catch:{ Throwable -> 0x01cd }
        r0 = 0;
    L_0x015a:
        r3 = r12.f15428f;	 Catch:{ Throwable -> 0x01cd }
        r3 = r3.m17395b();	 Catch:{ Throwable -> 0x01cd }
        if (r3 != 0) goto L_0x016d;
    L_0x0162:
        r8 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        com.tencent.bugly.proguard.C4479y.m17804b(r8);	 Catch:{ Throwable -> 0x01cd }
        r0 = r0 + 500;
        r3 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        if (r0 < r3) goto L_0x015a;
    L_0x016d:
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x01cd }
        r2 = r2.getFilesDir();	 Catch:{ Throwable -> 0x01cd }
        r3 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01cd }
        r7 = "bugly/bugly_trace_";
        r3.<init>(r7);	 Catch:{ Throwable -> 0x01cd }
        r3 = r3.append(r4);	 Catch:{ Throwable -> 0x01cd }
        r7 = ".txt";
        r3 = r3.append(r7);	 Catch:{ Throwable -> 0x01cd }
        r3 = r3.toString();	 Catch:{ Throwable -> 0x01cd }
        r0.<init>(r2, r3);	 Catch:{ Throwable -> 0x01cd }
        r7 = new com.tencent.bugly.crashreport.crash.anr.a;	 Catch:{ Throwable -> 0x01cd }
        r7.<init>();	 Catch:{ Throwable -> 0x01cd }
        r7.f15416c = r4;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.getAbsolutePath();	 Catch:{ Throwable -> 0x01cd }
        r7.f15417d = r0;	 Catch:{ Throwable -> 0x01cd }
        r0 = r1.processName;	 Catch:{ Throwable -> 0x01cd }
        r7.f15414a = r0;	 Catch:{ Throwable -> 0x01cd }
        r0 = r1.shortMsg;	 Catch:{ Throwable -> 0x01cd }
        r7.f15419f = r0;	 Catch:{ Throwable -> 0x01cd }
        r0 = r1.longMsg;	 Catch:{ Throwable -> 0x01cd }
        r7.f15418e = r0;	 Catch:{ Throwable -> 0x01cd }
        r7.f15415b = r6;	 Catch:{ Throwable -> 0x01cd }
        if (r6 == 0) goto L_0x01f2;
    L_0x01a8:
        r0 = r6.keySet();	 Catch:{ Throwable -> 0x01cd }
        r1 = r0.iterator();	 Catch:{ Throwable -> 0x01cd }
    L_0x01b0:
        r0 = r1.hasNext();	 Catch:{ Throwable -> 0x01cd }
        if (r0 == 0) goto L_0x01f2;
    L_0x01b6:
        r0 = r1.next();	 Catch:{ Throwable -> 0x01cd }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x01cd }
        r2 = "main(";
        r2 = r0.startsWith(r2);	 Catch:{ Throwable -> 0x01cd }
        if (r2 == 0) goto L_0x01b0;
    L_0x01c4:
        r0 = r6.get(r0);	 Catch:{ Throwable -> 0x01cd }
        r0 = (java.lang.String) r0;	 Catch:{ Throwable -> 0x01cd }
        r7.f15420g = r0;	 Catch:{ Throwable -> 0x01cd }
        goto L_0x01b0;
    L_0x01cd:
        r0 = move-exception;
        r1 = com.tencent.bugly.proguard.C4475w.m17748a(r0);	 Catch:{ all -> 0x0264 }
        if (r1 != 0) goto L_0x01d7;
    L_0x01d4:
        r0.printStackTrace();	 Catch:{ all -> 0x0264 }
    L_0x01d7:
        r1 = "handle anr error %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x0264 }
        r3 = 0;
        r0 = r0.getClass();	 Catch:{ all -> 0x0264 }
        r0 = r0.toString();	 Catch:{ all -> 0x0264 }
        r2[r3] = r0;	 Catch:{ all -> 0x0264 }
        com.tencent.bugly.proguard.C4475w.m17753e(r1, r2);	 Catch:{ all -> 0x0264 }
        r0 = r12.f15423a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x01f2:
        r1 = "anr tm:%d\ntr:%s\nproc:%s\nsMsg:%s\n lMsg:%s\n threads:%d";
        r0 = 6;
        r2 = new java.lang.Object[r0];	 Catch:{ Throwable -> 0x01cd }
        r0 = 0;
        r4 = r7.f15416c;	 Catch:{ Throwable -> 0x01cd }
        r3 = java.lang.Long.valueOf(r4);	 Catch:{ Throwable -> 0x01cd }
        r2[r0] = r3;	 Catch:{ Throwable -> 0x01cd }
        r0 = 1;
        r3 = r7.f15417d;	 Catch:{ Throwable -> 0x01cd }
        r2[r0] = r3;	 Catch:{ Throwable -> 0x01cd }
        r0 = 2;
        r3 = r7.f15414a;	 Catch:{ Throwable -> 0x01cd }
        r2[r0] = r3;	 Catch:{ Throwable -> 0x01cd }
        r0 = 3;
        r3 = r7.f15419f;	 Catch:{ Throwable -> 0x01cd }
        r2[r0] = r3;	 Catch:{ Throwable -> 0x01cd }
        r0 = 4;
        r3 = r7.f15418e;	 Catch:{ Throwable -> 0x01cd }
        r2[r0] = r3;	 Catch:{ Throwable -> 0x01cd }
        r3 = 5;
        r0 = r7.f15415b;	 Catch:{ Throwable -> 0x01cd }
        if (r0 != 0) goto L_0x024a;
    L_0x0219:
        r0 = 0;
    L_0x021a:
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Throwable -> 0x01cd }
        r2[r3] = r0;	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17751c(r1, r2);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f15428f;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.m17395b();	 Catch:{ Throwable -> 0x01cd }
        if (r0 != 0) goto L_0x0251;
    L_0x022b:
        r0 = "crash report sync remote fail, will not upload to Bugly , print local for helpful!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = "ANR";
        r1 = com.tencent.bugly.proguard.C4479y.m17777a();	 Catch:{ Throwable -> 0x01cd }
        r2 = r7.f15414a;	 Catch:{ Throwable -> 0x01cd }
        r3 = 0;
        r4 = r7.f15418e;	 Catch:{ Throwable -> 0x01cd }
        r5 = 0;
        com.tencent.bugly.crashreport.crash.C4433b.m17429a(r0, r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x01cd }
    L_0x0242:
        r0 = r12.f15423a;
        r1 = 0;
        r0.set(r1);
        goto L_0x0012;
    L_0x024a:
        r0 = r7.f15415b;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.size();	 Catch:{ Throwable -> 0x01cd }
        goto L_0x021a;
    L_0x0251:
        r0 = r12.f15428f;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.m17396c();	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.f15323j;	 Catch:{ Throwable -> 0x01cd }
        if (r0 != 0) goto L_0x026c;
    L_0x025b:
        r0 = "ANR Report is closed!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        goto L_0x0242;
    L_0x0264:
        r0 = move-exception;
        r1 = r12.f15423a;
        r2 = 0;
        r1.set(r2);
        throw r0;
    L_0x026c:
        r0 = "found visiable anr , start to upload!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17747a(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        r5 = r12.m17410a(r7);	 Catch:{ Throwable -> 0x01cd }
        if (r5 != 0) goto L_0x0283;
    L_0x027a:
        r0 = "pack anr fail!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17753e(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        goto L_0x0242;
    L_0x0283:
        r0 = com.tencent.bugly.crashreport.crash.C4436c.m17444a();	 Catch:{ Throwable -> 0x01cd }
        r0.m17450a(r5);	 Catch:{ Throwable -> 0x01cd }
        r0 = r5.f15374a;	 Catch:{ Throwable -> 0x01cd }
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 < 0) goto L_0x02e4;
    L_0x0292:
        r0 = "backup anr record success!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17747a(r0, r1);	 Catch:{ Throwable -> 0x01cd }
    L_0x029a:
        if (r13 == 0) goto L_0x02bf;
    L_0x029c:
        r0 = new java.io.File;	 Catch:{ Throwable -> 0x01cd }
        r0.<init>(r13);	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.exists();	 Catch:{ Throwable -> 0x01cd }
        if (r0 == 0) goto L_0x02bf;
    L_0x02a7:
        r0 = r12.f15423a;	 Catch:{ Throwable -> 0x01cd }
        r1 = 3;
        r0.set(r1);	 Catch:{ Throwable -> 0x01cd }
        r0 = r7.f15417d;	 Catch:{ Throwable -> 0x01cd }
        r1 = r7.f15414a;	 Catch:{ Throwable -> 0x01cd }
        r0 = com.tencent.bugly.crashreport.crash.anr.C4431b.m17411a(r13, r0, r1);	 Catch:{ Throwable -> 0x01cd }
        if (r0 == 0) goto L_0x02bf;
    L_0x02b7:
        r0 = "backup trace success";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17747a(r0, r1);	 Catch:{ Throwable -> 0x01cd }
    L_0x02bf:
        r0 = "ANR";
        r1 = com.tencent.bugly.proguard.C4479y.m17777a();	 Catch:{ Throwable -> 0x01cd }
        r2 = r7.f15414a;	 Catch:{ Throwable -> 0x01cd }
        r3 = 0;
        r4 = r7.f15418e;	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.crashreport.crash.C4433b.m17429a(r0, r1, r2, r3, r4, r5);	 Catch:{ Throwable -> 0x01cd }
        r0 = r12.f15430h;	 Catch:{ Throwable -> 0x01cd }
        r0 = r0.m17440a(r5);	 Catch:{ Throwable -> 0x01cd }
        if (r0 != 0) goto L_0x02dd;
    L_0x02d5:
        r0 = r12.f15430h;	 Catch:{ Throwable -> 0x01cd }
        r2 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r1 = 1;
        r0.m17438a(r5, r2, r1);	 Catch:{ Throwable -> 0x01cd }
    L_0x02dd:
        r0 = r12.f15430h;	 Catch:{ Throwable -> 0x01cd }
        r0.m17442b(r5);	 Catch:{ Throwable -> 0x01cd }
        goto L_0x0242;
    L_0x02e4:
        r0 = "backup anr record fail!";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01cd }
        com.tencent.bugly.proguard.C4475w.m17752d(r0, r1);	 Catch:{ Throwable -> 0x01cd }
        goto L_0x029a;
    L_0x02ed:
        r2 = r1;
        goto L_0x00c6;
    L_0x02f0:
        r4 = r0;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.anr.b.a(java.lang.String):void");
    }

    /* renamed from: c */
    private synchronized void m17413c() {
        if (m17416e()) {
            C4475w.m17752d("start when started!", new Object[0]);
        } else {
            this.f15431i = new C44291(this, "/data/anr/", 8);
            try {
                this.f15431i.startWatching();
                C4475w.m17747a("start anr monitor!", new Object[0]);
                this.f15427e.m17741a(new C44302(this));
            } catch (Throwable th) {
                this.f15431i = null;
                C4475w.m17752d("start anr monitor failed!", new Object[0]);
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
            }
        }
    }

    /* renamed from: d */
    private synchronized void m17415d() {
        if (m17416e()) {
            try {
                this.f15431i.stopWatching();
                this.f15431i = null;
                C4475w.m17752d("close anr monitor!", new Object[0]);
            } catch (Throwable th) {
                C4475w.m17752d("stop anr monitor failed!", new Object[0]);
                if (!C4475w.m17748a(th)) {
                    th.printStackTrace();
                }
            }
        } else {
            C4475w.m17752d("close when closed!", new Object[0]);
        }
    }

    /* renamed from: e */
    private synchronized boolean m17416e() {
        return this.f15431i != null;
    }

    /* renamed from: b */
    private synchronized void m17412b(boolean z) {
        if (z) {
            m17413c();
        } else {
            m17415d();
        }
    }

    /* renamed from: f */
    private synchronized boolean m17417f() {
        return this.f15432j;
    }

    /* renamed from: c */
    private synchronized void m17414c(boolean z) {
        if (this.f15432j != z) {
            C4475w.m17747a("user change anr %b", Boolean.valueOf(z));
            this.f15432j = z;
        }
    }

    /* renamed from: a */
    public final void m17420a(boolean z) {
        m17414c(z);
        boolean f = m17417f();
        C4421a a = C4421a.m17388a();
        if (a != null) {
            f = f && a.m17396c().f15320g;
        }
        if (f != m17416e()) {
            C4475w.m17747a("anr changed to %b", Boolean.valueOf(f));
            m17412b(f);
        }
    }

    /* renamed from: b */
    protected final void m17422b() {
        long b = C4479y.m17800b() - C4436c.f15455f;
        File file = new File(this.f15429g);
        if (file.exists() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                String str = "bugly_trace_";
                String str2 = ".txt";
                int length = str.length();
                int i = 0;
                for (File file2 : listFiles) {
                    String name = file2.getName();
                    if (name.startsWith(str)) {
                        try {
                            int indexOf = name.indexOf(str2);
                            if (indexOf > 0 && Long.parseLong(name.substring(length, indexOf)) >= b) {
                            }
                        } catch (Throwable th) {
                            C4475w.m17753e("tomb format error delete %s", name);
                        }
                        if (file2.delete()) {
                            i++;
                        }
                    }
                }
                C4475w.m17751c("clean tombs %d", Integer.valueOf(i));
            }
        }
    }

    /* renamed from: a */
    public final synchronized void m17418a(StrategyBean strategyBean) {
        boolean z = true;
        synchronized (this) {
            if (strategyBean != null) {
                if (strategyBean.f15323j != m17416e()) {
                    C4475w.m17752d("server anr changed to %b", Boolean.valueOf(strategyBean.f15323j));
                }
                if (!(strategyBean.f15323j && m17417f())) {
                    z = false;
                }
                if (z != m17416e()) {
                    C4475w.m17747a("anr changed to %b", Boolean.valueOf(z));
                    m17412b(z);
                }
            }
        }
    }
}
