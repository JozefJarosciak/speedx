package cn.jpush.android.p003b.p004a;

import android.webkit.WebView;
import cn.jpush.android.api.C0419o;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.b.a.b */
public class C0422b {
    private static final String TAG;
    private static C0425f mWebViewHelper;
    /* renamed from: z */
    private static final String f574z;

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
        r1 = 0;
        r2 = "AeNeg\\s\tWlC{\tF{N,\t";
        r0 = -1;
    L_0x0004:
        r2 = r2.toCharArray();
        r3 = r2.length;
        r4 = 1;
        if (r3 > r4) goto L_0x004d;
    L_0x000c:
        r4 = r1;
    L_0x000d:
        r5 = r2;
        r6 = r4;
        r9 = r3;
        r3 = r2;
        r2 = r9;
    L_0x0012:
        r8 = r3[r4];
        r7 = r6 % 5;
        switch(r7) {
            case 0: goto L_0x0041;
            case 1: goto L_0x0044;
            case 2: goto L_0x0047;
            case 3: goto L_0x004a;
            default: goto L_0x0019;
        };
    L_0x0019:
        r7 = 30;
    L_0x001b:
        r7 = r7 ^ r8;
        r7 = (char) r7;
        r3[r4] = r7;
        r4 = r6 + 1;
        if (r2 != 0) goto L_0x0027;
    L_0x0023:
        r3 = r5;
        r6 = r4;
        r4 = r2;
        goto L_0x0012;
    L_0x0027:
        r3 = r2;
        r2 = r5;
    L_0x0029:
        if (r3 > r4) goto L_0x000d;
    L_0x002b:
        r3 = new java.lang.String;
        r3.<init>(r2);
        r2 = r3.intern();
        switch(r0) {
            case 0: goto L_0x003e;
            default: goto L_0x0037;
        };
    L_0x0037:
        f574z = r2;
        r0 = "dyZET_EJ^nI";
        r2 = r0;
        r0 = r1;
        goto L_0x0004;
    L_0x003e:
        TAG = r2;
        return;
    L_0x0041:
        r7 = 44;
        goto L_0x001b;
    L_0x0044:
        r7 = 22;
        goto L_0x001b;
    L_0x0047:
        r7 = 41;
        goto L_0x001b;
    L_0x004a:
        r7 = 49;
        goto L_0x001b;
    L_0x004d:
        r4 = r1;
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.b.a.b.<clinit>():void");
    }

    public static void click(WebView webView, String str, String str2, String str3) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1236b(str, str2, str3);
        }
    }

    public static void close(WebView webView) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1229a();
        }
    }

    public static void createShortcut(WebView webView, String str, String str2, String str3) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1232a(str, str2, str3);
        }
    }

    public static void download(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1237c(str);
        }
    }

    public static void download(WebView webView, String str, String str2) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1238c(str, str2);
        }
    }

    public static void download(WebView webView, String str, String str2, String str3) {
        if (mWebViewHelper != null) {
            C0425f c0425f = mWebViewHelper;
            new StringBuilder(f574z).append(str3);
            ac.m1576a();
            c0425f.m1238c(str, str2);
        }
    }

    public static void executeMsgMessage(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1240e(str);
        }
    }

    public static void setWebViewHelper(C0425f c0425f) {
        if (c0425f != null) {
            mWebViewHelper = c0425f;
        }
    }

    public static void showTitleBar(WebView webView) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1233b();
        }
    }

    public static void showToast(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1239d(str);
        }
    }

    public static void startActivityByIntent(WebView webView, String str, String str2) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1235b(str, str2);
        }
    }

    public static void startActivityByName(WebView webView, String str, String str2) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1231a(str, str2);
        }
    }

    public static void startActivityByNameWithSystemAlert(WebView webView, String str, String str2) {
        if (C0419o.f567a != null) {
            C0419o.f567a.m1227a(str, str2);
        }
    }

    public static void startMainActivity(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1234b(str);
        }
    }

    public static void startPushActivity(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1241f(str);
        }
    }

    public static void triggerNativeAction(WebView webView, String str) {
        if (mWebViewHelper != null) {
            mWebViewHelper.m1230a(str);
        }
    }
}
