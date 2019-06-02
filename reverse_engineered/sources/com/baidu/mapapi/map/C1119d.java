package com.baidu.mapapi.map;

import android.content.Context;
import android.os.Bundle;
import com.baidu.platform.comapi.map.C1118L;

/* renamed from: com.baidu.mapapi.map.d */
class C1119d implements C1118L {
    /* renamed from: a */
    final /* synthetic */ BaiduMap f3242a;

    C1119d(BaiduMap baiduMap) {
        this.f3242a = baiduMap;
    }

    /* renamed from: a */
    public Bundle mo2646a(int i, int i2, int i3, Context context) {
        this.f3242a.f2831F.lock();
        try {
            if (this.f3242a.f2828C != null) {
                Tile a = this.f3242a.f2828C.m4202a(i, i2, i3);
                if (a != null) {
                    Bundle toBundle = a.toBundle();
                    return toBundle;
                }
            }
            this.f3242a.f2831F.unlock();
            return null;
        } finally {
            this.f3242a.f2831F.unlock();
        }
    }
}
