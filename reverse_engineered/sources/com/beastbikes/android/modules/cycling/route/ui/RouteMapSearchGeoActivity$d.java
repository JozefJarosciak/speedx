package com.beastbikes.android.modules.cycling.route.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.baidu.mapapi.search.core.PoiInfo;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.route.ui.RouteMapSearchGeoActivity.C1420e;
import java.util.List;

final class RouteMapSearchGeoActivity$d extends BaseAdapter {
    /* renamed from: a */
    public final List<PoiInfo> f10310a;
    /* renamed from: b */
    final /* synthetic */ RouteMapSearchGeoActivity f10311b;

    public RouteMapSearchGeoActivity$d(RouteMapSearchGeoActivity routeMapSearchGeoActivity, List<PoiInfo> list) {
        this.f10311b = routeMapSearchGeoActivity;
        this.f10310a = list;
    }

    public int getCount() {
        return this.f10310a.size();
    }

    public Object getItem(int i) {
        return this.f10310a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1420e c1420e;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.route_map_search_list_item, null);
            c1420e = new C1420e(this.f10311b, view);
        } else {
            c1420e = (C1420e) view.getTag();
        }
        c1420e.a((PoiInfo) this.f10310a.get(i));
        return view;
    }
}
