package com.beastbikes.android.ble.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.beastbikes.android.ble.dto.NavigationLocation;

class SearchLocationActivity$4 implements OnItemClickListener {
    /* renamed from: a */
    final /* synthetic */ SearchLocationActivity f7654a;

    SearchLocationActivity$4(SearchLocationActivity searchLocationActivity) {
        this.f7654a = searchLocationActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        NavigationLocation navigationLocation = (NavigationLocation) SearchLocationActivity.a(this.f7654a).get(i);
        if (navigationLocation == null) {
            SearchLocationActivity.a(this.f7654a).clear();
            SearchLocationActivity.d(this.f7654a).clear();
            SearchLocationActivity.b(this.f7654a).notifyDataSetChanged();
            SearchLocationActivity.a(this.f7654a, null);
            return;
        }
        if (SearchLocationActivity.d(this.f7654a).contains(navigationLocation)) {
            SearchLocationActivity.d(this.f7654a).remove(navigationLocation);
        }
        if (SearchLocationActivity.d(this.f7654a).size() >= 5) {
            SearchLocationActivity.d(this.f7654a).remove(4);
        }
        SearchLocationActivity.d(this.f7654a).add(0, navigationLocation);
        SearchLocationActivity.a(this.f7654a, navigationLocation);
    }
}
