package com.baidu.mapapi.map.offline;

import com.baidu.platform.comapi.map.C1131y;
import java.util.List;

/* renamed from: com.baidu.mapapi.map.offline.a */
class C1132a implements C1131y {
    /* renamed from: a */
    final /* synthetic */ MKOfflineMap f3269a;

    C1132a(MKOfflineMap mKOfflineMap) {
        this.f3269a = mKOfflineMap;
    }

    /* renamed from: a */
    public void mo2647a(int i, int i2) {
        switch (i) {
            case 4:
                List<MKOLUpdateElement> allUpdateInfo = this.f3269a.getAllUpdateInfo();
                if (allUpdateInfo != null) {
                    for (MKOLUpdateElement mKOLUpdateElement : allUpdateInfo) {
                        if (mKOLUpdateElement.update) {
                            this.f3269a.f3268c.onGetOfflineMapState(4, mKOLUpdateElement.cityID);
                        }
                    }
                    return;
                }
                return;
            case 6:
                this.f3269a.f3268c.onGetOfflineMapState(6, i2);
                return;
            case 8:
                this.f3269a.f3268c.onGetOfflineMapState(0, 65535 & (i2 >> 8));
                return;
            case 10:
                this.f3269a.f3268c.onGetOfflineMapState(2, i2);
                return;
            case 12:
                this.f3269a.f3267b.m4789a(true, false);
                return;
            default:
                return;
        }
    }
}
