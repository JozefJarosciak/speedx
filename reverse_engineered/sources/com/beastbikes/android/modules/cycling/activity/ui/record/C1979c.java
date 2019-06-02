package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.activity.ui.record.p122b.C1976h;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2024b;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2031f;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2033h;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2034i;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2035j;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2036k;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.dto.C2411a;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ChartViewFactory */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.c */
class C1979c {
    /* renamed from: a */
    private Context f8894a;
    /* renamed from: b */
    private C2035j f8895b = new C2035j(this.f8894a);
    /* renamed from: c */
    private C2035j f8896c = new C2035j(this.f8894a);
    /* renamed from: d */
    private C2034i f8897d = new C2034i(this.f8894a);
    /* renamed from: e */
    private C2033h f8898e = new C2033h(this.f8894a);
    /* renamed from: f */
    private C2036k f8899f = new C2036k(this.f8894a);
    /* renamed from: g */
    private C2031f f8900g = new C2031f(this.f8894a);
    /* renamed from: h */
    private C2036k f8901h = new C2036k(this.f8894a);
    /* renamed from: i */
    private ActivityDTO f8902i;
    /* renamed from: j */
    private C1977b f8903j = C1977b.m10160a();
    /* renamed from: k */
    private DecimalFormat f8904k = new DecimalFormat("0.0");
    /* renamed from: l */
    private DecimalFormat f8905l = new DecimalFormat("0");
    /* renamed from: m */
    private ArrayList<C2024b> f8906m = new ArrayList();

    C1979c(Context context) {
        this.f8894a = context;
        this.f8906m.add(this.f8895b);
        this.f8906m.add(this.f8896c);
        this.f8906m.add(this.f8897d);
        this.f8906m.add(this.f8898e);
        this.f8906m.add(this.f8899f);
        this.f8906m.add(this.f8900g);
        this.f8906m.add(this.f8901h);
    }

    /* renamed from: a */
    void m10201a() {
        this.f8906m.clear();
        this.f8906m = null;
        this.f8895b = null;
        this.f8896c = null;
        this.f8897d = null;
        this.f8898e = null;
        this.f8899f = null;
        this.f8900g = null;
        this.f8901h = null;
        this.f8903j.m10169b();
    }

    /* renamed from: a */
    ArrayList<C2024b> m10200a(ActivityDTO activityDTO, boolean z) {
        if (activityDTO.getMaxCardiacRate() <= 0.0d) {
            this.f8906m.remove(this.f8900g);
        }
        if (activityDTO.getMaxPower() <= 0.0d) {
            this.f8906m.remove(this.f8898e);
        }
        if (!z) {
            if (activityDTO.getMaxCardiacRate() <= 0.0d) {
                this.f8906m.remove(this.f8899f);
            }
            if (activityDTO.getMaxPower() <= 0.0d) {
                this.f8906m.remove(this.f8897d);
            }
            if (activityDTO.getMaxCadence() <= 0.0d) {
                this.f8906m.remove(this.f8901h);
            }
        }
        return this.f8906m;
    }

    /* renamed from: a */
    void m10205a(ActivityDTO activityDTO, List<C2411a> list, ArrayList<C2411a> arrayList) {
        if (activityDTO != null && list != null && !list.isEmpty()) {
            this.f8903j.m10162a(activityDTO.getTotalDistance());
            this.f8902i = activityDTO;
            this.f8903j.m10165a(activityDTO);
            this.f8903j.m10166a((ArrayList) arrayList);
        }
    }

    /* renamed from: a */
    void m10202a(double d, List<Double> list) {
        if (list != null && !list.isEmpty()) {
            this.f8903j.m10163a(list.size());
            this.f8903j.m10168a((List) list);
            m10194a(list.size());
        }
    }

    /* renamed from: a */
    void m10207a(List<Double> list, double d, double d2) {
        this.f8903j.m10170b(list);
        m10193a(d, d2);
    }

    /* renamed from: a */
    void m10203a(ActivityDTO activityDTO, int i, List<Double> list) {
        this.f8902i = activityDTO;
        this.f8903j.m10172c(list);
        m10195a(activityDTO);
        this.f8903j.m10164a((long) activityDTO.getElapsedTime(), i, list);
        m10196b(i);
    }

    /* renamed from: a */
    void m10206a(List<Double> list) {
        this.f8903j.m10174d(list);
        m10197c();
    }

