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
import com.beastbikes.android.locale.C1849a;
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
import org.slf4j.Marker;

@C1457a(a = "手机号绑定重置页")
@C1459b(a = 2130903165)
@C1460c(a = 2131820547)
public class ResetBindPhoneActivity extends BaseAccountActivity implements OnClickListener, ValidCodeView$b {
    @C1458a(a = 2131755868)
    /* renamed from: d */
    private EditText f6760d;
    @C1458a(a = 2131755796)
    /* renamed from: e */
    private ValidCodeView f6761e;
    @C1458a(a = 2131755867)
    /* renamed from: f */
    private TextView f6762f;
    @C1458a(a = 2131755318)
    /* renamed from: g */
    private TextView f6763g;
    @C1458a(a = 2131755797)
    /* renamed from: h */
    private TextView f6764h;
    /* renamed from: i */
    private int f6765i;
    /* renamed from: j */
    private boolean f6766j;
    /* renamed from: k */
    private boolean f6767k = false;
    /* renamed from: l */
    private boolean f6768l;
    /* renamed from: m */
    private boolean f6769m = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.f6768l = intent.getBooleanExtra("is_reset", false);
        this.f6769m = intent.getBooleanExtra("is_bind_mail", false);
        this.f6767k = intent.getBooleanExtra("from_register", false);
        this.f6761e.m7969a((ValidCodeView$b) this, (int) C1373R.string.str_valid_code);
        this.f6765i = C1849a.a(this);
        this.f6762f.setText(String.format("+%d", new Object[]{Integer.valueOf(this.f6765i)}));
        this.f6760d.getRootView().setVisibility(0);
        this.f6760d.addTextChangedListener(this);
        this.f6764h.setOnClickListener(this);
        if (this.f6768l) {
            setTitle(C1373R.string.str_account_manage_reset_mobile_phone);
            this.f6760d.setHint(C1373R.string.str_account_manage_new_mobile_phone);
            this.f6764h.setText(C1373R.string.activity_alert_dialog_text_ok);
            this.f6763g.setText(C1373R.string.str_account_manage_enter_new_phone_and_verify);
            return;
        }
        setTitle(C1373R.string.str_account_manage_bind_mobile_phone);
        this.f6764h.setText(C1373R.string.activity_bound_phone_bind_str);
        this.f6763g.setText(C1373R.string.str_account_manage_account_bind_phone_tip);
        if (this.f6767k) {
            this.f6763g.setText(C1373R.string.str_account_manage_bind_email_phone_for_account_safety);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return !this.f6767k || super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C1373R.id.menu_item_switch) {
            Intent intent = new Intent(this, ResetBindMailActivity.class);
            if (!(getIntent() == null || getIntent().getSerializableExtra("account_dto") == null)) {
                this.c = (AccountDTO) getIntent().getSerializableExtra("account_dto");
                intent.putExtra("from_register", this.f6767k);
                intent.putExtra("account_dto", this.c);
                a(intent, 1);
                finish();
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void finish() {
        super.finish();
        this.f6761e.destroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.tv_mail_phone_binding_update_commit:
                if (this.f6768l) {
                    b(this.f6761e.getText(), Marker.ANY_NON_NULL_MARKER + this.f6765i + this.f6760d.getText().toString(), 2);
                    return;
                } else {
                    a(this.f6761e.getText(), Marker.ANY_NON_NULL_MARKER + this.f6765i + this.f6760d.getText().toString(), "bindPhone");
                    return;
                }
            case C1373R.id.tv_mail_phone_binding_update_area_code:
                startActivityForResult(new Intent(this, CountryPageActivity.class), 291);
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    protected void m7950a(boolean z, String str, String str2) {
        super.a(z, str, str2);
        if (z) {
            Object obj = -1;
            switch (str2.hashCode()) {
                case -944224463:
                    if (str2.equals("bindPhone")) {
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
                        this.c.setUsername(Marker.ANY_NON_NULL_MARKER + this.f6765i + this.f6760d.getText().toString());
                        if (!this.f6767k && this.f6769m) {
                            a(this.c.getUsername(), null, this.c.getAuthType() == 1 ? "mail" : "mobilephone", Integer.parseInt(this.f6761e.getText()), null);
                            return;
                        } else if (intent.getSerializableExtra("account_dto") != null) {
                            Intent intent2 = new Intent(this, ResetPwdActivity.class);
                            intent2.putExtra("account_dto", this.c);
                            intent2.putExtra("from_register", this.f6767k);
                            intent2.putExtra("valid_code", this.f6761e.getText());
                            a(intent2, 1);
                            return;
                        } else {
                            return;
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    protected void m7949a(boolean z, String str, int i) {
        super.a(z, str, i);
        if (z) {
            c(3, str);
            finish();
            Toasts.show(this, C1373R.string.str_account_manage_modify_phone_success);
        }
    }

    /* renamed from: b */
    protected void m7952b(List<AccountDTO> list) {
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
                Intent intent = new Intent(this, BindMailPhoneSuccessActivity.class);
                intent.putExtra("account_dto", serializable2);
                intent.putExtra("is_bound_phone", true);
                startActivity(intent);
                c(2, list);
                Toasts.show(this, C1373R.string.str_account_manage_bind_success);
            }
        }
    }

    /* renamed from: b */
    protected void m7951b(int i, Object obj) {
        super.b(i, obj);
        if (i == 1) {
            finish();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null) {
            switch (i2) {
                case -1:
                    switch (i) {
                        case 291:
                            Object stringExtra = intent.getStringExtra("code");
                            if (TextUtils.isDigitsOnly(stringExtra)) {
                                this.f6765i = Integer.parseInt(stringExtra);
                            }
                            this.f6762f.setText(String.format("+%s", new Object[]{stringExtra}));
                            this.f6766j = C2510a.a(this.f6760d.getText().toString(), this.f6765i);
                            this.f6761e.setEnable(this.f6766j);
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    /* renamed from: b */
    public boolean m7953b() {
        a(Marker.ANY_NON_NULL_MARKER + this.f6765i + this.f6760d.getText().toString(), this.f6768l ? "changePhone" : "bindPhone");
        return true;
    }

    /* renamed from: a */
    public void m7948a(boolean z) {
        this.f6766j = C2510a.a(this.f6760d.getText().toString(), this.f6765i);
        m7947c(z);
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        this.f6766j = C2510a.a(charSequence.toString(), this.f6765i);
        ValidCodeView validCodeView = this.f6761e;
        boolean z = !TextUtils.isEmpty(charSequence) && this.f6766j;
        validCodeView.setEnable(z);
        m7947c(this.f6761e.m7971b());
    }

    /* renamed from: c */
    private void m7947c(boolean z) {
        boolean z2;
        boolean z3 = true;
        TextView textView = this.f6764h;
        if (z && this.f6766j) {
            z2 = true;
        } else {
            z2 = false;
        }
        textView.setEnabled(z2);
        TextView textView2 = this.f6764h;
        if (!(z && this.f6766j)) {
            z3 = false;
        }
        textView2.setClickable(z3);
    }
}
