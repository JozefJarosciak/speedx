package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import android.util.Log;
import com.journeyapps.barcodescanner.C4168l;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* compiled from: PreviewScalingStrategy */
/* renamed from: com.journeyapps.barcodescanner.camera.k */
public abstract class C4144k {
    /* renamed from: a */
    private static final String f14772a = C4144k.class.getSimpleName();

    /* renamed from: b */
    public abstract Rect mo5931b(C4168l c4168l, C4168l c4168l2);

    /* renamed from: a */
    public C4168l m16625a(List<C4168l> list, C4168l c4168l) {
        List b = m16627b((List) list, c4168l);
        Log.i(f14772a, "Viewfinder size: " + c4168l);
        Log.i(f14772a, "Preview in order of preference: " + b);
        return (C4168l) b.get(0);
    }

    /* renamed from: b */
    public List<C4168l> m16627b(List<C4168l> list, final C4168l c4168l) {
        if (c4168l != null) {
            Collections.sort(list, new Comparator<C4168l>(this) {
                /* renamed from: b */
                final /* synthetic */ C4144k f14782b;

                public /* synthetic */ int compare(Object obj, Object obj2) {
                    return m16641a((C4168l) obj, (C4168l) obj2);
                }

                /* renamed from: a */
                public int m16641a(C4168l c4168l, C4168l c4168l2) {
                    return Float.compare(this.f14782b.mo5930a(c4168l2, c4168l), this.f14782b.mo5930a(c4168l, c4168l));
                }
            });
        }
        return list;
    }

    /* renamed from: a */
    protected float mo5930a(C4168l c4168l, C4168l c4168l2) {
        return 0.5f;
    }
}
