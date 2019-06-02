package p203u.aly;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import java.util.Map;
import p203u.aly.av.C5858e;
import p203u.aly.av.C5859f;

/* compiled from: UMCCStorageManager */
/* renamed from: u.aly.bs */
public class bs {
    /* renamed from: a */
    private static Context f18931a;

    /* compiled from: UMCCStorageManager */
    /* renamed from: u.aly.bs$a */
    private static final class C5929a {
        /* renamed from: a */
        private static final bs f18930a = new bs();
    }

    private bs() {
        if (f18931a == null) {
        }
    }

    /* renamed from: a */
    public static bs m21764a(Context context) {
        f18931a = context;
        return C5929a.f18930a;
    }

    /* renamed from: a */
    public void m21766a(bo boVar) {
        try {
            SQLiteDatabase a = ac.m21095a(f18931a).m21097a();
            String a2 = C5845a.m21068a(a);
            String a3 = bt.m21776a(System.currentTimeMillis());
            if (a2.equals("0")) {
                boVar.mo6179a("faild", false);
                return;
            }
            if (a2.equals(a3)) {
                C5845a.m21080b(a, boVar);
            } else {
                C5845a.m21076a(a, boVar);
            }
            ac.m21095a(f18931a).m21099c();
        } catch (Exception e) {
            boVar.mo6179a(Boolean.valueOf(false), false);
            ah.m21165d("load agg data error");
        } finally {
            ac.m21095a(f18931a).m21099c();
        }
    }

    /* renamed from: a */
    public void m21769a(bo boVar, Map<List<String>, C5947i> map) {
        try {
            C5845a.m21075a(ac.m21095a(f18931a).m21098b(), map.values());
            boVar.mo6179a("success", false);
        } catch (Exception e) {
            ah.m21165d("save agg data error");
        } finally {
            ac.m21095a(f18931a).m21099c();
        }
    }

    /* renamed from: a */
    public Map<String, List<C5858e>> m21765a() {
        Map<String, List<C5858e>> b;
        try {
            b = C5845a.m21079b(ac.m21095a(f18931a).m21097a());
        } catch (Exception e) {
            ah.m21165d("upload agg date error");
            return null;
        } finally {
            ac.m21095a(f18931a).m21099c();
        }
        return b;
    }

    /* renamed from: b */
    public Map<String, List<C5859f>> m21772b(bo boVar) {
        Map<String, List<C5859f>> a;
        try {
            a = C5845a.m21069a(boVar, ac.m21095a(f18931a).m21097a());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ac.m21095a(f18931a).m21099c();
        }
        return a;
    }

    /* renamed from: a */
    public void m21770a(bo boVar, boolean z) {
        try {
            C5845a.m21072a(ac.m21095a(f18931a).m21098b(), z, boVar);
        } catch (Exception e) {
            ah.m21165d("notifyUploadSuccess error");
        } finally {
            ac.m21095a(f18931a).m21099c();
        }
    }

    /* renamed from: a */
    public void m21767a(bo boVar, String str, long j, long j2) {
        try {
            C5845a.m21070a(ac.m21095a(f18931a).m21098b(), str, j, j2);
            boVar.mo6179a("success", false);
        } catch (Exception e) {
            ah.m21165d("package size to big or envelopeOverflowPackageCount exception");
        } finally {
            ac.m21095a(f18931a).m21099c();
        }
    }

    /* renamed from: a */
    public void m21768a(bo boVar, List<String> list) {
        try {
            C5845a.m21073a(boVar, ac.m21095a(f18931a).m21098b(), (List) list);
        } catch (Exception e) {
            ah.m21165d("saveToLimitCKTable exception");
        } finally {
            ac.m21095a(f18931a).m21099c();
        }
    }

    /* renamed from: b */
    public void m21773b(bo boVar, Map<String, C5949k> map) {
        try {
            C5845a.m21071a(ac.m21095a(f18931a).m21098b(), (Map) map, boVar);
        } catch (Exception e) {
            ah.m21165d("arrgetated system buffer exception");
        } finally {
            ac.m21095a(f18931a).m21099c();
        }
    }

    /* renamed from: b */
    public List<String> m21771b() {
        List<String> c;
        try {
            c = C5845a.m21081c(ac.m21095a(f18931a).m21097a());
        } catch (Exception e) {
            ah.m21165d("loadCKToMemory exception");
            return null;
        } finally {
            ac.m21095a(f18931a).m21099c();
        }
        return c;
    }

    /* renamed from: c */
    public void m21774c(bo boVar, Map<List<String>, C5947i> map) {
        try {
            C5845a.m21077a(boVar, ac.m21095a(f18931a).m21098b(), map.values());
        } catch (Exception e) {
            ah.m21165d("cacheToData error");
        } finally {
            ac.m21095a(f18931a).m21099c();
        }
    }
}
