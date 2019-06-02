package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.p062c.C1880a;
import com.beastbikes.android.modules.social.im.ui.conversation.LocationSelectActivity;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.ui.binding.CountryPageActivity;
import com.beastbikes.android.utils.C2560h;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.utils.aa;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.SwitchView;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2798a;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

@C1457a(a = "创建俱乐部")
@C1459b(a = 2130903252)
public class ClubCreateActivity extends SessionFragmentActivity implements OnClickListener {
    /* renamed from: C */
    private static int f4968C = 100;
    /* renamed from: D */
    private static int f4969D = 101;
    /* renamed from: b */
    private static final Logger f4970b = LoggerFactory.getLogger(ClubCreateActivity.class);
    /* renamed from: A */
    private String f4971A;
    /* renamed from: B */
    private String f4972B;
    /* renamed from: E */
    private ClubManager f4973E;
    /* renamed from: F */
    private C2389c f4974F;
    /* renamed from: G */
    private C2560h f4975G = null;
    /* renamed from: H */
    private int f4976H = 0;
    /* renamed from: I */
    private String f4977I;
    /* renamed from: J */
    private int f4978J = 0;
    /* renamed from: K */
    private String f4979K;
    /* renamed from: L */
    private String f4980L;
    /* renamed from: M */
    private CountDownTimer f4981M;
    /* renamed from: N */
    private int f4982N = 0;
    /* renamed from: O */
    private double f4983O;
    /* renamed from: P */
    private double f4984P;
    /* renamed from: a */
    C1802i f4985a;
    @C1458a(a = 2131756271)
    /* renamed from: c */
    private FrameLayout f4986c;
    @C1458a(a = 2131756270)
    /* renamed from: d */
    private ViewGroup f4987d;
    @C1458a(a = 2131756273)
    /* renamed from: e */
    private CircleImageView f4988e;
    @C1458a(a = 2131756275)
    /* renamed from: f */
    private EditText f4989f;
    @C1458a(a = 2131756274)
    /* renamed from: g */
    private TextView f4990g;
    @C1458a(a = 2131756276)
    /* renamed from: h */
    private FrameLayout f4991h;
    @C1458a(a = 2131756277)
    /* renamed from: i */
    private TextView f4992i;
    @C1458a(a = 2131756278)
    /* renamed from: j */
    private TextView f4993j;
    @C1458a(a = 2131756281)
    /* renamed from: k */
    private EditText f4994k;
    @C1458a(a = 2131756280)
    /* renamed from: l */
    private TextView f4995l;
    @C1458a(a = 2131756283)
    /* renamed from: m */
    private EditText f4996m;
    @C1458a(a = 2131756282)
    /* renamed from: n */
    private TextView f4997n;
    @C1458a(a = 2131756285)
    /* renamed from: o */
    private EditText f4998o;
    @C1458a(a = 2131756284)
    /* renamed from: p */
    private TextView f4999p;
    @C1458a(a = 2131756286)
    /* renamed from: q */
    private TextView f5000q;
    @C1458a(a = 2131756287)
    /* renamed from: r */
    private EditText f5001r;
    @C1458a(a = 2131756288)
    /* renamed from: s */
    private Button f5002s;
    @C1458a(a = 2131756290)
    /* renamed from: t */
    private EditText f5003t;
    @C1458a(a = 2131756294)
    /* renamed from: u */
    private Button f5004u;
    @C1458a(a = 2131755472)
    /* renamed from: v */
    private RelativeLayout f5005v;
    @C1458a(a = 2131756293)
    /* renamed from: w */
    private TextView f5006w;
    @C1458a(a = 2131755473)
    /* renamed from: x */
    private SwitchView f5007x;
    @C1458a(a = 2131755506)
    /* renamed from: y */
    private TextView f5008y;
    /* renamed from: z */
    private String f5009z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        if (this.f4982N == 0 && bundle != null) {
            this.f4982N = bundle.getInt("COUNTTIME", 0);
            m6317a(this.f4982N);
        }
        C2580w.a(this, "进入创建俱乐部页面", null);
        this.f4973E = new ClubManager(this);
        this.f4974F = new C2389c(this);
        this.f4989f.requestFocus();
        this.f4986c.setOnClickListener(this);
        this.f4991h.setOnClickListener(this);
        this.f5004u.setOnClickListener(this);
        this.f5005v.setOnClickListener(this);
        this.f5002s.setOnClickListener(this);
        this.f5007x.setChecked(false);
        this.f5008y.setOnClickListener(this);
        this.f5008y.setText(Marker.ANY_NON_NULL_MARKER + C1849a.a(this));
        this.f5007x.setOnClickSwitchListener(new ClubCreateActivity$1(this));
        m6333e();
        m6327c();
        m6331d();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        if (this.f4981M != null) {
            this.f4981M.cancel();
            this.f4981M = null;
        }
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332 || this.f4976H != 0) {
            return super.onOptionsItemSelected(menuItem);
        }
        if (TextUtils.isEmpty(this.f5009z) && TextUtils.isEmpty(this.f4989f.getText().toString()) && TextUtils.isEmpty(this.f4994k.getText().toString()) && TextUtils.isEmpty(this.f4996m.getText().toString()) && TextUtils.isEmpty(this.f4998o.getText().toString()) && TextUtils.isEmpty(this.f5001r.getText().toString()) && TextUtils.isEmpty(this.f5003t.getText().toString())) {
            finish();
            return true;
        }
        C2621c c2621c = new C2621c(this);
        c2621c.b(C1373R.string.club_create_back_warming);
        c2621c.a(C1373R.string.str_ok, new ClubCreateActivity$3(this, c2621c));
        c2621c.b(C1373R.string.cancel, new ClubCreateActivity$4(this, c2621c));
        c2621c.a();
        return true;
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Object stringExtra;
        if (i == 4660) {
            if (intent != null) {
                stringExtra = intent.getStringExtra("code");
                if (!TextUtils.isEmpty(stringExtra)) {
                    this.f5008y.setText(Marker.ANY_NON_NULL_MARKER + stringExtra);
                }
            }
        } else if (i == 100 && i2 == -1 && intent != null) {
            intent.getStringExtra("extra_addr");
            stringExtra = intent.getStringExtra("province_name");
            String stringExtra2 = intent.getStringExtra("area_name");
            this.f4979K = intent.getStringExtra("city_name");
            if (TextUtils.isEmpty(this.f4979K)) {
                this.f4979K = stringExtra2;
            }
            if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals(this.f4979K)) {
                this.f4979K = stringExtra2;
            }
            if (!TextUtils.isEmpty(this.f4979K)) {
                stringExtra = stringExtra + " " + this.f4979K;
            }
            this.f4993j.setText(stringExtra);
            if (!TextUtils.isEmpty(stringExtra)) {
                this.f4980L = stringExtra;
            }
            this.f4983O = intent.getDoubleExtra("extra_lat", 0.0d);
            this.f4984P = intent.getDoubleExtra("extra_lng", 0.0d);
        }
        if (this.f4975G != null) {
            this.f4975G.a(i, i2, intent);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.openprivateclubrl:
                if (this.f5006w.getVisibility() == 0) {
                    this.f5006w.setVisibility(4);
                    return;
                } else {
                    this.f5006w.setVisibility(0);
                    return;
                }
            case C1373R.id.club_create_zone_tv:
                startActivityForResult(new Intent(this, CountryPageActivity.class), 4660);
                return;
            case C1373R.id.club_create_head_lay:
                this.f4975G = new C2560h(this, new ClubCreateActivity$5(this), true);
                this.f4975G.a();
                return;
            case C1373R.id.club_create_city_lay:
                startActivityForResult(new Intent(this, LocationSelectActivity.class), 100);
                return;
            case C1373R.id.club_create_verificationcode_btn:
                if (TextUtils.isEmpty(this.f4998o.getText())) {
                    this.f4999p.setTextColor(SupportMenu.CATEGORY_MASK);
                    return;
                }
                this.f5002s.setClickable(false);
                this.f5002s.setBackgroundResource(C1373R.drawable.gray);
                m6322b();
                return;
            case C1373R.id.club_create_create_btn:
                m6316a();
                return;
            default:
                return;
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("COUNTTIME", this.f4982N);
        super.onSaveInstanceState(bundle);
    }

    /* renamed from: a */
    private void m6316a() {
        if (m6335f()) {
            C2580w.a(this, "提交资料创建俱乐部", null);
            this.f4985a = new C1802i(this, getString(C1373R.string.club_create_create_waiting), false);
            this.f4985a.show();
            String obj = this.f4989f.getText().toString();
            String obj2 = this.f4994k.getText().toString();
            String str = this.f5008y.getText().toString() + this.f4998o.getText().toString();
            String obj3 = this.f4996m.getText().toString();
            String obj4 = this.f5003t.getText().toString();
            String obj5 = this.f5001r.getText().toString();
            if (TextUtils.isEmpty(this.f5009z)) {
                m6319a(null, obj, this.f4980L, this.f4979K, obj2, obj3, str, obj4, obj5, this.f4978J, this.f4983O, this.f4984P);
                return;
            }
            String a = C2798a.a(UUID.randomUUID().toString());
            C1880a c1880a = new C1880a(this);
            String str2 = c1880a.c() + a;
            c1880a.a(new ClubCreateActivity$6(this, a, obj, obj2, obj3, str, obj4, obj5));
            c1880a.a(str2, this.f5009z, str2);
        }
    }

    /* renamed from: a */
    private void m6319a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, double d, double d2) {
        getAsyncTaskQueue().a(new ClubCreateActivity$7(this, str, str2, str3, str4, str5, str6, str7, str8, str9, i, d, d2), new String[0]);
    }

    /* renamed from: b */
    private void m6322b() {
        getAsyncTaskQueue().a(new ClubCreateActivity$8(this), new String[]{this.f5008y.getText().toString() + this.f4998o.getText().toString()});
    }

    /* renamed from: c */
    private void m6327c() {
        getAsyncTaskQueue().a(new ClubCreateActivity$9(this), new String[0]);
    }

    /* renamed from: d */
    private void m6331d() {
        String p = m5331p();
        getAsyncTaskQueue().a(new ClubCreateActivity$10(this), new String[]{p});
    }

    /* renamed from: e */
    private void m6333e() {
        try {
            if (!(TextUtils.isEmpty(this.f4971A) || this.f4971A.equals("null"))) {
                this.f4993j.setText(this.f4971A);
            }
            f4970b.trace(ANSIConstants.ESC_END, "clubCity=" + this.f4971A);
            switch (this.f4976H) {
                case 0:
                    this.f4987d.setVisibility(0);
                    this.f5004u.setBackgroundColor(-1882040);
                    this.f5004u.setText(C1373R.string.club_btn_create);
                    return;
                default:
                    return;
            }
        } catch (Exception e) {
            f4970b.error("刷新view错误， " + e);
        }
    }

    /* renamed from: f */
    private boolean m6335f() {
        if (TextUtils.isEmpty(this.f4989f.getText())) {
            this.f4990g.setTextColor(SupportMenu.CATEGORY_MASK);
            return false;
        }
        this.f4990g.setTextColor(-870178270);
        if (TextUtils.isEmpty(this.f4979K)) {
            this.f4992i.setTextColor(SupportMenu.CATEGORY_MASK);
            return false;
        }
        this.f4992i.setTextColor(-870178270);
        if (TextUtils.isEmpty(this.f4994k.getText())) {
            this.f4995l.setTextColor(SupportMenu.CATEGORY_MASK);
            return false;
        }
        this.f4995l.setTextColor(-870178270);
        if (TextUtils.isEmpty(this.f4996m.getText())) {
            this.f4997n.setTextColor(SupportMenu.CATEGORY_MASK);
            return false;
        }
        this.f4997n.setTextColor(-870178270);
        if (TextUtils.isEmpty(this.f4998o.getText())) {
            this.f4999p.setTextColor(SupportMenu.CATEGORY_MASK);
            return false;
        }
        this.f4999p.setTextColor(-870178270);
        if (TextUtils.isEmpty(this.f5001r.getText())) {
            this.f5000q.setTextColor(SupportMenu.CATEGORY_MASK);
            return false;
        }
        this.f5000q.setTextColor(-870178270);
        return true;
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

    /* renamed from: a */
    private void m6317a(int i) {
        this.f4981M = new ClubCreateActivity$2(this, (long) (i * 1000), 1000);
        this.f4981M.start();
    }
}
