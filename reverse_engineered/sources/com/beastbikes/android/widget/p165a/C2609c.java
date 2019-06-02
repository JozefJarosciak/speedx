package com.beastbikes.android.widget.p165a;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.Display;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: ImageUtils */
/* renamed from: com.beastbikes.android.widget.a.c */
public class C2609c {
    /* renamed from: a */
    public static void m13005a(Bitmap bitmap, File file) {
        IOException e;
        Throwable th;
        if (file == null) {
            Log.d("ImageUtils", "Error creating media file, check storage permissions: ");
            return;
        }
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                bitmap.compress(CompressFormat.PNG, 90, fileOutputStream);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            fileOutputStream = null;
            e22.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    public static int m13004a(Activity activity) {
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        if (VERSION.SDK_INT < 13) {
            return defaultDisplay.getWidth();
        }
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point.x;
    }
}
