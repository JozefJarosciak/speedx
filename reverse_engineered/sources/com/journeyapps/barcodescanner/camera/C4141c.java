package com.journeyapps.barcodescanner.camera;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.Log;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.zxing.client.android.AmbientLightManager;
import com.google.zxing.client.android.camera.CameraConfigurationUtils;
import com.google.zxing.client.android.camera.open.OpenCameraInterface;
import com.journeyapps.barcodescanner.C4168l;
import com.journeyapps.barcodescanner.C4169m;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* compiled from: CameraManager */
/* renamed from: com.journeyapps.barcodescanner.camera.c */
public final class C4141c {
    /* renamed from: a */
    private static final String f14750a = C4141c.class.getSimpleName();
    /* renamed from: b */
    private Camera f14751b;
    /* renamed from: c */
    private CameraInfo f14752c;
    /* renamed from: d */
    private C4132a f14753d;
    /* renamed from: e */
    private AmbientLightManager f14754e;
    /* renamed from: f */
    private boolean f14755f;
    /* renamed from: g */
    private String f14756g;
    /* renamed from: h */
    private CameraSettings f14757h = new CameraSettings();
    /* renamed from: i */
    private C4146g f14758i;
    /* renamed from: j */
    private C4168l f14759j;
    /* renamed from: k */
    private C4168l f14760k;
    /* renamed from: l */
    private int f14761l = -1;
    /* renamed from: m */
    private Context f14762m;
    /* renamed from: n */
    private final C4140a f14763n;

    /* compiled from: CameraManager */
    /* renamed from: com.journeyapps.barcodescanner.camera.c$a */
    private final class C4140a implements PreviewCallback {
        /* renamed from: a */
        final /* synthetic */ C4141c f14747a;
        /* renamed from: b */
        private C4149j f14748b;
        /* renamed from: c */
        private C4168l f14749c;

        public C4140a(C4141c c4141c) {
            this.f14747a = c4141c;
        }

        /* renamed from: a */
        public void m16595a(C4168l c4168l) {
            this.f14749c = c4168l;
        }

        /* renamed from: a */
        public void m16594a(C4149j c4149j) {
            this.f14748b = c4149j;
        }

        public void onPreviewFrame(byte[] bArr, Camera camera) {
            C4168l c4168l = this.f14749c;
            C4149j c4149j = this.f14748b;
            if (c4168l == null || c4149j == null) {
                Log.d(C4141c.f14750a, "Got preview callback, but no handler or resolution available");
                return;
            }
            int previewFormat = camera.getParameters().getPreviewFormat();
            c4149j.mo5933a(new C4169m(bArr, c4168l.f14828a, c4168l.f14829b, previewFormat, this.f14747a.m16614g()));
        }
    }

    public C4141c(Context context) {
        this.f14762m = context;
        this.f14763n = new C4140a(this);
    }

    /* renamed from: a */
    public void m16603a() {
        this.f14751b = OpenCameraInterface.open(this.f14757h.m16553a());
        if (this.f14751b == null) {
            throw new RuntimeException("Failed to open camera");
        }
        int cameraId = OpenCameraInterface.getCameraId(this.f14757h.m16553a());
        this.f14752c = new CameraInfo();
        Camera.getCameraInfo(cameraId, this.f14752c);
    }

    /* renamed from: b */
    public void m16609b() {
        if (this.f14751b == null) {
            throw new RuntimeException("Camera not open");
        }
        m16602m();
    }

    /* renamed from: a */
    public void m16605a(C4142d c4142d) throws IOException {
        c4142d.m16617a(this.f14751b);
    }

    /* renamed from: c */
    public void m16610c() {
        Camera camera = this.f14751b;
        if (camera != null && !this.f14755f) {
            camera.startPreview();
            this.f14755f = true;
            this.f14753d = new C4132a(this.f14751b, this.f14757h);
            this.f14754e = new AmbientLightManager(this.f14762m, this, this.f14757h);
            this.f14754e.start();
        }
    }

    /* renamed from: d */
    public void m16611d() {
        if (this.f14753d != null) {
            this.f14753d.m16571b();
            this.f14753d = null;
        }
        if (this.f14754e != null) {
            this.f14754e.stop();
            this.f14754e = null;
        }
        if (this.f14751b != null && this.f14755f) {
            this.f14751b.stopPreview();
            this.f14763n.m16594a(null);
            this.f14755f = false;
        }
    }

    /* renamed from: e */
    public void m16612e() {
        if (this.f14751b != null) {
            this.f14751b.release();
            this.f14751b = null;
        }
    }

    /* renamed from: f */
    public boolean m16613f() {
        if (this.f14761l != -1) {
            return this.f14761l % Opcodes.GETFIELD != 0;
        } else {
            throw new IllegalStateException("Rotation not calculated yet. Call configure() first.");
        }
    }

    /* renamed from: g */
    public int m16614g() {
        return this.f14761l;
    }

    /* renamed from: k */
    private Parameters m16600k() {
        Parameters parameters = this.f14751b.getParameters();
        if (this.f14756g == null) {
            this.f14756g = parameters.flatten();
        } else {
            parameters.unflatten(this.f14756g);
        }
        return parameters;
    }

