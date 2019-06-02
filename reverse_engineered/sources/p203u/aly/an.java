package p203u.aly;

import java.io.ByteArrayOutputStream;
import p203u.aly.ci.C5934a;

/* compiled from: TSerializer */
/* renamed from: u.aly.an */
public class an {
    /* renamed from: a */
    private final ByteArrayOutputStream f18588a;
    /* renamed from: b */
    private final bk f18589b;
    /* renamed from: c */
    private as f18590c;

    public an() {
        this(new C5934a());
    }

    public an(cq cqVar) {
        this.f18588a = new ByteArrayOutputStream();
        this.f18589b = new bk(this.f18588a);
        this.f18590c = cqVar.mo7222a(this.f18589b);
    }

    /* renamed from: a */
    public byte[] m21192a(bp bpVar) throws bv {
        this.f18588a.reset();
        bpVar.mo7214b(this.f18590c);
        return this.f18588a.toByteArray();
    }
}
