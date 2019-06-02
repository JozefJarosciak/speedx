package com.beastbikes.android.modules.cycling.sections.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.sections.ui.RoutesSelfFrag.C1425b;
import java.util.List;

final class RoutesSelfFrag$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ RoutesSelfFrag f10578a;
    /* renamed from: b */
    private final List<RouteDTO> f10579b;

    public RoutesSelfFrag$a(RoutesSelfFrag routesSelfFrag, List<RouteDTO> list) {
        this.f10578a = routesSelfFrag;
        this.f10579b = list;
    }

    public int getCount() {
        return this.f10579b.size();
    }

    public Object getItem(int i) {
        return this.f10579b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    @SuppressLint({"InflateParams"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        C1425b c1425b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.route_self_list_item, null);
            c1425b = new C1425b(this.f10578a, view);
        } else {
            c1425b = (C1425b) view.getTag();
        }
        c1425b.a((RouteDTO) this.f10579b.get(i));
        return view;
    }
}
