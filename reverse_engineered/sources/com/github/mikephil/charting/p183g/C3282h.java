package com.github.mikephil.charting.p183g;

/* compiled from: TransformerHorizontalBarChart */
/* renamed from: com.github.mikephil.charting.g.h */
public class C3282h extends C3281g {
    public C3282h(C3275j c3275j) {
        super(c3275j);
    }

    /* renamed from: a */
    public void mo4019a(boolean z) {
        this.b.reset();
        if (z) {
            this.b.setTranslate(-(this.c.m15879n() - this.c.m15855b()), this.c.m15878m() - this.c.m15860d());
            this.b.postScale(-1.0f, 1.0f);
            return;
        }
        this.b.postTranslate(this.c.m15847a(), this.c.m15878m() - this.c.m15860d());
    }
}
