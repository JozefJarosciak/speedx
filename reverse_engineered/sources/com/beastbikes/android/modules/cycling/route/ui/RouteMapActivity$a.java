package com.beastbikes.android.modules.cycling.route.ui;

import com.beastbikes.android.modules.cycling.route.dto.RouteNodeDTO;
import java.util.Comparator;

final class RouteMapActivity$a implements Comparator<RouteNodeDTO> {
    /* renamed from: a */
    final /* synthetic */ RouteMapActivity f10302a;

    private RouteMapActivity$a(RouteMapActivity routeMapActivity) {
        this.f10302a = routeMapActivity;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m11248a((RouteNodeDTO) obj, (RouteNodeDTO) obj2);
    }

    /* renamed from: a */
    public int m11248a(RouteNodeDTO routeNodeDTO, RouteNodeDTO routeNodeDTO2) {
        return (int) (routeNodeDTO.getOrdinal() - routeNodeDTO2.getOrdinal());
    }
}
