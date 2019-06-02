package com.google.android.gms.common.util;

import com.google.android.gms.common.internal.zzf;
import java.util.regex.Pattern;

public class zzw {
    private static final Pattern AZ = Pattern.compile("\\$\\{(.*?)\\}");

    public static boolean zzic(String str) {
        return str == null || zzf.xC.zzb(str);
    }
}
