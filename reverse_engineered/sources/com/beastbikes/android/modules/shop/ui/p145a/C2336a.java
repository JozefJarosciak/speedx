package com.beastbikes.android.modules.shop.ui.p145a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import com.beastbikes.android.locale.p104a.C1848b;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.beastbikes.android.modules.shop.p073a.C2327a;
import com.beastbikes.android.modules.shop.ui.BikeShopDetailActivity;
import com.beastbikes.android.modules.shop.ui.BikeShopsActivity;
import com.beastbikes.android.modules.shop.ui.p146b.C2340a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p056e.C2794a;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.List;

/* compiled from: BikeShopsPresenter */
/* renamed from: com.beastbikes.android.modules.shop.ui.a.a */
public class C2336a {
    /* renamed from: a */
    private BikeShopsActivity f11102a;
    /* renamed from: b */
    private C2340a f11103b;
    /* renamed from: c */
    private C2794a f11104c = this.f11102a.getAsyncTaskQueue();
    /* renamed from: d */
    private C2327a f11105d = new C2327a(this.f11102a);
    /* renamed from: e */
    private double f11106e;
    /* renamed from: f */
    private double f11107f;

    public C2336a(C2340a c2340a) {
        this.f11103b = c2340a;
        this.f11102a = (BikeShopsActivity) c2340a.m11945a();
        SharedPreferences sharedPreferences = this.f11102a.getSharedPreferences(C1848b.m9630a().getClass().getName(), 0);
        this.f11106e = (double) Float.parseFloat(sharedPreferences.getString("beast.location.manager.lat", "0"));
        this.f11107f = (double) Float.parseFloat(sharedPreferences.getString("beast.location.manager.lon", "0"));
    }

    /* renamed from: a */
    public void m11932a(final String str, final int i) {
        this.f11104c.m13740a(new AsyncTask<String, Void, List<BikeShopListDTO>>(this) {
            /* renamed from: c */
            final /* synthetic */ C2336a f11101c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11925a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11926a((List) obj);
            }

            /* renamed from: a */
            protected List<BikeShopListDTO> m11925a(String... strArr) {
                return this.f11101c.f11105d.m11891a(this.f11101c.f11107f, this.f11101c.f11106e, str, i, 1, 1000);
            }

            /* renamed from: a */
            protected void m11926a(List<BikeShopListDTO> list) {
                this.f11101c.f11103b.m11946a(list);
            }
        }, new String[0]);
    }

    /* renamed from: a */
    public void m11931a(BikeShopListDTO bikeShopListDTO) {
        if (bikeShopListDTO != null) {
            C2580w.m12905a(this.f11102a, "查看车店详情", "open_bicycle_detail");
            Intent intent = new Intent(this.f11102a, BikeShopDetailActivity.class);
            intent.putExtra("bike_shop_id", bikeShopListDTO.getShopId());
            intent.putExtra("show_enter_club", true);
            intent.putExtra("type", MapboxEvent.TYPE_LOCATION);
            this.f11102a.startActivity(intent);
        }
    }
}
