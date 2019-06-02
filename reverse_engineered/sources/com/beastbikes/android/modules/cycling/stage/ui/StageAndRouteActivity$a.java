package com.beastbikes.android.modules.cycling.stage.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.beastbikes.android.modules.SessionFragment;
import java.util.List;

class StageAndRouteActivity$a extends FragmentStatePagerAdapter {
    /* renamed from: a */
    final /* synthetic */ StageAndRouteActivity f10765a;
    /* renamed from: b */
    private List<SessionFragment> f10766b;
    /* renamed from: c */
    private List<String> f10767c;

    StageAndRouteActivity$a(StageAndRouteActivity stageAndRouteActivity, FragmentManager fragmentManager, List<SessionFragment> list, List<String> list2) {
        this.f10765a = stageAndRouteActivity;
        super(fragmentManager);
        this.f10766b = list;
        this.f10767c = list2;
    }

    public Fragment getItem(int i) {
        return (Fragment) this.f10766b.get(i);
    }

    public int getCount() {
        return this.f10766b.size();
    }

    public CharSequence getPageTitle(int i) {
        return (CharSequence) this.f10767c.get(i);
    }
}
