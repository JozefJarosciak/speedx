package com.beastbikes.android.modules.cycling.route.ui;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.route.dto.RouteDTO;
import com.beastbikes.android.modules.cycling.route.ui.RoutesFrag.C1423b;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

final class RoutesFrag$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ RoutesFrag f10350a;
    /* renamed from: b */
    private final WindowManager f10351b = this.f10350a.getActivity().getWindowManager();
    /* renamed from: c */
    private final Display f10352c = this.f10351b.getDefaultDisplay();
    /* renamed from: d */
    private final DisplayMetrics f10353d = new DisplayMetrics();

    public RoutesFrag$a(RoutesFrag routesFrag) {
        this.f10350a = routesFrag;
        this.f10352c.getMetrics(this.f10353d);
    }

    public int getCount() {
        return RoutesFrag.e(this.f10350a).size();
    }

    public Object getItem(int i) {
        return RoutesFrag.e(this.f10350a).get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder c1423b;
        if (view == null) {
            view = View.inflate(viewGroup.getContext(), C1373R.layout.routes_fragment_route_list_item, null);
            view.setLayoutParams(new LayoutParams(this.f10353d.widthPixels, (int) (((float) this.f10353d.widthPixels) / 1.6842105f)));
            c1423b = new C1423b(this.f10350a, view);
        } else {
            c1423b = C1423b.as(view);
        }
        c1423b.bind((RouteDTO) getItem(i));
        return view;
    }
}
