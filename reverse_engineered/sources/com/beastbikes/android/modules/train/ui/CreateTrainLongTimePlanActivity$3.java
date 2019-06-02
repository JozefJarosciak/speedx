package com.beastbikes.android.modules.train.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

class CreateTrainLongTimePlanActivity$3 implements OnItemClickListener {
    /* renamed from: a */
    final /* synthetic */ String[] f11247a;
    /* renamed from: b */
    final /* synthetic */ CreateTrainLongTimePlanActivity f11248b;

    CreateTrainLongTimePlanActivity$3(CreateTrainLongTimePlanActivity createTrainLongTimePlanActivity, String[] strArr) {
        this.f11248b = createTrainLongTimePlanActivity;
        this.f11247a = strArr;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        CreateTrainLongTimePlanActivity.b(this.f11248b, i + 1);
        CreateTrainLongTimePlanActivity.b(this.f11248b, this.f11247a[i]);
    }
}
