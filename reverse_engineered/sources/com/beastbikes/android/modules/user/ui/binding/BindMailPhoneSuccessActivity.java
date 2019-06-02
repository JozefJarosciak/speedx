package com.beastbikes.android.modules.user.ui.binding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.modules.user.ui.binding.p159a.C2510a;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.List;

@C1457a(a = "邮箱手机号绑定成功页")
@C1459b(a = 2130903148)
public class BindMailPhoneSuccessActivity extends BaseAccountActivity implements OnClickListener {
    @C1458a(a = 2131755798)
    /* renamed from: d */
    private TextView f6747d;
    @C1458a(a = 2131755800)
    /* renamed from: e */
    private TextView f6748e;
    @C1458a(a = 2131755799)
    /* renamed from: f */
    private TextView f6749f;
    /* renamed from: g */
    private boolean f6750g;
    /* renamed from: h */
    private boolean f6751h;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        this.c = (AccountDTO) intent.getSerializableExtra("account_dto");
        if (this.c == null) {
            finish();
            return;
        }
        this.f6750g = intent.getBooleanExtra("is_mail", false);
        this.f6751h = intent.getBooleanExtra("is_bound_phone", false);
        m7937b();
    }

    /* renamed from: b */
    private void m7937b() {
        int i;
        CharSequence a;
        this.f6749f.setOnClickListener(this);
        this.f6748e.setOnClickListener(this);
        if (this.f6750g) {
            this.f6748e.setVisibility(this.f6751h ? 0 : 8);
            setTitle(C1373R.string.str_set_email);
            i = C1373R.drawable.ic_account_mail_bound;
            this.f6749f.setText(C1373R.string.str_account_manage_reset_email);
            a = C2510a.a(this.c.getAuthKey());
        } else {
            setTitle(C1373R.string.str_set_phone);
            i = C1373R.drawable.ic_account_phone_bound;
            this.f6749f.setText(C1373R.string.str_account_manage_reset_mobile_phone);
            a = C2510a.b(this.c.getAuthKey());
        }
        this.f6747d.setText(a);
        this.f6747d.setCompoundDrawablesWithIntrinsicBounds(0, i, 0, 0);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.tv_mail_phone_bound_reset:
                Intent intent = new Intent(this, ValidMailPhoneActivity.class);
                intent.putExtra("account_dto", this.c);
                intent.putExtra("is_mail", this.f6750g);
                startActivityForResult(intent, this.f6750g ? 17 : 18);
                e(this.f6750g ? 4 : 3);
                return;
            case C1373R.id.tv_mail_phone_bound_unbound:
                C2621c c2621c = new C2621c(this);
                c2621c.b(C1373R.string.str_account_manage_confirm_unbind_email);
                c2621c.a(C1373R.string.club_release_activites_dialog_ok, new BindMailPhoneSuccessActivity$1(this, c2621c));
                c2621c.b(C1373R.string.activity_alert_dialog_text_cancel, new BindMailPhoneSuccessActivity$2(this, c2621c));
                c2621c.a();
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    protected void m7939b(int i, Object obj) {
        super.b(i, obj);
        if (obj != null) {
            String valueOf = String.valueOf(obj);
            if (this.c != null) {
                this.c.setAuthKey(valueOf);
            }
            switch (i) {
                case 3:
                    this.f6747d.setText(C2510a.b(valueOf));
                    return;
                case 4:
                    this.f6747d.setText(C2510a.a(valueOf));
                    return;
                default:
                    return;
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null) {
            String stringExtra = intent.getStringExtra("valid_code");
            Intent intent2;
            switch (i) {
                case 16:
                    a(stringExtra, this.c.getAuthKey(), this.c.getAuthType());
                    return;
                case 17:
                    intent2 = new Intent(this, ResetBindMailActivity.class);
                    intent2.putExtra("is_reset", true);
                    intent2.putExtra("account_dto", this.c);
                    startActivity(intent2);
                    return;
                case 18:
                    intent2 = new Intent(this, ResetBindPhoneActivity.class);
                    intent2.putExtra("is_reset", true);
                    intent2.putExtra("account_dto", this.c);
                    startActivity(intent2);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: a */
    protected void m7938a(List<AccountDTO> list) {
        super.a(list);
        if (list != null) {
            for (AccountDTO accountDTO : list) {
                if (accountDTO != null && accountDTO.getAuthType() == 1) {
                    break;
                }
            }
            AccountDTO accountDTO2 = null;
            if (accountDTO2 != null && accountDTO2.getStatus() != 1) {
                c(2, list);
                Toasts.show(this, C1373R.string.str_account_manage_unbind_success);
                finish();
            }
        }
    }
}
