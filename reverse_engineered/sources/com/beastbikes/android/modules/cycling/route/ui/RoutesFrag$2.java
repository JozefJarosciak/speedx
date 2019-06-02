package com.beastbikes.android.modules.cycling.route.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.modules.cycling.route.dto.C2187a;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class RoutesFrag$2 extends AsyncTask<String, Void, List<RouteDTO>> {
    /* renamed from: a */
    final /* synthetic */ RoutesFrag f10349a;

    RoutesFrag$2(RoutesFrag routesFrag) {
        this.f10349a = routesFrag;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11267a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11268a((List) obj);
    }

    protected void onPreExecute() {
        if (RoutesFrag.a(this.f10349a) != null) {
            RoutesFrag.a(this.f10349a).show();
        }
    }

    /* renamed from: a */
    protected List<RouteDTO> m11267a(String... strArr) {
        String str = strArr[0];
        if (TextUtils.isEmpty(str)) {
            str = "131";
        }
        try {
            Collection a = RoutesFrag.b(this.f10349a).m11195a();
            if (!(a == null || a.isEmpty())) {
                synchronized (RoutesFrag.c(this.f10349a)) {
                    RoutesFrag.c(this.f10349a).clear();
                    RoutesFrag.c(this.f10349a).addAll(a);
                }
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        try {
            String str2;
            if (RoutesFrag.c(this.f10349a).size() < 1) {
                str = "131";
            }
            Set linkedHashSet = new LinkedHashSet();
            for (C2187a a2 : RoutesFrag.c(this.f10349a)) {
                linkedHashSet.add(a2.m11211a());
            }
            if (linkedHashSet.contains(str)) {
                str2 = str;
            } else {
                str2 = "131";
            }
            return RoutesFrag.b(this.f10349a).m11196a(str2);
        } catch (BusinessException e2) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11268a(List<RouteDTO> list) {
        if (RoutesFrag.a(this.f10349a) != null) {
            RoutesFrag.a(this.f10349a).dismiss();
        }
        if (list == null || list.isEmpty()) {
            RoutesFrag.d(this.f10349a).setVisibility(0);
            RoutesFrag.e(this.f10349a).clear();
            RoutesFrag.f(this.f10349a).notifyDataSetChanged();
            RoutesFrag.a(this.f10349a, false);
        }
        Activity activity = this.f10349a.getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
        if (list != null) {
            synchronized (RoutesFrag.e(this.f10349a)) {
                RoutesFrag.d(this.f10349a).setVisibility(8);
                RoutesFrag.e(this.f10349a).clear();
                RoutesFrag.e(this.f10349a).addAll(list);
                RoutesFrag.f(this.f10349a).notifyDataSetChanged();
                RoutesFrag.a(this.f10349a, true);
            }
        }
    }
}
