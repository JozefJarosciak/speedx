package com.beastbikes.android.modules.cycling.route.ui;

import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.beastbikes.android.C1373R;

class RouteMapSearchGeoActivity$1 implements TextWatcher {
    /* renamed from: a */
    final /* synthetic */ RouteMapSearchGeoActivity f10303a;

    RouteMapSearchGeoActivity$1(RouteMapSearchGeoActivity routeMapSearchGeoActivity) {
        this.f10303a = routeMapSearchGeoActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (charSequence.length() > 0) {
            RouteMapSearchGeoActivity.a(this.f10303a, charSequence.toString());
            if (RouteMapSearchGeoActivity.a(this.f10303a)) {
                if (RouteMapSearchGeoActivity.b(this.f10303a) != null) {
                    RouteMapSearchGeoActivity.b(this.f10303a).searchInCity(new PoiCitySearchOption().city("").keyword(RouteMapSearchGeoActivity.c(this.f10303a)));
                }
            } else if (RouteMapSearchGeoActivity.d(this.f10303a) != null) {
                RouteMapSearchGeoActivity.d(this.f10303a).m9698a(RouteMapSearchGeoActivity.c(this.f10303a));
            }
        }
        if (TextUtils.isEmpty(RouteMapSearchGeoActivity.e(this.f10303a).getText().toString())) {
            RouteMapSearchGeoActivity.e(this.f10303a).setCompoundDrawables(null, null, null, null);
            return;
        }
        Drawable drawable = this.f10303a.getResources().getDrawable(C1373R.drawable.ic_section_search_clear_black);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        RouteMapSearchGeoActivity.e(this.f10303a).setCompoundDrawables(null, null, drawable, null);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
    }
}
