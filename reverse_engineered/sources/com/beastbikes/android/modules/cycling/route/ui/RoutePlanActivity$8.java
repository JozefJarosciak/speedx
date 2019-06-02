package com.beastbikes.android.modules.cycling.route.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.dto.RouteNodeDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class RoutePlanActivity$8 extends AsyncTask<String, Void, RouteDTO> {
    /* renamed from: a */
    final /* synthetic */ RoutePlanActivity f10323a;

    RoutePlanActivity$8(RoutePlanActivity routePlanActivity) {
        this.f10323a = routePlanActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11251a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11252a((RouteDTO) obj);
    }

    /* renamed from: a */
    protected RouteDTO m11251a(String... strArr) {
        try {
            return RoutePlanActivity.l(this.f10323a).m11204d(strArr[0]);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11252a(RouteDTO routeDTO) {
        if (routeDTO != null) {
            List<RouteNodeDTO> nodes = routeDTO.getNodes();
            if (nodes != null && !nodes.isEmpty()) {
                RoutePlanActivity.b(this.f10323a).clear();
                for (RouteNodeDTO routeNodeDTO : nodes) {
                    if (routeNodeDTO.getKeyNode() >= 0) {
                        RoutePlanActivity.b(this.f10323a).add(new PoiInfoDTO(routeNodeDTO));
                    }
                }
                RoutePlanActivity.m(this.f10323a).addAll(RoutePlanActivity.b(this.f10323a));
                RoutePlanActivity.a(this.f10323a, true);
                RoutePlanActivity.a(this.f10323a).notifyDataSetChanged();
            }
        }
    }
}
