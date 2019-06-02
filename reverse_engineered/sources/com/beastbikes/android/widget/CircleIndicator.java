package com.beastbikes.android.widget;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;

public class CircleIndicator extends LinearLayout {
    /* renamed from: a */
    private ViewPager f6811a;
    /* renamed from: b */
    private int f6812b = -1;
    /* renamed from: c */
    private int f6813c = -1;
    /* renamed from: d */
    private int f6814d = -1;
    /* renamed from: e */
    private int f6815e = -1;
    /* renamed from: f */
    private int f6816f = -1;
    /* renamed from: g */
    private int f6817g = C1373R.animator.scale_with_alpha;
    /* renamed from: h */
    private int f6818h = 0;
    /* renamed from: i */
    private int f6819i = C1373R.drawable.white_radius;
    /* renamed from: j */
    private int f6820j = C1373R.drawable.white_radius;
    /* renamed from: k */
    private Animator f6821k;
    /* renamed from: l */
    private Animator f6822l;
    /* renamed from: m */
    private Animator f6823m;
    /* renamed from: n */
    private Animator f6824n;
    /* renamed from: o */
    private int f6825o = -1;
    /* renamed from: p */
    private final OnPageChangeListener f6826p = new CircleIndicator$1(this);
    /* renamed from: q */
    private DataSetObserver f6827q = new CircleIndicator$2(this);

    public CircleIndicator(Context context) {
        super(context);
        m8007a(context, null);
    }

    public CircleIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m8007a(context, attributeSet);
    }

    public CircleIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m8007a(context, attributeSet);
    }

    @TargetApi(21)
    public CircleIndicator(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        m8007a(context, attributeSet);
    }

    /* renamed from: a */
    private void m8007a(Context context, AttributeSet attributeSet) {
        m8010b(context, attributeSet);
        m8006a(context);
    }

    /* renamed from: b */
    private void m8010b(Context context, AttributeSet attributeSet) {
        int i = 1;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CircleIndicator);
            this.f6813c = obtainStyledAttributes.getDimensionPixelSize(0, -1);
            this.f6814d = obtainStyledAttributes.getDimensionPixelSize(1, -1);
            this.f6815e = obtainStyledAttributes.getDimensionPixelSize(2, -1);
            this.f6816f = obtainStyledAttributes.getDimensionPixelSize(3, -1);
            this.f6812b = obtainStyledAttributes.getDimensionPixelSize(4, -1);
            this.f6817g = obtainStyledAttributes.getResourceId(5, C1373R.animator.scale_with_alpha);
            this.f6818h = obtainStyledAttributes.getResourceId(6, 0);
            this.f6819i = obtainStyledAttributes.getResourceId(7, C1373R.drawable.white_radius);
            this.f6820j = obtainStyledAttributes.getResourceId(8, this.f6819i);
            if (obtainStyledAttributes.getInt(9, -1) != 1) {
                i = 0;
            }
            setOrientation(i);
            i = obtainStyledAttributes.getInt(10, -1);
            if (i < 0) {
                i = 17;
            }
            setGravity(i);
            obtainStyledAttributes.recycle();
        }
    }

    /* renamed from: a */
    private void m8006a(Context context) {
        this.f6813c = this.f6813c < 0 ? m8021a(5.0f) : this.f6813c;
        this.f6814d = this.f6814d < 0 ? m8021a(5.0f) : this.f6814d;
        this.f6812b = this.f6812b < 0 ? m8021a(5.0f) : this.f6812b;
        this.f6817g = this.f6817g == 0 ? C1373R.animator.scale_with_alpha : this.f6817g;
        this.f6821k = m8008b(context);
        this.f6823m = m8008b(context);
        this.f6823m.setDuration(0);
        this.f6822l = m8011c(context);
        this.f6824n = m8011c(context);
        this.f6824n.setDuration(0);
        this.f6819i = this.f6819i == 0 ? C1373R.drawable.white_radius : this.f6819i;
        this.f6820j = this.f6820j == 0 ? this.f6819i : this.f6820j;
    }

    /* renamed from: b */
    private Animator m8008b(Context context) {
        return AnimatorInflater.loadAnimator(context, this.f6817g);
    }

    /* renamed from: c */
    private Animator m8011c(Context context) {
        if (this.f6818h != 0) {
            return AnimatorInflater.loadAnimator(context, this.f6818h);
        }
        Animator loadAnimator = AnimatorInflater.loadAnimator(context, this.f6817g);
        loadAnimator.setInterpolator(new CircleIndicator$a(this, null));
        return loadAnimator;
    }

    public void setViewPager(ViewPager viewPager) {
        this.f6811a = viewPager;
        if (this.f6811a != null && this.f6811a.getAdapter() != null) {
            this.f6825o = -1;
            m8004a();
            this.f6811a.removeOnPageChangeListener(this.f6826p);
            this.f6811a.addOnPageChangeListener(this.f6826p);
            this.f6826p.onPageSelected(this.f6811a.getCurrentItem());
        }
    }

    public DataSetObserver getDataSetObserver() {
        return this.f6827q;
    }

    @Deprecated
    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener) {
        if (this.f6811a == null) {
            throw new NullPointerException("can not find Viewpager , setViewPager first");
        }
        this.f6811a.removeOnPageChangeListener(onPageChangeListener);
        this.f6811a.addOnPageChangeListener(onPageChangeListener);
    }

    /* renamed from: a */
    private void m8004a() {
        removeAllViews();
        int count = this.f6811a.getAdapter().getCount();
        if (count > 0) {
            int currentItem = this.f6811a.getCurrentItem();
            for (int i = 0; i < count; i++) {
                if (currentItem == i) {
                    m8005a(this.f6819i, this.f6823m, true);
                } else {
                    m8005a(this.f6820j, this.f6824n, false);
                }
            }
        }
    }

    /* renamed from: a */
    private void m8005a(@DrawableRes int i, Animator animator, boolean z) {
        if (animator.isRunning()) {
            animator.end();
            animator.cancel();
        }
        View view = new View(getContext());
        view.setBackgroundResource(i);
        addView(view, z ? this.f6815e : this.f6813c, z ? this.f6816f : this.f6814d);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        layoutParams.leftMargin = this.f6812b;
        layoutParams.rightMargin = this.f6812b;
        view.setLayoutParams(layoutParams);
        animator.setTarget(view);
        animator.start();
    }

    /* renamed from: a */
    public int m8021a(float f) {
        return (int) ((getResources().getDisplayMetrics().density * f) + 0.5f);
    }
}
