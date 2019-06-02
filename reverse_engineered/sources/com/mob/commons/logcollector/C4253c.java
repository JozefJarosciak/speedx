package com.mob.commons.logcollector;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.util.C0882j;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mob.tools.MobLog;
import com.mob.tools.SSDKHandlerThread;
import com.mob.tools.log.NLog;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.FileLocker;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.ReflectHelper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.GZIPOutputStream;

/* compiled from: LogsManager */
/* renamed from: com.mob.commons.logcollector.c */
public class C4253c extends SSDKHandlerThread {
    /* renamed from: a */
    private static C4253c f14934a;
    /* renamed from: b */
    private static String f14935b = "http://api.exc.mob.com:80";
    /* renamed from: c */
    private HashMap<String, Integer> f14936c;
    /* renamed from: d */
    private Context f14937d;
    /* renamed from: e */
    private NetworkHelper f14938e = new NetworkHelper();
    /* renamed from: f */
    private C4254d f14939f;
    /* renamed from: g */
    private File f14940g;
    /* renamed from: h */
    private FileLocker f14941h;

    private C4253c(Context context) {
        this.f14937d = context.getApplicationContext();
        this.f14939f = C4254d.m16916a(context);
        this.f14936c = new HashMap();
        this.f14941h = new FileLocker();
        this.f14940g = new File(context.getFilesDir(), ".lock");
        if (!this.f14940g.exists()) {
            try {
                this.f14940g.createNewFile();
            } catch (Throwable e) {
                MobLog.getInstance().m16946w(e);
            }
        }
        NLog.setContext(context);
        startThread();
    }

    /* renamed from: a */
    public Context m16912a() {
        return this.f14937d;
    }

    /* renamed from: a */
    public static synchronized C4253c m16901a(Context context) {
        C4253c c4253c;
        synchronized (C4253c.class) {
            if (f14934a == null) {
                f14934a = new C4253c(context);
            }
            c4253c = f14934a;
        }
        return c4253c;
    }

    /* renamed from: a */
    public void m16914a(int i, String str, String str2) {
        Message message = new Message();
        message.what = 100;
        message.arg1 = i;
        message.obj = new Object[]{str, str2};
        this.handler.sendMessage(message);
    }

    /* renamed from: a */
    public void m16913a(int i, int i2, String str, String str2, String str3) {
        Message message = new Message();
        message.what = 101;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = new Object[]{str, str2, str3};
        this.handler.sendMessage(message);
    }

    /* renamed from: a */
    private void m16904a(Message message) {
        this.handler.sendMessageDelayed(message, 1000);
    }

    /* renamed from: b */
    public void m16915b(int i, int i2, String str, String str2, String str3) {
        m16913a(i, i2, str, str2, str3);
        try {
            this.handler.wait();
        } catch (Throwable th) {
        }
    }

