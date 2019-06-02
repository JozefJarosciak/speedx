package com.beastbikes.android.home;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.location.Location;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import com.alipay.sdk.util.C0882j;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.C1592b;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.embapi.C1815e;
import com.beastbikes.android.home.p106b.C1841b;
import com.beastbikes.android.locale.googlemaputils.C1821c;
import com.beastbikes.android.locale.googlemaputils.C1855a;
import com.beastbikes.android.locale.googlemaputils.C1856b;
import com.beastbikes.android.locale.p104a.C1823a;
import com.beastbikes.android.locale.p104a.C1848b;
import com.beastbikes.android.modules.cycling.SyncService;
import com.beastbikes.android.modules.cycling.club.biz.C2057d;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dao.entity.Club;
import com.beastbikes.android.modules.cycling.ranking.p067a.C2172b;
import com.beastbikes.android.modules.social.im.p074a.C1434c;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.update.p079a.C2547a;
import com.beastbikes.android.update.p079a.C2547a.C2546a;
import com.beastbikes.android.update.p162b.C2548a;
import com.beastbikes.framework.android.p088g.C1467h;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.DensityUtil;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.squareup.picasso.Picasso;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient$ConnectionStatusListener$ConnectionStatus;
import java.util.Locale;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: HomeManager */
/* renamed from: com.beastbikes.android.home.a */
public class C1835a implements OnSharedPreferenceChangeListener, C1371a, C1823a, C1821c {
    /* renamed from: a */
    private final Logger f8261a = LoggerFactory.getLogger(C1835a.class);
    /* renamed from: b */
    private HomeActivity f8262b;
    /* renamed from: c */
    private SharedPreferences f8263c;
    /* renamed from: d */
    private SharedPreferences f8264d;
    /* renamed from: e */
    private ClubManager f8265e;
    /* renamed from: f */
    private AVUser f8266f;
    /* renamed from: g */
    private C1841b f8267g;
    /* renamed from: h */
    private boolean f8268h = false;
    /* renamed from: i */
    private boolean f8269i = true;
    /* renamed from: j */
    private CountDownTimer f8270j;
    /* renamed from: k */
    private C2389c f8271k;
    /* renamed from: l */
    private C1831c f8272l;
    /* renamed from: m */
    private C1829a f8273m;
    /* renamed from: n */
    private C1830b f8274n;
    /* renamed from: o */
    private final TagAliasCallback f8275o = new TagAliasCallback(this) {
        /* renamed from: a */
        final /* synthetic */ C1835a f8240a;

        {
            this.f8240a = r1;
        }

        public void gotResult(int i, String str, Set<String> set) {
            String str2;
            switch (i) {
                case 0:
                    str2 = "Jpush Set tag and alias success";
                    break;
                case 6002:
                    str2 = "Jpush Failed to set alias and tags due to timeout. Try again after 60s.";
                    break;
                default:
                    str2 = "Jpush Failed with errorCode = " + i;
                    break;
            }
            this.f8240a.f8261a.info(str2);
        }
    };

    /* compiled from: HomeManager */
    /* renamed from: com.beastbikes.android.home.a$2 */
    class C18182 implements Listener<JSONArray> {
        /* renamed from: a */
        final /* synthetic */ C1835a f8244a;

        C18182(C1835a c1835a) {
            this.f8244a = c1835a;
        }

        public /* synthetic */ void onResponse(Object obj) {
            m9532a((JSONArray) obj);
        }

