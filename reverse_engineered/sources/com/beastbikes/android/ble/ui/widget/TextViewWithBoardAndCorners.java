package com.beastbikes.android.ble.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;

public class TextViewWithBoardAndCorners extends TextView {
    /* renamed from: a */
    private int f8060a;
    /* renamed from: b */
    private float f8061b;
    /* renamed from: c */
    private int f8062c;

    public TextViewWithBoardAndCorners(Context context) {
        super(context);
        m9373a(context, null, 0);
    }

    public TextViewWithBoardAndCorners(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m9373a(context, attributeSet, 0);
    }

    public TextViewWithBoardAndCorners(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9373a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m9373a(Context context, AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.TextViewWithBoardAndCorners, i, 0);
        this.f8060a = obtainStyledAttributes.getColor(0, -1);
        this.f8061b = obtainStyledAttributes.getDimension(1, 0.0f);
        this.f8062c = obtainStyledAttributes.getColor(2, 0);
        obtainStyledAttributes.recycle();
        setBackgroundDrawable(getResources().getDrawable(C1373R.drawable.bg_white_with_black_board_and_corners));
        GradientDrawable gradientDrawable = (GradientDrawable) getBackground();
        gradientDrawable.setStroke(1, this.f8060a);
        gradientDrawable.setCornerRadius(this.f8061b);
        gradientDrawable.setColor(this.f8062c);
    }

    public void setBoardColor(int i) {
        this.f8060a = i;
        ((GradientDrawable) getBackground()).setStroke(1, this.f8060a);
    }

    public void setSolidColor(int i) {
        this.f8062c = i;
        ((GradientDrawable) getBackground()).setColor(this.f8062c);
    }

    public void setRadius(float f) {
        this.f8061b = f;
        ((GradientDrawable) getBackground()).setColor(this.f8062c);
    }
}
