package com.beastbikes.android.main;

import java.io.File;
import java.io.FilenameFilter;

class AboutActivity$1 implements FilenameFilter {
    /* renamed from: a */
    final /* synthetic */ AboutActivity f8369a;

    AboutActivity$1(AboutActivity aboutActivity) {
        this.f8369a = aboutActivity;
    }

    public boolean accept(File file, String str) {
        return str.endsWith(".log");
    }
}
