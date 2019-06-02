package okhttp3.internal.framed;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import okio.ByteString;
import okio.C5636e;

/* compiled from: FrameReader */
/* renamed from: okhttp3.internal.framed.a */
public interface C5501a extends Closeable {

    /* compiled from: FrameReader */
    /* renamed from: okhttp3.internal.framed.a$a */
    public interface C5500a {
        /* renamed from: a */
        void mo6711a();

        /* renamed from: a */
        void mo6712a(int i, int i2, int i3, boolean z);

        /* renamed from: a */
        void mo6713a(int i, int i2, List<C5526e> list) throws IOException;

        /* renamed from: a */
        void mo6714a(int i, long j);

        /* renamed from: a */
        void mo6715a(int i, ErrorCode errorCode);

        /* renamed from: a */
        void mo6716a(int i, ErrorCode errorCode, ByteString byteString);

        /* renamed from: a */
        void mo6717a(boolean z, int i, int i2);

        /* renamed from: a */
        void mo6718a(boolean z, int i, C5636e c5636e, int i2) throws IOException;

        /* renamed from: a */
        void mo6719a(boolean z, C5545l c5545l);

        /* renamed from: a */
        void mo6720a(boolean z, boolean z2, int i, int i2, List<C5526e> list, HeadersMode headersMode);
    }

    /* renamed from: a */
    void mo6726a() throws IOException;

    /* renamed from: a */
    boolean mo6727a(C5500a c5500a) throws IOException;
}
