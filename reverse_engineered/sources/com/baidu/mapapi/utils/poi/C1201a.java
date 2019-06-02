package com.baidu.mapapi.utils.poi;

import android.content.Context;
import android.util.Log;
import com.baidu.mapapi.http.HttpClient.HttpStateError;
import com.baidu.mapapi.utils.poi.BaiduMapPoiSearch.C11991;
import com.baidu.platform.comapi.pano.C1269a.C1200a;
import com.baidu.platform.comapi.pano.C1271c;

/* renamed from: com.baidu.mapapi.utils.poi.a */
final class C1201a implements C1200a<C1271c> {
    /* renamed from: a */
    final /* synthetic */ Context f3545a;

    C1201a(Context context) {
        this.f3545a = context;
    }

    /* renamed from: a */
    public void mo2662a(HttpStateError httpStateError) {
        switch (C11991.f3539b[httpStateError.ordinal()]) {
            case 1:
                Log.d("baidumapsdk", "current network is not available");
                return;
            case 2:
                Log.d("baidumapsdk", "network inner error, please check network");
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m4516a(C1271c c1271c) {
        if (c1271c == null) {
            Log.d("baidumapsdk", "pano info is null");
            return;
        }
        switch (C11991.f3538a[c1271c.m4811a().ordinal()]) {
            case 1:
                Log.d("baidumapsdk", "pano uid is error, please check param poi uid");
                return;
            case 2:
                Log.d("baidumapsdk", "pano id not found for this poi point");
                return;
            case 3:
                Log.d("baidumapsdk", "please check ak for permission");
                return;
            case 4:
                if (c1271c.m4815c() == 1) {
                    try {
                        BaiduMapPoiSearch.m4512b(c1271c.m4814b(), this.f3545a);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                Log.d("baidumapsdk", "this point do not support for pano show");
                return;
            default:
                return;
        }
    }
}
