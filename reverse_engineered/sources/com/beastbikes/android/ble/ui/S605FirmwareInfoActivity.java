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
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.C1603b;
import com.beastbikes.android.ble.CentralService;
import com.beastbikes.android.ble.CentralService.C1596c;
import com.beastbikes.android.ble.dto.S605FirmwareInfo;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903176)
public class S605FirmwareInfoActivity extends SessionFragmentActivity implements ServiceConnection, OnClickListener {
    /* renamed from: a */
    private static final Logger f4192a = LoggerFactory.getLogger(S605FirmwareInfoActivity.class);
    @C1458a(a = 2131755922)
    /* renamed from: b */
    private TextView f4193b;
    @C1458a(a = 2131755924)
    /* renamed from: c */
    private TextView f4194c;
    @C1458a(a = 2131755925)
    /* renamed from: d */
    private TextView f4195d;
    @C1458a(a = 2131755926)
    /* renamed from: e */
    private LinearLayout f4196e;
    @C1458a(a = 2131755927)
    /* renamed from: f */
    private TextView f4197f;
    /* renamed from: g */
    private CentralService f4198g;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        Intent intent2 = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
        intent2.setPackage(getPackageName());
        bindService(intent2, this, 1);
        this.f4193b.setText(intent.getStringExtra("device_frame_id"));
        S605FirmwareInfo s605FirmwareInfo = (S605FirmwareInfo) intent.getSerializableExtra("firmware_info");
        if (s605FirmwareInfo != null) {
            if (TextUtils.isEmpty(s605FirmwareInfo.getRomNewVersion())) {
                this.f4195d.setVisibility(0);
                this.f4196e.setVisibility(0);
                this.f4197f.setVisibility(8);
            } else {
                this.f4195d.setVisibility(8);
                this.f4196e.setVisibility(0);
                this.f4197f.setVisibility(0);
                this.f4197f.setOnClickListener(this);
            }
            this.f4194c.setText(s605FirmwareInfo.getRomCurrVersion());
        }
    }

    public void finish() {
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
        super.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        unbindService(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.firmware_info_update_start_view:
                if (this.f4198g == null) {
                    f4192a.info("CentralService is null");
                    return;
                }
                C1603b c = this.f4198g.c();
                if (c == null) {
                    f4192a.info("S605CentralInvocation is null");
                    return;
                } else {
                    c.f();
                    return;
                }
            default:
                return;
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f4198g = ((C1596c) iBinder).a();
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }
}
