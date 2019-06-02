package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageColorMatrixFilter */
/* renamed from: jp.co.cyberagent.android.gpuimage.k */
public class C5428k extends C5421v {
    /* renamed from: a */
    private float f17477a;
    /* renamed from: b */
    private float[] f17478b;
    /* renamed from: c */
    private int f17479c;
    /* renamed from: d */
    private int f17480d;

    public C5428k(float f, float[] fArr) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nuniform lowp mat4 colorMatrix;\nuniform lowp float intensity;\n\nvoid main()\n{\n    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n    lowp vec4 outputColor = textureColor * colorMatrix;\n    \n    gl_FragColor = (intensity * outputColor) + ((1.0 - intensity) * textureColor);\n}");
        this.f17477a = f;
        this.f17478b = fArr;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17479c = GLES20.glGetUniformLocation(m19502k(), "colorMatrix");
        this.f17480d = GLES20.glGetUniformLocation(m19502k(), "intensity");
    }

    public void d_() {
        super.d_();
        m19563a(this.f17477a);
        m19564a(this.f17478b);
    }

    /* renamed from: a */
    public void m19563a(float f) {
        this.f17477a = f;
        m19486a(this.f17480d, f);
    }

    /* renamed from: a */
    public void m19564a(float[] fArr) {
        this.f17478b = fArr;
        m19495c(this.f17479c, fArr);
    }
}
