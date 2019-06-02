package org.xclcharts.common;

import android.os.Build.VERSION;

public class SysinfoHelper {
    private static SysinfoHelper instance = null;

    public static synchronized SysinfoHelper getInstance() {
        SysinfoHelper sysinfoHelper;
        synchronized (SysinfoHelper.class) {
            if (instance == null) {
                instance = new SysinfoHelper();
            }
            sysinfoHelper = instance;
        }
        return sysinfoHelper;
    }

    public boolean supportHardwareAccelerated() {
        if (VERSION.SDK_INT < 11) {
            return false;
        }
        return true;
    }
}
