package jp.co.cyberagent.android.gpuimage;

/* compiled from: GPUImageSourceOverBlendFilter */
public class ay extends bb {
    public ay() {
        super("varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n void main()\n {\n   lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n   lowp vec4 textureColor2 = texture2D(inputImageTexture2, textureCoordinate);\n   \n   gl_FragColor = mix(textureColor, textureColor2, textureColor2.a);\n }");
    }
}
