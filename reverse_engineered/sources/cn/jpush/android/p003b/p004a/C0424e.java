package cn.jpush.android.p003b.p004a;

import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import cn.jpush.android.api.C0419o;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;

/* renamed from: cn.jpush.android.b.a.e */
public final class C0424e {
    /* renamed from: z */
    private static final String[] f580z;
    /* renamed from: a */
    private Context f581a;
    /* renamed from: b */
    private WindowManager f582b;
    /* renamed from: c */
    private WebView f583c;
    /* renamed from: d */
    private ImageButton f584d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 6;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = ":MsEL,P5\u0001]7G/@U=\r\u001clh\u0010u\u0014{e\u0006s\u001c}}\u0014";
        r0 = -1;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
    L_0x0011:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0016:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0068;
            case 1: goto L_0x006b;
            case 2: goto L_0x006e;
            case 3: goto L_0x0071;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 60;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0011;
    L_0x002f:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "t\u000ep\u0002\u0011yS<]]4P}\u0015\u001c";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\nZ.[Y4b1JN-t8Mj0F*l]5O?N_2";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "8@)FS7\u0003p\u0002\u0011yP)NN-b>[U/J)V~ m<BYt\u000ep\u0002\u0011t\u000epN_-J+FH m<BYy\u0019}";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\rK8\u000f]:W4YU-Z}A]4F}FOyJ3Y]5J9\u0003\u001c\u001eJ+J\u001c,Ss\u0001";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\rK8\u000f]:W4YU-Z}A]4F}FOyM(CPyL/\u000fY4S)V\u0010yd4YYyV-\u0001\u0012";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        f580z = r4;
        return;
    L_0x0068:
        r9 = 89;
        goto L_0x001f;
    L_0x006b:
        r9 = 35;
        goto L_0x001f;
    L_0x006e:
        r9 = 93;
        goto L_0x001f;
    L_0x0071:
        r9 = 47;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.b.a.e.<clinit>():void");
    }

    /* renamed from: a */
    public final void m1227a(String str, String str2) {
        new StringBuilder(f580z[3]).append(str).append(f580z[1]).append(str2);
        ac.m1581b();
        if (an.m1657a(str)) {
            ac.m1589e(f580z[2], f580z[5]);
        }
        if (this.f581a != null) {
            try {
                Class cls = Class.forName(str);
                if (cls != null) {
                    Intent intent = new Intent(this.f581a, cls);
                    intent.putExtra(f580z[0], str2);
                    intent.setFlags(268435456);
                    this.f581a.startActivity(intent);
                    ac.m1581b();
                    C0419o.m1222a(this.f582b, this.f583c, this.f584d);
                }
            } catch (Exception e) {
                ac.m1589e(f580z[2], f580z[4]);
            }
        }
    }
}
