package com.beastbikes.android.modules.cycling.route.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class RouteActivity$6 extends AsyncTask<String, Void, Integer> {
    /* renamed from: a */
    final /* synthetic */ RouteActivity f10288a;

    RouteActivity$6(RouteActivity routeActivity) {
        this.f10288a = routeActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11237a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11238a((Integer) obj);
    }

    /* renamed from: a */
    protected Integer m11237a(String... strArr) {
        try {
            return Integer.valueOf(RouteActivity.c(this.f10288a).m11194a(strArr[0], strArr[1], null));
        } catch (BusinessException e) {
            return Integer.valueOf(-1);
        }
    }

    /* renamed from: a */
    protected void m11238a(Integer num) {
        RouteActivity.u(this.f10288a).setClickable(true);
        if (num.intValue() == -1) {
            Toasts.show(this.f10288a.getApplicationContext(), (int) C1373R.string.route_activity_comment_commit_err);
            return;
        }
        RouteActivity.v(this.f10288a).setText("");
        Toasts.show(this.f10288a.getApplicationContext(), (int) C1373R.string.route_activity_comment_commit_sucess);
        RouteActivity.a(this.f10288a, RouteActivity.m(this.f10288a).getId());
    }
}
