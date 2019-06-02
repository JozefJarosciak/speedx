package com.beastbikes.framework.persistence;

/* compiled from: AbstractUpgradeHandler */
/* renamed from: com.beastbikes.framework.persistence.a */
public abstract class C1566a implements C1565d {
    /* renamed from: a */
    private final C1377c f7309a;
    /* renamed from: b */
    private final int f7310b;

    public /* synthetic */ int compareTo(Object obj) {
        return m8538a((C1565d) obj);
    }

    public C1566a(C1377c c1377c, int i) {
        this.f7309a = c1377c;
        this.f7310b = i;
    }

    /* renamed from: a */
    public final int mo3125a() {
        return this.f7310b;
    }

    /* renamed from: a */
    public int m8538a(C1565d c1565d) {
        return mo3125a() - c1565d.mo3125a();
    }
}