    /* renamed from: a */
    void m10204a(ActivityDTO activityDTO, ArrayList<Double> arrayList) {
        this.f8903j.m10167a(arrayList, (long) activityDTO.getElapsedTime());
        m10198d();
        m10199e();
    }

    /* renamed from: b */
    ArrayList<C2024b> m10208b() {
        return this.f8906m;
    }

    /* renamed from: a */
    private void m10193a(double d, double d2) {
        String string;
        this.f8896c.setLineColor(0);
        this.f8896c.setFillDrawable(ContextCompat.getDrawable(this.f8894a, C1373R.drawable.bg_fade_gray));
        this.f8896c.m10449a(this.f8894a.getString(C1373R.string.str_elevation), 2, -1);
        this.f8896c.setXAxisUnit(C1849a.m9645b(this.f8894a) ? "KM" : "MI");
        if (d < 0.0d) {
            this.f8896c.setYMaxValue(0.0f);
        } else {
            this.f8896c.setYMaxValue((float) (1.3d * d));
        }
        this.f8896c.setXValueFormatter(new C1976h(this.f8902i.getElapsedTime()));
        this.f8896c.setAverageLineValue(this.f8903j.m10177g());
        this.f8896c.setYMaxValue((float) d);
        this.f8896c.setYMinValue(0.0f);
        this.f8896c.setXMaxValue(((float) this.f8903j.m10176f().size()) - 1.0f);
        if (this.f8902i != null) {
            this.f8896c.setTotalDistance((float) this.f8902i.getTotalDistance());
        }
        this.f8896c.mo3356a();
        if (C1849a.m9645b(this.f8894a)) {
            string = this.f8894a.getString(C1373R.string.str_meter);
        } else {
            string = this.f8894a.getString(C1373R.string.str_feet);
        }
        this.f8896c.setLeftLabel((this.f8894a.getString(C1373R.string.str_total_ascent) + "(" + string + ")").toUpperCase());
        this.f8896c.setMiddleLabel((this.f8894a.getString(C1373R.string.str_max_altitude) + "(" + string + ")").toUpperCase());
        this.f8896c.setRightLabel((this.f8894a.getString(C1373R.string.str_total_descent) + "(" + string + ")").toUpperCase());
        this.f8896c.setYAxisUnit(string);
        if (this.f8902i != null) {
            this.f8896c.setMiddleValue(this.f8905l.format(this.f8902i.getMaxAltitude()));
            this.f8896c.setRightValue(this.f8905l.format(this.f8902i.getTotalDecline()));
            this.f8896c.setLeftValue(this.f8905l.format(this.f8902i.getRiseTotal()));
        }
        this.f8896c.setData(this.f8903j.m10176f());
    }

    /* renamed from: a */
    private void m10194a(int i) {
        String string;
        this.f8895b.setLineColor(0);
        this.f8895b.setFillDrawable(ContextCompat.getDrawable(this.f8894a, C1373R.drawable.bg_fade_green));
        this.f8895b.m10449a(this.f8894a.getString(C1373R.string.str_speed), 1, -16450648);
        this.f8895b.setXMaxValue((float) (i - 1));
        this.f8895b.setXValueFormatter(new C1976h(this.f8902i.getElapsedTime()));
        if (this.f8902i != null) {
            this.f8895b.setAverageLineValue((float) this.f8902i.getVelocity());
            this.f8895b.setTotalDistance((float) this.f8902i.getTotalDistance());
        }
        this.f8895b.mo3356a();
        if (C1849a.m9645b(this.f8894a)) {
            string = this.f8894a.getString(C1373R.string.str_unit_km_per_hour);
        } else {
            string = this.f8894a.getString(C1373R.string.str_unit_mile_per_hour);
        }
        this.f8895b.setYAxisUnit(string);
        this.f8895b.setLeftLabel((this.f8894a.getString(C1373R.string.str_average_speed) + "(" + string + ")").toUpperCase());
        this.f8895b.setMiddleLabel((this.f8894a.getString(C1373R.string.str_max_speed) + "(" + string + ")").toUpperCase());
        this.f8895b.setRightLabel(this.f8894a.getString(C1373R.string.str_calorie).toUpperCase() + "(" + this.f8894a.getString(C1373R.string.str_unit_calorie) + ")");
        if (this.f8902i != null) {
            this.f8895b.setLeftValue(this.f8904k.format(this.f8902i.getVelocity()));
            this.f8895b.setMiddleValue(this.f8904k.format(this.f8902i.getMaxVelocity()));
            this.f8895b.setRightValue(this.f8905l.format(this.f8902i.getCalories()));
        }
        this.f8895b.setData(this.f8903j.m10175e());
    }

