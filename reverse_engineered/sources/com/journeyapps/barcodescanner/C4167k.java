package com.journeyapps.barcodescanner;

import android.content.Context;
import android.view.OrientationEventListener;
import android.view.WindowManager;

/* compiled from: RotationListener */
/* renamed from: com.journeyapps.barcodescanner.k */
public class C4167k {
    /* renamed from: a */
    private int f14824a;
    /* renamed from: b */
    private WindowManager f14825b;
    /* renamed from: c */
    private OrientationEventListener f14826c;
    /* renamed from: d */
    private C4126j f14827d;

    /* renamed from: a */
    public void m16692a(Context context, C4126j c4126j) {
        m16691a();
        Context applicationContext = context.getApplicationContext();
        this.f14827d = c4126j;
        this.f14825b = (WindowManager) applicationContext.getSystemService("window");
        this.f14826c = new OrientationEventListener(this, applicationContext, 3) {
            /* renamed from: a */
            final /* synthetic */ C4167k f14823a;

            public void onOrientationChanged(int i) {
                WindowManager a = this.f14823a.f14825b;
                C4126j b = this.f14823a.f14827d;
                if (this.f14823a.f14825b != null && b != null) {
                    int rotation = a.getDefaultDisplay().getRotation();
                    if (rotation != this.f14823a.f14824a) {
                        this.f14823a.f14824a = rotation;
                        b.mo5929a(rotation);
                    }
                }
            }
        };
        this.f14826c.enable();
        this.f14824a = this.f14825b.getDefaultDisplay().getRotation();
    }

    /* renamed from: a */
    public void m16691a() {
        if (this.f14826c != null) {
            this.f14826c.disable();
        }
        this.f14826c = null;
        this.f14825b = null;
        this.f14827d = null;
    }
}
