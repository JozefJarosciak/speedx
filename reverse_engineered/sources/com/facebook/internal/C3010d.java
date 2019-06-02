package com.facebook.internal;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;

/* compiled from: CustomTab */
/* renamed from: com.facebook.internal.d */
public class C3010d {
    /* renamed from: a */
    private Uri f13544a;

    public C3010d(String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.f13544a = C3048s.m14724a(C3040r.m14704a(), C3040r.m14707d() + "/" + "dialog/" + str, bundle);
    }

    /* renamed from: a */
    public void m14560a(Activity activity, String str) {
        CustomTabsIntent build = new Builder().build();
        build.intent.setPackage(str);
        build.launchUrl(activity, this.f13544a);
    }
}
