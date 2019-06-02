package com.beastbikes.android.modules.user.filter.p154a;

import android.content.Context;
import com.beastbikes.android.C1373R;

/* compiled from: IFLordKelvinFilter */
/* renamed from: com.beastbikes.android.modules.user.filter.a.j */
public class C2431j extends C2417g {
    public C2431j(Context context) {
        super(context, "precision lowp float;\n \n varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n void main()\n {\n     vec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\n     \n     vec2 lookup;\n     lookup.y = .5;\n     \n     lookup.x = texel.r;\n     texel.r = texture2D(inputImageTexture2, lookup).r;\n     \n     lookup.x = texel.g;\n     texel.g = texture2D(inputImageTexture2, lookup).g;\n     \n     lookup.x = texel.b;\n     texel.b = texture2D(inputImageTexture2, lookup).b;\n     \n     gl_FragColor = vec4(texel, 1.0);\n }\n");
        m12291l();
    }

    /* renamed from: l */
    private void m12291l() {
        m12279a((int) C1373R.drawable.kelvin_map);
    }
}
