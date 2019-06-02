package com.journeyapps.barcodescanner.camera;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.google.zxing.client.android.C4087R;
import com.journeyapps.barcodescanner.C4168l;
import com.journeyapps.barcodescanner.C4170n;

/* compiled from: CameraInstance */
/* renamed from: com.journeyapps.barcodescanner.camera.b */
public class C4139b {
    /* renamed from: a */
    private static final String f14735a = C4139b.class.getSimpleName();
    /* renamed from: b */
    private C4143e f14736b;
    /* renamed from: c */
    private C4142d f14737c;
    /* renamed from: d */
    private C4141c f14738d;
    /* renamed from: e */
    private Handler f14739e;
    /* renamed from: f */
    private C4146g f14740f;
    /* renamed from: g */
    private boolean f14741g = false;
    /* renamed from: h */
    private CameraSettings f14742h = new CameraSettings();
    /* renamed from: i */
    private Runnable f14743i = new C41353(this);
    /* renamed from: j */
    private Runnable f14744j = new C41364(this);
    /* renamed from: k */
    private Runnable f14745k = new C41375(this);
    /* renamed from: l */
    private Runnable f14746l = new C41386(this);

    /* compiled from: CameraInstance */
    /* renamed from: com.journeyapps.barcodescanner.camera.b$3 */
    class C41353 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C4139b f14731a;

        C41353(C4139b c4139b) {
            this.f14731a = c4139b;
        }

        public void run() {
            try {
                Log.d(C4139b.f14735a, "Opening camera");
                this.f14731a.f14738d.m16603a();
            } catch (Throwable e) {
                this.f14731a.m16574a((Exception) e);
                Log.e(C4139b.f14735a, "Failed to open camera", e);
            }
        }
    }

    /* compiled from: CameraInstance */
    /* renamed from: com.journeyapps.barcodescanner.camera.b$4 */
    class C41364 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C4139b f14732a;

        C41364(C4139b c4139b) {
            this.f14732a = c4139b;
        }

        public void run() {
            try {
                Log.d(C4139b.f14735a, "Configuring camera");
                this.f14732a.f14738d.m16609b();
                if (this.f14732a.f14739e != null) {
                    this.f14732a.f14739e.obtainMessage(C4087R.id.zxing_prewiew_size_ready, this.f14732a.m16580h()).sendToTarget();
                }
            } catch (Throwable e) {
                this.f14732a.m16574a((Exception) e);
                Log.e(C4139b.f14735a, "Failed to configure camera", e);
            }
        }
    }

    /* compiled from: CameraInstance */
    /* renamed from: com.journeyapps.barcodescanner.camera.b$5 */
    class C41375 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C4139b f14733a;

        C41375(C4139b c4139b) {
            this.f14733a = c4139b;
        }

        public void run() {
            try {
                Log.d(C4139b.f14735a, "Starting preview");
                this.f14733a.f14738d.m16605a(this.f14733a.f14737c);
                this.f14733a.f14738d.m16610c();
            } catch (Throwable e) {
                this.f14733a.m16574a((Exception) e);
                Log.e(C4139b.f14735a, "Failed to start preview", e);
            }
        }
    }

    /* compiled from: CameraInstance */
    /* renamed from: com.journeyapps.barcodescanner.camera.b$6 */
    class C41386 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C4139b f14734a;

        C41386(C4139b c4139b) {
            this.f14734a = c4139b;
        }

        public void run() {
            try {
                Log.d(C4139b.f14735a, "Closing camera");
                this.f14734a.f14738d.m16611d();
                this.f14734a.f14738d.m16612e();
            } catch (Throwable e) {
                Log.e(C4139b.f14735a, "Failed to close camera", e);
            }
            this.f14734a.f14736b.m16622b();
        }
    }

    public C4139b(Context context) {
        C4170n.m16706a();
        this.f14736b = C4143e.m16618a();
        this.f14738d = new C4141c(context);
        this.f14738d.m16604a(this.f14742h);
    }

    /* renamed from: a */
    public void m16586a(C4146g c4146g) {
        this.f14740f = c4146g;
        this.f14738d.m16606a(c4146g);
    }

    /* renamed from: a */
    public C4146g m16582a() {
        return this.f14740f;
    }

    /* renamed from: a */
    public void m16583a(Handler handler) {
        this.f14739e = handler;
    }

    /* renamed from: a */
    public void m16585a(C4142d c4142d) {
        this.f14737c = c4142d;
    }

    /* renamed from: a */
    public void m16584a(CameraSettings cameraSettings) {
        if (!this.f14741g) {
            this.f14742h = cameraSettings;
            this.f14738d.m16604a(cameraSettings);
        }
    }

    /* renamed from: h */
    private C4168l m16580h() {
        return this.f14738d.m16615h();
    }

    /* renamed from: b */
    public void m16589b() {
        C4170n.m16706a();
        this.f14741g = true;
        this.f14736b.m16623b(this.f14743i);
    }

    /* renamed from: c */
    public void m16590c() {
        C4170n.m16706a();
        m16581i();
        this.f14736b.m16621a(this.f14744j);
    }

    /* renamed from: d */
    public void m16591d() {
        C4170n.m16706a();
        m16581i();
        this.f14736b.m16621a(this.f14745k);
    }

    /* renamed from: a */
    public void m16588a(final boolean z) {
        C4170n.m16706a();
        if (this.f14741g) {
            this.f14736b.m16621a(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C4139b f14728b;

                public void run() {
                    this.f14728b.f14738d.m16608a(z);
                }
            });
        }
    }

    /* renamed from: e */
    public void m16592e() {
        C4170n.m16706a();
        if (this.f14741g) {
            this.f14736b.m16621a(this.f14746l);
        }
        this.f14741g = false;
    }

    /* renamed from: f */
    public boolean m16593f() {
        return this.f14741g;
    }

    /* renamed from: a */
    public void m16587a(final C4149j c4149j) {
        m16581i();
        this.f14736b.m16621a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C4139b f14730b;

            public void run() {
                this.f14730b.f14738d.m16607a(c4149j);
            }
        });
    }

    /* renamed from: i */
    private void m16581i() {
        if (!this.f14741g) {
            throw new IllegalStateException("CameraInstance is not open");
        }
    }

    /* renamed from: a */
    private void m16574a(Exception exception) {
        if (this.f14739e != null) {
            this.f14739e.obtainMessage(C4087R.id.zxing_camera_error, exception).sendToTarget();
        }
    }
}
