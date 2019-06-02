package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImagePosterizeFilter */
public class ao extends C5421v {
    /* renamed from: a */
    private int f17438a;
    /* renamed from: b */
    private int f17439b;

    public ao() {
        this(10);
    }

    public ao(int i) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\nuniform highp float colorLevels;\n\nvoid main()\n{\n   highp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n   \n   gl_FragColor = floor((textureColor * colorLevels) + vec4(0.5)) / colorLevels;\n}");
        this.f17439b = i;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17438a = GLES20.glGetUniformLocation(m19502k(), "colorLevels");
        m19531a(this.f17439b);
    }

    /* renamed from: a */
    public void m19531a(int i) {
        this.f17439b = i;
        m19486a(this.f17438a, (float) i);
    }
}
