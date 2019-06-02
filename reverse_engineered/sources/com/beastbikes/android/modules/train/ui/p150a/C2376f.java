package com.beastbikes.android.modules.train.ui.p150a;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import com.beastbikes.framework.android.p088g.C2801d;
import com.beastbikes.framework.ui.android.utils.ViewHolder;
import com.squareup.picasso.Picasso;

/* compiled from: TrainLongTimeCourseViewHolder */
/* renamed from: com.beastbikes.android.modules.train.ui.a.f */
public class C2376f extends ViewHolder<CourseDTO> {
    /* renamed from: a */
    private ImageView f11299a;
    /* renamed from: b */
    private TextView f11300b;
    /* renamed from: c */
    private TextView f11301c;

    public /* synthetic */ void bind(Object obj) {
        m12089a((CourseDTO) obj);
    }

    public C2376f(View view) {
        super(view);
        view.setPadding(0, C2801d.m13756a(getContext(), 10.0f), 0, 0);
        this.f11299a = (ImageView) view.findViewById(C1373R.id.train_long_time_course_item_img);
        this.f11300b = (TextView) view.findViewById(C1373R.id.train_long_time_course_item_title);
        this.f11301c = (TextView) view.findViewById(C1373R.id.train_long_time_course_item_desc);
    }

    /* renamed from: a */
    public void m12089a(CourseDTO courseDTO) {
        if (courseDTO != null) {
            if (C1849a.m9647c()) {
                this.f11300b.setText(courseDTO.getName());
                this.f11301c.setText(courseDTO.getDesc());
            } else {
                this.f11300b.setText(courseDTO.getEnName());
                this.f11301c.setText(courseDTO.getEnDesc());
            }
            Object picture = courseDTO.getPicture();
            if (!TextUtils.isEmpty(picture)) {
                Picasso.with(getContext()).load(picture).fit().placeholder(C1373R.drawable.transparent).error(C1373R.drawable.transparent).centerCrop().into(this.f11299a);
            }
        }
    }
}
