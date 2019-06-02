package com.beastbikes.android.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Point;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.view.Display;
import android.view.WindowManager;
import com.beastbikes.android.C1373R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: ImageUtility */
/* renamed from: com.beastbikes.android.utils.i */
public class C2561i {
    /* renamed from: a */
    public static Uri m12864a(Context context, Bitmap bitmap) {
        int width;
        IOException e;
        OutputStream outputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Intent intent;
        Uri fromFile;
        FileOutputStream fileOutputStream = null;
        if (bitmap.getHeight() > bitmap.getWidth()) {
            width = bitmap.getWidth();
        } else {
            width = bitmap.getHeight();
        }
        Bitmap extractThumbnail = ThumbnailUtils.extractThumbnail(bitmap, width, width, 2);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), context.getString(C1373R.string.app_name));
        if (!file.exists() && !file.mkdirs()) {
            return null;
        }
        File file2 = new File(file.getPath() + File.separator + "IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpg");
        try {
            OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                extractThumbnail.compress(CompressFormat.JPEG, 100, byteArrayOutputStream2);
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                try {
                    fileOutputStream2.write(byteArrayOutputStream2.toByteArray());
                    fileOutputStream2.close();
                    byteArrayOutputStream2.close();
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    outputStream = byteArrayOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                    fromFile = Uri.fromFile(file2);
                    intent.setData(fromFile);
                    context.sendBroadcast(intent);
                    return fromFile;
                }
            } catch (IOException e4) {
                e3 = e4;
                outputStream = byteArrayOutputStream2;
                e3.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
                fromFile = Uri.fromFile(file2);
                intent.setData(fromFile);
                context.sendBroadcast(intent);
                return fromFile;
            }
        } catch (IOException e5) {
            e3 = e5;
            byteArrayOutputStream = null;
            e3.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            fromFile = Uri.fromFile(file2);
            intent.setData(fromFile);
            context.sendBroadcast(intent);
            return fromFile;
        }
        intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        fromFile = Uri.fromFile(file2);
        intent.setData(fromFile);
        context.sendBroadcast(intent);
        return fromFile;
    }

    /* renamed from: a */
    public static Bitmap m12863a(Context context, byte[] bArr) {
        int i;
        int i2;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        if (VERSION.SDK_INT >= 13) {
            defaultDisplay.getSize(point);
            i = point.x;
            i2 = point.y;
        } else {
            i = defaultDisplay.getWidth();
            i2 = defaultDisplay.getHeight();
        }
        Options options = new Options();
        options.inJustDecodeBounds = true;
        options.inMutable = true;
        options.inBitmap = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        options.inSampleSize = C2561i.m12862a(options, i, i2);
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
    }

    /* renamed from: a */
    public static int m12862a(Options options, int i, int i2) {
        int b = C2561i.m12865b(options, i, i2);
        if (b > 8) {
            return ((b + 7) / 8) * 8;
        }
        int i3 = 1;
        while (i3 < b) {
            i3 <<= 1;
        }
        return i3;
    }

    /* renamed from: b */
    private static int m12865b(Options options, int i, int i2) {
        int i3;
        int i4;
        double d = (double) options.outHeight;
        double d2 = (double) options.outWidth;
        long j = (long) (i * i2);
        int min = Math.min(i2, i);
        if (j < 0) {
            i3 = 1;
        } else {
            i3 = (int) Math.ceil(Math.sqrt((d2 * d) / ((double) j)));
        }
        if (min < 0) {
            i4 = 128;
        } else {
            i4 = (int) Math.min(Math.floor(d2 / ((double) min)), Math.floor(d / ((double) min)));
        }
        if (i4 < i3) {
            return i3;
        }
        if (j < 0 && min < 0) {
            return 1;
        }
        if (min >= 0) {
            return i4;
        }
        return i3;
    }
}
