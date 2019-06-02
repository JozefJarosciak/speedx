package com.beastbikes.android.authentication.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.BaseAuthActivity;
import com.beastbikes.android.authentication.register.RegisterSelectActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1457a(a = "登录方式选择页面")
@C1459b(a = 2130903146)
public class LoginSelectActivity extends BaseAuthActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m5268a(getString(C1373R.string.str_signin_with_phone));
        m5270b(getString(C1373R.string.str_signin_with_email));
        m5267a((int) C1373R.string.str_has_no_account);
    }

    public void onClickPhone(View view) {
        m5312b(2);
    }

    public void onClickEmail(View view) {
        m5312b(1);
    }

    /* renamed from: b */
    public void mo2735b() {
        startActivity(new Intent(this, RegisterSelectActivity.class));
        finish();
    }

    /* renamed from: b */
    private void m5312b(int i) {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("key_login_type", i);
        startActivity(intent);
    }
}
