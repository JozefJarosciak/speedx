package com.qiniu.android.http;

import ch.qos.logback.core.joran.action.Action;
import com.qiniu.android.dns.C4345a;
import com.qiniu.android.dns.C4346b;
import com.qiniu.android.http.CancellationHandler.CancellationException;
import com.qiniu.android.p189c.C4334a;
import com.qiniu.android.p189c.C4337c;
import com.qiniu.android.p189c.C4337c.C4336a;
import com.qiniu.android.p189c.C4338d;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.C5468e;
import okhttp3.C5469f;
import okhttp3.C5596r;
import okhttp3.C5596r.C5572a;
import okhttp3.C5598n;
import okhttp3.C5602x;
import okhttp3.C5608s;
import okhttp3.C5611t.C5609a;
import okhttp3.C5614u;
import okhttp3.C5614u.C5613a;
import okhttp3.C5621w;
import okhttp3.C5621w.C5620a;
import okhttp3.C5627y;
import okhttp3.HttpUrl;
import org.json.JSONObject;

/* compiled from: Client */
/* renamed from: com.qiniu.android.http.a */
public final class C4366a {
    /* renamed from: a */
    private final C4373h f15155a;
    /* renamed from: b */
    private C5614u f15156b;

    /* compiled from: Client */
    /* renamed from: com.qiniu.android.http.a$2 */
    class C43582 implements C5596r {
        /* renamed from: a */
        final /* synthetic */ C4366a f15137a;

        C43582(C4366a c4366a) {
            this.f15137a = c4366a;
        }

        public C5627y intercept(C5572a c5572a) throws IOException {
            C5621w a = c5572a.a();
            C5627y a2 = c5572a.a(a);
            C4365a c4365a = (C4365a) a.e();
            String str = "";
            try {
                str = c5572a.b().b().getRemoteSocketAddress().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            c4365a.f15154a = str;
            return a2;
        }
    }

    /* compiled from: Client */
    /* renamed from: com.qiniu.android.http.a$a */
    private static class C4365a {
        /* renamed from: a */
        public String f15154a;

        private C4365a() {
            this.f15154a = null;
        }
    }

    public C4366a() {
        this(null, 10, 30, null, null);
    }

    public C4366a(C4371f c4371f, int i, int i2, C4373h c4373h, final C4345a c4345a) {
        this.f15155a = c4373h;
        C5613a c5613a = new C5613a();
        if (c4371f != null) {
            c5613a.a(c4371f.m17193a());
        }
        if (c4345a != null) {
            c5613a.a(new C5598n(this) {
                /* renamed from: b */
                final /* synthetic */ C4366a f15136b;

                /* renamed from: a */
                public List<InetAddress> m17176a(String str) throws UnknownHostException {
                    try {
                        InetAddress[] b = c4345a.m17148b(new C4346b(str));
                        if (b == null) {
                            throw new UnknownHostException(str + " resolve failed");
                        }
                        Object arrayList = new ArrayList();
                        Collections.addAll(arrayList, b);
                        return arrayList;
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new UnknownHostException(e.getMessage());
                    }
                }
            });
        }
        c5613a.a().add(new C43582(this));
        c5613a.a((long) i, TimeUnit.SECONDS);
        c5613a.b((long) i2, TimeUnit.SECONDS);
        c5613a.c(0, TimeUnit.SECONDS);
        this.f15156b = c5613a.b();
    }

    /* renamed from: a */
    private static String m17180a(C5627y c5627y) {
        String a = c5627y.a("X-Via", "");
        if (a.equals("")) {
            a = c5627y.a("X-Px", "");
            if (a.equals("")) {
                a = c5627y.a("Fw-Via", "");
                if (!a.equals("")) {
                }
            }
        }
        return a;
    }

    /* renamed from: b */
    private static String m17185b(C5627y c5627y) {
        C5608s contentType = c5627y.g().contentType();
        if (contentType == null) {
            return "";
        }
        return contentType.a() + "/" + contentType.b();
    }

