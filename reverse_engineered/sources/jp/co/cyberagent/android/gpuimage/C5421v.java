package jp.co.cyberagent.android.gpuimage;

import android.graphics.PointF;
import android.opengl.GLES20;
import java.nio.FloatBuffer;
import java.util.LinkedList;

/* compiled from: GPUImageFilter */
/* renamed from: jp.co.cyberagent.android.gpuimage.v */
public class C5421v {
    /* renamed from: a */
    private final LinkedList<Runnable> f17397a;
    /* renamed from: b */
    private final String f17398b;
    /* renamed from: c */
    private final String f17399c;
    /* renamed from: d */
    private int f17400d;
    /* renamed from: e */
    private int f17401e;
    /* renamed from: f */
    protected int f17402f;
    /* renamed from: g */
    protected int f17403g;
    /* renamed from: h */
    protected int f17404h;
    /* renamed from: i */
    protected int f17405i;
    /* renamed from: j */
    private boolean f17406j;

    public C5421v() {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", "varying highp vec2 textureCoordinate;\n \nuniform sampler2D inputImageTexture;\n \nvoid main()\n{\n     gl_FragColor = texture2D(inputImageTexture, textureCoordinate);\n}");
    }

    public C5421v(String str, String str2) {
        this.f17397a = new LinkedList();
        this.f17398b = str;
        this.f17399c = str2;
    }

    /* renamed from: e */
    public final void m19496e() {
        mo6683a();
        this.f17406j = true;
        d_();
    }

    /* renamed from: a */
    public void mo6683a() {
        this.f17402f = be.m19604a(this.f17398b, this.f17399c);
        this.f17403g = GLES20.glGetAttribLocation(this.f17402f, "position");
        this.f17404h = GLES20.glGetUniformLocation(this.f17402f, "inputImageTexture");
        this.f17405i = GLES20.glGetAttribLocation(this.f17402f, "inputTextureCoordinate");
        this.f17406j = true;
    }

    public void d_() {
    }

    /* renamed from: f */
    public final void m19497f() {
        this.f17406j = false;
        GLES20.glDeleteProgram(this.f17402f);
        mo6686b();
    }

    /* renamed from: b */
    public void mo6686b() {
    }

    /* renamed from: a */
    public void mo6684a(int i, int i2) {
        this.f17400d = i;
        this.f17401e = i2;
    }

    /* renamed from: a */
    public void mo6688a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        GLES20.glUseProgram(this.f17402f);
        m19498g();
        if (this.f17406j) {
            floatBuffer.position(0);
            GLES20.glVertexAttribPointer(this.f17403g, 2, 5126, false, 0, floatBuffer);
            GLES20.glEnableVertexAttribArray(this.f17403g);
            floatBuffer2.position(0);
            GLES20.glVertexAttribPointer(this.f17405i, 2, 5126, false, 0, floatBuffer2);
            GLES20.glEnableVertexAttribArray(this.f17405i);
            if (i != -1) {
                GLES20.glActiveTexture(33984);
                GLES20.glBindTexture(3553, i);
                GLES20.glUniform1i(this.f17404h, 0);
            }
            mo6687c();
            GLES20.glDrawArrays(5, 0, 4);
            GLES20.glDisableVertexAttribArray(this.f17403g);
            GLES20.glDisableVertexAttribArray(this.f17405i);
            GLES20.glBindTexture(3553, 0);
        }
    }

    /* renamed from: c */
    protected void mo6687c() {
    }

    /* renamed from: g */
    protected void m19498g() {
        while (!this.f17397a.isEmpty()) {
            ((Runnable) this.f17397a.removeFirst()).run();
        }
    }

    /* renamed from: h */
    public boolean m19499h() {
        return this.f17406j;
    }

    /* renamed from: i */
    public int m19500i() {
        return this.f17400d;
    }

    /* renamed from: j */
    public int m19501j() {
        return this.f17401e;
    }

    /* renamed from: k */
    public int m19502k() {
        return this.f17402f;
    }

    /* renamed from: a */
    protected void m19486a(final int i, final float f) {
        m19491a(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C5421v f17544c;

            public void run() {
                GLES20.glUniform1f(i, f);
            }
        });
    }

    /* renamed from: a */
    protected void m19490a(final int i, final float[] fArr) {
        m19491a(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C5421v f17547c;

            public void run() {
                GLES20.glUniform3fv(i, 1, FloatBuffer.wrap(fArr));
            }
        });
    }

    /* renamed from: a */
    protected void m19488a(final int i, final PointF pointF) {
        m19491a(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C5421v f17550c;

            public void run() {
                GLES20.glUniform2fv(i, 1, new float[]{pointF.x, pointF.y}, 0);
            }
        });
    }

    /* renamed from: b */
    protected void m19493b(final int i, final float[] fArr) {
        m19491a(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C5421v f17553c;

            public void run() {
                GLES20.glUniformMatrix3fv(i, 1, false, fArr, 0);
            }
        });
    }

    /* renamed from: c */
    protected void m19495c(final int i, final float[] fArr) {
        m19491a(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ C5421v f17556c;

            public void run() {
                GLES20.glUniformMatrix4fv(i, 1, false, fArr, 0);
            }
        });
    }

    /* renamed from: a */
    protected void m19491a(Runnable runnable) {
        synchronized (this.f17397a) {
            this.f17397a.addLast(runnable);
        }
    }
}
