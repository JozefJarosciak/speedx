package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageBrightnessFilter */
/* renamed from: jp.co.cyberagent.android.gpuimage.e */
public class C5435e extends C5421v {
    /* renamed from: a */
    private int f17529a;
    /* renamed from: b */
    private float f17530b;

    public C5435e() {
        this(0.0f);
    }

    public C5435e(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float brightness;\n \n void main()\n {\n     lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4((textureColor.rgb + vec3(brightness)), textureColor.w);\n }");
        this.f17530b = f;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17529a = GLES20.glGetUniformLocation(m19502k(), "brightness");
    }

    public void d_() {
        super.d_();
        m19612a(this.f17530b);
    }

    /* renamed from: a */
    public void m19612a(float f) {
        this.f17530b = f;
        m19486a(this.f17529a, this.f17530b);
    }
}
