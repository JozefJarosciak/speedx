package com.beastbikes.android.home.p105a;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.home.C1835a.C1830b;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.home.p106b.C1844c;
import java.util.ArrayList;

/* compiled from: HomePagerAdapter */
/* renamed from: com.beastbikes.android.home.a.a */
public class C1834a extends FragmentPagerAdapter implements OnPageChangeListener, C1830b {
    /* renamed from: a */
    private final FragmentActivity f8256a;
    /* renamed from: b */
    private final ViewPager f8257b;
    /* renamed from: c */
    private final ArrayList<C1833b> f8258c = new ArrayList();
    /* renamed from: d */
    private final ArrayList<C1844c> f8259d = new ArrayList();
    /* renamed from: e */
    private C1832a f8260e;

    /* compiled from: HomePagerAdapter */
    /* renamed from: com.beastbikes.android.home.a.a$a */
    public interface C1832a {
        /* renamed from: d */
        void m9551d(int i);
    }

    /* compiled from: HomePagerAdapter */
    /* renamed from: com.beastbikes.android.home.a.a$b */
    private static final class C1833b {
        /* renamed from: a */
        private final Class<?> f8254a;
        /* renamed from: b */
        private final Bundle f8255b;

        C1833b(Class<?> cls, Bundle bundle) {
            this.f8254a = cls;
            this.f8255b = bundle;
        }
    }

    public C1834a(FragmentActivity fragmentActivity, ViewPager viewPager) {
        super(fragmentActivity.getSupportFragmentManager());
        this.f8256a = fragmentActivity;
        this.f8257b = viewPager;
        this.f8257b.setAdapter(this);
        this.f8257b.addOnPageChangeListener(this);
    }

    /* renamed from: a */
    public void m9557a(C1844c c1844c, Class<?> cls, Bundle bundle) {
        C1833b c1833b = new C1833b(cls, bundle);
        c1844c.m9625a((C1830b) this);
        this.f8258c.add(c1833b);
        this.f8259d.add(c1844c);
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.f8258c.size();
    }

    public Fragment getItem(int i) {
        C1833b c1833b = (C1833b) this.f8258c.get(i);
        return Fragment.instantiate(this.f8256a, c1833b.f8254a.getName(), c1833b.f8255b);
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        m9554a(i);
    }

    public void onPageScrollStateChanged(int i) {
    }

    /* renamed from: a */
    public void mo3255a(String str) {
        int size = this.f8259d.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(((C1844c) this.f8259d.get(i)).m9624a(), str)) {
                this.f8257b.setCurrentItem(i, false);
                return;
            }
        }
    }

    /* renamed from: a */
    private void m9554a(int i) {
        HomeActivity.f4419b = i;
        if (this.f8259d != null) {
            if (this.f8260e != null) {
                this.f8260e.m9551d(i);
            }
            ((C1844c) this.f8259d.get(i)).m9626a(true);
            ((C1844c) this.f8259d.get((i + 1) % 3)).m9626a(false);
            ((C1844c) this.f8259d.get((i + 2) % 3)).m9626a(false);
            m9555b(i);
        }
    }

    /* renamed from: a */
    public void m9556a(C1832a c1832a) {
        this.f8260e = c1832a;
    }

    /* renamed from: b */
    private void m9555b(int i) {
        switch (i) {
            case 0:
                this.f8256a.setTitle(C1373R.string.str_my_data);
                return;
            case 1:
                this.f8256a.setTitle(C1373R.string.activity_fragment_title);
                return;
            case 2:
                this.f8256a.setTitle(C1373R.string.profile_fragment_detail_item_speed_force_title);
                return;
            default:
                return;
        }
    }
}
