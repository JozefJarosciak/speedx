package com.digits.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.widget.EditText;
import com.digits.sdk.android.bn.C2908a;
import com.twitter.sdk.android.core.C4586l;
import io.fabric.sdk.android.services.common.C4877i;
import java.util.Locale;
import org.slf4j.Marker;

/* compiled from: PhoneNumberController */
class bk extends ai implements C2908a {
    /* renamed from: k */
    final CountryListSpinner f13283k;
    /* renamed from: l */
    boolean f13284l;
    /* renamed from: m */
    boolean f13285m;
    /* renamed from: n */
    boolean f13286n;
    /* renamed from: o */
    private final bw f13287o;

    bk(ResultReceiver resultReceiver, StateButton stateButton, EditText editText, CountryListSpinner countryListSpinner, bw bwVar, ao aoVar, boolean z) {
        this(resultReceiver, stateButton, editText, countryListSpinner, aa.a().h(), new bl(stateButton.getContext().getResources()), aa.a().l(), aa.b(), bwVar, aoVar, z);
    }

    bk(ResultReceiver resultReceiver, StateButton stateButton, EditText editText, CountryListSpinner countryListSpinner, ag agVar, az azVar, C2877a c2877a, C4586l<ap> c4586l, bw bwVar, ao aoVar, boolean z) {
        super(resultReceiver, stateButton, editText, agVar, azVar, c2877a, c4586l, aoVar);
        this.f13283k = countryListSpinner;
        this.f13287o = bwVar;
        this.f13284l = false;
        this.f13285m = false;
        this.f13286n = z;
    }

    /* renamed from: b */
    public void m14126b(bi biVar) {
        if (bi.m14099a(biVar)) {
            this.e.setText(biVar.m14102c());
            this.e.setSelection(biVar.m14102c().length());
        }
    }

    /* renamed from: c */
    public void m14127c(bi biVar) {
        if (bi.m14100b(biVar)) {
            this.f13283k.m13864a(new Locale("", biVar.m14103d()).getDisplayName(), biVar.m14101b());
        }
    }

    /* renamed from: b */
    bh m14125b(Context context, String str) {
        final Context context2 = context;
        return new bh(this, context, this.a, str, m14122j(), this.f13286n, this.d, this.b) {
            /* renamed from: j */
            final /* synthetic */ bk f13282j;

            /* renamed from: a */
            public void mo3675a(final Intent intent) {
                this.f13282j.f.m13881e();
                this.f13282j.e.postDelayed(new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ C29101 f13280b;

                    public void run() {
                        this.f13280b.f13282j.h.mo3670c();
                        this.f13280b.f13282j.m13951a((Activity) context2, intent);
                    }
                }, 1500);
            }

            /* renamed from: a */
            public void mo3676a(DigitsException digitsException) {
                if (digitsException instanceof OperatorUnsupportedException) {
                    this.f13282j.f13284l = digitsException.getConfig().isVoiceEnabled;
                    this.f13282j.m14128g();
                    this.f13282j.mo3638a(context2, digitsException);
                    return;
                }
                this.f13282j.mo3638a(context2, digitsException);
            }
        };
    }

    /* renamed from: a */
    public void mo3660a(Context context) {
        m14120h();
        if (mo3662a(this.e.getText())) {
            this.f.m13880d();
            C4877i.a(context, this.e);
            m14125b(context, m14119a((long) ((Integer) this.f13283k.getTag()).intValue(), this.e.getText().toString())).m14095a();
        }
    }

    /* renamed from: h */
    private void m14120h() {
        if (m14121i()) {
            this.h.mo3668a(Element.RETRY);
        } else {
            this.h.mo3668a(Element.SUBMIT);
        }
    }

    /* renamed from: i */
    private boolean m14121i() {
        return this.i > 0;
    }

    @NonNull
    /* renamed from: j */
    private Verification m14122j() {
        return (this.f13285m && this.f13284l) ? Verification.voicecall : Verification.sms;
    }

    /* renamed from: a */
    private String m14119a(long j, String str) {
        return Marker.ANY_NON_NULL_MARKER + String.valueOf(j) + str;
    }

    /* renamed from: a */
    public void mo3674a(bi biVar) {
        m14126b(biVar);
        m14127c(biVar);
    }

    /* renamed from: g */
    public void m14128g() {
        this.f13285m = true;
        if (this.f13284l) {
            this.f.m13875a(C2876R.string.dgts__call_me, C2876R.string.dgts__calling, C2876R.string.dgts__calling);
            this.f13287o.mo3673a(C2876R.string.dgts__terms_text_call_me);
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (Verification.voicecall.equals(m14122j())) {
            this.f13285m = false;
            this.f.m13875a(C2876R.string.dgts__continue, C2876R.string.dgts__sending, C2876R.string.dgts__done);
            this.f.m13883g();
            this.f13287o.mo3673a(C2876R.string.dgts__terms_text);
        }
    }
}
