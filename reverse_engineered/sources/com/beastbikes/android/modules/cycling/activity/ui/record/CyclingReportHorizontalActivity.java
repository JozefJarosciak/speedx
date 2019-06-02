package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.ui.record.p122b.C1973e;
import com.beastbikes.android.modules.cycling.activity.ui.record.p122b.C1976h;
import com.beastbikes.android.modules.cycling.activity.ui.record.p124e.C1985b;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.CyclingHorizontalCheckView;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.CyclingHorizontalCheckView.C2002a;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.SuperscriptView;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.C3238l;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.listener.C2012c;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3283i;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@C1459b(a = 2130903119)
public class CyclingReportHorizontalActivity extends SessionFragmentActivity implements OnClickListener, C2002a, C2012c {
    /* renamed from: A */
    private ArrayList<Entry> f4731A;
    /* renamed from: B */
    private ArrayList<Entry> f4732B;
    /* renamed from: C */
    private LineDataSet f4733C;
    /* renamed from: D */
    private LineDataSet f4734D;
    /* renamed from: E */
    private PopupWindow f4735E;
    /* renamed from: F */
    private TextView f4736F;
    /* renamed from: G */
    private TextView f4737G;
    /* renamed from: H */
    private int f4738H;
    /* renamed from: I */
    private int f4739I = 0;
    /* renamed from: J */
    private CyclingHorizontalCheckView f4740J;
    /* renamed from: K */
    private CyclingHorizontalCheckView f4741K;
    /* renamed from: L */
    private CyclingHorizontalCheckView f4742L;
    /* renamed from: M */
    private CyclingHorizontalCheckView f4743M;
    /* renamed from: N */
    private CyclingHorizontalCheckView f4744N;
    /* renamed from: O */
    private CyclingHorizontalCheckView f4745O;
    /* renamed from: P */
    private CyclingHorizontalCheckView f4746P;
    /* renamed from: Q */
    private CyclingHorizontalCheckView f4747Q;
    /* renamed from: R */
    private String f4748R = "";
    /* renamed from: S */
    private String f4749S = "";
    /* renamed from: T */
    private int f4750T;
    /* renamed from: U */
    private int f4751U = -1;
    /* renamed from: V */
    private Drawable f4752V;
    /* renamed from: W */
    private Drawable f4753W;
    /* renamed from: X */
    private Drawable f4754X;
    /* renamed from: Y */
    private Drawable f4755Y;
    /* renamed from: Z */
    private Drawable f4756Z;
    @C1458a(a = 2131755671)
    /* renamed from: a */
    private TextView f4757a;
    private Drawable aa;
    private FrameLayout ab;
    private ActivityDTO ac;
    private CyclingReportHorizontalActivity$a ad;
    @C1458a(a = 2131755677)
    /* renamed from: b */
    private TextView f4758b;
    @C1458a(a = 2131755672)
    /* renamed from: c */
    private RelativeLayout f4759c;
    @C1458a(a = 2131755673)
    /* renamed from: d */
    private SuperscriptView f4760d;
    @C1458a(a = 2131755674)
    /* renamed from: e */
    private TextView f4761e;
    @C1458a(a = 2131755675)
    /* renamed from: f */
    private TextView f4762f;
    @C1458a(a = 2131755676)
    /* renamed from: g */
    private TextView f4763g;
    @C1458a(a = 2131755678)
    /* renamed from: h */
    private RelativeLayout f4764h;
    @C1458a(a = 2131755680)
    /* renamed from: i */
    private TextView f4765i;
    @C1458a(a = 2131755681)
    /* renamed from: j */
    private TextView f4766j;
    @C1458a(a = 2131755682)
    /* renamed from: k */
    private TextView f4767k;
    @C1458a(a = 2131755683)
    /* renamed from: l */
    private LineChart f4768l;
    @C1458a(a = 2131756801)
    /* renamed from: m */
    private LinearLayout f4769m;
    @C1458a(a = 2131756802)
    /* renamed from: n */
    private TextView f4770n;
    @C1458a(a = 2131756803)
    /* renamed from: o */
    private TextView f4771o;
    @C1458a(a = 2131756804)
    /* renamed from: p */
    private TextView f4772p;
    @C1458a(a = 2131756805)
    /* renamed from: q */
    private ImageView f4773q;
    /* renamed from: r */
    private C1977b f4774r;
    /* renamed from: s */
    private C1973e f4775s;
    /* renamed from: t */
    private int f4776t;
    /* renamed from: u */
    private int f4777u;
    /* renamed from: v */
    private int f4778v;
    /* renamed from: w */
    private DecimalFormat f4779w;
    /* renamed from: x */
    private DecimalFormat f4780x;
    /* renamed from: y */
    private float f4781y;
    /* renamed from: z */
    private float f4782z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f4774r = C1977b.a();
        this.ac = this.f4774r.c();
        this.f4775s = new C1973e();
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(C1373R.dimen.dimen_5);
        this.f4769m.setPadding(0, dimensionPixelOffset, 0, dimensionPixelOffset);
        this.f4769m.setVisibility(4);
        this.f4773q.setVisibility(4);
        this.f4770n.setTextSize(1, 11.0f);
        this.f4771o.setTextSize(1, 11.0f);
        this.f4772p.setTextSize(1, 11.0f);
        this.f4779w = new DecimalFormat("#");
        this.f4780x = new DecimalFormat("#.#");
        dimensionPixelOffset = MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f4776t = displayMetrics.widthPixels;
        this.f4777u = getResources().getDimensionPixelOffset(C1373R.dimen.dimen_105);
        this.f4773q.measure(dimensionPixelOffset, makeMeasureSpec);
        this.f4778v = this.f4773q.getMeasuredWidth();
        this.f4738H = getIntent().getIntExtra("chart_type", 0);
        this.f4752V = ContextCompat.getDrawable(this, C1373R.drawable.bg_fade_green);
        this.f4753W = ContextCompat.getDrawable(this, C1373R.drawable.bg_fade_gray);
        this.f4754X = ContextCompat.getDrawable(this, C1373R.drawable.bg_fade_red);
        this.f4755Y = ContextCompat.getDrawable(this, C1373R.drawable.bg_fade_blue_for_cadence);
        this.f4756Z = ContextCompat.getDrawable(this, C1373R.drawable.bg_fade_red_for_power);
        this.ad = new CyclingReportHorizontalActivity$a(this, null);
        m6095b();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.tv_cycling_report_horizontal_close:
                finish();
                return;
            case C1373R.id.tv_cycling_report_horizontal_overlying:
                m6101f();
                return;
            case C1373R.id.view:
                this.f4735E.dismiss();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m6104a(Entry entry, C3213d c3213d) {
        int i = 0;
        this.f4769m.setVisibility(0);
        this.f4773q.setVisibility(0);
        m6091a((int) entry.i());
        C3213d c3213d2 = null;
        if (c3213d.f() == 0) {
            if (!(this.f4731A == null || this.f4732B == null || this.f4732B.isEmpty())) {
                c3213d2 = new C3213d(c3213d.a(), ((Entry) this.f4732B.get((int) c3213d.a())).b(), 1);
            }
        } else if (!(this.f4732B == null || this.f4731A == null || this.f4731A.isEmpty())) {
            c3213d2 = new C3213d(c3213d.a(), ((Entry) this.f4731A.get((int) c3213d.a())).b(), 0);
        }
        if (c3213d2 != null) {
            this.f4768l.a(new C3213d[]{c3213d, c3213d2});
        }
        int c = (int) c3213d.c();
        int i2 = c - (this.f4777u / 2);
        LayoutParams layoutParams = (LayoutParams) this.f4769m.getLayoutParams();
        if (i2 >= 0) {
            i = i2 > this.f4776t - this.f4777u ? this.f4776t - this.f4777u : i2;
        }
        layoutParams.leftMargin = i;
        this.f4769m.setLayoutParams(layoutParams);
        layoutParams = (LayoutParams) this.f4773q.getLayoutParams();
        layoutParams.leftMargin = c - (this.f4778v / 2);
        this.f4773q.setLayoutParams(layoutParams);
    }

