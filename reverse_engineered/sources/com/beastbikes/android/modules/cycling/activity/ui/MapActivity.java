package com.beastbikes.android.modules.cycling.activity.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903408)
public class MapActivity extends SessionFragmentActivity implements MapFragment$b {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(C1373R.id.map_activity_container, new MapFragment());
        beginTransaction.commitAllowingStateLoss();
    }

    /* renamed from: a */
    public void m6033a() {
        finish();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_bottom_and_alpha);
    }

    protected void onDestroy() {
        super.onDestroy();
        Runtime.getRuntime().gc();
    }
}
