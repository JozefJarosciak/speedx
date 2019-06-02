package com.beastbikes.android.modules.cycling.route.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1457a(a = "精品路线")
@C1459b(a = 2130903356)
public class RoutesActivity extends SessionFragmentActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction().add((int) C1373R.id.frag_container, new RoutesFrag()).commit();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }
}