    protected void onMessage(Message message) {
        switch (message.what) {
            case 100:
                m16908b(message);
                return;
            case 101:
                m16911c(message);
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    private void m16908b(Message message) {
        try {
            int i = message.arg1;
            Object[] objArr = (Object[]) message.obj;
            String str = (String) objArr[0];
            String str2 = (String) objArr[1];
            m16907b(i, str, str2);
            m16903a(i, str, str2, null);
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
        }
    }

    /* renamed from: c */
    private void m16911c(Message message) {
        int c;
        String MD5;
        try {
            int i = message.arg1;
            Object[] objArr = (Object[]) message.obj;
            String str = (String) objArr[0];
            String str2 = (String) objArr[1];
            String str3 = (String) objArr[2];
            int i2 = 1;
            if (message.arg2 == 0) {
                i2 = 2;
            } else if (message.arg2 == 2) {
                i2 = 1;
            }
            String f = this.f14939f.m16928f();
            if (!TextUtils.isEmpty(f)) {
                ArrayList arrayList = (ArrayList) new Hashon().fromJson(f).get("fakelist");
                if (arrayList != null && arrayList.size() > 0) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        f = (String) it.next();
                        if (!TextUtils.isEmpty(f) && str3.contains(f)) {
                            return;
                        }
                    }
                }
            }
            c = this.f14939f.m16924c();
            int d = this.f14939f.m16926d();
            int e = this.f14939f.m16927e();
            if (3 != i2 || -1 != e) {
                if (1 != i2 || -1 != c) {
                    if (2 != i2 || -1 != d) {
                        MD5 = Data.MD5(str3);
                        this.f14941h.setLockFile(this.f14940g.getAbsolutePath());
                        if (this.f14941h.lock(false)) {
                            C4256f.m16929a(this.f14937d, System.currentTimeMillis() - this.f14939f.m16917a(), str3, i2, MD5);
                        }
                        this.f14941h.release();
                        this.f14936c.remove(MD5);
                        if (3 == i2 && 1 == e) {
                            m16903a(i, str, str2, new String[]{String.valueOf(3)});
                        } else if (1 == i2 && 1 == c) {
                            m16903a(i, str, str2, new String[]{String.valueOf(1)});
                        } else if (2 == i2 && 1 == d) {
                            m16903a(i, str, str2, new String[]{String.valueOf(2)});
                        }
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m16946w(th);
        }
    }

    /* renamed from: b */
    private String m16906b() {
        return f14935b + "/errconf";
    }

    /* renamed from: b */
    private void m16907b(int i, String str, String str2) throws Throwable {
        ArrayList arrayList = new ArrayList();
        Object invokeStaticMethod = ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14937d);
        arrayList.add(new KVPair(Action.KEY_ATTRIBUTE, str2));
        arrayList.add(new KVPair("sdk", str));
        arrayList.add(new KVPair("apppkg", String.valueOf(ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getPackageName", new Object[0]))));
        arrayList.add(new KVPair("appver", String.valueOf(ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getAppVersion", new Object[0]))));
        arrayList.add(new KVPair("sdkver", String.valueOf(i)));
        arrayList.add(new KVPair("plat", String.valueOf(ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getPlatformCode", new Object[0]))));
        try {
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = 10000;
            networkTimeOut.connectionTimeout = 10000;
            MobLog.getInstance().m16939i("get server config == %s", this.f14938e.httpPost(m16906b(), arrayList, null, null, networkTimeOut));
            HashMap fromJson = new Hashon().fromJson(r0);
            if ("-200".equals(String.valueOf(fromJson.get("status")))) {
                MobLog.getInstance().m16939i("error log server config response fail !!", new Object[0]);
                return;
            }
            invokeStaticMethod = fromJson.get(C0882j.f2229c);
            if (invokeStaticMethod != null && (invokeStaticMethod instanceof HashMap)) {
                HashMap hashMap;
                fromJson = (HashMap) invokeStaticMethod;
                if (fromJson.containsKey("timestamp")) {
                    this.f14939f.m16919a(System.currentTimeMillis() - C4275R.parseLong(String.valueOf(fromJson.get("timestamp"))));
                }
                if (C0844a.f2048d.equals(String.valueOf(fromJson.get("enable")))) {
                    this.f14939f.m16921a(true);
                } else {
                    this.f14939f.m16921a(false);
                }
                Object obj = fromJson.get("upconf");
                if (obj != null && (obj instanceof HashMap)) {
                    hashMap = (HashMap) obj;
                    String valueOf = String.valueOf(hashMap.get("crash"));
                    String valueOf2 = String.valueOf(hashMap.get("sdkerr"));
                    String valueOf3 = String.valueOf(hashMap.get("apperr"));
                    this.f14939f.m16918a(Integer.parseInt(valueOf));
                    this.f14939f.m16922b(Integer.parseInt(valueOf2));
                    this.f14939f.m16925c(Integer.parseInt(valueOf3));
                }
                if (fromJson.containsKey("requesthost") && fromJson.containsKey("requestport")) {
                    obj = String.valueOf(fromJson.get("requesthost"));
                    Object valueOf4 = String.valueOf(fromJson.get("requestport"));
                    if (!(TextUtils.isEmpty(obj) || TextUtils.isEmpty(valueOf4))) {
                        f14935b = "http://" + obj + ":" + valueOf4;
                    }
                }
                invokeStaticMethod = fromJson.get("filter");
                if (invokeStaticMethod != null && (invokeStaticMethod instanceof ArrayList)) {
                    ArrayList arrayList2 = (ArrayList) invokeStaticMethod;
                    if (arrayList2.size() > 0) {
                        hashMap = new HashMap();
                        hashMap.put("fakelist", arrayList2);
                        this.f14939f.m16920a(new Hashon().fromHashMap(hashMap));
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m16934d(th);
        }
    }

    /* renamed from: c */
    private String m16909c() {
        return f14935b + "/errlog";
    }

    /* renamed from: a */
    private void m16903a(int i, String str, String str2, String[] strArr) {
        try {
            if (this.f14939f.m16923b()) {
                if ("none".equals((String) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14937d), "getDetailNetworkTypeForStatic", new Object[0]))) {
                    throw new IllegalStateException("network is disconnected!");
                }
                ArrayList a = C4256f.m16932a(this.f14937d, strArr);
                for (int i2 = 0; i2 < a.size(); i2++) {
                    C4255e c4255e = (C4255e) a.get(i2);
                    HashMap c = m16910c(i, str, str2);
                    c.put("errmsg", c4255e.f14944a);
                    if (m16905a(m16902a(new Hashon().fromHashMap(c)), true)) {
                        C4256f.m16930a(this.f14937d, c4255e.f14945b);
                    }
                }
            }
        } catch (Throwable th) {
            MobLog.getInstance().m16940i(th);
        }
    }

    /* renamed from: c */
    private HashMap<String, Object> m16910c(int i, String str, String str2) throws Throwable {
        HashMap<String, Object> hashMap = new HashMap();
        Object invokeStaticMethod = ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14937d);
        hashMap.put(Action.KEY_ATTRIBUTE, str2);
        hashMap.put("plat", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getPlatformCode", new Object[0]));
        hashMap.put("sdk", str);
        hashMap.put("sdkver", Integer.valueOf(i));
        hashMap.put("appname", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getAppName", new Object[0]));
        hashMap.put("apppkg", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getPackageName", new Object[0]));
        hashMap.put("appver", String.valueOf(ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getAppVersion", new Object[0])));
        hashMap.put("deviceid", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getDeviceKey", new Object[0]));
        hashMap.put(MapboxEvent.ATTRIBUTE_MODEL, ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getModel", new Object[0]));
        hashMap.put("mac", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getMacAddress", new Object[0]));
        hashMap.put("udid", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getDeviceId", new Object[0]));
        hashMap.put("sysver", String.valueOf(ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getOSVersionInt", new Object[0])));
        hashMap.put("networktype", ReflectHelper.invokeInstanceMethod(invokeStaticMethod, "getDetailNetworkTypeForStatic", new Object[0]));
        return hashMap;
    }

    /* renamed from: a */
    private String m16902a(String str) throws Throwable {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        byte[] bArr = new byte[1024];
        while (true) {
            int read = byteArrayInputStream.read(bArr, 0, 1024);
            if (read != -1) {
                gZIPOutputStream.write(bArr, 0, read);
            } else {
                gZIPOutputStream.flush();
                gZIPOutputStream.close();
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.flush();
                byteArrayOutputStream.close();
                byteArrayInputStream.close();
                return Base64.encodeToString(toByteArray, 2);
            }
        }
    }

    /* renamed from: a */
    private boolean m16905a(String str, boolean z) throws Throwable {
        try {
            if ("none".equals((String) ReflectHelper.invokeInstanceMethod(ReflectHelper.invokeStaticMethod("DeviceHelper", "getInstance", this.f14937d), "getDetailNetworkTypeForStatic", new Object[0]))) {
                throw new IllegalStateException("network is disconnected!");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair(ANSIConstants.ESC_END, str));
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = 10000;
            networkTimeOut.connectionTimeout = 10000;
            this.f14938e.httpPost(m16909c(), arrayList, null, null, networkTimeOut);
            return true;
        } catch (Throwable th) {
            MobLog.getInstance().m16940i(th);
            return false;
        }
    }
}
