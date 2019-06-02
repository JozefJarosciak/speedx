package com.facebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.alipay.sdk.packet.C0861d;
import com.facebook.AccessToken.C2939a;
import com.facebook.C2986e.C2980a;
import com.facebook.GraphRequest.C2942b;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3049t;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AccessTokenManager */
/* renamed from: com.facebook.b */
final class C2983b {
    /* renamed from: a */
    private static volatile C2983b f13488a;
    /* renamed from: b */
    private final LocalBroadcastManager f13489b;
    /* renamed from: c */
    private final C2958a f13490c;
    /* renamed from: d */
    private AccessToken f13491d;
    /* renamed from: e */
    private AtomicBoolean f13492e = new AtomicBoolean(false);
    /* renamed from: f */
    private Date f13493f = new Date(0);

    /* compiled from: AccessTokenManager */
    /* renamed from: com.facebook.b$a */
    private static class C2982a {
        /* renamed from: a */
        public String f13486a;
        /* renamed from: b */
        public int f13487b;

        private C2982a() {
        }
    }

    C2983b(LocalBroadcastManager localBroadcastManager, C2958a c2958a) {
        C3049t.m14790a((Object) localBroadcastManager, "localBroadcastManager");
        C3049t.m14790a((Object) c2958a, "accessTokenCache");
        this.f13489b = localBroadcastManager;
        this.f13490c = c2958a;
    }

    /* renamed from: a */
    static C2983b m14457a() {
        if (f13488a == null) {
            synchronized (C2983b.class) {
                if (f13488a == null) {
                    f13488a = new C2983b(LocalBroadcastManager.getInstance(C1472c.f()), new C2958a());
                }
            }
        }
        return f13488a;
    }

    /* renamed from: b */
    AccessToken m14467b() {
        return this.f13491d;
    }

    /* renamed from: c */
    boolean m14468c() {
        AccessToken a = this.f13490c.m14404a();
        if (a == null) {
            return false;
        }
        m14460a(a, false);
        return true;
    }

    /* renamed from: a */
    void m14466a(AccessToken accessToken) {
        m14460a(accessToken, true);
    }

    /* renamed from: a */
    private void m14460a(AccessToken accessToken, boolean z) {
        AccessToken accessToken2 = this.f13491d;
        this.f13491d = accessToken;
        this.f13492e.set(false);
        this.f13493f = new Date(0);
        if (z) {
            if (accessToken != null) {
                this.f13490c.m14405a(accessToken);
            } else {
                this.f13490c.m14406b();
                C3048s.m14769b(C1472c.f());
            }
        }
        if (!C3048s.m14760a((Object) accessToken2, (Object) accessToken)) {
            m14459a(accessToken2, accessToken);
        }
    }

    /* renamed from: a */
    private void m14459a(AccessToken accessToken, AccessToken accessToken2) {
        Intent intent = new Intent("com.facebook.sdk.ACTION_CURRENT_ACCESS_TOKEN_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_ACCESS_TOKEN", accessToken);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_ACCESS_TOKEN", accessToken2);
        this.f13489b.sendBroadcast(intent);
    }

    /* renamed from: d */
    void m14469d() {
        if (m14464e()) {
            m14465a(null);
        }
    }

