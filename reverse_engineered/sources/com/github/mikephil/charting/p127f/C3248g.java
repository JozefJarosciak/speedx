package com.github.mikephil.charting.p127f;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVException;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p089e.p090b.C3220e;
import com.github.mikephil.charting.p089e.p128a.C2007e;
import com.github.mikephil.charting.p121c.C3192f;
import com.github.mikephil.charting.p179a.C3185a;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3275j;
import com.github.mikephil.charting.p183g.C3283i;

/* compiled from: DataRenderer */
/* renamed from: com.github.mikephil.charting.f.g */
public abstract class C3248g extends C2003o {
    /* renamed from: g */
    protected C3185a f14068g;
    /* renamed from: h */
    protected Paint f14069h = new Paint(1);
    /* renamed from: i */
    protected Paint f14070i;
    /* renamed from: j */
    protected Paint f14071j;
    /* renamed from: k */
    protected Paint f14072k;

    /* renamed from: a */
    public abstract void mo3994a();

    /* renamed from: a */
    public abstract void mo3995a(Canvas canvas);

    /* renamed from: a */
    public abstract void mo3996a(Canvas canvas, C3213d[] c3213dArr);

    /* renamed from: b */
    public abstract void mo3997b(Canvas canvas);

    /* renamed from: c */
    public abstract void mo3998c(Canvas canvas);

    public C3248g(C3185a c3185a, C3275j c3275j) {
        super(c3275j);
        this.f14068g = c3185a;
        this.f14069h.setStyle(Style.FILL);
        this.f14071j = new Paint(4);
        this.f14072k = new Paint(1);
        this.f14072k.setColor(Color.rgb(63, 63, 63));
        this.f14072k.setTextAlign(Align.CENTER);
        this.f14072k.setTextSize(C3283i.m15928a(9.0f));
        this.f14070i = new Paint(1);
        this.f14070i.setStyle(Style.STROKE);
        this.f14070i.setStrokeWidth(2.0f);
        this.f14070i.setColor(Color.rgb(255, Opcodes.NEW, AVException.PUSH_MISCONFIGURED));
    }

    /* renamed from: a */
    protected boolean mo4002a(C2007e c2007e) {
        return ((float) c2007e.getData().m15597k()) < ((float) c2007e.getMaxVisibleCount()) * this.o.m15882q();
    }

    /* renamed from: b */
    protected void m15714b(C3220e c3220e) {
        this.f14072k.setTypeface(c3220e.mo3927q());
        this.f14072k.setTextSize(c3220e.mo3928r());
    }

    /* renamed from: a */
    public void m15710a(Canvas canvas, C3192f c3192f, float f, Entry entry, int i, float f2, float f3, int i2) {
        this.f14072k.setColor(i2);
        canvas.drawText(c3192f.mo3886a(f, entry, i, this.o), f2, f3, this.f14072k);
    }
}
