package com.beastbikes.android.ble.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.C1602a;
import com.beastbikes.android.ble.C1603b;
import com.beastbikes.android.ble.CentralService;
import com.beastbikes.android.ble.CentralService.C1596c;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p097b.C1630n;
import com.beastbikes.android.ble.protocol.v1.DeviceInfoCommandCharacteristic;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.widget.C2657e;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903195)
public class SpeedXSettingActivity extends SessionFragmentActivity implements ServiceConnection, OnClickListener, C1630n {
    /* renamed from: a */
    private static final Logger f4356a = LoggerFactory.getLogger("SpeedXSettingActivity");
    @C1458a(a = 2131756049)
    /* renamed from: b */
    private ViewGroup f4357b;
    /* renamed from: c */
    private TextView f4358c;
    @C1458a(a = 2131756047)
    /* renamed from: d */
    private ViewGroup f4359d;
    /* renamed from: e */
    private TextView f4360e;
    @C1458a(a = 2131756048)
    /* renamed from: f */
    private ViewGroup f4361f;
    /* renamed from: g */
    private TextView f4362g;
    @C1458a(a = 2131756054)
    /* renamed from: h */
    private ViewGroup f4363h;
    /* renamed from: i */
    private Switch f4364i;
    @C1458a(a = 2131756055)
    /* renamed from: j */
    private ViewGroup f4365j;
    /* renamed from: k */
    private Switch f4366k;
    @C1458a(a = 2131756052)
    /* renamed from: l */
    private ViewGroup f4367l;
    @C1458a(a = 2131756050)
    /* renamed from: m */
    private ViewGroup f4368m;
    @C1458a(a = 2131756051)
    /* renamed from: n */
    private ViewGroup f4369n;
    @C1458a(a = 2131756053)
    /* renamed from: o */
    private ViewGroup f4370o;
    /* renamed from: p */
    private TextView f4371p;
    /* renamed from: q */
    private DeviceInfoCommandCharacteristic f4372q;
    /* renamed from: r */
    private C1602a f4373r;
    /* renamed from: s */
    private int f4374s = 1;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m5726a();
        this.f4372q = (DeviceInfoCommandCharacteristic) getIntent().getSerializableExtra("device_info");
        if (this.f4372q == null) {
            f4356a.error("SpeedXSettingActivity onCreate DeviceInfo is null");
            finish();
            return;
        }
        m5727a(this.f4372q);
        this.f4374s = this.f4372q.getHardwareType();
        this.f4368m.setVisibility(8);
        switch (this.f4374s) {
            case 0:
            case 2:
                this.f4369n.setVisibility(8);
                this.f4370o.setVisibility(8);
                this.f4367l.setVisibility(8);
                break;
            case 1:
                this.f4369n.setVisibility(8);
                this.f4370o.setVisibility(8);
                this.f4367l.setVisibility(0);
                break;
            case 3:
                this.f4369n.setVisibility(8);
                this.f4370o.setVisibility(8);
                this.f4367l.setVisibility(8);
                break;
            case 4:
                this.f4369n.setVisibility(0);
                this.f4370o.setVisibility(0);
                this.f4367l.setVisibility(8);
                this.f4368m.setVisibility(0);
                break;
        }
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.setPackage(getPackageName());
        bindService(intent, this, 1);
    }

    public void finish() {
        Intent intent = getIntent();
        intent.putExtra("device_info", this.f4372q);
        setResult(-1, intent);
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f4373r != null) {
            unbindService(this);
        }
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.speed_force_setting_language:
                m5732c(this.f4360e.getText().toString());
                return;
            case C1373R.id.speed_force_setting_mileage_unit:
                m5734d(this.f4362g.getText().toString());
                return;
            case C1373R.id.speed_force_setting_diameter:
                m5730b(this.f4358c.getText().toString());
                return;
            case C1373R.id.speed_force_setting_training_target:
                startActivity(new Intent(this, SpeedXTrainingTargetActivity.class));
                return;
            case C1373R.id.speed_force_setting_display_setting:
                intent = new Intent(this, SpeedXForceScreenSettingsActivity.class);
                intent.putExtra("screen_settings_type", 2);
                startActivity(intent);
                return;
            case C1373R.id.speed_force_setting_back_auto_light:
                m5729b();
                return;
            case C1373R.id.speed_force_setting_wifi:
                intent = new Intent(this, SpeedXWiFiInfoActivity.class);
                intent.putExtra("wifi_uuid", this.f4371p.getText().toString());
                startActivity(intent);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m5736a(String str) {
        if (TextUtils.isEmpty(str)) {
            f4356a.info("onResponseWifi(), wifi is null");
            this.f4371p.setText(C1373R.string.str_disconnect);
            return;
        }
        this.f4371p.setText(str);
    }

    /* renamed from: a */
    private void m5726a() {
        this.f4357b.setOnClickListener(this);
        ((TextView) this.f4357b.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.label_diameter);
        this.f4358c = (TextView) this.f4357b.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f4359d.setOnClickListener(this);
        ((TextView) this.f4359d.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.label_language);
        this.f4360e = (TextView) this.f4359d.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f4361f.setOnClickListener(this);
        ((TextView) this.f4361f.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.ble_distance_unit_label);
        this.f4362g = (TextView) this.f4361f.findViewById(C1373R.id.speed_force_setting_item_value);
        ((TextView) this.f4363h.findViewById(C1373R.id.speed_force_setting_switch_label)).setText(C1373R.string.label_message);
        this.f4364i = (Switch) this.f4363h.findViewById(C1373R.id.speed_force_setting_switch_value);
        this.f4364i.setOnCheckedChangeListener(new SpeedXSettingActivity$1(this));
        ((TextView) this.f4365j.findViewById(C1373R.id.speed_force_setting_switch_label)).setText(C1373R.string.label_vibration_wake);
        this.f4366k = (Switch) this.f4365j.findViewById(C1373R.id.speed_force_setting_switch_value);
        this.f4366k.setOnCheckedChangeListener(new SpeedXSettingActivity$2(this));
        this.f4367l.setVisibility(8);
        this.f4367l.setOnClickListener(this);
        ((TextView) this.f4367l.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_ble_taillight_setting);
        this.f4368m.setOnClickListener(this);
        ((TextView) this.f4368m.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_ble_config_training_target);
        this.f4369n.setOnClickListener(this);
        ((TextView) this.f4369n.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_ble_config_display_setting);
        ((TextView) this.f4370o.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_ble_config_wifi_setting);
        this.f4371p = (TextView) this.f4370o.findViewById(C1373R.id.speed_force_setting_item_value);
    }

    /* renamed from: a */
    private void m5727a(DeviceInfoCommandCharacteristic deviceInfoCommandCharacteristic) {
        if (deviceInfoCommandCharacteristic != null) {
            boolean z;
            int locale = deviceInfoCommandCharacteristic.getLocale();
            String[] stringArray = getResources().getStringArray(C1373R.array.select_language_array);
            if (locale >= 0 && locale < stringArray.length) {
                this.f4360e.setText(stringArray[locale]);
            }
            locale = deviceInfoCommandCharacteristic.getMileageUnit();
            stringArray = getResources().getStringArray(C1373R.array.select_mileage_unit_array);
            if (locale >= 0 && locale < stringArray.length) {
                this.f4362g.setText(stringArray[locale]);
            }
            this.f4364i.setChecked(deviceInfoCommandCharacteristic.getNotification() == 1);
            Switch switchR = this.f4366k;
            if (deviceInfoCommandCharacteristic.getShakeUp() == 1) {
                z = true;
            } else {
                z = false;
            }
            switchR.setChecked(z);
            this.f4374s = deviceInfoCommandCharacteristic.getHardwareType();
            if (this.f4374s == 1) {
                this.f4367l.setVisibility(0);
            }
            int wheelType = deviceInfoCommandCharacteristic.getWheelType();
            int[] intArray = getResources().getIntArray(C1373R.array.select_wheel_value_array);
            locale = 0;
            while (locale < intArray.length) {
                if (intArray[locale] == wheelType) {
                    break;
                }
                locale++;
            }
            locale = 0;
            String[] stringArray2 = getResources().getStringArray(C1373R.array.select_wheel_array);
            if (locale >= 0 && locale < stringArray2.length) {
                this.f4358c.setText(stringArray2[locale]);
            }
        }
    }

    /* renamed from: b */
    private void m5730b(String str) {
        if (C1661h.a().b() == null) {
            Toasts.show(this, getString(C1373R.string.str_ble_retry_after_connect_to_device));
            return;
        }
        String[] stringArray = getResources().getStringArray(C1373R.array.select_wheel_array);
        if (stringArray.length > 0) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 0; i2 < stringArray.length; i2++) {
                if (str.equals(stringArray[i2])) {
                    i = i2;
                }
                arrayList.add(stringArray[i2]);
            }
            new C2657e(this, arrayList, i, new SpeedXSettingActivity$3(this)).showAtLocation(findViewById(C1373R.id.activity_speedx_setting), 81, 0, 0);
        }
    }

    /* renamed from: c */
    private void m5732c(String str) {
        if (C1661h.a().b() == null) {
            Toasts.show(this, getString(C1373R.string.str_ble_retry_after_connect_to_device));
            return;
        }
        String[] stringArray = getResources().getStringArray(C1373R.array.select_language_array);
        if (stringArray.length > 0) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 0; i2 < stringArray.length; i2++) {
                if (str.equals(stringArray[i2])) {
                    i = i2;
                }
                arrayList.add(stringArray[i2]);
            }
            new C2657e(this, arrayList, i, new SpeedXSettingActivity$4(this)).showAtLocation(findViewById(C1373R.id.activity_speedx_setting), 81, 0, 0);
        }
    }

    /* renamed from: b */
    private void m5729b() {
        if (C1661h.a().b() == null) {
            Toasts.show(this, getString(C1373R.string.str_ble_retry_after_connect_to_device));
        } else if (this.f4372q == null) {
            f4356a.error("selectBackLightWindow(), Device Info is null");
        } else {
            int a = m5723a(this.f4372q.getAutolight());
            String[] stringArray = getResources().getStringArray(C1373R.array.select_back_light_array);
            if (stringArray.length > 0) {
                Object arrayList = new ArrayList();
                Collections.addAll(arrayList, stringArray);
                new C2657e(this, arrayList, a, new SpeedXSettingActivity$5(this)).showAtLocation(findViewById(C1373R.id.activity_speedx_setting), 81, 0, 0);
            }
        }
    }

    /* renamed from: a */
    private int m5723a(int i) {
        switch (i) {
            case 0:
                return 0;
            case 1:
                return 2;
            case 2:
                return 1;
            default:
                return i;
        }
    }

    /* renamed from: d */
    private void m5734d(String str) {
        if (C1661h.a().b() == null) {
            Toasts.show(this, getString(C1373R.string.str_ble_retry_after_connect_to_device));
            return;
        }
        String[] stringArray = getResources().getStringArray(C1373R.array.select_mileage_unit_array);
        if (stringArray.length > 0) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            for (int i2 = 0; i2 < stringArray.length; i2++) {
                if (str.equals(stringArray[i2])) {
                    i = i2;
                }
                arrayList.add(stringArray[i2]);
            }
            new C2657e(this, arrayList, i, new SpeedXSettingActivity$6(this)).showAtLocation(findViewById(C1373R.id.activity_speedx_setting), 81, 0, 0);
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        f4356a.info("onServiceConnected");
        CentralService a = ((C1596c) iBinder).a();
        this.f4373r = a.b();
        C1603b c = a.c();
        if (c != null) {
            c.a(this);
            c.e();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        f4356a.info("onServiceDisconnected");
    }
}
