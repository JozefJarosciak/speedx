package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.ui.record.p122b.C1974f;
import com.beastbikes.android.modules.cycling.activity.ui.record.p124e.C1983a;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;

/* compiled from: PowerBarChartView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.h */
public class C2033h extends C2025a {
    /* renamed from: d */
    private LinearLayout f9194d;
    /* renamed from: e */
    private TextView f9195e;
    /* renamed from: f */
    private TextView f9196f;
    /* renamed from: g */
    private TextView f9197g;
    /* renamed from: h */
    private TextView f9198h;
    /* renamed from: i */
    private TextView f9199i;
    /* renamed from: j */
    private TextView f9200j;
    /* renamed from: k */
    private TextView f9201k;
    /* renamed from: l */
    private TextView f9202l;
    /* renamed from: m */
    private TextView f9203m;
    /* renamed from: n */
    private TextView f9204n;
    /* renamed from: o */
    private TextView f9205o;
    /* renamed from: p */
    private TextView f9206p;
    /* renamed from: q */
    private LinearLayout f9207q;
    /* renamed from: r */
    private TextView f9208r;

    public C2033h(Context context) {
        super(context);
    }

    public C2033h(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2033h(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3358a(Context context, AttributeSet attributeSet) {
        super.mo3358a(context, attributeSet);
        ((ViewStub) findViewById(C1373R.id.layout_cycling_data_with_six_items)).inflate();
        ((ViewStub) findViewById(C1373R.id.layout_cycling_data_with_one_items)).inflate();
        this.f9194d = (LinearLayout) findViewById(C1373R.id.linear_cycling_chart_top_with_six_item);
        this.f9195e = (TextView) findViewById(C1373R.id.textView_left_top_value);
        this.f9196f = (TextView) findViewById(C1373R.id.textView_left_top_label);
        this.f9197g = (TextView) findViewById(C1373R.id.textView_middle_top_value);
        this.f9198h = (TextView) findViewById(C1373R.id.textView_middle_top_label);
        this.f9199i = (TextView) findViewById(C1373R.id.textView_right_top_value);
        this.f9200j = (TextView) findViewById(C1373R.id.textView_right_top_label);
        this.f9201k = (TextView) findViewById(C1373R.id.textView_left_bottom_value);
        this.f9202l = (TextView) findViewById(C1373R.id.textView_left_bottom_label);
        this.f9203m = (TextView) findViewById(C1373R.id.textView_middle_bottom_value);
        this.f9204n = (TextView) findViewById(C1373R.id.textView_middle_bottom_label);
        this.f9205o = (TextView) findViewById(C1373R.id.textView_right_bottom_value);
        this.f9206p = (TextView) findViewById(C1373R.id.textView_right_bottom_label);
        this.f9207q = (LinearLayout) findViewById(C1373R.id.linear_cycling_chart_top_with_one_item);
        this.f9208r = (TextView) findViewById(C1373R.id.textView_top_item_value);
        ((TextView) findViewById(C1373R.id.textView_top_item_label)).setText(context.getString(C1373R.string.str_avg_power).toUpperCase() + "(W)");
        this.f9196f.setText(context.getString(C1373R.string.str_max_20_minutes_power) + "(W)");
        this.f9198h.setText(context.getString(C1373R.string.str_standard_power) + "(W)");
        this.f9200j.setText(context.getString(C1373R.string.str_avg_power) + "(W)");
        this.f9202l.setText(C1373R.string.str_training_intensity);
        this.f9204n.setText(C1373R.string.str_training_volume);
        this.f9206p.setText("FTP(W)");
        m10451f();
        setXLabelFormatter(new C1974f());
        setMark(new C1983a(getContext(), 0));
        setVirtual(false);
    }

    public void setVirtual(boolean z) {
        if (z) {
            this.f9207q.setVisibility(0);
            this.f9194d.setVisibility(8);
            return;
        }
        this.f9207q.setVisibility(8);
        this.f9194d.setVisibility(0);
    }

    public void setData(ArrayList<BarEntry> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            m10460a(false);
        }
        m10458a((ArrayList) arrayList, this.a);
    }

    public void setLeftTopValue(CharSequence charSequence) {
        this.f9195e.setText(charSequence);
    }

    public void setLeftTopValue(int i) {
        this.f9195e.setText(i);
    }

    public void setLeftTopLabel(CharSequence charSequence) {
        this.f9196f.setText(charSequence);
    }

    public void setLeftTopLabel(int i) {
        this.f9196f.setText(i);
    }

    public void setMiddleTopValue(CharSequence charSequence) {
        this.f9197g.setText(charSequence);
    }

    public void setMiddleTopValue(int i) {
        this.f9197g.setText(i);
    }

    public void setMiddleTopLabel(CharSequence charSequence) {
        this.f9198h.setText(charSequence);
    }

    public void setMiddleTopLabel(int i) {
        this.f9198h.setText(i);
    }

    public void setRightTopValue(CharSequence charSequence) {
        this.f9199i.setText(charSequence);
    }

    public void setRightTopValue(int i) {
        this.f9199i.setText(i);
    }

    public void setRightTopLabel(CharSequence charSequence) {
        this.f9200j.setText(charSequence);
    }

    public void setRightTopLabel(int i) {
        this.f9200j.setText(i);
    }

    public void setLeftBottomValue(CharSequence charSequence) {
        this.f9201k.setText(charSequence);
    }

    public void setLeftBottomValue(int i) {
        this.f9201k.setText(i);
    }

    public void setLeftBottomLabel(CharSequence charSequence) {
        this.f9202l.setText(charSequence);
    }

    public void setLeftBottomLabel(int i) {
        this.f9202l.setText(i);
    }

    public void setMiddleBottomValue(CharSequence charSequence) {
        this.f9203m.setText(charSequence);
    }

    public void setMiddleBottomValue(int i) {
        this.f9203m.setText(i);
    }

    public void setMiddleBottomLabel(CharSequence charSequence) {
        this.f9204n.setText(charSequence);
    }

    public void setMiddleBottomLabel(int i) {
        this.f9204n.setText(i);
    }

    public void setRightBottomValue(CharSequence charSequence) {
        this.f9205o.setText(charSequence);
    }

    public void setRightBottomValue(int i) {
        this.f9205o.setText(i);
    }

    public void setRightBottomLabel(CharSequence charSequence) {
        this.f9206p.setText(charSequence);
    }

    public void setRightBottomLabel(int i) {
        this.f9206p.setText(i);
    }

    public void setTopItemValue(CharSequence charSequence) {
        this.f9208r.setText(charSequence);
    }
}
