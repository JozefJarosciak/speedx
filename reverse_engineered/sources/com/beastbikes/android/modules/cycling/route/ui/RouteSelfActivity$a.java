package com.beastbikes.android.modules.cycling.route.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.ui.RouteSelfActivity.C1422b;
import java.util.List;

final class RouteSelfActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ RouteSelfActivity f10338a;
    /* renamed from: b */
    private final List<RouteDTO> f10339b;
    /* renamed from: c */
    private final RouteSelfActivity f10340c;

    public RouteSelfActivity$a(RouteSelfActivity routeSelfActivity, RouteSelfActivity routeSelfActivity2, List<RouteDTO> list) {
        this.f10338a = routeSelfActivity;
        this.f10339b = list;
        this.f10340c = routeSelfActivity2;
    }

    public int getCount() {
        return this.f10339b.size();
    }

    public Object getItem(int i) {
        return this.f10339b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    @SuppressLint({"InflateParams"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        C1422b c1422b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.route_self_list_item, null);
            c1422b = new C1422b(this.f10338a, view, this.f10338a);
        } else {
            c1422b = (C1422b) view.getTag();
        }
        c1422b.a((RouteDTO) this.f10339b.get(i));
        return view;
    }
}
