package io.rong.imkit.utils;

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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtil {
    private static final String TAG = "Util";

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getBase64FromBitmap(android.graphics.Bitmap r5) {
        /*
        r2 = 0;
        if (r5 == 0) goto L_0x0062;
    L_0x0003:
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ IOException -> 0x002d, all -> 0x0041 }
        r1.<init>();	 Catch:{ IOException -> 0x002d, all -> 0x0041 }
        r0 = android.graphics.Bitmap.CompressFormat.JPEG;	 Catch:{ IOException -> 0x0056, all -> 0x0051 }
        r3 = 60;
        r5.compress(r0, r3, r1);	 Catch:{ IOException -> 0x0056, all -> 0x0051 }
        r0 = r1.toByteArray();	 Catch:{ IOException -> 0x0056, all -> 0x0051 }
        r3 = 2;
        r2 = android.util.Base64.encodeToString(r0, r3);	 Catch:{ IOException -> 0x0056, all -> 0x0051 }
        r1.flush();	 Catch:{ IOException -> 0x005c, all -> 0x0051 }
        r1.close();	 Catch:{ IOException -> 0x005c, all -> 0x0051 }
        r0 = r2;
    L_0x001f:
        if (r1 == 0) goto L_0x0027;
    L_0x0021:
        r1.flush();	 Catch:{ IOException -> 0x0028 }
        r1.close();	 Catch:{ IOException -> 0x0028 }
    L_0x0027:
        return r0;
    L_0x0028:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0027;
    L_0x002d:
        r0 = move-exception;
        r1 = r0;
        r0 = r2;
    L_0x0030:
        r1.printStackTrace();	 Catch:{ all -> 0x0053 }
        if (r2 == 0) goto L_0x0027;
    L_0x0035:
        r2.flush();	 Catch:{ IOException -> 0x003c }
        r2.close();	 Catch:{ IOException -> 0x003c }
        goto L_0x0027;
    L_0x003c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0027;
    L_0x0041:
        r0 = move-exception;
        r1 = r2;
    L_0x0043:
        if (r1 == 0) goto L_0x004b;
    L_0x0045:
        r1.flush();	 Catch:{ IOException -> 0x004c }
        r1.close();	 Catch:{ IOException -> 0x004c }
    L_0x004b:
        throw r0;
    L_0x004c:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x004b;
    L_0x0051:
        r0 = move-exception;
        goto L_0x0043;
    L_0x0053:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0043;
    L_0x0056:
        r0 = move-exception;
        r4 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r4;
        goto L_0x0030;
    L_0x005c:
        r0 = move-exception;
        r4 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r4;
        goto L_0x0030;
    L_0x0062:
        r1 = r2;
        r0 = r2;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rong.imkit.utils.BitmapUtil.getBase64FromBitmap(android.graphics.Bitmap):java.lang.String");
    }

    public static Bitmap getBitmapFromBase64(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        byte[] decode = Base64.decode(str, 2);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    public static Bitmap getResizedBitmap(Context context, Uri uri, int i, int i2) throws IOException {
        String path;
        Bitmap decodeFile;
        Bitmap bitmap = null;
        int i3 = 1;
        if (uri.getScheme().equals(Action.FILE_ATTRIBUTE)) {
            path = uri.getPath();
        } else if (!uri.getScheme().equals("content")) {
            return bitmap;
        } else {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, bitmap, bitmap, bitmap);
            query.moveToFirst();
            path = query.getString(0);
            query.close();
        }
        ExifInterface exifInterface = new ExifInterface(path);
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
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
            decodeFile = BitmapFactory.decodeFile(path, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            options.inSampleSize <<= 1;
            decodeFile = BitmapFactory.decodeFile(path, options);
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
            Log.e("ResourceCompressHandler", "OOMHeight:" + decodeFile.getHeight() + "Width:" + decodeFile.getHeight() + "matrix:" + width + " " + height2);
            return bitmap;
        }
    }

    public static Bitmap getRotateBitmap(float f, Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    private static Options decodeBitmapOptionsInfo(Context context, Uri uri) {
        InputStream inputStream = null;
        Options options = new Options();
        try {
            if (uri.getScheme().equals("content")) {
                inputStream = context.getContentResolver().openInputStream(uri);
            } else if (uri.getScheme().equals(Action.FILE_ATTRIBUTE)) {
                inputStream = new FileInputStream(uri.getPath());
            }
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        } catch (FileNotFoundException e2) {
            if (inputStream == null) {
                inputStream = getFileInputStream(uri.getPath());
            }
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, options);
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e3) {
                }
            }
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                }
            }
        }
        return options;
    }

    private static Bitmap rotateBitMap(String str, Bitmap bitmap) {
        ExifInterface exifInterface;
        float f;
        try {
            exifInterface = new ExifInterface(str);
        } catch (IOException e) {
            e.printStackTrace();
            exifInterface = null;
        }
        if (exifInterface != null) {
            switch (exifInterface.getAttributeInt("Orientation", 0)) {
                case 3:
                    f = 180.0f;
                    break;
                case 6:
                    f = 90.0f;
                    break;
                case 8:
                    f = 270.0f;
                    break;
            }
        }
        f = 0.0f;
        if (f == 0.0f) {
            return bitmap;
        }
        Matrix matrix = new Matrix();
        matrix.setRotate(f, (float) bitmap.getWidth(), (float) bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (createBitmap == null || bitmap == createBitmap) {
            return bitmap;
        }
        bitmap.recycle();
        return createBitmap;
    }

    public static InputStream getFileInputStream(String str) {
        try {
            return new FileInputStream(new File(str));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
