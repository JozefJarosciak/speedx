package com.beastbikes.android.modules.cycling.sections.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;

class SectionFiltersActivity$3 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ SectionFiltersActivity f10592a;

    SectionFiltersActivity$3(SectionFiltersActivity sectionFiltersActivity) {
        this.f10592a = sectionFiltersActivity;
    }

    public void onClick(View view) {
        SectionFiltersActivity.a(this.f10592a);
        SectionFiltersActivity.d(this.f10592a).setSelected(true);
        SectionFiltersActivity.d(this.f10592a).setTextColor(this.f10592a.getResources().getColor(C1373R.color.colorPrimaryDark));
        SectionFiltersActivity.a(this.f10592a, "3");
    }
}
