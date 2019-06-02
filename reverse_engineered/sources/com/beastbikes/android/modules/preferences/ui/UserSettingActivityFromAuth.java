package com.beastbikes.android.modules.preferences.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.ImageView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.widget.p165a.C2608b;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1457a(a = "个人设置页")
@C1459b(a = 2130903725)
public class UserSettingActivityFromAuth extends SessionFragmentActivity {
    @C1458a(a = 2131757731)
    /* renamed from: a */
    ImageView f5930a;

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        getSupportFragmentManager().beginTransaction().add((int) C1373R.id.user_setting_activity_fragment_container, Fragment.instantiate(this, UserSettingFragment.class.getName())).commitAllowingStateLoss();
        C2608b.a(this, this.f5930a, C1373R.drawable.authentication_bg);
    }

    protected void onDestroy() {
        super.onDestroy();
        C2608b.a(this.f5930a);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return true;
    }
}
