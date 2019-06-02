package com.beastbikes.android.modules.cycling.sections.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.avos.avoscloud.AVAnalytics;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.sections.dto.UseRouteLineDTO;
import com.beastbikes.android.modules.cycling.sections.ui.RoutesSelfFrag.C1425b;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.framework.ui.android.utils.Toasts;

class RoutesSelfFrag$b$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ RouteDTO f10583a;
    /* renamed from: b */
    final /* synthetic */ C1425b f10584b;

    RoutesSelfFrag$b$2(C1425b c1425b, RouteDTO routeDTO) {
        this.f10584b = c1425b;
        this.f10583a = routeDTO;
    }

    public void onClick(View view) {
        RoutesSelfFrag.e(this.f10584b.f5675a).edit().putString("use_route_id", this.f10583a.getId()).putString("use_route_name", this.f10583a.getName()).apply();
        C2574s.m12893a().m12895a(new UseRouteLineDTO(this.f10583a.getName()));
        Toasts.show(this.f10584b.f5675a.getActivity(), (int) C1373R.string.route_self_activity_use_success);
        AVAnalytics.onEvent(this.f10584b.f5675a.getActivity(), this.f10584b.f5675a.getString(C1373R.string.route_self_activity_make_route_to_map));
        this.f10584b.f5675a.getActivity().finish();
    }
}
