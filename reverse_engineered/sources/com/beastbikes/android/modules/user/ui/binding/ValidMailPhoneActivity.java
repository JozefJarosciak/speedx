package com.beastbikes.android.modules.user.ui.binding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.modules.user.ui.binding.widget.ValidCodeView;
import com.beastbikes.android.modules.user.ui.binding.widget.ValidCodeView$b;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;

@C1457a(a = "邮箱手机修改获取验证码页")
@C1459b(a = 2130903149)
public class ValidMailPhoneActivity extends BaseAccountActivity implements OnClickListener, ValidCodeView$b {
    /* renamed from: d */
    public static String f6780d = "is_unbind";
    @C1458a(a = 2131755801)
    /* renamed from: e */
    private ValidCodeView f6781e;
    @C1458a(a = 2131755802)
    /* renamed from: f */
    private TextView f6782f;
    @C1458a(a = 2131755318)
    /* renamed from: g */
    private TextView f6783g;
    /* renamed from: h */
    private boolean f6784h;
    /* renamed from: i */
    private boolean f6785i;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.c = (AccountDTO) intent.getSerializableExtra("account_dto");
        this.f6784h = intent.getBooleanExtra("is_mail", true);
        this.f6785i = intent.getBooleanExtra(f6780d, false);
        if (this.f6784h) {
            this.f6783g.setText(C1373R.string.str_account_manage_authentication_have_send_vcode_to_email);
            setTitle(C1373R.string.str_account_manage_mail_verification);
        } else {
            this.f6783g.setText(C1373R.string.str_account_manage_authentication_have_send_vcode_to_phone);
            setTitle(C1373R.string.str_account_manage_phone_num_verification);
        }
        this.f6781e.m7969a((ValidCodeView$b) this, (int) C1373R.string.str_valid_code);
        this.f6781e.m7968a();
        this.f6782f.setOnClickListener(this);
    }

    public void finish() {
        super.finish();
        this.f6781e.destroy();
    }

    public void onClick(View view) {
        if (view == this.f6782f) {
            a(this.f6781e.getText(), this.c.getAuthKey(), null);
        }
    }

    /* renamed from: b */
    public boolean m7961b() {
        String str = "defaultPhone";
        if (this.f6784h) {
            str = "defaultEmail";
        } else if (this.f6785i) {
            str = "unbindEmail";
        }
        a(this.c.getAuthKey(), str);
        return true;
    }

    /* renamed from: a */
    public void m7959a(boolean z) {
        this.f6782f.setEnabled(z);
        this.f6782f.setClickable(z);
    }

    /* renamed from: a */
    protected void m7960a(boolean z, String str, String str2) {
        super.a(z, str, str2);
        if (z) {
            Intent intent = getIntent();
            intent.putExtra("valid_code", str);
            setResult(-1, intent);
            finish();
        }
    }
}
