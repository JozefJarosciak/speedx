package com.beastbikes.android.modules.preferences.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.beastbikes.android.C1373R;

public class ImageCut extends C2143a {
    /* renamed from: h */
    private Paint f10930h = new Paint();
    /* renamed from: i */
    private int f10931i = 200;
    /* renamed from: j */
    private Xfermode f10932j;
    /* renamed from: k */
    private Rect f10933k;
    /* renamed from: l */
    private RectF f10934l;
    /* renamed from: m */
    private boolean f10935m = false;

    public ImageCut(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10930h.setColor(getResources().getColor(C1373R.color.activity_user_setting_img_cut));
        this.f10930h.setAntiAlias(true);
        this.f10932j = new PorterDuffXfermode(Mode.DST_OUT);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.f10935m) {
            if (this.f10934l == null || this.f10934l.isEmpty()) {
                this.f10933k = new Rect(0, 0, getWidth(), getHeight());
                this.f10934l = new RectF(this.f10933k);
            }
            int saveLayer = canvas.saveLayer(this.f10934l, null, 31);
            this.f10930h.setColor(Color.parseColor("#99000000"));
            canvas.drawRect(this.f10933k, this.f10930h);
            this.f10930h.setXfermode(this.f10932j);
            this.f10930h.setColor(Color.parseColor("#ffffff"));
            this.f10931i = (getWidth() / 4) - 20;
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) this.f10931i, this.f10930h);
            canvas.restoreToCount(saveLayer);
            this.f10930h.setXfermode(null);
        }
    }

    /* renamed from: a */
    public Bitmap m11812a() {
        Paint paint = new Paint();
        this.f10935m = true;
        invalidate();
        setDrawingCacheEnabled(true);
        if (getDrawingCache() == null) {
            return null;
        }
        Bitmap copy = getDrawingCache().copy(getDrawingCache().getConfig(), false);
        setDrawingCacheEnabled(false);
        Bitmap createBitmap = Bitmap.createBitmap(this.f10931i * 2, this.f10931i * 2, Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(copy, null, new RectF((float) (((-copy.getWidth()) / 2) + this.f10931i), (float) (((-getHeight()) / 2) + this.f10931i), (float) ((copy.getWidth() - (copy.getWidth() / 2)) + this.f10931i), (float) ((getHeight() - (getHeight() / 2)) + this.f10931i)), paint);
        if (copy != null) {
            copy.recycle();
        }
        this.f10935m = false;
        return Bitmap.createScaledBitmap(createBitmap, 640, 640, false);
    }
}
