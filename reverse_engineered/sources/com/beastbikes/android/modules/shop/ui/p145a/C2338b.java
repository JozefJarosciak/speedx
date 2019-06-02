package com.beastbikes.android.modules.shop.ui.p145a;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.locale.p104a.C1848b;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.beastbikes.android.modules.shop.p073a.C2327a;
import com.beastbikes.android.modules.shop.ui.BikeShopDetailActivity;
import com.beastbikes.android.modules.shop.ui.BikeShopsActivity;
import com.beastbikes.android.modules.shop.ui.p146b.C2340a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.framework.android.p056e.C2794a;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.ArrayList;
import java.util.List;

/* compiled from: NearbyBikeShopPresenter */
/* renamed from: com.beastbikes.android.modules.shop.ui.a.b */
public class C2338b {
    /* renamed from: a */
    private SessionFragmentActivity f11113a;
    /* renamed from: b */
    private C2340a f11114b;
    /* renamed from: c */
    private C2794a f11115c;
    /* renamed from: d */
    private C2327a f11116d;
    /* renamed from: e */
    private double f11117e;
    /* renamed from: f */
    private double f11118f;
    /* renamed from: g */
    private List<BikeShopListDTO> f11119g = new ArrayList();

    public C2338b(C2340a c2340a) {
        this.f11114b = c2340a;
        this.f11113a = c2340a.m11945a();
        this.f11115c = this.f11113a.getAsyncTaskQueue();
        this.f11116d = new C2327a(this.f11113a);
        SharedPreferences sharedPreferences = this.f11113a.getSharedPreferences(C1848b.m9630a().getClass().getName(), 0);
        this.f11117e = (double) Float.parseFloat(sharedPreferences.getString("beast.location.manager.lat", "0"));
        this.f11118f = (double) Float.parseFloat(sharedPreferences.getString("beast.location.manager.lon", "0"));
    }

    /* renamed from: a */
    public void m11942a(String str, int i, int i2, int i3) {
        final String str2 = str;
        final int i4 = i;
        final int i5 = i2;
        final int i6 = i3;
        this.f11115c.m13740a(new AsyncTask<String, Void, List<BikeShopListDTO>>(this) {
            /* renamed from: e */
            final /* synthetic */ C2338b f11112e;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m11933a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m11934a((List) obj);
            }

            /* renamed from: a */
            protected List<BikeShopListDTO> m11933a(String... strArr) {
                return this.f11112e.f11116d.m11891a(this.f11112e.f11118f, this.f11112e.f11117e, str2, i4, i5, i6);
            }

            /* renamed from: a */
            protected void m11934a(List<BikeShopListDTO> list) {
                if (list != null) {
                    switch (i4) {
                        case 0:
                            this.f11112e.f11114b.m11947b(list);
                            return;
                        case 1:
                            this.f11112e.f11119g.clear();
                            this.f11112e.f11119g.addAll(list);
                            this.f11112e.f11114b.m11946a(list);
                            return;
                        default:
                            return;
                    }
                }
            }
        }, new String[0]);
    }

    /* renamed from: a */
    public void m11940a() {
        this.f11113a.startActivity(new Intent(this.f11113a, BikeShopsActivity.class));
    }

    /* renamed from: b */
    public void m11943b() {
        Uri parse = Uri.parse(new StringBuilder(C1768c.f8076b).toString());
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(parse);
        this.f11113a.startActivity(intent);
    }

    /* renamed from: a */
    public void m11941a(BikeShopListDTO bikeShopListDTO) {
        if (bikeShopListDTO != null) {
            C2580w.m12905a(this.f11113a, "查看车店详情", "open_bicycle_detail");
            Intent intent = new Intent(this.f11113a, BikeShopDetailActivity.class);
            intent.putExtra("bike_shop_id", bikeShopListDTO.getShopId());
            intent.putExtra("show_enter_club", true);
            intent.putExtra("type", MapboxEvent.TYPE_LOCATION);
            this.f11113a.startActivity(intent);
        }
    }

    /* renamed from: c */
    public List<BikeShopListDTO> m11944c() {
        return this.f11119g;
    }
}
