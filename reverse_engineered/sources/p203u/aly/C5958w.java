package p203u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.umeng.analytics.C4731a;
import java.lang.reflect.Method;
import p203u.aly.av.C5869o;

/* compiled from: SessionTracker */
/* renamed from: u.aly.w */
public class C5958w {
    /* renamed from: c */
    private static String f19105c = null;
    /* renamed from: d */
    private static Context f19106d = null;
    /* renamed from: a */
    private final String f19107a = "a_start_time";
    /* renamed from: b */
    private final String f19108b = "a_end_time";

    /* renamed from: a */
    public C5869o m22035a(Context context) {
        SharedPreferences a = C5955u.m22014a(context);
        String string = a.getString("session_id", null);
        if (string == null) {
            return null;
        }
        long j = a.getLong("session_start_time", 0);
        long j2 = a.getLong("session_end_time", 0);
        long j3 = 0;
        if (j2 != 0) {
            j3 = j2 - j;
            if (Math.abs(j3) > 86400000) {
                j3 = 0;
            }
        }
        C5869o c5869o = new C5869o();
        c5869o.f18707b = string;
        c5869o.f18708c = j;
        c5869o.f18709d = j2;
        c5869o.f18710e = j3;
        double[] a2 = C4731a.m18618a();
        if (a2 != null) {
            c5869o.f18714j.f18643a = a2[0];
            c5869o.f18714j.f18644b = a2[1];
            c5869o.f18714j.f18645c = System.currentTimeMillis();
        }
        try {
            Class cls = Class.forName("android.net.TrafficStats");
            Method method = cls.getMethod("getUidRxBytes", new Class[]{Integer.TYPE});
            Method method2 = cls.getMethod("getUidTxBytes", new Class[]{Integer.TYPE});
            if (context.getApplicationInfo().uid == -1) {
                return null;
            }
            j = ((Long) method.invoke(null, new Object[]{Integer.valueOf(context.getApplicationInfo().uid)})).longValue();
            j3 = ((Long) method2.invoke(null, new Object[]{Integer.valueOf(r5)})).longValue();
            if (j > 0 && j3 > 0) {
                long j4 = a.getLong("uptr", -1);
                j2 = a.getLong("dntr", -1);
                a.edit().putLong("uptr", j3).putLong("dntr", j).commit();
                if (j4 > 0 && j2 > 0) {
                    j -= j2;
                    j3 -= j4;
                    if (j > 0 && j3 > 0) {
                        c5869o.f18713i.f18716a = j;
                        c5869o.f18713i.f18717b = j3;
                    }
                }
            }
            C5960y.m22057a(a, c5869o);
            m22032a(a);
            return c5869o;
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    private void m22032a(SharedPreferences sharedPreferences) {
        Editor edit = sharedPreferences.edit();
        edit.remove("session_start_time");
        edit.remove("session_end_time");
        edit.remove("a_start_time");
        edit.remove("a_end_time");
        edit.putString("activities", "");
        edit.commit();
    }

    /* renamed from: b */
    public String m22036b(Context context) {
        String c = af.m21119c(context);
        String a = C4731a.m18614a(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (a == null) {
            throw new RuntimeException("Appkey is null or empty, Please check AndroidManifest.xml");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(currentTimeMillis).append(a).append(c);
        f19105c = ag.m21146a(stringBuilder.toString());
        return f19105c;
    }

    /* renamed from: c */
    public void m22037c(Context context) {
        f19106d = context;
        SharedPreferences a = C5955u.m22014a(context);
        if (a != null) {
            Editor edit = a.edit();
            int i = a.getInt("versioncode", 0);
            int parseInt = Integer.parseInt(af.m21111a(f19106d));
            if (i != 0 && parseInt != i) {
                if (C5958w.m22034g(context) == null) {
                    m22031a(context, a);
                }
                m22039e(f19106d);
                C5943f.m21932a(f19106d).mo7226c();
                m22040f(f19106d);
            } else if (m22033b(a)) {
                ah.m21158b("Start new session: " + m22031a(context, a));
            } else {
                String string = a.getString("session_id", null);
                edit.putLong("a_start_time", System.currentTimeMillis());
                edit.putLong("a_end_time", 0);
                edit.commit();
                ah.m21158b("Extend current session: " + string);
            }
        }
    }

    /* renamed from: d */
    public void m22038d(Context context) {
        SharedPreferences a = C5955u.m22014a(context);
        if (a != null) {
            if (a.getLong("a_start_time", 0) == 0 && C4731a.f16609e) {
                ah.m21165d("onPause called before onResume");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            Editor edit = a.edit();
            edit.putLong("a_start_time", 0);
            edit.putLong("a_end_time", currentTimeMillis);
            edit.putLong("session_end_time", currentTimeMillis);
            edit.commit();
        }
    }

    /* renamed from: b */
    private boolean m22033b(SharedPreferences sharedPreferences) {
        long j = sharedPreferences.getLong("a_start_time", 0);
        long j2 = sharedPreferences.getLong("a_end_time", 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (j != 0 && currentTimeMillis - j < C4731a.f16613i) {
            ah.m21165d("onResume called before onPause");
            return false;
        } else if (currentTimeMillis - j2 > C4731a.f16613i) {
            return true;
        } else {
            return false;
        }
    }

    /* renamed from: a */
    private String m22031a(Context context, SharedPreferences sharedPreferences) {
        C5943f a = C5943f.m21932a(context);
        String b = m22036b(context);
        C5862n a2 = m22035a(context);
        Editor edit = sharedPreferences.edit();
        edit.putString("session_id", b);
        edit.putLong("session_start_time", System.currentTimeMillis());
        edit.putLong("session_end_time", 0);
        edit.putLong("a_start_time", System.currentTimeMillis());
        edit.putLong("a_end_time", 0);
        edit.putInt("versioncode", Integer.parseInt(af.m21111a(context)));
        edit.putString("versionname", af.m21117b(context));
        edit.commit();
        if (a2 != null) {
            a.mo7223a(a2);
        } else {
            a.mo7223a((C5869o) null);
        }
        return b;
    }

    /* renamed from: e */
    public boolean m22039e(Context context) {
        boolean z = false;
        SharedPreferences a = C5955u.m22014a(context);
        if (!(a == null || a.getString("session_id", null) == null)) {
            long j = a.getLong("a_start_time", 0);
            long j2 = a.getLong("a_end_time", 0);
            if (j > 0 && j2 == 0) {
                z = true;
                m22038d(context);
            }
            C5943f a2 = C5943f.m21932a(context);
            C5862n a3 = m22035a(context);
            if (a3 != null) {
                a2.mo7225b(a3);
            }
        }
        return z;
    }

    /* renamed from: f */
    public void m22040f(Context context) {
        SharedPreferences a = C5955u.m22014a(context);
        if (a != null) {
            String b = m22036b(context);
            Editor edit = a.edit();
            edit.putString("session_id", b);
            edit.putLong("session_start_time", System.currentTimeMillis());
            edit.putLong("session_end_time", 0);
            edit.putLong("a_start_time", System.currentTimeMillis());
            edit.putLong("a_end_time", 0);
            edit.putInt("versioncode", Integer.parseInt(af.m21111a(context)));
            edit.putString("versionname", af.m21117b(context));
            edit.commit();
            ah.m21158b("Restart session: " + b);
        }
    }

    /* renamed from: g */
    public static String m22034g(Context context) {
        if (f19105c == null) {
            f19105c = C5955u.m22014a(context).getString("session_id", null);
        }
        return f19105c;
    }

    /* renamed from: a */
    public static String m22030a() {
        try {
            if (f19105c == null) {
                f19105c = C5955u.m22014a(f19106d).getString("session_id", null);
            }
        } catch (Exception e) {
        }
        return f19105c;
    }
}
