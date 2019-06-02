package com.digits.sdk.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.widget.EditText;
import android.widget.TextView;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: EmailRequestActivityDelegate */
public class aw extends ac {
    /* renamed from: a */
    EditText f13200a;
    /* renamed from: b */
    StateButton f13201b;
    /* renamed from: c */
    InvertedStateButton f13202c;
    /* renamed from: d */
    InvertedStateButton f13203d;
    /* renamed from: e */
    LinkTextView f13204e;
    /* renamed from: f */
    TextView f13205f;
    /* renamed from: g */
    TextView f13206g;
    /* renamed from: h */
    ah f13207h;
    /* renamed from: i */
    Activity f13208i;
    /* renamed from: j */
    ao f13209j;
    /* renamed from: k */
    TextView f13210k;
    /* renamed from: l */
    bv f13211l;

    /* renamed from: a */
    public /* bridge */ /* synthetic */ void mo3635a() {
        super.mo3635a();
    }

    aw(ao aoVar) {
        this.f13209j = aoVar;
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
        this.f13208i = activity;
        this.f13210k = (TextView) activity.findViewById(C2876R.id.dgts__titleText);
        this.f13200a = (EditText) activity.findViewById(C2876R.id.dgts__confirmationEditText);
        this.f13201b = (StateButton) activity.findViewById(C2876R.id.dgts__createAccount);
        this.f13202c = (InvertedStateButton) activity.findViewById(C2876R.id.dgts__resendConfirmationButton);
        this.f13203d = (InvertedStateButton) activity.findViewById(C2876R.id.dgts__callMeButton);
        this.f13204e = (LinkTextView) activity.findViewById(C2876R.id.dgts__editPhoneNumber);
        this.f13205f = (TextView) activity.findViewById(C2876R.id.dgts__termsTextCreateAccount);
        this.f13206g = (TextView) activity.findViewById(C2876R.id.dgts__countdownTimer);
        AuthConfig authConfig = (AuthConfig) bundle.getParcelable("auth_config");
        this.f13207h = m13990b(bundle);
        this.f13211l = new bv(activity);
        this.f13200a.setHint(C2876R.string.dgts__email_request_edit_hint);
        this.f13210k.setText(C2876R.string.dgts__email_request_title);
        mo3651a(activity, this.f13207h, this.f13200a);
        mo3653a(activity, this.f13207h, this.f13201b);
        mo3654a(activity, this.f13207h, this.f13209j, this.f13202c);
        mo3655a(activity, this.f13207h, this.f13209j, this.f13203d, authConfig);
        mo3656a(this.f13207h, this.f13206g, authConfig);
        mo3650a(activity, this.f13204e, bundle.getString("phone_number"));
        mo3652a(activity, this.f13207h, this.f13205f);
        C4877i.b(activity, this.f13200a);
    }

    /* renamed from: a */
    protected void mo3650a(Activity activity, LinkTextView linkTextView, String str) {
        linkTextView.setVisibility(8);
    }

    /* renamed from: a */
    void mo3654a(Activity activity, ah ahVar, ao aoVar, InvertedStateButton invertedStateButton) {
        invertedStateButton.setVisibility(8);
    }

    /* renamed from: a */
    void mo3655a(Activity activity, ah ahVar, ao aoVar, InvertedStateButton invertedStateButton, AuthConfig authConfig) {
        invertedStateButton.setVisibility(8);
    }

    /* renamed from: a */
    void mo3656a(ah ahVar, TextView textView, AuthConfig authConfig) {
        textView.setVisibility(8);
    }

    /* renamed from: a */
    public void mo3651a(Activity activity, ah ahVar, EditText editText) {
        editText.setInputType(32);
        super.mo3651a(activity, ahVar, editText);
    }

    /* renamed from: a */
    public void mo3653a(Activity activity, ah ahVar, StateButton stateButton) {
        stateButton.m13875a(C2876R.string.dgts__continue, C2876R.string.dgts__sending, C2876R.string.dgts__done);
        stateButton.m13883g();
        super.mo3653a(activity, ahVar, stateButton);
    }

    /* renamed from: a */
    public void mo3652a(Activity activity, ah ahVar, TextView textView) {
        textView.setText(this.f13211l.m14181a(C2876R.string.dgts__terms_email_request));
        super.mo3652a(activity, ahVar, textView);
    }

    /* renamed from: b */
    private ah m13990b(Bundle bundle) {
        return new ax(this.f13201b, this.f13200a, (ResultReceiver) bundle.getParcelable("receiver"), bundle.getString("phone_number"), this.f13209j);
    }

    /* renamed from: b */
    public void mo3658b() {
        this.f13209j.mo3666a();
        this.f13207h.mo3637a();
    }
}
