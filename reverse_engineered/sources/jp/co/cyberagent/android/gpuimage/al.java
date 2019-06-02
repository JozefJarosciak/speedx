package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageOpacityFilter */
public class al extends C5421v {
    /* renamed from: a */
    private int f17432a;
    /* renamed from: b */
    private float f17433b;

    public al() {
        this(1.0f);
    }

    public al(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "  varying highp vec2 textureCoordinate;\n  \n  uniform sampler2D inputImageTexture;\n  uniform lowp float opacity;\n  \n  void main()\n  {\n      lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n      \n      gl_FragColor = vec4(textureColor.rgb, textureColor.a * opacity);\n  }\n");
        this.f17433b = f;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17432a = GLES20.glGetUniformLocation(m19502k(), "opacity");
    }

    public void d_() {
        super.d_();
        m19526a(this.f17433b);
    }

    /* renamed from: a */
    public void m19526a(float f) {
        this.f17433b = f;
        m19486a(this.f17432a, this.f17433b);
    }
}
