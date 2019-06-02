package com.beastbikes.android.modules.cycling.activity.ui.record.p120a;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* compiled from: CyclingReportChartPagerAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.a.a */
public class C1965a extends PagerAdapter {
    /* renamed from: a */
    private ArrayList<View> f8859a;

    public C1965a(ArrayList<View> arrayList) {
        this.f8859a = arrayList;
    }

    public int getCount() {
        return this.f8859a.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) this.f8859a.get(i);
        viewGroup.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
