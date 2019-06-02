package cn.jpush.android.api;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.RemoteViews;
import cn.jpush.android.C0404a;
import cn.jpush.android.C0448e;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.data.C0441m;
import cn.jpush.android.helpers.C0452b;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.service.PushReceiver;
import cn.jpush.android.service.PushService;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.C0503p;
import cn.jpush.android.util.C0505r;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;
import com.google.common.primitives.Ints;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.zip.Adler32;

/* renamed from: cn.jpush.android.api.m */
public final class C0417m {
    /* renamed from: z */
    private static final String[] f564z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 37;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u001aNa\"$\u0015\u0017v'.\u001aC[$?\u0012K|(*\u000fDz%kV\r{$?\u0012K|(*\u000fDz%\u0002\u001f\u0017";
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
            case 0: goto L_0x01b5;
            case 1: goto L_0x01b9;
            case 2: goto L_0x01bd;
            case 3: goto L_0x01c1;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 75;
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
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            case 22: goto L_0x0121;
            case 23: goto L_0x012c;
            case 24: goto L_0x0137;
            case 25: goto L_0x0142;
            case 26: goto L_0x014d;
            case 27: goto L_0x0158;
            case 28: goto L_0x0163;
            case 29: goto L_0x016e;
            case 30: goto L_0x0179;
            case 31: goto L_0x0184;
            case 32: goto L_0x018f;
            case 33: goto L_0x019a;
            case 34: goto L_0x01a5;
            case 35: goto L_0x01b0;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0015Ba\"-\u0012Nt?\"\u0014C";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "U";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u000bLg*&\u001eYp9k\tHf\u001f2\u000bH5$9[K|.'\u001fct&.\b\rp99\u0014_;";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003[\u0004\u001f2k\\\b\n/dZ\u0005\u00142i";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = ")\t|/";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = ")\ty*2\u0014Xa";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\bYt?.$Ot9\u0014\u0012@t,.$[|.<";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u0016Hf8*\u001cHJ\"&\u001aJp\u00148\u000fLa.\u0014\u0019Lg\u0014'\u001aTz>?";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u001aNa\"$\u0015\u0017v'.\u001aCT''5Ba\"-\u0012Nt?\"\u0014C5fk\u0016Hf8*\u001cH\\/q";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003]\u001f\u00067rE\n\u001f3";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003|%?\u001eCae\u00054y\\\r\u00028lA\u0002\u00045rG\u000e\b>dC\u000e\u000f";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003]\u001f\u00067rG\u000e\u0018";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "5Ba\"-\u0012Nt?\"\u0014C].'\u000bHg";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "U]p9&\u0012^f\"$\u0015\u0003_\u001b\u001e(eJ\u0006\u000e(~T\f\u000e";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "(H{/k\u000bXf#k\tHv.\"\rHqk)\tBt/(\u001a^ak?\u0014\rq.=\u001eAz;.\t\rq.-\u0012Cp/k\tHv.\"\rHg";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u001dDy.qT\u0002";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0013Ya;qT\u0002";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003[\u0004\u001f2k\\\b\n/dZ\u0005\u0014?hC\u000e\u00074}P\u0019\u0014:R{";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u001aNa\"$\u0015\u0017v'.\u001aC[$?\u0012K|(*\u000fDz%kV\rx.8\bLr.\u0002\u001f\u0017";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "\bHa\u0007*\u000fHf?\u000e\rH{?\u0002\u0015Kz";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\u001aCq9$\u0012I;*;\u000b\u0003[$?\u0012K|(*\u000fDz%";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003|%?\u001eCae\u0006.aA\u0002\u0014+Z\b\u000e(~";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003P\u0013\u001f)l";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "\u0015Ba\"-\u0012Nt?\"\u0014CJ\"/";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u001a]e";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003[\u0004\u001f2k\\\b\n/dZ\u0005\u0014/tE\u000e";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "\u0016Xy?\"$Yl;.";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003[\u0004\u001f2k\\\b\n/dZ\u0005\u00148b[\u001f\u000e5yJ\u001f\u0002/aP";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003X\u0018\f$dQ";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003|%?\u001eCae\u00054y\\\r\u00028lA\u0002\u00045rZ\u001b\u000e5hQ\u0014\u001b)bM\u0012e";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "<Bak\u0005.aYk%\u0014Y|-\"\u0018La\"$\u0015\u00035\f\"\rH5>;[Yzk8\u0013Bbe";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "\u001fHy\"=\u001e_lk9\u0012N}k;\u000e^}k?\u0002]pqk";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003T\u0007\u000e)y";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = "\u0018C;!;\u000e^}e*\u0015Ig$\"\u001f\u0003|%?\u001eCae\u00054y\\\r\u00028lA\u0002\u00045rZ\u001b\u000e5hQ";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        r2 = 35;
        r1 = ")\tq9*\fLw'.";
        r0 = 34;
        r3 = r4;
        goto L_0x0009;
    L_0x01a5:
        r3[r2] = r1;
        r2 = 36;
        r1 = "\u0011]`8#$Cz?\"\u001dDv*?\u0012B{\u0014\"\u0018B{";
        r0 = 35;
        r3 = r4;
        goto L_0x0009;
    L_0x01b0:
        r3[r2] = r1;
        f564z = r4;
        return;
    L_0x01b5:
        r9 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        goto L_0x0020;
    L_0x01b9:
        r9 = 45;
        goto L_0x0020;
    L_0x01bd:
        r9 = 21;
        goto L_0x0020;
    L_0x01c1:
        r9 = 75;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.m.<clinit>():void");
    }

    /* renamed from: a */
    public static int m1206a(int i) {
        switch (i) {
            case -1:
                int intValue;
                try {
                    intValue = ((Integer) C0417m.m1210a(f564z[35], new String[]{f564z[36]}).get(f564z[36])).intValue();
                } catch (Exception e) {
                    intValue = 0;
                    ac.m1588e();
                }
                return intValue <= 0 ? 17301618 : intValue;
            case 0:
                return 17301647;
            case 2:
                return 17301618;
            case 3:
                return 17301567;
            default:
                return 17301586;
        }
    }

    /* renamed from: a */
    public static int m1207a(C0429c c0429c, int i) {
        String str = c0429c.f613c;
        if (!an.m1657a(c0429c.f614d)) {
            str = c0429c.f614d;
        }
        return C0417m.m1208a(str, i);
    }

    /* renamed from: a */
    private static int m1208a(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            ac.m1581b();
            return 0;
        }
        try {
            return Integer.valueOf(str).intValue();
        } catch (Exception e) {
            ac.m1586d();
            Adler32 adler32 = new Adler32();
            adler32.update(str.getBytes());
            int value = (int) adler32.getValue();
            if (value < 0) {
                value = Math.abs(value);
            }
            value += 13889152 * i;
            return value < 0 ? Math.abs(value) : value;
        }
    }

    /* renamed from: a */
    public static Notification m1209a(Context context, int i, Intent intent, C0429c c0429c, boolean z, boolean z2) {
        int i2;
        int i3 = -1;
        if (z) {
            ac.m1581b();
            try {
                i2 = context.getPackageManager().getPackageInfo(context.getPackageName(), 256).applicationInfo.icon;
            } catch (NameNotFoundException e) {
                i2 = i3;
                ac.m1591g();
            }
        } else {
            i2 = i3;
        }
        if (i2 < 0) {
            ac.m1586d();
            return null;
        }
        PendingIntent broadcast = z2 ? PendingIntent.getBroadcast(context, i, intent, 134217728) : PendingIntent.getActivity(context, i, intent, 134217728);
        Bitmap decodeFile;
        Integer num;
        Integer num2;
        if (VERSION.SDK_INT >= 11) {
            Builder ticker = new Builder(context.getApplicationContext()).setWhen(System.currentTimeMillis()).setSmallIcon(i2).setTicker(c0429c.f630t);
            if (c0429c.f618h) {
                ticker.setDefaults(3);
                if (C0490b.m1727n(context)) {
                    ticker.setDefaults(0);
                }
            }
            if (an.m1657a(c0429c.f607A)) {
                ticker.setContentTitle(c0429c.f629s).setContentText(c0429c.f630t).setContentIntent(broadcast);
            } else {
                try {
                    decodeFile = BitmapFactory.decodeFile(c0429c.f607A);
                    if (decodeFile != null) {
                        num = (Integer) C0417m.m1210a(f564z[5], new String[]{f564z[7]}).get(f564z[7]);
                        num2 = (Integer) C0417m.m1210a(f564z[6], new String[]{f564z[8]}).get(f564z[8]);
                        if (num == null || num2 == null || num.intValue() <= 0 || num2.intValue() <= 0) {
                            ac.m1586d();
                            return null;
                        }
                        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), num2.intValue());
                        remoteViews.setImageViewBitmap(num.intValue(), decodeFile);
                        ticker.setContent(remoteViews);
                        ticker.setContentIntent(broadcast);
                    } else {
                        ac.m1586d();
                        return null;
                    }
                } catch (Exception e2) {
                    ac.m1592h();
                    return null;
                }
            }
            Notification notification = ticker.getNotification();
            notification.flags = C0417m.m1219b(c0429c.f628r);
            return notification;
        }
        Notification notification2 = new Notification();
        notification2.when = System.currentTimeMillis();
        notification2.icon = i2;
        notification2.tickerText = c0429c.f630t;
        notification2.flags = C0417m.m1219b(c0429c.f628r);
        if (c0429c.f618h) {
            notification2.defaults = 3;
            if (C0490b.m1727n(context)) {
                notification2.defaults = 0;
            }
        }
        if (an.m1657a(c0429c.f607A)) {
            C0417m.m1211a(notification2, context, c0429c.f629s, c0429c.f630t, broadcast);
        } else {
            try {
                decodeFile = BitmapFactory.decodeFile(c0429c.f607A);
                if (decodeFile != null) {
                    num = (Integer) C0417m.m1210a(f564z[5], new String[]{f564z[7]}).get(f564z[7]);
                    num2 = (Integer) C0417m.m1210a(f564z[6], new String[]{f564z[8]}).get(f564z[8]);
                    if (num == null || num2 == null || num.intValue() <= 0 || num2.intValue() <= 0) {
                        ac.m1586d();
                        return null;
                    }
                    remoteViews = new RemoteViews(context.getPackageName(), num2.intValue());
                    remoteViews.setImageViewBitmap(num.intValue(), decodeFile);
                    notification2.contentView = remoteViews;
                    notification2.contentIntent = broadcast;
                } else {
                    ac.m1586d();
                    return null;
                }
            } catch (Exception e3) {
                ac.m1592h();
                return null;
            }
        }
        return notification2;
    }

    /* renamed from: a */
    public static HashMap<String, Integer> m1210a(String str, String[] strArr) {
        int i = 0;
        if (an.m1657a(str) || strArr.length == 0) {
            throw new NullPointerException(f564z[3]);
        }
        HashMap<String, Integer> hashMap = new HashMap();
        try {
            int length;
            for (Class cls : Class.forName(C0448e.f753e.getPackageName() + f564z[2]).getDeclaredClasses()) {
                if (cls.getName().contains(str)) {
                    length = strArr.length;
                    while (i < length) {
                        String str2 = strArr[i];
                        hashMap.put(str2, Integer.valueOf(cls.getDeclaredField(str2).getInt(str2)));
                        i++;
                    }
                    return hashMap;
                }
            }
        } catch (Exception e) {
            ac.m1592h();
        }
        return hashMap;
    }

    /* renamed from: a */
    public static void m1211a(Notification notification, Context context, String str, String str2, PendingIntent pendingIntent) {
        try {
            Class.forName(f564z[21]).getDeclaredMethod(f564z[20], new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{context, str, str2, pendingIntent});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static void m1212a(Context context) {
        while (true) {
            Integer valueOf = Integer.valueOf(C0452b.m1368a());
            if (valueOf.intValue() != 0) {
                C0417m.m1220b(context, valueOf.intValue());
            } else {
                return;
            }
        }
    }

    /* renamed from: a */
    public static void m1213a(Context context, int i) {
        if (i > 0) {
            for (int i2 = 0; i2 < i; i2++) {
                Integer valueOf = Integer.valueOf(C0452b.m1368a());
                if (valueOf.intValue() != 0) {
                    C0417m.m1220b(context, valueOf.intValue());
                }
            }
        }
    }

    /* renamed from: a */
    public static void m1214a(Context context, C0429c c0429c) {
        if (Thread.currentThread().getId() == PushService.f807a) {
            ac.m1584c();
            new Thread(new C0418n(context, c0429c)).start();
            return;
        }
        C0417m.m1221b(context, c0429c);
    }

    /* renamed from: a */
    public static void m1215a(Context context, C0429c c0429c, int i) {
        new StringBuilder(f564z[19]).append(c0429c.f613c);
        ac.m1581b();
        if (context == null) {
            context = C0448e.f753e;
        }
        ((NotificationManager) context.getSystemService(f564z[1])).cancel(C0417m.m1207a(c0429c, i));
    }

    /* renamed from: a */
    public static void m1216a(Context context, String str) {
        new StringBuilder(f564z[9]).append(str);
        ac.m1581b();
        if (context == null) {
            context = C0448e.f753e;
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(f564z[1]);
        notificationManager.cancel(C0417m.m1208a(str, 0));
        notificationManager.cancel(C0417m.m1208a(str, 1));
    }

    /* renamed from: a */
    private static void m1217a(Context context, Map<String, String> map, int i, String str, String str2, C0429c c0429c) {
        ac.m1582b(f564z[13], f564z[15]);
        Intent intent = new Intent(f564z[11]);
        C0417m.m1218a(intent, (Map) map, i);
        if (!an.m1657a(str)) {
            intent.putExtra(f564z[18], str);
        }
        if (c0429c.m1279e() && (c0429c instanceof C0441m)) {
            C0441m c0441m = (C0441m) c0429c;
            if (!(c0441m.f710G == 0 || c0441m.f710G == 4)) {
                if (c0441m.f715L != null && c0441m.f715L.startsWith(f564z[16])) {
                    c0441m.f715L = c0441m.f715L.replaceFirst(f564z[16], "");
                    intent.putExtra(f564z[10], c0441m.f715L);
                }
                if (c0441m.f712I != null && c0441m.f712I.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    String b = C0503p.m1783b(context, c0429c.f613c);
                    Iterator it = c0441m.f712I.iterator();
                    while (it.hasNext()) {
                        String str3 = (String) it.next();
                        if (str3.startsWith(f564z[17])) {
                            str3 = C0505r.m1795c(str3);
                        }
                        if (an.m1657a(stringBuilder.toString())) {
                            stringBuilder.append(b).append(str3);
                        } else {
                            stringBuilder.append(",").append(b).append(str3);
                        }
                    }
                    intent.putExtra(f564z[12], stringBuilder.toString());
                }
            }
        }
        intent.addCategory(str2);
        context.sendBroadcast(intent, str2 + f564z[14]);
    }

    /* renamed from: a */
    private static void m1218a(Intent intent, Map<String, String> map, int i) {
        for (String str : map.keySet()) {
            intent.putExtra(str, (String) map.get(str));
        }
        if (i != 0) {
            intent.putExtra(f564z[4], i);
        }
    }

    /* renamed from: b */
    private static int m1219b(int i) {
        switch (i) {
            case 1:
                return 16;
            case 2:
                return 32;
            default:
                return 1;
        }
    }

    /* renamed from: b */
    public static void m1220b(Context context, int i) {
        new StringBuilder(f564z[0]).append(i);
        ac.m1581b();
        if (context == null) {
            context = C0448e.f753e;
        }
        ((NotificationManager) context.getSystemService(f564z[1])).cancel(i);
    }

    /* renamed from: b */
    public static void m1221b(Context context, C0429c c0429c) {
        ac.m1576a();
        int a = C0417m.m1207a(c0429c, 0);
        if (c0429c.f618h && c0429c.f615e) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(f564z[1]);
            if (c0429c instanceof C0441m) {
                String str = c0429c.f630t;
                CharSequence charSequence = c0429c.f629s;
                String str2 = c0429c.f622l;
                String packageName = an.m1657a(c0429c.f623m) ? context.getPackageName() : c0429c.f623m;
                Map hashMap = new HashMap();
                hashMap.put(f564z[29], c0429c.f613c);
                hashMap.put(f564z[33], str);
                if (!TextUtils.isEmpty(charSequence)) {
                    hashMap.put(f564z[28], charSequence);
                }
                if (!an.m1657a(str2)) {
                    hashMap.put(f564z[23], str2);
                }
                if (an.m1657a(str)) {
                    C0417m.m1217a(context, hashMap, 0, "", packageName, c0429c);
                    return;
                }
                PushNotificationBuilder b = JPushInterface.m1168b(c0429c.f616f);
                String a2 = b.mo2196a();
                Notification a3 = b.mo2195a(str, hashMap);
                if (a3 == null || an.m1657a(str)) {
                    ac.m1587d(f564z[13], f564z[31]);
                    return;
                }
                PendingIntent activity;
                Intent a4;
                if (c0429c.m1279e()) {
                    new StringBuilder(f564z[32]).append(((C0441m) c0429c).f710G);
                    ac.m1584c();
                    a4 = (3 == ((C0441m) c0429c).f710G || 4 == ((C0441m) c0429c).f710G || ((C0441m) c0429c).f710G == 0) ? C0490b.m1667a(context, c0429c, false) : 2 == ((C0441m) c0429c).f710G ? C0490b.m1666a(context, c0429c) : C0490b.m1667a(context, c0429c, false);
                    activity = PendingIntent.getActivity(context, a, a4, 134217728);
                } else {
                    if (C0490b.m1701d(context, PushReceiver.class.getCanonicalName())) {
                        a4 = new Intent(new StringBuilder(f564z[30]).append(UUID.randomUUID().toString()).toString());
                        a4.setClass(context, PushReceiver.class);
                        a4.putExtra(f564z[26], c0429c.f617g);
                    } else {
                        ac.m1584c();
                        a4 = new Intent(f564z[34]);
                        a4.addCategory(packageName);
                    }
                    C0417m.m1218a(a4, hashMap, a);
                    a4.putExtra(f564z[33], str);
                    a4.putExtra(f564z[25], packageName);
                    if (!an.m1657a(a2)) {
                        a4.putExtra(f564z[18], a2);
                    }
                    activity = PendingIntent.getBroadcast(context, 0, a4, Ints.MAX_POWER_OF_TWO);
                }
                a3.contentIntent = activity;
                if (!JPushInterface.m1167a(c0429c.f616f)) {
                    if (1 == c0429c.f617g) {
                        c0429c.f628r = 1;
                    }
                    a3.flags = C0417m.m1219b(c0429c.f628r);
                    if (a3.defaults == 0) {
                        a3.defaults = 3;
                    }
                }
                if (C0490b.m1727n(context)) {
                    a3.defaults = 0;
                }
                if (a3 != null) {
                    notificationManager.notify(a, a3);
                }
                if (1 != c0429c.f617g) {
                    if (C0448e.f763o == null && C0448e.f762n) {
                        Intent intent = new Intent(context, PushService.class);
                        intent.setAction(f564z[22]);
                        Bundle bundle = new Bundle();
                        bundle.putInt(f564z[27], 9);
                        bundle.putInt(f564z[24], a);
                        intent.putExtras(bundle);
                        context.startService(intent);
                    } else {
                        if (!C0452b.m1371b(a)) {
                            C0452b.m1369a(a);
                        }
                        if (C0452b.m1370b() > C0404a.m1073b(context)) {
                            int a5 = C0452b.m1368a();
                            if (a5 != 0) {
                                C0417m.m1220b(context, a5);
                            }
                        }
                    }
                    C0459i.m1415a(c0429c.f613c, 1018, context);
                }
                C0417m.m1217a(context, hashMap, a, a2, packageName, c0429c);
            }
        }
    }
}
