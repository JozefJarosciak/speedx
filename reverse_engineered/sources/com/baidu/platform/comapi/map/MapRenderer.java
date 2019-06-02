package com.baidu.platform.comapi.map;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MapRenderer implements Renderer {
    /* renamed from: d */
    private static final String f3705d = MapRenderer.class.getSimpleName();
    /* renamed from: a */
    public int f3706a;
    /* renamed from: b */
    public int f3707b;
    /* renamed from: c */
    public int f3708c;
    /* renamed from: e */
    private long f3709e;
    /* renamed from: f */
    private C1243a f3710f;
    /* renamed from: g */
    private final C1255j f3711g;

    /* renamed from: com.baidu.platform.comapi.map.MapRenderer$a */
    public interface C1243a {
        /* renamed from: e */
        void mo2677e();
    }

    public MapRenderer(C1255j c1255j, C1243a c1243a) {
        this.f3710f = c1243a;
        this.f3711g = c1255j;
    }

    /* renamed from: a */
    private void m4632a(GL10 gl10) {
        GLES20.glClear(16640);
        GLES20.glClearColor(0.85f, 0.8f, 0.8f, 0.0f);
    }

    /* renamed from: a */
    private boolean m4633a() {
        return this.f3709e != 0;
    }

    public static native void nativeInit(long j);

    public static native int nativeRender(long j);

    public static native void nativeResize(long j, int i, int i2);

    /* renamed from: a */
    public void m4634a(long j) {
        this.f3709e = j;
    }

    public void onDrawFrame(GL10 gl10) {
        if (m4633a()) {
            if (this.f3708c <= 1) {
                nativeResize(this.f3709e, this.f3706a, this.f3707b);
                this.f3708c++;
            }
            this.f3710f.mo2677e();
            int nativeRender = nativeRender(this.f3709e);
            for (C1114l a : this.f3711g.m4760a().f3759f) {
                a.mo2631a(gl10, this.f3711g.m4760a().m4664H());
            }
            C1255j c1255j = this.f3711g;
            if (nativeRender == 1) {
                c1255j.requestRender();
                return;
            } else if (this.f3711g.m4760a().m4706c()) {
                if (c1255j.getRenderMode() != 1) {
                    c1255j.setRenderMode(1);
                    return;
                }
                return;
            } else if (c1255j.getRenderMode() != 0) {
                c1255j.setRenderMode(0);
                return;
            } else {
                return;
            }
        }
        m4632a(gl10);
    }

    public void onSurfaceChanged(GL10 gl10, int i, int i2) {
        if (this.f3709e != 0) {
            nativeResize(this.f3709e, i, i2);
        }
    }

    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        nativeInit(this.f3709e);
        if (m4633a()) {
            this.f3710f.mo2677e();
        }
    }
}
