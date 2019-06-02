package com.beastbikes.android.modules.user.ui.p157a;

import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

/* compiled from: AbstractPagerAdapter */
/* renamed from: com.beastbikes.android.modules.user.ui.a.a */
public abstract class C2489a<T> extends PagerAdapter {
    /* renamed from: a */
    protected ArrayList<T> f11829a;
    /* renamed from: b */
    private SparseArray<View> f11830b;

    /* renamed from: b */
    protected abstract View mo3511b(int i);

    public C2489a(ArrayList<T> arrayList) {
        this.f11829a = arrayList;
        this.f11830b = new SparseArray(arrayList.size());
    }

    public int getCount() {
        return this.f11829a.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) this.f11830b.get(i);
        if (view == null) {
            view = mo3511b(i);
            this.f11830b.put(i, view);
        }
        viewGroup.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) this.f11830b.get(i));
    }

    public int getItemPosition(Object obj) {
        return -2;
    }

    /* renamed from: a */
    public T m12560a(int i) {
        return this.f11829a.get(i);
    }

    /* renamed from: a */
    public void m12561a() {
        this.f11830b.clear();
    }
}
