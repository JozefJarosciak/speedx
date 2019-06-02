package com.beastbikes.android.modules.cycling.route.ui;

import android.os.AsyncTask;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class RouteActivity$5 extends AsyncTask<String, Void, List<String>> {
    /* renamed from: a */
    final /* synthetic */ RouteActivity f10287a;

    RouteActivity$5(RouteActivity routeActivity) {
        this.f10287a = routeActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11235a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11236a((List) obj);
    }

    /* renamed from: a */
    protected List<String> m11235a(String... strArr) {
        try {
            return RouteActivity.c(this.f10287a).m11203c(strArr[0]);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11236a(List<String> list) {
        if (list != null && !list.isEmpty()) {
            RouteActivity.r(this.f10287a).clear();
            RouteActivity.r(this.f10287a).addAll(list);
            RouteActivity.f5440a.trace("Route cover " + RouteActivity.r(this.f10287a).toString());
            RouteActivity.s(this.f10287a);
            RouteActivity.t(this.f10287a);
        }
    }
}
