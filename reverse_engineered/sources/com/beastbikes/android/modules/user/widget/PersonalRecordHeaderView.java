package com.beastbikes.android.modules.user.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

public class PersonalRecordHeaderView extends LinearLayout {
    /* renamed from: a */
    private TextView f11949a;
    /* renamed from: b */
    private TextView f11950b;

    public PersonalRecordHeaderView(Context context) {
        super(context);
        m12701a();
    }

    public PersonalRecordHeaderView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m12701a();
    }

    public PersonalRecordHeaderView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12701a();
    }

    /* renamed from: a */
    private void m12701a() {
        inflate(getContext(), C1373R.layout.personal_record_activity_header_item, this);
        this.f11949a = (TextView) findViewById(C1373R.id.tv_personal_record_activity_header_item_value);
        this.f11950b = (TextView) findViewById(C1373R.id.tv_personal_record_activity_header_item_unit);
    }

    public void setValue(String str) {
        if (this.f11949a != null && !TextUtils.isEmpty(str)) {
            this.f11949a.setText(str);
        }
    }

    public void setLabel(String str) {
        if (this.f11950b != null && !TextUtils.isEmpty(str)) {
            this.f11950b.setText(str);
        }
    }
}
