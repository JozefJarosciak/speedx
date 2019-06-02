package com.beastbikes.android.widget.p164c;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.google.common.primitives.Ints;

/* compiled from: IcsLinearLayout */
/* renamed from: com.beastbikes.android.widget.c.a */
public class C2588a extends LinearLayout {
    /* renamed from: c */
    private static final int[] f12083c = new int[]{16843049, 16843476, 16843561, 16843562};
    /* renamed from: d */
    private static final boolean f12084d = (VERSION.SDK_INT >= 11);
    /* renamed from: a */
    protected int f12085a;
    /* renamed from: b */
    protected int f12086b;
    /* renamed from: e */
    private Drawable f12087e;
    /* renamed from: f */
    private int f12088f;
    /* renamed from: g */
    private int f12089g;
    /* renamed from: h */
    private boolean f12090h;
    /* renamed from: i */
    private boolean f12091i;

    public C2588a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f12083c);
        setDividerDrawable(obtainStyledAttributes.getDrawable(0));
        this.f12088f = obtainStyledAttributes.getInt(2, 0);
        this.f12089g = obtainStyledAttributes.getDimensionPixelSize(3, 0);
        this.f12091i = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.f12088f) {
            requestLayout();
            invalidate();
        }
        this.f12088f = i;
    }

    public int getShowDividers() {
        return this.f12088f;
    }

    public void setDividerDrawable(Drawable drawable) {
        boolean z = false;
        if (drawable != this.f12087e) {
            this.f12087e = drawable;
            this.f12090h = drawable instanceof ColorDrawable;
            if (drawable != null) {
                this.f12085a = drawable.getIntrinsicWidth();
                this.f12086b = drawable.getIntrinsicHeight();
            } else {
                this.f12085a = 0;
                this.f12086b = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.f12089g = i;
    }

    public int getDividerPadding() {
        return this.f12089g;
    }

    public int getDividerWidth() {
        return this.f12085a;
    }

    protected void measureChildWithMargins(View view, int i, int i2, int i3, int i4) {
        int indexOfChild = indexOfChild(view);
        int orientation = getOrientation();
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        if (m12925a(indexOfChild)) {
            if (orientation == 1) {
                layoutParams.topMargin = this.f12086b;
            } else {
                layoutParams.leftMargin = this.f12085a;
            }
        }
        int childCount = getChildCount();
        if (indexOfChild == childCount - 1 && m12925a(childCount)) {
            if (orientation == 1) {
                layoutParams.bottomMargin = this.f12086b;
            } else {
                layoutParams.rightMargin = this.f12085a;
            }
        }
        super.measureChildWithMargins(view, i, i2, i3, i4);
    }

    protected void onDraw(Canvas canvas) {
        if (this.f12087e != null) {
            if (getOrientation() == 1) {
                m12923a(canvas);
            } else {
                m12926b(canvas);
            }
        }
        super.onDraw(canvas);
    }

    /* renamed from: a */
    void m12923a(Canvas canvas) {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !m12925a(i))) {
                m12924a(canvas, childAt.getTop() - ((LayoutParams) childAt.getLayoutParams()).topMargin);
            }
            i++;
        }
        if (m12925a(childCount)) {
            int height;
            View childAt2 = getChildAt(childCount - 1);
            if (childAt2 == null) {
                height = (getHeight() - getPaddingBottom()) - this.f12086b;
            } else {
                height = childAt2.getBottom();
            }
            m12924a(canvas, height);
        }
    }

    /* renamed from: b */
    void m12926b(Canvas canvas) {
        int childCount = getChildCount();
        int i = 0;
        while (i < childCount) {
            View childAt = getChildAt(i);
            if (!(childAt == null || childAt.getVisibility() == 8 || !m12925a(i))) {
                m12927b(canvas, childAt.getLeft() - ((LayoutParams) childAt.getLayoutParams()).leftMargin);
            }
            i++;
        }
        if (m12925a(childCount)) {
            int width;
            View childAt2 = getChildAt(childCount - 1);
            if (childAt2 == null) {
                width = (getWidth() - getPaddingRight()) - this.f12085a;
            } else {
                width = childAt2.getRight();
            }
            m12927b(canvas, width);
        }
    }

    /* renamed from: a */
    void m12924a(Canvas canvas, int i) {
        if (!this.f12090h || f12084d) {
            this.f12087e.setBounds(getPaddingLeft() + this.f12089g, i, (getWidth() - getPaddingRight()) - this.f12089g, this.f12086b + i);
            this.f12087e.draw(canvas);
            return;
        }
        canvas.save();
        canvas.clipRect(getPaddingLeft() + this.f12089g, i, (getWidth() - getPaddingRight()) - this.f12089g, this.f12086b + i);
        this.f12087e.draw(canvas);
        canvas.restore();
    }

    /* renamed from: b */
    void m12927b(Canvas canvas, int i) {
        if (!this.f12090h || f12084d) {
            this.f12087e.setBounds(i, getPaddingTop() + this.f12089g, this.f12085a + i, (getHeight() - getPaddingBottom()) - this.f12089g);
            this.f12087e.draw(canvas);
            return;
        }
        canvas.save();
        canvas.clipRect(i, getPaddingTop() + this.f12089g, this.f12085a + i, (getHeight() - getPaddingBottom()) - this.f12089g);
        this.f12087e.draw(canvas);
        canvas.restore();
    }

    /* renamed from: a */
    protected boolean m12925a(int i) {
        if (i == 0) {
            if ((this.f12088f & 1) != 0) {
                return true;
            }
            return false;
        } else if (i == getChildCount()) {
            if ((this.f12088f & 4) == 0) {
                return false;
            }
            return true;
        } else if ((this.f12088f & 2) == 0) {
            return false;
        } else {
            for (int i2 = i - 1; i2 >= 0; i2--) {
                if (getChildAt(i2).getVisibility() != 8) {
                    return true;
                }
            }
            return false;
        }
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.f12091i;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.f12091i = z;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.f12091i) {
            switch (getOrientation()) {
                case 0:
                    m12921a();
                    return;
                case 1:
                    m12922b();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    private void m12921a() {
        int childCount = getChildCount();
        int i = 0;
        int i2 = 0;
        while (i < childCount) {
            i++;
            i2 = Math.max(getChildAt(i).getMeasuredWidth(), i2);
        }
        int i3 = 0;
        i = 0;
        while (i3 < childCount) {
            int i4;
            View childAt = getChildAt(i3);
            if (childAt == null) {
                i4 = i;
            } else if (childAt.getVisibility() == 8) {
                i4 = i;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.weight > 0.0f) {
                    childAt.measure(MeasureSpec.makeMeasureSpec(i2, Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec(childAt.getMeasuredHeight(), Ints.MAX_POWER_OF_TWO));
                    i += i2;
                } else {
                    i += childAt.getMeasuredWidth();
                }
                i4 = (layoutParams.rightMargin + layoutParams.leftMargin) + i;
            }
            i3++;
            i = i4;
        }
        setMeasuredDimension((getPaddingLeft() + getPaddingRight()) + i, getMeasuredHeight());
    }

    /* renamed from: b */
    private void m12922b() {
        int childCount = getChildCount();
        int i = 0;
        int i2 = 0;
        while (i < childCount) {
            i++;
            i2 = Math.max(getChildAt(i).getMeasuredHeight(), i2);
        }
        int i3 = 0;
        i = 0;
        while (i3 < childCount) {
            int i4;
            View childAt = getChildAt(i3);
            if (childAt == null) {
                i4 = i;
            } else if (childAt.getVisibility() == 8) {
                i4 = i;
            } else {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                if (layoutParams.weight > 0.0f) {
                    childAt.measure(MeasureSpec.makeMeasureSpec(childAt.getMeasuredWidth(), Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec(i2, Ints.MAX_POWER_OF_TWO));
                    i += i2;
                } else {
                    i += childAt.getMeasuredHeight();
                }
                i4 = (layoutParams.rightMargin + layoutParams.leftMargin) + i;
            }
            i3++;
            i = i4;
        }
        setMeasuredDimension(getMeasuredWidth(), (getPaddingLeft() + getPaddingRight()) + i);
    }
}
