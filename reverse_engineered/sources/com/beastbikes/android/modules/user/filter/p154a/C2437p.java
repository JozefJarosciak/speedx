package com.beastbikes.android.modules.user.filter.p154a;

import android.content.Context;
import com.beastbikes.android.C1373R;

/* compiled from: IFValenciaFilter */
/* renamed from: com.beastbikes.android.modules.user.filter.a.p */
public class C2437p extends C2417g {
    public C2437p(Context context) {
        super(context, "precision lowp float;\n \n varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2; //map\n uniform sampler2D inputImageTexture3; //gradMap\n \n mat3 saturateMatrix = mat3(\n                            1.1402,\n                            -0.0598,\n                            -0.061,\n                            -0.1174,\n                            1.0826,\n                            -0.1186,\n                            -0.0228,\n                            -0.0228,\n                            1.1772);\n \n vec3 lumaCoeffs = vec3(.3, .59, .11);\n \n void main()\n {\n     vec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\n     \n     texel = vec3(\n                  texture2D(inputImageTexture2, vec2(texel.r, .1666666)).r,\n                  texture2D(inputImageTexture2, vec2(texel.g, .5)).g,\n                  texture2D(inputImageTexture2, vec2(texel.b, .8333333)).b\n                  );\n     \n     texel = saturateMatrix * texel;\n     float luma = dot(lumaCoeffs, texel);\n     texel = vec3(\n                  texture2D(inputImageTexture3, vec2(luma, texel.r)).r,\n                  texture2D(inputImageTexture3, vec2(luma, texel.g)).g,\n                  texture2D(inputImageTexture3, vec2(luma, texel.b)).b);\n     \n     gl_FragColor = vec4(texel, 1.0);\n }\n");
        m12297l();
    }

    /* renamed from: l */
    private void m12297l() {
        m12279a((int) C1373R.drawable.valencia_map);
        m12279a((int) C1373R.drawable.valencia_gradient_map);
    }
}
