package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2049a;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityInfo;
import com.beastbikes.android.modules.cycling.club.ui.widget.C2155f;
import com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.C2168b;
import com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.C2169c;
import com.beastbikes.android.modules.p062c.C1880a;
import com.beastbikes.android.modules.social.im.ui.conversation.LocationSelectActivity;
import com.beastbikes.android.modules.user.ui.binding.CountryPageActivity;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.android.widget.multiimageselector.MultiImageSelectorActivity;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p084c.p085a.C1460c;
import com.beastbikes.framework.android.p088g.C2798a;
import com.beastbikes.framework.ui.android.WebActivity;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

@C1457a(a = "发布活动")
@C1459b(a = 2130903096)
@C1460c(a = 2131820544)
public class ClubActivityReleaseActivity extends SessionFragmentActivity implements OnClickListener, OnTouchListener, C1371a {
    /* renamed from: A */
    private String f4921A;
    /* renamed from: B */
    private String f4922B;
    /* renamed from: C */
    private Animation f4923C;
    /* renamed from: D */
    private Animation f4924D;
    /* renamed from: E */
    private Animation f4925E;
    /* renamed from: F */
    private Animation f4926F;
    /* renamed from: G */
    private C2155f f4927G;
    /* renamed from: H */
    private String f4928H;
    /* renamed from: I */
    private String[] f4929I = new String[]{"http://bazaar.speedx.com/clubActivity/clubActivityCover1@3x.png", "http://bazaar.speedx.com/clubActivity/clubActivityCover2@2x.png", "http://bazaar.speedx.com/clubActivity/clubActivityCover3@3x.png"};
    /* renamed from: J */
    private int[] f4930J = new int[]{C1373R.drawable.activity_club_release_defual01, C1373R.drawable.activity_club_release_defual02, C1373R.drawable.activity_club_release_defual03};
    /* renamed from: K */
    private ClubActivityInfo f4931K;
    /* renamed from: L */
    private double f4932L;
    /* renamed from: M */
    private double f4933M;
    /* renamed from: N */
    private String f4934N = "";
    /* renamed from: O */
    private String f4935O = "";
    /* renamed from: P */
    private C2049a f4936P;
    /* renamed from: Q */
    private String f4937Q;
    /* renamed from: R */
    private C1802i f4938R;
    /* renamed from: S */
    private int f4939S;
    /* renamed from: T */
    private boolean f4940T;
    /* renamed from: U */
    private C1880a f4941U;
    @C1458a(a = 2131755489)
    /* renamed from: a */
    private View f4942a;
    @C1458a(a = 2131755490)
    /* renamed from: b */
    private ImageView f4943b;
    @C1458a(a = 2131755488)
    /* renamed from: c */
    private ScrollView f4944c;
    @C1458a(a = 2131755496)
    /* renamed from: d */
    private View f4945d;
    @C1458a(a = 2131755498)
    /* renamed from: e */
    private View f4946e;
    @C1458a(a = 2131755500)
    /* renamed from: f */
    private View f4947f;
    @C1458a(a = 2131755502)
    /* renamed from: g */
    private View f4948g;
    @C1458a(a = 2131755497)
    /* renamed from: h */
    private TextView f4949h;
    @C1458a(a = 2131755492)
    /* renamed from: i */
    private View f4950i;
    @C1458a(a = 2131755513)
    /* renamed from: j */
    private View f4951j;
    @C1458a(a = 2131755514)
    /* renamed from: k */
    private View f4952k;
    @C1458a(a = 2131755503)
    /* renamed from: l */
    private TextView f4953l;
    @C1458a(a = 2131756261)
    /* renamed from: m */
    private TextView f4954m;
    @C1458a(a = 2131755512)
    /* renamed from: n */
    private View f4955n;
    @C1458a(a = 2131755511)
    /* renamed from: o */
    private View f4956o;
    @C1458a(a = 2131756341)
    /* renamed from: p */
    private View f4957p;
    @C1458a(a = 2131755499)
    /* renamed from: q */
    private TextView f4958q;
    @C1458a(a = 2131755501)
    /* renamed from: r */
    private TextView f4959r;
    @C1458a(a = 2131756263)
    /* renamed from: s */
    private TextView f4960s;
    @C1458a(a = 2131755516)
    /* renamed from: t */
    private TextView f4961t;
    @C1458a(a = 2131755495)
    /* renamed from: u */
    private EditText f4962u;
    @C1458a(a = 2131755508)
    /* renamed from: v */
    private EditText f4963v;
    @C1458a(a = 2131755510)
    /* renamed from: w */
    private EditText f4964w;
    @C1458a(a = 2131756260)
    /* renamed from: x */
    private Switch f4965x;
    @C1458a(a = 2131755506)
    /* renamed from: y */
    private TextView f4966y;
    /* renamed from: z */
    private SharedPreferences f4967z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f4967z = getSharedPreferences("release_info", 0);
        this.f4936P = new C2049a(this);
        this.f4941U = new C1880a(this);
        this.f4927G = new C2155f(this, this.f4958q, this.f4959r, this.f4960s);
        m6289a();
        this.f4942a.setOnClickListener(this);
        this.f4945d.setOnClickListener(this);
        this.f4946e.setOnClickListener(this);
        this.f4947f.setOnClickListener(this);
        this.f4948g.setOnClickListener(this);
        this.f4952k.setOnClickListener(this);
        this.f4951j.setOnClickListener(this);
        if (C1849a.a()) {
            this.f4951j.setVisibility(0);
        } else {
            this.f4951j.setVisibility(8);
        }
        this.f4956o.setOnClickListener(this);
        this.f4961t.setOnClickListener(this);
        this.f4966y.setOnClickListener(this);
        this.f4966y.setText(Marker.ANY_NON_NULL_MARKER + C1849a.a(this));
        m6311h();
        Intent intent = getIntent();
        if (intent.hasExtra("club_activity_manage_tag")) {
            this.f4931K = (ClubActivityInfo) intent.getSerializableExtra("ACT_INFO");
            m6290a(this.f4931K);
            if (intent.getIntExtra("club_activity_manage_tag", -1) == 2) {
                this.f4965x.setEnabled(false);
            } else if (intent.getIntExtra("club_activity_manage_tag", -1) == 1) {
                m6298b();
            }
        } else {
            try {
                m6290a(new ClubActivityInfo(new JSONObject(this.f4967z.getString("release_info", ""))));
            } catch (JSONException e) {
                m6290a(new ClubActivityInfo());
            }
        }
        this.f4944c.setOnTouchListener(this);
    }

    /* renamed from: a */
    private void m6290a(ClubActivityInfo clubActivityInfo) {
        boolean z = true;
        this.f4927G.a(clubActivityInfo);
        this.f4935O = clubActivityInfo.getRouteName();
        this.f4934N = clubActivityInfo.getRouteId();
        m6309f();
        if (clubActivityInfo.getMaxMembers() != 0) {
            this.f4964w.setText(clubActivityInfo.getMaxMembers() + "");
        }
        Object mobilephone = clubActivityInfo.getMobilephone();
        if (TextUtils.isEmpty(mobilephone)) {
            this.f4963v.setText("");
        } else {
            try {
                PhoneNumber parse = PhoneNumberUtil.getInstance().parse(mobilephone, "CN");
                int countryCode = parse.getCountryCode();
                this.f4963v.setText(String.valueOf(parse.getNationalNumber()));
                this.f4966y.setText(Marker.ANY_NON_NULL_MARKER + countryCode);
            } catch (NumberParseException e) {
                e.printStackTrace();
            }
        }
        if (TextUtils.isEmpty(clubActivityInfo.getTitle())) {
            this.f4962u.setText("");
        } else {
            this.f4962u.setText(clubActivityInfo.getTitle());
        }
        m6299b(clubActivityInfo.getDecstiption());
        m6301c(clubActivityInfo.getMobPlace());
        this.f4932L = clubActivityInfo.getMobPoint()[0];
        this.f4933M = clubActivityInfo.getMobPoint()[1];
        if (getIntent().getIntExtra("club_activity_manage_tag", -1) == 2) {
            Switch switchR = this.f4965x;
            if (clubActivityInfo.isClubPrivate()) {
                z = false;
            }
            switchR.setChecked(z);
        }
        if (TextUtils.isEmpty(clubActivityInfo.getCover()) || clubActivityInfo.getCover().equals("null")) {
            this.f4943b.setImageResource(this.f4930J[m6306e()]);
            this.f4921A = "";
            return;
        }
        m6293a(clubActivityInfo.getCover());
        if (new File(clubActivityInfo.getCover()).exists()) {
            this.f4921A = clubActivityInfo.getCover();
        } else {
            this.f4928H = clubActivityInfo.getCover();
        }
    }

    /* renamed from: a */
    private void m6289a() {
        this.f4923C = AnimationUtils.loadAnimation(this, C1373R.anim.club_release_down);
        this.f4923C.setFillAfter(true);
        this.f4925E = AnimationUtils.loadAnimation(this, C1373R.anim.club_release_commit_down);
        this.f4925E.setFillAfter(true);
        this.f4924D = AnimationUtils.loadAnimation(this, C1373R.anim.club_release_up);
        this.f4926F = AnimationUtils.loadAnimation(this, C1373R.anim.club_release_commit_up);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == C1373R.id.activity_club_release_menu) {
            m6290a(new ClubActivityInfo());
        } else if (menuItem.getItemId() == 16908332) {
            m6312i();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* renamed from: a */
    private void m6293a(String str) {
        if (new File(str).exists()) {
            str = "file://" + str;
        }
        Picasso.with(this).load(str).fit().placeholder(this.f4930J[0]).error(this.f4930J[0]).centerCrop().into(this.f4943b);
        this.f4950i.setVisibility(8);
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.club_act_map_img:
                intent = new Intent(this, MultiImageSelectorActivity.class);
                intent.putExtra("gallery_full", true);
                intent.putExtra("max_select_count", 1);
                intent.putExtra("is_max", false);
                intent.putStringArrayListExtra("default_list", new ArrayList());
                startActivityForResult(intent, 291);
                return;
            case C1373R.id.club_act_info:
                intent = new Intent(this, ClubActInfoEditorActivity.class);
                if (!TextUtils.isEmpty(this.f4922B)) {
                    intent.putExtra(ClubActInfoEditorActivity.f4810a, this.f4922B);
                }
                startActivityForResult(intent, 292);
                return;
            case C1373R.id.club_act_starttime:
                this.f4927G.a();
                return;
            case C1373R.id.club_act_endtime:
                this.f4927G.b();
                return;
            case C1373R.id.club_act_info_place:
                startActivityForResult(new Intent(this, LocationSelectActivity.class), 293);
                return;
            case C1373R.id.club_create_zone_tv:
                startActivityForResult(new Intent(this, CountryPageActivity.class), 4660);
                return;
            case C1373R.id.club_release_act_more_setting_group:
                if (this.f4955n.getVisibility() == 8) {
                    this.f4957p.startAnimation(this.f4923C);
                    this.f4955n.setVisibility(0);
                    m6310g();
                    return;
                }
                this.f4957p.startAnimation(this.f4924D);
                this.f4955n.setVisibility(8);
                return;
            case C1373R.id.club_act_add_route:
                startActivityForResult(new Intent(this, ClubActRouteSelfActivity.class), 294);
                return;
            case C1373R.id.club_act_deadline:
                this.f4927G.c();
                return;
            case C1373R.id.club_act_commit:
                m6305d();
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    private void m6298b() {
        this.f4940T = true;
        this.f4958q.setTextColor(getResources().getColor(C1373R.color.design_color_c7));
        this.f4959r.setTextColor(getResources().getColor(C1373R.color.design_color_c7));
    }

    /* renamed from: c */
    private boolean m6302c() {
        if (!this.f4940T || this.f4931K == null) {
            return false;
        }
        long b = C2555d.b(this.f4931K.getStartDate());
        long b2 = C2555d.b(this.f4931K.getEndDate());
        Object h = C2555d.h(b);
        Object h2 = C2555d.h(b2);
        CharSequence charSequence = this.f4958q.getText().toString();
        CharSequence charSequence2 = this.f4959r.getText().toString();
        if (TextUtils.isEmpty(h) || TextUtils.isEmpty(h2) || TextUtils.isEmpty(charSequence) || TextUtils.isEmpty(charSequence2) || !h.equals(charSequence) || !h2.equals(charSequence2)) {
            return false;
        }
        return true;
    }

    /* renamed from: d */
    private void m6305d() {
        if (m6302c()) {
            Toasts.showOnUiThread(this, getResources().getString(C1373R.string.change_time_repost_activity));
            this.f4958q.setTextColor(getResources().getColor(C1373R.color.design_color_c7));
            this.f4959r.setTextColor(getResources().getColor(C1373R.color.design_color_c7));
        } else if (TextUtils.isEmpty(this.f4962u.getText())) {
            Toasts.showOnUiThread(this, getString(C1373R.string.club_act_theme_error_str));
        } else if (TextUtils.isEmpty(this.f4922B)) {
            Toasts.showOnUiThread(this, getString(C1373R.string.club_act_html_error_str));
        } else if (this.f4958q.getCurrentTextColor() == getResources().getColor(C1373R.color.design_color_c7)) {
            Toasts.showOnUiThread(this, getString(C1373R.string.club_act_starttime_error_str));
        } else if (this.f4959r.getCurrentTextColor() == getResources().getColor(C1373R.color.design_color_c7)) {
            Toasts.showOnUiThread(this, getString(C1373R.string.club_act_endtime_error_str));
        } else if (this.f4953l.getText().toString().equals(getString(C1373R.string.club_act_info_place_str)) || this.f4953l.getCurrentTextColor() == getResources().getColor(C1373R.color.design_color_c7)) {
            Toasts.showOnUiThread(this, getString(C1373R.string.club_act_route_error_str));
        } else if (TextUtils.isEmpty(this.f4963v.getText().toString())) {
            Toasts.showOnUiThread(this, getString(C1373R.string.str_phone_error));
        } else if (this.f4964w.getText().toString().equals("0")) {
            Toasts.showOnUiThread(this, getString(C1373R.string.club_act_userNumber_error_str));
        } else {
            int i;
            String obj = this.f4962u.getText().toString();
            String charSequence = this.f4953l.getText().toString();
            String str = this.f4932L + "," + this.f4933M;
            String d = m6304d(this.f4958q.getText().toString());
            String d2 = m6304d(this.f4959r.getText().toString());
            String str2 = this.f4966y.getText().toString() + this.f4963v.getText().toString();
            this.f4937Q = "";
            if (!this.f4960s.getText().toString().equals(getString(C1373R.string.club_act_time_str))) {
                this.f4937Q = m6304d(this.f4960s.getText().toString());
            }
            if (TextUtils.isEmpty(this.f4964w.getText())) {
                this.f4939S = 50;
            } else {
                this.f4939S = Integer.valueOf(this.f4964w.getText().toString()).intValue();
            }
            if (this.f4965x.isChecked()) {
                i = 0;
            } else {
                i = 1;
            }
            if (this.f4935O == null) {
                this.f4935O = "";
            }
            if (this.f4934N == null) {
                this.f4934N = "";
            }
            this.f4961t.setClickable(false);
            this.f4938R = new C1802i(this, "", true);
            this.f4938R.show();
            if (TextUtils.isEmpty(this.f4921A)) {
                m6295a(obj, charSequence, str, d, d2, this.f4934N, this.f4935O, str2, this.f4937Q, this.f4939S, i, this.f4928H);
            } else {
                m6294a(obj, charSequence, str, d, d2, this.f4934N, this.f4935O, str2, this.f4937Q, this.f4939S, i);
            }
        }
    }

    /* renamed from: a */
    private void m6295a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, int i2, String str10) {
        new C2168b(this.f4922B, getAsyncTaskQueue()).a(new ClubActivityReleaseActivity$1(this, str, str2, str3, str4, str5, str6, str7, str8, str9, i, i2, str10));
    }

    /* renamed from: e */
    private int m6306e() {
        int random = (int) (Math.random() * 3.0d);
        this.f4928H = this.f4929I[random];
        return random;
    }

    /* renamed from: a */
    private void m6294a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, int i, int i2) {
        String str10 = this.f4941U.f() + C2798a.a(UUID.randomUUID().toString());
        this.f4941U.a(new ClubActivityReleaseActivity$2(this, str, str2, str3, str4, str5, str6, str7, str8, str9, i, i2));
        this.f4941U.a(str10, this.f4921A, str10);
    }

    /* renamed from: a */
    private void m6296a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, int i2, String str11, String str12) {
        getAsyncTaskQueue().a(new ClubActivityReleaseActivity$3(this, str, str2, str3, str4, str5, str6, str7, str8, str9, str10, i, i2, str11, str12), new Object[0]);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        Object stringExtra;
        switch (i2) {
            case -1:
                switch (i) {
                    case 291:
                        ArrayList stringArrayListExtra = intent.getStringArrayListExtra("select_result");
                        if (!stringArrayListExtra.isEmpty()) {
                            String str = (String) stringArrayListExtra.get(0);
                            Intent intent2 = new Intent(this, ClubCutBitmapActivity.class);
                            intent2.putExtra("path", str);
                            startActivityForResult(intent2, 295);
                            break;
                        }
                        break;
                    case 292:
                        m6299b(intent.getStringExtra(ClubActInfoEditorActivity.f4811b));
                        break;
                    case 293:
                        this.f4932L = intent.getDoubleExtra("extra_lat", 0.0d);
                        this.f4933M = intent.getDoubleExtra("extra_lng", 0.0d);
                        m6301c(intent.getStringExtra("extra_addr"));
                        break;
                    case 294:
                        this.f4934N = intent.getStringExtra("route_id");
                        this.f4935O = intent.getStringExtra("route_name");
                        m6309f();
                        break;
                    case 4660:
                        if (intent != null) {
                            stringExtra = intent.getStringExtra("code");
                            if (!TextUtils.isEmpty(stringExtra)) {
                                this.f4966y.setText(Marker.ANY_NON_NULL_MARKER + stringExtra);
                                break;
                            }
                        }
                        break;
                    default:
                        break;
                }
            case 4:
                if (i == 295) {
                    stringExtra = intent.getStringExtra("path");
                    if (!TextUtils.isEmpty(stringExtra)) {
                        this.f4921A = stringExtra;
                        m6293a(this.f4921A);
                        break;
                    }
                }
                break;
        }
        super.onActivityResult(i, i2, intent);
    }

    /* renamed from: b */
    private void m6299b(String str) {
        if (TextUtils.isEmpty(str) || str.equals("null")) {
            this.f4949h.setTextColor(getResources().getColor(C1373R.color.design_color_c7));
            this.f4949h.setText(getString(C1373R.string.club_act_info));
        } else {
            this.f4949h.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.f4949h.setText(C2169c.a(str));
        }
        this.f4922B = str;
    }

    /* renamed from: c */
    private void m6301c(String str) {
        if (!TextUtils.isEmpty(str) || this.f4953l.getText().toString().equals("null")) {
            this.f4953l.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.f4953l.setText(str);
            return;
        }
        this.f4953l.setTextColor(getResources().getColor(C1373R.color.design_color_c7));
        this.f4953l.setText(getString(C1373R.string.club_act_info_place_str));
    }

    /* renamed from: f */
    private void m6309f() {
        if (!TextUtils.isEmpty(this.f4935O) || this.f4954m.getText().toString().equals("null")) {
            this.f4954m.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.f4954m.setText(this.f4935O);
            return;
        }
        this.f4954m.setTextColor(getResources().getColor(C1373R.color.design_color_c7));
        this.f4954m.setText("");
    }

    /* renamed from: g */
    private void m6310g() {
        this.f4944c.post(new ClubActivityReleaseActivity$4(this));
    }

    /* renamed from: h */
    private void m6311h() {
        this.f4944c.post(new ClubActivityReleaseActivity$5(this));
    }

    /* renamed from: d */
    private String m6304d(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        long j = 0;
        try {
            j = simpleDateFormat.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return C2555d.a(j);
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 2 && this.f4961t.getVisibility() == 0) {
            this.f4961t.startAnimation(this.f4925E);
            this.f4961t.setVisibility(4);
        } else if (2 != motionEvent.getAction() && this.f4961t.getVisibility() == 4) {
            this.f4961t.startAnimation(this.f4926F);
            this.f4961t.setVisibility(0);
        }
        return super.onTouchEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            m6312i();
        }
        return false;
    }

    /* renamed from: i */
    private void m6312i() {
        boolean z = true;
        if (!getIntent().hasExtra("club_activity_manage_tag")) {
            String obj = this.f4962u.getText().toString();
            String charSequence = this.f4953l.getText().toString();
            String d = m6304d(this.f4958q.getText().toString());
            String d2 = m6304d(this.f4959r.getText().toString());
            String obj2 = this.f4963v.getText().toString();
            this.f4937Q = "";
            if (!this.f4960s.getText().toString().equals(getString(C1373R.string.club_act_time_str))) {
                this.f4937Q = m6304d(this.f4960s.getText().toString());
            }
            if (!TextUtils.isEmpty(this.f4964w.getText())) {
                this.f4939S = Integer.valueOf(this.f4964w.getText().toString()).intValue();
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(WebActivity.EXTRA_TITLE, obj);
                jSONObject.put("decstiption", this.f4922B);
                jSONObject.put("cover", this.f4921A);
                jSONObject.put("endDate", d2);
                jSONObject.put("applyEndDate", this.f4937Q);
                jSONObject.put("startDate", d);
                jSONObject.put("maxMembers", this.f4939S);
                obj = "isClubPrivate";
                if (this.f4965x.isChecked()) {
                    z = false;
                }
                jSONObject.put(obj, z);
                jSONObject.put("mobPlace", charSequence);
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(0, Double.valueOf(this.f4932L));
                jSONArray.put(1, Double.valueOf(this.f4933M));
                jSONObject.put("mobPoint", jSONArray);
                jSONObject.put("routeId", this.f4934N);
                jSONObject.put("routeName", this.f4935O);
                jSONObject.put("mobilephone", obj2);
                Editor edit = this.f4967z.edit();
                edit.putString("release_info", jSONObject.toString());
                edit.commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        finish();
    }
}
