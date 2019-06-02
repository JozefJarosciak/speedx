package cn.jpush.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.data.C0441m;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.p003b.p004a.C0421a;
import cn.jpush.android.p003b.p004a.C0422b;
import cn.jpush.android.p003b.p004a.C0425f;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;
import java.io.File;

public class PopWinActivity extends Activity {
    /* renamed from: a */
    public static C0425f f905a = null;
    /* renamed from: z */
    private static final String[] f906z;
    /* renamed from: b */
    private String f907b;
    /* renamed from: c */
    private WebView f908c;
    /* renamed from: d */
    private C0429c f909d = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 17;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "kL7r\u001cXM-l4O@d}8O\u0019\nO\u0011w\u0019-t)^W0;";
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
            case 0: goto L_0x00dc;
            case 1: goto L_0x00e0;
            case 2: goto L_0x00e4;
            case 3: goto L_0x00e8;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 93;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "xX*:3TMd}8O\u00193?mP!m}RWdv<BV1n}]P(|";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "kU!{.^\u0019%~9\u001bU%c2NMdh8HV1h>^\u0019.j(HQ\u001bj2KN-t\u0002WX=u(O\u0017<w1\u001bM+:/^Jkv<BV1n}\u001a";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "R]";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "HQ+m\bIUd'}";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "lX6t4U^ｈt(WUdw8HJ%}8\u001b\\*n4O@e:\u001eWV7}kL7r\u001cXM-l4O@e";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "~A0h<\u001b]%n<\u001bP7:3TMdi8IP%v4AX&v8\u001a";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "QI1i5dI+j*RW\u001bv<BV1n";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "kU!{.^\u00191i8\u001b]!|<NU0:>T]!:4U\u0019.j(HQ\u001bj2KN-t\u0002WX=u(O\u0017<w1\u001a";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "H\\%h>S{+b\u0017ZO%X/R]#\u0002";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "]P(g\u0014\u0016";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "YV c";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "qi1i5l\\&";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "WX=u(O";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "kL7r\u001cXM-l4O@";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "LO\u0014u-LP*";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "]K+w\u0002LX=";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        f906z = r4;
        r0 = 0;
        f905a = r0;
        return;
    L_0x00dc:
        r9 = 59;
        goto L_0x0020;
    L_0x00e0:
        r9 = 57;
        goto L_0x0020;
    L_0x00e4:
        r9 = 68;
        goto L_0x0020;
    L_0x00e8:
        r9 = 26;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.ui.PopWinActivity.<clinit>():void");
    }

    /* renamed from: a */
    public final void m1541a(String str) {
        if (this.f909d != null && this.f908c != null && (this.f909d instanceof C0441m)) {
            if (!an.m1657a(str)) {
                ((C0441m) this.f909d).f716a = str;
                Intent intent = new Intent(this, PushActivity.class);
                intent.putExtra(f906z[11], this.f909d);
                intent.putExtra(f906z[16], true);
                startActivity(intent);
            }
            finish();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        C0459i.m1415a(this.f907b, 1006, this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getIntent() != null) {
            try {
                this.f909d = (C0429c) getIntent().getSerializableExtra(f906z[11]);
                if (this.f909d != null) {
                    this.f907b = this.f909d.f613c;
                    int identifier = getResources().getIdentifier(f906z[7], f906z[13], getPackageName());
                    if (identifier == 0) {
                        ac.m1589e(f906z[14], f906z[2]);
                        finish();
                    } else {
                        setContentView(identifier);
                        identifier = getResources().getIdentifier(f906z[15], f906z[3], getPackageName());
                        if (identifier == 0) {
                            ac.m1589e(f906z[14], f906z[8]);
                            finish();
                        } else {
                            this.f908c = (WebView) findViewById(identifier);
                            if (this.f908c == null) {
                                ac.m1589e(f906z[14], f906z[1]);
                                finish();
                            } else {
                                this.f908c.setScrollbarFadingEnabled(true);
                                this.f908c.setScrollBarStyle(33554432);
                                WebSettings settings = this.f908c.getSettings();
                                settings.setDomStorageEnabled(true);
                                C0490b.m1680a(settings);
                                this.f908c.setBackgroundColor(0);
                                f905a = new C0425f(this, this.f909d);
                                this.f908c.removeJavascriptInterface(f906z[9]);
                                this.f908c.setWebChromeClient(new C0421a(f906z[12], C0422b.class));
                                this.f908c.setWebViewClient(new C0484c(this.f909d));
                                C0422b.setWebViewHelper(f905a);
                            }
                        }
                    }
                    C0441m c0441m = (C0441m) this.f909d;
                    String str = c0441m.f715L;
                    String str2 = c0441m.f716a;
                    new StringBuilder(f906z[4]).append(str2);
                    ac.m1581b();
                    if (TextUtils.isEmpty(str) || !new File(str.replace(f906z[10], "")).exists()) {
                        this.f908c.loadUrl(str2);
                    } else {
                        this.f908c.loadUrl(str);
                    }
                    C0459i.m1415a(this.f907b, 1000, this);
                    return;
                }
                ac.m1587d(f906z[14], f906z[5]);
                finish();
                return;
            } catch (Exception e) {
                ac.m1589e(f906z[14], f906z[6]);
                e.printStackTrace();
                finish();
                return;
            }
        }
        ac.m1587d(f906z[14], f906z[0]);
        finish();
    }

    protected void onDestroy() {
        if (this.f908c != null) {
            this.f908c.removeAllViews();
            this.f908c.destroy();
            this.f908c = null;
        }
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
        if (this.f908c != null) {
            this.f908c.onPause();
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.f908c != null) {
            this.f908c.onResume();
            C0422b.setWebViewHelper(f905a);
        }
    }
}
