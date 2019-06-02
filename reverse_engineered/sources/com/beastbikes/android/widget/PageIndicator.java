package com.beastbikes.android.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;

public class PageIndicator extends LinearLayout implements OnPageChangeListener {
    /* renamed from: a */
    private int f12123a;
    /* renamed from: b */
    private int f12124b;
    /* renamed from: c */
    private boolean f12125c;
    /* renamed from: d */
    private IndicatorType f12126d;
    /* renamed from: e */
    private ViewPager f12127e;

    public enum IndicatorType {
        CIRCLE(0),
        FRACTION(1),
        UNKNOWN(-1);
        
        private int type;

        private IndicatorType(int i) {
            this.type = i;
        }

        public static IndicatorType of(int i) {
            switch (i) {
                case 0:
                    return CIRCLE;
                case 1:
                    return FRACTION;
                default:
                    return UNKNOWN;
            }
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        m12963b(i);
    }

    public void onPageScrollStateChanged(int i) {
    }

    public PageIndicator(Context context) {
        this(context, null);
    }

    public PageIndicator(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f12123a = -1;
        this.f12125c = false;
        this.f12126d = IndicatorType.of(0);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.PageIndicator, 0, 0);
        try {
            this.f12124b = obtainStyledAttributes.getDimensionPixelSize(0, m12959a(context, 5));
            this.f12126d = IndicatorType.of(obtainStyledAttributes.getInt(1, this.f12126d.type));
            m12960a();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    @SuppressLint({"InlinedApi"})
    /* renamed from: a */
    private void m12960a() {
        setOrientation(0);
        if (!(getLayoutParams() instanceof LayoutParams)) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
            layoutParams.gravity = 8388691;
            setLayoutParams(layoutParams);
        }
    }

    public void setViewPager(ViewPager viewPager) {
        this.f12127e = viewPager;
        setIndicatorType(this.f12126d);
        this.f12127e.setOnPageChangeListener(this);
    }

    public void setIndicatorType(IndicatorType indicatorType) {
        this.f12126d = indicatorType;
        this.f12125c = true;
        if (this.f12127e != null) {
            m12961a(this.f12127e.getAdapter().getCount());
        }
    }

    /* renamed from: b */
    private void m12962b() {
        removeAllViews();
    }

    /* renamed from: a */
    private void m12961a(int i) {
        m12962b();
        if (i > 0) {
            if (this.f12126d == IndicatorType.CIRCLE) {
                for (int i2 = 0; i2 < i; i2++) {
                    View imageView = new ImageView(getContext());
                    ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                    layoutParams.leftMargin = this.f12124b;
                    layoutParams.rightMargin = this.f12124b;
                    imageView.setImageResource(C1373R.drawable.circle_indicator_stroke);
                    addView(imageView, layoutParams);
                }
            } else if (this.f12126d == IndicatorType.FRACTION) {
                View textView = new TextView(getContext());
                textView.setTextColor(-1);
                int a = m12959a(getContext(), 10);
                textView.setPadding(a, a >> 1, a, a >> 1);
                textView.setBackgroundResource(C1373R.drawable.transparent);
                textView.setTag(Integer.valueOf(i));
                addView(textView, new LinearLayout.LayoutParams(-2, -2));
            }
            m12963b(this.f12127e.getCurrentItem());
        }
    }

    /* renamed from: b */
    private void m12963b(int i) {
        if (this.f12125c || this.f12123a != i) {
            this.f12125c = false;
            if (this.f12126d == IndicatorType.CIRCLE) {
                if (this.f12123a == -1) {
                    ((ImageView) getChildAt(i)).setImageResource(C1373R.drawable.circle_indicator_solid);
                    this.f12123a = i;
                    return;
                }
                ((ImageView) getChildAt(this.f12123a)).setImageResource(C1373R.drawable.circle_indicator_stroke);
                ((ImageView) getChildAt(i)).setImageResource(C1373R.drawable.circle_indicator_solid);
            } else if (this.f12126d == IndicatorType.FRACTION) {
                ((TextView) getChildAt(0)).setText(String.format("%d/%d", new Object[]{Integer.valueOf(i + 1), Integer.valueOf(Integer.parseInt(((TextView) getChildAt(0)).getTag().toString()))}));
            }
            this.f12123a = i;
        }
    }

    /* renamed from: a */
    private int m12959a(Context context, int i) {
        return ((int) context.getResources().getDisplayMetrics().density) * i;
    }
}
