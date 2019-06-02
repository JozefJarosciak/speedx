package com.mob.tools.gui;

import android.view.View;
import android.view.ViewGroup;

public abstract class ViewPagerAdapter {
    private MobViewPager parent;

    public abstract int getCount();

    public abstract View getView(int i, View view, ViewGroup viewGroup);

    final void setMobViewPager(MobViewPager mobViewPager) {
        this.parent = mobViewPager;
    }

    public void onScreenChange(int i, int i2) {
    }

    public void invalidate() {
        if (this.parent != null) {
            this.parent.setAdapter(this);
        }
    }
}
