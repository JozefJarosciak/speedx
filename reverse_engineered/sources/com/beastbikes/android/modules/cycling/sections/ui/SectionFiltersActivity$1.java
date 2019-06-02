package com.beastbikes.android.modules.cycling.sections.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.alipay.sdk.cons.C0844a;
import com.beastbikes.android.C1373R;

class SectionFiltersActivity$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ SectionFiltersActivity f10590a;

    SectionFiltersActivity$1(SectionFiltersActivity sectionFiltersActivity) {
        this.f10590a = sectionFiltersActivity;
    }

    public void onClick(View view) {
        SectionFiltersActivity.a(this.f10590a);
        SectionFiltersActivity.b(this.f10590a).setSelected(true);
        SectionFiltersActivity.b(this.f10590a).setTextColor(this.f10590a.getResources().getColor(C1373R.color.colorPrimaryDark));
        SectionFiltersActivity.a(this.f10590a, C0844a.f2048d);
    }
}
