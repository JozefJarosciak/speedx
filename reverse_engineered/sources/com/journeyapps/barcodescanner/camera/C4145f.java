package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import android.util.Log;
import com.journeyapps.barcodescanner.C4168l;

/* compiled from: CenterCropStrategy */
/* renamed from: com.journeyapps.barcodescanner.camera.f */
public class C4145f extends C4144k {
    /* renamed from: a */
    private static final String f14773a = C4145f.class.getSimpleName();

    /* renamed from: a */
    protected float mo5930a(C4168l c4168l, C4168l c4168l2) {
        if (c4168l.f14828a <= 0 || c4168l.f14829b <= 0) {
            return 0.0f;
        }
        C4168l b = c4168l.m16695b(c4168l2);
        float f = (((float) b.f14828a) * 1.0f) / ((float) c4168l.f14828a);
        if (f > 1.0f) {
            f = (float) Math.pow((double) (1.0f / f), 1.1d);
        }
        float f2 = ((((float) b.f14829b) * 1.0f) / ((float) c4168l2.f14829b)) + ((((float) b.f14828a) * 1.0f) / ((float) c4168l2.f14828a));
        return f * ((1.0f / f2) / f2);
    }

    /* renamed from: b */
    public Rect mo5931b(C4168l c4168l, C4168l c4168l2) {
        C4168l b = c4168l.m16695b(c4168l2);
        Log.i(f14773a, "Preview: " + c4168l + "; Scaled: " + b + "; Want: " + c4168l2);
        int i = (b.f14828a - c4168l2.f14828a) / 2;
        int i2 = (b.f14829b - c4168l2.f14829b) / 2;
        return new Rect(-i, -i2, b.f14828a - i, b.f14829b - i2);
    }
}
