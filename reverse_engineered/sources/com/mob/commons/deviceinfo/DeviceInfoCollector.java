package com.mob.commons.deviceinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Parcelable;
import com.alipay.sdk.packet.C0861d;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mob.commons.C4226a;
import com.mob.commons.C4240c;
import com.mob.commons.C4245e;
import com.mob.commons.C4250f;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ReflectHelper;
import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class DeviceInfoCollector implements Callback {
    /* renamed from: a */
    private static DeviceInfoCollector f14917a;
    /* renamed from: b */
    private Context f14918b;
    /* renamed from: c */
    private Hashon f14919c = new Hashon();
    /* renamed from: d */
    private Handler f14920d;
    /* renamed from: e */
    private Random f14921e = new Random();

    /* renamed from: com.mob.commons.deviceinfo.DeviceInfoCollector$1 */
    class C42431 extends MobHandlerThread {
        /* renamed from: a */
        final /* synthetic */ DeviceInfoCollector f14915a;

        /* renamed from: com.mob.commons.deviceinfo.DeviceInfoCollector$1$1 */
        class C42421 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C42431 f14914a;

            C42421(C42431 c42431) {
                this.f14914a = c42431;
            }

            public void run() {
                this.f14914a.m16857a();
            }
        }

        C42431(DeviceInfoCollector deviceInfoCollector) {
            this.f14915a = deviceInfoCollector;
        }

        public void run() {
            C4245e.m16870a(new File(C4275R.getCacheRoot(this.f14915a.f14918b), "comm/locks/.dic_lock"), new C42421(this));
        }

        /* renamed from: a */
        private void m16857a() {
            super.run();
        }
    }

    /* renamed from: com.mob.commons.deviceinfo.DeviceInfoCollector$2 */
    class C42442 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ DeviceInfoCollector f14916a;

        C42442(DeviceInfoCollector deviceInfoCollector) {
            this.f14916a = deviceInfoCollector;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                if ("android.net.wifi.STATE_CHANGE".equals(intent.getAction())) {
                    Parcelable parcelableExtra = intent.getParcelableExtra("networkInfo");
                    if (parcelableExtra != null && ((NetworkInfo) parcelableExtra).isAvailable()) {
                        HashMap hashMap = new HashMap();
                        Object invokeStaticMethod = ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", context);
                        hashMap.put("ssid", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getSSID", new Object[0]));
                        hashMap.put("bssid", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getBssid", new Object[0]));
                        String MD5 = Data.MD5(this.f14916a.f14919c.fromHashMap(hashMap));
                        String c = C4250f.m16885c(context);
                        if ((c == null || !c.equals(MD5)) && C4226a.m16780l(context)) {
                            this.f14916a.m16866d();
                        }
                    }
                }
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
            }
        }
    }

    public static synchronized void startCollector(Context context) {
        synchronized (DeviceInfoCollector.class) {
            if (f14917a == null) {
                f14917a = new DeviceInfoCollector(context);
                f14917a.m16860a();
            }
        }
    }

    private DeviceInfoCollector(Context context) {
        this.f14918b = context.getApplicationContext();
    }

    /* renamed from: a */
    private void m16860a() {
        MobHandlerThread c42431 = new C42431(this);
        c42431.start();
        this.f14920d = new Handler(c42431.getLooper(), this);
        this.f14920d.sendEmptyMessage(1);
        this.f14920d.sendEmptyMessage(2);
        this.f14920d.sendEmptyMessage(3);
        this.f14920d.sendEmptyMessage(5);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean handleMessage(android.os.Message r9) {
        /*
        r8 = this;
        r7 = 4;
        r6 = 0;
        r0 = r9.what;
        switch(r0) {
            case 1: goto L_0x0008;
            case 2: goto L_0x0014;
            case 3: goto L_0x0029;
            case 4: goto L_0x0050;
            case 5: goto L_0x0099;
            default: goto L_0x0007;
        };
    L_0x0007:
        return r6;
    L_0x0008:
        r0 = r8.f14918b;
        r0 = com.mob.commons.C4226a.m16775g(r0);
        if (r0 == 0) goto L_0x0007;
    L_0x0010:
        r8.m16863b();
        goto L_0x0007;
    L_0x0014:
        r0 = r8.f14918b;
        r0 = com.mob.commons.C4226a.m16780l(r0);
        if (r0 == 0) goto L_0x0007;
    L_0x001c:
        r0 = r8.m16865c();
        if (r0 == 0) goto L_0x0025;
    L_0x0022:
        r8.m16866d();
    L_0x0025:
        r8.m16867e();
        goto L_0x0007;
    L_0x0029:
        r0 = r8.f14918b;
        r0 = com.mob.commons.C4226a.m16776h(r0);
        if (r0 == 0) goto L_0x0034;
    L_0x0031:
        r8.m16868f();	 Catch:{ Throwable -> 0x0047 }
    L_0x0034:
        r0 = r8.f14921e;
        r1 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r0 = r0.nextInt(r1);
        r0 = r0 + 180;
        r1 = r8.f14920d;
        r0 = r0 * 1000;
        r2 = (long) r0;
        r1.sendEmptyMessageDelayed(r7, r2);
        goto L_0x0007;
    L_0x0047:
        r0 = move-exception;
        r1 = com.mob.tools.MobLog.getInstance();
        r1.m16946w(r0);
        goto L_0x0034;
    L_0x0050:
        r0 = r8.f14918b;
        r0 = com.mob.commons.C4226a.m16776h(r0);
        if (r0 == 0) goto L_0x007c;
    L_0x0058:
        r0 = r8.f14918b;
        r0 = com.mob.commons.C4226a.m16764a(r0);
        r2 = r8.f14918b;
        r2 = com.mob.commons.C4226a.m16777i(r2);
        r2 = (long) r2;
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = r2 * r4;
        r0 = r0 + r2;
        r2 = r8.f14918b;
        r2 = com.mob.commons.C4226a.m16764a(r2);
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 < 0) goto L_0x0079;
    L_0x0073:
        r0 = r8.m16869g();	 Catch:{ Throwable -> 0x0090 }
        if (r0 == 0) goto L_0x007c;
    L_0x0079:
        r8.m16868f();	 Catch:{ Throwable -> 0x0090 }
    L_0x007c:
        r0 = r8.f14921e;
        r1 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r0 = r0.nextInt(r1);
        r0 = r0 + 180;
        r1 = r8.f14920d;
        r0 = r0 * 1000;
        r2 = (long) r0;
        r1.sendEmptyMessageDelayed(r7, r2);
        goto L_0x0007;
    L_0x0090:
        r0 = move-exception;
        r1 = com.mob.tools.MobLog.getInstance();
        r1.m16946w(r0);
        goto L_0x007c;
    L_0x0099:
        r0 = r8.f14918b;
        r0 = com.mob.commons.C4226a.m16778j(r0);
        if (r0 == 0) goto L_0x0101;
    L_0x00a1:
        r0 = "DeviceHelper";
        r1 = "getInstance";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0112 }
        r3 = 0;
        r4 = r8.f14918b;	 Catch:{ Throwable -> 0x0112 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0112 }
        r1 = com.mob.tools.utils.ReflectHelper.invokeStaticMethod(r0, r1, r2);	 Catch:{ Throwable -> 0x0112 }
        r0 = "getLocation";
        r2 = 3;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0112 }
        r3 = 0;
        r4 = 30;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0112 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0112 }
        r3 = 1;
        r4 = 0;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0112 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0112 }
        r3 = 2;
        r4 = 1;
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ Throwable -> 0x0112 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0112 }
        r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r1, r0, r2);	 Catch:{ Throwable -> 0x0112 }
        r0 = (android.location.Location) r0;	 Catch:{ Throwable -> 0x0112 }
        r2 = 1;
        r8.m16861a(r0, r2);	 Catch:{ Throwable -> 0x0112 }
        r0 = "getLocation";
        r2 = 3;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x0112 }
        r3 = 0;
        r4 = 15;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0112 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0112 }
        r3 = 1;
        r4 = 0;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x0112 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0112 }
        r3 = 2;
        r4 = 1;
        r4 = java.lang.Boolean.valueOf(r4);	 Catch:{ Throwable -> 0x0112 }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x0112 }
        r0 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethod(r1, r0, r2);	 Catch:{ Throwable -> 0x0112 }
        r0 = (android.location.Location) r0;	 Catch:{ Throwable -> 0x0112 }
        r1 = 2;
        r8.m16861a(r0, r1);	 Catch:{ Throwable -> 0x0112 }
    L_0x0101:
        r0 = r8.f14920d;
        r1 = 5;
        r2 = r8.f14918b;
        r2 = com.mob.commons.C4226a.m16779k(r2);
        r2 = r2 * 1000;
        r2 = (long) r2;
        r0.sendEmptyMessageDelayed(r1, r2);
        goto L_0x0007;
    L_0x0112:
        r0 = move-exception;
        r1 = com.mob.tools.MobLog.getInstance();
        r1.m16946w(r0);
        goto L_0x0101;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.deviceinfo.DeviceInfoCollector.handleMessage(android.os.Message):boolean");
    }

    /* renamed from: b */
    private void m16863b() {
        try {
            HashMap hashMap = new HashMap();
            Object invokeStaticMethod = ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14918b);
            hashMap.put("phonename", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getBluetoothName", new Object[0]));
            hashMap.put("signmd5", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getSignMD5", new Object[0]));
            String MD5 = Data.MD5(this.f14919c.fromHashMap(hashMap));
            String a = C4250f.m16879a(this.f14918b);
            if (a == null || !a.equals(MD5)) {
                HashMap hashMap2 = new HashMap();
                hashMap2.put("type", "DEVEXT");
                hashMap2.put(C0861d.f2139k, hashMap);
                hashMap2.put("datetime", Long.valueOf(C4226a.m16764a(this.f14918b)));
                C4240c.m16840a(this.f14918b).m16853a(C4226a.m16764a(this.f14918b), hashMap2);
                C4250f.m16881a(this.f14918b, MD5);
            }
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
        }
    }

    /* renamed from: c */
    private boolean m16865c() {
        long b = C4250f.m16882b(this.f14918b);
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(b);
        int i = instance.get(1);
        int i2 = instance.get(2);
        int i3 = instance.get(5);
        long a = C4226a.m16764a(this.f14918b);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(a);
        int i4 = instance2.get(1);
        int i5 = instance2.get(2);
        int i6 = instance2.get(5);
        if (i == i4 && i2 == i5 && i3 == i6) {
            return false;
        }
        return true;
    }

    /* renamed from: d */
    private void m16866d() {
        synchronized (f14917a) {
            try {
                HashMap hashMap = new HashMap();
                Object invokeStaticMethod = ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14918b);
                hashMap.put("ssid", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getSSID", new Object[0]));
                hashMap.put("bssid", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getBssid", new Object[0]));
                HashMap hashMap2 = new HashMap();
                hashMap2.put("type", "WIFI_INFO");
                hashMap2.put(C0861d.f2139k, hashMap);
                long a = C4226a.m16764a(this.f14918b);
                hashMap2.put("datetime", Long.valueOf(a));
                C4240c.m16840a(this.f14918b).m16853a(C4226a.m16764a(this.f14918b), hashMap2);
                C4250f.m16880a(this.f14918b, a);
                C4250f.m16884b(this.f14918b, Data.MD5(this.f14919c.fromHashMap(hashMap)));
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
            }
        }
    }

    /* renamed from: e */
    private void m16867e() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.wifi.STATE_CHANGE");
        this.f14918b.registerReceiver(new C42442(this), intentFilter);
    }

    /* renamed from: f */
    private void m16868f() throws Throwable {
        int parseInt;
        HashMap hashMap = new HashMap();
        Object invokeStaticMethod = ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14918b);
        try {
            parseInt = Integer.parseInt((String) ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getCarrier", new Object[0]));
        } catch (Throwable th) {
            parseInt = -1;
        }
        hashMap.put(MapboxEvent.ATTRIBUTE_CARRIER, Integer.valueOf(parseInt));
        hashMap.put("simopname", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getCarrierName", new Object[0]));
        hashMap.put("lac", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getCellLac", new Object[0]));
        hashMap.put("cell", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getCellId", new Object[0]));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("type", "BSINFO");
        hashMap2.put(C0861d.f2139k, hashMap);
        hashMap2.put("datetime", Long.valueOf(C4226a.m16764a(this.f14918b)));
        C4240c.m16840a(this.f14918b).m16853a(C4226a.m16764a(this.f14918b), hashMap2);
        C4250f.m16886c(this.f14918b, Data.MD5(this.f14919c.fromHashMap(hashMap)));
        C4250f.m16883b(this.f14918b, C4226a.m16764a(this.f14918b) + (((long) C4226a.m16777i(this.f14918b)) * 1000));
    }

    /* renamed from: g */
    private boolean m16869g() throws Throwable {
        int parseInt;
        HashMap hashMap = new HashMap();
        Object invokeStaticMethod = ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14918b);
        try {
            parseInt = Integer.parseInt((String) ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getCarrier", new Object[0]));
        } catch (Throwable th) {
            parseInt = -1;
        }
        hashMap.put(MapboxEvent.ATTRIBUTE_CARRIER, Integer.valueOf(parseInt));
        hashMap.put("simopname", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getCarrierName", new Object[0]));
        hashMap.put("lac", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getCellLac", new Object[0]));
        hashMap.put("cell", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getCellId", new Object[0]));
        String MD5 = Data.MD5(this.f14919c.fromHashMap(hashMap));
        String d = C4250f.m16887d(this.f14918b);
        return d == null || !d.equals(MD5);
    }

    /* renamed from: a */
    private void m16861a(Location location, int i) {
        if (location != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("accuracy", Float.valueOf(location.getAccuracy()));
            hashMap.put("latitude", Double.valueOf(location.getLatitude()));
            hashMap.put("longitude", Double.valueOf(location.getLongitude()));
            hashMap.put("location_type", Integer.valueOf(i));
            HashMap hashMap2 = new HashMap();
            hashMap2.put("type", "LOCATION");
            hashMap2.put(C0861d.f2139k, hashMap);
            hashMap2.put("datetime", Long.valueOf(C4226a.m16764a(this.f14918b)));
            C4240c.m16840a(this.f14918b).m16853a(C4226a.m16764a(this.f14918b), hashMap2);
        }
    }
}
