package com.beastbikes.android.modules.cycling.activity.ui.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: CyclingItemViewForApp */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.widget.b */
public class C2038b extends LinearLayout {
    /* renamed from: a */
    private TextView f9276a;
    /* renamed from: b */
    private TextView f9277b;
    /* renamed from: c */
    private TextView f9278c;
    /* renamed from: d */
    private int f9279d;
    /* renamed from: e */
    private String f9280e;
    /* renamed from: f */
    private String f9281f;
    /* renamed from: g */
    private String f9282g;

    public C2038b(Context context, int i, String str, String str2, String str3) {
        super(context);
        this.f9279d = i;
        this.f9280e = str;
        this.f9281f = str2;
        this.f9282g = str3;
        m10501a(context);
    }

    public C2038b(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m10501a(context);
    }

    public C2038b(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10501a(context);
    }

    /* renamed from: a */
    private void m10501a(Context context) {
        C2038b.inflate(context, C1373R.layout.layout_cycling_data_settings_item_for_app, this);
        this.f9276a = (TextView) findViewById(C1373R.id.tv_cycling_data_settings_item_desc);
        this.f9277b = (TextView) findViewById(C1373R.id.tv_cycling_data_settings_item_value);
        this.f9278c = (TextView) findViewById(C1373R.id.tv_cycling_data_settings_item_unit);
        this.f9276a.setText(this.f9281f);
        this.f9277b.setText(this.f9280e);
        if (!TextUtils.isEmpty(this.f9282g)) {
            this.f9278c.setText("(" + this.f9282g + ")");
        }
    }

    /* renamed from: a */
    public void m10502a(float f, float f2, float f3) {
        this.f9276a.setTextSize(2, f);
        this.f9277b.setTextSize(2, f2);
        this.f9278c.setTextSize(2, f3);
    }

    /* renamed from: a */
    public void m10503a(String str) {
        this.f9277b.setText(str);
    }

    public int getPosition() {
        return this.f9279d;
    }

    public String getDesc() {
        return this.f9281f;
    }
}
