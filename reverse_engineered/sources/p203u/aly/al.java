package p203u.aly;

import p203u.aly.ci.C5934a;

/* compiled from: TDeserializer */
/* renamed from: u.aly.al */
public class al {
    /* renamed from: a */
    private final as f18586a;
    /* renamed from: b */
    private final bl f18587b;

    public al() {
        this(new C5934a());
    }

    public al(cq cqVar) {
        this.f18587b = new bl();
        this.f18586a = cqVar.mo7222a(this.f18587b);
    }

    /* renamed from: a */
    public void m21191a(bp bpVar, byte[] bArr) throws bv {
        try {
            this.f18587b.m21714a(bArr);
            bpVar.mo7211a(this.f18586a);
        } finally {
            this.f18587b.m21712a();
            this.f18586a.mo7204x();
        }
    }
}
