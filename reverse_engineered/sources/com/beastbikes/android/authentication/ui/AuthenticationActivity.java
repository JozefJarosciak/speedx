package com.beastbikes.android.authentication.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import cn.sharesdk.facebook.Facebook;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.google.GooglePlus;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.twitter.Twitter;
import cn.sharesdk.wechat.friends.Wechat;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.authentication.AuthenticationException;
import com.beastbikes.android.authentication.C1541b;
import com.beastbikes.android.authentication.p055a.C1536a;
import com.beastbikes.android.authentication.p055a.C1536a.C1535a;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.main.MeiZuSettingActivity;
import com.beastbikes.android.main.MiuiSettingActivity;
import com.beastbikes.android.main.SelectAuthActivity;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.preferences.ui.UserSettingActivityFromAuth;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.modules.user.ui.binding.CountryPageActivity;
import com.beastbikes.android.modules.user.ui.binding.ResetBindPhoneActivity;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.utils.aa;
import com.beastbikes.android.widget.p165a.C2608b;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Marker;

@C1457a(a = "注册登录页(不区分注册页或登录页)")
@C1459b(a = 2130903224)
public class AuthenticationActivity extends SessionFragmentActivity implements OnClickListener, C1371a, C1535a {
    /* renamed from: A */
    private TextView f4049A;
    /* renamed from: B */
    private TextView f4050B;
    /* renamed from: C */
    private C1536a f4051C;
    /* renamed from: D */
    private C1802i f4052D;
    /* renamed from: E */
    private CountDownTimer f4053E;
    /* renamed from: F */
    private AnimationSet f4054F;
    /* renamed from: G */
    private List<View> f4055G = new ArrayList();
    /* renamed from: H */
    private SharedPreferences f4056H;
    @C1458a(a = 2131756193)
    /* renamed from: a */
    private ViewGroup f4057a;
    @C1458a(a = 2131756209)
    /* renamed from: b */
    private TextView f4058b;
    @C1458a(a = 2131756210)
    /* renamed from: c */
    private TextView f4059c;
    @C1458a(a = 2131756189)
    /* renamed from: d */
    private TextView f4060d;
    @C1458a(a = 2131756194)
    /* renamed from: e */
    private TextView f4061e;
    @C1458a(a = 2131756192)
    /* renamed from: f */
    private ViewGroup f4062f;
    @C1458a(a = 2131756204)
    /* renamed from: g */
    private EditText f4063g;
    @C1458a(a = 2131756205)
    /* renamed from: h */
    private EditText f4064h;
    @C1458a(a = 2131756211)
    /* renamed from: i */
    private EditText f4065i;
    @C1458a(a = 2131756212)
    /* renamed from: j */
    private EditText f4066j;
    @C1458a(a = 2131756213)
    /* renamed from: k */
    private EditText f4067k;
    @C1458a(a = 2131756190)
    /* renamed from: l */
    private ViewGroup f4068l;
    @C1458a(a = 2131756191)
    /* renamed from: m */
    private ViewGroup f4069m;
    @C1458a(a = 2131756216)
    /* renamed from: n */
    private TextView f4070n;
    @C1458a(a = 2131756219)
    /* renamed from: o */
    private Button f4071o;
    @C1458a(a = 2131756221)
    /* renamed from: p */
    private Button f4072p;
    @C1458a(a = 2131756218)
    /* renamed from: q */
    private EditText f4073q;
    @C1458a(a = 2131756220)
    /* renamed from: r */
    private EditText f4074r;
    @C1458a(a = 2131756215)
    /* renamed from: s */
    private EditText f4075s;
    @C1458a(a = 2131756217)
    /* renamed from: t */
    private EditText f4076t;
    @C1458a(a = 2131756208)
    /* renamed from: u */
    private Button f4077u;
    @C1458a(a = 2131756214)
    /* renamed from: v */
    private Button f4078v;
    @C1458a(a = 2131756207)
    /* renamed from: w */
    private View f4079w;
    @C1458a(a = 2131756206)
    /* renamed from: x */
    private View f4080x;
    @C1458a(a = 2131756187)
    /* renamed from: y */
    private ImageView f4081y;
    @C1458a(a = 2131756187)
    /* renamed from: z */
    private ImageView f4082z;

