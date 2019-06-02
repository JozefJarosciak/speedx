package com.beastbikes.android.modules.cycling.activity.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: CyclingItemView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.widget.a */
public class C2037a extends LinearLayout {
    /* renamed from: a */
    private TextView f9270a;
    /* renamed from: b */
    private TextView f9271b;
    /* renamed from: c */
    private TextView f9272c;
    /* renamed from: d */
    private String f9273d;
    /* renamed from: e */
    private String f9274e;
    /* renamed from: f */
    private String f9275f;

    public C2037a(Context context, String str, String str2, String str3) {
        super(context);
        this.f9273d = str;
        this.f9274e = str2;
        this.f9275f = str3;
        m10499a(context);
    }

    public C2037a(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m10499a(context);
    }

    public C2037a(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10499a(context);
    }

    /* renamed from: a */
    private void m10499a(Context context) {
        C2037a.inflate(context, C1373R.layout.layout_cycling_data_settings_item, this);
        this.f9270a = (TextView) findViewById(C1373R.id.tv_cycling_data_settings_item_desc);
        this.f9271b = (TextView) findViewById(C1373R.id.tv_cycling_data_settings_item_value);
        this.f9272c = (TextView) findViewById(C1373R.id.tv_cycling_data_settings_item_unit);
        this.f9270a.setText(this.f9274e);
        this.f9271b.setText(this.f9273d);
        if (!TextUtils.isEmpty(this.f9275f)) {
            this.f9272c.setText("(" + this.f9275f + ")");
        }
    }

    /* renamed from: a */
    public void m10500a(float f, float f2, float f3) {
        this.f9270a.setTextSize(2, f);
        this.f9271b.setTextSize(2, f2);
        this.f9272c.setTextSize(2, f3);
    }
}
