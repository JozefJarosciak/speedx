package com.beastbikes.android.modules.shop.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.beastbikes.android.modules.SessionFragment;
import java.util.List;

public class NearbyBikeShopActivity$a extends FragmentStatePagerAdapter {
    /* renamed from: a */
    final /* synthetic */ NearbyBikeShopActivity f11094a;
    /* renamed from: b */
    private List<SessionFragment> f11095b;
    /* renamed from: c */
    private List<String> f11096c;

    NearbyBikeShopActivity$a(NearbyBikeShopActivity nearbyBikeShopActivity, FragmentManager fragmentManager, List<SessionFragment> list, List<String> list2) {
        this.f11094a = nearbyBikeShopActivity;
        super(fragmentManager);
        this.f11095b = list;
        this.f11096c = list2;
    }

    public Fragment getItem(int i) {
        return (Fragment) this.f11095b.get(i);
    }

    public int getCount() {
        return this.f11095b.size();
    }

    public CharSequence getPageTitle(int i) {
        return (CharSequence) this.f11096c.get(i);
    }
}
