package com.beastbikes.android.modules.cycling.sections.ui;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

class SectionFiltersActivity$c implements OnSeekBarChangeListener {
    /* renamed from: a */
    final /* synthetic */ SectionFiltersActivity f10598a;

    SectionFiltersActivity$c(SectionFiltersActivity sectionFiltersActivity) {
        this.f10598a = sectionFiltersActivity;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        double d = (double) (i / 4);
        if (d > 20.0d) {
            SectionFiltersActivity.k(this.f10598a).setText("20+");
        } else {
            SectionFiltersActivity.k(this.f10598a).setText("" + ((i * 25) / 100));
        }
        switch (((int) d) / 5) {
            case 0:
                SectionFiltersActivity.d(this.f10598a, "0,5");
                return;
            case 1:
                SectionFiltersActivity.d(this.f10598a, "5,10");
                return;
            case 2:
                SectionFiltersActivity.d(this.f10598a, "10,15");
                return;
            case 3:
                SectionFiltersActivity.d(this.f10598a, "15,20");
                return;
            case 4:
                SectionFiltersActivity.d(this.f10598a, "20,180");
                return;
            default:
                return;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
