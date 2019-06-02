package com.beastbikes.android.modules.cycling.route.ui;

import android.os.AsyncTask;
import com.baidu.mapapi.model.LatLng;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.dto.RouteNodeDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.Collections;
import java.util.List;

class RouteMapActivity$2 extends AsyncTask<String, Void, RouteDTO> {
    /* renamed from: a */
    final /* synthetic */ RouteMapActivity f10301a;

    RouteMapActivity$2(RouteMapActivity routeMapActivity) {
        this.f10301a = routeMapActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11246a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11247a((RouteDTO) obj);
    }

    /* renamed from: a */
    protected RouteDTO m11246a(String... strArr) {
        try {
            return RouteMapActivity.e(this.f10301a).m11204d(strArr[0]);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11247a(RouteDTO routeDTO) {
        if (routeDTO != null) {
            List<RouteNodeDTO> nodes = routeDTO.getNodes();
            if (nodes != null && nodes.size() >= 2) {
                Collections.sort(nodes, RouteMapActivity.f(this.f10301a));
                for (RouteNodeDTO routeNodeDTO : nodes) {
                    RouteMapActivity.g(this.f10301a).add(new LatLng(routeNodeDTO.getLatitude(), routeNodeDTO.getLongitude()));
                }
                RouteMapActivity.a(this.f10301a, RouteMapActivity.g(this.f10301a));
                RouteMapActivity.h(this.f10301a).setVisibility(0);
            }
        }
    }
}
