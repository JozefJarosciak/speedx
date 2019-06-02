package com.beastbikes.android.widget.convenientbanner.p167c;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;
import java.util.ArrayList;

/* compiled from: CBPageChangeListener */
/* renamed from: com.beastbikes.android.widget.convenientbanner.c.a */
public class C2626a implements OnPageChangeListener {
    /* renamed from: a */
    private ArrayList<ImageView> f12306a;
    /* renamed from: b */
    private int[] f12307b;
    /* renamed from: c */
    private OnPageChangeListener f12308c;

    public C2626a(ArrayList<ImageView> arrayList, int[] iArr) {
        this.f12306a = arrayList;
        this.f12307b = iArr;
    }

    public void onPageScrollStateChanged(int i) {
        if (this.f12308c != null) {
            this.f12308c.onPageScrollStateChanged(i);
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
        if (this.f12308c != null) {
            this.f12308c.onPageScrolled(i, f, i2);
        }
    }

    public void onPageSelected(int i) {
        for (int i2 = 0; i2 < this.f12306a.size(); i2++) {
            ((ImageView) this.f12306a.get(i)).setImageResource(this.f12307b[1]);
            if (i != i2) {
                ((ImageView) this.f12306a.get(i2)).setImageResource(this.f12307b[0]);
            }
        }
        if (this.f12308c != null) {
            this.f12308c.onPageSelected(i);
        }
    }

    /* renamed from: a */
    public void m13106a(OnPageChangeListener onPageChangeListener) {
        this.f12308c = onPageChangeListener;
    }
}
