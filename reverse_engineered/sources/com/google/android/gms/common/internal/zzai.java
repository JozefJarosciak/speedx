package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.R$string;

public class zzai {
    private final Resources zc;
    private final String zd = this.zc.getResourcePackageName(R$string.common_google_play_services_unknown_issue);

    public zzai(Context context) {
        zzab.zzaa(context);
        this.zc = context.getResources();
    }

    public String getString(String str) {
        int identifier = this.zc.getIdentifier(str, "string", this.zd);
        return identifier == 0 ? null : this.zc.getString(identifier);
    }
}
