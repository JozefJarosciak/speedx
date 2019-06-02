package okio;

import com.alipay.sdk.data.C0847a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: Okio */
/* renamed from: okio.m */
public final class C5647m {
    /* renamed from: a */
    static final Logger f18235a = Logger.getLogger(C5647m.class.getName());

    private C5647m() {
    }

    /* renamed from: a */
    public static C5636e m20714a(C5520s c5520s) {
        if (c5520s != null) {
            return new C5650o(c5520s);
        }
        throw new IllegalArgumentException("source == null");
    }

    /* renamed from: a */
    public static C5635d m20713a(C5492r c5492r) {
        if (c5492r != null) {
            return new C5648n(c5492r);
        }
        throw new IllegalArgumentException("sink == null");
    }

    /* renamed from: a */
    private static C5492r m20715a(final OutputStream outputStream, final C5522t c5522t) {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        } else if (c5522t != null) {
            return new C5492r() {
                /* renamed from: a */
                public void mo6706a(C5637c c5637c, long j) throws IOException {
                    C5654u.m20769a(c5637c.f18210b, 0, j);
                    while (j > 0) {
                        c5522t.mo6841g();
                        C5651p c5651p = c5637c.f18209a;
                        int min = (int) Math.min(j, (long) (c5651p.f18245c - c5651p.f18244b));
                        outputStream.write(c5651p.f18243a, c5651p.f18244b, min);
                        c5651p.f18244b += min;
                        j -= (long) min;
                        c5637c.f18210b -= (long) min;
                        if (c5651p.f18244b == c5651p.f18245c) {
                            c5637c.f18209a = c5651p.m20757a();
                            C5652q.m20763a(c5651p);
                        }
                    }
                }

                public void flush() throws IOException {
                    outputStream.flush();
                }

                public void close() throws IOException {
                    outputStream.close();
                }

                public C5522t timeout() {
                    return c5522t;
                }

                public String toString() {
                    return "sink(" + outputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    /* renamed from: a */
    public static C5492r m20716a(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        C5522t c = C5647m.m20722c(socket);
        return c.m19981a(C5647m.m20715a(socket.getOutputStream(), c));
    }

    /* renamed from: a */
    public static C5520s m20718a(InputStream inputStream) {
        return C5647m.m20719a(inputStream, new C5522t());
    }

    /* renamed from: a */
    private static C5520s m20719a(final InputStream inputStream, final C5522t c5522t) {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        } else if (c5522t != null) {
            return new C5520s() {
                public long read(C5637c c5637c, long j) throws IOException {
                    if (j < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    } else if (j == 0) {
                        return 0;
                    } else {
                        try {
                            c5522t.mo6841g();
                            C5651p f = c5637c.m20659f(1);
                            int read = inputStream.read(f.f18243a, f.f18245c, (int) Math.min(j, (long) (8192 - f.f18245c)));
                            if (read == -1) {
                                return -1;
                            }
                            f.f18245c += read;
                            c5637c.f18210b += (long) read;
                            return (long) read;
                        } catch (AssertionError e) {
                            if (C5647m.m20720a(e)) {
                                throw new IOException(e);
                            }
                            throw e;
                        }
                    }
                }

                public void close() throws IOException {
                    inputStream.close();
                }

                public C5522t timeout() {
                    return c5522t;
                }

                public String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        } else {
            throw new IllegalArgumentException("timeout == null");
        }
    }

    /* renamed from: a */
    public static C5520s m20717a(File file) throws FileNotFoundException {
        if (file != null) {
            return C5647m.m20718a(new FileInputStream(file));
        }
        throw new IllegalArgumentException("file == null");
    }

    /* renamed from: b */
    public static C5520s m20721b(Socket socket) throws IOException {
        if (socket == null) {
            throw new IllegalArgumentException("socket == null");
        }
        C5522t c = C5647m.m20722c(socket);
        return c.m19982a(C5647m.m20719a(socket.getInputStream(), c));
    }

    /* renamed from: c */
    private static C5523a m20722c(final Socket socket) {
        return new C5523a() {
            /* renamed from: a */
            protected IOException mo6724a(IOException iOException) {
                IOException socketTimeoutException = new SocketTimeoutException(C0847a.f2089f);
                if (iOException != null) {
                    socketTimeoutException.initCause(iOException);
                }
                return socketTimeoutException;
            }

            /* renamed from: a */
            protected void mo6725a() {
                try {
                    socket.close();
                } catch (Throwable e) {
                    C5647m.f18235a.log(Level.WARNING, "Failed to close timed out socket " + socket, e);
                } catch (AssertionError e2) {
                    if (C5647m.m20720a(e2)) {
                        C5647m.f18235a.log(Level.WARNING, "Failed to close timed out socket " + socket, e2);
                        return;
                    }
                    throw e2;
                }
            }
        };
    }

    /* renamed from: a */
    static boolean m20720a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
