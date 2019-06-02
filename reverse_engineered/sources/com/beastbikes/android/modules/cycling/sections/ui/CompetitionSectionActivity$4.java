package com.beastbikes.android.modules.cycling.sections.ui;

import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.TimerTask;

class CompetitionSectionActivity$4 extends TimerTask {
    /* renamed from: a */
    final /* synthetic */ CompetitionSectionActivity f10559a;

    /* renamed from: com.beastbikes.android.modules.cycling.sections.ui.CompetitionSectionActivity$4$1 */
    class C22251 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ CompetitionSectionActivity$4 f10558a;

        C22251(CompetitionSectionActivity$4 competitionSectionActivity$4) {
            this.f10558a = competitionSectionActivity$4;
        }

        public void run() {
            Toasts.show(this.f10558a.f10559a, this.f10558a.f10559a.getResources().getString(C1373R.string.str_locating_failed));
            CompetitionSectionActivity.c(this.f10558a.f10559a).setEnabled(true);
        }
    }

    CompetitionSectionActivity$4(CompetitionSectionActivity competitionSectionActivity) {
        this.f10559a = competitionSectionActivity;
    }

    public void run() {
        this.f10559a.runOnUiThread(new C22251(this));
    }
}
