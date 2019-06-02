package com.twitter.sdk.android;

import com.digits.sdk.android.aa;
import com.twitter.sdk.android.core.C4655n;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.tweetcomposer.C1517m;
import com.twitter.sdk.android.tweetui.C1518i;
import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.C1498i;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/* compiled from: Twitter */
/* renamed from: com.twitter.sdk.android.a */
public class C1499a extends C1468h implements C1498i {
    /* renamed from: a */
    public final C4655n f7043a;
    /* renamed from: b */
    public final C1518i f7044b = new C1518i();
    /* renamed from: c */
    public final C1517m f7045c = new C1517m();
    /* renamed from: d */
    public final aa f7046d = new aa();
    /* renamed from: e */
    public final Collection<? extends C1468h> f7047e = Collections.unmodifiableCollection(Arrays.asList(new C1468h[]{this.f7043a, this.f7044b, this.f7045c, this.f7046d}));

    public C1499a(TwitterAuthConfig twitterAuthConfig) {
        this.f7043a = new C4655n(twitterAuthConfig);
    }

    /* renamed from: c */
    public String mo2810c() {
        return "1.13.1.111";
    }

    /* renamed from: g */
    public String mo2812g() {
        return "com.twitter.sdk.android:twitter";
    }

    /* renamed from: a */
    public Collection<? extends C1468h> mo2981a() {
        return this.f7047e;
    }

    /* renamed from: n */
    protected Object mo2813n() {
        return null;
    }
}
