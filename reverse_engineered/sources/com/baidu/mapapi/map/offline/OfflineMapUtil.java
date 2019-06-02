package com.baidu.mapapi.map.offline;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.platform.comapi.map.C1263t;
import com.baidu.platform.comapi.map.C1266w;
import java.util.ArrayList;
import java.util.Iterator;

public class OfflineMapUtil {
    public static MKOLSearchRecord getSearchRecordFromLocalCityInfo(C1263t c1263t) {
        if (c1263t == null) {
            return null;
        }
        int i;
        MKOLSearchRecord mKOLSearchRecord = new MKOLSearchRecord();
        mKOLSearchRecord.cityID = c1263t.f3818a;
        mKOLSearchRecord.cityName = c1263t.f3819b;
        mKOLSearchRecord.cityType = c1263t.f3821d;
        if (c1263t.m4779a() != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = c1263t.m4779a().iterator();
            i = 0;
            while (it.hasNext()) {
                C1263t c1263t2 = (C1263t) it.next();
                arrayList.add(getSearchRecordFromLocalCityInfo(c1263t2));
                int i2 = c1263t2.f3820c + i;
                mKOLSearchRecord.childCities = arrayList;
                i = i2;
            }
        } else {
            i = 0;
        }
        if (mKOLSearchRecord.cityType == 1) {
            mKOLSearchRecord.size = i;
        } else {
            mKOLSearchRecord.size = c1263t.f3820c;
        }
        return mKOLSearchRecord;
    }

    public static MKOLUpdateElement getUpdatElementFromLocalMapElement(C1266w c1266w) {
        if (c1266w == null) {
            return null;
        }
        MKOLUpdateElement mKOLUpdateElement = new MKOLUpdateElement();
        mKOLUpdateElement.cityID = c1266w.f3829a;
        mKOLUpdateElement.cityName = c1266w.f3830b;
        if (c1266w.f3835g != null) {
            mKOLUpdateElement.geoPt = CoordUtil.mc2ll(c1266w.f3835g);
        }
        mKOLUpdateElement.level = c1266w.f3833e;
        mKOLUpdateElement.ratio = c1266w.f3837i;
        mKOLUpdateElement.serversize = c1266w.f3836h;
        if (c1266w.f3837i == 100) {
            mKOLUpdateElement.size = c1266w.f3836h;
        } else {
            mKOLUpdateElement.size = (c1266w.f3836h / 100) * c1266w.f3837i;
        }
        mKOLUpdateElement.status = c1266w.f3840l;
        mKOLUpdateElement.update = c1266w.f3838j;
        return mKOLUpdateElement;
    }
}
