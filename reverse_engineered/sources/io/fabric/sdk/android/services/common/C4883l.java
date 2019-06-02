package io.fabric.sdk.android.services.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.C1520c;
import java.util.Collection;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/* compiled from: IdManager */
/* renamed from: io.fabric.sdk.android.services.common.l */
public class C4883l {
    /* renamed from: d */
    private static final Pattern f17171d = Pattern.compile("[^\\p{Alnum}]");
    /* renamed from: e */
    private static final String f17172e = Pattern.quote("/");
    /* renamed from: a */
    C4868c f17173a;
    /* renamed from: b */
    C4865b f17174b;
    /* renamed from: c */
    boolean f17175c;
    /* renamed from: f */
    private final ReentrantLock f17176f = new ReentrantLock();
    /* renamed from: g */
    private final C4885m f17177g;
    /* renamed from: h */
    private final boolean f17178h;
    /* renamed from: i */
    private final boolean f17179i;
    /* renamed from: j */
    private final Context f17180j;
    /* renamed from: k */
    private final String f17181k;
    /* renamed from: l */
    private final String f17182l;
    /* renamed from: m */
    private final Collection<C1468h> f17183m;

    public C4883l(Context context, String str, String str2, Collection<C1468h> collection) {
        if (context == null) {
            throw new IllegalArgumentException("appContext must not be null");
        } else if (str == null) {
            throw new IllegalArgumentException("appIdentifier must not be null");
        } else if (collection == null) {
            throw new IllegalArgumentException("kits must not be null");
        } else {
            this.f17180j = context;
            this.f17181k = str;
            this.f17182l = str2;
            this.f17183m = collection;
            this.f17177g = new C4885m();
            this.f17173a = new C4868c(context);
            this.f17178h = C4877i.m19162a(context, "com.crashlytics.CollectDeviceIdentifiers", true);
            if (!this.f17178h) {
                C1520c.h().mo6215a("Fabric", "Device ID collection disabled for " + context.getPackageName());
            }
            this.f17179i = C4877i.m19162a(context, "com.crashlytics.CollectUserIdentifiers", true);
            if (!this.f17179i) {
                C1520c.h().mo6215a("Fabric", "User information collection disabled for " + context.getPackageName());
            }
        }
    }

    /* renamed from: a */
    private String m19182a(String str) {
        return str == null ? null : f17171d.matcher(str).replaceAll("").toLowerCase(Locale.US);
    }

    /* renamed from: a */
    public String m19184a() {
        String str = this.f17182l;
        if (str != null) {
            return str;
        }
        SharedPreferences a = C4877i.m19145a(this.f17180j);
        str = a.getString("crashlytics.installation.id", null);
        if (str == null) {
            return m19181a(a);
        }
        return str;
    }

    /* renamed from: b */
    public String m19185b() {
        return this.f17181k;
    }

    /* renamed from: c */
    public String m19186c() {
        return m19183b(VERSION.RELEASE);
    }

    /* renamed from: d */
    public String m19187d() {
        return m19183b(VERSION.INCREMENTAL);
    }

    /* renamed from: e */
    public String m19188e() {
        return String.format(Locale.US, "%s/%s", new Object[]{m19183b(Build.MANUFACTURER), m19183b(Build.MODEL)});
    }

    /* renamed from: b */
    private String m19183b(String str) {
        return str.replaceAll(f17172e, "");
    }

    /* renamed from: f */
    public String m19189f() {
        String str = "";
        if (!this.f17178h) {
            return str;
        }
        str = m19193j();
        if (str != null) {
            return str;
        }
        SharedPreferences a = C4877i.m19145a(this.f17180j);
        str = a.getString("crashlytics.installation.id", null);
        if (str == null) {
            return m19181a(a);
        }
        return str;
    }

    /* renamed from: a */
    private String m19181a(SharedPreferences sharedPreferences) {
        this.f17176f.lock();
        try {
            String string = sharedPreferences.getString("crashlytics.installation.id", null);
            if (string == null) {
                string = m19182a(UUID.randomUUID().toString());
                sharedPreferences.edit().putString("crashlytics.installation.id", string).commit();
            }
            this.f17176f.unlock();
            return string;
        } catch (Throwable th) {
            this.f17176f.unlock();
        }
    }

    /* renamed from: g */
    public String m19190g() {
        return this.f17177g.m19196a(this.f17180j);
    }

    /* renamed from: h */
    synchronized C4865b m19191h() {
        if (!this.f17175c) {
            this.f17174b = this.f17173a.m19124a();
            this.f17175c = true;
        }
        return this.f17174b;
    }

    /* renamed from: i */
    public String m19192i() {
        if (!this.f17178h) {
            return null;
        }
        C4865b h = m19191h();
        if (h != null) {
            return h.f17148a;
        }
        return null;
    }

    /* renamed from: j */
    public String m19193j() {
        if (!this.f17178h) {
            return null;
        }
        String string = Secure.getString(this.f17180j.getContentResolver(), "android_id");
        if ("9774d56d682e549c".equals(string)) {
            return null;
        }
        return m19182a(string);
    }
}
