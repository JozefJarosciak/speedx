package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903093)
public class ClubDiscoverActivity extends SessionFragmentActivity {
    /* renamed from: a */
    public static String f5017a = "club_disfragment_menu_show";
    /* renamed from: b */
    private FragmentManager f5018b;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent == null || !intent.getBooleanExtra(f5017a, true)) {
            ClubDiscoverFrag.f9565a = false;
        } else {
            ClubDiscoverFrag.f9565a = true;
        }
        this.f5018b = getSupportFragmentManager();
        this.f5018b.beginTransaction().add((int) C1373R.id.club_discovery_frag_container, new ClubDiscoverFrag()).commitAllowingStateLoss();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }
}
