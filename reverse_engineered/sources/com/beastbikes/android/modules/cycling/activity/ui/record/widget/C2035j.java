package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: ThreeItemsLineChartView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.j */
public class C2035j extends C2026c {
    /* renamed from: h */
    private TextView f9224h;
    /* renamed from: i */
    private TextView f9225i;
    /* renamed from: j */
    private TextView f9226j;
    /* renamed from: k */
    private TextView f9227k;
    /* renamed from: l */
    private TextView f9228l;
    /* renamed from: m */
    private TextView f9229m;

    public C2035j(Context context) {
        super(context);
    }

    public C2035j(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2035j(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3358a(Context context, AttributeSet attributeSet) {
        super.mo3358a(context, attributeSet);
        ((ViewStub) findViewById(C1373R.id.layout_cycling_data_three_items)).inflate();
        this.f9224h = (TextView) findViewById(C1373R.id.textView_left_value);
        this.f9225i = (TextView) findViewById(C1373R.id.textView_left_label);
        this.f9226j = (TextView) findViewById(C1373R.id.textView_middle_value);
        this.f9227k = (TextView) findViewById(C1373R.id.textView_middle_label);
        this.f9228l = (TextView) findViewById(C1373R.id.textView_right_value);
        this.f9229m = (TextView) findViewById(C1373R.id.textView_right_label);
    }

    public void setLeftValue(CharSequence charSequence) {
        this.f9224h.setText(charSequence);
    }

    public void setLeftValue(int i) {
        this.f9224h.setText(i);
    }

    public void setLeftLabel(CharSequence charSequence) {
        this.f9225i.setText(charSequence);
    }

    public void setLeftLabel(int i) {
        this.f9225i.setText(i);
    }

    public void setMiddleValue(CharSequence charSequence) {
        this.f9226j.setText(charSequence);
    }

    public void setMiddleValue(int i) {
        this.f9226j.setText(i);
    }

    public void setMiddleLabel(CharSequence charSequence) {
        this.f9227k.setText(charSequence);
    }

    public void setMiddleLabel(int i) {
        this.f9227k.setText(i);
    }

    public void setRightValue(CharSequence charSequence) {
        this.f9228l.setText(charSequence);
    }

    public void setRightValue(int i) {
        this.f9228l.setText(i);
    }

    public void setRightLabel(CharSequence charSequence) {
        this.f9229m.setText(charSequence);
    }

    public void setRightLabel(int i) {
        this.f9229m.setText(i);
    }
}
