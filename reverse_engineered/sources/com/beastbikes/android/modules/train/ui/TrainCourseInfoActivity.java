package com.beastbikes.android.modules.train.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1786a;
import com.beastbikes.android.dialog.C1786a.C1785b;
import com.beastbikes.android.dialog.C1787b;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.SessionFragmentActivity;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.activity.ui.CyclingActivity;
import com.beastbikes.android.modules.cycling.activity.ui.CyclingStartAnimationActivity;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO;
import com.beastbikes.android.modules.train.dto.TrainInfoDTO;
import com.beastbikes.android.modules.train.dto.TrainResultDTO;
import com.beastbikes.android.modules.train.p148c.C2354b;
import com.beastbikes.android.modules.train.p149d.C2364b;
import com.beastbikes.android.modules.train.ui.view.CourseInfoView;
import com.beastbikes.android.modules.train.ui.view.CourseInfoView.C2385a;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.framework.android.p084c.p085a.C1458a;
import com.beastbikes.framework.android.p084c.p085a.C1459b;
import com.beastbikes.framework.android.p088g.C2801d;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.squareup.picasso.Picasso;
import java.util.Calendar;
import java.util.TreeMap;

@C1459b(a = 2130903381)
public class TrainCourseInfoActivity extends SessionFragmentActivity implements OnClickListener, C1785b, C2354b, C2385a {
    @C1458a(a = 2131756684)
    /* renamed from: a */
    private ImageView f6252a;
    @C1458a(a = 2131756685)
    /* renamed from: b */
    private CourseInfoView f6253b;
    @C1458a(a = 2131756687)
    /* renamed from: c */
    private ViewGroup f6254c;
    /* renamed from: d */
    private C1802i f6255d;
    /* renamed from: e */
    private SingleTrainDetailFragment f6256e;
    /* renamed from: f */
    private LongTrainDetailFragment f6257f;
    /* renamed from: g */
    private C2364b f6258g;
    /* renamed from: h */
    private int f6259h;
    /* renamed from: i */
    private TrainCourseDTO f6260i;
    /* renamed from: j */
    private int f6261j;
    /* renamed from: k */
    private String f6262k;
    /* renamed from: l */
    private boolean f6263l = false;
    /* renamed from: m */
    private long f6264m;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.f6254c.setOnClickListener(this);
        this.f6253b.setFocusable(true);
        this.f6253b.setFocusableInTouchMode(true);
        this.f6253b.requestFocus();
        this.f6253b.setStartListener(this);
        this.f6258g = new C2364b(this);
        this.f6262k = intent.getStringExtra("train_type");
        this.f6259h = intent.getIntExtra("train_course_id", -1);
        this.f6264m = intent.getLongExtra("train_course_time", 0);
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f6261j = currentUser.getTrainingId();
        }
        if (intent.hasExtra("train_type_info")) {
            this.f6254c.setVisibility(8);
            this.f6258g.a();
        } else if (this.f6261j <= 0) {
            this.f6254c.setVisibility(8);
            this.f6258g.a();
        } else if (TextUtils.equals(this.f6262k, "train_type_single")) {
            this.f6254c.setVisibility(8);
            this.f6258g.b();
        } else if (TextUtils.equals(this.f6262k, "train_type_long")) {
            this.f6254c.setVisibility(0);
            this.f6258g.c();
        } else {
            finish();
        }
    }

    protected void onResume() {
        super.onResume();
        this.f6253b.b();
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null && currentUser.getTrainingId() <= 0) {
            this.f6254c.setVisibility(8);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.course_info_course_stop:
                new C1786a(this, getString(C1373R.string.str_train_course_finish_train_msg), this).show();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    public void m7438a() {
        this.f6258g.a(this.f6261j);
    }

    /* renamed from: b */
    public TrainCourseInfoActivity m7443b() {
        return this;
    }

    /* renamed from: c */
    public void m7444c() {
        if (this.f6255d == null) {
            this.f6255d = new C1802i(this, getString(C1373R.string.str_loading), false);
        }
        if (!this.f6255d.isShowing()) {
            this.f6255d.show();
        }
    }

    /* renamed from: d */
    public void m7445d() {
        if (this.f6255d != null && this.f6255d.isShowing()) {
            this.f6255d.dismiss();
        }
    }

    /* renamed from: a */
    public void m7440a(TrainCourseDTO trainCourseDTO) {
        if (trainCourseDTO != null) {
            this.f6260i = trainCourseDTO;
            if (this.f6263l) {
                m7437j();
                return;
            }
            this.f6253b.a(trainCourseDTO, this.f6262k);
            if (!TextUtils.isEmpty(trainCourseDTO.getBgPicture())) {
                Picasso.with(this).load(trainCourseDTO.getBgPicture()).resize(C2801d.a(this), C2801d.b(this)).placeholder((int) C1373R.drawable.transparent).error((int) C1373R.drawable.transparent).centerCrop().into(this.f6252a);
            }
            if (this.f6256e == null) {
                this.f6256e = new SingleTrainDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("train_type_single", trainCourseDTO);
                this.f6256e.setArguments(bundle);
            }
            getSupportFragmentManager().beginTransaction().replace(C1373R.id.frame_train_course_info_container, this.f6256e).commit();
        }
    }

    /* renamed from: e */
    public void m7446e() {
    }

    /* renamed from: f */
    public int m7447f() {
        return this.f6259h;
    }

    /* renamed from: a */
    public void m7441a(TrainInfoDTO trainInfoDTO) {
        if (trainInfoDTO != null) {
            m7436b(trainInfoDTO);
            if (this.f6257f == null) {
                this.f6257f = new LongTrainDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("train_type_long", trainInfoDTO);
                this.f6257f.setArguments(bundle);
            }
            getSupportFragmentManager().beginTransaction().replace(C1373R.id.frame_train_course_info_container, this.f6257f).commit();
        }
    }

    /* renamed from: g */
    public void m7448g() {
    }

    /* renamed from: h */
    public void m7449h() {
        finish();
    }

    /* renamed from: i */
    public void m7450i() {
    }

    /* renamed from: a */
    public void m7442a(TrainResultDTO trainResultDTO) {
        if (trainResultDTO == null) {
            Toasts.show(this, C1373R.string.route_activity_comment_followed_err);
            return;
        }
        int trainingType;
        this.f6261j = trainResultDTO.getTrainId();
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            trainingType = currentUser.getTrainingType();
        } else {
            trainingType = 0;
        }
        if (trainingType == 2) {
            C2574s.a().a(trainResultDTO);
            this.f6254c.setVisibility(0);
        }
        m7437j();
    }

    /* renamed from: a */
    public void m7439a(int i) {
        LocalActivity a = new C1398a((Activity) this).m5861a();
        if (a == null) {
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                this.f6261j = currentUser.getTrainingId();
            }
            if (this.f6261j <= 0) {
                this.f6258g.d();
            } else if (this.f6260i == null || this.f6260i.getId() != i) {
                this.f6259h = i;
                this.f6263l = true;
                this.f6258g.a();
            } else {
                m7437j();
            }
        } else if (a.getCourseId() <= 0 || a.getCourseId() != i) {
            new C1787b(this, getString(C1373R.string.str_is_cycling)).show();
        } else {
            startActivity(new Intent(this, CyclingActivity.class));
        }
    }

    /* renamed from: b */
    private void m7436b(TrainInfoDTO trainInfoDTO) {
        if (trainInfoDTO != null) {
            TreeMap courses = trainInfoDTO.getCourses();
            if (courses != null && courses.size() > 0) {
                int i;
                if (!TextUtils.isEmpty(trainInfoDTO.getBgPicture())) {
                    Picasso.with(this).load(trainInfoDTO.getBgPicture()).resize(C2801d.a(this), C2801d.b(this)).placeholder((int) C1373R.drawable.transparent).error((int) C1373R.drawable.transparent).centerCrop().into(this.f6252a);
                }
                Calendar instance = Calendar.getInstance();
                int i2 = instance.get(3);
                int i3 = instance.get(7);
                instance.setTimeInMillis(trainInfoDTO.getCreated());
                int i4 = instance.get(3);
                if (i3 - 1 == 0) {
                    i = 7;
                } else {
                    i = i3 - 1;
                }
                String valueOf = String.valueOf((i2 - i4) + 1);
                if (courses.containsKey(valueOf)) {
                    TreeMap treeMap = (TreeMap) courses.get(valueOf);
                    if (treeMap != null && treeMap.size() > 0 && treeMap.containsKey(String.valueOf(i))) {
                        CourseDTO courseDTO = (CourseDTO) treeMap.get(String.valueOf(i));
                        if (courseDTO != null) {
                            this.f6264m = courseDTO.getTrainCourseTime();
                        }
                        this.f6253b.a(courseDTO, this.f6262k);
                    }
                }
            }
        }
    }

    /* renamed from: j */
    private void m7437j() {
        Intent intent = new Intent(this, CyclingStartAnimationActivity.class);
        intent.putExtra("cycling_type", 2);
        if (this.f6260i != null) {
            this.f6260i.setTrainCourseTime(this.f6264m);
        }
        intent.putExtra("course_info", this.f6260i);
        startActivity(intent);
        this.f6253b.a();
    }
}
