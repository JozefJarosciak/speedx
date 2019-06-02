package com.beastbikes.android.home;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.a$c;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.ui.DevicesFragment;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.embapi.BrowserActivity;
import com.beastbikes.android.home.C1835a.C1829a;
import com.beastbikes.android.home.C1835a.C1831c;
import com.beastbikes.android.home.p105a.C1834a;
import com.beastbikes.android.home.p105a.C1834a.C1832a;
import com.beastbikes.android.home.p106b.C1837a;
import com.beastbikes.android.home.p106b.C1841b;
import com.beastbikes.android.home.p106b.C1841b.C1840c;
import com.beastbikes.android.home.p106b.C1844c;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.ui.CyclingFragment;
import com.beastbikes.android.modules.cycling.club.ui.ClubDefaultActivity;
import com.beastbikes.android.modules.cycling.club.ui.widget.MyFrameLayout;
import com.beastbikes.android.modules.cycling.ranking.ui.RankActivity;
import com.beastbikes.android.modules.cycling.route.ui.RouteSelfActivity;
import com.beastbikes.android.modules.cycling.sections.ui.CompetitionSectionActivity;
import com.beastbikes.android.modules.cycling.stage.ui.StageMapListBaseActivity;
import com.beastbikes.android.modules.preferences.ui.SettingActivity;
import com.beastbikes.android.modules.shop.ui.NearbyBikeShopActivity;
import com.beastbikes.android.modules.train.ui.TrainCourseInfoActivity;
import com.beastbikes.android.modules.train.ui.TrainDefaultActivity;
import com.beastbikes.android.modules.user.dto.MedalDTO;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.ui.MedalInfoActivity;
import com.beastbikes.android.modules.user.ui.ProfileDataFragment;
import com.beastbikes.android.update.p079a.C2547a.C2546a;
import com.beastbikes.android.update.p162b.C2548a;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.utils.aa;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.ui.android.WebActivity;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.mapbox.services.directions.v5.DirectionsCriteria;
import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903141)
public class HomeActivity extends SessionFragmentActivity implements C1371a, C1829a, C1831c, C1832a, C1840c, C2546a {
    /* renamed from: a */
    public static int f4418a = 1;
    /* renamed from: b */
    public static int f4419b = 0;
    /* renamed from: c */
    private static final Logger f4420c = LoggerFactory.getLogger("HomeActivity");
    @C1458a(a = 2131755785)
    /* renamed from: d */
    private ListView f4421d;
    @C1458a(a = 2131755788)
    /* renamed from: e */
    private ViewPager f4422e;
    @C1458a(a = 2131755789)
    /* renamed from: f */
    private LinearLayout f4423f;
    /* renamed from: g */
    private C1841b f4424g;
    /* renamed from: h */
    private C1837a f4425h;
    /* renamed from: i */
    private C1835a f4426i;
    /* renamed from: j */
    private long f4427j;
    /* renamed from: k */
    private SharedPreferences f4428k;
    /* renamed from: l */
    private SharedPreferences f4429l;
    /* renamed from: m */
    private HomeActivity$a f4430m;
    /* renamed from: n */
    private C1398a f4431n;
    /* renamed from: o */
    private C2389c f4432o;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f4425h = new C1837a(this);
            this.f4424g = new C1841b(this, this.f4421d);
            this.f4424g.a(this.f4425h);
            this.f4424g.a(this);
            this.f4424g.a(this);
            this.f4424g.a(C1373R.id.nav_item_cycling);
            this.f4421d.postDelayed(new HomeActivity$1(this), 200);
            this.f4428k = getSharedPreferences(currentUser.getObjectId(), 0);
            this.f4429l = PreferenceManager.getDefaultSharedPreferences(this);
            this.f4426i = new C1835a(this, this.f4424g);
            this.f4426i.b();
            this.f4426i.a(this);
            this.f4426i.a(this);
            this.f4430m = new HomeActivity$a(this);
            this.f4426i.a(this);
            this.f4431n = new C1398a((Activity) this);
            this.f4432o = new C2389c(this);
            m5780c();
            m5782d();
            m5785f();
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.f4426i != null) {
            this.f4426i.c();
        }
        if (this.f4425h != null) {
            this.f4425h.b();
        }
        if (this.f4424g != null) {
            this.f4424g.a();
        }
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        if (intent.getBooleanExtra("INTENT_TO_CYCLING_FRAGMENT", false) && this.f4426i != null) {
            this.f4422e.setCurrentItem(1);
            f4419b = 1;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        m5784e();
        if (this.f4426i != null) {
            this.f4426i.a();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1) {
            return true;
        }
        if (keyEvent.getKeyCode() != 4) {
            return super.dispatchKeyEvent(keyEvent);
        }
        if (aa.a() - this.f4427j > 2000) {
            Toasts.show(this, C1373R.string.activity_home_toast_backhome_dellay);
            this.f4427j = aa.a();
            return true;
        }
        finish();
        return true;
    }

    /* renamed from: a */
    public boolean m5790a(int i) {
        Intent intent;
        switch (i) {
            case C1373R.id.nav_item_activity:
                C2580w.a(this, "", "click_cycling_event");
                m5778b();
                break;
            case C1373R.id.nav_item_club:
                intent = new Intent(this, ClubDefaultActivity.class);
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser != null) {
                    intent.putExtra("club_id", currentUser.getClubId());
                    startActivity(intent);
                    break;
                }
                break;
            case C1373R.id.nav_item_railway:
                startActivity(new Intent(this, CompetitionSectionActivity.class));
                break;
            case C1373R.id.nav_item_ranking:
                startActivity(new Intent(this, RankActivity.class));
                break;
            case C1373R.id.nav_item_route:
                C2580w.a(this, "", "click_my_page_my_road_book");
                startActivity(new Intent(this, RouteSelfActivity.class));
                break;
            case C1373R.id.nav_item_segment:
                intent = new Intent(this, StageMapListBaseActivity.class);
                intent.putExtra("intent_type", 17);
                startActivity(intent);
                break;
            case C1373R.id.nav_item_setting:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case C1373R.id.nav_item_store:
                startActivity(new Intent(this, NearbyBikeShopActivity.class));
                break;
            case C1373R.id.nav_item_train_course:
                m5788g();
                break;
        }
        return false;
    }

    /* renamed from: a */
    private void m5774a() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.getTrainingId() > 0) {
                Intent intent = new Intent(this, TrainCourseInfoActivity.class);
                intent.putExtra("train_course_id", currentUser.getTrainingId());
                intent.putExtra("train_type", currentUser.getTrainingType() == 1 ? "train_type_single" : "train_type_long");
                startActivity(intent);
                return;
            }
            startActivity(new Intent(this, TrainDefaultActivity.class));
        }
    }

    /* renamed from: b */
    public void m5791b(int i) {
    }

    /* renamed from: c */
    public void m5792c(int i) {
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        f4420c.info("onTrimMemory level: " + i);
    }

    /* renamed from: b */
    private void m5778b() {
        Uri parse = Uri.parse(new StringBuilder(a$c.f7200a).append("/app/activity/list.html").toString());
        Intent intent = new Intent(this, BrowserActivity.class);
        intent.setData(parse);
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setPackage(getPackageName());
        intent.putExtra(WebActivity.EXTRA_TITLE, getString(C1373R.string.task_title));
        intent.putExtra(WebActivity.EXTRA_ENTER_ANIMATION, C1373R.anim.activity_in_from_right);
        intent.putExtra(WebActivity.EXTRA_EXIT_ANIMATION, C1373R.anim.activity_out_to_right);
        intent.putExtra(WebActivity.EXTRA_NONE_ANIMATION, C1373R.anim.activity_none);
        Bundle bundle = new Bundle();
        bundle.putString("X-User-Id", m5331p());
        intent.putExtra(WebActivity.EXTRA_HTTP_HEADERS, bundle);
        startActivity(intent);
        this.f4428k.edit().putInt("beast.cycling.activity.dot", 0).apply();
        C2580w.a(this, "进入活动入口", "open_activity");
    }

    /* renamed from: c */
    private void m5780c() {
        PagerAdapter c1834a = new C1834a(this, this.f4422e);
        C1844c c1844c = new C1844c(C1373R.string.str_my_data, C1373R.drawable.selector_tab_profile_data, "profile_data", this, this.f4423f, 3, null);
        C1844c c1844c2 = new C1844c(C1373R.string.activity_fragment_title, C1373R.drawable.selector_tab_cycling, DirectionsCriteria.PROFILE_CYCLING, this, this.f4423f, 3, null);
        C1844c c1844c3 = new C1844c(C1373R.string.profile_fragment_detail_item_speed_force_title, C1373R.drawable.selector_tab_my_devices, "my_devices", this, this.f4423f, 3, null);
        c1834a.a(c1844c, ProfileDataFragment.class, null);
        c1834a.a(c1844c2, CyclingFragment.class, null);
        c1834a.a(c1844c3, DevicesFragment.class, null);
        c1834a.a(this);
        this.f4422e.setOffscreenPageLimit(3);
        this.f4422e.setAdapter(c1834a);
        this.f4422e.setCurrentItem(0);
        setTitle(C1373R.string.str_my_data);
        c1844c.a(true);
        f4419b = 0;
    }

    /* renamed from: a */
    public void m5789a(C2548a c2548a) {
        if (c2548a != null) {
            if (c2548a.c() != this.f4429l.getInt("beast.version.update", 0) || c2548a.d() != 0) {
                C2621c c2621c = new C2621c(this);
                c2621c.a(String.format(getString(C1373R.string.version_update_title), new Object[]{c2548a.b()}));
                c2621c.b(c2548a.f());
                c2621c.b(false);
                if (c2548a.d() == 0) {
                    c2621c.b(C1373R.string.version_update_cancel, new HomeActivity$2(this, c2621c, c2548a));
                } else {
                    c2621c.a(false);
                }
                c2621c.a(C1373R.string.version_update_ok, new HomeActivity$3(this, c2548a, c2621c));
                c2621c.a();
            }
        }
    }

    /* renamed from: d */
    public void m5793d(int i) {
    }

    /* renamed from: d */
    private void m5782d() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyFrameLayout.f10047a);
        intentFilter.addAction("action_finish_home_activity");
        registerReceiver(this.f4430m, intentFilter);
    }

    /* renamed from: e */
    private void m5784e() {
        if (this.f4430m != null && !this.f4430m.isOrderedBroadcast()) {
            unregisterReceiver(this.f4430m);
        }
    }

    /* renamed from: f */
    private void m5785f() {
        getAsyncTaskQueue().a(new HomeActivity$4(this), new String[0]);
    }

    /* renamed from: a */
    private void m5776a(List<MedalDTO> list) {
        Intent intent = new Intent(this, MedalInfoActivity.class);
        intent.putExtra("medal_list", (Serializable) list);
        intent.putExtra("from_push", true);
        startActivity(intent);
    }

    /* renamed from: g */
    private void m5788g() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            if (currentUser.getTrainingType() != 2) {
                m5774a();
                return;
            }
            getAsyncTaskQueue().a(new HomeActivity$5(this, new C1802i(this, C1373R.string.empty, true), currentUser), new Void[0]);
        }
    }
}
