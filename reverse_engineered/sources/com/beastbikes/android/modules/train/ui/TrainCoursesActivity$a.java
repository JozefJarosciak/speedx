package com.beastbikes.android.modules.train.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.train.dto.TrainCourseItemDTO;
import com.beastbikes.android.modules.train.dto.TrainWeekDTO;
import com.beastbikes.android.modules.train.ui.p150a.C2377g;
import com.beastbikes.android.modules.train.ui.p150a.C2378h;
import java.util.ArrayList;

class TrainCoursesActivity$a extends BaseExpandableListAdapter {
    /* renamed from: a */
    public ArrayList<TrainWeekDTO> f11258a;
    /* renamed from: b */
    final /* synthetic */ TrainCoursesActivity f11259b;

    public TrainCoursesActivity$a(TrainCoursesActivity trainCoursesActivity, ArrayList<TrainWeekDTO> arrayList) {
        this.f11259b = trainCoursesActivity;
        this.f11258a = arrayList;
    }

    public int getGroupCount() {
        return this.f11258a.size();
    }

    public int getChildrenCount(int i) {
        TrainWeekDTO trainWeekDTO = (TrainWeekDTO) this.f11258a.get(i);
        if (trainWeekDTO.getCourses() == null || trainWeekDTO.getCourses().size() <= 0) {
            return 0;
        }
        return trainWeekDTO.getCourses().size();
    }

    public Object getGroup(int i) {
        return this.f11258a.get(i);
    }

    public Object getChild(int i, int i2) {
        TrainWeekDTO trainWeekDTO = (TrainWeekDTO) this.f11258a.get(i);
        if (trainWeekDTO.getCourses() == null || trainWeekDTO.getCourses().size() <= 0) {
            return null;
        }
        return trainWeekDTO.getCourses().get(i2);
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        C2377g c2377g;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.train_week_item_view, null);
            c2377g = new C2377g(view);
        } else {
            c2377g = (C2377g) view.getTag();
        }
        if (z) {
            c2377g.m12090a().setCompoundDrawablesWithIntrinsicBounds(0, 0, C1373R.drawable.ic_train_course_down, 0);
        } else {
            c2377g.m12090a().setCompoundDrawablesWithIntrinsicBounds(0, 0, C1373R.drawable.ic_train_course_up, 0);
        }
        c2377g.m12091a((TrainWeekDTO) getGroup(i));
        return view;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        C2378h c2378h;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.week_course_item_view, null);
            c2378h = new C2378h(view);
        } else {
            c2378h = (C2378h) view.getTag();
        }
        c2378h.m12092a((TrainCourseItemDTO) getChild(i, i2));
        return view;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}
