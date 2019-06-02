package com.beastbikes.android.modules.cycling.route.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.avos.avoscloud.AVAnalytics;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.ui.RouteSelfActivity.C1422b;
import com.beastbikes.framework.ui.android.utils.Toasts;

class RouteSelfActivity$b$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ RouteDTO f10344a;
    /* renamed from: b */
    final /* synthetic */ C1422b f10345b;

    RouteSelfActivity$b$2(C1422b c1422b, RouteDTO routeDTO) {
        this.f10345b = c1422b;
        this.f10344a = routeDTO;
    }

    public void onClick(View view) {
        RouteSelfActivity.f(this.f10345b.f5590b).edit().putString("use_route_id", this.f10344a.getId()).putString("use_route_name", this.f10344a.getName()).apply();
        Toasts.show(this.f10345b.f5590b, (int) C1373R.string.route_self_activity_use_success);
        AVAnalytics.onEvent(this.f10345b.f5590b, this.f10345b.f5590b.getString(C1373R.string.route_self_activity_make_route_to_map));
    }
}
