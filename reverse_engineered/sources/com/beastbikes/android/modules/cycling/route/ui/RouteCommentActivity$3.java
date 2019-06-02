package com.beastbikes.android.modules.cycling.route.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class RouteCommentActivity$3 extends AsyncTask<String, Void, Integer> {
    /* renamed from: a */
    final /* synthetic */ RouteCommentActivity f10294a;

    RouteCommentActivity$3(RouteCommentActivity routeCommentActivity) {
        this.f10294a = routeCommentActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11243a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11244a((Integer) obj);
    }

    /* renamed from: a */
    protected Integer m11243a(String... strArr) {
        try {
            return Integer.valueOf(RouteCommentActivity.a(this.f10294a).m11194a(strArr[0], strArr[1], null));
        } catch (BusinessException e) {
            return Integer.valueOf(-1);
        }
    }

    /* renamed from: a */
    protected void m11244a(Integer num) {
        if (num.intValue() == -1) {
            Toasts.show(this.f10294a.getApplicationContext(), (int) C1373R.string.route_activity_comment_commit_err);
            return;
        }
        RouteCommentActivity.f(this.f10294a).setText("");
        Toasts.show(this.f10294a.getApplicationContext(), (int) C1373R.string.route_activity_comment_commit_sucess);
        RouteCommentActivity.a(this.f10294a, 2);
        RouteCommentActivity.g(this.f10294a);
    }
}
