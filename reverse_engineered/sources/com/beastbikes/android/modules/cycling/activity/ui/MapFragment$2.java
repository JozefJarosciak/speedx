package com.beastbikes.android.modules.cycling.activity.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.dto.RouteNodeDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.ArrayList;
import java.util.List;

class MapFragment$2 extends AsyncTask<String, Void, RouteDTO> {
    /* renamed from: a */
    final /* synthetic */ MapFragment f8713a;

    MapFragment$2(MapFragment mapFragment) {
        this.f8713a = mapFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9969a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9970a((RouteDTO) obj);
    }

    /* renamed from: a */
    protected RouteDTO m9969a(String... strArr) {
        try {
            return MapFragment.f(this.f8713a).m11204d(strArr[0]);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m9970a(RouteDTO routeDTO) {
        if (routeDTO != null && this.f8713a.getActivity() != null) {
            List<RouteNodeDTO> nodes = routeDTO.getNodes();
            if (nodes != null && !nodes.isEmpty()) {
                MapFragment.a(this.f8713a, new ArrayList());
                for (RouteNodeDTO routeNodeDTO : nodes) {
                    if (routeNodeDTO.getKeyNode() >= 0) {
                        MapFragment.g(this.f8713a).add(new PoiInfoDTO(routeNodeDTO));
                    }
                }
                MapFragment.a(this.f8713a, routeDTO.getName());
                MapFragment.h(this.f8713a);
            }
        }
    }
}
