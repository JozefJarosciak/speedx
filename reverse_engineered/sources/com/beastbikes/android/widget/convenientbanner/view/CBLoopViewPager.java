package com.beastbikes.android.widget.convenientbanner.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.beastbikes.android.widget.convenientbanner.p166a.C2624a;
import com.beastbikes.android.widget.convenientbanner.p167c.C2627b;

public class CBLoopViewPager extends ViewPager {
    /* renamed from: a */
    OnPageChangeListener f12311a;
    /* renamed from: b */
    private C2627b f12312b;
    /* renamed from: c */
    private C2624a f12313c;
    /* renamed from: d */
    private boolean f12314d = true;
    /* renamed from: e */
    private boolean f12315e = true;
    /* renamed from: f */
    private float f12316f = 0.0f;
    /* renamed from: g */
    private float f12317g = 0.0f;
    /* renamed from: h */
    private OnPageChangeListener f12318h = new C26281(this);

    /* renamed from: com.beastbikes.android.widget.convenientbanner.view.CBLoopViewPager$1 */
    class C26281 implements OnPageChangeListener {
        /* renamed from: a */
        final /* synthetic */ CBLoopViewPager f12309a;
        /* renamed from: b */
        private float f12310b = -1.0f;

        C26281(CBLoopViewPager cBLoopViewPager) {
            this.f12309a = cBLoopViewPager;
        }

        public void onPageSelected(int i) {
            int a = this.f12309a.f12313c.m13100a(i);
            if (this.f12310b != ((float) a)) {
                this.f12310b = (float) a;
                if (this.f12309a.f12311a != null) {
                    this.f12309a.f12311a.onPageSelected(a);
                }
            }
        }

        public void onPageScrolled(int i, float f, int i2) {
            if (this.f12309a.f12311a == null) {
                return;
            }
            if (i != this.f12309a.f12313c.m13099a() - 1) {
                this.f12309a.f12311a.onPageScrolled(i, f, i2);
            } else if (((double) f) > 0.5d) {
                this.f12309a.f12311a.onPageScrolled(0, 0.0f, 0);
            } else {
                this.f12309a.f12311a.onPageScrolled(i, 0.0f, 0);
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (this.f12309a.f12311a != null) {
                this.f12309a.f12311a.onPageScrollStateChanged(i);
            }
        }
    }

    /* renamed from: a */
    public void m13110a(PagerAdapter pagerAdapter, boolean z) {
        this.f12313c = (C2624a) pagerAdapter;
        this.f12313c.m13103a(z);
        this.f12313c.m13102a(this);
        super.setAdapter(this.f12313c);
        setCurrentItem(getFristItem(), false);
    }

    public int getFristItem() {
        return this.f12315e ? this.f12313c.m13099a() : 0;
    }

    public int getLastItem() {
        return this.f12313c.m13099a() - 1;
    }

    public void setCanScroll(boolean z) {
        this.f12314d = z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.f12314d) {
            return false;
        }
        if (this.f12312b != null) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.f12316f = motionEvent.getX();
                    break;
                case 1:
                    this.f12317g = motionEvent.getX();
                    if (Math.abs(this.f12316f - this.f12317g) < 5.0f) {
                        this.f12312b.m13107a(getRealItem());
                    }
                    this.f12316f = 0.0f;
                    this.f12317g = 0.0f;
                    break;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f12314d) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    public C2624a getAdapter() {
        return this.f12313c;
    }

    public int getRealItem() {
        return this.f12313c != null ? this.f12313c.m13100a(super.getCurrentItem()) : 0;
    }

    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        this.f12311a = onPageChangeListener;
    }

    public CBLoopViewPager(Context context) {
        super(context);
        m13109a();
    }

    public CBLoopViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m13109a();
    }

    /* renamed from: a */
    private void m13109a() {
        super.setOnPageChangeListener(this.f12318h);
    }

    public void setCanLoop(boolean z) {
        this.f12315e = z;
        if (!z) {
            setCurrentItem(getRealItem(), false);
        }
        if (this.f12313c != null) {
            this.f12313c.m13103a(z);
            this.f12313c.notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(C2627b c2627b) {
        this.f12312b = c2627b;
    }
}