    /* renamed from: a */
    private static JSONObject m17181a(byte[] bArr) throws Exception {
        String str = new String(bArr, "utf-8");
        if (C4338d.m17127a(str)) {
            return new JSONObject();
        }
        return new JSONObject(str);
    }

    /* renamed from: a */
    public void m17188a(final C5620a c5620a, C4337c c4337c, final C4316b c4316b) {
        if (c4337c != null) {
            c4337c.m17124a(new C4336a(this) {
                /* renamed from: b */
                final /* synthetic */ C4366a f15139b;

                /* renamed from: a */
                public void mo6028a(String str, Object obj) {
                    c5620a.a(str, obj.toString());
                }
            });
        }
        final C4316b c43614 = new C4316b(this) {
            /* renamed from: b */
            final /* synthetic */ C4366a f15144b;

            /* renamed from: a */
            public void mo6024a(final C4372g c4372g, final JSONObject jSONObject) {
                C4334a.m17117a(new Runnable(this) {
                    /* renamed from: c */
                    final /* synthetic */ C43614 f15142c;

                    public void run() {
                        c4316b.mo6024a(c4372g, jSONObject);
                    }
                });
            }
        };
        c5620a.a("User-Agent", C4374i.m17208a().toString());
        final long currentTimeMillis = System.currentTimeMillis();
        this.f15156b.a(c5620a.a(new C4365a()).a()).a(new C5469f(this) {
            /* renamed from: c */
            final /* synthetic */ C4366a f15147c;

            public void onFailure(C5468e c5468e, IOException iOException) {
                iOException.printStackTrace();
                long currentTimeMillis = (System.currentTimeMillis() - currentTimeMillis) / 1000;
                int i = -1;
                String message = iOException.getMessage();
                if (iOException instanceof CancellationException) {
                    i = -2;
                } else if (iOException instanceof UnknownHostException) {
                    i = -1003;
                } else if (message != null && message.indexOf("Broken pipe") == 0) {
                    i = -1005;
                } else if (iOException instanceof SocketTimeoutException) {
                    i = -1001;
                } else if (iOException instanceof ConnectException) {
                    i = -1004;
                }
                HttpUrl a = c5468e.a().a();
                c43614.mo6024a(new C4372g(null, i, "", "", "", a.f(), a.h(), "", a.g(), (double) currentTimeMillis, 0, iOException.getMessage()), null);
            }

            public void onResponse(C5468e c5468e, C5627y c5627y) throws IOException {
                C5627y c5627y2 = c5627y;
                this.f15147c.m17184a(c5627y2, ((C4365a) c5627y.a().e()).f15154a, (System.currentTimeMillis() - currentTimeMillis) / 1000, c43614);
            }
        });
    }

    /* renamed from: a */
    public void m17187a(String str, byte[] bArr, int i, int i2, C4337c c4337c, C4314e c4314e, C4316b c4316b, CancellationHandler cancellationHandler) {
        C5602x create;
        C5602x c4369c;
        if (this.f15155a != null) {
            str = this.f15155a.m17207a(str);
        }
        if (bArr == null || bArr.length <= 0) {
            create = C5602x.create(null, new byte[0]);
        } else {
            create = C5602x.create(C5608s.a("application/octet-stream"), bArr, i, i2);
        }
        if (c4314e != null) {
            c4369c = new C4369c(create, c4314e, cancellationHandler);
        } else {
            c4369c = create;
        }
        m17188a(new C5620a().a(str).a(c4369c), c4337c, c4316b);
    }

    /* renamed from: a */
    public void m17186a(String str, C4370d c4370d, C4314e c4314e, C4316b c4316b, CancellationHandler cancellationHandler) {
        C5602x create;
        if (c4370d.f15164b != null) {
            create = C5602x.create(C5608s.a(c4370d.f15167e), c4370d.f15164b);
        } else {
            create = C5602x.create(C5608s.a(c4370d.f15167e), c4370d.f15163a);
        }
        m17183a(str, c4370d.f15165c, c4314e, c4370d.f15166d, create, c4316b, cancellationHandler);
    }

