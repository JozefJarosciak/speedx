package com.beastbikes.android.modules.cycling.route.dto;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import java.util.List;

/* compiled from: RouteCommentAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.route.dto.c */
public class C2189c extends BaseAdapter {
    /* renamed from: a */
    private List<C2190d> f10271a;

    public C2189c(List<C2190d> list) {
        this.f10271a = list;
    }

    public int getCount() {
        return this.f10271a.size();
    }

    public Object getItem(int i) {
        return this.f10271a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        c$a c_a;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), C1373R.layout.route_comment_list_item, null);
            c_a = new c$a(this, view);
        } else {
            c_a = (c$a) view.getTag();
        }
        c_a.a((C2190d) this.f10271a.get(i));
        return view;
    }
}
