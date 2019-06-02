package com.mob.commons;

import android.content.Context;
import com.mob.tools.utils.SharePrefrenceHelper;

/* compiled from: ProcessLevelSPDB */
/* renamed from: com.mob.commons.f */
public class C4250f {
    /* renamed from: a */
    private static SharePrefrenceHelper f14928a;

    /* renamed from: h */
    private static synchronized void m16894h(Context context) {
        synchronized (C4250f.class) {
            if (f14928a == null) {
                f14928a = new SharePrefrenceHelper(context.getApplicationContext());
                f14928a.open("mob_commons", 1);
            }
        }
    }

    /* renamed from: a */
    public static synchronized String m16879a(Context context) {
        String string;
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            string = f14928a.getString("key_ext_info");
        }
        return string;
    }

    /* renamed from: a */
    public static synchronized void m16881a(Context context, String str) {
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            f14928a.putString("key_ext_info", str);
        }
    }

    /* renamed from: b */
    public static synchronized long m16882b(Context context) {
        long j;
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            j = f14928a.getLong("wifi_last_time");
        }
        return j;
    }

    /* renamed from: a */
    public static synchronized void m16880a(Context context, long j) {
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            f14928a.putLong("wifi_last_time", Long.valueOf(j));
        }
    }

    /* renamed from: c */
    public static synchronized String m16885c(Context context) {
        String string;
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            string = f14928a.getString("wifi_last_info");
        }
        return string;
    }

    /* renamed from: b */
    public static synchronized void m16884b(Context context, String str) {
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            f14928a.putString("wifi_last_info", str);
        }
    }

    /* renamed from: c */
    public static synchronized void m16886c(Context context, String str) {
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            f14928a.putString("key_cellinfo", str);
        }
    }

    /* renamed from: d */
    public static synchronized String m16887d(Context context) {
        String string;
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            string = f14928a.getString("key_cellinfo");
        }
        return string;
    }

    /* renamed from: b */
    public static synchronized void m16883b(Context context, long j) {
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            f14928a.putLong("key_cellinfo_next_total", Long.valueOf(j));
        }
    }

    /* renamed from: d */
    public static synchronized void m16888d(Context context, String str) {
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            f14928a.putString("key_switches", str);
        }
    }

    /* renamed from: e */
    public static synchronized String m16889e(Context context) {
        String string;
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            string = f14928a.getString("key_switches");
        }
        return string;
    }

    /* renamed from: e */
    public static synchronized void m16890e(Context context, String str) {
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            if (str == null) {
                f14928a.remove("key_data_url");
            } else {
                f14928a.putString("key_data_url", str);
            }
        }
    }

    /* renamed from: f */
    public static synchronized String m16891f(Context context) {
        String string;
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            string = f14928a.getString("key_data_url");
        }
        return string;
    }

    /* renamed from: f */
    public static synchronized void m16892f(Context context, String str) {
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            if (str == null) {
                f14928a.remove("key_conf_url");
            } else {
                f14928a.putString("key_conf_url", str);
            }
        }
    }

    /* renamed from: g */
    public static synchronized String m16893g(Context context) {
        String string;
        synchronized (C4250f.class) {
            C4250f.m16894h(context);
            string = f14928a.getString("key_conf_url");
        }
        return string;
    }
}
