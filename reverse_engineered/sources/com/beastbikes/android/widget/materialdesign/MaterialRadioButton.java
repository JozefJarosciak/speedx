package com.beastbikes.android.widget.materialdesign;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import android.widget.CompoundButton;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;
import com.google.common.primitives.Ints;

public class MaterialRadioButton extends CompoundButton {
    /* renamed from: a */
    public boolean f12409a;
    /* renamed from: b */
    private int f12410b;
    /* renamed from: c */
    private long f12411c;
    /* renamed from: d */
    private int f12412d;
    /* renamed from: e */
    private int f12413e;
    /* renamed from: f */
    private int f12414f;
    /* renamed from: g */
    private int f12415g;
    /* renamed from: h */
    private int f12416h;
    /* renamed from: i */
    private int f12417i;
    /* renamed from: j */
    private int f12418j;
    /* renamed from: k */
    private int f12419k;
    /* renamed from: l */
    private Rect f12420l;
    /* renamed from: m */
    private boolean f12421m;
    /* renamed from: n */
    private Paint f12422n;
    /* renamed from: o */
    private Paint f12423o;
    /* renamed from: p */
    private Paint f12424p;

    public MaterialRadioButton(Context context) {
        this(context, null);
    }

    public MaterialRadioButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MaterialRadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f12410b = 1;
        this.f12409a = true;
        this.f12422n = new Paint(1);
        this.f12423o = new Paint(1);
        this.f12424p = new Paint(1);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.RadioButton);
        this.f12412d = obtainStyledAttributes.getColor(0, getResources().getColor(C1373R.color.radio_color));
        this.f12413e = obtainStyledAttributes.getColor(1, getResources().getColor(C1373R.color.radio_checked_color));
        obtainStyledAttributes.recycle();
        this.f12414f = getResources().getDimensionPixelSize(C1373R.dimen.radio_width);
        this.f12415g = getResources().getDimensionPixelSize(C1373R.dimen.radio_height);
        this.f12416h = getResources().getDimensionPixelSize(C1373R.dimen.radio_border_radius);
        this.f12417i = getResources().getDimensionPixelSize(C1373R.dimen.radio_thumb_radius);
        this.f12418j = getResources().getDimensionPixelSize(C1373R.dimen.radio_ripple_radius);
        this.f12419k = getResources().getDimensionPixelSize(C1373R.dimen.radio_stroke_width);
        this.f12423o.setColor(this.f12413e);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(m13197a(i), Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec(m13198b(i2), Ints.MAX_POWER_OF_TWO));
    }

    /* renamed from: a */
    private int m13197a(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode != Ints.MAX_POWER_OF_TWO) {
            return this.f12414f;
        }
        if (size < this.f12414f) {
            return this.f12414f;
        }
        return size;
    }

    /* renamed from: b */
    private int m13198b(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode != Ints.MAX_POWER_OF_TWO) {
            return this.f12415g;
        }
        if (size < this.f12415g) {
            return this.f12415g;
        }
        return size;
    }

    public boolean isClickable() {
        return this.f12409a;
    }

    public void setIsClickable(boolean z) {
        this.f12409a = z;
    }

    public boolean onTouchEvent(@NonNull MotionEvent motionEvent) {
        boolean z = false;
        if (this.f12409a) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.f12421m = false;
                    this.f12410b = 2;
                    this.f12420l = new Rect(getLeft(), getTop(), getRight(), getBottom());
                    this.f12411c = System.currentTimeMillis();
                    invalidate();
                    break;
                case 1:
                    if (!this.f12421m) {
                        this.f12410b = 3;
                        if (!isChecked()) {
                            z = true;
                        }
                        setChecked(z);
                        this.f12411c = System.currentTimeMillis();
                        invalidate();
                        break;
                    }
                    break;
                case 2:
                    if (!this.f12420l.contains(getLeft() + ((int) motionEvent.getX()), getTop() + ((int) motionEvent.getY()))) {
                        this.f12421m = true;
                        this.f12410b = 1;
                        this.f12411c = System.currentTimeMillis();
                        invalidate();
                        break;
                    }
                    break;
                case 3:
                    this.f12410b = 1;
                    invalidate();
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    /* renamed from: c */
    private int m13199c(int i) {
        return Color.argb(Math.round(((float) Color.alpha(i)) * 0.2f), Color.red(i), Color.green(i), Color.blue(i));
    }

    protected void onDraw(@NonNull Canvas canvas) {
        int i = 0;
        super.onDraw(canvas);
        int i2 = isChecked() ? this.f12417i : 0;
        long currentTimeMillis = System.currentTimeMillis() - this.f12411c;
        switch (this.f12410b) {
            case 2:
                this.f12424p.setAlpha(255);
                if (currentTimeMillis < 200) {
                    i = Math.round((float) ((currentTimeMillis * ((long) this.f12418j)) / 200));
                } else {
                    i = this.f12418j;
                }
                postInvalidate();
                break;
            case 3:
                if (currentTimeMillis < 200) {
                    this.f12424p.setAlpha(Math.round((float) (((200 - currentTimeMillis) * 255) / 200)));
                    i = Math.round((float) (((200 - currentTimeMillis) * ((long) this.f12418j)) / 200));
                    if (isChecked()) {
                        i2 = Math.round((float) ((currentTimeMillis * ((long) this.f12417i)) / 200));
                    } else {
                        i2 = Math.round((float) (((200 - currentTimeMillis) * ((long) this.f12417i)) / 200));
                    }
                } else {
                    this.f12410b = 1;
                    this.f12424p.setAlpha(0);
                }
                postInvalidate();
                break;
        }
        if (isChecked()) {
            this.f12424p.setColor(m13199c(this.f12413e));
            this.f12422n.setColor(this.f12413e);
            this.f12422n.setStyle(Style.STROKE);
            this.f12422n.setStrokeWidth((float) this.f12419k);
        } else {
            this.f12424p.setColor(m13199c(this.f12412d));
            this.f12422n.setColor(this.f12412d);
            this.f12422n.setStyle(Style.STROKE);
            this.f12422n.setStrokeWidth((float) this.f12419k);
        }
        canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) i, this.f12424p);
        canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) this.f12416h, this.f12422n);
        canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) i2, this.f12423o);
    }
}
