package com.beastbikes.android.modules.preferences.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1457a(a = "个人设置页")
@C1459b(a = 2130903724)
public class UserSettingActivity extends SessionFragmentActivity {
    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            Intent intent = getIntent();
            if (intent.hasExtra("from_setting") && intent.getBooleanExtra("from_setting", false)) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
            }
            getSupportFragmentManager().beginTransaction().add((int) C1373R.id.user_setting_activity_fragment_container, Fragment.instantiate(this, UserSettingFragment.class.getName())).commit();
        }
    }
}
