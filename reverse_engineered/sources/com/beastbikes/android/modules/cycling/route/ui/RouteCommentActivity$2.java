package com.beastbikes.android.modules.cycling.route.ui;

import android.net.NetworkInfo;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.route.dto.C2190d;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.List;

class RouteCommentActivity$2 extends AsyncTask<String, Void, List<C2190d>> {
    /* renamed from: a */
    final /* synthetic */ RouteCommentActivity f10293a;

    RouteCommentActivity$2(RouteCommentActivity routeCommentActivity) {
        this.f10293a = routeCommentActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11241a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11242a((List) obj);
    }

    /* renamed from: a */
    protected List<C2190d> m11241a(String... strArr) {
        try {
            return RouteCommentActivity.a(this.f10293a).m11197a(strArr[0], 10, RouteCommentActivity.e(this.f10293a));
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11242a(List<C2190d> list) {
        if (list == null || list.isEmpty()) {
            RouteCommentActivity.b(this.f10293a).m12977a(3);
            NetworkInfo a = C2799c.m13753a(this.f10293a);
            if (a == null || !a.isConnected()) {
                Toasts.show(this.f10293a, (int) C1373R.string.activity_unnetwork_err);
                return;
            } else {
                Toasts.show(this.f10293a, (int) C1373R.string.route_comment_no_more);
                return;
            }
        }
        RouteCommentActivity.b(this.f10293a).m12977a(0);
        RouteCommentActivity.b(this.f10293a).setPullLoadEnable(true);
        RouteCommentActivity.a(this.f10293a, RouteCommentActivity.e(this.f10293a) + 1);
        RouteCommentActivity.c(this.f10293a).addAll(list);
        RouteCommentActivity.d(this.f10293a).notifyDataSetChanged();
    }
}
