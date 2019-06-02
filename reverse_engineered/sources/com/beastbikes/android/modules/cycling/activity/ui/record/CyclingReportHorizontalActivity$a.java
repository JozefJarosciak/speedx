package com.beastbikes.android.modules.cycling.activity.ui.record;

import com.github.mikephil.charting.data.Entry;
import java.util.Comparator;

class CyclingReportHorizontalActivity$a implements Comparator<Entry> {
    /* renamed from: a */
    final /* synthetic */ CyclingReportHorizontalActivity f8797a;

    private CyclingReportHorizontalActivity$a(CyclingReportHorizontalActivity cyclingReportHorizontalActivity) {
        this.f8797a = cyclingReportHorizontalActivity;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m10111a((Entry) obj, (Entry) obj2);
    }

    /* renamed from: a */
    public int m10111a(Entry entry, Entry entry2) {
        float b = entry.mo3912b() - entry2.mo3912b();
        if (b > 0.0f) {
            return 1;
        }
        if (b == 0.0f) {
            return 0;
        }
        return -1;
    }
}
