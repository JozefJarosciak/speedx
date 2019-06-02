package com.facebook.login;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.C3038p.C3037a;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3048s.C2953c;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;
import java.util.ArrayList;
import java.util.HashSet;
import org.json.JSONException;
import org.json.JSONObject;

class GetTokenLoginMethodHandler extends LoginMethodHandler {
    public static final Creator<GetTokenLoginMethodHandler> CREATOR = new C30713();
    /* renamed from: c */
    private C3083a f13700c;

    /* renamed from: com.facebook.login.GetTokenLoginMethodHandler$3 */
    static class C30713 implements Creator {
        C30713() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m14901a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m14902a(i);
        }

        /* renamed from: a */
        public GetTokenLoginMethodHandler m14901a(Parcel parcel) {
            return new GetTokenLoginMethodHandler(parcel);
        }

        /* renamed from: a */
        public GetTokenLoginMethodHandler[] m14902a(int i) {
            return new GetTokenLoginMethodHandler[i];
        }
    }

    GetTokenLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    /* renamed from: a */
    String mo3699a() {
        return "get_token";
    }

    /* renamed from: b */
    void mo3707b() {
        if (this.f13700c != null) {
            this.f13700c.m14701b();
            this.f13700c.m14699a(null);
            this.f13700c = null;
        }
    }

    /* renamed from: a */
    boolean mo3701a(final Request request) {
        this.f13700c = new C3083a(this.b.m14958b(), request.m14926d());
        if (!this.f13700c.m14700a()) {
            return false;
        }
        this.b.m14969k();
        this.f13700c.m14699a(new C3037a(this) {
            /* renamed from: b */
            final /* synthetic */ GetTokenLoginMethodHandler f13696b;

            /* renamed from: a */
            public void mo3706a(Bundle bundle) {
                this.f13696b.m14904a(request, bundle);
            }
        });
        return true;
    }

    /* renamed from: a */
    void m14904a(Request request, Bundle bundle) {
        if (this.f13700c != null) {
            this.f13700c.m14699a(null);
        }
        this.f13700c = null;
        this.b.m14970l();
        if (bundle != null) {
            ArrayList stringArrayList = bundle.getStringArrayList("com.facebook.platform.extra.PERMISSIONS");
            Object<String> a = request.m14922a();
            if (stringArrayList == null || !(a == null || stringArrayList.containsAll(a))) {
                Object hashSet = new HashSet();
                for (String str : a) {
                    if (!stringArrayList.contains(str)) {
                        hashSet.add(str);
                    }
                }
                if (!hashSet.isEmpty()) {
                    m14828a("new_permissions", TextUtils.join(",", hashSet));
                }
                request.m14923a(hashSet);
            } else {
                m14908c(request, bundle);
                return;
            }
        }
        this.b.m14967i();
    }

    /* renamed from: b */
    void m14907b(Request request, Bundle bundle) {
        this.b.m14954a(Result.m14934a(this.b.m14961c(), LoginMethodHandler.m14822a(bundle, AccessTokenSource.FACEBOOK_APPLICATION_SERVICE, request.m14926d())));
    }

    /* renamed from: c */
    void m14908c(final Request request, final Bundle bundle) {
        String string = bundle.getString("com.facebook.platform.extra.USER_ID");
        if (string == null || string.isEmpty()) {
            this.b.m14969k();
            C3048s.m14752a(bundle.getString("com.facebook.platform.extra.ACCESS_TOKEN"), new C2953c(this) {
                /* renamed from: c */
                final /* synthetic */ GetTokenLoginMethodHandler f13699c;

                /* renamed from: a */
                public void mo3690a(JSONObject jSONObject) {
                    try {
                        bundle.putString("com.facebook.platform.extra.USER_ID", jSONObject.getString("id"));
                        this.f13699c.m14907b(request, bundle);
                    } catch (JSONException e) {
                        this.f13699c.b.m14960b(Result.m14936a(this.f13699c.b.m14961c(), "Caught exception", e.getMessage()));
                    }
                }

                /* renamed from: a */
                public void mo3689a(FacebookException facebookException) {
                    this.f13699c.b.m14960b(Result.m14936a(this.f13699c.b.m14961c(), "Caught exception", facebookException.getMessage()));
                }
            });
            return;
        }
        m14907b(request, bundle);
    }

    GetTokenLoginMethodHandler(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
