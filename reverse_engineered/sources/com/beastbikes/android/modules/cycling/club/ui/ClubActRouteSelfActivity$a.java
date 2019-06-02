package com.beastbikes.android.modules.cycling.club.ui;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.ui.ClubActRouteSelfActivity.C1406b;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import java.util.List;

final class ClubActRouteSelfActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ ClubActRouteSelfActivity f9433a;
    /* renamed from: b */
    private final List<RouteDTO> f9434b;
    /* renamed from: c */
    private final ClubActRouteSelfActivity f9435c;

    public ClubActRouteSelfActivity$a(ClubActRouteSelfActivity clubActRouteSelfActivity, ClubActRouteSelfActivity clubActRouteSelfActivity2, List<RouteDTO> list) {
        this.f9433a = clubActRouteSelfActivity;
        this.f9434b = list;
        this.f9435c = clubActRouteSelfActivity2;
    }

    public int getCount() {
        return this.f9434b.size();
    }

    public Object getItem(int i) {
        return this.f9434b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    @SuppressLint({"InflateParams"})
    public View getView(int i, View view, ViewGroup viewGroup) {
        C1406b c1406b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.route_self_list_item, null);
            c1406b = new C1406b(this.f9433a, view);
        } else {
            c1406b = (C1406b) view.getTag();
        }
        c1406b.a((RouteDTO) this.f9434b.get(i));
        return view;
    }
}
