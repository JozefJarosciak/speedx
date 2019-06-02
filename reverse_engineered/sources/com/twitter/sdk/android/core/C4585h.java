package com.twitter.sdk.android.core;

import android.content.Context;
import android.content.Intent;

/* compiled from: IntentUtils */
/* renamed from: com.twitter.sdk.android.core.h */
public class C4585h {
    /* renamed from: a */
    public static boolean m18156a(Context context, Intent intent) {
        if (context.getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    public static boolean m18157b(Context context, Intent intent) {
        if (!C4585h.m18156a(context, intent)) {
            return false;
        }
        context.startActivity(intent);
        return true;
    }
}
