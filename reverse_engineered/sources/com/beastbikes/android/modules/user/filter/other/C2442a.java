package com.beastbikes.android.modules.user.filter.other;

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
/* renamed from: com.beastbikes.android.modules.user.filter.other.a */
public class C2442a extends ImageView {
    /* renamed from: a */
    final PointF f11475a = new PointF();
    /* renamed from: b */
    final PointF f11476b = new PointF();
    /* renamed from: c */
    final Matrix f11477c = new Matrix();
    /* renamed from: d */
    final Matrix f11478d = new Matrix();
    /* renamed from: e */
    float f11479e = 1.0f;
    /* renamed from: f */
    int f11480f = 0;
    /* renamed from: g */
    PointF f11481g = new PointF();
    /* renamed from: h */
    PointF f11482h = new PointF();
    /* renamed from: i */
    float f11483i = 1.0f;
    /* renamed from: j */
    private Bitmap f11484j = null;
    /* renamed from: k */
    private DisplayMetrics f11485k;

    /* compiled from: CutImageView */
    /* renamed from: com.beastbikes.android.modules.user.filter.other.a$1 */
    class C24451 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2442a f11510a;

        C24451(C2442a c2442a) {
            this.f11510a = c2442a;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction() & 255) {
                case 0:
                    this.f11510a.f11478d.set(this.f11510a.f11477c);
                    this.f11510a.f11476b.set(motionEvent.getX(), motionEvent.getY());
                    this.f11510a.f11481g.set(motionEvent.getX(), motionEvent.getY());
                    this.f11510a.f11480f = 1;
                    break;
                case 2:
                    if (this.f11510a.f11480f != 1) {
                        if (this.f11510a.f11480f == 2) {
                            float a = this.f11510a.m12310a(motionEvent);
                            if (a > 10.0f) {
                                this.f11510a.f11477c.set(this.f11510a.f11478d);
                                a /= this.f11510a.f11483i;
                                this.f11510a.f11477c.postScale(a, a, this.f11510a.f11482h.x, this.f11510a.f11482h.y);
                                break;
                            }
                        }
                    }
                    if (motionEvent.getX() - this.f11510a.f11476b.x > 0.0f) {
                        this.f11510a.f11477c.set(this.f11510a.f11478d);
                        this.f11510a.f11475a.offset(motionEvent.getX() - this.f11510a.f11476b.x, motionEvent.getY() - this.f11510a.f11476b.y);
                    } else {
                        this.f11510a.f11475a.offset(this.f11510a.f11476b.x - motionEvent.getX(), this.f11510a.f11476b.y - motionEvent.getY());
                    }
                    if (this.f11510a.f11475a.x > 0.0f && this.f11510a.f11475a.y > 0.0f) {
                        this.f11510a.f11477c.postTranslate(0.0f, 0.0f);
                    } else if (this.f11510a.f11475a.x > 0.0f && this.f11510a.f11475a.y < 0.0f) {
                        this.f11510a.f11477c.postTranslate(0.0f, motionEvent.getY() - this.f11510a.f11481g.y);
                    } else if (this.f11510a.f11475a.x >= 0.0f || this.f11510a.f11475a.y <= 0.0f) {
                        this.f11510a.f11477c.postTranslate(motionEvent.getX() - this.f11510a.f11481g.x, motionEvent.getY() - this.f11510a.f11481g.y);
                    } else {
                        this.f11510a.f11477c.postTranslate(motionEvent.getX() - this.f11510a.f11481g.x, 0.0f);
                    }
                    this.f11510a.f11476b.set(motionEvent.getX(), motionEvent.getY());
                    break;
                    break;
                case 5:
                    this.f11510a.f11483i = this.f11510a.m12310a(motionEvent);
                    if (this.f11510a.m12310a(motionEvent) > 10.0f) {
                        this.f11510a.f11478d.set(this.f11510a.f11477c);
                        this.f11510a.m12312a(this.f11510a.f11482h, motionEvent);
                        this.f11510a.f11480f = 2;
                        break;
                    }
                    break;
                case 6:
                    this.f11510a.f11480f = 0;
                    break;
            }
            this.f11510a.setImageMatrix(this.f11510a.f11477c);
            this.f11510a.invalidate();
            return true;
        }
    }

    public C2442a(Context context) {
        super(context);
        m12314a();
    }

    public C2442a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m12314a();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    /* renamed from: a */
    public void m12314a() {
        this.f11485k = getContext().getResources().getDisplayMetrics();
        BitmapDrawable bitmapDrawable = (BitmapDrawable) getDrawable();
        if (bitmapDrawable != null) {
            this.f11484j = bitmapDrawable.getBitmap();
        }
        setScaleType(ScaleType.MATRIX);
        setImageBitmap(this.f11484j);
        if (this.f11484j != null) {
            m12315a(true, false);
        }
        setImageMatrix(this.f11477c);
        setOnTouchListener(new C24451(this));
    }

    /* renamed from: a */
    protected void m12315a(boolean z, boolean z2) {
        int i;
        float f = 0.0f;
        Matrix matrix = new Matrix();
        matrix.set(this.f11477c);
        RectF rectF = new RectF(0.0f, 0.0f, (float) this.f11484j.getWidth(), (float) this.f11484j.getHeight());
        matrix.mapRect(rectF);
        float height = rectF.height();
        float width = rectF.width();
        if (z2) {
            i = this.f11485k.heightPixels;
            if (height < ((float) i)) {
                height = ((((float) i) - height) / 2.0f) - rectF.top;
            } else if (rectF.top > 0.0f) {
                height = -rectF.top;
            } else if (rectF.bottom < ((float) i)) {
                height = ((float) getHeight()) - rectF.bottom;
            }
            if (z) {
                i = this.f11485k.widthPixels;
                if (width < ((float) i)) {
                    f = ((((float) i) - width) / 2.0f) - rectF.left;
                } else if (rectF.left > 0.0f) {
                    f = -rectF.left;
                } else if (rectF.right < ((float) i)) {
                    f = ((float) getWidth()) - rectF.right;
                }
            }
            this.f11477c.postTranslate(f, height);
        }
        height = 0.0f;
        if (z) {
            i = this.f11485k.widthPixels;
            if (width < ((float) i)) {
                f = ((((float) i) - width) / 2.0f) - rectF.left;
            } else if (rectF.left > 0.0f) {
                f = -rectF.left;
            } else if (rectF.right < ((float) i)) {
                f = ((float) getWidth()) - rectF.right;
            }
        }
        this.f11477c.postTranslate(f, height);
    }

    /* renamed from: a */
    private float m12310a(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }

    /* renamed from: a */
    private void m12312a(PointF pointF, MotionEvent motionEvent) {
        pointF.set((motionEvent.getX(0) + motionEvent.getX(1)) / 2.0f, (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f);
    }
}
