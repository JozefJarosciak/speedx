package com.beastbikes.android.modules.train.ui.p150a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.train.dto.TrainCourseItemDTO;
import java.util.List;

/* compiled from: TrainCourseInfoAdapter */
/* renamed from: com.beastbikes.android.modules.train.ui.a.c */
public class C2373c extends BaseAdapter {
    /* renamed from: a */
    private List<TrainCourseItemDTO> f11289a;

    public C2373c(List<TrainCourseItemDTO> list) {
        this.f11289a = list;
    }

    public int getCount() {
        return this.f11289a.size();
    }

    public Object getItem(int i) {
        return this.f11289a.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C2374d c2374d;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.train_course_item_view, null);
            c2374d = new C2374d(view);
        } else {
            c2374d = (C2374d) view.getTag();
        }
        c2374d.m12088a((TrainCourseItemDTO) this.f11289a.get(i));
        return view;
    }
}
