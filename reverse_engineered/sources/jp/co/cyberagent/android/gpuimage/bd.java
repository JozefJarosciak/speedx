package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageWhiteBalanceFilter */
public class bd extends C5421v {
    /* renamed from: a */
    private int f17513a;
    /* renamed from: b */
    private float f17514b;
    /* renamed from: c */
    private int f17515c;
    /* renamed from: d */
    private float f17516d;

    public bd() {
        this(5000.0f, 0.0f);
    }

    public bd(float f, float f2) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "uniform sampler2D inputImageTexture;\nvarying highp vec2 textureCoordinate;\n \nuniform lowp float temperature;\nuniform lowp float tint;\n\nconst lowp vec3 warmFilter = vec3(0.93, 0.54, 0.0);\n\nconst mediump mat3 RGBtoYIQ = mat3(0.299, 0.587, 0.114, 0.596, -0.274, -0.322, 0.212, -0.523, 0.311);\nconst mediump mat3 YIQtoRGB = mat3(1.0, 0.956, 0.621, 1.0, -0.272, -0.647, 1.0, -1.105, 1.702);\n\nvoid main()\n{\n\tlowp vec4 source = texture2D(inputImageTexture, textureCoordinate);\n\t\n\tmediump vec3 yiq = RGBtoYIQ * source.rgb; //adjusting tint\n\tyiq.b = clamp(yiq.b + tint*0.5226*0.1, -0.5226, 0.5226);\n\tlowp vec3 rgb = YIQtoRGB * yiq;\n\n\tlowp vec3 processed = vec3(\n\t\t(rgb.r < 0.5 ? (2.0 * rgb.r * warmFilter.r) : (1.0 - 2.0 * (1.0 - rgb.r) * (1.0 - warmFilter.r))), //adjusting temperature\n\t\t(rgb.g < 0.5 ? (2.0 * rgb.g * warmFilter.g) : (1.0 - 2.0 * (1.0 - rgb.g) * (1.0 - warmFilter.g))), \n\t\t(rgb.b < 0.5 ? (2.0 * rgb.b * warmFilter.b) : (1.0 - 2.0 * (1.0 - rgb.b) * (1.0 - warmFilter.b))));\n\n\tgl_FragColor = vec4(mix(rgb, processed, temperature), source.a);\n}");
        this.f17514b = f;
        this.f17516d = f2;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17513a = GLES20.glGetUniformLocation(m19502k(), "temperature");
        this.f17515c = GLES20.glGetUniformLocation(m19502k(), "tint");
        m19600a(this.f17514b);
        m19601b(this.f17516d);
    }

    /* renamed from: a */
    public void m19600a(float f) {
        this.f17514b = f;
        m19486a(this.f17513a, this.f17514b < 5000.0f ? (float) (4.0E-4d * (((double) this.f17514b) - 5000.0d)) : (float) (6.0E-5d * (((double) this.f17514b) - 5000.0d)));
    }

    /* renamed from: b */
    public void m19601b(float f) {
        this.f17516d = f;
        m19486a(this.f17515c, (float) (((double) this.f17516d) / 100.0d));
    }
}
