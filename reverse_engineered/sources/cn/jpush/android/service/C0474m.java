package cn.jpush.android.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.helpers.ConnectingHelper;
import cn.jpush.android.util.ac;
import cn.jpush.p005b.p006a.p007a.C0517h;
import com.google.android.gms.location.places.Place;
import java.lang.ref.WeakReference;

/* renamed from: cn.jpush.android.service.m */
final class C0474m extends Handler {
    /* renamed from: z */
    private static final String[] f875z;
    /* renamed from: a */
    private final WeakReference<PushService> f876a;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
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
        r3 = " g\u0017Vx |\u0010Ws";
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
        r11 = 29;
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
        r0 = "+i\u0017\\q&E\u001cKn\"o\u001c\u0002";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "\u0016f\u001c@m&k\r]yy(\fVu\"f\u001dTx'(\u0014Kzc%Y";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        f875z = r6;
        return;
    L_0x0055:
        r11 = 67;
        goto L_0x0021;
    L_0x0058:
        r11 = 8;
        goto L_0x0021;
    L_0x005b:
        r11 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        goto L_0x0021;
    L_0x005e:
        r11 = 56;
        goto L_0x0021;
    L_0x0061:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.m.<clinit>():void");
    }

    public C0474m(PushService pushService) {
        this.f876a = new WeakReference(pushService);
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        new StringBuilder(f875z[1]).append(message.toString());
        ac.m1576a();
        PushService pushService = (PushService) this.f876a.get();
        if (pushService == null || pushService.f813f == null || !pushService.f813f.isAlive()) {
            ac.m1586d();
            return;
        }
        Context applicationContext = pushService.getApplicationContext();
        switch (message.what) {
            case 1001:
                ac.m1581b();
                pushService.stopSelf();
                ConnectingHelper.sendConnectionChanged(applicationContext, C0462a.f831b);
                return;
            case 1002:
                C0459i.m1414a(applicationContext, true, PushService.f808b, PushService.f810d, PushService.f809c);
                return;
            case 1003:
                pushService.stopSelf();
                return;
            case 1004:
                PushService.m1440a(pushService, true);
                return;
            case 1005:
                PushService.m1440a(pushService, false);
                return;
            case 1006:
                removeMessages(1011);
                removeMessages(1010);
                sendEmptyMessageDelayed(1011, 3000);
                return;
            case 1007:
                sendEmptyMessageDelayed(1010, 200);
                return;
            case 1009:
                pushService.f813f.m1528a(C0472k.f865a.get(), message.obj);
                return;
            case 1010:
                PushService.m1442a(pushService.f814g);
                return;
            case 1011:
                pushService.m1435a();
                return;
            case Place.TYPE_SUBLOCALITY /*1022*/:
                PushService.m1449d(pushService);
                return;
            case 7301:
                PushService.m1445b(pushService, message.getData().getLong(f875z[0]));
                return;
            case 7302:
                pushService.f813f.m1528a(message.getData().getLong(f875z[0]), message.obj);
                return;
            case 7303:
                PushService.m1448c(pushService, message.getData().getLong(f875z[0]));
                return;
            case 7304:
                PushService.m1437a(pushService, message.getData().getLong(f875z[0]));
                return;
            case 7306:
                ac.m1582b(PushService.f811z[0], new StringBuilder(PushService.f811z[1]).append(message.getData().getLong(f875z[0])).append(PushService.f811z[2]).append(message.arg2).toString());
                return;
            case 7307:
                C0472k.f866b.set(false);
                return;
            case 7501:
                pushService.f813f.m1529a((C0517h) message.obj, 0);
                return;
            case 7502:
                pushService.f813f.m1529a((C0517h) message.obj, 0);
                return;
            default:
                new StringBuilder(f875z[2]).append(message.what);
                ac.m1576a();
                return;
        }
    }
}
