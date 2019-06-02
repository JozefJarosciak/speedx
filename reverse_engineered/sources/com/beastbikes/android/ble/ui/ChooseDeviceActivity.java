package com.beastbikes.android.ble.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903083)
public class ChooseDeviceActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131755418)
    /* renamed from: a */
    private LinearLayout f4124a;
    @C1458a(a = 2131755419)
    /* renamed from: b */
    private LinearLayout f4125b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m5443a();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.choose_device_bluetooth_connect_view:
                startActivity(new Intent(this, DiscoveryActivity.class));
                finish();
                return;
            case C1373R.id.choose_device_qrcode_connect_view:
                startActivity(new Intent(this, ScanConnectActivity.class));
                finish();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m5443a() {
        this.f4124a.setOnClickListener(this);
        this.f4125b.setOnClickListener(this);
    }
}
