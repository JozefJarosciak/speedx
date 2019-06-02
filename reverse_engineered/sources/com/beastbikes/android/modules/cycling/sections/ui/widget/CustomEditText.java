package com.beastbikes.android.modules.cycling.sections.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import com.beastbikes.android.modules.cycling.sections.ui.widget.DrawableClickListener.DrawablePosition;

public class CustomEditText extends EditText {
    /* renamed from: a */
    int f10662a;
    /* renamed from: b */
    int f10663b;
    /* renamed from: c */
    private Drawable f10664c;
    /* renamed from: d */
    private Drawable f10665d;
    /* renamed from: e */
    private Drawable f10666e;
    /* renamed from: f */
    private Drawable f10667f;
    /* renamed from: g */
    private DrawableClickListener f10668g;

    public CustomEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CustomEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        if (drawable != null) {
            this.f10665d = drawable;
        }
        if (drawable3 != null) {
            this.f10664c = drawable3;
        }
        if (drawable2 != null) {
            this.f10666e = drawable2;
        }
        if (drawable4 != null) {
            this.f10667f = drawable4;
        }
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.f10662a = (int) motionEvent.getX();
            this.f10663b = (int) motionEvent.getY();
            if (this.f10667f != null && this.f10667f.getBounds().contains(this.f10662a, this.f10663b)) {
                this.f10668g.onClick(DrawablePosition.BOTTOM);
                return super.onTouchEvent(motionEvent);
            } else if (this.f10666e == null || !this.f10666e.getBounds().contains(this.f10662a, this.f10663b)) {
                Rect bounds;
                int i;
                int i2;
                if (this.f10665d != null) {
                    bounds = this.f10665d.getBounds();
                    int i3 = (int) (((double) (13.0f * getResources().getDisplayMetrics().density)) + 0.5d);
                    i = this.f10662a;
                    i2 = this.f10663b;
                    if (!bounds.contains(this.f10662a, this.f10663b)) {
                        i2 = this.f10662a - i3;
                        i = this.f10663b - i3;
                        if (i2 <= 0) {
                            i2 = this.f10662a;
                        }
                        if (i <= 0) {
                            i = this.f10663b;
                        }
                        if (i2 < i) {
                            i = i2;
                        } else {
                            int i4 = i;
                            i = i2;
                            i2 = i4;
                        }
                    }
                    if (bounds.contains(i, i2) && this.f10668g != null) {
                        this.f10668g.onClick(DrawablePosition.LEFT);
                        motionEvent.setAction(3);
                        return false;
                    }
                }
                if (this.f10664c != null) {
                    bounds = this.f10664c.getBounds();
                    i2 = this.f10663b - 13;
                    i = getWidth() - (this.f10662a + 13);
                    if (i <= 0) {
                        i += 13;
                    }
                    if (i2 <= 0) {
                        i2 = this.f10663b;
                    }
                    if (!bounds.contains(i, i2) || this.f10668g == null) {
                        return super.onTouchEvent(motionEvent);
                    }
                    this.f10668g.onClick(DrawablePosition.RIGHT);
                    motionEvent.setAction(3);
                    return false;
                }
            } else {
                this.f10668g.onClick(DrawablePosition.TOP);
                return super.onTouchEvent(motionEvent);
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    protected void finalize() throws Throwable {
        this.f10664c = null;
        this.f10667f = null;
        this.f10665d = null;
        this.f10666e = null;
        super.finalize();
    }

    public void setDrawableClickListener(DrawableClickListener drawableClickListener) {
        this.f10668g = drawableClickListener;
    }
}
