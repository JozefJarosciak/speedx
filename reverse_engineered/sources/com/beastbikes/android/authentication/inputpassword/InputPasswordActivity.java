package com.beastbikes.android.authentication.inputpassword;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.BaseSignActivity;
import com.beastbikes.android.authentication.C1530e;
import com.beastbikes.android.authentication.C1542d;
import com.beastbikes.android.authentication.login.LoginActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.lang.ref.WeakReference;

@C1457a(a = "输入密码页面 -- 创建密码/重置密码")
@C1459b(a = 2130903145)
public class InputPasswordActivity extends BaseSignActivity<C1550a> implements OnClickListener, OnCheckedChangeListener {
    @C1458a(a = 2131756939)
    /* renamed from: a */
    private EditText f4021a;
    @C1458a(a = 2131756938)
    /* renamed from: b */
    private CheckBox f4022b;
    @C1458a(a = 2131755792)
    /* renamed from: g */
    private Button f4023g;
    /* renamed from: h */
    private String f4024h;
    /* renamed from: i */
    private int f4025i;

    /* renamed from: c */
    protected /* synthetic */ C1542d m5299c() {
        return m5298a();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setBackgroundDrawable(new ColorDrawable(-15198184));
        }
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.f4024h = intent.getStringExtra("key_valid_code");
        this.f4025i = intent.getIntExtra("key_password_type", 17);
        e(17);
        m5296b();
        m5297r();
    }

    /* renamed from: a */
    protected C1550a m5298a() {
        return new C1550a((C1530e) new WeakReference(this).get());
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.f4021a.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            this.f4021a.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.btn_auth_input_pwd:
                if (this.f4025i == 17) {
                    ((C1550a) this.e).a();
                    return;
                } else {
                    ((C1550a) this.e).c();
                    return;
                }
            default:
                return;
        }
    }

    /* renamed from: k */
    public void m5303k() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("key_login_type", i());
        c(17, null);
        startActivity(intent);
        finish();
    }

    /* renamed from: e */
    public String m5300e() {
        return this.d;
    }

    /* renamed from: g */
    public String m5302g() {
        return this.f4021a.getText().toString().trim();
    }

    /* renamed from: f */
    public String m5301f() {
        return this.f4024h;
    }

    /* renamed from: b */
    private void m5296b() {
        if (this.f4025i == 17) {
            this.f4023g.setText(C1373R.string.str_sign_up);
        } else {
            this.f4023g.setText(C1373R.string.ok);
        }
        this.f4021a.setImeOptions(6);
    }

    /* renamed from: r */
    private void m5297r() {
        this.f4022b.setOnCheckedChangeListener(this);
        this.f4023g.setOnClickListener(this);
    }
}
