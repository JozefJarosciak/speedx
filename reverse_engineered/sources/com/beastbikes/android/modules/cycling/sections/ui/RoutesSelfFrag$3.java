package com.beastbikes.android.modules.cycling.sections.ui;

import android.os.AsyncTask;
import com.beastbikes.framework.business.BusinessException;

class RoutesSelfFrag$3 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ RoutesSelfFrag f10577a;

    RoutesSelfFrag$3(RoutesSelfFrag routesSelfFrag) {
        this.f10577a = routesSelfFrag;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11449a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11450a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m11449a(String... strArr) {
        try {
            return Boolean.valueOf(RoutesSelfFrag.a(this.f10577a).m11199a(RoutesSelfFrag.g(this.f10577a).getId(), strArr[0]));
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m11450a(Boolean bool) {
        if (bool.booleanValue()) {
            RoutesSelfFrag.d(this.f10577a).remove(RoutesSelfFrag.h(this.f10577a));
            RoutesSelfFrag.d(this.f10577a).add(RoutesSelfFrag.h(this.f10577a), RoutesSelfFrag.g(this.f10577a));
            RoutesSelfFrag.f(this.f10577a).notifyDataSetChanged();
        }
    }
}
