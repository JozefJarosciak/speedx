package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;

public class HeartRateBottomItemView extends LinearLayout {
    /* renamed from: a */
    private TextView f9016a;
    /* renamed from: b */
    private TextView f9017b;
    /* renamed from: c */
    private View f9018c;
    /* renamed from: d */
    private boolean f9019d;

    public HeartRateBottomItemView(Context context) {
        super(context);
        m10332a(context, null, 0);
    }

    public HeartRateBottomItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m10332a(context, attributeSet, 0);
    }

    public HeartRateBottomItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10332a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m10332a(Context context, AttributeSet attributeSet, int i) {
        CharSequence charSequence;
        inflate(context, C1373R.layout.layout_heart_rate_bottom_item, this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HeartRateBottomItemView);
        String string = obtainStyledAttributes.getString(0);
        String string2 = obtainStyledAttributes.getString(1);
        int color = obtainStyledAttributes.getColor(2, ViewCompat.MEASURED_STATE_MASK);
        obtainStyledAttributes.recycle();
        this.f9016a = (TextView) findViewById(C1373R.id.textView_heart_rate_range);
        this.f9017b = (TextView) findViewById(C1373R.id.textView_heart_rate_desc);
        this.f9018c = findViewById(C1373R.id.view_heart_rate_color);
        TextView textView = this.f9016a;
        if (string == null) {
            charSequence = "";
        } else {
            Object obj = string;
        }
        textView.setText(charSequence);
        TextView textView2 = this.f9017b;
        if (string2 == null) {
            charSequence = "";
        } else {
            obj = string2;
        }
        textView2.setText(charSequence);
        this.f9018c.setBackgroundColor(color);
    }

    public void setHeartRateRange(CharSequence charSequence) {
        this.f9016a.setText(charSequence);
    }

    public void setHeartRateRange(int i) {
        this.f9016a.setText(i);
    }

    public void setHeartRateDesc(CharSequence charSequence) {
        this.f9017b.setText(charSequence);
    }

    public void setHeartRateDesc(int i) {
        this.f9017b.setText(i);
    }

    public void setHeartRateColor(int i) {
        this.f9018c.setBackgroundColor(i);
    }

    public void setSeleted(boolean z) {
        this.f9019d = z;
    }
}
