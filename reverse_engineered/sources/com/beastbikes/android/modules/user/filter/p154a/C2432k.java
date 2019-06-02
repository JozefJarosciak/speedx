package com.beastbikes.android.modules.user.filter.p154a;

import android.content.Context;
import com.beastbikes.android.C1373R;

/* compiled from: IFNashvilleFilter */
/* renamed from: com.beastbikes.android.modules.user.filter.a.k */
public class C2432k extends C2417g {
    public C2432k(Context context) {
        super(context, "precision lowp float;\n \n varying highp vec2 textureCoordinate;\n \n uniform sampler2D inputImageTexture;\n uniform sampler2D inputImageTexture2;\n \n void main()\n {\n     vec3 texel = texture2D(inputImageTexture, textureCoordinate).rgb;\n     texel = vec3(\n                  texture2D(inputImageTexture2, vec2(texel.r, .16666)).r,\n                  texture2D(inputImageTexture2, vec2(texel.g, .5)).g,\n                  texture2D(inputImageTexture2, vec2(texel.b, .83333)).b);\n     gl_FragColor = vec4(texel, 1.0);\n }\n");
        m12292l();
    }

    /* renamed from: l */
    private void m12292l() {
        m12279a((int) C1373R.drawable.nashville_map);
    }
}
