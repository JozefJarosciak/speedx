package com.beastbikes.android.modules.cycling.sections.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;

class SectionFiltersActivity$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ SectionFiltersActivity f10591a;

    SectionFiltersActivity$2(SectionFiltersActivity sectionFiltersActivity) {
        this.f10591a = sectionFiltersActivity;
    }

    public void onClick(View view) {
        SectionFiltersActivity.a(this.f10591a);
        SectionFiltersActivity.c(this.f10591a).setSelected(true);
        SectionFiltersActivity.c(this.f10591a).setTextColor(this.f10591a.getResources().getColor(C1373R.color.colorPrimaryDark));
        SectionFiltersActivity.a(this.f10591a, "2");
    }
}
