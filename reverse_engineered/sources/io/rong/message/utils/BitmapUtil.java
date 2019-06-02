package io.rong.message.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import ch.qos.logback.core.joran.action.Action;
import io.rong.common.RLog;
import java.io.IOException;

public class BitmapUtil {
    private static final String TAG = "Util";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getBase64FromBitmap(android.graphics.Bitmap r6) {
        /*
        r2 = 0;
        if (r6 == 0) goto L_0x007e;
    L_0x0003:
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x0049, all -> 0x005d }
        r1.<init>();	 Catch:{ IOException -> 0x0049, all -> 0x005d }
        r0 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ IOException -> 0x0072, all -> 0x006d }
        r3 = 40;
        r6.compress(r0, r3, r1);	 Catch:{ IOException -> 0x0072, all -> 0x006d }
        r0 = r1.toByteArray();	 Catch:{ IOException -> 0x0072, all -> 0x006d }
        r3 = 2;
        r2 = android.util.Base64.encodeToString(r0, r3);	 Catch:{ IOException -> 0x0072, all -> 0x006d }
        r0 = "base64Str";
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x0078, all -> 0x006d }
        r3.<init>();	 Catch:{ IOException -> 0x0078, all -> 0x006d }
        r4 = "";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x0078, all -> 0x006d }
        r4 = r2.length();	 Catch:{ IOException -> 0x0078, all -> 0x006d }
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x0078, all -> 0x006d }
        r3 = r3.toString();	 Catch:{ IOException -> 0x0078, all -> 0x006d }
        android.util.Log.d(r0, r3);	 Catch:{ IOException -> 0x0078, all -> 0x006d }
        r1.flush();	 Catch:{ IOException -> 0x0078, all -> 0x006d }
        r1.close();	 Catch:{ IOException -> 0x0078, all -> 0x006d }
        r0 = r2;
    L_0x003b:
        if (r1 == 0) goto L_0x0043;
    L_0x003d:
        r1.flush();	 Catch:{ IOException -> 0x0044 }
        r1.close();	 Catch:{ IOException -> 0x0044 }
    L_0x0043:
        return r0;
    L_0x0044:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0043;
    L_0x0049:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x004c:
        r1.printStackTrace();	 Catch:{ all -> 0x006f }
        if (r2 == 0) goto L_0x0043;
    L_0x0051:
        r2.flush();	 Catch:{ IOException -> 0x0058 }
        r2.close();	 Catch:{ IOException -> 0x0058 }
        goto L_0x0043;
    L_0x0058:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0043;
    L_0x005d:
        r0 = move-exception;
        r1 = r2;
    L_0x005f:
        if (r1 == 0) goto L_0x0067;
    L_0x0061:
        r1.flush();	 Catch:{ IOException -> 0x0068 }
        r1.close();	 Catch:{ IOException -> 0x0068 }
    L_0x0067:
        throw r0;
    L_0x0068:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0067;
    L_0x006d:
        r0 = move-exception;
        goto L_0x005f;
    L_0x006f:
        r0 = move-exception;
        r1 = r2;
        goto L_0x005f;
    L_0x0072:
        r0 = move-exception;
        r5 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x004c;
    L_0x0078:
        r0 = move-exception;
        r5 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x004c;
    L_0x007e:
        r1 = r2;
        r0 = r2;
        goto L_0x003b;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rong.message.utils.BitmapUtil.getBase64FromBitmap(android.graphics.Bitmap):java.lang.String");
    }

    public static Bitmap getBitmapFromBase64(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] decode = Base64.decode(str, 2);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    public static Bitmap getResizedBitmap(Context context, Uri uri, int i, int i2) throws IOException {
        String substring;
        Bitmap decodeFile;
        Bitmap bitmap = null;
        int i3 = 1;
        if (uri.getScheme().equals(Action.FILE_ATTRIBUTE)) {
            substring = uri.toString().substring(5);
        } else if (!uri.getScheme().equals("content")) {
            return bitmap;
        } else {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, bitmap, bitmap, bitmap);
            query.moveToFirst();
            substring = query.getString(0);
            query.close();
        }
        ExifInterface exifInterface = new ExifInterface(substring);
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(substring, options);
        int attributeInt = exifInterface.getAttributeInt("Orientation", 0);
        if (attributeInt == 6 || attributeInt == 8 || attributeInt == 5 || attributeInt == 7) {
            int i4 = i;
            i = i2;
            i2 = i4;
        }
        int i5 = options.outWidth;
        int i6 = options.outHeight;
        int i7 = i5;
        i5 = 1;
        while (i7 / 2 > i) {
            i7 /= 2;
            i5 <<= 1;
        }
        i7 = i6;
        while (i7 / 2 > i2) {
            i7 /= 2;
            i3 <<= 1;
        }
        options = new Options();
        if (i == Integer.MAX_VALUE || i2 == Integer.MAX_VALUE) {
            i5 = Math.max(i5, i3);
        } else {
            i5 = Math.max(i5, i3);
        }
        options.inSampleSize = i5;
        try {
            decodeFile = BitmapFactory.decodeFile(substring, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            options.inSampleSize <<= 1;
            decodeFile = BitmapFactory.decodeFile(substring, options);
        }
        Matrix matrix = new Matrix();
        if (decodeFile == null) {
            return decodeFile;
        }
        i5 = decodeFile.getWidth();
        int height = decodeFile.getHeight();
        if (attributeInt == 6 || attributeInt == 8 || attributeInt == 5 || attributeInt == 7) {
            i4 = i5;
            i5 = height;
            height = i4;
        }
        switch (attributeInt) {
            case 2:
                matrix.preScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.setRotate(180.0f, ((float) i5) / 2.0f, ((float) height) / 2.0f);
                break;
            case 4:
                matrix.preScale(1.0f, -1.0f);
                break;
            case 5:
                matrix.setRotate(90.0f, ((float) i5) / 2.0f, ((float) height) / 2.0f);
                matrix.preScale(1.0f, -1.0f);
                break;
            case 6:
                matrix.setRotate(90.0f, ((float) i5) / 2.0f, ((float) height) / 2.0f);
                break;
            case 7:
                matrix.setRotate(270.0f, ((float) i5) / 2.0f, ((float) height) / 2.0f);
                matrix.preScale(1.0f, -1.0f);
                break;
            case 8:
                matrix.setRotate(270.0f, ((float) i5) / 2.0f, ((float) height) / 2.0f);
                break;
        }
        float width = ((float) i) / ((float) decodeFile.getWidth());
        float height2 = ((float) i2) / ((float) decodeFile.getHeight());
        matrix.postScale(Math.min(width, height2), Math.min(width, height2));
        try {
            return Bitmap.createBitmap(decodeFile, 0, 0, decodeFile.getWidth(), decodeFile.getHeight(), matrix, true);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            Log.d("ResourceCompressHandler", "OOMHeight:" + decodeFile.getHeight() + "Width:" + decodeFile.getHeight() + "matrix:" + width + " " + height2);
            return bitmap;
        }
    }

    public static Bitmap interceptBitmap(String str, int i, int i2) {
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        int width = decodeFile.getWidth();
        int height = decodeFile.getHeight();
        int i3 = (width - i) / 2;
        int i4 = (height - i2) / 2;
        if (i3 <= 0 || i4 <= 0) {
            RLog.m19424w(TAG, "ignore intercept [" + width + ", " + height + ":" + i + ", " + i2 + "]");
            return decodeFile;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(decodeFile, i3, i4, i, i2);
            if (decodeFile.isRecycled()) {
                return createBitmap;
            }
            decodeFile.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    public static Bitmap getThumbBitmap(Context context, Uri uri, int i, int i2) throws IOException {
        String substring;
        int i3;
        Options options;
        int i4;
        Bitmap decodeFile;
        float f;
        float f2;
        float f3;
        if (uri.getScheme().equals(Action.FILE_ATTRIBUTE)) {
            substring = uri.toString().substring(5);
        } else if (!uri.getScheme().equals("content")) {
            return null;
        } else {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null);
            query.moveToFirst();
            substring = query.getString(0);
            query.close();
        }
        ExifInterface exifInterface = new ExifInterface(substring);
        Options options2 = new Options();
        options2.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(substring, options2);
        int attributeInt = exifInterface.getAttributeInt("Orientation", 0);
        int i5 = options2.outWidth;
        int i6 = options2.outHeight;
        int i7 = i5 > i6 ? i5 : i6;
        if (i5 > i6) {
            i3 = i6;
        } else {
            i3 = i5;
        }
        float f4 = ((float) i7) / ((float) i3);
        i7 = 1;
        if (f4 > ((float) i) / ((float) i2)) {
            while (i3 / 2 > i2) {
                i3 /= 2;
                i7 <<= 1;
            }
            options2 = new Options();
            options2.inSampleSize = i7;
            options = options2;
        } else {
            i7 = 1;
            i3 = i5;
            while (i3 / 2 > i) {
                i3 /= 2;
                i7 <<= 1;
            }
            i3 = 1;
            i4 = i6;
            while (i4 / 2 > i) {
                i4 /= 2;
                i3 <<= 1;
            }
            Options options3 = new Options();
            options3.inSampleSize = Math.max(i7, i3);
            options = options3;
        }
        try {
            decodeFile = BitmapFactory.decodeFile(substring, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            options.inSampleSize <<= 1;
            decodeFile = BitmapFactory.decodeFile(substring, options);
        }
        Matrix matrix = new Matrix();
        if (decodeFile == null) {
            return decodeFile;
        }
        i3 = decodeFile.getWidth();
        i7 = decodeFile.getHeight();
        if (attributeInt == 6 || attributeInt == 8 || attributeInt == 5 || attributeInt == 7) {
            int i8 = i3;
            i3 = i7;
            i7 = i8;
        }
        switch (attributeInt) {
            case 2:
                matrix.preScale(-1.0f, 1.0f);
                break;
            case 3:
                matrix.setRotate(180.0f, ((float) i3) / 2.0f, ((float) i7) / 2.0f);
                break;
            case 4:
                matrix.preScale(1.0f, -1.0f);
                break;
            case 5:
                matrix.setRotate(90.0f, ((float) i3) / 2.0f, ((float) i7) / 2.0f);
                matrix.preScale(1.0f, -1.0f);
                break;
            case 6:
                matrix.setRotate(90.0f, ((float) i3) / 2.0f, ((float) i7) / 2.0f);
                break;
            case 7:
                matrix.setRotate(270.0f, ((float) i3) / 2.0f, ((float) i7) / 2.0f);
                matrix.preScale(1.0f, -1.0f);
                break;
            case 8:
                matrix.setRotate(270.0f, ((float) i3) / 2.0f, ((float) i7) / 2.0f);
                break;
        }
        float height;
        if (f4 > ((float) i) / ((float) i2)) {
            height = ((float) i2) / ((float) (decodeFile.getWidth() > decodeFile.getHeight() ? decodeFile.getHeight() : decodeFile.getWidth()));
            matrix.postScale(height, height);
            f = 0.0f;
            f2 = 0.0f;
            f3 = height;
        } else {
            float width = ((float) i) / ((float) decodeFile.getWidth());
            height = ((float) i) / ((float) decodeFile.getHeight());
            matrix.postScale(Math.min(width, height), Math.min(width, height));
            f = height;
            f2 = width;
            f3 = 0.0f;
        }
        i7 = 0;
        i3 = 0;
        if (f4 > ((float) i) / ((float) i2)) {
            try {
                if (decodeFile.getWidth() > decodeFile.getHeight()) {
                    i5 = decodeFile.getHeight();
                    i4 = (i5 * i) / i2;
                    i7 = (decodeFile.getWidth() - i4) / 2;
                    i3 = 0;
                } else {
                    i4 = decodeFile.getWidth();
                    i5 = (i4 * i) / i2;
                    i7 = 0;
                    i3 = (decodeFile.getHeight() - i5) / 2;
                }
            } catch (OutOfMemoryError e2) {
                e2.printStackTrace();
                Log.d("ResourceCompressHandler", "OOMHeight:" + decodeFile.getHeight() + "Width:" + decodeFile.getHeight() + "matrix:" + f3 + " " + f2 + " " + f);
                if (!decodeFile.isRecycled()) {
                    decodeFile.recycle();
                }
                return null;
            }
        }
        i4 = decodeFile.getWidth();
        i5 = decodeFile.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(decodeFile, i7, i3, i4, i5, matrix, true);
        if (!decodeFile.isRecycled()) {
            decodeFile.recycle();
        }
        return createBitmap;
    }
}
