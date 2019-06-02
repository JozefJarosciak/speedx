package com.beastbikes.android.modules.preferences.ui;

import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import java.util.ArrayList;
import java.util.Iterator;

class OfflineMapSettingActivity$e {
    /* renamed from: a */
    private final String f10943a;
    /* renamed from: b */
    private final ArrayList<OfflineMapSettingActivity$f> f10944b = new ArrayList();

    public OfflineMapSettingActivity$e(OfflineMapSettingActivity offlineMapSettingActivity, int i, ArrayList<MKOLSearchRecord> arrayList) {
        this.f10943a = offlineMapSettingActivity.getString(i);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            this.f10944b.add(new OfflineMapSettingActivity$f(offlineMapSettingActivity, (MKOLSearchRecord) it.next()));
        }
    }
}
