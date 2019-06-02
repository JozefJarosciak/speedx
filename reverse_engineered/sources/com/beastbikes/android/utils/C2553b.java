package com.beastbikes.android.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.widget.ScrollView;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import net.bither.util.NativeUtil;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: BitmapUtil */
/* renamed from: com.beastbikes.android.utils.b */
public class C2553b {
    /* renamed from: a */
    public static final String f12035a = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DCIM" + File.separator + "Camera" + File.separator);
    /* renamed from: b */
    private static final Logger f12036b = LoggerFactory.getLogger(C2553b.class);

    /* renamed from: a */
    public static Bitmap m12778a(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.JPEG, 90, byteArrayOutputStream);
        int i = 100;
        f12036b.info("Save image compress start");
        int i2 = 0;
        while (byteArrayOutputStream.toByteArray().length > 225280 && i > 30) {
            byteArrayOutputStream.reset();
            i -= 10;
            bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
            i2++;
            f12036b.info("Save image compress baos size = " + byteArrayOutputStream.toByteArray().length + "index" + i2);
        }
        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
        f12036b.info("Save image compress success");
        return decodeStream;
    }

    /* renamed from: b */
    public static String m12786b(Bitmap bitmap) {
        if (bitmap == null) {
            f12036b.error("saveCompressImage(), Bitmap is null");
            return null;
        }
        String str = f12035a + "Beast-" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date()) + ".jpg";
        NativeUtil.a(bitmap, str, true);
        return str;
    }

    /* renamed from: c */
    public static String m12787c(Bitmap bitmap) {
        IOException e;
        Throwable th;
        String str = null;
        f12036b.info("Save to sacard by iamge compress");
        Bitmap a = C2553b.m12778a(bitmap);
        if (a != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault());
            File file = new File(f12035a);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(f12035a, "Beast-" + simpleDateFormat.format(new Date()) + ".jpg");
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    a.compress(CompressFormat.JPEG, 100, fileOutputStream);
                    fileOutputStream.flush();
                    f12036b.info("Save to sdcard by iamge success");
                    str = file2.getAbsolutePath();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (!a.isRecycled()) {
                        a.recycle();
                    }
                    System.gc();
                } catch (IOException e4) {
                    e3 = e4;
                    try {
                        e3.printStackTrace();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                        if (!a.isRecycled()) {
                            a.recycle();
                        }
                        System.gc();
                        return str;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e322) {
                                e322.printStackTrace();
                            }
                        }
                        if (!a.isRecycled()) {
                            a.recycle();
                        }
                        System.gc();
                        throw th;
                    }
                }
            } catch (IOException e5) {
                e322 = e5;
                fileOutputStream = str;
                e322.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (a.isRecycled()) {
                    a.recycle();
                }
                System.gc();
                return str;
            } catch (Throwable th3) {
                fileOutputStream = str;
                th = th3;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (a.isRecycled()) {
                    a.recycle();
                }
                System.gc();
                throw th;
            }
        }
        return str;
    }

    /* renamed from: a */
    public static String m12782a(Bitmap bitmap, String str) {
        FileOutputStream fileOutputStream;
        IOException e;
        Throwable th;
        f12036b.info("Save to sacard by iamge compress");
        Bitmap a = C2553b.m12778a(bitmap);
        if (a == null) {
            return "";
        }
        File file = new File(f12035a);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(f12035a, "Beast-" + str + ".jpg");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            fileOutputStream = new FileOutputStream(file);
            try {
                a.compress(CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                f12036b.info("Save to sdcard by iamge success");
                String absolutePath = file.getAbsolutePath();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                if (!a.isRecycled()) {
                    a.recycle();
                }
                System.gc();
                return absolutePath;
            } catch (IOException e4) {
                e = e4;
                try {
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    if (!a.isRecycled()) {
                        a.recycle();
                    }
                    System.gc();
                    return "";
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                    if (!a.isRecycled()) {
                        a.recycle();
                    }
                    System.gc();
                    throw th;
                }
            }
        } catch (IOException e6) {
            e5 = e6;
            fileOutputStream = null;
            e5.printStackTrace();
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (a.isRecycled()) {
                a.recycle();
            }
            System.gc();
            return "";
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (a.isRecycled()) {
                a.recycle();
            }
            System.gc();
            throw th;
        }
    }

    /* renamed from: a */
    public static Bitmap m12780a(ScrollView scrollView) {
        int i = 0;
        int i2 = 0;
        while (i < scrollView.getChildCount()) {
            i2 += scrollView.getChildAt(i).getHeight();
            i++;
        }
        if (scrollView.getWidth() <= 0 || i2 <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(scrollView.getWidth(), i2, Config.RGB_565);
        scrollView.draw(new Canvas(createBitmap));
        return createBitmap;
    }

    /* renamed from: a */
    public static Bitmap m12779a(View view) {
        int i = HttpStatus.SC_BAD_REQUEST;
        int height = view.getHeight();
        int width = view.getWidth();
        if (height == 0) {
            height = HttpStatus.SC_BAD_REQUEST;
        }
        if (width != 0) {
            i = width;
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, height, Config.ARGB_4444);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0);
        view.draw(canvas);
        return createBitmap;
    }

    /* renamed from: b */
    public static Bitmap m12785b(View view) {
        view.clearFocus();
        view.setPressed(false);
        boolean willNotCacheDrawing = view.willNotCacheDrawing();
        view.setWillNotCacheDrawing(false);
        int drawingCacheBackgroundColor = view.getDrawingCacheBackgroundColor();
        view.setDrawingCacheBackgroundColor(0);
        if (drawingCacheBackgroundColor != 0) {
            view.destroyDrawingCache();
        }
        view.buildDrawingCache();
        Bitmap drawingCache = view.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        drawingCache = Bitmap.createBitmap(drawingCache);
        view.destroyDrawingCache();
        view.setWillNotCacheDrawing(willNotCacheDrawing);
        view.setDrawingCacheBackgroundColor(drawingCacheBackgroundColor);
        return drawingCache;
    }

    /* renamed from: a */
    public static List<Integer> m12783a(int i) {
        Bitmap createBitmap = Bitmap.createBitmap(i + 1, 3, Config.RGB_565);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        float f = 0.0f;
        paint.setShader(new LinearGradient(0.0f, f, (float) (i + 1), 3.0f, new int[]{-41728, -2743260}, null, TileMode.MIRROR));
        canvas.drawRect(new Rect(0, 0, createBitmap.getWidth(), createBitmap.getHeight()), paint);
        List<Integer> arrayList = new ArrayList();
        for (int i2 = 0; i2 <= i; i2++) {
            int pixel = createBitmap.getPixel(i2, 1);
            if (pixel != 0) {
                arrayList.add(Integer.valueOf(pixel));
            }
        }
        if (!createBitmap.isRecycled()) {
            createBitmap.recycle();
        }
        return arrayList;
    }

    /* renamed from: a */
    public static void m12784a(String str) {
        Context applicationContext = BeastBikes.j().getApplicationContext();
        if (TextUtils.isEmpty(str)) {
            Toasts.show(applicationContext, (int) C1373R.string.activity_finished_share_sdcard_err);
            return;
        }
        Toasts.show(applicationContext, (int) C1373R.string.activity_finished_share_sdcard_success);
        Uri parse = Uri.parse(str);
        applicationContext.sendBroadcast(new Intent("android.hardware.action.NEW_PICTURE", parse));
        applicationContext.sendBroadcast(new Intent("com.android.camera.NEW_PICTURE", parse));
        applicationContext.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.parse("file://" + str)));
    }

    /* renamed from: a */
    public static Uri m12781a(int i, String str) {
        return Uri.parse("android.resource://" + str + "/" + i);
    }
}
