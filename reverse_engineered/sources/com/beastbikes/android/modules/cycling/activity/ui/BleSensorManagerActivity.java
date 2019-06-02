package com.beastbikes.android.modules.cycling.activity.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.p058c.C1386e;
import com.beastbikes.android.ble.biz.p058c.C1647h;
import com.beastbikes.android.ble.biz.p058c.C1648i;
import com.beastbikes.android.ble.biz.p058c.C1649j;
import com.beastbikes.android.ble.biz.p058c.C1650m;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.utils.p080a.C1454a;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.Locale;

@C1459b(a = 2130903082)
public class BleSensorManagerActivity extends SessionFragmentActivity implements OnClickListener, C1647h, C1648i, C1649j, C1650m {
    @C1458a(a = 2131755407)
    /* renamed from: a */
    private ViewGroup f4505a;
    @C1458a(a = 2131755408)
    /* renamed from: b */
    private ViewGroup f4506b;
    /* renamed from: c */
    private TextView f4507c;
    @C1458a(a = 2131755409)
    /* renamed from: d */
    private ViewGroup f4508d;
    @C1458a(a = 2131755410)
    /* renamed from: e */
    private ViewGroup f4509e;
    /* renamed from: f */
    private TextView f4510f;
    @C1458a(a = 2131755411)
    /* renamed from: g */
    private ViewGroup f4511g;
    @C1458a(a = 2131755412)
    /* renamed from: h */
    private ViewGroup f4512h;
    /* renamed from: i */
    private TextView f4513i;
    @C1458a(a = 2131755413)
    /* renamed from: j */
    private ViewGroup f4514j;
    @C1458a(a = 2131755414)
    /* renamed from: k */
    private ViewGroup f4515k;
    /* renamed from: l */
    private TextView f4516l;
    @C1458a(a = 2131755415)
    /* renamed from: m */
    private ViewGroup f4517m;
    @C1458a(a = 2131755416)
    /* renamed from: n */
    private ViewGroup f4518n;
    /* renamed from: o */
    private TextView f4519o;
    /* renamed from: p */
    private LocalActivity f4520p;
    /* renamed from: q */
    private boolean f4521q;
    /* renamed from: r */
    private String f4522r;
    /* renamed from: s */
    private int f4523s;
    /* renamed from: t */
    private double f4524t;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m5916d();
        this.f4521q = C1849a.b(this);
        this.f4520p = new C1398a((Activity) this).m5861a();
        C1386e.m5414b().m5427c();
        C1386e.m5414b().m5419a((C1650m) this);
        C1386e.m5414b().m5416a((C1647h) this);
        C1386e.m5414b().m5417a((C1648i) this);
        C1386e.m5414b().m5418a((C1649j) this);
    }

    public void finish() {
        if (this.f4520p == null) {
            C1386e.m5414b().m5429d();
        }
        C1386e.m5414b().m5425b((C1649j) this);
        C1386e.m5414b().m5424b((C1648i) this);
        C1386e.m5414b().m5423b((C1647h) this);
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    /* renamed from: a */
    public void m5920a(int i, String str) {
    }

    /* renamed from: d */
    public void m5925d(int i) {
        runOnUiThread(new BleSensorManagerActivity$1(this, i));
    }

    /* renamed from: b */
    public void m5922b(int i) {
        this.f4507c.setText(String.format(Locale.getDefault(), "%dBPM", new Object[]{Integer.valueOf(i)}));
    }

    /* renamed from: b */
    public void m5921b() {
        m5915b(1, null);
    }

    /* renamed from: c */
    public void m5924c(int i) {
        this.f4510f.setText(String.format(Locale.getDefault(), "%dRPM", new Object[]{Integer.valueOf(i)}));
    }

    /* renamed from: c */
    public void m5923c() {
        m5915b(2, null);
    }

    /* renamed from: a */
    public void m5917a() {
        m5915b(5, null);
        m5915b(3, null);
        m5915b(4, null);
    }

    /* renamed from: a */
    public void m5918a(double d) {
        this.f4524t = d;
        this.f4522r = "KM/H";
        if (!this.f4521q) {
            this.f4524t = C1849a.d(d);
            this.f4522r = "MPH";
        }
        CharSequence charSequence = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(this.f4524t)}) + this.f4522r;
        this.f4519o.setText(charSequence + "  " + String.format(Locale.getDefault(), "%dRPM", new Object[]{Integer.valueOf(this.f4523s)}));
        this.f4513i.setText(charSequence);
    }

    /* renamed from: a */
    public void m5919a(int i) {
        this.f4523s = i;
        String str = String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(this.f4524t)}) + this.f4522r;
        CharSequence format = String.format(Locale.getDefault(), "%dRPM", new Object[]{Integer.valueOf(this.f4523s)});
        this.f4519o.setText(str + "  " + format);
        this.f4516l.setText(format);
    }

    public void onClick(View view) {
        int i = 1;
        Intent intent = new Intent(this, BleSensorListActivity.class);
        String string = getString(C1373R.string.str_heart_rate);
        switch (view.getId()) {
            case C1373R.id.ble_sensor_manager_heart_rate_parent:
                string = getString(C1373R.string.str_heart_rate);
                break;
            case C1373R.id.ble_sensor_manager_power_parent:
                i = 2;
                string = getString(C1373R.string.str_power);
                break;
            case C1373R.id.ble_sensor_manager_speed_parent:
                string = getString(C1373R.string.str_ble_sensor_speed);
                i = 5;
                break;
            case C1373R.id.ble_sensor_manager_cadence_parent:
                string = getString(C1373R.string.str_ble_sensor_cadence);
                i = 5;
                break;
            case C1373R.id.ble_sensor_manager_speed_and_cadence_parent:
                string = getString(C1373R.string.str_ble_sensor_csc);
                i = 5;
                break;
        }
        intent.putExtra("sensor_type", i);
        intent.putExtra("sensor_title", string);
        startActivity(intent);
    }

    /* renamed from: d */
    private void m5916d() {
        this.f4505a.setOnClickListener(this);
        ((TextView) this.f4506b.findViewById(C1373R.id.speed_force_setting_switch_label)).setText(C1373R.string.str_heart_rate);
        this.f4507c = (TextView) this.f4506b.findViewById(C1373R.id.speed_force_setting_switch_desc);
        this.f4507c.setVisibility(0);
        this.f4507c.setText(C1373R.string.str_disconnect);
        Switch switchR = (Switch) this.f4506b.findViewById(C1373R.id.speed_force_setting_switch_value);
        switchR.setChecked(C1454a.m7990a().m7996a((Context) this, "PREF.SENSOR.HR", false));
        switchR.setOnCheckedChangeListener(new BleSensorManagerActivity$2(this));
        this.f4508d.setOnClickListener(this);
        ((TextView) this.f4509e.findViewById(C1373R.id.speed_force_setting_switch_label)).setText(C1373R.string.str_power);
        this.f4510f = (TextView) this.f4509e.findViewById(C1373R.id.speed_force_setting_switch_desc);
        this.f4510f.setVisibility(0);
        this.f4510f.setText(C1373R.string.str_disconnect);
        switchR = (Switch) this.f4509e.findViewById(C1373R.id.speed_force_setting_switch_value);
        switchR.setChecked(C1454a.m7990a().m7996a((Context) this, "PREF.SENSOR.POWER", false));
        switchR.setOnCheckedChangeListener(new BleSensorManagerActivity$3(this));
        this.f4511g.setOnClickListener(this);
        ((TextView) this.f4512h.findViewById(C1373R.id.speed_force_setting_switch_label)).setText(C1373R.string.str_ble_sensor_speed);
        this.f4513i = (TextView) this.f4512h.findViewById(C1373R.id.speed_force_setting_switch_desc);
        this.f4513i.setVisibility(0);
        this.f4513i.setText(C1373R.string.str_disconnect);
        switchR = (Switch) this.f4512h.findViewById(C1373R.id.speed_force_setting_switch_value);
        switchR.setChecked(C1454a.m7990a().m7996a((Context) this, "PREF.SENSOR.SPEED", false));
        switchR.setOnCheckedChangeListener(new BleSensorManagerActivity$4(this));
        this.f4514j.setOnClickListener(this);
        ((TextView) this.f4515k.findViewById(C1373R.id.speed_force_setting_switch_label)).setText(C1373R.string.str_ble_sensor_cadence);
        this.f4516l = (TextView) this.f4515k.findViewById(C1373R.id.speed_force_setting_switch_desc);
        this.f4516l.setVisibility(0);
        this.f4516l.setText(C1373R.string.str_disconnect);
        switchR = (Switch) this.f4515k.findViewById(C1373R.id.speed_force_setting_switch_value);
        switchR.setChecked(C1454a.m7990a().m7996a((Context) this, "PREF.SENSOR.CADENCE", false));
        switchR.setOnCheckedChangeListener(new BleSensorManagerActivity$5(this));
        this.f4517m.setOnClickListener(this);
        ((TextView) this.f4518n.findViewById(C1373R.id.speed_force_setting_switch_label)).setText(C1373R.string.str_ble_sensor_csc);
        this.f4519o = (TextView) this.f4518n.findViewById(C1373R.id.speed_force_setting_switch_desc);
        this.f4519o.setVisibility(0);
        this.f4519o.setText(C1373R.string.str_disconnect);
        switchR = (Switch) this.f4518n.findViewById(C1373R.id.speed_force_setting_switch_value);
        switchR.setChecked(C1454a.m7990a().m7996a((Context) this, "PREF.SENSOR.SPEED.AND.CADENCE", false));
        switchR.setOnCheckedChangeListener(new BleSensorManagerActivity$6(this));
    }

    /* renamed from: b */
    private void m5915b(int i, String str) {
        TextView textView;
        switch (i) {
            case 1:
                textView = this.f4507c;
                if (TextUtils.isEmpty(str)) {
                    str = getString(C1373R.string.str_disconnect);
                }
                textView.setText(str);
                return;
            case 2:
                textView = this.f4510f;
                if (TextUtils.isEmpty(str)) {
                    str = getString(C1373R.string.str_disconnect);
                }
                textView.setText(str);
                return;
            case 3:
                textView = this.f4513i;
                if (TextUtils.isEmpty(str)) {
                    str = getString(C1373R.string.str_disconnect);
                }
                textView.setText(str);
                return;
            case 4:
                textView = this.f4516l;
                if (TextUtils.isEmpty(str)) {
                    str = getString(C1373R.string.str_disconnect);
                }
                textView.setText(str);
                return;
            case 5:
                textView = this.f4519o;
                if (TextUtils.isEmpty(str)) {
                    str = getString(C1373R.string.str_disconnect);
                }
                textView.setText(str);
                return;
            default:
                return;
        }
    }
}
