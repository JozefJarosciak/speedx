package com.baidu.location.p042d;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import com.baidu.location.C1102f;
import java.util.Map;

/* renamed from: com.baidu.location.d.e */
public abstract class C1041e {
    /* renamed from: a */
    private static String f2334a = "10.0.0.172";
    /* renamed from: b */
    private static int f2335b = 80;
    /* renamed from: g */
    public static int f2336g = C1090a.f2665g;
    /* renamed from: o */
    protected static int f2337o = 0;
    /* renamed from: h */
    public String f2338h = null;
    /* renamed from: i */
    public int f2339i = 3;
    /* renamed from: j */
    public String f2340j = null;
    /* renamed from: k */
    public Map<String, Object> f2341k = null;
    /* renamed from: l */
    public String f2342l = null;
    /* renamed from: m */
    public byte[] f2343m = null;
    /* renamed from: n */
    public String f2344n = null;

    /* renamed from: a */
    private static int m3663a(Context context, NetworkInfo networkInfo) {
        String toLowerCase;
        if (!(networkInfo == null || networkInfo.getExtraInfo() == null)) {
            toLowerCase = networkInfo.getExtraInfo().toLowerCase();
            if (toLowerCase != null) {
                if (toLowerCase.startsWith("cmwap") || toLowerCase.startsWith("uniwap") || toLowerCase.startsWith("3gwap")) {
                    toLowerCase = Proxy.getDefaultHost();
                    if (toLowerCase == null || toLowerCase.equals("") || toLowerCase.equals("null")) {
                        toLowerCase = "10.0.0.172";
                    }
                    f2334a = toLowerCase;
                    return C1090a.f2662d;
                } else if (toLowerCase.startsWith("ctwap")) {
                    toLowerCase = Proxy.getDefaultHost();
                    if (toLowerCase == null || toLowerCase.equals("") || toLowerCase.equals("null")) {
                        toLowerCase = "10.0.0.200";
                    }
                    f2334a = toLowerCase;
                    return C1090a.f2662d;
                } else if (toLowerCase.startsWith("cmnet") || toLowerCase.startsWith("uninet") || toLowerCase.startsWith("ctnet") || toLowerCase.startsWith("3gnet")) {
                    return C1090a.f2663e;
                }
            }
        }
        toLowerCase = Proxy.getDefaultHost();
        if (toLowerCase != null && toLowerCase.length() > 0) {
            if ("10.0.0.172".equals(toLowerCase.trim())) {
                f2334a = "10.0.0.172";
                return C1090a.f2662d;
            } else if ("10.0.0.200".equals(toLowerCase.trim())) {
                f2334a = "10.0.0.200";
                return C1090a.f2662d;
            }
        }
        return C1090a.f2663e;
    }

    /* renamed from: b */
    private void mo2603b() {
        f2336g = mo2602c();
    }

    /* renamed from: c */
    private int mo2602c() {
        Context serviceContext = C1102f.getServiceContext();
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) serviceContext.getSystemService("connectivity");
            if (connectivityManager == null) {
                return C1090a.f2665g;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                return C1090a.f2665g;
            }
            if (activeNetworkInfo.getType() != 1) {
                return C1041e.m3663a(serviceContext, activeNetworkInfo);
            }
            String defaultHost = Proxy.getDefaultHost();
            return (defaultHost == null || defaultHost.length() <= 0) ? C1090a.f2664f : C1090a.f2666h;
        } catch (Exception e) {
            return C1090a.f2665g;
        }
    }

    /* renamed from: a */
    public abstract void mo2597a();

    /* renamed from: a */
    public abstract void mo2598a(boolean z);

    /* renamed from: a */
    public void m3669a(boolean z, String str) {
        new C1097g(this, str, z).start();
    }

    /* renamed from: b */
    public void m3670b(String str) {
        new C1098h(this, str).start();
    }

    /* renamed from: d */
    public void m3671d() {
        new C1096f(this).start();
    }

    /* renamed from: e */
    public void m3672e() {
        m3669a(false, "loc.map.baidu.com");
    }
}
