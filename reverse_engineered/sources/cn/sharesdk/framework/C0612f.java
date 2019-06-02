package cn.sharesdk.framework;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.text.TextUtils;
import cn.sharesdk.framework.p011b.C0590a;
import cn.sharesdk.framework.utils.C0621d;
import com.mob.commons.eventrecoder.EventRecorder;
import com.mob.tools.SSDKHandlerThread;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Hashon;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/* compiled from: ShareSDKCoreThread */
/* renamed from: cn.sharesdk.framework.f */
public class C0612f extends SSDKHandlerThread {
    /* renamed from: a */
    private C0611a f1372a = C0611a.IDLE;
    /* renamed from: b */
    private Context f1373b;
    /* renamed from: c */
    private HashMap<String, HashMap<String, String>> f1374c;
    /* renamed from: d */
    private ArrayList<Platform> f1375d;
    /* renamed from: e */
    private HashMap<String, Integer> f1376e;
    /* renamed from: f */
    private HashMap<Integer, String> f1377f;
    /* renamed from: g */
    private HashMap<Integer, CustomPlatform> f1378g;
    /* renamed from: h */
    private HashMap<Integer, HashMap<String, Object>> f1379h;
    /* renamed from: i */
    private HashMap<Integer, Service> f1380i;
    /* renamed from: j */
    private String f1381j;
    /* renamed from: k */
    private boolean f1382k;
    /* renamed from: l */
    private String f1383l;
    /* renamed from: m */
    private boolean f1384m;
    /* renamed from: n */
    private boolean f1385n;

    /* compiled from: ShareSDKCoreThread */
    /* renamed from: cn.sharesdk.framework.f$1 */
    class C06091 extends Thread {
        /* renamed from: a */
        final /* synthetic */ C0612f f1365a;

        C06091(C0612f c0612f) {
            this.f1365a = c0612f;
        }

        public void run() {
            try {
                HashMap hashMap = new HashMap();
                if (!this.f1365a.m2253d() && this.f1365a.m2239a(hashMap)) {
                    this.f1365a.m2247b(hashMap);
                }
            } catch (Throwable th) {
                C0621d.m2279a().w(th);
            }
        }
    }

    /* compiled from: ShareSDKCoreThread */
    /* renamed from: cn.sharesdk.framework.f$a */
    private enum C0611a {
        IDLE,
        INITIALIZING,
        READY
    }

    public C0612f(Context context, String str) {
        this.f1381j = str;
        this.f1373b = context.getApplicationContext();
        this.f1374c = new HashMap();
        this.f1375d = new ArrayList();
        this.f1376e = new HashMap();
        this.f1377f = new HashMap();
        this.f1378g = new HashMap();
        this.f1379h = new HashMap();
        this.f1380i = new HashMap();
    }

    /* renamed from: a */
    public void m2238a(boolean z) {
        this.f1382k = z;
    }

    public void startThread() {
        this.f1372a = C0611a.INITIALIZING;
        C0621d.m2280a(this.f1373b, 60068, this.f1381j);
        EventRecorder.prepare(this.f1373b);
        m2222f();
        super.startThread();
    }

    /* renamed from: f */
    private void m2222f() {
        synchronized (this.f1374c) {
            this.f1374c.clear();
            m2223g();
            if (this.f1374c.containsKey("ShareSDK")) {
                HashMap hashMap = (HashMap) this.f1374c.remove("ShareSDK");
                if (hashMap != null) {
                    if (this.f1381j == null) {
                        this.f1381j = (String) hashMap.get("AppKey");
                    }
                    this.f1383l = hashMap.containsKey("UseVersion") ? (String) hashMap.get("UseVersion") : "latest";
                }
            }
        }
    }

