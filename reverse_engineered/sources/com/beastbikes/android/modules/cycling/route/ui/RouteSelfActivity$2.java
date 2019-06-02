package com.beastbikes.android.modules.cycling.route.ui;

import android.net.NetworkInfo;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class RouteSelfActivity$2 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ int f10335a;
    /* renamed from: b */
    final /* synthetic */ RouteSelfActivity f10336b;

    RouteSelfActivity$2(RouteSelfActivity routeSelfActivity, int i) {
        this.f10336b = routeSelfActivity;
        this.f10335a = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11263a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11264a((Boolean) obj);
    }

    protected void onPreExecute() {
        if (RouteSelfActivity.a(this.f10336b) != null) {
            RouteSelfActivity.a(this.f10336b).show();
        }
    }

    /* renamed from: a */
    protected Boolean m11263a(String... strArr) {
        try {
            return Boolean.valueOf(RouteSelfActivity.b(this.f10336b).m11205e(strArr[0]));
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m11264a(Boolean bool) {
        if (RouteSelfActivity.a(this.f10336b) != null) {
            RouteSelfActivity.a(this.f10336b).dismiss();
        }
        if (bool.booleanValue()) {
            RouteSelfActivity.e(this.f10336b).remove(this.f10335a);
            RouteSelfActivity.g(this.f10336b).notifyDataSetChanged();
            if (RouteSelfActivity.e(this.f10336b).size() == 0) {
                RouteSelfActivity.c(this.f10336b).setVisibility(8);
                RouteSelfActivity.d(this.f10336b).setVisibility(0);
                return;
            }
            return;
        }
        NetworkInfo a = C2799c.m13753a(this.f10336b);
        if (a == null || !a.isConnected()) {
            Toasts.show(this.f10336b, (int) C1373R.string.network_not_awesome);
        } else {
            Toasts.show(this.f10336b, (int) C1373R.string.delete_err);
        }
    }
}
