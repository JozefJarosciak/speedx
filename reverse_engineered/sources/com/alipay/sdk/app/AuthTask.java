package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C0823a;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.cons.C0846c;
import com.alipay.sdk.data.C0847a;
import com.alipay.sdk.data.C0849c;
import com.alipay.sdk.packet.impl.C0863a;
import com.alipay.sdk.protocol.C0867a;
import com.alipay.sdk.protocol.C0868b;
import com.alipay.sdk.sys.C0869a;
import com.alipay.sdk.sys.C0870b;
import com.alipay.sdk.util.C0877e;
import com.alipay.sdk.util.C0877e.C0813a;
import com.alipay.sdk.util.C0882j;
import com.alipay.sdk.util.C0885l;
import com.alipay.sdk.widget.C0889a;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AuthTask {
    /* renamed from: a */
    static final Object f1900a = C0877e.class;
    /* renamed from: b */
    private static final int f1901b = 73;
    /* renamed from: c */
    private Activity f1902c;
    /* renamed from: d */
    private C0889a f1903d;

    public AuthTask(Activity activity) {
        this.f1902c = activity;
        C0870b a = C0870b.m3386a();
        Context context = this.f1902c;
        C0849c.m3265a();
        a.m3391a(context);
        C0823a.m3182a(activity);
        this.f1903d = new C0889a(activity, C0889a.f2242c);
    }

    /* renamed from: a */
    private C0813a m3139a() {
        return new C0814a(this);
    }

    /* renamed from: b */
    private void m3145b() {
        if (this.f1903d != null) {
            this.f1903d.m3498a();
        }
    }

    /* renamed from: c */
    private void m3146c() {
        if (this.f1903d != null) {
            this.f1903d.m3499b();
        }
    }

    public synchronized Map<String, String> authV2(String str, boolean z) {
        return C0882j.m3455a(auth(str, z));
    }

    public synchronized String auth(String str, boolean z) {
        String a;
        if (z) {
            m3145b();
        }
        C0870b a2 = C0870b.m3386a();
        Context context = this.f1902c;
        C0849c.m3265a();
        a2.m3391a(context);
        String a3 = C0821h.m3171a();
        try {
            Context context2 = this.f1902c;
            String a4 = new C0869a(this.f1902c).m3384a(str);
            if (m3143a(context2)) {
                a = new C0877e(context2, new C0814a(this)).m3444a(a4);
                if (!TextUtils.equals(a, C0877e.f2209b)) {
                    if (TextUtils.isEmpty(a)) {
                        a = C0821h.m3171a();
                    }
                    C0847a.m3258b().m3264a(this.f1902c);
                    m3146c();
                    C0823a.m3183a(this.f1902c, str);
                }
            }
            a = m3144b(context2, a4);
            C0847a.m3258b().m3264a(this.f1902c);
            m3146c();
            C0823a.m3183a(this.f1902c, str);
        } catch (Exception e) {
            C0847a.m3258b().m3264a(this.f1902c);
            m3146c();
            C0823a.m3183a(this.f1902c, str);
            a = a3;
        } catch (Throwable th) {
            C0847a.m3258b().m3264a(this.f1902c);
            m3146c();
            C0823a.m3183a(this.f1902c, str);
        }
        return a;
    }

    /* renamed from: a */
    private String m3140a(Activity activity, String str) {
        String a = new C0869a(this.f1902c).m3384a(str);
        if (!m3143a((Context) activity)) {
            return m3144b(activity, a);
        }
        String a2 = new C0877e(activity, new C0814a(this)).m3444a(a);
        if (TextUtils.equals(a2, C0877e.f2209b)) {
            return m3144b(activity, a);
        }
        if (TextUtils.isEmpty(a2)) {
            return C0821h.m3171a();
        }
        return a2;
    }

    /* renamed from: b */
    private String m3144b(Activity activity, String str) {
        String a;
        C0822i c0822i;
        m3145b();
        C0822i c0822i2 = null;
        try {
            List a2 = C0868b.m3372a(new C0863a().mo2428a((Context) activity, str).m3332a().optJSONObject(C0846c.f2072c).optJSONObject(C0846c.f2073d));
            m3146c();
            for (int i = 0; i < a2.size(); i++) {
                if (((C0868b) a2.get(i)).f2157a == C0867a.WapPay) {
                    a = m3141a((C0868b) a2.get(i));
                    return a;
                }
            }
            m3146c();
            c0822i = null;
        } catch (IOException e) {
            a = e;
            c0822i2 = C0822i.m3178a(C0822i.NETWORK_ERROR.f1938h);
            C0823a.m3187a(C0825c.f1951a, (Throwable) a);
            c0822i = c0822i2;
            if (c0822i == null) {
                c0822i = C0822i.m3178a(C0822i.FAILED.f1938h);
            }
            return C0821h.m3172a(c0822i.f1938h, c0822i.f1939i, "");
        } catch (Throwable th) {
            a = th;
            C0823a.m3185a(C0825c.f1952b, C0825c.f1969s, (Throwable) a);
            c0822i = c0822i2;
            if (c0822i == null) {
                c0822i = C0822i.m3178a(C0822i.FAILED.f1938h);
            }
            return C0821h.m3172a(c0822i.f1938h, c0822i.f1939i, "");
        } finally {
            m3146c();
        }
        if (c0822i == null) {
            c0822i = C0822i.m3178a(C0822i.FAILED.f1938h);
        }
        return C0821h.m3172a(c0822i.f1938h, c0822i.f1939i, "");
    }

    /* renamed from: a */
    private String m3141a(C0868b c0868b) {
        String[] strArr = c0868b.f2158b;
        Bundle bundle = new Bundle();
        bundle.putString("url", strArr[0]);
        Intent intent = new Intent(this.f1902c, H5AuthActivity.class);
        intent.putExtras(bundle);
        this.f1902c.startActivity(intent);
        synchronized (f1900a) {
            try {
                f1900a.wait();
            } catch (InterruptedException e) {
                return C0821h.m3171a();
            }
        }
        String str = C0821h.f1929a;
        if (TextUtils.isEmpty(str)) {
            return C0821h.m3171a();
        }
        return str;
    }

    /* renamed from: a */
    private static boolean m3143a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(C0885l.f2234b, 128);
            if (packageInfo != null && packageInfo.versionCode >= 73) {
                return true;
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }
}
