package com.beastbikes.android.ble.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.beastbikes.android.R$styleable;

public class ShapeIndicatorView extends View implements OnTabSelectedListener, OnPageChangeListener {
    /* renamed from: a */
    private Paint f8044a;
    /* renamed from: b */
    private Path f8045b;
    /* renamed from: c */
    private int f8046c;
    /* renamed from: d */
    private int f8047d;
    /* renamed from: e */
    private TabLayout f8048e;
    /* renamed from: f */
    private ViewPager f8049f;

    /* renamed from: com.beastbikes.android.ble.ui.widget.ShapeIndicatorView$1 */
    class C17601 implements OnScrollChangedListener {
        /* renamed from: a */
        final /* synthetic */ ShapeIndicatorView f8041a;

        C17601(ShapeIndicatorView shapeIndicatorView) {
            this.f8041a = shapeIndicatorView;
        }

        public void onScrollChanged() {
            if (this.f8041a.f8048e.getScrollX() != this.f8041a.getScrollX()) {
                this.f8041a.scrollTo(this.f8041a.f8048e.getScrollX(), this.f8041a.f8048e.getScrollY());
            }
        }
    }

    public ShapeIndicatorView(Context context) {
        this(context, null);
    }

    public ShapeIndicatorView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ShapeIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9367a(context, attributeSet, i, 0);
    }

    @TargetApi(21)
    public ShapeIndicatorView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        m9367a(context, attributeSet, i, i2);
    }

    /* renamed from: a */
    private void m9367a(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ShapeIndicatorView, i2, 0);
        this.f8046c = obtainStyledAttributes.getInteger(0, 15);
        this.f8047d = obtainStyledAttributes.getColor(1, SupportMenu.CATEGORY_MASK);
        int integer = obtainStyledAttributes.getInteger(2, 0);
        obtainStyledAttributes.recycle();
        this.f8044a = new Paint();
        this.f8044a.setAntiAlias(true);
        this.f8044a.setDither(true);
        this.f8044a.setColor(this.f8047d);
        this.f8044a.setStyle(Style.FILL);
        this.f8044a.setPathEffect(new CornerPathEffect((float) integer));
        this.f8044a.setStrokeCap(Cap.ROUND);
    }

    /* renamed from: a */
    public void m9369a(TabLayout tabLayout, final int i) {
        this.f8048e = tabLayout;
        tabLayout.setSelectedTabIndicatorColor(0);
        tabLayout.setOnTabSelectedListener(this);
        tabLayout.getViewTreeObserver().addOnScrollChangedListener(new C17601(this));
        ViewCompat.setElevation(this, ViewCompat.getElevation(this.f8048e));
        tabLayout.post(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ ShapeIndicatorView f8043b;

            public void run() {
                if (this.f8043b.f8048e.getTabCount() > 0 && i < this.f8043b.f8048e.getTabCount()) {
                    this.f8043b.onTabSelected(this.f8043b.f8048e.getTabAt(i));
                }
            }
        });
        for (int i2 = 0; i2 < tabLayout.getTabCount(); i2++) {
            m9366a(i2).setBackgroundResource(0);
        }
    }

    public void setupWithViewPager(ViewPager viewPager) {
        this.f8049f = viewPager;
        viewPager.addOnPageChangeListener(this);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        m9368a(canvas);
    }

    /* renamed from: a */
    private void m9368a(Canvas canvas) {
        if (this.f8045b != null && !this.f8045b.isEmpty()) {
            int save = canvas.save();
            canvas.drawPath(this.f8045b, this.f8044a);
            canvas.restoreToCount(save);
        }
    }

    /* renamed from: a */
    private Path m9364a(int i, float f) {
        RectF rectF = new RectF();
        View a = m9366a(i);
        if (a == null) {
            return null;
        }
        if (f <= 0.0f || i >= this.f8048e.getTabCount() - 1) {
            int top = a.getTop() + getPaddingTop();
            rectF.set((float) (a.getLeft() + this.f8046c), (float) top, (float) (a.getRight() - this.f8046c), (float) (a.getBottom() - getPaddingBottom()));
            if (rectF.isEmpty()) {
                return this.f8045b;
            }
        }
        View a2 = m9366a(i + 1);
        int right = 0 + ((int) ((((float) a2.getRight()) * f) + (((float) a.getRight()) * (1.0f - f))));
        top = a.getTop() + getPaddingTop();
        rectF.set((float) (this.f8046c + (((int) ((((float) a2.getLeft()) * f) + (((float) a.getLeft()) * (1.0f - f)))) + 0)), (float) top, (float) (right - this.f8046c), (float) (a.getBottom() - getPaddingBottom()));
        if (this.f8045b == null) {
            this.f8045b = new Path();
        }
        this.f8045b.reset();
        this.f8045b.moveTo(rectF.left, rectF.bottom);
        this.f8045b.lineTo(rectF.left, rectF.top);
        this.f8045b.lineTo(rectF.right, rectF.top);
        this.f8045b.lineTo(rectF.right, rectF.bottom);
        this.f8045b.close();
        return this.f8045b;
    }

    private Rect getTabArea() {
        if (this.f8048e == null) {
            return null;
        }
        View childAt = this.f8048e.getChildAt(0);
        Rect rect = new Rect();
        childAt.getHitRect(rect);
        return rect;
    }

    /* renamed from: a */
    private View m9366a(int i) {
        if (this.f8048e == null || this.f8048e.getTabCount() <= 0) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) this.f8048e.getChildAt(0);
        if (viewGroup != null) {
            return viewGroup.getChildAt(i);
        }
        return null;
    }

    public void onPageScrolled(int i, float f, int i2) {
        m9364a(i, f);
        invalidate();
    }

    public void onPageSelected(int i) {
        if (this.f8048e.getSelectedTabPosition() != i) {
            this.f8048e.getTabAt(i).select();
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onTabSelected(Tab tab) {
        if (this.f8049f == null) {
            m9364a(tab.getPosition(), 0.0f);
            invalidate();
        } else if (tab.getPosition() != this.f8049f.getCurrentItem()) {
            this.f8049f.setCurrentItem(tab.getPosition());
        }
    }

    public void onTabUnselected(Tab tab) {
    }

    public void onTabReselected(Tab tab) {
    }
}
