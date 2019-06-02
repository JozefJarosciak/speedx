package com.beastbikes.android.ble.ui;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.alipay.sdk.packet.C0861d;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.ble.C1602a;
import com.beastbikes.android.ble.C1603b;
import com.beastbikes.android.ble.CentralService;
import com.beastbikes.android.ble.CentralService.C1596c;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.biz.p097b.C1617a;
import com.beastbikes.android.ble.biz.p097b.C1618b;
import com.beastbikes.android.ble.biz.p097b.C1621e;
import com.beastbikes.android.ble.biz.p097b.C1622f;
import com.beastbikes.android.ble.biz.p097b.C1623g;
import com.beastbikes.android.ble.biz.p097b.C1626j;
import com.beastbikes.android.ble.biz.p097b.C1627k;
import com.beastbikes.android.ble.biz.p097b.C1628l;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.ble.dto.BleCyclingDTO;
import com.beastbikes.android.ble.dto.S605FirmwareInfo;
import com.beastbikes.android.ble.protocol.v1.AGpsInfoCharacteristic;
import com.beastbikes.android.ble.protocol.v1.BatterySensorCharacteristic;
import com.beastbikes.android.ble.protocol.v1.DeviceInfoCommandCharacteristic;
import com.beastbikes.android.ble.protocol.v1.OTAFirmwareInfoCharacteristic;
import com.beastbikes.android.ble.ui.p098a.C1714d;
import com.beastbikes.android.ble.ui.p098a.C1740p;
import com.beastbikes.android.ble.ui.widget.SpeedForceSettingView;
import com.beastbikes.android.ble.ui.widget.TextViewWithBoardAndCorners;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.SyncService;
import com.beastbikes.android.modules.cycling.activity.dto.MyGoalInfoDTO;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.squareup.picasso.Picasso;
import com.umeng.analytics.MobclickAgent;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903191)
@TargetApi(19)
public class SpeedForceActivity extends SessionFragmentActivity implements ServiceConnection, OnClickListener, C1617a, C1618b, C1621e, C1622f, C1623g, C1626j, C1627k, C1628l {
    /* renamed from: A */
    private int f4215A;
    /* renamed from: B */
    private int f4216B = 100;
    /* renamed from: C */
    private OTAFirmwareInfoCharacteristic f4217C;
    /* renamed from: D */
    private DeviceInfoCommandCharacteristic f4218D;
    /* renamed from: E */
    private C1740p f4219E;
    /* renamed from: F */
    private BleDevice f4220F;
    /* renamed from: G */
    private boolean f4221G;
    /* renamed from: H */
    private C1802i f4222H;
    /* renamed from: I */
    private C1714d f4223I;
    /* renamed from: J */
    private boolean f4224J;
    /* renamed from: K */
    private boolean f4225K;
    /* renamed from: L */
    private boolean f4226L;
    /* renamed from: M */
    private SpeedForceActivity$a f4227M;
    /* renamed from: N */
    private boolean f4228N;
    /* renamed from: O */
    private CentralService f4229O;
    /* renamed from: P */
    private boolean f4230P;
    /* renamed from: Q */
    private C1802i f4231Q;
    /* renamed from: R */
    private S605FirmwareInfo f4232R;
    /* renamed from: S */
    private DecimalFormat f4233S;
    /* renamed from: T */
    private BroadcastReceiver f4234T = new SpeedForceActivity$9(this);
    /* renamed from: a */
    private Logger f4235a = LoggerFactory.getLogger("SpeedForceActivity");
    @C1458a(a = 2131755786)
    /* renamed from: b */
    private Toolbar f4236b;
    @C1458a(a = 2131756011)
    /* renamed from: c */
    private TextView f4237c;
    @C1458a(a = 2131756012)
    /* renamed from: d */
    private RelativeLayout f4238d;
    @C1458a(a = 2131756013)
    /* renamed from: e */
    private TextView f4239e;
    @C1458a(a = 2131756014)
    /* renamed from: f */
    private ImageView f4240f;
    @C1458a(a = 2131756015)
    /* renamed from: g */
    private TextView f4241g;
    @C1458a(a = 2131756016)
    /* renamed from: h */
    private ImageView f4242h;
    @C1458a(a = 2131756017)
    /* renamed from: i */
    private TextViewWithBoardAndCorners f4243i;
    @C1458a(a = 2131756021)
    /* renamed from: j */
    private SpeedForceSettingView f4244j;
    @C1458a(a = 2131756018)
    /* renamed from: k */
    private ViewGroup f4245k;
    /* renamed from: l */
    private TextView f4246l;
    @C1458a(a = 2131756019)
    /* renamed from: m */
    private ViewGroup f4247m;
    /* renamed from: n */
    private TextView f4248n;
    @C1458a(a = 2131756020)
    /* renamed from: o */
    private ViewGroup f4249o;
    /* renamed from: p */
    private TextView f4250p;
    @C1458a(a = 2131756023)
    /* renamed from: q */
    private SpeedForceSettingView f4251q;
    @C1458a(a = 2131756026)
    /* renamed from: r */
    private SpeedForceSettingView f4252r;
    @C1458a(a = 2131756025)
    /* renamed from: s */
    private SpeedForceSettingView f4253s;
    @C1458a(a = 2131756024)
    /* renamed from: t */
    private SpeedForceSettingView f4254t;
    @C1458a(a = 2131756027)
    /* renamed from: u */
    private TextView f4255u;
    @C1458a(a = 2131756028)
    /* renamed from: v */
    private LinearLayout f4256v;
    @C1458a(a = 2131756029)
    /* renamed from: w */
    private TextView f4257w;
    /* renamed from: x */
    private C1602a f4258x;
    /* renamed from: y */
    private C1651c f4259y;
    /* renamed from: z */
    private int f4260z = 1;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            this.f4235a.error("SpeedForceActivity onCreate intent is null");
            finish();
            return;
        }
        if (intent.hasExtra("ble_device")) {
            this.f4220F = (BleDevice) intent.getSerializableExtra("ble_device");
            if (this.f4220F != null) {
                this.f4260z = this.f4220F.getHardwareType();
            }
        }
        this.f4233S = new DecimalFormat("##");
        m5557d();
        this.f4259y = new C1651c(this);
        m5586r();
        m5581o();
        this.f4227M = new SpeedForceActivity$a(this, this);
        if (this.f4220F != null) {
            m5558d(C1614a.a(this.f4220F.getDeviceId()));
        }
    }

    protected void onResume() {
        super.onResume();
        m5589t();
        if (this.f4229O != null) {
            this.f4229O.a(this);
            this.f4258x.a(this);
        }
    }

    protected void onDestroy() {
        if (this.f4227M != null) {
            this.f4227M.removeMessages(1);
        }
        if (this.f4234T != null) {
            m5587s();
        }
        if (this.f4229O != null) {
            this.f4229O.a(null);
            unbindService(this);
            this.f4220F = null;
        }
        if (this.f4258x != null) {
            this.f4258x.a(null);
            this.f4258x.a(null);
            this.f4258x.a(null);
            this.f4258x.a(null);
            this.f4258x.a(null);
            this.f4258x.a(null);
        }
        if (!C1661h.a().f()) {
            Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
            intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_STOP_SCAN");
            intent.setPackage(getPackageName());
            startService(intent);
            stopService(intent);
        }
        if (this.f4222H != null) {
            if (this.f4222H.isShowing()) {
                this.f4222H.dismiss();
            }
            this.f4222H = null;
        }
        this.f4221G = true;
        super.onDestroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 83:
                if (i2 == -1) {
                    m5549b(false);
                }
                if (i2 == 82) {
                    Toasts.show(this, C1373R.string.msg_device_disconnect);
                    return;
                }
                return;
            case 84:
                if (i2 == -1) {
                    this.f4218D = (DeviceInfoCommandCharacteristic) intent.getSerializableExtra("device_info");
                    if (this.f4258x != null) {
                        this.f4258x.a();
                        return;
                    }
                    return;
                }
                return;
            case 85:
                if (i2 == 82) {
                    Toasts.show(this, C1373R.string.msg_device_disconnect);
                }
                if (this.f4258x != null) {
                    this.f4258x.a(this);
                    return;
                }
                return;
            case 88:
                if (i2 == -1) {
                    Intent intent2 = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
                    intent2.setPackage(getPackageName());
                    startService(intent2);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m5602a(BatterySensorCharacteristic batterySensorCharacteristic) {
        if ((this.f4220F == null || C1661h.a().d(this.f4220F.getMacAddress())) && batterySensorCharacteristic != null) {
            this.f4216B = batterySensorCharacteristic.getPercentage();
            this.f4215A = batterySensorCharacteristic.getChargeState();
            this.f4256v.setVisibility(0);
            this.f4257w.setText(String.valueOf(this.f4216B));
        }
    }

    /* renamed from: a */
    public void m5599a(BleDevice bleDevice, DeviceInfoCommandCharacteristic deviceInfoCommandCharacteristic) {
        if (bleDevice != null && deviceInfoCommandCharacteristic != null) {
            this.f4220F = bleDevice;
            this.f4216B = deviceInfoCommandCharacteristic.getBattery();
            this.f4256v.setVisibility(0);
            this.f4257w.setText(String.valueOf(this.f4216B));
            this.f4218D = deviceInfoCommandCharacteristic;
            this.f4241g.setText(BleDevice.brandType2Name(deviceInfoCommandCharacteristic.getBrandType()));
            this.f4237c.setText(bleDevice.getDeviceName());
            this.f4260z = deviceInfoCommandCharacteristic.getHardwareType();
            m5558d(C1614a.a(this.f4220F.getDeviceId()));
            if (C1614a.c(this.f4260z)) {
                m5568h();
            } else {
                m5570i();
            }
            if (!TextUtils.isEmpty(bleDevice.getUrl())) {
                Picasso.with(this).load(bleDevice.getUrl() + "").error((int) C1373R.drawable.ic_speedx_force_bike_normal_logo).placeholder((int) C1373R.drawable.ic_speedx_force_bike_normal_logo).into(this.f4240f);
            }
            if (TextUtils.isEmpty(bleDevice.getFrameId()) && this.f4260z != 0 && this.f4260z != 2) {
                this.f4241g.setText(C1373R.string.speed_force_complete_device_info);
            }
        }
    }

    /* renamed from: a */
    public void m5601a(AGpsInfoCharacteristic aGpsInfoCharacteristic) {
        if (aGpsInfoCharacteristic != null) {
            int updateTime = aGpsInfoCharacteristic.getUpdateTime();
            long currentTimeMillis = ((System.currentTimeMillis() / 1000) - ((long) updateTime)) / 86400;
            this.f4235a.info("Update A_GPS file, Days =  ：" + currentTimeMillis + ", UpdateTime = " + updateTime + ",current = " + (System.currentTimeMillis() / 1000));
            this.f4225K = currentTimeMillis >= 12;
            m5549b(this.f4225K);
        }
    }

    /* renamed from: a */
    public void m5595a() {
        if (this.f4258x != null) {
            m5572j();
        }
        runOnUiThread(new SpeedForceActivity$1(this));
    }

    /* renamed from: b */
    public void m5606b() {
        if (this.f4258x != null) {
            m5572j();
        }
        runOnUiThread(new SpeedForceActivity$11(this));
    }

    /* renamed from: a */
    public void m5597a(int i, int i2) {
        runOnUiThread(new SpeedForceActivity$12(this));
    }

    /* renamed from: a */
    public void m5603a(OTAFirmwareInfoCharacteristic oTAFirmwareInfoCharacteristic) {
        if (oTAFirmwareInfoCharacteristic != null) {
            this.f4217C = oTAFirmwareInfoCharacteristic;
            m5545b(oTAFirmwareInfoCharacteristic);
        } else {
            this.f4235a.error("OTA固件版本信息解析失败");
        }
        m5592u();
    }

    /* renamed from: b */
    public void m5607b(int i) {
        switch (i) {
            case 0:
                try {
                    startService(new Intent(this, SyncService.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Toasts.show(this, C1373R.string.str_sync_success);
                this.f4238d.setVisibility(8);
                return;
            case 1:
                this.f4225K = false;
                m5549b(false);
                return;
            case 2:
                this.f4226L = false;
                m5554c(false);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m5596a(int i) {
        switch (i) {
            case 1:
                if (this.f4258x != null) {
                    this.f4258x.b();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m5604a(String str, float f) {
        this.f4235a.trace("onResponseMacAddress macAddress :" + str + ", totalDistance: " + f);
        m5548b(str, f);
        m5536a(str);
    }

    /* renamed from: a */
    public void m5600a(S605FirmwareInfo s605FirmwareInfo) {
        if (s605FirmwareInfo == null) {
            this.f4235a.trace("onS605OtaInfoResponse(), S605FirmwareInfo is null");
            return;
        }
        this.f4232R = s605FirmwareInfo;
        m5551c();
    }

    /* renamed from: c */
    private void m5551c() {
        getAsyncTaskQueue().a(new SpeedForceActivity$13(this), new Void[0]);
    }

    /* renamed from: b */
    private void m5548b(String str, float f) {
        if (f > 0.0f && !TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new SpeedForceActivity$14(this, str, f), new String[0]);
        }
    }

    /* renamed from: a */
    private void m5536a(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new SpeedForceActivity$15(this, new C1651c(this), str), new String[0]);
        }
    }

    /* renamed from: b */
    private void m5547b(String str) {
        C1651c c1651c = new C1651c(this);
        this.f4222H = new C1802i(this, "", true);
        getAsyncTaskQueue().a(new SpeedForceActivity$16(this, c1651c, str), new String[0]);
    }

    /* renamed from: c */
    private void m5553c(String str) {
        if (!isDestroyed() && !isFinishing()) {
            C1802i c1802i = new C1802i(this, getString(C1373R.string.loading_msg), true);
            c1802i.show();
            getAsyncTaskQueue().a(new SpeedForceActivity$17(this, str, c1802i), new String[0]);
        }
    }

    /* renamed from: a */
    private void m5537a(String str, boolean z) {
        if (!isFinishing() && !isDestroyed()) {
            C1802i c1802i = new C1802i(this, getString(C1373R.string.loading_msg), true);
            c1802i.show();
            getAsyncTaskQueue().a(new SpeedForceActivity$18(this, str, c1802i, z), new String[0]);
        }
    }

    /* renamed from: d */
    private void m5557d() {
        this.f4236b.setTitle((CharSequence) "");
        setSupportActionBar(this.f4236b);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        TextView textView = (TextView) this.f4245k.findViewById(C1373R.id.speedx_force_data_item_label);
        this.f4246l = (TextView) this.f4245k.findViewById(C1373R.id.speedx_force_data_item_value);
        if (C1849a.b(this)) {
            textView.setText(getResources().getString(C1373R.string.str_mileage) + "(KM)");
        } else {
            textView.setText(getResources().getString(C1373R.string.str_mileage) + "(MI)");
        }
        this.f4246l.setText("0.0");
        textView = (TextView) this.f4247m.findViewById(C1373R.id.speedx_force_data_item_label);
        this.f4248n = (TextView) this.f4247m.findViewById(C1373R.id.speedx_force_data_item_value);
        textView.setText(C1373R.string.str_cycling_time_with_unit);
        this.f4248n.setText("0.0");
        textView = (TextView) this.f4249o.findViewById(C1373R.id.speedx_force_data_item_label);
        this.f4250p = (TextView) this.f4249o.findViewById(C1373R.id.speedx_force_data_item_value);
        textView.setText(getString(C1373R.string.str_times));
        this.f4250p.setText("0");
        if (this.f4220F != null) {
            this.f4237c.setText(this.f4220F.getDeviceName());
            if (this.f4220F.getHardwareType() == 4) {
                this.f4253s.setVisibility(8);
                this.f4254t.setVisibility(0);
            } else {
                this.f4253s.setVisibility(0);
                this.f4254t.setVisibility(8);
            }
        }
        m5561e();
        C1614a b = C1661h.a().b();
        if (this.f4220F != null && b != null && !b.a().equals(this.f4220F.getMacAddress())) {
            m5552c(this.f4260z);
        } else if (b != null) {
            m5566g();
        } else {
            m5552c(-1);
        }
    }

    /* renamed from: e */
    private void m5561e() {
        this.f4237c.setOnClickListener(this);
        this.f4238d.setOnClickListener(this);
        this.f4241g.setOnClickListener(this);
        this.f4242h.setOnClickListener(this);
        this.f4243i.setOnClickListener(this);
        this.f4251q.setOnClickListener(this);
        this.f4252r.setOnClickListener(this);
        this.f4253s.setOnClickListener(this);
        this.f4255u.setOnClickListener(this);
        this.f4254t.setOnClickListener(this);
        this.f4244j.setOnClickListener(this);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.activity_speedx_force_sync_rela:
                m5574k();
                return;
            case C1373R.id.activity_speedx_force_device_type:
            case C1373R.id.activity_speedx_force_device_type_img:
                if (this.f4220F == null || !(this.f4220F.getHardwareType() == 0 || this.f4220F.getHardwareType() == 2)) {
                    intent = new Intent(this, BrowserActivity.class);
                    intent.setData(Uri.parse(a$c.f7204e));
                    startActivity(intent);
                    return;
                }
                return;
            case C1373R.id.activity_speedx_force_connect_to_bike:
                BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                if (defaultAdapter == null) {
                    return;
                }
                if (defaultAdapter.isEnabled()) {
                    m5564f();
                    return;
                }
                intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
                intent.putExtra("android.bluetooth.adapter.extra.DISCOVERABLE_DURATION", 300);
                startActivityForResult(intent, 88);
                return;
            case C1373R.id.activity_speedx_force_go_to_navigate:
                if (this.f4244j.a()) {
                    intent = new Intent();
                    if (this.f4220F != null) {
                        intent.putExtra("central_id", this.f4220F.getMacAddress());
                        intent.putExtra("extraBleDevice", this.f4220F);
                    }
                    intent.setClass(this, NavigationActivity.class);
                    startActivity(intent);
                    return;
                }
                return;
            case C1373R.id.speedx_force_service_location:
                if (this.f4220F != null) {
                    m5547b(this.f4220F.getMacAddress());
                    return;
                }
                return;
            case C1373R.id.speedx_force_bikes_settings:
                if (this.f4251q.a()) {
                    if (this.f4218D == null && this.f4220F != null) {
                        this.f4218D = C1661h.a().b(this.f4220F.getMacAddress()).i().q();
                    }
                    if (this.f4218D != null) {
                        intent = new Intent(this, SpeedXSettingActivity.class);
                        intent.putExtra("device_info", this.f4218D);
                        startActivityForResult(intent, 84);
                        return;
                    }
                    this.f4235a.error("车辆设置 deviceInfo is null");
                    this.f4258x.a();
                    return;
                }
                this.f4235a.error("车辆设置 enable is false");
                return;
            case C1373R.id.speedx_force_bikes_offline_map:
                if (this.f4254t.a()) {
                    startActivity(new Intent(this, OfflineMapSelectActivity.class));
                    return;
                }
                return;
            case C1373R.id.speedx_force_gps_version:
                if (this.f4253s.a()) {
                    startActivityForResult(new Intent(this, SpeedXGpsUpdateActivity.class), 83);
                    return;
                }
                return;
            case C1373R.id.speedx_force_ota_version:
                if (!this.f4252r.a()) {
                    this.f4235a.trace("OTAVersion view is disable");
                    return;
                } else if (this.f4220F == null) {
                    this.f4235a.trace("Current BleDevice is null");
                    return;
                } else if (this.f4260z == 4) {
                    if (this.f4232R == null) {
                        this.f4235a.info("S605FirmwareInfo is null");
                        return;
                    }
                    intent = new Intent(this, S605FirmwareInfoActivity.class);
                    intent.putExtra("device_frame_id", this.f4220F.getFrameId());
                    intent.putExtra("firmware_info", this.f4232R);
                    startActivity(intent);
                    return;
                } else if (this.f4217C == null) {
                    Toasts.show(this, C1373R.string.str_disconnect);
                    return;
                } else {
                    intent = new Intent(this, SpeedXOtaVersionActivity.class);
                    intent.putExtra("ota_info", this.f4217C);
                    intent.putExtra("hardware_type", this.f4260z);
                    intent.putExtra("central_id", this.f4220F.getMacAddress());
                    intent.putExtra("battery", this.f4216B);
                    intent.putExtra("battery_charge", this.f4215A);
                    startActivityForResult(intent, 85);
                    return;
                }
            case C1373R.id.activity_speedx_force_unbind_bike:
                m5575l();
                return;
            default:
                return;
        }
    }

    /* renamed from: f */
    private void m5564f() {
        if (!isDestroyed() && !isFinishing()) {
            if (this.f4220F == null) {
                this.f4235a.error("currentDevice is null !!");
            } else if (C1661h.a().d(this.f4220F.getMacAddress())) {
                this.f4235a.warn("currentSession is connected !!");
            } else {
                Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
                intent.setPackage(getPackageName());
                intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_SCAN_AND_CONNECT");
                intent.putExtra("extra_central_id", this.f4220F.getMacAddress());
                if (this.f4220F.getHardwareType() == 4) {
                    intent.putExtra("central_invocation_type", 1);
                } else {
                    intent.putExtra("central_invocation_type", 0);
                }
                startService(intent);
                if (this.f4229O == null) {
                    bindService(intent, this, 1);
                }
                if (this.f4222H == null && !this.f4221G) {
                    this.f4222H = new C1802i(this, "", true);
                }
                if (!this.f4221G) {
                    this.f4222H.a(30000, getString(C1373R.string.dialog_ble_connect_fail));
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m5539a(BleDevice bleDevice) {
        if (bleDevice == null || TextUtils.isEmpty(bleDevice.getMacAddress())) {
            return false;
        }
        C1614a b = C1661h.a().b(bleDevice.getMacAddress());
        if (b == null || b.c() != 4) {
            return false;
        }
        return true;
    }

    /* renamed from: g */
    private void m5566g() {
        m5538a(true);
        this.f4251q.setEnable(true);
        this.f4252r.setEnable(true);
        this.f4253s.setEnable(true);
        this.f4244j.setEnable(true);
        this.f4254t.setEnable(true);
        m5554c(this.f4226L);
        m5549b(this.f4225K);
        if (!(this.f4220F == null || TextUtils.isEmpty(this.f4220F.getUrl()))) {
            Picasso.with(this).load(this.f4220F.getUrl() + "").error((int) C1373R.drawable.ic_speedx_force_bike_normal_logo).placeholder((int) C1373R.drawable.ic_speedx_force_bike_normal_logo).into(this.f4240f);
        }
        if (this.f4220F != null) {
            int hardwareType = this.f4220F.getHardwareType();
            if (hardwareType == 4) {
                this.f4253s.setVisibility(8);
                this.f4254t.setVisibility(0);
            } else {
                this.f4253s.setVisibility(0);
                this.f4254t.setVisibility(8);
            }
            if (hardwareType == 2) {
                this.f4244j.setVisibility(8);
            } else {
                this.f4244j.setVisibility(0);
            }
            if (!TextUtils.isEmpty(this.f4220F.getFrameId()) || hardwareType == 0 || hardwareType == 2) {
                this.f4241g.setText(BleDevice.brandType2Name(this.f4220F.getBrandType()));
                return;
            }
            this.f4241g.setText(C1373R.string.speed_force_complete_device_info);
        }
    }

    /* renamed from: a */
    private void m5538a(boolean z) {
        if (z) {
            this.f4243i.setText(C1373R.string.speed_force_activity_connected);
            this.f4243i.setSolidColor(0);
            this.f4243i.setCompoundDrawablesWithIntrinsicBounds(C1373R.drawable.ic_ble_connected, 0, 0, 0);
            this.f4243i.setOnClickListener(null);
            return;
        }
        this.f4243i.setText(C1373R.string.speed_force_activity_click_connect);
        this.f4243i.setSolidColor(Color.parseColor("#ff002a"));
        this.f4243i.setCompoundDrawablesWithIntrinsicBounds(C1373R.drawable.ic_ble_click_to_connect, 0, 0, 0);
        this.f4243i.setOnClickListener(this);
    }

    /* renamed from: b */
    private void m5549b(boolean z) {
        if (z) {
            this.f4253s.setDotVisible(true);
            this.f4253s.setValue(C1373R.string.label_updatable);
            this.f4253s.setClickable(true);
            return;
        }
        this.f4253s.setDotVisible(false);
        this.f4253s.setValue(C1373R.string.version_update_not_has_new);
        this.f4253s.setClickable(false);
    }

    /* renamed from: h */
    private void m5568h() {
        m5566g();
    }

    /* renamed from: i */
    private void m5570i() {
        m5566g();
    }

    /* renamed from: c */
    private void m5552c(int i) {
        this.f4256v.setVisibility(8);
        this.f4238d.setVisibility(8);
        m5538a(false);
        this.f4251q.setEnable(false);
        this.f4252r.setEnable(false);
        this.f4253s.setEnable(false);
        this.f4244j.setEnable(false);
        this.f4254t.setEnable(false);
        this.f4252r.setValue("");
        this.f4252r.setDotVisible(false);
        this.f4253s.setValue("");
        this.f4253s.setDotVisible(false);
        if (!(this.f4220F == null || TextUtils.isEmpty(this.f4220F.getUrl()))) {
            Picasso.with(this).load(this.f4220F.getUrl() + "").error((int) C1373R.drawable.ic_speedx_force_bike_normal_logo).placeholder((int) C1373R.drawable.ic_speedx_force_bike_normal_logo).into(this.f4240f);
        }
        if (this.f4220F != null) {
            int hardwareType = this.f4220F.getHardwareType();
            if (!TextUtils.isEmpty(this.f4220F.getFrameId()) || hardwareType == 0 || hardwareType == 2) {
                this.f4241g.setText(BleDevice.brandType2Name(this.f4220F.getBrandType()));
            } else {
                this.f4241g.setText(C1373R.string.speed_force_complete_device_info);
            }
        }
        if (this.f4219E != null && this.f4219E.isVisible()) {
            this.f4219E.dismissAllowingStateLoss();
        }
    }

    /* renamed from: a */
    private void m5531a(BleCyclingDTO bleCyclingDTO) {
        if (bleCyclingDTO != null) {
            long j;
            this.f4250p.setText(String.valueOf(bleCyclingDTO.getTotalCount()));
            if (C1849a.b(this)) {
                if (bleCyclingDTO.getTotalDistance() > 1000.0d) {
                    this.f4246l.setText(this.f4233S.format(bleCyclingDTO.getTotalDistance() / 1000.0d));
                } else {
                    this.f4246l.setText(String.format(Locale.CHINA, "%.1f", new Object[]{Double.valueOf(bleCyclingDTO.getTotalDistance() / 1000.0d)}));
                }
            } else if (bleCyclingDTO.getTotalDistance() > 1000.0d) {
                this.f4246l.setText(this.f4233S.format(C1849a.a(bleCyclingDTO.getTotalDistance() / 1000.0d)));
            } else {
                this.f4246l.setText(String.format(Locale.CHINA, "%.1f", new Object[]{Double.valueOf(C1849a.a(bleCyclingDTO.getTotalDistance() / 1000.0d))}));
            }
            long totalTime = bleCyclingDTO.getTotalTime();
            if (totalTime > 0) {
                j = totalTime / 3600;
                totalTime = (totalTime % 3600) / 60;
            } else {
                totalTime = 0;
                j = 0;
            }
            if (j <= 0) {
                this.f4248n.setText(String.format(Locale.CHINA, "%.1f", new Object[]{Float.valueOf(((float) totalTime) / 60.0f)}));
                return;
            }
            this.f4248n.setText(String.format(Locale.CHINA, "%d", new Object[]{Long.valueOf(j)}));
        }
    }

    /* renamed from: d */
    private void m5558d(String str) {
        if (!TextUtils.isEmpty(str)) {
            getAsyncTaskQueue().a(new SpeedForceActivity$2(this, str), new String[0]);
        }
    }

    @TargetApi(18)
    /* renamed from: j */
    private void m5572j() {
        C1614a b = C1661h.a().b();
        if (b != null) {
            BluetoothDevice b2 = b.b();
            if (b2 != null) {
                getAsyncTaskQueue().a(new SpeedForceActivity$3(this, b, b2), new String[0]);
            }
        }
    }

    /* renamed from: k */
    private void m5574k() {
        FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
        Fragment findFragmentByTag = getFragmentManager().findFragmentByTag("SYNC_DATA");
        if (findFragmentByTag != null) {
            if (!m5539a(this.f4220F)) {
                beginTransaction.remove(findFragmentByTag);
            }
        } else if (this.f4220F != null) {
            this.f4219E = new C1740p();
            Bundle bundle = new Bundle();
            bundle.putInt("sync_type", 0);
            bundle.putString("central_id", this.f4220F.getMacAddress());
            this.f4219E.a(this.f4258x);
            this.f4219E.setArguments(bundle);
            beginTransaction.add(this.f4219E, "SYNC_DATA").commitAllowingStateLoss();
        }
    }

    /* renamed from: l */
    private void m5575l() {
        C2621c c2621c = new C2621c(this);
        c2621c.a(false);
        c2621c.b(C1373R.string.dialog_sure_or_delete).b(C1373R.string.cancel, new SpeedForceActivity$5(this, c2621c)).a(C1373R.string.label_sure, new SpeedForceActivity$4(this, c2621c)).a();
    }

    /* renamed from: m */
    private void m5578m() {
        getAsyncTaskQueue().a(new SpeedForceActivity$6(this), new BleDevice[0]);
    }

    @TargetApi(18)
    /* renamed from: n */
    private void m5579n() {
        if (this.f4220F == null) {
            this.f4235a.info("unBoundBike(), Current Device is null");
            return;
        }
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            for (BluetoothDevice bluetoothDevice : defaultAdapter.getBondedDevices()) {
                if (!bluetoothDevice.getAddress().equals(this.f4220F.getDeviceId())) {
                    if (bluetoothDevice.getName().equals(this.f4220F.getDeviceName())) {
                        break;
                    }
                }
                break;
            }
        }
        BluetoothDevice bluetoothDevice2 = null;
        C1614a a = C1661h.a().a(this.f4220F.getMacAddress());
        if (a != null) {
            a.c(true);
            a.a(false);
            a.b(false);
            a.a(0);
        }
        m5598a(bluetoothDevice2);
        MobclickAgent.m8335a(this, "BLE - 解除绑定");
    }

    /* renamed from: c */
    private void m5554c(boolean z) {
        if (z) {
            this.f4252r.setValue(C1373R.string.label_updatable);
            this.f4252r.setDotVisible(true);
            this.f4252r.setClickable(true);
            return;
        }
        this.f4252r.setValue(C1373R.string.version_update_not_has_new);
        this.f4252r.setDotVisible(false);
        if (this.f4260z != 4) {
            this.f4252r.setClickable(false);
        } else {
            this.f4252r.setClickable(true);
        }
    }

    /* renamed from: b */
    private void m5545b(OTAFirmwareInfoCharacteristic oTAFirmwareInfoCharacteristic) {
        if (oTAFirmwareInfoCharacteristic != null) {
            Request jsonObjectRequest = new JsonObjectRequest(a$c.f7203d, null, new SpeedForceActivity$7(this, oTAFirmwareInfoCharacteristic), new SpeedForceActivity$8(this));
            jsonObjectRequest.setShouldCache(false);
            getRequestQueueFactory().a(jsonObjectRequest, this);
        }
    }

    /* renamed from: a */
    private boolean m5543a(JSONObject jSONObject, OTAFirmwareInfoCharacteristic oTAFirmwareInfoCharacteristic) {
        if (jSONObject == null || oTAFirmwareInfoCharacteristic == null) {
            return false;
        }
        JSONObject jSONObject2 = null;
        this.f4235a.trace("parserOtaVersion, HardwareType = " + this.f4260z + ", \n 固件版本信息: " + oTAFirmwareInfoCharacteristic.toString() + ", uiCheckSum = " + oTAFirmwareInfoCharacteristic.getUiCheckSum());
        switch (this.f4260z) {
            case 0:
                jSONObject2 = jSONObject.optJSONObject("speed-force-v1.0");
                break;
            case 1:
                jSONObject2 = jSONObject.optJSONObject("whole_bike_s601");
                break;
            case 2:
                jSONObject2 = jSONObject.optJSONObject("speedforce_B09");
                break;
            case 3:
                jSONObject2 = jSONObject.optJSONObject("whole_bike_s603");
                break;
            case 4:
                jSONObject2 = jSONObject.optJSONObject("whole_bike_s605");
                break;
        }
        if (jSONObject2 != null) {
            JSONObject optJSONObject = jSONObject2.optJSONObject("main");
            JSONObject optJSONObject2 = jSONObject2.optJSONObject("ble");
            JSONObject optJSONObject3 = jSONObject2.optJSONObject("ui");
            jSONObject2.optJSONObject("font");
            jSONObject2 = jSONObject2.optJSONObject("power");
            if (jSONObject2 != null) {
                this.f4235a.info("Power is not null");
                if (oTAFirmwareInfoCharacteristic.getPowerCheckSum() != jSONObject2.optInt("checksum")) {
                    this.f4235a.info("Power has a new version");
                    return true;
                }
            }
            if (optJSONObject3 != null && oTAFirmwareInfoCharacteristic.getUiCheckSum() != optJSONObject3.optInt("checksum")) {
                this.f4235a.info("UI has a new version");
                return true;
            } else if (optJSONObject != null && oTAFirmwareInfoCharacteristic.getMcuCheckSum() != optJSONObject.optInt("checksum")) {
                this.f4235a.info("Main is new version");
                return true;
            } else if (!(optJSONObject2 == null || oTAFirmwareInfoCharacteristic.getBleCheckSum() == optJSONObject2.optInt("checksum"))) {
                this.f4235a.info("Ble is new version");
                return true;
            }
        }
        return false;
    }

    /* renamed from: o */
    private void m5581o() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            if (defaultAdapter.isEnabled()) {
                Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
                intent.setPackage(getPackageName());
                int i = 0;
                if (this.f4220F != null) {
                    i = this.f4220F.getHardwareType();
                } else {
                    C1614a b = C1661h.a().b();
                    if (b != null) {
                        i = b.e();
                    }
                }
                if (i == 4) {
                    intent.putExtra("central_invocation_type", 1);
                }
                startService(intent);
                if (this.f4229O == null) {
                    bindService(intent, this, 1);
                    return;
                }
                return;
            }
            Intent intent2 = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
            intent2.putExtra("android.bluetooth.adapter.extra.DISCOVERABLE_DURATION", 300);
            startActivityForResult(intent2, 88);
        }
    }

    /* renamed from: r */
    private void m5586r() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.beastbikes.android.ble.connected.action");
        intentFilter.addAction("com.beastbikes.android.ble.disconnected.action");
        intentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        intentFilter.addAction("com.beastbikes.android.connect.no.token");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        registerReceiver(this.f4234T, intentFilter);
    }

    /* renamed from: s */
    private void m5587s() {
        unregisterReceiver(this.f4234T);
    }

    /* renamed from: t */
    private void m5589t() {
        if (!m5539a(this.f4220F)) {
            FragmentTransaction beginTransaction = getFragmentManager().beginTransaction();
            Fragment findFragmentByTag = getFragmentManager().findFragmentByTag("SYNC_DATA");
            if (findFragmentByTag != null) {
                beginTransaction.remove(findFragmentByTag);
            }
        }
    }

    /* renamed from: u */
    private void m5592u() {
        Object string = getSharedPreferences(m5331p(), 0).getString("cycling_my_goal", "");
        if (!TextUtils.isEmpty(string)) {
            try {
                MyGoalInfoDTO myGoalInfoDTO = new MyGoalInfoDTO(new JSONObject(string));
                this.f4235a.info("设置目标里程: 目标里程 = " + myGoalInfoDTO.getMyGoal() + ", 当前完成 = " + myGoalInfoDTO.getCurGoal() + ", " + this.f4258x.a(0, (int) myGoalInfoDTO.getMyGoal(), (int) myGoalInfoDTO.getCurGoal()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public void m5605a(List<C1614a> list, C1614a c1614a) {
        if (this.f4220F != null) {
            C1614a b = C1661h.a().b();
            if (b != null && b.a().equals(this.f4220F.getMacAddress())) {
                return;
            }
            if (c1614a == null) {
                if (this.f4223I != null && this.f4223I.isShowing()) {
                    this.f4223I.dismiss();
                }
            } else if (!this.f4230P) {
                if (c1614a.a().equals(this.f4220F.getMacAddress())) {
                    if (!c1614a.d()) {
                        if (this.f4223I == null) {
                            this.f4223I = new C1714d(this, C1614a.c(c1614a.e()), new SpeedForceActivity$10(this));
                        }
                        if (!this.f4223I.isShowing()) {
                            this.f4223I.show();
                        }
                    } else if (this.f4223I != null && this.f4223I.isShowing()) {
                        this.f4223I.dismiss();
                    }
                }
                this.f4235a.error("onScanResult currentCentralName =[" + this.f4220F.getDeviceName() + "] currentCentralId =[" + this.f4220F.getMacAddress() + "] session = [" + c1614a + "]");
            }
        }
    }

    /* renamed from: a */
    public void m5598a(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            this.f4235a.error("Current Device is not bond");
            return;
        }
        try {
            bluetoothDevice.getClass().getMethod("removeBond", (Class[]) null).invoke(bluetoothDevice, (Object[]) null);
            this.f4235a.info("Remove bond device " + bluetoothDevice.getName() + ":" + bluetoothDevice.getAddress() + " success");
        } catch (Exception e) {
            e.printStackTrace();
            Toasts.show(this, C1373R.string.speed_force_activity_unbind_fail);
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f4235a.info("onServiceConnected");
        this.f4229O = ((C1596c) iBinder).a();
        this.f4229O.a(this);
        this.f4258x = this.f4229O.b();
        if (this.f4258x != null) {
            this.f4258x.a(this);
            this.f4258x.a(this);
            this.f4258x.a(this);
            this.f4258x.a(this);
            this.f4258x.a(this);
            this.f4258x.a(this);
        }
        C1603b c = this.f4229O.c();
        if (c != null) {
            c.a(this);
        }
        C1614a b = C1661h.a().b();
        if (b != null && this.f4220F != null && !TextUtils.equals(this.f4220F.getMacAddress(), b.a())) {
            this.f4235a.info("设备详情页：当前CentralId = " + this.f4220F.getMacAddress() + ", Current Connect Device CentralId = " + b.a());
        } else if (b != null && this.f4258x != null) {
            this.f4237c.setText(b.h());
            this.f4260z = b.e();
            this.f4235a.info("ServiceConnected, HardwareType = " + this.f4260z);
            if (this.f4260z == 4) {
                this.f4253s.setVisibility(8);
                this.f4254t.setVisibility(0);
            } else {
                this.f4253s.setVisibility(0);
                this.f4254t.setVisibility(8);
            }
            this.f4258x.a();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.f4235a.info("onServiceDisconnected");
    }
}
