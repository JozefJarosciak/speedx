package com.beastbikes.android.modules.preferences.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

/* compiled from: CutImageView */
/* renamed from: com.beastbikes.android.modules.preferences.ui.a */
public class C2143a extends ImageView {
    /* renamed from: a */
    Matrix f10032a = new Matrix();
    /* renamed from: b */
    Matrix f10033b = new Matrix();
    /* renamed from: c */
    float f10034c = 1.0f;
    /* renamed from: d */
    int f10035d = 0;
    /* renamed from: e */
    PointF f10036e = new PointF();
    /* renamed from: f */
    PointF f10037f = new PointF();
    /* renamed from: g */
    float f10038g = 1.0f;
    /* renamed from: h */
    private Bitmap f10039h = null;
    /* renamed from: i */
    private DisplayMetrics f10040i;

    /* compiled from: CutImageView */
    /* renamed from: com.beastbikes.android.modules.preferences.ui.a$1 */
    class C23131 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2143a f11011a;

        C23131(C2143a c2143a) {
            this.f11011a = c2143a;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.f11011a.f10033b.set(this.f11011a.f10032a);
                    this.f11011a.f10036e.set(motionEvent.getX(), motionEvent.getY());
                    this.f11011a.f10035d = 1;
                    break;
                case 2:
                    if (this.f11011a.f10035d != 1) {
                        if (this.f11011a.f10035d == 2) {
                            float a = this.f11011a.m11026a(motionEvent);
                            if (a > 10.0f) {
                                this.f11011a.f10032a.set(this.f11011a.f10033b);
                                a /= this.f11011a.f10038g;
                                this.f11011a.f10032a.postScale(a, a, this.f11011a.f10037f.x, this.f11011a.f10037f.y);
                                break;
                            }
                        }
                    }
                    this.f11011a.f10032a.set(this.f11011a.f10033b);
                    this.f11011a.f10032a.postTranslate(motionEvent.getX() - this.f11011a.f10036e.x, motionEvent.getY() - this.f11011a.f10036e.y);
                    break;
                    break;
                case 5:
                    this.f11011a.f10038g = this.f11011a.m11026a(motionEvent);
                    if (this.f11011a.m11026a(motionEvent) > 10.0f) {
                        this.f11011a.f10033b.set(this.f11011a.f10032a);
                        this.f11011a.m11028a(this.f11011a.f10037f, motionEvent);
                        this.f11011a.f10035d = 2;
                        break;
                    }
                    break;
                case 6:
                    this.f11011a.f10035d = 0;
                    break;
            }
            this.f11011a.setImageMatrix(this.f11011a.f10032a);
            this.f11011a.invalidate();
            return true;
        }
    }

    public C2143a(Context context) {
        super(context);
        m11031b();
    }

    public C2143a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m11031b();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    /* renamed from: b */
    public void m11031b() {
        this.f10040i = getContext().getResources().getDisplayMetrics();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
        if (bitmapDrawable != null) {
            this.f10039h = bitmapDrawable.getBitmap();
        }
        setScaleType(ScaleType.MATRIX);
        setImageBitmap(this.f10039h);
        if (this.f10039h != null) {
            m11030a(true, true);
        }
        setImageMatrix(this.f10032a);
        setOnTouchListener(new C23131(this));
    }

    /* renamed from: a */
    protected void m11030a(boolean z, boolean z2) {
        int i;
        float f = 0.0f;
        Matrix matrix = new Matrix();
        matrix.set(this.f10032a);
        RectF rectF = new RectF(0.0f, 0.0f, (float) this.f10039h.getWidth(), (float) this.f10039h.getHeight());
        matrix.mapRect(rectF);
        float height = rectF.height();
        float width = rectF.width();
        if (z2) {
            i = this.f10040i.heightPixels;
            if (height < ((float) i)) {
                height = ((((float) i) - height) / 2.0f) - rectF.top;
            } else if (rectF.top > 0.0f) {
                height = -rectF.top;
            } else if (rectF.bottom < ((float) i)) {
                height = ((float) getHeight()) - rectF.bottom;
            }
            if (z) {
                i = this.f10040i.widthPixels;
                if (width < ((float) i)) {
                    f = ((((float) i) - width) / 2.0f) - rectF.left;
                } else if (rectF.left > 0.0f) {
                    f = -rectF.left;
                } else if (rectF.right < ((float) i)) {
                    f = ((float) getWidth()) - rectF.right;
                }
            }
            this.f10032a.postTranslate(f, height);
        }
        height = 0.0f;
        if (z) {
            i = this.f10040i.widthPixels;
            if (width < ((float) i)) {
                f = ((((float) i) - width) / 2.0f) - rectF.left;
            } else if (rectF.left > 0.0f) {
                f = -rectF.left;
            } else if (rectF.right < ((float) i)) {
                f = ((float) getWidth()) - rectF.right;
            }
        }
        this.f10032a.postTranslate(f, height);
    }

    /* renamed from: a */
    private float m11026a(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    /* renamed from: a */
    private void m11028a(PointF pointF, MotionEvent motionEvent) {
        pointF.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
    }
}
