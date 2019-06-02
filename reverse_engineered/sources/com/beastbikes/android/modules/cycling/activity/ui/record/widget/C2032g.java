package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import com.github.mikephil.charting.components.C1982g;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3279e;

/* compiled from: MyMarkerView */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.widget.g */
public class C2032g extends C1982g {
    public C2032g(Context context, int i) {
        super(context, i);
    }

    /* renamed from: a */
    public void mo3336a(Entry entry, C3213d c3213d) {
    }

    public C3279e getOffset() {
        return new C3279e((float) (-(getWidth() / 2)), (float) ((-getHeight()) / 2));
    }
}
