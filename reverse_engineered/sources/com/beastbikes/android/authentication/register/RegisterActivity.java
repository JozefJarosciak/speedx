package com.beastbikes.android.authentication.register;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.BaseSignActivity;
import com.beastbikes.android.authentication.C1530e;
import com.beastbikes.android.authentication.C1542d;
import com.beastbikes.android.authentication.inputpassword.InputPasswordActivity;
import com.beastbikes.android.authentication.validcode.ValidCodeActivity;
import com.beastbikes.android.modules.user.ui.binding.CountryPageActivity;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.lang.ref.WeakReference;
import java.util.Locale;

@C1459b(a = 2130903175)
public class RegisterActivity extends BaseSignActivity<C1553a> implements OnClickListener {
    @C1458a(a = 2131755917)
    /* renamed from: a */
    private TextView f4035a;
    @C1458a(a = 2131755918)
    /* renamed from: b */
    private TextView f4036b;
    @C1458a(a = 2131756940)
    /* renamed from: g */
    private LinearLayout f4037g;
    @C1458a(a = 2131756942)
    /* renamed from: h */
    private TextView f4038h;
    @C1458a(a = 2131756941)
    /* renamed from: i */
    private TextView f4039i;
    @C1458a(a = 2131756943)
    /* renamed from: j */
    private EditText f4040j;
    @C1458a(a = 2131756935)
    /* renamed from: k */
    private LinearLayout f4041k;
    @C1458a(a = 2131756936)
    /* renamed from: l */
    private TextView f4042l;
    @C1458a(a = 2131756937)
    /* renamed from: m */
    private EditText f4043m;
    @C1458a(a = 2131755920)
    /* renamed from: n */
    private TextView f4044n;
    @C1458a(a = 2131755919)
    /* renamed from: o */
    private Button f4045o;

    /* renamed from: c */
    protected /* synthetic */ C1542d m5319c() {
        return m5318a();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setBackgroundDrawable(new ColorDrawable(-15198184));
        }
        m5314b();
        m5315r();
        e(18);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.btn_auth_register_next:
                ((C1553a) this.e).c();
                return;
            case C1373R.id.tv_auth_register_switch:
                if (this.c == 2) {
                    this.c = 1;
                    m5317t();
                    return;
                }
                this.c = 2;
                m5316s();
                return;
            case C1373R.id.tv_auth_phone_area_code:
                startActivityForResult(new Intent(this, CountryPageActivity.class), 4660);
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 4660:
                if (intent != null) {
                    if (!TextUtils.isEmpty(intent.getStringExtra("code"))) {
                        this.f4038h.setText(String.format("+%s", new Object[]{r0}));
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    protected C1553a m5318a() {
        return new C1553a((C1530e) new WeakReference(this).get());
    }

    /* renamed from: e */
    public String m5320e() {
        if (this.c == 2) {
            return this.f4040j.getText().toString().trim();
        }
        return this.f4043m.getText().toString().trim();
    }

    /* renamed from: k */
    public void m5321k() {
        Intent intent = new Intent();
        if (this.c == 2) {
            intent.setClass(this, ValidCodeActivity.class);
            intent.putExtra("key_valid_code_type", "regPhone");
        } else {
            intent.setClass(this, InputPasswordActivity.class);
            intent.putExtra("key_valid_code_type", "defaultEmail");
        }
        intent.putExtra("key_login_type", this.c);
        intent.putExtra("key_username", m5320e());
        startActivity(intent);
    }

    /* renamed from: b */
    private void m5314b() {
        this.f4039i.setText(C1373R.string.str_sign_up_with_phone);
        this.f4042l.setText(C1373R.string.str_sign_up_with_email);
        this.f4044n.getPaint().setFlags(8);
        this.f4038h.setText(String.format(Locale.getDefault(), "+%d", new Object[]{Integer.valueOf(this.f)}));
        if (this.c == 2) {
            m5316s();
        } else {
            m5317t();
        }
        this.f4040j.setImeOptions(6);
        this.f4043m.setImeOptions(6);
    }

    /* renamed from: r */
    private void m5315r() {
        this.f4038h.setOnClickListener(this);
        this.f4044n.setOnClickListener(this);
        this.f4045o.setOnClickListener(this);
    }

    /* renamed from: s */
    private void m5316s() {
        this.f4035a.setText(C1373R.string.str_sign_up_with_phone);
        this.f4036b.setText(C1373R.string.str_sign_up_with_phone_desc);
        this.f4041k.setVisibility(8);
        this.f4037g.setVisibility(0);
        this.f4044n.setText(C1373R.string.str_sign_up_with_email);
    }

    /* renamed from: t */
    private void m5317t() {
        this.f4035a.setText(C1373R.string.str_sign_up_with_email);
        this.f4036b.setText(C1373R.string.str_sign_up_with_email_desc);
        this.f4037g.setVisibility(8);
        this.f4041k.setVisibility(0);
        this.f4044n.setText(C1373R.string.str_sign_up_with_phone);
    }
}
