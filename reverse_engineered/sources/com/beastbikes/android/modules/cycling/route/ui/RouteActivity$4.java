package com.beastbikes.android.modules.cycling.route.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.route.dto.C2190d;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class RouteActivity$4 extends AsyncTask<String, Void, List<C2190d>> {
    /* renamed from: a */
    final /* synthetic */ RouteActivity f10286a;

    RouteActivity$4(RouteActivity routeActivity) {
        this.f10286a = routeActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11233a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11234a((List) obj);
    }

    /* renamed from: a */
    protected List<C2190d> m11233a(String... strArr) {
        try {
            return RouteActivity.c(this.f10286a).m11197a(strArr[0], 3, 1);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11234a(List<C2190d> list) {
        if (list != null && !list.isEmpty()) {
            RouteActivity.n(this.f10286a).clear();
            RouteActivity.n(this.f10286a).addAll(list);
            RouteActivity.o(this.f10286a).notifyDataSetChanged();
            RouteActivity.a(this.f10286a, ((C2190d) list.get(0)).m11226e());
            RouteActivity.q(this.f10286a).setText(String.format(this.f10286a.getString(C1373R.string.route_activity_comment_title), new Object[]{Integer.valueOf(RouteActivity.p(this.f10286a))}));
        }
    }
}
