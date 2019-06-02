package com.beastbikes.android.modules.user.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog.Builder;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.ble.C1602a;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p097b.C1623g;
import com.beastbikes.android.ble.ui.p098a.C1740p;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.dialog.C1803j;
import com.beastbikes.android.dialog.C1805k;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.ui.record.CyclingCompletedActivity;
import com.beastbikes.android.modules.cycling.club.dto.RecordInfo;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.p152b.C2394a;
import com.beastbikes.android.modules.user.p153c.C2408a;
import com.beastbikes.android.modules.user.view.C2526a;
import com.beastbikes.android.modules.user.view.RecordMenuActionProvider;
import com.beastbikes.android.modules.user.view.RecordMenuActionProvider.C2525a;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.p081b.C2409c;
import com.beastbikes.android.widget.p081b.C2611f;
import com.beastbikes.android.widget.p081b.C2613i;
import com.beastbikes.android.widget.p081b.C2617k;
import com.beastbikes.android.widget.p081b.C2617k.C2616a;
import com.beastbikes.android.widget.stickylistlibrary.p156a.C2755d;
import com.beastbikes.framework.android.p082a.p083a.C1457a;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.squareup.picasso.Picasso;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.apache.commons.cli.HelpFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903169)
@C1457a(a = "骑行记录列表页")
public class CyclingRecordActivity extends SessionFragmentActivity implements OnSharedPreferenceChangeListener, OnClickListener, C1623g, C2525a, C2526a, C2611f<ActivityDTO>, C2616a {
    /* renamed from: a */
    private static final Logger f6313a = LoggerFactory.getLogger("CyclingRecordActivity");
    /* renamed from: b */
    private CyclingRecordActivity$c f6314b;
    /* renamed from: c */
    private C2621c f6315c;
    @C1458a(a = 2131755881)
    /* renamed from: d */
    private LinearLayout f6316d;
    @C1458a(a = 2131755882)
    /* renamed from: e */
    private TextView f6317e;
    @C1458a(a = 2131755879)
    /* renamed from: f */
    private RelativeLayout f6318f;
    @C1458a(a = 2131755880)
    /* renamed from: g */
    private TextView f6319g;
    /* renamed from: h */
    private C2617k f6320h;
    /* renamed from: i */
    private String f6321i;
    /* renamed from: j */
    private boolean f6322j;
    /* renamed from: k */
    private boolean f6323k;
    /* renamed from: l */
    private boolean f6324l;
    /* renamed from: m */
    private String f6325m;
    /* renamed from: n */
    private String f6326n = null;
    /* renamed from: o */
    private String f6327o = "";
    /* renamed from: p */
    private C1802i f6328p;
    /* renamed from: q */
    private C1602a f6329q;
    /* renamed from: r */
    private long f6330r;
    /* renamed from: s */
    private boolean f6331s;
    /* renamed from: t */
    private CyclingRecordActivity$b f6332t;
    /* renamed from: u */
    private SharedPreferences f6333u;
    /* renamed from: v */
    private C2394a f6334v;
    /* renamed from: w */
    private RecordMenuActionProvider f6335w;

    /* renamed from: com.beastbikes.android.modules.user.ui.CyclingRecordActivity$a */
    private static final class C1442a extends C2613i<ActivityDTO> {
        @C1458a(a = 2131755905)
        /* renamed from: a */
        private ImageView f6303a;
        @C1458a(a = 2131755906)
        /* renamed from: b */
        private TextView f6304b;
        @C1458a(a = 2131755912)
        /* renamed from: c */
        private TextView f6305c;
        @C1458a(a = 2131755907)
        /* renamed from: d */
        private TextView f6306d;
        @C1458a(a = 2131755910)
        /* renamed from: e */
        private ImageView f6307e;
        @C1458a(a = 2131755909)
        /* renamed from: f */
        private ImageView f6308f;
        @C1458a(a = 2131755913)
        /* renamed from: g */
        private TextView f6309g;
        @C1458a(a = 2131755911)
        /* renamed from: h */
        private TextView f6310h;
        /* renamed from: i */
        private C2611f<ActivityDTO> f6311i;
        /* renamed from: j */
        private ActivityDTO f6312j;

        C1442a(View view, C2611f c2611f) {
            super(view);
            this.f6311i = c2611f;
        }

