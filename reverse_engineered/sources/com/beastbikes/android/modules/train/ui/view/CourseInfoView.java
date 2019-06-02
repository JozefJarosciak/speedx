package com.beastbikes.android.modules.train.ui.view;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import com.beastbikes.android.modules.train.ui.TrainCourseInfoActivity;
import com.beastbikes.android.modules.train.ui.p151b.C2382a;

public class CourseInfoView extends LinearLayout implements OnClickListener {
    /* renamed from: a */
    private ImageView f11317a;
    /* renamed from: b */
    private TextView f11318b;
    /* renamed from: c */
    private TextView f11319c;
    /* renamed from: d */
    private LinearLayout f11320d;
    /* renamed from: e */
    private TextView f11321e;
    /* renamed from: f */
    private TextView f11322f;
    /* renamed from: g */
    private TextView f11323g;
    /* renamed from: h */
    private Button f11324h;
    /* renamed from: i */
    private LinearLayout f11325i;
    /* renamed from: j */
    private RatingBar f11326j;
    /* renamed from: k */
    private C2382a f11327k;
    /* renamed from: l */
    private boolean f11328l;
    /* renamed from: m */
    private CourseDTO f11329m;
    /* renamed from: n */
    private C2385a f11330n;

    /* renamed from: com.beastbikes.android.modules.train.ui.view.CourseInfoView$a */
    public interface C2385a {
        /* renamed from: a */
        void m12105a(int i);
    }

    public CourseInfoView(Context context) {
        super(context);
        m12109c();
    }

