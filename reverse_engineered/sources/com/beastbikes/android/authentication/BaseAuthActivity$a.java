package com.beastbikes.android.authentication;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

class BaseAuthActivity$a extends PagerAdapter {
    /* renamed from: a */
    final /* synthetic */ BaseAuthActivity f7213a;

    private BaseAuthActivity$a(BaseAuthActivity baseAuthActivity) {
        this.f7213a = baseAuthActivity;
    }

    public int getCount() {
        return BaseAuthActivity.a(this.f7213a).size();
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) BaseAuthActivity.a(this.f7213a).get(i);
        viewGroup.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) BaseAuthActivity.a(this.f7213a).get(i));
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
