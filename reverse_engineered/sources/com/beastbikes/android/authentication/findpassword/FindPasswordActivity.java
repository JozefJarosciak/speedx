package com.beastbikes.android.authentication.findpassword;

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
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.BaseSignActivity;
import com.beastbikes.android.authentication.C1530e;
import com.beastbikes.android.authentication.C1542d;
import com.beastbikes.android.authentication.validcode.ValidCodeActivity;
import com.beastbikes.android.modules.user.ui.binding.CountryPageActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.lang.ref.WeakReference;
import java.util.Locale;

@C1457a(a = "忘记密码页面")
@C1459b(a = 2130903131)
public class FindPasswordActivity extends BaseSignActivity<C1548a> implements OnClickListener {
    @C1458a(a = 2131756940)
    /* renamed from: a */
    private LinearLayout f4010a;
    @C1458a(a = 2131756942)
    /* renamed from: b */
    private TextView f4011b;
    @C1458a(a = 2131756941)
    /* renamed from: g */
    private TextView f4012g;
    @C1458a(a = 2131756943)
    /* renamed from: h */
    private EditText f4013h;
    @C1458a(a = 2131756935)
    /* renamed from: i */
    private LinearLayout f4014i;
    @C1458a(a = 2131756936)
    /* renamed from: j */
    private TextView f4015j;
    @C1458a(a = 2131756937)
    /* renamed from: k */
    private EditText f4016k;
    @C1458a(a = 2131755738)
    /* renamed from: l */
    private TextView f4017l;
    @C1458a(a = 2131755737)
    /* renamed from: m */
    private Button f4018m;
    /* renamed from: n */
    private long f4019n;
    /* renamed from: o */
    private int f4020o;

    /* renamed from: c */
    protected /* synthetic */ C1542d m5293c() {
        return m5292a();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setBackgroundDrawable(new ColorDrawable(-15198184));
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        m5288r();
        m5289s();
        e(17);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        switch (i) {
            case 4660:
                if (intent != null) {
                    Object stringExtra = intent.getStringExtra("code");
                    if (!TextUtils.isEmpty(stringExtra)) {
                        this.f = Integer.parseInt(stringExtra);
                        this.f4011b.setText(String.format("+%s", new Object[]{stringExtra}));
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.btn_auth_find_pwd_next:
                if (this.c != this.f4020o) {
                    ((C1548a) this.e).c();
                    this.f4020o = this.c;
                    return;
                }
                if (System.currentTimeMillis() - this.f4019n < ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD) {
                    m5287b();
                } else {
                    ((C1548a) this.e).c();
                }
                this.f4020o = this.c;
                return;
            case C1373R.id.tv_auth_find_pwd_switch:
                if (this.c == 2) {
                    this.c = 1;
                    m5291u();
                    return;
                }
                this.c = 2;
                m5290t();
                return;
            case C1373R.id.tv_auth_phone_area_code:
                startActivityForResult(new Intent(this, CountryPageActivity.class), 4660);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    protected C1548a m5292a() {
        return new C1548a((C1530e) new WeakReference(this).get());
    }

    /* renamed from: e */
    public String m5294e() {
        if (this.c == 2) {
            return this.f4013h.getText().toString().trim();
        }
        return this.f4016k.getText().toString().trim();
    }

    /* renamed from: k */
    public void m5295k() {
        this.f4019n = System.currentTimeMillis();
        m5287b();
    }

    /* renamed from: b */
    private void m5287b() {
        long j = ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD;
        Intent intent = new Intent(this, ValidCodeActivity.class);
        if (this.c == 2) {
            intent.putExtra("key_valid_code_type", "resetPwdPhone");
        } else {
            intent.putExtra("key_valid_code_type", "resetPwdEmail");
        }
        intent.putExtra("key_password_type", 18);
        intent.putExtra("key_login_type", this.c);
        long currentTimeMillis = System.currentTimeMillis() - this.f4019n;
        if (currentTimeMillis <= ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD) {
            j = ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD - currentTimeMillis;
        }
        intent.putExtra("key_valid_code_time", j);
        intent.putExtra("key_username", m5294e());
        startActivity(intent);
    }

    /* renamed from: r */
    private void m5288r() {
        this.f4012g.setText(C1373R.string.str_find_pass_word_by_phone);
        this.f4015j.setText(C1373R.string.str_find_pass_word_by_email);
        this.f4017l.getPaint().setFlags(8);
        this.f4011b.setText(String.format(Locale.getDefault(), "+%d", new Object[]{Integer.valueOf(this.f)}));
        if (this.c == 2) {
            m5290t();
        } else {
            m5291u();
        }
        this.f4013h.setImeOptions(6);
        this.f4016k.setImeOptions(6);
    }

    /* renamed from: s */
    private void m5289s() {
        this.f4011b.setOnClickListener(this);
        this.f4017l.setOnClickListener(this);
        this.f4018m.setOnClickListener(this);
    }

    /* renamed from: t */
    private void m5290t() {
        this.f4014i.setVisibility(8);
        this.f4010a.setVisibility(0);
        this.f4017l.setText(C1373R.string.str_find_pass_word_by_email);
    }

    /* renamed from: u */
    private void m5291u() {
        this.f4010a.setVisibility(8);
        this.f4014i.setVisibility(0);
        this.f4017l.setText(C1373R.string.str_find_pass_word_by_phone);
    }
}
