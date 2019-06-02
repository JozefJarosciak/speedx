package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImage3x3TextureSamplingFilter */
/* renamed from: jp.co.cyberagent.android.gpuimage.b */
public class C5422b extends C5421v {
    /* renamed from: a */
    private int f17407a;
    /* renamed from: b */
    private int f17408b;
    /* renamed from: c */
    private boolean f17409c;
    /* renamed from: d */
    private float f17410d;
    /* renamed from: e */
    private float f17411e;
    /* renamed from: j */
    private float f17412j;

    public C5422b() {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}");
    }

    public C5422b(String str) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n\nuniform highp float texelWidth; \nuniform highp float texelHeight; \n\nvarying vec2 textureCoordinate;\nvarying vec2 leftTextureCoordinate;\nvarying vec2 rightTextureCoordinate;\n\nvarying vec2 topTextureCoordinate;\nvarying vec2 topLeftTextureCoordinate;\nvarying vec2 topRightTextureCoordinate;\n\nvarying vec2 bottomTextureCoordinate;\nvarying vec2 bottomLeftTextureCoordinate;\nvarying vec2 bottomRightTextureCoordinate;\n\nvoid main()\n{\n    gl_Position = position;\n\n    vec2 widthStep = vec2(texelWidth, 0.0);\n    vec2 heightStep = vec2(0.0, texelHeight);\n    vec2 widthHeightStep = vec2(texelWidth, texelHeight);\n    vec2 widthNegativeHeightStep = vec2(texelWidth, -texelHeight);\n\n    textureCoordinate = inputTextureCoordinate.xy;\n    leftTextureCoordinate = inputTextureCoordinate.xy - widthStep;\n    rightTextureCoordinate = inputTextureCoordinate.xy + widthStep;\n\n    topTextureCoordinate = inputTextureCoordinate.xy - heightStep;\n    topLeftTextureCoordinate = inputTextureCoordinate.xy - widthHeightStep;\n    topRightTextureCoordinate = inputTextureCoordinate.xy + widthNegativeHeightStep;\n\n    bottomTextureCoordinate = inputTextureCoordinate.xy + heightStep;\n    bottomLeftTextureCoordinate = inputTextureCoordinate.xy - widthNegativeHeightStep;\n    bottomRightTextureCoordinate = inputTextureCoordinate.xy + widthHeightStep;\n}", str);
        this.f17409c = false;
        this.f17412j = 1.0f;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17407a = GLES20.glGetUniformLocation(m19502k(), "texelWidth");
        this.f17408b = GLES20.glGetUniformLocation(m19502k(), "texelHeight");
        if (this.f17410d != 0.0f) {
            m19503l();
        }
    }

    /* renamed from: a */
    public void mo6684a(int i, int i2) {
        super.mo6684a(i, i2);
        if (!this.f17409c) {
            m19505a(this.f17412j);
        }
    }

    /* renamed from: a */
    public void m19505a(float f) {
        this.f17412j = f;
        this.f17410d = f / ((float) m19500i());
        this.f17411e = f / ((float) m19501j());
        m19503l();
    }

    /* renamed from: l */
    private void m19503l() {
        m19486a(this.f17407a, this.f17410d);
        m19486a(this.f17408b, this.f17411e);
    }
}
