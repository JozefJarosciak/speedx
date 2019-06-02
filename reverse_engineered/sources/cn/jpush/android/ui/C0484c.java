package cn.jpush.android.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import ch.qos.logback.classic.spi.CallerData;
import cn.jpush.android.C0448e;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.ui.c */
public final class C0484c extends WebViewClient {
    /* renamed from: z */
    private static final String[] f917z;
    /* renamed from: a */
    private final C0429c f918a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 22;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u001eXhqL\u001c";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0110;
            case 1: goto L_0x0114;
            case 2: goto L_0x0118;
            case 3: goto L_0x011b;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 56;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "]Tq.";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "L]ho]\u0010M<{Y\u001fJd";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0012WeoW\u001a]/tV\u0007\\oi\u0016\u0012ZutW\u001d\u0017WT}$";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0003U`tV\\MdeL";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "]\nfm";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0012LetW\\\u0013";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0012WeoW\u001a]/tV\u0007\\oi\u0016\u0016AuoY]mDEl";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "UZnsL\u0016Wu ";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "&Km=N\u0012Lmx\u0018\u001aJ!'";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\"LdoA MstV\u0014\u0003!";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "U]ho]\u0010M<{Y\u001fJd";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "]Tq)";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\b\u001btoTQ\u0003#8KQD";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0017Psx[\u0007\u0004";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\u0012WeoW\u001a]/tV\u0007\\oi\u0016\u0016AuoY]jT_r6zU";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0012WeoW\u001a]/tV\u0007\\oi\u0016\u0012ZutW\u001d\u0017RXv7";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0012WeoW\u001a]/tV\u0007\\oi\u0016\u0016AuoY]|L\\q?";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u0005PexW\\\u0013";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u0007Puq]N";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "&Kh'\u0018";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\u001bMum";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        f917z = r4;
        return;
    L_0x0110:
        r9 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        goto L_0x0020;
    L_0x0114:
        r9 = 57;
        goto L_0x0020;
    L_0x0118:
        r9 = 1;
        goto L_0x0020;
    L_0x011b:
        r9 = 29;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.ui.c.<clinit>():void");
    }

    public C0484c(C0429c c0429c) {
        this.f918a = c0429c;
    }

    public final void onLoadResource(WebView webView, String str) {
        super.onLoadResource(webView, str);
    }

    public final void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        Context context = webView.getContext();
        new StringBuilder(f917z[9]).append(str);
        ac.m1584c();
        try {
            String format = String.format(f917z[13], new Object[]{str});
            if (this.f918a.f635y) {
                context.startActivity(new Intent(f917z[3], Uri.parse(str)));
                C0459i.m1416a(this.f918a.f613c, 1016, format, C0448e.f753e);
                return true;
            } else if (str.endsWith(f917z[1])) {
                r1 = new Intent(f917z[3]);
                r1.setDataAndType(Uri.parse(str), f917z[6]);
                webView.getContext().startActivity(r1);
                return true;
            } else if (str.endsWith(f917z[12]) || str.endsWith(f917z[5])) {
                r1 = new Intent(f917z[3]);
                r1.setDataAndType(Uri.parse(str), f917z[18]);
                webView.getContext().startActivity(r1);
                return true;
            } else {
                if (str.startsWith(f917z[21])) {
                    webView.loadUrl(str);
                    C0459i.m1416a(this.f918a.f613c, 1016, format, C0448e.f753e);
                } else if (str != null && str.startsWith(f917z[0])) {
                    if (str.lastIndexOf(f917z[14]) < 0 && !str.startsWith(f917z[0])) {
                        str = str.indexOf(CallerData.NA) > 0 ? str + f917z[11] : str + f917z[2];
                        str.lastIndexOf(f917z[14]);
                    }
                    int indexOf = str.indexOf(CallerData.NA);
                    String substring = str.substring(0, indexOf);
                    String substring2 = str.substring(indexOf);
                    new StringBuilder(f917z[20]).append(substring);
                    ac.m1576a();
                    new StringBuilder(f917z[10]).append(substring2);
                    ac.m1576a();
                    r1 = null;
                    if (substring.startsWith(f917z[0])) {
                        String[] split = substring.split(":");
                        if (split != null && split.length == 2) {
                            indexOf = substring2.indexOf(f917z[19]) + 6;
                            int indexOf2 = substring2.indexOf(f917z[8]);
                            String substring3 = substring2.substring(indexOf, indexOf2);
                            substring2 = substring2.substring(indexOf2 + 9);
                            String[] strArr = new String[]{split[1]};
                            r1 = new Intent(f917z[16]);
                            r1.setType(f917z[4]);
                            r1.putExtra(f917z[17], strArr);
                            r1.putExtra(f917z[15], substring3);
                            r1.putExtra(f917z[7], substring2);
                        }
                    }
                    if (r1 != null) {
                        context.startActivity(r1);
                    }
                    C0459i.m1416a(this.f918a.f613c, 1016, format, C0448e.f753e);
                    return true;
                }
                return false;
            }
        } catch (Exception e) {
            ac.m1588e();
            return true;
        }
    }
}
