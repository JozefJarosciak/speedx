package com.beastbikes.android.modules.user.ui.binding;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.authentication.AuthenticationException;
import com.beastbikes.android.authentication.p055a.C1536a;
import com.beastbikes.android.authentication.p055a.C1536a.C1535a;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.utils.p163b.C2552b;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.io.Serializable;
import java.util.List;

@C1457a(a = "密码重置页&密码设置")
@C1459b(a = 2130903070)
public class ResetPwdActivity extends BaseAccountActivity implements OnClickListener, C1535a {
    @C1458a(a = 2131755319)
    /* renamed from: d */
    private EditText f6770d;
    @C1458a(a = 2131755320)
    /* renamed from: e */
    private EditText f6771e;
    @C1458a(a = 2131755321)
    /* renamed from: f */
    private EditText f6772f;
    @C1458a(a = 2131755322)
    /* renamed from: g */
    private TextView f6773g;
    @C1458a(a = 2131755323)
    /* renamed from: h */
    private TextView f6774h;
    @C1458a(a = 2131755318)
    /* renamed from: i */
    private TextView f6775i;
    /* renamed from: j */
    private boolean f6776j = false;
    /* renamed from: k */
    private boolean f6777k = false;
    /* renamed from: l */
    private boolean f6778l = false;
    /* renamed from: m */
    private boolean f6779m = false;

    protected void onCreate(Bundle bundle) {
        int i = 8;
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.f6776j = intent.getBooleanExtra("from_register", false);
        this.f6777k = intent.getBooleanExtra("is_reset", false);
        this.f6778l = intent.getBooleanExtra("is_mail", false);
        this.f6779m = intent.getBooleanExtra("is_bound_phone", false);
        this.f6770d.setVisibility(this.f6777k ? 0 : 8);
        TextView textView = this.f6775i;
        if (!this.f6777k) {
            i = 0;
        }
        textView.setVisibility(i);
        setTitle(this.f6777k ? C1373R.string.activity_remove_bound_mail_modify_password : C1373R.string.activity_bound_phone_password_title);
        this.f6774h.setOnClickListener(this);
        this.f6772f.addTextChangedListener(this);
        this.f6771e.addTextChangedListener(this);
        this.f6770d.addTextChangedListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.tv_account_pwd_reset_commit:
                if (!TextUtils.equals(this.f6771e.getText(), this.f6772f.getText())) {
                    this.f6773g.setVisibility(0);
                    return;
                } else if (this.f6777k) {
                    b(this.f6771e.getText().toString(), this.f6770d.getText().toString());
                    return;
                } else {
                    Intent intent = getIntent();
                    if (intent.getSerializableExtra("account_dto") != null && intent.getStringExtra("valid_code") != null) {
                        this.c = (AccountDTO) intent.getSerializableExtra("account_dto");
                        int parseInt = Integer.parseInt(intent.getStringExtra("valid_code"));
                        if (!this.f6776j) {
                            a(this.c.getUsername(), this.f6771e.getText().toString(), this.c.getAuthType() == 1 ? "mail" : "mobilephone", parseInt, this.c.getThirdNick());
                            return;
                        } else if (AVUser.getCurrentUser() != null) {
                            a(this.c.getUsername(), this.f6771e.getText().toString(), this.c.getAuthType() == 1 ? "mail" : "mobilephone", parseInt, this.c.getThirdNick());
                            return;
                        } else {
                            if (this.a == null) {
                                this.a = new C1536a(this);
                            }
                            this.c.setPassword(this.f6771e.getText().toString());
                            a();
                            this.a.a(this.c, parseInt + "", this);
                            return;
                        }
                    }
                    return;
                }
            default:
                return;
        }
    }

    /* renamed from: b */
    protected void m7958b(boolean z) {
        super.b(z);
        if (z) {
            Toasts.show(this, C1373R.string.str_account_manage_pwd_update_success);
            finish();
        }
    }

    /* renamed from: b */
    protected void m7957b(List<AccountDTO> list) {
        super.b(list);
        if (list != null) {
            for (Serializable serializable : list) {
                if (serializable != null && serializable.getAuthType() == this.c.getAuthType()) {
                    break;
                }
            }
            Serializable serializable2 = null;
            if (serializable2 != null && serializable2.getStatus() == 1) {
                finish();
                c(1, list);
                if (!this.f6776j) {
                    Intent intent = new Intent(this, BindMailPhoneSuccessActivity.class);
                    intent.putExtra("account_dto", serializable2);
                    intent.putExtra("is_mail", this.f6778l);
                    intent.putExtra("is_bound_phone", this.f6779m);
                    startActivity(intent);
                    if (this.f6778l) {
                        Toasts.show(this, C1373R.string.str_account_manage_bind_success);
                    } else {
                        Toasts.show(this, C1373R.string.str_account_manage_bind_success);
                    }
                }
            }
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        boolean z;
        boolean z2 = true;
        super.onTextChanged(charSequence, i, i2, i3);
        boolean z3 = (this.f6777k && TextUtils.isEmpty(this.f6770d.getText())) ? false : true;
        if (!(TextUtils.isEmpty(this.f6771e.getText()) || TextUtils.isEmpty(this.f6772f.getText()) || !TextUtils.equals(this.f6771e.getText(), this.f6772f.getText()))) {
            this.f6773g.setVisibility(8);
        }
        boolean z4 = (!this.f6777k || m7954a(this.f6770d.getText().toString())) && m7954a(this.f6771e.getText().toString()) && m7954a(this.f6772f.getText().toString());
        TextView textView = this.f6774h;
        if (z3 && z4) {
            z = true;
        } else {
            z = false;
        }
        textView.setEnabled(z);
        TextView textView2 = this.f6774h;
        if (!(z3 && z4)) {
            z2 = false;
        }
        textView2.setClickable(z2);
    }

    /* renamed from: a */
    private boolean m7954a(String str) {
        return str.length() >= 6 && str.length() <= 16;
    }

    /* renamed from: a */
    public void m7955a(AuthenticationException authenticationException, AccountDTO accountDTO) {
    }

    /* renamed from: b */
    public void m7956b(AuthenticationException authenticationException, AccountDTO accountDTO) {
        c();
        if (authenticationException != null) {
            Toasts.show(this, authenticationException.getMessage());
            return;
        }
        a(new C2552b(1, null));
        finish();
    }
}
