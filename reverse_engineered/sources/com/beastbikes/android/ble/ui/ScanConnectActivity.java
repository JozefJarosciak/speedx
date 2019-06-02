package com.beastbikes.android.ble.ui;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.ui.p098a.C1722j;
import com.beastbikes.android.dialog.C1802i;
import com.journeyapps.barcodescanner.C4121b;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScanConnectActivity extends ScanQRCodeActivity {
    /* renamed from: a */
    private static final Logger f7646a = LoggerFactory.getLogger(ScanConnectActivity.class);
    /* renamed from: b */
    private C1692a f7647b = new C1692a();
    /* renamed from: c */
    private C1802i f7648c;
    /* renamed from: d */
    private boolean f7649d;
    /* renamed from: e */
    private C1722j f7650e;

    /* renamed from: com.beastbikes.android.ble.ui.ScanConnectActivity$a */
    private class C1692a extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ ScanConnectActivity f7641a;

        private C1692a(ScanConnectActivity scanConnectActivity) {
            this.f7641a = scanConnectActivity;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                this.f7641a.f7649d = false;
                String action = intent.getAction();
                if ("com.beastbikes.android.ble.connected.action".equals(action)) {
                    if (this.f7641a.f7648c != null && this.f7641a.f7648c.isShowing()) {
                        this.f7641a.f7648c.dismiss();
                    }
                    this.f7641a.startActivity(new Intent(this.f7641a, SpeedForceActivity.class));
                    this.f7641a.finish();
                }
                if ("com.beastbikes.android.ble.disconnected.action".equals(action)) {
                    if (this.f7641a.f7648c != null && this.f7641a.f7648c.isShowing()) {
                        this.f7641a.f7648c.dismiss();
                    }
                    if (this.f7641a.f7650e == null) {
                        this.f7641a.f7650e = new C1722j(this.f7641a);
                    }
                    if (!this.f7641a.f7650e.isShowing()) {
                        this.f7641a.f7650e.show();
                    }
                }
                if ("com.beastbikes.android.connect.no.token".equals(action)) {
                    if (this.f7641a.f7648c != null && this.f7641a.f7648c.isShowing()) {
                        this.f7641a.f7648c.dismiss();
                    }
                    Intent intent2 = new Intent(this.f7641a, SpeedXTrainingTargetActivity.class);
                    intent2.putExtra("show_menu", true);
                    this.f7641a.startActivity(intent2);
                    this.f7641a.finish();
                }
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m9143a();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.beastbikes.android.ble.connected.action");
        intentFilter.addAction("com.beastbikes.android.ble.disconnected.action");
        intentFilter.addAction("com.beastbikes.android.connect.no.token");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        registerReceiver(this.f7647b, intentFilter);
        m9151c();
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.f7647b);
        if (C1661h.m8999a().m9004b() == null) {
            Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
            intent.setPackage(getPackageName());
            intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_STOP_SCAN");
            startService(intent);
            stopService(intent);
            return;
        }
        intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_STOP_SCAN");
        intent.setPackage(getPackageName());
        startService(intent);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 88) {
            switch (i2) {
                case -1:
                    m9151c();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: c */
    private void m9151c() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            Intent intent;
            if (defaultAdapter.isEnabled()) {
                intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
                intent.setPackage(getPackageName());
                intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_START_SCAN");
                intent.putExtra("central_invocation_type", 1);
                startService(intent);
                return;
            }
            intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
            intent.putExtra("android.bluetooth.adapter.extra.DISCOVERABLE_DURATION", 300);
            startActivityForResult(intent, 88);
        }
    }

    /* renamed from: a */
    public void mo3211a(C4121b c4121b) {
        super.mo3211a(c4121b);
        Object b = c4121b.m16543b();
        if (TextUtils.isEmpty(b)) {
            f7646a.info("result is null");
            return;
        }
        try {
            f7646a.info("result = " + c4121b);
            JSONObject jSONObject = new JSONObject(b);
            String optString = jSONObject.optString("centralId");
            String optString2 = jSONObject.optString("name");
            if (TextUtils.isEmpty(optString)) {
                f7646a.error("Scan QRCode result centralId is null");
            } else if (!this.f7649d) {
                m9153a(optString, optString2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m9153a(String str, String str2) {
        if (C1661h.m8999a().m9009d(str)) {
            f7646a.warn("currentSession is connected !!");
            return;
        }
        C1661h.m8999a().m9003a(false);
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.setPackage(getPackageName());
        intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_SCAN_AND_CONNECT");
        intent.putExtra("central_invocation_type", 1);
        intent.putExtra("extra_central_id", str);
        intent.putExtra("extra_central_name", str2);
        startService(intent);
        this.f7649d = true;
        if (this.f7648c == null) {
            this.f7648c = new C1802i((Context) this, "", true);
        }
        this.f7648c.show();
    }
}
