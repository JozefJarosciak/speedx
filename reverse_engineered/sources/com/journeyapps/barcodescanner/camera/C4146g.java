package com.journeyapps.barcodescanner.camera;

import android.graphics.Rect;
import com.journeyapps.barcodescanner.C4168l;
import java.util.List;

/* compiled from: DisplayConfiguration */
/* renamed from: com.journeyapps.barcodescanner.camera.g */
public class C4146g {
    /* renamed from: a */
    private static final String f14774a = C4146g.class.getSimpleName();
    /* renamed from: b */
    private C4168l f14775b;
    /* renamed from: c */
    private int f14776c;
    /* renamed from: d */
    private boolean f14777d = false;
    /* renamed from: e */
    private C4144k f14778e = new C4147h();

    public C4146g(int i, C4168l c4168l) {
        this.f14776c = i;
        this.f14775b = c4168l;
    }

    /* renamed from: a */
    public int m16630a() {
        return this.f14776c;
    }

    /* renamed from: a */
    public void m16634a(C4144k c4144k) {
        this.f14778e = c4144k;
    }

    /* renamed from: a */
    public C4168l m16633a(boolean z) {
        if (this.f14775b == null) {
            return null;
        }
        if (z) {
            return this.f14775b.m16693a();
        }
        return this.f14775b;
    }

    /* renamed from: a */
    public C4168l m16632a(List<C4168l> list, boolean z) {
        return this.f14778e.m16625a((List) list, m16633a(z));
    }

    /* renamed from: a */
    public Rect m16631a(C4168l c4168l) {
        return this.f14778e.mo5931b(c4168l, this.f14775b);
    }
}
