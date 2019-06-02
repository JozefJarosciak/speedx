package com.digits.sdk.android;

import android.content.Context;
import android.os.ResultReceiver;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.avos.avoscloud.AVOSCloud;
import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.C4645j;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: ConfirmationCodeController */
/* renamed from: com.digits.sdk.android.l */
class C2923l extends ai {
    /* renamed from: k */
    private final String f13334k;
    /* renamed from: l */
    private final Boolean f13335l;
    /* renamed from: m */
    private final InvertedStateButton f13336m;
    /* renamed from: n */
    private final InvertedStateButton f13337n;
    /* renamed from: o */
    private final TextView f13338o;

    C2923l(ResultReceiver resultReceiver, StateButton stateButton, InvertedStateButton invertedStateButton, InvertedStateButton invertedStateButton2, EditText editText, String str, ao aoVar, boolean z, TextView textView) {
        this(resultReceiver, stateButton, invertedStateButton, invertedStateButton2, editText, str, aa.b(), aa.a().h(), new C2925n(stateButton.getContext().getResources()), aa.a().l(), aoVar, z, textView);
    }

    C2923l(ResultReceiver resultReceiver, StateButton stateButton, InvertedStateButton invertedStateButton, InvertedStateButton invertedStateButton2, EditText editText, String str, C4586l<ap> c4586l, ag agVar, az azVar, C2877a c2877a, ao aoVar, boolean z, TextView textView) {
        super(resultReceiver, stateButton, editText, agVar, azVar, c2877a, c4586l, aoVar);
        this.f13334k = str;
        this.f13335l = Boolean.valueOf(z);
        this.f13336m = invertedStateButton;
        this.f13337n = invertedStateButton2;
        this.j = m13949a(AVOSCloud.DEFAULT_NETWORK_TIMEOUT, textView, invertedStateButton, invertedStateButton2);
        this.f13338o = textView;
    }

    /* renamed from: a */
    public void mo3660a(final Context context) {
        this.h.mo3668a(Element.SUBMIT);
        if (mo3662a(this.e.getText())) {
            this.f.m13880d();
            C4877i.a(context, this.e);
            this.a.m13934a(this.e.getText().toString(), this.f13334k, new af<as>(this, this, context) {
                /* renamed from: b */
                final /* synthetic */ C2923l f13330b;

                /* renamed from: a */
                public void m14222a(C4645j<as> c4645j) {
                    this.f13330b.h.mo3670c();
                    C1469k a = ap.a(c4645j, this.f13330b.f13334k);
                    if (this.f13330b.f13335l.booleanValue()) {
                        this.f13330b.g.a(a);
                        this.f13330b.m13956a(context, this.f13330b.f13334k);
                        return;
                    }
                    this.f13330b.m13955a(context, (ap) a, this.f13330b.f13334k);
                }
            });
        }
    }

    /* renamed from: a */
    public void mo3639a(Context context, final InvertedStateButton invertedStateButton, Verification verification) {
        invertedStateButton.m13880d();
        this.a.m13936b(this.f13334k, verification, new af<C1471z>(this, context, this) {
            /* renamed from: b */
            final /* synthetic */ C2923l f13333b;

            /* compiled from: ConfirmationCodeController */
            /* renamed from: com.digits.sdk.android.l$2$1 */
            class C29211 implements Runnable {
                /* renamed from: a */
                final /* synthetic */ C29222 f13331a;

                C29211(C29222 c29222) {
                    this.f13331a = c29222;
                }

                public void run() {
                    invertedStateButton.m13883g();
                    this.f13331a.f13333b.f13338o.setText(String.valueOf(15), BufferType.NORMAL);
                    this.f13331a.f13333b.f13336m.setEnabled(false);
                    this.f13331a.f13333b.f13337n.setEnabled(false);
                    this.f13331a.f13333b.mo3643e();
                }
            }

            /* renamed from: a */
            public void m14223a(C4645j<C1471z> c4645j) {
                invertedStateButton.m13881e();
                invertedStateButton.postDelayed(new C29211(this), 1500);
            }
        });
    }

    /* renamed from: a */
    public void mo3638a(Context context, DigitsException digitsException) {
        this.f13337n.m13882f();
        this.f13336m.m13882f();
        super.mo3638a(context, digitsException);
    }
}
