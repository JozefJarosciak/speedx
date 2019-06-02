package com.beastbikes.android.widget.convenientbanner.p166a;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.convenientbanner.p116b.C1923a;
import com.beastbikes.android.widget.convenientbanner.p116b.C1935b;
import com.beastbikes.android.widget.convenientbanner.view.CBLoopViewPager;
import java.util.List;

/* compiled from: CBPageAdapter */
/* renamed from: com.beastbikes.android.widget.convenientbanner.a.a */
public class C2624a<T> extends PagerAdapter {
    /* renamed from: a */
    protected List<T> f12298a;
    /* renamed from: b */
    protected C1923a f12299b;
    /* renamed from: c */
    private boolean f12300c = true;
    /* renamed from: d */
    private CBLoopViewPager f12301d;
    /* renamed from: e */
    private final int f12302e = 300;
    /* renamed from: f */
    private int f12303f;

    /* renamed from: a */
    public int m13100a(int i) {
        int a = m13099a();
        if (a == 0) {
            return 0;
        }
        return i % a;
    }

    public int getCount() {
        return this.f12300c ? m13099a() * 300 : m13099a();
    }

    /* renamed from: a */
    public int m13099a() {
        return this.f12298a == null ? 0 : this.f12298a.size();
    }

    public void notifyDataSetChanged() {
        this.f12303f = getCount();
        super.notifyDataSetChanged();
    }

    public int getItemPosition(Object obj) {
        if (this.f12303f <= 0) {
            return super.getItemPosition(obj);
        }
        this.f12303f--;
        return -2;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View a = m13101a(m13100a(i), null, viewGroup);
        viewGroup.addView(a);
        return a;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public void finishUpdate(ViewGroup viewGroup) {
        int currentItem = this.f12301d.getCurrentItem();
        if (currentItem == 0) {
            currentItem = this.f12301d.getFristItem();
        } else if (currentItem == getCount() - 1) {
            currentItem = this.f12301d.getLastItem();
        }
        try {
            this.f12301d.setCurrentItem(currentItem, false);
        } catch (IllegalStateException e) {
        }
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    /* renamed from: a */
    public void m13103a(boolean z) {
        this.f12300c = z;
    }

    /* renamed from: a */
    public void m13102a(CBLoopViewPager cBLoopViewPager) {
        this.f12301d = cBLoopViewPager;
    }

    public C2624a(C1923a c1923a, List<T> list) {
        this.f12299b = c1923a;
        this.f12298a = list;
    }

    /* renamed from: a */
    public View m13101a(int i, View view, ViewGroup viewGroup) {
        C1935b c1935b;
        if (view == null) {
            c1935b = (C1935b) this.f12299b.mo3288b();
            view = c1935b.mo3293a(viewGroup.getContext());
            view.setTag(C1373R.id.cb_item_tag, c1935b);
        } else {
            c1935b = (C1935b) view.getTag(C1373R.id.cb_item_tag);
        }
        if (!(this.f12298a == null || this.f12298a.isEmpty())) {
            c1935b.mo3294a(viewGroup.getContext(), i, this.f12298a.get(i));
        }
        return view;
    }
}
