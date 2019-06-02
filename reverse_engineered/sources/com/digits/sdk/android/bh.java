package com.digits.sdk.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.ResultReceiver;
import com.twitter.sdk.android.core.C4580e;
import com.twitter.sdk.android.core.C4645j;
import com.twitter.sdk.android.core.TwitterException;
import io.fabric.sdk.android.C1520c;

/* compiled from: LoginOrSignupComposer */
abstract class bh {
    /* renamed from: a */
    protected final C4580e<C1471z> f13257a = new C29062(this);
    /* renamed from: b */
    protected final C4580e<C1470g> f13258b = new C29051(this);
    /* renamed from: c */
    final ag f13259c;
    /* renamed from: d */
    final String f13260d;
    /* renamed from: e */
    final Verification f13261e;
    /* renamed from: f */
    final boolean f13262f;
    /* renamed from: g */
    final ResultReceiver f13263g;
    /* renamed from: h */
    final C2877a f13264h;
    /* renamed from: i */
    private final Context f13265i;

    /* compiled from: LoginOrSignupComposer */
    /* renamed from: com.digits.sdk.android.bh$1 */
    class C29051 extends C4580e<C1470g> {
        /* renamed from: a */
        final /* synthetic */ bh f13255a;

        C29051(bh bhVar) {
            this.f13255a = bhVar;
        }

        /* renamed from: a */
        public void m14082a(C4645j<C1470g> c4645j) {
            this.f13255a.mo3675a(this.f13255a.m14088a((C1470g) c4645j.f16364a));
        }

        /* renamed from: a */
        public void m14081a(TwitterException twitterException) {
            DigitsException a = this.f13255a.m14091a(twitterException);
            C1520c.h().d("Digits", "HTTP Error: " + twitterException.getMessage() + ", API Error: " + "" + a.getErrorCode() + ", User Message: " + a.getMessage());
            if (a instanceof CouldNotAuthenticateException) {
                this.f13255a.m14093b();
            } else {
                this.f13255a.mo3676a(a);
            }
        }
    }

    /* compiled from: LoginOrSignupComposer */
    /* renamed from: com.digits.sdk.android.bh$2 */
    class C29062 extends C4580e<C1471z> {
        /* renamed from: a */
        final /* synthetic */ bh f13256a;

        C29062(bh bhVar) {
            this.f13256a = bhVar;
        }

        /* renamed from: a */
        public void m14084a(C4645j<C1471z> c4645j) {
            this.f13256a.mo3675a(this.f13256a.m14089a((C1471z) c4645j.f16364a));
        }

        /* renamed from: a */
        public void m14083a(TwitterException twitterException) {
            DigitsException a = this.f13256a.m14091a(twitterException);
            C1520c.h().d("Digits", "HTTP Error: " + twitterException.getMessage() + ", API Error: " + "" + a.getErrorCode() + ", User Message: " + a.getMessage());
            this.f13256a.mo3676a(a);
        }
    }

    /* renamed from: a */
    public abstract void mo3675a(Intent intent);

    /* renamed from: a */
    public abstract void mo3676a(DigitsException digitsException);

    bh(Context context, ag agVar, String str, Verification verification, boolean z, ResultReceiver resultReceiver, C2877a c2877a) {
        this.f13265i = context;
        this.f13259c = agVar;
        this.f13260d = str;
        this.f13261e = verification;
        this.f13262f = z;
        this.f13263g = resultReceiver;
        this.f13264h = c2877a;
    }

    /* renamed from: a */
    public void m14095a() {
        m14094c();
    }

    /* renamed from: b */
    private void m14093b() {
        this.f13259c.m13936b(this.f13260d, this.f13261e, this.f13257a);
    }

    /* renamed from: c */
    private void m14094c() {
        this.f13259c.m13933a(this.f13260d, this.f13261e, this.f13258b);
    }

    /* renamed from: a */
    private Intent m14089a(C1471z c1471z) {
        return m14085a(c1471z.f6896b, c1471z.f6895a, this.f13264h.mo3678b());
    }

    /* renamed from: a */
    private Intent m14088a(C1470g c1470g) {
        Intent a = m14085a(c1470g.f6894d, c1470g.f6891a, this.f13264h.mo3679c());
        a.putExtra("request_id", c1470g.f6892b);
        a.putExtra("user_id", c1470g.f6893c);
        return a;
    }

    /* renamed from: a */
    private Intent m14085a(AuthConfig authConfig, String str, Class<? extends Activity> cls) {
        boolean z = authConfig == null ? this.f13262f : authConfig.isEmailEnabled && this.f13262f;
        if (str == null) {
            str = this.f13260d;
        }
        Intent intent = new Intent(this.f13265i, cls);
        intent.putExtra("receiver", this.f13263g);
        intent.putExtra("phone_number", str);
        intent.putExtra("auth_config", authConfig);
        intent.putExtra("email_enabled", z);
        return intent;
    }

    /* renamed from: a */
    private DigitsException m14091a(TwitterException twitterException) {
        return DigitsException.m13853a(new bl(this.f13265i.getResources()), twitterException);
    }
}
