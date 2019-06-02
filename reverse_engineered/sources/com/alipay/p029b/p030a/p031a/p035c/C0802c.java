package com.alipay.p029b.p030a.p031a.p035c;

import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.tscenter.biz.rpc.vkeydfp.request.DeviceDataReportRequest;
import com.alipay.tscenter.biz.rpc.vkeydfp.result.DeviceDataReportResult;

/* renamed from: com.alipay.b.a.a.c.c */
final class C0802c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ DeviceDataReportRequest f1884a;
    /* renamed from: b */
    final /* synthetic */ C0801b f1885b;

    C0802c(C0801b c0801b, DeviceDataReportRequest deviceDataReportRequest) {
        this.f1885b = c0801b;
        this.f1884a = deviceDataReportRequest;
    }

    public final void run() {
        try {
            C0801b.f1880e = this.f1885b.f1883c.reportStaticData(this.f1884a);
        } catch (Throwable th) {
            C0801b.f1880e = new DeviceDataReportResult();
            C0801b.f1880e.success = false;
            C0801b.f1880e.resultCode = "static data rpc upload error, " + C0789a.m3018a(th);
            C0789a.m3018a(th);
        }
    }
}
