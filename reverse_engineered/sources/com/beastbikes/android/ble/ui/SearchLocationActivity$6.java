package com.beastbikes.android.ble.ui;

import android.content.Intent;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.dto.NavigationLocation;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.google.gson.Gson;
import com.mapbox.mapboxsdk.geometry.LatLng;

class SearchLocationActivity$6 extends AsyncTask<Void, Void, LatLng> {
    /* renamed from: a */
    final /* synthetic */ NavigationLocation f7656a;
    /* renamed from: b */
    final /* synthetic */ SearchLocationActivity f7657b;

    SearchLocationActivity$6(SearchLocationActivity searchLocationActivity, NavigationLocation navigationLocation) {
        this.f7657b = searchLocationActivity;
        this.f7656a = navigationLocation;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9156a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9157a((LatLng) obj);
    }

    /* renamed from: a */
    protected LatLng m9156a(Void... voidArr) {
        return SearchLocationActivity.e(this.f7657b).m8900e(this.f7656a.getPlaceId(), this.f7656a.getReference());
    }

    /* renamed from: a */
    protected void m9157a(LatLng latLng) {
        SearchLocationActivity.f(this.f7657b);
        if (latLng == null) {
            this.f7656a.setSuccess(false);
            SearchLocationActivity.a().error("get location information from google error");
            Toasts.show(this.f7657b, this.f7657b.getString(C1373R.string.str_get_location_info_failed));
            return;
        }
        this.f7656a.setLatitude(latLng.getLatitude());
        this.f7656a.setLongitude(latLng.getLongitude());
        this.f7656a.setSuccess(true);
        SearchLocationActivity.g(this.f7657b).a(this.f7657b, "searchHistory", new Gson().toJson(SearchLocationActivity.d(this.f7657b))).apply();
        Intent intent = this.f7657b.getIntent();
        intent.putExtra("mapboxlocation", this.f7656a);
        intent.putExtra("way_point_position", SearchLocationActivity.h(this.f7657b));
        SearchLocationActivity.a(this.f7657b, SearchLocationActivity.i(this.f7657b), intent);
        this.f7657b.finish();
    }
}
