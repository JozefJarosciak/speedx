package cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;
import cn.jpush.android.data.C0434f;
import cn.jpush.android.data.C0436h;
import cn.jpush.android.util.ac;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* renamed from: cn.jpush.android.service.h */
public final class C0469h {
    /* renamed from: a */
    private static C0469h f852a = null;
    /* renamed from: b */
    private static ExecutorService f853b = Executors.newSingleThreadExecutor();
    /* renamed from: f */
    private static C0434f f854f = null;
    /* renamed from: g */
    private static C0436h f855g = new C0436h();
    /* renamed from: h */
    private static Object f856h = new Object();
    /* renamed from: z */
    private static final String[] f857z;
    /* renamed from: c */
    private Handler f858c = null;
    /* renamed from: d */
    private Context f859d = null;
    /* renamed from: e */
    private String f860e = "";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 14;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0005WNEt\u0003hXWp\u0001hO_z&dZBu ixSr;bI";
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
            case 0: goto L_0x00d2;
            case 1: goto L_0x00d6;
            case 2: goto L_0x00d9;
            case 3: goto L_0x00dd;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 28;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = ",k^WnofWZ<#hXWpoiTBu)nXWh&hU\u0016";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "=bVYj*'WY.k\u001bUs:iO\u0016&o";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "=bVYj*'wY.kuYh&aRU};nTX<";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "?hHB<+bWWe*c\u001b\f<";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = ".c_\u0016P dZZR sRPu,fO_s!";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "&iRB<\u0003hXWp\u0001hO_z&dZBu i\u001bU}<s\u001bSd?s\u0001";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "aw^Dq&tH_s!)qfI\u001cOd{Y\u001cTzqY";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = ",i\u0015\\l:tS\u0018}!cIYu+)RXh*iO\u0018R\u0000SrpU\fFoS\u0001Xis_\nNmsX\u0010WiyD\u0016";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "!hO_z&dZ_s!XOOl*";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\"bHE}(b";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = ".wKx";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "<bURy=N_";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = ";uRQ{*uwxW&kWfn d^Eou'";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        f857z = r4;
        r0 = 0;
        f852a = r0;
        r0 = java.util.concurrent.Executors.newSingleThreadExecutor();
        f853b = r0;
        r0 = 0;
        f854f = r0;
        r0 = new cn.jpush.android.data.h;
        r0.<init>();
        f855g = r0;
        r0 = new java.lang.Object;
        r0.<init>();
        f856h = r0;
        return;
    L_0x00d2:
        r9 = 79;
        goto L_0x0020;
    L_0x00d6:
        r9 = 7;
        goto L_0x0020;
    L_0x00d9:
        r9 = 59;
        goto L_0x0020;
    L_0x00dd:
        r9 = 54;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.h.<clinit>():void");
    }

    private C0469h(Context context) {
        ac.m1581b();
        this.f858c = new Handler(Looper.getMainLooper());
        this.f859d = context;
        this.f860e = this.f859d.getPackageName();
    }

    /* renamed from: a */
    public static C0469h m1498a(Context context) {
        ac.m1581b();
        synchronized (f856h) {
            if (f852a == null) {
                f852a = new C0469h(context);
            }
        }
        return f852a;
    }

    /* renamed from: a */
    private synchronized void m1499a(long j, long j2) {
        ac.m1581b();
        if (j < 0) {
            ac.m1588e();
        } else if (this.f858c != null) {
            Runnable c0471j = new C0471j(this, j);
            if (j2 <= 0) {
                ac.m1581b();
                this.f858c.post(c0471j);
            } else {
                new StringBuilder(f857z[4]).append(j2);
                ac.m1581b();
                this.f858c.postDelayed(c0471j, j2);
            }
        }
    }

    /* renamed from: a */
    private void m1500a(Context context, String str, String str2, String str3) {
        ac.m1581b();
        Intent intent = new Intent(f857z[8]);
        intent.putExtra(f857z[12], str3);
        intent.putExtra(f857z[11], str2);
        intent.putExtra(f857z[10], str);
        intent.putExtra(f857z[9], 1);
        intent.addCategory(str2);
        context.sendOrderedBroadcast(intent, str2 + f857z[7]);
        ac.m1581b();
    }

    /* renamed from: e */
    private synchronized void m1505e(Context context) {
        Exception e;
        Throwable th;
        ac.m1581b();
        Cursor cursor = null;
        Cursor a;
        try {
            if (f854f == null) {
                f854f = new C0434f(context);
            }
            f854f.m1296a(true);
            a = f854f.m1291a(1, System.currentTimeMillis());
            try {
                if (a.moveToFirst()) {
                    do {
                        f854f.m1295a(a, f855g);
                        m1500a(context, f855g.m1309d(), this.f860e, "");
                        f854f.m1298b(f855g.m1299a(), 0, 0, 0, f855g.m1309d(), f855g.m1311f(), f855g.m1310e());
                    } while (a.moveToNext());
                }
                f854f.m1294a();
                if (a != null) {
                    a.close();
                }
            } catch (Exception e2) {
                e = e2;
                cursor = a;
                try {
                    ac.m1587d(f857z[0], new StringBuilder(f857z[13]).append(e.getMessage()).toString());
                    e.printStackTrace();
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    a = cursor;
                    if (a != null) {
                        a.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (a != null) {
                    a.close();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            ac.m1587d(f857z[0], new StringBuilder(f857z[13]).append(e.getMessage()).toString());
            e.printStackTrace();
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th4) {
            th = th4;
            a = null;
            if (a != null) {
                a.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final synchronized boolean m1506a(android.content.Context r17, long r18) {
        /*
        r16 = this;
        monitor-enter(r16);
        r2 = f857z;	 Catch:{ all -> 0x0080 }
        r3 = 0;
        r2 = r2[r3];	 Catch:{ all -> 0x0080 }
        r3 = f857z;	 Catch:{ all -> 0x0080 }
        r4 = 3;
        r3 = r3[r4];	 Catch:{ all -> 0x0080 }
        cn.jpush.android.util.ac.m1582b(r2, r3);	 Catch:{ all -> 0x0080 }
        r2 = f854f;	 Catch:{ all -> 0x0080 }
        if (r2 != 0) goto L_0x001b;
    L_0x0012:
        r2 = new cn.jpush.android.data.f;	 Catch:{ all -> 0x0080 }
        r0 = r17;
        r2.<init>(r0);	 Catch:{ all -> 0x0080 }
        f854f = r2;	 Catch:{ all -> 0x0080 }
    L_0x001b:
        r2 = 0;
        r3 = f854f;	 Catch:{ Exception -> 0x0079, all -> 0x0083 }
        r4 = 1;
        r3.m1296a(r4);	 Catch:{ Exception -> 0x0079, all -> 0x0083 }
        r3 = f854f;	 Catch:{ Exception -> 0x0079, all -> 0x0083 }
        r4 = 0;
        r0 = r18;
        r2 = r3.m1292a(r0, r4);	 Catch:{ Exception -> 0x0079, all -> 0x0083 }
        r3 = f854f;	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r4 = f855g;	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r3.m1295a(r2, r4);	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r3 = f855g;	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r3 = r3.m1303b();	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        if (r3 <= 0) goto L_0x006c;
    L_0x003a:
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r4 = f857z;	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r5 = 2;
        r4 = r4[r5];	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r3.<init>(r4);	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r4 = f855g;	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r4 = r4.m1303b();	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r3.append(r4);	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        cn.jpush.android.util.ac.m1581b();	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r3 = f854f;	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r6 = 0;
        r7 = 1;
        r8 = 0;
        r4 = f855g;	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r9 = r4.m1309d();	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r4 = f855g;	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r10 = r4.m1311f();	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r4 = f855g;	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r12 = r4.m1310e();	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r4 = r18;
        r3.m1298b(r4, r6, r7, r8, r9, r10, r12);	 Catch:{ Exception -> 0x0079, all -> 0x008d }
    L_0x006c:
        r3 = f854f;	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        r3.m1294a();	 Catch:{ Exception -> 0x0079, all -> 0x008d }
        if (r2 == 0) goto L_0x0076;
    L_0x0073:
        r2.close();	 Catch:{ all -> 0x0080 }
    L_0x0076:
        r2 = 1;
        monitor-exit(r16);
        return r2;
    L_0x0079:
        r3 = move-exception;
        if (r2 == 0) goto L_0x0076;
    L_0x007c:
        r2.close();	 Catch:{ all -> 0x0080 }
        goto L_0x0076;
    L_0x0080:
        r2 = move-exception;
        monitor-exit(r16);
        throw r2;
    L_0x0083:
        r3 = move-exception;
        r14 = r3;
        r3 = r2;
        r2 = r14;
    L_0x0087:
        if (r3 == 0) goto L_0x008c;
    L_0x0089:
        r3.close();	 Catch:{ all -> 0x0080 }
    L_0x008c:
        throw r2;	 Catch:{ all -> 0x0080 }
    L_0x008d:
        r3 = move-exception;
        r14 = r3;
        r3 = r2;
        r2 = r14;
        goto L_0x0087;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.h.a(android.content.Context, long):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public final synchronized boolean m1507a(android.content.Context r18, cn.jpush.android.data.JPushLocalNotification r19) {
        /*
        r17 = this;
        monitor-enter(r17);
        r2 = f857z;	 Catch:{ all -> 0x00a2 }
        r3 = 0;
        r2 = r2[r3];	 Catch:{ all -> 0x00a2 }
        r3 = f857z;	 Catch:{ all -> 0x00a2 }
        r4 = 5;
        r3 = r3[r4];	 Catch:{ all -> 0x00a2 }
        cn.jpush.android.util.ac.m1582b(r2, r3);	 Catch:{ all -> 0x00a2 }
        r12 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00a2 }
        r2 = r19.getBroadcastTime();	 Catch:{ all -> 0x00a2 }
        r14 = r2 - r12;
        r2 = cn.jpush.android.service.ServiceInterface.m1476e(r18);	 Catch:{ all -> 0x00a2 }
        if (r2 == 0) goto L_0x0021;
    L_0x001e:
        cn.jpush.android.util.ac.m1581b();	 Catch:{ all -> 0x00a2 }
    L_0x0021:
        r2 = f854f;	 Catch:{ all -> 0x00a2 }
        if (r2 != 0) goto L_0x002e;
    L_0x0025:
        r2 = new cn.jpush.android.data.f;	 Catch:{ all -> 0x00a2 }
        r0 = r18;
        r2.<init>(r0);	 Catch:{ all -> 0x00a2 }
        f854f = r2;	 Catch:{ all -> 0x00a2 }
    L_0x002e:
        r2 = 0;
        r3 = f854f;	 Catch:{ Exception -> 0x009b, all -> 0x00a5 }
        r4 = 1;
        r3.m1296a(r4);	 Catch:{ Exception -> 0x009b, all -> 0x00a5 }
        r3 = f854f;	 Catch:{ Exception -> 0x009b, all -> 0x00a5 }
        r4 = r19.getNotificationId();	 Catch:{ Exception -> 0x009b, all -> 0x00a5 }
        r6 = 0;
        r2 = r3.m1292a(r4, r6);	 Catch:{ Exception -> 0x009b, all -> 0x00a5 }
        r3 = f854f;	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r4 = f855g;	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r3.m1295a(r2, r4);	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r3 = f855g;	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r4 = r3.m1299a();	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r6 = r19.getNotificationId();	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 == 0) goto L_0x0086;
    L_0x0055:
        r3 = f854f;	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r4 = r19.getNotificationId();	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r6 = 1;
        r7 = 0;
        r8 = 0;
        r9 = r19.toJSON();	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r10 = r19.getBroadcastTime();	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r3.m1290a(r4, r6, r7, r8, r9, r10, r12);	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
    L_0x0069:
        r3 = f854f;	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r3.m1294a();	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        if (r2 == 0) goto L_0x0073;
    L_0x0070:
        r2.close();	 Catch:{ all -> 0x00a2 }
    L_0x0073:
        r2 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
        r2 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1));
        if (r2 >= 0) goto L_0x00b1;
    L_0x007a:
        r2 = r19.getNotificationId();	 Catch:{ all -> 0x00a2 }
        r0 = r17;
        r0.m1499a(r2, r14);	 Catch:{ all -> 0x00a2 }
        r2 = 1;
    L_0x0084:
        monitor-exit(r17);
        return r2;
    L_0x0086:
        r3 = f854f;	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r4 = r19.getNotificationId();	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r6 = 1;
        r7 = 0;
        r8 = 0;
        r9 = r19.toJSON();	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r10 = r19.getBroadcastTime();	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        r3.m1298b(r4, r6, r7, r8, r9, r10, r12);	 Catch:{ Exception -> 0x009b, all -> 0x00b3 }
        goto L_0x0069;
    L_0x009b:
        r3 = move-exception;
        if (r2 == 0) goto L_0x0073;
    L_0x009e:
        r2.close();	 Catch:{ all -> 0x00a2 }
        goto L_0x0073;
    L_0x00a2:
        r2 = move-exception;
        monitor-exit(r17);
        throw r2;
    L_0x00a5:
        r3 = move-exception;
        r16 = r3;
        r3 = r2;
        r2 = r16;
    L_0x00ab:
        if (r3 == 0) goto L_0x00b0;
    L_0x00ad:
        r3.close();	 Catch:{ all -> 0x00a2 }
    L_0x00b0:
        throw r2;	 Catch:{ all -> 0x00a2 }
    L_0x00b1:
        r2 = 1;
        goto L_0x0084;
    L_0x00b3:
        r3 = move-exception;
        r16 = r3;
        r3 = r2;
        r2 = r16;
        goto L_0x00ab;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.h.a(android.content.Context, cn.jpush.android.data.JPushLocalNotification):boolean");
    }

    /* renamed from: b */
    public final synchronized void m1508b(Context context) {
        ac.m1582b(f857z[0], f857z[1]);
        if (f854f == null) {
            f854f = new C0434f(context);
        }
        f854f.m1296a(true);
        if (f854f.m1297b()) {
            ac.m1581b();
        }
        f854f.m1294a();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: c */
    public final synchronized void m1509c(android.content.Context r10) {
        /*
        r9 = this;
        monitor-enter(r9);
        cn.jpush.android.util.ac.m1581b();	 Catch:{ all -> 0x006e }
        r0 = f854f;	 Catch:{ Exception -> 0x0059 }
        if (r0 != 0) goto L_0x000f;
    L_0x0008:
        r0 = new cn.jpush.android.data.f;	 Catch:{ Exception -> 0x0059 }
        r0.<init>(r10);	 Catch:{ Exception -> 0x0059 }
        f854f = r0;	 Catch:{ Exception -> 0x0059 }
    L_0x000f:
        r0 = 0;
        r1 = f854f;	 Catch:{ Exception -> 0x0052, all -> 0x0071 }
        r2 = 0;
        r1.m1296a(r2);	 Catch:{ Exception -> 0x0052, all -> 0x0071 }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0052, all -> 0x0071 }
        r1 = f854f;	 Catch:{ Exception -> 0x0052, all -> 0x0071 }
        r4 = 300000; // 0x493e0 float:4.2039E-40 double:1.482197E-318;
        r0 = r1.m1293a(r2, r4);	 Catch:{ Exception -> 0x0052, all -> 0x0071 }
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x0052, all -> 0x007b }
        if (r1 == 0) goto L_0x0046;
    L_0x0029:
        r1 = f854f;	 Catch:{ Exception -> 0x0052, all -> 0x007b }
        r4 = f855g;	 Catch:{ Exception -> 0x0052, all -> 0x007b }
        r1.m1295a(r0, r4);	 Catch:{ Exception -> 0x0052, all -> 0x007b }
        r1 = f855g;	 Catch:{ Exception -> 0x0052, all -> 0x007b }
        r4 = r1.m1299a();	 Catch:{ Exception -> 0x0052, all -> 0x007b }
        r1 = f855g;	 Catch:{ Exception -> 0x0052, all -> 0x007b }
        r6 = r1.m1311f();	 Catch:{ Exception -> 0x0052, all -> 0x007b }
        r6 = r6 - r2;
        r9.m1499a(r4, r6);	 Catch:{ Exception -> 0x0052, all -> 0x007b }
        r1 = r0.moveToNext();	 Catch:{ Exception -> 0x0052, all -> 0x007b }
        if (r1 != 0) goto L_0x0029;
    L_0x0046:
        r1 = f854f;	 Catch:{ Exception -> 0x0052, all -> 0x007b }
        r1.m1294a();	 Catch:{ Exception -> 0x0052, all -> 0x007b }
        if (r0 == 0) goto L_0x0050;
    L_0x004d:
        r0.close();	 Catch:{ Exception -> 0x0059 }
    L_0x0050:
        monitor-exit(r9);
        return;
    L_0x0052:
        r1 = move-exception;
        if (r0 == 0) goto L_0x0050;
    L_0x0055:
        r0.close();	 Catch:{ Exception -> 0x0059 }
        goto L_0x0050;
    L_0x0059:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006e }
        r2 = f857z;	 Catch:{ all -> 0x006e }
        r3 = 6;
        r2 = r2[r3];	 Catch:{ all -> 0x006e }
        r1.<init>(r2);	 Catch:{ all -> 0x006e }
        r1.append(r0);	 Catch:{ all -> 0x006e }
        cn.jpush.android.util.ac.m1581b();	 Catch:{ all -> 0x006e }
        r0.printStackTrace();	 Catch:{ all -> 0x006e }
        goto L_0x0050;
    L_0x006e:
        r0 = move-exception;
        monitor-exit(r9);
        throw r0;
    L_0x0071:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
    L_0x0075:
        if (r1 == 0) goto L_0x007a;
    L_0x0077:
        r1.close();	 Catch:{ Exception -> 0x0059 }
    L_0x007a:
        throw r0;	 Catch:{ Exception -> 0x0059 }
    L_0x007b:
        r1 = move-exception;
        r8 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x0075;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.h.c(android.content.Context):void");
    }

    /* renamed from: d */
    public final void m1510d(Context context) {
        ac.m1581b();
        f853b.execute(new C0470i(this, context));
    }
}