    /* renamed from: a */
    public void m6102a() {
        this.f4769m.setVisibility(4);
        this.f4773q.setVisibility(4);
    }

    /* renamed from: a */
    public void m6103a(CyclingHorizontalCheckView cyclingHorizontalCheckView, View view, boolean z) {
        if (z) {
            if (this.f4746P != null) {
                this.f4746P.b();
            }
            this.f4746P = cyclingHorizontalCheckView;
            this.f4738H = cyclingHorizontalCheckView.getChartType();
            this.f4736F.setText(cyclingHorizontalCheckView.getText());
        } else {
            if (this.f4747Q != null) {
                this.f4747Q.b();
            }
            this.f4747Q = cyclingHorizontalCheckView;
            this.f4737G.setText(cyclingHorizontalCheckView.getText());
            this.f4739I = cyclingHorizontalCheckView.getChartType();
        }
        m6094a(z, cyclingHorizontalCheckView.getChartType());
        this.f4768l.a(null);
        this.f4769m.setVisibility(4);
        this.f4773q.setVisibility(4);
        this.f4768l.invalidate();
    }

    /* renamed from: b */
    private void m6095b() {
        m6098c();
        m6099d();
        m6100e();
        m6094a(true, this.f4738H);
        m6094a(false, this.f4739I);
    }

