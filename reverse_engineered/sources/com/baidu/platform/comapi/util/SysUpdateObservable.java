package com.baidu.platform.comapi.util;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class SysUpdateObservable {
    /* renamed from: a */
    private static volatile SysUpdateObservable f3867a;
    /* renamed from: b */
    private List<SysUpdateObserver> f3868b;

    private SysUpdateObservable() {
        this.f3868b = null;
        this.f3868b = new ArrayList();
    }

    public static SysUpdateObservable getInstance() {
        if (f3867a == null) {
            synchronized (SysUpdateObservable.class) {
                if (f3867a == null) {
                    f3867a = new SysUpdateObservable();
                }
            }
        }
        return f3867a;
    }

    public void addObserver(SysUpdateObserver sysUpdateObserver) {
        this.f3868b.add(sysUpdateObserver);
    }

    public void init() {
        for (SysUpdateObserver sysUpdateObserver : this.f3868b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.init();
            }
        }
    }

    public void updateNetworkInfo(Context context) {
        for (SysUpdateObserver sysUpdateObserver : this.f3868b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.updateNetworkInfo(context);
            }
        }
    }

    public void updateNetworkProxy(Context context) {
        for (SysUpdateObserver sysUpdateObserver : this.f3868b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.updateNetworkProxy(context);
            }
        }
    }

    public void updatePhoneInfo() {
        for (SysUpdateObserver sysUpdateObserver : this.f3868b) {
            if (sysUpdateObserver != null) {
                sysUpdateObserver.updatePhoneInfo();
            }
        }
    }
}