    /* renamed from: g */
    private void m2223g() {
        XmlPullParser newPullParser;
        InputStream open;
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            newPullParser = newInstance.newPullParser();
            open = this.f1373b.getAssets().open("ShareSDK.xml");
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
            return;
        }
        newPullParser.setInput(open, "utf-8");
        for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
            if (eventType == 2) {
                String name = newPullParser.getName();
                HashMap hashMap = new HashMap();
                int attributeCount = newPullParser.getAttributeCount();
                for (eventType = 0; eventType < attributeCount; eventType++) {
                    hashMap.put(newPullParser.getAttributeName(eventType), newPullParser.getAttributeValue(eventType).trim());
                }
                this.f1374c.put(name, hashMap);
            }
        }
        open.close();
    }

    protected void onStart(Message message) {
        synchronized (this.f1380i) {
            synchronized (this.f1375d) {
                try {
                    Object checkRecord = EventRecorder.checkRecord("ShareSDK");
                    if (!TextUtils.isEmpty(checkRecord)) {
                        C0590a.m2063a(this.f1373b, this.f1381j).m2077a(null);
                        C0621d.m2279a().w("EventRecorder checkRecord result ==" + checkRecord, new Object[0]);
                        m2254e();
                    }
                    EventRecorder.clear();
                } catch (Throwable th) {
                    this.f1372a = C0611a.READY;
                    this.f1375d.notify();
                    this.f1380i.notify();
                    C0621d.m2279a().w(th);
                }
                m2225i();
                m2226j();
                this.f1372a = C0611a.READY;
                this.f1375d.notify();
                this.f1380i.notify();
                m2224h();
            }
        }
    }

    /* renamed from: h */
    private void m2224h() {
        new C06091(this).start();
    }

    /* renamed from: i */
    private void m2225i() {
        this.f1375d.clear();
        Collection a = new C0608e(this.f1373b, this.f1381j).m2210a();
        if (a != null) {
            this.f1375d.addAll(a);
        }
        synchronized (this.f1376e) {
            synchronized (this.f1377f) {
                Iterator it = this.f1375d.iterator();
                while (it.hasNext()) {
                    Platform platform = (Platform) it.next();
                    this.f1377f.put(Integer.valueOf(platform.getPlatformId()), platform.getName());
                    this.f1376e.put(platform.getName(), Integer.valueOf(platform.getPlatformId()));
                }
            }
        }
    }

    /* renamed from: j */
    private void m2226j() {
        C0608e c0608e = new C0608e(this.f1373b, this.f1381j);
        c0608e.m2214a(this);
        c0608e.m2213a(this.handler, this.f1382k, 68);
    }

    protected void onStop(Message message) {
        synchronized (this.f1380i) {
            for (Entry value : this.f1380i.entrySet()) {
                ((Service) value.getValue()).onUnbind();
            }
            this.f1380i.clear();
        }
        synchronized (this.f1378g) {
            this.f1378g.clear();
        }
        try {
            new C0608e(this.f1373b, this.f1381j).m2218b();
        } catch (Throwable th) {
            C0621d.m2279a().w(th);
            this.handler.getLooper().quit();
            this.f1372a = C0611a.IDLE;
        }
    }

    protected void onMessage(Message message) {
        switch (message.what) {
            case 1:
                this.f1372a = C0611a.IDLE;
                this.handler.getLooper().quit();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m2234a(Class<? extends Service> cls) {
        synchronized (this.f1380i) {
            if (this.f1380i.containsKey(Integer.valueOf(cls.hashCode()))) {
                return;
            }
            try {
                Service service = (Service) cls.newInstance();
                this.f1380i.put(Integer.valueOf(cls.hashCode()), service);
                service.m1975a(this.f1373b);
                service.m1976a(this.f1381j);
                service.onBind();
            } catch (Throwable th) {
                C0621d.m2279a().w(th);
            }
        }
    }

    /* renamed from: b */
    public void m2245b(Class<? extends Service> cls) {
        synchronized (this.f1380i) {
            int hashCode = cls.hashCode();
            if (this.f1380i.containsKey(Integer.valueOf(hashCode))) {
                ((Service) this.f1380i.get(Integer.valueOf(hashCode))).onUnbind();
                this.f1380i.remove(Integer.valueOf(hashCode));
            }
        }
    }

    /* renamed from: c */
    public <T extends Service> T m2248c(Class<T> cls) {
        synchronized (this.f1380i) {
            if (this.f1372a == C0611a.IDLE) {
                return null;
            }
            if (this.f1372a == C0611a.INITIALIZING) {
                try {
                    this.f1380i.wait();
                } catch (Throwable th) {
                    C0621d.m2279a().w(th);
                }
            }
            try {
                Service service = (Service) cls.cast(this.f1380i.get(Integer.valueOf(cls.hashCode())));
                return service;
            } catch (Throwable th2) {
                C0621d.m2279a().w(th2);
                return null;
            }
        }
    }

    /* renamed from: d */
    public void m2252d(Class<? extends CustomPlatform> cls) {
        synchronized (this.f1378g) {
            if (this.f1378g.containsKey(Integer.valueOf(cls.hashCode()))) {
                return;
            }
            try {
                CustomPlatform customPlatform = (CustomPlatform) cls.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{this.f1373b});
                this.f1378g.put(Integer.valueOf(cls.hashCode()), customPlatform);
                synchronized (this.f1376e) {
                    synchronized (this.f1377f) {
                        if (customPlatform != null) {
                            if (customPlatform.m1910b()) {
                                this.f1377f.put(Integer.valueOf(customPlatform.getPlatformId()), customPlatform.getName());
                                this.f1376e.put(customPlatform.getName(), Integer.valueOf(customPlatform.getPlatformId()));
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                C0621d.m2279a().w(th);
            }
        }
    }

    /* renamed from: e */
    public void m2255e(Class<? extends CustomPlatform> cls) {
        int hashCode = cls.hashCode();
        synchronized (this.f1378g) {
            this.f1378g.remove(Integer.valueOf(hashCode));
        }
    }

    /* renamed from: a */
    public Platform m2227a(String str) {
        if (str == null) {
            return null;
        }
        Platform[] a = m2240a();
        if (a == null) {
            return null;
        }
        for (Platform platform : a) {
            if (str.equals(platform.getName())) {
                return platform;
            }
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public cn.sharesdk.framework.Platform[] m2240a() {
        /*
        r10 = this;
        r1 = 0;
        r2 = 0;
        r4 = java.lang.System.currentTimeMillis();
        r3 = r10.f1375d;
        monitor-enter(r3);
        r0 = r10.f1372a;	 Catch:{ all -> 0x004d }
        r6 = cn.sharesdk.framework.C0612f.C0611a.IDLE;	 Catch:{ all -> 0x004d }
        if (r0 != r6) goto L_0x0012;
    L_0x000f:
        monitor-exit(r3);	 Catch:{ all -> 0x004d }
        r0 = r1;
    L_0x0011:
        return r0;
    L_0x0012:
        r0 = r10.f1372a;	 Catch:{ all -> 0x004d }
        r6 = cn.sharesdk.framework.C0612f.C0611a.INITIALIZING;	 Catch:{ all -> 0x004d }
        if (r0 != r6) goto L_0x001d;
    L_0x0018:
        r0 = r10.f1375d;	 Catch:{ Throwable -> 0x0044 }
        r0.wait();	 Catch:{ Throwable -> 0x0044 }
    L_0x001d:
        monitor-exit(r3);	 Catch:{ all -> 0x004d }
        r6 = new java.util.ArrayList;
        r6.<init>();
        r0 = r10.f1375d;
        r3 = r0.iterator();
    L_0x0029:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x0050;
    L_0x002f:
        r0 = r3.next();
        r0 = (cn.sharesdk.framework.Platform) r0;
        if (r0 == 0) goto L_0x0029;
    L_0x0037:
        r7 = r0.m1910b();
        if (r7 == 0) goto L_0x0029;
    L_0x003d:
        r0.m1909a();
        r6.add(r0);
        goto L_0x0029;
    L_0x0044:
        r0 = move-exception;
        r6 = cn.sharesdk.framework.utils.C0621d.m2279a();	 Catch:{ all -> 0x004d }
        r6.w(r0);	 Catch:{ all -> 0x004d }
        goto L_0x001d;
    L_0x004d:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x004d }
        throw r0;
    L_0x0050:
        r0 = r10.f1378g;
        r0 = r0.entrySet();
        r3 = r0.iterator();
    L_0x005a:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x0078;
    L_0x0060:
        r0 = r3.next();
        r0 = (java.util.Map.Entry) r0;
        r0 = r0.getValue();
        r0 = (cn.sharesdk.framework.Platform) r0;
        if (r0 == 0) goto L_0x005a;
    L_0x006e:
        r7 = r0.m1910b();
        if (r7 == 0) goto L_0x005a;
    L_0x0074:
        r6.add(r0);
        goto L_0x005a;
    L_0x0078:
        r0 = r6.size();
        if (r0 > 0) goto L_0x0080;
    L_0x007e:
        r0 = r1;
        goto L_0x0011;
    L_0x0080:
        r0 = r6.size();
        r3 = new cn.sharesdk.framework.Platform[r0];
        r1 = r2;
    L_0x0087:
        r0 = r3.length;
        if (r1 >= r0) goto L_0x0096;
    L_0x008a:
        r0 = r6.get(r1);
        r0 = (cn.sharesdk.framework.Platform) r0;
        r3[r1] = r0;
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0087;
    L_0x0096:
        r0 = cn.sharesdk.framework.utils.C0621d.m2279a();
        r1 = "sort list use time: %s";
        r6 = 1;
        r6 = new java.lang.Object[r6];
        r8 = java.lang.System.currentTimeMillis();
        r4 = r8 - r4;
        r4 = java.lang.Long.valueOf(r4);
        r6[r2] = r4;
        r0.i(r1, r6);
        r0 = r3;
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.framework.f.a():cn.sharesdk.framework.Platform[]");
    }

    /* renamed from: b */
    public String m2242b() {
        try {
            return new C0608e(this.f1373b, this.f1381j).m2219c();
        } catch (Throwable th) {
            C0621d.m2279a().w(th);
            return "2.7.10";
        }
    }

    /* renamed from: a */
    public void m2231a(int i) {
        NetworkHelper.connectionTimeout = i;
    }

    /* renamed from: b */
    public void m2244b(int i) {
        NetworkHelper.readTimout = i;
    }

    /* renamed from: b */
    public void m2246b(boolean z) {
        this.f1384m = z;
    }

    /* renamed from: c */
    public boolean m2251c() {
        return this.f1384m;
    }

    /* renamed from: a */
    public void m2233a(int i, Platform platform) {
        new C0608e(this.f1373b, this.f1381j).m2212a(i, platform);
    }

    /* renamed from: a */
    public void m2235a(String str, int i) {
        new C0608e(this.f1373b, this.f1381j).m2215a(str, i);
    }

    /* renamed from: a */
    public void m2237a(String str, HashMap<String, Object> hashMap) {
        synchronized (this.f1374c) {
            HashMap hashMap2;
            HashMap hashMap3 = (HashMap) this.f1374c.get(str);
            if (hashMap3 == null) {
                hashMap2 = new HashMap();
            } else {
                hashMap2 = hashMap3;
            }
            synchronized (hashMap2) {
                for (Entry entry : hashMap.entrySet()) {
                    String str2 = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value != null) {
                        hashMap2.put(str2, String.valueOf(value));
                    }
                }
            }
            this.f1374c.put(str, hashMap2);
        }
        synchronized (this.f1375d) {
            if (this.f1372a == C0611a.INITIALIZING) {
                try {
                    this.f1375d.wait();
                } catch (Throwable th) {
                    C0621d.m2279a().w(th);
                }
            }
            Iterator it = this.f1375d.iterator();
            while (it.hasNext()) {
                Platform platform = (Platform) it.next();
                if (platform != null && platform.getName().equals(str)) {
                    platform.m1909a();
                    break;
                }
            }
        }
    }

    /* renamed from: c */
    public String m2249c(int i) {
        String str;
        synchronized (this.f1377f) {
            str = (String) this.f1377f.get(Integer.valueOf(i));
        }
        return str;
    }

    /* renamed from: b */
    public int m2241b(String str) {
        int intValue;
        synchronized (this.f1376e) {
            if (this.f1376e.containsKey(str)) {
                intValue = ((Integer) this.f1376e.get(str)).intValue();
            } else {
                intValue = 0;
            }
        }
        return intValue;
    }

    /* renamed from: a */
    public void m2236a(String str, String str2) {
        synchronized (this.f1374c) {
            this.f1374c.put(str2, (HashMap) this.f1374c.get(str));
        }
    }

    /* renamed from: a */
    public void m2232a(int i, int i2) {
        synchronized (this.f1379h) {
            new C0608e(this.f1373b, this.f1381j).m2211a(i, i2, this.f1379h);
        }
    }

    /* renamed from: b */
    public String m2243b(String str, String str2) {
        String str3;
        synchronized (this.f1374c) {
            HashMap hashMap = (HashMap) this.f1374c.get(str);
            if (hashMap == null) {
                str3 = null;
            } else {
                str3 = (String) hashMap.get(str2);
            }
        }
        return str3;
    }

    /* renamed from: a */
    public String m2228a(int i, String str) {
        String a;
        synchronized (this.f1379h) {
            a = new C0608e(this.f1373b, this.f1381j).m2206a(i, str, this.f1379h);
        }
        return a;
    }

    /* renamed from: d */
    public boolean m2253d() {
        boolean z;
        synchronized (this.f1379h) {
            if (this.f1379h == null || this.f1379h.size() <= 0) {
                z = this.f1385n;
            } else {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: a */
    public boolean m2239a(HashMap<String, Object> hashMap) {
        if (C0611a.READY != this.f1372a) {
            C0621d.m2279a().d("Statistics module unopened", new Object[0]);
            return false;
        }
        final C0590a a = C0590a.m2063a(this.f1373b, this.f1381j);
        boolean a2 = m2220a(a, a.m2082d(), hashMap);
        if (a2) {
            this.f1385n = true;
            new Thread(this) {
                /* renamed from: b */
                final /* synthetic */ C0612f f1367b;

                public void run() {
                    try {
                        HashMap e = a.m2083e();
                        HashMap hashMap = new HashMap();
                        if (this.f1367b.m2220a(a, e, hashMap)) {
                            this.f1367b.m2247b(hashMap);
                        }
                        a.m2077a(e);
                    } catch (Throwable th) {
                        C0621d.m2279a().w(th);
                    }
                }
            }.start();
            return a2;
        }
        try {
            HashMap e = a.m2083e();
            a2 = m2220a(a, e, hashMap);
            if (a2) {
                a.m2077a(e);
            }
            this.f1385n = true;
            return a2;
        } catch (Throwable th) {
            C0621d.m2279a().w(th);
            this.f1385n = false;
            return a2;
        }
    }

    /* renamed from: a */
    private boolean m2220a(C0590a c0590a, HashMap<String, Object> hashMap, HashMap<String, Object> hashMap2) {
        try {
            if (hashMap.containsKey("error")) {
                C0621d.m2279a().i("ShareSDK parse sns config ==>>", new Object[]{new Hashon().fromHashMap(hashMap)});
                return false;
            } else if (hashMap.containsKey("res")) {
                String str = (String) hashMap.get("res");
                if (str == null) {
                    return false;
                }
                hashMap2.putAll(c0590a.m2079b(str));
                return true;
            } else {
                C0621d.m2279a().d("ShareSDK platform config result ==>>", new Object[]{"SNS configuration is empty"});
                return false;
            }
        } catch (Throwable th) {
            C0621d.m2279a().w(th);
            return false;
        }
    }

    /* renamed from: b */
    public boolean m2247b(HashMap<String, Object> hashMap) {
        boolean a;
        synchronized (this.f1379h) {
            this.f1379h.clear();
            a = new C0608e(this.f1373b, this.f1381j).m2217a((HashMap) hashMap, this.f1379h);
        }
        return a;
    }

    /* renamed from: a */
    public String m2230a(String str, boolean z, int i, String str2) {
        return C0611a.READY != this.f1372a ? str : new C0608e(this.f1373b, this.f1381j).m2209a(str, z, i, str2);
    }

    /* renamed from: c */
    public String m2250c(String str) {
        if (C0611a.READY != this.f1372a) {
            return null;
        }
        return new C0608e(this.f1373b, this.f1381j).m2208a(str);
    }

    /* renamed from: a */
    public String m2229a(Bitmap bitmap) {
        if (C0611a.READY != this.f1372a) {
            return null;
        }
        return new C0608e(this.f1373b, this.f1381j).m2207a(bitmap);
    }

    /* renamed from: e */
    public void m2254e() {
        try {
            C4275R.clearCache(this.f1373b);
        } catch (Throwable th) {
            C0621d.m2279a().w(th);
        }
    }
}
