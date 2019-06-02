package jp.co.cyberagent.android.gpuimage;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import jp.co.cyberagent.android.gpuimage.p205a.C5420a;

/* compiled from: GPUImageTwoInputFilter */
public class bb extends C5421v {
    /* renamed from: a */
    public int f17419a;
    /* renamed from: b */
    public int f17420b;
    /* renamed from: c */
    public int f17421c;
    /* renamed from: d */
    private ByteBuffer f17422d;
    /* renamed from: e */
    private Bitmap f17423e;

    public bb(String str) {
        this("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\nattribute vec4 inputTextureCoordinate2;\n \nvarying vec2 textureCoordinate;\nvarying vec2 textureCoordinate2;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n    textureCoordinate2 = inputTextureCoordinate2.xy;\n}", str);
    }

    public bb(String str, String str2) {
        super(str, str2);
        this.f17421c = -1;
        m19514a(Rotation.NORMAL, false, false);
    }

    /* renamed from: a */
    public void mo6683a() {
        super.mo6683a();
        this.f17419a = GLES20.glGetAttribLocation(m19502k(), "inputTextureCoordinate2");
        this.f17420b = GLES20.glGetUniformLocation(m19502k(), "inputImageTexture2");
        GLES20.glEnableVertexAttribArray(this.f17419a);
        if (this.f17423e != null) {
            m19513a(this.f17423e);
        }
    }

    /* renamed from: a */
    public void m19513a(final Bitmap bitmap) {
        this.f17423e = bitmap;
        m19491a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ bb f17504b;

            public void run() {
                if (this.f17504b.f17421c == -1) {
                    GLES20.glActiveTexture(33987);
                    this.f17504b.f17421c = be.m19602a(bitmap, -1, false);
                }
            }
        });
    }

    /* renamed from: b */
    public void mo6686b() {
        super.mo6686b();
        GLES20.glDeleteTextures(1, new int[]{this.f17421c}, 0);
        this.f17421c = -1;
    }

    /* renamed from: c */
    protected void mo6687c() {
        GLES20.glEnableVertexAttribArray(this.f17419a);
        GLES20.glActiveTexture(33987);
        GLES20.glBindTexture(3553, this.f17421c);
        GLES20.glUniform1i(this.f17420b, 3);
        this.f17422d.position(0);
        GLES20.glVertexAttribPointer(this.f17419a, 2, 5126, false, 0, this.f17422d);
    }

    /* renamed from: a */
    public void m19514a(Rotation rotation, boolean z, boolean z2) {
        float[] a = C5420a.m19484a(rotation, z, z2);
        ByteBuffer order = ByteBuffer.allocateDirect(32).order(ByteOrder.nativeOrder());
        FloatBuffer asFloatBuffer = order.asFloatBuffer();
        asFloatBuffer.put(a);
        asFloatBuffer.flip();
        this.f17422d = order;
    }
}
