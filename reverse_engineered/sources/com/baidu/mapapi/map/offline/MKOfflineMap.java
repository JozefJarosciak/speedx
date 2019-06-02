package com.baidu.mapapi.map.offline;

import com.baidu.platform.comapi.map.C1253i;
import com.baidu.platform.comapi.map.C1263t;
import com.baidu.platform.comapi.map.C1264u;
import com.baidu.platform.comapi.map.C1267x;
import java.util.ArrayList;
import java.util.Iterator;

public class MKOfflineMap {
    public static final int TYPE_DOWNLOAD_UPDATE = 0;
    public static final int TYPE_NETWORK_ERROR = 2;
    public static final int TYPE_NEW_OFFLINE = 6;
    public static final int TYPE_VER_UPDATE = 4;
    /* renamed from: a */
    private static final String f3266a = MKOfflineMap.class.getSimpleName();
    /* renamed from: b */
    private C1264u f3267b;
    /* renamed from: c */
    private MKOfflineMapListener f3268c;

    public void destroy() {
        this.f3267b.m4796d(0);
        this.f3267b.m4791b(null);
        this.f3267b.m4790b();
        C1253i.m4753b();
    }

    public ArrayList<MKOLUpdateElement> getAllUpdateInfo() {
        ArrayList e = this.f3267b.m4797e();
        if (e == null) {
            return null;
        }
        ArrayList<MKOLUpdateElement> arrayList = new ArrayList();
        Iterator it = e.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getUpdatElementFromLocalMapElement(((C1267x) it.next()).m4801a()));
        }
        return arrayList;
    }

    public ArrayList<MKOLSearchRecord> getHotCityList() {
        ArrayList c = this.f3267b.m4793c();
        if (c == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList();
        Iterator it = c.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo((C1263t) it.next()));
        }
        return arrayList;
    }

    public ArrayList<MKOLSearchRecord> getOfflineCityList() {
        ArrayList d = this.f3267b.m4795d();
        if (d == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList();
        Iterator it = d.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo((C1263t) it.next()));
        }
        return arrayList;
    }

    public MKOLUpdateElement getUpdateInfo(int i) {
        C1267x g = this.f3267b.m4800g(i);
        return g == null ? null : OfflineMapUtil.getUpdatElementFromLocalMapElement(g.m4801a());
    }

    @Deprecated
    public int importOfflineData() {
        return importOfflineData(false);
    }

    @Deprecated
    public int importOfflineData(boolean z) {
        int i;
        int i2 = 0;
        ArrayList e = this.f3267b.m4797e();
        if (e != null) {
            i2 = e.size();
            i = i2;
        } else {
            i = 0;
        }
        this.f3267b.m4789a(z, true);
        ArrayList e2 = this.f3267b.m4797e();
        if (e2 != null) {
            i2 = e2.size();
        }
        return i2 - i;
    }

    public boolean init(MKOfflineMapListener mKOfflineMapListener) {
        C1253i.m4750a();
        this.f3267b = C1264u.m4781a();
        if (this.f3267b == null) {
            return false;
        }
        this.f3267b.m4787a(new C1132a(this));
        this.f3268c = mKOfflineMapListener;
        return true;
    }

    public boolean pause(int i) {
        return this.f3267b.m4794c(i);
    }

    public boolean remove(int i) {
        return this.f3267b.m4798e(i);
    }

    public ArrayList<MKOLSearchRecord> searchCity(String str) {
        ArrayList a = this.f3267b.m4786a(str);
        if (a == null) {
            return null;
        }
        ArrayList<MKOLSearchRecord> arrayList = new ArrayList();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            arrayList.add(OfflineMapUtil.getSearchRecordFromLocalCityInfo((C1263t) it.next()));
        }
        return arrayList;
    }

    public boolean start(int i) {
        if (this.f3267b == null) {
            return false;
        }
        if (this.f3267b.m4797e() != null) {
            Iterator it = this.f3267b.m4797e().iterator();
            while (it.hasNext()) {
                C1267x c1267x = (C1267x) it.next();
                if (c1267x.f3841a.f3829a == i) {
                    return (c1267x.f3841a.f3838j || c1267x.f3841a.f3840l == 2 || c1267x.f3841a.f3840l == 3 || c1267x.f3841a.f3840l == 6) ? this.f3267b.m4792b(i) : false;
                }
            }
        }
        return this.f3267b.m4788a(i);
    }

    public boolean update(int i) {
        if (this.f3267b == null) {
            return false;
        }
        if (this.f3267b.m4797e() != null) {
            Iterator it = this.f3267b.m4797e().iterator();
            while (it.hasNext()) {
                C1267x c1267x = (C1267x) it.next();
                if (c1267x.f3841a.f3829a == i) {
                    return c1267x.f3841a.f3838j ? this.f3267b.m4799f(i) : false;
                }
            }
        }
        return false;
    }
}
