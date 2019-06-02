package com.beastbikes.android.modules.cycling.route.ui;

import android.os.AsyncTask;
import com.beastbikes.framework.business.BusinessException;

class RouteSelfActivity$3 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ RouteSelfActivity f10337a;

    RouteSelfActivity$3(RouteSelfActivity routeSelfActivity) {
        this.f10337a = routeSelfActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11265a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11266a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m11265a(String... strArr) {
        try {
            return Boolean.valueOf(RouteSelfActivity.b(this.f10337a).m11199a(RouteSelfActivity.h(this.f10337a).getId(), strArr[0]));
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m11266a(Boolean bool) {
        if (bool.booleanValue()) {
            RouteSelfActivity.e(this.f10337a).remove(RouteSelfActivity.i(this.f10337a));
            RouteSelfActivity.e(this.f10337a).add(RouteSelfActivity.i(this.f10337a), RouteSelfActivity.h(this.f10337a));
            RouteSelfActivity.g(this.f10337a).notifyDataSetChanged();
        }
    }
}
