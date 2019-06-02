package com.journeyapps.barcodescanner.camera;

import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: AutoFocusManager */
/* renamed from: com.journeyapps.barcodescanner.camera.a */
public final class C4132a {
    /* renamed from: a */
    private static final String f14717a = C4132a.class.getSimpleName();
    /* renamed from: h */
    private static final Collection<String> f14718h = new ArrayList(2);
    /* renamed from: b */
    private boolean f14719b;
    /* renamed from: c */
    private boolean f14720c;
    /* renamed from: d */
    private final boolean f14721d;
    /* renamed from: e */
    private final Camera f14722e;
    /* renamed from: f */
    private Handler f14723f = new Handler(this.f14725i);
    /* renamed from: g */
    private int f14724g = 1;
    /* renamed from: i */
    private final Callback f14725i = new C41291(this);
    /* renamed from: j */
    private final AutoFocusCallback f14726j = new C41312(this);

    /* compiled from: AutoFocusManager */
    /* renamed from: com.journeyapps.barcodescanner.camera.a$1 */
    class C41291 implements Callback {
        /* renamed from: a */
        final /* synthetic */ C4132a f14714a;

        C41291(C4132a c4132a) {
            this.f14714a = c4132a;
        }

        public boolean handleMessage(Message message) {
            if (message.what != this.f14714a.f14724g) {
                return false;
            }
            this.f14714a.m16568d();
            return true;
        }
    }

    /* compiled from: AutoFocusManager */
    /* renamed from: com.journeyapps.barcodescanner.camera.a$2 */
    class C41312 implements AutoFocusCallback {
        /* renamed from: a */
        final /* synthetic */ C4132a f14716a;

        /* compiled from: AutoFocusManager */
        /* renamed from: com.journeyapps.barcodescanner.camera.a$2$1 */
        class C41301 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C41312 f14715a;

            C41301(C41312 c41312) {
                this.f14715a = c41312;
            }

            public void run() {
                this.f14715a.f14716a.f14720c = false;
                this.f14715a.f14716a.m16565c();
            }
        }

        C41312(C4132a c4132a) {
            this.f14716a = c4132a;
        }

        public void onAutoFocus(boolean z, Camera camera) {
            this.f14716a.f14723f.post(new C41301(this));
        }
    }

    static {
        f14718h.add("auto");
        f14718h.add("macro");
    }

    public C4132a(Camera camera, CameraSettings cameraSettings) {
        boolean z = true;
        this.f14722e = camera;
        String focusMode = camera.getParameters().getFocusMode();
        if (!(cameraSettings.m16559f() && f14718h.contains(focusMode))) {
            z = false;
        }
        this.f14721d = z;
        Log.i(f14717a, "Current focus mode '" + focusMode + "'; use auto focus? " + this.f14721d);
        m16570a();
    }

    /* renamed from: c */
    private synchronized void m16565c() {
        if (!(this.f14719b || this.f14723f.hasMessages(this.f14724g))) {
            this.f14723f.sendMessageDelayed(this.f14723f.obtainMessage(this.f14724g), 2000);
        }
    }

    /* renamed from: a */
    public void m16570a() {
        this.f14719b = false;
        m16568d();
    }

    /* renamed from: d */
    private void m16568d() {
        if (this.f14721d && !this.f14719b && !this.f14720c) {
            try {
                this.f14722e.autoFocus(this.f14726j);
                this.f14720c = true;
            } catch (Throwable e) {
                Log.w(f14717a, "Unexpected exception while focusing", e);
                m16565c();
            }
        }
    }

    /* renamed from: e */
    private void m16569e() {
        this.f14723f.removeMessages(this.f14724g);
    }

    /* renamed from: b */
    public void m16571b() {
        this.f14719b = true;
        this.f14720c = false;
        m16569e();
        if (this.f14721d) {
            try {
                this.f14722e.cancelAutoFocus();
            } catch (Throwable e) {
                Log.w(f14717a, "Unexpected exception while cancelling focusing", e);
            }
        }
    }
}
