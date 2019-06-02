package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckedTextView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.ChartDataCompareItemView;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.CustomVerticalLineView;
import com.beastbikes.android.widget.NumberTextView;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p183g.C3283i;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class RecordDataCompareActivity extends SessionFragmentActivity implements OnClickListener, OnSeekBarChangeListener {
    /* renamed from: a */
    private AppCompatSeekBar f8815a;
    /* renamed from: b */
    private CustomVerticalLineView f8816b;
    /* renamed from: c */
    private CheckedTextView f8817c;
    /* renamed from: d */
    private CheckedTextView f8818d;
    /* renamed from: e */
    private CheckedTextView f8819e;
    /* renamed from: f */
    private ChartDataCompareItemView f8820f;
    /* renamed from: g */
    private ChartDataCompareItemView f8821g;
    /* renamed from: h */
    private ChartDataCompareItemView f8822h;
    /* renamed from: i */
    private NumberTextView f8823i;
    /* renamed from: j */
    private TextView f8824j;
    /* renamed from: k */
    private int f8825k = 0;
    /* renamed from: l */
    private int f8826l = 4;
    /* renamed from: m */
    private C1977b f8827m;
    /* renamed from: n */
    private DecimalFormat f8828n;
    /* renamed from: o */
    private DecimalFormat f8829o;
    /* renamed from: p */
    private float f8830p;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C1373R.layout.activity_record_data_compare);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f8825k = getWindowManager().getDefaultDisplay().getWidth();
        this.f8827m = C1977b.m10160a();
        this.f8830p = (((float) this.f8825k) - C3283i.m15928a(94.0f)) / ((float) (this.f8827m.m10175e().size() - 1));
        this.f8828n = new DecimalFormat("0.0");
        this.f8829o = new DecimalFormat("0");
        m10121a();
    }

    /* renamed from: a */
    private void m10121a() {
        this.f8815a = (AppCompatSeekBar) findViewById(C1373R.id.seekBar_record_data_compare);
        this.f8815a.setMax(this.f8827m.m10175e().size() - 1);
        this.f8816b = (CustomVerticalLineView) findViewById(C1373R.id.custom_vertical_line);
        this.f8817c = (CheckedTextView) findViewById(C1373R.id.checked_tv_speed);
        this.f8818d = (CheckedTextView) findViewById(C1373R.id.checked_tv_heart_rate);
        this.f8819e = (CheckedTextView) findViewById(C1373R.id.checked_tv_cadence);
        this.f8820f = (ChartDataCompareItemView) findViewById(C1373R.id.chart_data_compare_speed);
        this.f8821g = (ChartDataCompareItemView) findViewById(C1373R.id.chart_data_compare_heart_rate);
        this.f8822h = (ChartDataCompareItemView) findViewById(C1373R.id.chart_data_compare_cadence);
        this.f8823i = (NumberTextView) findViewById(C1373R.id.tv_chart_data_compare_bottom_distance);
        this.f8824j = (TextView) findViewById(C1373R.id.tv_chart_data_compare_bottom_unit);
        this.f8823i.setText("0.0");
        if (C1849a.m9645b((Context) this)) {
            this.f8824j.setText(C1373R.string.label_distance_unit);
        } else {
            this.f8824j.setText("MI");
        }
        m10122b();
        m10123c();
        m10124d();
        m10125e();
        m10126f();
    }

    /* renamed from: b */
    private void m10122b() {
        ArrayList arrayList = new ArrayList();
        int size = this.f8827m.m10175e().size();
        float f = 100.0f;
        int i = 0;
        while (i < size) {
            float b = ((Entry) this.f8827m.m10175e().get(i)).mo3912b();
            arrayList.add(new Entry((float) i, b));
            if (b <= f) {
                b = f;
            }
            i++;
            f = b;
        }
        this.f8820f.setYMaxValue(f);
        this.f8820f.setData(arrayList);
    }

    /* renamed from: c */
    private void m10123c() {
        if (this.f8827m.m10171c() == null || this.f8827m.m10171c().getMaxCardiacRate() <= 0.0d) {
            this.f8821g.setData(null);
            this.f8818d.toggle();
            this.f8821g.setVisibility(8);
            this.f8826l--;
            return;
        }
        int size = this.f8827m.m10178h().size();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            arrayList.add(new Entry((float) i, ((Entry) this.f8827m.m10178h().get(i)).mo3912b()));
        }
        this.f8821g.setData(arrayList);
    }

    /* renamed from: d */
    private void m10124d() {
        if (this.f8827m.m10171c() == null || this.f8827m.m10171c().getMaxCadence() <= 0.0d) {
            this.f8822h.setData(null);
            this.f8819e.toggle();
            this.f8822h.setVisibility(8);
            this.f8826l--;
            return;
        }
        this.f8822h.setData(this.f8827m.m10181k());
    }

    /* renamed from: e */
    private void m10125e() {
        this.f8820f.setValue("0.0");
        if (C1849a.m9645b((Context) this)) {
            this.f8820f.setUnit("KM/H");
        } else {
            this.f8820f.setUnit("MPH");
        }
        this.f8821g.setValue("0");
        this.f8821g.setUnit(getResources().getString(C1373R.string.label_bpm));
        this.f8822h.setValue("0");
        this.f8822h.setUnit(getResources().getString(C1373R.string.str_unit_cadence));
    }

    /* renamed from: f */
    private void m10126f() {
        this.f8815a.setOnSeekBarChangeListener(this);
        this.f8817c.setOnClickListener(this);
        this.f8818d.setOnClickListener(this);
        this.f8819e.setOnClickListener(this);
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (i <= this.f8827m.m10175e().size() - 1) {
            this.f8816b.m10326a(this.f8830p * ((float) i));
            this.f8820f.setHighLightValue((float) i);
            if (this.f8827m.m10171c().getMaxCardiacRate() > 0.0d) {
                this.f8821g.setHighLightValue((float) i);
                this.f8821g.setValue(this.f8829o.format((double) ((Entry) this.f8827m.m10178h().get(i)).mo3912b()));
            }
            if (this.f8827m.m10171c().getMaxCadence() > 0.0d) {
                this.f8822h.setHighLightValue((float) i);
                this.f8822h.setValue(this.f8829o.format((double) ((Entry) this.f8827m.m10181k().get(i)).mo3912b()));
            }
            this.f8820f.setValue(this.f8828n.format((double) ((Entry) this.f8827m.m10175e().get(i)).mo3912b()));
            this.f8823i.setText(this.f8828n.format(((double) (((Entry) this.f8827m.m10175e().get(i)).m15450i() / ((float) (this.f8827m.m10187q() - 1)))) * this.f8827m.m10186p()));
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.checked_tv_speed:
                if (!this.f8817c.isChecked()) {
                    this.f8820f.setVisibility(0);
                    this.f8826l++;
                } else if (this.f8826l > 1) {
                    this.f8820f.setVisibility(8);
                    this.f8826l--;
                } else {
                    return;
                }
                this.f8817c.toggle();
                return;
            case C1373R.id.checked_tv_heart_rate:
                if (this.f8818d.isChecked()) {
                    if (this.f8826l > 1) {
                        this.f8821g.setVisibility(8);
                        this.f8826l--;
                    } else {
                        return;
                    }
                } else if (this.f8827m.m10171c() == null || this.f8827m.m10171c().getMaxCardiacRate() <= 0.0d) {
                    Toasts.show((Context) this, (int) C1373R.string.toast_unrecord_cycling_data);
                    return;
                } else {
                    this.f8821g.setVisibility(0);
                    this.f8826l++;
                }
                this.f8818d.toggle();
                return;
            case C1373R.id.checked_tv_cadence:
                if (this.f8819e.isChecked()) {
                    if (this.f8826l > 1) {
                        this.f8822h.setVisibility(8);
                        this.f8826l--;
                    } else {
                        return;
                    }
                } else if (this.f8827m.m10171c() == null || this.f8827m.m10171c().getMaxCadence() <= 0.0d) {
                    Toasts.show((Context) this, (int) C1373R.string.toast_unrecord_cycling_data);
                    return;
                } else {
                    this.f8822h.setVisibility(0);
                    this.f8826l++;
                }
                this.f8819e.toggle();
                return;
            default:
                return;
        }
    }
}
