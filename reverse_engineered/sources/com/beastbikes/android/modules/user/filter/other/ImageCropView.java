package com.beastbikes.android.modules.user.filter.other;

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

public class ImageCropView extends C2442a {
    /* renamed from: j */
    private Paint f11486j = new Paint();
    /* renamed from: k */
    private int f11487k = 200;
    /* renamed from: l */
    private Xfermode f11488l;
    /* renamed from: m */
    private Rect f11489m;
    /* renamed from: n */
    private RectF f11490n;
    /* renamed from: o */
    private boolean f11491o = false;

    public ImageCropView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f11486j.setColor(getResources().getColor(C1373R.color.activity_user_setting_img_cut));
        this.f11486j.setAntiAlias(true);
        this.f11488l = new PorterDuffXfermode(Mode.DST_OUT);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.f11491o) {
            if (this.f11490n == null || this.f11490n.isEmpty()) {
                this.f11489m = new Rect(0, 0, getWidth(), getHeight());
                this.f11490n = new RectF(this.f11489m);
            }
            int saveLayer = canvas.saveLayer(this.f11490n, null, 31);
            this.f11486j.setColor(Color.parseColor("#99000000"));
            canvas.drawRect(this.f11489m, this.f11486j);
            this.f11486j.setXfermode(this.f11488l);
            this.f11486j.setColor(Color.parseColor("#ffffff"));
            this.f11487k = (getWidth() / 2) - 20;
            canvas.drawCircle((float) (getWidth() / 2), (float) (getHeight() / 2), (float) this.f11487k, this.f11486j);
            canvas.drawRect(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight(), this.f11486j);
            canvas.restoreToCount(saveLayer);
            this.f11486j.setXfermode(null);
            this.f11486j.setColor(-1);
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            int i = width / 3;
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (i2 < 100) {
                canvas.drawLine(0.0f, (float) i4, (float) width, (float) i4, this.f11486j);
                canvas.drawLine((float) i3, 0.0f, (float) i3, (float) height, this.f11486j);
                i2++;
                i3 += i;
                i4 += i;
            }
        }
    }

    /* renamed from: b */
    public Bitmap m12316b() {
        Paint paint = new Paint();
        this.f11491o = true;
        invalidate();
        setDrawingCacheEnabled(true);
        Bitmap copy = getDrawingCache().copy(getDrawingCache().getConfig(), false);
        setDrawingCacheEnabled(false);
        Bitmap createBitmap = Bitmap.createBitmap(this.f11487k * 2, this.f11487k * 2, Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(copy, null, new RectF((float) (((-copy.getWidth()) / 2) + this.f11487k), (float) (((-getHeight()) / 2) + this.f11487k), (float) ((copy.getWidth() - (copy.getWidth() / 2)) + this.f11487k), (float) ((getHeight() - (getHeight() / 2)) + this.f11487k)), paint);
        if (copy != null) {
            copy.recycle();
        }
        this.f11491o = false;
        return Bitmap.createScaledBitmap(createBitmap, 640, 640, false);
    }
}
