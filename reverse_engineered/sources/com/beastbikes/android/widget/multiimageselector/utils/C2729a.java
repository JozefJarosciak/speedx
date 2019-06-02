package com.beastbikes.android.widget.multiimageselector.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: FileUtils */
/* renamed from: com.beastbikes.android.widget.multiimageselector.utils.a */
public class C2729a {
    /* renamed from: a */
    public static File m13459a(Context context) {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), ("multi_image_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date()) + "") + ".jpg");
        }
        return new File(context.getCacheDir(), ("multi_image_" + new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date()) + "") + ".jpg");
    }
}
