package okhttp3.internal.framed;

import java.io.IOException;
import java.util.List;
import okio.C5636e;

/* compiled from: PushObserver */
/* renamed from: okhttp3.internal.framed.k */
public interface C5543k {
    /* renamed from: a */
    public static final C5543k f17862a = new C55441();

    /* compiled from: PushObserver */
    /* renamed from: okhttp3.internal.framed.k$1 */
    static class C55441 implements C5543k {
        C55441() {
        }

        /* renamed from: a */
        public boolean mo6743a(int i, List<C5526e> list) {
            return true;
        }

        /* renamed from: a */
        public boolean mo6744a(int i, List<C5526e> list, boolean z) {
            return true;
        }

        /* renamed from: a */
        public boolean mo6745a(int i, C5636e c5636e, int i2, boolean z) throws IOException {
            c5636e.mo6822h((long) i2);
            return true;
        }

        /* renamed from: a */
        public void mo6742a(int i, ErrorCode errorCode) {
        }
    }

    /* renamed from: a */
    void mo6742a(int i, ErrorCode errorCode);

    /* renamed from: a */
    boolean mo6743a(int i, List<C5526e> list);

    /* renamed from: a */
    boolean mo6744a(int i, List<C5526e> list, boolean z);

    /* renamed from: a */
    boolean mo6745a(int i, C5636e c5636e, int i2, boolean z) throws IOException;
}
