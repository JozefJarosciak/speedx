package com.facebook;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3049t;

/* compiled from: ProfileManager */
/* renamed from: com.facebook.i */
final class C2990i {
    /* renamed from: a */
    private static volatile C2990i f13518a;
    /* renamed from: b */
    private final LocalBroadcastManager f13519b;
    /* renamed from: c */
    private final C2989h f13520c;
    /* renamed from: d */
    private Profile f13521d;

    C2990i(LocalBroadcastManager localBroadcastManager, C2989h c2989h) {
        C3049t.m14790a((Object) localBroadcastManager, "localBroadcastManager");
        C3049t.m14790a((Object) c2989h, "profileCache");
        this.f13519b = localBroadcastManager;
        this.f13520c = c2989h;
    }

    /* renamed from: a */
    static C2990i m14512a() {
        if (f13518a == null) {
            synchronized (C2990i.class) {
                if (f13518a == null) {
                    f13518a = new C2990i(LocalBroadcastManager.getInstance(C1472c.f()), new C2989h());
                }
            }
        }
        return f13518a;
    }

    /* renamed from: b */
    Profile m14516b() {
        return this.f13521d;
    }

    /* renamed from: c */
    boolean m14517c() {
        Profile a = this.f13520c.m14509a();
        if (a == null) {
            return false;
        }
        m14514a(a, false);
        return true;
    }

    /* renamed from: a */
    void m14515a(Profile profile) {
        m14514a(profile, true);
    }

    /* renamed from: a */
    private void m14514a(Profile profile, boolean z) {
        Profile profile2 = this.f13521d;
        this.f13521d = profile;
        if (z) {
            if (profile != null) {
                this.f13520c.m14510a(profile);
            } else {
                this.f13520c.m14511b();
            }
        }
        if (!C3048s.m14760a((Object) profile2, (Object) profile)) {
            m14513a(profile2, profile);
        }
    }

    /* renamed from: a */
    private void m14513a(Profile profile, Profile profile2) {
        Intent intent = new Intent("com.facebook.sdk.ACTION_CURRENT_PROFILE_CHANGED");
        intent.putExtra("com.facebook.sdk.EXTRA_OLD_PROFILE", profile);
        intent.putExtra("com.facebook.sdk.EXTRA_NEW_PROFILE", profile2);
        this.f13519b.sendBroadcast(intent);
    }
}
