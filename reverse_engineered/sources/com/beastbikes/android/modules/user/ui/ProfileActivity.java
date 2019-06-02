package com.beastbikes.android.modules.user.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1457a(a = "个人详情首页")
@C1459b(a = 2130903560)
public class ProfileActivity extends SessionFragmentActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null || !currentUser.getObjectId().equals(m5331p())) {
            beginTransaction.replace(C1373R.id.profile_activity_container, new OtherProfileFragment());
        } else {
            beginTransaction.replace(C1373R.id.profile_activity_container, new UserProfileFragment());
        }
        beginTransaction.commit();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
