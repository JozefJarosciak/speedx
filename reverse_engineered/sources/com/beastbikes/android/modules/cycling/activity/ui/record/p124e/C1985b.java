package com.beastbikes.android.modules.cycling.activity.ui.record.p124e;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import com.github.mikephil.charting.components.C1984f;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3279e;

/* compiled from: HorizontalChartMarkerImage */
/* renamed from: com.beastbikes.android.modules.cycling.activity.ui.record.e.b */
public class C1985b extends C1984f {
    /* renamed from: a */
    private Drawable f8927a;

    public C1985b(Context context, int i) {
        super(context, i);
        this.f8927a = ContextCompat.getDrawable(context, i);
    }

    /* renamed from: a */
    public C3279e mo3338a() {
        if (this.f8927a != null) {
            return new C3279e((float) (-(this.f8927a.getIntrinsicWidth() / 2)), (float) (-(this.f8927a.getIntrinsicHeight() / 2)));
        }
        return super.mo3338a();
    }

    /* renamed from: a */
    public void mo3336a(Entry entry, C3213d c3213d) {
        super.mo3336a(entry, c3213d);
    }
}
