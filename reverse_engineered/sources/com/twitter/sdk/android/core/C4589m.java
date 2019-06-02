package com.twitter.sdk.android.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.internal.C4611f;
import com.twitter.sdk.android.core.models.C4649e;
import com.twitter.sdk.android.core.models.C4651f;
import com.twitter.sdk.android.core.services.AccountService;
import com.twitter.sdk.android.core.services.FavoriteService;
import com.twitter.sdk.android.core.services.MediaService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.SSLSocketFactory;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.android.MainThreadExecutor;
import retrofit.converter.GsonConverter;

/* compiled from: TwitterApiClient */
/* renamed from: com.twitter.sdk.android.core.m */
public class C4589m {
    /* renamed from: a */
    final ConcurrentHashMap<Class, Object> f16228a;
    /* renamed from: b */
    final RestAdapter f16229b;
    /* renamed from: c */
    final RestAdapter f16230c;

    C4589m(TwitterAuthConfig twitterAuthConfig, C1469k c1469k, C4611f c4611f, SSLSocketFactory sSLSocketFactory, ExecutorService executorService) {
        if (c1469k == null) {
            throw new IllegalArgumentException("Session must not be null.");
        }
        this.f16228a = new ConcurrentHashMap();
        Gson create = new GsonBuilder().registerTypeAdapterFactory(new C4649e()).registerTypeAdapterFactory(new C4651f()).create();
        this.f16229b = new Builder().setClient(new C4579d(twitterAuthConfig, c1469k, sSLSocketFactory)).setEndpoint(c4611f.m18249a()).setConverter(new GsonConverter(create)).setExecutors(executorService, new MainThreadExecutor()).build();
        this.f16230c = new Builder().setClient(new C4579d(twitterAuthConfig, c1469k, sSLSocketFactory)).setEndpoint("https://upload.twitter.com").setConverter(new GsonConverter(create)).setExecutors(executorService, new MainThreadExecutor()).build();
    }

    public C4589m(C1469k c1469k) {
        this(C4655n.m18381a().m18387b(), c1469k, new C4611f(), C4655n.m18381a().m18390e(), C4655n.m18381a().r().e());
    }

    /* renamed from: a */
    public AccountService m18180a() {
        return (AccountService) m18181a(AccountService.class);
    }

    /* renamed from: b */
    public FavoriteService m18183b() {
        return (FavoriteService) m18181a(FavoriteService.class);
    }

    /* renamed from: c */
    public MediaService m18184c() {
        return (MediaService) m18182a(this.f16230c, MediaService.class);
    }

    /* renamed from: a */
    protected <T> T m18181a(Class<T> cls) {
        return m18182a(this.f16229b, cls);
    }

    /* renamed from: a */
    protected <T> T m18182a(RestAdapter restAdapter, Class<T> cls) {
        if (!this.f16228a.contains(cls)) {
            this.f16228a.putIfAbsent(cls, restAdapter.create(cls));
        }
        return this.f16228a.get(cls);
    }
}
