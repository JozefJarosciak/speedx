package com.beastbikes.android.modules.shop.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.beastbikes.android.modules.shop.ui.p146b.C2340a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2801d;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903371)
public class NearbyBikeShopFrag extends SessionFragment implements OnClickListener, OnItemClickListener, C2340a {
    @C1458a(a = 2131756661)
    /* renamed from: a */
    private ListView f6100a;
    /* renamed from: b */
    private TextView f6101b;
    /* renamed from: c */
    private TextView f6102c;
    /* renamed from: d */
    private NearbyBikeShopFrag$a f6103d;
    /* renamed from: e */
    private List<BikeShopListDTO> f6104e = new ArrayList();

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        m7273d();
        m7272c();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.nearby_bike_shop_see_more:
                ((NearbyBikeShopActivity) getActivity()).m7270e();
                return;
            case C1373R.id.nearby_bike_shop_apply_join:
                ((NearbyBikeShopActivity) getActivity()).m7271f();
                return;
            default:
                return;
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        BikeShopListDTO bikeShopListDTO = (BikeShopListDTO) adapterView.getItemAtPosition(i);
        if (bikeShopListDTO != null) {
            ((NearbyBikeShopActivity) getActivity()).m7264a(bikeShopListDTO);
        }
    }

    /* renamed from: a */
    public void m7275a(List<BikeShopListDTO> list) {
        this.f6101b.setVisibility(0);
        this.f6102c.setVisibility(0);
        this.f6104e.clear();
        if (!(list == null || list.isEmpty())) {
            this.f6104e.addAll(list);
        }
        this.f6103d.notifyDataSetChanged();
    }

    /* renamed from: b */
    public void m7276b(List<BikeShopListDTO> list) {
    }

    /* renamed from: a */
    public SessionFragmentActivity m7274a() {
        return (SessionFragmentActivity) getActivity();
    }

    /* renamed from: c */
    private void m7272c() {
        this.f6100a.setOnItemClickListener(this);
        this.f6101b.setOnClickListener(this);
        this.f6102c.setOnClickListener(this);
    }

    /* renamed from: d */
    private void m7273d() {
        this.f6103d = new NearbyBikeShopFrag$a(this, this.f6104e);
        View textView = new TextView(getActivity());
        textView.setLayoutParams(new LayoutParams(-1, C2801d.a(getActivity(), 50.0f)));
        textView.setGravity(16);
        textView.setPadding(C2801d.a(getActivity(), 12.0f), 0, 0, 0);
        textView.setText(C1373R.string.str_bicycle_shop_list_section_title_near_by);
        textView.setTextColor(Color.parseColor("#ffffff"));
        textView.setTextSize(2, 15.0f);
        this.f6100a.addHeaderView(textView);
        this.f6100a.setAdapter(this.f6103d);
        View inflate = LayoutInflater.from(getActivity()).inflate(C1373R.layout.nearby_bike_shop_list_foot_view, null);
        this.f6101b = (TextView) inflate.findViewById(C1373R.id.nearby_bike_shop_see_more);
        this.f6102c = (TextView) inflate.findViewById(C1373R.id.nearby_bike_shop_apply_join);
        this.f6100a.addFooterView(inflate);
    }
}
