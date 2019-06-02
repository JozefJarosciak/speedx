package com.beastbikes.android.modules.user.filter.p155b;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.dto.C2412b;

/* compiled from: DynamicStickerViewBase */
/* renamed from: com.beastbikes.android.modules.user.filter.b.b */
public class C2440b extends View {
    /* renamed from: a */
    private final Paint f11456a;
    /* renamed from: b */
    private final DrawFilter f11457b;
    /* renamed from: c */
    protected Context f11458c;
    /* renamed from: d */
    private Bitmap f11459d;
    /* renamed from: e */
    private boolean f11460e;
    /* renamed from: f */
    private ActivityDTO f11461f;
    /* renamed from: g */
    private boolean f11462g;
    /* renamed from: h */
    private C2412b f11463h;

    public C2440b(Context context) {
        this(context, null);
    }

    public C2440b(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public C2440b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f11458c = context;
        this.f11456a = new Paint();
        this.f11456a.setAntiAlias(true);
        this.f11457b = new PaintFlagsDrawFilter(0, 3);
    }

    public void setReverseMode(boolean z) {
        this.f11462g = z;
        invalidate();
    }

    /* renamed from: a */
    public boolean m12300a() {
        return this.f11462g;
    }

    public Paint getPaint() {
        return this.f11456a;
    }

    public void setCover(Bitmap bitmap) {
        this.f11459d = bitmap;
        invalidate();
    }

    /* renamed from: b */
    public void m12301b() {
        this.f11459d = null;
        invalidate();
    }

    public Bitmap getCover() {
        return this.f11459d;
    }

    public C2412b getWaterMark() {
        return this.f11463h;
    }

    public void setWaterMark(C2412b c2412b) {
        this.f11463h = c2412b;
        invalidate();
    }

    public void setActivityDto(ActivityDTO activityDTO) {
        this.f11461f = activityDTO;
    }

    public ActivityDTO getActivityDTO() {
        return this.f11461f;
    }

    protected void onMeasure(int i, int i2) {
        int i3 = Integer.MAX_VALUE;
        int size = MeasureSpec.getSize(i);
        if (size <= 0) {
            size = Integer.MAX_VALUE;
        }
        int size2 = MeasureSpec.getSize(i2);
        if (size2 > 0) {
            i3 = size2;
        }
        size = Math.min(size, i3);
        setMeasuredDimension(size, size);
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        canvas.setDrawFilter(this.f11457b);
        super.onDraw(canvas);
        float width = (float) getWidth();
        float height = (float) getHeight();
        float f = width / height;
        if (this.f11459d != null) {
            float width2 = ((float) this.f11459d.getWidth()) / ((float) this.f11459d.getHeight());
            Rect rect = new Rect(0, 0, (int) width, (int) height);
            if (width2 > f) {
                height *= width2;
                rect.left = (int) ((width - height) / 2.0f);
                rect.right = (int) (((float) rect.left) + height);
            } else if (width2 < f) {
                f = width / width2;
                rect.right = (int) width;
                rect.top = (int) ((height - f) / 2.0f);
                rect.bottom = (int) (((float) rect.top) + f);
            }
            canvas.drawBitmap(this.f11459d, null, rect, this.f11456a);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void setIsChineseVersion(boolean z) {
        this.f11460e = z;
    }
}
