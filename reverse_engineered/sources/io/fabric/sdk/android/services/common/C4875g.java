package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import io.fabric.sdk.android.C1520c;

/* compiled from: ApiKey */
/* renamed from: io.fabric.sdk.android.services.common.g */
public class C4875g {
    /* renamed from: a */
    public String m19139a(Context context) {
        Object b = m19140b(context);
        if (TextUtils.isEmpty(b)) {
            b = m19141c(context);
        }
        if (TextUtils.isEmpty(b)) {
            m19142d(context);
        }
        return b;
    }

    /* renamed from: b */
    protected String m19140b(Context context) {
        String str = null;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle != null) {
                str = bundle.getString("io.fabric.ApiKey");
                if (str == null) {
                    C1520c.h().mo6215a("Fabric", "Falling back to Crashlytics key lookup from Manifest");
                    str = bundle.getString("com.crashlytics.ApiKey");
                }
            }
        } catch (Exception e) {
            C1520c.h().mo6215a("Fabric", "Caught non-fatal exception while retrieving apiKey: " + e);
        }
        return str;
    }

    /* renamed from: c */
    protected String m19141c(Context context) {
        int a = C4877i.m19144a(context, "io.fabric.ApiKey", "string");
        if (a == 0) {
            C1520c.h().mo6215a("Fabric", "Falling back to Crashlytics key lookup from Strings");
            a = C4877i.m19144a(context, "com.crashlytics.ApiKey", "string");
        }
        if (a != 0) {
            return context.getResources().getString(a);
        }
        return null;
    }

    /* renamed from: d */
    protected void m19142d(Context context) {
        if (C1520c.i() || C4877i.m19168c(context)) {
            throw new IllegalArgumentException(m19138a());
        }
        C1520c.h().mo6221d("Fabric", m19138a());
    }

    /* renamed from: a */
    protected String m19138a() {
        return "Fabric could not be initialized, API key missing from AndroidManifest.xml. Add the following tag to your Application element \n\t<meta-data android:name=\"io.fabric.ApiKey\" android:value=\"YOUR_API_KEY\"/>";
    }
}
