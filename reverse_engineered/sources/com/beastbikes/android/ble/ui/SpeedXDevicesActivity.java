package com.beastbikes.android.ble.ui;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.MenuItem;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903190)
@C1460c(a = 2131820551)
public class SpeedXDevicesActivity extends SessionFragmentActivity implements C2534b {
    /* renamed from: a */
    private static final Logger f4266a = LoggerFactory.getLogger(SpeedXDevicesActivity.class);
    @C1458a(a = 2131756010)
    /* renamed from: b */
    private RecyclerView f4267b;
    /* renamed from: c */
    private List<BleDevice> f4268c = new ArrayList();
    /* renamed from: d */
    private C1651c f4269d;
    /* renamed from: e */
    private SpeedXDevicesActivity$b f4270e;
    /* renamed from: f */
    private boolean f4271f;
    /* renamed from: g */
    private String f4272g;
    /* renamed from: h */
    private BleDevice f4273h;
    /* renamed from: i */
    private SpeedXDevicesActivity$a f4274i;
    /* renamed from: j */
    private BroadcastReceiver f4275j = new SpeedXDevicesActivity$2(this);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m5615c();
        this.f4271f = C1849a.b(this);
        this.f4269d = new C1651c(this);
        this.f4270e = new SpeedXDevicesActivity$b(this, this.f4268c, this);
        this.f4274i = new SpeedXDevicesActivity$a(this, null);
        this.f4267b.setLayoutManager(new LinearLayoutManager(this));
        this.f4267b.setAdapter(this.f4270e);
        this.f4270e.notifyDataSetChanged();
    }

    protected void onResume() {
        super.onResume();
        C1614a b = C1661h.a().b();
        if (b != null) {
            this.f4272g = b.a();
        } else {
            this.f4272g = null;
        }
        this.f4268c.clear();
        m5613b();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.speedx_force_add:
                startActivity(new Intent(this, ChooseDeviceActivity.class));
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onDestroy() {
        m5617d();
        super.onDestroy();
    }

    /* renamed from: a */
    public void m5621a(ViewHolder viewHolder, int i) {
        if (!this.f4268c.isEmpty() && i < this.f4268c.size()) {
            this.f4273h = (BleDevice) this.f4268c.get(i);
            if (this.f4273h == null) {
                f4266a.error("OnItemClick(), BleDevice is null");
                return;
            }
            Intent intent = new Intent(this, SpeedForceActivity.class);
            intent.putExtra("ble_device", this.f4273h);
            super.startActivityForResult(intent, 101);
        }
    }

    /* renamed from: b */
    public void m5622b(ViewHolder viewHolder, int i) {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i2) {
            case -1:
                if (i == 101 && this.f4273h != null) {
                    this.f4268c.remove(this.f4273h);
                    Collections.sort(this.f4268c, this.f4274i);
                    this.f4270e.notifyDataSetChanged();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    private void m5613b() {
        getAsyncTaskQueue().a(new SpeedXDevicesActivity$1(this), new String[0]);
    }

    /* renamed from: c */
    private void m5615c() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.beastbikes.android.ble.connected.action");
        intentFilter.addAction("com.beastbikes.android.ble.disconnected.action");
        registerReceiver(this.f4275j, intentFilter);
    }

    /* renamed from: d */
    private void m5617d() {
        unregisterReceiver(this.f4275j);
    }
}