        /* renamed from: a */
        public void m9532a(JSONArray jSONArray) {
            if (jSONArray != null && jSONArray.length() != 0) {
                this.f8244a.f8264d.edit().putBoolean("beast.water.marker.load", true).commit();
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONArray optJSONArray = jSONArray.optJSONObject(i).optJSONArray("images");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                            if (optJSONObject != null) {
                                String optString = optJSONObject.optString("white_url");
                                String optString2 = optJSONObject.optString("black_url");
                                Picasso.with(this.f8244a.f8262b).load(optString).fetch();
                                Picasso.with(this.f8244a.f8262b).load(optString2).fetch();
                            }
                        }
                    }
                }
            }
        }
    }

    /* compiled from: HomeManager */
    /* renamed from: com.beastbikes.android.home.a$3 */
    class C18193 implements ErrorListener {
        /* renamed from: a */
        final /* synthetic */ C1835a f8245a;

        C18193(C1835a c1835a) {
            this.f8245a = c1835a;
        }

        public void onErrorResponse(VolleyError volleyError) {
        }
    }

    /* compiled from: HomeManager */
    /* renamed from: com.beastbikes.android.home.a$5 */
    class C18245 implements C1823a {
        /* renamed from: a */
        final /* synthetic */ C1835a f8249a;

        /* compiled from: HomeManager */
        /* renamed from: com.beastbikes.android.home.a$5$1 */
        class C18221 implements C1821c {
            /* renamed from: a */
            final /* synthetic */ C18245 f8248a;

            C18221(C18245 c18245) {
                this.f8248a = c18245;
            }

            /* renamed from: a */
            public void mo3252a(C1856b c1856b) {
                if (c1856b != null) {
                    String d = c1856b.m9678d();
                    if (this.f8248a.f8249a.f8269i) {
                        if (this.f8248a.f8249a.f8270j != null) {
                            this.f8248a.f8249a.f8270j.cancel();
                        }
                        this.f8248a.f8249a.m9568c(d);
                    }
                }
            }

            /* renamed from: a */
            public void mo3251a(VolleyError volleyError) {
            }
        }

        C18245(C1835a c1835a) {
            this.f8249a = c1835a;
        }

        /* renamed from: a */
        public void mo3253a(Location location) {
            new C1855a().m9671a(this.f8249a.f8262b, this.f8249a.f8262b.getRequestQueueFactory(), location.getLatitude(), location.getLongitude(), new C18221(this));
        }

        public void e_() {
        }
    }

    /* compiled from: HomeManager */
    /* renamed from: com.beastbikes.android.home.a$6 */
    class C18256 extends AsyncTask<Void, Void, Club> {
        /* renamed from: a */
        final /* synthetic */ C1835a f8250a;

        C18256(C1835a c1835a) {
            this.f8250a = c1835a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m9541a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m9542a((Club) obj);
        }

        /* renamed from: a */
        protected Club m9541a(Void... voidArr) {
            try {
                if (this.f8250a.f8266f == null) {
                    return null;
                }
                if (this.f8250a.f8265e == null) {
                    this.f8250a.f8265e = new ClubManager(this.f8250a.f8262b);
                }
                Club b = this.f8250a.f8265e.m10544b(this.f8250a.f8266f.getObjectId());
                if (b == null || TextUtils.isEmpty(b.getClubId())) {
                    return b;
                }
                this.f8250a.f8265e.m10547c(b.getClubId());
                return b;
            } catch (BusinessException e) {
                return null;
            }
        }

        /* renamed from: a */
        protected void m9542a(Club club) {
            if (club == null) {
                this.f8250a.f8261a.error("getClubRelation(), Club is null");
                return;
            }
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                currentUser.setClubId(club.getClubId());
            }
        }
    }

    /* compiled from: HomeManager */
    /* renamed from: com.beastbikes.android.home.a$7 */
    class C18267 extends AsyncTask<Void, Void, JSONObject> {
        /* renamed from: a */
        final /* synthetic */ C1835a f8251a;

        C18267(C1835a c1835a) {
            this.f8251a = c1835a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m9543a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m9544a((JSONObject) obj);
        }

        /* renamed from: a */
        protected JSONObject m9543a(Void... voidArr) {
            try {
                return this.f8251a.f8265e.m10545b();
            } catch (BusinessException e) {
                return null;
            }
        }

        /* renamed from: a */
        protected void m9544a(JSONObject jSONObject) {
            if (jSONObject != null) {
                int optInt = jSONObject.optInt("clubMsg") | this.f8251a.f8263c.getInt("beast.club.feed.dot.total.count", 0);
                int optInt2 = jSONObject.optInt("clubApply") | this.f8251a.f8263c.getInt("beast.club.dot.more", 0);
                int optInt3 = jSONObject.optInt("follow") | this.f8251a.f8263c.getInt("beast.follow.dot", 0);
                int optInt4 = jSONObject.optInt("medal") | this.f8251a.f8263c.getInt("beast.cycling.activity.dot", 0);
                Editor edit = this.f8251a.f8263c.edit();
                edit.putInt("beast.club.feed.dot.total.count", optInt);
                edit.putInt("beast.club.dot.more", optInt2);
                edit.putInt("beast.follow.dot", optInt3);
                edit.putInt("beast.cycling.activity.dot", optInt4);
                edit.apply();
                this.f8251a.m9575g();
            }
        }
    }

    /* compiled from: HomeManager */
    /* renamed from: com.beastbikes.android.home.a$8 */
    class C18278 extends AsyncTask<Void, Void, JSONObject> {
        /* renamed from: a */
        final /* synthetic */ C1835a f8252a;

        C18278(C1835a c1835a) {
            this.f8252a = c1835a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m9545a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m9546a((JSONObject) obj);
        }

        /* renamed from: a */
        protected JSONObject m9545a(Void... voidArr) {
            try {
                return this.f8252a.f8271k.m12137c();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        protected void m9546a(JSONObject jSONObject) {
            super.onPostExecute(jSONObject);
            if (jSONObject != null && jSONObject.optInt("code") == 0) {
                JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
                if (optJSONObject != null) {
                    ((BeastBikes) BeastBikes.j()).f(optJSONObject.optBoolean("display_repair_function_status"));
                }
            }
        }
    }

    /* compiled from: HomeManager */
    /* renamed from: com.beastbikes.android.home.a$9 */
    class C18289 extends AsyncTask<Void, Void, Void> {
        /* renamed from: a */
        final /* synthetic */ C1835a f8253a;

        C18289(C1835a c1835a) {
            this.f8253a = c1835a;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m9547a((Void[]) objArr);
        }

        /* renamed from: a */
        protected Void m9547a(Void... voidArr) {
            try {
                String registrationID = JPushInterface.getRegistrationID(this.f8253a.f8262b);
                try {
                    this.f8253a.f8261a.info("Jpush RegistrationID = [" + registrationID + "]");
                    JPushInterface.setAliasAndTags(this.f8253a.f8262b, registrationID, null, this.f8253a.f8275o);
                } catch (Exception e) {
                    this.f8253a.f8261a.error(e.toString());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            return null;
        }
    }

    /* compiled from: HomeManager */
    /* renamed from: com.beastbikes.android.home.a$a */
    public interface C1829a {
        /* renamed from: b */
        void m9548b(int i);
    }

    /* compiled from: HomeManager */
    /* renamed from: com.beastbikes.android.home.a$b */
    public interface C1830b {
        /* renamed from: a */
        void mo3255a(String str);
    }

    /* compiled from: HomeManager */
    /* renamed from: com.beastbikes.android.home.a$c */
    public interface C1831c {
        /* renamed from: c */
        void m9550c(int i);
    }

    /* renamed from: a */
    public void mo3252a(C1856b c1856b) {
        this.f8269i = true;
        this.f8270j = new CountDownTimer(this, 3000, 1000) {
            /* renamed from: a */
            final /* synthetic */ C1835a f8243a;

            public void onTick(long j) {
            }

            public void onFinish() {
                this.f8243a.f8269i = false;
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser != null) {
                    this.f8243a.m9568c(currentUser.getCity());
                }
            }
        };
        this.f8270j.start();
        C1848b.m9630a().m9636a(this.f8262b, new C18245(this));
    }

    /* renamed from: a */
    public void mo3251a(VolleyError volleyError) {
    }

    /* renamed from: a */
    public void m9590a(C1831c c1831c) {
        this.f8272l = c1831c;
        m9579i();
    }

    /* renamed from: a */
    public void m9589a(C1829a c1829a) {
        this.f8273m = c1829a;
    }

    /* renamed from: a */
    public void m9586a() {
        if (this.f8263c != null) {
            this.f8263c.unregisterOnSharedPreferenceChangeListener(this);
        }
        if (this.f8264d != null) {
            this.f8264d.unregisterOnSharedPreferenceChangeListener(this);
        }
    }

    /* renamed from: b */
    public void m9593b() {
        if (C1434c.c() != null) {
            C1434c.c().a(this.f8266f.getObjectId());
        }
        if (!C1467h.a(this.f8262b, SyncService.class.getName())) {
            try {
                this.f8262b.startService(new Intent(this.f8262b, SyncService.class));
            } catch (Exception e) {
            }
        }
        if (C2057d.m10598a() != null) {
            C2057d.m10598a().m10605a(this.f8262b);
        }
        String stringExtra = this.f8262b.getIntent().getStringExtra("push_data");
        if (!TextUtils.isEmpty(stringExtra)) {
            m9563a(stringExtra);
        }
        stringExtra = this.f8262b.getIntent().getStringExtra("RONGCLOUDPUSHRONGCLOUDPUSHKEY");
        if (!TextUtils.isEmpty(stringExtra)) {
            m9565b(stringExtra);
        }
        m9584l();
        C1848b.m9630a().m9636a(this.f8262b, (C1823a) this);
        m9571e();
        m9573f();
        m9576h();
    }

    /* renamed from: c */
    public void m9594c() {
        C1815e.m9522a(this.f8262b.getIntent().getData(), this.f8262b);
        m9579i();
        m9581j();
        m9583k();
    }

    public C1835a(HomeActivity homeActivity, C1841b c1841b) {
        this.f8262b = homeActivity;
        this.f8267g = c1841b;
        this.f8265e = new ClubManager((Activity) homeActivity);
        this.f8271k = new C2389c((Activity) homeActivity);
        this.f8266f = AVUser.getCurrentUser();
        if (this.f8266f != null) {
            this.f8263c = homeActivity.getSharedPreferences(this.f8266f.getObjectId(), 0);
            this.f8263c.registerOnSharedPreferenceChangeListener(this);
            this.f8264d = PreferenceManager.getDefaultSharedPreferences(homeActivity);
            this.f8264d.registerOnSharedPreferenceChangeListener(this);
            m9585m();
        }
    }

    /* renamed from: a */
    private void m9563a(String str) {
        try {
            this.f8262b.startActivity(C1592b.m8559a().m8560a(this.f8262b, new JSONObject(str)));
        } catch (Exception e) {
            this.f8261a.error("startActivity4Push error" + e.toString());
        }
    }

    /* renamed from: b */
    private void m9565b(String str) {
        try {
            if (RongIM.getInstance().getCurrentConnectionStatus().equals(RongIMClient$ConnectionStatusListener$ConnectionStatus.CONNECTED)) {
                RongIM.getInstance().startConversationList(this.f8262b, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: e */
    private void m9571e() {
        if (this.f8262b != null) {
            this.f8262b.getAsyncTaskQueue().m13740a(new C18256(this), new Void[0]);
        }
    }

    /* renamed from: f */
    private void m9573f() {
        if (this.f8262b != null) {
            this.f8262b.getAsyncTaskQueue().m13740a(new C18267(this), new Void[0]);
        }
    }

    /* renamed from: g */
    private void m9575g() {
        if (this.f8262b != null) {
            if (AVUser.getCurrentUser() == null || !TextUtils.isEmpty(AVUser.getCurrentUser().getClubId())) {
                int i = (this.f8263c.getInt("beast.club.dot.more", 0) + this.f8263c.getInt("beast.club.feed.dot.total.count", 0)) + this.f8263c.getInt("beast.club.dot.activity", 0);
                if (this.f8273m != null) {
                    this.f8273m.m9548b(i);
                }
            }
        }
    }

    /* renamed from: h */
    private void m9576h() {
        new C18278(this).execute(new Void[0]);
    }

    /* renamed from: i */
    private void m9579i() {
        int i = this.f8263c.getInt("beast.rongcloud.new.message.count", 0);
        int i2 = this.f8263c.getInt("beast.friend.new.message.count", 0);
        int i3 = this.f8263c.getInt("beast.follow.dot", 0);
        if (this.f8272l != null) {
            this.f8272l.m9550c((i + i2) + i3);
        }
    }

    /* renamed from: j */
    private void m9581j() {
        int i = 0;
        int i2 = this.f8263c.getInt("beast.cycling.activity.dot", 0);
        C1841b c1841b = this.f8267g;
        if (i2 <= 0) {
            i = 8;
        }
        c1841b.m9618a(C1373R.id.nav_item_activity, i2, null, i);
    }

    /* renamed from: k */
    private void m9583k() {
        boolean z = true;
        int i = this.f8264d.getInt("beast.version.update", 0);
        boolean z2 = this.f8264d.getBoolean("beast.version.update.guide1" + i, true);
        if (i <= C2547a.m12741a(this.f8262b) || !z2) {
            z = false;
        }
        this.f8267g.m9618a(C1373R.id.nav_item_setting, 0, new LayoutParams(DensityUtil.dip2px(this.f8262b, 8.0f), DensityUtil.dip2px(this.f8262b, 8.0f)), z ? 0 : 8);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (!(!str.contains("beast.home.tab.change") || this.f8274n == null || TextUtils.isEmpty(sharedPreferences.getString(str, "")))) {
            this.f8274n.mo3255a(sharedPreferences.getString(str, ""));
        }
        if (str.contains("beast.cycling.activity.dot")) {
            m9581j();
        } else if (str.contains("beast.version.update")) {
            m9583k();
        } else if (str.contains("beast.rongcloud.new.message.count") || str.contains("beast.friend.new.message.count") || str.contains("beast.follow.dot")) {
            m9579i();
        } else {
            if (str.contains("beast.club.dot.activity") || str.contains("beast.club.dot.more") || str.contains("beast.club.feed.dot.total.count")) {
                m9575g();
            }
            if (str.contains("beast.club.notify.transfer") || str.contains("beast.club.notify.transfer.master")) {
                m9571e();
            } else if (str.contains("beast.club.notify.apply.pass")) {
                this.f8265e.m10552e();
            } else if (str.contains("beast.club.notify.apply.refuse")) {
                this.f8265e.m10553f();
            }
        }
    }

    /* renamed from: a */
    public void mo3253a(Location location) {
        if (location != null && !this.f8268h) {
            this.f8268h = true;
            new C1855a().m9671a(this.f8262b, this.f8262b.getRequestQueueFactory(), location.getLatitude(), location.getLongitude(), this);
            m9561a(location.getLatitude(), location.getLongitude());
        }
    }

    public void e_() {
        m9561a(0.0d, 0.0d);
    }

    /* renamed from: l */
    private void m9584l() {
        this.f8262b.getAsyncTaskQueue().m13740a(new C18289(this), new Void[0]);
    }

    /* renamed from: a */
    private void m9561a(double d, double d2) {
        if (this.f8262b != null) {
            if (JPushInterface.isPushStopped(this.f8262b)) {
                JPushInterface.resumePush(this.f8262b);
            }
            final Object registrationID = JPushInterface.getRegistrationID(this.f8262b);
            if (!TextUtils.isEmpty(registrationID)) {
                final double d3 = d;
                final double d4 = d2;
                this.f8262b.getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, Void>(this) {
                    /* renamed from: d */
                    final /* synthetic */ C1835a f8239d;

                    protected /* synthetic */ Object doInBackground(Object[] objArr) {
                        return m9529a((Void[]) objArr);
                    }

                    /* renamed from: a */
                    protected Void m9529a(Void... voidArr) {
                        try {
                            new C2389c(this.f8239d.f8262b).m12127a(d3, d4, registrationID);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }, new Void[0]);
            }
        }
    }

    /* renamed from: a */
    public void m9592a(final C2546a c2546a) {
        this.f8262b.getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, C2548a>(this) {
            /* renamed from: b */
            final /* synthetic */ C1835a f8242b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m9530a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m9531a((C2548a) obj);
            }

            /* renamed from: a */
            protected C2548a m9530a(Void... voidArr) {
                return new C2547a(this.f8242b.f8262b).m12743a();
            }

            /* renamed from: a */
            protected void m9531a(C2548a c2548a) {
                super.onPostExecute(c2548a);
                if (c2548a != null && c2546a != null) {
                    c2546a.m12740a(c2548a);
                }
            }
        }, new Void[0]);
    }

    /* renamed from: m */
    private void m9585m() {
        if (!this.f8264d.getBoolean("beast.water.marker.load", false)) {
            String str;
            if (Locale.getDefault().getLanguage().equals("zh")) {
                str = "http://bazaar.speedx.com/watermarks/watermark_zh_android.json?ver=" + System.currentTimeMillis();
            } else {
                str = "http://bazaar.speedx.com/watermarks/watermark_en_android.json?ver=" + System.currentTimeMillis();
            }
            Request jsonArrayRequest = new JsonArrayRequest(str, new C18182(this), new C18193(this));
            jsonArrayRequest.setShouldCache(false);
            this.f8262b.getRequestQueueFactory().m13745a(jsonArrayRequest, this.f8262b);
        }
    }

    /* renamed from: c */
    private void m9568c(final String str) {
        this.f8262b.getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, JSONObject>(this) {
            /* renamed from: b */
            final /* synthetic */ C1835a f8247b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m9533a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m9534a((JSONObject) obj);
            }

            /* renamed from: a */
            protected JSONObject m9533a(Void... voidArr) {
                return new C2172b(this.f8247b.f8262b).m11134a(str);
            }

            /* renamed from: a */
            protected void m9534a(JSONObject jSONObject) {
                String str;
                if (jSONObject == null) {
                    str = "CN.22.2038349";
                } else if (jSONObject.has("code") && jSONObject.optInt("code") == 0) {
                    str = jSONObject.optString(C0882j.f2229c);
                } else {
                    Toasts.show(this.f8247b.f8262b, jSONObject.optString(AVStatus.MESSAGE_TAG));
                    str = "CN.22.2038349";
                }
                if (TextUtils.isEmpty(str)) {
                    str = "CN.22.2038349";
                }
                AVUser currentUser = AVUser.getCurrentUser();
                if (currentUser != null) {
                    currentUser.setGeoCode(str);
                    AVUser.saveCurrentUser(currentUser);
                }
            }
        }, new Void[0]);
    }
}
