package com.tencent.wxop.stat.common;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/* renamed from: com.tencent.wxop.stat.common.m */
class C4541m implements FileFilter {
    C4541m() {
    }

    public boolean accept(File file) {
        return Pattern.matches("cpu[0-9]", file.getName());
    }
}
