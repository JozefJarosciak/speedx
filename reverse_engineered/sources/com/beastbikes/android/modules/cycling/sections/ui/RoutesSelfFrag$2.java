package com.beastbikes.android.modules.cycling.sections.ui;

import android.net.NetworkInfo;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class RoutesSelfFrag$2 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ C1802i f10574a;
    /* renamed from: b */
    final /* synthetic */ int f10575b;
    /* renamed from: c */
    final /* synthetic */ RoutesSelfFrag f10576c;

    RoutesSelfFrag$2(RoutesSelfFrag routesSelfFrag, C1802i c1802i, int i) {
        this.f10576c = routesSelfFrag;
        this.f10574a = c1802i;
        this.f10575b = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11447a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11448a((Boolean) obj);
    }

    protected void onPreExecute() {
        if (this.f10574a != null) {
            this.f10574a.show();
        }
    }

    /* renamed from: a */
    protected Boolean m11447a(String... strArr) {
        try {
            return Boolean.valueOf(RoutesSelfFrag.a(this.f10576c).m11205e(strArr[0]));
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m11448a(Boolean bool) {
        if (this.f10574a != null) {
            this.f10574a.dismiss();
        }
        if (bool.booleanValue()) {
            RoutesSelfFrag.d(this.f10576c).remove(this.f10575b);
            RoutesSelfFrag.f(this.f10576c).notifyDataSetChanged();
            if (RoutesSelfFrag.d(this.f10576c).size() == 0) {
                RoutesSelfFrag.b(this.f10576c).setVisibility(8);
                RoutesSelfFrag.c(this.f10576c).setVisibility(0);
                return;
            }
            return;
        }
        NetworkInfo a = C2799c.m13753a(this.f10576c.getActivity());
        if (a == null || !a.isConnected()) {
            Toasts.show(this.f10576c.getActivity(), (int) C1373R.string.network_not_awesome);
        } else {
            Toasts.show(this.f10576c.getActivity(), (int) C1373R.string.delete_err);
        }
    }
}
