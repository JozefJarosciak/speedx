package com.beastbikes.android.ble.ui;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.dao.entity.BleDevice;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragment;
import com.beastbikes.android.widget.p081b.C2534b;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903367)
public class DevicesFragment extends SessionFragment implements OnClickListener, C2534b {
    /* renamed from: f */
    private static final Logger f4126f = LoggerFactory.getLogger("DevicesFragment");
    @C1458a(a = 2131756648)
    /* renamed from: a */
    private ViewStub f4127a;
    @C1458a(a = 2131756649)
    /* renamed from: b */
    private ViewStub f4128b;
    /* renamed from: c */
    private LinearLayout f4129c;
    /* renamed from: d */
    private LinearLayout f4130d;
    /* renamed from: e */
    private LinearLayout f4131e;
    /* renamed from: g */
    private LinearLayout f4132g;
    /* renamed from: h */
    private RecyclerView f4133h;
    /* renamed from: i */
    private List<BleDevice> f4134i = new ArrayList();
    /* renamed from: j */
    private C1651c f4135j;
    /* renamed from: k */
    private DevicesFragment$b f4136k;
    /* renamed from: l */
    private boolean f4137l;
    /* renamed from: m */
    private String f4138m;
    /* renamed from: n */
    private BleDevice f4139n;
    /* renamed from: o */
    private DevicesFragment$a f4140o;
    /* renamed from: p */
    private View f4141p;
    /* renamed from: q */
    private boolean f4142q = true;
    /* renamed from: r */
    private BroadcastReceiver f4143r = new DevicesFragment$2(this);

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f4141p = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.f4135j = new C1651c(getContext());
        this.f4134i = this.f4135j.a();
        this.f4140o = new DevicesFragment$a(this, null);
        this.f4136k = new DevicesFragment$b(this, this.f4134i, this);
        if (this.f4134i == null || this.f4134i.size() <= 0) {
            this.f4127a.inflate();
            this.f4127a = null;
            m5450c();
        } else {
            Collections.sort(this.f4134i, this.f4140o);
            this.f4128b.inflate();
            this.f4128b = null;
            m5452d();
        }
        return this.f4141p;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        m5459h();
    }

    public void onResume() {
        super.onResume();
        if (this.f4142q) {
            this.f4142q = false;
            return;
        }
        this.f4134i = this.f4135j.a();
        if (this.f4134i == null || this.f4134i.isEmpty()) {
            if (this.f4132g != null) {
                this.f4132g.setVisibility(8);
            }
            if (this.f4127a != null) {
                this.f4127a.inflate();
                this.f4127a = null;
                m5450c();
                this.f4134i.clear();
                this.f4136k.notifyDataSetChanged();
                return;
            }
            this.f4129c.setVisibility(0);
            return;
        }
        if (this.f4128b != null) {
            this.f4128b.inflate();
            this.f4128b = null;
            m5452d();
            this.f4129c.setVisibility(8);
        }
        C1614a b = C1661h.a().b();
        if (b != null) {
            this.f4138m = b.a();
        } else {
            this.f4138m = null;
        }
        this.f4134i.clear();
        m5458g();
    }

    public void onDestroy() {
        m5460i();
        super.onDestroy();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        switch (i2) {
            case -1:
                if (i == 101 && this.f4139n != null) {
                    this.f4134i.remove(this.f4139n);
                    Collections.sort(this.f4134i, this.f4140o);
                    this.f4136k.notifyDataSetChanged();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        if (!(this.f4134i == null || this.f4134i.isEmpty())) {
            menuInflater.inflate(C1373R.menu.activity_speedx_force_menu, menu);
        }
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.speedx_force_add:
                startActivity(new Intent(getContext(), ChooseDeviceActivity.class));
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.choose_device_bluetooth_connect_view:
                startActivity(new Intent(getContext(), DiscoveryActivity.class));
                return;
            case C1373R.id.choose_device_qrcode_connect_view:
                startActivity(new Intent(getContext(), ScanConnectActivity.class));
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m5461a(ViewHolder viewHolder, int i) {
        if (!this.f4134i.isEmpty() && i < this.f4134i.size()) {
            this.f4139n = (BleDevice) this.f4134i.get(i);
            if (this.f4139n == null) {
                f4126f.error("OnItemClick(), BleDevice is null");
                return;
            }
            Intent intent = new Intent(getContext(), SpeedForceActivity.class);
            intent.putExtra("ble_device", this.f4139n);
            super.startActivityForResult(intent, 101);
        }
    }

    /* renamed from: b */
    public void m5462b(ViewHolder viewHolder, int i) {
    }

    /* renamed from: c */
    private void m5450c() {
        this.f4129c = (LinearLayout) this.f4141p.findViewById(C1373R.id.activity_choose_device);
        this.f4130d = (LinearLayout) this.f4141p.findViewById(C1373R.id.choose_device_bluetooth_connect_view);
        this.f4131e = (LinearLayout) this.f4141p.findViewById(C1373R.id.choose_device_qrcode_connect_view);
        m5455f();
    }

    /* renamed from: d */
    private void m5452d() {
        this.f4132g = (LinearLayout) this.f4141p.findViewById(C1373R.id.activity_speedx_devices);
        this.f4133h = (RecyclerView) this.f4141p.findViewById(C1373R.id.speedx_devices_recycler_view);
        m5453e();
    }

    /* renamed from: e */
    private void m5453e() {
        this.f4137l = C1849a.b(getContext());
        this.f4133h.setLayoutManager(new LinearLayoutManager(getContext()));
        this.f4133h.setAdapter(this.f4136k);
        this.f4136k.notifyDataSetChanged();
    }

    /* renamed from: f */
    private void m5455f() {
        this.f4130d.setOnClickListener(this);
        this.f4131e.setOnClickListener(this);
    }

    /* renamed from: g */
    private void m5458g() {
        f4126f.info("getBleDevices");
        getAsyncTaskQueue().a(new DevicesFragment$1(this), new String[0]);
    }

    /* renamed from: h */
    private void m5459h() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.beastbikes.android.ble.connected.action");
        intentFilter.addAction("com.beastbikes.android.ble.disconnected.action");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        getActivity().registerReceiver(this.f4143r, intentFilter);
    }

    /* renamed from: i */
    private void m5460i() {
        getActivity().unregisterReceiver(this.f4143r);
    }
}
