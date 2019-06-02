package com.twitter.sdk.android.tweetui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.models.C1513g;

public class TweetActionBarView extends LinearLayout {
    /* renamed from: a */
    final C4690a f16499a;
    /* renamed from: b */
    ToggleImageButton f16500b;
    /* renamed from: c */
    ImageButton f16501c;
    /* renamed from: d */
    C4580e<C1513g> f16502d;

    /* renamed from: com.twitter.sdk.android.tweetui.TweetActionBarView$a */
    static class C4690a {
        C4690a() {
        }

        /* renamed from: a */
        C1518i m18491a() {
            return C1518i.a();
        }
    }

    public TweetActionBarView(Context context) {
        this(context, null, new C4690a());
    }

    public TweetActionBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, new C4690a());
    }

    TweetActionBarView(Context context, AttributeSet attributeSet, C4690a c4690a) {
        super(context, attributeSet);
        this.f16499a = c4690a;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        m18492a();
    }

    void setOnActionCallback(C4580e<C1513g> c4580e) {
        this.f16502d = c4580e;
    }

    /* renamed from: a */
    void m18492a() {
        this.f16500b = (ToggleImageButton) findViewById(C4689R.id.tw__tweet_like_button);
        this.f16501c = (ImageButton) findViewById(C4689R.id.tw__tweet_share_button);
    }

    void setTweet(C1513g c1513g) {
        setLike(c1513g);
        setShare(c1513g);
    }

    void setLike(C1513g c1513g) {
        C1518i a = this.f16499a.m18491a();
        if (c1513g != null) {
            this.f16500b.setToggledOn(c1513g.f7100g);
            this.f16500b.setOnClickListener(new C4693b(c1513g, a, this.f16502d));
        }
    }

    void setShare(C1513g c1513g) {
        C1518i a = this.f16499a.m18491a();
        if (c1513g != null) {
            this.f16501c.setOnClickListener(new C4699e(c1513g, a));
        }
    }
}
