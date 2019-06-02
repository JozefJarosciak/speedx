package com.beastbikes.android.modules.cycling.route.ui;

import android.os.AsyncTask;
import android.text.Html;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.framework.business.BusinessException;

class RouteActivity$2 extends AsyncTask<String, Void, RouteDTO> {
    /* renamed from: a */
    final /* synthetic */ RouteActivity f10284a;

    RouteActivity$2(RouteActivity routeActivity) {
        this.f10284a = routeActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11229a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11230a((RouteDTO) obj);
    }

    /* renamed from: a */
    protected RouteDTO m11229a(String... strArr) {
        try {
            return RouteActivity.c(this.f10284a).m11204d(strArr[0]);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11230a(RouteDTO routeDTO) {
        if (routeDTO != null) {
            RouteActivity.a(this.f10284a, routeDTO);
            RouteActivity.d(this.f10284a).setRating((float) routeDTO.getDifficultyCoefficient());
            RouteActivity.e(this.f10284a).setRating((float) routeDTO.getViewCoefficient());
            RouteActivity.f(this.f10284a).setRating((float) routeDTO.getTrafficCoefficient());
            if (C1849a.m9645b(this.f10284a)) {
                RouteActivity.g(this.f10284a).setText(String.format("%.0f", new Object[]{Double.valueOf(routeDTO.getTotalDistance() / 1000.0d)}));
            } else {
                RouteActivity.g(this.f10284a).setText(String.format("%.0f", new Object[]{Double.valueOf(C1849a.m9638a(routeDTO.getTotalDistance()) / 1000.0d)}));
            }
            RouteActivity.h(this.f10284a);
            RouteActivity.i(this.f10284a).setText("(" + routeDTO.getNumberOfFollowers() + ")");
            RouteActivity.j(this.f10284a).setText("      " + Html.fromHtml(routeDTO.getDescription()));
        }
    }
}
