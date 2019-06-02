package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import android.util.Log;
import com.journeyapps.barcodescanner.C4168l;

/* compiled from: FitCenterStrategy */
/* renamed from: com.journeyapps.barcodescanner.camera.h */
public class C4147h extends C4144k {
    /* renamed from: a */
    private static final String f14779a = C4147h.class.getSimpleName();

    /* renamed from: a */
    protected float mo5930a(C4168l c4168l, C4168l c4168l2) {
        if (c4168l.f14828a <= 0 || c4168l.f14829b <= 0) {
            return 0.0f;
        }
        C4168l a = c4168l.m16694a(c4168l2);
        float f = (((float) a.f14828a) * 1.0f) / ((float) c4168l.f14828a);
        if (f > 1.0f) {
            f = (float) Math.pow((double) (1.0f / f), 1.1d);
        }
        float f2 = ((((float) c4168l2.f14829b) * 1.0f) / ((float) a.f14829b)) * ((((float) c4168l2.f14828a) * 1.0f) / ((float) a.f14828a));
        return f * (((1.0f / f2) / f2) / f2);
    }

    /* renamed from: b */
    public Rect mo5931b(C4168l c4168l, C4168l c4168l2) {
        C4168l a = c4168l.m16694a(c4168l2);
        Log.i(f14779a, "Preview: " + c4168l + "; Scaled: " + a + "; Want: " + c4168l2);
        int i = (a.f14828a - c4168l2.f14828a) / 2;
        int i2 = (a.f14829b - c4168l2.f14829b) / 2;
        return new Rect(-i, -i2, a.f14828a - i, a.f14829b - i2);
    }
}
