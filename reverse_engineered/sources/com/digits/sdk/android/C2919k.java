package com.digits.sdk.android;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.widget.EditText;
import android.widget.TextView;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: ConfirmationCodeActivityDelegate */
/* renamed from: com.digits.sdk.android.k */
class C2919k extends ac {
    /* renamed from: a */
    EditText f13316a;
    /* renamed from: b */
    LinkTextView f13317b;
    /* renamed from: c */
    StateButton f13318c;
    /* renamed from: d */
    InvertedStateButton f13319d;
    /* renamed from: e */
    InvertedStateButton f13320e;
    /* renamed from: f */
    TextView f13321f;
    /* renamed from: g */
    TextView f13322g;
    /* renamed from: h */
    ah f13323h;
    /* renamed from: i */
    SmsBroadcastReceiver f13324i;
    /* renamed from: j */
    Activity f13325j;
    /* renamed from: k */
    ao f13326k;
    /* renamed from: l */
    AuthConfig f13327l;
    /* renamed from: m */
    bv f13328m;

    public C2919k(ao aoVar) {
        this.f13326k = aoVar;
    }

    /* renamed from: c */
    public int mo3659c() {
        return C2876R.layout.dgts__activity_confirmation;
    }

    /* renamed from: a */
    public boolean mo3657a(Bundle bundle) {
        return C2917i.m14203a(bundle, "receiver", "phone_number");
    }

    /* renamed from: a */
    public void mo3649a(Activity activity, Bundle bundle) {
        this.f13325j = activity;
        this.f13316a = (EditText) activity.findViewById(C2876R.id.dgts__confirmationEditText);
        this.f13318c = (StateButton) activity.findViewById(C2876R.id.dgts__createAccount);
        this.f13319d = (InvertedStateButton) activity.findViewById(C2876R.id.dgts__resendConfirmationButton);
        this.f13320e = (InvertedStateButton) activity.findViewById(C2876R.id.dgts__callMeButton);
        this.f13317b = (LinkTextView) activity.findViewById(C2876R.id.dgts__editPhoneNumber);
        this.f13321f = (TextView) activity.findViewById(C2876R.id.dgts__termsTextCreateAccount);
        this.f13322g = (TextView) activity.findViewById(C2876R.id.dgts__countdownTimer);
        this.f13327l = (AuthConfig) bundle.getParcelable("auth_config");
        this.f13323h = m14219b(bundle);
        this.f13328m = new bv(activity);
        mo3651a(activity, this.f13323h, this.f13316a);
        mo3653a(activity, this.f13323h, this.f13318c);
        mo3654a(activity, this.f13323h, this.f13326k, this.f13319d);
        mo3655a(activity, this.f13323h, this.f13326k, this.f13320e, this.f13327l);
        mo3656a(this.f13323h, this.f13322g, this.f13327l);
        mo3650a(activity, this.f13317b, bundle.getString("phone_number"));
        mo3652a(activity, this.f13323h, this.f13321f);
        m14215a(activity, this.f13316a);
        C4877i.b(activity, this.f13316a);
    }

    /* renamed from: b */
    ah m14219b(Bundle bundle) {
        return new C2923l((ResultReceiver) bundle.getParcelable("receiver"), this.f13318c, this.f13319d, this.f13320e, this.f13316a, bundle.getString("phone_number"), this.f13326k, bundle.getBoolean("email_enabled"), this.f13322g);
    }

    /* renamed from: a */
    public void mo3653a(Activity activity, ah ahVar, StateButton stateButton) {
        stateButton.m13875a(C2876R.string.dgts__create_account, C2876R.string.dgts__sending, C2876R.string.dgts__done);
        stateButton.m13883g();
        super.mo3653a(activity, ahVar, stateButton);
    }

    /* renamed from: a */
    public void mo3652a(Activity activity, ah ahVar, TextView textView) {
        textView.setText(this.f13328m.m14181a(C2876R.string.dgts__terms_text_create));
        super.mo3652a(activity, ahVar, textView);
    }

    /* renamed from: b */
    public void mo3658b() {
        this.f13326k.mo3666a();
        this.f13323h.mo3637a();
    }

    /* renamed from: a */
    public void mo3635a() {
        if (this.f13324i != null) {
            this.f13325j.unregisterReceiver(this.f13324i);
        }
        this.f13323h.mo3644f();
    }

    /* renamed from: a */
    protected void m14215a(Activity activity, EditText editText) {
        if (C4877i.c(activity, "android.permission.RECEIVE_SMS")) {
            IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            this.f13324i = new SmsBroadcastReceiver(editText);
            activity.registerReceiver(this.f13324i, intentFilter);
        }
    }
}
