package com.beastbikes.android.widget.materialdesign.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;

/* compiled from: SingleHorizontalProgressDrawable */
/* renamed from: com.beastbikes.android.widget.materialdesign.progressbar.l */
class C2697l extends C2677j {
    /* renamed from: a */
    private static final RectF f12611a = new RectF(-180.0f, -1.0f, 180.0f, 1.0f);
    /* renamed from: i */
    private static final RectF f12612i = new RectF(-180.0f, -5.0f, 180.0f, 5.0f);
    /* renamed from: j */
    private int f12613j;
    /* renamed from: k */
    private int f12614k;
    /* renamed from: l */
    private boolean f12615l = true;
    /* renamed from: m */
    private float f12616m;

    public C2697l(Context context) {
        super(context);
        float f = context.getResources().getDisplayMetrics().density;
        this.f12613j = Math.round(3.2f * f);
        this.f12614k = Math.round(f * 16.0f);
        this.f12616m = C2698m.m13347b(16842803, context);
    }

    /* renamed from: a */
    public boolean mo3531a() {
        return this.f12615l;
    }

    /* renamed from: a */
    public void m13344a(boolean z) {
        if (this.f12615l != z) {
            this.f12615l = z;
            invalidateSelf();
        }
    }

    public int getIntrinsicHeight() {
        return this.b ? this.f12614k : this.f12613j;
    }

    protected boolean onLevelChange(int i) {
        invalidateSelf();
        return true;
    }

    /* renamed from: a */
    protected void mo3529a(Paint paint) {
        paint.setStyle(Style.FILL);
    }

    /* renamed from: a */
    protected void mo3528a(Canvas canvas, int i, int i2, Paint paint) {
        if (this.b) {
            canvas.scale(((float) i) / f12612i.width(), ((float) i2) / f12612i.height());
            canvas.translate(f12612i.width() / 2.0f, f12612i.height() / 2.0f);
        } else {
            canvas.scale(((float) i) / f12611a.width(), ((float) i2) / f12611a.height());
            canvas.translate(f12611a.width() / 2.0f, f12611a.height() / 2.0f);
        }
        if (this.f12615l) {
            paint.setAlpha(Math.round(((float) this.d) * this.f12616m));
            C2697l.m13340a(canvas, paint);
            paint.setAlpha(this.d);
        }
        m13341b(canvas, paint);
    }

    /* renamed from: a */
    private static void m13340a(Canvas canvas, Paint paint) {
        canvas.drawRect(f12611a, paint);
    }

    /* renamed from: b */
    private void m13341b(Canvas canvas, Paint paint) {
        int level = getLevel();
        if (level != 0) {
            int save = canvas.save();
            canvas.scale(((float) level) / 10000.0f, 1.0f, f12611a.left, 0.0f);
            canvas.drawRect(f12611a, paint);
            canvas.restoreToCount(save);
        }
    }
}
