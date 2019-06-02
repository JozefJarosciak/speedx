package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import android.util.LongSparseArray;

public class BaseMapCallback {
    /* renamed from: a */
    private static LongSparseArray<C1248b> f3922a = new LongSparseArray();

    public static int ReqLayerData(Bundle bundle, long j, int i, Bundle bundle2) {
        int size = f3922a.size();
        for (int i2 = 0; i2 < size; i2++) {
            C1248b c1248b = (C1248b) f3922a.valueAt(i2);
            if (c1248b != null && c1248b.mo2676a(j)) {
                return c1248b.mo2675a(bundle, j, i, bundle2);
            }
        }
        return 0;
    }

    public static void addLayerDataInterface(long j, C1248b c1248b) {
        f3922a.put(j, c1248b);
    }

    public static void removeLayerDataInterface(long j) {
        f3922a.remove(j);
    }
}
