package com.beastbikes.android.widget.stickylistlibrary.stickylistheaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.google.common.primitives.Ints;

/* compiled from: WrapperView */
/* renamed from: com.beastbikes.android.widget.stickylistlibrary.stickylistheaders.e */
public class C2783e extends ViewGroup {
    /* renamed from: a */
    View f12986a;
    /* renamed from: b */
    Drawable f12987b;
    /* renamed from: c */
    int f12988c;
    /* renamed from: d */
    View f12989d;
    /* renamed from: e */
    int f12990e;

    C2783e(Context context) {
        super(context);
    }

    /* renamed from: a */
    public boolean m13717a() {
        return this.f12989d != null;
    }

    public View getItem() {
        return this.f12986a;
    }

    public View getHeader() {
        return this.f12989d;
    }

    /* renamed from: a */
    void m13716a(View view, View view2, Drawable drawable, int i) {
        if (view == null) {
            throw new NullPointerException("List view item must not be null.");
        }
        if (this.f12986a != view) {
            removeView(this.f12986a);
            this.f12986a = view;
            Object parent = view.getParent();
            if (!(parent == null || parent == this || !(parent instanceof ViewGroup))) {
                ((ViewGroup) parent).removeView(view);
            }
            addView(view);
        }
        if (this.f12989d != view2) {
            if (this.f12989d != null) {
                removeView(this.f12989d);
            }
            this.f12989d = view2;
            if (view2 != null) {
                addView(view2);
            }
        }
        if (this.f12987b != drawable) {
            this.f12987b = drawable;
            this.f12988c = i;
            invalidate();
        }
    }

    protected void onMeasure(int i, int i2) {
        int measuredHeight;
        int size = MeasureSpec.getSize(i);
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, Ints.MAX_POWER_OF_TWO);
        if (this.f12989d != null) {
            LayoutParams layoutParams = this.f12989d.getLayoutParams();
            if (layoutParams == null || layoutParams.height <= 0) {
                this.f12989d.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(0, 0));
            } else {
                this.f12989d.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(layoutParams.height, Ints.MAX_POWER_OF_TWO));
            }
            measuredHeight = this.f12989d.getMeasuredHeight() + 0;
        } else if (this.f12987b == null || this.f12986a.getVisibility() == 8) {
            measuredHeight = 0;
        } else {
            measuredHeight = this.f12988c + 0;
        }
        LayoutParams layoutParams2 = this.f12986a.getLayoutParams();
        if (this.f12986a.getVisibility() == 8) {
            this.f12986a.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(0, Ints.MAX_POWER_OF_TWO));
        } else if (layoutParams2 == null || layoutParams2.height < 0) {
            this.f12986a.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(0, 0));
            measuredHeight += this.f12986a.getMeasuredHeight();
        } else {
            this.f12986a.measure(makeMeasureSpec, MeasureSpec.makeMeasureSpec(layoutParams2.height, Ints.MAX_POWER_OF_TWO));
            measuredHeight += this.f12986a.getMeasuredHeight();
        }
        setMeasuredDimension(size, measuredHeight);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int width = getWidth();
        int height = getHeight();
        if (this.f12989d != null) {
            int measuredHeight = this.f12989d.getMeasuredHeight();
            this.f12989d.layout(0, 0, width, measuredHeight);
            this.f12990e = measuredHeight;
            this.f12986a.layout(0, measuredHeight, width, height);
        } else if (this.f12987b != null) {
            this.f12987b.setBounds(0, 0, width, this.f12988c);
            this.f12990e = this.f12988c;
            this.f12986a.layout(0, this.f12988c, width, height);
        } else {
            this.f12990e = 0;
            this.f12986a.layout(0, 0, width, height);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.f12989d == null && this.f12987b != null && this.f12986a.getVisibility() != 8) {
            if (VERSION.SDK_INT < 11) {
                canvas.clipRect(0, 0, getWidth(), this.f12988c);
            }
            this.f12987b.draw(canvas);
        }
    }
}
