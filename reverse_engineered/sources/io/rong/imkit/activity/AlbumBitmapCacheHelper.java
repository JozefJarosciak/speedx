package io.rong.imkit.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.view.WindowManager;
import com.avos.avoscloud.AVStatus;
import io.rong.common.FileUtils;
import io.rong.imkit.utils.CommonUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AlbumBitmapCacheHelper {
    private static final String TAG = "AlbumBitmapCacheHelper";
    private static volatile AlbumBitmapCacheHelper instance = null;
    private LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>((int) ((Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / 4)) {
        protected int sizeOf(String str, Bitmap bitmap) {
            return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024;
        }
    };
    private ArrayList<String> currentShowString = new ArrayList();
    Context mContext;
    ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new LinkedBlockingQueue());

    public interface ILoadImageCallback {
        void onLoadImageCallBack(Bitmap bitmap, String str, Object... objArr);
    }

    private AlbumBitmapCacheHelper() {
    }

    public void releaseAllSizeCache() {
        this.cache.evictAll();
        this.cache.resize(1);
    }

    public void releaseHalfSizeCache() {
        this.cache.resize((int) ((Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / 8));
    }

    public void resizeCache() {
        this.cache.resize((int) ((Runtime.getRuntime().maxMemory() / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) / 4));
    }

    private void clearCache() {
        this.cache.evictAll();
        this.cache = null;
        this.tpe = null;
        instance = null;
    }

    public static AlbumBitmapCacheHelper getInstance() {
        if (instance == null) {
            synchronized (AlbumBitmapCacheHelper.class) {
                if (instance == null) {
                    instance = new AlbumBitmapCacheHelper();
                }
            }
        }
        return instance;
    }

    public static void init(Context context) {
        getInstance().mContext = context.getApplicationContext();
    }

    public void uninit() {
        this.currentShowString.clear();
        for (Runnable remove : this.tpe.getQueue()) {
            this.tpe.remove(remove);
        }
        clearCache();
    }

    public Bitmap getBitmap(String str, int i, int i2, ILoadImageCallback iLoadImageCallback, Object... objArr) {
        Bitmap bitmapFromCache = getBitmapFromCache(str, i, i2);
        if (bitmapFromCache != null) {
            Log.e(TAG, "getBitmap from cache");
        } else {
            decodeBitmapFromPath(str, i, i2, iLoadImageCallback, objArr);
        }
        return bitmapFromCache;
    }

    private void decodeBitmapFromPath(final String str, int i, int i2, final ILoadImageCallback iLoadImageCallback, final Object... objArr) throws OutOfMemoryError {
        final Handler c49802 = new Handler() {
            public void handleMessage(Message message) {
                if (iLoadImageCallback != null) {
                    iLoadImageCallback.onLoadImageCallBack((Bitmap) message.obj, str, objArr);
                }
            }
        };
        final String str2 = str;
        final int i3 = i;
        final int i4 = i2;
        this.tpe.execute(new Runnable() {
            public void run() {
                Bitmap bitmap;
                if (AlbumBitmapCacheHelper.this.currentShowString.contains(str2) && AlbumBitmapCacheHelper.this.cache != null) {
                    Object access$200;
                    if (i3 == 0 || i4 == 0) {
                        try {
                            access$200 = AlbumBitmapCacheHelper.this.getBitmap(str2, i3, i4);
                        } catch (OutOfMemoryError e) {
                            e.printStackTrace();
                            access$200 = null;
                        }
                    } else {
                        Bitmap bitmap2;
                        String str = FileUtils.getCachePath(AlbumBitmapCacheHelper.this.mContext, AVStatus.IMAGE_TAG) + "/" + CommonUtils.md5(str2 + "_" + i3 + "_" + i4) + ".temp";
                        File file = new File(str2);
                        File file2 = new File(str);
                        if (!file2.exists() || file.lastModified() > file2.lastModified()) {
                            bitmap2 = null;
                        } else {
                            bitmap2 = BitmapFactory.decodeFile(str);
                        }
                        if (bitmap2 == null) {
                            try {
                                bitmap2 = AlbumBitmapCacheHelper.this.getBitmap(str2, i3, i4);
                            } catch (OutOfMemoryError e2) {
                                bitmap2 = null;
                            }
                            if (!(bitmap2 == null || AlbumBitmapCacheHelper.this.cache == null)) {
                                bitmap2 = AlbumBitmapCacheHelper.centerSquareScaleBitmap(bitmap2, bitmap2.getWidth() > bitmap2.getHeight() ? bitmap2.getHeight() : bitmap2.getWidth());
                            }
                            if (bitmap2 != null) {
                                try {
                                    file = new File(str);
                                    if (file.exists()) {
                                        file.delete();
                                        file.createNewFile();
                                    } else {
                                        file.createNewFile();
                                    }
                                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                                    OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                    bitmap2.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
                                    fileOutputStream.write(byteArrayOutputStream.toByteArray());
                                    fileOutputStream.flush();
                                    fileOutputStream.close();
                                    bitmap = bitmap2;
                                } catch (FileNotFoundException e3) {
                                    e3.printStackTrace();
                                    bitmap = bitmap2;
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                    bitmap = bitmap2;
                                }
                            }
                        } else if (AlbumBitmapCacheHelper.this.cache != null) {
                            access$200 = AlbumBitmapCacheHelper.centerSquareScaleBitmap(bitmap2, bitmap2.getWidth() > bitmap2.getHeight() ? bitmap2.getHeight() : bitmap2.getWidth());
                        }
                        bitmap = bitmap2;
                    }
                    if (!(access$200 == null || AlbumBitmapCacheHelper.this.cache == null)) {
                        AlbumBitmapCacheHelper.this.cache.put(str2 + "_" + i3 + "_" + i4, access$200);
                    }
                    Message obtain = Message.obtain();
                    obtain.obj = access$200;
                    c49802.sendMessage(obtain);
                }
            }
        });
    }

    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int i) {
        if (bitmap == null || i <= 0) {
            return null;
        }
        int width = (bitmap.getWidth() - i) / 2;
        int height = (bitmap.getHeight() - i) / 2;
        if (width == 0 && height == 0) {
            return bitmap;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, width, height, i, i);
            try {
                if (!bitmap.isRecycled()) {
                    bitmap.recycle();
                }
                return createBitmap;
            } catch (OutOfMemoryError e) {
                return createBitmap;
            }
        } catch (OutOfMemoryError e2) {
            return bitmap;
        }
    }

    private int computeScale(Options options, int i, int i2) {
        if (options == null) {
            return 1;
        }
        int i3 = (int) (((float) options.outWidth) / ((float) i));
        int i4 = (int) (((float) options.outHeight) / ((float) i2));
        if (i3 <= i4) {
            i3 = i4;
        }
        if (i3 >= 1) {
            return i3;
        }
        return 1;
    }

    private Bitmap getBitmapFromCache(String str, int i, int i2) {
        return (Bitmap) this.cache.get(str + "_" + i + "_" + i2);
    }

    public void addPathToShowlist(String str) {
        this.currentShowString.add(str);
    }

    public void removePathFromShowlist(String str) {
        this.currentShowString.remove(str);
    }

    private Bitmap getBitmap(String str, int i, int i2) throws OutOfMemoryError {
        int i3;
        Bitmap bitmap;
        Options options;
        OutOfMemoryError e;
        Matrix matrix;
        int width;
        int i4 = 1;
        Options options2 = new Options();
        options2.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options2);
        int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 0);
        if (i == 0 && i2 == 0) {
            i4 = computeScale(options2, ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getWidth(), ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getWidth());
        } else {
            if (attributeInt == 6 || attributeInt == 8 || attributeInt == 5 || attributeInt == 7) {
                int i5 = i;
                i = i2;
                i2 = i5;
            }
            try {
                int i6 = options2.outWidth;
                i3 = options2.outHeight;
                int i7 = i6;
                i6 = 1;
                while (i7 / 2 > i) {
                    i7 /= 2;
                    i6 <<= 1;
                }
                while (i3 / 2 > i2) {
                    i3 /= 2;
                    i4 <<= 1;
                }
                if (i == Integer.MAX_VALUE || i2 == Integer.MAX_VALUE) {
                    i4 = Math.max(i6, i4);
                } else {
                    i4 = Math.max(i6, i4);
                }
            } catch (IOException e2) {
                IOException iOException = e2;
                bitmap = null;
                iOException.printStackTrace();
                return bitmap;
            }
        }
        try {
            options = new Options();
            try {
                options.inJustDecodeBounds = false;
                options.inSampleSize = i4;
                bitmap = BitmapFactory.decodeFile(str, options);
            } catch (OutOfMemoryError e3) {
                e = e3;
                e.printStackTrace();
                options.inSampleSize <<= 1;
                bitmap = BitmapFactory.decodeFile(str, options);
                matrix = new Matrix();
                if (bitmap != null) {
                    width = bitmap.getWidth();
                    i3 = bitmap.getHeight();
                    i5 = width;
                    width = i3;
                    i3 = i5;
                    switch (attributeInt) {
                        case 2:
                            matrix.preScale(-1.0f, 1.0f);
                            break;
                        case 3:
                            matrix.setRotate(180.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                            break;
                        case 4:
                            matrix.preScale(1.0f, -1.0f);
                            break;
                        case 5:
                            matrix.setRotate(90.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                            matrix.preScale(1.0f, -1.0f);
                            break;
                        case 6:
                            matrix.setRotate(90.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                            break;
                        case 7:
                            matrix.setRotate(270.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                            matrix.preScale(1.0f, -1.0f);
                            break;
                        case 8:
                            matrix.setRotate(270.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                            break;
                    }
                }
                return bitmap;
            }
        } catch (OutOfMemoryError e4) {
            e = e4;
            options = options2;
            e.printStackTrace();
            options.inSampleSize <<= 1;
            bitmap = BitmapFactory.decodeFile(str, options);
            matrix = new Matrix();
            if (bitmap != null) {
                width = bitmap.getWidth();
                i3 = bitmap.getHeight();
                i5 = width;
                width = i3;
                i3 = i5;
                switch (attributeInt) {
                    case 2:
                        matrix.preScale(-1.0f, 1.0f);
                        break;
                    case 3:
                        matrix.setRotate(180.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                        break;
                    case 4:
                        matrix.preScale(1.0f, -1.0f);
                        break;
                    case 5:
                        matrix.setRotate(90.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                        matrix.preScale(1.0f, -1.0f);
                        break;
                    case 6:
                        matrix.setRotate(90.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                        break;
                    case 7:
                        matrix.setRotate(270.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                        matrix.preScale(1.0f, -1.0f);
                        break;
                    case 8:
                        matrix.setRotate(270.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                        break;
                }
            }
            return bitmap;
        }
        try {
            matrix = new Matrix();
            if (bitmap != null) {
                width = bitmap.getWidth();
                i3 = bitmap.getHeight();
                if (attributeInt == 6 || attributeInt == 8 || attributeInt == 5 || attributeInt == 7) {
                    i5 = width;
                    width = i3;
                    i3 = i5;
                }
                switch (attributeInt) {
                    case 2:
                        matrix.preScale(-1.0f, 1.0f);
                        break;
                    case 3:
                        matrix.setRotate(180.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                        break;
                    case 4:
                        matrix.preScale(1.0f, -1.0f);
                        break;
                    case 5:
                        matrix.setRotate(90.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                        matrix.preScale(1.0f, -1.0f);
                        break;
                    case 6:
                        matrix.setRotate(90.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                        break;
                    case 7:
                        matrix.setRotate(270.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                        matrix.preScale(1.0f, -1.0f);
                        break;
                    case 8:
                        matrix.setRotate(270.0f, ((float) width) / 2.0f, ((float) i3) / 2.0f);
                        break;
                }
                if (!(i == 0 || i2 == 0)) {
                    float width2 = ((float) i) / ((float) bitmap.getWidth());
                    float height = ((float) i2) / ((float) bitmap.getHeight());
                    matrix.postScale(Math.min(width2, height), Math.min(width2, height));
                }
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }
        } catch (IOException e5) {
            iOException = e5;
            iOException.printStackTrace();
            return bitmap;
        }
        return bitmap;
    }
}
