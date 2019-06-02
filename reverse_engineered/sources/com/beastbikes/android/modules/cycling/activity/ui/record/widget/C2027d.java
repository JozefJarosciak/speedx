package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.ui.record.C1967a;
import com.beastbikes.android.modules.cycling.activity.ui.record.RecordTrain;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.widget.CircleIndicator;
import com.beastbikes.framework.ui.android.lib.pulltorefresh.DensityUtil;

/* compiled from: CyclingCompletedChartItemView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.d */
public class C2027d extends RelativeLayout {
    /* renamed from: a */
    private ViewPager f9180a;
    /* renamed from: b */
    private CircleIndicator f9181b;
    /* renamed from: c */
    private RecordTrain f9182c;

    public C2027d(Context context) {
        super(context);
        m10469a(context);
    }

    public C2027d(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m10469a(context);
    }

    public C2027d(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10469a(context);
    }

    /* renamed from: a */
    private void m10469a(Context context) {
        C2027d.inflate(context, C1373R.layout.layout_cycling_completed_chart_item, this);
        this.f9180a = (ViewPager) findViewById(C1373R.id.viewPager_cycling_completed_chart);
        this.f9181b = (CircleIndicator) findViewById(C1373R.id.indicator);
        this.f9182c = (RecordTrain) findViewById(C1373R.id.record_train_cycling_completed);
    }

    public void setAdapter(C1967a c1967a) {
        this.f9180a.setAdapter(c1967a);
        this.f9180a.setPageMargin(DensityUtil.dip2px(getContext(), 10.0f));
    }

    public void setCurrentPosition(int i) {
        if (this.f9180a != null && i != this.f9180a.getCurrentItem()) {
            this.f9180a.setCurrentItem(i);
        }
    }

    /* renamed from: a */
    public void m10471a(ActivityDTO activityDTO) {
        m10468a();
        m10470b(activityDTO);
    }

    /* renamed from: a */
    private void m10468a() {
        this.f9181b.setViewPager(this.f9180a);
    }

    /* renamed from: b */
    private void m10470b(ActivityDTO activityDTO) {
        if (activityDTO.getCourseId() <= 0 || ((double) activityDTO.getCourseTime()) > activityDTO.getElapsedTime()) {
            ((LayoutParams) this.f9181b.getLayoutParams()).addRule(12);
            this.f9182c.setVisibility(8);
        }
        this.f9182c.m10138a(activityDTO);
    }
}
