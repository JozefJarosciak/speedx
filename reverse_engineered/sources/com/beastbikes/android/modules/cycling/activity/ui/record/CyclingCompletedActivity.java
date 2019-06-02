package com.beastbikes.android.modules.cycling.activity.ui.record;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory.Options;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1800h;
import com.beastbikes.android.dialog.C1800h.C1799a;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.SyncService;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.activity.ui.record.C1986e.C1941a;
import com.beastbikes.android.modules.cycling.activity.ui.record.p119h.C1957a;
import com.beastbikes.android.modules.cycling.activity.ui.record.p126g.C1998a;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.C2030e;
import com.beastbikes.android.modules.map.C2296d.C1950e;
import com.beastbikes.android.modules.map.C2296d.C1958b;
import com.beastbikes.android.modules.preferences.ui.BaseEditTextActivity;
import com.beastbikes.android.modules.user.dao.entity.LocalUser;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.dto.C2411a;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.modules.user.ui.ActivityComplainActivity;
import com.beastbikes.android.modules.user.ui.WatermarkCameraActivity;
import com.beastbikes.android.utils.C2553b;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.p168e.C2655d;
import com.beastbikes.android.widget.p168e.p169a.C2641b;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CyclingCompletedActivity extends CyclingCompletedBase implements OnSharedPreferenceChangeListener, C1799a, C1957a, C1958b {
    /* renamed from: j */
    private AVUser f8774j = AVUser.getCurrentUser();
    /* renamed from: k */
    private C1398a f8775k;
    /* renamed from: l */
    private ActivityDTO f8776l;
    /* renamed from: m */
    private String f8777m;
    /* renamed from: n */
    private String f8778n;
    /* renamed from: o */
    private C2655d f8779o;
    /* renamed from: p */
    private C2641b f8780p;
    /* renamed from: q */
    private List<String> f8781q = new ArrayList();
    /* renamed from: r */
    private String f8782r;
    /* renamed from: s */
    private C1800h f8783s;
    /* renamed from: t */
    private C1998a f8784t;
    /* renamed from: u */
    private SharedPreferences f8785u;
    /* renamed from: v */
    private Bitmap f8786v;
    /* renamed from: w */
    private int f8787w = 0;
    /* renamed from: x */
    private String f8788x;
    /* renamed from: y */
    private FrameLayout f8789y;

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedActivity$6 */
    class C19476 extends AsyncTask<String, Void, Boolean> {
        /* renamed from: a */
        final /* synthetic */ CyclingCompletedActivity f8749a;

        C19476(CyclingCompletedActivity cyclingCompletedActivity) {
            this.f8749a = cyclingCompletedActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m9990a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m9991a((Boolean) obj);
        }

        /* renamed from: a */
        protected Boolean m9990a(String... strArr) {
            return Boolean.valueOf(this.f8749a.f8775k.b(strArr[0], strArr[1]));
        }

        /* renamed from: a */
        protected void m9991a(Boolean bool) {
            if (bool.booleanValue()) {
                Toasts.show(this.f8749a, (int) C1373R.string.toast_update_success);
            } else {
                Toasts.show(this.f8749a, (int) C1373R.string.toast_update_error);
            }
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedActivity$7 */
    class C19487 extends AsyncTask<String, Void, Integer> {
        /* renamed from: a */
        final /* synthetic */ CyclingCompletedActivity f8750a;

        C19487(CyclingCompletedActivity cyclingCompletedActivity) {
            this.f8750a = cyclingCompletedActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m9992a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m9993a((Integer) obj);
        }

        /* renamed from: a */
        protected Integer m9992a(String... strArr) {
            try {
                LocalActivity b = this.f8750a.f8775k.b(this.f8750a.f8776l.getActivityIdentifier());
                if (b != null) {
                    return Integer.valueOf(this.f8750a.f8775k.a(b));
                }
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            return Integer.valueOf(-1);
        }

        /* renamed from: a */
        protected void m9993a(Integer num) {
            if (num.intValue() == 0) {
                Toasts.show(this.f8750a, (int) C1373R.string.setting_fragment_item_upload_error_log_success);
            } else {
                Toasts.show(this.f8750a, (int) C1373R.string.setting_fragment_item_upload_error);
            }
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedActivity$8 */
    class C19498 extends AsyncTask<String, Void, Boolean> {
        /* renamed from: a */
        final /* synthetic */ CyclingCompletedActivity f8751a;

        C19498(CyclingCompletedActivity cyclingCompletedActivity) {
            this.f8751a = cyclingCompletedActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m9994a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m9995a((Boolean) obj);
        }

        protected void onPreExecute() {
            this.f8751a.m10012a(true, null);
        }

        /* renamed from: a */
        protected Boolean m9994a(String... strArr) {
            try {
                return Boolean.valueOf(this.f8751a.f8775k.e(this.f8751a.f8776l.getActivityId(), strArr[0]));
            } catch (BusinessException e) {
                return Boolean.valueOf(false);
            }
        }

        /* renamed from: a */
        protected void m9995a(Boolean bool) {
            this.f8751a.m10023k();
            if (!(this.f8751a.isFinishing() || this.f8751a.f8783s == null)) {
                this.f8751a.f8783s.dismiss();
            }
            this.f8751a.f8776l.setHasReport(true);
            this.f8751a.m10020d(this.f8751a.f8776l);
        }
    }

    /* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedActivity$9 */
    class C19519 implements C1950e {
        /* renamed from: a */
        final /* synthetic */ CyclingCompletedActivity f8752a;

        C19519(CyclingCompletedActivity cyclingCompletedActivity) {
            this.f8752a = cyclingCompletedActivity;
        }

        /* renamed from: a */
        public void mo3296a(Bitmap bitmap) {
            this.f8752a.f8786v = bitmap;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null || this.f8774j == null) {
            finish();
            return;
        }
        this.f8775k = new C1398a(this);
        this.f8777m = getIntent().getStringExtra("sport_identify");
        this.f8788x = getIntent().getStringExtra("user_id");
        this.f8784t = new C1998a(this, this);
        if (intent.getSerializableExtra("activity_dto") != null) {
            this.f8776l = (ActivityDTO) intent.getSerializableExtra("activity_dto");
            this.f8784t.m10305b(this.f8776l);
            m10011a(true, this.f8776l, TextUtils.equals(AVUser.getCurrentUser().getObjectId(), p()));
        }
        m10022j();
        this.c.setOnMapChangeFinishedListener(this);
        this.f8784t.m10301a(this.f8776l);
        this.f8785u = getSharedPreferences(p(), 0);
        this.f8785u.registerOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (this.f8776l != null && !TextUtils.isEmpty(str) && str.equals("beast.cycling.repair.activity.id")) {
            String string = sharedPreferences.getString(str, "");
            if (string.equals(this.f8776l.getActivityIdentifier())) {
                a.info("接受到数据修复完成的Push，activityId ＝ " + string);
                this.f8784t.m10302a(string);
                Toasts.show((Context) this, (int) C1373R.string.str_cycling_record_repaired);
                sharedPreferences.edit().remove(str).apply();
            }
        }
    }

    public void onDestroy() {
        if (this.f8785u != null) {
            this.f8785u.unregisterOnSharedPreferenceChangeListener(this);
        }
        if (!(this.f8786v == null || this.f8786v.isRecycled())) {
            this.f8786v.recycle();
            this.f8786v = null;
        }
        m10027o();
        super.onDestroy();
    }

    /* renamed from: a */
    public List<C2411a> mo3303a() {
        return this.f8784t.m10300a();
    }

    /* renamed from: a */
    public void mo3297a(int i) {
        super.mo3297a(i);
        switch (i) {
            case C1373R.id.record_side_btn_switch:
                List a = this.f8784t.m10300a();
                if (a != null && a.size() > 0) {
                    this.c.m11669a(a, (this.c.getmCoordinateType() + 1) % 3);
                    return;
                }
                return;
            case C1373R.id.record_side_btn_visible:
                C2580w.m12905a(this, "设置地图私密", "setting_cycling_record_private");
                if (this.g) {
                    this.g = false;
                    this.f8776l.setIsPrivate(0);
                    m10066f(0);
                    this.c.m11670a(this.g);
                    return;
                } else if (this.i.getBoolean("com.beastbikes.android.set_map_private_first", true)) {
                    m10070s();
                    return;
                } else {
                    this.g = true;
                    this.f8776l.setIsPrivate(1);
                    m10066f(1);
                    this.c.m11670a(this.g);
                    return;
                }
            default:
                return;
        }
    }

    /* renamed from: b */
    public void mo3300b(int i) {
        super.mo3300b(i);
        switch (i) {
            case C1373R.id.action_bar_tools_repair:
                m10074w();
                return;
            case C1373R.id.action_bar_tools_upload:
                if (SyncService.f4475a) {
                    a.error("正在后台上传数据");
                    return;
                }
                C2580w.m12905a(this, "", "save_ridding_goal");
                m10071t();
                return;
            case C1373R.id.action_bar_tools_camera:
                m10061b(false);
                C2580w.m12905a(this, "分享水印相机", "click_ridding_history_share_digital_watermarking");
                return;
            case C1373R.id.action_bar_tools_share:
                m10073v();
                return;
            case C1373R.id.action_bar_cheat_report:
            case C1373R.id.action_bar_tip_cheat:
                m10069r();
                return;
            case C1373R.id.action_bar_tools_report:
                if (this.f8776l != null && !this.f8776l.isHasReport()) {
                    this.f8783s = new C1800h(this, null, getString(C1373R.string.activity_complete_activity_report_hint), this, 70, false, false);
                    this.f8783s.show();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: c */
    public void mo3301c(int i) {
        super.mo3301c(i);
        if (i == C1373R.id.summary_cycling_edit) {
            Intent intent = new Intent(this, BaseEditTextActivity.class);
            intent.putExtra("value", this.f8776l.getTitle());
            startActivityForResult(intent, 11);
            C2580w.m12905a(this, "更改骑行记录名称", "edit_cycling_record_title");
        }
    }

    /* renamed from: b */
    public void mo3314b(String str) {
        m10068g(str);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        switch (i2) {
            case -1:
                String string;
                int i3;
                switch (i) {
                    case 11:
                        string = intent.getExtras().getString("value");
                        if (!TextUtils.isEmpty(string) && !this.f8776l.getTitle().equals(string) && this.f8776l != null) {
                            this.f8776l.setTitle(string);
                            m10018c(this.f8776l);
                            m10060b(this.f8776l.getActivityIdentifier(), string);
                            return;
                        }
                        return;
                    case 12:
                        string = intent.getStringExtra("path");
                        if (!TextUtils.isEmpty(string) && new File(string).exists()) {
                            this.f8778n = string;
                            new Options().inSampleSize = 4;
                            this.f8781q.clear();
                            this.f8781q.add(this.f8778n);
                            JSONArray jSONArray = new JSONArray();
                            for (i3 = 0; i3 < this.f8781q.size(); i3++) {
                                JSONObject jSONObject = new JSONObject();
                                try {
                                    jSONObject.put("filePath0", this.f8781q.get(i3));
                                    jSONArray.put(jSONObject);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            if (jSONArray.length() > 0 && !TextUtils.isEmpty(this.f8777m)) {
                                try {
                                    LocalActivity b = this.f8775k.b(this.f8777m);
                                    if (b != null) {
                                        this.f8775k.b(b);
                                        a.trace("update local activity local scenery url " + jSONArray.toString());
                                        return;
                                    }
                                    return;
                                } catch (BusinessException e2) {
                                    e2.printStackTrace();
                                    return;
                                }
                            }
                            return;
                        }
                        return;
                    case 13:
                        i3 = intent.getIntExtra("edit_activity", -1);
                        if (i3 == 0) {
                            m10061b(true);
                        }
                        if (i3 == 1) {
                            m10087b();
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

    /* renamed from: a */
    public void mo3299a(String str) {
        super.mo3299a(str);
        if (!TextUtils.isEmpty(str)) {
            C2641b c2641b = new C2641b();
            c2641b.m13156a(str);
            this.f8779o = new C2655d((BaseFragmentActivity) this, c2641b, "数据模版");
            if (this.f8789y == null) {
                this.f8789y = new FrameLayout(this);
            }
            this.f8779o.showAtLocation(this.f8789y, 81, 0, 0);
        }
    }

    /* renamed from: r */
    private void m10069r() {
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser == null || currentUser.getObjectId().equals(p())) {
            final C2621c c2621c = new C2621c(this);
            c2621c.m13060a(LayoutInflater.from(this).inflate(C1373R.layout.activity_finished_cheat_dialog, null));
            c2621c.m13058a((int) C1373R.string.activity_finished_activity_cheat_title);
            c2621c.m13059a((int) C1373R.string.activity_finished_activity_complain, new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ CyclingCompletedActivity f8740b;

                public void onClick(View view) {
                    Intent intent = new Intent(this.f8740b, ActivityComplainActivity.class);
                    intent.putExtra("user_id", this.f8740b.p());
                    if (this.f8740b.f8776l != null) {
                        intent.putExtra("activity_id", this.f8740b.f8776l.getActivityId());
                    }
                    this.f8740b.startActivity(intent);
                    c2621c.m13069b();
                }
            });
            c2621c.m13066b((int) C1373R.string.label_i_know, new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ CyclingCompletedActivity f8744b;

                public void onClick(View view) {
                    c2621c.m13069b();
                }
            });
            c2621c.m13063a();
        }
    }

    /* renamed from: s */
    private void m10070s() {
        final C2621c c2621c = new C2621c(this);
        c2621c.m13058a((int) C1373R.string.activity_finished_activity_set_map_private);
        c2621c.m13067b((CharSequence) "");
        c2621c.m13059a((int) C1373R.string.label_i_know, new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ CyclingCompletedActivity f8746b;

            public void onClick(View view) {
                int i = 0;
                this.f8746b.i.edit().putBoolean("com.beastbikes.android.set_map_private_first", false).apply();
                this.f8746b.g = true;
                ActivityDTO b = this.f8746b.f8776l;
                if (this.f8746b.g) {
                    i = 1;
                }
                b.setIsPrivate(i);
                this.f8746b.m10066f(1);
                this.f8746b.c.m11670a(this.f8746b.g);
                c2621c.m13069b();
            }
        });
        c2621c.m13063a();
    }

    /* renamed from: f */
    private void m10066f(final int i) {
        setResult(2);
        this.c.m11676d();
        this.c.m11677e();
        m10022j();
        m10021e(this.f8776l);
        getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, Integer>(this) {
            /* renamed from: b */
            final /* synthetic */ CyclingCompletedActivity f8748b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m9989a((String[]) objArr);
            }

            /* renamed from: a */
            protected Integer m9989a(String... strArr) {
                try {
                    this.f8748b.f8775k.a(this.f8748b.f8776l.getActivityIdentifier(), i);
                } catch (BusinessException e) {
                    e.printStackTrace();
                }
                return Integer.valueOf(-1);
            }
        }, new String[0]);
    }

    /* renamed from: b */
    private void m10060b(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            getAsyncTaskQueue().m13740a(new C19476(this), str, str2);
        }
    }

    /* renamed from: t */
    private void m10071t() {
        if (this.f8776l != null) {
            getAsyncTaskQueue().m13740a(new C19487(this), new String[0]);
        }
    }

    /* renamed from: b */
    private void m10061b(boolean z) {
        Intent intent = new Intent(this, WatermarkCameraActivity.class);
        String str = "";
        String str2 = "";
        try {
            LocalUser a = new C2389c((Activity) this).m12118a(p());
            if (a != null) {
                str = a.getNickname();
                str2 = a.getCity();
            }
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        if (this.f8776l != null) {
            this.f8776l.setNickname(str);
            this.f8776l.setCityName(str2);
            if (!TextUtils.isEmpty(this.f8782r)) {
                this.f8776l.setCityName(this.f8782r);
            }
            intent.putExtra("activity_dto", this.f8776l);
        }
        if (z) {
            startActivityForResult(intent, 12);
        } else {
            startActivity(intent);
        }
    }

    /* renamed from: b */
    public void m10087b() {
        if (!TextUtils.isEmpty(this.f8777m)) {
            try {
                this.f8775k.b(this.f8775k.b(this.f8777m));
                this.f8778n = "";
            } catch (BusinessException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: g */
    private void m10068g(String str) {
        if (!TextUtils.isEmpty(this.f8776l.getActivityId())) {
            getAsyncTaskQueue().m13740a(new C19498(this), str);
        }
    }

    /* renamed from: u */
    private void m10072u() {
        this.c.m11665a(new C19519(this));
    }

    /* renamed from: v */
    private void m10073v() {
        NetworkInfo a = C2799c.m13753a(this);
        if (a == null || !a.isConnected()) {
            Toast.makeText(this, C1373R.string.account_failure, 0).show();
            return;
        }
        if (this.f8780p != null) {
            this.f8779o = new C2655d((BaseFragmentActivity) this, this.f8780p, "数据模版");
            if (this.f8789y == null) {
                this.f8789y = new FrameLayout(this);
            }
            this.f8779o.showAtLocation(this.f8789y, 81, 0, 0);
        } else if (this.f8786v != null) {
            Bitmap summmaryBitmap = ((C2030e) this.f.get(0)).getSummmaryBitmap();
            ArrayList n = m10026n();
            if (n != null && !n.isEmpty()) {
                View c1986e = new C1986e(this);
                this.b.removeAllViews();
                this.b.addView(c1986e);
                c1986e.m10227a(summmaryBitmap, this.f8786v, n, new C1941a(this) {
                    /* renamed from: a */
                    final /* synthetic */ CyclingCompletedActivity f8738a;

                    {
                        this.f8738a = r1;
                    }

                    /* renamed from: a */
                    public void mo3295a(Bitmap bitmap) {
                        this.f8738a.m10056a(bitmap);
                    }
                });
            } else {
                return;
            }
        } else {
            return;
        }
        C2580w.m12905a(this, "分享数据模版", "click_ridding_history_share_data_report");
    }

    /* renamed from: a */
    private void m10056a(final Bitmap bitmap) {
        if (bitmap != null) {
            getAsyncTaskQueue().m13740a(new AsyncTask<Void, Void, String>(this) {
                /* renamed from: b */
                final /* synthetic */ CyclingCompletedActivity f8742b;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m9987a((Void[]) objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m9988a((String) obj);
                }

                protected void onPreExecute() {
                    this.f8742b.m10012a(true, this.f8742b.getString(C1373R.string.activity_complete_activity_create_share_loading));
                }

                /* renamed from: a */
                protected String m9987a(Void... voidArr) {
                    return C2553b.m12786b(bitmap);
                }

                /* renamed from: a */
                protected void m9988a(String str) {
                    this.f8742b.m10023k();
                    if (!TextUtils.isEmpty(str)) {
                        this.f8742b.f8780p = new C2641b();
                        this.f8742b.f8780p.m13156a(str);
                        this.f8742b.f8779o = new C2655d(this.f8742b, this.f8742b.f8780p, "数据模版");
                        if (this.f8742b.f8789y == null) {
                            this.f8742b.f8789y = new FrameLayout(this.f8742b);
                        }
                        this.f8742b.f8779o.showAtLocation(this.f8742b.f8789y, 81, 0, 0);
                    }
                }
            }, new Void[0]);
        }
    }

    /* renamed from: w */
    private void m10074w() {
        boolean z = false;
        if (this.f8776l != null && this.f8776l.getShowRepair() != 2) {
            boolean z2;
            this.f8776l.setShowRepair(this.f8776l.getShowRepair() == 1 ? 0 : 1);
            C1998a c1998a = this.f8784t;
            if (this.f8776l.getShowRepair() == 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            c1998a.m10304a(z2);
            C1998a c1998a2 = this.f8784t;
            String activityIdentifier = this.f8776l.getActivityIdentifier();
            if (this.f8776l.getShowRepair() == 1) {
                z = true;
            }
            c1998a2.m10303a(activityIdentifier, z);
            m10020d(this.f8776l);
            m10018c(this.f8776l);
            setResult(2);
            if (this.f8776l.getShowRepair() == 1) {
                Toasts.show((Context) this, (int) C1373R.string.str_navigation_already_repair_cycling_data);
            } else {
                Toasts.show((Context) this, (int) C1373R.string.str_cycling_record_already_recovery_cycling_data);
            }
        }
    }

    /* renamed from: c */
    public String mo3316c() {
        if (TextUtils.isEmpty(this.f8788x)) {
            return p();
        }
        return this.f8788x;
    }

    /* renamed from: d */
    public String mo3319d() {
        return this.f8777m;
    }

    /* renamed from: e */
    public String mo3321e() {
        return getIntent().getStringExtra("avatar_url");
    }

    /* renamed from: f */
    public String mo3323f() {
        return getIntent().getStringExtra("nick_name");
    }

    /* renamed from: c */
    public void mo3317c(String str) {
        this.f8782r = str;
    }

    /* renamed from: a */
    public void mo3312a(boolean z) {
        this.f8759g = z;
    }

    /* renamed from: a */
    public void mo3310a(ArrayList<Double> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (C1849a.m9645b((Context) this)) {
                m10005a(((Double) Collections.max(arrayList)).doubleValue(), (List) arrayList);
            } else {
                m10005a(C1849a.m9648d(((Double) Collections.max(arrayList)).doubleValue()), C1849a.m9640a((ArrayList) arrayList));
            }
        }
    }

    /* renamed from: b */
    public void mo3315b(ArrayList<Double> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            if (C1849a.m9645b((Context) this)) {
                m10010a((List) arrayList, ((Double) Collections.max(arrayList)).doubleValue(), ((Double) Collections.min(arrayList)).doubleValue());
                return;
            }
            m10010a(C1849a.m9643b((ArrayList) arrayList), ((Double) Collections.max(arrayList)).doubleValue(), ((Double) Collections.min(arrayList)).doubleValue());
        }
    }

    /* renamed from: a */
    public void mo3305a(ActivityDTO activityDTO, int i, ArrayList<Double> arrayList) {
        m10008a(activityDTO, i, (List) arrayList);
    }

    /* renamed from: a */
    public void mo3307a(ActivityDTO activityDTO, List<C2411a> list, ArrayList<C2411a> arrayList) {
        m10015b(activityDTO, list, arrayList);
    }

    /* renamed from: c */
    public void mo3318c(ArrayList<Double> arrayList) {
        m10016b((List) arrayList);
    }

    /* renamed from: a */
    public void mo3306a(ActivityDTO activityDTO, ArrayList<Double> arrayList) {
        m10014b(activityDTO, arrayList);
    }

    /* renamed from: a */
    public void mo3304a(ActivityDTO activityDTO) {
        this.f8776l = activityDTO;
    }

    /* renamed from: g */
    public void mo3325g() {
        m10012a(true, getString(C1373R.string.str_loading));
    }

    /* renamed from: h */
    public void mo3326h() {
        m10023k();
    }

    /* renamed from: a */
    public void mo3311a(List<C2411a> list) {
        this.c.m11668a((List) list);
    }

    /* renamed from: b */
    public void mo3313b(ActivityDTO activityDTO) {
        m10018c(activityDTO);
    }

    /* renamed from: a */
    public void mo3308a(ActivityDTO activityDTO, boolean z) {
        m10011a(false, activityDTO, z);
    }

    /* renamed from: d */
    public void mo3320d(String str) {
        a.error("get user information failed!");
    }

    /* renamed from: e */
    public void mo3322e(String str) {
        a.error("get activity information failed!");
    }

    /* renamed from: f */
    public void mo3324f(String str) {
        a.error("get activity information failed!");
    }

    /* renamed from: a */
    public void mo3309a(String str, String str2) {
        a.error(str, str2);
    }

    /* renamed from: i */
    public void mo3327i() {
        this.f8787w++;
        if (this.f8787w == 1) {
            m10072u();
        }
    }
}
