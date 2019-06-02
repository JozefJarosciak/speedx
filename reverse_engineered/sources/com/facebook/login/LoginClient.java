package com.facebook.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import com.alipay.sdk.cons.C0844a;
import com.facebook.AccessToken;
import com.facebook.C2956R;
import com.facebook.FacebookException;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3049t;
import com.facebook.internal.CallbackManagerImpl.RequestCodeOffset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

class LoginClient implements Parcelable {
    public static final Creator<LoginClient> CREATOR = new C30731();
    /* renamed from: a */
    LoginMethodHandler[] f13714a;
    /* renamed from: b */
    int f13715b = -1;
    /* renamed from: c */
    Fragment f13716c;
    /* renamed from: d */
    C3077b f13717d;
    /* renamed from: e */
    C3076a f13718e;
    /* renamed from: f */
    boolean f13719f;
    /* renamed from: g */
    Request f13720g;
    /* renamed from: h */
    Map<String, String> f13721h;
    /* renamed from: i */
    private C3084b f13722i;

    /* renamed from: com.facebook.login.LoginClient$1 */
    static class C30731 implements Creator {
        C30731() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m14918a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m14919a(i);
        }

        /* renamed from: a */
        public LoginClient m14918a(Parcel parcel) {
            return new LoginClient(parcel);
        }

