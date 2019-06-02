package com.beastbikes.android.modules.user.filter.p154a;

import android.content.Context;
import com.beastbikes.android.C1373R;

/* compiled from: IF1977Filter */
/* renamed from: com.beastbikes.android.modules.user.filter.a.a */
public class C2418a extends C2417g {
    public C2418a(Context context) {
        super(context, "precision lowp float;\n \n varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n void main()\n {\n     \n     vec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\n     \n     texel = vec3(\n                  texture2D(inputImageTexture2, vec2(texel.r, .5)).r,\n                  texture2D(inputImageTexture2, vec2(texel.g, .1)).g,\n                  texture2D(inputImageTexture2, vec2(texel.b, .2)).b);\n     \n     gl_FragColor = vec4(texel, 1.0);\n }\n");
        m12283l();
    }

    /* renamed from: l */
    private void m12283l() {
        m12279a((int) C1373R.drawable.nmap);
        m12279a((int) C1373R.drawable.nblowout);
    }
}
