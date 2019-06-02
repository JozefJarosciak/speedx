package com.beastbikes.android.modules.cycling.sections.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;

class SectionFiltersActivity$6 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ SectionFiltersActivity f10595a;

    SectionFiltersActivity$6(SectionFiltersActivity sectionFiltersActivity) {
        this.f10595a = sectionFiltersActivity;
    }

    public void onClick(View view) {
        SectionFiltersActivity.a(this.f10595a);
        SectionFiltersActivity.g(this.f10595a).setSelected(true);
        SectionFiltersActivity.g(this.f10595a).setTextColor(this.f10595a.getResources().getColor(C1373R.color.colorPrimaryDark));
        SectionFiltersActivity.a(this.f10595a, "6");
    }
}
