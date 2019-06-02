package com.beastbikes.android.modules.shop.ui;

import android.view.View;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

class BikeShopsActivity$b extends ViewHolder<BikeShopListDTO> {
    /* renamed from: a */
    final /* synthetic */ BikeShopsActivity f11092a;
    /* renamed from: b */
    private TextView f11093b;

    public /* synthetic */ void bind(Object obj) {
        m11924a((BikeShopListDTO) obj);
    }

    BikeShopsActivity$b(BikeShopsActivity bikeShopsActivity, View view) {
        this.f11092a = bikeShopsActivity;
        super(view);
        this.f11093b = (TextView) view.findViewById(C1373R.id.bike_shops_header_view_tv);
    }

    /* renamed from: a */
    public void m11924a(BikeShopListDTO bikeShopListDTO) {
        if (bikeShopListDTO != null) {
            this.f11093b.setText(bikeShopListDTO.getProvince());
        }
    }
}