        /* renamed from: a */
        public LoginClient[] m14919a(int i) {
            return new LoginClient[i];
        }
    }

    public static class Request implements Parcelable {
        public static final Creator<Request> CREATOR = new C30741();
        /* renamed from: a */
        private final LoginBehavior f13701a;
        /* renamed from: b */
        private Set<String> f13702b;
        /* renamed from: c */
        private final DefaultAudience f13703c;
        /* renamed from: d */
        private final String f13704d;
        /* renamed from: e */
        private final String f13705e;
        /* renamed from: f */
        private boolean f13706f;
        /* renamed from: g */
        private String f13707g;

        /* renamed from: com.facebook.login.LoginClient$Request$1 */
        static class C30741 implements Creator {
            C30741() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m14920a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m14921a(i);
            }

            /* renamed from: a */
            public Request m14920a(Parcel parcel) {
                return new Request(parcel);
            }

            /* renamed from: a */
            public Request[] m14921a(int i) {
                return new Request[i];
            }
        }

        /* renamed from: a */
        Set<String> m14922a() {
            return this.f13702b;
        }

        /* renamed from: a */
        void m14923a(Set<String> set) {
            C3049t.m14790a((Object) set, "permissions");
            this.f13702b = set;
        }

        /* renamed from: b */
        LoginBehavior m14924b() {
            return this.f13701a;
        }

        /* renamed from: c */
        DefaultAudience m14925c() {
            return this.f13703c;
        }

        /* renamed from: d */
        String m14926d() {
            return this.f13704d;
        }

        /* renamed from: e */
        String m14927e() {
            return this.f13705e;
        }

        /* renamed from: f */
        boolean m14928f() {
            return this.f13706f;
        }

        /* renamed from: g */
        String m14929g() {
            return this.f13707g;
        }

        /* renamed from: h */
        boolean m14930h() {
            for (String a : this.f13702b) {
                if (C3085c.m14999a(a)) {
                    return true;
                }
            }
            return false;
        }

        private Request(Parcel parcel) {
            boolean z;
            DefaultAudience defaultAudience = null;
            this.f13706f = false;
            String readString = parcel.readString();
            this.f13701a = readString != null ? LoginBehavior.valueOf(readString) : null;
            Collection arrayList = new ArrayList();
            parcel.readStringList(arrayList);
            this.f13702b = new HashSet(arrayList);
            readString = parcel.readString();
            if (readString != null) {
                defaultAudience = DefaultAudience.valueOf(readString);
            }
            this.f13703c = defaultAudience;
            this.f13704d = parcel.readString();
            this.f13705e = parcel.readString();
            if (parcel.readByte() != (byte) 0) {
                z = true;
            } else {
                z = false;
            }
            this.f13706f = z;
            this.f13707g = parcel.readString();
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            String str = null;
            parcel.writeString(this.f13701a != null ? this.f13701a.name() : null);
            parcel.writeStringList(new ArrayList(this.f13702b));
            if (this.f13703c != null) {
                str = this.f13703c.name();
            }
            parcel.writeString(str);
            parcel.writeString(this.f13704d);
            parcel.writeString(this.f13705e);
            parcel.writeByte((byte) (this.f13706f ? 1 : 0));
            parcel.writeString(this.f13707g);
        }
    }

    public static class Result implements Parcelable {
        public static final Creator<Result> CREATOR = new C30751();
        /* renamed from: a */
        final Code f13708a;
        /* renamed from: b */
        final AccessToken f13709b;
        /* renamed from: c */
        final String f13710c;
        /* renamed from: d */
        final String f13711d;
        /* renamed from: e */
        final Request f13712e;
        /* renamed from: f */
        public Map<String, String> f13713f;

        /* renamed from: com.facebook.login.LoginClient$Result$1 */
        static class C30751 implements Creator {
            C30751() {
            }

            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return m14931a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return m14932a(i);
            }

            /* renamed from: a */
            public Result m14931a(Parcel parcel) {
                return new Result(parcel);
            }

            /* renamed from: a */
            public Result[] m14932a(int i) {
                return new Result[i];
            }
        }

        enum Code {
            SUCCESS("success"),
            CANCEL("cancel"),
            ERROR("error");
            
            private final String loggingValue;

            private Code(String str) {
                this.loggingValue = str;
            }

            /* renamed from: a */
            String m14933a() {
                return this.loggingValue;
            }
        }

        Result(Request request, Code code, AccessToken accessToken, String str, String str2) {
            C3049t.m14790a((Object) code, "code");
            this.f13712e = request;
            this.f13709b = accessToken;
            this.f13710c = str;
            this.f13708a = code;
            this.f13711d = str2;
        }

        /* renamed from: a */
        static Result m14934a(Request request, AccessToken accessToken) {
            return new Result(request, Code.SUCCESS, accessToken, null, null);
        }

        /* renamed from: a */
        static Result m14935a(Request request, String str) {
            return new Result(request, Code.CANCEL, null, str, null);
        }

        /* renamed from: a */
        static Result m14936a(Request request, String str, String str2) {
            return m14937a(request, str, str2, null);
        }

        /* renamed from: a */
        static Result m14937a(Request request, String str, String str2, String str3) {
            return new Result(request, Code.ERROR, null, TextUtils.join(": ", C3048s.m14766b(str, str2)), str3);
        }

        private Result(Parcel parcel) {
            this.f13708a = Code.valueOf(parcel.readString());
            this.f13709b = (AccessToken) parcel.readParcelable(AccessToken.class.getClassLoader());
            this.f13710c = parcel.readString();
            this.f13711d = parcel.readString();
            this.f13712e = (Request) parcel.readParcelable(Request.class.getClassLoader());
            this.f13713f = C3048s.m14744a(parcel);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.f13708a.name());
            parcel.writeParcelable(this.f13709b, i);
            parcel.writeString(this.f13710c);
            parcel.writeString(this.f13711d);
            parcel.writeParcelable(this.f13712e, i);
            C3048s.m14749a(parcel, this.f13713f);
        }
    }

    /* renamed from: com.facebook.login.LoginClient$a */
    interface C3076a {
        /* renamed from: a */
        void mo3709a();

        /* renamed from: b */
        void mo3710b();
    }

    /* renamed from: com.facebook.login.LoginClient$b */
    public interface C3077b {
        /* renamed from: a */
        void mo3708a(Result result);
    }

    public LoginClient(Fragment fragment) {
        this.f13716c = fragment;
    }

    /* renamed from: a */
    public Fragment m14951a() {
        return this.f13716c;
    }

    /* renamed from: a */
    void m14952a(Fragment fragment) {
        if (this.f13716c != null) {
            throw new FacebookException("Can't set fragment once it is already set.");
        }
        this.f13716c = fragment;
    }

    /* renamed from: b */
    FragmentActivity m14958b() {
        return this.f13716c.getActivity();
    }

    /* renamed from: c */
    public Request m14961c() {
        return this.f13720g;
    }

    /* renamed from: d */
    public static int m14945d() {
        return RequestCodeOffset.Login.toRequestCode();
    }

    /* renamed from: a */
    void m14953a(Request request) {
        if (!m14963e()) {
            m14959b(request);
        }
    }

    /* renamed from: b */
    void m14959b(Request request) {
        if (request != null) {
            if (this.f13720g != null) {
                throw new FacebookException("Attempted to authorize while a request is pending.");
            } else if (AccessToken.m14270a() == null || m14966h()) {
                this.f13720g = request;
                this.f13714a = m14944c(request);
                m14967i();
            }
        }
    }

    /* renamed from: e */
    boolean m14963e() {
        return this.f13720g != null && this.f13715b >= 0;
    }

    /* renamed from: f */
    void m14964f() {
        if (this.f13715b >= 0) {
            m14965g().mo3707b();
        }
    }

    /* renamed from: g */
    LoginMethodHandler m14965g() {
        if (this.f13715b >= 0) {
            return this.f13714a[this.f13715b];
        }
        return null;
    }

    /* renamed from: a */
    public boolean m14957a(int i, int i2, Intent intent) {
        if (this.f13720g != null) {
            return m14965g().mo3705a(i, i2, intent);
        }
        return false;
    }

    /* renamed from: c */
    private LoginMethodHandler[] m14944c(Request request) {
        ArrayList arrayList = new ArrayList();
        LoginBehavior b = request.m14924b();
        if (b.m14913a()) {
            arrayList.add(new GetTokenLoginMethodHandler(this));
            arrayList.add(new KatanaProxyLoginMethodHandler(this));
        }
        if (b.m14917e()) {
            arrayList.add(new FacebookLiteLoginMethodHandler(this));
        }
        if (b.m14916d()) {
            arrayList.add(new CustomTabLoginMethodHandler(this));
        }
        if (b.m14914b()) {
            arrayList.add(new WebViewLoginMethodHandler(this));
        }
        if (b.m14915c()) {
            arrayList.add(new DeviceAuthMethodHandler(this));
        }
        LoginMethodHandler[] loginMethodHandlerArr = new LoginMethodHandler[arrayList.size()];
        arrayList.toArray(loginMethodHandlerArr);
        return loginMethodHandlerArr;
    }

    /* renamed from: h */
    boolean m14966h() {
        if (this.f13719f) {
            return true;
        }
        if (m14950a("android.permission.INTERNET") != 0) {
            Activity b = m14958b();
            m14960b(Result.m14936a(this.f13720g, b.getString(C2956R.string.com_facebook_internet_permission_error_title), b.getString(C2956R.string.com_facebook_internet_permission_error_message)));
            return false;
        }
        this.f13719f = true;
        return true;
    }

    /* renamed from: i */
    void m14967i() {
        if (this.f13715b >= 0) {
            m14942a(m14965g().mo3699a(), "skipped", null, null, m14965g().f13669a);
        }
        while (this.f13714a != null && this.f13715b < this.f13714a.length - 1) {
            this.f13715b++;
            if (m14968j()) {
                return;
            }
        }
        if (this.f13720g != null) {
            m14948n();
        }
    }

    /* renamed from: n */
    private void m14948n() {
        m14960b(Result.m14936a(this.f13720g, "Login attempt failed.", null));
    }

    /* renamed from: a */
    private void m14943a(String str, String str2, boolean z) {
        if (this.f13721h == null) {
            this.f13721h = new HashMap();
        }
        if (this.f13721h.containsKey(str) && z) {
            str2 = ((String) this.f13721h.get(str)) + "," + str2;
        }
        this.f13721h.put(str, str2);
    }

    /* renamed from: j */
    boolean m14968j() {
        boolean z = false;
        LoginMethodHandler g = m14965g();
        if (!g.mo3712d() || m14966h()) {
            z = g.mo3701a(this.f13720g);
            if (z) {
                m14949o().m14994a(this.f13720g.m14927e(), g.mo3699a());
            } else {
                m14943a("not_tried", g.mo3699a(), true);
            }
        } else {
            m14943a("no_internet_permission", C0844a.f2048d, false);
        }
        return z;
    }

    /* renamed from: a */
    void m14954a(Result result) {
        if (result.f13709b == null || AccessToken.m14270a() == null) {
            m14960b(result);
        } else {
            m14962c(result);
        }
    }

    /* renamed from: b */
    void m14960b(Result result) {
        LoginMethodHandler g = m14965g();
        if (g != null) {
            m14941a(g.mo3699a(), result, g.f13669a);
        }
        if (this.f13721h != null) {
            result.f13713f = this.f13721h;
        }
        this.f13714a = null;
        this.f13715b = -1;
        this.f13720g = null;
        this.f13721h = null;
        m14946d(result);
    }

    /* renamed from: a */
    void m14956a(C3077b c3077b) {
        this.f13717d = c3077b;
    }

    /* renamed from: a */
    void m14955a(C3076a c3076a) {
        this.f13718e = c3076a;
    }

    /* renamed from: a */
    int m14950a(String str) {
        return m14958b().checkCallingOrSelfPermission(str);
    }

    /* renamed from: c */
    void m14962c(Result result) {
        if (result.f13709b == null) {
            throw new FacebookException("Can't validate without a token");
        }
        Result a;
        AccessToken a2 = AccessToken.m14270a();
        AccessToken accessToken = result.f13709b;
        if (!(a2 == null || accessToken == null)) {
            try {
                if (a2.m14284i().equals(accessToken.m14284i())) {
                    a = Result.m14934a(this.f13720g, result.f13709b);
                    m14960b(a);
                }
            } catch (Exception e) {
                m14960b(Result.m14936a(this.f13720g, "Caught exception", e.getMessage()));
                return;
            }
        }
        a = Result.m14936a(this.f13720g, "User logged in as different Facebook user.", null);
        m14960b(a);
    }

    /* renamed from: o */
    private C3084b m14949o() {
        if (this.f13722i == null || !this.f13722i.m14993a().equals(this.f13720g.m14926d())) {
            this.f13722i = new C3084b(m14958b(), this.f13720g.m14926d());
        }
        return this.f13722i;
    }

    /* renamed from: d */
    private void m14946d(Result result) {
        if (this.f13717d != null) {
            this.f13717d.mo3708a(result);
        }
    }

    /* renamed from: k */
    void m14969k() {
        if (this.f13718e != null) {
            this.f13718e.mo3709a();
        }
    }

    /* renamed from: l */
    void m14970l() {
        if (this.f13718e != null) {
            this.f13718e.mo3710b();
        }
    }

    /* renamed from: a */
    private void m14941a(String str, Result result, Map<String, String> map) {
        m14942a(str, result.f13708a.m14933a(), result.f13710c, result.f13711d, map);
    }

    /* renamed from: a */
    private void m14942a(String str, String str2, String str3, String str4, Map<String, String> map) {
        if (this.f13720g == null) {
            m14949o().m14995a("fb_mobile_login_method_complete", "Unexpected call to logCompleteLogin with null pendingAuthorizationRequest.", str);
        } else {
            m14949o().m14996a(this.f13720g.m14927e(), str, str2, str3, str4, map);
        }
    }

    /* renamed from: m */
    static String m14947m() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("init", System.currentTimeMillis());
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    public LoginClient(Parcel parcel) {
        Parcelable[] readParcelableArray = parcel.readParcelableArray(LoginMethodHandler.class.getClassLoader());
        this.f13714a = new LoginMethodHandler[readParcelableArray.length];
        for (int i = 0; i < readParcelableArray.length; i++) {
            this.f13714a[i] = (LoginMethodHandler) readParcelableArray[i];
            this.f13714a[i].m14827a(this);
        }
        this.f13715b = parcel.readInt();
        this.f13720g = (Request) parcel.readParcelable(Request.class.getClassLoader());
        this.f13721h = C3048s.m14744a(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelableArray(this.f13714a, i);
        parcel.writeInt(this.f13715b);
        parcel.writeParcelable(this.f13720g, i);
        C3048s.m14749a(parcel, this.f13721h);
    }
}
