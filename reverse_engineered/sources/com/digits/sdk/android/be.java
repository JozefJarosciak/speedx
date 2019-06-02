package com.digits.sdk.android;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.widget.EditText;
import android.widget.TextView;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: LoginCodeActivityDelegate */
class be extends ac {
    /* renamed from: a */
    EditText f13226a;
    /* renamed from: b */
    LinkTextView f13227b;
    /* renamed from: c */
    StateButton f13228c;
    /* renamed from: d */
    InvertedStateButton f13229d;
    /* renamed from: e */
    InvertedStateButton f13230e;
    /* renamed from: f */
    TextView f13231f;
    /* renamed from: g */
    TextView f13232g;
    /* renamed from: h */
    ah f13233h;
    /* renamed from: i */
    SmsBroadcastReceiver f13234i;
    /* renamed from: j */
    Activity f13235j;
    /* renamed from: k */
    AuthConfig f13236k;
    /* renamed from: l */
    bv f13237l;
    /* renamed from: m */
    private final ao f13238m;

    be(ao aoVar) {
        this.f13238m = aoVar;
    }

    /* renamed from: a */
    public void mo3649a(Activity activity, Bundle bundle) {
        this.f13235j = activity;
        this.f13226a = (EditText) activity.findViewById(C2876R.id.dgts__confirmationEditText);
        this.f13228c = (StateButton) activity.findViewById(C2876R.id.dgts__createAccount);
        this.f13229d = (InvertedStateButton) activity.findViewById(C2876R.id.dgts__resendConfirmationButton);
        this.f13230e = (InvertedStateButton) activity.findViewById(C2876R.id.dgts__callMeButton);
        this.f13227b = (LinkTextView) activity.findViewById(C2876R.id.dgts__editPhoneNumber);
        this.f13231f = (TextView) activity.findViewById(C2876R.id.dgts__termsTextCreateAccount);
        this.f13232g = (TextView) activity.findViewById(C2876R.id.dgts__countdownTimer);
        this.f13236k = (AuthConfig) bundle.getParcelable("auth_config");
        this.f13233h = m14054b(bundle);
        this.f13237l = new bv(activity);
        mo3651a(activity, this.f13233h, this.f13226a);
        mo3653a(activity, this.f13233h, this.f13228c);
        mo3654a(activity, this.f13233h, this.f13238m, this.f13229d);
        mo3655a(activity, this.f13233h, this.f13238m, this.f13230e, this.f13236k);
        mo3656a(this.f13233h, this.f13232g, this.f13236k);
        mo3650a(activity, this.f13227b, bundle.getString("phone_number"));
        mo3652a(activity, this.f13233h, this.f13231f);
        m14050a(activity, this.f13226a);
        C4877i.b(activity, this.f13226a);
    }

    /* renamed from: b */
    ah m14054b(Bundle bundle) {
        return new bf((ResultReceiver) bundle.getParcelable("receiver"), this.f13228c, this.f13229d, this.f13230e, this.f13226a, bundle.getString("request_id"), bundle.getLong("user_id"), bundle.getString("phone_number"), this.f13238m, Boolean.valueOf(bundle.getBoolean("email_enabled")), this.f13232g);
    }

    /* renamed from: a */
    public void mo3653a(Activity activity, ah ahVar, StateButton stateButton) {
        stateButton.m13875a(C2876R.string.dgts__continue, C2876R.string.dgts__sending, C2876R.string.dgts__done);
        stateButton.m13883g();
        super.mo3653a(activity, ahVar, stateButton);
    }

    /* renamed from: a */
    public void mo3652a(Activity activity, ah ahVar, TextView textView) {
        if (this.f13236k == null || !this.f13236k.tosUpdate) {
            textView.setText(this.f13237l.m14181a(C2876R.string.dgts__terms_text_sign_in));
        } else {
            textView.setText(this.f13237l.m14181a(C2876R.string.dgts__terms_text_updated));
        }
        super.mo3652a(activity, ahVar, textView);
    }

    /* renamed from: c */
    public int mo3659c() {
        return C2876R.layout.dgts__activity_confirmation;
    }

    /* renamed from: a */
    public boolean mo3657a(Bundle bundle) {
        return C2917i.m14203a(bundle, "receiver", "phone_number", "request_id", "user_id");
    }

    /* renamed from: b */
    public void mo3658b() {
        this.f13238m.mo3666a();
        this.f13233h.mo3637a();
    }

    /* renamed from: a */
    public void mo3635a() {
        if (this.f13234i != null) {
            this.f13235j.unregisterReceiver(this.f13234i);
        }
        this.f13233h.mo3644f();
    }

    /* renamed from: a */
    protected void m14050a(Activity activity, EditText editText) {
        if (C4877i.c(activity, "android.permission.RECEIVE_SMS")) {
            IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
            this.f13234i = new SmsBroadcastReceiver(editText);
            activity.registerReceiver(this.f13234i, intentFilter);
        }
    }
}
