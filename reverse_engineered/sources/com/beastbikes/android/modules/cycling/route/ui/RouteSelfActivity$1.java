package com.beastbikes.android.modules.cycling.route.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class RouteSelfActivity$1 extends AsyncTask<String, Void, List<RouteDTO>> {
    /* renamed from: a */
    final /* synthetic */ RouteSelfActivity f10334a;

    RouteSelfActivity$1(RouteSelfActivity routeSelfActivity) {
        this.f10334a = routeSelfActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11261a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11262a((List) obj);
    }

    protected void onPreExecute() {
        if (RouteSelfActivity.a(this.f10334a) != null && !RouteSelfActivity.a(this.f10334a).isShowing()) {
            RouteSelfActivity.a(this.f10334a).show();
        }
    }

    /* renamed from: a */
    protected List<RouteDTO> m11261a(String... strArr) {
        try {
            return RouteSelfActivity.b(this.f10334a).m11201b();
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11262a(List<RouteDTO> list) {
        if (!this.f10334a.isFinishing()) {
            if (RouteSelfActivity.a(this.f10334a) != null && RouteSelfActivity.a(this.f10334a).isShowing()) {
                RouteSelfActivity.a(this.f10334a).dismiss();
            }
            if (list == null || list.isEmpty()) {
                RouteSelfActivity.c(this.f10334a).setVisibility(8);
                RouteSelfActivity.d(this.f10334a).setVisibility(0);
                return;
            }
            RouteSelfActivity.d(this.f10334a).setVisibility(8);
            RouteSelfActivity.c(this.f10334a).setVisibility(0);
            RouteSelfActivity.e(this.f10334a).clear();
            RouteSelfActivity.e(this.f10334a).addAll(list);
            if (RouteSelfActivity.f(this.f10334a).contains("use_route_id")) {
                String string = RouteSelfActivity.f(this.f10334a).getString("use_route_id", "");
                for (RouteDTO routeDTO : RouteSelfActivity.e(this.f10334a)) {
                    if (string.equals(routeDTO.getId())) {
                        routeDTO.setUse(true);
                    } else {
                        routeDTO.setUse(false);
                    }
                }
            }
            RouteSelfActivity.g(this.f10334a).notifyDataSetChanged();
        }
    }
}
