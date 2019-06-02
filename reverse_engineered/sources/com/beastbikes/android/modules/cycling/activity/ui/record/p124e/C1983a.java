package com.beastbikes.android.modules.cycling.activity.ui.record.p124e;

import android.content.Context;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.ui.record.p125f.C1988b;
import com.beastbikes.android.modules.cycling.activity.ui.record.p125f.C1989c;
import com.beastbikes.android.utils.C2555d;
import com.github.mikephil.charting.components.C1982g;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3279e;

/* compiled from: BarChartMarker */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.e.a */
public class C1983a extends C1982g {
    /* renamed from: a */
    private TextView f8916a = ((TextView) findViewById(C1373R.id.tv_bar_chart_marker_view1));
    /* renamed from: b */
    private TextView f8917b = ((TextView) findViewById(C1373R.id.tv_bar_chart_marker_view2));
    /* renamed from: c */
    private TextView f8918c = ((TextView) findViewById(C1373R.id.tv_bar_chart_marker_view3));
    /* renamed from: d */
    private String[] f8919d;

    public C1983a(Context context, int i) {
        super(context, C1373R.layout.layout_bar_marker);
        this.f8919d = context.getResources().getStringArray(C1373R.array.cycling_report_power_bar_label);
    }

    /* renamed from: a */
    public void mo3336a(Entry entry, C3213d c3213d) {
        Object h = entry.m15449h();
        if (h instanceof C1988b) {
            C1988b c1988b = (C1988b) entry.m15449h();
            this.f8916a.setText(c1988b.m10234b());
            this.f8917b.setText(C2555d.m12802b(c1988b.m10233a()));
            this.f8918c.setText(c1988b.m10235c() + "%");
        } else if (h instanceof C1989c) {
            C1989c c1989c = (C1989c) entry.m15449h();
            this.f8916a.setText(this.f8919d[c1989c.m10237b()]);
            this.f8917b.setText(C2555d.m12802b(c1989c.m10236a()));
            this.f8918c.setText(c1989c.m10238c() + "%");
        }
    }

    public C3279e getOffset() {
        return new C3279e((float) (-(getWidth() / 2)), (float) (-getHeight()));
    }
}
