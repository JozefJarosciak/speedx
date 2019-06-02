package okhttp3.internal.framed;

import java.io.Closeable;
import java.io.IOException;
import java.util.List;
import okio.C5637c;

/* compiled from: FrameWriter */
/* renamed from: okhttp3.internal.framed.b */
public interface C5502b extends Closeable {
    /* renamed from: a */
    void mo6728a() throws IOException;

    /* renamed from: a */
    void mo6729a(int i, int i2, List<C5526e> list) throws IOException;

    /* renamed from: a */
    void mo6730a(int i, long j) throws IOException;

    /* renamed from: a */
    void mo6731a(int i, ErrorCode errorCode) throws IOException;

    /* renamed from: a */
    void mo6732a(int i, ErrorCode errorCode, byte[] bArr) throws IOException;

    /* renamed from: a */
    void mo6733a(C5545l c5545l) throws IOException;

    /* renamed from: a */
    void mo6734a(boolean z, int i, int i2) throws IOException;

    /* renamed from: a */
    void mo6735a(boolean z, int i, C5637c c5637c, int i2) throws IOException;

    /* renamed from: a */
    void mo6736a(boolean z, boolean z2, int i, int i2, List<C5526e> list) throws IOException;

    /* renamed from: b */
    void mo6737b() throws IOException;

    /* renamed from: b */
    void mo6738b(C5545l c5545l) throws IOException;

    /* renamed from: c */
    int mo6739c();
}
