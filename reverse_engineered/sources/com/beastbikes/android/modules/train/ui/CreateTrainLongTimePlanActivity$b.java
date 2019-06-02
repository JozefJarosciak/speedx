package com.beastbikes.android.modules.train.ui;

import android.view.View;
import android.widget.TextView;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.ViewHolder;

class CreateTrainLongTimePlanActivity$b extends ViewHolder<String> {
    /* renamed from: a */
    final /* synthetic */ CreateTrainLongTimePlanActivity f11253a;
    /* renamed from: b */
    private TextView f11254b;

    public /* synthetic */ void bind(Object obj) {
        m12072a((String) obj);
    }

    CreateTrainLongTimePlanActivity$b(CreateTrainLongTimePlanActivity createTrainLongTimePlanActivity, View view) {
        this.f11253a = createTrainLongTimePlanActivity;
        super(view);
        this.f11254b = (TextView) view.findViewById(C1373R.id.train_plan_item_desc);
    }

    /* renamed from: a */
    public void m12072a(String str) {
        this.f11254b.setText(str);
    }
}
