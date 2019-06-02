package com.beastbikes.android.modules.cycling.route.ui;

import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;
import com.beastbikes.android.modules.cycling.route.ui.widget.DragSortListView.C2192h;

class RoutePlanActivity$1 implements C2192h {
    /* renamed from: a */
    final /* synthetic */ RoutePlanActivity f10314a;

    RoutePlanActivity$1(RoutePlanActivity routePlanActivity) {
        this.f10314a = routePlanActivity;
    }

    public void a_(int i, int i2) {
        if (i != i2) {
            Object item = RoutePlanActivity.a(this.f10314a).getItem(i);
            RoutePlanActivity.b(this.f10314a).remove(item);
            RoutePlanActivity.b(this.f10314a).add(i2, (PoiInfoDTO) item);
            RoutePlanActivity.a(this.f10314a, true);
            RoutePlanActivity.a(this.f10314a, 0);
            RoutePlanActivity.a(this.f10314a, 0.0d);
            RoutePlanActivity.c(this.f10314a).clear();
            RoutePlanActivity.a(this.f10314a).notifyDataSetChanged();
        }
    }
}
