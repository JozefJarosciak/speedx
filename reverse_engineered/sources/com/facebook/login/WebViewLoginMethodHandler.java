package com.facebook.login;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.C3019u;
import com.facebook.internal.C3019u.C2993c;
import com.facebook.internal.C3019u.C3054a;
import com.facebook.internal.FacebookDialogFragment;
import com.facebook.login.LoginClient.Request;

class WebViewLoginMethodHandler extends WebLoginMethodHandler {
    public static final Creator<WebViewLoginMethodHandler> CREATOR = new C30812();
    /* renamed from: c */
    private C3019u f13735c;
    /* renamed from: d */
    private String f13736d;

    /* renamed from: com.facebook.login.WebViewLoginMethodHandler$2 */
    static class C30812 implements Creator {
        C30812() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m14981a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m14982a(i);
        }

        /* renamed from: a */
        public WebViewLoginMethodHandler m14981a(Parcel parcel) {
            return new WebViewLoginMethodHandler(parcel);
        }

        /* renamed from: a */
        public WebViewLoginMethodHandler[] m14982a(int i) {
            return new WebViewLoginMethodHandler[i];
        }
    }

    /* renamed from: com.facebook.login.WebViewLoginMethodHandler$a */
    static class C3082a extends C3054a {
        /* renamed from: a */
        private String f13733a;
        /* renamed from: b */
        private boolean f13734b;

        public C3082a(Context context, String str, Bundle bundle) {
            super(context, str, "oauth", bundle);
        }

        /* renamed from: a */
        public C3082a m14984a(String str) {
            this.f13733a = str;
            return this;
        }

        /* renamed from: a */
        public C3082a m14985a(boolean z) {
            this.f13734b = z;
            return this;
        }

        /* renamed from: a */
        public C3019u mo3711a() {
            Bundle e = m14807e();
            e.putString("redirect_uri", "fbconnect://success");
            e.putString("client_id", m14804b());
            e.putString("e2e", this.f13733a);
            e.putString("response_type", "token,signed_request");
            e.putString("return_scopes", "true");
            e.putString("auth_type", "rerequest");
            return new C3019u(m14805c(), "oauth", e, m14806d(), m14808f());
        }
    }

    WebViewLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    /* renamed from: a */
    String mo3699a() {
        return "web_view";
    }

    AccessTokenSource f_() {
        return AccessTokenSource.WEB_VIEW;
    }

    /* renamed from: d */
    boolean mo3712d() {
        return true;
    }

    /* renamed from: b */
    void mo3707b() {
        if (this.f13735c != null) {
            this.f13735c.cancel();
            this.f13735c = null;
        }
    }

    /* renamed from: a */
    boolean mo3701a(final Request request) {
        Bundle b = m14840b(request);
        C2993c c30801 = new C2993c(this) {
            /* renamed from: b */
            final /* synthetic */ WebViewLoginMethodHandler f13732b;

            /* renamed from: a */
            public void mo3692a(Bundle bundle, FacebookException facebookException) {
                this.f13732b.m14989b(request, bundle, facebookException);
            }
        };
        this.f13736d = LoginClient.m14947m();
        m14828a("e2e", this.f13736d);
        Context b2 = this.b.m14958b();
        this.f13735c = new C3082a(b2, request.m14926d(), b).m14984a(this.f13736d).m14985a(request.m14928f()).m14802a(c30801).mo3711a();
        FacebookDialogFragment facebookDialogFragment = new FacebookDialogFragment();
        facebookDialogFragment.setRetainInstance(true);
        facebookDialogFragment.m14530a(this.f13735c);
        facebookDialogFragment.show(b2.getSupportFragmentManager(), "FacebookDialogFragment");
        return true;
    }

    /* renamed from: b */
    void m14989b(Request request, Bundle bundle, FacebookException facebookException) {
        super.m14839a(request, bundle, facebookException);
    }

    WebViewLoginMethodHandler(Parcel parcel) {
        super(parcel);
        this.f13736d = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.f13736d);
    }
}