    /* renamed from: e */
    private boolean m14464e() {
        if (this.f13491d == null) {
            return false;
        }
        Long valueOf = Long.valueOf(new Date().getTime());
        if (!this.f13491d.m14281f().m14286a() || valueOf.longValue() - this.f13493f.getTime() <= 3600000 || valueOf.longValue() - this.f13491d.m14282g().getTime() <= 86400000) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    private static GraphRequest m14456a(AccessToken accessToken, C2942b c2942b) {
        return new GraphRequest(accessToken, "me/permissions", new Bundle(), HttpMethod.GET, c2942b);
    }

    /* renamed from: b */
    private static GraphRequest m14462b(AccessToken accessToken, C2942b c2942b) {
        Bundle bundle = new Bundle();
        bundle.putString("grant_type", "fb_extend_sso_token");
        return new GraphRequest(accessToken, "oauth/access_token", bundle, HttpMethod.GET, c2942b);
    }

    /* renamed from: a */
    void m14465a(final C2939a c2939a) {
        if (Looper.getMainLooper().equals(Looper.myLooper())) {
            m14463b(c2939a);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C2983b f13472b;

                public void run() {
                    this.f13472b.m14463b(c2939a);
                }
            });
        }
    }

    /* renamed from: b */
    private void m14463b(C2939a c2939a) {
        final AccessToken accessToken = this.f13491d;
        if (accessToken == null) {
            if (c2939a != null) {
                c2939a.m14269a(new FacebookException("No current access token to refresh"));
            }
        } else if (this.f13492e.compareAndSet(false, true)) {
            this.f13493f = new Date();
            final Set hashSet = new HashSet();
            final Set hashSet2 = new HashSet();
            final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
            final C2982a c2982a = new C2982a();
            C2986e c2986e = new C2986e(C2983b.m14456a(accessToken, new C2942b(this) {
                /* renamed from: d */
                final /* synthetic */ C2983b f13476d;

                /* renamed from: a */
                public void mo3687a(C2987f c2987f) {
                    JSONObject b = c2987f.m14500b();
                    if (b != null) {
                        JSONArray optJSONArray = b.optJSONArray(C0861d.f2139k);
                        if (optJSONArray != null) {
                            atomicBoolean.set(true);
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                if (optJSONObject != null) {
                                    String optString = optJSONObject.optString("permission");
                                    String optString2 = optJSONObject.optString("status");
                                    if (!(C3048s.m14761a(optString) || C3048s.m14761a(optString2))) {
                                        optString2 = optString2.toLowerCase(Locale.US);
                                        if (optString2.equals("granted")) {
                                            hashSet.add(optString);
                                        } else if (optString2.equals("declined")) {
                                            hashSet2.add(optString);
                                        } else {
                                            Log.w("AccessTokenManager", "Unexpected status: " + optString2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }), C2983b.m14462b(accessToken, new C2942b(this) {
                /* renamed from: b */
                final /* synthetic */ C2983b f13478b;

                /* renamed from: a */
                public void mo3687a(C2987f c2987f) {
                    JSONObject b = c2987f.m14500b();
                    if (b != null) {
                        c2982a.f13486a = b.optString("access_token");
                        c2982a.f13487b = b.optInt("expires_at");
                    }
                }
            }));
            final C2939a c2939a2 = c2939a;
            c2986e.m14480a(new C2980a(this) {
                /* renamed from: g */
                final /* synthetic */ C2983b f13485g;

                /* renamed from: a */
                public void mo3691a(C2986e c2986e) {
                    AccessToken accessToken;
                    Throwable th;
                    try {
                        if (C2983b.m14457a().m14467b() == null || C2983b.m14457a().m14467b().m14284i() != accessToken.m14284i()) {
                            if (c2939a2 != null) {
                                c2939a2.m14269a(new FacebookException("No current access token to refresh"));
                            }
                            this.f13485g.f13492e.set(false);
                            if (c2939a2 != null && null != null) {
                                c2939a2.m14268a(null);
                            }
                        } else if (!atomicBoolean.get() && c2982a.f13486a == null && c2982a.f13487b == 0) {
                            if (c2939a2 != null) {
                                c2939a2.m14269a(new FacebookException("Failed to refresh access token"));
                            }
                            this.f13485g.f13492e.set(false);
                            if (c2939a2 != null && null != null) {
                                c2939a2.m14268a(null);
                            }
                        } else {
                            String str;
                            Collection collection;
                            Collection collection2;
                            Date date;
                            if (c2982a.f13486a != null) {
                                str = c2982a.f13486a;
                            } else {
                                str = accessToken.m14277b();
                            }
                            String h = accessToken.m14283h();
                            String i = accessToken.m14284i();
                            if (atomicBoolean.get()) {
                                collection = hashSet;
                            } else {
                                collection = accessToken.m14279d();
                            }
                            if (atomicBoolean.get()) {
                                collection2 = hashSet2;
                            } else {
                                collection2 = accessToken.m14280e();
                            }
                            AccessTokenSource f = accessToken.m14281f();
                            if (c2982a.f13487b != 0) {
                                date = new Date(((long) c2982a.f13487b) * 1000);
                            } else {
                                date = accessToken.m14278c();
                            }
                            AccessToken accessToken2 = new AccessToken(str, h, i, collection, collection2, f, date, new Date());
                            try {
                                C2983b.m14457a().m14466a(accessToken2);
                                this.f13485g.f13492e.set(false);
                                if (c2939a2 != null && accessToken2 != null) {
                                    c2939a2.m14268a(accessToken2);
                                }
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                accessToken = accessToken2;
                                th = th3;
                                this.f13485g.f13492e.set(false);
                                c2939a2.m14268a(accessToken);
                                throw th;
                            }
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        accessToken = null;
                        this.f13485g.f13492e.set(false);
                        if (!(c2939a2 == null || accessToken == null)) {
                            c2939a2.m14268a(accessToken);
                        }
                        throw th;
                    }
                }
            });
            c2986e.m14490h();
        } else if (c2939a != null) {
            c2939a.m14269a(new FacebookException("Refresh already in progress"));
        }
    }
}
