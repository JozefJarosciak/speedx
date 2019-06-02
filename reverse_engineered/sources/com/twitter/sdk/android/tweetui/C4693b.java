package com.twitter.sdk.android.tweetui;

import android.view.View;
import android.view.View.OnClickListener;
import ch.qos.logback.core.net.SyslogConstants;
import com.avos.avoscloud.AVException;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.TwitterApiException;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.C1513g;
import com.twitter.sdk.android.core.models.C4652h;

/* compiled from: LikeTweetAction */
/* renamed from: com.twitter.sdk.android.tweetui.b */
class C4693b extends C4691a implements OnClickListener {
    /* renamed from: b */
    final C1513g f16507b;
    /* renamed from: c */
    final C4702f f16508c;
    /* renamed from: d */
    final C1518i f16509d;
    /* renamed from: e */
    final C4703g f16510e;

    /* compiled from: LikeTweetAction */
    /* renamed from: com.twitter.sdk.android.tweetui.b$a */
    static class C4692a extends C4580e<C1513g> {
        /* renamed from: a */
        ToggleImageButton f16504a;
        /* renamed from: b */
        C1513g f16505b;
        /* renamed from: c */
        C4580e<C1513g> f16506c;

        C4692a(ToggleImageButton toggleImageButton, C1513g c1513g, C4580e<C1513g> c4580e) {
            this.f16504a = toggleImageButton;
            this.f16505b = c1513g;
            this.f16506c = c4580e;
        }

        /* renamed from: a */
        public void mo6128a(C4645j<C1513g> c4645j) {
            this.f16506c.mo6128a((C4645j) c4645j);
        }

        /* renamed from: a */
        public void mo6127a(TwitterException twitterException) {
            if (twitterException instanceof TwitterApiException) {
                switch (((TwitterApiException) twitterException).getErrorCode()) {
                    case AVException.INVALID_ROLE_NAME /*139*/:
                        this.f16506c.mo6128a(new C4645j(new C4652h().m18379a(this.f16505b).m18380a(true).m18378a(), null));
                        return;
                    case SyslogConstants.LOG_LOCAL2 /*144*/:
                        this.f16506c.mo6128a(new C4645j(new C4652h().m18379a(this.f16505b).m18380a(false).m18378a(), null));
                        return;
                    default:
                        this.f16504a.setToggledOn(this.f16505b.f7100g);
                        this.f16506c.mo6127a(twitterException);
                        return;
                }
            }
            this.f16504a.setToggledOn(this.f16505b.f7100g);
            this.f16506c.mo6127a(twitterException);
        }
    }

    C4693b(C1513g c1513g, C1518i c1518i, C4580e<C1513g> c4580e) {
        this(c1513g, c1518i, c4580e, new C4704h(c1518i));
    }

    C4693b(C1513g c1513g, C1518i c1518i, C4580e<C1513g> c4580e, C4703g c4703g) {
        super(c4580e);
        this.f16507b = c1513g;
        this.f16509d = c1518i;
        this.f16510e = c4703g;
        this.f16508c = c1518i.f();
    }

    public void onClick(View view) {
        if (view instanceof ToggleImageButton) {
            ToggleImageButton toggleImageButton = (ToggleImageButton) view;
            if (this.f16507b.f7100g) {
                m18497c();
                this.f16508c.m18514b(this.f16507b.f7102i, new C4692a(toggleImageButton, this.f16507b, m18493a()));
                return;
            }
            m18496b();
            this.f16508c.m18513a(this.f16507b.f7102i, new C4692a(toggleImageButton, this.f16507b, m18493a()));
        }
    }

    /* renamed from: b */
    void m18496b() {
        this.f16510e.mo6164b(this.f16507b);
    }

    /* renamed from: c */
    void m18497c() {
        this.f16510e.mo6165c(this.f16507b);
    }
}
