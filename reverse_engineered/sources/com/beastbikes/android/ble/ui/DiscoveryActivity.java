package com.beastbikes.android.ble.ui;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.CentralService;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.biz.p097b.C1617a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.List;
import java.util.Timer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "扫描设备页")
@C1459b(a = 2130903245)
public class DiscoveryActivity extends SessionFragmentActivity implements C1617a {
    /* renamed from: a */
    private static final Logger f4146a = LoggerFactory.getLogger("BLE-DiscoveryActivity");
    /* renamed from: b */
    private CentralService f4147b;
    /* renamed from: c */
    private Timer f4148c;
    /* renamed from: d */
    private boolean f4149d = false;
    /* renamed from: e */
    private boolean f4150e;
    /* renamed from: f */
    private ServiceConnection f4151f = new DiscoveryActivity$1(this);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        C1661h.a().a(true);
        m5470b();
        this.f4148c = new Timer();
        this.f4148c.schedule(new DiscoveryActivity$a(this, null), 3000);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        super.onStop();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
        if (this.f4147b != null) {
            this.f4147b.a(null);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f4150e) {
            unbindService(this.f4151f);
        }
        if (!this.f4149d) {
            f4146a.info("退出扫描设备页停止扫描，Quit DiscoveryActivity and stop scan");
            Intent intent;
            if (C1661h.a().b() == null) {
                intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
                intent.setPackage(getPackageName());
                intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_STOP_SCAN");
                startService(intent);
                stopService(intent);
                return;
            }
            C1661h.a().a(false);
            intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
            intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_STOP_SCAN");
            intent.setPackage(getPackageName());
            startService(intent);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        menuItem.getItemId();
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 88) {
            switch (i2) {
                case -1:
                    m5470b();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: b */
    private void m5470b() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            Intent intent;
            if (defaultAdapter.isEnabled()) {
                intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
                intent.setPackage(getPackageName());
                intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_START_SCAN");
                intent.putExtra("central_invocation_type", 0);
                startService(intent);
                bindService(intent, this.f4151f, 1);
                return;
            }
            intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
            intent.putExtra("android.bluetooth.adapter.extra.DISCOVERABLE_DURATION", 300);
            startActivityForResult(intent, 88);
        }
    }

    /* renamed from: c */
    private void m5471c() {
        startActivity(new Intent(this, DiscoveryResultActivity.class));
        finish();
    }

    /* renamed from: a */
    public void m5474a(List<C1614a> list, C1614a c1614a) {
        if (this.f4148c == null && !this.f4149d) {
            this.f4149d = true;
            m5471c();
        }
    }
}
