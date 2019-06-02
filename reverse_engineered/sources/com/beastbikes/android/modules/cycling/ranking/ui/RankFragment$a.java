package com.beastbikes.android.modules.cycling.ranking.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.beastbikes.android.modules.SessionFragment;
import java.util.List;

public class RankFragment$a extends FragmentStatePagerAdapter {
    /* renamed from: a */
    final /* synthetic */ RankFragment f10196a;
    /* renamed from: b */
    private List<SessionFragment> f10197b;
    /* renamed from: c */
    private List<String> f10198c;

    public RankFragment$a(RankFragment rankFragment, FragmentManager fragmentManager, List<SessionFragment> list, List<String> list2) {
        this.f10196a = rankFragment;
        super(fragmentManager);
        this.f10197b = list;
        this.f10198c = list2;
    }

    public Fragment getItem(int i) {
        return (Fragment) this.f10197b.get(i);
    }

    public int getCount() {
        return this.f10197b.size();
    }

    public CharSequence getPageTitle(int i) {
        return (CharSequence) this.f10198c.get(i);
    }
}
