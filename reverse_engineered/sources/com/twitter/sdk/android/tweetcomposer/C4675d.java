package com.twitter.sdk.android.tweetcomposer;

import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C1514q;
import com.twitter.sdk.android.core.C4579d;
import com.twitter.sdk.android.core.C4589m;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.models.C4649e;
import com.twitter.sdk.android.core.models.C4651f;
import com.twitter.sdk.android.tweetcomposer.internal.CardService;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.SSLSocketFactory;
import retrofit.RestAdapter;
import retrofit.RestAdapter.Builder;
import retrofit.android.MainThreadExecutor;
import retrofit.converter.GsonConverter;

/* compiled from: ComposerApiClient */
/* renamed from: com.twitter.sdk.android.tweetcomposer.d */
class C4675d extends C4589m {
    /* renamed from: d */
    final RestAdapter f16458d;

    C4675d(TwitterAuthConfig twitterAuthConfig, C1469k c1469k, SSLSocketFactory sSLSocketFactory, ExecutorService executorService) {
        super(c1469k);
        this.f16458d = new Builder().setClient(new C4579d(twitterAuthConfig, c1469k, sSLSocketFactory)).setEndpoint("https://caps.twitter.com").setConverter(new GsonConverter(new GsonBuilder().registerTypeAdapterFactory(new C4649e()).registerTypeAdapterFactory(new C4651f()).create())).setExecutors(executorService, new MainThreadExecutor()).build();
    }

    C4675d(C1514q c1514q) {
        this(C4655n.m18381a().m18387b(), c1514q, C4655n.m18381a().m18390e(), C4655n.m18381a().r().e());
    }

    /* renamed from: d */
    StatusesService m18437d() {
        return (StatusesService) m18181a(StatusesService.class);
    }

    /* renamed from: e */
    CardService m18438e() {
        return (CardService) m18182a(this.f16458d, CardService.class);
    }
}
