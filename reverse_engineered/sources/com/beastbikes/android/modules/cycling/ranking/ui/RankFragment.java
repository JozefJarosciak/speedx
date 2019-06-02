package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.locale.p104a.C1848b;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import java.util.ArrayList;
import java.util.List;

@C1460c(a = 2131820582)
public class RankFragment extends SessionFragment implements C1371a {
    /* renamed from: a */
    private TabLayout f5366a;
    /* renamed from: b */
    private ViewPager f5367b;
    /* renamed from: c */
    private View f5368c;
    /* renamed from: d */
    private String f5369d = "CN.22.2038349";
    /* renamed from: e */
    private int f5370e;
    /* renamed from: f */
    private TextView f5371f;
    /* renamed from: g */
    private View f5372g;
    /* renamed from: h */
    private boolean f5373h = true;
    /* renamed from: i */
    private RankFragment$b f5374i;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (getActivity() != null) {
            getActivity().setTitle(C1373R.string.ranking_fragment_title);
        }
        if (this.f5368c != null) {
            ViewGroup viewGroup2 = (ViewGroup) this.f5368c.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.f5368c);
            }
            return this.f5368c;
        }
        setHasOptionsMenu(true);
        this.f5368c = LayoutInflater.from(getActivity()).inflate(C1373R.layout.activity_ranking, viewGroup, false);
        this.f5366a = (TabLayout) this.f5368c.findViewById(C1373R.id.activity_main_tablayout);
        this.f5367b = (ViewPager) this.f5368c.findViewById(C1373R.id.activity_main_viewpager);
        m6673c();
        return this.f5368c;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    /* renamed from: c */
    private void m6673c() {
        this.f5370e = HomeActivity.f4418a;
        this.f5373h = true;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f5369d = currentUser.getGeoCode();
            m6678a();
            if (TextUtils.isEmpty(this.f5369d)) {
                this.f5374i = new RankFragment$b(this, 3000, 1000);
                this.f5374i.start();
                C1848b.a().a(getActivity(), new RankFragment$1(this));
            }
        }
    }

    /* renamed from: a */
    private void m6667a(String str) {
        getAsyncTaskQueue().a(new RankFragment$2(this, str), new Void[0]);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        super.onCreateOptionsMenu(menu, menuInflater);
        m6675d();
        menu.getItem(0).setActionView(this.f5372g);
    }

    /* renamed from: d */
    private void m6675d() {
        List arrayList = new ArrayList();
        arrayList.add(getString(C1373R.string.ranking_fragment_menu_safety_net));
        arrayList.add(getString(C1373R.string.ranking_fragment_menu_whole_country));
        arrayList.add(getString(C1373R.string.ranking_fragment_menu_whole_area));
        this.f5372g = LayoutInflater.from(getActivity()).inflate(C1373R.layout.fragment_rank_area, null);
        this.f5371f = (TextView) this.f5372g.findViewById(C1373R.id.fragment_rank_area_tv);
        this.f5371f.setText((CharSequence) arrayList.get(this.f5370e));
        this.f5372g.setOnClickListener(new RankFragment$3(this));
    }

    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        if (!z && getActivity() != null) {
            getActivity().setTitle(C1373R.string.ranking_fragment_title);
        }
    }

    /* renamed from: a */
    public void m6678a() {
        int i = 0;
        String str = "";
        String[] split = this.f5369d.split("\\.");
        if (this.f5370e == 1 && split.length > 0) {
            str = this.f5369d.split("\\.")[0];
        } else if (this.f5370e == 2 && split.length > 1) {
            str = this.f5369d.split("\\.")[0] + "." + this.f5369d.split("\\.")[1];
        }
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        arrayList.add(getResources().getString(C1373R.string.week));
        arrayList.add(getResources().getString(C1373R.string.month));
        arrayList.add(getResources().getString(C1373R.string.year));
        arrayList.add(getResources().getString(C1373R.string.total));
        Bundle bundle = new Bundle();
        bundle.putString("beast.rank_geo", str);
        SessionFragment weeklyRankFragment = new WeeklyRankFragment();
        weeklyRankFragment.setArguments(bundle);
        arrayList2.add(weeklyRankFragment);
        weeklyRankFragment = new MonthlyRankFragment();
        weeklyRankFragment.setArguments(bundle);
        arrayList2.add(weeklyRankFragment);
        weeklyRankFragment = new YearlyRankFragment();
        weeklyRankFragment.setArguments(bundle);
        arrayList2.add(weeklyRankFragment);
        weeklyRankFragment = new TotallyRankFragment();
        weeklyRankFragment.setArguments(bundle);
        arrayList2.add(weeklyRankFragment);
        while (i < arrayList.size()) {
            this.f5366a.addTab(this.f5366a.newTab().setText((CharSequence) arrayList.get(i)));
            i++;
        }
        PagerAdapter rankFragment$a = new RankFragment$a(this, getChildFragmentManager(), arrayList2, arrayList);
        this.f5367b.setOffscreenPageLimit(4);
        this.f5367b.setAdapter(rankFragment$a);
        this.f5366a.setupWithViewPager(this.f5367b);
        this.f5366a.setTabsFromPagerAdapter(rankFragment$a);
    }
}
