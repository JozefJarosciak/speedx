package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageGammaFilter */
/* renamed from: jp.co.cyberagent.android.gpuimage.x */
public class C5455x extends C5421v {
    /* renamed from: a */
    private int f17557a;
    /* renamed from: b */
    private float f17558b;

    public C5455x() {
        this(1.2f);
    }

    public C5455x(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float gamma;\n \n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4(pow(textureColor.rgb, vec3(gamma)), textureColor.w);\n }");
        this.f17558b = f;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17557a = GLES20.glGetUniformLocation(m19502k(), "gamma");
    }

    public void d_() {
        super.d_();
        m19624a(this.f17558b);
    }

    /* renamed from: a */
    public void m19624a(float f) {
        this.f17558b = f;
        m19486a(this.f17557a, this.f17558b);
    }
}
