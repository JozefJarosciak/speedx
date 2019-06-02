package com.beastbikes.android.modules.preferences.ui.offlineMap;

import android.os.AsyncTask;
import com.baidu.mapapi.map.offline.MKOLSearchRecord;
import com.baidu.mapapi.map.offline.MKOLUpdateElement;
import com.beastbikes.android.modules.preferences.ui.offlineMap.p142c.C2322a;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

class OfflineMapActivity$1 extends AsyncTask<Void, Void, Void> {
    /* renamed from: a */
    final /* synthetic */ OfflineMapActivity f11015a;

    OfflineMapActivity$1(OfflineMapActivity offlineMapActivity) {
        this.f11015a = offlineMapActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11849a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11850a((Void) obj);
    }

    /* renamed from: a */
    protected Void m11849a(Void... voidArr) {
        List offlineCityList;
        int importOfflineData = OfflineMapActivity.a(this.f11015a).importOfflineData();
        if (importOfflineData > 0) {
            Toasts.show(this.f11015a, "成功导入" + importOfflineData + "个离线包");
        }
        try {
            offlineCityList = OfflineMapActivity.a(this.f11015a).getOfflineCityList();
        } catch (Throwable e) {
            BusinessException businessException = new BusinessException(e);
            offlineCityList = null;
        }
        if (r1 == null || r1.isEmpty()) {
            Toasts.show(this.f11015a, (CharSequence) "未取到离线地图城市数据！");
        } else {
            Iterator it;
            MKOLSearchRecord mKOLSearchRecord;
            List<MKOLSearchRecord> hotCityList = OfflineMapActivity.a(this.f11015a).getHotCityList();
            HashSet hashSet = new HashSet();
            if (!hotCityList.isEmpty()) {
                for (MKOLSearchRecord mKOLSearchRecord2 : hotCityList) {
                    hashSet.add(Integer.valueOf(mKOLSearchRecord2.cityID));
                }
            }
            ArrayList arrayList = new ArrayList();
            for (MKOLSearchRecord mKOLSearchRecord22 : r1) {
                C2322a c2322a = new C2322a();
                c2322a.m11869a(mKOLSearchRecord22);
                List<MKOLSearchRecord> list = mKOLSearchRecord22.childCities;
                if (list == null || list.isEmpty()) {
                    arrayList.add(mKOLSearchRecord22);
                } else {
                    List arrayList2 = new ArrayList();
                    for (MKOLSearchRecord mKOLSearchRecord222 : list) {
                        C2322a c2322a2 = new C2322a();
                        c2322a2.m11869a(mKOLSearchRecord222);
                        arrayList2.add(c2322a2);
                        OfflineMapActivity.b(this.f11015a).add(c2322a2);
                        if (hashSet.contains(Integer.valueOf(mKOLSearchRecord222.cityID))) {
                            OfflineMapActivity.c(this.f11015a).add(c2322a2);
                        }
                    }
                    OfflineMapActivity.d(this.f11015a).add(arrayList2);
                    OfflineMapActivity.e(this.f11015a).add(c2322a);
                }
            }
            if (!arrayList.isEmpty()) {
                MKOLSearchRecord mKOLSearchRecord3 = new MKOLSearchRecord();
                mKOLSearchRecord3.cityName = "全国概略图、直辖市、港澳";
                mKOLSearchRecord3.childCities = arrayList;
                mKOLSearchRecord3.cityType = 1;
                List arrayList3 = new ArrayList();
                it = arrayList.iterator();
                while (it.hasNext()) {
                    mKOLSearchRecord222 = (MKOLSearchRecord) it.next();
                    C2322a c2322a3 = new C2322a();
                    c2322a3.m11869a(mKOLSearchRecord222);
                    arrayList3.add(c2322a3);
                    mKOLSearchRecord3.size += mKOLSearchRecord222.size;
                    OfflineMapActivity.b(this.f11015a).add(c2322a3);
                    if (hashSet.contains(Integer.valueOf(mKOLSearchRecord222.cityID))) {
                        OfflineMapActivity.c(this.f11015a).add(c2322a3);
                    }
                }
                C2322a c2322a4 = new C2322a();
                c2322a4.m11869a(mKOLSearchRecord3);
                OfflineMapActivity.d(this.f11015a).add(0, arrayList3);
                OfflineMapActivity.e(this.f11015a).add(0, c2322a4);
            }
            List<MKOLUpdateElement> allUpdateInfo = OfflineMapActivity.a(this.f11015a).getAllUpdateInfo();
            if (allUpdateInfo != null && allUpdateInfo.size() > 0) {
                for (MKOLUpdateElement a : allUpdateInfo) {
                    OfflineMapActivity.a(this.f11015a, a, false);
                }
                if (OfflineMapActivity.f(this.f11015a) != null && OfflineMapActivity.f(this.f11015a).size() > 1) {
                    Collections.sort(OfflineMapActivity.f(this.f11015a), C2322a.f11044b);
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    protected void m11850a(Void voidR) {
        super.onPostExecute(voidR);
        OfflineMapActivity.g(this.f11015a);
        OfflineMapActivity.h(this.f11015a).mo3478a(OfflineMapActivity.c(this.f11015a));
        OfflineMapActivity.i(this.f11015a).m11855a(OfflineMapActivity.e(this.f11015a), OfflineMapActivity.d(this.f11015a));
    }
}
