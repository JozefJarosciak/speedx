package com.beastbikes.android.modules.train.ui.p150a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.train.dto.CourseDTO;
import java.util.List;

/* compiled from: TrainLongTimeCourseAdapter */
/* renamed from: com.beastbikes.android.modules.train.ui.a.e */
public class C2375e extends BaseAdapter {
    /* renamed from: a */
    private List<CourseDTO> f11298a;

    public C2375e(List<CourseDTO> list) {
        this.f11298a = list;
    }

    public int getCount() {
        return this.f11298a.size();
    }

    public Object getItem(int i) {
        return this.f11298a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C2376f c2376f;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.train_long_time_course_item_view, null);
            c2376f = new C2376f(view);
        } else {
            c2376f = (C2376f) view.getTag();
        }
        c2376f.m12089a((CourseDTO) this.f11298a.get(i));
        return view;
    }
}
