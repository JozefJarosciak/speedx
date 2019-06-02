package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageRGBFilter */
public class ap extends C5421v {
    /* renamed from: a */
    private int f17440a;
    /* renamed from: b */
    private float f17441b;
    /* renamed from: c */
    private int f17442c;
    /* renamed from: d */
    private float f17443d;
    /* renamed from: e */
    private int f17444e;
    /* renamed from: j */
    private float f17445j;
    /* renamed from: k */
    private boolean f17446k;

    public ap() {
        this(1.0f, 1.0f, 1.0f);
    }

    public ap(float f, float f2, float f3) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "  varying highp vec2 textureCoordinate;\n  \n  uniform sampler2D inputImageTexture;\n  uniform highp float red;\n  uniform highp float green;\n  uniform highp float blue;\n  \n  void main()\n  {\n      highp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n      \n      gl_FragColor = vec4(textureColor.r * red, textureColor.g * green, textureColor.b * blue, 1.0);\n  }\n");
        this.f17446k = false;
        this.f17441b = f;
        this.f17443d = f2;
        this.f17445j = f3;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17440a = GLES20.glGetUniformLocation(m19502k(), "red");
        this.f17442c = GLES20.glGetUniformLocation(m19502k(), "green");
        this.f17444e = GLES20.glGetUniformLocation(m19502k(), "blue");
        this.f17446k = true;
        m19533a(this.f17441b);
        m19534b(this.f17443d);
        m19535c(this.f17445j);
    }

    /* renamed from: a */
    public void m19533a(float f) {
        this.f17441b = f;
        if (this.f17446k) {
            m19486a(this.f17440a, this.f17441b);
        }
    }

    /* renamed from: b */
    public void m19534b(float f) {
        this.f17443d = f;
        if (this.f17446k) {
            m19486a(this.f17442c, this.f17443d);
        }
    }

    /* renamed from: c */
    public void m19535c(float f) {
        this.f17445j = f;
        if (this.f17446k) {
            m19486a(this.f17444e, this.f17445j);
        }
    }
}
