package com.beastbikes.android.modules.user.filter.p154a;

import android.content.Context;
import com.beastbikes.android.C1373R;

/* compiled from: IFInkwellFilter */
/* renamed from: com.beastbikes.android.modules.user.filter.a.h */
public class C2429h extends C2417g {
    public C2429h(Context context) {
        super(context, "precision lowp float;\n \n varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n void main()\n {\n     vec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\n     texel = vec3(dot(vec3(0.3, 0.6, 0.1), texel));\n     texel = vec3(texture2D(inputImageTexture2, vec2(texel.r, .16666)).r);\n     gl_FragColor = vec4(texel, 1.0);\n }\n");
        m12289l();
    }

    /* renamed from: l */
    private void m12289l() {
        m12279a((int) C1373R.drawable.inkwell_map);
    }
}
