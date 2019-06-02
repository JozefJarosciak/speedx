package com.twitter.sdk.android.core.identity;

import android.os.Bundle;
import android.os.ResultReceiver;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.TwitterException;

class ShareEmailResultReceiver extends ResultReceiver {
    /* renamed from: a */
    private final C4580e<String> f16231a;

    public void onReceiveResult(int i, Bundle bundle) {
        switch (i) {
            case -1:
                this.f16231a.mo6128a(new C4645j(bundle.getString("email"), null));
                return;
            case 0:
                this.f16231a.mo6127a(new TwitterException(bundle.getString("msg")));
                return;
            case 1:
                this.f16231a.mo6127a((TwitterException) bundle.getSerializable("error"));
                return;
            default:
                throw new IllegalArgumentException("Invalid result code " + i);
        }
    }
}
