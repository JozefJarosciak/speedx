package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageMixBlendFilter */
public class ah extends bb {
    /* renamed from: d */
    private int f17426d;
    /* renamed from: e */
    private float f17427e;

    public ah(String str) {
        this(str, 0.5f);
    }

    public ah(String str, float f) {
        super(str);
        this.f17427e = f;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17426d = GLES20.glGetUniformLocation(m19502k(), "mixturePercent");
    }

    public void d_() {
        super.d_();
        m19520a(this.f17427e);
    }

    /* renamed from: a */
    public void m19520a(float f) {
        this.f17427e = f;
        m19486a(this.f17426d, this.f17427e);
    }
}
