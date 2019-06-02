package cn.jpush.android.service;

import cn.jpush.android.data.C0437i;
import cn.jpush.android.data.C0447s;
import cn.jpush.android.helpers.C0456f;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import com.google.android.gms.location.places.Place;

/* renamed from: cn.jpush.android.service.e */
final class C0466e implements C0465d {
    /* renamed from: z */
    private static final String[] f844z;
    /* renamed from: a */
    final /* synthetic */ boolean f845a;
    /* renamed from: b */
    final /* synthetic */ int f846b;
    /* renamed from: c */
    final /* synthetic */ DownloadService f847c;

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
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = ".9\u0017*{q";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000b:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0061;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r3;
        r10 = r8;
        r13 = r7;
        r7 = r3;
        r3 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0055;
            case 1: goto L_0x0058;
            case 2: goto L_0x005b;
            case 3: goto L_0x005e;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 68;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x0018;
    L_0x002d:
        r7 = r3;
        r3 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            case 1: goto L_0x0050;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r4] = r3;
        r0 = "#k.\u001d0n'`";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "#k>\u001d3a'5\u0013 j/`";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        f844z = r6;
        return;
    L_0x0055:
        r11 = 15;
        goto L_0x0021;
    L_0x0058:
        r11 = 75;
        goto L_0x0021;
    L_0x005b:
        r11 = 90;
        goto L_0x0021;
    L_0x005e:
        r11 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        goto L_0x0021;
    L_0x0061:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.e.<clinit>():void");
    }

    C0466e(DownloadService downloadService, boolean z, int i) {
        this.f847c = downloadService;
        this.f845a = z;
        this.f846b = i;
    }

    /* renamed from: a */
    public final void mo2221a(int i) {
        this.f847c.f792c.cancel(this.f846b);
        if (C0463b.m1486a(i)) {
            String str;
            this.f847c.f793d.f632v = true;
            C0459i.m1415a(this.f847c.f793d.f613c, 1011, this.f847c);
            String str2 = "";
            try {
                str = ((C0437i) this.f847c.f793d).f673K;
            } catch (Exception e) {
                str = str2;
            }
            C0459i.m1416a(this.f847c.f793d.f613c, Place.TYPE_SUBLOCALITY, C0490b.m1685b(this.f847c, str), this.f847c);
        }
        this.f847c.f793d.f633w = true;
        DownloadService.m1428a(this.f847c, this.f846b, this.f847c.f793d, i);
    }

    /* renamed from: a */
    public final void mo2222a(long j, long j2) {
        new StringBuilder(f844z[0]).append((int) ((((float) j) / ((float) j2)) * 100.0f)).append(f844z[2]).append(j).append(f844z[1]).append(j2);
        ac.m1581b();
        if (!this.f845a) {
            this.f847c.m1426a(this.f847c.f793d, this.f846b, j, j2);
        }
    }

    /* renamed from: a */
    public final void mo2223a(String str, boolean z) {
        int i;
        this.f847c.f793d.f634x = true;
        DownloadService.f789a.remove(this.f847c.f793d);
        int i2 = 1001;
        if (this.f847c.f793d.m1273a()) {
            C0437i c0437i = (C0437i) this.f847c.f793d;
            c0437i.f678P = str;
            c0437i.f679Q = false;
            if (C0456f.m1403a(c0437i.f669G, c0437i.f670H, this.f847c)) {
                i2 = 1003;
                c0437i.f679Q = true;
            }
        } else if (this.f847c.f793d.m1275b()) {
            ((C0447s) this.f847c.f793d).f747I = str;
            i = 1004;
            if (z) {
                i = 1013;
            }
            C0459i.m1415a(this.f847c.f793d.f613c, i, this.f847c);
            if (!this.f847c.f793d.m1279e()) {
                this.f847c.f803n.sendEmptyMessageDelayed(this.f846b, 500);
            }
            this.f847c.f793d.f633w = true;
            DownloadService.m1429a(this.f847c, this.f847c.f793d);
        } else if (this.f847c.f793d.m1279e()) {
            this.f847c.f793d.f609C = str;
        }
        i = i2;
        if (z) {
            i = 1013;
        }
        C0459i.m1415a(this.f847c.f793d.f613c, i, this.f847c);
        if (this.f847c.f793d.m1279e()) {
            this.f847c.f803n.sendEmptyMessageDelayed(this.f846b, 500);
        }
        this.f847c.f793d.f633w = true;
        DownloadService.m1429a(this.f847c, this.f847c.f793d);
    }
}
