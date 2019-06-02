package jp.co.cyberagent.android.gpuimage;

/* compiled from: GPUImageDifferenceBlendFilter */
/* renamed from: jp.co.cyberagent.android.gpuimage.n */
public class C5443n extends bb {
    public C5443n() {
        super("varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n\n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n void main()\n {\n     mediump vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n     mediump vec4 textureColor2 = texture2D(inputImageTexture2, textureCoordinate2);\n     gl_FragColor = vec4(abs(textureColor2.rgb - textureColor.rgb), textureColor.a);\n }");
    }
}
