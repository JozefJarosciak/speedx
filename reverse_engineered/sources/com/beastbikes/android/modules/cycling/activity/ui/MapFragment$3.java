package com.beastbikes.android.modules.cycling.activity.ui;

import android.os.AsyncTask;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.model.LatLng;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample;
import com.beastbikes.android.utils.C2558g;
import com.beastbikes.framework.business.BusinessException;
import java.util.ArrayList;
import java.util.List;

class MapFragment$3 extends AsyncTask<String, Void, List<LatLng>> {
    /* renamed from: a */
    final /* synthetic */ MapFragment f8714a;

    MapFragment$3(MapFragment mapFragment) {
        this.f8714a = mapFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9971a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9972a((List) obj);
    }

    /* renamed from: a */
    protected List<LatLng> m9971a(String... strArr) {
        try {
            List<LocalActivitySample> d = MapFragment.i(this.f8714a).d(strArr[0]);
            if (d == null || d.isEmpty()) {
                return null;
            }
            List<LatLng> arrayList = new ArrayList();
            for (LocalActivitySample localActivitySample : d) {
                double parseDouble = Double.parseDouble(localActivitySample.getLatitude1());
                double parseDouble2 = Double.parseDouble(localActivitySample.getLongitude1());
                if (!(parseDouble == 0.0d || parseDouble2 == 0.0d || parseDouble == Double.MIN_VALUE || parseDouble2 == Double.MIN_VALUE)) {
                    arrayList.add(C2558g.m12851g(parseDouble, parseDouble2));
                }
            }
            LatLng latLng = (LatLng) arrayList.get(arrayList.size() - 1);
            if (MapFragment.c(this.f8714a)) {
                if (MapFragment.b(this.f8714a) == null) {
                    MapFragment.j(this.f8714a);
                    if (MapFragment.b(this.f8714a) == null) {
                        return null;
                    }
                }
                MapFragment.b(this.f8714a).animateMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
            }
            MapFragment.a(this.f8714a, false);
            return arrayList;
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m9972a(List<LatLng> list) {
        System.gc();
        if (this.f8714a.getActivity() != null && list != null) {
            try {
                if (!list.isEmpty() && MapFragment.b(this.f8714a) != null) {
                    LatLng latLng = (LatLng) list.get(0);
                    if (latLng != null) {
                        if (!MapFragment.k(this.f8714a)) {
                            MapFragment.a(this.f8714a, latLng, null);
                        }
                        MapFragment.b(this.f8714a, true);
                        MapFragment.e().info("Activity source sample size = " + list.size());
                        MapFragment.a(this.f8714a, list, -1426128896);
                    }
                }
            } catch (Throwable e) {
                MapFragment.e().error("Activity draw line ", e);
            }
        }
    }
}
