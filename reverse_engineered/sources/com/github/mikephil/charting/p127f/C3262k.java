package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3283i;

/* compiled from: LineRadarRenderer */
/* renamed from: com.github.mikephil.charting.f.k */
public abstract class C3262k extends C3253l {
    public C3262k(C3185a c3185a, C3275j c3275j) {
        super(c3185a, c3275j);
    }

    /* renamed from: a */
    protected void m15764a(Canvas canvas, Path path, Drawable drawable) {
        if (mo4003b()) {
            int save = canvas.save();
            canvas.clipPath(path);
            drawable.setBounds((int) this.o.m15864f(), (int) this.o.m15862e(), (int) this.o.m15866g(), (int) this.o.m15868h());
            drawable.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        throw new RuntimeException("Fill-drawables not (yet) supported below API level 18, this code was run on API level " + C3283i.m15951d() + ".");
    }

    /* renamed from: a */
    protected void m15763a(Canvas canvas, Path path, int i, int i2) {
        int i3 = (i2 << 24) | (ViewCompat.MEASURED_SIZE_MASK & i);
        if (mo4003b()) {
            int save = canvas.save();
            canvas.clipPath(path);
            canvas.drawColor(i3);
            canvas.restoreToCount(save);
            return;
        }
        Style style = this.h.getStyle();
        int color = this.h.getColor();
        this.h.setStyle(Style.FILL);
        this.h.setColor(i3);
        canvas.drawPath(path, this.h);
        this.h.setColor(color);
        this.h.setStyle(style);
    }

    /* renamed from: b */
    private boolean mo4003b() {
        return C3283i.m15951d() >= 18;
    }
}
