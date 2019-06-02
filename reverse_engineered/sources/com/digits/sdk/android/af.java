package com.digits.sdk.android;

import android.content.Context;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.TwitterException;
import io.fabric.sdk.android.C1520c;
import java.lang.ref.WeakReference;

/* compiled from: DigitsCallback */
public abstract class af<T> extends C4580e<T> {
    /* renamed from: a */
    private final WeakReference<Context> f13141a;
    /* renamed from: c */
    final ah f13142c;

    af(Context context, ah ahVar) {
        this.f13141a = new WeakReference(context);
        this.f13142c = ahVar;
    }

    /* renamed from: a */
    public void m13922a(TwitterException twitterException) {
        DigitsException a = DigitsException.m13853a(this.f13142c.mo3641c(), twitterException);
        C1520c.h().d("Digits", "HTTP Error: " + twitterException.getMessage() + ", API Error: " + "" + a.getErrorCode() + ", User Message: " + a.getMessage());
        this.f13142c.mo3638a((Context) this.f13141a.get(), a);
    }
}
