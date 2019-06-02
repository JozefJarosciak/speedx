package com.beastbikes.android.modules.user.filter.p154a;

import android.content.Context;
import com.beastbikes.android.C1373R;

/* compiled from: IFWaldenFilter */
/* renamed from: com.beastbikes.android.modules.user.filter.a.q */
public class C2438q extends C2417g {
    public C2438q(Context context) {
        super(context, "precision lowp float;\n \n varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2; //map\n uniform sampler2D inputImageTexture3; //vigMap\n \n void main()\n {\n     \n     vec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\n     \n     texel = vec3(\n                  texture2D(inputImageTexture2, vec2(texel.r, .16666)).r,\n                  texture2D(inputImageTexture2, vec2(texel.g, .5)).g,\n                  texture2D(inputImageTexture2, vec2(texel.b, .83333)).b);\n     \n     vec2 tc = (2.0 * textureCoordinate) - 1.0;\n     float d = dot(tc, tc);\n     vec2 lookup = vec2(d, texel.r);\n     texel.r = texture2D(inputImageTexture3, lookup).r;\n     lookup.y = texel.g;\n     texel.g = texture2D(inputImageTexture3, lookup).g;\n     lookup.y = texel.b;\n     texel.b\t= texture2D(inputImageTexture3, lookup).b;\n     \n     gl_FragColor = vec4(texel, 1.0);\n }\n");
        m12298l();
    }

    /* renamed from: l */
    private void m12298l() {
        m12279a((int) C1373R.drawable.walden_map);
        m12279a((int) C1373R.drawable.vignette_map);
    }
}
