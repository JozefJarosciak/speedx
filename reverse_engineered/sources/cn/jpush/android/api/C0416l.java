package cn.jpush.android.api;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.app.TabActivity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;

/* renamed from: cn.jpush.android.api.l */
final class C0416l implements ActivityLifecycleCallbacks {
    /* renamed from: z */
    private static final String[] f563z;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 4;
        r5 = new java.lang.String[r0];
        r4 = "\r#\u00065\u001e\u0011b<5\u0005\u0000,\u0001u0&\u0016<\u0014?:\u000f4\u0012?E#\u001b?Q,,\u0001>\u001f\u0011l6\u001a% \u0005:\t(:\u000e4\u000e?&\n0\t";
        r0 = -1;
        r6 = r5;
        r7 = r5;
        r5 = r1;
    L_0x000c:
        r4 = r4.toCharArray();
        r8 = r4.length;
        if (r8 > r2) goto L_0x006b;
    L_0x0013:
        r9 = r1;
    L_0x0014:
        r10 = r4;
        r11 = r9;
        r14 = r8;
        r8 = r4;
        r4 = r14;
    L_0x0019:
        r13 = r8[r9];
        r12 = r11 % 5;
        switch(r12) {
            case 0: goto L_0x005f;
            case 1: goto L_0x0062;
            case 2: goto L_0x0065;
            case 3: goto L_0x0068;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
    L_0x0022:
        r12 = r12 ^ r13;
        r12 = (char) r12;
        r8[r9] = r12;
        r9 = r11 + 1;
        if (r4 != 0) goto L_0x002e;
    L_0x002a:
        r8 = r10;
        r11 = r9;
        r9 = r4;
        goto L_0x0019;
    L_0x002e:
        r8 = r4;
        r4 = r10;
    L_0x0030:
        if (r8 > r9) goto L_0x0014;
    L_0x0032:
        r8 = new java.lang.String;
        r8.<init>(r4);
        r4 = r8.intern();
        switch(r0) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0050;
            case 2: goto L_0x005a;
            default: goto L_0x003e;
        };
    L_0x003e:
        r6[r5] = r4;
        r0 = ")+\u0013>2\u001c!\u0019>2\u0004.\u00199\u0010\u0006)\u0006";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r5] = r4;
        r0 = "\u0004,\u0011)\u001e\f&[2\u001f\u0011'\u001b/_\u0004!\u00012\u001e\u000bl8\u001a8+";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r5] = r4;
        r4 = 3;
        r0 = "\u0004,\u0011)\u001e\f&[2\u001f\u0011'\u001b/_\u0006#\u0001>\u0016\n0\fu=$\u0017;\u00189 \u0010";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000c;
    L_0x005a:
        r6[r5] = r4;
        f563z = r7;
        return;
    L_0x005f:
        r12 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0022;
    L_0x0062:
        r12 = 66;
        goto L_0x0022;
    L_0x0065:
        r12 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        goto L_0x0022;
    L_0x0068:
        r12 = 91;
        goto L_0x0022;
    L_0x006b:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.l.<clinit>():void");
    }

    C0416l() {
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
        C0415k.f561f = activity.getClass().getName();
        if (an.m1657a(C0415k.f560e)) {
            C0415k.f560e = activity.getClass().getName();
        }
        if (VERSION.SDK_INT >= 14 && C0415k.f556a) {
            if (!C0409e.f526a && C0409e.m1183b().m1191a()) {
                C0415k.f559d.put(C0415k.f560e, Integer.valueOf(0));
                if (!an.m1657a(C0415k.f562g) && C0415k.f562g.equals(C0415k.f560e)) {
                    C0490b.m1690b(activity, C0415k.f558c, C0415k.f560e, 0);
                }
            }
            if (activity instanceof TabActivity) {
                ac.m1586d();
            } else {
                C0409e.f526a = false;
            }
        }
    }

    public final void onActivityResumed(Activity activity) {
        C0415k.f560e = activity.getClass().getName();
        if (VERSION.SDK_INT >= 14 && C0415k.f556a) {
            if (C0415k.f557b) {
                Intent intent = new Intent(f563z[2]);
                intent.setPackage(activity.getPackageName());
                intent.addCategory(f563z[3]);
                ResolveInfo resolveActivity = activity.getPackageManager().resolveActivity(intent, 0);
                if (resolveActivity == null) {
                    ac.m1587d(f563z[1], f563z[0]);
                    return;
                }
                C0415k.f562g = resolveActivity.activityInfo.name;
                C0415k.f557b = false;
                return;
            }
            if (!(C0409e.f527b || !C0409e.m1183b().m1191a() || C0415k.f561f == null)) {
                if (C0415k.f559d.containsKey(C0415k.f561f)) {
                    C0415k.f559d.put(C0415k.f561f, Integer.valueOf(2));
                    if (!an.m1657a(C0415k.f562g) && C0415k.f562g.equals(C0415k.f561f)) {
                        C0490b.m1690b(activity, C0415k.f558c, C0415k.f561f, 2);
                    }
                } else {
                    C0415k.f559d.put(C0415k.f561f, Integer.valueOf(1));
                    if (!an.m1657a(C0415k.f562g) && C0415k.f562g.equals(C0415k.f561f)) {
                        C0490b.m1690b(activity, C0415k.f558c, C0415k.f561f, 1);
                    }
                }
            }
            if (activity instanceof TabActivity) {
                ac.m1586d();
            } else {
                C0409e.f527b = false;
            }
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}
