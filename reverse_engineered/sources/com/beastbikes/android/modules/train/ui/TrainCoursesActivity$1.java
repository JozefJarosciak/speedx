package com.beastbikes.android.modules.train.ui;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

class TrainCoursesActivity$1 implements OnGroupClickListener {
    /* renamed from: a */
    final /* synthetic */ TrainCoursesActivity f11257a;

    TrainCoursesActivity$1(TrainCoursesActivity trainCoursesActivity) {
        this.f11257a = trainCoursesActivity;
    }

    public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
        TrainCoursesActivity.a(this.f11257a, i);
        TrainCoursesActivity.a(this.f11257a);
        return false;
    }
}