    public CourseInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m12109c();
    }

    public CourseInfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m12109c();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case C1373R.id.course_info_train_calendar_view:
                if (this.f11327k == null) {
                    this.f11327k = new C2382a(getContext(), 2);
                }
                this.f11327k.show();
                return;
            case C1373R.id.course_info_course_info_label:
                if (this.f11329m != null) {
                    Intent intent = new Intent(getContext(), TrainCourseInfoActivity.class);
                    intent.putExtra("train_type", "train_type_single");
                    intent.putExtra("train_course_id", this.f11329m.getId());
                    intent.putExtra("train_type_info", true);
                    getContext().startActivity(intent);
                    return;
                }
                return;
            case C1373R.id.course_info_start_train_btn:
                if (this.f11330n != null && this.f11329m != null) {
                    this.f11330n.m12105a(this.f11329m.getId());
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: c */
    private void m12109c() {
        inflate(getContext(), C1373R.layout.view_course_info, this);
        ((TextView) findViewById(C1373R.id.course_info_train_calendar_view)).setOnClickListener(this);
        this.f11317a = (ImageView) findViewById(C1373R.id.course_info_course_type);
        this.f11318b = (TextView) findViewById(C1373R.id.course_info_course_name);
        this.f11321e = (TextView) findViewById(C1373R.id.course_info_train_time_value);
        this.f11322f = (TextView) findViewById(C1373R.id.course_info_train_if_value);
        this.f11323g = (TextView) findViewById(C1373R.id.course_info_train_tss_value);
        this.f11319c = (TextView) findViewById(C1373R.id.course_info_course_info_label);
        this.f11319c.setOnClickListener(this);
        this.f11320d = (LinearLayout) findViewById(C1373R.id.course_info_course_data_view);
        this.f11325i = (LinearLayout) findViewById(C1373R.id.course_info_rating_view);
        this.f11326j = (RatingBar) findViewById(C1373R.id.course_info_rating);
        this.f11324h = (Button) findViewById(C1373R.id.course_info_start_train_btn);
        this.f11324h.setOnClickListener(this);
        this.f11328l = C1849a.m9647c();
        this.f11320d.setVisibility(8);
        this.f11324h.setVisibility(8);
    }

    /* renamed from: a */
    public void m12112a(CourseDTO courseDTO, String str) {
        if (courseDTO != null) {
            if (this.f11329m != null) {
                courseDTO.setTrainCourseTime(this.f11329m.getTrainCourseTime());
            }
            this.f11329m = courseDTO;
            if (this.f11328l) {
                this.f11318b.setText(courseDTO.getName());
            } else {
                this.f11318b.setText(courseDTO.getEnName());
            }
            this.f11317a.setImageResource(C1373R.drawable.ic_train_today_course);
            this.f11320d.setVisibility(0);
            this.f11324h.setVisibility(0);
            this.f11321e.setText(String.valueOf(courseDTO.getCourseTime() / 60));
            this.f11323g.setText("TSS  " + String.valueOf(courseDTO.getTss()));
            this.f11322f.setText("IF  " + String.valueOf(courseDTO.getWattsIf()));
            if (TextUtils.equals(str, "train_type_single")) {
                this.f11319c.setVisibility(8);
                this.f11325i.setVisibility(0);
                switch (courseDTO.getCategoryId()) {
                    case 1:
                        this.f11317a.setImageResource(C1373R.drawable.ic_train_category_relax);
                        break;
                    case 2:
                        this.f11317a.setImageResource(C1373R.drawable.ic_train_category_fat);
                        break;
                    case 3:
                        this.f11317a.setImageResource(C1373R.drawable.ic_train_category_climbing);
                        break;
                    case 4:
                        this.f11317a.setImageResource(C1373R.drawable.ic_train_category_breakout);
                        break;
                }
            } else if (TextUtils.equals(str, "train_type_long")) {
                this.f11325i.setVisibility(8);
                this.f11319c.setVisibility(0);
            }
            m12110d();
            int tss = courseDTO.getTss();
            if (tss <= 15) {
                this.f11326j.setRating(1.0f);
            } else if (tss > 15 && tss <= 30) {
                this.f11326j.setRating(2.0f);
            } else if (tss > 30 && tss <= 50) {
                this.f11326j.setRating(3.0f);
            } else if (tss <= 50 || tss > 70) {
                this.f11326j.setRating(5.0f);
            } else {
                this.f11326j.setRating(4.0f);
            }
            m12113b();
        }
    }

    public void setStartListener(C2385a c2385a) {
        this.f11330n = c2385a;
    }

    /* renamed from: a */
    public void m12111a() {
        this.f11324h.setBackgroundResource(C1373R.drawable.bg_red_btn);
        this.f11324h.setText(C1373R.string.str_is_training);
    }

    /* renamed from: d */
    private void m12110d() {
        if (this.f11329m != null) {
            if (TextUtils.isEmpty(this.f11329m.getSportRouteId())) {
                this.f11324h.setBackgroundResource(C1373R.drawable.bg_red_btn);
                this.f11324h.setText(C1373R.string.str_start_training);
                this.f11324h.setOnClickListener(this);
                return;
            }
            this.f11324h.setBackgroundResource(C1373R.drawable.bg_gray_btn);
            this.f11324h.setText(C1373R.string.str_training_complete);
            this.f11324h.setOnClickListener(null);
        }
    }

    /* renamed from: b */
    public void m12113b() {
        final Context context = getContext();
        new AsyncTask<Void, Void, LocalActivity>(this) {
            /* renamed from: b */
            final /* synthetic */ CourseInfoView f11316b;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m12103a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m12104a((LocalActivity) obj);
            }

            /* renamed from: a */
            protected LocalActivity m12103a(Void... voidArr) {
                return new C1398a(context).a();
            }

            /* renamed from: a */
            protected void m12104a(LocalActivity localActivity) {
                if (localActivity == null) {
                    this.f11316b.m12110d();
                } else if (localActivity.getCourseId() > 0 && this.f11316b.f11329m != null && this.f11316b.f11329m.getId() == localActivity.getCourseId()) {
                    this.f11316b.f11324h.setBackgroundResource(C1373R.drawable.bg_red_btn);
                    this.f11316b.f11324h.setText(C1373R.string.str_is_training);
                }
            }
        }.execute(new Void[0]);
    }
}
