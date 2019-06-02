package com.beastbikes.android.widget.p168e;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

/* compiled from: FacebookUtil */
/* renamed from: com.beastbikes.android.widget.e.e */
public class C2656e {
    /* renamed from: a */
    public static Boolean m13193a(Context context) {
        boolean z = true;
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 1);
        } catch (NameNotFoundException e) {
            z = false;
        }
        return Boolean.valueOf(z);
    }
}
