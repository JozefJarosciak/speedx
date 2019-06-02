package com.beastbikes.android.modules.train.ui.p150a;

import android.view.View;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import com.beastbikes.android.modules.train.dto.TrainCourseItemDTO;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

/* compiled from: WeekCourseViewHolder */
/* renamed from: com.beastbikes.android.modules.train.ui.a.h */
public class C2378h extends ViewHolder<TrainCourseItemDTO> {
    /* renamed from: a */
    private TextView f11306a;
    /* renamed from: b */
    private TextView f11307b;
    /* renamed from: c */
    private TextView f11308c;

    public /* synthetic */ void bind(Object obj) {
        m12092a((TrainCourseItemDTO) obj);
    }

    public C2378h(View view) {
        super(view);
        this.f11306a = (TextView) view.findViewById(C1373R.id.week_course_item_week_day);
        this.f11307b = (TextView) view.findViewById(C1373R.id.week_course_item_name);
        this.f11308c = (TextView) view.findViewById(C1373R.id.week_course_item_time);
    }

    /* renamed from: a */
    public void m12092a(TrainCourseItemDTO trainCourseItemDTO) {
        if (trainCourseItemDTO != null) {
            this.f11306a.setText(trainCourseItemDTO.getWeekDay());
            CourseDTO course = trainCourseItemDTO.getCourse();
            if (course != null) {
                if (C1849a.m9647c()) {
                    this.f11307b.setText(course.getName());
                } else {
                    this.f11307b.setText(course.getEnName());
                }
                this.f11308c.setText(getContext().getString(C1373R.string.str_duration) + String.valueOf(course.getCourseTime() / 60) + getContext().getString(C1373R.string.str_minute));
            }
        }
    }
}
