package com.digits.sdk.android;

import android.app.Activity;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: FailureActivityDelegateImpl */
class ba {
    /* renamed from: a */
    final Activity f13221a;
    /* renamed from: b */
    final bb f13222b;
    /* renamed from: c */
    final ao f13223c;

    /* compiled from: FailureActivityDelegateImpl */
    /* renamed from: com.digits.sdk.android.ba$1 */
    class C28991 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ ba f13219a;

        C28991(ba baVar) {
            this.f13219a = baVar;
        }

        public void onClick(View view) {
            this.f13219a.f13223c.mo3668a(Element.DISMISS);
            C4877i.a(this.f13219a.f13221a, 200);
            this.f13219a.f13222b.mo3672a(this.f13219a.m14030d(), this.f13219a.m14031e());
        }
    }

    /* compiled from: FailureActivityDelegateImpl */
    /* renamed from: com.digits.sdk.android.ba$2 */
    class C29002 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ ba f13220a;

        C29002(ba baVar) {
            this.f13220a = baVar;
        }

        public void onClick(View view) {
            this.f13220a.f13223c.mo3668a(Element.RETRY);
            this.f13220a.f13222b.mo3671a(this.f13220a.f13221a, this.f13220a.m14030d());
            this.f13220a.f13221a.finish();
        }
    }

    public ba(Activity activity) {
        this(activity, new bc(), new bd(aa.a().i()));
    }

    public ba(Activity activity, bb bbVar, ao aoVar) {
        this.f13221a = activity;
        this.f13222b = bbVar;
        this.f13223c = aoVar;
    }

    /* renamed from: a */
    public void m14032a() {
        this.f13223c.mo3666a();
        if (m14035a(this.f13221a.getIntent().getExtras())) {
            m14036b();
            m14037c();
            return;
        }
        throw new IllegalAccessError("This activity can only be started from Digits");
    }

    /* renamed from: a */
    protected boolean m14035a(Bundle bundle) {
        return C2917i.m14203a(bundle, "receiver");
    }

    /* renamed from: b */
    protected void m14036b() {
        this.f13221a.setContentView(C2876R.layout.dgts__activity_failure);
    }

    /* renamed from: c */
    protected void m14037c() {
        TextView textView = (TextView) this.f13221a.findViewById(C2876R.id.dgts__try_another_phone);
        m14033a((Button) this.f13221a.findViewById(C2876R.id.dgts__dismiss_button));
        m14034a(textView);
    }

    /* renamed from: a */
    protected void m14033a(Button button) {
        button.setOnClickListener(new C28991(this));
    }

    /* renamed from: a */
    protected void m14034a(TextView textView) {
        textView.setOnClickListener(new C29002(this));
    }

    /* renamed from: d */
    private ResultReceiver m14030d() {
        return (ResultReceiver) this.f13221a.getIntent().getExtras().getParcelable("receiver");
    }

    /* renamed from: e */
    private DigitsException m14031e() {
        return (DigitsException) this.f13221a.getIntent().getExtras().getSerializable("fallback_reason");
    }
}
