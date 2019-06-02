package com.beastbikes.android.modules.shop.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.List;

class NearbyBikeShopFrag$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ NearbyBikeShopFrag f11097a;
    /* renamed from: b */
    private List<BikeShopListDTO> f11098b;

    NearbyBikeShopFrag$a(NearbyBikeShopFrag nearbyBikeShopFrag, List<BikeShopListDTO> list) {
        this.f11097a = nearbyBikeShopFrag;
        this.f11098b = list;
    }

    public int getCount() {
        return this.f11098b.size();
    }

    public Object getItem(int i) {
        return this.f11098b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1432b c1432b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.bike_shop_listitem, null);
            c1432b = new C1432b(view, MapboxEvent.TYPE_LOCATION);
        } else {
            c1432b = (C1432b) view.getTag();
        }
        c1432b.a((BikeShopListDTO) getItem(i));
        return view;
    }
}
