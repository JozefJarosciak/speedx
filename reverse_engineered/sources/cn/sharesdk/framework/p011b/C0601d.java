package cn.sharesdk.framework.p011b;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import cn.sharesdk.framework.p011b.p012a.C0589e;
import cn.sharesdk.framework.p011b.p013b.C0591c;
import cn.sharesdk.framework.p011b.p013b.C0595e;
import cn.sharesdk.framework.p011b.p013b.C0598g;
import cn.sharesdk.framework.utils.C0621d;
import com.mob.commons.SHARESDK;
import com.mob.commons.appcollector.PackageCollector;
import com.mob.commons.appcollector.RuntimeCollector;
import com.mob.commons.deviceinfo.DeviceInfoCollector;
import com.mob.commons.iosbridge.UDPServer;
import com.mob.tools.SSDKHandlerThread;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.FileLocker;
import java.io.File;
import java.util.Calendar;

/* compiled from: StatisticsLogger */
/* renamed from: cn.sharesdk.framework.b.d */
public class C0601d extends SSDKHandlerThread {
    /* renamed from: a */
    private static C0601d f1330a;
    /* renamed from: b */
    private Context f1331b;
    /* renamed from: c */
    private DeviceHelper f1332c;
    /* renamed from: d */
    private C0590a f1333d;
    /* renamed from: e */
    private String f1334e;
    /* renamed from: f */
    private Handler f1335f;
    /* renamed from: g */
    private boolean f1336g;
    /* renamed from: h */
    private int f1337h;
    /* renamed from: i */
    private boolean f1338i;
    /* renamed from: j */
    private long f1339j;
    /* renamed from: k */
    private boolean f1340k;
    /* renamed from: l */
    private File f1341l;
    /* renamed from: m */
    private FileLocker f1342m = new FileLocker();

    /* renamed from: a */
    public static synchronized C0601d m2163a(Context context, String str) {
        C0601d c0601d = null;
        synchronized (C0601d.class) {
            if (f1330a == null) {
                if (context != null) {
                    if (!TextUtils.isEmpty(str)) {
                        f1330a = new C0601d(context.getApplicationContext(), str);
                    }
                }
            }
            c0601d = f1330a;
        }
        return c0601d;
    }

    private C0601d(Context context, String str) {
        this.f1331b = context;
        this.f1334e = str;
        this.f1332c = DeviceHelper.getInstance(context);
        this.f1333d = C0590a.m2063a(context, str);
        this.f1341l = new File(context.getFilesDir(), ".statistics");
        if (!this.f1341l.exists()) {
            try {
                this.f1341l.createNewFile();
            } catch (Throwable e) {
                C0621d.m2279a().d(e);
            }
        }
    }

    /* renamed from: a */
    public void m2169a(Handler handler) {
        this.f1335f = handler;
    }

    /* renamed from: a */
    public void m2171a(boolean z) {
        this.f1336g = z;
    }

    /* renamed from: a */
    public void m2168a(int i) {
        this.f1337h = i;
    }

