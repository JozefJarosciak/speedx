package com.baidu.platform.comapi.map;

import android.annotation.SuppressLint;
import android.graphics.SurfaceTexture;
import android.opengl.GLUtils;
import java.lang.Thread.State;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;
import javax.microedition.khronos.opengles.GL10;

@SuppressLint({"NewApi"})
/* renamed from: com.baidu.platform.comapi.map.m */
public class C1257m extends Thread {
    /* renamed from: a */
    private AtomicBoolean f3807a;
    /* renamed from: b */
    private SurfaceTexture f3808b;
    /* renamed from: c */
    private C1236a f3809c;
    /* renamed from: d */
    private EGL10 f3810d;
    /* renamed from: e */
    private EGLDisplay f3811e = EGL10.EGL_NO_DISPLAY;
    /* renamed from: f */
    private EGLContext f3812f = EGL10.EGL_NO_CONTEXT;
    /* renamed from: g */
    private EGLSurface f3813g = EGL10.EGL_NO_SURFACE;
    /* renamed from: h */
    private GL10 f3814h;
    /* renamed from: i */
    private int f3815i = 1;
    /* renamed from: j */
    private boolean f3816j = false;
    /* renamed from: k */
    private final C1237F f3817k;

    /* renamed from: com.baidu.platform.comapi.map.m$a */
    public interface C1236a {
        /* renamed from: a */
        int mo2674a();
    }

    public C1257m(SurfaceTexture surfaceTexture, C1236a c1236a, AtomicBoolean atomicBoolean, C1237F c1237f) {
        this.f3808b = surfaceTexture;
        this.f3809c = c1236a;
        this.f3807a = atomicBoolean;
        this.f3817k = c1237f;
    }

    /* renamed from: a */
    private boolean m4772a(int i, int i2, int i3, int i4, int i5, int i6) {
        this.f3810d = (EGL10) EGLContext.getEGL();
        this.f3811e = this.f3810d.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        if (this.f3811e == EGL10.EGL_NO_DISPLAY) {
            throw new RuntimeException("eglGetdisplay failed : " + GLUtils.getEGLErrorString(this.f3810d.eglGetError()));
        }
        if (this.f3810d.eglInitialize(this.f3811e, new int[2])) {
            EGLConfig[] eGLConfigArr = new EGLConfig[100];
            int[] iArr = new int[1];
            if (!this.f3810d.eglChooseConfig(this.f3811e, new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344}, eGLConfigArr, 100, iArr) || iArr[0] <= 0) {
                return false;
            }
            this.f3812f = this.f3810d.eglCreateContext(this.f3811e, eGLConfigArr[0], EGL10.EGL_NO_CONTEXT, new int[]{12440, 2, 12344});
            this.f3813g = this.f3810d.eglCreateWindowSurface(this.f3811e, eGLConfigArr[0], this.f3808b, null);
            if (this.f3813g == EGL10.EGL_NO_SURFACE || this.f3812f == EGL10.EGL_NO_CONTEXT) {
                if (this.f3810d.eglGetError() == 12299) {
                    throw new RuntimeException("eglCreateWindowSurface returned EGL_BAD_NATIVE_WINDOW. ");
                }
                GLUtils.getEGLErrorString(this.f3810d.eglGetError());
            }
            if (this.f3810d.eglMakeCurrent(this.f3811e, this.f3813g, this.f3813g, this.f3812f)) {
                this.f3814h = (GL10) this.f3812f.getGL();
                return true;
            }
            throw new RuntimeException("eglMakeCurrent failed : " + GLUtils.getEGLErrorString(this.f3810d.eglGetError()));
        }
        throw new RuntimeException("eglInitialize failed : " + GLUtils.getEGLErrorString(this.f3810d.eglGetError()));
    }

    /* renamed from: b */
    private static boolean m4773b(int i, int i2, int i3, int i4, int i5, int i6) {
        EGL10 egl10 = (EGL10) EGLContext.getEGL();
        EGLDisplay eglGetDisplay = egl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
        egl10.eglInitialize(eglGetDisplay, new int[2]);
        int[] iArr = new int[1];
        return egl10.eglChooseConfig(eglGetDisplay, new int[]{12324, i, 12323, i2, 12322, i3, 12321, i4, 12325, i5, 12326, i6, 12344}, new EGLConfig[100], 100, iArr) && iArr[0] > 0;
    }

    /* renamed from: d */
    private void m4774d() {
        try {
            if (C1257m.m4773b(5, 6, 5, 0, 24, 0)) {
                m4772a(5, 6, 5, 0, 24, 0);
            } else {
                m4772a(8, 8, 8, 0, 16, 0);
            }
        } catch (IllegalArgumentException e) {
            m4772a(8, 8, 8, 0, 16, 0);
        }
        MapRenderer.nativeInit(this.f3817k.m4625b().f3761h);
        MapRenderer.nativeResize(this.f3817k.m4625b().f3761h, C1237F.f3697a, C1237F.f3698b);
    }

    /* renamed from: e */
    private void m4775e() {
        this.f3810d.eglDestroyContext(this.f3811e, this.f3812f);
        this.f3810d.eglDestroySurface(this.f3811e, this.f3813g);
        this.f3810d.eglTerminate(this.f3811e);
        this.f3812f = EGL10.EGL_NO_CONTEXT;
        this.f3813g = EGL10.EGL_NO_SURFACE;
    }

    /* renamed from: a */
    public void m4776a() {
        this.f3815i = 1;
        synchronized (this) {
            if (getState() == State.WAITING) {
                notify();
            }
        }
    }

    /* renamed from: b */
    public void m4777b() {
        this.f3815i = 0;
    }

    /* renamed from: c */
    public void m4778c() {
        this.f3816j = true;
        synchronized (this) {
            if (getState() == State.WAITING) {
                notify();
            }
        }
    }

    public void run() {
        m4774d();
        while (this.f3809c != null) {
            if (this.f3815i != 1) {
                try {
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (this.f3817k.m4625b() == null) {
                break;
            } else {
                synchronized (this.f3817k.m4625b()) {
                    this.f3815i = this.f3809c.mo2674a();
                    for (C1114l c1114l : this.f3817k.m4625b().f3759f) {
                        C1235E H = this.f3817k.m4625b().m4664H();
                        if (this.f3814h == null) {
                            return;
                        }
                        c1114l.mo2631a(this.f3814h, H);
                    }
                    this.f3810d.eglSwapBuffers(this.f3811e, this.f3813g);
                }
            }
            if (this.f3816j) {
                break;
            }
        }
        m4775e();
    }
}
