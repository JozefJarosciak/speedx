package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageChromaKeyBlendFilter */
/* renamed from: jp.co.cyberagent.android.gpuimage.f */
public class C5436f extends bb {
    /* renamed from: d */
    private int f17531d;
    /* renamed from: e */
    private int f17532e;
    /* renamed from: j */
    private int f17533j;
    /* renamed from: k */
    private float f17534k = 0.1f;
    /* renamed from: l */
    private float f17535l = 0.3f;
    /* renamed from: m */
    private float[] f17536m = new float[]{0.0f, 1.0f, 0.0f};

    public C5436f() {
        super("varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n highp float lum(lowp vec3 c) {\n     return dot(c, vec3(0.3, 0.59, 0.11));\n }\n \n lowp vec3 clipcolor(lowp vec3 c) {\n     highp float l = lum(c);\n     lowp float n = min(min(c.r, c.g), c.b);\n     lowp float x = max(max(c.r, c.g), c.b);\n     \n     if (n < 0.0) {\n         c.r = l + ((c.r - l) * l) / (l - n);\n         c.g = l + ((c.g - l) * l) / (l - n);\n         c.b = l + ((c.b - l) * l) / (l - n);\n     }\n     if (x > 1.0) {\n         c.r = l + ((c.r - l) * (1.0 - l)) / (x - l);\n         c.g = l + ((c.g - l) * (1.0 - l)) / (x - l);\n         c.b = l + ((c.b - l) * (1.0 - l)) / (x - l);\n     }\n     \n     return c;\n }\n\n lowp vec3 setlum(lowp vec3 c, highp float l) {\n     highp float d = l - lum(c);\n     c = c + vec3(d);\n     return clipcolor(c);\n }\n \n void main()\n {\n   highp vec4 baseColor = texture2D(inputImageTexture, textureCoordinate);\n   highp vec4 overlayColor = texture2D(inputImageTexture2, textureCoordinate2);\n\n     gl_FragColor = vec4(baseColor.rgb * (1.0 - overlayColor.a) + setlum(overlayColor.rgb, lum(baseColor.rgb)) * overlayColor.a, baseColor.a);\n }");
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17531d = GLES20.glGetUniformLocation(m19502k(), "thresholdSensitivity");
        this.f17532e = GLES20.glGetUniformLocation(m19502k(), "smoothing");
        this.f17533j = GLES20.glGetUniformLocation(m19502k(), "colorToReplace");
    }

    public void d_() {
        super.d_();
        m19614a(this.f17534k);
        m19616b(this.f17535l);
        m19615a(this.f17536m[0], this.f17536m[1], this.f17536m[2]);
    }

    /* renamed from: a */
    public void m19614a(float f) {
        this.f17534k = f;
        m19486a(this.f17532e, this.f17534k);
    }

    /* renamed from: b */
    public void m19616b(float f) {
        this.f17535l = f;
        m19486a(this.f17531d, this.f17535l);
    }

    /* renamed from: a */
    public void m19615a(float f, float f2, float f3) {
        this.f17536m = new float[]{f, f2, f3};
        m19490a(this.f17533j, this.f17536m);
    }
}
