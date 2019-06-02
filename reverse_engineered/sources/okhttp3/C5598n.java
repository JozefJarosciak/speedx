package okhttp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* compiled from: Dns */
/* renamed from: okhttp3.n */
public interface C5598n {
    /* renamed from: c */
    public static final C5598n f18047c = new C55991();

    /* compiled from: Dns */
    /* renamed from: okhttp3.n$1 */
    static class C55991 implements C5598n {
        C55991() {
        }

        /* renamed from: a */
        public List<InetAddress> mo6765a(String str) throws UnknownHostException {
            if (str != null) {
                return Arrays.asList(InetAddress.getAllByName(str));
            }
            throw new UnknownHostException("hostname == null");
        }
    }

    /* renamed from: a */
    List<InetAddress> mo6765a(String str) throws UnknownHostException;
}
