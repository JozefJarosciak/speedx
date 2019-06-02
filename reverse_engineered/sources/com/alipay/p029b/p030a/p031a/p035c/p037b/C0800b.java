package com.alipay.p029b.p030a.p031a.p035c.p037b;

import android.content.Context;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.p029b.p030a.p031a.p035c.C0798a;
import com.alipay.p029b.p030a.p031a.p035c.C0801b;
import com.alipay.p029b.p030a.p031a.p035c.p036a.C0796b;
import com.alipay.p029b.p030a.p031a.p035c.p036a.C0797c;
import com.alipay.tscenter.biz.rpc.vkeydfp.request.DeviceDataReportRequest;
import com.alipay.tscenter.biz.rpc.vkeydfp.result.DeviceDataReportResult;

/* renamed from: com.alipay.b.a.a.c.b.b */
public final class C0800b implements C0799a {
    /* renamed from: a */
    private static C0799a f1877a = null;
    /* renamed from: b */
    private static C0798a f1878b = null;

    /* renamed from: a */
    public static C0799a m3116a(Context context, String str) {
        C0798a c0798a = null;
        if (context == null) {
            return null;
        }
        if (f1877a == null) {
            if (context != null) {
                c0798a = C0801b.m3119a(context, str);
            }
            f1878b = c0798a;
            f1877a = new C0800b();
        }
        return f1877a;
    }

    /* renamed from: a */
    public final C0796b mo2419a(C0797c c0797c) {
        DeviceDataReportRequest deviceDataReportRequest = new DeviceDataReportRequest();
        deviceDataReportRequest.os = c0797c.m3095a();
        deviceDataReportRequest.apdid = c0797c.m3098b();
        deviceDataReportRequest.pubApdid = c0797c.m3100c();
        deviceDataReportRequest.priApdid = c0797c.m3102d();
        deviceDataReportRequest.token = c0797c.m3104e();
        deviceDataReportRequest.umidToken = c0797c.m3106f();
        deviceDataReportRequest.version = c0797c.m3108g();
        deviceDataReportRequest.lastTime = c0797c.m3110h();
        deviceDataReportRequest.dataMap = c0797c.m3111i();
        DeviceDataReportResult a = f1878b.mo2421a(deviceDataReportRequest);
        C0796b c0796b = new C0796b();
        if (a == null) {
            return null;
        }
        c0796b.a = a.success;
        c0796b.b = a.resultCode;
        c0796b.f1860c = a.apdid;
        c0796b.f1861d = a.token;
        c0796b.f1862e = a.currentTime;
        c0796b.f1863f = a.version;
        c0796b.f1864g = a.vkeySwitch;
        c0796b.f1866i = a.appListVer;
        String str = a.bugTrackSwitch;
        c0796b.f1865h = "0";
        c0796b.f1867j = "0";
        if (!C0789a.m3023b(str) || str.length() <= 0) {
            return c0796b;
        }
        c0796b.f1865h = str.charAt(0);
        return c0796b;
    }

    /* renamed from: a */
    public final boolean mo2420a(String str) {
        return f1878b.mo2422a(str);
    }
}
