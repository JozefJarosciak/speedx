package cn.jpush.android.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/* renamed from: cn.jpush.android.service.g */
final class C0468g extends Handler {
    /* renamed from: z */
    private static final String f849z;
    /* renamed from: a */
    final /* synthetic */ DownloadService f850a;
    /* renamed from: b */
    private Context f851b;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "v\tOFYAmoFE\u0005#cS\u000bR\"~L";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0027;
    L_0x000b:
        r3 = r0;
        r4 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x0010:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0035;
            case 1: goto L_0x0038;
            case 2: goto L_0x003b;
            case 3: goto L_0x003e;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 43;
    L_0x0019:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0010;
    L_0x0025:
        r1 = r0;
        r0 = r3;
    L_0x0027:
        if (r1 > r2) goto L_0x000b;
    L_0x0029:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f849z = r0;
        return;
    L_0x0035:
        r5 = 37;
        goto L_0x0019;
    L_0x0038:
        r5 = 77;
        goto L_0x0019;
    L_0x003b:
        r5 = 12;
        goto L_0x0019;
    L_0x003e:
        r5 = 39;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.g.<clinit>():void");
    }

    public C0468g(DownloadService downloadService, Context context) {
        this.f850a = downloadService;
        super(context.getMainLooper());
        this.f851b = context;
    }

    public final void handleMessage(Message message) {
        super.handleMessage(message);
        Toast.makeText(this.f851b, f849z, 1).show();
    }
}
