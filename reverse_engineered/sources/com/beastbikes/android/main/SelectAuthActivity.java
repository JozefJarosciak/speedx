package com.beastbikes.android.main;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.android.p082a.p083a.C1457a;

@C1457a(a = "选择登录或注册")
public class SelectAuthActivity extends BaseTutorialActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m5330e(18);
        ((ViewStub) findViewById(C1373R.id.viewStub_index)).inflate();
    }

    public void onClickJoinNow(View view) {
        super.onClickJoinNow(view);
    }

    public void onClickLogin(View view) {
        super.onClickLogin(view);
    }
}
