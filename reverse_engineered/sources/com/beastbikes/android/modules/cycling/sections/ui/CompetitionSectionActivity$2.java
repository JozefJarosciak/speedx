package com.beastbikes.android.modules.cycling.sections.ui;

import com.beastbikes.android.modules.cycling.sections.ui.widget.DrawableClickListener;
import com.beastbikes.android.modules.cycling.sections.ui.widget.DrawableClickListener.DrawablePosition;

class CompetitionSectionActivity$2 implements DrawableClickListener {
    /* renamed from: a */
    final /* synthetic */ CompetitionSectionActivity f10556a;

    CompetitionSectionActivity$2(CompetitionSectionActivity competitionSectionActivity) {
        this.f10556a = competitionSectionActivity;
    }

    public void onClick(DrawablePosition drawablePosition) {
        switch (CompetitionSectionActivity$7.f10570a[drawablePosition.ordinal()]) {
            case 1:
                CompetitionSectionActivity.a(this.f10556a).setText("");
                return;
            default:
                return;
        }
    }
}
