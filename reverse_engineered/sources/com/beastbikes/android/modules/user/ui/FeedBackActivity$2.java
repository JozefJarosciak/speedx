package com.beastbikes.android.modules.user.ui;

import java.io.File;
import java.io.FilenameFilter;

class FeedBackActivity$2 implements FilenameFilter {
    /* renamed from: a */
    final /* synthetic */ FeedBackActivity f11618a;

    FeedBackActivity$2(FeedBackActivity feedBackActivity) {
        this.f11618a = feedBackActivity;
    }

    public boolean accept(File file, String str) {
        return str.endsWith(".log");
    }
}
