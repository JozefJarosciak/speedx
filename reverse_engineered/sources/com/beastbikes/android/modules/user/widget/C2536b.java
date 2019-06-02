package com.beastbikes.android.modules.user.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.user.dto.PersonalRecordDataDTO;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.widget.convenientbanner.p116b.C1935b;
import java.text.DecimalFormat;
import org.apache.commons.cli.HelpFormatter;

/* compiled from: PersonalRecordDataHolder */
/* renamed from: com.beastbikes.android.modules.user.widget.b */
public class C2536b implements C1935b<PersonalRecordDataDTO> {
    /* renamed from: a */
    private LinearLayout f11981a;
    /* renamed from: b */
    private TextView f11982b;
    /* renamed from: c */
    private TextView f11983c;
    /* renamed from: d */
    private LinearLayout f11984d;
    /* renamed from: e */
    private PersonalRecordHeaderView f11985e;
    /* renamed from: f */
    private PersonalRecordHeaderView f11986f;
    /* renamed from: g */
    private PersonalRecordHeaderView f11987g;
    /* renamed from: h */
    private PersonalRecordLittleItem f11988h;
    /* renamed from: i */
    private PersonalRecordLittleItem f11989i;
    /* renamed from: j */
    private PersonalRecordLittleItem f11990j;
    /* renamed from: k */
    private PersonalRecordLittleItem f11991k;
    /* renamed from: l */
    private LinearLayout f11992l;
    /* renamed from: m */
    private PersonalRecordLittleItem f11993m;
    /* renamed from: n */
    private PersonalRecordLittleItem f11994n;
    /* renamed from: o */
    private int f11995o;
    /* renamed from: p */
    private DecimalFormat f11996p;
    /* renamed from: q */
    private DecimalFormat f11997q;
    /* renamed from: r */
    private Context f11998r;
    /* renamed from: s */
    private boolean f11999s;
    /* renamed from: t */
    private String f12000t;
    /* renamed from: u */
    private String f12001u;
    /* renamed from: v */
    private String f12002v;

    /* renamed from: a */
    public View mo3293a(Context context) {
        this.f11998r = context;
        View inflate = LayoutInflater.from(context).inflate(C1373R.layout.layout_personal_record_data, null);
        this.f11981a = (LinearLayout) inflate.findViewById(C1373R.id.linear_personal_record_total_mileage);
        this.f11982b = (TextView) inflate.findViewById(C1373R.id.tv_personal_record_data_total_mileage);
        this.f11983c = (TextView) inflate.findViewById(C1373R.id.tv_personal_record_data_total_mileage_unit);
        this.f11984d = (LinearLayout) inflate.findViewById(C1373R.id.linear_personal_record_header);
        this.f11985e = (PersonalRecordHeaderView) inflate.findViewById(C1373R.id.personal_record_header_item1);
        this.f11986f = (PersonalRecordHeaderView) inflate.findViewById(C1373R.id.personal_record_header_item2);
        this.f11987g = (PersonalRecordHeaderView) inflate.findViewById(C1373R.id.personal_record_header_item3);
        this.f11988h = (PersonalRecordLittleItem) inflate.findViewById(C1373R.id.personal_record_row1_left);
        this.f11989i = (PersonalRecordLittleItem) inflate.findViewById(C1373R.id.personal_record_row1_right);
        this.f11990j = (PersonalRecordLittleItem) inflate.findViewById(C1373R.id.personal_record_row2_left);
        this.f11991k = (PersonalRecordLittleItem) inflate.findViewById(C1373R.id.personal_record_row2_right);
        this.f11992l = (LinearLayout) inflate.findViewById(C1373R.id.linear_personal_record_row3);
        this.f11993m = (PersonalRecordLittleItem) inflate.findViewById(C1373R.id.personal_record_row3_left);
        this.f11993m.setVisibility(4);
        this.f11994n = (PersonalRecordLittleItem) inflate.findViewById(C1373R.id.personal_record_row3_right);
        this.f11981a.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        this.f11995o = this.f11981a.getMeasuredHeight();
        this.f11996p = new DecimalFormat("#.#");
        this.f11997q = new DecimalFormat("#");
        this.f11999s = C1849a.m9645b(context);
        if (this.f11999s) {
            this.f12000t = this.f11998r.getString(C1373R.string.str_unit_km_per_hour);
            this.f12001u = this.f11998r.getString(C1373R.string.str_mileage_unit_km);
            this.f12002v = this.f11998r.getString(C1373R.string.str_meter);
        } else {
            this.f12000t = this.f11998r.getString(C1373R.string.str_unit_mile_per_hour);
            this.f12001u = this.f11998r.getString(C1373R.string.str_mileage_unit_mile);
            this.f12002v = this.f11998r.getString(C1373R.string.str_feet);
        }
        return inflate;
    }

