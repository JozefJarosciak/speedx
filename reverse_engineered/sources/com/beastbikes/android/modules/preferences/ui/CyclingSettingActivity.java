package com.beastbikes.android.modules.preferences.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.ui.SpeedXForceScreenSettingsActivity;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.activity.ui.BleSensorManagerActivity;
import com.beastbikes.android.widget.materialdesign.MaterialRadioButton;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch.C1706a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903121)
public class CyclingSettingActivity extends SessionFragmentActivity implements OnClickListener, OnCheckedChangeListener, C1371a, C1706a {
    @C1458a(a = 2131755686)
    /* renamed from: a */
    private View f5886a;
    @C1458a(a = 2131755690)
    /* renamed from: b */
    private View f5887b;
    @C1458a(a = 2131755689)
    /* renamed from: c */
    private ViewGroup f5888c;
    @C1458a(a = 2131755687)
    /* renamed from: d */
    private ViewGroup f5889d;
    @C1458a(a = 2131755691)
    /* renamed from: e */
    private ViewGroup f5890e;
    @C1458a(a = 2131755693)
    /* renamed from: f */
    private MaterialRadioButton f5891f;
    @C1458a(a = 2131755695)
    /* renamed from: g */
    private MaterialRadioButton f5892g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5886a.setOnClickListener(this);
        this.f5887b.setOnClickListener(this);
        this.f5891f.setOnCheckedChangeListener(this);
        this.f5892g.setOnCheckedChangeListener(this);
        this.f5889d.setOnClickListener(this);
        Switch switchR = (Switch) this.f5888c.findViewById(C1373R.id.speed_force_setting_switch_value);
        ((TextView) this.f5888c.findViewById(C1373R.id.speed_force_setting_switch_label)).setText(C1373R.string.label_screen_always_on);
        BeastBikes beastBikes = (BeastBikes) getApplication();
        switchR.setChecked(beastBikes.m5259g());
        switchR.setOnCheckedChangeListener(new CyclingSettingActivity$1(this, beastBikes));
        C1398a c1398a = new C1398a((Activity) this);
        switchR = (Switch) this.f5890e.findViewById(C1373R.id.speed_force_setting_switch_value);
        ((TextView) this.f5890e.findViewById(C1373R.id.speed_force_setting_switch_label)).setText(C1373R.string.setting_fragment_item_auto_pause);
        switchR.setChecked(beastBikes.m5249b());
        switchR.setOnCheckedChangeListener(new CyclingSettingActivity$2(this, c1398a));
        if (C1849a.b(this)) {
            this.f5892g.setChecked(true);
        } else {
            this.f5891f.setChecked(true);
        }
    }

    protected void onResume() {
        super.onResume();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            switch (compoundButton.getId()) {
                case C1373R.id.activity_cycling_setting_miles_radiobutton:
                    defaultSharedPreferences.edit().putInt("km_or_mi", 1).apply();
                    this.f5891f.setIsClickable(false);
                    this.f5892g.setIsClickable(true);
                    this.f5892g.setChecked(false);
                    this.f5892g.invalidate();
                    return;
                case C1373R.id.activity_cycling_setting_kilometer_radiobutton:
                    defaultSharedPreferences.edit().putInt("km_or_mi", 0).apply();
                    this.f5892g.setIsClickable(false);
                    this.f5891f.setIsClickable(true);
                    this.f5891f.setChecked(false);
                    this.f5891f.invalidate();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    public void m7132a(Switch switchR, boolean z) {
        ((BeastBikes) getApplication()).m5247a(z);
        if (!z) {
            LocalActivity a = new C1398a((Activity) this).m5861a();
            if (a != null && a.getState() == 3) {
                Intent intent = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.putExtra(C0861d.f2143o, "com.beastbikes.intent.action.ACTIVITY_RESUME");
                intent.setPackage(getPackageName());
                startService(intent);
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.cycling_page_settings:
                startActivity(new Intent(this, SpeedXForceScreenSettingsActivity.class));
                return;
            case C1373R.id.bluetooth_sensor_settings:
                startActivity(new Intent(this, BleSensorManagerActivity.class));
                return;
            case C1373R.id.setting_fragment_item_voice_prompt:
                startActivity(new Intent(this, VoiceFeedbackSettingActivity.class));
                return;
            default:
                return;
        }
    }
}
