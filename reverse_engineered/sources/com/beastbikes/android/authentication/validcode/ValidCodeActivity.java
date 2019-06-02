package com.beastbikes.android.authentication.validcode;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.BaseSignActivity;
import com.beastbikes.android.authentication.C1530e;
import com.beastbikes.android.authentication.C1542d;
import com.beastbikes.android.authentication.inputpassword.InputPasswordActivity;
import com.beastbikes.android.modules.user.ui.binding.widget.ValidCodeView;
import com.beastbikes.android.modules.user.ui.binding.widget.ValidCodeView$b;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.lang.ref.WeakReference;

@C1457a(a = "输入验证码页面")
@C1459b(a = 2130903211)
public class ValidCodeActivity extends BaseSignActivity<C1564a> implements OnClickListener, ValidCodeView$b {
    @C1458a(a = 2131756944)
    /* renamed from: a */
    private ValidCodeView f4107a;
    @C1458a(a = 2131756107)
    /* renamed from: b */
    private Button f4108b;
    /* renamed from: g */
    private String f4109g;
    /* renamed from: h */
    private long f4110h;
    /* renamed from: i */
    private boolean f4111i;

    /* renamed from: c */
    protected /* synthetic */ C1542d m5401c() {
        return m5398a();
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
        this.f4109g = intent.getStringExtra("key_valid_code_type");
        this.f4110h = intent.getLongExtra("key_valid_code_time", ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD);
        if (TextUtils.isEmpty(this.f4109g)) {
            finish();
        }
        a(new int[]{18, 17});
        m5396r();
        m5397s();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.btn_auth_valid_code_next:
                Object f = m5403f();
                if (TextUtils.isEmpty(f) || f.length() != 6) {
                    c(getString(C1373R.string.str_invalid_code));
                    return;
                } else {
                    ((C1564a) this.e).b(this.f4109g);
                    return;
                }
            default:
                return;
        }
    }

    /* renamed from: a */
    protected C1564a m5398a() {
        return new C1564a((C1530e) new WeakReference(this).get());
    }

    /* renamed from: e */
    public String m5402e() {
        return this.d;
    }

    /* renamed from: f */
    public String m5403f() {
        return this.f4107a.getText().trim();
    }

    /* renamed from: b */
    public boolean m5400b() {
        ((C1564a) this.e).a(this.f4109g);
        this.f4111i = true;
        return true;
    }

    /* renamed from: a */
    public void m5399a(boolean z) {
        this.f4108b.setEnabled(z);
    }

    /* renamed from: k */
    public void m5404k() {
        if (this.f4111i) {
            this.f4111i = false;
            return;
        }
        Intent intent = new Intent(this, InputPasswordActivity.class);
        intent.putExtra("key_login_type", this.c);
        intent.putExtra("key_username", this.d);
        intent.putExtra("key_valid_code", m5403f());
        intent.putExtra("key_password_type", getIntent().getIntExtra("key_password_type", 17));
        startActivity(intent);
    }

    /* renamed from: r */
    private void m5396r() {
        this.f4107a.m7970a(this, this.f4110h, C1373R.string.str_valid_code);
        a(9, null);
        this.f4108b.setEnabled(false);
    }

    /* renamed from: s */
    private void m5397s() {
        this.f4108b.setOnClickListener(this);
    }
}
