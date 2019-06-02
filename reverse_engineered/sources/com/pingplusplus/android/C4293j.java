package com.pingplusplus.android;

import android.content.Context;
import com.tencent.mobileqq.openpay.api.IOpenApi;
import com.tencent.mobileqq.openpay.api.OpenApiFactory;

/* renamed from: com.pingplusplus.android.j */
public class C4293j {
    /* renamed from: a */
    public static String f14969a;
    /* renamed from: b */
    private static C4293j f14970b;
    /* renamed from: c */
    private IOpenApi f14971c;
    /* renamed from: d */
    private PaymentActivity f14972d;

    private C4293j(Context context, String str) {
        f14969a = str;
        this.f14971c = OpenApiFactory.getInstance(context.getApplicationContext(), str);
        if (context instanceof PaymentActivity) {
            this.f14972d = (PaymentActivity) context;
        }
    }

    /* renamed from: a */
    public static C4293j m16994a(Context context, String str) {
        if (f14970b == null) {
            synchronized (C4293j.class) {
                if (f14970b == null) {
                    f14970b = new C4293j(context, str);
                }
            }
        }
        return f14970b;
    }

    /* renamed from: a */
    public IOpenApi m16995a() {
        return this.f14971c;
    }

    /* renamed from: b */
    public PaymentActivity m16996b() {
        return this.f14972d;
    }

    /* renamed from: c */
    public void m16997c() {
        f14970b = null;
        this.f14971c = null;
        f14969a = null;
        this.f14972d = null;
    }
}
