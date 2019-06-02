package com.beastbikes.android.modules.train.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.beastbikes.android.C1373R;

class CreateTrainLongTimePlanActivity$a extends BaseAdapter {
    /* renamed from: a */
    final /* synthetic */ CreateTrainLongTimePlanActivity f11251a;
    /* renamed from: b */
    private String[] f11252b;

    public CreateTrainLongTimePlanActivity$a(CreateTrainLongTimePlanActivity createTrainLongTimePlanActivity, String[] strArr) {
        this.f11251a = createTrainLongTimePlanActivity;
        this.f11252b = strArr;
    }

    public int getCount() {
        return this.f11252b.length;
    }

    public Object getItem(int i) {
        return this.f11252b[i];
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        CreateTrainLongTimePlanActivity$b createTrainLongTimePlanActivity$b;
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.train_plan_item_view, null);
            createTrainLongTimePlanActivity$b = new CreateTrainLongTimePlanActivity$b(this.f11251a, view);
        } else {
            createTrainLongTimePlanActivity$b = (CreateTrainLongTimePlanActivity$b) view.getTag();
        }
        createTrainLongTimePlanActivity$b.m12072a(this.f11252b[i]);
        return view;
    }
}
