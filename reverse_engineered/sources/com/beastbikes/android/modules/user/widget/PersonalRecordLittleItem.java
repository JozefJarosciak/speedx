package com.beastbikes.android.modules.user.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

public class PersonalRecordLittleItem extends LinearLayout {
    /* renamed from: a */
    private TextView f11951a;
    /* renamed from: b */
    private TextView f11952b;
    /* renamed from: c */
    private TextView f11953c;

    public PersonalRecordLittleItem(Context context) {
        super(context);
        m12702a(context);
    }

    public PersonalRecordLittleItem(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m12702a(context);
    }

    public PersonalRecordLittleItem(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12702a(context);
    }

    /* renamed from: a */
    private void m12702a(Context context) {
        inflate(context, C1373R.layout.layout_personal_record_little_item, this);
        this.f11951a = (TextView) findViewById(C1373R.id.tv_personal_record_little_item_label);
        this.f11952b = (TextView) findViewById(C1373R.id.tv_personal_record_little_item_value);
        this.f11953c = (TextView) findViewById(C1373R.id.tv_personal_record_little_item_unit);
    }

    public void setLabel(String str) {
        if (this.f11951a != null && !TextUtils.isEmpty(str)) {
            this.f11951a.setText(str);
        }
    }

    public void setValue(String str) {
        if (this.f11952b != null && !TextUtils.isEmpty(str)) {
            this.f11952b.setText(str);
        }
    }

    public void setUnit(String str) {
        if (this.f11953c != null && !TextUtils.isEmpty(str)) {
            this.f11953c.setText(str);
            this.f11953c.setVisibility(0);
        }
    }
}
