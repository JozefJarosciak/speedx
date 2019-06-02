package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.common.info.C4418b;
import com.tencent.bugly.crashreport.common.strategy.C4421a;
import com.tencent.bugly.crashreport.crash.C4433b;
import com.tencent.bugly.crashreport.crash.C4436c;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4478x;
import com.tencent.bugly.proguard.C4479y;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.protocol.HttpRequestExecutor;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.jni.a */
public final class C4444a implements NativeExceptionHandler {
    /* renamed from: a */
    private final Context f15522a;
    /* renamed from: b */
    private final C4433b f15523b;
    /* renamed from: c */
    private final C4417a f15524c;
    /* renamed from: d */
    private final C4421a f15525d;
    /* renamed from: e */
    private final String f15526e;

    public C4444a(Context context, C4417a c4417a, C4433b c4433b, C4421a c4421a, String str) {
        this.f15522a = context;
        this.f15523b = c4433b;
        this.f15524c = c4417a;
        this.f15525d = c4421a;
        this.f15526e = str;
    }

    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, byte[] bArr, Map<String, String> map, boolean z) {
        boolean l = C4436c.m17444a().m17462l();
        if (l) {
            C4475w.m17753e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f15375b = 1;
        crashDetailBean.f15378e = this.f15524c.m17339h();
        crashDetailBean.f15379f = this.f15524c.f15295j;
        crashDetailBean.f15380g = this.f15524c.m17354w();
        crashDetailBean.f15386m = this.f15524c.m17337g();
        crashDetailBean.f15387n = str3;
        crashDetailBean.f15388o = l ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        crashDetailBean.f15389p = str4;
        if (str5 == null) {
            str5 = "";
        }
        crashDetailBean.f15390q = str5;
        crashDetailBean.f15391r = j;
        crashDetailBean.f15394u = C4479y.m17802b(crashDetailBean.f15390q.getBytes());
        crashDetailBean.f15399z = str;
        crashDetailBean.f15352A = str2;
        crashDetailBean.f15359H = this.f15524c.m17356y();
        crashDetailBean.f15381h = this.f15524c.m17353v();
        crashDetailBean.f15382i = this.f15524c.m17314I();
        crashDetailBean.f15395v = str8;
        String a = C4445b.m17496a(this.f15526e, str8);
        if (!C4479y.m17792a(a)) {
            crashDetailBean.f15371T = a;
        }
        File file = new File(this.f15526e, "backup_record.txt");
        crashDetailBean.f15372U = file.exists() ? file.getAbsolutePath() : null;
        crashDetailBean.f15360I = str7;
        crashDetailBean.f15361J = str6;
        crashDetailBean.f15362K = str9;
        crashDetailBean.f15356E = this.f15524c.m17347p();
        crashDetailBean.f15357F = this.f15524c.m17346o();
        crashDetailBean.f15358G = this.f15524c.m17348q();
        if (z) {
            crashDetailBean.f15353B = C4418b.m17371g();
            crashDetailBean.f15354C = C4418b.m17367e();
            crashDetailBean.f15355D = C4418b.m17375i();
            crashDetailBean.f15396w = C4479y.m17779a(this.f15522a, C4436c.f15453d, null);
            crashDetailBean.f15397x = C4478x.m17766a(true);
            crashDetailBean.f15363L = this.f15524c.f15286a;
            crashDetailBean.f15364M = this.f15524c.m17325a();
            crashDetailBean.f15366O = this.f15524c.m17311F();
            crashDetailBean.f15367P = this.f15524c.m17312G();
            crashDetailBean.f15368Q = this.f15524c.m17357z();
            crashDetailBean.f15369R = this.f15524c.m17310E();
            crashDetailBean.f15398y = C4479y.m17786a(C4436c.f15454e, false);
            a = "java:\n";
            int indexOf = crashDetailBean.f15390q.indexOf(a);
            if (indexOf > 0) {
                indexOf += a.length();
                String substring = crashDetailBean.f15390q.substring(indexOf, crashDetailBean.f15390q.length() - 1);
                if (substring.length() > 0 && crashDetailBean.f15398y.containsKey(crashDetailBean.f15352A)) {
                    a = (String) crashDetailBean.f15398y.get(crashDetailBean.f15352A);
                    int indexOf2 = a.indexOf(substring);
                    if (indexOf2 > 0) {
                        a = a.substring(indexOf2);
                        crashDetailBean.f15398y.put(crashDetailBean.f15352A, a);
                        crashDetailBean.f15390q = crashDetailBean.f15390q.substring(0, indexOf);
                        crashDetailBean.f15390q += a;
                    }
                }
            }
            if (str == null) {
                crashDetailBean.f15399z = this.f15524c.f15289d;
            }
            this.f15523b.m17442b(crashDetailBean);
        } else {
            crashDetailBean.f15353B = -1;
            crashDetailBean.f15354C = -1;
            crashDetailBean.f15355D = -1;
            crashDetailBean.f15396w = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            crashDetailBean.f15363L = -1;
            crashDetailBean.f15366O = -1;
            crashDetailBean.f15367P = -1;
            crashDetailBean.f15368Q = map;
            crashDetailBean.f15369R = this.f15524c.m17310E();
            crashDetailBean.f15398y = null;
            if (str == null) {
                crashDetailBean.f15399z = "unknown(record)";
            }
            if (bArr == null) {
                crashDetailBean.f15397x = "this crash is occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.".getBytes();
            } else {
                crashDetailBean.f15397x = bArr;
            }
        }
        return crashDetailBean;
    }

    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        C4475w.m17747a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    public final void handleNativeException2(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7, String[] strArr) {
        C4475w.m17747a("Native Crash Happen v2", new Object[0]);
        try {
            int i7;
            String str8;
            String str9;
            if (!this.f15525d.m17395b()) {
                C4475w.m17753e("waiting for remote sync", new Object[0]);
                i7 = 0;
                while (!this.f15525d.m17395b()) {
                    C4479y.m17804b(500);
                    i7 += 500;
                    if (i7 >= HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE) {
                        break;
                    }
                }
            }
            String a = C4445b.m17495a(str3);
            String str10 = "UNKNOWN";
            if (i3 > 0) {
                str8 = "KERNEL";
                str9 = str + "(" + str5 + ")";
            } else {
                if (i4 > 0) {
                    Context context = this.f15522a;
                    str10 = AppInfo.m17292a(i4);
                }
                if (str10.equals(String.valueOf(i4))) {
                    str8 = str5;
                    str9 = str;
                } else {
                    str10 = str10 + "(" + i4 + ")";
                    str8 = str5;
                    str9 = str;
                }
            }
            if (!this.f15525d.m17395b()) {
                C4475w.m17752d("no remote but still store!", new Object[0]);
            }
            if (this.f15525d.m17396c().f15320g || !this.f15525d.m17395b()) {
                String str11 = null;
                String str12 = null;
                if (strArr != null) {
                    Map hashMap = new HashMap();
                    for (String str122 : strArr) {
                        String[] split = str122.split(SimpleComparison.EQUAL_TO_OPERATION);
                        if (split.length == 2) {
                            hashMap.put(split[0], split[1]);
                        } else {
                            C4475w.m17752d("bad extraMsg %s", str122);
                        }
                    }
                    str122 = (String) hashMap.get("ExceptionThreadName");
                    str11 = (String) hashMap.get("ExceptionProcessName");
                } else {
                    C4475w.m17751c("not found extraMsg", new Object[0]);
                }
                if (str11 == null || str11.length() == 0) {
                    str11 = this.f15524c.f15289d;
                } else {
                    C4475w.m17751c("crash process name change to %s", str11);
                }
                Thread currentThread;
                if (str122 != null && str122.length() != 0) {
                    C4475w.m17751c("crash thread name change to %s", str122);
                    for (Thread currentThread2 : Thread.getAllStackTraces().keySet()) {
                        if (currentThread2.getName().equals(str122)) {
                            str122 = str122 + "(" + currentThread2.getId() + ")";
                            break;
                        }
                    }
                }
                currentThread2 = Thread.currentThread();
                str122 = currentThread2.getName() + "(" + currentThread2.getId() + ")";
                CrashDetailBean packageCrashDatas = packageCrashDatas(str11, str122, (j2 / 1000) + (1000 * j), str9, str2, a, str8, str10, str4, str7, null, null, true);
                if (packageCrashDatas == null) {
                    C4475w.m17753e("pkg crash datas fail!", new Object[0]);
                    return;
                }
                C4433b.m17429a("NATIVE_CRASH", C4479y.m17777a(), this.f15524c.f15289d, Thread.currentThread(), str9 + "\n" + str2 + "\n" + a, packageCrashDatas);
                if (!this.f15523b.m17441a(packageCrashDatas, i3)) {
                    this.f15523b.m17438a(packageCrashDatas, 3000, true);
                }
                C4445b.m17497b(this.f15526e);
                return;
            }
            C4475w.m17753e("crash report was closed by remote , will not upload to Bugly , print local for helpful!", new Object[0]);
            C4433b.m17429a("NATIVE_CRASH", C4479y.m17777a(), this.f15524c.f15289d, Thread.currentThread(), str9 + "\n" + str2 + "\n" + a, null);
            C4479y.m17806b(str4);
        } catch (Throwable th) {
            if (!C4475w.m17748a(th)) {
                th.printStackTrace();
            }
        }
    }
}
