package cn.jpush.android.api;

import android.content.Context;
import android.widget.RemoteViews;
import cn.jpush.android.C0448e;

public class CustomPushNotificationBuilder extends BasicPushNotificationBuilder {
    /* renamed from: z */
    private static final String[] f499z;
    public int layout;
    public int layoutContentId;
    public int layoutIconDrawable = C0448e.f750b;
    public int layoutIconId;
    public int layoutTitleId;

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
        r12 = 24;
        r4 = 1;
        r1 = 0;
        r0 = 2;
        r3 = new java.lang.String[r0];
        r2 = "{m\u0013J\u0016uG?a&G";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000d:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0057;
    L_0x0014:
        r8 = r1;
    L_0x0015:
        r9 = r2;
        r10 = r8;
        r14 = r7;
        r7 = r2;
        r2 = r14;
    L_0x001a:
        r13 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x004d;
            case 1: goto L_0x004f;
            case 2: goto L_0x0051;
            case 3: goto L_0x0054;
            default: goto L_0x0021;
        };
    L_0x0021:
        r11 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
    L_0x0023:
        r11 = r11 ^ r13;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r2 != 0) goto L_0x002f;
    L_0x002b:
        r7 = r9;
        r10 = r8;
        r8 = r2;
        goto L_0x001a;
    L_0x002f:
        r7 = r2;
        r2 = r9;
    L_0x0031:
        if (r7 > r8) goto L_0x0015;
    L_0x0033:
        r7 = new java.lang.String;
        r7.<init>(r2);
        r2 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0048;
            default: goto L_0x003f;
        };
    L_0x003f:
        r5[r3] = r2;
        r0 = "GG?a&";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r5[r3] = r2;
        f499z = r6;
        return;
    L_0x004d:
        r11 = r12;
        goto L_0x0023;
    L_0x004f:
        r11 = r12;
        goto L_0x0023;
    L_0x0051:
        r11 = 96;
        goto L_0x0023;
    L_0x0054:
        r11 = 62;
        goto L_0x0023;
    L_0x0057:
        r8 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.CustomPushNotificationBuilder.<clinit>():void");
    }

    CustomPushNotificationBuilder(Context context) {
        super(context);
    }

    public CustomPushNotificationBuilder(Context context, int i, int i2, int i3, int i4) {
        super(context);
        this.layout = i;
        this.layoutIconId = i2;
        this.layoutTitleId = i3;
        this.layoutContentId = i4;
    }

    /* renamed from: a */
    final void mo2199a(String[] strArr) {
        super.mo2199a(strArr);
        this.layout = Integer.parseInt(strArr[5]);
        this.layoutIconId = Integer.parseInt(strArr[6]);
        this.layoutTitleId = Integer.parseInt(strArr[7]);
        this.layoutContentId = Integer.parseInt(strArr[8]);
        this.layoutIconDrawable = Integer.parseInt(strArr[9]);
    }

    /* renamed from: b */
    final RemoteViews mo2200b(String str) {
        RemoteViews remoteViews = new RemoteViews(this.a.getPackageName(), this.layout);
        remoteViews.setTextViewText(this.layoutTitleId, this.b);
        remoteViews.setImageViewResource(this.layoutIconId, this.layoutIconDrawable);
        remoteViews.setTextViewText(this.layoutContentId, str);
        return remoteViews;
    }

    /* renamed from: b */
    final String mo2201b() {
        return super.mo2201b() + f499z[1] + this.layout + f499z[1] + this.layoutIconId + f499z[1] + this.layoutTitleId + f499z[1] + this.layoutContentId + f499z[1] + this.layoutIconDrawable;
    }

    public String toString() {
        return new StringBuilder(f499z[0]).append(mo2201b()).toString();
    }
}
