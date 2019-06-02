package com.beastbikes.android.modules.user.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.ui.CyclingRecordActivity.C1442a;
import com.beastbikes.android.modules.user.view.C1443d;
import com.beastbikes.android.widget.p081b.C1456a;
import com.beastbikes.android.widget.p081b.C2409c;
import com.beastbikes.android.widget.p081b.C2471h;
import com.beastbikes.android.widget.p081b.C2613i;

final class CyclingRecordActivity$c extends C2471h<C2409c> {
    private CyclingRecordActivity$c() {
    }

    /* renamed from: a */
    public C2613i mo3497a(ViewGroup viewGroup) {
        return new C1442a(LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.activity_record_list_item, viewGroup, false), this.b);
    }

    /* renamed from: b */
    public C2613i mo3499b(ViewGroup viewGroup) {
        return null;
    }

    /* renamed from: c */
    public C2613i mo3501c(ViewGroup viewGroup) {
        return null;
    }

    /* renamed from: d */
    public C2613i mo3503d(ViewGroup viewGroup) {
        return new C1443d(LayoutInflater.from(viewGroup.getContext()).inflate(C1373R.layout.activity_record_group_item, viewGroup, false));
    }

    /* renamed from: a */
    public void mo3498a(C2613i c2613i, int i) {
    }

    /* renamed from: b */
    public void mo3500b(C2613i c2613i, int i) {
        if (c2613i instanceof C1456a) {
            ((C1456a) c2613i).a(Boolean.valueOf(this.a));
        }
    }

    /* renamed from: c */
    public void mo3502c(C2613i c2613i, int i) {
        if (this.c != null && !this.c.isEmpty()) {
            C2409c c2409c = (C2409c) this.c.get(i);
            if (c2409c != null && (c2613i instanceof C1443d)) {
                ((C1443d) c2613i).a(c2409c);
            }
        }
    }

    /* renamed from: d */
    public void mo3504d(C2613i c2613i, int i) {
        if (this.c != null && !this.c.isEmpty()) {
            C2409c c2409c = (C2409c) this.c.get(i);
            if (c2409c != null && (c2613i instanceof C1442a)) {
                C1442a c1442a = (C1442a) c2613i;
                if (c2409c instanceof ActivityDTO) {
                    c1442a.a((ActivityDTO) c2409c);
                }
            }
        }
    }
}
