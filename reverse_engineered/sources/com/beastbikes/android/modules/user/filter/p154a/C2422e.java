package com.beastbikes.android.modules.user.filter.p154a;

import android.content.Context;
import com.beastbikes.android.C1373R;

/* compiled from: IFHefeFilter */
/* renamed from: com.beastbikes.android.modules.user.filter.a.e */
public class C2422e extends C2417g {
    public C2422e(Context context) {
        super(context, "precision lowp float;\n \n varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;  //edgeBurn\n uniform sampler2D inputImageTexture3;  //hefeMap\n uniform sampler2D inputImageTexture4;  //hefeGradientMap\n uniform sampler2D inputImageTexture5;  //hefeSoftLight\n uniform sampler2D inputImageTexture6;  //hefeMetal\n \n void main()\n{\t\n\tvec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\n\tvec3 edge = texture2D(inputImageTexture2, textureCoordinate).rgb;\n\ttexel = texel * edge;\n\t\n\ttexel = vec3(\n                 texture2D(inputImageTexture3, vec2(texel.r, .16666)).r,\n                 texture2D(inputImageTexture3, vec2(texel.g, .5)).g,\n                 texture2D(inputImageTexture3, vec2(texel.b, .83333)).b);\n\t\n\tvec3 luma = vec3(.30, .59, .11);\n\tvec3 gradSample = texture2D(inputImageTexture4, vec2(dot(luma, texel), .5)).rgb;\n\tvec3 final = vec3(\n                      texture2D(inputImageTexture5, vec2(gradSample.r, texel.r)).r,\n                      texture2D(inputImageTexture5, vec2(gradSample.g, texel.g)).g,\n                      texture2D(inputImageTexture5, vec2(gradSample.b, texel.b)).b\n                      );\n    \n    vec3 metal = texture2D(inputImageTexture6, textureCoordinate).rgb;\n    vec3 metaled = vec3(\n                        texture2D(inputImageTexture5, vec2(metal.r, texel.r)).r,\n                        texture2D(inputImageTexture5, vec2(metal.g, texel.g)).g,\n                        texture2D(inputImageTexture5, vec2(metal.b, texel.b)).b\n                        );\n\t\n\tgl_FragColor = vec4(metaled, 1.0);\n}\n");
        m12287l();
    }

    /* renamed from: l */
    private void m12287l() {
        m12279a((int) C1373R.drawable.edge_burn);
        m12279a((int) C1373R.drawable.hefe_map);
        m12279a((int) C1373R.drawable.hefe_gradient_map);
        m12279a((int) C1373R.drawable.hefe_soft_light);
        m12279a((int) C1373R.drawable.hefe_metal);
    }
}
