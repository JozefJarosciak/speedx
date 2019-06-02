package jp.co.cyberagent.android.gpuimage;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.opengl.GLSurfaceView.Renderer;
import java.nio.Buffer;
import java.nio.IntBuffer;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

/* compiled from: PixelBuffer */
public class bf {
    /* renamed from: a */
    Renderer f17517a;
    /* renamed from: b */
    int f17518b;
    /* renamed from: c */
    int f17519c;
    /* renamed from: d */
    Bitmap f17520d;
    /* renamed from: e */
    EGL10 f17521e = ((EGL10) EGLContext.getEGL());
    /* renamed from: f */
    EGLDisplay f17522f = this.f17521e.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
    /* renamed from: g */
    EGLConfig[] f17523g;
    /* renamed from: h */
    EGLConfig f17524h;
    /* renamed from: i */
    EGLContext f17525i;
    /* renamed from: j */
    EGLSurface f17526j;
    /* renamed from: k */
    GL10 f17527k;
    /* renamed from: l */
    String f17528l;

    public bf(int i, int i2) {
        this.f17518b = i;
        this.f17519c = i2;
        r1 = new int[2];
        int[] iArr = new int[]{12375, this.f17518b, 12374, this.f17519c, 12344};
        this.f17521e.eglInitialize(this.f17522f, r1);
        this.f17524h = m19606c();
        this.f17525i = this.f17521e.eglCreateContext(this.f17522f, this.f17524h, EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
        this.f17526j = this.f17521e.eglCreatePbufferSurface(this.f17522f, this.f17524h, iArr);
        this.f17521e.eglMakeCurrent(this.f17522f, this.f17526j, this.f17526j, this.f17525i);
        this.f17527k = (GL10) this.f17525i.getGL();
        this.f17528l = Thread.currentThread().getName();
    }

    /* renamed from: a */
    public void m19609a(Renderer renderer) {
        this.f17517a = renderer;
        if (Thread.currentThread().getName().equals(this.f17528l)) {
            this.f17517a.onSurfaceCreated(this.f17527k, this.f17524h);
            this.f17517a.onSurfaceChanged(this.f17527k, this.f17518b, this.f17519c);
        }
    }

    /* renamed from: a */
    public Bitmap m19608a() {
        if (this.f17517a == null || !Thread.currentThread().getName().equals(this.f17528l)) {
            return null;
        }
        this.f17517a.onDrawFrame(this.f17527k);
        this.f17517a.onDrawFrame(this.f17527k);
        m19607d();
        return this.f17520d;
    }

    /* renamed from: b */
    public void m19610b() {
        this.f17517a.onDrawFrame(this.f17527k);
        this.f17517a.onDrawFrame(this.f17527k);
        this.f17521e.eglMakeCurrent(this.f17522f, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
        this.f17521e.eglDestroySurface(this.f17522f, this.f17526j);
        this.f17521e.eglDestroyContext(this.f17522f, this.f17525i);
        this.f17521e.eglTerminate(this.f17522f);
    }

    /* renamed from: c */
    private EGLConfig m19606c() {
        int[] iArr = new int[]{12325, 0, 12326, 0, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12344};
        int[] iArr2 = new int[1];
        this.f17521e.eglChooseConfig(this.f17522f, iArr, null, 0, iArr2);
        int i = iArr2[0];
        this.f17523g = new EGLConfig[i];
        this.f17521e.eglChooseConfig(this.f17522f, iArr, this.f17523g, i, iArr2);
        return this.f17523g[0];
    }

    /* renamed from: d */
    private void m19607d() {
        Buffer allocate = IntBuffer.allocate(this.f17518b * this.f17519c);
        Buffer allocate2 = IntBuffer.allocate(this.f17518b * this.f17519c);
        this.f17527k.glReadPixels(0, 0, this.f17518b, this.f17519c, 6408, 5121, allocate);
        for (int i = 0; i < this.f17519c; i++) {
            for (int i2 = 0; i2 < this.f17518b; i2++) {
                allocate2.put((((this.f17519c - i) - 1) * this.f17518b) + i2, allocate.get((this.f17518b * i) + i2));
            }
        }
        this.f17520d = Bitmap.createBitmap(this.f17518b, this.f17519c, Config.ARGB_8888);
        this.f17520d.copyPixelsFromBuffer(allocate2);
    }
}
