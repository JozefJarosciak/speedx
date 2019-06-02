package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import com.journeyapps.barcodescanner.C4168l;

/* compiled from: FitXYStrategy */
/* renamed from: com.journeyapps.barcodescanner.camera.i */
public class C4148i extends C4144k {
    /* renamed from: a */
    private static final String f14780a = C4148i.class.getSimpleName();

    /* renamed from: a */
    private static float m16637a(float f) {
        if (f < 1.0f) {
            return 1.0f / f;
        }
        return f;
    }

    /* renamed from: a */
    protected float mo5930a(C4168l c4168l, C4168l c4168l2) {
        if (c4168l.f14828a <= 0 || c4168l.f14829b <= 0) {
            return 0.0f;
        }
        float a = (1.0f / C4148i.m16637a((((float) c4168l.f14828a) * 1.0f) / ((float) c4168l2.f14828a))) / C4148i.m16637a((((float) c4168l.f14829b) * 1.0f) / ((float) c4168l2.f14829b));
        float a2 = C4148i.m16637a(((((float) c4168l.f14828a) * 1.0f) / ((float) c4168l.f14829b)) / ((((float) c4168l2.f14828a) * 1.0f) / ((float) c4168l2.f14829b)));
        return a * (((1.0f / a2) / a2) / a2);
    }

    /* renamed from: b */
    public Rect mo5931b(C4168l c4168l, C4168l c4168l2) {
        return new Rect(0, 0, c4168l2.f14828a, c4168l2.f14829b);
    }
}
