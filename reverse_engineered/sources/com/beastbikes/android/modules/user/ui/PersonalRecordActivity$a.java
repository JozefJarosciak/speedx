package com.beastbikes.android.modules.user.ui;

import com.github.mikephil.charting.data.Entry;
import java.util.Comparator;

class PersonalRecordActivity$a implements Comparator<Entry> {
    /* renamed from: a */
    final /* synthetic */ PersonalRecordActivity f11745a;

    private PersonalRecordActivity$a(PersonalRecordActivity personalRecordActivity) {
        this.f11745a = personalRecordActivity;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m12511a((Entry) obj, (Entry) obj2);
    }

    /* renamed from: a */
    public int m12511a(Entry entry, Entry entry2) {
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
