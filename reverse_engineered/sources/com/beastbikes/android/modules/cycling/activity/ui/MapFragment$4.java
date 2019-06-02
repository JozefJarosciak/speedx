package com.beastbikes.android.modules.cycling.activity.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample;
import com.beastbikes.framework.business.BusinessException;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.List;

class MapFragment$4 extends AsyncTask<String, Void, List<LatLng>> {
    /* renamed from: a */
    final /* synthetic */ MapFragment f8715a;

    MapFragment$4(MapFragment mapFragment) {
        this.f8715a = mapFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9973a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9974a((List) obj);
    }

    /* renamed from: a */
    protected List<LatLng> m9973a(String... strArr) {
        try {
            List<LocalActivitySample> d = MapFragment.i(this.f8715a).d(strArr[0]);
            if (d == null || d.isEmpty()) {
                return null;
            }
            List<LatLng> arrayList = new ArrayList();
            for (LocalActivitySample localActivitySample : d) {
                double parseDouble = Double.parseDouble(localActivitySample.getLatitude1());
                double parseDouble2 = Double.parseDouble(localActivitySample.getLongitude1());
                if (!(parseDouble == 0.0d || parseDouble2 == 0.0d || parseDouble == Double.MIN_VALUE || parseDouble2 == Double.MIN_VALUE)) {
                    arrayList.add(new LatLng(parseDouble, parseDouble2));
                }
            }
            return arrayList;
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m9974a(List<LatLng> list) {
        if (this.f8715a.getActivity() == null) {
            MapFragment.e().error("getActivity is null");
        } else if (list != null) {
            try {
                if (!list.isEmpty() && MapFragment.l(this.f8715a) != null) {
                    LatLng latLng = (LatLng) list.get(0);
                    if (latLng != null) {
                        if (!MapFragment.k(this.f8715a)) {
                            MapFragment.a(this.f8715a, latLng, null);
                        }
                        MapFragment.b(this.f8715a, true);
                        MapFragment.b(this.f8715a, list);
                    }
                }
            } catch (Throwable e) {
                MapFragment.e().error("Google Activity draw line ", e);
            }
        }
    }
}
