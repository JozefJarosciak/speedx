package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.widget.TextView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1794e;
import com.beastbikes.android.dialog.C1796f;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.dialog.C1805k;
import com.beastbikes.android.home.HomeActivity;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.main.MeiZuSettingActivity;
import com.beastbikes.android.main.MiuiSettingActivity;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.train.p076a.C2351d;
import com.beastbikes.android.utils.C2554c;
import com.beastbikes.android.utils.aa;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.google.zxing.client.result.ExpandedProductParsedResult;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.apache.commons.cli.HelpFormatter;

@C1459b(a = 2130903209)
public class TrainTargetSetActivity extends SessionFragmentActivity implements OnClickListener, C1371a {
    /* renamed from: A */
    private SharedPreferences f6581A;
    @C1458a(a = 2131756093)
    /* renamed from: a */
    private ViewGroup f6582a;
    /* renamed from: b */
    private TextView f6583b;
    @C1458a(a = 2131756094)
    /* renamed from: c */
    private ViewGroup f6584c;
    /* renamed from: d */
    private TextView f6585d;
    @C1458a(a = 2131756095)
    /* renamed from: e */
    private ViewGroup f6586e;
    /* renamed from: f */
    private TextView f6587f;
    @C1458a(a = 2131756096)
    /* renamed from: g */
    private ViewGroup f6588g;
    /* renamed from: h */
    private TextView f6589h;
    @C1458a(a = 2131756097)
    /* renamed from: i */
    private ViewGroup f6590i;
    /* renamed from: j */
    private TextView f6591j;
    @C1458a(a = 2131756098)
    /* renamed from: k */
    private ViewGroup f6592k;
    /* renamed from: l */
    private TextView f6593l;
    @C1458a(a = 2131756099)
    /* renamed from: m */
    private TextView f6594m;
    /* renamed from: n */
    private C2351d f6595n;
    /* renamed from: o */
    private boolean f6596o;
    /* renamed from: p */
    private double f6597p;
    /* renamed from: q */
    private double f6598q;
    /* renamed from: r */
    private int f6599r = -1;
    /* renamed from: s */
    private long f6600s;
    /* renamed from: t */
    private int f6601t;
    /* renamed from: u */
    private int f6602u;
    /* renamed from: v */
    private int f6603v;
    /* renamed from: w */
    private int f6604w = 1990;
    /* renamed from: x */
    private int f6605x = 1;
    /* renamed from: y */
    private int f6606y = 1;
    /* renamed from: z */
    private int f6607z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        this.f6595n = new C2351d(this);
        this.f6581A = getSharedPreferences(m5331p(), 0);
        this.f6607z = getIntent().getIntExtra("enter_type", 1);
        if (this.f6607z != 0) {
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
        this.f6596o = C1849a.b(this);
        m7804b();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.f6607z == 0) {
            getMenuInflater().inflate(C1373R.menu.save_menu, menu);
            this.f6594m.setVisibility(8);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case C1373R.id.save_menu_item:
                m7825j();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    public void onBackPressed() {
        if (this.f6607z != 0) {
            super.onBackPressed();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.training_target_set_gender:
                m7810d();
                return;
            case C1373R.id.training_target_set_height:
                m7812e();
                return;
            case C1373R.id.training_target_set_weight:
                m7816f();
                return;
            case C1373R.id.training_target_set_birthday:
                m7819g();
                return;
            case C1373R.id.training_target_set_heart_rate:
                m7821h();
                return;
            case C1373R.id.training_target_set_ftp:
                m7823i();
                return;
            case C1373R.id.train_target_set_save:
                m7825j();
                return;
            default:
                return;
        }
    }

    public void finish() {
        if (this.f6607z == 0) {
            m7834a();
        }
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_right);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i2) {
            case -1:
                switch (i) {
                    case 33:
                        this.f6602u = intent.getIntExtra("MAX_HEART_RATE", 200);
                        this.f6591j.setText(String.valueOf(this.f6602u));
                        return;
                    case 34:
                        this.f6603v = intent.getIntExtra("FTP_VALUE", 200);
                        this.f6593l.setText(this.f6603v + "W");
                        AVUser currentUser = AVUser.getCurrentUser();
                        if (currentUser != null) {
                            currentUser.setFtp(this.f6603v);
                            AVUser.saveCurrentUser(currentUser);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    /* renamed from: b */
    private void m7804b() {
        this.f6582a.setOnClickListener(this);
        ((TextView) this.f6582a.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_gender);
        this.f6583b = (TextView) this.f6582a.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f6584c.setOnClickListener(this);
        ((TextView) this.f6584c.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_height);
        this.f6585d = (TextView) this.f6584c.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f6586e.setOnClickListener(this);
        ((TextView) this.f6586e.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_weight);
        this.f6587f = (TextView) this.f6586e.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f6588g.setOnClickListener(this);
        ((TextView) this.f6588g.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_birthday);
        this.f6589h = (TextView) this.f6588g.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f6590i.setOnClickListener(this);
        ((TextView) this.f6590i.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_max_heart_rate);
        this.f6591j = (TextView) this.f6590i.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f6592k.setOnClickListener(this);
        ((TextView) this.f6592k.findViewById(C1373R.id.speed_force_setting_item_label)).setText(C1373R.string.str_training_target_current_ftp);
        this.f6593l = (TextView) this.f6592k.findViewById(C1373R.id.speed_force_setting_item_value);
        this.f6594m.setOnClickListener(this);
        this.f6583b.setText(C1373R.string.str_required);
        this.f6585d.setText(C1373R.string.str_required);
        this.f6587f.setText(C1373R.string.str_required);
        this.f6589h.setText(C1373R.string.str_required);
        this.f6591j.setText(C1373R.string.str_calculate_by_birthday);
        this.f6593l.setText("200W");
        AVUser currentUser = AVUser.getCurrentUser();
        if (!(currentUser == null || this.f6607z == 0)) {
            CharSequence charSequence;
            this.f6599r = currentUser.getGender();
            if (this.f6599r >= 0) {
                this.f6583b.setText(currentUser.getGender() == 0 ? C1373R.string.str_gender_female : C1373R.string.str_gender_male);
            }
            this.f6597p = currentUser.getHeight();
            if (this.f6597p > 0.0d) {
                TextView textView = this.f6585d;
                if (this.f6596o) {
                    charSequence = ((int) this.f6597p) + "CM";
                } else {
                    charSequence = C1849a.g(this.f6597p) + "'" + C1849a.f(this.f6597p) + "\"";
                }
                textView.setText(charSequence);
            }
            this.f6598q = currentUser.getWeight();
            if (this.f6598q > 0.0d) {
                this.f6587f.setText(this.f6596o ? ((int) this.f6598q) + ExpandedProductParsedResult.KILOGRAM : ((int) C1849a.h(this.f6598q)) + ExpandedProductParsedResult.POUND);
            }
            charSequence = currentUser.getBirthDay();
            if (!TextUtils.isEmpty(charSequence)) {
                String[] split = charSequence.split(HelpFormatter.DEFAULT_OPT_PREFIX);
                if (split.length == 3) {
                    this.f6604w = aa.a(split[0]);
                    this.f6605x = aa.a(split[1]);
                    this.f6606y = aa.a(split[2]);
                    GregorianCalendar gregorianCalendar = new GregorianCalendar();
                    gregorianCalendar.set(this.f6604w, this.f6605x, this.f6606y);
                    this.f6600s = gregorianCalendar.getTimeInMillis() / 1000;
                    this.f6589h.setText(charSequence);
                    this.f6601t = C2554c.a(this.f6604w, this.f6605x, this.f6606y);
                }
            }
            this.f6603v = currentUser.getFtp();
            if (this.f6603v > 0) {
                this.f6593l.setText(this.f6603v + "W");
            }
        }
        if (this.f6607z == 1) {
            m7807c();
        }
    }

    /* renamed from: c */
    private void m7807c() {
        getAsyncTaskQueue().a(new TrainTargetSetActivity$1(this), new Void[0]);
    }

    /* renamed from: d */
    private void m7810d() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(getString(C1373R.string.str_gender_female));
        arrayList.add(getString(C1373R.string.str_gender_male));
        C1805k c1805k = new C1805k(this, new TrainTargetSetActivity$2(this));
        c1805k.show();
        c1805k.a(arrayList);
        int i = this.f6599r;
        if (i < 0) {
            i = 1;
        }
        c1805k.a(i);
    }

    /* renamed from: e */
    private void m7812e() {
        if (this.f6597p == 0.0d) {
            this.f6597p = 175.0d;
        }
        ArrayList arrayList;
        int i;
        if (this.f6596o) {
            String str = "CM";
            arrayList = new ArrayList();
            for (i = 100; i <= 230; i++) {
                arrayList.add(String.valueOf(i));
            }
            C1805k c1805k = new C1805k(this, new TrainTargetSetActivity$3(this));
            c1805k.show();
            c1805k.a(arrayList);
            c1805k.a("CM");
            c1805k.b(String.valueOf((int) this.f6597p));
            return;
        }
        arrayList = new ArrayList();
        for (i = 3; i <= 7; i++) {
            arrayList.add(i + "'");
        }
        ArrayList arrayList2 = new ArrayList();
        for (i = 0; i <= 11; i++) {
            arrayList2.add(i + "\"");
        }
        C1796f c1796f = new C1796f(this, new TrainTargetSetActivity$4(this));
        c1796f.show();
        c1796f.a(arrayList, arrayList2);
        c1796f.a(String.valueOf(C1849a.g(this.f6597p) + "'"), String.valueOf(C1849a.f(this.f6597p) + "\""));
    }

    /* renamed from: f */
    private void m7816f() {
        String valueOf;
        ArrayList arrayList = new ArrayList();
        int i = 30;
        int i2 = 200;
        if (!this.f6596o) {
            i = 50;
            i2 = 500;
        }
        while (i <= i2) {
            arrayList.add(String.valueOf(i));
            i++;
        }
        C1805k c1805k = new C1805k(this, new TrainTargetSetActivity$5(this));
        c1805k.show();
        c1805k.a(arrayList);
        c1805k.a(this.f6596o ? ExpandedProductParsedResult.KILOGRAM : ExpandedProductParsedResult.POUND);
        i2 = (int) this.f6598q;
        if (i2 == 0) {
            i2 = 70;
        }
        if (this.f6596o) {
            valueOf = String.valueOf(i2);
        } else {
            valueOf = String.valueOf((int) C1849a.h(this.f6598q));
        }
        c1805k.b(valueOf);
    }

    /* renamed from: g */
    private void m7819g() {
        C1794e c1794e = new C1794e(this, 0, new TrainTargetSetActivity$6(this));
        c1794e.show();
        c1794e.a(this.f6604w, this.f6605x, this.f6606y);
    }

    /* renamed from: h */
    private void m7821h() {
        Intent intent = new Intent(this, HeartRateSetActivity.class);
        intent.putExtra("AGE", this.f6601t);
        intent.putExtra("MAX_HEART_RATE", this.f6602u);
        startActivityForResult(intent, 33);
    }

    /* renamed from: i */
    private void m7823i() {
        startActivityForResult(new Intent(this, FTPListActivity.class), 34);
    }

    /* renamed from: j */
    private void m7825j() {
        if (this.f6597p != 0.0d && this.f6599r >= 0 && this.f6598q > 0.0d && this.f6602u != 0 && this.f6600s != 0) {
            C2351d c2351d = new C2351d(this);
            getAsyncTaskQueue().a(new TrainTargetSetActivity$7(this, new C1802i(this, C1373R.string.empty, true), c2351d), new Void[0]);
        }
    }

    /* renamed from: a */
    protected void m7834a() {
        String str = Build.BRAND;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            CookieManager.getInstance().setCookie(a$c.f7201b, "sessionid=" + currentUser.getSessionToken());
            currentUser.setAuthenticated(true);
            AVUser.saveCurrentUser(currentUser);
        }
        SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), 0);
        boolean z = sharedPreferences.getBoolean("guide_setting", false);
        if (str.equalsIgnoreCase("Xiaomi") && !z) {
            startActivity(new Intent(this, MiuiSettingActivity.class));
            sharedPreferences.edit().putBoolean("guide_setting", true).apply();
        } else if (!str.equalsIgnoreCase("Meizu") || z) {
            Intent intent = new Intent(this, HomeActivity.class);
            m5329c(18, null);
            startActivity(intent);
        } else {
            startActivity(new Intent(this, MeiZuSettingActivity.class));
            sharedPreferences.edit().putBoolean("guide_setting", true).apply();
        }
    }
}
