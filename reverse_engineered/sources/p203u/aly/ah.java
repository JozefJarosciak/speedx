package p203u.aly;

import android.util.Log;
import java.util.Formatter;

/* compiled from: MLog */
/* renamed from: u.aly.ah */
public class ah {
    /* renamed from: a */
    public static boolean f18581a = false;
    /* renamed from: b */
    private static String f18582b = "MobclickAgent";

    /* renamed from: a */
    public static void m21156a(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                ah.m21159b(f18582b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            ah.m21159b(str, str2, null);
        } catch (Throwable th) {
            ah.m21157a(th);
        }
    }

    /* renamed from: b */
    public static void m21161b(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                ah.m21166d(f18582b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            ah.m21166d(str, str2, null);
        } catch (Throwable th) {
            ah.m21157a(th);
        }
    }

    /* renamed from: c */
    public static void m21164c(String str, Object... objArr) {
        try {
            String str2 = "";
            if (str.contains("%")) {
                ah.m21163c(f18582b, new Formatter().format(str, objArr).toString(), null);
                return;
            }
            if (objArr != null) {
                str2 = (String) objArr[0];
            }
            ah.m21163c(str, str2, null);
        } catch (Throwable th) {
            ah.m21157a(th);
        }
    }

    /* renamed from: a */
    public static void m21157a(Throwable th) {
        ah.m21166d(f18582b, null, th);
    }

    /* renamed from: a */
    public static void m21155a(String str, Throwable th) {
        ah.m21159b(f18582b, str, th);
    }

    /* renamed from: b */
    public static void m21160b(String str, Throwable th) {
        ah.m21166d(f18582b, str, th);
    }

    /* renamed from: a */
    public static void m21153a(String str) {
        ah.m21154a(f18582b, str, null);
    }

    /* renamed from: b */
    public static void m21158b(String str) {
        ah.m21159b(f18582b, str, null);
    }

    /* renamed from: c */
    public static void m21162c(String str) {
        ah.m21163c(f18582b, str, null);
    }

    /* renamed from: d */
    public static void m21165d(String str) {
        ah.m21166d(f18582b, str, null);
    }

    /* renamed from: a */
    public static void m21154a(String str, String str2, Throwable th) {
        if (!f18581a) {
            return;
        }
        if (th != null) {
            if (str2 != null) {
                Log.d(str, th.toString() + ":  [" + str2 + "]");
            } else {
                Log.d(str, th.toString());
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                Log.d(str, "        at  " + stackTraceElement.toString());
            }
        } else if (str2 == null) {
            try {
                Log.w(str, "the msg is null!");
            } catch (Throwable th2) {
            }
        } else {
            Log.d(str, str2);
        }
    }

    /* renamed from: b */
    public static void m21159b(String str, String str2, Throwable th) {
        if (!f18581a) {
            return;
        }
        if (th != null) {
            if (str2 != null) {
                Log.i(str, th.toString() + ":  [" + str2 + "]");
            } else {
                Log.i(str, th.toString());
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                Log.i(str, "        at  " + stackTraceElement.toString());
            }
        } else if (str2 == null) {
            try {
                Log.w(str, "the msg is null!");
            } catch (Throwable th2) {
            }
        } else {
            Log.i(str, str2);
        }
    }

    /* renamed from: c */
    public static void m21163c(String str, String str2, Throwable th) {
        if (!f18581a) {
            return;
        }
        if (th != null) {
            if (str2 != null) {
                Log.w(str, th.toString() + ":  [" + str2 + "]");
            } else {
                Log.w(str, th.toString());
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                Log.w(str, "        at  " + stackTraceElement.toString());
            }
        } else if (str2 == null) {
            try {
                Log.w(str, "the msg is null!");
            } catch (Throwable th2) {
            }
        } else {
            Log.w(str, str2);
        }
    }

    /* renamed from: d */
    public static void m21166d(String str, String str2, Throwable th) {
        if (!f18581a) {
            return;
        }
        if (th != null) {
            if (str2 != null) {
                Log.e(str, th.toString() + ":  [" + str2 + "]");
            } else {
                Log.e(str, th.toString());
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                Log.e(str, "        at  " + stackTraceElement.toString());
            }
        } else if (str2 == null) {
            try {
                Log.w(str, "the msg is null!");
            } catch (Throwable th2) {
            }
        } else {
            Log.e(str, str2);
        }
    }
}