    /* renamed from: a */
    private void m17183a(String str, C4337c c4337c, C4314e c4314e, String str2, C5602x c5602x, C4316b c4316b, CancellationHandler cancellationHandler) {
        C5602x c4369c;
        if (this.f15155a != null) {
            str = this.f15155a.m17207a(str);
        }
        final C5609a c5609a = new C5609a();
        c5609a.a(Action.FILE_ATTRIBUTE, str2, c5602x);
        c4337c.m17124a(new C4336a(this) {
            /* renamed from: b */
            final /* synthetic */ C4366a f15149b;

            /* renamed from: a */
            public void mo6028a(String str, Object obj) {
                c5609a.a(str, obj.toString());
            }
        });
        c5609a.a(C5608s.a("multipart/form-data"));
        C5602x a = c5609a.a();
        if (c4314e != null) {
            c4369c = new C4369c(a, c4314e, cancellationHandler);
        } else {
            c4369c = a;
        }
        m17188a(new C5620a().a(str).a(c4369c), null, c4316b);
    }

    /* renamed from: a */
    private void m17184a(C5627y c5627y, String str, long j, C4316b c4316b) {
        JSONObject jSONObject;
        String str2;
        Exception exception;
        Exception exception2;
        HttpUrl a;
        final C4372g c4372g;
        final C4316b c4316b2;
        int b = c5627y.b();
        String a2 = c5627y.a("X-Reqid");
        String trim = a2 == null ? null : a2.trim();
        byte[] bArr = null;
        a2 = null;
        try {
            bArr = c5627y.g().bytes();
        } catch (IOException e) {
            a2 = e.getMessage();
        }
        JSONObject jSONObject2 = null;
        if (!C4366a.m17185b(c5627y).equals("application/json") || bArr == null) {
            a2 = new String(bArr);
            jSONObject = jSONObject2;
            str2 = a2;
        } else {
            try {
                jSONObject2 = C4366a.m17181a(bArr);
                try {
                    if (c5627y.b() != 200) {
                        a2 = jSONObject2.optString("error", new String(bArr, "utf-8"));
                    }
                    jSONObject = jSONObject2;
                    str2 = a2;
                } catch (Exception e2) {
                    exception = e2;
                    jSONObject = jSONObject2;
                    exception2 = exception;
                    if (c5627y.b() < 300) {
                        a2 = exception2.getMessage();
                    }
                    str2 = a2;
                    a = c5627y.a().a();
                    c4372g = new C4372g(jSONObject, b, trim, c5627y.a("X-Log"), C4366a.m17180a(c5627y), a.f(), a.h(), str, a.g(), (double) j, 0, str2);
                    c4316b2 = c4316b;
                    C4334a.m17117a(new Runnable(this) {
                        /* renamed from: d */
                        final /* synthetic */ C4366a f15153d;

                        public void run() {
                            c4316b2.mo6024a(c4372g, jSONObject);
                        }
                    });
                }
            } catch (Exception e22) {
                exception = e22;
                jSONObject = jSONObject2;
                exception2 = exception;
                if (c5627y.b() < 300) {
                    a2 = exception2.getMessage();
                }
                str2 = a2;
                a = c5627y.a().a();
                c4372g = new C4372g(jSONObject, b, trim, c5627y.a("X-Log"), C4366a.m17180a(c5627y), a.f(), a.h(), str, a.g(), (double) j, 0, str2);
                c4316b2 = c4316b;
                C4334a.m17117a(/* anonymous class already generated */);
            }
        }
        a = c5627y.a().a();
        c4372g = new C4372g(jSONObject, b, trim, c5627y.a("X-Log"), C4366a.m17180a(c5627y), a.f(), a.h(), str, a.g(), (double) j, 0, str2);
        c4316b2 = c4316b;
        C4334a.m17117a(/* anonymous class already generated */);
    }
}
