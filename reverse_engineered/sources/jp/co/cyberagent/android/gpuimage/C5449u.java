package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageExposureFilter */
/* renamed from: jp.co.cyberagent.android.gpuimage.u */
public class C5449u extends C5421v {
    /* renamed from: a */
    private int f17540a;
    /* renamed from: b */
    private float f17541b;

    public C5449u() {
        this(1.0f);
    }

    public C5449u(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform highp float exposure;\n \n void main()\n {\n     highp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     \n     gl_FragColor = vec4(textureColor.rgb * pow(2.0, exposure), textureColor.w);\n } ");
        this.f17541b = f;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17540a = GLES20.glGetUniformLocation(m19502k(), "exposure");
    }

    public void d_() {
        super.d_();
        m19622a(this.f17541b);
    }

    /* renamed from: a */
    public void m19622a(float f) {
        this.f17541b = f;
        m19486a(this.f17540a, this.f17541b);
    }
}
