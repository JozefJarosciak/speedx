package com.beastbikes.android.modules.preferences.ui.offlineMap;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

class OfflineMapActivity$c extends PagerAdapter {
    /* renamed from: a */
    final /* synthetic */ OfflineMapActivity f11022a;
    /* renamed from: b */
    private List<View> f11023b;

    public OfflineMapActivity$c(OfflineMapActivity offlineMapActivity, List<View> list) {
        this.f11022a = offlineMapActivity;
        this.f11023b = list;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) this.f11023b.get(i));
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        viewGroup.addView((View) this.f11023b.get(i), 0);
        return this.f11023b.get(i);
    }

    public int getCount() {
        return this.f11023b.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
