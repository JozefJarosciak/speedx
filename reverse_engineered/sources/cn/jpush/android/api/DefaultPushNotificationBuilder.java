package cn.jpush.android.api;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.os.Build.VERSION;
import android.widget.RemoteViews;
import cn.jpush.android.C0448e;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;
import java.util.Map;

public class DefaultPushNotificationBuilder implements PushNotificationBuilder {
    /* renamed from: z */
    private static final String[] f495z;
    /* renamed from: b */
    protected String f496b;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 6;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "C\u0012{-kv\fa*jO\u000bm?w@\r`\u0001jJ\r`";
        r0 = -1;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002c;
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
            case 0: goto L_0x0067;
            case 1: goto L_0x006a;
            case 2: goto L_0x006d;
            case 3: goto L_0x0070;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 3;
    L_0x001e:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002a;
    L_0x0026:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002a:
        r5 = r1;
        r1 = r7;
    L_0x002c:
        if (r5 > r6) goto L_0x0011;
    L_0x002e:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0042;
            case 1: goto L_0x004a;
            case 2: goto L_0x0052;
            case 3: goto L_0x005a;
            case 4: goto L_0x0062;
            default: goto L_0x003a;
        };
    L_0x003a:
        r3[r2] = r1;
        r2 = 1;
        r1 = "g\r.0l]\u000bh7`H\u0016g1m\t\u0001a0wL\fz~wFB}6l^L.\u0019j_\u0007.+s\u0007";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0042:
        r3[r2] = r1;
        r2 = 2;
        r1 = "m\u0007h?vE\u0016^+pA,a*jO\u000bm?w@\r`\u001cv@\u000ej;q";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004a:
        r3[r2] = r1;
        r2 = 3;
        r1 = "O\u0003g2fMBz1#N\u0007z~bY\u0012b7`H\u0016g1m\t\u000b`8l\t\u0003`:#@\u0001a0-";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0052:
        r3[r2] = r1;
        r2 = 4;
        r1 = "J\f 4s\\\u0011fpbG\u0006|1jML@\u0011W`$G\u001dB}+A\u0010\\j-@\nFg6Q\nJ}.K";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005a:
        r3[r2] = r1;
        r2 = 5;
        r1 = "M\u0010o)bK\u000ek";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0062:
        r3[r2] = r1;
        f495z = r4;
        return;
    L_0x0067:
        r9 = 41;
        goto L_0x001e;
    L_0x006a:
        r9 = 98;
        goto L_0x001e;
    L_0x006d:
        r9 = 14;
        goto L_0x001e;
    L_0x0070:
        r9 = 94;
        goto L_0x001e;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.DefaultPushNotificationBuilder.<clinit>():void");
    }

    @TargetApi(16)
    /* renamed from: a */
    Notification mo2197a(Builder builder) {
        return builder.build();
    }

    /* renamed from: a */
    public final Notification mo2195a(String str, Map<String, String> map) {
        if (an.m1657a(str)) {
            ac.m1587d(f495z[2], f495z[1]);
            return null;
        } else if (C0448e.f753e != null) {
            int i;
            int identifier = C0448e.f753e.getResources().getIdentifier(f495z[0], f495z[5], C0448e.f753e.getPackageName());
            if (identifier != 0) {
                i = identifier;
            } else if (C0448e.f750b != 0) {
                i = C0448e.f750b;
            } else {
                try {
                    identifier = C0448e.f753e.getPackageManager().getApplicationInfo(C0448e.f753e.getPackageName(), 0).icon;
                    ac.m1584c();
                    i = identifier;
                } catch (Throwable e) {
                    ac.m1583b(f495z[2], f495z[3], e);
                    return null;
                }
            }
            if (map.containsKey(f495z[4])) {
                this.f496b = (String) map.get(f495z[4]);
            }
            if (this.f496b == null) {
                this.f496b = C0448e.f752d;
            }
            RemoteViews b;
            if (VERSION.SDK_INT >= 16) {
                Builder smallIcon = new Builder(C0448e.f753e).setContentTitle(this.f496b).setContentText(str).setTicker(str).setSmallIcon(i);
                b = mo2200b(str);
                if (b != null) {
                    smallIcon.setContent(b);
                } else {
                    ac.m1584c();
                }
                return mo2197a(smallIcon);
            }
            Notification notification = new Notification(i, str, System.currentTimeMillis());
            mo2198a(notification);
            if (this.f496b == null) {
                this.f496b = C0448e.f752d;
            }
            b = mo2200b(str);
            if (b != null) {
                notification.contentView = b;
                return notification;
            }
            C0417m.m1211a(notification, C0448e.f753e, this.f496b, str, null);
            return notification;
        } else {
            ac.m1586d();
            return null;
        }
    }

    /* renamed from: a */
    public String mo2196a() {
        return null;
    }

    /* renamed from: a */
    void mo2198a(Notification notification) {
    }

    /* renamed from: b */
    RemoteViews mo2200b(String str) {
        return null;
    }
}
