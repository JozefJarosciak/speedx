package com.twitter.sdk.android.tweetcomposer;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import com.twitter.sdk.android.core.C1514q;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.C1512c;
import com.twitter.sdk.android.core.models.C1513g;
import com.twitter.sdk.android.tweetcomposer.internal.C1515a;
import io.fabric.sdk.android.C1520c;
import java.io.File;
import retrofit.mime.TypedFile;

public class TweetUploadService extends IntentService {
    /* renamed from: a */
    C4671a f16448a;
    /* renamed from: b */
    C1514q f16449b;
    /* renamed from: c */
    String f16450c;
    /* renamed from: d */
    Card f16451d;
    /* renamed from: e */
    Intent f16452e;

    /* renamed from: com.twitter.sdk.android.tweetcomposer.TweetUploadService$1 */
    class C46671 extends C4580e<C1513g> {
        /* renamed from: a */
        final /* synthetic */ TweetUploadService f16441a;

        C46671(TweetUploadService tweetUploadService) {
            this.f16441a = tweetUploadService;
        }

        /* renamed from: a */
        public void mo6128a(C4645j<C1513g> c4645j) {
            this.f16441a.m18426a(((C1513g) c4645j.f16364a).a());
            this.f16441a.stopSelf();
        }

        /* renamed from: a */
        public void mo6127a(TwitterException twitterException) {
            this.f16441a.m18428a(twitterException);
        }
    }

    /* renamed from: com.twitter.sdk.android.tweetcomposer.TweetUploadService$a */
    static class C4671a {
        C4671a() {
        }

        /* renamed from: a */
        C4675d m18424a(C1514q c1514q) {
            return C1517m.b().a(c1514q);
        }

        /* renamed from: a */
        String m18425a() {
            return C1517m.b().f();
        }
    }

    public TweetUploadService() {
        this(new C4671a());
    }

    TweetUploadService(C4671a c4671a) {
        super("TweetUploadService");
        this.f16448a = c4671a;
    }

    protected void onHandleIntent(Intent intent) {
        TwitterAuthToken twitterAuthToken = (TwitterAuthToken) intent.getParcelableExtra("EXTRA_USER_TOKEN");
        this.f16452e = intent;
        this.f16449b = new C1514q(twitterAuthToken, -1, "");
        this.f16450c = intent.getStringExtra("EXTRA_TWEET_TEXT");
        this.f16451d = (Card) intent.getSerializableExtra("EXTRA_TWEET_CARD");
        if (Card.m18408a(this.f16451d)) {
            m18430a(this.f16449b, this.f16450c, this.f16451d);
        } else {
            m18429a(this.f16449b, this.f16450c);
        }
    }

    /* renamed from: a */
    void m18429a(C1514q c1514q, String str) {
        this.f16448a.m18424a(c1514q).m18437d().update(str, null, new C46671(this));
    }

    /* renamed from: a */
    void m18430a(C1514q c1514q, final String str, final Card card) {
        final C4675d a = this.f16448a.m18424a(c1514q);
        String a2 = C4683h.m18461a(this, Uri.parse(card.f16419b));
        if (a2 == null) {
            m18428a(new TwitterException("Uri file path resolved to null"));
            return;
        }
        File file = new File(a2);
        a.m18184c().upload(new TypedFile(C4683h.m18463a(file), file), null, null, new C4580e<C1512c>(this) {
            /* renamed from: d */
            final /* synthetic */ TweetUploadService f16447d;

            /* renamed from: com.twitter.sdk.android.tweetcomposer.TweetUploadService$2$1 */
            class C46691 extends C4580e<C1515a> {
                /* renamed from: a */
                final /* synthetic */ C46702 f16443a;

                /* renamed from: com.twitter.sdk.android.tweetcomposer.TweetUploadService$2$1$1 */
                class C46681 extends C4580e<C1513g> {
                    /* renamed from: a */
                    final /* synthetic */ C46691 f16442a;

                    C46681(C46691 c46691) {
                        this.f16442a = c46691;
                    }

                    /* renamed from: a */
                    public void mo6128a(C4645j<C1513g> c4645j) {
                        this.f16442a.f16443a.f16447d.m18426a(((C1513g) c4645j.f16364a).a());
                        this.f16442a.f16443a.f16447d.stopSelf();
                    }

                    /* renamed from: a */
                    public void mo6127a(TwitterException twitterException) {
                        this.f16442a.f16443a.f16447d.m18428a(twitterException);
                    }
                }

                C46691(C46702 c46702) {
                    this.f16443a = c46702;
                }

                /* renamed from: a */
                public void mo6128a(C4645j<C1515a> c4645j) {
                    a.m18437d().update(str, ((C1515a) c4645j.f16364a).f7121a, new C46681(this));
                }

                /* renamed from: a */
                public void mo6127a(TwitterException twitterException) {
                    this.f16443a.f16447d.m18428a(twitterException);
                }
            }

            /* renamed from: a */
            public void mo6128a(C4645j<C1512c> c4645j) {
                a.m18438e().create(C4673b.m18434a(card, Long.valueOf(((C1512c) c4645j.f16364a).f7090a), this.f16447d.f16448a.m18425a()), new C46691(this));
            }

            /* renamed from: a */
            public void mo6127a(TwitterException twitterException) {
                this.f16447d.m18428a(twitterException);
            }
        });
    }

    /* renamed from: a */
    void m18428a(TwitterException twitterException) {
        m18427a(this.f16452e);
        C1520c.h().mo6222d("TweetUploadService", "Post Tweet failed", twitterException);
        stopSelf();
    }

    /* renamed from: a */
    void m18426a(long j) {
        Intent intent = new Intent("com.twitter.sdk.android.tweetcomposer.UPLOAD_SUCCESS");
        intent.putExtra("EXTRA_TWEET_ID", j);
        sendBroadcast(intent);
    }

    /* renamed from: a */
    void m18427a(Intent intent) {
        Intent intent2 = new Intent("com.twitter.sdk.android.tweetcomposer.UPLOAD_FAILURE");
        intent2.putExtra("EXTRA_RETRY_INTENT", intent);
        sendBroadcast(intent2);
    }
}
