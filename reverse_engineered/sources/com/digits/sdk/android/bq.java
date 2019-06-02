package com.digits.sdk.android;

import android.content.Context;
import android.os.ResultReceiver;
import android.widget.EditText;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4645j;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: PinCodeController */
class bq extends ai {
    /* renamed from: k */
    private final String f13303k;
    /* renamed from: l */
    private final long f13304l;
    /* renamed from: m */
    private final String f13305m;
    /* renamed from: n */
    private final Boolean f13306n;

    bq(ResultReceiver resultReceiver, StateButton stateButton, EditText editText, String str, long j, String str2, ao aoVar, Boolean bool) {
        this(resultReceiver, stateButton, editText, aa.b(), aa.a().h(), str, j, str2, new C2925n(stateButton.getContext().getResources()), aa.a().l(), aoVar, bool);
    }

    bq(ResultReceiver resultReceiver, StateButton stateButton, EditText editText, C4586l<ap> c4586l, ag agVar, String str, long j, String str2, az azVar, C2877a c2877a, ao aoVar, Boolean bool) {
        super(resultReceiver, stateButton, editText, agVar, azVar, c2877a, c4586l, aoVar);
        this.f13303k = str;
        this.f13304l = j;
        this.f13305m = str2;
        this.f13306n = bool;
    }

    /* renamed from: a */
    public void mo3660a(final Context context) {
        this.h.mo3668a(Element.SUBMIT);
        if (mo3662a(this.e.getText())) {
            this.f.m13880d();
            C4877i.a(context, this.e);
            this.a.m13935b(this.f13303k, this.f13304l, this.e.getText().toString(), new af<aq>(this, this, context) {
                /* renamed from: b */
                final /* synthetic */ bq f13299b;

                /* renamed from: a */
                public void m14152a(C4645j<aq> c4645j) {
                    this.f13299b.h.mo3670c();
                    ap a = ap.a((aq) c4645j.f16364a, this.f13299b.f13305m);
                    if (this.f13299b.f13306n.booleanValue()) {
                        this.f13299b.m14155a(context, a);
                    } else {
                        this.f13299b.m13955a(context, a, this.f13299b.f13305m);
                    }
                }
            });
        }
    }

    /* renamed from: a */
    private boolean m14157a(ap apVar, ap apVar2) {
        return this.f13306n.booleanValue() && apVar.c().equals(ap.f6876a) && apVar.e() == apVar2.e();
    }

    /* renamed from: a */
    private void m14155a(Context context, ap apVar) {
        final ap apVar2 = apVar;
        final Context context2 = context;
        m14160a(apVar).verifyAccount(new af<bz>(this, context, this) {
            /* renamed from: d */
            final /* synthetic */ bq f13302d;

            /* renamed from: a */
            public void m14153a(C4645j<bz> c4645j) {
                ap a = ap.a((bz) c4645j.f16364a);
                if (this.f13302d.m14157a(a, apVar2)) {
                    this.f13302d.g.a(a);
                    this.f13302d.m13956a(context2, this.f13302d.f13305m);
                    return;
                }
                this.f13302d.m13955a(context2, a, this.f13302d.f13305m);
            }
        });
    }

    /* renamed from: a */
    DigitsApiClient$AccountService m14160a(ap apVar) {
        return new DigitsApiClient(apVar).m13870d();
    }
}
