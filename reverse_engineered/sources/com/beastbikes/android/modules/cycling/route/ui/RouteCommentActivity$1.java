package com.beastbikes.android.modules.cycling.route.ui;

import android.net.NetworkInfo;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.route.dto.C2190d;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.List;

class RouteCommentActivity$1 extends AsyncTask<String, Void, List<C2190d>> {
    /* renamed from: a */
    final /* synthetic */ RouteCommentActivity f10292a;

    RouteCommentActivity$1(RouteCommentActivity routeCommentActivity) {
        this.f10292a = routeCommentActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11239a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11240a((List) obj);
    }

    /* renamed from: a */
    protected List<C2190d> m11239a(String... strArr) {
        try {
            return RouteCommentActivity.a(this.f10292a).m11197a(strArr[0], 10, 1);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11240a(List<C2190d> list) {
        RouteCommentActivity.b(this.f10292a).m12976a();
        if (list == null || list.isEmpty()) {
            NetworkInfo a = C2799c.m13753a(this.f10292a);
            if (a == null || !a.isConnected()) {
                Toasts.show(this.f10292a, (int) C1373R.string.activity_unnetwork_err);
            } else {
                Toasts.show(this.f10292a, (int) C1373R.string.route_comment_no_more);
            }
            RouteCommentActivity.b(this.f10292a).setPullLoadEnable(false);
            return;
        }
        RouteCommentActivity.c(this.f10292a).clear();
        RouteCommentActivity.c(this.f10292a).addAll(list);
        C2190d c2190d = (C2190d) list.get(0);
        if (!this.f10292a.isFinishing()) {
            this.f10292a.setTitle(String.format(this.f10292a.getString(C1373R.string.route_comment_activity_title), new Object[]{Integer.valueOf(c2190d.m11226e())}));
        }
        RouteCommentActivity.d(this.f10292a).notifyDataSetChanged();
    }
}
