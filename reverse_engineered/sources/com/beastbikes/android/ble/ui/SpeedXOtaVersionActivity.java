package com.beastbikes.android.ble.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.ble.C1602a;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.biz.p096a.C1615b;
import com.beastbikes.android.ble.biz.p097b.C1625i;
import com.beastbikes.android.ble.biz.p097b.C1626j;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.ble.otadownload.C1677a;
import com.beastbikes.android.ble.otadownload.C1677a.C1675a;
import com.beastbikes.android.ble.protocol.v1.BatterySensorCharacteristic;
import com.beastbikes.android.ble.protocol.v1.DeviceInfoCommandCharacteristic;
import com.beastbikes.android.ble.protocol.v1.OTAFirmwareInfoCharacteristic;
import com.beastbikes.android.ble.ui.painter.PowerView;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.utils.C2557f;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903194)
public class SpeedXOtaVersionActivity extends SessionFragmentActivity implements OnClickListener, C1625i, C1626j, C1675a {
    /* renamed from: a */
    private static final Logger f4335a = LoggerFactory.getLogger("SpeedXOtaVersionActivity");
    @C1458a(a = 2131756042)
    /* renamed from: b */
    private TextView f4336b;
    @C1458a(a = 2131756043)
    /* renamed from: c */
    private TextView f4337c;
    @C1458a(a = 2131756044)
    /* renamed from: d */
    private PowerView f4338d;
    @C1458a(a = 2131756045)
    /* renamed from: e */
    private Button f4339e;
    /* renamed from: f */
    private C1602a f4340f;
    /* renamed from: g */
    private C1677a f4341g;
    /* renamed from: h */
    private double f4342h;
    /* renamed from: i */
    private double f4343i;
    /* renamed from: j */
    private boolean f4344j;
    /* renamed from: k */
    private int f4345k = -1;
    /* renamed from: l */
    private SharedPreferences f4346l;
    /* renamed from: m */
    private OTAFirmwareInfoCharacteristic f4347m;
    /* renamed from: n */
    private int f4348n;
    /* renamed from: o */
    private String f4349o;
    /* renamed from: p */
    private int f4350p = 1;
    /* renamed from: q */
    private C1614a f4351q;
    /* renamed from: r */
    private int f4352r;
    /* renamed from: s */
    private int f4353s;
    /* renamed from: t */
    private int f4354t = 0;
    /* renamed from: u */
    private ServiceConnection f4355u = new SpeedXOtaVersionActivity$3(this);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f4338d.setChangeColor(false);
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("central_id");
            this.f4352r = intent.getIntExtra("battery", 0);
            this.f4353s = intent.getIntExtra("battery_charge", 0);
            this.f4351q = C1661h.a().b(stringExtra);
            this.f4341g = new C1677a(this, this);
            this.f4346l = getSharedPreferences(getPackageName(), 0);
            this.f4347m = (OTAFirmwareInfoCharacteristic) intent.getSerializableExtra("ota_info");
            this.f4348n = intent.getIntExtra("hardware_type", 0);
            this.f4339e.setOnClickListener(this);
            getWindow().addFlags(128);
            Intent intent2 = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
            intent2.setPackage(getPackageName());
            startService(intent2);
            bindService(intent2, this.f4355u, 1);
        }
    }

    public void finish() {
        getWindow().clearFlags(128);
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
        setResult(-1, getIntent());
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f4340f != null) {
            unbindService(this.f4355u);
            this.f4340f.a(null);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332 || !this.f4344j) {
            return super.onOptionsItemSelected(menuItem);
        }
        m5706b();
        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.speedx_cancel_update:
                if (this.f4344j) {
                    m5706b();
                    return;
                } else if (this.f4351q != null && this.f4340f != null) {
                    if (this.f4352r >= 30 || this.f4353s != 0) {
                        this.f4340f.a(this);
                        this.f4351q.i().a(false);
                        this.f4351q.i().c(0);
                        this.f4336b.setText(C1373R.string.label_speedx_ota_version_msg_1);
                        this.f4337c.setVisibility(0);
                        this.f4339e.setText(C1373R.string.label_cancel_upgrade);
                        m5701a(this.f4347m);
                        return;
                    }
                    m5713f();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    /* renamed from: b */
    public void m5720b(int i) {
        if (this.f4345k != i) {
            this.f4345k = i;
            this.f4344j = true;
            this.f4343i += 190.0d;
            runOnUiThread(new SpeedXOtaVersionActivity$1(this));
        }
    }

    /* renamed from: c */
    public void m5722c(int i) {
        if (!isFinishing()) {
            runOnUiThread(new SpeedXOtaVersionActivity$4(this, i));
        }
    }

    /* renamed from: c */
    public void m5721c() {
    }

    /* renamed from: a */
    public void m5719a(BatterySensorCharacteristic batterySensorCharacteristic) {
        if (batterySensorCharacteristic != null) {
            this.f4352r = batterySensorCharacteristic.getPercentage();
            this.f4353s = batterySensorCharacteristic.getChargeState();
        }
    }

    /* renamed from: a */
    public void m5718a(BleDevice bleDevice, DeviceInfoCommandCharacteristic deviceInfoCommandCharacteristic) {
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            return true;
        }
        if (keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (this.f4344j) {
            m5706b();
            return true;
        }
        finish();
        return super.dispatchKeyEvent(keyEvent);
    }

    /* renamed from: a */
    public void m5717a(int i, String str, String str2) {
        f4335a.info("固件升级：下载固件成功，type = " + i);
        switch (i) {
            case 1:
                this.f4354t |= 1;
                break;
            case 2:
                this.f4354t |= 2;
                break;
            case 3:
                this.f4354t |= 4;
                break;
            case 5:
                this.f4354t |= 8;
                break;
            case 6:
                this.f4354t |= 32;
                break;
        }
        C1615b i2 = this.f4351q.i();
        if (i2 != null) {
            i2.c(this.f4354t);
        }
        if (i == this.f4350p) {
            this.f4338d.setVersion(this.f4349o + HelpFormatter.DEFAULT_OPT_PREFIX + str);
            this.f4340f.a(i, str, str2);
        }
    }

    /* renamed from: a */
    public void m5716a(int i) {
        f4335a.error("固件更新：下载固件失败，type = " + i);
        Toasts.show(this, C1373R.string.str_ble_download_ota_file_error);
        this.f4351q.i().a(true);
    }

    @TargetApi(17)
    /* renamed from: b */
    private void m5706b() {
        if (!isDestroyed() && !isFinishing()) {
            C2621c c2621c = new C2621c(this);
            c2621c.b(C1373R.string.msg_cancel_update_ota);
            c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new SpeedXOtaVersionActivity$5(this, c2621c));
            c2621c.b(C1373R.string.cancel, new SpeedXOtaVersionActivity$6(this, c2621c)).a();
        }
    }

    /* renamed from: d */
    private void m5709d() {
        C1802i c1802i = new C1802i(this, C1373R.string.loading_msg, false);
        c1802i.show();
        new Handler(getMainLooper()).postDelayed(new SpeedXOtaVersionActivity$7(this, c1802i), 20000);
    }

    /* renamed from: a */
    private void m5701a(OTAFirmwareInfoCharacteristic oTAFirmwareInfoCharacteristic) {
        if (oTAFirmwareInfoCharacteristic != null && Environment.getExternalStorageState().equals("mounted")) {
            this.f4342h = 0.0d;
            this.f4341g.b();
            Request jsonObjectRequest = new JsonObjectRequest(a$c.f7203d, null, new SpeedXOtaVersionActivity$8(this, oTAFirmwareInfoCharacteristic), new SpeedXOtaVersionActivity$9(this));
            jsonObjectRequest.setShouldCache(false);
            getRequestQueueFactory().a(jsonObjectRequest, this);
        }
    }

    /* renamed from: a */
    private void m5703a(JSONObject jSONObject, OTAFirmwareInfoCharacteristic oTAFirmwareInfoCharacteristic) {
        if (jSONObject == null || oTAFirmwareInfoCharacteristic == null) {
            Toasts.show(this, C1373R.string.label_ota_version_is_new_msg);
            return;
        }
        JSONObject jSONObject2 = null;
        switch (this.f4348n) {
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
            int optInt;
            JSONObject optJSONObject = jSONObject2.optJSONObject("main");
            JSONObject optJSONObject2 = jSONObject2.optJSONObject("ble");
            JSONObject optJSONObject3 = jSONObject2.optJSONObject("ui");
            jSONObject2.optJSONObject("font");
            JSONObject optJSONObject4 = jSONObject2.optJSONObject("power");
            Object obj = null;
            if (optJSONObject4 != null) {
                String optString = optJSONObject4.optString(MapboxEvent.ATTRIBUTE_VERSION);
                int optInt2 = optJSONObject4.optInt("checksum");
                if (oTAFirmwareInfoCharacteristic.getPowerCheckSum() != optInt2) {
                    f4335a.info("Power has a new version");
                    optInt = optJSONObject4.optInt("size");
                    if (optInt % 190 == 0) {
                        optInt /= 190;
                    } else {
                        optInt = (optInt / 190) + 1;
                    }
                    this.f4342h += (double) (optInt * 190);
                    this.f4350p = 6;
                    obj = 1;
                    m5700a(6, optInt2);
                    this.f4341g.a(6, optString, optJSONObject4.optString(MapboxEvent.TYPE_LOCATION), optInt2);
                }
            }
            if (optJSONObject3 != null) {
                String optString2 = optJSONObject3.optString(MapboxEvent.ATTRIBUTE_VERSION);
                int optInt3 = optJSONObject3.optInt("checksum");
                if (oTAFirmwareInfoCharacteristic.getUiCheckSum() != optInt3) {
                    f4335a.info("UI has a new version");
                    optInt = optJSONObject3.optInt("size");
                    if (optInt % 190 == 0) {
                        optInt /= 190;
                    } else {
                        optInt = (optInt / 190) + 1;
                    }
                    this.f4342h += (double) (optInt * 190);
                    this.f4350p = 3;
                    obj = 1;
                    m5700a(3, optInt3);
                    this.f4341g.a(3, optString2, optJSONObject3.optString(MapboxEvent.TYPE_LOCATION), optInt3);
                }
            }
            if (optJSONObject != null) {
                String optString3 = optJSONObject.optString(MapboxEvent.ATTRIBUTE_VERSION);
                int optInt4 = optJSONObject.optInt("checksum");
                if (oTAFirmwareInfoCharacteristic.getMcuCheckSum() != optInt4) {
                    f4335a.info("Main has a new version");
                    optInt = optJSONObject.optInt("size");
                    if (optInt % 190 == 0) {
                        optInt /= 190;
                    } else {
                        optInt = (optInt / 190) + 1;
                    }
                    this.f4342h += (double) (optInt * 190);
                    this.f4350p = 2;
                    obj = 1;
                    m5700a(2, optInt4);
                    this.f4341g.a(2, optString3, optJSONObject.optString(MapboxEvent.TYPE_LOCATION), optInt4);
                }
            }
            if (optJSONObject2 != null) {
                String optString4 = optJSONObject2.optString(MapboxEvent.ATTRIBUTE_VERSION);
                int optInt5 = optJSONObject2.optInt("checksum");
                if (oTAFirmwareInfoCharacteristic.getBleCheckSum() != optInt5) {
                    f4335a.info("Ble has a new version");
                    optInt = optJSONObject2.optInt("size");
                    if (optInt % 190 == 0) {
                        optInt /= 190;
                    } else {
                        optInt = (optInt / 190) + 1;
                    }
                    this.f4342h += (double) (optInt * 190);
                    this.f4350p = 1;
                    obj = 1;
                    m5700a(1, optInt5);
                    this.f4341g.a(1, optString4, optJSONObject2.optString(MapboxEvent.TYPE_LOCATION), optInt5);
                }
            }
            if (obj == null) {
                Toasts.show(this, C1373R.string.label_ota_version_is_new_msg);
            }
        }
    }

    /* renamed from: a */
    private void m5700a(int i, int i2) {
        Object obj = "";
        String str = "";
        switch (i) {
            case 1:
                str = "beast.ble.img";
                obj = this.f4346l.getString("beast.ble.img", "");
                break;
            case 2:
                str = "beast.mcu.img";
                obj = this.f4346l.getString("beast.mcu.img", "");
                break;
            case 3:
                str = "beast.ui.img";
                obj = this.f4346l.getString("beast.ui.img", "");
                break;
            case 5:
                str = "beast.font.img";
                obj = this.f4346l.getString("beast.font.img", "");
                break;
            case 6:
                str = "beast.power.img";
                obj = this.f4346l.getString("beast.power.img", "");
                break;
        }
        if (!TextUtils.isEmpty(obj)) {
            try {
                JSONObject jSONObject = new JSONObject(obj);
                obj = jSONObject.optString("path");
                int optInt = jSONObject.optInt("checksum");
                if (!TextUtils.isEmpty(obj) && optInt != i2 && C2557f.a(obj)) {
                    this.f4346l.edit().remove(str).apply();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @TargetApi(17)
    /* renamed from: e */
    private void m5710e() {
        if (!isFinishing() && !isDestroyed()) {
            C2621c c2621c = new C2621c(this);
            c2621c.b(C1373R.string.msg_ble_update_ota_error);
            c2621c.a(C1373R.string.label_i_know, new SpeedXOtaVersionActivity$10(this, c2621c)).a();
        }
    }

    @TargetApi(17)
    /* renamed from: f */
    private void m5713f() {
        if (!isDestroyed() && !isFinishing()) {
            C2621c c2621c = new C2621c(this);
            c2621c.b(C1373R.string.speed_force_little_battery_msg);
            c2621c.a(C1373R.string.label_sure, new SpeedXOtaVersionActivity$11(this, c2621c));
            c2621c.b(C1373R.string.cancel, new SpeedXOtaVersionActivity$2(this, c2621c));
            c2621c.a();
        }
    }
}
