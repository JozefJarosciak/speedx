package com.baidu.platform.comapi.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.sys.C0869a;
import com.avos.avoscloud.AVStatus;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import java.util.Hashtable;
import org.json.JSONException;
import org.json.JSONObject;

public class PermissionCheck {
    /* renamed from: a */
    private static final String f3860a = PermissionCheck.class.getSimpleName();
    /* renamed from: b */
    private static Context f3861b;
    /* renamed from: c */
    private static String f3862c;
    /* renamed from: d */
    private static Hashtable<String, String> f3863d;
    /* renamed from: e */
    private static LBSAuthManager f3864e = null;
    /* renamed from: f */
    private static LBSAuthManagerListener f3865f = null;
    /* renamed from: g */
    private static C1217c f3866g = null;

    /* renamed from: com.baidu.platform.comapi.util.PermissionCheck$c */
    public interface C1217c {
        /* renamed from: a */
        void mo2669a(C1274b c1274b);
    }

    /* renamed from: com.baidu.platform.comapi.util.PermissionCheck$a */
    private static class C1273a implements LBSAuthManagerListener {
        private C1273a() {
        }

        public void onAuthResult(int i, String str) {
            if (str != null) {
                C1274b c1274b = new C1274b();
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.has("status")) {
                        c1274b.f3855a = jSONObject.optInt("status");
                    }
                    if (jSONObject.has("appid")) {
                        c1274b.f3857c = jSONObject.optString("appid");
                    }
                    if (jSONObject.has("uid")) {
                        c1274b.f3856b = jSONObject.optString("uid");
                    }
                    if (jSONObject.has(AVStatus.MESSAGE_TAG)) {
                        c1274b.f3858d = jSONObject.optString(AVStatus.MESSAGE_TAG);
                    }
                    if (jSONObject.has("token")) {
                        c1274b.f3859e = jSONObject.optString("token");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (PermissionCheck.f3866g != null) {
                    PermissionCheck.f3866g.mo2669a(c1274b);
                }
            }
        }
    }

    /* renamed from: com.baidu.platform.comapi.util.PermissionCheck$b */
    public static class C1274b {
        /* renamed from: a */
        public int f3855a = 0;
        /* renamed from: b */
        public String f3856b = "-1";
        /* renamed from: c */
        public String f3857c = "-1";
        /* renamed from: d */
        public String f3858d = "";
        /* renamed from: e */
        public String f3859e;

        public String toString() {
            return String.format("=============================================\n----------------- 鉴权错误信息 ------------\nsha1;package:%s\nkey:%s\nerrorcode: %d uid: %s appid %s msg: %s\n请仔细核查 SHA1、package与key申请信息是否对应，key是否删除，平台是否匹配\nerrorcode为230时，请参考论坛链接：\nhttp://bbs.lbsyun.baidu.com/forum.php?mod=viewthread&tid=106461\n=============================================\n", new Object[]{C1276a.m4820a(PermissionCheck.f3861b), PermissionCheck.f3862c, Integer.valueOf(this.f3855a), this.f3856b, this.f3857c, this.f3858d});
        }
    }

    public static void destory() {
        f3866g = null;
        f3861b = null;
        f3865f = null;
    }

    public static void init(Context context) {
        ApplicationInfo applicationInfo;
        f3861b = context;
        try {
            applicationInfo = f3861b.getPackageManager().getApplicationInfo(f3861b.getPackageName(), 128);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            applicationInfo = null;
        }
        if (applicationInfo != null) {
            f3862c = applicationInfo.metaData.getString("com.baidu.lbsapi.API_KEY");
        }
        if (f3863d == null) {
            f3863d = new Hashtable();
        }
        if (f3864e == null) {
            f3864e = LBSAuthManager.getInstance(f3861b);
        }
        if (f3865f == null) {
            f3865f = new C1273a();
        }
        Object obj = "";
        try {
            obj = context.getPackageManager().getPackageInfo(f3861b.getPackageName(), 0).applicationInfo.loadLabel(f3861b.getPackageManager()).toString();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        Bundle b = C1281e.m4850b();
        f3863d.put("mb", b.getString("mb"));
        f3863d.put("os", b.getString("os"));
        f3863d.put(C0869a.f2167h, b.getString(C0869a.f2167h));
        f3863d.put("imt", C0844a.f2048d);
        f3863d.put(C0825c.f1951a, b.getString(C0825c.f1951a));
        f3863d.put("cpu", b.getString("cpu"));
        f3863d.put("glr", b.getString("glr"));
        f3863d.put("glv", b.getString("glv"));
        f3863d.put("resid", b.getString("resid"));
        f3863d.put("appid", "-1");
        f3863d.put("ver", C0844a.f2048d);
        f3863d.put("screen", String.format("(%d,%d)", new Object[]{Integer.valueOf(b.getInt("screen_x")), Integer.valueOf(b.getInt("screen_y"))}));
        f3863d.put("dpi", String.format("(%d,%d)", new Object[]{Integer.valueOf(b.getInt("dpi_x")), Integer.valueOf(b.getInt("dpi_y"))}));
        f3863d.put("pcn", b.getString("pcn"));
        f3863d.put("cuid", b.getString("cuid"));
        f3863d.put("name", obj);
    }

    public static synchronized int permissionCheck() {
        int i = 0;
        synchronized (PermissionCheck.class) {
            if (!(f3864e == null || f3865f == null || f3861b == null)) {
                i = f3864e.authenticate(false, "lbs_androidsdk", f3863d, f3865f);
            }
        }
        return i;
    }

    public static void setPermissionCheckResultListener(C1217c c1217c) {
        f3866g = c1217c;
    }
}
