package com.beastbikes.android.authentication.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.BaseAuthActivity;
import com.beastbikes.android.authentication.login.LoginSelectActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;

@C1457a(a = "注册方式选择页面")
public class RegisterSelectActivity extends BaseAuthActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        m5268a(getString(C1373R.string.str_sign_up_with_phone));
        m5270b(getString(C1373R.string.str_sign_up_with_email));
        m5267a((int) C1373R.string.str_already_have_an_account);
    }

    public void onClickPhone(View view) {
        m5322b(2);
    }

    public void onClickEmail(View view) {
        m5322b(1);
    }

    /* renamed from: b */
    public void mo2735b() {
        startActivity(new Intent(this, LoginSelectActivity.class));
        finish();
    }

    /* renamed from: b */
    private void m5322b(int i) {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("key_login_type", i);
        startActivity(intent);
    }
}