    /* renamed from: c */
    private void m6098c() {
        this.f4768l.setDragEnabled(true);
        this.f4768l.setScaleEnabled(false);
        this.f4768l.setPinchZoom(false);
        this.f4768l.setBackgroundColor(Color.parseColor("#181818"));
        this.f4768l.setUnbindEnabled(true);
        this.f4768l.getDescription().e(false);
        this.f4768l.setNoDataText("");
        this.f4768l.setExtraBottomOffset(10.0f);
        XAxis xAxis = this.f4768l.getXAxis();
        xAxis.a(10.0f, 10.0f, 0.0f);
        xAxis.a(false);
        xAxis.a(6, true);
        xAxis.c(true);
        xAxis.b(Color.parseColor("#333333"));
        xAxis.e(-1);
        xAxis.k(10.0f);
        xAxis.a(XAxisPosition.BOTTOM);
        xAxis.j(8.0f);
        xAxis.a(new C1976h(this.f4774r.c().getElapsedTime()));
        YAxis axisLeft = this.f4768l.getAxisLeft();
        axisLeft.m();
        axisLeft.e(Color.parseColor("#999999"));
        axisLeft.j(0.0f);
        axisLeft.a(Color.parseColor("#979797"));
        axisLeft.d(0.0f);
        axisLeft.a(1.0f, 10.0f, 0.0f);
        axisLeft.a(6, true);
        axisLeft.b(0.5f);
        axisLeft.f(false);
        axisLeft.b(false);
        axisLeft.e(-1);
        axisLeft.k(10.0f);
        axisLeft.a(this.f4775s);
        YAxis axisRight = this.f4768l.getAxisRight();
        axisRight.e(true);
        axisRight.m();
        axisRight.e(-1);
        axisRight.k(10.0f);
        axisRight.j(0.0f);
        axisRight.d(0.0f);
        axisRight.a(Color.parseColor("#979797"));
        axisRight.a(1.0f, 10.0f, 0.0f);
        axisRight.a(6, true);
        axisRight.b(0.5f);
        axisRight.f(false);
        axisRight.b(false);
        axisRight.a(this.f4775s);
        axisLeft.d(false);
        axisRight.d(false);
        this.f4768l.getLegend().e(false);
        this.f4768l.setMarker(new C1985b(this, C1373R.drawable.bg_chart_marker_ring_image));
        this.f4768l.setOnChartValueSelectedListener(this);
    }

    /* renamed from: d */
    private void m6099d() {
        this.f4757a.setOnClickListener(this);
        this.f4758b.setOnClickListener(this);
    }

    /* renamed from: e */
    private void m6100e() {
        this.f4731A = new ArrayList();
        this.f4732B = new ArrayList();
        this.f4733C = new LineDataSet(this.f4731A, "");
        this.f4733C.m8244c(false);
        this.f4733C.e(2.0f);
        this.f4733C.f(2.0f);
        this.f4733C.a(-1);
        this.f4733C.g(true);
        this.f4733C.f(false);
        this.f4733C.a(AxisDependency.LEFT);
        this.f4733C.b(false);
        this.f4734D = new LineDataSet(this.f4732B, "");
        this.f4734D.m8244c(false);
        this.f4734D.e(2.0f);
        this.f4734D.e(2.0f);
        this.f4734D.f(2.0f);
        this.f4734D.c(-1);
        this.f4734D.a(-1);
        this.f4734D.g(true);
        this.f4734D.f(false);
        this.f4734D.a(AxisDependency.RIGHT);
        this.f4734D.b(false);
        List arrayList = new ArrayList();
        arrayList.add(this.f4733C);
        arrayList.add(this.f4734D);
        this.f4768l.setData(new C3238l(arrayList));
    }