    public void onBackPressed() {
        setResult(0, getIntent());
        finish();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f4056H = getSharedPreferences(getPackageName(), 0);
        this.f4051C = new C1536a((BeastBikes) getApplication());
        this.f4054F = m5375y();
        this.f4065i.setFilters(new InputFilter[]{new LengthFilter(12)});
        this.f4060d.setOnClickListener(this);
        this.f4077u.setOnClickListener(this);
        this.f4078v.setOnClickListener(this);
        this.f4079w.setOnClickListener(this);
        this.f4080x.setOnClickListener(this);
        this.f4059c.setOnClickListener(this);
        this.f4058b.setOnClickListener(this);
        this.f4061e.setOnClickListener(this);
        this.f4072p.setOnClickListener(this);
        this.f4071o.setOnClickListener(this);
        this.f4070n.setOnClickListener(this);
        this.f4070n.setText(Marker.ANY_NON_NULL_MARKER + C1849a.a(this));
        this.f4063g.setOnTouchListener(new AuthenticationActivity$1(this));
        this.f4061e.getPaint().setFlags(8);
        if (AVUser.getCurrentUser() != null) {
            m5349g();
            this.f4063g.setText(AVUser.getCurrentUser().getEmail());
        } else {
            m5347f();
        }
        LayoutInflater layoutInflater = getLayoutInflater();
        View inflate = layoutInflater.inflate(C1373R.layout.authentication_sign_by_qq_weibo_weixin, null);
        this.f4049A = (TextView) findViewById(C1373R.id.authentication_activity_bottom_view1);
        this.f4049A.setSelected(true);
        View inflate2 = layoutInflater.inflate(C1373R.layout.authentication_sign_by_facebook_twitter_googleplus, null);
        this.f4050B = (TextView) findViewById(C1373R.id.authentication_activity_bottom_view2);
        if (C1849a.a()) {
            this.f4055G.add(inflate);
            this.f4055G.add(inflate2);
        } else {
            this.f4055G.add(inflate2);
            this.f4055G.add(inflate);
        }
        ImageButton imageButton = (ImageButton) inflate.findViewById(C1373R.id.authentication_activity_auth_weixin);
        ImageButton imageButton2 = (ImageButton) inflate.findViewById(C1373R.id.authentication_activity_auth_tencent);
        ImageButton imageButton3 = (ImageButton) inflate.findViewById(C1373R.id.authentication_activity_auth_weibo);
        imageButton.setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        imageButton2 = (ImageButton) inflate2.findViewById(C1373R.id.authentication_activity_auth_twitter);
        imageButton3 = (ImageButton) inflate2.findViewById(C1373R.id.authentication_activity_auth_googleplus);
        ((ImageButton) inflate2.findViewById(C1373R.id.authentication_activity_auth_facebook)).setOnClickListener(this);
        imageButton2.setOnClickListener(this);
        imageButton3.setOnClickListener(this);
        ViewPager viewPager = (ViewPager) findViewById(C1373R.id.authentication_activity_viewpager);
        PagerAdapter authenticationActivity$a = new AuthenticationActivity$a(this);
        viewPager.addOnPageChangeListener(new AuthenticationActivity$6(this));
        viewPager.setAdapter(authenticationActivity$a);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f4052D = null;
        C2608b.a(this.f4081y);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.authentication_activity_form_switch:
                m5338b();
                return;
            case C1373R.id.authentication_sign_up_switch:
                m5341c();
                return;
            case C1373R.id.authentication_activity_auth_facebook:
                m5363n();
                return;
            case C1373R.id.authentication_activity_auth_twitter:
                m5365o();
                return;
            case C1373R.id.authentication_activity_auth_googleplus:
                Platform platform = ShareSDK.getPlatform(GooglePlus.NAME);
                if (platform == null || !platform.isClientValid()) {
                    Toasts.show(this, C1373R.string.google_plus_is_not_valid);
                    return;
                } else {
                    m5368r();
                    return;
                }
            case C1373R.id.authentication_activity_auth_weibo:
                m5357k();
                return;
            case C1373R.id.authentication_activity_auth_weixin:
                m5361m();
                return;
            case C1373R.id.authentication_activity_auth_tencent:
                m5358l();
                return;
            case C1373R.id.authentication_sign_in_fragment_forget_password_fl:
            case C1373R.id.authentication_sign_in_fragment_forget_password:
                m5370t();
                return;
            case C1373R.id.authentication_sign_in_fragment_button_sign_in:
                m5351h();
                return;
            case C1373R.id.authentication_switch_to_sign_up_by_phone:
                m5345e();
                return;
            case C1373R.id.authentication_switch_to_sign_up_by_email:
                m5343d();
                return;
            case C1373R.id.authentication_sign_up_fragment_button_sign_up:
                m5353i();
                return;
            case C1373R.id.authentication_sign_up_fragment_zone:
                startActivityForResult(new Intent(this, CountryPageActivity.class), 4660);
                return;
            case C1373R.id.authentication_send_valid:
                m5369s();
                return;
            case C1373R.id.authentication_sign_up_fragment_button_sign_up1:
                m5355j();
                return;
            default:
                return;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i) {
            case 1:
                m5376a();
                return;
            case 2:
                if (intent != null) {
                    int intExtra = intent.getIntExtra("extra_result_redirect", 549);
                    CharSequence stringExtra = intent.getStringExtra("extra_result_areacode");
                    CharSequence stringExtra2 = intent.getStringExtra("extra_result_phone");
                    CharSequence stringExtra3 = intent.getStringExtra("extra_result_email");
                    switch (intExtra) {
                        case 548:
                            m5349g();
                            this.f4063g.setText(stringExtra2);
                            return;
                        case 549:
                            m5345e();
                            this.f4076t.setText(stringExtra2);
                            this.f4070n.setText(stringExtra);
                            return;
                        case 550:
                            m5343d();
                            this.f4066j.setText(stringExtra3);
                            return;
                        default:
                            return;
                    }
                }
                return;
            case 4660:
                if (intent != null) {
                    if (!TextUtils.isEmpty(intent.getStringExtra("code"))) {
                        this.f4070n.setText(String.format("+%s", new Object[]{r0}));
                        return;
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (aa.a(currentFocus, motionEvent)) {
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(motionEvent);
        } else if (getWindow().superDispatchTouchEvent(motionEvent)) {
            return true;
        } else {
            return onTouchEvent(motionEvent);
        }
    }

    /* renamed from: b */
    private void m5338b() {
        if (8 == this.f4062f.getVisibility() && (this.f4068l.getVisibility() == 0 || this.f4069m.getVisibility() == 0 || this.f4057a.getVisibility() == 0)) {
            m5349g();
        } else if ((8 == this.f4068l.getVisibility() || 8 == this.f4069m.getVisibility() || 8 == this.f4057a.getVisibility()) && this.f4062f.getVisibility() == 0) {
            m5347f();
        }
    }

    /* renamed from: c */
    private void m5341c() {
        if (this.f4068l.getVisibility() == 0) {
            m5345e();
        } else {
            m5343d();
        }
    }

    /* renamed from: d */
    private void m5343d() {
        this.f4054F.setAnimationListener(new AuthenticationActivity$7(this));
        this.f4068l.startAnimation(this.f4054F);
        this.f4060d.setText(C1373R.string.str_already_have_an_account);
        this.f4061e.setText(C1373R.string.authentication_switch_to_phone);
    }

    /* renamed from: e */
    private void m5345e() {
        this.f4060d.setText(C1373R.string.str_already_have_an_account);
        this.f4061e.setText(C1373R.string.authentication_switch_to_email);
        this.f4054F.setAnimationListener(new AuthenticationActivity$8(this));
        this.f4069m.startAnimation(this.f4054F);
    }

    /* renamed from: f */
    private void m5347f() {
        this.f4062f.setVisibility(8);
        this.f4068l.setVisibility(8);
        this.f4069m.setVisibility(8);
        this.f4057a.setVisibility(0);
        this.f4061e.setVisibility(8);
        this.f4060d.setText(C1373R.string.str_already_have_an_account);
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.f4073q.getWindowToken(), 0);
        }
        this.f4082z.setAlpha(1.0f);
        C2608b.a(this.f4081y, C1373R.drawable.authentication_bg);
    }

