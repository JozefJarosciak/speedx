package com.beastbikes.android.modules.user.ui.binding;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.modules.user.ui.binding.p159a.C2510a;
import com.beastbikes.android.modules.user.ui.binding.widget.ValidCodeView;
import com.beastbikes.android.modules.user.ui.binding.widget.ValidCodeView$b;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.io.Serializable;
import java.util.List;

@C1457a(a = "邮箱绑定重置页")
@C1459b(a = 2130903147)
@C1460c(a = 2131820548)
public class ResetBindMailActivity extends BaseAccountActivity implements OnClickListener, ValidCodeView$b {
    @C1458a(a = 2131755796)
    /* renamed from: d */
    private ValidCodeView f6752d;
    @C1458a(a = 2131755318)
    /* renamed from: e */
    private TextView f6753e;
    @C1458a(a = 2131755795)
    /* renamed from: f */
    private EditText f6754f;
    @C1458a(a = 2131755797)
    /* renamed from: g */
    private TextView f6755g;
    /* renamed from: h */
    private boolean f6756h;
    /* renamed from: i */
    private boolean f6757i = false;
    /* renamed from: j */
    private boolean f6758j = false;
    /* renamed from: k */
    private boolean f6759k;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.f6758j = intent.getBooleanExtra("is_reset", false);
        this.f6757i = intent.getBooleanExtra("from_register", false);
        this.f6759k = intent.getBooleanExtra("is_bound_phone", false);
        this.f6752d.m7969a((ValidCodeView$b) this, (int) C1373R.string.str_valid_code);
        this.f6754f.addTextChangedListener(this);
        if (this.f6758j) {
            setTitle(C1373R.string.str_account_manage_reset_email);
            this.f6755g.setText(C1373R.string.activity_alert_dialog_text_ok);
            this.f6753e.setText(C1373R.string.str_account_manage_enter_new_email_and_verify);
            this.f6754f.setHint(C1373R.string.str_account_manage_new_email);
        } else {
            setTitle(C1373R.string.str_account_manage_bind_email);
            this.f6755g.setText(C1373R.string.activity_bound_phone_bind_str);
            this.f6753e.setText(C1373R.string.str_account_manage_account_bind_email_tip);
            if (this.f6757i) {
                this.f6753e.setText(C1373R.string.str_account_manage_bind_email_phone_for_account_safety);
            }
        }
        this.f6755g.setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return !this.f6757i || super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C1373R.id.menu_item_switch) {
            Intent intent = new Intent(this, ResetBindPhoneActivity.class);
            intent.putExtra("from_register", this.f6757i);
            intent.putExtra("account_dto", this.c);
            a(intent, 1);
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void finish() {
        super.finish();
        this.f6752d.destroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.tv_mail_phone_binding_update_commit:
                if (this.f6758j) {
                    b(this.f6752d.getText(), this.f6754f.getText().toString(), 1);
                    return;
                } else {
                    a(this.f6752d.getText(), this.f6754f.getText().toString(), "bindEmail");
                    return;
                }
            default:
                return;
        }
    }

    /* renamed from: a */
    protected void m7943a(boolean z, String str, String str2) {
        super.a(z, str, str2);
        if (z) {
            Object obj = -1;
            switch (str2.hashCode()) {
                case -954247841:
                    if (str2.equals("bindEmail")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    Intent intent = getIntent();
                    if (intent.getSerializableExtra("account_dto") != null) {
                        this.c = (AccountDTO) intent.getSerializableExtra("account_dto");
                        this.c.setUsername(this.f6754f.getText().toString());
                        if (this.f6757i || !this.f6759k) {
                            intent = new Intent(this, ResetPwdActivity.class);
                            intent.putExtra("account_dto", this.c);
                            intent.putExtra("from_register", this.f6757i);
                            intent.putExtra("is_mail", true);
                            intent.putExtra("valid_code", this.f6752d.getText());
                            intent.putExtra("is_bound_phone", this.f6759k);
                            a(intent, 1);
                            return;
                        }
                        a(this.c.getUsername(), null, this.c.getAuthType() == 1 ? "mail" : "mobilephone", Integer.parseInt(this.f6752d.getText()), null);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: b */
    protected void m7945b(List<AccountDTO> list) {
        super.b(list);
        if (list != null) {
            for (Serializable serializable : list) {
                if (serializable != null && serializable.getAuthType() == this.c.getAuthType()) {
                    break;
                }
            }
            Serializable serializable2 = null;
            if (serializable2 != null && serializable2.getStatus() == 1) {
                Toasts.show(this, C1373R.string.str_account_manage_bind_success);
                Intent intent = new Intent(this, BindMailPhoneSuccessActivity.class);
                intent.putExtra("account_dto", serializable2);
                intent.putExtra("is_mail", true);
                intent.putExtra("is_bound_phone", this.f6759k);
                startActivity(intent);
                c(2, list);
                finish();
            }
        }
    }

    /* renamed from: b */
    protected void m7944b(int i, Object obj) {
        super.b(i, obj);
        if (i == 1) {
            finish();
        }
    }

    /* renamed from: a */
    protected void m7942a(boolean z, String str, int i) {
        super.a(z, str, i);
        if (z) {
            finish();
            c(4, str);
            Toasts.show(this, C1373R.string.str_account_manage_modify_email_success);
        }
    }

    /* renamed from: b */
    public boolean m7946b() {
        a(this.f6754f.getText().toString(), this.f6758j ? "changeEmail" : "bindEmail");
        return true;
    }

    /* renamed from: a */
    public void m7941a(boolean z) {
        m7940c(z);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        boolean z = !TextUtils.isEmpty(charSequence) && C2510a.c(charSequence.toString());
        this.f6756h = z;
        this.f6752d.setEnable(this.f6756h);
        m7940c(this.f6752d.m7971b());
    }

    /* renamed from: c */
    private void m7940c(boolean z) {
        boolean z2;
        boolean z3 = true;
        TextView textView = this.f6755g;
        if (z && this.f6756h) {
            z2 = true;
        } else {
            z2 = false;
        }
        textView.setEnabled(z2);
        TextView textView2 = this.f6755g;
        if (!(z && this.f6756h)) {
            z3 = false;
        }
        textView2.setClickable(z3);
    }
}
