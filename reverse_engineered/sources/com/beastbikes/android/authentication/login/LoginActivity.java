package com.beastbikes.android.authentication.login;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.BaseSignActivity;
import com.beastbikes.android.authentication.C1530e;
import com.beastbikes.android.authentication.C1542d;
import com.beastbikes.android.authentication.findpassword.FindPasswordActivity;
import com.beastbikes.android.modules.user.ui.binding.CountryPageActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import java.lang.ref.WeakReference;
import java.util.Locale;

@C1457a(a = "登录界面")
@C1459b(a = 2130903146)
public class LoginActivity extends BaseSignActivity<C1551a> implements OnClickListener, OnCheckedChangeListener {
    @C1458a(a = 2131756940)
    /* renamed from: a */
    private LinearLayout f4026a;
    @C1458a(a = 2131756942)
    /* renamed from: b */
    private TextView f4027b;
    @C1458a(a = 2131756943)
    /* renamed from: g */
    private EditText f4028g;
    @C1458a(a = 2131756935)
    /* renamed from: h */
    private LinearLayout f4029h;
    @C1458a(a = 2131756937)
    /* renamed from: i */
    private EditText f4030i;
    @C1458a(a = 2131756939)
    /* renamed from: j */
    private EditText f4031j;
    @C1458a(a = 2131756938)
    /* renamed from: k */
    private CheckBox f4032k;
    @C1458a(a = 2131755794)
    /* renamed from: l */
    private TextView f4033l;
    @C1458a(a = 2131755793)
    /* renamed from: m */
    private Button f4034m;

    /* renamed from: c */
    protected /* synthetic */ C1542d m5309c() {
        return m5308a();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setBackgroundDrawable(new ColorDrawable(-15198184));
        }
        this.f4033l.getPaint().setFlags(8);
        this.f4027b.setText(String.format(Locale.getDefault(), "+%d", new Object[]{Integer.valueOf(this.f)}));
        if (this.c == 2) {
            m5306s();
        } else {
            m5307t();
        }
        m5304b();
        m5305r();
        e(17);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C1373R.menu.menu_with_one_title_without_icon, menu);
        menu.findItem(C1373R.id.menu_with_one_title_without_icon).setTitle(C1373R.string.str_forget_password);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.menu_with_one_title_without_icon:
                Intent intent = new Intent(this, FindPasswordActivity.class);
                intent.putExtra("key_login_type", i());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.btn_auth_login:
                ((C1551a) this.e).c();
                return;
            case C1373R.id.tv_auth_login_switch:
                if (this.c == 2) {
                    this.c = 1;
                    m5307t();
                    return;
                }
                this.c = 2;
                m5306s();
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
                        this.f4027b.setText(String.format("+%s", new Object[]{r0}));
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.f4031j.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            this.f4031j.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    /* renamed from: e */
    public String m5310e() {
        if (this.c == 2) {
            return this.f4028g.getText().toString().trim();
        }
        return this.f4030i.getText().toString().trim();
    }

    /* renamed from: g */
    public String m5311g() {
        return this.f4031j.getText().toString().trim();
    }

    /* renamed from: a */
    protected C1551a m5308a() {
        return new C1551a((C1530e) new WeakReference(this).get());
    }

    /* renamed from: b */
    private void m5304b() {
        this.f4028g.setImeOptions(5);
        this.f4030i.setImeOptions(5);
        this.f4031j.setImeOptions(6);
    }

    /* renamed from: r */
    private void m5305r() {
        this.f4034m.setOnClickListener(this);
        this.f4032k.setOnCheckedChangeListener(this);
        this.f4033l.setOnClickListener(this);
        this.f4027b.setOnClickListener(this);
    }

    /* renamed from: s */
    private void m5306s() {
        this.f4029h.setVisibility(8);
        this.f4026a.setVisibility(0);
        this.f4033l.setText(C1373R.string.str_signin_with_email);
    }

    /* renamed from: t */
    private void m5307t() {
        this.f4026a.setVisibility(8);
        this.f4029h.setVisibility(0);
        this.f4033l.setText(C1373R.string.str_signin_with_phone);
    }
}
