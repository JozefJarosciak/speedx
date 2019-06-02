package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImagePixelationFilter */
public class an extends C5421v {
    /* renamed from: a */
    private int f17434a;
    /* renamed from: b */
    private int f17435b;
    /* renamed from: c */
    private float f17436c = 1.0f;
    /* renamed from: d */
    private int f17437d;

    public an() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "precision highp float;\nvarying vec2 textureCoordinate;\nuniform float imageWidthFactor;\nuniform float imageHeightFactor;\nuniform sampler2D inputImageTexture;\nuniform float pixel;\nvoid main()\n{\n  vec2 uv  = textureCoordinate.xy;\n  float dx = pixel * imageWidthFactor;\n  float dy = pixel * imageHeightFactor;\n  vec2 coord = vec2(dx * floor(uv.x / dx), dy * floor(uv.y / dy));\n  vec3 tc = texture2D(inputImageTexture, coord).xyz;\n  gl_FragColor = vec4(tc, 1.0);\n}");
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17434a = GLES20.glGetUniformLocation(m19502k(), "imageWidthFactor");
        this.f17435b = GLES20.glGetUniformLocation(m19502k(), "imageHeightFactor");
        this.f17437d = GLES20.glGetUniformLocation(m19502k(), "pixel");
        m19528a(this.f17436c);
    }

    /* renamed from: a */
    public void mo6684a(int i, int i2) {
        super.mo6684a(i, i2);
        m19486a(this.f17434a, 1.0f / ((float) i));
        m19486a(this.f17435b, 1.0f / ((float) i2));
    }

    /* renamed from: a */
    public void m19528a(float f) {
        this.f17436c = f;
        m19486a(this.f17437d, this.f17436c);
    }
}
