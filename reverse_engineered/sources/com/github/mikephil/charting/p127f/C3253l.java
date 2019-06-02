package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Path;
import com.github.mikephil.charting.p089e.p090b.C3233h;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p183g.C3275j;

/* compiled from: LineScatterCandleRadarRenderer */
/* renamed from: com.github.mikephil.charting.f.l */
public abstract class C3253l extends C3249c {
    /* renamed from: a */
    private Path f14088a = new Path();

    public C3253l(C3185a c3185a, C3275j c3275j) {
        super(c3185a, c3275j);
    }

    /* renamed from: a */
    protected void m15734a(Canvas canvas, float f, float f2, C3233h c3233h) {
        this.i.setColor(c3233h.mo3961h());
        this.i.setStrokeWidth(c3233h.mo3971V());
        this.i.setPathEffect(c3233h.mo3972W());
        if (c3233h.mo3969T()) {
            this.f14088a.reset();
            this.f14088a.moveTo(f, this.o.m15862e());
            this.f14088a.lineTo(f, this.o.m15868h());
            canvas.drawPath(this.f14088a, this.i);
        }
        if (c3233h.mo3970U()) {
            this.f14088a.reset();
            this.f14088a.moveTo(this.o.m15864f(), f2);
            this.f14088a.lineTo(this.o.m15866g(), f2);
            canvas.drawPath(this.f14088a, this.i);
        }
    }
}
