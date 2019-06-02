package com.twitter.sdk.android.tweetui;

import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.TwitterException;
import io.fabric.sdk.android.C4835k;

/* compiled from: LoggingCallback */
/* renamed from: com.twitter.sdk.android.tweetui.c */
abstract class C4694c<T> extends C4580e<T> {
    /* renamed from: a */
    private final C4580e f16511a;
    /* renamed from: b */
    private final C4835k f16512b;

    C4694c(C4580e c4580e, C4835k c4835k) {
        this.f16511a = c4580e;
        this.f16512b = c4835k;
    }

    /* renamed from: a */
    public void mo6127a(TwitterException twitterException) {
        this.f16512b.mo6222d("TweetUi", twitterException.getMessage(), twitterException);
        if (this.f16511a != null) {
            this.f16511a.mo6127a(twitterException);
        }
    }
}
