package okhttp3.internal;

import android.util.Log;
import com.baidu.mapapi.UIMsg.m_AppUI;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;
import javax.net.ssl.SSLSocket;
import okhttp3.Protocol;

/* compiled from: AndroidPlatform */
/* renamed from: okhttp3.internal.a */
class C5482a extends C5481j {
    /* renamed from: a */
    private final Class<?> f17652a;
    /* renamed from: b */
    private final C5583i<Socket> f17653b;
    /* renamed from: c */
    private final C5583i<Socket> f17654c;
    /* renamed from: d */
    private final C5583i<Socket> f17655d;
    /* renamed from: e */
    private final C5583i<Socket> f17656e;

    public C5482a(Class<?> cls, C5583i<Socket> c5583i, C5583i<Socket> c5583i2, C5583i<Socket> c5583i3, C5583i<Socket> c5583i4) {
        this.f17652a = cls;
        this.f17653b = c5583i;
        this.f17654c = c5583i2;
        this.f17655d = c5583i3;
        this.f17656e = c5583i4;
    }

    /* renamed from: a */
    public void mo6701a(Socket socket, InetSocketAddress inetSocketAddress, int i) throws IOException {
        try {
            socket.connect(inetSocketAddress, i);
        } catch (AssertionError e) {
            if (C5586l.m20330a(e)) {
                throw new IOException(e);
            }
            throw e;
        } catch (Throwable e2) {
            IOException iOException = new IOException("Exception in connect");
            iOException.initCause(e2);
            throw iOException;
        }
    }

    /* renamed from: a */
    public void mo6702a(SSLSocket sSLSocket, String str, List<Protocol> list) {
        if (str != null) {
            this.f17653b.m20309b(sSLSocket, Boolean.valueOf(true));
            this.f17654c.m20309b(sSLSocket, str);
        }
        if (this.f17656e != null && this.f17656e.m20308a((Object) sSLSocket)) {
            this.f17656e.m20311d(sSLSocket, C5481j.m19769b((List) list));
        }
    }

    /* renamed from: a */
    public String mo6699a(SSLSocket sSLSocket) {
        if (this.f17655d == null || !this.f17655d.m20308a((Object) sSLSocket)) {
            return null;
        }
        byte[] bArr = (byte[]) this.f17655d.m20311d(sSLSocket, new Object[0]);
        return bArr != null ? new String(bArr, C5586l.f18008c) : null;
    }

    /* renamed from: a */
    public void mo6700a(int i, String str, Throwable th) {
        int i2 = i == 5 ? 5 : 3;
        if (th != null) {
            str = str + '\n' + Log.getStackTraceString(th);
        }
        int i3 = 0;
        int length = str.length();
        while (i3 < length) {
            int min;
            int indexOf = str.indexOf(10, i3);
            if (indexOf == -1) {
                indexOf = length;
            }
            while (true) {
                min = Math.min(indexOf, i3 + m_AppUI.MSG_APP_SAVESCREEN);
                Log.println(i2, "OkHttp", str.substring(i3, min));
                if (min >= indexOf) {
                    break;
                }
                i3 = min;
            }
            i3 = min + 1;
        }
    }

    /* renamed from: a */
    public boolean mo6703a() {
        try {
            Class cls = Class.forName("android.security.NetworkSecurityPolicy");
            return ((Boolean) cls.getMethod("isCleartextTrafficPermitted", new Class[0]).invoke(cls.getMethod("getInstance", new Class[0]).invoke(null, new Object[0]), new Object[0])).booleanValue();
        } catch (ClassNotFoundException e) {
            return super.mo6703a();
        } catch (NoSuchMethodException e2) {
            throw new AssertionError();
        } catch (IllegalAccessException e3) {
            throw new AssertionError();
        } catch (IllegalArgumentException e4) {
            throw new AssertionError();
        } catch (InvocationTargetException e5) {
            throw new AssertionError();
        }
    }

    /* renamed from: b */
    public static C5481j m19778b() {
        Class cls;
        C5583i c5583i;
        C5583i c5583i2;
        C5583i c5583i3;
        try {
            cls = Class.forName("com.android.org.conscrypt.SSLParametersImpl");
        } catch (ClassNotFoundException e) {
            cls = Class.forName("org.apache.harmony.xnet.provider.jsse.SSLParametersImpl");
        }
        try {
            C5583i c5583i4 = new C5583i(null, "setUseSessionTickets", Boolean.TYPE);
            C5583i c5583i5 = new C5583i(null, "setHostname", String.class);
            try {
                Class.forName("android.net.Network");
                c5583i = new C5583i(byte[].class, "getAlpnSelectedProtocol", new Class[0]);
                try {
                    c5583i2 = new C5583i(null, "setAlpnProtocols", byte[].class);
                    c5583i3 = c5583i;
                } catch (ClassNotFoundException e2) {
                    c5583i2 = null;
                    c5583i3 = c5583i;
                    return new C5482a(cls, c5583i4, c5583i5, c5583i3, c5583i2);
                }
            } catch (ClassNotFoundException e3) {
                c5583i = null;
                c5583i2 = null;
                c5583i3 = c5583i;
                return new C5482a(cls, c5583i4, c5583i5, c5583i3, c5583i2);
            }
            return new C5482a(cls, c5583i4, c5583i5, c5583i3, c5583i2);
        } catch (ClassNotFoundException e4) {
            return null;
        }
    }
}
