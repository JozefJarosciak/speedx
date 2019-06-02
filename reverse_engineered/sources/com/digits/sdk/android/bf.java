package com.digits.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.avos.avoscloud.AVOSCloud;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4645j;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: LoginCodeController */
class bf extends ai {
    /* renamed from: k */
    private final long f13247k;
    /* renamed from: l */
    private final String f13248l;
    /* renamed from: m */
    private final Boolean f13249m;
    /* renamed from: n */
    private final InvertedStateButton f13250n;
    /* renamed from: o */
    private final InvertedStateButton f13251o;
    /* renamed from: p */
    private String f13252p;
    /* renamed from: q */
    private final TextView f13253q;

    bf(ResultReceiver resultReceiver, StateButton stateButton, InvertedStateButton invertedStateButton, InvertedStateButton invertedStateButton2, EditText editText, String str, long j, String str2, ao aoVar, Boolean bool, TextView textView) {
        this(resultReceiver, stateButton, invertedStateButton, invertedStateButton2, editText, aa.b(), aa.a().h(), str, j, str2, new C2925n(stateButton.getContext().getResources()), aa.a().l(), aoVar, bool, textView);
    }

    bf(ResultReceiver resultReceiver, StateButton stateButton, InvertedStateButton invertedStateButton, InvertedStateButton invertedStateButton2, EditText editText, C4586l<ap> c4586l, ag agVar, String str, long j, String str2, az azVar, C2877a c2877a, ao aoVar, Boolean bool, TextView textView) {
        super(resultReceiver, stateButton, editText, agVar, azVar, c2877a, c4586l, aoVar);
        this.f13252p = str;
        this.f13247k = j;
        this.f13248l = str2;
        this.f13249m = bool;
        this.f13250n = invertedStateButton;
        this.f13251o = invertedStateButton2;
        this.j = m13949a(AVOSCloud.DEFAULT_NETWORK_TIMEOUT, textView, invertedStateButton, invertedStateButton2);
        this.f13253q = textView;
    }

    /* renamed from: a */
    public void mo3660a(final Context context) {
        this.h.mo3668a(Element.SUBMIT);
        if (mo3662a(this.e.getText())) {
            this.f.m13880d();
            C4877i.a(context, this.e);
            this.a.m13932a(this.f13252p, this.f13247k, this.e.getText().toString(), new af<aq>(this, this, context) {
                /* renamed from: b */
                final /* synthetic */ bf f13240b;

                /* renamed from: a */
                public void m14057a(C4645j<aq> c4645j) {
                    this.f13240b.h.mo3670c();
                    if (((aq) c4645j.f16364a).a()) {
                        this.f13240b.m14068b(context);
                    } else if (this.f13240b.f13249m.booleanValue()) {
                        this.f13240b.m14062a(context, ap.a((aq) c4645j.f16364a, this.f13240b.f13248l));
                    } else {
                        this.f13240b.m13955a(context, ap.a((aq) c4645j.f16364a, this.f13240b.f13248l), this.f13240b.f13248l);
                    }
                }
            });
        }
    }

    /* renamed from: a */
    public void mo3639a(Context context, final InvertedStateButton invertedStateButton, Verification verification) {
        invertedStateButton.m13880d();
        this.a.m13933a(this.f13248l, verification, new af<C1470g>(this, context, this) {
            /* renamed from: b */
            final /* synthetic */ bf f13243b;

            /* compiled from: LoginCodeController */
            /* renamed from: com.digits.sdk.android.bf$2$1 */
            class C29021 implements Runnable {
                /* renamed from: a */
                final /* synthetic */ C29032 f13241a;

                C29021(C29032 c29032) {
                    this.f13241a = c29032;
                }

                public void run() {
                    invertedStateButton.m13883g();
                    this.f13241a.f13243b.f13253q.setText(String.valueOf(15), BufferType.NORMAL);
                    this.f13241a.f13243b.f13250n.setEnabled(false);
                    this.f13241a.f13243b.f13251o.setEnabled(false);
                    this.f13241a.f13243b.mo3643e();
                }
            }

            /* renamed from: a */
            public void m14058a(C4645j<C1470g> c4645j) {
                invertedStateButton.m13881e();
                this.f13243b.f13252p = ((C1470g) c4645j.f16364a).f6892b;
                invertedStateButton.postDelayed(new C29021(this), 1500);
            }
        });
    }

    /* renamed from: a */
    public void mo3638a(Context context, DigitsException digitsException) {
        this.f13251o.m13882f();
        this.f13250n.m13882f();
        super.mo3638a(context, digitsException);
    }

    /* renamed from: a */
    private void m14062a(Context context, ap apVar) {
        final ap apVar2 = apVar;
        final Context context2 = context;
        m14072a(apVar).verifyAccount(new af<bz>(this, context, this) {
            /* renamed from: d */
            final /* synthetic */ bf f13246d;

            /* renamed from: a */
            public void m14059a(C4645j<bz> c4645j) {
                ap a = ap.a((bz) c4645j.f16364a);
                if (this.f13246d.m14065a(a, apVar2)) {
                    this.f13246d.g.a(a);
                    this.f13246d.m13956a(context2, this.f13246d.f13248l);
                    return;
                }
                this.f13246d.m13955a(context2, a, this.f13246d.f13248l);
            }
        });
    }

    /* renamed from: b */
    private void m14068b(Context context) {
        Intent intent = new Intent(context, this.b.mo3681e());
        Bundle a = m13948a(this.f13248l);
        a.putParcelable("receiver", this.d);
        a.putString("request_id", this.f13252p);
        a.putLong("user_id", this.f13247k);
        a.putBoolean("email_enabled", this.f13249m.booleanValue());
        intent.putExtras(a);
        m13951a((Activity) context, intent);
    }

    /* renamed from: a */
    private boolean m14065a(ap apVar, ap apVar2) {
        return this.f13249m.booleanValue() && apVar.c().equals(ap.f6876a) && apVar.e() == apVar2.e();
    }

    /* renamed from: a */
    DigitsApiClient$AccountService m14072a(ap apVar) {
        return new DigitsApiClient(apVar).m13870d();
    }
}