    /* renamed from: a */
    private void m10195a(ActivityDTO activityDTO) {
        float maxCardiacRate = (float) activityDTO.getMaxCardiacRate();
        this.f8899f.setXValueFormatter(new C1976h(this.f8902i.getElapsedTime()));
        this.f8899f.setLineColor(0);
        this.f8899f.setFillDrawable(ContextCompat.getDrawable(this.f8894a, C1373R.drawable.bg_fade_red));
        this.f8899f.m10449a(this.f8894a.getString(C1373R.string.str_heart_rate), 4, -65494);
        this.f8899f.setXAxisUnit(C1849a.m9645b(this.f8894a) ? "KM" : "MI");
        this.f8899f.setYMaxValue(maxCardiacRate * 1.5f);
        this.f8899f.setTotalDistance((float) this.f8902i.getTotalDistance());
        float cardiacRate = (float) activityDTO.getCardiacRate();
        if (cardiacRate <= 0.0f) {
            cardiacRate = this.f8903j.m10179i();
        }
        this.f8899f.setAverageLineValue(cardiacRate);
        this.f8899f.mo3356a();
        if (this.f8902i.getMaxCardiacRate() <= 0.0d) {
            this.f8899f.m10486a(true);
            this.f8899f.setData(null);
            return;
        }
        this.f8899f.setYAxisUnit(this.f8894a.getString(C1373R.string.str_unit_heart_rate));
        this.f8899f.setLeftLabel((this.f8894a.getString(C1373R.string.str_average_heart_rate) + this.f8894a.getString(C1373R.string.label_bpm)).toUpperCase());
        this.f8899f.setLeftValue(this.f8905l.format((double) cardiacRate));
        this.f8899f.setRightLabel((this.f8894a.getString(C1373R.string.str_max_heart_rate) + this.f8894a.getString(C1373R.string.label_bpm)).toUpperCase());
        this.f8899f.setRightValue(this.f8905l.format(this.f8902i.getMaxCardiacRate()));
        this.f8899f.setAverageLineValue((float) this.f8902i.getCardiacRate());
        this.f8899f.setData(this.f8903j.m10178h());
    }

    /* renamed from: b */
    private void m10196b(int i) {
        this.f8900g.setMaxHeartRate(i);
        this.f8900g.mo3359b();
        this.f8900g.m10462c();
        this.f8900g.m10449a(this.f8894a.getString(C1373R.string.str_heart_rate_zone), 5, -65494);
        if (this.f8902i.getMaxCardiacRate() <= 0.0d) {
            this.f8900g.setData(null);
            return;
        }
        double cardiacRate = this.f8902i.getCardiacRate();
        if (cardiacRate <= 0.0d) {
            cardiacRate = (double) this.f8903j.m10179i();
        }
        this.f8900g.setLeftValue(this.f8905l.format(cardiacRate));
        this.f8900g.setRightValue(this.f8905l.format(this.f8902i.getMaxCardiacRate()));
        this.f8900g.setData(this.f8903j.m10180j());
    }

    /* renamed from: c */
    private void m10197c() {
        this.f8901h.setXValueFormatter(new C1976h(this.f8902i.getElapsedTime()));
        this.f8901h.setFillDrawable(ContextCompat.getDrawable(this.f8894a, C1373R.drawable.bg_fade_blue_for_cadence));
        this.f8901h.m10449a(this.f8894a.getString(C1373R.string.str_cadence), 3, -16733185);
        this.f8901h.setAverageLineValue(this.f8903j.m10184n());
        this.f8901h.mo3356a();
        this.f8901h.setYAxisUnit(this.f8894a.getString(C1373R.string.str_unit_cadence));
        this.f8901h.setLeftLabel(this.f8894a.getString(C1373R.string.str_avg_cadence_with_unit).toUpperCase());
        this.f8901h.setRightLabel(this.f8894a.getString(C1373R.string.str_max_cadence_with_unit).toUpperCase());
        ArrayList k = this.f8903j.m10181k();
        if (k == null || k.isEmpty() || this.f8902i.getMaxCadence() <= 0.0d) {
            this.f8901h.m10486a(false);
            this.f8901h.setData(null);
            return;
        }
        double cadence = this.f8902i.getCadence();
        if (cadence <= 0.0d) {
            cadence = (double) this.f8903j.m10184n();
        }
        this.f8901h.setLeftValue(this.f8905l.format(cadence));
        this.f8901h.setRightValue(this.f8905l.format(this.f8902i.getMaxCadence()));
        this.f8901h.setData(this.f8903j.m10181k());
    }

