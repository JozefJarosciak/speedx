package jp.co.cyberagent.android.gpuimage;

/* compiled from: GPUImageMultiplyBlendFilter */
public class aj extends bb {
    public aj() {
        super("varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n\n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n void main()\n {\n     lowp vec4 base = texture2D(inputImageTexture, textureCoordinate);\n     lowp vec4 overlayer = texture2D(inputImageTexture2, textureCoordinate2);\n          \n     gl_FragColor = overlayer * base + overlayer * (1.0 - base.a) + base * (1.0 - overlayer.a);\n }");
    }
}
