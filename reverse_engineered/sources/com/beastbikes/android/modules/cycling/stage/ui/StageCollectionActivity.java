package com.beastbikes.android.modules.cycling.stage.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1459b(a = 2130903198)
public class StageCollectionActivity extends SessionFragmentActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        int intExtra = getIntent().getIntExtra("enter_source", 1);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("stage_type", intExtra);
        Fragment stagesCollectionFrag = new StagesCollectionFrag();
        stagesCollectionFrag.setArguments(bundle2);
        beginTransaction.replace(C1373R.id.activity_stage_collection, stagesCollectionFrag);
        beginTransaction.commit();
    }
}
