package com.beastbikes.android.modules.shop.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.shop.dto.BikeShopInfoDTO;
import com.beastbikes.framework.business.BusinessException;

class BikeShopDetailActivity$1 extends AsyncTask<Void, Void, BikeShopInfoDTO> {
    /* renamed from: a */
    final /* synthetic */ BikeShopDetailActivity f11063a;

    BikeShopDetailActivity$1(BikeShopDetailActivity bikeShopDetailActivity) {
        this.f11063a = bikeShopDetailActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11893a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11894a((BikeShopInfoDTO) obj);
    }

    /* renamed from: a */
    protected BikeShopInfoDTO m11893a(Void... voidArr) {
        try {
            return BikeShopDetailActivity.d(this.f11063a).m11889a(BikeShopDetailActivity.a(this.f11063a), BikeShopDetailActivity.b(this.f11063a), BikeShopDetailActivity.c(this.f11063a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11894a(BikeShopInfoDTO bikeShopInfoDTO) {
        if (bikeShopInfoDTO != null) {
            BikeShopDetailActivity.a(this.f11063a, bikeShopInfoDTO);
            BikeShopDetailActivity.e(this.f11063a);
        }
    }
}
