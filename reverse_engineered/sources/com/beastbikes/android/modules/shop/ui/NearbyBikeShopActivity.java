package com.beastbikes.android.modules.shop.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.support.v7.widget.SearchView.SearchAutoComplete;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.ui.widget.ShapeIndicatorView;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.beastbikes.android.modules.shop.ui.p145a.C2338b;
import com.beastbikes.android.modules.shop.ui.p146b.C2340a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2801d;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@C1459b(a = 2130903156)
public class NearbyBikeShopActivity extends SessionFragmentActivity implements OnQueryTextListener, C2340a {
    @C1458a(a = 2131755830)
    /* renamed from: a */
    private ShapeIndicatorView f6094a;
    @C1458a(a = 2131755831)
    /* renamed from: b */
    private TabLayout f6095b;
    @C1458a(a = 2131755832)
    /* renamed from: c */
    private ViewPager f6096c;
    /* renamed from: d */
    private NearbyBikeShopFrag f6097d;
    /* renamed from: e */
    private BikeShopMapFragment f6098e;
    /* renamed from: f */
    private C2338b f6099f;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m7261g();
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

    public boolean onQueryTextSubmit(String str) {
        this.f6099f.a(str, 1, 1, 1000);
        return false;
    }

    public boolean onQueryTextChange(String str) {
        this.f6099f.a(str, 1, 1, 1000);
        return false;
    }

    /* renamed from: a */
    public void m7265a(List<BikeShopListDTO> list) {
        if (this.f6097d != null) {
            this.f6097d.m7275a(list);
        }
        if (this.f6098e != null) {
            this.f6098e.m7254a(list);
        }
    }

    /* renamed from: b */
    public void m7267b(List<BikeShopListDTO> list) {
        if (this.f6098e != null) {
            this.f6098e.m7255b(list);
        }
    }

    /* renamed from: a */
    public SessionFragmentActivity m7263a() {
        return this;
    }

    /* renamed from: g */
    private void m7261g() {
        List arrayList = new ArrayList();
        arrayList.add(getString(C1373R.string.str_list));
        arrayList.add(getString(C1373R.string.str_map));
        List arrayList2 = new ArrayList();
        this.f6097d = new NearbyBikeShopFrag();
        this.f6098e = new BikeShopMapFragment();
        arrayList2.add(this.f6097d);
        arrayList2.add(this.f6098e);
        this.f6096c.setAdapter(new NearbyBikeShopActivity$a(this, getSupportFragmentManager(), arrayList2, arrayList));
        this.f6096c.setOffscreenPageLimit(arrayList2.size());
        this.f6095b.setupWithViewPager(this.f6096c);
        m7262h();
        this.f6094a.setupWithViewPager(this.f6096c);
        this.f6094a.a(this.f6095b, 0);
        this.f6099f = new C2338b(this);
        this.f6099f.a("", 1, 1, 5);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f6097d = null;
        this.f6098e = null;
    }

    /* renamed from: b */
    public void m7266b() {
        this.f6099f.a("", 1, 1, 5);
    }

    /* renamed from: c */
    public void m7268c() {
        this.f6099f.a("", 0, 1, 1000);
    }

    /* renamed from: d */
    public List<BikeShopListDTO> m7269d() {
        return this.f6099f.c();
    }

    /* renamed from: e */
    public void m7270e() {
        this.f6099f.a();
    }

    /* renamed from: f */
    public void m7271f() {
        this.f6099f.b();
    }

    /* renamed from: a */
    public void m7264a(BikeShopListDTO bikeShopListDTO) {
        this.f6099f.a(bikeShopListDTO);
    }

    /* renamed from: h */
    private void m7262h() {
        Class cls = this.f6095b.getClass();
        int width = getWindowManager().getDefaultDisplay().getWidth();
        try {
            Field declaredField = cls.getDeclaredField("mTabStrip");
            declaredField.setAccessible(true);
            LinearLayout linearLayout = (LinearLayout) declaredField.get(this.f6095b);
            int a = C2801d.a(this, 30.0f);
            width = (width - (a * 9)) / 5;
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                View childAt = linearLayout.getChildAt(i);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1, 1.0f);
                layoutParams.setMargins(a, 0, a, 0);
                layoutParams.width = width;
                childAt.setLayoutParams(layoutParams);
                childAt.invalidate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
