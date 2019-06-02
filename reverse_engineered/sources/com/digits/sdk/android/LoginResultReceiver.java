package com.digits.sdk.android;

import android.os.Bundle;
import android.os.ResultReceiver;
import com.twitter.sdk.android.core.C4586l;
import org.apache.http.HttpStatus;

class LoginResultReceiver extends ResultReceiver {
    /* renamed from: a */
    final ca f13114a;
    /* renamed from: b */
    final C4586l<ap> f13115b;

    public void onReceiveResult(int i, Bundle bundle) {
        if (this.f13114a == null) {
            return;
        }
        if (i == 200) {
            this.f13114a.mo3684a((ap) this.f13115b.b(), bundle.getString("phone_number"));
        } else if (i == HttpStatus.SC_BAD_REQUEST) {
            this.f13114a.mo3683a(new DigitsException(bundle.getString("login_error")));
        }
    }
}
