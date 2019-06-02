package jp.co.cyberagent.android.gpuimage;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.LinkedList;
import java.util.Queue;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import jp.co.cyberagent.android.gpuimage.GPUImage.ScaleType;
import jp.co.cyberagent.android.gpuimage.p205a.C5420a;

@TargetApi(11)
/* compiled from: GPUImageRenderer */
public class aq implements PreviewCallback, Renderer {
    /* renamed from: a */
    static final float[] f17457a = new float[]{-1.0f, -1.0f, 1.0f, -1.0f, -1.0f, 1.0f, 1.0f, 1.0f};
    /* renamed from: b */
    public final Object f17458b = new Object();
    /* renamed from: c */
    private C5421v f17459c;
    /* renamed from: d */
    private int f17460d = -1;
    /* renamed from: e */
    private SurfaceTexture f17461e = null;
    /* renamed from: f */
    private final FloatBuffer f17462f;
    /* renamed from: g */
    private final FloatBuffer f17463g;
    /* renamed from: h */
    private IntBuffer f17464h;
    /* renamed from: i */
    private int f17465i;
    /* renamed from: j */
    private int f17466j;
    /* renamed from: k */
    private int f17467k;
    /* renamed from: l */
    private int f17468l;
    /* renamed from: m */
    private int f17469m;
    /* renamed from: n */
    private final Queue<Runnable> f17470n;
    /* renamed from: o */
    private Rotation f17471o;
    /* renamed from: p */
    private boolean f17472p;
    /* renamed from: q */
    private boolean f17473q;
    /* renamed from: r */
    private ScaleType f17474r = ScaleType.CENTER_CROP;

    /* compiled from: GPUImageRenderer */
    /* renamed from: jp.co.cyberagent.android.gpuimage.aq$3 */
    class C54263 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ aq f17453a;

        C54263(aq aqVar) {
            this.f17453a = aqVar;
        }

