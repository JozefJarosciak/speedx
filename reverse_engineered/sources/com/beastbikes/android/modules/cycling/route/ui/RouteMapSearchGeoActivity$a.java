package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.beastbikes.android.modules.cycling.route.dto.C2188b;
import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;
import java.io.Serializable;

class RouteMapSearchGeoActivity$a implements OnItemClickListener {
    /* renamed from: a */
    final /* synthetic */ RouteMapSearchGeoActivity f10307a;

    private RouteMapSearchGeoActivity$a(RouteMapSearchGeoActivity routeMapSearchGeoActivity) {
        this.f10307a = routeMapSearchGeoActivity;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        C2188b c2188b = (C2188b) adapterView.getAdapter().getItem(i);
        if (c2188b != null) {
            Serializable poiInfoDTO = new PoiInfoDTO(c2188b);
            Intent intent = this.f10307a.getIntent();
            intent.putExtra("poiinfo", poiInfoDTO);
            this.f10307a.setResult(-1, intent);
            this.f10307a.finish();
        }
    }
}
