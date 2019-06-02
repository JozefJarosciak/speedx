package com.beastbikes.android.update.p079a;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.alipay.sdk.util.C0882j;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.update.p162b.C2548a;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import org.json.JSONObject;

/* compiled from: UpdateManager */
/* renamed from: com.beastbikes.android.update.a.a */
public class C2547a extends C1397a {
    /* renamed from: a */
    private C1453b f12022a = null;
    /* renamed from: b */
    private Context f12023b;

    /* compiled from: UpdateManager */
    /* renamed from: com.beastbikes.android.update.a.a$a */
    public interface C2546a {
        /* renamed from: a */
        void m12740a(C2548a c2548a);
    }

    public C2547a(Context context) {
        super((C1372b) context.getApplicationContext());
        this.f12023b = context.getApplicationContext();
        this.f12022a = (C1453b) new C1772d(this.f12023b).m9399a(C1453b.class, C1768c.f8075a, C1768c.m9391a(this.f12023b));
    }

    /* renamed from: b */
    private C2548a m12742b() throws BusinessException {
        try {
            JSONObject a = this.f12022a.a();
            if (a == null || a.optInt("code") != 0) {
                return null;
            }
            JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
            if (optJSONObject != null) {
                return new C2548a(optJSONObject);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static int m12741a(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            return i;
        }
    }

    /* renamed from: a */
    public C2548a m12743a() {
        BusinessException e;
        C2548a b;
        try {
            b = m12742b();
            if (b == null) {
                return b;
            }
            try {
                if (b.m12746c() <= C2547a.m12741a(this.f12023b)) {
                    return null;
                }
                return b;
            } catch (BusinessException e2) {
                e = e2;
                e.printStackTrace();
                return b;
            }
        } catch (BusinessException e3) {
            BusinessException businessException = e3;
            b = null;
            e = businessException;
            e.printStackTrace();
            return b;
        }
    }
}
