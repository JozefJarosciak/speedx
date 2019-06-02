package okhttp3.internal;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import okhttp3.C5614u;
import okhttp3.Protocol;
import okio.C5637c;

/* compiled from: Platform */
/* renamed from: okhttp3.internal.j */
public class C5481j {
    /* renamed from: a */
    private static final C5481j f17650a = C5481j.m19768b();
    /* renamed from: b */
    private static final Logger f17651b = Logger.getLogger(C5614u.class.getName());

    /* renamed from: c */
    public static C5481j m19770c() {
        return f17650a;
    }

    /* renamed from: d */
    public String m19777d() {
        return "OkHttp";
    }

    /* renamed from: a */
    public void mo6702a(SSLSocket sSLSocket, String str, List<Protocol> list) {
    }

    /* renamed from: b */
    public void mo6746b(SSLSocket sSLSocket) {
    }

    /* renamed from: a */
    public String mo6699a(SSLSocket sSLSocket) {
        return null;
    }

    /* renamed from: a */
    public void mo6701a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        socket.connect(inetSocketAddress, i);
    }

    /* renamed from: a */
    public void mo6700a(int i, String str, Throwable th) {
        f17651b.log(i == 5 ? Level.WARNING : Level.INFO, str, th);
    }

    /* renamed from: a */
    public boolean mo6703a() {
        return true;
    }

    /* renamed from: a */
    public static List<String> m19767a(List<Protocol> list) {
        List<String> arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = (Protocol) list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                arrayList.add(protocol.toString());
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    private static C5481j m19768b() {
        C5481j b = C5482a.m19778b();
        if (b != null) {
            return b;
        }
        b = C5499f.m19850b();
        if (b != null) {
            return b;
        }
        b = C5550g.m20157b();
        return b == null ? new C5481j() : b;
    }

    /* renamed from: b */
    static byte[] m19769b(List<Protocol> list) {
        C5637c c5637c = new C5637c();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Protocol protocol = (Protocol) list.get(i);
            if (protocol != Protocol.HTTP_1_0) {
                c5637c.m20641b(protocol.toString().length());
                c5637c.m20631a(protocol.toString());
            }
        }
        return c5637c.mo6835s();
    }
}