    /* renamed from: b */
    private void m16598b(boolean z) {
        Parameters k = m16600k();
        if (k == null) {
            Log.w(f14750a, "Device error: no camera parameters are available. Proceeding without configuration.");
            return;
        }
        Log.i(f14750a, "Initial camera parameters: " + k.flatten());
        if (z) {
            Log.w(f14750a, "In camera config safe mode -- most settings will not be honored");
        }
        CameraConfigurationUtils.setFocus(k, this.f14757h.m16560g(), z);
        if (!z) {
            CameraConfigurationUtils.setTorch(k, false);
            if (this.f14757h.m16555b()) {
                CameraConfigurationUtils.setInvertColor(k);
            }
            if (this.f14757h.m16556c()) {
                CameraConfigurationUtils.setBarcodeSceneMode(k);
            }
            if (this.f14757h.m16558e() && VERSION.SDK_INT >= 15) {
                CameraConfigurationUtils.setVideoStabilization(k);
                CameraConfigurationUtils.setFocusArea(k);
                CameraConfigurationUtils.setMetering(k);
            }
        }
        List a = C4141c.m16596a(k);
        if (a.size() == 0) {
            this.f14759j = null;
        } else {
            this.f14759j = this.f14758i.m16632a(a, m16613f());
            k.setPreviewSize(this.f14759j.f14828a, this.f14759j.f14829b);
        }
        if (Build.DEVICE.equals("glass-1")) {
            CameraConfigurationUtils.setBestPreviewFPS(k);
        }
        Log.i(f14750a, "Final camera parameters: " + k.flatten());
        this.f14751b.setParameters(k);
    }

    /* renamed from: a */
    private static List<C4168l> m16596a(Parameters parameters) {
        List<Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List<C4168l> arrayList = new ArrayList();
        Size previewSize;
        if (supportedPreviewSizes == null) {
            previewSize = parameters.getPreviewSize();
            if (previewSize != null) {
                arrayList.add(new C4168l(previewSize.width, previewSize.height));
            }
            return arrayList;
        }
        for (Size previewSize2 : supportedPreviewSizes) {
            arrayList.add(new C4168l(previewSize2.width, previewSize2.height));
        }
        return arrayList;
    }

    /* renamed from: l */
    private int m16601l() {
        int i = 0;
        switch (this.f14758i.m16630a()) {
            case 1:
                i = 90;
                break;
            case 2:
                i = Opcodes.GETFIELD;
                break;
            case 3:
                i = 270;
                break;
        }
        if (this.f14752c.facing == 1) {
            i = (360 - ((i + this.f14752c.orientation) % 360)) % 360;
        } else {
            i = ((this.f14752c.orientation - i) + 360) % 360;
        }
        Log.i(f14750a, "Camera Display Orientation: " + i);
        return i;
    }

    /* renamed from: a */
    private void m16597a(int i) {
        this.f14751b.setDisplayOrientation(i);
    }

    /* renamed from: m */
    private void m16602m() {
        try {
            this.f14761l = m16601l();
            m16597a(this.f14761l);
        } catch (Exception e) {
            Log.w(f14750a, "Failed to set rotation.");
        }
        try {
            m16598b(false);
        } catch (Exception e2) {
            try {
                m16598b(true);
            } catch (Exception e3) {
                Log.w(f14750a, "Camera rejected even safe-mode parameters! No configuration");
            }
        }
        Size previewSize = this.f14751b.getParameters().getPreviewSize();
        if (previewSize == null) {
            this.f14760k = this.f14759j;
        } else {
            this.f14760k = new C4168l(previewSize.width, previewSize.height);
        }
        this.f14763n.m16595a(this.f14760k);
    }

    /* renamed from: h */
    public C4168l m16615h() {
        if (this.f14760k == null) {
            return null;
        }
        if (m16613f()) {
            return this.f14760k.m16693a();
        }
        return this.f14760k;
    }

    /* renamed from: a */
    public void m16607a(C4149j c4149j) {
        Camera camera = this.f14751b;
        if (camera != null && this.f14755f) {
            this.f14763n.m16594a(c4149j);
            camera.setOneShotPreviewCallback(this.f14763n);
        }
    }

    /* renamed from: a */
    public void m16604a(CameraSettings cameraSettings) {
        this.f14757h = cameraSettings;
    }

    /* renamed from: a */
    public void m16606a(C4146g c4146g) {
        this.f14758i = c4146g;
    }

    /* renamed from: a */
    public void m16608a(boolean z) {
        if (this.f14751b != null && z != m16616i()) {
            if (this.f14753d != null) {
                this.f14753d.m16571b();
            }
            Parameters parameters = this.f14751b.getParameters();
            CameraConfigurationUtils.setTorch(parameters, z);
            if (this.f14757h.m16557d()) {
                CameraConfigurationUtils.setBestExposure(parameters, z);
            }
            this.f14751b.setParameters(parameters);
            if (this.f14753d != null) {
                this.f14753d.m16570a();
            }
        }
    }

    /* renamed from: i */
    public boolean m16616i() {
        Parameters parameters = this.f14751b.getParameters();
        if (parameters == null) {
            return false;
        }
        String flashMode = parameters.getFlashMode();
        if (flashMode == null) {
            return false;
        }
        if ("on".equals(flashMode) || "torch".equals(flashMode)) {
            return true;
        }
        return false;
    }
}
