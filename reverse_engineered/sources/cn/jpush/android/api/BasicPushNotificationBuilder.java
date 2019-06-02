package cn.jpush.android.api;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.content.Context;
import cn.jpush.android.C0448e;

public class BasicPushNotificationBuilder extends DefaultPushNotificationBuilder {
    /* renamed from: z */
    private static final String[] f497z;
    /* renamed from: a */
    protected Context f498a;
    public String developerArg0 = f497z[2];
    public int notificationDefaults = -1;
    public int notificationFlags = 16;
    public int statusBarDrawable = C0448e.f750b;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 6;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "6\u000ek1{";
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
        r9 = 36;
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
        r1 = "\u000b0G\u0007G6\u000ek1{";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\r4B\u000bH\u0006!Q\u001ce\u001b6\u0004";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "'\u0004x\"\u0004\n>Z\u001aA\u0011%";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\n$G\u001aK\u0004";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u000b0G\u0007G";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        f497z = r4;
        return;
    L_0x0068:
        r9 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        goto L_0x001f;
    L_0x006b:
        r9 = 81;
        goto L_0x001f;
    L_0x006e:
        r9 = 52;
        goto L_0x001f;
    L_0x0071:
        r9 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.BasicPushNotificationBuilder.<clinit>():void");
    }

    public BasicPushNotificationBuilder(Context context) {
        if (context == null) {
            throw new IllegalArgumentException(f497z[3]);
        }
        this.f498a = context;
    }

    /* renamed from: a */
    static PushNotificationBuilder m1154a(String str) {
        String[] split = str.split(f497z[0]);
        Object obj = split[0];
        PushNotificationBuilder basicPushNotificationBuilder = f497z[5].equals(obj) ? new BasicPushNotificationBuilder(C0448e.f753e) : f497z[4].equals(obj) ? new CustomPushNotificationBuilder(C0448e.f753e) : new BasicPushNotificationBuilder(C0448e.f753e);
        basicPushNotificationBuilder.mo2199a(split);
        return basicPushNotificationBuilder;
    }

    @TargetApi(16)
    /* renamed from: a */
    final Notification mo2197a(Builder builder) {
        builder.setDefaults(this.notificationDefaults);
        builder.setSmallIcon(this.statusBarDrawable);
        Notification build = builder.build();
        build.flags = this.notificationFlags;
        return build;
    }

    /* renamed from: a */
    public final String mo2196a() {
        return this.developerArg0;
    }

    /* renamed from: a */
    final void mo2198a(Notification notification) {
        notification.defaults = this.notificationDefaults;
        notification.flags = this.notificationFlags;
        notification.icon = this.statusBarDrawable;
    }

    /* renamed from: a */
    void mo2199a(String[] strArr) {
        this.notificationDefaults = Integer.parseInt(strArr[1]);
        this.notificationFlags = Integer.parseInt(strArr[2]);
        this.statusBarDrawable = Integer.parseInt(strArr[3]);
        if (5 == strArr.length) {
            this.developerArg0 = strArr[4];
        }
    }

    /* renamed from: b */
    String mo2201b() {
        return this.notificationDefaults + f497z[0] + this.notificationFlags + f497z[0] + this.statusBarDrawable + f497z[0] + this.developerArg0;
    }

    public String toString() {
        return new StringBuilder(f497z[1]).append(mo2201b()).toString();
    }
}
