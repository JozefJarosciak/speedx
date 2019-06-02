package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageContrastFilter */
/* renamed from: jp.co.cyberagent.android.gpuimage.l */
public class C5441l extends C5421v {
    /* renamed from: a */
    private int f17537a;
    /* renamed from: b */
    private float f17538b;

    public C5441l() {
        this(1.2f);
    }

    public C5441l(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float contrast;\n \n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4(((textureColor.rgb - vec3(0.5)) * contrast + vec3(0.5)), textureColor.w);\n }");
        this.f17538b = f;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17537a = GLES20.glGetUniformLocation(m19502k(), "contrast");
    }

    public void d_() {
        super.d_();
        m19618a(this.f17538b);
    }

    /* renamed from: a */
    public void m19618a(float f) {
        this.f17538b = f;
        m19486a(this.f17537a, this.f17538b);
    }
}
