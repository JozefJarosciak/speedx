package com.beastbikes.android.modules.cycling.activity.ui.record.p120a;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.beastbikes.android.modules.cycling.activity.ui.record.widget.TrainStatisticsChartView;
import java.util.ArrayList;

/* compiled from: CyclingReportTrainStatisticsAdapter */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.a.b */
public class C1966b extends PagerAdapter {
    /* renamed from: a */
    private ArrayList<TrainStatisticsChartView> f8860a;

    public C1966b(ArrayList<TrainStatisticsChartView> arrayList) {
        this.f8860a = arrayList;
    }

    public int getCount() {
        return this.f8860a.size();
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) this.f8860a.get(i);
        viewGroup.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
