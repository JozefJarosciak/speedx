package com.twitter.sdk.android.core;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/* compiled from: Callback */
/* renamed from: com.twitter.sdk.android.core.e */
public abstract class C4580e<T> implements Callback<T> {
    /* renamed from: a */
    public abstract void mo6127a(TwitterException twitterException);

    /* renamed from: a */
    public abstract void mo6128a(C4645j<T> c4645j);

    public final void success(T t, Response response) {
        mo6128a(new C4645j(t, response));
    }

    public final void failure(RetrofitError retrofitError) {
        mo6127a(TwitterApiException.convert(retrofitError));
    }
}
