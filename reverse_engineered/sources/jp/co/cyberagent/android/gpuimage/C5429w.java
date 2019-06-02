package jp.co.cyberagent.android.gpuimage;

import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.p205a.C5420a;

/* compiled from: GPUImageFilterGroup */
/* renamed from: jp.co.cyberagent.android.gpuimage.w */
public class C5429w extends C5421v {
    /* renamed from: a */
    private final List<C5421v> f17485a;
    /* renamed from: b */
    private int[] f17486b;
    /* renamed from: c */
    private int[] f17487c;
    /* renamed from: d */
    private final FloatBuffer f17488d = ByteBuffer.allocateDirect(aq.f17457a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
    /* renamed from: e */
    private final FloatBuffer f17489e;
    /* renamed from: j */
    private final FloatBuffer f17490j;

    public C5429w(List<C5421v> list) {
        this.f17485a = list;
        this.f17488d.put(aq.f17457a).position(0);
        this.f17489e = ByteBuffer.allocateDirect(C5420a.f17393a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f17489e.put(C5420a.f17393a).position(0);
        float[] a = C5420a.m19484a(Rotation.NORMAL, false, true);
        this.f17490j = ByteBuffer.allocateDirect(a.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.f17490j.put(a).position(0);
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        for (C5421v e : this.f17485a) {
            e.m19496e();
        }
    }

    /* renamed from: b */
    public void mo6686b() {
        m19568m();
        for (C5421v f : this.f17485a) {
            f.m19497f();
        }
        super.mo6686b();
    }

    /* renamed from: m */
    private void m19568m() {
        if (this.f17487c != null) {
            GLES20.glDeleteTextures(this.f17487c.length, this.f17487c, 0);
            this.f17487c = null;
        }
        if (this.f17486b != null) {
            GLES20.glDeleteFramebuffers(this.f17486b.length, this.f17486b, 0);
            this.f17486b = null;
        }
    }

    /* renamed from: a */
    public void mo6684a(int i, int i2) {
        super.mo6684a(i, i2);
        if (this.f17486b != null) {
            m19568m();
        }
        this.f17486b = new int[(this.f17485a.size() - 1)];
        this.f17487c = new int[(this.f17485a.size() - 1)];
        for (int i3 = 0; i3 < this.f17485a.size() - 1; i3++) {
            ((C5421v) this.f17485a.get(i3)).mo6684a(i, i2);
            GLES20.glGenFramebuffers(1, this.f17486b, i3);
            GLES20.glGenTextures(1, this.f17487c, i3);
            GLES20.glBindTexture(3553, this.f17487c[i3]);
            GLES20.glTexImage2D(3553, 0, 6408, i, i2, 0, 6408, 5121, null);
            GLES20.glTexParameterf(3553, 10240, 9729.0f);
            GLES20.glTexParameterf(3553, 10241, 9729.0f);
            GLES20.glTexParameterf(3553, 10242, 33071.0f);
            GLES20.glTexParameterf(3553, 10243, 33071.0f);
            GLES20.glBindFramebuffer(36160, this.f17486b[i3]);
            GLES20.glFramebufferTexture2D(36160, 36064, 3553, this.f17487c[i3], 0);
            GLES20.glBindTexture(3553, 0);
            GLES20.glBindFramebuffer(36160, 0);
        }
        ((C5421v) this.f17485a.get(this.f17485a.size() - 1)).mo6684a(i, i2);
    }

    /* renamed from: a */
    public void mo6688a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        m19498g();
        if (m19499h() && this.f17486b != null && this.f17487c != null) {
            int i2 = 0;
            while (i2 < this.f17485a.size() - 1) {
                C5421v c5421v = (C5421v) this.f17485a.get(i2);
                GLES20.glBindFramebuffer(36160, this.f17486b[i2]);
                GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
                FloatBuffer floatBuffer3 = this.f17488d;
                FloatBuffer floatBuffer4 = (i2 == 0 && this.f17485a.size() % 2 == 0) ? this.f17490j : this.f17489e;
                c5421v.mo6688a(i, floatBuffer3, floatBuffer4);
                GLES20.glBindFramebuffer(36160, 0);
                i = this.f17487c[i2];
                i2++;
            }
            ((C5421v) this.f17485a.get(this.f17485a.size() - 1)).mo6688a(i, floatBuffer, floatBuffer2);
        }
    }

    /* renamed from: l */
    public List<C5421v> m19573l() {
        return this.f17485a;
    }
}
