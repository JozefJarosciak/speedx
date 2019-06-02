package com.beastbikes.android.modules.preferences.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;

@C1457a(a = "精度设置")
@C1459b(a = 2130903066)
public class AccuracySettingActivity extends BaseFragmentActivity implements OnClickListener, C1371a {
    @C1458a(a = 2131755293)
    /* renamed from: a */
    private TextView f5876a;
    @C1458a(a = 2131755294)
    /* renamed from: b */
    private TextView f5877b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5876a.setOnClickListener(this);
        this.f5877b.setOnClickListener(this);
    }

    protected void onResume() {
        super.onResume();
        m7128a();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        BeastBikes beastBikes = (BeastBikes) getApplication();
        switch (view.getId()) {
            case C1373R.id.accuracy_setting_activity_mode_high:
                beastBikes.m5245a(0);
                break;
            case C1373R.id.accuracy_setting_activity_mode_power_saving:
                beastBikes.m5245a(1);
                break;
            default:
                return;
        }
        m7128a();
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private void m7128a() {
        switch (((BeastBikes) getApplication()).m5244a()) {
            case 0:
                this.f5877b.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
                this.f5876a.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, C1373R.drawable.ic_setting_checked, 0);
                return;
            default:
                this.f5876a.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, 0);
                this.f5877b.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, C1373R.drawable.ic_setting_checked, 0);
                return;
        }
    }
}
