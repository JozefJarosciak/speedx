package cn.jpush.android.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.RemoteViews;
import cn.jpush.android.C0448e;
import cn.jpush.android.api.C0417m;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.data.C0431b;
import cn.jpush.android.data.C0437i;
import cn.jpush.android.data.C0447s;
import cn.jpush.android.helpers.C0456f;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.apache.http.protocol.HttpRequestExecutor;

public class DownloadService extends IntentService {
    /* renamed from: a */
    public static ConcurrentLinkedQueue<C0429c> f789a = new ConcurrentLinkedQueue();
    /* renamed from: b */
    private static Bundle f790b;
    /* renamed from: z */
    private static final String[] f791z;
    /* renamed from: c */
    private NotificationManager f792c;
    /* renamed from: d */
    private C0429c f793d;
    /* renamed from: e */
    private C0468g f794e;
    /* renamed from: f */
    private Notification f795f;
    /* renamed from: g */
    private Builder f796g;
    /* renamed from: h */
    private RemoteViews f797h;
    /* renamed from: i */
    private Integer f798i = Integer.valueOf(0);
    /* renamed from: j */
    private Integer f799j = Integer.valueOf(0);
    /* renamed from: k */
    private Integer f800k = Integer.valueOf(0);
    /* renamed from: l */
    private Integer f801l = Integer.valueOf(0);
    /* renamed from: m */
    private Integer f802m = Integer.valueOf(0);
    /* renamed from: n */
    private Handler f803n = new C0467f(this);

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 22;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "c\u00047\u0005;H\n$82U\u001d)\b2";
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
            case 0: goto L_0x0117;
            case 1: goto L_0x011b;
            case 2: goto L_0x011f;
            case 3: goto L_0x0123;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 87;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "b\u0013%\b\"S\u000e`\u0004;CK$\u0004 I\u0007/\n3\u0007\u001f!\u0018<\u0007F`\u0018>]\u000ez";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "t\u001f!\u0019#N\u0005'K#HK$\u0004 I\u0007/\n3\u0007F`\u00062T\u0018!\f2n\u000fz";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "I\u0004446R\u001f/\u0019\"I";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "i\u0004`\u000e9CK.\u0004#N\r)\b6S\u0002/\u0005y\u0007\u00023K1N\u0007%;6S\u0003`\u000e:W\u001f9Kh\u0007F`";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "J\u00045\u0005#B\u000f";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "E\u0004$\u0012";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "丬輖贄滻奦敯ど讷穦呙炞冐醍旛乜轚ｪ";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "彴刦缑纷乚又畃あ穦呙伽續纭习輪Ｆ";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "丬輖奱赎さ诐穦呎烒冬釪旛之輖ｖ";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "I\u00044\u00021N\b!\u001f>H\u0005\u001f\u00076^\u00045\u001f";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "S\u000e8\u001f\bW\u0019/\f%B\u00183";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "W\u0019/\f%B\u0018345F\u0019";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "N\b/\u0005";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "uO)\u000f";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "uO,\n.H\u001e4";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "S\u00024\u00072";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "I\u00044\u00021N\b!\u001f>H\u0005";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "曓旛爈杇嶥丬輖富殾｛诐烒冻寢袒〥";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "宮袮卅嶙乜轚寧殕ｧ讠炞冐寉袮さ";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "D\u0005n\u0001'R\u0018(E6I\u000f2\u0004>CE)\u0005#B\u00054E\u0019h?\t-\u001ed*\u0014\"\u0018i4\t%\u0004s*\f'\bd'\t(\u001cb/";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "丬輖乭Ey\tK";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        f791z = r4;
        r0 = new java.util.concurrent.ConcurrentLinkedQueue;
        r0.<init>();
        f789a = r0;
        return;
    L_0x0117:
        r9 = 39;
        goto L_0x0020;
    L_0x011b:
        r9 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        goto L_0x0020;
    L_0x011f:
        r9 = 64;
        goto L_0x0020;
    L_0x0123:
        r9 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.DownloadService.<clinit>():void");
    }

    public DownloadService() {
        super(f791z[0]);
    }

    /* renamed from: a */
    public static void m1425a(Context context) {
        new StringBuilder(f791z[1]).append(f789a.size());
        ac.m1581b();
        ArrayList arrayList = new ArrayList();
        while (true) {
            C0429c c0429c = (C0429c) f789a.poll();
            if (c0429c == null) {
                break;
            } else if (c0429c.f633w) {
                new StringBuilder(f791z[2]).append(c0429c.f613c);
                ac.m1581b();
                ServiceInterface.m1461a(context, c0429c);
            } else {
                ac.m1576a();
                arrayList.add(c0429c);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            f789a.offer((C0429c) it.next());
        }
    }

    /* renamed from: a */
    private void m1426a(C0429c c0429c, int i, long j, long j2) {
        int i2;
        if (VERSION.SDK_INT >= 11) {
            if (this.f796g == null) {
                this.f796g = new Builder(getApplicationContext()).setSmallIcon(17301633).setWhen(System.currentTimeMillis()).setDefaults(4).setOngoing(true);
                this.f796g.setContentIntent(PendingIntent.getActivity(getApplicationContext(), i, new Intent(), 134217728));
            }
            CharSequence charSequence = c0429c.f629s;
            CharSequence charSequence2 = f791z[21];
            i2 = (int) ((((float) j) / ((float) j2)) * 100.0f);
            if (j2 > 0) {
                charSequence2 = charSequence2 + i2 + "%";
            }
            if (this.f802m == null || this.f802m.intValue() <= 0) {
                this.f796g.setContentTitle(charSequence).setContentText(charSequence2).setContentIntent(PendingIntent.getActivity(getApplicationContext(), i, new Intent(), 134217728));
            } else {
                if (this.f797h == null) {
                    this.f797h = new RemoteViews(getPackageName(), this.f802m.intValue());
                    this.f797h.setTextViewText(this.f798i.intValue(), charSequence);
                    this.f797h.setImageViewResource(this.f800k.intValue(), C0448e.f750b);
                }
                this.f797h.setTextViewText(this.f801l.intValue(), i2 + "%");
                this.f797h.setProgressBar(this.f799j.intValue(), 100, i2, false);
                this.f796g.setContent(this.f797h);
            }
            this.f792c.notify(i, this.f796g.getNotification());
            return;
        }
        if (this.f795f == null) {
            this.f795f = new Notification();
            this.f795f.icon = 17301633;
            this.f795f.when = System.currentTimeMillis();
            this.f795f.flags = 2;
            this.f795f.defaults = 4;
            this.f795f.contentIntent = PendingIntent.getActivity(getApplicationContext(), i, new Intent(), 134217728);
        }
        Object obj = c0429c.f629s;
        String str = f791z[21];
        i2 = (int) ((((float) j) / ((float) j2)) * 100.0f);
        if (j2 > 0) {
            str = str + i2 + "%";
        }
        if (this.f802m == null || this.f802m.intValue() <= 0) {
            C0417m.m1211a(this.f795f, this, obj, str, PendingIntent.getActivity(getApplicationContext(), i, new Intent(), 134217728));
        } else {
            if (this.f797h == null) {
                this.f797h = new RemoteViews(getPackageName(), this.f802m.intValue());
                this.f797h.setTextViewText(this.f798i.intValue(), obj);
                this.f797h.setImageViewResource(this.f800k.intValue(), C0448e.f750b);
            }
            this.f797h.setTextViewText(this.f801l.intValue(), i2 + "%");
            this.f797h.setProgressBar(this.f799j.intValue(), 100, i2, false);
            this.f795f.contentView = this.f797h;
        }
        if (this.f795f != null) {
            this.f792c.notify(i, this.f795f);
        }
    }

    /* renamed from: a */
    private void m1427a(C0429c c0429c, boolean z) {
        Intent a;
        boolean z2;
        boolean c = c0429c.m1277c();
        if (!c0429c.m1273a() || z) {
            a = C0490b.m1667a(getApplicationContext(), c0429c, false);
            z2 = false;
        } else {
            z2 = true;
            a = new Intent();
            a.putExtra(f791z[6], c0429c);
            a.setClass(getApplicationContext(), PushReceiver.class);
            a.setAction(f791z[20]);
            if (c0429c.m1277c()) {
                c0429c.f630t = f791z[18];
            } else {
                c0429c.f630t = f791z[19];
            }
        }
        int a2 = C0417m.m1207a(c0429c, 0);
        Notification a3 = C0417m.m1209a(getApplicationContext(), a2, a, c0429c, c, z2);
        if (a3 != null) {
            this.f792c.notify(a2, a3);
        } else {
            ac.m1588e();
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m1428a(DownloadService downloadService, int i, C0429c c0429c, int i2) {
        if (i2 != 0 && !c0429c.m1279e()) {
            Object obj;
            int i3 = 4;
            if (2 == i2) {
                obj = f791z[9];
            } else if (3 == i2) {
                obj = f791z[7];
            } else if (1 == i2) {
                String str = f791z[8];
                i3 = 2;
            } else {
                return;
            }
            Object obj2 = c0429c.f629s;
            Intent intent = new Intent();
            if (C0463b.m1486a(i2)) {
                intent.setClass(downloadService.getApplicationContext(), DownloadService.class);
                c0429c.f636z = -1;
                intent.putExtra(f791z[6], c0429c);
            }
            PendingIntent service = PendingIntent.getService(downloadService, i, intent, 134217728);
            if (VERSION.SDK_INT >= 11) {
                new Builder(downloadService.getApplicationContext()).setContentTitle(obj2).setContentText(obj).setContentIntent(service).setWhen(System.currentTimeMillis()).setSmallIcon(17301634).getNotification().flags = i3;
            } else {
                Notification notification = new Notification();
                notification.icon = 17301634;
                notification.when = System.currentTimeMillis();
                notification.flags = i3;
                C0417m.m1211a(notification, downloadService.getApplicationContext(), obj2, obj, service);
            }
            if (downloadService.f795f != null) {
                downloadService.f792c.notify(i, downloadService.f795f);
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m1429a(DownloadService downloadService, C0429c c0429c) {
        if (c0429c.m1279e()) {
            C0490b.m1688b(downloadService.getApplicationContext(), c0429c);
            return;
        }
        if (c0429c.m1273a()) {
            Object obj = ((C0437i) c0429c).f678P;
        } else {
            String str = c0429c.m1275b() ? ((C0447s) c0429c).f747I : "";
        }
        if (c0429c.m1273a() && !TextUtils.isEmpty(obj)) {
            C0437i c0437i = (C0437i) c0429c;
            String str2 = c0437i.f671I ? c0437i.f667E : f791z[3];
            PackageInfo packageInfo = null;
            try {
                packageInfo = downloadService.getPackageManager().getPackageArchiveInfo(obj, 16384);
            } catch (Exception e) {
                ac.m1592h();
            }
            String str3 = packageInfo != null ? packageInfo.packageName : "";
            if (TextUtils.isEmpty(str3) && !TextUtils.isEmpty(c0437i.f680a)) {
                str3 = c0437i.f680a;
            }
            C0431b.m1284a(downloadService, c0429c, str3, str2);
            if (C0456f.m1403a(c0437i.f669G, c0437i.f670H, downloadService.getApplicationContext())) {
                downloadService.m1427a(c0429c, true);
                return;
            }
            if (c0437i.f676N) {
                downloadService.m1427a(c0429c, false);
            }
            if (c0437i.f677O) {
                C0490b.m1706e(downloadService.getApplicationContext(), obj);
            }
            if (!c0437i.f676N && !c0437i.f677O) {
                ac.m1581b();
            }
        } else if (!c0429c.m1275b() || TextUtils.isEmpty(obj)) {
            new StringBuilder(f791z[4]).append(obj);
            ac.m1581b();
        } else {
            downloadService.m1427a(c0429c, false);
        }
    }

    /* renamed from: a */
    public static boolean m1431a() {
        return f789a.size() > 0;
    }

    public void onCreate() {
        ac.m1581b();
        super.onCreate();
        this.f794e = new C0468g(this, getApplicationContext());
        this.f792c = (NotificationManager) getSystemService(f791z[17]);
        if (f790b == null) {
            f790b = new Bundle();
        }
        try {
            if (this.f802m.intValue() == 0) {
                HashMap a = C0417m.m1210a(f791z[15], new String[]{f791z[10]});
                if (a.size() > 0) {
                    this.f802m = (Integer) a.get(f791z[10]);
                }
                HashMap a2 = C0417m.m1210a(f791z[14], new String[]{f791z[16], f791z[12], f791z[13], f791z[11]});
                if (a2.size() > 0) {
                    this.f798i = (Integer) a2.get(f791z[16]);
                    this.f799j = (Integer) a2.get(f791z[12]);
                    this.f800k = (Integer) a2.get(f791z[13]);
                    this.f801l = (Integer) a2.get(f791z[11]);
                }
            }
        } catch (Exception e) {
            ac.m1592h();
        }
    }

    public void onDestroy() {
        ac.m1581b();
        super.onDestroy();
    }

    protected void onHandleIntent(Intent intent) {
        ac.m1581b();
        this.f793d = (C0429c) intent.getSerializableExtra(f791z[6]);
        if (this.f793d == null) {
            ac.m1586d();
        } else if (!Environment.getExternalStorageState().equals(f791z[5])) {
            ac.m1586d();
            this.f794e.sendEmptyMessage(0);
        } else if (this.f793d.f634x) {
            ac.m1581b();
        } else {
            boolean z;
            if (this.f793d.f632v) {
                C0459i.m1415a(this.f793d.f613c, 1012, this);
            }
            if (!f789a.contains(this.f793d)) {
                f789a.offer(this.f793d);
            }
            int a = C0417m.m1207a(this.f793d, 1);
            C0429c c0429c = this.f793d;
            if (c0429c.m1279e()) {
                z = true;
            } else {
                int i;
                if (c0429c.m1273a()) {
                    C0437i c0437i = (C0437i) c0429c;
                    if (C0456f.m1403a(c0437i.f669G, c0437i.f670H, (Context) this)) {
                        i = 1;
                    } else {
                        m1426a(c0429c, a, 0, 0);
                        i = 0;
                    }
                } else {
                    i = 0;
                }
                z = i != 0 || c0429c.m1275b();
            }
            Thread.currentThread().setPriority(1);
            C0463b c0463b = new C0463b(this, this.f793d, f790b, new C0466e(this, z, a), HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE);
        }
    }
}
