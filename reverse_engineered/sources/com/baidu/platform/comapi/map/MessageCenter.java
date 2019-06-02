package com.baidu.platform.comapi.map;

import android.os.Handler;
import com.baidu.platform.comjni.engine.C1282a;

public class MessageCenter {
    public static void registMessage(int i, Handler handler) {
        C1282a.m4870a(i, handler);
    }

    public static void unregistMessage(int i, Handler handler) {
        C1282a.m4871b(i, handler);
    }
}
