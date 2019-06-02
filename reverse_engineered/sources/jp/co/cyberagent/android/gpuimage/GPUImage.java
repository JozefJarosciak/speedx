package jp.co.cyberagent.android.gpuimage;

import android.app.ActivityManager;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.AsyncTask;
import android.view.WindowManager;
import com.alibaba.fastjson.asm.Opcodes;
import com.alipay.sdk.cons.C0845b;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.Semaphore;
import org.apache.http.HttpHost;

public class GPUImage {
    /* renamed from: a */
    private final Context f17383a;
    /* renamed from: b */
    private final aq f17384b;
    /* renamed from: c */
    private GLSurfaceView f17385c;
    /* renamed from: d */
    private C5421v f17386d;
    /* renamed from: e */
    private Bitmap f17387e;
    /* renamed from: f */
    private ScaleType f17388f = ScaleType.CENTER_CROP;

    public enum ScaleType {
        CENTER_INSIDE,
        CENTER_CROP
    }

    /* renamed from: jp.co.cyberagent.android.gpuimage.GPUImage$b */
    private abstract class C5414b extends AsyncTask<Void, Void, Bitmap> {
        /* renamed from: a */
        private final GPUImage f17373a;
        /* renamed from: b */
        final /* synthetic */ GPUImage f17374b;
        /* renamed from: c */
        private int f17375c;
        /* renamed from: d */
        private int f17376d;

        /* renamed from: a */
        protected abstract int mo6681a() throws IOException;

        /* renamed from: a */
        protected abstract Bitmap mo6682a(Options options);

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m19452a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m19453a((Bitmap) obj);
        }

        public C5414b(GPUImage gPUImage, GPUImage gPUImage2) {
            this.f17374b = gPUImage;
            this.f17373a = gPUImage2;
        }

