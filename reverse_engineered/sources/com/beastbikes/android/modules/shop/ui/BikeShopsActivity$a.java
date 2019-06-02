package com.beastbikes.android.modules.shop.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.C2095d;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.List;

class BikeShopsActivity$a extends BaseAdapter implements C2095d {
    /* renamed from: a */
    final /* synthetic */ BikeShopsActivity f11090a;
    /* renamed from: b */
    private List<BikeShopListDTO> f11091b;

    /* renamed from: b */
    public /* synthetic */ Object mo3400b(int i) {
        return m11922a(i);
    }

    private BikeShopsActivity$a(BikeShopsActivity bikeShopsActivity, List<BikeShopListDTO> list) {
        this.f11090a = bikeShopsActivity;
        this.f11091b = list;
    }

    public int getCount() {
        if (this.f11091b == null) {
            return 0;
        }
        return this.f11091b.size();
    }

    public Object getItem(int i) {
        return this.f11091b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1432b c1432b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.bike_shop_listitem, null);
            C1432b c1432b2 = new C1432b(view, MapboxEvent.TYPE_LOCATION);
            view.setTag(c1432b2);
            c1432b = c1432b2;
        } else {
            c1432b = (C1432b) view.getTag();
        }
        if (i < this.f11091b.size()) {
            c1432b.a((BikeShopListDTO) this.f11091b.get(i));
        }
        return view;
    }

    /* renamed from: a */
    public String m11922a(int i) {
        return ((BikeShopListDTO) this.f11091b.get(i)).getProvince();
    }

    /* renamed from: a */
    public View mo3399a(int i, View view, ViewGroup viewGroup) {
        BikeShopsActivity$b bikeShopsActivity$b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.bike_shops_header_view, null);
            BikeShopsActivity$b bikeShopsActivity$b2 = new BikeShopsActivity$b(this.f11090a, view);
            view.setTag(bikeShopsActivity$b2);
            bikeShopsActivity$b = bikeShopsActivity$b2;
        } else {
            bikeShopsActivity$b = (BikeShopsActivity$b) view.getTag();
        }
        if (this.f11091b.size() > i) {
            bikeShopsActivity$b.m11924a((BikeShopListDTO) this.f11091b.get(i));
        }
        return view;
    }
}
