package com.digits.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import com.digits.sdk.android.bn.C2908a;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: PhoneNumberActivityDelegate */
class bj extends ac implements C2908a, bw {
    /* renamed from: a */
    CountryListSpinner f13271a;
    /* renamed from: b */
    StateButton f13272b;
    /* renamed from: c */
    EditText f13273c;
    /* renamed from: d */
    TextView f13274d;
    /* renamed from: e */
    bk f13275e;
    /* renamed from: f */
    bv f13276f;
    /* renamed from: g */
    private final ao f13277g;
    /* renamed from: h */
    private Activity f13278h;

    /* compiled from: PhoneNumberActivityDelegate */
    /* renamed from: com.digits.sdk.android.bj$1 */
    class C29071 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ bj f13270a;

        C29071(bj bjVar) {
            this.f13270a = bjVar;
        }

        public void onClick(View view) {
            this.f13270a.f13277g.mo3668a(Element.COUNTRY_CODE);
            this.f13270a.f13275e.mo3642d();
        }
    }

    public bj(ao aoVar) {
        this.f13277g = aoVar;
    }

    /* renamed from: c */
    public int mo3659c() {
        return C2876R.layout.dgts__activity_phone_number;
    }

    /* renamed from: a */
    public boolean mo3657a(Bundle bundle) {
        return C2917i.m14203a(bundle, "receiver");
    }

    /* renamed from: a */
    public void mo3649a(Activity activity, Bundle bundle) {
        this.f13278h = activity;
        this.f13271a = (CountryListSpinner) activity.findViewById(C2876R.id.dgts__countryCode);
        this.f13272b = (StateButton) activity.findViewById(C2876R.id.dgts__sendCodeButton);
        this.f13273c = (EditText) activity.findViewById(C2876R.id.dgts__phoneNumberEditText);
        this.f13274d = (TextView) activity.findViewById(C2876R.id.dgts__termsText);
        this.f13275e = m14114b(bundle);
        this.f13276f = new bv(activity);
        mo3651a(activity, this.f13275e, this.f13273c);
        mo3653a(activity, this.f13275e, this.f13272b);
        mo3652a(activity, this.f13275e, this.f13274d);
        m14111a(this.f13271a);
        m14107a(new bo(bt.m14168a((Context) activity)), bundle);
        C4877i.b(activity, this.f13273c);
    }

    /* renamed from: a */
    private void m14107a(bo boVar, Bundle bundle) {
        Object string = bundle.getString("phone_number");
        if (TextUtils.isEmpty(string)) {
            new bn(boVar, this).a(aa.a().k(), new Void[0]);
        } else {
            new bn(boVar, string, this).a(aa.a().k(), new Void[0]);
        }
    }

    /* renamed from: b */
    bk m14114b(Bundle bundle) {
        return new bk((ResultReceiver) bundle.getParcelable("receiver"), this.f13272b, this.f13273c, this.f13271a, this, this.f13277g, bundle.getBoolean("email_enabled"));
    }

    /* renamed from: a */
    public void mo3652a(Activity activity, ah ahVar, TextView textView) {
        textView.setText(this.f13276f.m14181a(C2876R.string.dgts__terms_text));
        super.mo3652a(activity, ahVar, textView);
    }

    /* renamed from: a */
    protected void m14111a(CountryListSpinner countryListSpinner) {
        countryListSpinner.setOnClickListener(new C29071(this));
    }

    /* renamed from: b */
    public void mo3658b() {
        this.f13277g.mo3666a();
        this.f13275e.mo3637a();
    }

    /* renamed from: a */
    public void mo3674a(bi biVar) {
        this.f13275e.m14126b(biVar);
        this.f13275e.m14127c(biVar);
    }

    /* renamed from: a */
    public void mo3673a(int i) {
        this.f13274d.setText(this.f13276f.m14181a(i));
    }
}