        /* renamed from: a */
        protected Bitmap m19452a(Void... voidArr) {
            if (this.f17374b.f17384b != null && this.f17374b.f17384b.m19556b() == 0) {
                try {
                    synchronized (this.f17374b.f17384b.f17458b) {
                        this.f17374b.f17384b.f17458b.wait(3000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.f17375c = this.f17374b.m19466d();
            this.f17376d = this.f17374b.m19468e();
            return m19447b();
        }

        /* renamed from: a */
        protected void m19453a(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            this.f17373a.m19473a(bitmap);
        }

        /* renamed from: b */
        private Bitmap m19447b() {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            mo6682a(options);
            int i = 1;
            while (true) {
                boolean z;
                if (options.outWidth / i > this.f17375c) {
                    z = true;
                } else {
                    z = false;
                }
                if (!m19445a(z, options.outHeight / i > this.f17376d)) {
                    break;
                }
                i++;
            }
            i--;
            if (i < 1) {
                i = 1;
            }
            Options options2 = new Options();
            options2.inSampleSize = i;
            options2.inPreferredConfig = Config.RGB_565;
            options2.inPurgeable = true;
            options2.inTempStorage = new byte[32768];
            Bitmap a = mo6682a(options2);
            if (a == null) {
                return null;
            }
            return m19448b(m19449c(a));
        }

        /* renamed from: b */
        private Bitmap m19448b(Bitmap bitmap) {
            int[] a = m19446a(bitmap.getWidth(), bitmap.getHeight());
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, a[0], a[1], true);
            if (createScaledBitmap != bitmap) {
                bitmap.recycle();
                bitmap = createScaledBitmap;
            }
            System.gc();
            if (this.f17374b.f17388f != ScaleType.CENTER_CROP) {
                return bitmap;
            }
            int i = a[0] - this.f17375c;
            int i2 = a[1] - this.f17376d;
            createScaledBitmap = Bitmap.createBitmap(bitmap, i / 2, i2 / 2, a[0] - i, a[1] - i2);
            if (createScaledBitmap == bitmap) {
                return bitmap;
            }
            bitmap.recycle();
            return createScaledBitmap;
        }

        /* renamed from: a */
        private int[] m19446a(int i, int i2) {
            float f = ((float) i) / ((float) this.f17375c);
            float f2 = ((float) i2) / ((float) this.f17376d);
            int i3 = this.f17374b.f17388f == ScaleType.CENTER_CROP ? f > f2 ? 1 : 0 : f < f2 ? 1 : 0;
            if (i3 != 0) {
                f = (float) this.f17376d;
                f2 = (f / ((float) i2)) * ((float) i);
            } else {
                f2 = (float) this.f17375c;
                f = (f2 / ((float) i)) * ((float) i2);
            }
            return new int[]{Math.round(f2), Math.round(f)};
        }

        /* renamed from: a */
        private boolean m19445a(boolean z, boolean z2) {
            boolean z3 = false;
            if (this.f17374b.f17388f != ScaleType.CENTER_CROP) {
                if (z || z2) {
                    z3 = true;
                }
                return z3;
            } else if (z && z2) {
                return true;
            } else {
                return false;
            }
        }

        /* renamed from: c */
        private Bitmap m19449c(Bitmap bitmap) {
            IOException iOException;
            if (bitmap == null) {
                return null;
            }
            try {
                int a = mo6681a();
                if (a == 0) {
                    return bitmap;
                }
                Matrix matrix = new Matrix();
                matrix.postRotate((float) a);
                Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                try {
                    bitmap.recycle();
                    return createBitmap;
                } catch (IOException e) {
                    bitmap = createBitmap;
                    iOException = e;
                    iOException.printStackTrace();
                    return bitmap;
                }
            } catch (IOException e2) {
                iOException = e2;
                iOException.printStackTrace();
                return bitmap;
            }
        }
    }

    /* renamed from: jp.co.cyberagent.android.gpuimage.GPUImage$a */
    private class C5415a extends C5414b {
        /* renamed from: a */
        final /* synthetic */ GPUImage f17377a;
        /* renamed from: c */
        private final File f17378c;

        public C5415a(GPUImage gPUImage, GPUImage gPUImage2, File file) {
            this.f17377a = gPUImage;
            super(gPUImage, gPUImage2);
            this.f17378c = file;
        }

        /* renamed from: a */
        protected Bitmap mo6682a(Options options) {
            return BitmapFactory.decodeFile(this.f17378c.getAbsolutePath(), options);
        }

        /* renamed from: a */
        protected int mo6681a() throws IOException {
            switch (new ExifInterface(this.f17378c.getAbsolutePath()).getAttributeInt("Orientation", 1)) {
                case 3:
                    return Opcodes.GETFIELD;
                case 6:
                    return 90;
                case 8:
                    return 270;
                default:
                    return 0;
            }
        }
    }

    /* renamed from: jp.co.cyberagent.android.gpuimage.GPUImage$c */
    private class C5416c extends C5414b {
        /* renamed from: a */
        final /* synthetic */ GPUImage f17379a;
        /* renamed from: c */
        private final Uri f17380c;

        public C5416c(GPUImage gPUImage, GPUImage gPUImage2, Uri uri) {
            this.f17379a = gPUImage;
            super(gPUImage, gPUImage2);
            this.f17380c = uri;
        }

        /* renamed from: a */
        protected Bitmap mo6682a(Options options) {
            try {
                InputStream openStream;
                if (this.f17380c.getScheme().startsWith(HttpHost.DEFAULT_SCHEME_NAME) || this.f17380c.getScheme().startsWith(C0845b.f2060a)) {
                    openStream = new URL(this.f17380c.toString()).openStream();
                } else {
                    openStream = this.f17379a.f17383a.getContentResolver().openInputStream(this.f17380c);
                }
                return BitmapFactory.decodeStream(openStream, null, options);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* renamed from: a */
        protected int mo6681a() throws IOException {
            Cursor query = this.f17379a.f17383a.getContentResolver().query(this.f17380c, new String[]{MapboxEvent.ATTRIBUTE_ORIENTATION}, null, null, null);
            if (query == null || query.getCount() != 1) {
                if (query != null) {
                    query.close();
                }
                return 0;
            }
            query.moveToFirst();
            int i = query.getInt(0);
            query.close();
            return i;
        }
    }

    /* renamed from: jp.co.cyberagent.android.gpuimage.GPUImage$d */
    private class C5417d extends AsyncTask<Bitmap, Void, Bitmap> {
        /* renamed from: a */
        final /* synthetic */ GPUImage f17381a;
        /* renamed from: b */
        private final float f17382b;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m19458a((Bitmap[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m19459a((Bitmap) obj);
        }

        public C5417d(GPUImage gPUImage, float f) {
            this.f17381a = gPUImage;
            this.f17382b = f;
        }

        /* renamed from: a */
        protected Bitmap m19458a(Bitmap... bitmapArr) {
            Bitmap bitmap = bitmapArr[0];
            Matrix matrix = new Matrix();
            matrix.postRotate(this.f17382b);
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }

        /* renamed from: a */
        protected void m19459a(Bitmap bitmap) {
            this.f17381a.m19461a(bitmap, true);
        }
    }

    public GPUImage(Context context) {
        if (m19463a(context)) {
            this.f17383a = context;
            this.f17386d = new C5421v();
            this.f17384b = new aq(this.f17386d);
            return;
        }
        throw new IllegalStateException("OpenGL ES 2.0 is not supported on this phone.");
    }

    /* renamed from: a */
    private boolean m19463a(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= 131072;
    }

    /* renamed from: a */
    public void m19475a(GLSurfaceView gLSurfaceView) {
        this.f17385c = gLSurfaceView;
        this.f17385c.setEGLContextClientVersion(2);
        this.f17385c.setRenderer(this.f17384b);
        this.f17385c.setRenderMode(0);
        this.f17385c.requestRender();
    }

    /* renamed from: a */
    public void m19471a() {
        if (this.f17385c != null) {
            this.f17385c.requestRender();
        }
    }

    /* renamed from: a */
    public void m19472a(float f) {
        new C5417d(this, f).execute(new Bitmap[]{this.f17387e});
    }

    /* renamed from: a */
    public void m19477a(C5421v c5421v) {
        this.f17386d = c5421v;
        this.f17384b.m19555a(this.f17386d);
        m19471a();
    }

    /* renamed from: a */
    public void m19473a(Bitmap bitmap) {
        m19461a(bitmap, false);
        this.f17387e = bitmap;
    }

    /* renamed from: a */
    private void m19461a(Bitmap bitmap, boolean z) {
        this.f17384b.m19551a(bitmap, z);
        m19471a();
    }

    /* renamed from: b */
    public void m19479b() {
        this.f17384b.m19550a();
        this.f17387e = null;
        m19471a();
    }

    /* renamed from: a */
    public void m19474a(Uri uri) {
        new C5416c(this, this, uri).execute(new Void[0]);
    }

    /* renamed from: a */
    public void m19476a(File file) {
        new C5415a(this, this, file).execute(new Void[0]);
    }

    /* renamed from: c */
    public Bitmap m19480c() {
        return m19478b(this.f17387e);
    }

    /* renamed from: b */
    public Bitmap m19478b(Bitmap bitmap) {
        if (this.f17385c != null) {
            this.f17384b.m19550a();
            final Semaphore semaphore = new Semaphore(0);
            this.f17384b.m19552a(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ GPUImage f17372b;

                public void run() {
                    this.f17372b.f17386d.m19497f();
                    semaphore.release();
                }
            });
            m19471a();
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        aq aqVar = new aq(this.f17386d);
        aqVar.m19554a(Rotation.NORMAL, this.f17384b.m19558d(), this.f17384b.m19559e());
        aqVar.m19553a(this.f17388f);
        bf bfVar = new bf(bitmap.getWidth(), bitmap.getHeight());
        bfVar.m19609a(aqVar);
        aqVar.m19551a(bitmap, false);
        Bitmap a = bfVar.m19608a();
        this.f17386d.m19497f();
        aqVar.m19550a();
        bfVar.m19610b();
        this.f17384b.m19555a(this.f17386d);
        if (this.f17387e != null) {
            this.f17384b.m19551a(this.f17387e, false);
        }
        m19471a();
        return a;
    }

    /* renamed from: d */
    private int m19466d() {
        if (this.f17384b != null && this.f17384b.m19556b() != 0) {
            return this.f17384b.m19556b();
        }
        if (this.f17387e != null) {
            return this.f17387e.getWidth();
        }
        return ((WindowManager) this.f17383a.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    /* renamed from: e */
    private int m19468e() {
        if (this.f17384b != null && this.f17384b.m19557c() != 0) {
            return this.f17384b.m19557c();
        }
        if (this.f17387e != null) {
            return this.f17387e.getHeight();
        }
        return ((WindowManager) this.f17383a.getSystemService("window")).getDefaultDisplay().getHeight();
    }
}
