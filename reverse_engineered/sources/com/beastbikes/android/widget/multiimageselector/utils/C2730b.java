package com.beastbikes.android.widget.multiimageselector.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: TimeUtils */
/* renamed from: com.beastbikes.android.widget.multiimageselector.utils.b */
public class C2730b {
    /* renamed from: a */
    public static String m13461a(long j, String str) {
        return new SimpleDateFormat(str, Locale.CHINA).format(new Date(j));
    }

    /* renamed from: a */
    public static String m13460a(long j) {
        return C2730b.m13461a(j, "yyyy-MM-dd");
    }

    /* renamed from: a */
    public static String m13462a(String str) {
        File file = new File(str);
        if (file.exists()) {
            return C2730b.m13460a(file.lastModified());
        }
        return "1970-01-01";
    }
}
