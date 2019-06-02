package com.beastbikes.android.ble.ui;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.CentralService;
import com.beastbikes.android.ble.CentralService.C1596c;
import com.beastbikes.android.ble.biz.C1651c;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.ble.biz.p097b.C1617a;
import com.beastbikes.android.ble.ui.p098a.C1714d;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903347)
@C1460c(a = 2131820578)
@C1457a(a = "扫描结束返回结果页")
@TargetApi(19)
public class DiscoveryResultActivity extends SessionFragmentActivity implements ServiceConnection, OnItemClickListener, C1617a {
    /* renamed from: a */
    private static final Logger f4157a = LoggerFactory.getLogger("BLE-DiscoveryResultActivity");
    @C1458a(a = 2131755786)
    /* renamed from: b */
    private Toolbar f4158b;
    @C1458a(a = 2131756579)
    /* renamed from: c */
    private ListView f4159c;
    /* renamed from: d */
    private C1651c f4160d;
    /* renamed from: e */
    private DiscoveryResultActivity$c f4161e;
    /* renamed from: f */
    private C1614a f4162f = null;
    /* renamed from: g */
    private DiscoveryResultActivity$a f4163g = new DiscoveryResultActivity$a(this, null);
    /* renamed from: h */
    private C1802i f4164h;
    /* renamed from: i */
    private C1714d f4165i;
    /* renamed from: j */
    private String f4166j;
    /* renamed from: k */
    private CentralService f4167k;

    /* renamed from: com.beastbikes.android.ble.ui.DiscoveryResultActivity$b */
    private final class C1391b extends ViewHolder<C1614a> {
        /* renamed from: a */
        final /* synthetic */ DiscoveryResultActivity f4152a;
        @C1458a(a = 2131757583)
        /* renamed from: b */
        private ImageView f4153b;
        @C1458a(a = 2131757585)
        /* renamed from: c */
        private TextView f4154c;
        @C1458a(a = 2131757584)
        /* renamed from: d */
        private TextView f4155d;
        @C1458a(a = 2131757586)
        /* renamed from: e */
        private ImageView f4156e;

        public /* synthetic */ void bind(Object obj) {
            m5475a((C1614a) obj);
        }

        public C1391b(DiscoveryResultActivity discoveryResultActivity, View view) {
            this.f4152a = discoveryResultActivity;
            super(view);
        }

        /* renamed from: a */
        public void m5475a(C1614a c1614a) {
            if (c1614a != null) {
                CharSequence h = c1614a.h();
                if (!TextUtils.isEmpty(h)) {
                    h = c1614a.h().toUpperCase();
                }
                this.f4155d.setText(h);
                if (this.f4152a.f4162f == null || !this.f4152a.f4162f.a().equals(c1614a.a())) {
                    this.f4156e.setVisibility(8);
                } else {
                    this.f4156e.setVisibility(0);
                }
                if (C1614a.c(c1614a.e())) {
                    this.f4153b.setImageResource(C1373R.drawable.ic_mustang_icon);
                } else {
                    this.f4153b.setImageResource(C1373R.drawable.ic_speed_force);
                }
                if (TextUtils.equals(c1614a.a(), this.f4152a.f4166j)) {
                    this.f4154c.setVisibility(0);
                } else {
                    this.f4154c.setVisibility(8);
                }
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        setSupportActionBar(this.f4158b);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        C1661h.a().a(true);
        this.f4159c.setOnItemClickListener(this);
        this.f4160d = new C1651c(this);
        this.f4161e = new DiscoveryResultActivity$c(this, C1661h.a().d());
        this.f4159c.setAdapter(this.f4161e);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.beastbikes.android.ble.connected.action");
        intentFilter.addAction("com.beastbikes.android.connect.no.token");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        registerReceiver(this.f4163g, intentFilter);
        m5481b();
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.setPackage(getPackageName());
        bindService(intent, this, 1);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.f4163g);
        C1661h.a().a(false);
        unbindService(this);
        if (this.f4162f != null) {
            this.f4162f.b(true);
        }
        Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent.setPackage(getPackageName());
        intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_STOP_SCAN");
        startService(intent);
        C1661h.a().e();
        this.f4162f = null;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C1373R.id.menu_sure) {
            m5479a();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.f4162f = (C1614a) this.f4161e.getItem(i);
        this.f4161e.notifyDataSetChanged();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
        if (this.f4167k != null) {
            this.f4167k.a(null);
        }
    }

    /* renamed from: a */
    private void m5479a() {
        if (this.f4162f == null) {
            return;
        }
        if (this.f4162f.d()) {
            m5483c();
            return;
        }
        if (this.f4165i == null) {
            this.f4165i = new C1714d(this, C1614a.c(this.f4162f.e()));
        }
        this.f4165i.show();
    }

    /* renamed from: b */
    private void m5481b() {
        getAsyncTaskQueue().a(new DiscoveryResultActivity$1(this), new String[0]);
    }

    /* renamed from: c */
    private void m5483c() {
        if (this.f4165i != null && this.f4165i.isShowing()) {
            this.f4165i.dismiss();
        }
        if (this.f4162f != null && this.f4162f.c() == 0) {
            f4157a.error("connectDevice centralId=[" + this.f4162f.a() + "]");
            this.f4162f.a(0);
            Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
            intent.setPackage(getPackageName());
            intent.putExtra("extra_central_id", this.f4162f.a());
            intent.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_CONNECT");
            if (this.f4162f.e() == 4) {
                intent.putExtra("central_invocation_type", 1);
            } else {
                intent.putExtra("central_invocation_type", 0);
            }
            startService(intent);
            if (this.f4164h == null) {
                this.f4164h = new C1802i(this, "", true);
            }
            this.f4164h.a(30000, getString(C1373R.string.dialog_ble_connect_fail));
        }
    }

    /* renamed from: a */
    public void m5486a(List<C1614a> list, C1614a c1614a) {
        String str = "";
        if (this.f4162f != null) {
            str = this.f4162f.a();
        }
        f4157a.error("scanResults currentCentralId =[" + str + "] session = [" + c1614a + "]");
        if (c1614a != null && this.f4162f != null && c1614a.a().equals(this.f4162f.a()) && c1614a.d()) {
            m5483c();
        }
        if (list != null) {
            DiscoveryResultActivity$c.a(this.f4161e, m5478a((List) list));
            this.f4161e.notifyDataSetChanged();
        }
    }

    /* renamed from: a */
    private List<C1614a> m5478a(List<C1614a> list) {
        List<C1614a> arrayList = new ArrayList();
        for (C1614a c1614a : list) {
            if (!TextUtils.isEmpty(c1614a.h())) {
                arrayList.add(c1614a);
            }
        }
        return arrayList;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        f4157a.info("onServiceConnected");
        this.f4167k = ((C1596c) iBinder).a();
        this.f4167k.a(this);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        f4157a.info("onServiceDisconnected");
    }
}
