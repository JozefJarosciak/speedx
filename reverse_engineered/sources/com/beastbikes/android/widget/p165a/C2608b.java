package com.beastbikes.android.widget.p165a;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.LruCache;
import android.widget.ImageView;
import java.io.File;

/* compiled from: BlurUtil */
/* renamed from: com.beastbikes.android.widget.a.b */
public class C2608b {
    /* renamed from: a */
    static LruCache<ImageView, Bitmap> f12202a = new LruCache(1);

    /* renamed from: a */
    public static void m12999a(Activity activity, ImageView imageView, int i) {
        C2608b.m13000a(activity, imageView, i, true);
    }

    /* renamed from: a */
    public static void m13002a(ImageView imageView, int i) {
        C2608b.m13000a(null, imageView, i, false);
    }

    /* renamed from: a */
    public static void m13001a(ImageView imageView) {
        if (imageView != null) {
            imageView.setImageBitmap(null);
            imageView.setImageDrawable(null);
            Bitmap bitmap = (Bitmap) f12202a.get(imageView);
            if (!(bitmap == null || bitmap.isRecycled())) {
                bitmap.recycle();
            }
            f12202a.remove(imageView);
        }
    }

    /* renamed from: a */
    public static void m13000a(Activity activity, ImageView imageView, int i, boolean z) {
        if (imageView != null && i > 0) {
            boolean z2 = false;
            if (imageView.getTag() != null) {
                z2 = ((Boolean) imageView.getTag()).booleanValue();
            }
            if (z) {
                if (activity != null && !z2) {
                    final File file = new File(activity.getFilesDir() + "blurred_image.png");
                    final int a = C2609c.m13004a(activity);
                    if (file.exists()) {
                        C2608b.m13003b(a, activity, imageView);
                    } else {
                        final Activity activity2 = activity;
                        final int i2 = i;
                        final ImageView imageView2 = imageView;
                        new Thread(new Runnable() {
                            public void run() {
                                Options options = new Options();
                                options.inSampleSize = 2;
                                final Bitmap a = C2605a.m12997a(activity2, BitmapFactory.decodeResource(activity2.getResources(), i2, options), 25);
                                C2609c.m13005a(a, file);
                                activity2.runOnUiThread(new Runnable(this) {
                                    /* renamed from: b */
                                    final /* synthetic */ C26071 f12196b;

                                    public void run() {
                                        C2608b.f12202a.put(imageView2, a);
                                        C2608b.m13003b(a, activity2, imageView2);
                                    }
                                });
                            }
                        }).start();
                    }
                } else {
                    return;
                }
            } else if (z2) {
                imageView.setImageResource(i);
            }
            imageView.setTag(Boolean.valueOf(z));
        }
    }

    /* renamed from: b */
    private static void m13003b(int i, Activity activity, ImageView imageView) {
        Bitmap bitmap = (Bitmap) f12202a.get(imageView);
        if (f12202a.get(imageView) == null) {
            bitmap = BitmapFactory.decodeFile(activity.getFilesDir() + "blurred_image.png");
        }
        if (bitmap != null) {
            imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, i, (int) ((((float) bitmap.getHeight()) * ((float) i)) / ((float) bitmap.getWidth())), false));
        }
    }
}
