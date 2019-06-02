package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;

public class SlopePieBottomItemView extends LinearLayout {
    /* renamed from: a */
    private String f9064a;
    /* renamed from: b */
    private int f9065b;
    /* renamed from: c */
    private View f9066c;
    /* renamed from: d */
    private TextView f9067d;
    /* renamed from: e */
    private TextView f9068e;
    /* renamed from: f */
    private TextView f9069f;

    public SlopePieBottomItemView(Context context) {
        super(context);
        m10388a(context, null);
    }

    public SlopePieBottomItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m10388a(context, attributeSet);
    }

    public SlopePieBottomItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10388a(context, attributeSet);
    }

    /* renamed from: a */
    private void m10388a(Context context, AttributeSet attributeSet) {
        inflate(context, C1373R.layout.layout_slope_bottom_item, this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SlopeBottomItemView);
        this.f9064a = obtainStyledAttributes.getString(0);
        this.f9065b = obtainStyledAttributes.getColor(1, -16711936);
        this.f9066c = findViewById(C1373R.id.view_slope_bottom_item_color);
        this.f9067d = (TextView) findViewById(C1373R.id.textView_slope_bottom_item_desc);
        this.f9068e = (TextView) findViewById(C1373R.id.textView_slope_bottom_item_percent);
        this.f9069f = (TextView) findViewById(C1373R.id.textView_slope_bottom_item_average_speed);
        ((GradientDrawable) this.f9066c.getBackground()).setColor(this.f9065b);
        this.f9067d.setText(this.f9064a == null ? "" : this.f9064a);
    }

    public void setSlopePercent(CharSequence charSequence) {
        this.f9068e.setText(charSequence);
    }

    public void setSlopePercent(int i) {
        this.f9068e.setText(i);
    }

    public void setSlopAverageSpeed(CharSequence charSequence) {
        this.f9069f.setText(charSequence);
    }

    public void setSlopAverageSpeed(int i) {
        this.f9069f.setText(i);
    }
}
