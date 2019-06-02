package com.beastbikes.android.authentication.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.p055a.C1536a;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.user.ui.binding.CountryPageActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import java.util.regex.Pattern;
import org.slf4j.Marker;

@C1457a(a = "找回密码")
@C1459b(a = 2130903127)
public class FindPassWordActivity extends SessionFragmentActivity implements OnClickListener {
    @C1458a(a = 2131755718)
    /* renamed from: a */
    private View f4083a;
    @C1458a(a = 2131755719)
    /* renamed from: b */
    private TextView f4084b;
    @C1458a(a = 2131755720)
    /* renamed from: c */
    private TextView f4085c;
    @C1458a(a = 2131755721)
    /* renamed from: d */
    private View f4086d;
    @C1458a(a = 2131755722)
    /* renamed from: e */
    private TextView f4087e;
    @C1458a(a = 2131755723)
    /* renamed from: f */
    private TextView f4088f;
    @C1458a(a = 2131755724)
    /* renamed from: g */
    private View f4089g;
    @C1458a(a = 2131755725)
    /* renamed from: h */
    private View f4090h;
    @C1458a(a = 2131755726)
    /* renamed from: i */
    private EditText f4091i;
    @C1458a(a = 2131755727)
    /* renamed from: j */
    private TextView f4092j;
    @C1458a(a = 2131755728)
    /* renamed from: k */
    private TextView f4093k;
    @C1458a(a = 2131755729)
    /* renamed from: l */
    private EditText f4094l;
    @C1458a(a = 2131755730)
    /* renamed from: m */
    private EditText f4095m;
    @C1458a(a = 2131755732)
    /* renamed from: n */
    private EditText f4096n;
    @C1458a(a = 2131755733)
    /* renamed from: o */
    private TextView f4097o;
    @C1458a(a = 2131755731)
    /* renamed from: p */
    private TextView f4098p;
    /* renamed from: q */
    private ActionBar f4099q;
    /* renamed from: r */
    private C1536a f4100r;
    /* renamed from: s */
    private String f4101s;
    /* renamed from: t */
    private FindPassWordActivity$a f4102t;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.f4099q = getSupportActionBar();
        if (this.f4099q != null) {
            this.f4099q.setDisplayHomeAsUpEnabled(true);
        }
        this.f4083a.setOnClickListener(this);
        this.f4086d.setOnClickListener(this);
        this.f4092j.setOnClickListener(this);
        this.f4098p.setOnClickListener(this);
        this.f4097o.setOnClickListener(this);
        this.f4093k.setOnClickListener(this);
        this.f4100r = new C1536a(this);
        this.f4101s = getIntent().getStringExtra("account");
        if (m5383a(this.f4101s)) {
            m5389d();
        } else {
            m5387c();
        }
        m5382a();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    /* renamed from: a */
    private void m5382a() {
        this.f4093k.setText(Marker.ANY_NON_NULL_MARKER + PhoneNumberUtil.getInstance().getCountryCodeForRegion(getResources().getConfiguration().locale.getCountry()));
    }

    public void finish() {
        if (this.f4102t != null) {
            this.f4102t.cancel();
        }
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: b */
    private void m5385b() {
        Object obj = this.f4091i.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            Toasts.showOnUiThread(this, getString(C1373R.string.activity_find_pass_word_mail_empty));
            return;
        }
        C1802i c1802i = new C1802i(this, getString(C1373R.string.loading_msg), false);
        c1802i.setCancelable(true);
        getAsyncTaskQueue().a(new FindPassWordActivity$1(this, c1802i, obj), new String[0]);
    }

    /* renamed from: c */
    private void m5387c() {
        this.f4084b.setTextColor(getResources().getColor(C1373R.color.account_management_color));
        this.f4085c.setVisibility(0);
        this.f4087e.setTextColor(getResources().getColor(C1373R.color.text_default));
        this.f4088f.setVisibility(8);
        this.f4089g.setVisibility(0);
        this.f4090h.setVisibility(8);
        if (!m5383a(this.f4101s)) {
            this.f4091i.setText(this.f4101s);
        }
    }

    /* renamed from: d */
    private void m5389d() {
        this.f4084b.setTextColor(getResources().getColor(C1373R.color.text_default));
        this.f4085c.setVisibility(8);
        this.f4087e.setTextColor(getResources().getColor(C1373R.color.account_management_color));
        this.f4088f.setVisibility(0);
        this.f4089g.setVisibility(8);
        this.f4090h.setVisibility(0);
        if (m5383a(this.f4101s)) {
            this.f4094l.setText(this.f4101s);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.activity_find_pass_word_mail:
                m5387c();
                return;
            case C1373R.id.activity_find_pass_word_phone:
                m5389d();
                return;
            case C1373R.id.activity_find_pass_word_mail_commit:
                m5385b();
                return;
            case C1373R.id.activity_find_pass_word_phone_areacode:
                startActivityForResult(new Intent(this, CountryPageActivity.class), 291);
                return;
            case C1373R.id.activity_find_pass_word_code:
                m5391e();
                return;
            case C1373R.id.activity_find_pass_word_commit:
                m5392f();
                return;
            default:
                return;
        }
    }

    /* renamed from: e */
    private void m5391e() {
        String charSequence = this.f4093k.getText().toString();
        Object obj = this.f4094l.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            Toasts.showOnUiThread(this, getString(C1373R.string.activity_find_pass_word_phone_empty));
            return;
        }
        C1802i c1802i = new C1802i(this, getString(C1373R.string.loading_msg), false);
        c1802i.setCancelable(true);
        getAsyncTaskQueue().a(new FindPassWordActivity$2(this, c1802i, charSequence, obj), new String[0]);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent != null) {
            switch (i2) {
                case -1:
                    switch (i) {
                        case 291:
                            this.f4093k.setText(Marker.ANY_NON_NULL_MARKER + intent.getStringExtra("code"));
                            return;
                        case 292:
                            String stringExtra = intent.getStringExtra("extra_result_email");
                            Intent intent2 = getIntent();
                            intent2.putExtra("extra_result_email", stringExtra);
                            intent2.putExtra("extra_result_redirect", 550);
                            setResult(-1, intent2);
                            finish();
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    /* renamed from: f */
    private void m5392f() {
        String charSequence = this.f4093k.getText().toString();
        Object obj = this.f4094l.getText().toString();
        Object obj2 = this.f4095m.getText().toString();
        String obj3 = this.f4096n.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            Toasts.showOnUiThread(this, getString(C1373R.string.activity_find_pass_word_phone_empty));
        } else if (TextUtils.isEmpty(obj2)) {
            Toasts.showOnUiThread(this, getString(C1373R.string.activity_find_pass_word_cod_empty));
        } else if (TextUtils.isEmpty(obj3)) {
            Toasts.showOnUiThread(this, getString(C1373R.string.activity_find_pass_password_cod_empty));
        } else if (obj3.length() < 6 || obj3.length() > 16) {
            Toasts.showOnUiThread(this, getString(C1373R.string.activity_find_pass_password_cod_error));
        } else {
            C1802i c1802i = new C1802i(this, getString(C1373R.string.loading_msg), false);
            c1802i.setCancelable(true);
            getAsyncTaskQueue().a(new FindPassWordActivity$3(this, c1802i, charSequence, obj, obj2, obj3), new String[0]);
        }
    }

    /* renamed from: a */
    public static boolean m5383a(String str) {
        return Pattern.compile("[0-9]+").matcher(str).matches();
    }
}
