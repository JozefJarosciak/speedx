package com.beastbikes.android.authentication.p055a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import org.json.JSONObject;

/* compiled from: AuthenticationServiceStub */
/* renamed from: com.beastbikes.android.authentication.a.b */
public interface C1375b extends C1600d {
    @C1447d(a = "/signOut")
    /* renamed from: a */
    JSONObject m5272a();

    @C1447d(a = "/reset")
    /* renamed from: a */
    JSONObject m5273a(@C1444a(a = "username") String str);

    @C1447d(a = "/account/check")
    /* renamed from: a */
    JSONObject m5274a(@C1444a(a = "account") String str, @C1444a(a = "type") int i);

    @C1447d(a = "/modify")
    /* renamed from: a */
    JSONObject m5275a(@C1444a(a = "account") String str, @C1444a(a = "type") int i, @C1444a(a = "vcode") String str2);

    @C1447d(a = "/vcode/check")
    /* renamed from: a */
    JSONObject m5276a(@C1444a(a = "account") String str, @C1444a(a = "vcode") String str2);

    @C1447d(a = "/bind")
    /* renamed from: a */
    JSONObject m5277a(@C1444a(a = "auth_key") String str, @C1444a(a = "auth_token") String str2, @C1444a(a = "auth_type") int i, @C1444a(a = "vcode") int i2, @C1444a(a = "third_nick") String str3);

    @C1447d(a = "/sign_in")
    /* renamed from: a */
    JSONObject m5278a(@C1444a(a = "username") String str, @C1444a(a = "password") String str2, @C1444a(a = "type") int i, @C1444a(a = "third_nick") String str3);

    @C1447d(a = "/resetPasswordByMobile")
    /* renamed from: a */
    JSONObject m5279a(@C1444a(a = "mobilephone") String str, @C1444a(a = "vcode") String str2, @C1444a(a = "password") String str3);

    @C1447d(a = "/sign_up")
    /* renamed from: a */
    JSONObject m5280a(@C1444a(a = "nickname") String str, @C1444a(a = "username") String str2, @C1444a(a = "password") String str3, @C1444a(a = "vcode") String str4, @C1444a(a = "auth_key") String str5, @C1444a(a = "auth_token") String str6, @C1444a(a = "auth_type") int i, @C1444a(a = "third_nick") String str7);

    @C1447d(a = "/bindStatus")
    /* renamed from: b */
    JSONObject m5281b();

    @C1447d(a = "/vcode/send")
    /* renamed from: b */
    JSONObject m5282b(@C1444a(a = "account") String str, @C1444a(a = "type") String str2);

    @C1447d(a = "/unbind")
    /* renamed from: b */
    JSONObject m5283b(@C1444a(a = "auth_key") String str, @C1444a(a = "auth_token") String str2, @C1444a(a = "auth_type") int i, @C1444a(a = "vcode") String str3);

    @C1447d(a = "/auth/token")
    /* renamed from: c */
    JSONObject m5284c();

    @C1447d(a = "/password/change")
    /* renamed from: c */
    JSONObject m5285c(@C1444a(a = "new") String str, @C1444a(a = "old") String str2);

    @C1447d(a = "/password/reset")
    /* renamed from: c */
    JSONObject m5286c(@C1444a(a = "account") String str, @C1444a(a = "new") String str2, @C1444a(a = "type") int i, @C1444a(a = "vcode") String str3);
}
