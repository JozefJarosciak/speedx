package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.grid.dto.GridDTO;
import java.util.ArrayList;
import java.util.List;

class GridExploreActivity$1 extends AsyncTask<String, Void, List<GridDTO>> {
    /* renamed from: a */
    final /* synthetic */ String f11716a;
    /* renamed from: b */
    final /* synthetic */ GridExploreActivity f11717b;

    GridExploreActivity$1(GridExploreActivity gridExploreActivity, String str) {
        this.f11717b = gridExploreActivity;
        this.f11716a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12493a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12494a((List) obj);
    }

    /* renamed from: a */
    protected List<GridDTO> m12493a(String... strArr) {
        return GridExploreActivity.a(this.f11717b).m11129a(this.f11716a);
    }

    /* renamed from: a */
    protected void m12494a(List<GridDTO> list) {
        if (list != null && !list.isEmpty() && !GridExploreActivity.b(this.f11717b)) {
            GridExploreActivity.c(this.f11717b).addAll(list);
            GridExploreActivity.d(this.f11717b).setText(String.valueOf(list.size()));
            List arrayList = new ArrayList();
            for (GridDTO latLng1 : list) {
                arrayList.add(latLng1.getLatLng1());
            }
            if (!GridExploreActivity.b(this.f11717b)) {
                GridExploreActivity.f(this.f11717b).m12874a(GridExploreActivity.e(this.f11717b), arrayList);
            }
            GridExploreActivity.g(this.f11717b);
        }
    }
}