        public void run() {
            GLES20.glDeleteTextures(1, new int[]{this.f17453a.f17460d}, 0);
            this.f17453a.f17460d = -1;
        }
    }

    public aq(C5421v c5421v) {
        this.f17459c = c5421v;
        this.f17470n = new LinkedList();
        this.f17462f = ByteBuffer.allocateDirect(f17457a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f17462f.put(f17457a).position(0);
        this.f17463g = ByteBuffer.allocateDirect(C5420a.f17393a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        m19554a(Rotation.NORMAL, false, false);
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        GLES20.glDisable(2929);
        this.f17459c.m19496e();
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        this.f17465i = i;
        this.f17466j = i2;
        GLES20.glViewport(0, 0, i, i2);
        GLES20.glUseProgram(this.f17459c.m19502k());
        this.f17459c.mo6684a(i, i2);
        synchronized (this.f17458b) {
            this.f17458b.notifyAll();
        }
    }

    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(16640);
        synchronized (this.f17470n) {
            while (!this.f17470n.isEmpty()) {
                ((Runnable) this.f17470n.poll()).run();
            }
        }
        this.f17459c.mo6688a(this.f17460d, this.f17462f, this.f17463g);
        if (this.f17461e != null) {
            this.f17461e.updateTexImage();
        }
    }

    public void onPreviewFrame(final byte[] bArr, final Camera camera) {
        final Size previewSize = camera.getParameters().getPreviewSize();
        if (this.f17464h == null) {
            this.f17464h = IntBuffer.allocate(previewSize.width * previewSize.height);
        }
        if (this.f17470n.isEmpty()) {
            m19552a(new Runnable(this) {
                /* renamed from: d */
                final /* synthetic */ aq f17450d;

                public void run() {
                    GPUImageNativeLibrary.YUVtoRBGA(bArr, previewSize.width, previewSize.height, this.f17450d.f17464h.array());
                    this.f17450d.f17460d = be.m19605a(this.f17450d.f17464h, previewSize, this.f17450d.f17460d);
                    camera.addCallbackBuffer(bArr);
                    if (this.f17450d.f17467k != previewSize.width) {
                        this.f17450d.f17467k = previewSize.width;
                        this.f17450d.f17468l = previewSize.height;
                        this.f17450d.m19548f();
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public void m19555a(final C5421v c5421v) {
        m19552a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ aq f17452b;

            public void run() {
                C5421v e = this.f17452b.f17459c;
                this.f17452b.f17459c = c5421v;
                if (e != null) {
                    e.m19497f();
                }
                this.f17452b.f17459c.m19496e();
                GLES20.glUseProgram(this.f17452b.f17459c.m19502k());
                this.f17452b.f17459c.mo6684a(this.f17452b.f17465i, this.f17452b.f17466j);
            }
        });
    }

    /* renamed from: a */
    public void m19550a() {
        m19552a(new C54263(this));
    }

    /* renamed from: a */
    public void m19551a(final Bitmap bitmap, final boolean z) {
        if (bitmap != null) {
            m19552a(new Runnable(this) {
                /* renamed from: c */
                final /* synthetic */ aq f17456c;

                public void run() {
                    Bitmap createBitmap;
                    if (bitmap.getWidth() % 2 == 1) {
                        createBitmap = Bitmap.createBitmap(bitmap.getWidth() + 1, bitmap.getHeight(), Config.ARGB_8888);
                        Canvas canvas = new Canvas(createBitmap);
                        canvas.drawARGB(0, 0, 0, 0);
                        canvas.drawBitmap(bitmap, 0.0f, 0.0f, null);
                        this.f17456c.f17469m = 1;
                    } else {
                        this.f17456c.f17469m = 0;
                        createBitmap = null;
                    }
                    this.f17456c.f17460d = be.m19602a(createBitmap != null ? createBitmap : bitmap, this.f17456c.f17460d, z);
                    if (createBitmap != null) {
                        createBitmap.recycle();
                    }
                    this.f17456c.f17467k = bitmap.getWidth();
                    this.f17456c.f17468l = bitmap.getHeight();
                    this.f17456c.m19548f();
                }
            });
        }
    }

    /* renamed from: a */
    public void m19553a(ScaleType scaleType) {
        this.f17474r = scaleType;
    }

    /* renamed from: b */
    protected int m19556b() {
        return this.f17465i;
    }

    /* renamed from: c */
    protected int m19557c() {
        return this.f17466j;
    }

    /* renamed from: f */
    private void m19548f() {
        float[] fArr;
        float[] fArr2;
        float f = (float) this.f17465i;
        float f2 = (float) this.f17466j;
        if (this.f17471o == Rotation.ROTATION_270 || this.f17471o == Rotation.ROTATION_90) {
            f = (float) this.f17466j;
            f2 = (float) this.f17465i;
        }
        float min = Math.min(f / ((float) this.f17467k), f2 / ((float) this.f17468l));
        this.f17467k = Math.round(((float) this.f17467k) * min);
        this.f17468l = Math.round(min * ((float) this.f17468l));
        if (((float) this.f17467k) != f) {
            f = ((float) this.f17467k) / f;
            f2 = 1.0f;
        } else if (((float) this.f17468l) != f2) {
            f2 = ((float) this.f17468l) / f2;
            f = 1.0f;
        } else {
            f2 = 1.0f;
            f = 1.0f;
        }
        float[] fArr3 = f17457a;
        float[] a = C5420a.m19484a(this.f17471o, this.f17472p, this.f17473q);
        if (this.f17474r == ScaleType.CENTER_CROP) {
            f = ((1.0f / f) - 1.0f) / 2.0f;
            float f3 = ((1.0f / f2) - 1.0f) / 2.0f;
            fArr = new float[]{m19536a(a[0], f3), m19536a(a[1], f), m19536a(a[2], f3), m19536a(a[3], f), m19536a(a[4], f3), m19536a(a[5], f), m19536a(a[6], f3), m19536a(a[7], f)};
            fArr2 = fArr3;
        } else {
            float[] fArr4 = new float[]{f17457a[0] * f, f17457a[1] * f2, f17457a[2] * f, f17457a[3] * f2, f17457a[4] * f, f17457a[5] * f2, f * f17457a[6], f2 * f17457a[7]};
            fArr = a;
            fArr2 = fArr4;
        }
        this.f17462f.clear();
        this.f17462f.put(fArr2).position(0);
        this.f17463g.clear();
        this.f17463g.put(fArr).position(0);
    }

    /* renamed from: a */
    private float m19536a(float f, float f2) {
        return f == 0.0f ? f2 : 1.0f - f2;
    }

    /* renamed from: a */
    public void m19554a(Rotation rotation, boolean z, boolean z2) {
        this.f17471o = rotation;
        this.f17472p = z;
        this.f17473q = z2;
        m19548f();
    }

    /* renamed from: d */
    public boolean m19558d() {
        return this.f17472p;
    }

    /* renamed from: e */
    public boolean m19559e() {
        return this.f17473q;
    }

    /* renamed from: a */
    protected void m19552a(Runnable runnable) {
        synchronized (this.f17470n) {
            this.f17470n.add(runnable);
        }
    }
}