    /* renamed from: g */
    private void m5349g() {
        this.f4069m.setVisibility(8);
        this.f4057a.setVisibility(8);
        this.f4068l.setVisibility(8);
        this.f4061e.setVisibility(8);
        this.f4054F.setAnimationListener(new AuthenticationActivity$9(this));
        this.f4062f.startAnimation(this.f4054F);
        this.f4060d.setText(C1373R.string.authentication_sign_in_fragment_back_to_sign_up);
    }

    /* renamed from: h */
    private void m5351h() {
        if (TextUtils.isEmpty(this.f4063g.getText())) {
            Toasts.show(this, C1373R.string.str_please_input_email);
            this.f4063g.requestFocus();
        } else if (TextUtils.isEmpty(this.f4064h.getText())) {
            Toasts.show(this, C1373R.string.str_please_input_password);
            this.f4064h.requestFocus();
        } else {
            this.f4052D = new C1802i(this, getString(C1373R.string.authentication_sign_in_loading_msg), false);
            this.f4052D.show();
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setUsername(this.f4063g.getText().toString());
            accountDTO.setPassword(this.f4064h.getText().toString());
            accountDTO.setAuthType(accountDTO.getUsername().contains("@") ? 1 : 2);
            this.f4051C.a(accountDTO, this);
        }
    }

