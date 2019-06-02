package com.beastbikes.android.modules.shop.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.modules.cycling.club.ui.ClubFeedImageDetailsActivity;
import java.util.ArrayList;

class BikeShopDetailActivity$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ArrayList f11064a;
    /* renamed from: b */
    final /* synthetic */ BikeShopDetailActivity f11065b;

    BikeShopDetailActivity$2(BikeShopDetailActivity bikeShopDetailActivity, ArrayList arrayList) {
        this.f11065b = bikeShopDetailActivity;
        this.f11064a = arrayList;
    }

    public void onClick(View view) {
        Intent intent = new Intent(this.f11065b, ClubFeedImageDetailsActivity.class);
        intent.putStringArrayListExtra("images", this.f11064a);
        intent.putExtra("position", view.getId());
        intent.putExtra("canDel", false);
        intent.putExtra("compress", false);
        this.f11065b.startActivity(intent);
    }
}
