package com.beastbikes.android.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.a$c;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.main.adv.AdvService;
import com.beastbikes.android.main.adv.AdvertiseActivity;
import com.beastbikes.android.main.dto.AdvertiseDTO;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.utils.C2550a;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1457a(a = "程序入口(无界面)")
@C1459b(a = 2130903693)
public class MainActivity extends SessionFragmentActivity implements C1371a, C1392b {
    /* renamed from: a */
    private static final Logger f4447a = LoggerFactory.getLogger("MainActivity");
    /* renamed from: b */
    private SharedPreferences f4448b;
    /* renamed from: c */
    private String f4449c = "";
    /* renamed from: d */
    private String f4450d = "";
    /* renamed from: e */
    private boolean f4451e = false;
    /* renamed from: f */
    private SharedPreferences f4452f;
    /* renamed from: g */
    private MainActivity$a f4453g = new MainActivity$a(this);
    /* renamed from: h */
    private C1864a f4454h;
    /* renamed from: i */
    private float f4455i;

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 4:
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case 4:
                return true;
            default:
                return super.onKeyUp(i, keyEvent);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f4452f = getSharedPreferences(getPackageName(), 0);
        m5330e(111);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f4455i = displayMetrics.density;
        this.f4448b = getSharedPreferences("advertise", 0);
        this.f4450d = this.f4448b.getString("img_url", "");
        this.f4449c = this.f4448b.getString("url", "");
        this.f4454h = new C1864a(this);
        if (BeastBikes.f3996a) {
            m5809d();
        } else {
            this.f4454h.a();
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.f4451e) {
            this.f4451e = false;
            m5809d();
        }
    }

    /* renamed from: a */
    public MainActivity mo2754a() {
        return this;
    }

    /* renamed from: a */
    public void mo2755a(AdvertiseDTO advertiseDTO) {
        if (advertiseDTO == null || TextUtils.isEmpty(advertiseDTO.getLaunchPhoto())) {
            f4447a.error("onGetAdvInfoSuccess: advertiseDTO is null or photo is null");
            return;
        }
        f4447a.info("onGetAdvInfoSuccess");
        if (!m5816a(this.f4450d, this.f4449c)) {
            this.f4453g.postDelayed(new MainActivity$1(this), 3000);
        }
        m5808b(advertiseDTO);
    }

    /* renamed from: a */
    public void mo2756a(String str) {
        f4447a.error("onGetAdvInfoFailed: " + str);
        this.f4453g.postDelayed(new MainActivity$2(this), 3000);
    }

    /* renamed from: b */
    public void mo2758b(String str) {
        f4447a.info("onGetNullAdvInfo: " + str);
        Editor edit = this.f4448b.edit();
        edit.remove("img_url");
        edit.remove("url");
        edit.apply();
        this.f4453g.postDelayed(new MainActivity$3(this), 3000);
    }

    /* renamed from: b */
    public float mo2757b() {
        return this.f4455i;
    }

    /* renamed from: b */
    protected void mo2739b(int i, Object obj) {
        if (i == 111) {
            this.f4451e = true;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.f4453g != null) {
            this.f4453g = null;
        }
        if (this.f4454h != null) {
            this.f4454h.b();
            this.f4454h = null;
        }
    }

    /* renamed from: b */
    private void m5808b(AdvertiseDTO advertiseDTO) {
        Intent intent = new Intent(this, AdvService.class);
        intent.putExtra("advertise_dto", advertiseDTO);
        startService(intent);
    }

    /* renamed from: a */
    public boolean m5816a(String str, String str2) {
        if (TextUtils.isEmpty(str) || !C2550a.d(str, this) || AVUser.getCurrentUser() == null) {
            return false;
        }
        Intent intent = new Intent(this, AdvertiseActivity.class);
        intent.putExtra("url", str2);
        intent.putExtra("img_url", str);
        intent.addFlags(268435456);
        startActivity(intent);
        return true;
    }

    /* renamed from: d */
    private void m5809d() {
        BeastBikes.f3996a = true;
        if (this.f4452f.getBoolean("has_shown_tutorials", false) || !C1849a.a()) {
            m5810e();
            return;
        }
        startActivity(new Intent(this, TutorialActivity.class));
        finish();
    }

    /* renamed from: e */
    private void m5810e() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null || ((TextUtils.isEmpty(currentUser.getSessionToken()) && !currentUser.isAuthenticated()) || TextUtils.isEmpty(currentUser.getDisplayName()) || currentUser.getMaxHeartRate() <= 0)) {
            startActivity(new Intent(this, SelectAuthActivity.class));
            finish();
            return;
        }
        m5820c();
    }

    /* renamed from: c */
    protected void m5820c() {
        String str = Build.BRAND;
        boolean z = this.f4452f.getBoolean("guide_setting", false);
        if (str.equalsIgnoreCase("Xiaomi") && !z) {
            m5811f();
        } else if (!str.equalsIgnoreCase("Meizu") || z) {
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                CookieManager.getInstance().setCookie(a$c.f7201b, "sessionid=" + currentUser.getSessionToken());
            }
            Intent intent = new Intent(this, HomeActivity.class);
            Intent intent2 = getIntent();
            if (intent2 != null) {
                Object stringExtra = intent2.getStringExtra("push_data");
                if (!TextUtils.isEmpty(stringExtra)) {
                    intent.putExtra("push_data", stringExtra);
                }
                Object stringExtra2 = intent2.getStringExtra("RONGCLOUDPUSHRONGCLOUDPUSHKEY");
                if (!TextUtils.isEmpty(stringExtra2)) {
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
            m5812g();
        }
    }

    /* renamed from: f */
    private void m5811f() {
        startActivity(new Intent(this, MiuiSettingActivity.class));
        this.f4452f.edit().putBoolean("guide_setting", true).apply();
        finish();
    }

    /* renamed from: g */
    private void m5812g() {
        startActivity(new Intent(this, MeiZuSettingActivity.class));
        this.f4452f.edit().putBoolean("guide_setting", true).apply();
        finish();
    }
}