    /* renamed from: i */
    private void m5353i() {
        if (TextUtils.isEmpty(this.f4065i.getText().toString().trim())) {
            Toasts.show(this, C1373R.string.str_nickname_is_required);
            this.f4065i.requestFocus();
        } else if (TextUtils.isEmpty(this.f4066j.getText())) {
            Toasts.show(this, C1373R.string.str_please_input_email);
            this.f4066j.requestFocus();
        } else if (TextUtils.isEmpty(this.f4067k.getText())) {
            Toasts.show(this, C1373R.string.str_please_input_password);
            this.f4067k.requestFocus();
        } else if (this.f4067k.getText().length() < 6) {
            Toasts.show(this, C1373R.string.str_sign_up_password_limit_6_16);
            this.f4067k.requestFocus();
        } else {
            this.f4052D = new C1802i(this, getString(C1373R.string.authentication_sign_up_loading_msg), false);
            this.f4052D.show();
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setUsername(this.f4066j.getText().toString());
            accountDTO.setPassword(this.f4067k.getText().toString());
            accountDTO.setNickname(this.f4065i.getText().toString());
            accountDTO.setAuthType(1);
            this.f4051C.a(accountDTO, null, this);
        }
    }

    /* renamed from: j */
    private void m5355j() {
        if (TextUtils.isEmpty(this.f4075s.getText().toString().trim())) {
            Toasts.show(this, C1373R.string.str_nickname_is_required);
            this.f4065i.requestFocus();
            return;
        }
        Object trim = this.f4076t.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            Toasts.show(this, C1373R.string.str_please_input_phone_number);
            this.f4076t.requestFocus();
            return;
        }
        Object obj = this.f4073q.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            Toasts.show(this, C1373R.string.str_please_input_vcode);
            this.f4073q.requestFocus();
        } else if (TextUtils.isEmpty(this.f4074r.getText())) {
            Toasts.show(this, C1373R.string.str_please_input_password);
            this.f4074r.requestFocus();
        } else if (this.f4074r.getText().length() < 6) {
            Toasts.show(this, C1373R.string.str_sign_up_password_limit_6_16);
            this.f4074r.requestFocus();
        } else {
            this.f4052D = new C1802i(this, getString(C1373R.string.authentication_sign_up_loading_msg), false);
            this.f4052D.show();
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setUsername(this.f4070n.getText().toString() + trim);
            accountDTO.setPassword(this.f4074r.getText().toString());
            accountDTO.setNickname(this.f4075s.getText().toString());
            accountDTO.setAuthType(2);
            this.f4051C.a(accountDTO, obj, this);
        }
    }

    /* renamed from: k */
    private void m5357k() {
        this.f4052D = new C1802i(this, getString(C1373R.string.authentication_sign_up_loading_msg), false);
        C1541b.a(this, SinaWeibo.NAME, new AuthenticationActivity$10(this));
    }

    /* renamed from: l */
    private void m5358l() {
        this.f4052D = new C1802i(this, getString(C1373R.string.authentication_sign_in_loading_msg), false);
        C1541b.a(this, QQ.NAME, new AuthenticationActivity$11(this));
    }

    /* renamed from: m */
    private void m5361m() {
        this.f4052D = new C1802i(this, getString(C1373R.string.authentication_sign_in_loading_msg), false);
        C1541b.a(this, Wechat.NAME, new AuthenticationActivity$12(this));
    }

    /* renamed from: n */
    private void m5363n() {
        this.f4052D = new C1802i(this, getString(C1373R.string.authentication_sign_in_loading_msg), false);
        C1541b.a(this, Facebook.NAME, new AuthenticationActivity$13(this));
    }

    /* renamed from: o */
    private void m5365o() {
        this.f4052D = new C1802i(this, getString(C1373R.string.authentication_sign_in_loading_msg), false);
        C1541b.a(this, Twitter.NAME, new AuthenticationActivity$2(this));
    }

    /* renamed from: r */
    private void m5368r() {
        this.f4052D = new C1802i(this, getString(C1373R.string.authentication_sign_in_loading_msg), false);
        C1541b.a(this, GooglePlus.NAME, new AuthenticationActivity$3(this));
    }

    /* renamed from: s */
    private void m5369s() {
        if (TextUtils.isEmpty(this.f4076t.getText().toString())) {
            Toasts.show(this, C1373R.string.str_please_input_phone_number);
            return;
        }
        this.f4071o.setClickable(false);
        getAsyncTaskQueue().a(new AuthenticationActivity$4(this), new String[]{this.f4070n.getText().toString() + this.f4076t.getText().toString()});
    }

    /* renamed from: t */
    private void m5370t() {
        Intent intent = new Intent(this, FindPassWordActivity.class);
        intent.putExtra("account", this.f4063g.getText().toString());
        startActivityForResult(intent, 2);
    }

    /* renamed from: a */
    private void m5334a(int i) {
        this.f4053E = new AuthenticationActivity$5(this, (long) (i * 1000), 1000);
        this.f4053E.start();
    }

    /* renamed from: a */
    public void m5377a(AuthenticationException authenticationException, AccountDTO accountDTO) {
        if (this.f4052D != null && this.f4052D.isShowing()) {
            this.f4052D.dismiss();
        }
        if (authenticationException == null) {
            m5336a(accountDTO);
        } else if (authenticationException.getErrorNumber() != 1007 || accountDTO.getAuthType() == 1 || accountDTO.getAuthType() == 2) {
            Toasts.show(this, authenticationException.getMessage());
        } else {
            m5339b(accountDTO);
        }
    }

    /* renamed from: b */
    public void m5379b(AuthenticationException authenticationException, AccountDTO accountDTO) {
        if (this.f4052D != null && this.f4052D.isShowing()) {
            this.f4052D.dismiss();
        }
        if (authenticationException != null) {
            switch (authenticationException.getErrorNumber()) {
                case 0:
                    Toasts.show(this, C1373R.string.str_nickname_has_exist);
                    return;
                case 1008:
                    if (!TextUtils.isEmpty(this.f4066j.getText())) {
                        this.f4063g.setText(this.f4066j.getText());
                        m5349g();
                        Toasts.show(this, authenticationException.getMessage());
                        return;
                    }
                    return;
                default:
                    Toasts.show(this, authenticationException.getMessage());
                    return;
            }
        }
        C2580w.a(this, getString(C1373R.string.authentication_event_sign_in), null);
        m5336a(accountDTO);
    }

    /* renamed from: a */
    private void m5336a(AccountDTO accountDTO) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(this, SelectAuthActivity.class));
        } else if (TextUtils.isEmpty(currentUser.getMobilePhoneNumber()) && (TextUtils.isEmpty(currentUser.getEmail()) || currentUser.getEmail().contains("beastbikes.default.com"))) {
            m5339b(accountDTO);
        } else {
            m5371u();
        }
    }

    /* renamed from: b */
    public void mo2739b(int i, Object obj) {
        switch (i) {
            case 1:
                m5371u();
                return;
            default:
                return;
        }
    }

    /* renamed from: u */
    private void m5371u() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(this, SelectAuthActivity.class));
        } else if (currentUser.isEdited()) {
            m5376a();
        } else {
            Intent intent = new Intent(this, UserSettingActivityFromAuth.class);
            intent.putExtra("from_auth", 2);
            startActivityForResult(intent, 1);
        }
    }

    /* renamed from: b */
    private void m5339b(AccountDTO accountDTO) {
        Intent intent = new Intent(this, ResetBindPhoneActivity.class);
        intent.putExtra("from_register", true);
        intent.putExtra("account_dto", accountDTO);
        m5325a(intent, 1);
    }

    /* renamed from: a */
    protected void m5376a() {
        String str = Build.BRAND;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            CookieManager.getInstance().setCookie(a$c.f7201b, "sessionid=" + currentUser.getSessionToken());
            currentUser.setAuthenticated(true);
            AVUser.saveCurrentUser(currentUser);
        }
        boolean z = this.f4056H.getBoolean("guide_setting", false);
        if (str.equalsIgnoreCase("Xiaomi") && !z) {
            m5372v();
        } else if (!str.equalsIgnoreCase("Meizu") || z) {
            Intent intent = new Intent(this, HomeActivity.class);
            Intent intent2 = getIntent();
            if (intent2 != null) {
                Object stringExtra = intent2.getStringExtra("push_data");
                if (!TextUtils.isEmpty(stringExtra)) {
                    intent.putExtra("push_data", stringExtra);
                }
                String stringExtra2 = intent2.getStringExtra("RONGCLOUDPUSHRONGCLOUDPUSHKEY");
                if (TextUtils.isEmpty(stringExtra2)) {
                    Log.e("rongPush", "null");
                } else {
                    Log.e("rongPush", stringExtra2);
                    intent.putExtra("RONGCLOUDPUSHRONGCLOUDPUSHKEY", stringExtra2);
                }
                if (getIntent() != null) {
                    Uri data = getIntent().getData();
                    if (data != null) {
                        intent.setData(data);
                    }
                }
            }
            startActivity(intent);
            finish();
        } else {
            m5373w();
        }
    }

    /* renamed from: v */
    private void m5372v() {
        startActivity(new Intent(this, MiuiSettingActivity.class));
        this.f4056H.edit().putBoolean("guide_setting", true).apply();
        finish();
    }

    /* renamed from: w */
    private void m5373w() {
        startActivity(new Intent(this, MeiZuSettingActivity.class));
        this.f4056H.edit().putBoolean("guide_setting", true).apply();
        finish();
    }

    /* renamed from: x */
    private void m5374x() {
        ((InputMethodManager) getSystemService("input_method")).toggleSoftInputFromWindow(this.f4073q.getWindowToken(), 1, 2);
    }

    /* renamed from: y */
    private AnimationSet m5375y() {
        Animation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        AnimationSet animationSet = new AnimationSet(this, null);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(400);
        return animationSet;
    }
}
