package com.beastbikes.android.modules.train.ui.p150a;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import com.beastbikes.android.modules.train.dto.TrainCourseItemDTO;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

/* compiled from: TrainCourseItemViewHolder */
/* renamed from: com.beastbikes.android.modules.train.ui.a.d */
public class C2374d extends ViewHolder<TrainCourseItemDTO> {
    /* renamed from: a */
    private TextView f11290a;
    /* renamed from: b */
    private TextView f11291b;
    /* renamed from: c */
    private TextView f11292c;
    /* renamed from: d */
    private TextView f11293d;
    /* renamed from: e */
    private TextView f11294e;
    /* renamed from: f */
    private ImageView f11295f;
    /* renamed from: g */
    private boolean f11296g = C1849a.m9647c();
    /* renamed from: h */
    private String[] f11297h = getContext().getResources().getStringArray(C1373R.array.train_course_category);

    public /* synthetic */ void bind(Object obj) {
        m12088a((TrainCourseItemDTO) obj);
    }

    public C2374d(View view) {
        super(view);
        this.f11290a = (TextView) view.findViewById(C1373R.id.train_course_item_week);
        this.f11291b = (TextView) view.findViewById(C1373R.id.train_course_item_date);
        this.f11292c = (TextView) view.findViewById(C1373R.id.train_course_item_name);
        this.f11293d = (TextView) view.findViewById(C1373R.id.train_course_item_desc);
        this.f11294e = (TextView) view.findViewById(C1373R.id.train_course_item_time);
        this.f11295f = (ImageView) view.findViewById(C1373R.id.train_course_item_train_iv);
    }

    /* renamed from: a */
    public void m12088a(TrainCourseItemDTO trainCourseItemDTO) {
        if (trainCourseItemDTO != null) {
            this.f11290a.setText(trainCourseItemDTO.getWeekDay());
            this.f11291b.setText(trainCourseItemDTO.getDate());
            CourseDTO course = trainCourseItemDTO.getCourse();
            if (course != null) {
                if (this.f11296g) {
                    this.f11292c.setText(course.getName());
                } else {
                    this.f11292c.setText(course.getEnName());
                }
                if (course.getCategoryId() - 1 < this.f11297h.length) {
                    this.f11293d.setText(this.f11297h[course.getCategoryId() - 1]);
                }
                this.f11294e.setText(String.valueOf(course.getCourseTime() / 60) + getContext().getString(C1373R.string.activity_start_navigation_selected_time_facility));
                if (TextUtils.isEmpty(course.getSportRouteId())) {
                    this.f11295f.setVisibility(8);
                } else {
                    this.f11295f.setVisibility(0);
                }
            }
        }
    }
}
