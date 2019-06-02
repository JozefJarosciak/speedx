package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import android.text.TextUtils;
import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4579d;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.services.common.C4877i;
import io.fabric.sdk.android.services.common.C4883l;
import io.fabric.sdk.android.services.common.C4889n;
import io.fabric.sdk.android.services.common.C4889n.C4624c;
import io.fabric.sdk.android.services.p202b.C4627k;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpStatus;
import retrofit.RequestInterceptor;
import retrofit.RequestInterceptor.RequestFacade;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.android.MainThreadExecutor;
import retrofit.client.Response;

class ScribeFilesSender implements C4627k {
    /* renamed from: a */
    private static final byte[] f16299a = new byte[]{(byte) 91};
    /* renamed from: b */
    private static final byte[] f16300b = new byte[]{(byte) 44};
    /* renamed from: c */
    private static final byte[] f16301c = new byte[]{(byte) 93};
    /* renamed from: d */
    private final Context f16302d;
    /* renamed from: e */
    private final C4635e f16303e;
    /* renamed from: f */
    private final long f16304f;
    /* renamed from: g */
    private final TwitterAuthConfig f16305g;
    /* renamed from: h */
    private final List<C4586l<? extends C1469k>> f16306h;
    /* renamed from: i */
    private final SSLSocketFactory f16307i;
    /* renamed from: j */
    private final AtomicReference<RestAdapter> f16308j = new AtomicReference();
    /* renamed from: k */
    private final ExecutorService f16309k;
    /* renamed from: l */
    private final C4883l f16310l;

    /* renamed from: com.twitter.sdk.android.core.internal.scribe.ScribeFilesSender$a */
    static class C4626a implements RequestInterceptor {
        /* renamed from: a */
        private final C4635e f16297a;
        /* renamed from: b */
        private final C4883l f16298b;

        C4626a(C4635e c4635e, C4883l c4883l) {
            this.f16297a = c4635e;
            this.f16298b = c4883l;
        }

        public void intercept(RequestFacade requestFacade) {
            if (!TextUtils.isEmpty(this.f16297a.f16341f)) {
                requestFacade.addHeader("User-Agent", this.f16297a.f16341f);
            }
            if (!TextUtils.isEmpty(this.f16298b.m19189f())) {
                requestFacade.addHeader("X-Client-UUID", this.f16298b.m19189f());
            }
            requestFacade.addHeader("X-Twitter-Polling", "true");
        }
    }

    public ScribeFilesSender(Context context, C4635e c4635e, long j, TwitterAuthConfig twitterAuthConfig, List<C4586l<? extends C1469k>> list, SSLSocketFactory sSLSocketFactory, ExecutorService executorService, C4883l c4883l) {
        this.f16302d = context;
        this.f16303e = c4635e;
        this.f16304f = j;
        this.f16305g = twitterAuthConfig;
        this.f16306h = list;
        this.f16307i = sSLSocketFactory;
        this.f16309k = executorService;
        this.f16310l = c4883l;
    }

    /* renamed from: a */
    public boolean mo6142a(List<File> list) {
        if (m18303c()) {
            try {
                String b = m18307b(list);
                C4877i.m19157a(this.f16302d, b);
                if (m18305a(b).getStatus() == 200) {
                    return true;
                }
                C4877i.m19158a(this.f16302d, "Failed sending files", null);
            } catch (Throwable e) {
                C4877i.m19158a(this.f16302d, "Failed sending files", e);
                if (e.getResponse() != null) {
                    if (e.getResponse().getStatus() == 500) {
                        return true;
                    }
                    if (e.getResponse().getStatus() == HttpStatus.SC_BAD_REQUEST) {
                        return true;
                    }
                }
            } catch (Throwable e2) {
                C4877i.m19158a(this.f16302d, "Failed sending files", e2);
            }
        } else {
            C4877i.m19157a(this.f16302d, "Cannot attempt upload at this time");
        }
        return false;
    }

    /* renamed from: b */
    String m18307b(List<File> list) throws IOException {
        Throwable th;
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        final boolean[] zArr = new boolean[1];
        byteArrayOutputStream.write(f16299a);
        for (File c4889n : list) {
            Closeable c4889n2;
            try {
                c4889n2 = new C4889n(c4889n);
                try {
                    c4889n2.m19218a(new C4624c(this) {
                        /* renamed from: c */
                        final /* synthetic */ ScribeFilesSender f16296c;

                        /* renamed from: a */
                        public void mo6141a(InputStream inputStream, int i) throws IOException {
                            byte[] bArr = new byte[i];
                            inputStream.read(bArr);
                            if (zArr[0]) {
                                byteArrayOutputStream.write(ScribeFilesSender.f16300b);
                            } else {
                                zArr[0] = true;
                            }
                            byteArrayOutputStream.write(bArr);
                        }
                    });
                    C4877i.m19159a(c4889n2);
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                c4889n2 = null;
            }
        }
        byteArrayOutputStream.write(f16301c);
        return byteArrayOutputStream.toString("UTF-8");
        C4877i.m19159a(c4889n2);
        throw th;
    }

    /* renamed from: c */
    private boolean m18303c() {
        return m18304a() != null;
    }

    /* renamed from: a */
    synchronized RestAdapter m18304a() {
        if (this.f16308j.get() == null) {
            C1469k a = m18300a(this.f16304f);
            RequestInterceptor c4626a = new C4626a(this.f16303e, this.f16310l);
            if (m18301a(a)) {
                this.f16308j.compareAndSet(null, new Builder().setEndpoint(this.f16303e.f16337b).setExecutors(this.f16309k, new MainThreadExecutor()).setRequestInterceptor(c4626a).setClient(new C4579d(this.f16305g, a, this.f16307i)).build());
            } else {
                C4877i.m19157a(this.f16302d, "No valid session at this time");
            }
        }
        return (RestAdapter) this.f16308j.get();
    }

    /* renamed from: a */
    private C1469k m18300a(long j) {
        C1469k c1469k = null;
        for (C4586l a : this.f16306h) {
            c1469k = a.mo6129a(j);
            if (c1469k != null) {
                break;
            }
        }
        return c1469k;
    }

    /* renamed from: a */
    private boolean m18301a(C1469k c1469k) {
        return (c1469k == null || c1469k.d() == null) ? false : true;
    }

    /* renamed from: a */
    Response m18305a(String str) {
        ScribeFilesSender$ScribeService scribeFilesSender$ScribeService = (ScribeFilesSender$ScribeService) ((RestAdapter) this.f16308j.get()).create(ScribeFilesSender$ScribeService.class);
        if (TextUtils.isEmpty(this.f16303e.f16340e)) {
            return scribeFilesSender$ScribeService.upload(this.f16303e.f16338c, this.f16303e.f16339d, str);
        }
        return scribeFilesSender$ScribeService.uploadSequence(this.f16303e.f16340e, str);
    }
}
