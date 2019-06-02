package com.digits.sdk.android;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/* compiled from: ContactsActivityDelegateImpl */
/* renamed from: com.digits.sdk.android.o */
class C2928o {
    /* renamed from: a */
    final Activity f13342a;
    /* renamed from: b */
    final C2930q f13343b;
    /* renamed from: c */
    private final ao f13344c;

    /* compiled from: ContactsActivityDelegateImpl */
    /* renamed from: com.digits.sdk.android.o$1 */
    class C29261 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2928o f13340a;

        C29261(C2928o c2928o) {
            this.f13340a = c2928o;
        }

        public void onClick(View view) {
            this.f13340a.f13344c.mo3668a(Element.CANCEL);
            this.f13340a.f13342a.finish();
        }
    }

    /* compiled from: ContactsActivityDelegateImpl */
    /* renamed from: com.digits.sdk.android.o$2 */
    class C29272 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2928o f13341a;

        C29272(C2928o c2928o) {
            this.f13341a = c2928o;
        }

        public void onClick(View view) {
            this.f13341a.f13344c.mo3668a(Element.SUBMIT);
            this.f13341a.f13343b.mo3685a(this.f13341a.f13342a);
            this.f13341a.f13342a.finish();
        }
    }

    public C2928o(Activity activity) {
        this(activity, new C2931s(), new C2934v(aa.a().i()));
    }

    public C2928o(Activity activity, C2930q c2930q, ao aoVar) {
        this.f13342a = activity;
        this.f13343b = c2930q;
        this.f13344c = aoVar;
    }

    /* renamed from: a */
    public void m14238a() {
        this.f13344c.mo3666a();
        m14241b();
        m14243c();
    }

    /* renamed from: b */
    protected void m14241b() {
        this.f13342a.setContentView(C2876R.layout.dgts__activity_contacts);
    }

    /* renamed from: c */
    protected void m14243c() {
        Button button = (Button) this.f13342a.findViewById(C2876R.id.dgts__okay);
        TextView textView = (TextView) this.f13342a.findViewById(C2876R.id.dgts__upload_contacts);
        m14239a((Button) this.f13342a.findViewById(C2876R.id.dgts__not_now));
        m14242b(button);
        m14240a(textView);
    }

    /* renamed from: a */
    protected void m14240a(TextView textView) {
        textView.setText(m14245e());
    }

    /* renamed from: d */
    protected String m14244d() {
        return this.f13342a.getApplicationInfo().loadLabel(this.f13342a.getPackageManager()).toString();
    }

    /* renamed from: e */
    protected String m14245e() {
        return this.f13342a.getString(C2876R.string.dgts__upload_contacts, new Object[]{m14244d()});
    }

    /* renamed from: a */
    protected void m14239a(Button button) {
        button.setOnClickListener(new C29261(this));
    }

    /* renamed from: b */
    protected void m14242b(Button button) {
        button.setOnClickListener(new C29272(this));
    }
}
