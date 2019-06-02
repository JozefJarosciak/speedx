package com.beastbikes.android.ble.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;

public class SpeedForceSettingView extends LinearLayout {
    /* renamed from: a */
    private boolean f8050a;
    /* renamed from: b */
    private TextView f8051b;
    /* renamed from: c */
    private String f8052c;
    /* renamed from: d */
    private TextView f8053d;
    /* renamed from: e */
    private String f8054e;
    /* renamed from: f */
    private View f8055f;
    /* renamed from: g */
    private boolean f8056g;
    /* renamed from: h */
    private View f8057h;
    /* renamed from: i */
    private boolean f8058i;
    /* renamed from: j */
    private ImageView f8059j;

    public SpeedForceSettingView(Context context) {
        super(context);
        m9370a(context, null, 0);
    }

    public SpeedForceSettingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m9370a(context, attributeSet, 0);
    }

    public SpeedForceSettingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9370a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m9370a(Context context, AttributeSet attributeSet, int i) {
        inflate(context, C1373R.layout.speed_force_setting_item, this);
        this.f8051b = (TextView) findViewById(C1373R.id.speed_force_setting_item_label);
        this.f8053d = (TextView) findViewById(C1373R.id.speed_force_setting_item_value);
        this.f8055f = findViewById(C1373R.id.speed_force_setting_item_dot);
        this.f8057h = findViewById(C1373R.id.speed_force_setting_item_bottom_line);
        this.f8059j = (ImageView) findViewById(C1373R.id.speed_force_setting_arrow);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.SpeedForceSettingView, i, 0);
        this.f8050a = obtainStyledAttributes.getBoolean(1, true);
        this.f8052c = obtainStyledAttributes.getString(0);
        this.f8054e = obtainStyledAttributes.getString(2);
        this.f8056g = obtainStyledAttributes.getBoolean(3, false);
        this.f8058i = obtainStyledAttributes.getBoolean(4, false);
        m9371b();
    }

    /* renamed from: b */
    private void m9371b() {
        int i;
        int i2 = 0;
        if (this.f8050a) {
            this.f8051b.setTextColor(Color.parseColor("#ffffff"));
        } else {
            this.f8051b.setTextColor(Color.parseColor("#343434"));
        }
        this.f8051b.setText(this.f8052c);
        this.f8053d.setText(this.f8054e);
        View view = this.f8055f;
        if (this.f8056g) {
            i = 0;
        } else {
            i = 8;
        }
        view.setVisibility(i);
        View view2 = this.f8057h;
        if (!this.f8058i) {
            i2 = 8;
        }
        view2.setVisibility(i2);
    }

    public void setEnable(boolean z) {
        this.f8050a = z;
        if (this.f8050a) {
            this.f8051b.setTextColor(Color.parseColor("#ffffff"));
            this.f8059j.setImageResource(C1373R.drawable.ic_club_arrow_right);
            return;
        }
        this.f8051b.setTextColor(Color.parseColor("#343434"));
        this.f8059j.setImageResource(C1373R.drawable.ic_arrow_right_icon);
    }

    /* renamed from: a */
    public boolean m9372a() {
        return this.f8050a;
    }

    public void setLabel(String str) {
        this.f8052c = str;
        this.f8051b.setText(this.f8052c);
    }

    public void setLabel(int i) {
        this.f8052c = getResources().getString(i);
        this.f8051b.setText(this.f8052c);
    }

    public void setValue(String str) {
        this.f8054e = str;
        this.f8053d.setText(this.f8054e);
    }

    public void setValue(int i) {
        this.f8054e = getResources().getString(i);
        this.f8053d.setText(this.f8054e);
    }

    public void setDotVisible(boolean z) {
        this.f8056g = z;
        this.f8055f.setVisibility(this.f8056g ? 0 : 8);
    }

    public void setLineVisible(boolean z) {
        this.f8058i = z;
        this.f8057h.setVisibility(this.f8058i ? 0 : 8);
    }
}
