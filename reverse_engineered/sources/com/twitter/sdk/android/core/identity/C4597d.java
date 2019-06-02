package com.twitter.sdk.android.core.identity;

import android.os.Bundle;
import android.os.ResultReceiver;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;
import io.fabric.sdk.android.C1520c;

/* compiled from: ShareEmailController */
/* renamed from: com.twitter.sdk.android.core.identity.d */
class C4597d {
    /* renamed from: a */
    private final ShareEmailClient f16243a;
    /* renamed from: b */
    private final ResultReceiver f16244b;

    /* compiled from: ShareEmailController */
    /* renamed from: com.twitter.sdk.android.core.identity.d$1 */
    class C45961 extends C4580e<User> {
        /* renamed from: a */
        final /* synthetic */ C4597d f16242a;

        C45961(C4597d c4597d) {
            this.f16242a = c4597d;
        }

        /* renamed from: a */
        public void mo6128a(C4645j<User> c4645j) {
            this.f16242a.m18212a((User) c4645j.f16364a);
        }

        /* renamed from: a */
        public void mo6127a(TwitterException twitterException) {
            C1520c.h().mo6222d("Twitter", "Failed to get email address.", twitterException);
            this.f16242a.m18211a(new TwitterException("Failed to get email address."));
        }
    }

    public C4597d(ShareEmailClient shareEmailClient, ResultReceiver resultReceiver) {
        this.f16243a = shareEmailClient;
        this.f16244b = resultReceiver;
    }

    /* renamed from: a */
    public void m18210a() {
        this.f16243a.m18185a(m18214b());
    }

    /* renamed from: b */
    C4580e<User> m18214b() {
        return new C45961(this);
    }

    /* renamed from: a */
    void m18212a(User user) {
        if (user.email == null) {
            m18211a(new TwitterException("Your application may not have access to email addresses or the user may not have an email address. To request access, please visit https://support.twitter.com/forms/platform."));
        } else if ("".equals(user.email)) {
            m18211a(new TwitterException("This user does not have an email address."));
        } else {
            m18213a(user.email);
        }
    }

    /* renamed from: a */
    void m18213a(String str) {
        Bundle bundle = new Bundle();
        bundle.putString("email", str);
        this.f16244b.send(-1, bundle);
    }

    /* renamed from: a */
    void m18211a(TwitterException twitterException) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("error", twitterException);
        this.f16244b.send(1, bundle);
    }

    /* renamed from: c */
    public void m18215c() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("msg", "The user chose not to share their email address at this time.");
        this.f16244b.send(0, bundle);
    }
}
