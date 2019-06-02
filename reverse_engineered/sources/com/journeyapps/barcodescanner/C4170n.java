package com.journeyapps.barcodescanner;

import android.os.Looper;

/* compiled from: Util */
/* renamed from: com.journeyapps.barcodescanner.n */
public class C4170n {
    /* renamed from: a */
    public static void m16706a() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("Must be called from the main thread.");
        }
    }
}
