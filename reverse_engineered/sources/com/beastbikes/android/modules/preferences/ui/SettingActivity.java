package com.beastbikes.android.modules.preferences.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.jpush.android.api.JPushInterface;
import com.alipay.sdk.packet.C0861d;
import com.baidu.mapapi.map.offline.MKOfflineMapListener;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.authentication.C1541b;
import com.beastbikes.android.authentication.p055a.C1536a;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.main.AboutActivity;
import com.beastbikes.android.main.SelectAuthActivity;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.SyncService;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.club.biz.C2057d;
import com.beastbikes.android.modules.preferences.ui.offlineMap.OfflineMapActivity;
import com.beastbikes.android.modules.setting.ui.LaboratoryActivity;
import com.beastbikes.android.modules.social.im.p074a.C1434c;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.ui.TrainTargetSetActivity;
import com.beastbikes.android.modules.user.ui.UserBaseInfoSettingsActivity;
import com.beastbikes.android.modules.user.ui.binding.AccountManagerActivity;
import com.beastbikes.android.update.p079a.C2547a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p176b.C2790a;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.widget.CircleImageView;
import com.squareup.picasso.Picasso;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation.ConversationType;

@C1457a(a = "设置")
@C1459b(a = 2130903678)
public class SettingActivity extends SessionFragmentActivity implements OnSharedPreferenceChangeListener, OnClickListener, MKOfflineMapListener {
    @C1458a(a = 2131757558)
    /* renamed from: a */
    private ViewGroup f5907a;
    @C1458a(a = 2131757559)
    /* renamed from: b */
    private CircleImageView f5908b;
    @C1458a(a = 2131757560)
    /* renamed from: c */
    private TextView f5909c;
    @C1458a(a = 2131757561)
    /* renamed from: d */
    private TextView f5910d;
    @C1458a(a = 2131757567)
    /* renamed from: e */
    private TextView f5911e;
    @C1458a(a = 2131757568)
    /* renamed from: f */
    private TextView f5912f;
    @C1458a(a = 2131757563)
    /* renamed from: g */
    private TextView f5913g;
    @C1458a(a = 2131757571)
    /* renamed from: h */
    private View f5914h;
    @C1458a(a = 2131757577)
    /* renamed from: i */
    private View f5915i;
    @C1458a(a = 2131757569)
    /* renamed from: j */
    private LinearLayout f5916j;
    @C1458a(a = 2131757570)
    /* renamed from: k */
    private Switch f5917k;
    @C1458a(a = 2131757572)
    /* renamed from: l */
    private View f5918l;
    @C1458a(a = 2131757575)
    /* renamed from: m */
    private View f5919m;
    @C1458a(a = 2131757576)
    /* renamed from: n */
    private View f5920n;
    @C1458a(a = 2131757564)
    /* renamed from: o */
    private View f5921o;
    @C1458a(a = 2131757578)
    /* renamed from: p */
    private TextView f5922p;
    @C1458a(a = 2131757573)
    /* renamed from: q */
    private View f5923q;
    @C1458a(a = 2131757565)
    /* renamed from: r */
    private ViewGroup f5924r;
    @C1458a(a = 2131757566)
    /* renamed from: s */
    private TextView f5925s;
    /* renamed from: t */
    private C2389c f5926t;
    /* renamed from: u */
    private SharedPreferences f5927u;
    /* renamed from: v */
    private SharedPreferences f5928v;
    /* renamed from: w */
    private String f5929w;

