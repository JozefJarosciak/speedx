package com.beastbikes.android.modules.cycling.activity.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.biz.ActivityService;
import com.beastbikes.android.modules.cycling.activity.biz.ActivityService.C1909b;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.activity.dto.PreviewDto;
import com.beastbikes.android.modules.cycling.activity.util.C1921b;
import com.beastbikes.android.modules.preferences.ui.CyclingSettingActivity;
import com.beastbikes.android.p054a.C1529b;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.NumberTextView;
import com.beastbikes.android.widget.convenientbanner.ConvenientBanner;
import com.beastbikes.android.widget.convenientbanner.ConvenientBanner.PageIndicatorAlign;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2801d;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903116)
public class CyclingActivityCopy extends SessionFragmentActivity implements OnClickListener, C1371a {
    /* renamed from: c */
    private static final Logger f4564c = LoggerFactory.getLogger(CyclingActivityCopy.class);
    /* renamed from: A */
    private int f4565A;
    /* renamed from: B */
    private boolean f4566B = true;
    /* renamed from: C */
    private C1921b f4567C;
    /* renamed from: D */
    private final ServiceConnection f4568D = new CyclingActivityCopy$8(this);
    /* renamed from: E */
    private final C1909b f4569E = new CyclingActivityCopy$9(this);
    /* renamed from: a */
    Handler f4570a = new Handler();
    /* renamed from: b */
    Runnable f4571b = new CyclingActivityCopy$5(this);
    @C1458a(a = 2131755662)
    /* renamed from: d */
    private ConvenientBanner f4572d;
    @C1458a(a = 2131755658)
    /* renamed from: e */
    private TextView f4573e;
    @C1458a(a = 2131755659)
    /* renamed from: f */
    private NumberTextView f4574f;
    @C1458a(a = 2131755660)
    /* renamed from: g */
    private TextView f4575g;
    @C1458a(a = 2131755661)
    /* renamed from: h */
    private NumberTextView f4576h;
    @C1458a(a = 2131755663)
    /* renamed from: i */
    private TextView f4577i;
    @C1458a(a = 2131755664)
    /* renamed from: j */
    private TextView f4578j;
    @C1458a(a = 2131755665)
    /* renamed from: k */
    private TextView f4579k;
    @C1458a(a = 2131755657)
    /* renamed from: l */
    private ViewGroup f4580l;
    @C1458a(a = 2131755617)
    /* renamed from: m */
    private ImageView f4581m;
    @C1458a(a = 2131755616)
    /* renamed from: n */
    private TextView f4582n;
    /* renamed from: o */
    private final BroadcastReceiver f4583o = new CyclingActivityCopy$a(this);
    /* renamed from: p */
    private C1398a f4584p;
    /* renamed from: q */
    private AlphaAnimation f4585q;
    /* renamed from: r */
    private boolean f4586r = false;
    /* renamed from: s */
    private List<PreviewDto> f4587s = new ArrayList();
    /* renamed from: t */
    private LocalActivity f4588t;
    /* renamed from: u */
    private SharedPreferences f4589u;
    @C1458a(a = 2131755619)
    /* renamed from: v */
    private View f4590v;
    @C1458a(a = 2131755620)
    /* renamed from: w */
    private View f4591w;
    @C1458a(a = 2131755621)
    /* renamed from: x */
    private View f4592x;
    @C1458a(a = 2131755622)
    /* renamed from: y */
    private TextView f4593y;
    /* renamed from: z */
    private List<View> f4594z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_bottom, C1373R.anim.activity_none);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        LocalActivity a;
        this.f4584p = new C1398a((Activity) this);
        LocalActivity localActivity = (LocalActivity) intent.getSerializableExtra("local_activity");
        if (localActivity == null) {
            a = this.f4584p.m5861a();
        } else {
            a = localActivity;
        }
        m5968b();
        this.f4589u = getSharedPreferences(m5331p(), 0);
        this.f4585q = new AlphaAnimation(0.3f, 1.0f);
        this.f4585q.setRepeatCount(-1);
        this.f4585q.setDuration(800);
        this.f4585q.setRepeatMode(2);
        this.f4586r = C1849a.b(this);
        m5971c();
        this.f4594z = new ArrayList();
        this.f4594z.add(this.f4590v);
        this.f4594z.add(this.f4591w);
        this.f4594z.add(this.f4592x);
        this.f4589u.edit().putBoolean("beast.cycling.state.check", false).apply();
        BeastBikes beastBikes = (BeastBikes) getApplication();
        if (a == null) {
            m5963a(0);
            this.f4578j.clearAnimation();
        } else {
            m5963a(a.getState());
            if (a.getState() == 2 || a.getState() == 3) {
                this.f4578j.startAnimation(this.f4585q);
            }
            if (beastBikes.m5259g()) {
                getWindow().addFlags(128);
            } else {
                getWindow().clearFlags(128);
            }
            this.f4588t = a;
            m5964a(this.f4588t);
        }
        intent = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
        intent.setPackage(getPackageName());
        startService(intent);
        bindService(intent, this.f4568D, 1);
    }

    public void onResume() {
        super.onResume();
        try {
            m5962a();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        m5973d();
        if (this.f4567C != null) {
            this.f4567C.c();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.f4567C != null) {
            this.f4567C.d();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        unbindService(this.f4568D);
        unregisterReceiver(this.f4583o);
        this.f4589u.edit().putBoolean("beast.cycling.state.check", true).apply();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_bottom);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.cycling_activity_hide_view:
                finish();
                return;
            case C1373R.id.cycling_activity_setting_view:
                startActivity(new Intent(this, CyclingSettingActivity.class));
                return;
            case C1373R.id.cycling_data_setting_view:
                C2580w.a(this, "设置数据", null);
                Intent intent = new Intent(this, CyclingSettingPageActivity.class);
                intent.putExtra("position", this.f4572d.getCurrentItem());
                startActivity(intent);
                return;
            case C1373R.id.cycling_fragment_cycling_finish:
                LocalActivity a = this.f4584p.m5861a();
                if (a == null) {
                    m5963a(0);
                    m5976f();
                    return;
                } else if (a.getTotalDistance() <= 10.0d) {
                    r0 = new C2621c(this);
                    r0.b(C1373R.string.activity_state_label_finish_error_message).a(C1373R.string.activity_alert_dialog_text_ok, new CyclingActivityCopy$2(this, r0)).b(C1373R.string.activity_alert_dialog_text_cancel, new CyclingActivityCopy$1(this, r0)).a();
                    return;
                } else {
                    r0 = new C2621c(this);
                    r0.b(C1373R.string.label_finish_cycling_dialog_msg);
                    r0.a(C1373R.string.activity_alert_dialog_text_ok, new CyclingActivityCopy$4(this, r0)).b(C1373R.string.activity_alert_dialog_text_cancel, new CyclingActivityCopy$3(this, r0)).a();
                    return;
                }
            case C1373R.id.cycling_fragment_cycling_resume_or_pause:
                m5975e();
                return;
            case C1373R.id.cycling_fragment_cycling_map:
                C2580w.a(this, "查看地图", "click_ridding_map");
                ActivityCompat.startActivity(this, new Intent(this, MapActivity.class), ActivityOptionsCompat.makeScaleUpAnimation(this.f4579k, this.f4579k.getWidth() / 2, this.f4579k.getHeight() / 2, 0, 0).toBundle());
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private void m5962a() {
        String[] strArr = new String[]{"android.permission.ACCESS_FINE_LOCATION"};
        if (!C1529b.a(this, strArr)) {
            C1529b.a(this, getString(C1373R.string.msg_start_cycling_get_location_permission), 12, strArr);
        } else if (this.f4566B && ActivityService.f8509a) {
            this.f4566B = false;
            this.f4565A = 0;
            this.f4570a.postDelayed(this.f4571b, 400);
            this.f4567C = new CyclingActivityCopy$6(this, this);
        }
    }

    /* renamed from: b */
    private void m5968b() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.beastbikes.intent.action.ACTIVITY_START");
        intentFilter.addAction("com.beastbikes.intent.action.ACTIVITY_PAUSE");
        intentFilter.addAction("com.beastbikes.intent.action.ACTIVITY_AUTO_PAUSE");
        intentFilter.addAction("com.beastbikes.intent.action.ACTIVITY_RESUME");
        intentFilter.addAction("com.beastbikes.intent.action.ACTIVITY_COMPLETE");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        registerReceiver(this.f4583o, intentFilter);
    }

    /* renamed from: c */
    private void m5971c() {
        this.f4577i.setOnClickListener(this);
        this.f4578j.setOnClickListener(this);
        this.f4579k.setOnClickListener(this);
        this.f4581m.setOnClickListener(this);
        this.f4582n.setOnClickListener(this);
        this.f4572d.a(new CyclingActivityCopy$7(this), this.f4587s);
        this.f4572d.a(new int[]{C1373R.drawable.circle_indicator_stroke, C1373R.drawable.circle_indicator_solid});
        this.f4572d.a(PageIndicatorAlign.CENTER_HORIZONTAL, C2801d.a(this, 94.0f));
        this.f4572d.setcurrentitem(0);
        this.f4580l.setOnClickListener(this);
        if (this.f4586r) {
            this.f4573e.setText(getString(C1373R.string.cycling_fragment_real_time_speed) + "(km/h)");
        } else {
            this.f4573e.setText(getString(C1373R.string.cycling_fragment_real_time_speed) + "(MPH)");
        }
    }

    /* renamed from: d */
    private void m5973d() {
        JSONArray jSONArray;
        if (this.f4589u.contains("cycling_data_setting")) {
            JSONArray jSONArray2;
            try {
                jSONArray2 = new JSONArray(this.f4589u.getString("cycling_data_setting", ""));
            } catch (Exception e) {
                f4564c.error("get cycling data setting error," + e);
                jSONArray2 = null;
            }
            jSONArray = jSONArray2;
        } else {
            jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("0", 4);
            } catch (Exception e2) {
                f4564c.error("Cycling data put time error, " + e2);
            }
            try {
                jSONArray.put(0, jSONObject);
            } catch (Exception e22) {
                f4564c.error("Cycling data set time error, " + e22);
            }
            jSONObject = new JSONObject();
            try {
                jSONObject.put("0", 2);
                jSONObject.put(C0844a.f2048d, 0);
            } catch (Exception e222) {
                f4564c.error("Cycling data put altitude and svg speed error, " + e222);
            }
            try {
                jSONArray.put(1, jSONObject);
            } catch (Exception e2222) {
                f4564c.error("Cycling data set altitude and svg error, " + e2222);
            }
            jSONObject = new JSONObject();
            try {
                jSONObject.put("0", 3);
            } catch (Exception e22222) {
                f4564c.error("Cycling data put altitude and svg error, " + e22222);
            }
            try {
                jSONArray.put(2, jSONObject);
            } catch (Exception e222222) {
                f4564c.error("Cycling data set uphill distance error, " + e222222);
            }
            this.f4589u.edit().putString("cycling_data_setting", jSONArray.toString()).apply();
        }
        if (jSONArray == null || jSONArray.length() <= 0) {
            this.f4589u.edit().remove("cycling_data_setting").apply();
            m5973d();
            return;
        }
        this.f4587s.clear();
        for (int i = 0; i < jSONArray.length(); i++) {
            this.f4587s.add(new PreviewDto(this, jSONArray.optJSONObject(i), this.f4588t, this.f4586r));
        }
        this.f4572d.a();
    }

    /* renamed from: e */
    private void m5975e() {
        Intent intent = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
        intent.putExtra(C0861d.f2143o, "com.beastbikes.intent.action.ACTIVITY_PAUSE_OR_RESUME");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setPackage(getPackageName());
        startService(intent);
    }

    /* renamed from: f */
    private void m5976f() {
        C2580w.a(this, "", "click_ridding_finish");
        Intent intent = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
        intent.putExtra(C0861d.f2143o, "com.beastbikes.intent.action.ACTIVITY_COMPLETE");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setPackage(getPackageName());
        startService(intent);
        getSharedPreferences(getPackageName(), 0).edit().remove("use_stage_route_id").remove("use_route_id").remove("use_route_name").remove("use_stage_route_hint").commit();
    }

    /* renamed from: a */
    private void m5963a(int i) {
        switch (i) {
            case 0:
            case 4:
                m5980h();
                return;
            case 1:
                m5982j();
                return;
            case 2:
            case 3:
                m5981i();
                return;
            default:
                return;
        }
    }

    /* renamed from: g */
    private void m5979g() {
        this.f4578j.setText(C1373R.string.activity_state_label_resume);
        this.f4578j.setCompoundDrawablesWithIntrinsicBounds(0, C1373R.drawable.ic_cycling_pause_icon, 0, 0);
        this.f4577i.setVisibility(0);
        if (((BeastBikes) getApplication()).m5259g()) {
            getWindow().addFlags(128);
        } else {
            getWindow().clearFlags(128);
        }
    }

    /* renamed from: h */
    private void m5980h() {
        getWindow().clearFlags(128);
        this.f4578j.setText(C1373R.string.activity_state_label_start);
        this.f4578j.setCompoundDrawablesWithIntrinsicBounds(0, C1373R.drawable.ic_cycling_start_icon, 0, 0);
        m5983k();
        finish();
    }

    /* renamed from: i */
    private void m5981i() {
        this.f4578j.startAnimation(this.f4585q);
        this.f4578j.setText(C1373R.string.activity_state_label_resume);
        this.f4578j.setCompoundDrawablesWithIntrinsicBounds(0, C1373R.drawable.ic_cycling_start_icon, 0, 0);
        this.f4577i.setVisibility(0);
        this.f4574f.setText("0.0");
        m5973d();
    }

    /* renamed from: j */
    private void m5982j() {
        this.f4578j.clearAnimation();
        this.f4576h.clearAnimation();
        this.f4574f.clearAnimation();
        this.f4577i.setVisibility(0);
        this.f4578j.setText("");
        this.f4578j.setCompoundDrawablesWithIntrinsicBounds(0, C1373R.drawable.ic_cycling_pause_icon, 0, 0);
    }

    /* renamed from: k */
    private void m5983k() {
        this.f4576h.setText("0.0");
        this.f4574f.setText("0.0");
    }

    /* renamed from: a */
    private void m5964a(LocalActivity localActivity) {
        if (localActivity != null) {
            this.f4588t = localActivity;
            double totalDistance = localActivity.getTotalDistance() / 1000.0d;
            double instantaneousVelocity = localActivity.getInstantaneousVelocity();
            if (this.f4586r) {
                this.f4576h.setText(String.format("%.1f", new Object[]{Double.valueOf(totalDistance)}));
                this.f4574f.setText(String.format("%.1f", new Object[]{Double.valueOf(instantaneousVelocity)}));
                this.f4575g.setText(C1373R.string.str_cycling_distance_with_unit_km);
            } else {
                this.f4576h.setText(String.format("%.1f", new Object[]{Double.valueOf(C1849a.a(totalDistance))}));
                this.f4575g.setText(C1373R.string.str_cycling_distance_with_unit_mile);
                this.f4574f.setText(String.format("%.1f", new Object[]{Double.valueOf(C1849a.d(instantaneousVelocity))}));
            }
            m5973d();
        }
    }
}
