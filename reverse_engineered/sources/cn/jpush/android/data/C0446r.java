package cn.jpush.android.data;

import android.content.Context;
import cn.jpush.android.api.C0417m;
import cn.jpush.android.helpers.C0456f;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.data.r */
public final class C0446r extends C0437i {
    /* renamed from: R */
    private static final String[] f741R;

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
        r2 = "yf\u0000KDpy\u001c";
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
        r11 = 55;
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
        r0 = "Mx\u0000zXoxKg_waKyX|sK9\u0017";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f741R = r6;
        return;
    L_0x004b:
        r11 = 24;
        goto L_0x0021;
    L_0x004e:
        r11 = 22;
        goto L_0x0021;
    L_0x0051:
        r11 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        goto L_0x0021;
    L_0x0054:
        r11 = 20;
        goto L_0x0021;
    L_0x0057:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.r.<clinit>():void");
    }

    public C0446r() {
        this.o = 3;
        this.L = null;
    }

    /* renamed from: a */
    public final void mo2219a(Context context) {
        ac.m1576a();
        if (C0456f.m1403a(this.G, this.H, context)) {
            ServiceInterface.m1461a(context, (C0429c) this);
        } else if (this.J == 1) {
            context.startActivity(C0490b.m1667a(context, (C0429c) this, true));
        } else if (this.J == 0) {
            C0417m.m1221b(context, (C0429c) this);
        } else {
            new StringBuilder(f741R[1]).append(this.J);
            ac.m1581b();
        }
    }

    /* renamed from: a */
    public final boolean mo2220a(Context context, JSONObject jSONObject) {
        ac.m1576a();
        boolean a = super.mo2220a(context, jSONObject);
        this.J = jSONObject.optInt(f741R[0], 0);
        return a;
    }
}