    /* renamed from: a */
    private void m6093a(ArrayList<Entry> arrayList, int i, Drawable drawable) {
        if (i == 0) {
            this.f4768l.getAxisLeft().f(this.f4781y);
        } else {
            this.f4768l.getAxisRight().f(this.f4782z);
        }
        if (arrayList != null) {
            LineDataSet lineDataSet;
            if (i == 0) {
                lineDataSet = this.f4733C;
            } else {
                lineDataSet = this.f4734D;
            }
            if (lineDataSet != null) {
                lineDataSet.b(arrayList);
                m6092a(i, i == 0 ? this.f4750T : this.f4751U, drawable, lineDataSet);
                ((C3238l) this.f4768l.getData()).b();
            } else {
                lineDataSet = new LineDataSet(arrayList, "");
                lineDataSet.a(i == 0 ? AxisDependency.LEFT : AxisDependency.RIGHT);
                lineDataSet.m8244c(false);
                lineDataSet.e(2.0f);
                lineDataSet.f(2.0f);
                if (i == 0) {
                    lineDataSet.e(true);
                    lineDataSet.a(drawable);
                    lineDataSet.c(0);
                    this.f4768l.getAxisLeft().e(this.f4750T);
                } else {
                    lineDataSet.e(false);
                    lineDataSet.c(-1);
                }
                lineDataSet.a(-1);
                lineDataSet.g(true);
                lineDataSet.f(false);
                lineDataSet.b(false);
                ((C3238l) this.f4768l.getData()).j();
                if (this.f4733C != null) {
                    ((C3238l) this.f4768l.getData()).a(this.f4733C);
                }
                ((C3238l) this.f4768l.getData()).a(lineDataSet);
                if (this.f4734D != null) {
                    ((C3238l) this.f4768l.getData()).a(this.f4734D);
                }
                if (i == 0) {
                    this.f4733C = lineDataSet;
                    this.f4768l.getAxisLeft().f(this.f4781y);
                } else {
                    this.f4734D = lineDataSet;
                    this.f4768l.getAxisRight().f(this.f4782z);
                }
            }
        } else if (i == 0) {
            ((C3238l) this.f4768l.getData()).b(this.f4733C);
            this.f4733C = null;
            this.f4731A = null;
        } else {
            ((C3238l) this.f4768l.getData()).b(this.f4734D);
            this.f4734D = null;
            this.f4732B = null;
        }
        this.f4768l.h();
    }

    /* renamed from: a */
    private void m6092a(int i, int i2, Drawable drawable, LineDataSet lineDataSet) {
        if (i == 0) {
            lineDataSet.c(0);
            lineDataSet.e(true);
            if (C3283i.d() >= 18) {
                lineDataSet.a(drawable);
            } else {
                lineDataSet.j(0);
            }
            this.f4768l.getAxisLeft().e(this.f4750T);
            return;
        }
        lineDataSet.c(i2);
        lineDataSet.e(false);
    }

    /* renamed from: a */
    private void m6091a(int i) {
        int i2 = 1;
        if (this.f4731A == null || this.f4731A.isEmpty()) {
            this.f4770n.setText("");
        } else {
            this.f4770n.setText(this.f4780x.format((double) ((Entry) this.f4731A.get(i)).b()) + this.f4748R);
            i2 = this.f4731A.size();
        }
        if (this.f4732B == null || this.f4732B.isEmpty()) {
            this.f4771o.setText("");
        } else {
            this.f4771o.setText(this.f4780x.format((double) ((Entry) this.f4732B.get(i)).b()) + this.f4749S);
            i2 = this.f4732B.size();
        }
        this.f4772p.setText(C2555d.b((long) ((this.f4774r.c().getElapsedTime() / ((double) i2)) * ((double) i))));
    }

