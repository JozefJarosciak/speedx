package com.digits.sdk.android;

import android.os.Bundle;

/* compiled from: BundleManager */
/* renamed from: com.digits.sdk.android.i */
class C2917i {
    /* renamed from: a */
    static boolean m14203a(Bundle bundle, String... strArr) {
        if (bundle == null || strArr == null) {
            return false;
        }
        for (String containsKey : strArr) {
            if (!bundle.containsKey(containsKey)) {
                return false;
            }
        }
        return true;
    }
}
