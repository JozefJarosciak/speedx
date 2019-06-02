package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageMonochromeFilter */
public class ai extends C5421v {
    /* renamed from: a */
    private int f17428a;
    /* renamed from: b */
    private float f17429b;
    /* renamed from: c */
    private int f17430c;
    /* renamed from: d */
    private float[] f17431d;

    public ai() {
        this(1.0f, new float[]{0.6f, 0.45f, 0.3f, 1.0f});
    }

    public ai(float f, float[] fArr) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " precision lowp float;\n  \n  varying highp vec2 textureCoordinate;\n  \n  uniform sampler2D inputImageTexture;\n  uniform float intensity;\n  uniform vec3 filterColor;\n  \n  const mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n  \n  void main()\n  {\n \t//desat, then apply overlay blend\n \tlowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n \tfloat luminance = dot(textureColor.rgb, luminanceWeighting);\n \t\n \tlowp vec4 desat = vec4(vec3(luminance), 1.0);\n \t\n \t//overlay\n \tlowp vec4 outputColor = vec4(\n                                  (desat.r < 0.5 ? (2.0 * desat.r * filterColor.r) : (1.0 - 2.0 * (1.0 - desat.r) * (1.0 - filterColor.r))),\n                                  (desat.g < 0.5 ? (2.0 * desat.g * filterColor.g) : (1.0 - 2.0 * (1.0 - desat.g) * (1.0 - filterColor.g))),\n                                  (desat.b < 0.5 ? (2.0 * desat.b * filterColor.b) : (1.0 - 2.0 * (1.0 - desat.b) * (1.0 - filterColor.b))),\n                                  1.0\n                                  );\n \t\n \t//which is better, or are they equal?\n \tgl_FragColor = vec4( mix(textureColor.rgb, outputColor.rgb, intensity), textureColor.a);\n  }");
        this.f17429b = f;
        this.f17431d = fArr;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17428a = GLES20.glGetUniformLocation(m19502k(), "intensity");
        this.f17430c = GLES20.glGetUniformLocation(m19502k(), "filterColor");
    }

    public void d_() {
        super.d_();
        m19522a(1.0f);
        m19524a(new float[]{0.6f, 0.45f, 0.3f, 1.0f});
    }

    /* renamed from: a */
    public void m19522a(float f) {
        this.f17429b = f;
        m19486a(this.f17428a, this.f17429b);
    }

    /* renamed from: a */
    public void m19524a(float[] fArr) {
        this.f17431d = fArr;
        m19523a(this.f17431d[0], this.f17431d[1], this.f17431d[2]);
    }

    /* renamed from: a */
    public void m19523a(float f, float f2, float f3) {
        m19490a(this.f17430c, new float[]{f, f2, f3});
    }
}