    /* renamed from: f */
    private void m6101f() {
        if (this.f4735E == null) {
            View inflate = LayoutInflater.from(this).inflate(C1373R.layout.layout_cycling_report_horizontal_popup, null);
            this.f4740J = (CyclingHorizontalCheckView) inflate.findViewById(C1373R.id.check_view_cycling_report_horizontal_popup_nil);
            this.f4741K = (CyclingHorizontalCheckView) inflate.findViewById(C1373R.id.check_view_cycling_report_horizontal_popup_speed);
            this.f4742L = (CyclingHorizontalCheckView) inflate.findViewById(C1373R.id.check_view_cycling_report_horizontal_popup_elevation);
            this.f4743M = (CyclingHorizontalCheckView) inflate.findViewById(C1373R.id.check_view_cycling_report_horizontal_popup_heart_rate);
            this.f4744N = (CyclingHorizontalCheckView) inflate.findViewById(C1373R.id.check_view_cycling_report_horizontal_popup_cadence);
            this.f4745O = (CyclingHorizontalCheckView) inflate.findViewById(C1373R.id.check_view_cycling_report_horizontal_popup_power);
            View findViewById = inflate.findViewById(C1373R.id.view);
            this.f4736F = (TextView) inflate.findViewById(C1373R.id.tv_cycling_report_horizontal_popup_left_select);
            this.f4737G = (TextView) inflate.findViewById(C1373R.id.tv_cycling_report_horizontal_popup_right_select);
            if (this.ac.getMaxPower() <= 0.0d) {
                this.f4745O.a();
            }
            if (this.ac.getMaxCadence() <= 0.0d) {
                this.f4744N.a();
            }
            if (this.ac.getMaxCardiacRate() <= 0.0d) {
                this.f4743M.a();
            }
            this.f4740J.setOnCheckViewChangedListener(this);
            this.f4741K.setOnCheckViewChangedListener(this);
            this.f4742L.setOnCheckViewChangedListener(this);
            this.f4743M.setOnCheckViewChangedListener(this);
            this.f4744N.setOnCheckViewChangedListener(this);
            this.f4745O.setOnCheckViewChangedListener(this);
            findViewById.setOnClickListener(this);
            this.f4735E = new PopupWindow(inflate, -1, -1);
            this.f4735E.setOutsideTouchable(true);
            this.f4735E.setFocusable(true);
            this.f4735E.setBackgroundDrawable(new ColorDrawable());
        }
        m6096b(true, this.f4738H);
        m6096b(false, this.f4739I);
        if (this.f4735E.isShowing()) {
            this.f4735E.dismiss();
            return;
        }
        if (this.ab == null) {
            this.ab = new FrameLayout(this);
        }
        this.f4735E.showAtLocation(this.ab, 0, 0, 0);
    }

    /* renamed from: a */
    private void m6094a(boolean z, int i) {
        ArrayList c = m6097c(z, i);
        if (z) {
            this.f4731A = c;
            m6093a(this.f4731A, 0, this.aa);
            return;
        }
        this.f4732B = c;
        m6093a(this.f4732B, 1, null);
    }

    /* renamed from: b */
    private void m6096b(boolean z, int i) {
        CyclingHorizontalCheckView cyclingHorizontalCheckView = null;
        switch (i) {
            case 0:
                cyclingHorizontalCheckView = this.f4740J;
                break;
            case 1:
                cyclingHorizontalCheckView = this.f4741K;
                break;
            case 2:
                cyclingHorizontalCheckView = this.f4742L;
                break;
            case 3:
                cyclingHorizontalCheckView = this.f4744N;
                break;
            case 4:
                cyclingHorizontalCheckView = this.f4743M;
                break;
            case 6:
                cyclingHorizontalCheckView = this.f4745O;
                break;
        }
        if (cyclingHorizontalCheckView != null) {
            if (z) {
                this.f4746P = cyclingHorizontalCheckView;
                this.f4736F.setText(cyclingHorizontalCheckView.getText());
            } else {
                this.f4747Q = cyclingHorizontalCheckView;
                this.f4737G.setText(cyclingHorizontalCheckView.getText());
            }
            cyclingHorizontalCheckView.setCheck(z);
        }
    }

