package com.alipay.p029b.p030a.p031a.p035c;

import android.content.Context;
import com.alipay.android.phone.mrpc.core.C0745w;
import com.alipay.android.phone.mrpc.core.C0746h;
import com.alipay.android.phone.mrpc.core.aa;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.tscenter.biz.rpc.deviceFp.BugTrackMessageService;
import com.alipay.tscenter.biz.rpc.vkeydfp.DeviceDataReportService;
import com.alipay.tscenter.biz.rpc.vkeydfp.request.DeviceDataReportRequest;
import com.alipay.tscenter.biz.rpc.vkeydfp.result.DeviceDataReportResult;
import org.json.JSONObject;

/* renamed from: com.alipay.b.a.a.c.b */
public final class C0801b implements C0798a {
    /* renamed from: d */
    private static C0801b f1879d = null;
    /* renamed from: e */
    private static DeviceDataReportResult f1880e;
    /* renamed from: a */
    private C0745w f1881a = null;
    /* renamed from: b */
    private BugTrackMessageService f1882b = null;
    /* renamed from: c */
    private DeviceDataReportService f1883c = null;

    private C0801b(Context context, String str) {
        aa aaVar = new aa();
        aaVar.m2833a(str);
        this.f1881a = new C0746h(context);
        this.f1882b = (BugTrackMessageService) this.f1881a.mo2412a(BugTrackMessageService.class, aaVar);
        this.f1883c = (DeviceDataReportService) this.f1881a.mo2412a(DeviceDataReportService.class, aaVar);
    }

    /* renamed from: a */
    public static synchronized C0801b m3119a(Context context, String str) {
        C0801b c0801b;
        synchronized (C0801b.class) {
            if (f1879d == null) {
                f1879d = new C0801b(context, str);
            }
            c0801b = f1879d;
        }
        return c0801b;
    }

    /* renamed from: a */
    public final DeviceDataReportResult mo2421a(DeviceDataReportRequest deviceDataReportRequest) {
        if (this.f1883c != null) {
            f1880e = null;
            new Thread(new C0802c(this, deviceDataReportRequest)).start();
            int i = 300000;
            while (f1880e == null && i >= 0) {
                Thread.sleep(50);
                i -= 50;
            }
        }
        return f1880e;
    }

    /* renamed from: a */
    public final boolean mo2422a(String str) {
        if (C0789a.m3020a(str)) {
            return false;
        }
        boolean booleanValue;
        if (this.f1882b != null) {
            String str2 = null;
            try {
                str2 = this.f1882b.logCollect(C0789a.m3026e(str));
            } catch (Exception e) {
            }
            if (!C0789a.m3020a(str2)) {
                booleanValue = ((Boolean) new JSONObject(str2).get("success")).booleanValue();
                return booleanValue;
            }
        }
        booleanValue = false;
        return booleanValue;
    }
}
