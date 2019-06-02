package io.fabric.sdk.android.services.network;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/* compiled from: NetworkUtils */
/* renamed from: io.fabric.sdk.android.services.network.d */
public final class C4926d {
    /* renamed from: a */
    public static final SSLSocketFactory m19356a(C4656e c4656e) throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext instance = SSLContext.getInstance("TLS");
        C4927f c4927f = new C4927f(new C4928g(c4656e.mo6151a(), c4656e.mo6152b()), c4656e);
        instance.init(null, new TrustManager[]{c4927f}, null);
        return instance.getSocketFactory();
    }
}
