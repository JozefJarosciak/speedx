package com.beastbikes.android.modules.cycling.stage.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903181)
public class StageAchievementDescActivity extends SessionFragmentActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
