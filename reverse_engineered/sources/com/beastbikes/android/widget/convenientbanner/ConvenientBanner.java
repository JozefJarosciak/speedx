package com.beastbikes.android.widget.convenientbanner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;
import com.beastbikes.android.widget.convenientbanner.p116b.C1923a;
import com.beastbikes.android.widget.convenientbanner.p166a.C2624a;
import com.beastbikes.android.widget.convenientbanner.p167c.C2626a;
import com.beastbikes.android.widget.convenientbanner.view.CBLoopViewPager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ConvenientBanner<T> extends LinearLayout {
    /* renamed from: a */
    private List<T> f12283a;
    /* renamed from: b */
    private int[] f12284b;
    /* renamed from: c */
    private ArrayList<ImageView> f12285c;
    /* renamed from: d */
    private C2626a f12286d;
    /* renamed from: e */
    private OnPageChangeListener f12287e;
    /* renamed from: f */
    private C2624a f12288f;
    /* renamed from: g */
    private CBLoopViewPager f12289g;
    /* renamed from: h */
    private C2625a f12290h;
    /* renamed from: i */
    private ViewGroup f12291i;
    /* renamed from: j */
    private long f12292j;
    /* renamed from: k */
    private boolean f12293k;
    /* renamed from: l */
    private boolean f12294l;
    /* renamed from: m */
    private boolean f12295m;
    /* renamed from: n */
    private boolean f12296n;
    /* renamed from: o */
    private Runnable f12297o;

    /* renamed from: com.beastbikes.android.widget.convenientbanner.ConvenientBanner$1 */
    class C26231 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ ConvenientBanner f12282a;

        C26231(ConvenientBanner convenientBanner) {
            this.f12282a = convenientBanner;
        }

        public void run() {
            if (this.f12282a.f12289g != null && this.f12282a.f12293k) {
                this.f12282a.f12289g.setCurrentItem(this.f12282a.f12289g.getCurrentItem() + 1);
                this.f12282a.postDelayed(this.f12282a.f12297o, this.f12282a.f12292j);
            }
        }
    }

    public enum PageIndicatorAlign {
        ALIGN_PARENT_LEFT,
        ALIGN_PARENT_RIGHT,
        CENTER_HORIZONTAL
    }

    public ConvenientBanner(Context context, boolean z) {
        this(context, null);
        this.f12296n = z;
    }

    public ConvenientBanner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f12285c = new ArrayList();
        this.f12294l = false;
        this.f12295m = true;
        this.f12296n = true;
        this.f12297o = new C26231(this);
        this.f12296n = context.obtainStyledAttributes(attributeSet, R$styleable.ConvenientBanner).getBoolean(0, true);
        m13088a(context);
    }

    /* renamed from: a */
    private void m13088a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(C1373R.layout.convenientbanner_include_viewpager, this, true);
        this.f12289g = (CBLoopViewPager) inflate.findViewById(C1373R.id.cbLoopViewPager);
        this.f12291i = (ViewGroup) inflate.findViewById(C1373R.id.loPageTurningPoint);
        m13091c();
    }

    /* renamed from: a */
    public ConvenientBanner m13095a(C1923a c1923a, List<T> list) {
        this.f12283a = list;
        this.f12288f = new C2624a(c1923a, this.f12283a);
        this.f12289g.m13110a(this.f12288f, this.f12296n);
        if (this.f12284b != null) {
            m13096a(this.f12284b);
        }
        return this;
    }

    /* renamed from: a */
    public void m13097a() {
        this.f12289g.getAdapter().notifyDataSetChanged();
        if (this.f12284b != null) {
            m13096a(this.f12284b);
        }
    }

    /* renamed from: a */
    public ConvenientBanner m13096a(int[] iArr) {
        this.f12291i.removeAllViews();
        this.f12285c.clear();
        this.f12284b = iArr;
        if (this.f12283a != null) {
            for (int i = 0; i < this.f12283a.size(); i++) {
                View imageView = new ImageView(getContext());
                imageView.setLayoutParams(new LayoutParams(20, 20));
                imageView.setPadding(5, 0, 5, 0);
                if (this.f12285c.isEmpty()) {
                    imageView.setImageResource(iArr[1]);
                } else {
                    imageView.setImageResource(iArr[0]);
                }
                this.f12285c.add(imageView);
                this.f12291i.addView(imageView);
            }
            this.f12286d = new C2626a(this.f12285c, iArr);
            this.f12289g.setOnPageChangeListener(this.f12286d);
            this.f12286d.onPageSelected(this.f12289g.getRealItem());
            if (this.f12287e != null) {
                this.f12286d.m13106a(this.f12287e);
            }
        }
        return this;
    }

    /* renamed from: a */
    public ConvenientBanner m13094a(PageIndicatorAlign pageIndicatorAlign, int i) {
        int i2;
        int i3 = -1;
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f12291i.getLayoutParams();
        layoutParams.addRule(9, pageIndicatorAlign == PageIndicatorAlign.ALIGN_PARENT_LEFT ? -1 : 0);
        if (pageIndicatorAlign == PageIndicatorAlign.ALIGN_PARENT_RIGHT) {
            i2 = -1;
        } else {
            i2 = 0;
        }
        layoutParams.addRule(11, i2);
        if (pageIndicatorAlign != PageIndicatorAlign.CENTER_HORIZONTAL) {
            i3 = 0;
        }
        layoutParams.addRule(14, i3);
        layoutParams.setMargins(0, i, 0, 0);
        this.f12291i.setLayoutParams(layoutParams);
        return this;
    }

    /* renamed from: a */
    public ConvenientBanner m13093a(long j) {
        if (this.f12293k) {
            m13098b();
        }
        this.f12294l = true;
        this.f12292j = j;
        this.f12293k = true;
        postDelayed(this.f12297o, j);
        return this;
    }

    /* renamed from: b */
    public void m13098b() {
        this.f12293k = false;
        removeCallbacks(this.f12297o);
    }

    /* renamed from: c */
    private void m13091c() {
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            this.f12290h = new C2625a(this.f12289g.getContext());
            declaredField.set(this.f12289g, this.f12290h);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        }
    }

    public void setManualPageable(boolean z) {
        this.f12289g.setCanScroll(z);
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 1 || action == 3 || action == 4) {
            if (this.f12294l) {
                m13093a(this.f12292j);
            }
        } else if (action == 0 && this.f12294l) {
            m13098b();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getCurrentItem() {
        if (this.f12289g != null) {
            return this.f12289g.getRealItem();
        }
        return -1;
    }

    public void setcurrentitem(int i) {
        if (this.f12289g != null) {
            this.f12289g.setCurrentItem(i);
        }
    }

    public OnPageChangeListener getOnPageChangeListener() {
        return this.f12287e;
    }

    public void setScrollDuration(int i) {
        this.f12290h.m13105a(i);
    }

    public int getScrollDuration() {
        return this.f12290h.m13104a();
    }

    public CBLoopViewPager getViewPager() {
        return this.f12289g;
    }

    public void setCanLoop(boolean z) {
        this.f12296n = z;
        this.f12289g.setCanLoop(z);
    }
}
