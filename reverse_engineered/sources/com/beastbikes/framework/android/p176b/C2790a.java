package com.beastbikes.framework.android.p176b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.util.LruCache;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.beastbikes.framework.android.p088g.C2802e;
import java.io.File;

/* compiled from: CacheManager */
/* renamed from: com.beastbikes.framework.android.b.a */
public class C2790a implements ImageCache {
    /* renamed from: a */
    private static C2790a f13001a = null;
    /* renamed from: b */
    private final LruCache<String, Bitmap> f13002b;
    /* renamed from: c */
    private final LruCache<String, String> f13003c;

    /* renamed from: a */
    public static final synchronized C2790a m13728a() {
        C2790a c2790a;
        synchronized (C2790a.class) {
            if (f13001a == null) {
                f13001a = new C2790a();
            }
            c2790a = f13001a;
        }
        return c2790a;
    }

    private C2790a() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 8);
        this.f13002b = new LruCache<String, Bitmap>(this, maxMemory) {
            /* renamed from: a */
            final /* synthetic */ C2790a f12999a;

            protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
                return m13726a((String) obj, (Bitmap) obj2);
            }

            /* renamed from: a */
            protected int m13726a(String str, Bitmap bitmap) {
                return bitmap.getByteCount();
            }
        };
        this.f13003c = new LruCache<String, String>(this, maxMemory) {
            /* renamed from: a */
            final /* synthetic */ C2790a f13000a;

            protected /* synthetic */ int sizeOf(Object obj, Object obj2) {
                return m13727a((String) obj, (String) obj2);
            }

            /* renamed from: a */
            protected int m13727a(String str, String str2) {
                return str2.getBytes().length;
            }
        };
    }

    /* renamed from: a */
    public String m13730a(String str) {
        if (str == null) {
            return null;
        }
        return (String) this.f13003c.get(str);
    }

    /* renamed from: a */
    public void m13732a(String str, String str2) {
        if (str != null && str2 != null) {
            this.f13003c.put(str, str2);
        }
    }

    public Bitmap getBitmap(String str) {
        if (str == null) {
            return null;
        }
        return (Bitmap) this.f13002b.get(str);
    }

    public void putBitmap(String str, Bitmap bitmap) {
        if (str != null && bitmap != null) {
            this.f13002b.put(str, bitmap);
        }
    }

    /* renamed from: b */
    public Bitmap m13733b(String str) {
        return m13729a(str, 4);
    }

    /* renamed from: a */
    public Bitmap m13729a(String str, int i) {
        Options options = new Options();
        options.inJustDecodeBounds = false;
        options.inPreferredConfig = Config.ARGB_8888;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inSampleSize = Math.max(2, i);
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        putBitmap(str, decodeFile);
        return decodeFile;
    }

    /* renamed from: a */
    public void m13731a(Context context) {
        m13734b(context);
        m13735c(context);
    }

    /* renamed from: b */
    public void m13734b(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir != null) {
            File[] listFiles = externalCacheDir.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File a : listFiles) {
                    C2802e.m13762a(a, true);
                }
            }
        }
    }

    /* renamed from: c */
    public void m13735c(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir != null) {
            File[] listFiles = cacheDir.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                for (File a : listFiles) {
                    C2802e.m13762a(a, true);
                }
            }
        }
    }
}
