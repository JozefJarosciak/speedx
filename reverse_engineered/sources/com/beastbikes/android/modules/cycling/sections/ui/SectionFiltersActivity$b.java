package com.beastbikes.android.modules.cycling.sections.ui;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

class SectionFiltersActivity$b implements OnSeekBarChangeListener {
    /* renamed from: a */
    final /* synthetic */ SectionFiltersActivity f10597a;

    SectionFiltersActivity$b(SectionFiltersActivity sectionFiltersActivity) {
        this.f10597a = sectionFiltersActivity;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        double d = ((double) i) * 3.75d;
        if (d > 300.0d) {
            SectionFiltersActivity.j(this.f10597a).setText("300+");
        } else {
            SectionFiltersActivity.j(this.f10597a).setText("" + ((i * 375) / 100));
        }
        switch (((int) d) / 60) {
            case 0:
                if (SectionFiltersActivity.i(this.f10597a)) {
                    SectionFiltersActivity.c(this.f10597a, "0,60");
                    return;
                } else {
                    SectionFiltersActivity.c(this.f10597a, "0,18");
                    return;
                }
            case 1:
                if (SectionFiltersActivity.i(this.f10597a)) {
                    SectionFiltersActivity.c(this.f10597a, "60,120");
                    return;
                } else {
                    SectionFiltersActivity.c(this.f10597a, "18,36");
                    return;
                }
            case 2:
                if (SectionFiltersActivity.i(this.f10597a)) {
                    SectionFiltersActivity.c(this.f10597a, "120,180");
                    return;
                } else {
                    SectionFiltersActivity.c(this.f10597a, "36,55");
                    return;
                }
            case 3:
                if (SectionFiltersActivity.i(this.f10597a)) {
                    SectionFiltersActivity.c(this.f10597a, "180,240");
                    return;
                } else {
                    SectionFiltersActivity.c(this.f10597a, "55,73");
                    return;
                }
            case 4:
                if (SectionFiltersActivity.i(this.f10597a)) {
                    SectionFiltersActivity.c(this.f10597a, "240,300");
                    return;
                } else {
                    SectionFiltersActivity.c(this.f10597a, "73,91");
                    return;
                }
            case 5:
                if (SectionFiltersActivity.i(this.f10597a)) {
                    SectionFiltersActivity.c(this.f10597a, "300,2000");
                    return;
                } else {
                    SectionFiltersActivity.c(this.f10597a, "91,2000");
                    return;
                }
            default:
                return;
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
