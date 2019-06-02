package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;
import com.beastbikes.android.modules.cycling.activity.ui.record.CyclingReportHorizontalActivity;

/* compiled from: BaseCyclingChartView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.b */
public abstract class C2024b extends LinearLayout implements OnClickListener {
    /* renamed from: a */
    protected int f9150a;
    /* renamed from: b */
    protected String f9151b;
    /* renamed from: c */
    protected String f9152c;
    /* renamed from: d */
    private String f9153d;
    /* renamed from: e */
    private View f9154e;
    /* renamed from: f */
    private TextView f9155f;
    /* renamed from: g */
    private int f9156g;
    /* renamed from: h */
    private TextView f9157h;
    /* renamed from: i */
    private ImageView f9158i;
    /* renamed from: j */
    private RelativeLayout f9159j;
    /* renamed from: k */
    private View f9160k;
    /* renamed from: l */
    private TextView f9161l;
    /* renamed from: m */
    private TextView f9162m;
    /* renamed from: n */
    private C1956a f9163n;

    /* compiled from: BaseCyclingChartView */
    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.b$a */
    public interface C1956a {
        /* renamed from: a */
        void mo3298a(View view, View view2);
    }

    public C2024b(Context context) {
        super(context);
        mo3358a(context, null);
    }

    public C2024b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mo3358a(context, attributeSet);
    }

    public C2024b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        mo3358a(context, attributeSet);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.img_cycling_report_chart_menu:
                if (this.f9163n != null) {
                    this.f9163n.mo3298a(this.f9159j, view);
                    this.f9158i.setImageResource(C1373R.drawable.ic_close_icon);
                    return;
                }
                return;
            case C1373R.id.tv_cycling_report_change_screen_to_horizontal:
                mo3359b();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    protected void mo3358a(Context context, AttributeSet attributeSet) {
        C2024b.inflate(context, C1373R.layout.layout_base_chart, this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BaseCyclingChartView);
        this.f9153d = obtainStyledAttributes.getString(0);
        this.f9151b = obtainStyledAttributes.getString(1);
        obtainStyledAttributes.recycle();
        this.f9162m = (TextView) findViewById(C1373R.id.tv_cycling_report_chart_title);
        this.f9162m.setText(this.f9153d);
        this.f9154e = findViewById(C1373R.id.view_cycling_report_name_left);
        this.f9155f = (TextView) findViewById(C1373R.id.tv_cycling_report_virtual);
        this.f9158i = (ImageView) findViewById(C1373R.id.img_cycling_report_chart_menu);
        this.f9159j = (RelativeLayout) findViewById(C1373R.id.rela_temp);
        this.f9160k = findViewById(C1373R.id.rela_cycling_report_unit);
        this.f9157h = (TextView) findViewById(C1373R.id.tv_cycling_report_chart_y_unit);
        this.f9161l = (TextView) findViewById(C1373R.id.tv_cycling_report_change_screen_to_horizontal);
        mo3356a();
    }

    /* renamed from: a */
    private void mo3356a() {
        this.f9158i.setOnClickListener(this);
        this.f9161l.setOnClickListener(this);
    }

    /* renamed from: a */
    public void m10449a(String str, int i, int i2) {
        this.f9153d = str;
        this.f9156g = i;
        this.f9150a = i2;
        this.f9162m.setText(str.toUpperCase());
        this.f9162m.setTextColor(i2);
        this.f9154e.setBackgroundColor(i2);
    }

    public void setXAxisUnit(String str) {
        this.f9151b = str;
    }

    public void setYAxisUnit(String str) {
        this.f9152c = str;
        this.f9157h.setText(str);
    }

    /* renamed from: f */
    public void m10451f() {
        this.f9160k.setVisibility(8);
    }

    /* renamed from: g */
    public void m10452g() {
        this.f9155f.setVisibility(0);
    }

    public int getType() {
        return this.f9156g;
    }

    /* renamed from: b */
    private void mo3359b() {
        Intent intent = new Intent(getContext(), CyclingReportHorizontalActivity.class);
        intent.putExtra("chart_type", this.f9156g);
        getContext().startActivity(intent);
    }

    /* renamed from: h */
    public void m10453h() {
        this.f9158i.setImageResource(C1373R.drawable.ic_menu);
    }

    public void setOnClickSelectMenuListener(C1956a c1956a) {
        this.f9163n = c1956a;
    }

    /* renamed from: e */
    public boolean mo3360e() {
        return true;
    }
}
