package okhttp3.internal;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;
import okhttp3.C5590j;

/* compiled from: ConnectionSpecSelector */
/* renamed from: okhttp3.internal.b */
public final class C5491b {
    /* renamed from: a */
    private final List<C5590j> f17671a;
    /* renamed from: b */
    private int f17672b = 0;
    /* renamed from: c */
    private boolean f17673c;
    /* renamed from: d */
    private boolean f17674d;

    public C5491b(List<C5590j> list) {
        this.f17671a = list;
    }

    /* renamed from: a */
    public C5590j m19809a(SSLSocket sSLSocket) throws IOException {
        C5590j c5590j;
        int i = this.f17672b;
        int size = this.f17671a.size();
        for (int i2 = i; i2 < size; i2++) {
            c5590j = (C5590j) this.f17671a.get(i2);
            if (c5590j.m20362a(sSLSocket)) {
                this.f17672b = i2 + 1;
                break;
            }
        }
        c5590j = null;
        if (c5590j == null) {
            throw new UnknownServiceException("Unable to find acceptable protocols. isFallback=" + this.f17674d + ", modes=" + this.f17671a + ", supported protocols=" + Arrays.toString(sSLSocket.getEnabledProtocols()));
        }
        this.f17673c = m19808b(sSLSocket);
        C5497d.f17702a.mo6772a(c5590j, sSLSocket, this.f17674d);
        return c5590j;
    }

    /* renamed from: a */
    public boolean m19810a(IOException iOException) {
        this.f17674d = true;
        if (!this.f17673c || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException)) {
            return false;
        }
        if (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        }
        if ((iOException instanceof SSLHandshakeException) || (iOException instanceof SSLProtocolException)) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private boolean m19808b(SSLSocket sSLSocket) {
        for (int i = this.f17672b; i < this.f17671a.size(); i++) {
            if (((C5590j) this.f17671a.get(i)).m20362a(sSLSocket)) {
                return true;
            }
        }
        return false;
    }
}
