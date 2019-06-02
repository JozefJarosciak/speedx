package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;

/* compiled from: SixItemsLineChartView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.i */
public class C2034i extends C2026c {
    /* renamed from: h */
    private LinearLayout f9209h;
    /* renamed from: i */
    private TextView f9210i;
    /* renamed from: j */
    private TextView f9211j;
    /* renamed from: k */
    private TextView f9212k;
    /* renamed from: l */
    private TextView f9213l;
    /* renamed from: m */
    private TextView f9214m;
    /* renamed from: n */
    private TextView f9215n;
    /* renamed from: o */
    private TextView f9216o;
    /* renamed from: p */
    private TextView f9217p;
    /* renamed from: q */
    private TextView f9218q;
    /* renamed from: r */
    private TextView f9219r;
    /* renamed from: s */
    private TextView f9220s;
    /* renamed from: t */
    private TextView f9221t;
    /* renamed from: u */
    private LinearLayout f9222u;
    /* renamed from: v */
    private TextView f9223v;

    public C2034i(Context context) {
        super(context);
    }

    public C2034i(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2034i(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3358a(Context context, AttributeSet attributeSet) {
        super.mo3358a(context, attributeSet);
        ((ViewStub) findViewById(C1373R.id.layout_cycling_data_with_six_items)).inflate();
        ((ViewStub) findViewById(C1373R.id.viewStub_chart_no_data)).inflate();
        ((ViewStub) findViewById(C1373R.id.layout_cycling_data_with_one_items)).inflate();
        this.f9209h = (LinearLayout) findViewById(C1373R.id.linear_cycling_chart_top_with_six_item);
        this.f9210i = (TextView) findViewById(C1373R.id.textView_left_top_value);
        this.f9211j = (TextView) findViewById(C1373R.id.textView_left_top_label);
        this.f9212k = (TextView) findViewById(C1373R.id.textView_middle_top_value);
        this.f9213l = (TextView) findViewById(C1373R.id.textView_middle_top_label);
        this.f9214m = (TextView) findViewById(C1373R.id.textView_right_top_value);
        this.f9215n = (TextView) findViewById(C1373R.id.textView_right_top_label);
        this.f9216o = (TextView) findViewById(C1373R.id.textView_left_bottom_value);
        this.f9217p = (TextView) findViewById(C1373R.id.textView_left_bottom_label);
        this.f9218q = (TextView) findViewById(C1373R.id.textView_middle_bottom_value);
        this.f9219r = (TextView) findViewById(C1373R.id.textView_middle_bottom_label);
        this.f9220s = (TextView) findViewById(C1373R.id.textView_right_bottom_value);
        this.f9221t = (TextView) findViewById(C1373R.id.textView_right_bottom_label);
        this.f9222u = (LinearLayout) findViewById(C1373R.id.linear_cycling_chart_top_with_one_item);
        this.f9223v = (TextView) findViewById(C1373R.id.textView_top_item_value);
        ((TextView) findViewById(C1373R.id.textView_top_item_label)).setText(context.getString(C1373R.string.str_avg_power).toUpperCase() + "(W)");
        this.f9211j.setText(context.getString(C1373R.string.str_max_20_minutes_power) + "(W)");
        this.f9213l.setText(context.getString(C1373R.string.str_standard_power) + "(W)");
        this.f9215n.setText(context.getString(C1373R.string.str_avg_power) + "(W)");
        this.f9217p.setText(C1373R.string.str_training_intensity);
        this.f9219r.setText(C1373R.string.str_training_volume);
        this.f9221t.setText("FTP(W)");
        setVirtual(false);
    }

    public void setVirtual(boolean z) {
        if (z) {
            this.f9222u.setVisibility(0);
            this.f9209h.setVisibility(8);
            return;
        }
        this.f9222u.setVisibility(8);
        this.f9209h.setVisibility(0);
    }

    public void setLeftTopValue(CharSequence charSequence) {
        this.f9210i.setText(charSequence);
    }

    public void setLeftTopValue(int i) {
        this.f9210i.setText(i);
    }

    public void setLeftTopLabel(CharSequence charSequence) {
        this.f9211j.setText(charSequence);
    }

    public void setLeftTopLabel(int i) {
        this.f9211j.setText(i);
    }

    public void setMiddleTopValue(CharSequence charSequence) {
        this.f9212k.setText(charSequence);
    }

    public void setMiddleTopValue(int i) {
        this.f9212k.setText(i);
    }

    public void setMiddleTopLabel(CharSequence charSequence) {
        this.f9213l.setText(charSequence);
    }

    public void setMiddleTopLabel(int i) {
        this.f9213l.setText(i);
    }

    public void setRightTopValue(CharSequence charSequence) {
        this.f9214m.setText(charSequence);
    }

    public void setRightTopValue(int i) {
        this.f9214m.setText(i);
    }

    public void setRightTopLabel(CharSequence charSequence) {
        this.f9215n.setText(charSequence);
    }

    public void setRightTopLabel(int i) {
        this.f9215n.setText(i);
    }

    public void setLeftBottomValue(CharSequence charSequence) {
        this.f9216o.setText(charSequence);
    }

    public void setLeftBottomValue(int i) {
        this.f9216o.setText(i);
    }

    public void setLeftBottomLabel(CharSequence charSequence) {
        this.f9217p.setText(charSequence);
    }

    public void setLeftBottomLabel(int i) {
        this.f9217p.setText(i);
    }

    public void setMiddleBottomValue(CharSequence charSequence) {
        this.f9218q.setText(charSequence);
    }

    public void setMiddleBottomValue(int i) {
        this.f9218q.setText(i);
    }

    public void setMiddleBottomLabel(CharSequence charSequence) {
        this.f9219r.setText(charSequence);
    }

    public void setMiddleBottomLabel(int i) {
        this.f9219r.setText(i);
    }

    public void setRightBottomValue(CharSequence charSequence) {
        this.f9220s.setText(charSequence);
    }

    public void setRightBottomValue(int i) {
        this.f9220s.setText(i);
    }

    public void setRightBottomLabel(CharSequence charSequence) {
        this.f9221t.setText(charSequence);
    }

    public void setRightBottomLabel(int i) {
        this.f9221t.setText(i);
    }

    public void setTopItemValue(CharSequence charSequence) {
        this.f9223v.setText(charSequence);
    }

    /* renamed from: b */
    public void mo3359b() {
        this.g = (RelativeLayout) findViewById(C1373R.id.relative_chart_no_data);
        this.g.setVisibility(0);
        TextView textView = (TextView) findViewById(C1373R.id.textView_chart_no_data_function);
        TextView textView2 = (TextView) findViewById(C1373R.id.textView_chart_no_data_function_desc);
        TextView textView3 = (TextView) findViewById(C1373R.id.textView_chart_no_data_bottom_desc);
        ((ImageView) findViewById(C1373R.id.img_chart_no_data_icon)).setImageResource(C1373R.drawable.ic_chart_no_power);
        textView.setText(C1373R.string.str_cycling_report_empty_data_title_power);
        textView3.setText(C1373R.string.str_cycling_report_empty_data_footersubtitle_power);
        textView2.setText(C1373R.string.str_cycling_report_empty_data_subtitle_power);
    }
}
