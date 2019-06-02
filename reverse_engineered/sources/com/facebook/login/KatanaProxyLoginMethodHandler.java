package com.facebook.login;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.internal.C3035o;
import com.facebook.login.LoginClient.Request;

class KatanaProxyLoginMethodHandler extends NativeAppLoginMethodHandler {
    public static final Creator<KatanaProxyLoginMethodHandler> CREATOR = new C30721();

    /* renamed from: com.facebook.login.KatanaProxyLoginMethodHandler$1 */
    static class C30721 implements Creator {
        C30721() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m14909a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m14910a(i);
        }

        /* renamed from: a */
        public KatanaProxyLoginMethodHandler m14909a(Parcel parcel) {
            return new KatanaProxyLoginMethodHandler(parcel);
        }

        /* renamed from: a */
        public KatanaProxyLoginMethodHandler[] m14910a(int i) {
            return new KatanaProxyLoginMethodHandler[i];
        }
    }

    KatanaProxyLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    /* renamed from: a */
    String mo3699a() {
        return "katana_proxy_auth";
    }

    /* renamed from: a */
    boolean mo3701a(Request request) {
        String m = LoginClient.m14947m();
        Intent b = C3035o.m14682b(this.b.m14958b(), request.m14926d(), request.m14922a(), m, request.m14928f(), request.m14930h(), request.m14925c(), m14826a(request.m14927e()));
        m14828a("e2e", m);
        return m14894a(b, LoginClient.m14945d());
    }

    KatanaProxyLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
