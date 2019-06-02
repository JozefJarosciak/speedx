package com.beastbikes.android.modules.shop.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.lib.view.search.MySearchBar;
import com.beastbikes.framework.ui.android.lib.view.search.MySearchListener;
import java.util.HashMap;
import java.util.Map;

@C1459b(a = 2130903078)
@C1460c(a = 2131820558)
public class BikeShopListActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131755372)
    /* renamed from: a */
    private TextView f6049a;
    @C1458a(a = 2131755370)
    /* renamed from: b */
    private View f6050b;
    @C1458a(a = 2131755371)
    /* renamed from: c */
    private TextView f6051c;
    @C1458a(a = 2131755369)
    /* renamed from: d */
    private LinearLayout f6052d;
    /* renamed from: e */
    private FragmentManager f6053e;
    /* renamed from: f */
    private int[] f6054f = new int[]{C1373R.id.bike_shop_tab_all};
    /* renamed from: g */
    private Map<Integer, BikeShopListFrag> f6055g = new HashMap();
    /* renamed from: h */
    private MySearchBar f6056h;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f6056h = new MySearchBar(this, getString(C1373R.string.club_search_search_btn), getString(C1373R.string.label_search_bike_shop_hint));
        this.f6052d.addView(this.f6056h);
        this.f6049a.setOnClickListener(this);
        this.f6051c.setOnClickListener(this);
        this.f6053e = getSupportFragmentManager();
        m7248a(this.f6051c);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.bike_shop_tab_all:
                this.f6052d.setVisibility(0);
                m7248a(view);
                return;
            case C1373R.id.bike_shop_tab_mine:
                this.f6052d.setVisibility(8);
                m7248a(view);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m7248a(View view) {
        FragmentTransaction beginTransaction = this.f6053e.beginTransaction();
        Bundle bundle = new Bundle();
        if (this.f6054f.length <= 1) {
            this.f6050b.setVisibility(8);
        }
        for (int i : this.f6054f) {
            MySearchListener mySearchListener = (BikeShopListFrag) this.f6055g.get(Integer.valueOf(i));
            if (view.getId() == i) {
                view.setSelected(true);
                bundle.putInt("type", view.getId());
                if (mySearchListener == null) {
                    mySearchListener = (BikeShopListFrag) Fragment.instantiate(this, BikeShopListFrag.class.getName());
                    mySearchListener.setArguments(bundle);
                    beginTransaction.add((int) C1373R.id.bike_shop_list_frag, (Fragment) mySearchListener);
                    this.f6055g.put(Integer.valueOf(view.getId()), mySearchListener);
                } else {
                    beginTransaction.show(mySearchListener);
                }
                this.f6056h.setSearchBarListener(mySearchListener);
            } else {
                findViewById(i).setSelected(false);
                if (mySearchListener != null) {
                    beginTransaction.hide(mySearchListener);
                }
            }
        }
        beginTransaction.commitAllowingStateLoss();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.bike_shop_map_switch:
                startActivity(new Intent(this, BikeShopMapFragment.class));
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
