package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ClubActRouteSelfActivity$1 extends AsyncTask<String, Void, List<RouteDTO>> {
    /* renamed from: a */
    final /* synthetic */ ClubActRouteSelfActivity f9429a;

    ClubActRouteSelfActivity$1(ClubActRouteSelfActivity clubActRouteSelfActivity) {
        this.f9429a = clubActRouteSelfActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10666a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10667a((List) obj);
    }

    protected void onPreExecute() {
        if (ClubActRouteSelfActivity.a(this.f9429a) != null) {
            ClubActRouteSelfActivity.a(this.f9429a).show();
        }
    }

    /* renamed from: a */
    protected List<RouteDTO> m10666a(String... strArr) {
        try {
            return ClubActRouteSelfActivity.b(this.f9429a).m11201b();
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10667a(List<RouteDTO> list) {
        if (ClubActRouteSelfActivity.a(this.f9429a) != null) {
            ClubActRouteSelfActivity.a(this.f9429a).dismiss();
        }
        if (list == null || list.isEmpty()) {
            ClubActRouteSelfActivity.c(this.f9429a).setVisibility(8);
            ClubActRouteSelfActivity.d(this.f9429a).setVisibility(0);
            return;
        }
        ClubActRouteSelfActivity.d(this.f9429a).setVisibility(8);
        ClubActRouteSelfActivity.c(this.f9429a).setVisibility(0);
        ClubActRouteSelfActivity.e(this.f9429a).clear();
        ClubActRouteSelfActivity.e(this.f9429a).addAll(list);
        if (ClubActRouteSelfActivity.f(this.f9429a).contains("use_route_id")) {
            String string = ClubActRouteSelfActivity.f(this.f9429a).getString("use_route_id", "");
            for (RouteDTO routeDTO : ClubActRouteSelfActivity.e(this.f9429a)) {
                if (string.equals(routeDTO.getId())) {
                    routeDTO.setUse(true);
                } else {
                    routeDTO.setUse(false);
                }
            }
        }
        ClubActRouteSelfActivity.g(this.f9429a).notifyDataSetChanged();
    }
}
