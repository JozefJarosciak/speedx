package cn.jpush.android.p003b.p004a;

import android.util.Log;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/* renamed from: cn.jpush.android.b.a.c */
public class C0420c extends WebChromeClient {
    /* renamed from: z */
    private static final String[] f570z;
    /* renamed from: a */
    private final String f571a = f570z[0];
    /* renamed from: b */
    private C0423d f572b;
    /* renamed from: c */
    private boolean f573c;

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
        r4 = 1;
        r1 = 0;
        r0 = 2;
        r3 = new java.lang.String[r0];
        r2 = "\f&\u0017)@1-\u0019\u000fK7'\u0010)`)!\u0018\"W";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0057;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r2;
        r10 = r8;
        r13 = r7;
        r7 = r2;
        r2 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x004b;
            case 1: goto L_0x004e;
            case 2: goto L_0x0051;
            case 3: goto L_0x0054;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 35;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r2 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r2;
        goto L_0x0018;
    L_0x002d:
        r7 = r2;
        r2 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r2);
        r2 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r3] = r2;
        r0 = "e!\u0013&F&<]&Pe!\u00138F7.\u001c/Fe+\u0012!S)-\t)O<h\u0012\"\u00035:\u0012+Q ;\u000el";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f570z = r6;
        return;
    L_0x004b:
        r11 = 69;
        goto L_0x0021;
    L_0x004e:
        r11 = 72;
        goto L_0x0021;
    L_0x0051:
        r11 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        goto L_0x0021;
    L_0x0054:
        r11 = 76;
        goto L_0x0021;
    L_0x0057:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.b.a.c.<clinit>():void");
    }

    public C0420c(String str, Class cls) {
        this.f572b = new C0423d(str, cls);
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        jsResult.confirm();
        return true;
    }

    public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        jsPromptResult.confirm(this.f572b.m1226a(webView, str2));
        return true;
    }

    public void onProgressChanged(WebView webView, int i) {
        if (i <= 25) {
            this.f573c = false;
        } else if (!this.f573c) {
            webView.loadUrl(this.f572b.m1225a());
            this.f573c = true;
            Log.d(f570z[0], new StringBuilder(f570z[1]).append(i).toString());
        }
        super.onProgressChanged(webView, i);
    }
}
