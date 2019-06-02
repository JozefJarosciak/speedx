package com.twitter.sdk.android.core.identity;

import com.twitter.sdk.android.core.C1514q;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4589m;
import com.twitter.sdk.android.core.models.User;

class ShareEmailClient extends C4589m {
    ShareEmailClient(C1514q c1514q) {
        super(c1514q);
    }

    /* renamed from: a */
    protected void m18185a(C4580e<User> c4580e) {
        ((ShareEmailClient$EmailService) m18181a(ShareEmailClient$EmailService.class)).verifyCredentials(Boolean.valueOf(true), Boolean.valueOf(true), c4580e);
    }
}
