package com.baidu.mapapi;

import android.app.Application;
import android.content.Context;

public class JNIInitializer {
    /* renamed from: a */
    private static Context f2756a;

    public static Context getCachedContext() {
        return f2756a;
    }

    public static void setContext(Application application) {
        if (application == null) {
            throw new RuntimeException();
        } else if (f2756a == null) {
            f2756a = application;
        }
    }
}
