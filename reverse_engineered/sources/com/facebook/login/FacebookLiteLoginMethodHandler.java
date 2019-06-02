package com.facebook.login;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.internal.C3035o;
import com.facebook.login.LoginClient.Request;

class FacebookLiteLoginMethodHandler extends NativeAppLoginMethodHandler {
    public static final Creator<FacebookLiteLoginMethodHandler> CREATOR = new C30681();

    /* renamed from: com.facebook.login.FacebookLiteLoginMethodHandler$1 */
    static class C30681 implements Creator {
        C30681() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m14887a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m14888a(i);
        }

        /* renamed from: a */
        public FacebookLiteLoginMethodHandler m14887a(Parcel parcel) {
            return new FacebookLiteLoginMethodHandler(parcel);
        }

        /* renamed from: a */
        public FacebookLiteLoginMethodHandler[] m14888a(int i) {
            return new FacebookLiteLoginMethodHandler[i];
        }
    }

    FacebookLiteLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    /* renamed from: a */
    String mo3699a() {
        return "fb_lite_login";
    }

    /* renamed from: a */
    boolean mo3701a(Request request) {
        String m = LoginClient.m14947m();
        Intent a = C3035o.m14672a(this.b.m14958b(), request.m14926d(), request.m14922a(), m, request.m14928f(), request.m14930h(), request.m14925c(), m14826a(request.m14927e()));
        m14828a("e2e", m);
        return m14894a(a, LoginClient.m14945d());
    }

    FacebookLiteLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
