package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;

/* compiled from: GPUImageSaturationFilter */
public class as extends C5421v {
    /* renamed from: a */
    private int f17475a;
    /* renamed from: b */
    private float f17476b;

    public as() {
        this(1.0f);
    }

    public as(float f) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", " varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform lowp float saturation;\n \n // Values from \"Graphics Shaders: Theory and Practice\" by Bailey and Cunningham\n const mediump vec3 luminanceWeighting = vec3(0.2125, 0.7154, 0.0721);\n \n void main()\n {\n    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n    lowp float luminance = dot(textureColor.rgb, luminanceWeighting);\n    lowp vec3 greyScaleColor = vec3(luminance);\n    \n    gl_FragColor = vec4(mix(greyScaleColor, textureColor.rgb, saturation), textureColor.w);\n     \n }");
        this.f17476b = f;
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17475a = GLES20.glGetUniformLocation(m19502k(), "saturation");
    }

    public void d_() {
        super.d_();
        m19561a(this.f17476b);
    }

    /* renamed from: a */
    public void m19561a(float f) {
        this.f17476b = f;
        m19486a(this.f17475a, this.f17476b);
    }
}
