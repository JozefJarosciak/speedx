package com.beastbikes.android.modules.shop.ui;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.SearchView.SearchAutoComplete;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.beastbikes.android.modules.shop.ui.p145a.C2336a;
import com.beastbikes.android.modules.shop.ui.p146b.C2340a;
import com.beastbikes.android.widget.SwipeRefreshAndLoadLayout;
import com.beastbikes.android.widget.stickylistlibrary.stickygridheaders.StickyGridHeadersGridView;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903079)
public class BikeShopsActivity extends SessionFragmentActivity implements OnRefreshListener, OnQueryTextListener, OnItemClickListener, C2340a {
    @C1458a(a = 2131755376)
    /* renamed from: a */
    private StickyGridHeadersGridView f6089a;
    @C1458a(a = 2131755375)
    /* renamed from: b */
    private SwipeRefreshAndLoadLayout f6090b;
    /* renamed from: c */
    private C2336a f6091c;
    /* renamed from: d */
    private BikeShopsActivity$a f6092d;
    /* renamed from: e */
    private List<BikeShopListDTO> f6093e = new ArrayList();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m7257b();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C1373R.menu.menu_nearby_bike_shop, menu);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(C1373R.id.menu_nearby_bike_shop_search));
        searchView.setIconifiedByDefault(true);
        searchView.setQueryHint(getString(C1373R.string.label_search_bike_shop_or_city));
        ((SearchAutoComplete) searchView.findViewById(C1373R.id.search_src_text)).setBackgroundDrawable(getResources().getDrawable(C1373R.drawable.bg_search_view));
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    public void onRefresh() {
        this.f6091c.a("", 0);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i < this.f6093e.size()) {
            this.f6091c.a((BikeShopListDTO) this.f6093e.get(i));
        }
    }

    /* renamed from: a */
    public void m7259a(List<BikeShopListDTO> list) {
        this.f6090b.setLoading(false);
        this.f6090b.setRefreshing(false);
        if (list != null && !list.isEmpty()) {
            this.f6093e.clear();
            this.f6093e.addAll(list);
            this.f6092d.notifyDataSetChanged();
        }
    }

    /* renamed from: b */
    public void m7260b(List<BikeShopListDTO> list) {
    }

    /* renamed from: a */
    public SessionFragmentActivity m7258a() {
        return this;
    }

    /* renamed from: b */
    private void m7257b() {
        this.f6091c = new C2336a(this);
        this.f6092d = new BikeShopsActivity$a(this, this.f6093e, null);
        this.f6089a.setAdapter(this.f6092d);
        this.f6089a.setOnItemClickListener(this);
        this.f6090b.setChildGridView(this.f6089a);
        this.f6090b.setOnRefreshListener(this);
        this.f6091c.a("", 0);
    }

    public boolean onQueryTextSubmit(String str) {
        this.f6091c.a(str, 1);
        return false;
    }

    public boolean onQueryTextChange(String str) {
        return false;
    }
}
