package com.beastbikes.android.ble.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;

public class HeartRateIntervalItemView extends RelativeLayout {
    /* renamed from: a */
    private TextView f8025a;
    /* renamed from: b */
    private TextView f8026b;

    public HeartRateIntervalItemView(Context context) {
        super(context);
        m9361a(context, null, 0);
    }

    public HeartRateIntervalItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m9361a(context, attributeSet, 0);
    }

    public HeartRateIntervalItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9361a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m9361a(Context context, AttributeSet attributeSet, int i) {
        inflate(context, C1373R.layout.setting_heart_rate_item, this);
        this.f8025a = (TextView) findViewById(C1373R.id.setting_heart_rate_label);
        this.f8026b = (TextView) findViewById(C1373R.id.setting_heart_rate_value);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.HeartRateIntervalItemView);
        CharSequence string = obtainStyledAttributes.getString(0);
        int color = obtainStyledAttributes.getColor(1, ViewCompat.MEASURED_STATE_MASK);
        obtainStyledAttributes.recycle();
        setBackgroundColor(color);
        this.f8025a.setText(string);
    }

    public void setHeartRateValue(String str) {
        this.f8026b.setText(str);
    }

    public void setHeartRateLabel(String str) {
        this.f8025a.setText(str);
    }
}
