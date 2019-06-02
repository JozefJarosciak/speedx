package jp.co.cyberagent.android.gpuimage;

/* compiled from: GPUImageDissolveBlendFilter */
/* renamed from: jp.co.cyberagent.android.gpuimage.p */
public class C5445p extends ah {
    public C5445p() {
        super("varying highp vec2 textureCoordinate;\n varying highp vec2 textureCoordinate2;\n\n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n uniform lowp float mixturePercent;\n \n void main()\n {\n    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n    lowp vec4 textureColor2 = texture2D(inputImageTexture2, textureCoordinate2);\n    \n    gl_FragColor = mix(textureColor, textureColor2, mixturePercent);\n }");
    }
}
