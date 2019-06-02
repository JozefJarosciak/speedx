package com.journeyapps.barcodescanner.camera;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build.VERSION;
import android.view.SurfaceHolder;
import java.io.IOException;

/* compiled from: CameraSurface */
/* renamed from: com.journeyapps.barcodescanner.camera.d */
public class C4142d {
    /* renamed from: a */
    private SurfaceHolder f14764a;
    /* renamed from: b */
    private SurfaceTexture f14765b;

    public C4142d(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalArgumentException("surfaceHolder may not be null");
        }
        this.f14764a = surfaceHolder;
    }

    public C4142d(SurfaceTexture surfaceTexture) {
        if (surfaceTexture == null) {
            throw new IllegalArgumentException("surfaceTexture may not be null");
        }
        this.f14765b = surfaceTexture;
    }

    /* renamed from: a */
    public void m16617a(Camera camera) throws IOException {
        if (this.f14764a != null) {
            camera.setPreviewDisplay(this.f14764a);
        } else if (VERSION.SDK_INT >= 11) {
            camera.setPreviewTexture(this.f14765b);
        } else {
            throw new IllegalStateException("SurfaceTexture not supported.");
        }
    }
}
