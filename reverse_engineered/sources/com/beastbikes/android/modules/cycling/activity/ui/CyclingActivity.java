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
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.beastbikes.android.modules.cycling.activity.ui.p117a.C1934a;
import com.beastbikes.android.modules.cycling.activity.ui.widget.C2039c;
import com.beastbikes.android.modules.cycling.activity.ui.widget.C2040d;
import com.beastbikes.android.modules.cycling.activity.ui.widget.CyclingPreviewLayoutForApp;
import com.beastbikes.android.modules.cycling.activity.util.C1921b;
import com.beastbikes.android.modules.cycling.activity.view.SegmentProgressBar;
import com.beastbikes.android.modules.preferences.ui.CyclingSettingActivity;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO.Program;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO.Stage;
import com.beastbikes.android.modules.train.p076a.C2350b;
import com.beastbikes.android.modules.train.ui.p151b.C2383b;
import com.beastbikes.android.p054a.C1529b;
import com.beastbikes.android.utils.C2557f;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.android.widget.CircleIndicator;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@C1459b(a = 2130903112)
public class CyclingActivity extends SessionFragmentActivity implements OnPageChangeListener, OnClickListener, C1371a {
    /* renamed from: c */
    private static final Logger f4525c = LoggerFactory.getLogger(CyclingActivity.class);
    /* renamed from: A */
    private int f4526A;
    /* renamed from: B */
    private TrainCourseDTO f4527B;
    /* renamed from: C */
    private int f4528C = 0;
    /* renamed from: D */
    private int[] f4529D;
    /* renamed from: E */
    private ArrayList<ArrayList<Integer>> f4530E;
    /* renamed from: F */
    private boolean f4531F;
    /* renamed from: G */
    private C2039c f4532G;
    /* renamed from: H */
    private boolean f4533H = C1849a.c();
    /* renamed from: I */
    private C2383b f4534I;
    /* renamed from: J */
    private boolean f4535J;
    /* renamed from: K */
    private Runnable f4536K = new CyclingActivity$2(this);
    /* renamed from: L */
    private final ServiceConnection f4537L = new CyclingActivity$5(this);
    /* renamed from: M */
    private final C1909b f4538M = new CyclingActivity$6(this);
    /* renamed from: a */
    Handler f4539a = new Handler();
    /* renamed from: b */
    Runnable f4540b = new CyclingActivity$3(this);
    @C1458a(a = 2131755612)
    /* renamed from: d */
    private ImageView f4541d;
    @C1458a(a = 2131755613)
    /* renamed from: e */
    private ImageView f4542e;
    @C1458a(a = 2131755614)
    /* renamed from: f */
    private ImageView f4543f;
    @C1458a(a = 2131755617)
    /* renamed from: g */
    private ImageView f4544g;
    @C1458a(a = 2131755616)
    /* renamed from: h */
    private TextView f4545h;
    @C1458a(a = 2131755623)
    /* renamed from: i */
    private ViewPager f4546i;
    @C1458a(a = 2131755610)
    /* renamed from: j */
    private SegmentProgressBar f4547j;
    @C1458a(a = 2131755624)
    /* renamed from: k */
    private CircleIndicator f4548k;
    /* renamed from: l */
    private final BroadcastReceiver f4549l = new CyclingActivity$a(this);
    /* renamed from: m */
    private C1398a f4550m;
    /* renamed from: n */
    private AlphaAnimation f4551n;
    /* renamed from: o */
    private LocalActivity f4552o;
    /* renamed from: p */
    private SharedPreferences f4553p;
    @C1458a(a = 2131755619)
    /* renamed from: q */
    private View f4554q;
    @C1458a(a = 2131755620)
    /* renamed from: r */
    private View f4555r;
    @C1458a(a = 2131755621)
    /* renamed from: s */
    private View f4556s;
    @C1458a(a = 2131755622)
    /* renamed from: t */
    private TextView f4557t;
    /* renamed from: u */
    private List<View> f4558u;
    /* renamed from: v */
    private int f4559v;
    /* renamed from: w */
    private boolean f4560w = true;
    /* renamed from: x */
    private C1921b f4561x;
    /* renamed from: y */
    private ArrayList<View> f4562y;
    /* renamed from: z */
    private C1934a f4563z;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        super.overridePendingTransition(C1373R.anim.activity_in_from_bottom, C1373R.anim.activity_none);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        LocalActivity a;
        this.f4553p = getSharedPreferences(m5331p(), 0);
        this.f4526A = intent.getIntExtra("cycling_type", 1);
        this.f4527B = (TrainCourseDTO) intent.getSerializableExtra("course_info");
        if (this.f4527B != null) {
            this.f4553p.edit().putString("train_course_information", this.f4527B.toJSON()).apply();
        }
        this.f4550m = new C1398a((Activity) this);
        LocalActivity localActivity = (LocalActivity) intent.getSerializableExtra("local_activity");
        if (localActivity == null) {
            a = this.f4550m.m5861a();
        } else {
            a = localActivity;
        }
        this.f4551n = new AlphaAnimation(0.3f, 1.0f);
        this.f4551n.setRepeatCount(-1);
        this.f4551n.setDuration(800);
        this.f4551n.setRepeatMode(2);
        this.f4535J = C1849a.b(this);
        m5953h();
        this.f4558u = new ArrayList();
        this.f4558u.add(this.f4554q);
        this.f4558u.add(this.f4555r);
        this.f4558u.add(this.f4556s);
        this.f4553p.edit().putBoolean("beast.cycling.state.check", false).apply();
        BeastBikes beastBikes = (BeastBikes) getApplication();
        if (a == null) {
            f4525c.info("LocalActivity is null");
            m5929a(0);
            this.f4542e.clearAnimation();
        } else {
            m5929a(a.getState());
            if (a.getState() == 2 || a.getState() == 3) {
                this.f4542e.startAnimation(this.f4551n);
            }
            if (beastBikes.m5259g()) {
                getWindow().addFlags(128);
            } else {
                getWindow().clearFlags(128);
            }
            this.f4552o = a;
            if (this.f4527B == null) {
                Object string = this.f4553p.getString("train_course_information", "");
                f4525c.info("Cached train course information: " + string);
                if (!TextUtils.isEmpty(string)) {
                    this.f4527B = (TrainCourseDTO) new Gson().fromJson(string, new CyclingActivity$1(this).getType());
                }
                if (!(this.f4527B == null || this.f4552o == null)) {
                    f4525c.info("TrainCourseDTO id: " + this.f4527B.getId() + " LocalActivity courseId: " + this.f4552o.getCourseId());
                    if (this.f4527B.getId() != this.f4552o.getCourseId()) {
                        this.f4553p.edit().remove("train_course_information").apply();
                        this.f4527B = null;
                        this.f4526A = 1;
                    } else {
                        this.f4526A = 2;
                    }
                }
            }
            m5930a(this.f4552o);
        }
        this.f4529D = getResources().getIntArray(C1373R.array.cycling_data_settings_value);
        m5928a();
        m5935b();
        this.f4563z.notifyDataSetChanged();
        this.f4548k.setViewPager(this.f4546i);
        m5950g();
        Intent intent2 = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
        intent2.setPackage(getPackageName());
        startService(intent2);
        bindService(intent2, this.f4537L, 1);
    }

    public void onResume() {
        super.onResume();
        try {
            m5949f();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        if (this.f4561x != null) {
            this.f4561x.c();
        }
        if (this.f4535J != C1849a.b(this)) {
            this.f4535J = C1849a.b(this);
            if (this.f4562y != null && !this.f4562y.isEmpty()) {
                Iterator it = this.f4562y.iterator();
                while (it.hasNext()) {
                    View view = (View) it.next();
                    if (view instanceof CyclingPreviewLayoutForApp) {
                        ((CyclingPreviewLayoutForApp) view).setDisplayKM(this.f4535J);
                    }
                }
            }
        }
    }

    public void onPause() {
        super.onPause();
        if (this.f4561x != null) {
            this.f4561x.d();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        unbindService(this.f4537L);
        unregisterReceiver(this.f4549l);
        this.f4553p.edit().putBoolean("beast.cycling.state.check", true).apply();
    }

    public void finish() {
        super.finish();
        super.overridePendingTransition(C1373R.anim.activity_none, C1373R.anim.activity_out_to_bottom);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.img_cycling_cycling_finish:
                LocalActivity a = this.f4550m.m5861a();
                if (a == null) {
                    m5929a(0);
                    m5955j();
                    return;
                } else if (a.getTotalDistance() <= 10.0d) {
                    CharSequence string = getString(C1373R.string.activity_state_label_finish_error_message);
                    if (this.f4527B != null) {
                        string = getString(C1373R.string.activity_state_label_train_finish_error_message);
                    }
                    C2621c c2621c = new C2621c(this);
                    c2621c.b(string).a(C1373R.string.activity_alert_dialog_text_ok, new CyclingActivity$9(this, c2621c)).b(C1373R.string.activity_alert_dialog_text_cancel, new CyclingActivity$8(this, c2621c)).a();
                    return;
                } else if (this.f4527B != null) {
                    r0 = new C2621c(this);
                    r0.b(C1373R.string.str_train_course_finish_train_msg).a(C1373R.string.activity_alert_dialog_text_ok, new CyclingActivity$11(this, r0)).b(C1373R.string.activity_alert_dialog_text_cancel, new CyclingActivity$10(this, r0)).a();
                    return;
                } else {
                    r0 = new C2621c(this);
                    r0.b(C1373R.string.label_finish_cycling_dialog_msg);
                    r0.a(C1373R.string.activity_alert_dialog_text_ok, new CyclingActivity$13(this, r0)).b(C1373R.string.activity_alert_dialog_text_cancel, new CyclingActivity$12(this, r0)).a();
                    return;
                }
            case C1373R.id.img_cycling_cycling_pause:
                m5954i();
                return;
            case C1373R.id.img_cycling_cycling_map:
                C2580w.a(this, "查看地图", "click_ridding_map");
                ActivityCompat.startActivity(this, new Intent(this, MapActivity.class), ActivityOptionsCompat.makeScaleUpAnimation(this.f4543f, this.f4543f.getWidth() / 2, this.f4543f.getHeight() / 2, 0, 0).toBundle());
                return;
            case C1373R.id.cycling_activity_hide_view:
                finish();
                return;
            case C1373R.id.cycling_activity_setting_view:
                m5325a(new Intent(this, CyclingSettingActivity.class), 16);
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    protected void mo2739b(int i, Object obj) {
        if (i == 16) {
            Intent intent = (Intent) obj;
            if (intent != null) {
                Object stringExtra = intent.getStringExtra("screen_settings_values");
                if (!TextUtils.isEmpty(stringExtra)) {
                    ArrayList arrayList = (ArrayList) new Gson().fromJson(stringExtra, new CyclingActivity$14(this).getType());
                    this.f4530E.clear();
                    if (arrayList == null || arrayList.isEmpty()) {
                        arrayList = new ArrayList();
                        arrayList.add(Integer.valueOf(2));
                        arrayList.add(Integer.valueOf(30));
                        arrayList.add(Integer.valueOf(33));
                        this.f4530E.add(arrayList);
                    } else {
                        m5934a(arrayList);
                    }
                    View view = (View) this.f4562y.get(0);
                    if (this.f4526A == 2 && (view instanceof C2040d)) {
                        stringExtra = (C2040d) view;
                    } else {
                        stringExtra = null;
                    }
                    this.f4562y.clear();
                    if (stringExtra != null) {
                        this.f4562y.add(stringExtra);
                    }
                    Iterator it = this.f4530E.iterator();
                    while (it.hasNext()) {
                        arrayList = (ArrayList) it.next();
                        if (!(arrayList == null || arrayList.isEmpty())) {
                            CyclingPreviewLayoutForApp cyclingPreviewLayoutForApp = new CyclingPreviewLayoutForApp(this);
                            cyclingPreviewLayoutForApp.a(arrayList);
                            this.f4562y.add(cyclingPreviewLayoutForApp);
                        }
                    }
                    this.f4563z.notifyDataSetChanged();
                    this.f4548k.setViewPager(this.f4546i);
                }
            }
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        if (this.f4552o != null && this.f4562y != null && !this.f4562y.isEmpty()) {
            View view = (View) this.f4562y.get(i);
            if (view instanceof C2040d) {
                m5939b(((C2040d) view).a((long) this.f4552o.getTotalElapsedTime(), this.f4552o.getRealTimePower(), this.f4552o.getRealTimeCadence()));
                this.f4547j.setVisibility(0);
                return;
            }
            ((CyclingPreviewLayoutForApp) view).a(this.f4552o);
            this.f4547j.setVisibility(8);
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (1 == i) {
            this.f4531F = true;
        } else {
            this.f4531F = false;
        }
    }

    /* renamed from: a */
    private void m5928a() {
        String string = this.f4553p.getString("key_cycling_data_settings", "");
        if (TextUtils.isEmpty(string)) {
            string = C2557f.a(this, "default_screen_settings_for_app");
            this.f4553p.edit().putString("key_cycling_data_settings", string).apply();
        }
        this.f4530E = new ArrayList();
        ArrayList arrayList = (ArrayList) new Gson().fromJson(string, new CyclingActivity$15(this).getType());
        if (arrayList != null && !arrayList.isEmpty()) {
            m5934a(arrayList);
        }
    }

    /* renamed from: b */
    private void m5935b() {
        if (!(this.f4526A != 2 || this.f4527B == null || this.f4552o == null)) {
            C2040d c2040d = new C2040d(this);
            c2040d.a(this.f4527B);
            Iterator it = this.f4527B.getPrograms().iterator();
            long j = 0;
            while (it.hasNext()) {
                Program program = (Program) it.next();
                this.f4528C += program.getStages().size() * program.getRecycleCount();
                j = ((long) program.getProgramTime()) + j;
            }
            if (this.f4552o.getTotalElapsedTime() < ((double) j)) {
                f4525c.info("(long) this.activity.getTotalElapsedTime(): " + ((long) this.f4552o.getTotalElapsedTime()));
                f4525c.info("mTrainCourseDTO: " + this.f4527B.getId());
                m5939b(c2040d.a((long) this.f4552o.getTotalElapsedTime(), this.f4552o.getRealTimePower(), this.f4552o.getRealTimeCadence()));
                this.f4562y.add(c2040d);
            }
            this.f4547j.setVisibility(0);
            this.f4547j.setProgressNum(this.f4528C);
        }
        Iterator it2 = this.f4530E.iterator();
        while (it2.hasNext()) {
            ArrayList arrayList = (ArrayList) it2.next();
            if (!(arrayList == null || arrayList.isEmpty())) {
                f4525c.info("values: " + arrayList);
                CyclingPreviewLayoutForApp cyclingPreviewLayoutForApp = new CyclingPreviewLayoutForApp(this);
                cyclingPreviewLayoutForApp.a(arrayList);
                this.f4562y.add(cyclingPreviewLayoutForApp);
            }
        }
    }

    /* renamed from: a */
    private void m5934a(ArrayList<ArrayList<Integer>> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ArrayList arrayList2 = (ArrayList) it.next();
            ArrayList arrayList3 = new ArrayList();
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                int intValue = ((Integer) it2.next()).intValue();
                if (intValue != 64) {
                    arrayList3.add(Integer.valueOf(m5926a(intValue, this.f4529D)));
                }
            }
            this.f4530E.add(arrayList3);
        }
    }

    /* renamed from: a */
    private int m5926a(int i, int[] iArr) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i == iArr[i2]) {
                return i2;
            }
        }
        return -1;
    }

    /* renamed from: b */
    private void m5939b(int[] iArr) {
        if (iArr == null) {
            f4525c.error("arr is null");
            m5941c();
            return;
        }
        f4525c.info("arr: " + iArr[0] + " " + iArr[1] + " " + iArr[2] + " " + iArr[3] + " " + iArr[4]);
        if (iArr[0] == 4) {
            Program program = (Program) this.f4527B.getPrograms().get(iArr[2]);
            if (iArr[1] == program.getStages().size() - 1 && iArr[2] == this.f4527B.getPrograms().size() - 1 && iArr[3] == program.getRecycleCount() - 1) {
                m5941c();
                return;
            } else if (iArr[1] < program.getStages().size() - 1) {
                m5933a(program, iArr[1], iArr[4]);
            } else if (program.getRecycleCount() - 1 != iArr[3]) {
                m5933a(program, -1, iArr[4]);
            } else if (iArr[2] + 1 > this.f4527B.getPrograms().size() - 1) {
                m5941c();
                return;
            } else {
                m5933a((Program) this.f4527B.getPrograms().get(iArr[2] + 1), -1, iArr[4]);
            }
        } else if (iArr[0] == 1 || iArr[0] == 2) {
            m5943c(iArr);
            m5941c();
        } else if (iArr[0] == 3) {
            this.f4547j.a();
            m5945d();
            this.f4539a.postDelayed(this.f4536K, 2000);
        }
        m5943c(iArr);
    }

    /* renamed from: c */
    private void m5943c(int[] iArr) {
        if (this.f4527B != null) {
            int i = iArr[0];
            int i2 = iArr[1];
            int i3 = iArr[2];
            int i4 = iArr[3];
            int i5 = 0;
            int i6 = 0;
            while (i5 <= i3) {
                int i7;
                Program program = (Program) this.f4527B.getPrograms().get(i5);
                if (program == null) {
                    i7 = i6;
                } else if (i5 < i3) {
                    i7 = (program.getRecycleCount() * program.getStages().size()) + i6;
                } else {
                    for (int i8 = 0; i8 <= i4; i8++) {
                        if (i8 < i4) {
                            i6 += program.getStages().size() * (i8 + 1);
                        } else if (i == 1 || i == 2) {
                            i6 += i2 + 1;
                        } else {
                            i6 += i2;
                        }
                    }
                    i7 = i6;
                }
                i5++;
                i6 = i7;
            }
            this.f4547j.setProgress(i6);
        }
    }

    /* renamed from: a */
    private void m5933a(Program program, int i, int i2) {
        if (!isFinishing()) {
            int currentItem = this.f4546i.getCurrentItem();
            if (currentItem < this.f4562y.size() && !(((View) this.f4562y.get(currentItem)) instanceof CyclingPreviewLayoutForApp)) {
                if (this.f4532G == null) {
                    this.f4532G = new C2039c(this);
                    this.f4532G.setCancelable(true);
                }
                this.f4532G.a(i2);
                this.f4532G.a(this.f4533H ? program.getName() : program.getEnName(), i + 1, program.getStages().size(), (Stage) program.getStages().get(i + 1));
                if (!this.f4532G.isShowing()) {
                    this.f4532G.show();
                }
                if (i2 == 0) {
                    m5941c();
                }
            }
        }
    }

    /* renamed from: c */
    private void m5941c() {
        if (this.f4532G != null && this.f4532G.isShowing()) {
            this.f4532G.dismiss();
        }
    }

    /* renamed from: d */
    private void m5945d() {
        int currentItem = this.f4546i.getCurrentItem();
        if (currentItem < this.f4562y.size() && !(((View) this.f4562y.get(currentItem)) instanceof CyclingPreviewLayoutForApp)) {
            if (this.f4534I == null) {
                this.f4534I = new C2383b(this);
                this.f4534I.setCancelable(true);
            }
            if (!this.f4534I.isShowing()) {
                this.f4534I.show();
            }
            this.f4547j.setVisibility(8);
        }
    }

    /* renamed from: e */
    private void m5947e() {
        if (this.f4534I != null && this.f4534I.isShowing()) {
            if (!(this.f4562y == null || this.f4562y.isEmpty() || !(((View) this.f4562y.get(0)) instanceof C2040d))) {
                this.f4553p.edit().remove("train_course_information").apply();
                this.f4562y.remove(0);
                this.f4563z.notifyDataSetChanged();
                this.f4548k.setViewPager(this.f4546i);
                this.f4527B = null;
            }
            this.f4534I.dismiss();
        }
    }

    /* renamed from: f */
    private void m5949f() {
        String[] strArr = new String[]{"android.permission.ACCESS_FINE_LOCATION"};
        if (!C1529b.a(this, strArr)) {
            C1529b.a(this, getString(C1373R.string.msg_start_cycling_get_location_permission), 12, strArr);
        } else if (this.f4560w && ActivityService.f8509a) {
            this.f4560w = false;
            this.f4559v = 0;
            this.f4539a.postDelayed(this.f4540b, 400);
            this.f4561x = new CyclingActivity$4(this, this);
        }
    }

    /* renamed from: g */
    private void m5950g() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.beastbikes.intent.action.ACTIVITY_START");
        intentFilter.addAction("com.beastbikes.intent.action.ACTIVITY_PAUSE");
        intentFilter.addAction("com.beastbikes.intent.action.ACTIVITY_AUTO_PAUSE");
        intentFilter.addAction("com.beastbikes.intent.action.ACTIVITY_RESUME");
        intentFilter.addAction("com.beastbikes.intent.action.ACTIVITY_COMPLETE");
        intentFilter.addCategory("android.intent.category.DEFAULT");
        registerReceiver(this.f4549l, intentFilter);
    }

    /* renamed from: h */
    private void m5953h() {
        this.f4562y = new ArrayList();
        this.f4563z = new C1934a(this.f4562y);
        this.f4546i.setAdapter(this.f4563z);
        this.f4546i.addOnPageChangeListener(this);
        this.f4541d.setOnClickListener(this);
        this.f4542e.setOnClickListener(this);
        this.f4543f.setOnClickListener(this);
        this.f4544g.setOnClickListener(this);
        this.f4545h.setOnClickListener(this);
    }

    /* renamed from: i */
    private void m5954i() {
        Intent intent = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
        intent.putExtra(C0861d.f2143o, "com.beastbikes.intent.action.ACTIVITY_PAUSE_OR_RESUME");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setPackage(getPackageName());
        startService(intent);
    }

    /* renamed from: j */
    private void m5955j() {
        C2580w.a(this, "", "click_ridding_finish");
        Intent intent = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
        intent.putExtra(C0861d.f2143o, "com.beastbikes.intent.action.ACTIVITY_COMPLETE");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setPackage(getPackageName());
        startService(intent);
        getSharedPreferences(getPackageName(), 0).edit().remove("use_stage_route_id").remove("use_route_id").remove("use_route_name").remove("use_stage_route_hint").apply();
    }

    /* renamed from: a */
    private void m5929a(int i) {
        switch (i) {
            case 0:
            case 4:
                m5957l();
                return;
            case 1:
                m5959n();
                return;
            case 2:
            case 3:
                m5958m();
                return;
            default:
                return;
        }
    }

    /* renamed from: k */
    private void m5956k() {
        this.f4542e.setImageResource(C1373R.drawable.ic_cycling_pause_icon);
        this.f4541d.setVisibility(0);
        if (((BeastBikes) getApplication()).m5259g()) {
            getWindow().addFlags(128);
        } else {
            getWindow().clearFlags(128);
        }
    }

    /* renamed from: l */
    private void m5957l() {
        getWindow().clearFlags(128);
        this.f4542e.setImageResource(C1373R.drawable.ic_cycling_start_icon);
        if (this.f4562y == null || this.f4562y.isEmpty()) {
            finish();
            return;
        }
        View view = (View) this.f4562y.get(0);
        if (view instanceof C2040d) {
            ((C2040d) view).a();
        }
        finish();
    }

    /* renamed from: m */
    private void m5958m() {
        this.f4542e.startAnimation(this.f4551n);
        this.f4542e.setImageResource(C1373R.drawable.ic_cycling_start_icon);
        if (this.f4562y != null && !this.f4562y.isEmpty()) {
            View view = (View) this.f4562y.get(0);
            if (view instanceof C2040d) {
                ((C2040d) view).a();
            }
        }
    }

    /* renamed from: n */
    private void m5959n() {
        this.f4542e.clearAnimation();
        this.f4542e.setImageResource(C1373R.drawable.ic_cycling_pause_icon);
        if (this.f4562y != null && !this.f4562y.isEmpty()) {
            View view = (View) this.f4562y.get(0);
            if (view instanceof C2040d) {
                ((C2040d) view).b();
            }
        }
    }

    /* renamed from: a */
    private void m5930a(LocalActivity localActivity) {
        if (localActivity != null) {
            this.f4552o = localActivity;
            if (this.f4527B != null && localActivity.getTotalElapsedTime() > ((double) this.f4527B.getCourseTime())) {
                this.f4547j.setVisibility(8);
            }
            if (!this.f4531F && this.f4562y != null && !this.f4562y.isEmpty()) {
                View view = (View) this.f4562y.get(this.f4546i.getCurrentItem());
                if (view instanceof C2040d) {
                    m5939b(((C2040d) view).a((long) localActivity.getTotalElapsedTime(), localActivity.getRealTimePower(), localActivity.getRealTimeCadence()));
                    return;
                }
                ((CyclingPreviewLayoutForApp) view).a(localActivity);
                view = (View) this.f4562y.get(0);
                if (view instanceof C2040d) {
                    m5939b(((C2040d) view).a((long) localActivity.getTotalElapsedTime(), localActivity.getRealTimePower(), localActivity.getRealTimeCadence()));
                }
            }
        }
    }

    /* renamed from: b */
    private void m5936b(int i) {
        getAsyncTaskQueue().a(new CyclingActivity$7(this, new C2350b(this), i), new Void[0]);
    }
}
