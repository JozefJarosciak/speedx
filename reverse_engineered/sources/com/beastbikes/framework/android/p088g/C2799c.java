package com.beastbikes.framework.android.p088g;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* compiled from: ConnectivityUtils */
/* renamed from: com.beastbikes.framework.android.g.c */
public final class C2799c {
    /* renamed from: a */
    public static NetworkInfo m13753a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return null;
        }
        return connectivityManager.getActiveNetworkInfo();
    }

    /* renamed from: b */
    public static int m13754b(Context context) {
        NetworkInfo a = C2799c.m13753a(context);
        if (a != null) {
            return a.getType();
        }
        return -1;
    }
}
