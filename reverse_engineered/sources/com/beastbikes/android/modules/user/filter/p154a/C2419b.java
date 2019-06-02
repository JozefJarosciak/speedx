package com.beastbikes.android.modules.user.filter.p154a;

import android.content.Context;
import com.beastbikes.android.C1373R;

/* compiled from: IFAmaroFilter */
/* renamed from: com.beastbikes.android.modules.user.filter.a.b */
public class C2419b extends C2417g {
    public C2419b(Context context) {
        super(context, "\n precision lowp float;\n\n varying highp vec2 textureCoordinate;\n\n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n uniform sampler2D inputImageTexture3;\n uniform sampler2D inputImageTexture4;\n\n void main()\n  {\n\n  vec4 texel = texture2D(inputImageTexture, textureCoordinate);\n   vec3 bbTexel = texture2D(inputImageTexture2, textureCoordinate).rgb;\n\n   texel.r = texture2D(inputImageTexture3, vec2(bbTexel.r, texel.r)).r;\n   texel.g = texture2D(inputImageTexture3, vec2(bbTexel.g, texel.g)).g;\n   texel.b = texture2D(inputImageTexture3, vec2(bbTexel.b, texel.b)).b;\n\n   vec4 mapped;\n   mapped.r = texture2D(inputImageTexture4, vec2(texel.r, .16666)).r;\n   mapped.g = texture2D(inputImageTexture4, vec2(texel.g, .5)).g;\n   mapped.b = texture2D(inputImageTexture4, vec2(texel.b, .83333)).b;\n   mapped.a = 1.0;\n\n   gl_FragColor = mapped;\n }");
        m12284l();
    }

    /* renamed from: l */
    private void m12284l() {
        m12279a((int) C1373R.drawable.blackboard);
        m12279a((int) C1373R.drawable.overlay_map);
        m12279a((int) C1373R.drawable.amaro_map);
    }
}
