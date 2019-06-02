package com.beastbikes.android.modules.user.filter.p154a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import java.util.ArrayList;
import java.util.List;
import jp.co.cyberagent.android.gpuimage.C5421v;
import jp.co.cyberagent.android.gpuimage.be;

/* compiled from: IFImageFilter */
/* renamed from: com.beastbikes.android.modules.user.filter.a.g */
public class C2417g extends C5421v {
    /* renamed from: a */
    public int f11439a = -1;
    /* renamed from: b */
    public int f11440b = -1;
    /* renamed from: c */
    public int f11441c = -1;
    /* renamed from: d */
    public int f11442d = -1;
    /* renamed from: e */
    public int f11443e = -1;
    /* renamed from: j */
    private int f11444j;
    /* renamed from: k */
    private int f11445k;
    /* renamed from: l */
    private int f11446l;
    /* renamed from: m */
    private int f11447m;
    /* renamed from: n */
    private int f11448n;
    /* renamed from: o */
    private List<Integer> f11449o;
    /* renamed from: p */
    private Context f11450p;

    /* compiled from: IFImageFilter */
    /* renamed from: com.beastbikes.android.modules.user.filter.a.g$1 */
    class C24241 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2417g f11451a;

        C24241(C2417g c2417g) {
            this.f11451a = c2417g;
        }

        public void run() {
            Bitmap decodeResource = BitmapFactory.decodeResource(this.f11451a.f11450p.getResources(), ((Integer) this.f11451a.f11449o.get(0)).intValue());
            this.f11451a.f11439a = be.a(decodeResource, -1, true);
        }
    }

    /* compiled from: IFImageFilter */
    /* renamed from: com.beastbikes.android.modules.user.filter.a.g$2 */
    class C24252 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2417g f11452a;

        C24252(C2417g c2417g) {
            this.f11452a = c2417g;
        }

        public void run() {
            Bitmap decodeResource = BitmapFactory.decodeResource(this.f11452a.f11450p.getResources(), ((Integer) this.f11452a.f11449o.get(1)).intValue());
            this.f11452a.f11440b = be.a(decodeResource, -1, true);
        }
    }

    /* compiled from: IFImageFilter */
    /* renamed from: com.beastbikes.android.modules.user.filter.a.g$3 */
    class C24263 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2417g f11453a;

        C24263(C2417g c2417g) {
            this.f11453a = c2417g;
        }

        public void run() {
            Bitmap decodeResource = BitmapFactory.decodeResource(this.f11453a.f11450p.getResources(), ((Integer) this.f11453a.f11449o.get(2)).intValue());
            this.f11453a.f11441c = be.a(decodeResource, -1, true);
        }
    }

    /* compiled from: IFImageFilter */
    /* renamed from: com.beastbikes.android.modules.user.filter.a.g$4 */
    class C24274 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2417g f11454a;

        C24274(C2417g c2417g) {
            this.f11454a = c2417g;
        }

        public void run() {
            Bitmap decodeResource = BitmapFactory.decodeResource(this.f11454a.f11450p.getResources(), ((Integer) this.f11454a.f11449o.get(3)).intValue());
            this.f11454a.f11442d = be.a(decodeResource, -1, true);
        }
    }

    /* compiled from: IFImageFilter */
    /* renamed from: com.beastbikes.android.modules.user.filter.a.g$5 */
    class C24285 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C2417g f11455a;

        C24285(C2417g c2417g) {
            this.f11455a = c2417g;
        }

        public void run() {
            Bitmap decodeResource = BitmapFactory.decodeResource(this.f11455a.f11450p.getResources(), ((Integer) this.f11455a.f11449o.get(4)).intValue());
            this.f11455a.f11443e = be.a(decodeResource, -1, true);
        }
    }

    public C2417g(Context context, String str) {
        super("attribute vec4 position;\nattribute vec4 inputTextureCoordinate;\n \nvarying vec2 textureCoordinate;\n \nvoid main()\n{\n    gl_Position = position;\n    textureCoordinate = inputTextureCoordinate.xy;\n}", str);
        this.f11450p = context;
    }

    /* renamed from: a */
    public void m12278a() {
        super.a();
        this.f11444j = GLES20.glGetUniformLocation(k(), "inputImageTexture2");
        this.f11445k = GLES20.glGetUniformLocation(k(), "inputImageTexture3");
        this.f11446l = GLES20.glGetUniformLocation(k(), "inputImageTexture4");
        this.f11447m = GLES20.glGetUniformLocation(k(), "inputImageTexture5");
        this.f11448n = GLES20.glGetUniformLocation(k(), "inputImageTexture6");
        m12282d();
    }

    /* renamed from: b */
    public void m12280b() {
        super.b();
        if (this.f11439a != -1) {
            GLES20.glDeleteTextures(1, new int[]{this.f11439a}, 0);
            this.f11439a = -1;
        }
        if (this.f11440b != -1) {
            GLES20.glDeleteTextures(1, new int[]{this.f11440b}, 0);
            this.f11440b = -1;
        }
        if (this.f11441c != -1) {
            GLES20.glDeleteTextures(1, new int[]{this.f11441c}, 0);
            this.f11441c = -1;
        }
        if (this.f11442d != -1) {
            GLES20.glDeleteTextures(1, new int[]{this.f11442d}, 0);
            this.f11442d = -1;
        }
        if (this.f11443e != -1) {
            GLES20.glDeleteTextures(1, new int[]{this.f11443e}, 0);
            this.f11443e = -1;
        }
    }

    /* renamed from: c */
    protected void m12281c() {
        super.c();
        if (this.f11439a != -1) {
            GLES20.glActiveTexture(33987);
            GLES20.glBindTexture(3553, this.f11439a);
            GLES20.glUniform1i(this.f11444j, 3);
        }
        if (this.f11440b != -1) {
            GLES20.glActiveTexture(33988);
            GLES20.glBindTexture(3553, this.f11440b);
            GLES20.glUniform1i(this.f11445k, 4);
        }
        if (this.f11441c != -1) {
            GLES20.glActiveTexture(33989);
            GLES20.glBindTexture(3553, this.f11441c);
            GLES20.glUniform1i(this.f11446l, 5);
        }
        if (this.f11442d != -1) {
            GLES20.glActiveTexture(33990);
            GLES20.glBindTexture(3553, this.f11442d);
            GLES20.glUniform1i(this.f11447m, 6);
        }
        if (this.f11443e != -1) {
            GLES20.glActiveTexture(33991);
            GLES20.glBindTexture(3553, this.f11443e);
            GLES20.glUniform1i(this.f11448n, 7);
        }
    }

    /* renamed from: a */
    public void m12279a(int i) {
        if (this.f11449o == null) {
            this.f11449o = new ArrayList();
        }
        this.f11449o.add(Integer.valueOf(i));
    }

    /* renamed from: d */
    public void m12282d() {
        if (this.f11449o != null) {
            if (this.f11449o.size() > 0) {
                a(new C24241(this));
            }
            if (this.f11449o.size() > 1) {
                a(new C24252(this));
            }
            if (this.f11449o.size() > 2) {
                a(new C24263(this));
            }
            if (this.f11449o.size() > 3) {
                a(new C24274(this));
            }
            if (this.f11449o.size() > 4) {
                a(new C24285(this));
            }
        }
    }
}