    public void onGetOfflineMapState(int i, int i2) {
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.f5926t = new C2389c(this);
        this.f5907a.setOnClickListener(this);
        if (C1849a.a()) {
            this.f5914h.setVisibility(0);
            this.f5914h.setOnClickListener(this);
        }
        BeastBikes beastBikes = (BeastBikes) getApplication();
        this.f5927u = getSharedPreferences(m5331p(), 0);
        this.f5927u.registerOnSharedPreferenceChangeListener(this);
        this.f5928v = PreferenceManager.getDefaultSharedPreferences(this);
        this.f5928v.registerOnSharedPreferenceChangeListener(this);
        this.f5917k.setOnCheckedChangeListener(new SettingActivity$1(this, beastBikes));
        this.f5918l.setOnClickListener(this);
        this.f5919m.setOnClickListener(this);
        this.f5921o.setOnClickListener(this);
        this.f5916j.setOnClickListener(this);
        this.f5911e.setOnClickListener(this);
        this.f5922p.setOnClickListener(this);
        this.f5913g.setOnClickListener(this);
        this.f5915i.setOnClickListener(this);
        this.f5923q.setOnClickListener(this);
        this.f5912f.setOnClickListener(this);
        this.f5924r.setOnClickListener(this);
        this.f5925s.setText(C1849a.b(this) ? C1373R.string.str_unit_type_metric : C1373R.string.str_unit_type_imperial);
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            if (TextUtils.isEmpty(currentUser.getAvatar())) {
                this.f5908b.setImageResource(C1373R.drawable.ic_avatar);
            } else {
                Picasso.with(this).load(currentUser.getAvatar()).fit().centerCrop().error((int) C1373R.drawable.ic_avatar).placeholder((int) C1373R.drawable.ic_avatar).into(this.f5908b);
            }
        }
        m7160b();
    }

    public void onResume() {
        super.onResume();
        this.f5917k.setChecked(((BeastBikes) getApplication()).m5258f());
        this.f5929w = m5331p();
        if (!TextUtils.isEmpty(this.f5929w)) {
            m7158a(this.f5929w);
        }
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case C1373R.id.setting_fragment_item_user:
                C2580w.a(this, "用户设置", null);
                intent = new Intent(this, UserBaseInfoSettingsActivity.class);
                intent.putExtra("enter_type", 1);
                startActivityForResult(intent, 2);
                return;
            case C1373R.id.settings_fragment_item_train_target:
                C2580w.a(this, "训练指标设置", "setting_ridding_goal");
                intent = new Intent(this, TrainTargetSetActivity.class);
                intent.putExtra("enter_type", 1);
                startActivity(intent);
                return;
            case C1373R.id.stopwatchsettings:
                C2580w.a(this, "骑行页设置", "setting_ridding_data");
                startActivity(new Intent(this, CyclingSettingActivity.class));
                return;
            case C1373R.id.setting_fragment_item_unit_view:
                if (C1849a.b(this)) {
                    this.f5928v.edit().putInt("km_or_mi", 1).apply();
                    this.f5925s.setText(C1373R.string.str_unit_type_imperial);
                    return;
                }
                this.f5928v.edit().putInt("km_or_mi", 0).apply();
                this.f5925s.setText(C1373R.string.str_unit_type_metric);
                return;
            case C1373R.id.setting_fragment_item_voice_banding:
                C2580w.a(this, "账号绑定", "accout_bind");
                startActivity(new Intent(this, AccountManagerActivity.class));
                return;
            case C1373R.id.setting_fragment_item_service_contact:
                C2580w.a(this, "服务管理", "service_manager");
                startActivity(new Intent(this, ServiceManagerActivity.class));
                return;
            case C1373R.id.setting_fragment_laboratory:
                C2580w.a(this, "野兽实验室", "speedx_library");
                startActivity(new Intent(this, LaboratoryActivity.class));
                return;
            case C1373R.id.setting_fragment_item_offline_map:
                startActivity(new Intent(this, OfflineMapActivity.class));
                C2580w.a(this, "离线地图", "enter_offline_map");
                return;
            case C1373R.id.setting_fragment_item_clear_cache:
                C2580w.a(this, "清除缓存", null);
                m7156a();
                return;
            case C1373R.id.setting_fragment_item_feedback:
                C2580w.a(this, "用户反馈", null);
                if (C1434c.m7302c().m7310a()) {
                    String str = "speedx_Feedback";
                    if (C1849a.c()) {
                        str = "speedx";
                    }
                    RongIM.getInstance().startConversation(this, ConversationType.PUBLIC_SERVICE, str, getResources().getString(C1373R.string.customerservice));
                    return;
                }
                return;
            case C1373R.id.setting_fragment_item_about:
                startActivity(new Intent(this, AboutActivity.class));
                C2580w.a(this, "关于页面", null);
                return;
            case C1373R.id.setting_fragment_item_quit:
                C2580w.a(this, "退出登录", null);
                LocalActivity a = new C1398a((Activity) this).m5861a();
                C2621c c2621c = new C2621c(this);
                if (a == null || (a.getState() == 0 && a.getState() == 4)) {
                    c2621c.a(getString(C1373R.string.str_exit_current_account));
                    c2621c.b("");
                    c2621c.a(getString(C1373R.string.str_exit), new SettingActivity$5(this)).b(C1373R.string.cancel, new SettingActivity$4(this, c2621c)).a();
                    return;
                }
                c2621c.a(C1373R.string.club_feed_del_hint);
                c2621c.b(C1373R.string.user_setting_activity_finish_cycling_tip);
                c2621c.a(C1373R.string.activity_alert_dialog_text_ok, new SettingActivity$3(this, c2621c)).b(C1373R.string.cancel, new SettingActivity$2(this, c2621c)).a();
                return;
            case C1373R.id.setting_fragment_email:
                intent = new Intent("android.intent.action.SENDTO");
                intent.setData(Uri.parse("mailto:" + getResources().getString(C1373R.string.contact_us_email_address)));
                startActivity(intent);
                return;
            default:
                return;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.f5927u.unregisterOnSharedPreferenceChangeListener(this);
        this.f5928v.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    /* renamed from: a */
    private void m7156a() {
        new SettingActivity$6(this, new C1802i(this, getString(C1373R.string.setting_fragment_clearing_cache), true)).execute(new Void[0]);
    }

    /* renamed from: a */
    public static void m7157a(Context context) {
        if (context != null) {
            try {
                context.stopService(new Intent(context, SyncService.class));
            } catch (Exception e) {
            }
            Activity activity = null;
            if (context instanceof Activity) {
                activity = (Activity) context;
            }
            JPushInterface.stopPush(context);
            if (AVUser.getCurrentUser() != null) {
                try {
                    new C2389c(context).a(new C2389c(context).a(AVUser.getCurrentUser().getObjectId()));
                } catch (BusinessException e2) {
                    e2.printStackTrace();
                }
            }
            C2057d.a().b();
            new C1536a(context).a();
            C1541b.a(context);
            C1434c.m7302c().m7311b();
            Intent intent = new Intent(context, SelectAuthActivity.class);
            intent.addFlags(32768);
            intent.addFlags(268435456);
            context.startActivity(intent);
            C1614a b = C1661h.a().b();
            if (b == null) {
                b = C1661h.a().c();
            }
            if (b != null) {
                Intent intent2 = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
                intent2.setPackage(context.getPackageName());
                intent2.putExtra("extra_central_id", b.a());
                intent2.putExtra(C0861d.f2143o, "com.beastbikes.android.ble.intent.action.CENTRAL_DISCONNECT");
                context.startService(intent2);
            }
            if (activity != null) {
                activity.finish();
            }
        }
    }

    /* renamed from: a */
    private void m7158a(String str) {
        C2790a a = C2790a.a();
        getAsyncTaskQueue().a(new SettingActivity$7(this, a), new String[]{str});
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (str.equals("com.beastbikes.android.home.update_userinfo")) {
            if (!TextUtils.isEmpty(this.f5929w)) {
                m7158a(this.f5929w);
            }
        } else if (str.contains("beast.version.update")) {
            m7160b();
        }
    }

    /* renamed from: b */
    private void m7160b() {
        int i = 0;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int i2 = defaultSharedPreferences.getInt("beast.version.update", 0);
        defaultSharedPreferences.edit().putBoolean("beast.version.update.guide1" + i2, false).apply();
        int a = C2547a.a(this);
        boolean z = defaultSharedPreferences.getBoolean("beast.version.update.guide2" + i2, true);
        View view = this.f5920n;
        if (i2 <= a || !z) {
            i = 8;
        }
        view.setVisibility(i);
    }
}
