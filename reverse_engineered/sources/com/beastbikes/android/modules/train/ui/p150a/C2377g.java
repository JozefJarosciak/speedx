package com.beastbikes.android.modules.train.ui.p150a;

import android.view.View;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.train.dto.TrainWeekDTO;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

/* compiled from: TrainWeekViewHolder */
/* renamed from: com.beastbikes.android.modules.train.ui.a.g */
public class C2377g extends ViewHolder<TrainWeekDTO> {
    /* renamed from: a */
    private View f11302a;
    /* renamed from: b */
    private TextView f11303b;
    /* renamed from: c */
    private TextView f11304c;
    /* renamed from: d */
    private TextView f11305d;

    public /* synthetic */ void bind(Object obj) {
        m12091a((TrainWeekDTO) obj);
    }

    public C2377g(View view) {
        super(view);
        this.f11302a = view.findViewById(C1373R.id.train_week_item_selected);
        this.f11303b = (TextView) view.findViewById(C1373R.id.train_week_item_week);
        this.f11304c = (TextView) view.findViewById(C1373R.id.train_week_item_date);
        this.f11305d = (TextView) view.findViewById(C1373R.id.train_week_item_count);
    }

    /* renamed from: a */
    public void m12091a(TrainWeekDTO trainWeekDTO) {
        if (trainWeekDTO != null) {
            this.f11303b.setText(trainWeekDTO.getWeek());
            this.f11304c.setText(trainWeekDTO.getDate());
            this.f11302a.setVisibility(trainWeekDTO.isThisWeek() ? 0 : 8);
            if (trainWeekDTO.getCourses() != null) {
                this.f11305d.setText(String.format(getContext().getString(C1373R.string.str_train_course_count), new Object[]{Integer.valueOf(trainWeekDTO.getCourses().size())}));
            }
        }
    }

    /* renamed from: a */
    public TextView m12090a() {
        return this.f11305d;
    }
}
