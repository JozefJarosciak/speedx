package com.digits.sdk.android;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4579d;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.SSLSocketFactory;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.android.MainThreadExecutor;

class DigitsApiClient {
    /* renamed from: a */
    private final ConcurrentHashMap<Class, Object> f13100a;
    /* renamed from: b */
    private final RestAdapter f13101b;
    /* renamed from: c */
    private final C1469k f13102c;

    DigitsApiClient(C1469k c1469k) {
        this(c1469k, C4655n.a().b(), C4655n.a().e(), aa.a().k());
    }

    DigitsApiClient(C1469k c1469k, TwitterAuthConfig twitterAuthConfig, SSLSocketFactory sSLSocketFactory, ExecutorService executorService) {
        this(c1469k, twitterAuthConfig, sSLSocketFactory, executorService, new at());
    }

    DigitsApiClient(C1469k c1469k, TwitterAuthConfig twitterAuthConfig, SSLSocketFactory sSLSocketFactory, ExecutorService executorService, at atVar) {
        this.f13102c = c1469k;
        this.f13100a = new ConcurrentHashMap();
        this.f13101b = new Builder().setEndpoint(new ad().a()).setRequestInterceptor(new al(atVar)).setExecutors(executorService, new MainThreadExecutor()).setClient(new C4579d(twitterAuthConfig, c1469k, sSLSocketFactory)).build();
    }

    /* renamed from: a */
    public C1469k m13867a() {
        return this.f13102c;
    }

    /* renamed from: b */
    public DigitsApiClient$SdkService m13868b() {
        return (DigitsApiClient$SdkService) m13866a(DigitsApiClient$SdkService.class);
    }

    /* renamed from: c */
    public DigitsApiClient$DeviceService m13869c() {
        return (DigitsApiClient$DeviceService) m13866a(DigitsApiClient$DeviceService.class);
    }

    /* renamed from: d */
    public DigitsApiClient$AccountService m13870d() {
        return (DigitsApiClient$AccountService) m13866a(DigitsApiClient$AccountService.class);
    }

    /* renamed from: a */
    private <T> T m13866a(Class<T> cls) {
        if (!this.f13100a.containsKey(cls)) {
            this.f13100a.put(cls, this.f13101b.create(cls));
        }
        return this.f13100a.get(cls);
    }
}