        /* renamed from: a */
        public void m7495a(ActivityDTO activityDTO) {
            this.f6312j = activityDTO;
            String activityUrl = activityDTO.getActivityUrl();
            if (TextUtils.isEmpty(activityUrl)) {
                Picasso.with(a()).load((int) C1373R.drawable.ic_map_loading).placeholder((int) C1373R.drawable.ic_map_loading).error((int) C1373R.drawable.ic_map_loading).fit().centerCrop().into(this.f6303a);
            } else {
                Picasso.with(a()).load(activityUrl).placeholder((int) C1373R.drawable.ic_map_loading).error((int) C1373R.drawable.ic_map_loading).fit().centerCrop().into(this.f6303a);
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd", Locale.getDefault());
            Calendar instance = Calendar.getInstance();
            long startTime = activityDTO.getStartTime();
            int i = instance.get(1);
            instance.setTime(new Date(startTime));
            int i2 = instance.get(1);
            if (i2 < i) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            }
            CharSequence title = activityDTO.getTitle();
            if (TextUtils.isEmpty(title) || title.equals("null")) {
                activityUrl = simpleDateFormat.format(new Date(startTime));
                this.f6304b.setText(activityUrl + C2408a.a(startTime));
            } else {
                this.f6304b.setText(title);
            }
            double totalDistance = activityDTO.getTotalDistance() / 1000.0d;
            if (activityDTO.getShowRepair() == 1 && TextUtils.isEmpty(activityDTO.getCentralId()) && activityDTO.getRepairDistance() != 0.0d) {
                totalDistance = activityDTO.getRepairDistance() / 1000.0d;
            }
            if (C1849a.b(a())) {
                this.f6309g.setText(C1373R.string.str_mileage_unit_km);
                this.f6305c.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(totalDistance)}));
            } else {
                this.f6309g.setText(C1373R.string.str_mileage_unit_mile);
                this.f6305c.setText(String.format(Locale.getDefault(), "%.1f", new Object[]{Double.valueOf(C1849a.a(totalDistance))}));
            }
            long abs = Math.abs(System.currentTimeMillis() - startTime);
            if (startTime <= 0) {
                this.f6306d.setVisibility(8);
            } else if (abs < 300000) {
                this.f6306d.setText(C1373R.string.feedback_activity_just_now);
            } else if (abs < 86400000) {
                this.f6306d.setText(DateUtils.getRelativeTimeSpanString(startTime));
            } else {
                simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm:ss", Locale.getDefault());
                if (i2 < i) {
                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                }
                this.f6306d.setText(simpleDateFormat.format(new Date(startTime)));
            }
            if (activityDTO.isFake()) {
                this.f6308f.setVisibility(0);
            } else {
                this.f6308f.setVisibility(8);
            }
            if (activityDTO.isSynced()) {
                this.f6307e.setVisibility(8);
            } else {
                this.f6307e.setVisibility(0);
            }
            CharSequence centralName = activityDTO.getCentralName();
            if (TextUtils.isEmpty(centralName) || centralName.equals("null")) {
                this.f6310h.setVisibility(4);
                return;
            }
            this.f6310h.setVisibility(0);
            this.f6310h.setText(centralName);
        }

        public void onClick(View view) {
            if (this.f6311i != null) {
                this.f6311i.b(this.f6312j);
            }
        }

        public boolean onLongClick(View view) {
            if (this.f6311i != null) {
                this.f6311i.a(this.f6312j);
            }
            return true;
        }
    }

    /* renamed from: a */
    public /* synthetic */ void m7516a(Object obj) {
        m7522b((ActivityDTO) obj);
    }

    /* renamed from: b */
    public /* synthetic */ void m7523b(Object obj) {
        m7514a((ActivityDTO) obj);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_right, C1373R.anim.activity_none);
        Intent intent = getIntent();
        if (intent != null) {
            this.f6324l = intent.getBooleanExtra("from_club", false);
            this.f6321i = intent.getStringExtra("avatar_url");
            this.f6325m = intent.getStringExtra("nick_name");
            this.f6326n = intent.getStringExtra("central_id");
            this.f6322j = intent.getBooleanExtra("refresh", true);
            this.f6327o = intent.getStringExtra("device_name");
            this.f6330r = intent.getLongExtra("filter_day", 0);
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setDisplayHomeAsUpEnabled(true);
            }
            this.f6334v = new C2394a(this);
            this.f6314b = new CyclingRecordActivity$c(null);
            this.f6314b.a(this);
            this.f6320h = new C2617k(this, this.f6316d);
            this.f6320h.a(new C2755d(this.f6314b));
            this.f6320h.setAdapter(this.f6314b);
            this.f6320h.setRecyclerCallBack(this);
            this.f6316d.addView(this.f6320h);
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                Object p = m5331p();
                if (!TextUtils.isEmpty(p) && p.equals(currentUser.getObjectId())) {
                    this.f6323k = true;
                }
            }
            if (this.f6330r == 0) {
                this.f6334v.b();
            } else {
                Calendar instance = Calendar.getInstance();
                instance.setTimeInMillis(this.f6330r);
                int i = instance.get(1);
                int i2 = instance.get(2);
                int i3 = instance.get(5);
                instance.set(i, i2, i3, 0, 0, 0);
                long timeInMillis = instance.getTimeInMillis() / 1000;
                instance.set(i, i2, i3, 24, 0, 0);
                this.f6334v.a(timeInMillis, instance.getTimeInMillis() / 1000);
            }
            if (C1661h.a().d(this.f6326n)) {
                m7500a(this.f6326n, this.f6327o);
            }
            this.f6333u = getSharedPreferences(m5331p(), 0);
            this.f6333u.registerOnSharedPreferenceChangeListener(this);
            m7511k();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.f6323k) {
            getMenuInflater().inflate(C1373R.menu.record_filter_menu, menu);
            this.f6335w = (RecordMenuActionProvider) MenuItemCompat.getActionProvider(menu.findItem(C1373R.id.menu_item_record_filter));
            this.f6335w.a(this);
            if (this.f6330r > 0) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
                this.f6335w.a(C1373R.string.str_filter_by_time);
                this.f6335w.a(simpleDateFormat.format(new Date(this.f6330r)) + HelpFormatter.DEFAULT_OPT_PREFIX + simpleDateFormat.format(new Date(this.f6330r)));
            }
        }
        return true;
    }

    /* renamed from: a */
    public void m7512a() {
        new C1803j(this, this).show();
    }

    /* renamed from: b */
    public void m7520b() {
        this.f6334v.c();
        this.f6335w.a(C1373R.string.str_add_data);
        this.f6335w.a("");
    }

    /* renamed from: c */
    public void m7524c() {
        this.f6334v.a();
    }

    /* renamed from: d */
    public void m7525d() {
        startActivityForResult(new Intent(this, CyclingRecordFilterTimeActivity.class), 37);
    }

    /* renamed from: a */
    public void m7517a(ArrayList<String> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            C1805k c1805k = new C1805k(this, new CyclingRecordActivity$1(this));
            c1805k.show();
            c1805k.a(arrayList);
            c1805k.b((String) arrayList.get(0));
        }
    }

    /* renamed from: k */
    private void m7511k() {
        if (VERSION.SDK_INT >= 18 && !TextUtils.isEmpty(this.f6326n)) {
            this.f6332t = new CyclingRecordActivity$b(this);
            CyclingRecordActivity$b.a(this.f6332t);
        }
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (!TextUtils.isEmpty(str) && str.equals("beast.cycling.repair.user.id")) {
            String string = sharedPreferences.getString(str, "");
            if (string.equals(m5331p())) {
                f6313a.info("接受到数据修复的push， userId = " + string);
                m7526e();
                sharedPreferences.edit().remove(str).commit();
            }
        }
    }

    protected void onDestroy() {
        this.f6334v.f();
        this.f6334v = null;
        super.onDestroy();
        this.f6333u.unregisterOnSharedPreferenceChangeListener(this);
        if (this.f6332t != null) {
            CyclingRecordActivity$b.b(this.f6332t);
        }
    }

    public void finish() {
        Intent intent = getIntent();
        if (intent != null) {
            intent.putExtra("refresh", this.f6322j);
            if (this.f6331s) {
                intent.putExtra("sync_activity", true);
            }
            setResult(-1, intent);
        }
        super.finish();
        super.overridePendingTransition(0, C1373R.anim.activity_out_to_right);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 273 && i2 == 2) {
            m7526e();
        }
        if (i == 37 && i2 == -1) {
            long longExtra = intent.getLongExtra("start_date", 0);
            long longExtra2 = intent.getLongExtra("end_date", 0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
            this.f6335w.a(C1373R.string.str_filter_by_time);
            this.f6335w.a(simpleDateFormat.format(new Date(longExtra * 1000)) + HelpFormatter.DEFAULT_OPT_PREFIX + simpleDateFormat.format(new Date(longExtra2 * 1000)));
            this.f6334v.a(longExtra, longExtra2);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.activity_record_unsync_rela:
                C1740p c1740p = new C1740p();
                Bundle bundle = new Bundle();
                bundle.putInt("sync_type", 0);
                bundle.putString("central_id", this.f6326n);
                c1740p.a(this.f6329q);
                c1740p.setArguments(bundle);
                c1740p.show(getFragmentManager(), "SYNC_DATA");
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m7513a(int i) {
    }

    /* renamed from: b */
    public void m7521b(int i) {
        switch (i) {
            case 0:
                this.f6331s = true;
                this.f6318f.setVisibility(8);
                m7526e();
                return;
            default:
                return;
        }
    }

    /* renamed from: e */
    public void m7526e() {
        this.f6334v.d();
    }

    /* renamed from: f */
    public void m7527f() {
        this.f6334v.e();
    }

    /* renamed from: a */
    public void m7519a(boolean z) {
        this.f6320h.a(z);
    }

    /* renamed from: a */
    public void m7514a(ActivityDTO activityDTO) {
        if (activityDTO != null) {
            if (this.f6324l && this.f6323k) {
                Serializable recordInfo = new RecordInfo(activityDTO);
                Intent intent = getIntent();
                intent.putExtra("record_info", recordInfo);
                setResult(-1, intent);
                finish();
                return;
            }
            Intent intent2 = new Intent(this, CyclingCompletedActivity.class);
            intent2.putExtra("user_id", m5331p());
            intent2.putExtra("avatar_url", this.f6321i);
            intent2.putExtra("nick_name", this.f6325m);
            intent2.putExtra("activity_dto", activityDTO);
            intent2.putExtra("sport_identify", activityDTO.getActivityIdentifier());
            startActivityForResult(intent2, 273);
        }
    }

    /* renamed from: b */
    public void m7522b(ActivityDTO activityDTO) {
        if (this.f6323k && activityDTO != null) {
            this.f6315c = new C2621c(this);
            this.f6315c.b(true);
            View inflate = LayoutInflater.from(this).inflate(C1373R.layout.activity_record_delete_dialog, null);
            ((TextView) inflate.findViewById(C1373R.id.delete_dialog_delete_item)).setOnClickListener(new CyclingRecordActivity$2(this, activityDTO));
            this.f6315c.a(inflate);
            this.f6315c.a();
        }
    }

    /* renamed from: a */
    public void m7515a(ActivityDTO activityDTO, boolean z) {
        if (this.f6328p != null && this.f6328p.isShowing()) {
            this.f6328p.dismiss();
        }
        if (z) {
            this.f6314b.a(activityDTO);
        }
    }

    /* renamed from: g */
    public CyclingRecordActivity m7528g() {
        return this;
    }

    /* renamed from: h */
    public String m7529h() {
        return m5331p();
    }

    /* renamed from: i */
    public String m7530i() {
        return this.f6326n;
    }

    /* renamed from: a */
    public void m7518a(List<C2409c> list) {
        if (list == null || list.isEmpty()) {
            NetworkInfo a = C2799c.a(this);
            if (a == null || !a.isConnected()) {
                Toasts.show(this, C1373R.string.activity_unnetwork_err);
            } else {
                Toasts.show(this, C1373R.string.activity_record_no_more);
            }
            this.f6317e.setVisibility(0);
            if (TextUtils.isEmpty(this.f6327o)) {
                this.f6327o = "";
            }
            this.f6317e.setText(this.f6327o + "\r\n" + getString(C1373R.string.activity_record_activity_no_record));
        } else {
            this.f6317e.setVisibility(8);
        }
        this.f6314b.a(list);
        this.f6320h.a();
    }

    /* renamed from: c */
    private void m7503c(ActivityDTO activityDTO) {
        if (activityDTO != null) {
            new Builder(this).setMessage(getString(C1373R.string.dialog_sure_or_delete)).setPositiveButton(getString(C1373R.string.str_ok), new CyclingRecordActivity$3(this, activityDTO)).setNegativeButton(getString(C1373R.string.str_cancel), null).create().show();
        }
    }

    /* renamed from: a */
    private void m7500a(String str, String str2) {
        getAsyncTaskQueue().a(new CyclingRecordActivity$4(this, str, str2), new String[0]);
    }
}
