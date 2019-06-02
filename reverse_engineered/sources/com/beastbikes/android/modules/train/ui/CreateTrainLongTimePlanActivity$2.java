package com.beastbikes.android.modules.train.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class CreateTrainLongTimePlanActivity$2 implements OnItemClickListener {
    /* renamed from: a */
    final /* synthetic */ String[] f11245a;
    /* renamed from: b */
    final /* synthetic */ CreateTrainLongTimePlanActivity f11246b;

    CreateTrainLongTimePlanActivity$2(CreateTrainLongTimePlanActivity createTrainLongTimePlanActivity, String[] strArr) {
        this.f11246b = createTrainLongTimePlanActivity;
        this.f11245a = strArr;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        CreateTrainLongTimePlanActivity.a(this.f11246b, i + 1);
        CreateTrainLongTimePlanActivity.a(this.f11246b, this.f11245a[i]);
    }
}
