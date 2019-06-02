package com.beastbikes.android.modules.cycling.route.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.route.dto.C2188b;
import com.beastbikes.android.modules.cycling.route.ui.RouteMapSearchGeoActivity.C1419c;
import java.util.List;

final class RouteMapSearchGeoActivity$b extends BaseAdapter {
    /* renamed from: a */
    public final List<C2188b> f10308a;
    /* renamed from: b */
    final /* synthetic */ RouteMapSearchGeoActivity f10309b;

    public RouteMapSearchGeoActivity$b(RouteMapSearchGeoActivity routeMapSearchGeoActivity, List<C2188b> list) {
        this.f10309b = routeMapSearchGeoActivity;
        this.f10308a = list;
    }

    public int getCount() {
        return this.f10308a.size();
    }

    public Object getItem(int i) {
        return this.f10308a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1419c c1419c;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.route_map_search_list_item, null);
            c1419c = new C1419c(this.f10309b, view);
        } else {
            c1419c = (C1419c) view.getTag();
        }
        c1419c.a((C2188b) this.f10308a.get(i));
        return view;
    }
}
