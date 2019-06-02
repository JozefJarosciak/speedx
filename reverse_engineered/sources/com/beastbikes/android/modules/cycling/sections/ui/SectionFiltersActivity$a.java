package com.beastbikes.android.modules.cycling.sections.ui;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

class SectionFiltersActivity$a implements OnSeekBarChangeListener {
    /* renamed from: a */
    final /* synthetic */ SectionFiltersActivity f10596a;

    SectionFiltersActivity$a(SectionFiltersActivity sectionFiltersActivity) {
        this.f10596a = sectionFiltersActivity;
    }

    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        double d = ((double) i) * 6.25d;
        if (d > 500.0d) {
            SectionFiltersActivity.h(this.f10596a).setText("500+");
        } else {
            SectionFiltersActivity.h(this.f10596a).setText("" + ((i * 625) / 100));
        }
        switch (((int) d) / 100) {
            case 0:
                if (SectionFiltersActivity.i(this.f10596a)) {
                    SectionFiltersActivity.b(this.f10596a, "0,100");
                    return;
                } else {
                    SectionFiltersActivity.b(this.f10596a, "0,160");
                    return;
                }
            case 1:
                if (SectionFiltersActivity.i(this.f10596a)) {
                    SectionFiltersActivity.b(this.f10596a, "100,200");
                    return;
                } else {
                    SectionFiltersActivity.b(this.f10596a, "160,321");
                    return;
                }
            case 2:
                if (SectionFiltersActivity.i(this.f10596a)) {
                    SectionFiltersActivity.b(this.f10596a, "200,300");
                    return;
                } else {
                    SectionFiltersActivity.b(this.f10596a, "321,482");
                    return;
                }
            case 3:
                if (SectionFiltersActivity.i(this.f10596a)) {
                    SectionFiltersActivity.b(this.f10596a, "300,400");
                    return;
                } else {
                    SectionFiltersActivity.b(this.f10596a, "482,643");
                    return;
                }
            case 4:
                if (SectionFiltersActivity.i(this.f10596a)) {
                    SectionFiltersActivity.b(this.f10596a, "400,500");
                    return;
                } else {
                    SectionFiltersActivity.b(this.f10596a, "643,804");
                    return;
                }
            case 5:
                if (SectionFiltersActivity.i(this.f10596a)) {
                    SectionFiltersActivity.b(this.f10596a, "500,1000");
                    return;
                } else {
                    SectionFiltersActivity.b(this.f10596a, "804,1609");
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
