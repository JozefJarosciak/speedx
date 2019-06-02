package com.beastbikes.android.ble.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.dto.NavigationLocation;
import com.beastbikes.android.ble.ui.p100c.C1748a;

class SearchLocationActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ SearchLocationActivity f7658a;
    /* renamed from: b */
    private LayoutInflater f7659b;

    public /* synthetic */ Object getItem(int i) {
        return m9158a(i);
    }

    public SearchLocationActivity$a(SearchLocationActivity searchLocationActivity) {
        this.f7658a = searchLocationActivity;
        this.f7659b = LayoutInflater.from(searchLocationActivity);
    }

    public int getCount() {
        return SearchLocationActivity.a(this.f7658a).size();
    }

    /* renamed from: a */
    public NavigationLocation m9158a(int i) {
        return (NavigationLocation) SearchLocationActivity.a(this.f7658a).get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        NavigationLocation a = m9158a(i);
        if (a == null) {
            View textView = new TextView(this.f7658a);
            textView.setText(C1373R.string.str_navigation_clear_history);
            textView.setTextColor(Color.parseColor("#999999"));
            textView.setTextSize(11.0f);
            textView.setHeight(C1748a.m9316a(30.0f, this.f7658a));
            textView.setGravity(17);
            return textView;
        }
        SearchLocationActivity$b searchLocationActivity$b;
        if (view instanceof TextView) {
            textView = null;
        } else {
            textView = view;
        }
        if (textView == null) {
            View inflate = this.f7659b.inflate(C1373R.layout.list_item_search_location, viewGroup, false);
            SearchLocationActivity$b searchLocationActivity$b2 = new SearchLocationActivity$b();
            searchLocationActivity$b2.f7660a = (TextView) inflate.findViewById(C1373R.id.textView_location_item_name);
            searchLocationActivity$b2.f7661b = (TextView) inflate.findViewById(C1373R.id.textView_location_item_address);
            inflate.setTag(searchLocationActivity$b2);
            searchLocationActivity$b = searchLocationActivity$b2;
            textView = inflate;
        } else {
            searchLocationActivity$b = (SearchLocationActivity$b) textView.getTag();
        }
        searchLocationActivity$b.f7660a.setText(a.getName());
        searchLocationActivity$b.f7661b.setText(a.getAddress());
        return textView;
    }
}
