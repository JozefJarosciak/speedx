package com.beastbikes.android.modules.cycling.route.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class RouteActivity$3 extends AsyncTask<String, Void, Integer> {
    /* renamed from: a */
    final /* synthetic */ RouteActivity f10285a;

    RouteActivity$3(RouteActivity routeActivity) {
        this.f10285a = routeActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11231a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11232a((Integer) obj);
    }

    /* renamed from: a */
    protected Integer m11231a(String... strArr) {
        try {
            return Integer.valueOf(RouteActivity.c(this.f10285a).m11200b(strArr[0]));
        } catch (BusinessException e) {
            return Integer.valueOf(-1);
        }
    }

    /* renamed from: a */
    protected void m11232a(Integer num) {
        RouteActivity.k(this.f10285a).setClickable(true);
        if (num.intValue() == -1) {
            RouteActivity.l(this.f10285a).setBackgroundResource(C1373R.drawable.route_wanted_bg);
            RouteActivity.k(this.f10285a).setText(C1373R.string.routes_activity_wanted_go);
            RouteActivity.k(this.f10285a).setTextColor(this.f10285a.getResources().getColor(C1373R.color.route_activity_wanted_go));
            RouteActivity.k(this.f10285a).setCompoundDrawablesWithIntrinsicBounds(this.f10285a.getResources().getDrawable(C1373R.drawable.ic_route_activity_wanted), null, null, null);
            RouteActivity.i(this.f10285a).setTextColor(this.f10285a.getResources().getColor(C1373R.color.route_activity_wanted_go));
            RouteActivity.i(this.f10285a).setText("(" + RouteActivity.m(this.f10285a).getNumberOfFollowers() + ")");
            Toasts.show(this.f10285a, (int) C1373R.string.route_activity_comment_followed_err);
            return;
        }
        RouteActivity.l(this.f10285a).setBackgroundResource(C1373R.drawable.route_want_bg);
        RouteActivity.k(this.f10285a).setText(C1373R.string.routes_activity_want_go);
        RouteActivity.k(this.f10285a).setTextColor(this.f10285a.getResources().getColor(C1373R.color.route_activity_want_go));
        RouteActivity.k(this.f10285a).setCompoundDrawablesWithIntrinsicBounds(this.f10285a.getResources().getDrawable(C1373R.drawable.ic_route_activity_want), null, null, null);
        RouteActivity.i(this.f10285a).setTextColor(this.f10285a.getResources().getColor(C1373R.color.route_activity_want_go));
        RouteActivity.m(this.f10285a).setFollowed(true);
        if (num.intValue() > 0) {
            RouteActivity.i(this.f10285a).setText("(" + num + ")");
        }
    }
}
