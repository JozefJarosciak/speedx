package com.beastbikes.android.modules.cycling.sections.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;

class SectionFiltersActivity$4 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ SectionFiltersActivity f10593a;

    SectionFiltersActivity$4(SectionFiltersActivity sectionFiltersActivity) {
        this.f10593a = sectionFiltersActivity;
    }

    public void onClick(View view) {
        SectionFiltersActivity.a(this.f10593a);
        SectionFiltersActivity.e(this.f10593a).setSelected(true);
        SectionFiltersActivity.e(this.f10593a).setTextColor(this.f10593a.getResources().getColor(C1373R.color.colorPrimaryDark));
        SectionFiltersActivity.a(this.f10593a, "4");
    }
}
