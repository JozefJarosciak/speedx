package jp.co.cyberagent.android.gpuimage;

/* compiled from: GPUImageColorInvertFilter */
/* renamed from: jp.co.cyberagent.android.gpuimage.j */
public class C5440j extends C5421v {
    public C5440j() {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n\nuniform sampler2D inputImageTexture;\n\nvoid main()\n{\n    lowp vec4 textureColor = texture2D(inputImageTexture, textureCoordinate);\n    \n    gl_FragColor = vec4((1.0 - textureColor.rgb), textureColor.w);\n}");
    }
}
