package com.beastbikes.android.modules.cycling.club.ui.widget;

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
import com.beastbikes.android.modules.preferences.ui.C2143a;

public class ImageCut extends C2143a {
    /* renamed from: h */
    private Paint f10041h = new Paint();
    /* renamed from: i */
    private Xfermode f10042i;
    /* renamed from: j */
    private Rect f10043j;
    /* renamed from: k */
    private RectF f10044k;
    /* renamed from: l */
    private boolean f10045l = false;

    public ImageCut(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f10041h.setColor(getResources().getColor(C1373R.color.activity_user_setting_img_cut));
        this.f10041h.setAntiAlias(true);
        this.f10042i = new PorterDuffXfermode(Mode.DST_OUT);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    @SuppressLint({"DrawAllocation"})
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.f10045l) {
            if (this.f10044k == null || this.f10044k.isEmpty()) {
                this.f10043j = new Rect(0, 0, getWidth(), getHeight());
                this.f10044k = new RectF(this.f10043j);
            }
            int saveLayer = canvas.saveLayer(this.f10044k, null, 31);
            this.f10041h.setColor(Color.parseColor("#99000000"));
            canvas.drawRect(this.f10043j, this.f10041h);
            this.f10041h.setXfermode(this.f10042i);
            this.f10041h.setColor(Color.parseColor("#ffffff"));
            float width = (float) getWidth();
            float width2 = (((float) getWidth()) - width) / 2.0f;
            float height = (((float) getHeight()) - ((width * 326.0f) / 640.0f)) / 2.0f;
            canvas.drawRect(width2, height, width2 + width, height + ((width * 326.0f) / 640.0f), this.f10041h);
            canvas.restoreToCount(saveLayer);
            this.f10041h.setXfermode(null);
        }
    }

    /* renamed from: a */
    public Bitmap m11032a() {
        int width;
        Paint paint = new Paint();
        this.f10045l = true;
        invalidate();
        setDrawingCacheEnabled(true);
        Bitmap copy = getDrawingCache().copy(getDrawingCache().getConfig(), false);
        setDrawingCacheEnabled(false);
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), (getWidth() * 326) / 640, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Rect rect = new Rect();
        if (copy != null) {
            width = copy.getWidth();
        } else {
            width = 0;
        }
        int width2 = (getWidth() - width) / 2;
        int height = (getHeight() - ((width * 326) / 640)) / 2;
        int i = width2 + width;
        width = ((width * 326) / 640) + height;
        rect.left = -width2;
        rect.top = -height;
        rect.right = i;
        rect.bottom = width;
        canvas.drawBitmap(copy, null, rect, paint);
        if (!(copy == null || copy.isRecycled())) {
            copy.recycle();
        }
        this.f10045l = false;
        return Bitmap.createScaledBitmap(createBitmap, getWidth(), (getWidth() * 326) / 640, false);
    }
}
