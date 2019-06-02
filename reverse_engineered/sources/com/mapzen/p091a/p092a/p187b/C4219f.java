package com.mapzen.p091a.p092a.p187b;

import android.content.Context;
import com.mapzen.p091a.p092a.p093a.C4210e;
import com.mapzen.p091a.p092a.p093a.C4212f;

/* compiled from: LostApiClientImpl */
/* renamed from: com.mapzen.a.a.b.f */
public class C4219f implements C4212f {
    /* renamed from: a */
    private final Context f14864a;

    public C4219f(Context context) {
        this.f14864a = context;
    }

    /* renamed from: a */
    public void mo5991a() {
        if (C4210e.f14846a == null) {
            C4210e.f14846a = new C4215b(this.f14864a);
        }
        if (C4210e.f14847b == null) {
            C4210e.f14847b = new C4218d();
        }
    }

    /* renamed from: b */
    public void mo5992b() {
        if (C4210e.f14846a != null && (C4210e.f14846a instanceof C4215b)) {
            ((C4215b) C4210e.f14846a).m16732b();
        }
        C4210e.f14846a = null;
        C4210e.f14847b = null;
    }

    /* renamed from: c */
    public boolean mo5993c() {
        return (C4210e.f14846a == null || C4210e.f14847b == null) ? false : true;
    }
}
