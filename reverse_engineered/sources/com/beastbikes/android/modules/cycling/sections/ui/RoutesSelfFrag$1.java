package com.beastbikes.android.modules.cycling.sections.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class RoutesSelfFrag$1 extends AsyncTask<String, Void, List<RouteDTO>> {
    /* renamed from: a */
    final /* synthetic */ RoutesSelfFrag f10573a;

    RoutesSelfFrag$1(RoutesSelfFrag routesSelfFrag) {
        this.f10573a = routesSelfFrag;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11445a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11446a((List) obj);
    }

    /* renamed from: a */
    protected List<RouteDTO> m11445a(String... strArr) {
        try {
            return RoutesSelfFrag.a(this.f10573a).m11201b();
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11446a(List<RouteDTO> list) {
        if (list == null || list.isEmpty()) {
            RoutesSelfFrag.b(this.f10573a).setVisibility(8);
            RoutesSelfFrag.c(this.f10573a).setVisibility(0);
            return;
        }
        RoutesSelfFrag.c(this.f10573a).setVisibility(8);
        RoutesSelfFrag.b(this.f10573a).setVisibility(0);
        RoutesSelfFrag.d(this.f10573a).clear();
        RoutesSelfFrag.d(this.f10573a).addAll(list);
        if (RoutesSelfFrag.e(this.f10573a).contains("use_route_id")) {
            String string = RoutesSelfFrag.e(this.f10573a).getString("use_route_id", "");
            for (RouteDTO routeDTO : RoutesSelfFrag.d(this.f10573a)) {
                if (string.equals(routeDTO.getId())) {
                    routeDTO.setUse(true);
                } else {
                    routeDTO.setUse(false);
                }
            }
        }
        RoutesSelfFrag.f(this.f10573a).notifyDataSetChanged();
    }
}
