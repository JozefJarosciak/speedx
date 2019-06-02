package com.baidu.location.p041a;

import android.os.HandlerThread;

/* renamed from: com.baidu.location.a.s */
public class C1068s {
    /* renamed from: a */
    private static HandlerThread f2523a = null;

    /* renamed from: a */
    public static synchronized HandlerThread m3829a() {
        HandlerThread handlerThread;
        synchronized (C1068s.class) {
            if (f2523a == null) {
                f2523a = new HandlerThread("ServiceStartArguments", 10);
                f2523a.start();
            }
            handlerThread = f2523a;
        }
        return handlerThread;
    }
}
