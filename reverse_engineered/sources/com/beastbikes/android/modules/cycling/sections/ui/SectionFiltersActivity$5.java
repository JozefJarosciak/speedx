package com.beastbikes.android.modules.cycling.sections.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;

class SectionFiltersActivity$5 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ SectionFiltersActivity f10594a;

    SectionFiltersActivity$5(SectionFiltersActivity sectionFiltersActivity) {
        this.f10594a = sectionFiltersActivity;
    }

    public void onClick(View view) {
        SectionFiltersActivity.a(this.f10594a);
        SectionFiltersActivity.f(this.f10594a).setSelected(true);
        SectionFiltersActivity.f(this.f10594a).setTextColor(this.f10594a.getResources().getColor(C1373R.color.colorPrimaryDark));
        SectionFiltersActivity.a(this.f10594a, "5");
    }
}