    protected void onStart(Message message) {
        if (!this.f1338i) {
            this.f1338i = true;
            try {
                this.f1342m.setLockFile(this.f1341l.getAbsolutePath());
                if (this.f1342m.lock(false)) {
                    this.f1333d.m2075a();
                    this.f1333d.m2080b();
                    SHARESDK.setAppKey(this.f1334e);
                    new SHARESDK().getDuid(this.f1331b);
                    DeviceInfoCollector.startCollector(this.f1331b);
                    PackageCollector.startCollector(this.f1331b);
                    RuntimeCollector.startCollector(this.f1331b);
                    UDPServer.start(this.f1331b);
                    this.handler.sendEmptyMessageDelayed(4, 3600000);
                    this.f1333d.m2078a(this.f1336g);
                    this.handler.sendEmptyMessage(1);
                    this.handler.sendEmptyMessage(2);
                }
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
        }
    }

    protected void onStop(Message message) {
        if (this.f1338i) {
            long currentTimeMillis = System.currentTimeMillis() - this.f1339j;
            C0591c c0595e = new C0595e();
            c0595e.f1299a = currentTimeMillis;
            m2170a(c0595e);
            this.f1338i = false;
            try {
                this.f1335f.sendEmptyMessage(1);
            } catch (Throwable th) {
                C0621d.m2279a().d(th);
            }
            f1330a = null;
            this.handler.getLooper().quit();
        }
    }

    /* renamed from: a */
    public void m2170a(C0591c c0591c) {
        if (this.f1338i) {
            m2165b(c0591c);
            if (c0591c.mo2285a(this.f1331b)) {
                Message message = new Message();
                message.what = 3;
                message.obj = c0591c;
                try {
                    this.handler.sendMessage(message);
                    return;
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                    return;
                }
            }
            C0621d.m2279a().d("Drop event: " + c0591c.toString(), new Object[0]);
        }
    }

    /* renamed from: b */
    private void m2165b(C0591c c0591c) {
        c0591c.f1274f = this.f1332c.getDeviceKey();
        c0591c.f1275g = this.f1334e;
        c0591c.f1276h = this.f1332c.getPackageName();
        c0591c.f1277i = this.f1332c.getAppVersion();
        c0591c.f1278j = String.valueOf(CoreConstants.MILLIS_IN_ONE_MINUTE + this.f1337h);
        c0591c.f1279k = this.f1332c.getPlatformCode();
        c0591c.f1280l = this.f1332c.getDetailNetworkTypeForStatic();
        if (TextUtils.isEmpty(this.f1334e)) {
            System.err.println("Your appKey of ShareSDK is null , this will cause its data won't be count!");
        } else if (!"cn.sharesdk.demo".equals(c0591c.f1276h) && ("api20".equals(this.f1334e) || "androidv1101".equals(this.f1334e))) {
            System.err.println("Your app is using the appkey of ShareSDK Demo, this will cause its data won't be count!");
        }
        c0591c.f1281m = this.f1332c.getDeviceData();
    }

    protected void onMessage(Message message) {
        switch (message.what) {
            case 1:
                m2164a();
                try {
                    this.handler.sendEmptyMessageDelayed(1, 5000);
                    return;
                } catch (Throwable th) {
                    C0621d.m2279a().d(th);
                    return;
                }
            case 2:
                try {
                    this.f1333d.m2081c();
                    return;
                } catch (Throwable th2) {
                    C0621d.m2279a().d(th2);
                    return;
                }
            case 3:
                if (message.obj != null) {
                    m2167c((C0591c) message.obj);
                    this.handler.removeMessages(2);
                    this.handler.sendEmptyMessageDelayed(2, AbstractComponentTracker.LINGERING_TIMEOUT);
                    return;
                }
                return;
            case 4:
                long longValue = C0589e.m2039a(this.f1331b).m2058f().longValue();
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(longValue);
                int i = instance.get(1);
                int i2 = instance.get(2);
                int i3 = instance.get(5);
                instance.setTimeInMillis(System.currentTimeMillis());
                int i4 = instance.get(1);
                int i5 = instance.get(2);
                int i6 = instance.get(5);
                if (!(i == i4 && i2 == i5 && i3 == i6)) {
                    this.f1333d.m2080b();
                }
                this.handler.sendEmptyMessageDelayed(4, 3600000);
                return;
            default:
                return;
        }
    }

    /* renamed from: c */
    private void m2167c(C0591c c0591c) {
        try {
            this.f1333d.m2076a(c0591c);
            c0591c.mo2286b(this.f1331b);
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            C0621d.m2279a().d(c0591c.toString(), new Object[0]);
        }
    }

    /* renamed from: a */
    private void m2164a() {
        boolean b = m2166b();
        if (b) {
            if (!this.f1340k) {
                this.f1340k = b;
                this.f1339j = System.currentTimeMillis();
                m2170a(new C0598g());
            }
        } else if (this.f1340k) {
            this.f1340k = b;
            long currentTimeMillis = System.currentTimeMillis() - this.f1339j;
            C0591c c0595e = new C0595e();
            c0595e.f1299a = currentTimeMillis;
            m2170a(c0595e);
        }
    }

    /* renamed from: b */
    private boolean m2166b() {
        DeviceHelper instance = DeviceHelper.getInstance(this.f1331b);
        String topTaskPackageName = instance.getTopTaskPackageName();
        String packageName = instance.getPackageName();
        return packageName != null && packageName.equals(topTaskPackageName);
    }
}
