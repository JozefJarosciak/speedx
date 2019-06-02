package com.digits.sdk.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.ResultReceiver;
import org.apache.http.HttpStatus;

/* compiled from: FailureControllerImpl */
class bc implements bb {
    /* renamed from: a */
    final C2877a f13224a;

    public bc() {
        this(aa.a().l());
    }

    public bc(C2877a c2877a) {
        this.f13224a = c2877a;
    }

    /* renamed from: a */
    public void mo3671a(Activity activity, ResultReceiver resultReceiver) {
        Intent intent = new Intent(activity, this.f13224a.mo3677a());
        Bundle bundle = new Bundle();
        bundle.putParcelable("receiver", resultReceiver);
        intent.putExtras(bundle);
        intent.setFlags(m14040a());
        activity.startActivity(intent);
    }

    /* renamed from: a */
    public void mo3672a(ResultReceiver resultReceiver, Exception exception) {
        Bundle bundle = new Bundle();
        bundle.putString("login_error", exception.getLocalizedMessage());
        resultReceiver.send(HttpStatus.SC_BAD_REQUEST, bundle);
    }

    @TargetApi(11)
    /* renamed from: a */
    int m14040a() {
        if (VERSION.SDK_INT >= 11) {
            return 268468224;
        }
        return 335544320;
    }
}
