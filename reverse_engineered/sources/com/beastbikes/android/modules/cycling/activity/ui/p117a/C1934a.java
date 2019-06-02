package com.beastbikes.android.modules.cycling.activity.ui.p117a;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* compiled from: CyclingPagerAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.a.a */
public class C1934a extends PagerAdapter {
    /* renamed from: a */
    private ArrayList<View> f8719a;

    public C1934a(ArrayList<View> arrayList) {
        this.f8719a = arrayList;
        if (this.f8719a == null) {
            this.f8719a = new ArrayList();
        }
    }

    public int getCount() {
        return this.f8719a.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public int getItemPosition(Object obj) {
        int intValue = ((Integer) ((View) obj).getTag()).intValue();
        if (this.f8719a.contains(obj) && this.f8719a.indexOf(obj) == intValue) {
            return super.getItemPosition(obj);
        }
        return -2;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) this.f8719a.get(i);
        view.setTag(Integer.valueOf(i));
        viewGroup.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
