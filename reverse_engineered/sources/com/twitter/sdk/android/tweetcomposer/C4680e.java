package com.twitter.sdk.android.tweetcomposer;

import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import com.avos.avoscloud.AVException;
import com.twitter.C4571c;
import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C1514q;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4589m;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity.C4659a;

/* compiled from: ComposerController */
/* renamed from: com.twitter.sdk.android.tweetcomposer.e */
class C4680e {
    /* renamed from: a */
    ComposerView f16463a;
    /* renamed from: b */
    C1514q f16464b;
    /* renamed from: c */
    Card f16465c;
    /* renamed from: d */
    C4659a f16466d;
    /* renamed from: e */
    final C4679c f16467e;

    /* compiled from: ComposerController */
    /* renamed from: com.twitter.sdk.android.tweetcomposer.e$1 */
    class C46761 extends C4580e<User> {
        /* renamed from: a */
        final /* synthetic */ C4680e f16459a;

        C46761(C4680e c4680e) {
            this.f16459a = c4680e;
        }

        /* renamed from: a */
        public void mo6128a(C4645j<User> c4645j) {
            this.f16459a.f16463a.setProfilePhotoView((User) c4645j.f16364a);
        }

        /* renamed from: a */
        public void mo6127a(TwitterException twitterException) {
            this.f16459a.f16463a.setProfilePhotoView(null);
        }
    }

    /* compiled from: ComposerController */
    /* renamed from: com.twitter.sdk.android.tweetcomposer.e$a */
    public interface C4677a {
        /* renamed from: a */
        void mo6157a();

        /* renamed from: a */
        void mo6158a(String str);

        /* renamed from: b */
        void mo6159b(String str);
    }

    /* compiled from: ComposerController */
    /* renamed from: com.twitter.sdk.android.tweetcomposer.e$b */
    class C4678b implements C4677a {
        /* renamed from: a */
        final /* synthetic */ C4680e f16460a;

        C4678b(C4680e c4680e) {
            this.f16460a = c4680e;
        }

        /* renamed from: a */
        public void mo6158a(String str) {
            int a = this.f16460a.m18454a(str);
            this.f16460a.f16463a.setCharCount(C4680e.m18451a(a));
            if (C4680e.m18453c(a)) {
                this.f16460a.f16463a.setCharCountTextStyle(C4666R.style.tw__ComposerCharCountOverflow);
            } else {
                this.f16460a.f16463a.setCharCountTextStyle(C4666R.style.tw__ComposerCharCount);
            }
            this.f16460a.f16463a.m18414a(C4680e.m18452b(a));
        }

        /* renamed from: b */
        public void mo6159b(String str) {
            this.f16460a.f16467e.m18450c().mo6161a(this.f16460a.f16465c, "tweet");
            Intent intent = new Intent(this.f16460a.f16463a.getContext(), TweetUploadService.class);
            intent.putExtra("EXTRA_USER_TOKEN", (Parcelable) this.f16460a.f16464b.d());
            intent.putExtra("EXTRA_TWEET_TEXT", str);
            intent.putExtra("EXTRA_TWEET_CARD", this.f16460a.f16465c);
            this.f16460a.f16463a.getContext().startService(intent);
            this.f16460a.f16466d.mo6155a();
        }

        /* renamed from: a */
        public void mo6157a() {
            this.f16460a.f16467e.m18450c().mo6161a(this.f16460a.f16465c, "cancel");
            this.f16460a.f16466d.mo6155a();
        }
    }

    /* compiled from: ComposerController */
    /* renamed from: com.twitter.sdk.android.tweetcomposer.e$c */
    static class C4679c {
        /* renamed from: a */
        final C4674c f16461a = new C4674c();
        /* renamed from: b */
        final C4571c f16462b = new C4571c();

        C4679c() {
        }

        /* renamed from: a */
        C4589m m18447a(C1514q c1514q) {
            return C4655n.m18381a().m18385a((C1469k) c1514q);
        }

        /* renamed from: a */
        C4674c m18448a() {
            return this.f16461a;
        }

        /* renamed from: b */
        C4571c m18449b() {
            return this.f16462b;
        }

        /* renamed from: c */
        C4681f m18450c() {
            return new C4682g(C1517m.b().e());
        }
    }

    C4680e(ComposerView composerView, C1514q c1514q, Card card, C4659a c4659a) {
        this(composerView, c1514q, card, c4659a, new C4679c());
    }

    C4680e(ComposerView composerView, C1514q c1514q, Card card, C4659a c4659a, C4679c c4679c) {
        this.f16463a = composerView;
        this.f16464b = c1514q;
        this.f16465c = card;
        this.f16466d = c4659a;
        this.f16467e = c4679c;
        composerView.setCallbacks(new C4678b(this));
        composerView.setTweetText("");
        composerView.m18415b();
        m18455a();
        m18456a(card);
        c4679c.m18450c().mo6160a(card);
    }

    /* renamed from: a */
    void m18455a() {
        this.f16467e.m18447a(this.f16464b).m18180a().verifyCredentials(Boolean.valueOf(false), Boolean.valueOf(true), new C46761(this));
    }

    /* renamed from: a */
    void m18456a(Card card) {
        if (card != null) {
            this.f16463a.setCardView(this.f16467e.m18448a().m18436a(this.f16463a.getContext(), card));
        }
    }

    /* renamed from: a */
    int m18454a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return this.f16467e.m18449b().m18130a(str);
    }

    /* renamed from: a */
    static int m18451a(int i) {
        return 140 - i;
    }

    /* renamed from: b */
    static boolean m18452b(int i) {
        return i > 0 && i <= AVException.EXCEEDED_QUOTA;
    }

    /* renamed from: c */
    static boolean m18453c(int i) {
        return i > AVException.EXCEEDED_QUOTA;
    }
}
