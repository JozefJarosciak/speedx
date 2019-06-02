package jp.co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;

/* compiled from: GPUImageVignetteFilter */
public class bc extends C5421v {
    /* renamed from: a */
    private int f17505a;
    /* renamed from: b */
    private PointF f17506b;
    /* renamed from: c */
    private int f17507c;
    /* renamed from: d */
    private float[] f17508d;
    /* renamed from: e */
    private int f17509e;
    /* renamed from: j */
    private float f17510j;
    /* renamed from: k */
    private int f17511k;
    /* renamed from: l */
    private float f17512l;

    public bc() {
        this(new PointF(), new float[]{0.0f, 0.0f, 0.0f}, 0.3f, 0.75f);
    }

    public bc(PointF pointF, float[] fArr, float f, float f2) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " uniform sampler2D inputImageTexture;\n varying highp vec2 textureCoordinate;\n \n uniform lowp vec2 vignetteCenter;\n uniform lowp vec3 vignetteColor;\n uniform highp float vignetteStart;\n uniform highp float vignetteEnd;\n \n void main()\n {\n     /*\n     lowp vec3 rgb = texture2D(inputImageTexture, textureCoordinate).rgb;\n     lowp float d = distance(textureCoordinate, vec2(0.5,0.5));\n     rgb *= (1.0 - smoothstep(vignetteStart, vignetteEnd, d));\n     gl_FragColor = vec4(vec3(rgb),1.0);\n      */\n     \n     lowp vec3 rgb = texture2D(inputImageTexture, textureCoordinate).rgb;\n     lowp float d = distance(textureCoordinate, vec2(vignetteCenter.x, vignetteCenter.y));\n     lowp float percent = smoothstep(vignetteStart, vignetteEnd, d);\n     gl_FragColor = vec4(mix(rgb.x, vignetteColor.x, percent), mix(rgb.y, vignetteColor.y, percent), mix(rgb.z, vignetteColor.z, percent), 1.0);\n }");
        this.f17506b = pointF;
        this.f17508d = fArr;
        this.f17510j = f;
        this.f17512l = f2;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17505a = GLES20.glGetUniformLocation(m19502k(), "vignetteCenter");
        this.f17507c = GLES20.glGetUniformLocation(m19502k(), "vignetteColor");
        this.f17509e = GLES20.glGetUniformLocation(m19502k(), "vignetteStart");
        this.f17511k = GLES20.glGetUniformLocation(m19502k(), "vignetteEnd");
        m19596a(this.f17506b);
        m19597a(this.f17508d);
        m19595a(this.f17510j);
        m19598b(this.f17512l);
    }

    /* renamed from: a */
    public void m19596a(PointF pointF) {
        this.f17506b = pointF;
        m19488a(this.f17505a, this.f17506b);
    }

    /* renamed from: a */
    public void m19597a(float[] fArr) {
        this.f17508d = fArr;
        m19490a(this.f17507c, this.f17508d);
    }

    /* renamed from: a */
    public void m19595a(float f) {
        this.f17510j = f;
        m19486a(this.f17509e, this.f17510j);
    }

    /* renamed from: b */
    public void m19598b(float f) {
        this.f17512l = f;
        m19486a(this.f17511k, this.f17512l);
    }
}
