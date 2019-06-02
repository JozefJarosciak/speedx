package net.bither.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class NativeUtil {
    /* renamed from: a */
    private static int f17559a = 50;

    private static native String compressBitmap(Bitmap bitmap, int i, int i2, int i3, byte[] bArr, boolean z);

    static {
        System.loadLibrary("jpegbither");
        System.loadLibrary("bitherjni");
    }

    /* renamed from: a */
    public static void m19626a(Bitmap bitmap, String str, boolean z) {
        m19625a(bitmap, f17559a, str, z);
    }

    /* renamed from: a */
    public static void m19625a(Bitmap bitmap, int i, String str, boolean z) {
        Log.d("native", "compress of native");
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap, null, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), null);
        m19627b(createBitmap, i, str, z);
        createBitmap.recycle();
    }

    /* renamed from: b */
    public static void m19627b(Bitmap bitmap, int i, String str, boolean z) {
        compressBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), i, str.getBytes(), z);
    }
}
