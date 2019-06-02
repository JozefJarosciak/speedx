package cn.jpush.android.p003b.p004a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Toast;
import cn.jpush.android.C0448e;
import cn.jpush.android.api.C0417m;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.data.C0437i;
import cn.jpush.android.data.C0447s;
import cn.jpush.android.helpers.C0456f;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.ui.PopWinActivity;
import cn.jpush.android.ui.PushActivity;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;
import java.lang.ref.WeakReference;

/* renamed from: cn.jpush.android.b.a.f */
public final class C0425f {
    /* renamed from: z */
    private static final String[] f585z;
    /* renamed from: a */
    private final WeakReference<Activity> f586a;
    /* renamed from: b */
    private final C0429c f587b;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 20;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "<\u001fF\u001dz\n\u0016H_x\b\u0011\u001eYv\u001c\u0014HRx\u000fZ\t\u001d";
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
            case 0: goto L_0x00fa;
            case 1: goto L_0x00fe;
            case 2: goto L_0x0102;
            case 3: goto L_0x0106;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 25;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\"\u0014R\\u\u0002\u001e\u0004P|\u0018\tEZ|?\u0003TX9\r\u0015V\u001d}\u0004\rJQv\n\u001e\u0004\u00109";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "<\u001fF\u001dz\n\u0016H_x\b\u0011\u001eNq\u0004\rpRx\u0018\u000e\u0004\u00109";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "?\u0012A\u001dx\b\u000eMKp\u001f\u0003\u0004Sx\u0006\u001f\u0004TjK\u0014QQuK\u0015V\u001d|\u0006\nPD5K=MK|K\u000fT\u00137";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\n\u0019PTo\u0002\u000e]sx\u0006\u001f\u0004\u00009";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "?\u0012A\u001dx\b\u000eMKp\u001f\u0003\u0004Sx\u0006\u001f\u0004TjK\u0013JKx\u0007\u0013@\u00119,\u0013RX9\u001e\n\n\u0013";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\b\u0014\nWi\u001e\tL\u0013x\u0005\u001eVRp\u000fTe~M\",mi@4*eoX&";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "<\u001fFkp\u000e\rlXu\u001b\u001fV";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = ">\u0014L\\w\u000f\u0016A\u001dp\u0005\u000eASmK@\u0004^wE\u0010THj\u0003TES}\u0019\u0015MY7\u0002\u0014PXw\u001fTe~M\"5jbX(.mkP?#{rI.4`x]";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "<\u001fF\u001dz\n\u0016H_x\b\u0011\u001e^u\u0002\u0019O\u001d4K\u001bGIp\u0004\u0014mY#";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "GZWUv\u001e\u0016@~x\u0005\u0019AQW\u0004\u000eM[p\b\u001bPTv\u0005@";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "GZWUv\u001e\u0016@~u\u0004\tA\u0007";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u0002\u0019KSP\u000fZWUv\u001e\u0016@\u001d{\u000eZMSmKW\u0004";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "GZQOuQ";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "<\u001fF\u001dz\n\u0016H_x\b\u0011\u001e^k\u000e\u001bPXJ\u0003\u0015VIz\u001e\u000e\u0004\u00109\u0005\u001bIX#";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\"\u0014R\\u\u0002\u001e\u0004\\z\u001f\u0013KSP\u000fZBOv\u0006ZsX{KW\u0004";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\b\u0014\nWi\u001e\tL\u0013x\u0005\u001eVRp\u000fTMSm\u000e\u0014P\u0013X(.mrW4(m~Q;/wuF(;hq[*9o";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "<\u001fF\u001dz\n\u0016H_x\b\u0011\u001eXa\u000e\u0019QI|&\tCp|\u0018\tEZ|KW\u0004";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = ">\u0014L\\w\u000f\u0016A\u001dp\u0005\u000eASmK@\u0004";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\b\u0014\nWi\u001e\tL\u0013x\u0005\u001eVRp\u000fTaeM9;";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        f585z = r4;
        return;
    L_0x00fa:
        r9 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        goto L_0x0020;
    L_0x00fe:
        r9 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        goto L_0x0020;
    L_0x0102:
        r9 = 36;
        goto L_0x0020;
    L_0x0106:
        r9 = 61;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.b.a.f.<clinit>():void");
    }

    public C0425f(Context context, C0429c c0429c) {
        this.f586a = new WeakReference((Activity) context);
        this.f587b = c0429c;
    }

    /* renamed from: g */
    private void m1228g(String str) {
        int parseInt;
        int i = 1100;
        try {
            parseInt = Integer.parseInt(str);
        } catch (Exception e) {
            new StringBuilder(f585z[15]).append(str);
            parseInt = i;
            ac.m1588e();
        }
        C0459i.m1415a(this.f587b.f613c, parseInt, (Context) this.f586a.get());
    }

    /* renamed from: a */
    public final void m1229a() {
        if (this.f586a.get() != null) {
            ac.m1581b();
            ((Activity) this.f586a.get()).finish();
        }
    }

    /* renamed from: a */
    public final void m1230a(String str) {
        Context context = (Context) this.f586a.get();
        if (context != null) {
            C0490b.m1689b(context, f585z[16], str);
        }
    }

    /* renamed from: a */
    public final void m1231a(String str, String str2) {
        new StringBuilder(f585z[4]).append(str);
        ac.m1581b();
        if (an.m1657a(str)) {
            ac.m1589e(f585z[7], f585z[3]);
        }
        Context context = (Context) this.f586a.get();
        if (context != null) {
            try {
                Class cls = Class.forName(str);
                if (cls != null) {
                    Intent intent = new Intent(context, cls);
                    intent.putExtra(f585z[6], str2);
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                }
            } catch (Exception e) {
                ac.m1589e(f585z[7], f585z[5]);
            }
        }
    }

    /* renamed from: a */
    public final void m1232a(String str, String str2, String str3) {
        int parseInt;
        int i = 0;
        try {
            parseInt = Integer.parseInt(str3);
        } catch (Exception e) {
            new StringBuilder(f585z[12]).append(str3);
            parseInt = i;
            ac.m1581b();
        }
        if (this.f586a.get() != null) {
            new StringBuilder(f585z[14]).append(str).append(f585z[13]).append(str2);
            ac.m1581b();
            C0490b.m1676a((Context) this.f586a.get(), str, str2, C0417m.m1206a(parseInt));
        }
    }

    /* renamed from: b */
    public final void m1233b() {
        if (this.f586a.get() != null && (this.f586a.get() instanceof PushActivity)) {
            ((PushActivity) this.f586a.get()).m1544a();
        }
    }

    /* renamed from: b */
    public final void m1234b(String str) {
        Context context = (Context) this.f586a.get();
        if (context != null) {
            try {
                C0490b.m1715h(context, str);
                ((Activity) context).finish();
            } catch (Exception e) {
                ac.m1589e(f585z[7], f585z[8]);
            }
        }
    }

    /* renamed from: b */
    public final void m1235b(String str, String str2) {
        Context context = (Context) this.f586a.get();
        if (context != null) {
            try {
                Intent intent = new Intent(str);
                intent.addCategory(context.getPackageName());
                intent.putExtra(f585z[19], str2);
                intent.setFlags(268435456);
                context.startActivity(intent);
            } catch (Exception e) {
                ac.m1589e(f585z[7], new StringBuilder(f585z[18]).append(str).toString());
            }
        }
    }

    /* renamed from: b */
    public final void m1236b(String str, String str2, String str3) {
        boolean z;
        if (this.f586a.get() != null) {
            boolean parseBoolean;
            new StringBuilder(f585z[9]).append(str).append(f585z[11]).append(str2).append(f585z[10]).append(str3);
            ac.m1581b();
            m1228g(str);
            try {
                parseBoolean = Boolean.parseBoolean(str2);
                try {
                    z = parseBoolean;
                    parseBoolean = Boolean.parseBoolean(str3);
                } catch (Exception e) {
                    z = parseBoolean;
                    parseBoolean = false;
                    if (parseBoolean) {
                        C0417m.m1215a((Context) this.f586a.get(), this.f587b, 0);
                    }
                    if (!z) {
                        ((Activity) this.f586a.get()).finish();
                    }
                }
            } catch (Exception e2) {
                parseBoolean = false;
                z = parseBoolean;
                parseBoolean = false;
                if (parseBoolean) {
                    C0417m.m1215a((Context) this.f586a.get(), this.f587b, 0);
                }
                if (!z) {
                    ((Activity) this.f586a.get()).finish();
                }
            }
            if (parseBoolean) {
                C0417m.m1215a((Context) this.f586a.get(), this.f587b, 0);
            }
            if (!z) {
                ((Activity) this.f586a.get()).finish();
            }
        }
    }

    /* renamed from: c */
    public final void m1237c(String str) {
        if (this.f586a.get() != null) {
            new StringBuilder(f585z[0]).append(str);
            ac.m1581b();
            Context context = (Context) this.f586a.get();
            C0429c c0429c = this.f587b;
            if (c0429c.m1273a()) {
                C0429c c0429c2 = (C0437i) c0429c;
                if (TextUtils.isEmpty(c0429c2.f673K)) {
                    c0429c2.f673K = str;
                }
                if (!TextUtils.isEmpty(c0429c2.f678P)) {
                    C0490b.m1706e(context, c0429c2.f678P);
                    C0417m.m1215a(context, c0429c2, 0);
                    C0417m.m1215a(context, c0429c2, 1);
                    return;
                }
            } else if (c0429c.m1275b()) {
                C0447s c0447s = (C0447s) c0429c;
                if (TextUtils.isEmpty(c0447s.f743E)) {
                    c0447s.f743E = str;
                }
                if (!TextUtils.isEmpty(c0447s.f747I)) {
                    context.startActivity(C0490b.m1667a(context, c0429c, false));
                    return;
                }
            } else {
                new StringBuilder(f585z[1]).append(c0429c.f625o);
                ac.m1586d();
                return;
            }
            ServiceInterface.m1461a(context, c0429c);
        }
    }

    /* renamed from: c */
    public final void m1238c(String str, String str2) {
        if (this.f586a.get() != null) {
            m1228g(str);
            m1237c(str2);
            C0417m.m1215a((Context) this.f586a.get(), this.f587b, 0);
            ((Activity) this.f586a.get()).finish();
        }
    }

    /* renamed from: d */
    public final void m1239d(String str) {
        if (this.f586a.get() != null) {
            new StringBuilder(f585z[2]).append(str);
            ac.m1581b();
            Toast.makeText((Context) this.f586a.get(), str, 0).show();
        }
    }

    /* renamed from: e */
    public final void m1240e(String str) {
        if (C0448e.f749a) {
            new StringBuilder(f585z[17]).append(str);
            ac.m1581b();
            if (this.f586a.get() != null) {
                C0456f.m1401a((Context) this.f586a.get(), str);
            }
        }
    }

    /* renamed from: f */
    public final void m1241f(String str) {
        if (this.f586a.get() != null && (this.f586a.get() instanceof PopWinActivity)) {
            ((PopWinActivity) this.f586a.get()).m1541a(str);
        }
    }
}
