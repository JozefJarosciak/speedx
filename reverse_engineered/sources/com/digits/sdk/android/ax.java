package com.digits.sdk.android;

import android.content.Context;
import android.os.ResultReceiver;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.EditText;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4645j;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: EmailRequestController */
public class ax extends ai {
    /* renamed from: k */
    private String f13215k;

    /* renamed from: a */
    public /* bridge */ /* synthetic */ void mo3637a() {
        super.mo3637a();
    }

    /* renamed from: a */
    public /* bridge */ /* synthetic */ void mo3638a(Context context, DigitsException digitsException) {
        super.mo3638a(context, digitsException);
    }

    public /* bridge */ /* synthetic */ void afterTextChanged(Editable editable) {
        super.afterTextChanged(editable);
    }

    /* renamed from: b */
    public /* bridge */ /* synthetic */ TextWatcher mo3640b() {
        return super.mo3640b();
    }

    public /* bridge */ /* synthetic */ void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.beforeTextChanged(charSequence, i, i2, i3);
    }

    /* renamed from: c */
    public /* bridge */ /* synthetic */ az mo3641c() {
        return super.mo3641c();
    }

    /* renamed from: d */
    public /* bridge */ /* synthetic */ void mo3642d() {
        super.mo3642d();
    }

    /* renamed from: e */
    public /* bridge */ /* synthetic */ void mo3643e() {
        super.mo3643e();
    }

    /* renamed from: f */
    public /* bridge */ /* synthetic */ void mo3644f() {
        super.mo3644f();
    }

    public /* bridge */ /* synthetic */ void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
    }

    ax(StateButton stateButton, EditText editText, ResultReceiver resultReceiver, String str, ao aoVar) {
        this(resultReceiver, stateButton, editText, aa.b(), aa.a().l(), new ag(), str, aoVar, new av(stateButton.getContext().getResources()));
    }

    ax(ResultReceiver resultReceiver, StateButton stateButton, EditText editText, C4586l<ap> c4586l, C2877a c2877a, ag agVar, String str, ao aoVar, az azVar) {
        super(resultReceiver, stateButton, editText, agVar, azVar, c2877a, c4586l, aoVar);
        this.f13215k = str;
    }

    /* renamed from: a */
    public void mo3660a(Context context) {
        this.h.mo3668a(Element.SUBMIT);
        if (mo3662a(this.e.getText())) {
            this.f.m13880d();
            C4877i.a(context, this.e);
            String obj = this.e.getText().toString();
            final ap apVar = (ap) this.g.b();
            if (apVar == null || apVar.a()) {
                mo3638a(context, new UnrecoverableException(""));
                return;
            }
            final Context context2 = context;
            m14007a(apVar).email(obj, new af<aq>(this, context, this) {
                /* renamed from: d */
                final /* synthetic */ ax f13214d;

                /* renamed from: a */
                public void m14004a(C4645j<aq> c4645j) {
                    this.f13214d.h.mo3670c();
                    this.f13214d.m13955a(context2, apVar, this.f13214d.f13215k);
                }
            });
            return;
        }
        this.e.setError(context.getString(C2876R.string.dgts__invalid_email));
    }

    /* renamed from: a */
    DigitsApiClient$SdkService m14007a(ap apVar) {
        return new DigitsApiClient(apVar).m13868b();
    }

    /* renamed from: a */
    public boolean mo3662a(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence) && m14006b(charSequence.toString());
    }

    /* renamed from: b */
    private boolean m14006b(String str) {
        return Patterns.EMAIL_ADDRESS.matcher(str).find();
    }
}
