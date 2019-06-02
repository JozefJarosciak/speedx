package cn.sharesdk.framework.p011b.p012a;

import android.content.Context;
import android.text.TextUtils;
import com.mob.tools.utils.SharePrefrenceHelper;

/* compiled from: SharePrefrenceUtil */
/* renamed from: cn.sharesdk.framework.b.a.e */
public class C0589e {
    /* renamed from: c */
    private static C0589e f1265c;
    /* renamed from: a */
    private Context f1266a;
    /* renamed from: b */
    private SharePrefrenceHelper f1267b = new SharePrefrenceHelper(this.f1266a);

    private C0589e(Context context) {
        this.f1266a = context.getApplicationContext();
        this.f1267b.open("share_sdk", 1);
    }

    /* renamed from: a */
    public static C0589e m2039a(Context context) {
        if (f1265c == null) {
            f1265c = new C0589e(context.getApplicationContext());
        }
        return f1265c;
    }

    /* renamed from: a */
    public long m2040a() {
        return this.f1267b.getLong("service_time");
    }

    /* renamed from: b */
    public boolean m2050b() {
        Object string = this.f1267b.getString("upload_device_info");
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        return Boolean.parseBoolean(string);
    }

    /* renamed from: c */
    public boolean m2052c() {
        Object string = this.f1267b.getString("upload_user_info");
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        return Boolean.parseBoolean(string);
    }

    /* renamed from: d */
    public boolean m2054d() {
        Object string = this.f1267b.getString("trans_short_link");
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return Boolean.parseBoolean(string);
    }

    /* renamed from: e */
    public int m2055e() {
        String string = this.f1267b.getString("upload_share_content");
        if ("true".equals(string)) {
            return 1;
        }
        if ("false".equals(string)) {
            return -1;
        }
        return 0;
    }

    /* renamed from: a */
    public void m2042a(String str) {
        this.f1267b.putString("trans_short_link", str);
    }

    /* renamed from: b */
    public void m2049b(String str) {
        this.f1267b.putString("upload_device_info", str);
    }

    /* renamed from: c */
    public void m2051c(String str) {
        this.f1267b.putString("upload_user_info", str);
    }

    /* renamed from: d */
    public void m2053d(String str) {
        this.f1267b.putString("upload_share_content", str);
    }

    /* renamed from: a */
    public void m2046a(String str, String str2) {
        this.f1267b.putString("buffered_snsconf_" + str, str2);
    }

    /* renamed from: e */
    public String m2056e(String str) {
        return this.f1267b.getString("buffered_snsconf_" + str);
    }

    /* renamed from: a */
    public void m2041a(long j) {
        this.f1267b.putLong("device_time", Long.valueOf(j));
    }

    /* renamed from: f */
    public Long m2058f() {
        return Long.valueOf(this.f1267b.getLong("device_time"));
    }

    /* renamed from: a */
    public void m2047a(boolean z) {
        this.f1267b.putBoolean("connect_server", Boolean.valueOf(z));
    }

    /* renamed from: g */
    public boolean m2060g() {
        return this.f1267b.getBoolean("connect_server");
    }

    /* renamed from: b */
    public void m2048b(long j) {
        this.f1267b.putLong("connect_server_time", Long.valueOf(j));
    }

    /* renamed from: h */
    public Long m2061h() {
        return Long.valueOf(this.f1267b.getLong("connect_server_time"));
    }

    /* renamed from: a */
    public void m2044a(String str, Long l) {
        this.f1267b.putLong(str, l);
    }

    /* renamed from: f */
    public long m2057f(String str) {
        return this.f1267b.getLong(str);
    }

    /* renamed from: a */
    public void m2043a(String str, int i) {
        this.f1267b.putInt(str, Integer.valueOf(i));
    }

    /* renamed from: g */
    public int m2059g(String str) {
        return this.f1267b.getInt(str);
    }

    /* renamed from: a */
    public void m2045a(String str, Object obj) {
        this.f1267b.put(str, obj);
    }

    /* renamed from: h */
    public Object m2062h(String str) {
        return this.f1267b.get(str);
    }
}
