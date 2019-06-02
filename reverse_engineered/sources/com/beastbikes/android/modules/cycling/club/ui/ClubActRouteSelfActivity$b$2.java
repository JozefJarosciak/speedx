package com.beastbikes.android.modules.cycling.club.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.avos.avoscloud.AVAnalytics;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.ui.ClubActRouteSelfActivity.C1406b;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubActRouteSelfActivity$b$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ RouteDTO f9439a;
    /* renamed from: b */
    final /* synthetic */ C1406b f9440b;

    ClubActRouteSelfActivity$b$2(C1406b c1406b, RouteDTO routeDTO) {
        this.f9440b = c1406b;
        this.f9439a = routeDTO;
    }

    public void onClick(View view) {
        ClubActRouteSelfActivity.f(this.f9440b.f4825a).edit().putString("use_route_id", this.f9439a.getId()).apply();
        Toasts.show(this.f9440b.f4825a, (int) C1373R.string.route_self_activity_use_success);
        AVAnalytics.onEvent(this.f9440b.f4825a, this.f9440b.f4825a.getString(C1373R.string.route_self_activity_make_route_to_map));
    }
}
