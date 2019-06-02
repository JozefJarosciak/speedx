package com.beastbikes.android.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.login.LoginSelectActivity;
import com.beastbikes.android.authentication.register.RegisterSelectActivity;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;

@C1457a(a = "引导页与非第一次进入且未登录页面基类")
public abstract class BaseTutorialActivity extends SessionFragmentActivity implements OnClickListener {
    /* renamed from: a */
    public LinearLayout f4444a;
    /* renamed from: b */
    private Button f4445b;
    /* renamed from: c */
    private Button f4446c;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        setContentView((int) C1373R.layout.tutorial_activity);
        mo2761a();
        m5800b();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.btn_tutorial_page_join_now:
                onClickJoinNow(view);
                return;
            case C1373R.id.btn_tutorial_page_login:
                onClickLogin(view);
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    protected void mo2739b(int i, Object obj) {
        if (i == 18) {
            finish();
        }
    }

    /* renamed from: a */
    private void mo2761a() {
        this.f4444a = (LinearLayout) findViewById(C1373R.id.linear_temp);
        this.f4445b = (Button) findViewById(C1373R.id.btn_tutorial_page_join_now);
        this.f4446c = (Button) findViewById(C1373R.id.btn_tutorial_page_login);
        if (C1849a.a()) {
            this.f4445b.setTypeface(this.f4445b.getTypeface(), 0);
            this.f4446c.setTypeface(this.f4446c.getTypeface(), 0);
            return;
        }
        this.f4445b.setTypeface(this.f4445b.getTypeface(), 2);
        this.f4446c.setTypeface(this.f4446c.getTypeface(), 2);
    }

    /* renamed from: b */
    private void m5800b() {
        this.f4446c.setOnClickListener(this);
        this.f4445b.setOnClickListener(this);
    }

    public void onClickJoinNow(View view) {
        startActivity(new Intent(this, RegisterSelectActivity.class));
    }

    public void onClickLogin(View view) {
        startActivity(new Intent(this, LoginSelectActivity.class));
    }
}
