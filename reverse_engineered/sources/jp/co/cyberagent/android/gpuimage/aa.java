package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageHighlightShadowFilter */
public class aa extends C5421v {
    /* renamed from: a */
    private int f17415a;
    /* renamed from: b */
    private float f17416b;
    /* renamed from: c */
    private int f17417c;
    /* renamed from: d */
    private float f17418d;

    public aa() {
        this(0.0f, 1.0f);
    }

    public aa(float f, float f2) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " uniform sampler2D inputImageTexture;\n varying highp vec2 textureCoordinate;\n  \n uniform lowp float shadows;\n uniform lowp float highlights;\n \n const mediump vec3 luminanceWeighting = vec3(0.3, 0.3, 0.3);\n \n void main()\n {\n \tlowp vec4 source = texture2D(inputImageTexture, textureCoordinate);\n \tmediump float luminance = dot(source.rgb, luminanceWeighting);\n \n \tmediump float shadow = clamp((pow(luminance, 1.0/(shadows+1.0)) + (-0.76)*pow(luminance, 2.0/(shadows+1.0))) - luminance, 0.0, 1.0);\n \tmediump float highlight = clamp((1.0 - (pow(1.0-luminance, 1.0/(2.0-highlights)) + (-0.8)*pow(1.0-luminance, 2.0/(2.0-highlights)))) - luminance, -1.0, 0.0);\n \tlowp vec3 result = vec3(0.0, 0.0, 0.0) + ((luminance + shadow + highlight) - 0.0) * ((source.rgb - vec3(0.0, 0.0, 0.0))/(luminance - 0.0));\n \n \tgl_FragColor = vec4(result.rgb, source.a);\n }");
        this.f17418d = f2;
        this.f17416b = f;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17417c = GLES20.glGetUniformLocation(m19502k(), "highlights");
        this.f17415a = GLES20.glGetUniformLocation(m19502k(), "shadows");
    }

    public void d_() {
        super.d_();
        m19510a(this.f17418d);
        m19511b(this.f17416b);
    }

    /* renamed from: a */
    public void m19510a(float f) {
        this.f17418d = f;
        m19486a(this.f17417c, this.f17418d);
    }

    /* renamed from: b */
    public void m19511b(float f) {
        this.f17416b = f;
        m19486a(this.f17415a, this.f17416b);
    }
}
