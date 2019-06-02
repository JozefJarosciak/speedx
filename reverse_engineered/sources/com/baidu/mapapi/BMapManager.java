package com.baidu.mapapi;

import android.content.Context;
import com.baidu.platform.comapi.C1218a;

public class BMapManager {
    public static void destroy() {
        C1218a.m4559a().m4567d();
    }

    public static Context getContext() {
        return C1218a.m4559a().m4568e();
    }

    public static void init() {
        C1218a.m4559a().m4565b();
    }
}
