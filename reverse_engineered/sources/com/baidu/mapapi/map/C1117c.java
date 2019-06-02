package com.baidu.mapapi.map;

import android.os.Bundle;
import com.baidu.platform.comapi.map.C1116q;

/* renamed from: com.baidu.mapapi.map.c */
class C1117c implements C1116q {
    /* renamed from: a */
    final /* synthetic */ BaiduMap f3241a;

    C1117c(BaiduMap baiduMap) {
        this.f3241a = baiduMap;
    }

    /* renamed from: a */
    public Bundle mo2645a(int i, int i2, int i3) {
        this.f3241a.f2830E.lock();
        try {
            if (this.f3241a.f2829D != null) {
                Tile a = this.f3241a.f2829D.m4128a(i, i2, i3);
                if (a != null) {
                    Bundle toBundle = a.toBundle();
                    return toBundle;
                }
            }
            this.f3241a.f2830E.unlock();
            return null;
        } finally {
            this.f3241a.f2830E.unlock();
        }
    }
}
