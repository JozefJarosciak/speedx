package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;
import com.beastbikes.android.ble.dto.NavigationLocation;
import java.util.ArrayList;

class SearchLocationActivity$5 extends AsyncTask<String, Void, ArrayList<NavigationLocation>> {
    /* renamed from: a */
    final /* synthetic */ SearchLocationActivity f7655a;

    SearchLocationActivity$5(SearchLocationActivity searchLocationActivity) {
        this.f7655a = searchLocationActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9154a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9155a((ArrayList) obj);
    }

    /* renamed from: a */
    protected ArrayList<NavigationLocation> m9154a(String... strArr) {
        return SearchLocationActivity.e(this.f7655a).m8898d(strArr[0]);
    }

    /* renamed from: a */
    protected void m9155a(ArrayList<NavigationLocation> arrayList) {
        SearchLocationActivity.a(this.f7655a).clear();
        if (!(arrayList == null || arrayList.isEmpty())) {
            SearchLocationActivity.a(this.f7655a).addAll(arrayList);
        }
        SearchLocationActivity.b(this.f7655a).notifyDataSetChanged();
    }
}
