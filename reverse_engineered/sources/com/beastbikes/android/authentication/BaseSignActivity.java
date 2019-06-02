package com.beastbikes.android.authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.authentication.login.LoginActivity;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.main.MeiZuSettingActivity;
import com.beastbikes.android.main.MiuiSettingActivity;
import com.beastbikes.android.main.SelectAuthActivity;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.user.dto.AccountDTO;
import com.beastbikes.android.modules.user.ui.ProfileSettingsActivity;
import com.beastbikes.android.modules.user.ui.TrainTargetSetActivity;
import com.beastbikes.android.modules.user.ui.binding.ResetBindPhoneActivity;
import com.beastbikes.android.utils.aa;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.lang.ref.WeakReference;
import org.slf4j.Marker;

public abstract class BaseSignActivity<T extends C1542d> extends SessionFragmentActivity implements C1530e {
    /* renamed from: a */
    private C1802i f7214a;
    /* renamed from: b */
    private SharedPreferences f7215b;
    /* renamed from: c */
    public int f7216c = 2;
    /* renamed from: d */
    public String f7217d;
    /* renamed from: e */
    protected T f7218e;
    /* renamed from: f */
    public int f7219f;
    /* renamed from: g */
    private String f7220g;

    /* renamed from: c */
    protected abstract T m8429c();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.f7219f = C1849a.m9639a((Context) this);
        Intent intent = getIntent();
        this.f7216c = intent.getIntExtra("key_login_type", 2);
        this.f7217d = intent.getStringExtra("key_username");
        this.f7218e = m8429c();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (aa.m12772a(currentFocus, motionEvent)) {
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

    /* renamed from: d */
    public BaseSignActivity mo3110d() {
        return (BaseSignActivity) new WeakReference(this).get();
    }

    /* renamed from: e */
    public String mo3111e() {
        return null;
    }

    /* renamed from: f */
    public String mo3112f() {
        return null;
    }

    /* renamed from: g */
    public String mo3113g() {
        return null;
    }

    /* renamed from: h */
    public String mo3114h() {
        return Marker.ANY_NON_NULL_MARKER + this.f7219f;
    }

    /* renamed from: i */
    public int mo3115i() {
        return this.f7216c;
    }

    /* renamed from: j */
    public String mo3116j() {
        return this.f7220g;
    }

    /* renamed from: k */
    public void mo3117k() {
    }

    /* renamed from: c */
    public void mo3109c(CharSequence charSequence) {
        Toasts.show((Context) this, charSequence);
    }

    /* renamed from: l */
    public void mo3118l() {
        if (this.f7214a == null) {
            this.f7214a = new C1802i(new WeakReference(this), getString(C1373R.string.str_loading), false);
        }
        if (!this.f7214a.isShowing()) {
            this.f7214a.show();
        }
    }

    /* renamed from: m */
    public void mo3119m() {
        if (this.f7214a != null && this.f7214a.isShowing()) {
            this.f7214a.dismiss();
        }
    }

    /* renamed from: a */
    public void mo3107a(AccountDTO accountDTO) {
        Intent intent = new Intent(this, ResetBindPhoneActivity.class);
        intent.putExtra("from_register", true);
        intent.putExtra("account_dto", accountDTO);
        a(intent, 1);
    }

    /* renamed from: n */
    public void mo3120n() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(this, SelectAuthActivity.class));
        } else if (TextUtils.isEmpty(currentUser.getDisplayName())) {
            r0 = new Intent(this, ProfileSettingsActivity.class);
            c(18, null);
            startActivity(r0);
            finish();
        } else if (currentUser.getMaxHeartRate() <= 0) {
            r0 = new Intent(this, TrainTargetSetActivity.class);
            r0.putExtra("enter_type", 0);
            startActivity(r0);
            finish();
        } else {
            m8442o();
        }
    }

    /* renamed from: b */
    public void mo3108b(AccountDTO accountDTO) {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            c(18, null);
            startActivity(intent);
            finish();
        } else if (TextUtils.isEmpty(currentUser.getMobilePhoneNumber()) && (TextUtils.isEmpty(currentUser.getEmail()) || currentUser.getEmail().contains("beastbikes.default.com"))) {
            mo3107a(accountDTO);
        } else {
            mo3120n();
        }
    }

    /* renamed from: a */
    public void mo3106a(int i, Object obj) {
        c(i, obj);
    }

    /* renamed from: a */
    public void m8426a(String str, int i) {
        this.f7216c = i;
        this.f7220g = str;
        this.f7218e.m8493b();
    }

    /* renamed from: b */
    protected void m8427b(int i, Object obj) {
        if (i == 18 || i == 17) {
            finish();
        }
    }

    /* renamed from: o */
    protected void m8442o() {
        String str = Build.BRAND;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            CookieManager.getInstance().setCookie(a$c.f7201b, "sessionid=" + currentUser.getSessionToken());
            currentUser.setAuthenticated(true);
            AVUser.saveCurrentUser(currentUser);
        }
        if (this.f7215b == null) {
            this.f7215b = getSharedPreferences(getPackageName(), 0);
        }
        boolean z = this.f7215b.getBoolean("guide_setting", false);
        if (str.equalsIgnoreCase("Xiaomi") && !z) {
            m8422a();
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
            c(18, null);
            startActivity(intent);
            finish();
        } else {
            m8423b();
        }
    }

    /* renamed from: a */
    private void m8422a() {
        startActivity(new Intent(this, MiuiSettingActivity.class));
        if (this.f7215b == null) {
            this.f7215b = getSharedPreferences(getPackageName(), 0);
        }
        this.f7215b.edit().putBoolean("guide_setting", true).apply();
        finish();
    }

    /* renamed from: b */
    private void m8423b() {
        startActivity(new Intent(this, MeiZuSettingActivity.class));
        if (this.f7215b == null) {
            this.f7215b = getSharedPreferences(getPackageName(), 0);
        }
        this.f7215b.edit().putBoolean("guide_setting", true).apply();
        finish();
    }
}
