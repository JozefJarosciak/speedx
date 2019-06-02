package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: TwoItemsLineChartView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.k */
public class C2036k extends C2026c {
    /* renamed from: h */
    private TextView f9230h;
    /* renamed from: i */
    private TextView f9231i;
    /* renamed from: j */
    private TextView f9232j;
    /* renamed from: k */
    private TextView f9233k;

    public C2036k(Context context) {
        super(context);
    }

    public C2036k(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2036k(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3358a(Context context, AttributeSet attributeSet) {
        super.mo3358a(context, attributeSet);
        ((ViewStub) findViewById(C1373R.id.layout_cycling_data_two_items)).inflate();
        ((ViewStub) findViewById(C1373R.id.viewStub_chart_no_data)).inflate();
        this.f9230h = (TextView) findViewById(C1373R.id.textView_left_item_value);
        this.f9231i = (TextView) findViewById(C1373R.id.textView_left_item_label);
        this.f9232j = (TextView) findViewById(C1373R.id.textView_right_item_value);
        this.f9233k = (TextView) findViewById(C1373R.id.textView_right_item_label);
    }

    public void setLeftValue(CharSequence charSequence) {
        this.f9230h.setText(charSequence);
    }

    public void setLeftValue(int i) {
        this.f9230h.setText(i);
    }

    public void setLeftLabel(CharSequence charSequence) {
        this.f9231i.setText(charSequence);
    }

    public void setLeftLabel(int i) {
        this.f9231i.setText(i);
    }

    public void setRightValue(CharSequence charSequence) {
        this.f9232j.setText(charSequence);
    }

    public void setRightValue(int i) {
        this.f9232j.setText(i);
    }

    public void setRightLabel(CharSequence charSequence) {
        this.f9233k.setText(charSequence);
    }

    public void setRightLabel(int i) {
        this.f9233k.setText(i);
    }

    /* renamed from: a */
    public void m10486a(boolean z) {
        this.g = (RelativeLayout) findViewById(C1373R.id.relative_chart_no_data);
        this.g.setClickable(true);
        this.g.setVisibility(0);
        ImageView imageView = (ImageView) findViewById(C1373R.id.img_chart_no_data_icon);
        TextView textView = (TextView) findViewById(C1373R.id.textView_chart_no_data_function);
        TextView textView2 = (TextView) findViewById(C1373R.id.textView_chart_no_data_function_desc);
        TextView textView3 = (TextView) findViewById(C1373R.id.textView_chart_no_data_bottom_desc);
        if (z) {
            imageView.setImageResource(C1373R.drawable.ic_chart_no_heart_rate);
            textView.setText(C1373R.string.str_have_not_connect_heart_rate_device);
            textView3.setText(C1373R.string.str_heart_rate_only_support_desc);
            textView2.setText(C1373R.string.str_heart_rate_no_data_desc);
            return;
        }
        imageView.setImageResource(C1373R.drawable.ic_chart_no_cadence);
        textView.setText(C1373R.string.str_have_not_connect_cadence_device);
        textView3.setText(C1373R.string.str_cadence_only_support_desc);
        textView2.setText(C1373R.string.str_cadence_no_data_desc);
    }
}
