package jp.co.cyberagent.android.gpuimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.google.common.primitives.Ints;
import java.io.File;

public class GPUImageView extends GLSurfaceView {
    /* renamed from: a */
    private GPUImage f17389a;
    /* renamed from: b */
    private C5421v f17390b;
    /* renamed from: c */
    private float f17391c = 0.0f;

    public GPUImageView(Context context) {
        super(context);
        m19481a();
    }

    public GPUImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m19481a();
    }

    /* renamed from: a */
    private void m19481a() {
        this.f17389a = new GPUImage(getContext());
        this.f17389a.m19475a((GLSurfaceView) this);
    }

    protected void onMeasure(int i, int i2) {
        if (this.f17391c == 0.0f) {
            super.onMeasure(i, i2);
            return;
        }
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (((float) size) / this.f17391c < ((float) size2)) {
            size2 = Math.round(((float) size) / this.f17391c);
        } else {
            size = Math.round(((float) size2) * this.f17391c);
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(size, Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec(size2, Ints.MAX_POWER_OF_TWO));
    }

    public void setRatio(float f) {
        this.f17391c = f;
        requestLayout();
        this.f17389a.m19479b();
    }

    public void setFilter(C5421v c5421v) {
        this.f17390b = c5421v;
        this.f17389a.m19477a(c5421v);
        requestRender();
    }

    public C5421v getFilter() {
        return this.f17390b;
    }

    public void setImage(Bitmap bitmap) {
        this.f17389a.m19473a(bitmap);
    }

    public void setImage(Uri uri) {
        this.f17389a.m19474a(uri);
    }

    public void setImage(File file) {
        this.f17389a.m19476a(file);
    }

    public Bitmap getBitmapWithFilterApplied() {
        return this.f17389a.m19480c();
    }

    /* renamed from: a */
    public void m19482a(int i) {
        this.f17389a.m19472a((float) i);
    }
}
