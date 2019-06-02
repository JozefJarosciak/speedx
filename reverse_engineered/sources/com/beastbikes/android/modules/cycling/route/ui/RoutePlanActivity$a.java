package com.beastbikes.android.modules.cycling.route.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;
import com.beastbikes.android.modules.cycling.route.ui.RoutePlanActivity.C1421b;
import java.util.List;

final class RoutePlanActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ RoutePlanActivity f10330a;
    /* renamed from: b */
    private final List<PoiInfoDTO> f10331b;

    public RoutePlanActivity$a(RoutePlanActivity routePlanActivity, List<PoiInfoDTO> list) {
        this.f10330a = routePlanActivity;
        this.f10331b = list;
    }

    public void notifyDataSetChanged() {
        if (this.f10331b != null && this.f10331b.size() >= 0) {
            if (this.f10331b.size() > 0) {
                RoutePlanActivity.p(this.f10330a).setVisibility(0);
                RoutePlanActivity.s(this.f10330a).setVisibility(0);
                if (RoutePlanActivity.t(this.f10330a)) {
                    RoutePlanActivity.s(this.f10330a).setText(C1373R.string.route_map_make_activity_point_edit_finish);
                    RoutePlanActivity.u(this.f10330a).setText(C1373R.string.route_map_make_activity_point_title);
                } else {
                    RoutePlanActivity.s(this.f10330a).setText(C1373R.string.str_edit);
                }
            } else {
                RoutePlanActivity.u(this.f10330a).setText(C1373R.string.route_map_make_activity_point_title);
                RoutePlanActivity.p(this.f10330a).setVisibility(8);
                RoutePlanActivity.s(this.f10330a).setVisibility(8);
            }
            RoutePlanActivity.a(this.f10330a, this.f10331b);
            int size = this.f10331b.size();
            if (!RoutePlanActivity.v(this.f10330a) || size <= 1) {
                RoutePlanActivity.p(this.f10330a).setVisibility(8);
            } else {
                RoutePlanActivity.p(this.f10330a).setVisibility(0);
            }
            if (1 < size) {
                RoutePlanActivity.w(this.f10330a).setVisibility(0);
            } else {
                RoutePlanActivity.w(this.f10330a).setVisibility(4);
            }
            RoutePlanActivity.b(this.f10330a, this.f10331b);
        }
        super.notifyDataSetChanged();
    }

    public int getCount() {
        return this.f10331b.size();
    }

    public Object getItem(int i) {
        return this.f10331b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1421b c1421b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.route_map_make_item_edit, null);
            c1421b = new C1421b(this.f10330a, view, this.f10331b);
        } else {
            c1421b = (C1421b) view.getTag();
        }
        PoiInfoDTO poiInfoDTO = (PoiInfoDTO) this.f10331b.get(i);
        poiInfoDTO.setIndex(i);
        c1421b.a(poiInfoDTO);
        return view;
    }
}
