package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ViewStub;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.ui.record.p122b.C1969a;
import com.beastbikes.android.modules.cycling.activity.ui.record.p124e.C1983a;
import com.github.mikephil.charting.data.BarEntry;
import java.util.ArrayList;

/* compiled from: HeartRateBarChartView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.f */
public class C2031f extends C2025a {
    /* renamed from: d */
    private TextView f9190d;
    /* renamed from: e */
    private TextView f9191e;
    /* renamed from: f */
    private TextView f9192f;
    /* renamed from: g */
    private TextView f9193g;

    public C2031f(Context context) {
        super(context);
    }

    public C2031f(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2031f(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* renamed from: a */
    protected void mo3358a(Context context, AttributeSet attributeSet) {
        super.mo3358a(context, attributeSet);
        ((ViewStub) findViewById(C1373R.id.layout_cycling_data_two_items)).inflate();
        this.f9191e = (TextView) findViewById(C1373R.id.textView_left_item_value);
        this.f9190d = (TextView) findViewById(C1373R.id.textView_left_item_label);
        this.f9192f = (TextView) findViewById(C1373R.id.textView_right_item_value);
        this.f9193g = (TextView) findViewById(C1373R.id.textView_right_item_label);
        this.f9190d.setText((getContext().getString(C1373R.string.str_average_heart_rate) + "(BPM)").toUpperCase());
        this.f9193g.setText((getContext().getString(C1373R.string.str_max_heart_rate) + "(BPM)").toUpperCase());
        m10451f();
        setMark(new C1983a(getContext(), 0));
    }

    public void setData(ArrayList<BarEntry> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            m10460a(false);
        }
        m10459a((ArrayList) arrayList, getColors());
    }

    private ArrayList<Integer> getColors() {
        ArrayList<Integer> arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(-30822));
        arrayList.add(Integer.valueOf(-65494));
        arrayList.add(Integer.valueOf(-1703898));
        arrayList.add(Integer.valueOf(-3407325));
        arrayList.add(Integer.valueOf(-7798761));
        return arrayList;
    }

    public void setMaxHeartRate(int i) {
        SparseArray sparseArray = new SparseArray();
        int i2 = (int) (((double) i) * 0.66d);
        int i3 = (int) (((double) i) * 0.73d);
        int i4 = (int) (((double) i) * 0.84d);
        int i5 = (int) (((double) i) * 0.91d);
        sparseArray.put(0, "< " + i2);
        sparseArray.put(1, i2 + " - " + i3);
        sparseArray.put(2, i3 + " - " + i4);
        sparseArray.put(3, i4 + " - " + i5);
        sparseArray.put(4, "> " + i5);
        setXLabelFormatter(new C1969a(sparseArray));
    }

    public void setLeftValue(CharSequence charSequence) {
        this.f9191e.setText(charSequence);
    }

    public void setLeftValue(int i) {
        this.f9191e.setText(i);
    }

    public void setLeftLabel(CharSequence charSequence) {
        this.f9190d.setText(charSequence);
    }

    public void setLeftLabel(int i) {
        this.f9190d.setText(i);
    }

    public void setRightValue(CharSequence charSequence) {
        this.f9192f.setText(charSequence);
    }

    public void setRightValue(int i) {
        this.f9192f.setText(i);
    }

    public void setRightLabel(CharSequence charSequence) {
        this.f9193g.setText(charSequence);
    }

    public void setRightLabel(int i) {
        this.f9193g.setText(i);
    }
}
