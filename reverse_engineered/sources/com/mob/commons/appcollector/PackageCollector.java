package com.mob.commons.appcollector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import com.mob.commons.C4226a;
import com.mob.commons.C4240c;
import com.mob.commons.C4245e;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ReflectHelper;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class PackageCollector {
    /* renamed from: b */
    private static PackageCollector f14882b;
    /* renamed from: a */
    private final String[] f14883a = new String[]{"android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_REPLACED"};
    /* renamed from: c */
    private Context f14884c;
    /* renamed from: d */
    private Hashon f14885d;
    /* renamed from: e */
    private Handler f14886e;

    /* renamed from: com.mob.commons.appcollector.PackageCollector$1 */
    class C42281 extends MobHandlerThread {
        /* renamed from: a */
        final /* synthetic */ PackageCollector f14879a;

        /* renamed from: com.mob.commons.appcollector.PackageCollector$1$1 */
        class C42271 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C42281 f14878a;

            C42271(C42281 c42281) {
                this.f14878a = c42281;
            }

            public void run() {
                if (C4226a.m16772d(this.f14878a.f14879a.f14884c)) {
                    this.f14878a.f14879a.m16799b();
                }
                this.f14878a.f14879a.m16806e();
                this.f14878a.m16789a();
            }
        }

        C42281(PackageCollector packageCollector) {
            this.f14879a = packageCollector;
        }

        public void run() {
            C4245e.m16870a(new File(C4275R.getCacheRoot(this.f14879a.f14884c), "comm/locks/.pkg_lock"), new C42271(this));
        }

        /* renamed from: a */
        private void m16789a() {
            super.run();
        }
    }

    /* renamed from: com.mob.commons.appcollector.PackageCollector$2 */
    class C42292 implements Callback {
        /* renamed from: a */
        final /* synthetic */ PackageCollector f14880a;

        C42292(PackageCollector packageCollector) {
            this.f14880a = packageCollector;
        }

        public boolean handleMessage(Message message) {
            this.f14880a.m16807f();
            return false;
        }
    }

    /* renamed from: com.mob.commons.appcollector.PackageCollector$3 */
    class C42303 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ PackageCollector f14881a;

        C42303(PackageCollector packageCollector) {
            this.f14881a = packageCollector;
        }

        public void onReceive(Context context, Intent intent) {
            String str = null;
            if (intent != null) {
                str = intent.getAction();
            }
            if (this.f14881a.m16798a(str) && this.f14881a.f14886e != null) {
                this.f14881a.f14886e.removeMessages(1);
                this.f14881a.f14886e.sendEmptyMessageDelayed(1, 5000);
            }
        }
    }

    public static synchronized boolean register(Context context, String str) {
        synchronized (PackageCollector.class) {
            startCollector(context);
        }
        return true;
    }

    public static synchronized void startCollector(Context context) {
        synchronized (PackageCollector.class) {
            if (f14882b == null) {
                f14882b = new PackageCollector(context);
                f14882b.m16793a();
            }
        }
    }

    private PackageCollector(Context context) {
        this.f14884c = context.getApplicationContext();
        this.f14885d = new Hashon();
    }

    /* renamed from: a */
    private void m16793a() {
        MobHandlerThread c42281 = new C42281(this);
        c42281.start();
        this.f14886e = new Handler(c42281.getLooper(), new C42292(this));
    }

    /* renamed from: b */
    private void m16799b() {
        ArrayList c = m16801c();
        if (c == null || c.isEmpty()) {
            try {
                Object[] objArr = new Object[]{Boolean.valueOf(false)};
                c = (ArrayList) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14884c), "getInstalledApp", objArr);
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
                c = new ArrayList();
            }
            m16795a(C4226a.m16781m(this.f14884c), "APPS_ALL", c);
            m16796a(c);
            m16794a(C4226a.m16764a(this.f14884c) + (C4226a.m16773e(this.f14884c) * 1000));
            return;
        }
        long a = C4226a.m16764a(this.f14884c);
        if (a >= m16803d()) {
            try {
                Object[] objArr2 = new Object[]{Boolean.valueOf(false)};
                c = (ArrayList) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14884c), "getInstalledApp", objArr2);
            } catch (Throwable th2) {
                MobLog.getInstance().m16946w(th2);
                c = new ArrayList();
            }
            m16795a(C4226a.m16781m(this.f14884c), "APPS_ALL", c);
            m16796a(c);
            m16794a((C4226a.m16773e(this.f14884c) * 1000) + a);
            return;
        }
        m16807f();
    }

    /* renamed from: c */
    private ArrayList<HashMap<String, String>> m16801c() {
        File file = new File(C4275R.getCacheRoot(this.f14884c), "comm/dbs/.al");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            try {
                ArrayList<HashMap<String, String>> arrayList = new ArrayList();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file)), "utf-8"));
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    HashMap fromJson = this.f14885d.fromJson(readLine);
                    if (fromJson != null) {
                        arrayList.add(fromJson);
                    }
                }
                bufferedReader.close();
                return arrayList;
            } catch (Throwable th) {
                MobLog.getInstance().m16934d(th);
            }
        }
        return new ArrayList();
    }

    /* renamed from: a */
    private void m16795a(long j, String str, ArrayList<HashMap<String, String>> arrayList) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", str);
        hashMap.put("list", arrayList);
        hashMap.put("datetime", Long.valueOf(C4226a.m16764a(this.f14884c)));
        C4240c.m16840a(this.f14884c).m16853a(j, hashMap);
    }

    /* renamed from: a */
    private void m16796a(ArrayList<HashMap<String, String>> arrayList) {
        File file = new File(C4275R.getCacheRoot(this.f14884c), "comm/dbs/.al");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(file)), "utf-8");
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                outputStreamWriter.append(this.f14885d.fromHashMap((HashMap) it.next())).append('\n');
            }
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (Throwable th) {
            MobLog.getInstance().m16934d(th);
        }
    }

    /* renamed from: a */
    private void m16794a(long j) {
        File file = new File(C4275R.getCacheRoot(this.f14884c), "comm/dbs/.nulal");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.writeLong(j);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().m16934d(th);
        }
    }

    /* renamed from: d */
    private long m16803d() {
        File file = new File(C4275R.getCacheRoot(this.f14884c), "comm/dbs/.nulal");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            try {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                long readLong = dataInputStream.readLong();
                dataInputStream.close();
                return readLong;
            } catch (Throwable th) {
                MobLog.getInstance().m16934d(th);
            }
        }
        return 0;
    }

    /* renamed from: e */
    private void m16806e() {
        IntentFilter intentFilter = new IntentFilter();
        for (String addAction : f14882b.f14883a) {
            intentFilter.addAction(addAction);
        }
        intentFilter.addDataScheme(OnlineConfigAgent.KEY_PACKAGE);
        this.f14884c.registerReceiver(new C42303(this), intentFilter);
    }

    /* renamed from: a */
    private boolean m16798a(String str) {
        for (String equals : this.f14883a) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: f */
    private void m16807f() {
        ArrayList arrayList;
        ArrayList c = m16801c();
        try {
            Object[] objArr = new Object[]{Boolean.valueOf(false)};
            arrayList = (ArrayList) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14884c), "getInstalledApp", objArr);
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
            arrayList = new ArrayList();
        }
        if (c == null || c.isEmpty()) {
            m16795a(C4226a.m16781m(this.f14884c), "APPS_ALL", arrayList);
            m16796a(arrayList);
            m16794a(C4226a.m16764a(this.f14884c) + (C4226a.m16773e(this.f14884c) * 1000));
            return;
        }
        ArrayList a = m16792a(arrayList, c);
        if (!a.isEmpty()) {
            m16795a(C4226a.m16764a(this.f14884c), "APPS_INCR", a);
        }
        c = m16792a(c, arrayList);
        if (!c.isEmpty()) {
            m16795a(C4226a.m16764a(this.f14884c), "UNINSTALL", c);
        }
        m16796a(arrayList);
        m16794a(C4226a.m16764a(this.f14884c) + (C4226a.m16773e(this.f14884c) * 1000));
    }

    /* renamed from: a */
    private ArrayList<HashMap<String, String>> m16792a(ArrayList<HashMap<String, String>> arrayList, ArrayList<HashMap<String, String>> arrayList2) {
        ArrayList<HashMap<String, String>> arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap hashMap = (HashMap) it.next();
            String str = (String) hashMap.get("pkg");
            if (!TextUtils.isEmpty(str)) {
                Object obj;
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    if (str.equals(((HashMap) it2.next()).get("pkg"))) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    arrayList3.add(hashMap);
                }
            }
        }
        return arrayList3;
    }
}