    /* renamed from: c */
    private ArrayList<Entry> m6097c(boolean z, int i) {
        int i2;
        CharSequence charSequence;
        CharSequence charSequence2;
        Collection collection;
        String str = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        Object obj = null;
        String string;
        Object e;
        Object obj2;
        Object obj3;
        Object obj4;
        switch (i) {
            case 1:
                if (C1849a.b(this)) {
                    string = getString(C1373R.string.str_unit_km_per_hour);
                } else {
                    string = getString(C1373R.string.str_unit_mile_per_hour);
                }
                i2 = -16450648;
                str = (getString(C1373R.string.str_speed) + "(" + string + ")").toUpperCase();
                charSequence = getString(C1373R.string.str_max_speed) + ":" + this.f4780x.format(this.ac.getMaxVelocity());
                charSequence2 = getString(C1373R.string.str_average_speed) + ":" + this.f4780x.format(this.ac.getVelocity());
                this.aa = this.f4752V;
                e = this.f4774r.e();
                obj2 = str;
                str = string;
                break;
            case 2:
                if (C1849a.b(this)) {
                    string = getString(C1373R.string.str_meter);
                } else {
                    string = getString(C1373R.string.str_feet);
                }
                i2 = -1;
                str = (getString(C1373R.string.str_elevation) + "(" + string + ")").toUpperCase();
                charSequence = getString(C1373R.string.str_max_altitude) + ":" + this.f4780x.format(this.ac.getMaxAltitude());
                charSequence2 = getString(C1373R.string.str_avg_altitude) + ":" + this.f4780x.format((double) this.f4774r.g());
                this.aa = this.f4753W;
                e = this.f4774r.f();
                obj2 = str;
                str = string;
                break;
            case 3:
                str2 = getString(C1373R.string.str_unit_cadence);
                str = (getString(C1373R.string.str_cadence) + "(" + str2 + ")").toUpperCase();
                str3 = getString(C1373R.string.str_max_cadence) + ":" + this.f4779w.format(this.ac.getMaxCadence());
                str4 = getString(C1373R.string.str_average_cadence) + ":" + this.f4779w.format((double) this.f4774r.n());
                this.aa = this.f4755Y;
                e = this.f4774r.k();
                obj2 = str;
                str = str2;
                obj3 = str3;
                obj4 = str4;
                i2 = -16733185;
                break;
            case 4:
                str2 = getString(C1373R.string.str_unit_heart_rate);
                str = (getString(C1373R.string.str_heart_rate) + "(" + str2 + ")").toUpperCase();
                str3 = getString(C1373R.string.str_max_heart_rate) + ":" + this.f4779w.format(this.ac.getMaxCardiacRate());
                str4 = getString(C1373R.string.str_average_heart_rate) + ":" + this.f4779w.format((double) this.f4774r.i());
                this.aa = this.f4754X;
                e = this.f4774r.h();
                obj2 = str;
                str = str2;
                obj3 = str3;
                obj4 = str4;
                i2 = -65494;
                break;
            case 6:
                String string2 = getString(C1373R.string.str_unit_power);
                string = (getString(C1373R.string.str_power) + "(" + string2 + ")").toUpperCase();
                if (this.ac.isVirtualPower()) {
                    string = getString(C1373R.string.str_cycling_report_virtual) + string;
                }
                double avgPower = this.ac.getAvgPower();
                if (avgPower <= 0.0d) {
                    avgPower = 0.0d;
                }
                str = getString(C1373R.string.str_max_power) + ":" + this.f4779w.format(this.ac.getMaxPower());
                str4 = getString(C1373R.string.str_avg_power) + ":" + this.f4779w.format(avgPower);
                this.aa = this.f4756Z;
                obj = 1;
                e = this.f4774r.l();
                obj4 = str4;
                i2 = -377847;
                obj3 = str;
                str = string2;
                obj2 = string;
                break;
            default:
                this.aa = null;
                collection = null;
                CharSequence charSequence3 = str;
                str = str2;
                charSequence = str3;
                charSequence2 = str4;
                i2 = -1;
                break;
        }
        if (collection == null) {
            if (z) {
                this.f4759c.setVisibility(4);
                this.f4748R = "";
            } else {
                this.f4764h.setVisibility(4);
                this.f4749S = "";
            }
        } else if (z) {
            this.f4781y = ((Entry) Collections.max(collection, this.ad)).b() + 5.0f;
            this.f4759c.setVisibility(0);
            this.f4750T = i2;
            this.f4761e.setTextColor(i2);
            this.f4762f.setTextColor(i2);
            this.f4763g.setTextColor(i2);
            this.f4760d.setColor(i2);
            if (obj != null) {
                this.f4762f.setVisibility(4);
            } else {
                this.f4762f.setVisibility(0);
            }
            this.f4761e.setText(charSequence3);
            this.f4762f.setText(charSequence);
            this.f4763g.setText(charSequence2);
            this.f4748R = str;
        } else {
            this.f4782z = ((Entry) Collections.max(collection, this.ad)).b() + 5.0f;
            this.f4764h.setVisibility(0);
            if (obj != null) {
                this.f4766j.setVisibility(4);
            } else {
                this.f4766j.setVisibility(0);
            }
            this.f4765i.setText(charSequence3);
            this.f4766j.setText(charSequence);
            this.f4767k.setText(charSequence2);
            this.f4749S = str;
        }
        return collection;
    }
}
