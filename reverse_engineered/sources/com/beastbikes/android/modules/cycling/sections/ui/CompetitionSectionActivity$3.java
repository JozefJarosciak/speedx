package com.beastbikes.android.modules.cycling.sections.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;

class CompetitionSectionActivity$3 implements OnFocusChangeListener {
    /* renamed from: a */
    final /* synthetic */ CompetitionSectionActivity f10557a;

    CompetitionSectionActivity$3(CompetitionSectionActivity competitionSectionActivity) {
        this.f10557a = competitionSectionActivity;
    }

    public void onFocusChange(View view, boolean z) {
        if (z) {
            CompetitionSectionActivity.b(this.f10557a).setVisibility(8);
        } else {
            CompetitionSectionActivity.b(this.f10557a).setVisibility(0);
        }
    }
}
