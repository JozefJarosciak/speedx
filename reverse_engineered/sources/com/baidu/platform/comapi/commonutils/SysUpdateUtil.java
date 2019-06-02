package com.baidu.platform.comapi.commonutils;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.Proxy;
import com.baidu.mapapi.NetworkUtil;
import com.baidu.platform.comapi.util.SysUpdateObserver;
import com.baidu.platform.comjni.engine.AppEngine;
import com.baidu.platform.comjni.map.commonmemcache.C1287a;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;

public class SysUpdateUtil implements SysUpdateObserver {
    /* renamed from: a */
    static C1287a f3611a = new C1287a();
    /* renamed from: b */
    public static boolean f3612b = false;
    /* renamed from: c */
    public static String f3613c = "";
    /* renamed from: d */
    public static int f3614d = 0;

    public void init() {
        if (f3611a != null) {
            f3611a.m4947a();
            f3611a.m4948b();
        }
    }

    public void updateNetworkInfo(Context context) {
        NetworkUtil.updateNetworkProxy(context);
    }

    public void updateNetworkProxy(Context context) {
        NetworkInfo activeNetworkInfo = NetworkUtil.getActiveNetworkInfo(context);
        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
            String toLowerCase = activeNetworkInfo.getTypeName().toLowerCase();
            if (toLowerCase.equals(MapboxEvent.ATTRIBUTE_WIFI) && activeNetworkInfo.isConnected()) {
                AppEngine.SetProxyInfo(null, 0);
                f3612b = false;
            } else if (toLowerCase.equals("mobile") || (toLowerCase.equals(MapboxEvent.ATTRIBUTE_WIFI) && !NetworkUtil.isWifiConnected(activeNetworkInfo))) {
                String extraInfo = activeNetworkInfo.getExtraInfo();
                f3612b = false;
                if (extraInfo != null) {
                    extraInfo = extraInfo.toLowerCase();
                    if (extraInfo.startsWith("cmwap") || extraInfo.startsWith("uniwap") || extraInfo.startsWith("3gwap")) {
                        f3613c = "10.0.0.172";
                        f3614d = 80;
                        f3612b = true;
                    } else if (extraInfo.startsWith("ctwap")) {
                        f3613c = "10.0.0.200";
                        f3614d = 80;
                        f3612b = true;
                    } else if (extraInfo.startsWith("cmnet") || extraInfo.startsWith("uninet") || extraInfo.startsWith("ctnet") || extraInfo.startsWith("3gnet")) {
                        f3612b = false;
                    }
                } else {
                    extraInfo = Proxy.getDefaultHost();
                    int defaultPort = Proxy.getDefaultPort();
                    if (extraInfo != null && extraInfo.length() > 0) {
                        if ("10.0.0.172".equals(extraInfo.trim())) {
                            f3613c = "10.0.0.172";
                            f3614d = defaultPort;
                            f3612b = true;
                        } else if ("10.0.0.200".equals(extraInfo.trim())) {
                            f3613c = "10.0.0.200";
                            f3614d = 80;
                            f3612b = true;
                        }
                    }
                }
                if (f3612b) {
                    AppEngine.SetProxyInfo(f3613c, f3614d);
                } else {
                    AppEngine.SetProxyInfo(null, 0);
                }
            }
        }
    }

    public void updatePhoneInfo() {
        if (f3611a != null) {
            f3611a.m4948b();
        }
    }
}
