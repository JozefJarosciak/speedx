package com.beastbikes.android.modules.cycling.route.ui;

import com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView.C2193m;

class RoutePlanActivity$3 implements C2193m {
    /* renamed from: a */
    final /* synthetic */ RoutePlanActivity f10317a;

    RoutePlanActivity$3(RoutePlanActivity routePlanActivity) {
        this.f10317a = routePlanActivity;
    }

    /* renamed from: a */
    public void mo3422a(int i) {
        RoutePlanActivity.b(this.f10317a).remove(RoutePlanActivity.a(this.f10317a).getItem(i));
        RoutePlanActivity.a(this.f10317a, true);
        RoutePlanActivity.a(this.f10317a, 0);
        RoutePlanActivity.a(this.f10317a, 0.0d);
        RoutePlanActivity.c(this.f10317a).clear();
        RoutePlanActivity.a(this.f10317a).notifyDataSetChanged();
    }
}
