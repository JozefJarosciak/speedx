package com.journeyapps.barcodescanner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.C4087R;
import com.journeyapps.barcodescanner.C4116c.C4119a;
import java.util.ArrayList;
import java.util.List;

public class ViewfinderView extends View {
    /* renamed from: a */
    protected static final String f8027a = ViewfinderView.class.getSimpleName();
    /* renamed from: b */
    protected static final int[] f8028b = new int[]{0, 64, 128, Opcodes.CHECKCAST, 255, Opcodes.CHECKCAST, 128, 64};
    /* renamed from: c */
    protected final Paint f8029c = new Paint(1);
    /* renamed from: d */
    protected Bitmap f8030d;
    /* renamed from: e */
    protected final int f8031e;
    /* renamed from: f */
    protected final int f8032f;
    /* renamed from: g */
    protected final int f8033g;
    /* renamed from: h */
    protected final int f8034h;
    /* renamed from: i */
    protected int f8035i;
    /* renamed from: j */
    protected List<ResultPoint> f8036j;
    /* renamed from: k */
    protected List<ResultPoint> f8037k;
    /* renamed from: l */
    protected C4116c f8038l;
    /* renamed from: m */
    protected Rect f8039m;
    /* renamed from: n */
    protected Rect f8040n;

    /* renamed from: com.journeyapps.barcodescanner.ViewfinderView$1 */
    class C41201 implements C4119a {
        /* renamed from: a */
        final /* synthetic */ ViewfinderView f14695a;

        C41201(ViewfinderView viewfinderView) {
            this.f14695a = viewfinderView;
        }

        /* renamed from: a */
        public void mo5925a() {
            this.f14695a.m9362a();
            this.f14695a.invalidate();
        }

        /* renamed from: b */
        public void mo5927b() {
        }

        /* renamed from: c */
        public void mo5928c() {
        }

        /* renamed from: a */
        public void mo5926a(Exception exception) {
        }
    }

    public ViewfinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = getResources();
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, C4087R.styleable.zxing_finder);
        this.f8031e = obtainStyledAttributes.getColor(C4087R.styleable.zxing_finder_zxing_viewfinder_mask, resources.getColor(C4087R.color.zxing_viewfinder_mask));
        this.f8032f = obtainStyledAttributes.getColor(C4087R.styleable.zxing_finder_zxing_result_view, resources.getColor(C4087R.color.zxing_result_view));
        this.f8033g = obtainStyledAttributes.getColor(C4087R.styleable.zxing_finder_zxing_viewfinder_laser, resources.getColor(C4087R.color.zxing_viewfinder_laser));
        this.f8034h = obtainStyledAttributes.getColor(C4087R.styleable.zxing_finder_zxing_possible_result_points, resources.getColor(C4087R.color.zxing_possible_result_points));
        obtainStyledAttributes.recycle();
        this.f8035i = 0;
        this.f8036j = new ArrayList(5);
        this.f8037k = null;
    }

    public void setCameraPreview(C4116c c4116c) {
        this.f8038l = c4116c;
        c4116c.m16500a(new C41201(this));
    }

    /* renamed from: a */
    protected void m9362a() {
        if (this.f8038l != null) {
            Rect framingRect = this.f8038l.getFramingRect();
            Rect previewFramingRect = this.f8038l.getPreviewFramingRect();
            if (framingRect != null && previewFramingRect != null) {
                this.f8039m = framingRect;
                this.f8040n = previewFramingRect;
            }
        }
    }

    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas canvas) {
        m9362a();
        if (this.f8039m != null && this.f8040n != null) {
            Rect rect = this.f8039m;
            Rect rect2 = this.f8040n;
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            this.f8029c.setColor(this.f8030d != null ? this.f8032f : this.f8031e);
            canvas.drawRect(0.0f, 0.0f, (float) width, (float) rect.top, this.f8029c);
            canvas.drawRect(0.0f, (float) rect.top, (float) rect.left, (float) (rect.bottom + 1), this.f8029c);
            canvas.drawRect((float) (rect.right + 1), (float) rect.top, (float) width, (float) (rect.bottom + 1), this.f8029c);
            canvas.drawRect(0.0f, (float) (rect.bottom + 1), (float) width, (float) height, this.f8029c);
            if (this.f8030d != null) {
                this.f8029c.setAlpha(160);
                canvas.drawBitmap(this.f8030d, null, rect, this.f8029c);
                return;
            }
            this.f8029c.setColor(this.f8033g);
            this.f8029c.setAlpha(f8028b[this.f8035i]);
            this.f8035i = (this.f8035i + 1) % f8028b.length;
            int height2 = (rect.height() / 2) + rect.top;
            canvas.drawRect((float) (rect.left + 2), (float) (height2 - 1), (float) (rect.right - 1), (float) (height2 + 2), this.f8029c);
            float width2 = ((float) rect.width()) / ((float) rect2.width());
            float height3 = ((float) rect.height()) / ((float) rect2.height());
            List<ResultPoint> list = this.f8036j;
            List<ResultPoint> list2 = this.f8037k;
            int i = rect.left;
            int i2 = rect.top;
            if (list.isEmpty()) {
                this.f8037k = null;
            } else {
                this.f8036j = new ArrayList(5);
                this.f8037k = list;
                this.f8029c.setAlpha(160);
                this.f8029c.setColor(this.f8034h);
                for (ResultPoint resultPoint : list) {
                    canvas.drawCircle((float) (((int) (resultPoint.getX() * width2)) + i), (float) (((int) (resultPoint.getY() * height3)) + i2), 6.0f, this.f8029c);
                }
            }
            if (list2 != null) {
                this.f8029c.setAlpha(80);
                this.f8029c.setColor(this.f8034h);
                for (ResultPoint resultPoint2 : list2) {
                    canvas.drawCircle((float) (((int) (resultPoint2.getX() * width2)) + i), (float) (((int) (resultPoint2.getY() * height3)) + i2), 3.0f, this.f8029c);
                }
            }
            postInvalidateDelayed(80, rect.left - 6, rect.top - 6, rect.right + 6, rect.bottom + 6);
        }
    }

    /* renamed from: a */
    public void m9363a(ResultPoint resultPoint) {
        List list = this.f8036j;
        list.add(resultPoint);
        int size = list.size();
        if (size > 20) {
            list.subList(0, size - 10).clear();
        }
    }
}