    /* renamed from: d */
    private void m10198d() {
        double o;
        this.f8897d.setLineColor(0);
        boolean isVirtualPower = this.f8902i.isVirtualPower();
        this.f8897d.setFillDrawable(ContextCompat.getDrawable(this.f8894a, isVirtualPower ? C1373R.drawable.bg_fade_gray : C1373R.drawable.bg_fade_red_for_power));
        this.f8897d.m10449a(this.f8894a.getString(C1373R.string.str_power), 6, isVirtualPower ? -10066330 : -377847);
        if (isVirtualPower) {
            this.f8897d.setVirtual(true);
            this.f8897d.m10452g();
        }
        this.f8897d.setXAxisUnit("");
        this.f8897d.setYAxisUnit(this.f8894a.getString(C1373R.string.str_unit_power));
        this.f8897d.setYMinValue(0.0f);
        this.f8897d.setTotalDistance((float) this.f8902i.getTotalDistance());
        this.f8897d.setAverageLineValue((float) this.f8902i.getAvgPower());
        this.f8897d.setXValueFormatter(new C1976h(this.f8902i.getElapsedTime()));
        if (this.f8902i.getAvgPower() <= 0.0d) {
            o = (double) this.f8903j.m10185o();
        } else {
            o = this.f8902i.getAvgPower();
        }
        this.f8897d.setAverageLineValue((float) o);
        this.f8897d.mo3356a();
        ArrayList l = this.f8903j.m10182l();
        if (l == null || l.isEmpty() || this.f8902i.getMaxPower() <= 0.0d) {
            this.f8897d.mo3359b();
            this.f8897d.setData(null);
            return;
        }
        this.f8897d.setLeftTopValue(this.f8902i.getMax20MinutesPower() + "");
        this.f8897d.setMiddleTopValue(this.f8902i.getStandardPower() + "");
        this.f8897d.setRightTopValue(this.f8905l.format(o));
        this.f8897d.setTopItemValue(this.f8905l.format(o));
        this.f8897d.setLeftBottomValue(this.f8904k.format(this.f8902i.getPowerIF()));
        this.f8897d.setMiddleBottomValue(this.f8902i.getPowerTSS() + "");
        this.f8897d.setRightBottomValue(this.f8902i.getPowerFTP() + "");
        this.f8897d.setData(this.f8903j.m10182l());
    }

    /* renamed from: e */
    private void m10199e() {
        this.f8898e.mo3359b();
        this.f8898e.m10462c();
        boolean isVirtualPower = this.f8902i.isVirtualPower();
        this.f8898e.m10449a(this.f8894a.getString(C1373R.string.str_power_distribution), 7, isVirtualPower ? -10066330 : -377847);
        if (isVirtualPower) {
            this.f8898e.m10452g();
            this.f8898e.setVirtual(true);
        }
        ArrayList m = this.f8903j.m10183m();
        if (m == null || m.isEmpty() || this.f8902i.getMaxPower() <= 0.0d) {
            this.f8898e.setData(null);
            return;
        }
        this.f8898e.setLeftTopValue(this.f8902i.getMax20MinutesPower() + "");
        this.f8898e.setMiddleTopValue(this.f8902i.getStandardPower() + "");
        double o = this.f8902i.getAvgPower() <= 0.0d ? (double) this.f8903j.m10185o() : this.f8902i.getAvgPower();
        this.f8898e.setRightTopValue(this.f8905l.format(o));
        this.f8898e.setTopItemValue(this.f8905l.format(o));
        this.f8898e.setLeftBottomValue(this.f8904k.format(this.f8902i.getPowerIF()));
        this.f8898e.setMiddleBottomValue(this.f8902i.getPowerTSS() + "");
        this.f8898e.setRightBottomValue(this.f8902i.getPowerFTP() + "");
        this.f8898e.setData(this.f8903j.m10183m());
    }
}
