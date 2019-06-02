package com.digits.sdk.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.widget.EditText;
import android.widget.TextView;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: PinCodeActivityDelegate */
class bp extends ac {
    /* renamed from: a */
    EditText f13293a;
    /* renamed from: b */
    StateButton f13294b;
    /* renamed from: c */
    TextView f13295c;
    /* renamed from: d */
    ah f13296d;
    /* renamed from: e */
    private final ao f13297e;

    bp(ao aoVar) {
        this.f13297e = aoVar;
    }

    /* renamed from: c */
    public int mo3659c() {
        return C2876R.layout.dgts__activity_pin_code;
    }

    /* renamed from: a */
    public void mo3649a(Activity activity, Bundle bundle) {
        this.f13293a = (EditText) activity.findViewById(C2876R.id.dgts__confirmationEditText);
        this.f13294b = (StateButton) activity.findViewById(C2876R.id.dgts__createAccount);
        this.f13295c = (TextView) activity.findViewById(C2876R.id.dgts__termsTextCreateAccount);
        this.f13296d = m14149b(bundle);
        mo3651a(activity, this.f13296d, this.f13293a);
        mo3653a(activity, this.f13296d, this.f13294b);
        mo3652a(activity, this.f13296d, this.f13295c);
        C4877i.b(activity, this.f13293a);
    }

    /* renamed from: b */
    ah m14149b(Bundle bundle) {
        return new bq((ResultReceiver) bundle.getParcelable("receiver"), this.f13294b, this.f13293a, bundle.getString("request_id"), bundle.getLong("user_id"), bundle.getString("phone_number"), this.f13297e, Boolean.valueOf(bundle.getBoolean("email_enabled")));
    }

    /* renamed from: a */
    public boolean mo3657a(Bundle bundle) {
        return C2917i.m14203a(bundle, "receiver", "phone_number", "request_id", "user_id");
    }

    /* renamed from: b */
    public void mo3658b() {
        this.f13297e.mo3666a();
        this.f13296d.mo3637a();
    }
}
