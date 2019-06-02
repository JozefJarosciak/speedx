package com.twitter.sdk.android.tweetui.internal;

import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.TwitterAuthException;
import com.twitter.sdk.android.core.internal.C4609d;
import java.util.List;

/* compiled from: UserSessionProvider */
/* renamed from: com.twitter.sdk.android.tweetui.internal.e */
public class C4726e extends C4609d {
    public C4726e(List<C4586l<? extends C1469k>> list) {
        super(list);
    }

    /* renamed from: a */
    public void mo6175a(C4580e<C1469k> c4580e) {
        c4580e.mo6127a(new TwitterAuthException("Twitter login required."));
    }
}
