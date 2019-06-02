package com.alipay.p029b.p030a.p031a.p034b;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* renamed from: com.alipay.b.a.a.b.c */
final class C0793c implements FileFilter {
    /* renamed from: a */
    final /* synthetic */ C0792b f1856a;

    C0793c(C0792b c0792b) {
        this.f1856a = c0792b;
    }

    public final boolean accept(File file) {
        return Pattern.matches("cpu[0-9]+", file.getName());
    }
}
