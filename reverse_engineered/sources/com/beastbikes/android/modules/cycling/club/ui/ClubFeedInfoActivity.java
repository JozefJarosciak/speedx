package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903268)
public class ClubFeedInfoActivity extends SessionFragmentActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle2 = new Bundle();
            ClubInfoCompact clubInfoCompact = (ClubInfoCompact) intent.getSerializableExtra("club_info");
            if (clubInfoCompact != null) {
                setTitle(clubInfoCompact.getName());
                bundle2.putSerializable("club_info", clubInfoCompact);
            }
            Object stringExtra = intent.getStringExtra("club_id");
            if (!TextUtils.isEmpty(stringExtra)) {
                bundle2.putString("club_id", stringExtra);
            }
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            Fragment clubFeedInfoFrag = new ClubFeedInfoFrag();
            clubFeedInfoFrag.setArguments(bundle2);
            supportFragmentManager.beginTransaction().add((int) C1373R.id.frag_container, clubFeedInfoFrag).commit();
        }
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }
}