    /* renamed from: a */
    public void m12725a(Context context, int i, PersonalRecordDataDTO personalRecordDataDTO) {
        LayoutParams layoutParams;
        if (personalRecordDataDTO == null) {
            String str = HelpFormatter.DEFAULT_LONG_OPT_PREFIX;
            if (i == 0) {
                this.f11981a.setVisibility(0);
                this.f11982b.setText(str);
                this.f11983c.setText(this.f12001u);
                this.f11985e.setLabel(this.f11998r.getString(C1373R.string.str_time) + "(H)");
                this.f11985e.setValue(str);
                this.f11986f.setLabel(this.f11998r.getString(C1373R.string.str_average_speed) + "(" + this.f12000t + ")");
                this.f11986f.setValue(str);
                this.f11987g.setLabel(this.f11998r.getString(C1373R.string.str_times));
                this.f11987g.setValue(str);
                this.f11988h.setLabel(this.f11998r.getString(C1373R.string.str_personal_farthest_distance));
                this.f11988h.setValue(str);
                this.f11989i.setLabel(this.f11998r.getString(C1373R.string.str_max_speed));
                this.f11989i.setValue(str);
                this.f11990j.setLabel(this.f11998r.getString(C1373R.string.str_calorie));
                this.f11990j.setValue(str);
                this.f11991k.setLabel(this.f11998r.getString(C1373R.string.str_cycling_climb_vertical));
                this.f11991k.setValue(str);
                this.f11992l.setVisibility(0);
                this.f11993m.setLabel(this.f11998r.getString(C1373R.string.str_personal_cycling_rank));
                this.f11993m.setValue(str);
                this.f11994n.setLabel(this.f11998r.getString(C1373R.string.str_personal_record_max_elapsed_time));
                this.f11994n.setValue(str);
                layoutParams = (LayoutParams) this.f11984d.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, 0);
                this.f11984d.setLayoutParams(layoutParams);
                return;
            }
            this.f11981a.setVisibility(8);
            this.f11985e.setLabel(this.f11998r.getString(C1373R.string.str_avg_cadence_with_unit));
            this.f11985e.setValue(str);
            this.f11986f.setLabel(this.f11998r.getString(C1373R.string.str_avg_power) + "(" + this.f11998r.getString(C1373R.string.str_unit_power) + ")");
            this.f11986f.setValue(str);
            this.f11987g.setLabel(this.f11998r.getString(C1373R.string.str_average_heart_rate) + this.f11998r.getString(C1373R.string.label_bpm));
            this.f11987g.setValue(str);
            this.f11988h.setLabel(this.f11998r.getString(C1373R.string.str_max_cadence));
            this.f11988h.setValue(str);
            this.f11989i.setLabel(this.f11998r.getString(C1373R.string.str_max_heart_rate));
            this.f11989i.setValue(str);
            this.f11990j.setLabel(this.f11998r.getString(C1373R.string.str_max_power));
            this.f11990j.setValue(str);
            this.f11991k.setLabel(this.f11998r.getString(C1373R.string.str_power_output));
            this.f11991k.setValue(str);
            this.f11992l.setVisibility(8);
            layoutParams = (LayoutParams) this.f11984d.getLayoutParams();
            layoutParams.setMargins(0, this.f11995o / 2, 0, this.f11995o / 2);
            this.f11984d.setLayoutParams(layoutParams);
        } else if (i == 0) {
            double totalAscent;
            this.f11981a.setVisibility(0);
            this.f11982b.setText(this.f11996p.format(this.f11999s ? personalRecordDataDTO.getTotalMileage() / 1000.0d : C1849a.m9638a(personalRecordDataDTO.getTotalMileage() / 1000.0d)));
            this.f11983c.setText("(" + this.f12001u + ")");
            this.f11985e.setLabel(this.f11998r.getString(C1373R.string.str_time) + "(H)");
            if (personalRecordDataDTO.getTotalTime() >= 3600) {
                this.f11985e.setValue(this.f11996p.format(personalRecordDataDTO.getTotalTime() / 3600));
            } else {
                this.f11985e.setValue(this.f11996p.format(((double) personalRecordDataDTO.getTotalTime()) / 3600.0d));
            }
            this.f11986f.setLabel(this.f11998r.getString(C1373R.string.str_average_speed) + "(" + this.f12000t + ")");
            this.f11986f.setValue(this.f11996p.format(this.f11999s ? personalRecordDataDTO.getAvgSpeed() : C1849a.m9638a(personalRecordDataDTO.getAvgSpeed())));
            this.f11987g.setLabel(this.f11998r.getString(C1373R.string.str_times));
            this.f11987g.setValue(personalRecordDataDTO.getCyclingTimes() + "");
            this.f11988h.setLabel(this.f11998r.getString(C1373R.string.str_personal_farthest_distance));
            this.f11988h.setValue(this.f11997q.format(this.f11999s ? personalRecordDataDTO.getMaxDistance() / 1000.0d : C1849a.m9638a(personalRecordDataDTO.getMaxDistance() / 1000.0d)));
            this.f11988h.setUnit(this.f12001u);
            this.f11989i.setLabel(this.f11998r.getString(C1373R.string.str_max_speed));
            this.f11989i.setValue(this.f11996p.format(this.f11999s ? personalRecordDataDTO.getMaxSpeed() : C1849a.m9638a(personalRecordDataDTO.getMaxSpeed())));
            this.f11989i.setUnit(this.f12000t);
            this.f11990j.setLabel(this.f11998r.getString(C1373R.string.str_calorie));
            this.f11990j.setValue(this.f11997q.format(personalRecordDataDTO.getTotalCalorie()));
            this.f11990j.setUnit(this.f11998r.getString(C1373R.string.str_unit_calorie));
            this.f11991k.setLabel(this.f11998r.getString(C1373R.string.str_cycling_climb_vertical));
            if (this.f11999s) {
                totalAscent = personalRecordDataDTO.getTotalAscent();
            } else {
                totalAscent = C1849a.m9646c(personalRecordDataDTO.getTotalAscent());
            }
            this.f11991k.setValue(this.f11996p.format(totalAscent));
            this.f11991k.setUnit(this.f12002v);
            this.f11992l.setVisibility(0);
            this.f11993m.setLabel(this.f11998r.getString(C1373R.string.str_personal_cycling_rank));
            this.f11993m.setValue(personalRecordDataDTO.getCyclingRank() + "");
            this.f11994n.setLabel(this.f11998r.getString(C1373R.string.str_personal_record_max_elapsed_time));
            this.f11994n.setValue(C2555d.m12802b(personalRecordDataDTO.getMaxCyclingDuration()));
            layoutParams = (LayoutParams) this.f11984d.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            this.f11984d.setLayoutParams(layoutParams);
        } else {
            this.f11981a.setVisibility(8);
            this.f11985e.setLabel(this.f11998r.getString(C1373R.string.str_avg_cadence_with_unit));
            this.f11985e.setValue(personalRecordDataDTO.getAvgCadence() + "");
            this.f11986f.setLabel(this.f11998r.getString(C1373R.string.str_avg_power) + "(" + this.f11998r.getString(C1373R.string.str_unit_power) + ")");
            this.f11986f.setValue(this.f11996p.format((long) personalRecordDataDTO.getAvgPower()));
            this.f11987g.setLabel(this.f11998r.getString(C1373R.string.str_average_heart_rate) + this.f11998r.getString(C1373R.string.label_bpm));
            this.f11987g.setValue(personalRecordDataDTO.getAvgHeartRate() + "");
            this.f11988h.setLabel(this.f11998r.getString(C1373R.string.str_max_cadence));
            this.f11988h.setValue(String.valueOf(personalRecordDataDTO.getMaxCadence()));
            this.f11988h.setUnit(this.f11998r.getString(C1373R.string.str_unit_cadence));
            this.f11989i.setLabel(this.f11998r.getString(C1373R.string.str_max_heart_rate));
            this.f11989i.setValue(String.valueOf(personalRecordDataDTO.getMaxHeartRate()));
            this.f11989i.setUnit(this.f11998r.getString(C1373R.string.str_unit_heart_rate));
            this.f11990j.setLabel(this.f11998r.getString(C1373R.string.str_max_power));
            this.f11990j.setValue(String.valueOf(personalRecordDataDTO.getMaxPower()));
            this.f11990j.setUnit(this.f11998r.getString(C1373R.string.str_unit_power));
            this.f11991k.setLabel(this.f11998r.getString(C1373R.string.str_power_output));
            this.f11991k.setValue(String.valueOf(personalRecordDataDTO.getOutputPower()));
            this.f11991k.setUnit(this.f11998r.getString(C1373R.string.str_unit_output_power));
            this.f11992l.setVisibility(8);
            layoutParams = (LayoutParams) this.f11984d.getLayoutParams();
            layoutParams.setMargins(0, this.f11995o / 2, 0, this.f11995o / 2);
            this.f11984d.setLayoutParams(layoutParams);
        }
    }
}
