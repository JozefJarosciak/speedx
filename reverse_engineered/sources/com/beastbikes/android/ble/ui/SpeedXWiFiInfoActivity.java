package com.beastbikes.android.ble.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903197)
public class SpeedXWiFiInfoActivity extends SessionFragmentActivity {
    @C1458a(a = 2131756064)
    /* renamed from: a */
    private LinearLayout f4397a;
    @C1458a(a = 2131756065)
    /* renamed from: b */
    private TextView f4398b;
    @C1458a(a = 2131756066)
    /* renamed from: c */
    private TextView f4399c;

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
        CharSequence stringExtra = intent.getStringExtra("wifi_uuid");
        if (TextUtils.isEmpty(stringExtra)) {
            this.f4397a.setVisibility(8);
            this.f4399c.setVisibility(0);
            return;
        }
        this.f4397a.setVisibility(0);
        this.f4399c.setVisibility(8);
        this.f4398b.setText(stringExtra);
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }
}
